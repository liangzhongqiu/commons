package com.lzq.commons;

import java.nio.charset.Charset;

import com.lzq.commons.charset.Coding;

import junit.framework.TestCase;

public class CodingTest extends TestCase {
	
	public CodingTest(String name){
		super(name);
	}
	
	public void testFields(){
		String name = "UTF-8";
		Charset charset = Charset.forName(name);
		Coding coding = new Coding(name);
		assertEquals(charset,coding.getCharset());
		assertEquals(name,coding.getCharsetName());
	}
}
