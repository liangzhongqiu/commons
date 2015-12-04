package com.lzq.commons.json;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午4:09:56
 */
@SuppressWarnings("rawtypes")
public class JsonParser {

	//JSON处理器
	private ObjectMapper objectMapper;
	//格式化，便于查看
	private boolean format = false;
	
	public JsonParser() {
		// TODO Auto-generated constructor stub
	}
	public JsonParser(ObjectMapper objectMapper){
		super();
		this.objectMapper = objectMapper;
	}
	public JsonParser(ObjectMapper objectMapper,boolean format){
		this(objectMapper);
		this.format = format;
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT,format);
	}
	/**
	 * 对象转化为JSON字符串
	 * @param object 需要转化JSON串的对象
	 * @return String
	 * @throws JsonProcessingException
	 */
	public String toJsonString(Object object) throws JsonProcessingException{
		return objectMapper.writeValueAsString(object);
	}
	/**
	 * JSON串转化为对象
	 * @param jsonString JSON串
	 * @param clazz 输出对象类
	 * @return T
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T>T fromJsonStr(String jsonString,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(jsonString, clazz);
	}
	
	public <K, V> Map<K, V>fromJsonStrToMap(String jsonStr,Class<? extends Map> mapType,Class<K> keyType,Class<V> valueType) throws JsonParseException, JsonMappingException, IOException{
		JavaType javaType = objectMapper.getTypeFactory().constructMapType(mapType,keyType,valueType);
		return objectMapper.readValue(jsonStr, javaType);
	}
	
	public <T>Collection<T> fromJsonStrToList(String jsonStr,Class<? extends Collection> listType,Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(listType,valueType);
		return objectMapper.readValue(jsonStr,javaType);
	}
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	public boolean isFormat() {
		return format;
	}
	public void setFormat(boolean format) {
		this.format = format;
	}
	
	
	
}
