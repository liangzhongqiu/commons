package com.lzq.commons.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.lzq.commons.charset.Coding;

/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午4:49:43
 * @param <T>
 */
public class XmlParser {

	//字符编码
	private Coding coding;
	//是否格式化，默认格式化
	private boolean format = true;
	//是否忽略头声明信息
	private boolean ignoreHeader = false;
	
	public XmlParser() {
		
	}
	public XmlParser(Coding coding, boolean format, boolean ignoreHeader) {
		setCoding(coding);
		setFormat(format);
		setIgnoreHeader(ignoreHeader);
	}

	/**
	 * 输出XML串
	 * @return String
	 * @throws JAXBException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public String toXml(Object object) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		final StringWriter sw  = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,format);
		marshaller.setProperty(Marshaller.JAXB_ENCODING,coding.getCharsetName());
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT,ignoreHeader);
		marshaller.marshal(object,sw);
		return sw.toString();
	}
	/**
	 * XML串转化成对应的对象
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public Object fromXml(String xml,Class<?> classType) throws JAXBException{
		  JAXBContext context = JAXBContext.newInstance(classType);
          Unmarshaller unmarshaller = context.createUnmarshaller();
          Object object = unmarshaller.unmarshal(new StringReader(xml));
          return object;
	}

	public Coding getCoding() {
		return coding;
	}

	public void setCoding(Coding coding) {
		this.coding = coding;
	}

	public boolean isFormat() {
		return format;
	}

	public void setFormat(boolean format) {
		this.format = format;
	}

	public boolean isIgnoreHeader() {
		return ignoreHeader;
	}

	public void setIgnoreHeader(boolean ignoreHeader) {
		this.ignoreHeader = ignoreHeader;
	}

}
