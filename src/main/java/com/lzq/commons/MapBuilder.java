package com.lzq.commons;

import java.util.Map;
/**
 * 
 * @author zhongqiu
 * @date 2015年11月16日
 * @time 下午5:05:29
 * @param <K>
 * @param <V>
 */
public class MapBuilder<K,V> {
	
	protected Map<K,V> map;
	
	public MapBuilder(Map<K, V> map){
		this.map = map;
	}
	
	public MapBuilder<K,V> put(K k,V v){
		map.put(k, v);
		return this;
	}

	public MapBuilder<K, V> putAll(Map<? extends K, ? extends V> m){
		map.putAll(m);
		return this;
	}
	
	public Map<K, V> getMap() {
		return map;
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}
	
	public String toString(){
		return map.toString();
	}
	
}
