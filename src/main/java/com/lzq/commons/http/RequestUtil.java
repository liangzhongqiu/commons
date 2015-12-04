package com.lzq.commons.http;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 请求&响应工具类
 * @author zhongqiu
 *
 */
public final class RequestUtil {

	private RequestUtil(){}
	/**
	 * 获取客户端的ip地址
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request){
		 String ip = request.getHeader("x-forwarded-for");
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
	}
	/**
	 * @see #getSessionValue(HttpSession, String)
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSessionValue(HttpServletRequest request,String key){
		return getSessionValue(request.getSession(), key);
	}
	/**
	 * 读取session中某个键对应的值
	 * @param session HttpSession对象
	 * @param key 键
	 * @return
	 */
	public static Object getSessionValue(HttpSession session,String key){
		return key==null?null:session.getAttribute(key);
	}
	/**
	 * @see #setSessionValue(HttpSession, String, Object)
	 * @param request
	 * @param key
	 * @param object
	 */
	public static void setSessionValue(HttpServletRequest request,String key,Object object){
		setSessionValue(request.getSession(), key, object);
	}
	/**
	 * 将键对应的值存入session
	 * @param session HttpSession对象
	 * @param key 键
	 * @param object 值
	 */
	public static void setSessionValue(HttpSession session,String key,Object object){
		session.setAttribute(key, object);
	}
	/**
	 * @see #getServletRealPath(HttpSession, String)
	 * @param request
	 * @param path
	 * @return
	 */
	public static String getServletRealPath(HttpServletRequest request,String path){
		return getServletRealPath(request.getSession(), path);
	}
	/**
	 * 根据路径获取改路径在服务器的真实物理地址
	 * @param session HttpSession对象
	 * @param path 路径：相对路径或者绝对路劲；null表示跟目录("/")
	 * @return
	 */
	public static String getServletRealPath(HttpSession session,String path){
		path = path == null?"/":path;
		return session.getServletContext().getRealPath(path);
	}
	/**
	 * @see #clearSessionValues(HttpSession)
	 * @param request
	 */
	public static void clearSessionValues(HttpServletRequest request){
		clearSessionValues(request.getSession());
	}
	/**
	 * 清空session中的存值
	 * @param session HttpSession对象
	 */
	public static void clearSessionValues(HttpSession session){
		@SuppressWarnings("unchecked")
		Enumeration<String> enums = session.getAttributeNames();
		String sName = null;
		while (enums.hasMoreElements()) {
			sName = (String) enums.nextElement();
			session.removeAttribute(sName);
		}
	}
}
