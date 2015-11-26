package com.example.helloworld.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.helloworld.R;
import android.app.Activity;
import android.os.Bundle;

public class XmlParserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xml_parser);
		startParserXmlFile();
	}
	
	public void startParserXmlFile(){		
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("/mnt/sdcard/update.xml"));
			Element element = doc.getDocumentElement();
			if(element == null){
				return;
			}
			String path = getValue(element, "path");
			String version_id = getValue(element, "version_id");
			String version = getValue(element, "aim_version");
			String content = getValue(element, "content");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(Element item, String str) {        
		NodeList n = item.getElementsByTagName(str); 
		String value = getElementValue(n.item(0));
		return value == null ? value:value.trim();
	}
	
	public String getElementValue( Node elem ) {
		Node child;
		if( elem != null){
			if (elem.hasChildNodes()){
				for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
					if( child.getNodeType() == Node.TEXT_NODE  ){
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}
}
