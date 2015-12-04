package com.lzq.commons;
/**
 * 排序
 * @author zhongqiu
 * @date 2015年11月14日
 * @time 下午8:57:38
 */
public class Sort {

	/**排序字段*/
	private String field;
	/**排序方式，默认true表示升序*/
	private boolean asc = true;
	
	public Sort() {
		super();
	}
	
	public Sort(String field){
		this();
		this.field = field;
	}
	
	public Sort(String field, boolean asc) {
		this(field);
		this.asc = asc;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	
}
