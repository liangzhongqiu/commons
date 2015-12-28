package com.lzq.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 页码
 * @author zhongqiu
 * @date 2015年11月14日
 * @time 下午8:56:25
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Page {
	/**当前页*/
	@XmlElement(name="CurrentPage")
	@JsonProperty(value="current_page")
	private Integer currentPage;
	/**每页多少行*/
	@XmlElement(name="PageSize")
	@JsonProperty(value="page_size")
	private Integer pageSize;
	/**总行数*/
	@XmlElement(name="Rows")
	private Integer rows;
	
	public Page() {
		super();
	}

	public Page(Integer currentPage, Integer pageSize, Integer rows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.rows = rows;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return new StringBuilder("{currentPage=").append(currentPage).append(",pageSize=")
				.append(pageSize).append(",rows=").append(rows).append("}").toString();
	}
	
	
	
}
