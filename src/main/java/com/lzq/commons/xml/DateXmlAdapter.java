package com.lzq.commons.xml;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.lzq.commons.date.DateTime;

public abstract class DateXmlAdapter extends XmlAdapter<String,Date> {
	
	private String pattern;
	
	protected DateXmlAdapter(){
		
	}
	protected DateXmlAdapter(String pattern){
		this.pattern = pattern;
	}
	
	@Override
	public Date unmarshal(String v) throws Exception {
		if(v == null){
			return null;
		}
		return DateTime.toDate(v,pattern);
	}
	
	@Override
	public String marshal(Date v) throws Exception {
		if(v == null){
			return null;
		}
		return DateTime.toString(v, pattern);
	}
	
}
