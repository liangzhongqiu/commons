package com.lzq.commons.em;
/**
 * 日期匹配权举
 * @author zhongqiu
 * @date 2016年1月6日
 * @time 下午2:06:53
 */
public enum DateTimeEnum {
	
	UNDEFINED(null),
	LONG("yyyy-MM-dd HH:mm:ss"),
	MIDDLE("yyyy-MM-dd"),
	SHORT("yyyy-MM");
	
	private String pattern;
	
	private DateTimeEnum(String pattern){
		this.pattern = pattern;
	}
	
	public String  pattern(){
		return pattern;
	}
	
}
