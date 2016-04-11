package com.lzq.commons;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public abstract class ImgUtil {
	
	
	public static void main(String[] args) throws IOException{
		File org = new File("C:\\Users\\zhongqiu\\Desktop\\pic\\1.jpg");
		File dest = new File("C:\\Users\\zhongqiu\\Desktop\\pic\\2.jpg");
		createThumbImg(org, dest,400,400);
	}
	
	public static void createThumbImg(File org,File dest,double coefficient) throws IOException{
		Image image = ImageIO.read(org);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		width = (int)Math.round(width*coefficient);
		height = (int)Math.round(height*coefficient);
		createThumbImg(image, dest, width, height);
	}
	
	private static void createThumbImg(Image orgImage,File dest,int width,int height)throws IOException{
		FileOutputStream out = null;
		try {
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(orgImage, 0, 0, width, height,null);
			out = new FileOutputStream(dest);
			JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(out);
			jpegImageEncoder.encode(bufferedImage);
		} finally {
			if(out != null ){
				out.close();
			}
		}
	}
	
	public static void createThumbImg(File org,File dest,int width,int height) throws IOException{
			Image image = ImageIO.read(org);
			createThumbImg(image, dest, width, height);
	}
}
