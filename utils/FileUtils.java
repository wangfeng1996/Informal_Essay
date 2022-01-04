package com.sinocarbon.polaris.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
	
	/**
	 * 删除文件
	 * 
	 * @param files
	 */
	public static boolean deleteFile(File... files) throws Exception {
		for (File file : files) {
			if (file.exists()) {
				if (!file.delete()) {
					log.error(file.getName() + "文件删除失败！");
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取文件的md5值 ，有可能不是32位
	 * 
	 * @param filePath 文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String md5HashCode(File file) throws FileNotFoundException,IOException {
		FileInputStream fis = new FileInputStream(file);
		return md5HashCode(fis);
	}
	
	/**
	 * java获取文件的md5值
	 * 
	 * @param fis 输入流
	 * @return
	 * @throws IOException 
	 */
	public static String md5HashCode(InputStream fis) throws IOException {
		try {
			// 拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			fis.close();
			// 转换并返回包含16个元素字节数组,返回数值范围为-128到127
			byte[] md5Bytes = md.digest();
			BigInteger bigInt = new BigInteger(1, md5Bytes);// 1代表绝对值
			return bigInt.toString(16);// 转换为16进制
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return StringUtils.EMPTY;
		}finally {
			if(fis != null) {
				fis.close();
			}
		}
	}

}
