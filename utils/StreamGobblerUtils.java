package com.sinocarbon.polaris.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 用于处理Runtime.getRuntime().exec产生的错误流及输出流
 * 
 */
@Slf4j
public class StreamGobblerUtils extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamGobblerUtils.class);

	InputStream is;
	String type;
	OutputStream os;

	public StreamGobblerUtils(InputStream is, String type) {
		this(is, type, null);
	}

	public StreamGobblerUtils(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			if (os != null)
				pw = new PrintWriter(os);

			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
				LOGGER.info(type + ">" + line);
			}

			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
//			ioe.printStackTrace();
			log.error(ioe.getMessage(), ioe);
		} finally {
			if (pw != null) {
				pw.close();
			}
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e.getMessage(), e);
			}

		}
	}

}
