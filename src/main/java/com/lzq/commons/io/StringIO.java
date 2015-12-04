package com.lzq.commons.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月9日
 * @time 下午3:45:53
 */
public abstract class StringIO {
	/**
	 * 从输入流中读出字符串
	 * @param in 输入流
	 * @param charset 字符集
	 * @param keepIntact 是否原样输出（保留换行）
	 * @return String
	 * @throws IOException
	 */
	public static String read(InputStream in,Charset charset,boolean  keepIntact)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(in,charset));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			boolean hasRead = false;
			while((line=br.readLine())!=null){
				sb.append(line);
				if(keepIntact){//原样输出，换行
					sb.append("\n");
					hasRead = true;
				}
			}
			if(keepIntact && hasRead){//删除最后一个\n
				sb.delete(sb.length()-1,sb.length());
			}
		}finally{
			if(br!=null){br.close();}
			if(in!=null){in.close();}
		}
		return sb.toString();
	}
	/**
	 * 从文件中读出字符串
	 * @param file 文件
	 * @param charset 字符集
	 * @param keepIntact 是否原样输出
	 * @return String
	 * @throws IOException
	 */
	public static String read(File file,Charset charset,boolean keepIntact) throws IOException{
		FileInputStream in = new FileInputStream(file);
		return read(in, charset, keepIntact);
	}
	/**
	 * 
	 * @param path 文件目录
	 * @param charset 字符集
	 * @param keepIntact 是否原样输出
	 * @return String
	 * @throws IOException
	 */
	public static String read(String path,Charset charset,boolean keepIntact) throws IOException{
		return read(new File(path), charset, keepIntact);
	}
}
