package com.lzq.commons;
/**
 * 页码
 * @author zhongqiu
 * @date 2015年11月14日
 * @time 下午8:56:25
 */
public class Page {
	/**当前页*/
	private Integer currentPage;
	/**每页多少行*/
	private Integer pageSize;
	/**总行数*/
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
