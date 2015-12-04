package com.lzq.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 打印查看属性值
 * @author zhongqiu
 * @date 2015年11月13日
 * @time 上午10:11:34
 */
public abstract class EntityBeen {
	
	/**
	 * 将map中的键对应的值复制到当前对象属性中
	 * @param mapOfFieldsAndValues
	 */
	public void setFieldsValueFromMap(Map<String,Object> mapOfFieldsAndValues){
		if(mapOfFieldsAndValues == null){
			return;
		}
		Class<?> clazz = getClass();
		for(Map.Entry<String,Object> entry : mapOfFieldsAndValues.entrySet()){
			String key = entry.getKey();
			if(key == null){
				continue;
			}
			Object object = entry.getValue();
			try {
				Method setMethod = clazz.getDeclaredMethod("set"+key.substring(0,1).toUpperCase()+key.substring(1),object.getClass());
				setMethod.invoke(this,object);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 将对象属性以及属性值（包括父类）复制到Map中
	 * @return
	 */
	public Map<String,Object> toMap(){
		Class<?> clazz = getClass();
		Field[] fields;
		Map<String,Object> map = new LinkedHashMap<>();
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			fields = clazz.getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				try {
					map.put(field.getName(),field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	/**
	 * 输出
	 */
	public String toString(){
		return toMap().toString();
	}
}
