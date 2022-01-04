package com.sinocarbon.polaris.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImagesTransformUtils {

	public static String transform(String paramValue, String regex) {
		Pattern pattern = Pattern.compile(regex);
		int length;
		int index;
		String[] splitchars;
		String returnStrResult = "";
		List<String> paternList = ImagesTransformUtils.getMatcherPattenList(pattern, paramValue, regex);

		List<String> valueList = new ArrayList<String>();
		length = paternList.size();
		for (index = 0; index < length; index++) {
			String mdImgName = paternList.get(index);
			int lastIndex = mdImgName.lastIndexOf(Constant.SPOT);
			String suffix = mdImgName.substring(lastIndex, mdImgName.length());
			String imgName = mdImgName.substring(0, lastIndex);
			lastIndex = imgName.lastIndexOf(Constant.UNDERLINE);
			imgName = imgName.substring(0, lastIndex);
			mdImgName = imgName + suffix;
			valueList.add(String.valueOf(mdImgName));
		}

		splitchars = pattern.split(paramValue);
		length = splitchars.length;
		returnStrResult = "";
		if (length > 0) {
			for (index = 0; index < length - 1; index++) {
				returnStrResult += splitchars[index];
				returnStrResult += valueList.get(index);
			}
			returnStrResult += splitchars[index];
		} else {
			index = 0;
		}
		length = valueList.size();
		while (index < length) {
			returnStrResult += valueList.get(index);
			index++;
		}
		return returnStrResult;
	}

	public static String transform(String filePath, String paramValue, String regex, String splitchars) {
		Pattern pattern = Pattern.compile(regex);
		int length;
		int index;
		String mdImgName;
		String newMdImgName;
		String[] splitArray;
		String returnStrResult = Constant.EMPTY;
		List<String> paternList = ImagesTransformUtils.getMatcherPattenList(pattern, paramValue, regex);
		length = paternList.size();
		log.info("匹配到需要替换的图片路径有：" + length + "条");

		List<String> valueList = new ArrayList<String>();
		for (index = 0; index < length; index++) {
			mdImgName = paternList.get(index);
			newMdImgName = Constant.RIGHT_BRACKET + filePath
					+ mdImgName.substring(mdImgName.indexOf(splitchars), mdImgName.length());
			valueList.add(String.valueOf(newMdImgName));
		}
		log.info("需要替换的图片路径已完成=========");
		splitArray = pattern.split(paramValue);
		length = splitArray.length;
		if (length > 0) {
			for (index = 0; index < length - 1; index++) {
				returnStrResult += splitArray[index];
				returnStrResult += valueList.get(index);
			}
			returnStrResult += splitArray[index];
		} else {
			index = 0;
		}
		log.info("重新拼装数据1完成=========");
		length = valueList.size();
		while (index < length) {
			returnStrResult += valueList.get(index);
			index++;
		}
		log.info("重新拼装数据2完成=========");
		return returnStrResult;
	}

	private static List<String> getMatcherPattenList(Pattern pattern, String paramValue, String regex) {
		Matcher matcher = null;
		List<String> paternList = new ArrayList<String>();
		if (!Constant.EMPTY.equals(paramValue) && paramValue != null) {
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(paramValue);
			while (matcher.find()) {
				paternList.add(matcher.group());
			}
		}
		return paternList;
	}

}

