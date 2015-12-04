package com.lzq.commons.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午4:55:26
 */
public class DateTime {

	/**
	 * 日期串转化成日期对象
	 * @param dateString 日期串
	 * @param pattern 匹配格式
	 * @return Date
	 * @throws ParseException
	 */
	public static Date toDate(String dateString,String pattern) throws ParseException{
		return simpleDateFormat(pattern).parse(dateString);
	} 
	/**
	 * 日期对象转化为日期串
	 * @param date 日期对象
	 * @param pattern 匹配格式
	 * @return
	 */
	public static String toString(Date date,String pattern){
		return simpleDateFormat(pattern).format(date);
	}
	//
	private static SimpleDateFormat simpleDateFormat(String pattern){
		return new SimpleDateFormat(pattern);
	}
	/**
	 * 日期迁移或后移某段时间
	 * @param date 需要变动的日期
	 * @param millisecond 一段时间，毫秒数
	 * @return Date
	 */
	public static Date backOrForwardToDate(Date date,long millisecond){
		long time = date.getTime() + millisecond;
		return new Date(time);
	}
	/**
	 * 日期串迁移或后移某段时间
	 * @param dateString 日期串
	 * @param pattern 匹配格式
	 * @param millisecond 变动的毫秒数
	 * @return Date
	 * @throws ParseException
	 */
	public static Date backOrForwardToDate(String dateString,String pattern,long millisecond) throws ParseException{
		Date date = toDate(dateString, pattern);
		long time = date.getTime() + millisecond;
		return new Date(time);
	}
	/**
	 * 迁移或后移某段时间
	 * @param date 需要变动的日期
	 * @param pattern 匹配格式
	 * @param millisecond 一段时间，毫秒数
	 * @return String
	 */
	public static String backOrForwardToString(Date date,String pattern,long millisecond){
		return toString(backOrForwardToDate(date, millisecond), pattern);
	}
	
	/**
	 * 日期串迁移或后移某段时间
	 * @param dateString 日期串
	 * @param pattern 匹配格式
	 * @param millisecond 变动的毫秒数
	 * @return String
	 * @throws ParseException
	 */
	public static String backOrForwardToString(String dateString,String pattern,long millisecond) throws ParseException{
		return toString(backOrForwardToDate(dateString, pattern, millisecond), pattern);
	}
}
