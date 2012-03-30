package com.gae.java.smartconsumer.util;

import java.io.IOException;
import org.w3c.dom.*;

/**
 * 
 * @author Nixforest
 *
 */
public class GetDealFunction {
    /**
     *  Get a value from Tag
     * [Give the description for method].
     * @param sTag
     * @param eElement
     * @param location
     * @return
     */
    private static String getTagValue(String sTag, Element eElement, int location) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node)nlList.item(location);
        return nValue.getNodeValue();
    }
    
    /**
     * Get deal information from http://www.hotdeal.vn
     * [Give the description for method].
     * @param link
     * @return
     */
    private String getFromHotDealVn(String link){
        String content = "";
        try{
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(link);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);
            
            // Duyệt danh sách thẻ div
            NodeList nList = document.getElementsByTagName("div");
            content = content + "HotDeal.vn\n";
            int item = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (nList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap att = nList.item(i).getAttributes();
                    
                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < att.getLength(); j++) {
                        if ("class".equals(att.item(j).getNodeName())) {
                            // Lấy thẻ có class = "deal_list"
                            if ("deal_list".equals(att.item(j).getTextContent())) {
                                content = content + Integer.toString(item) + ": \n";
                                item++;
                                // Lấy tiêu đề
                                Element element = (Element) nList.item(i + 1);                                
                                content = content + "Tiêu đề: " + getTagValue("a", element, 0).trim() + "\n";
                                
                                // Lấy phương thức
                                element = (Element)nList.item(i+2);
                                content = content + "Phương thức: " + element.getTextContent().trim() + "\n";
                                
                                // Lấy Link
                                element = (Element)nList.item(i + 3);
                                NamedNodeMap attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("href".equals(attx.item(k).getNodeName())) {                                        
                                        content = content + "Link gốc: " + link + attx.item(k).getTextContent().trim() + "\n";
                                    }
                                }
                                
                                // Lấy ảnh
                                attx = element.getElementsByTagName("img").item(0).getAttributes();
                                /*int k1 = 0;
                                while (k1 < attx.getLength() && !("src".equals(attx.item(k1).getNodeName()))) {
                                    k1++;
                                }
                                tbxshowlink.append("Link ?nh: " + attx.item(k1).getTextContent().trim() + "\n");*/
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("src".equals(attx.item(k).getNodeName())) {                                        
                                        content = content + "Link ảnh: " + attx.item(k).getTextContent().trim() + "\n";
                                    }
                                }
                                // Lấy mô tả
                                element = (Element)nList.item(i + 1);
                                attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("title".equals(attx.item(k).getNodeName())) {                                        
                                        content = content + "Mô tả: " + attx.item(k).getTextContent().trim() + "\n";
                                        break;
                                    }
                                }
                                // Lấy giá                                
                                element = (Element)nList.item(i + 7);
                                content = content + "Giá : " + nList.item(i + 7).getTextContent().trim() + "\n";
                                
                                // Lấy giá gốc                         
                                element = (Element)nList.item(i + 8);
                                content = content + "Giá gốc: " + getTagValue("em", element, 0).trim() + "\n";
                                
                                // Lấy Tiết kiệm
                                element = (Element)nList.item(i + 12);
                                content = content + nList.item(i + 12).getTextContent().trim() + "\n";
                                
                                // Lấy Số người mua
                                element = (Element)nList.item(i + 13);
                                content = content + nList.item(i + 13).getTextContent().trim() + "\n";
                                
                                // Lấy Thời gian còn lại
                                element = (Element)nList.item(i + 16);
                                content = content + "Thời gian còn lại: " + nList.item(i + 16).getTextContent().trim() + "\n";
                                // Kết thúc một item
                                content = content + "______________\n";
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            content = content + "Lỗi rồi: " + ex.toString();
        }
        return content;
    }
    
    /**
     * Get deal information from http://123do.vn
     * [Give the description for method].
     * @param link
     * @return
     */
    private String getFrom123doVn(String link){
        String content = "";
        try{
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(link);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);
            
            // Duyệt danh sách tất cả các thẻ div
            NodeList divList = document.getElementsByTagName("div");
            content = content + link + "\n";
            int item = 0;
            for (int i = 0; i < divList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (divList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap divContent = divList.item(i).getAttributes();
                    
                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            // Lấy thẻ có class = "deal_list"
                            if ("box-white".equals(divContent.item(j).getTextContent())) {
                                if (item != 1 && item != 0) {
                                    content = content + Integer.toString(item) + ": \n";
                                    // Lấy Link ảnh
                                    Element element = (Element) divList.item(i);
                                    NamedNodeMap divContentList = element.getElementsByTagName("img").item(0).getAttributes();
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        if ("src".equals(divContentList.item(k).getNodeName())) {
                                            content = content + "Link ảnh: " + divContentList.item(k).getTextContent().trim() + "\n";
                                        }
                                    }

                                    // Lấy Tiêu đề
                                    //element = (Element) nList.item(i + 6);
                                    //content = content + "Tiêu đề: " + element.getTextContent().trim() + "\n";
                                    NodeList divContentListX = element.getElementsByTagName("div");
                                    for (int k = 0; k < divContentListX.getLength(); k++) {
                                        if (divContentListX.item(k).hasAttributes()) {
                                            divContentList = divContentListX.item(k).getAttributes();
                                            for (int l = 0; l < divContentList.getLength(); l++) {
                                                if ("class".equals(divContentList.item(l).getNodeName())) {
                                                    // Tiêu đề
                                                    if ("titlemaildealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "titlemaildealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Tiêu đề: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                    }
                                                    
                                                    // Mô tả
                                                    if ("systemreviewdealnext".equals(divContentList.item(l).getTextContent())
                                                            || "systemreviewdealcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Mô tả: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                    }
                                                    
                                                    // Link gốc
                                                    if ("buttonmaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "buttonmaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k);
                                                        NamedNodeMap attxy = element.getElementsByTagName("a").item(0).getAttributes();
                                                        for (int m = 0; m < attxy.getLength(); m++) {
                                                            if ("href".equals(attxy.item(m).getNodeName())) {
                                                                content = content + "Link gốc: " + "http://123do.vn" + attxy.item(m).getTextContent().trim() + "\n";
                                                            }
                                                        }
                                                    }
                                                    
                                                    // Giá
                                                    if ("pricemaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "pricemaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Giá: " + divContentListX.item(k).getTextContent().trim() + "\n";  
                                                    }
                                                    
                                                    // Tiết kiệm
                                                    if ("dismaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "dismaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Tiết kiệm: " + getTagValue("span", element, 0).trim() + "\n";
                                                    }
                                                    
                                                    // Số người mua
                                                    if ("buyermaindealteamcurrent".equals(divContentList.item(l).getTextContent())
                                                            || "buyermaindealteamnext".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Số người mua: " + getTagValue("span", element, 0).trim() + "\n";
                                                    }
                                                    
                                                    // Thời gian còn lại
                                                    if ("timemaindealteamcurrent".equals(divContentList.item(l).getTextContent())
                                                            || "timemaindealteamnext".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Thời gian còn lại: " + divContentListX.item(k + 1).getTextContent().trim() + "\n";
                                                    }
                                                }
                                                if ("style".equals(divContentList.item(l).getNodeName())) {
                                                    // Giá gốc
                                                    if ("text-decoration:line-through".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Giá gốc: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    
                                    // Kết thúc một item
                                    content = content + "______________\n";
                                }
                                item++;                                
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            content = content + "Lỗi rồi: " + ex.toString();
        }
        return content;
    }
}
