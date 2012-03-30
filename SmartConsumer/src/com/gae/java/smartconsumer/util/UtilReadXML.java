package com.gae.java.smartconsumer.util;

import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * 
 * @author Nixforest
 *
 */
public class UtilReadXML {
  //read content of file xml
    public Document ReadFileXML(String fileName)throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dbf.newDocumentBuilder();
        File file = new File(fileName);
        Document document = build.parse(file);
        return document;
    }
    //read content xml directly
    public Document ReadContentXML(String content)throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dbf.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(content));
        //Document document = build.parse(new ByteArrayInputStream(content.getBytes()));
        Document document = build.parse(is);
        return document;
    }
}
