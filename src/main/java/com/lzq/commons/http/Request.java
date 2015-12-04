package com.lzq.commons.http;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午2:09:18
 */
public abstract class Request {

	protected final Log log = LogFactory.getLog(getClass());
	//标识请求器是否已初始化，默认未初始化
	private boolean hasInit = false;
	//连接超时时间，默认10秒
	private int socketTimeout = 10*1000;
	//传输超时时间，默认30秒
	private int connectTimeout = 30*1000;
	//HTTP请求器
	private CloseableHttpClient httpClient;
	//请求器配置
	private RequestConfig requestConfig;
	
	public Request() {
		//
	}

	/**
	 * 初始化
	 */
	protected void init(){
		setHttpClient(HttpClients.createDefault());
		resetRequestConfig();
		setHasInit(true);
	}

	/**
	 * 请求访问
	 * 注意：读写响应数据后请释放请求
	 *    <pre>
	 *    Request request = new Request();
	 *    HttpGet get = new HttpGet("http://www.ifeng.com/");
	 *    HttpEntity entity = request.execute(get).getEntity();
	 *    String responseString = EntityUtils.toString(entity);
	 *    get.abort();//释放请求
	 *    </pre>
	 * @param requestMethod 请求方式，GET或POST
	 * @return HttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected final HttpResponse execute(HttpRequestBase requestMethod) throws ClientProtocolException, IOException{
		if(!isHasInit()){
			init();
		}
		if(!isHasInit()){
			throw new UnsupportedOperationException("初始化失败，终止启动请求访问。");
		}
		requestMethod.setConfig(requestConfig);
		return httpClient.execute(requestMethod);
	}
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public RequestConfig getRequestConfig() {
		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	protected void resetRequestConfig(){
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	}
	
	public boolean isHasInit() {
		return hasInit;
	}

	public void setHasInit(boolean hasInit) {
		this.hasInit = hasInit;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}


	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void close(){
		if(httpClient!=null){
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
