package com.lzq.commons.xml;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jParser {
	
	private String content;
	private Element root;
	
	public Dom4jParser(String content)throws DocumentException{
		this.content = content;
	    root = DocumentHelper.parseText(content).getRootElement();
	}
	
	public String readValue(String node){
		return root.elementTextTrim(node);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}
	
	
}
