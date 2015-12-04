package com.lzq.commons;

import java.util.Collection;
import java.util.List;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月16日
 * @time 下午8:00:15
 * @param <E>
 */
public class ListBuilder<E> {
	
	private List<E> list;
	
	public ListBuilder(List<E> list){
		this.list = list;
	}
	
	public ListBuilder<E> add(E e){
		list.add(e);
		return this;
	}
	
	public ListBuilder<E> addAll(Collection<? extends E> c){
		list.addAll(c);
		return this;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
	
	public String toString(){
		return list.toString();
	}
}
