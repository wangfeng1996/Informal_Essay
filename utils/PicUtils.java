package com.sinocarbon.polaris.commons.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
public class PicUtils {

	private static final long COMMONT = 1024;
	private static final long MIN_SIZE = 800;

	public static void commpressPicForWidth(String srcPath, String desPath, String smallFile) throws Exception {
		if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(desPath)) {
			return;
		}
		File srcFile = new File(srcPath);
		log.info("图片压缩开始=========" + srcPath);
		long srcFileSize = srcFile.length();
		long size = srcFileSize / COMMONT;
		// 计算宽高
		BufferedImage bim = ImageIO.read(srcFile);
		int srcWidth = bim.getWidth();
		int srcHeight = bim.getHeight();
		log.info("源图片宽高=" + srcWidth + "*" + srcHeight + "；大小=" + size + "kb");
		if(size > MIN_SIZE) {
			Thumbnails.of(srcFile).scale(1f).outputQuality(1f).toFile(desPath);
			log.info("进行大小的压缩===============");
		}
	}

	/**
	 * 根据指定大小和指定精度压缩图片
	 * 
	 * @param srcPath     源图片地址
	 * @param desPath     目标图片地址
	 * @param desFilesize 指定图片大小，单位kb
	 * @param accuracy    精度，递归压缩的比率，建议小于0.9
	 * @return
	 */
	public static String commpressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy)
			throws Exception {
		if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(desPath)) {
			return null;
		}
		File srcFile = new File(srcPath);
		long srcFileSize = srcFile.length();
		System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");

		// 1、先转换成jpg
		Thumbnails.of(srcPath).scale(1f).toFile(desPath);
		// 递归压缩，直到目标文件大小小于desFileSize
		commpressPicCycle(desPath, desFileSize, accuracy);

		File desFile = new File(desPath);
		log.info("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
		log.info("图片压缩完成！");
		return desPath;
	}

	private static void commpressPicCycle(String desPath, long desFileSize, double accuracy) throws IOException {
		File srcFileJPG = new File(desPath);
		// long srcFileSizeJPG = srcFileJPG.length();
		// 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
		// if (srcFileSizeJPG <= desFileSize * 1024) {
		// return;
		// }
		// 计算宽高
		BufferedImage bim = ImageIO.read(srcFileJPG);
		int srcWdith = bim.getWidth();
		log.info("源图片宽：" + srcWdith);
		if (srcWdith <= 550) {
			return;
		}
		int srcHeigth = bim.getHeight();
		int desWidth = new BigDecimal(srcWdith).multiply(BigDecimal.valueOf(accuracy)).intValue();
		int desHeight = new BigDecimal(srcHeigth).multiply(BigDecimal.valueOf(accuracy)).intValue();

		Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
		commpressPicCycle(desPath, desFileSize, accuracy);
	}
	
	/**
	 * 添加无重复水印，处于中间位置
	 * 
	 * @param srcPath          源图地址
	 * @param desPath          输出目标地址
	 * @param waterMarkContent 水印内容
	 * @param fontName         字体
	 * @param transparency     透明度
	 * @param fontSize         字体大小
	 * @param degree           旋转角度
	 * @param color            字体颜色（RGB)
	 * @param fileSuffix       文件后缀
	 */
	public static void handleTextWaterMarkByNoRepeat(String srcPath, String desPath, String waterMarkContent,
			String fontName, float transparency, Integer fontSize, Integer degree, Color color, String fileSuffix) {
		log.info(
				"添加无重复水印，处于中间位置，参数值为：srcPath={},desPath={},waterMarkContent={},transparency={},fontSize={},degree={},fileSuffix={}",
				srcPath, desPath, waterMarkContent, transparency, fontSize, degree, fileSuffix);
		OutputStream outImgStream = null;
		try {
			// 得到文件
			File srcImgFile = new File(srcPath);
			// 文件转化为图片
			Image srcImg = ImageIO.read(srcImgFile);
			// 获取图片的宽
			int srcWidth = srcImg.getWidth(null);
			// 获取图片的高
			int srcHeight = srcImg.getHeight(null);
			// 如果字体为null，则计算字体大小，自适应图片
			if (fontSize == null) {
				// 斜边长 / 水印内容的个数 * 3/4
				fontSize = (int) ((Math.sqrt(srcWidth * srcWidth + srcHeight * srcHeight)) / waterMarkContent.length()
						* (3.0 / 4.0));
				log.info("计算字体大小，自适应图片===========");
			}
			Font font = new Font(fontName, Font.PLAIN, fontSize);
			// 如果旋转角度为null，则计算旋转角度，自适应图片
			if (degree == null) {
				degree = -(int) (Math.atan((double) srcHeight * 1.0 / (double) srcWidth * 1.0) / Math.PI * 180);
				log.info("计算旋转角度，自适应图片===========");
			}
			log.info(
					"srcWidth=" + srcWidth + ",srcHeight=" + srcHeight + ",fontSize=" + fontSize + ",degree=" + degree);
			// 加水印
			BufferedImage image = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			// 得到画笔
			Graphics2D graphics2d = getGraphics2D(srcImg, srcWidth, srcHeight, image, true, font, degree, transparency,
					color);
			JLabel label = new JLabel(waterMarkContent);
			FontMetrics metrics = label.getFontMetrics(font);
			// 文字水印的宽
			int waterMarkContentWidth = metrics.stringWidth(label.getText());
			graphics2d.drawString(waterMarkContent, 0 - waterMarkContentWidth / 2, 0);
			// 释放资源
			graphics2d.dispose();
			outImgStream = new FileOutputStream(desPath);
			ImageIO.write(image, fileSuffix, outImgStream);

		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			try {
				if (outImgStream != null) {
					outImgStream.flush();
					outImgStream.close();
				}
			} catch (Exception e) {
				log.error("{}", e);
			}
		}
	}

	/**
	 * 添加多个文字水印，铺满画布
	 * 
	 * @param srcPath          源图地址
	 * @param desPath          输出目标地址
	 * @param waterMarkContent 水印内容
	 * @param fontName         字体
	 * @param transparency     透明度
	 * @param fontSize         字体大小
	 * @param degree           旋转角度
	 * @param color            字体颜色（RGB)
	 * @param fileSuffix       文件后缀
	 */
	public static void handleTextWaterMark(String srcPath, String desPath, String waterMarkContent, String fontName,
			float transparency, Integer fontSize, Integer degree, Color color, String fileSuffix) {
		log.info("添加多个文字水印，铺满画布==========");
		OutputStream outImgStream = null;
		Graphics2D graphics2d = null;
		try {
			File srcImgFile = new File(srcPath);// 得到文件
			Image srcImg = ImageIO.read(srcImgFile);// 文件转化为图片
			int srcWidth = srcImg.getWidth(null);// 获取图片的宽
			int srcHeight = srcImg.getHeight(null);// 获取图片的高
			Font font = new Font(fontName, Font.PLAIN, fontSize);
			log.info("srcWidth=" + srcWidth + ",srcHeight=" + srcHeight);
			// 加水印
			BufferedImage image = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			JLabel label = new JLabel(waterMarkContent);
			FontMetrics metrics = label.getFontMetrics(font);
			// 文字水印的宽
			int waterMarkContentWidth = metrics.stringWidth(label.getText());
			// 间隔
			int interval = 200;
			log.info("waterMarkContentWidth=" + waterMarkContentWidth + ",interval=" + interval);
			// 图片的高 除以 文字水印的宽 ——> 打印的行数(以文字水印的宽为间隔)
			int rowsNumber = (int) (Math.sqrt(srcWidth * srcWidth + srcHeight * srcHeight) / interval);
			// 图片的宽 除以 文字水印的宽 ——> 每行打印的列数(以文字水印的宽为间隔)
			int columnsNumber = (int) ((Math.sqrt(3.0) / 2 * srcWidth + srcHeight / 2) / interval);
			log.info("rowsNumber=" + rowsNumber + ",columnsNumber=" + columnsNumber);
			if (rowsNumber < 2 && columnsNumber < 2) {
				log.info("倾斜放画布中间========");
				graphics2d = getGraphics2D(srcImg, srcWidth, srcHeight, image, true, font, degree, transparency, color);
				graphics2d.drawString(waterMarkContent, 0 - waterMarkContentWidth / 2, 0);
				// 释放资源
				graphics2d.dispose();
				outImgStream = new FileOutputStream(desPath);
				ImageIO.write(image, "png", outImgStream);
			} else {
				log.info("倾斜铺满画布========");
				graphics2d = getGraphics2D(srcImg, srcWidth, srcHeight, image, false, font, degree, transparency,
						color);
				for (int j = 0; j < rowsNumber; j++) {
					for (int i = 0; i < columnsNumber; i++) {
						// 画出水印,并设置水印位置
						int x = i * (waterMarkContentWidth + interval) - srcHeight / 2;
						int y = j * interval;
						graphics2d.drawString(waterMarkContent, x, y);
					}
				}
			}
			// 释放资源
			graphics2d.dispose();
			outImgStream = new FileOutputStream(desPath);
			ImageIO.write(image, fileSuffix, outImgStream);

		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			try {
				if (outImgStream != null) {
					outImgStream.flush();
					outImgStream.close();
				}
			} catch (Exception e) {
				log.error("{}", e);
			}
		}
	}

	private static Graphics2D getGraphics2D(Image srcImg, int srcWidth, int srcHeight, BufferedImage image,
			boolean isTranslate, Font font, Integer degree, float transparency, Color color) throws Exception {
		// 得到画笔
		Graphics2D graphics2d = image.createGraphics();
		graphics2d.drawImage(srcImg, 0, 0, srcWidth, srcHeight, null);
		if (isTranslate) {
			graphics2d.translate(srcWidth / 2, srcHeight / 2);
		}
		graphics2d.setColor(color);
		graphics2d.setFont(font);
		// 设置水印文字透明度
		graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
		if (null != degree) {
			// 设置水印旋转
			graphics2d.rotate(Math.toRadians(degree));
		}
		return graphics2d;
	}

}
