/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gae.java.smartconsumer.util;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
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
