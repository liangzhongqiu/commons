package com.lzq.commons;

import junit.framework.TestCase;

public class PageTest extends TestCase {
	
	public PageTest(String name){
		super(name);
	}
	
	public  void testGetMethod(){
		Page page = new Page(1,10,10);
		assertEquals(Integer.valueOf(1),page.getCurrentPage());
		assertEquals(Integer.valueOf(10),page.getPageSize());
		assertEquals(Integer.valueOf(10), page.getRows());
	}
}
