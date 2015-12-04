package com.lzq.commons.charset;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午2:29:35
 */
public class Coding {

	//字符集名称
	private String charsetName;
	//字符集
	private Charset charset;
	
	public Coding() {
		super();
	}
	public Coding(String charsetName){
		init(charsetName);
	}
	
	private void init(Object object){
		if (object instanceof Charset) {
			charset = (Charset)object;
		}else if(object instanceof String){
			charset = Charset.forName((String)object);
		}else{
			throw new UnsupportedCharsetException(object.toString());
		}
		charsetName = charset.name();
	}
	
	public static void main(String[] args) {
		Coding coding = new Coding();
		coding.setCharsetName("UTF-8");
		System.err.println(coding.getCharsetName());
	}
	
	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		init(charsetName);
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		init(charset);
	}

	
	
}
