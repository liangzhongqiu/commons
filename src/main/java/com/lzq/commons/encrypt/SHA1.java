package com.lzq.commons.encrypt;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Formatter;

/**
 * 
 * @author zhongqiu
 * @date 2015年11月7日
 * @time 下午2:54:28
 */
public class SHA1 {


	public static String SHA1Encode(String params,Charset charset){
		String result = "";
		try {
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.reset();
			md.update(params.getBytes(charset));
			// HEX输出
			byte[] hash = md.digest();
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			result = formatter.toString();
			formatter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
