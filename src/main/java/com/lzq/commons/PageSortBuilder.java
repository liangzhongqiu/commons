package com.lzq.commons;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月15日
 * @time 上午8:40:55
 */
public abstract class PageSortBuilder {
	
	public static String build(Page page,Sort[] sorts){
		StringBuilder sql = new StringBuilder();
		if(sorts != null){
			sql.append(" ORDER BY ");
			int i = 0,len = sorts.length;
			for(Sort sort : sorts){
				sql.append(sort.getField()).append(" ").append((sort.isAsc()?"ASC":"DESC"));
				if(i++<len-1){
					sql.append(",");
				}
			}
		}
		if(page != null){
			int offset = (page.getCurrentPage()-1)*page.getPageSize();
			sql.append(" LIMIT ").append(page.getPageSize()).append(" OFFSET ").append(offset);
		}
		return sql.toString();
	}
}
