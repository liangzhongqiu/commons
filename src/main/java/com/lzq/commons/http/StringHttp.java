package com.lzq.commons.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.lzq.commons.charset.Coding;
/**
 * HTTP请求，响应字符串处理
 * @author zhongqiu
 * @date 2015年11月10日
 * @time 上午8:34:15
 */
public class StringHttp extends Request {

	/**编码器*/
	private Coding coding;
	/**HTTP contentType*/
	private String defaultMimeType = "text/html";
	
	public StringHttp(){}
	public StringHttp(Coding coding){
		setCoding(coding);
	}
	public StringHttp(Coding coding,String mimeType){
		this(coding);
		setDefaultMimeType(mimeType);
	}
	/**
	 * 
	 * @param requestBase 请求方式
	 * @return String
	 * @throws IOException
	 */
	protected String request(HttpRequestBase requestBase)throws IOException{
		log.info("请求信息>>>\n"+requestBase.getRequestLine().toString());
		String responseResult = null;
		try{
			HttpResponse response = execute(requestBase);
			int status = response.getStatusLine().getStatusCode();
			log.info("响应码<<<\n"+status);
			if(HttpStatus.SC_OK == status){
				responseResult = EntityUtils.toString(response.getEntity(),coding.getCharset());
				log.info("响应体<<<\n"+responseResult);
			}
		}finally{
			requestBase.abort();
		}
		return responseResult;
	}
	/**
	 * GET请求
	 * @param url 请求地址
	 * @return String
	 * @throws IOException
	 */
	public String get(String url)throws IOException{
		HttpGet get = new HttpGet(url);
		return request(get);
	}
	/**
	 * POST请求，请求体
	 * @param url 请求地址
	 * @param requestBody 请求体
	 * @param mimeType content类型 null时启用默认 {@link #defaultMimeType}
	 * @return String
	 * @throws IOException
	 */
	public String post(String url,String requestBody,String mimeType)throws IOException{
		mimeType = mimeType == null ? defaultMimeType : mimeType;
		log.info("请求体>>>\n"+requestBody);
		HttpPost post = new HttpPost(url);
		StringEntity se = new StringEntity(requestBody,ContentType.create(mimeType,coding.getCharset()));
		post.setEntity(se);
		return request(post);
	}
	/**
	 * POST请求，键值对
	 * @param url 请求地址
	 * @param paramsMap 键值
	 * @param mimeType content类型 null时启用默认 {@link #defaultMimeType}
	 * @return String
	 * @throws IOException
	 */
	public String post(String url,Map<String, String> paramsMap,String mimeType)throws IOException{
		mimeType = mimeType == null ? defaultMimeType : mimeType;
		HttpPost post = new HttpPost(url);
		log.info("请求参数>>>\n"+paramsMap.toString());
		List<NameValuePair> nvs = new ArrayList<>(paramsMap.size());
		for(Map.Entry<String,String> entry : paramsMap.entrySet()){
			nvs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(nvs,coding.getCharset()));
		return request(post);
	}
	
	public Coding getCoding() {
		return coding;
	}
	public void setCoding(Coding coding) {
		this.coding = coding;
	}
	public String getDefaultMimeType() {
		return defaultMimeType;
	}
	public void setDefaultMimeType(String defaultMimeType) {
		this.defaultMimeType = defaultMimeType;
	}
	
}
