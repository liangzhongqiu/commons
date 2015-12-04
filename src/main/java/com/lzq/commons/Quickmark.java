package com.lzq.commons;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月3日
 * @time 下午9:32:01
 */
public abstract class Quickmark {

	/**
	 * 将内容写入二维码中
	 * @param markContent 需要写入的内容
	 * @param width 二维码宽
	 * @param height  二维码高
	 * @param charsetName 字符编码名称
	 * @return 缓冲图片
	 * @throws WriterException
	 */
	public static BufferedImage fromQuickmark(String markContent,int width,int height,String charsetName) throws WriterException{
		Hashtable<EncodeHintType,String> hins = new Hashtable<>();
		hins.put(EncodeHintType.CHARACTER_SET,charsetName);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(markContent, BarcodeFormat.QR_CODE,width,height,hins);
		BufferedImage image = new BufferedImage(bitMatrix.getWidth(),bitMatrix.getHeight(),
				BufferedImage.TYPE_INT_RGB); 
		for (int x = 0; x < width; x++) { 
			for (int y = 0; y < height; y++) { 
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			} 
		}
		return image;
	}
	
}
