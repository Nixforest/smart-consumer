package com.gae.java.smartconsumer.util;

import java.util.Calendar;
import java.util.Date;

import com.gae.java.smartconsumer.dao.DealDAO;
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
    private static String VOUCHER = "(Giao Voucher)";
    
    public static String getAddressFrom123doVn(String url) {
        String result = "";
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);
            
            // Duyệt danh sách thẻ div
            NodeList divList = document.getElementsByTagName("div");
            for (int i = 0; i < divList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (divList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap divContent = divList.item(i).getAttributes();
                    
                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("style".equals(divContent.item(j).getNodeName())) {
                            if ("margin: 25px 15px 6px 15px;overflow:hidden; width:179px".equals(divContent.item(j).getTextContent())) {
                                Element element = (Element)divList.item(i);
                                result += GeneralUtil.getTagValue("p", element, 0);
                                
                            }
                        }
                        /*if ("style".equals(divContent.item(j).getNodeName())) {
                            if ("margin: 25px 15px 6px 15px;overflow:hidden; width:179px".equals(divContent.item(j).getTextContent())) {
                                Element element = (Element) divList.item(i);
                                NodeList divContentList = element.getElementsByTagName("div");
                                for (int k = 0; k < divContentList.getLength(); k++) {
                                    NamedNodeMap divChildContent = divContentList.item(k).getAttributes();
                                    for (int l = 0; l < divChildContent.getLength(); l++) {
                                        if ("class".equals(divChildContent.item(l).getNodeName())
                                                && "normal compact".equals(divChildContent.item(l).getTextContent())) {
                                            result += divContentList.item(k).getTextContent();
                                        }
                                    }
                                    
                                }
                                //result += divContentList.getTextContent();
                                
                            }
                        }*/
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }
    
    public static String getAddressFromHotDealVn(String url) {
        String result = "";
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);
            
            // Duyệt danh sách thẻ div
            NodeList divList = document.getElementsByTagName("div");
            for (int i = 0; i < divList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (divList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap divContent = divList.item(i).getAttributes();
                    
                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < divContent.getLength(); j++) {
                        /*if ("id".equals(divContent.item(j).getNodeName())) {
                            if ("team_partner_side_1".equals(divContent.item(j).getTextContent())) {
                                
                                        result += divList.item(i).getTextContent();
                                
                            }
                        }*/
                        if ("id".equals(divContent.item(j).getNodeName())) {
                            if ("side-Business".equals(divContent.item(j).getTextContent())) {
                                Element element = (Element) divList.item(i);
                                NodeList divContentList = element.getElementsByTagName("div");
                                for (int k = 0; k < divContentList.getLength(); k++) {
                                    NamedNodeMap divChildContent = divContentList.item(k).getAttributes();
                                    for (int l = 0; l < divChildContent.getLength(); l++) {
                                        if ("style".equals(divChildContent.item(l).getNodeName())
                                                && "margin-top:10px;".equals(divChildContent.item(l).getTextContent())) {
                                            result += divContentList.item(k).getTextContent();
                                        }
                                    }
                                    
                                }
                                
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }
    
    
    /**
     * Get deal information from http://www.hotdeal.vn
     * [Give the description for method].
     * @param url
     * @return
     */
    public static String getFromHotDealVn(String url){
        String content = "";
        String title = "";
        String description = "";
        String address = "";
        String link = "";
        String imageLink = "";
        double price = 0;
        double basicPrice = 0;
        String unitPrice = "";
        float save = 0;
        int numberBuyer = 0;
        String remainTime = "";
        java.util.Date endTime = Calendar.getInstance().getTime();
        boolean isVoucher = true;
        try{
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);
            
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
                                content = content + "Tiêu đề: " + GeneralUtil.getTagValue("a", element, 0).trim() + "\n";
                                title = GeneralUtil.getTagValue("a", element, 0).trim();
                                
                                // Lấy phương thức
                                element = (Element)nList.item(i+2);
                                content = content + "Phương thức: " + element.getTextContent().trim() + "\n";
                                isVoucher = VOUCHER.equals(element.getTextContent().trim());
                                
                                // Lấy Link
                                element = (Element)nList.item(i + 3);
                                NamedNodeMap attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("href".equals(attx.item(k).getNodeName())) {                                        
                                        content = content + "Link gốc: " + "http://www.hotdeal.vn" + attx.item(k).getTextContent().trim() + "\n";
                                        link = "http://www.hotdeal.vn" + attx.item(k).getTextContent().trim();
                                        // Lấy địa chỉ
                                        address = getAddressFromHotDealVn(link);
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
                                        imageLink = attx.item(k).getTextContent().trim();
                                    }
                                }
                                // Lấy mô tả
                                element = (Element)nList.item(i + 1);
                                attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("title".equals(attx.item(k).getNodeName())) {                                        
                                        content = content + "Mô tả: " + attx.item(k).getTextContent().trim() + "\n";
                                        description = attx.item(k).getTextContent().trim();
                                        break;
                                    }
                                }
                                // Lấy giá                                
                                element = (Element)nList.item(i + 7);
                                content = content + "Giá : " + nList.item(i + 7).getTextContent().trim() + "\n";
                                String priceString = nList.item(i + 7).getTextContent().trim();
                                price = GeneralUtil.getPriceFromString(priceString);
                                
                                // Đơn vị
                                unitPrice = priceString.substring(priceString.length() - 3);
                                
                                // Lấy giá gốc                         
                                element = (Element)nList.item(i + 8);
                                content = content + "Giá gốc: " + GeneralUtil.getTagValue("em", element, 0).trim() + "\n";
                                basicPrice = GeneralUtil.getPriceFromString(GeneralUtil.getTagValue("em", element, 0).trim());
                                
                                // Lấy Tiết kiệm
                                element = (Element)nList.item(i + 12);
                                content = content + nList.item(i + 12).getTextContent().trim() + "\n";
                                String saveString = nList.item(i + 12).getTextContent().trim();
                                save = Float.parseFloat(saveString.substring(9, saveString.length() - 1));
                                
                                // Lấy Số người mua
                                element = (Element)nList.item(i + 13);
                                content = content + nList.item(i + 13).getTextContent().trim() + "\n";
                                numberBuyer = Integer.parseInt(nList.item(i + 13).getTextContent().trim().substring(15).trim());
                                
                                // Lấy Thời gian còn lại
                                element = (Element)nList.item(i + 16);
                                content = content + "Thời gian còn lại: " + nList.item(i + 16).getTextContent().trim() + "\n";
                                remainTime = nList.item(i + 16).getTextContent().trim();
                                endTime = GeneralUtil.getEndTime(remainTime);
                                
                                // Kết thúc một item
                                content = content + "______________\n";
                                DealDAO.INSTANCE.insert(title, description,
                                        address, link, imageLink, price,
                                        basicPrice, unitPrice, save, 
                                        numberBuyer, endTime, isVoucher);
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
     * @param url
     * @return
     */
    public static String getFrom123doVn(String url){
        String content = "";
        String title = "";
        String description = "";
        String address = "";
        String link = "";
        String imageLink = "";
        double price = 0;
        double basicPrice = 0;
        String unitPrice = "";
        float save = 0;
        int numberBuyer = 0;
        String remainTime = "";
        java.util.Date endTime = Calendar.getInstance().getTime();
        boolean isVoucher = true;
        //Deal deal;
        try{
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);
            
            // Duyệt danh sách tất cả các thẻ div
            NodeList divList = document.getElementsByTagName("div");
            content = content + url + "\n";
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
                                            imageLink = divContentList.item(k).getTextContent().trim();
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
                                                        title = divContentListX.item(k).getTextContent().trim();
                                                    }
                                                    
                                                    // Mô tả
                                                    if ("systemreviewdealnext".equals(divContentList.item(l).getTextContent())
                                                            || "systemreviewdealcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Mô tả: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                        description = divContentListX.item(k).getTextContent().trim();
                                                    }
                                                    
                                                    // Link gốc
                                                    if ("buttonmaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "buttonmaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k);
                                                        NamedNodeMap attxy = element.getElementsByTagName("a").item(0).getAttributes();
                                                        for (int m = 0; m < attxy.getLength(); m++) {
                                                            if ("href".equals(attxy.item(m).getNodeName())) {
                                                                content = content + "Link gốc: " + "http://123do.vn" + attxy.item(m).getTextContent().trim() + "\n";
                                                                link = "http://123do.vn" + attxy.item(m).getTextContent().trim();
                                                                // Lấy địa chỉ
                                                                address = getAddressFrom123doVn(link);
                                                            }
                                                        }
                                                    }
                                                    
                                                    
                                                    // Giá
                                                    if ("pricemaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "pricemaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Giá: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                        String priceString = divContentListX.item(k).getTextContent().trim();
                                                        price = GeneralUtil.getPriceFromString(priceString);
                                                        // Đơn vị
                                                        unitPrice = priceString.substring(priceString.length() - 3);
                                                    }
                                                    
                                                    
                                                    // Tiết kiệm
                                                    if ("dismaindealteamnext".equals(divContentList.item(l).getTextContent())
                                                            || "dismaindealteamcurrent".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Tiết kiệm: " + GeneralUtil.getTagValue("span", element, 0).trim() + "\n";
                                                        String saveString = GeneralUtil.getTagValue("span", element, 0).trim();
                                                        save = Float.parseFloat(saveString.substring(0, saveString.length() - 1));
                                                    }
                                                    
                                                    // Số người mua
                                                    if ("buyermaindealteamcurrent".equals(divContentList.item(l).getTextContent())
                                                            || "buyermaindealteamnext".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Số người mua: " + GeneralUtil.getTagValue("span", element, 0).trim() + "\n";
                                                        numberBuyer = Integer.parseInt(GeneralUtil.getTagValue("span", element, 0).trim());
                                                    }
                                                    
                                                    // Thời gian còn lại
                                                    if ("timemaindealteamcurrent".equals(divContentList.item(l).getTextContent())
                                                            || "timemaindealteamnext".equals(divContentList.item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Thời gian còn lại: " + divContentListX.item(k + 1).getTextContent().trim() + "\n";
                                                        remainTime = divContentListX.item(k + 1).getTextContent().trim();
                                                        endTime = GeneralUtil.getEndTime(remainTime);
                                                    }
                                                }
                                                if ("style".equals(divContentList.item(l).getNodeName())) {
                                                    // Giá gốc
                                                    if ("text-decoration:line-through".equals(divContentList.item(l).getTextContent())) {
                                                        content = content + "Giá gốc: " + divContentListX.item(k).getTextContent().trim() + "\n";
                                                        basicPrice = GeneralUtil.getPriceFromString(divContentListX.item(k).getTextContent().trim());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    
                                    // Kết thúc một item
                                    content = content + "______________\n";
                                    DealDAO.INSTANCE.insert(title, 
                                            description, address, 
                                            link, imageLink, 
                                            price, basicPrice,
                                            unitPrice, save,
                                            numberBuyer, endTime, isVoucher);
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
