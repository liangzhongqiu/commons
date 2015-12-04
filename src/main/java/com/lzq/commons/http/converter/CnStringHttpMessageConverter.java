package com.lzq.commons.http.converter;


import org.springframework.http.converter.StringHttpMessageConverter;

import com.lzq.commons.charset.Coding;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月9日
 * @time 下午10:13:41
 */
public class CnStringHttpMessageConverter extends StringHttpMessageConverter {

	private Coding coding;
	
	public CnStringHttpMessageConverter(Coding coding) {
		super(coding.getCharset());
		this.coding = coding;
	}

	public Coding getCoding() {
		return coding;
	}

}
