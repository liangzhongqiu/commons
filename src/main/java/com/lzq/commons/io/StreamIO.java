package com.lzq.commons.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author zhongqiu
 * @date 2015年11月12日
 * @time 下午3:59:59
 */
public abstract class StreamIO {
	
	private static final int BUFFER = 1024 * 8;
	
	/**
	 * 输入流>>输出流
	 * @param in 输入流
	 * @param out 输出流
	 * @return 长度
	 * @throws IOException
	 */
	public static long write(InputStream in,OutputStream out) throws IOException{
		byte[] bts = new byte[BUFFER];
		int readed = -1;
		long len = 0;
		try{
			while((readed = in.read(bts))!=-1){
				out.write(bts, 0,readed);
				len += readed;
			}
		}finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
		return len;
	}
	/**
	 * 从输入流写到文件中
	 * @param in
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long write(InputStream in,File file) throws IOException{
		FileOutputStream out = new FileOutputStream(file);
		return write(in, out);
	}
	/**
	 * 
	 * @param in 输入流
	 * @param path 文件目录
	 * @return
	 * @throws IOException
	 */
	public static long write(InputStream in,String path)throws IOException{
		return write(in, new File(path));
	}
}
