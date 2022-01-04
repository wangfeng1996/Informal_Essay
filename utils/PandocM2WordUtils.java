package com.sinocarbon.polaris.commons.utils;

import java.io.File;
import java.io.FileWriter;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PandocM2WordUtils {

	private static final String BAT_PATH = "/md2doc";
	private static final String SYSTEM = "os.name";
	private static final String WINDOWS = "Windows";
	private static final int LINUX_SYSTEM = 2;

	/**
	 * 将markdown文件转换为word文件
	 * 
	 * @param mdStr      markdown语言字符串
	 * @param filePath   文件路径
	 * @param fileName   文件名
	 * @param batBaseStr 执行命令的脚本基础
	 */
	public static void mdToWord(String mdStr, String filePath, String fileName) throws Exception {
		// 如果文件夹不存在，则创建文件夹
		File filedir = new File(filePath);
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		String mdFileName = fileName + Constant.MD_SUFFIX;
		String mdFilePath = filePath + Constant.SLASH + mdFileName;
		String batPath = filePath + BAT_PATH;
		int osName = getSystemName();
		if (osName == LINUX_SYSTEM) {
			batPath += Constant.SH_SUFFIX;
		} else {
			batPath += Constant.BAT_SUFFIX;
		}
		log.info("开始生成MD文件======================");
		FileWriter fw = new FileWriter(mdFilePath);
		fw.write(mdStr, 0, mdStr.length());// 写入文件
		fw.flush();
		fw.close();
		log.info("生成MD文件成功=====================");

		if (osName == LINUX_SYSTEM) {
			exec(Constant.SH + Constant.BLANK_SPACE + batPath + Constant.BLANK_SPACE + fileName + Constant.BLANK_SPACE
					+ filePath);
		} else {
			exec(batPath + Constant.BLANK_SPACE + fileName + Constant.BLANK_SPACE + filePath);
		}
		log.info("调用程序关闭=====================");
		log.info("删除MD文件开始：" + mdFilePath);
		File mdFile = new File(mdFilePath);
		if (FileUtils.deleteFile(mdFile)) {
			log.info("删除MD文件成功=====================");
		}
	}

	private static void exec(String execStr) throws Exception {
		log.info("pandoc执行中：" + execStr);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(Integer.MAX_VALUE);
		try {
			CommandLine commandLine = CommandLine.parse(execStr);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();

			executor.setWatchdog(watchdog);
			log.info("pandoc正在生成word=============");
			executor.execute(commandLine, resultHandler);
			log.info("pandoc生成word结束=============");
			log.info("pandoc等待1秒的返回=============");
			resultHandler.waitFor(1000); // 等待返回
			log.info("pandoc等待返回结束=============");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			watchdog.destroyProcess();// 终止进程
			log.info("pandoc终止进程=====================");
		}

	}

	private static int getSystemName() throws Exception {
		String osName = System.getProperties().getProperty(SYSTEM);
		if (osName.contains(WINDOWS)) {
			return 1;
		}
		return LINUX_SYSTEM;
	}

	// public static void main(String[] args) {
	// String mdStr =
	// "## 二级标题 (fnsdjifsajoj) ![](http://47.105.114.98//assets/1_small.jpg)
	// ![](/assets/12334ab12334dw12334_small.png)";
	// System.out.println(ImagesTransformUtils.transform(mdStr));
	// replaceImagePath(mdStr);
	// }
}
