/**
 * UtilReadXML.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.util;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Class handle to read xml.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class UtilReadXML {
    /**
     * Read content of file xml.
     * @param fileName file name
     * @return Document
     * @throws Exception maybe happen
     */
    public Document readFileXML(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dbf.newDocumentBuilder();
        File file = new File(fileName);
        Document document = build.parse(file);
        return document;
    }
    /**
     * Read content xml directly.
     * @param content XML content
     * @return Document
     * @throws Exception maybe happen
     */
    public Document readContentXML(String content)throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dbf.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(content));
        //Document document = build.parse(new ByteArrayInputStream(content.getBytes()));
        Document document = build.parse(is);
        return document;
    }
}
