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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

/**
 * @author Nixforest
 *
 */
public class GetDealFunction {
    
    private static String VOUCHER = "(Giao Voucher)";

    /**
     * Get address from http://123do.vn.
     * @param url link a deal from http://123do.vn
     * @return a string represent address get from url
     * @throws Exception 
     */
    public static String getAddressFrom123doVn(String url) throws Exception {
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
                            if ("margin: 25px 15px 6px 15px;overflow:hidden; width:179px".equals(divContent.item(j)
                                    .getTextContent())) {
                                Element element = (Element) divList.item(i);
                                result += GeneralUtil.getTagValue("p", element, 0);

                                result = GeneralUtil.addressNormalization(result);
                                return result;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * [Give the description for method].
     * @param url
     * @return
     * @throws Exception 
     */
    public static String getAddressFromHotDealVn(String url) throws Exception {
        String result = "";
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Get content HTML and convert to XML
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
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            if ("product-location".equals(divContent.item(j).getTextContent())) {
                                /*Element element = (Element) divList.item(i);
                                NodeList divContentList = element.getElementsByTagName("div");
                                for (int k = 0; k < divContentList.getLength(); k++) {
                                    NamedNodeMap divChildContent = divContentList.item(k).getAttributes();
                                    for (int l = 0; l < divChildContent.getLength(); l++) {
                                        if ("style".equals(divChildContent.item(l).getNodeName())
                                                && "margin-top:10px;".equals(divChildContent.item(l).getTextContent())) {
                                            result += divContentList.item(k).getTextContent();
                                        }
                                    }
                                }*/
                                //result += divList.item(i).getTextContent();
                                String address = divList.item(i).getTextContent();
                                if (GeneralUtil.RemoveSign4VietNameseString(address).toLowerCase().contains("van phong giao hang hotdeal")) {
                                    result += GeneralUtil.addressNormalization(address);
                                } else {
                                    Element element = (Element) divList.item(i);
                                    NodeList divContentList = element.getElementsByTagName("p");
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        result += divContentList.item(k).getTextContent();
                                    }
                                    result = GeneralUtil.addressNormalization(result);
                                    return result;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * Get deal information from http://www.hotdeal.vn.
     * @param url
     * @return
     * @throws Exception 
     */
    public static String getFromHotDealVn(String url) throws Exception {
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
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lay toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);

            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);

            // Duyệt danh sách thẻ div
            NodeList nList = document.getElementsByTagName("div");
            content = content + url + "\n";
            int item = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (nList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap att = nList.item(i).getAttributes();

                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < att.getLength(); j++) {
                        if ("class".equals(att.item(j).getNodeName())) {
                            // Lấy thẻ có class = "product"
                            if ("product".equals(att.item(j).getTextContent())) {
                                content = content + Integer.toString(item) + ": \n";
                                item++;
                                // Lấy tiêu đề
                                Element element = (Element) nList.item(i + 2);
                                title = GeneralUtil.getTagValue("a", element, 0).trim();
                                content = content + "Tiêu đề: " + title + "\n";

                                // Lấy phương thức
                                element = (Element) nList.item(i + 3);
                                String voucher = element.getTextContent().trim();
                                isVoucher = VOUCHER.equals(voucher);
                                content = content + "Phương thức: " + voucher + "\n";

                                // Lấy Link
                                element = (Element) nList.item(i + 1);
                                NamedNodeMap attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("href".equals(attx.item(k).getNodeName())) {
                                        link = "http://www.hotdeal.vn" + attx.item(k).getTextContent().trim();
                                        content = content + "Link gốc: " + link + "\n";
                                    }
                                }

                                // Lấy ảnh
                                attx = element.getElementsByTagName("img").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("src".equals(attx.item(k).getNodeName())) {
                                        content = content + "Link ảnh: " + attx.item(k).getTextContent().trim() + "\n";
                                        imageLink = attx.item(k).getTextContent().trim();
                                    }
                                }
                                // Lấy mô tả
                                element = (Element) nList.item(i + 4);
                                description = element.getTextContent();
                                content = content + "Mô tả: " + description + "\n";
                                
                                // Lấy giá
                                element = (Element) nList.item(i + 5);
                                String priceString = element.getElementsByTagName("span").item(0).getTextContent();
                                price = GeneralUtil.getPriceFromString(priceString);
                                content = content + "Giá : " + priceString + "\n";

                                // Đơn vị
                                unitPrice = priceString.substring(priceString.length() - 3);

                                // Lấy giá gốc
                                String basicPriceString = element.getElementsByTagName("span").item(1).getTextContent();
                                content = content + "Giá gốc: " + basicPriceString
                                        + "\n";
                                basicPrice = GeneralUtil.getPriceFromString(basicPriceString);

                                // Lấy Tiết kiệm
                                element = (Element) nList.item(i + 7);
                                String saveString = element.getTextContent().trim();
                                save = Float.parseFloat(saveString.substring(9, saveString.length() - 1));
                                content = content + "Tiết kiệm: " + save + "% \n";

                                // Lấy Số người mua
                                element = (Element) nList.item(i + 8);
                                numberBuyer = Integer.parseInt(element.getElementsByTagName("span").item(1).getTextContent());
                                content = content + "Số người đã mua: " + numberBuyer + "\n";

                                // Lấy Thời gian còn lại
                                element = (Element) nList.item(i + 9);
                                remainTime = element.getElementsByTagName("span").item(1).getTextContent();
                                content = content + "Thời gian còn lại: " + remainTime
                                        + "\n";
                                endTime = GeneralUtil.getEndTime(remainTime);

                                // Kết thúc một item
                                content = content + "______________\n";

                                // Lấy địa chỉ
                                address = "";//getAddressFromHotDealVn(link);
                                //content = content + "Địa chỉ: " + address + "\n";
                                Deal deal = new Deal(title, description, address, link, imageLink, price, basicPrice,
                                        unitPrice, save, numberBuyer, endTime, isVoucher);
                                if (!DealBLO.INSTANCE.isExist(deal)) {
                                    address = getAddressFromHotDealVn(link);
                                    deal.setAddress(address);
                                    DealBLO.INSTANCE.insert(deal);
                                }
                                //deal = GeneralUtil.encodeDeal(deal);
                            }
                        }
                    }
                }
            }
        } catch(SocketTimeoutException ex) {
            getFromHotDealVn(url);
        } catch (IOException ex) {
            getFromHotDealVn(url);
        } catch (Exception ex) {
            //throw ex;
            ex.printStackTrace();
            //System.out.println(ex.toString());;
        }
        return content;
    }

    /**
     * Get deal information from http://123do.vn.
     * @param url Address to get content
     * @param type If 0: http://123do.vn/, or 1: http://123do.vn/dealhot.php 
     * @return Content
     * @throws Exception 
     */
    public static String getFrom123doVn(String url, int type) throws Exception {
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
        // Deal deal;
        try {
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
                                if ((item != 1 && item != 0 && type == 0)
                                        || (type == 1 && item != 0)) {
                                    content = content + Integer.toString(item) + ": \n";
                                    
                                    // Lấy Link ảnh
                                    Element element = (Element) divList.item(i);
                                    NamedNodeMap divContentList = element.getElementsByTagName("img").item(0)
                                            .getAttributes();
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        if ("src".equals(divContentList.item(k).getNodeName())) {
                                            content = content + "Link ảnh: "
                                                    + divContentList.item(k).getTextContent().trim() + "\n";
                                            imageLink = divContentList.item(k).getTextContent().trim();
                                        }
                                    }

                                    // Lấy Tiêu đề
                                    NodeList divContentListX = element.getElementsByTagName("div");
                                    for (int k = 0; k < divContentListX.getLength(); k++) {
                                        if (divContentListX.item(k).hasAttributes()) {
                                            divContentList = divContentListX.item(k).getAttributes();
                                            for (int l = 0; l < divContentList.getLength(); l++) {
                                                if ("class".equals(divContentList.item(l).getNodeName())) {
                                                    // Tiêu đề
                                                    if ("titlemaildealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "titlemaildealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Tiêu đề: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        title = divContentListX.item(k).getTextContent().trim();
                                                    }

                                                    // Mô tả
                                                    if ("systemreviewdealnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "systemreviewdealcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Mô tả: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        description = divContentListX.item(k).getTextContent().trim();
                                                    }

                                                    // Link gốc
                                                    if ("buttonmaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buttonmaindealteamcurrent".equals(divContentList
                                                                    .item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k);
                                                        NamedNodeMap attxy = element.getElementsByTagName("a").item(0)
                                                                .getAttributes();
                                                        for (int m = 0; m < attxy.getLength(); m++) {
                                                            if ("href".equals(attxy.item(m).getNodeName())) {
                                                                content = content + "Link gốc: " + "http://123do.vn"
                                                                        + attxy.item(m).getTextContent().trim() + "\n";
                                                                link = "http://123do.vn"
                                                                        + attxy.item(m).getTextContent().trim();
                                                                // Lấy địa chỉ
                                                                address = getAddressFrom123doVn(link);
                                                            }
                                                        }
                                                    }

                                                    // Giá
                                                    if ("pricemaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "pricemaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Giá: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        String priceString = divContentListX.item(k).getTextContent()
                                                                .trim();
                                                        price = GeneralUtil.getPriceFromString(priceString);
                                                        // Đơn vị
                                                        unitPrice = priceString.substring(priceString.length() - 3);
                                                    }

                                                    // Tiết kiệm
                                                    if ("dismaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "dismaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        String saveString = GeneralUtil.getTagValue("span", element, 0)
                                                                .trim();
                                                        content = content + "Tiết kiệm: "
                                                                + saveString
                                                                + "\n";
                                                        save = Float.parseFloat(saveString.substring(0,
                                                                saveString.length() - 1));
                                                    }

                                                    // Số người mua
                                                    if ("buyermaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buyermaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Số người mua: "
                                                                + GeneralUtil.getTagValue("span", element, 0).trim()
                                                                + "\n";
                                                        numberBuyer = Integer.parseInt(GeneralUtil.getTagValue("span",
                                                                element, 0).trim());
                                                    }

                                                    // Thời gian còn lại
                                                    if ("timemaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "timemaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Thời gian còn lại: "
                                                                + divContentListX.item(k + 1).getTextContent().trim()
                                                                + "\n";
                                                        remainTime = divContentListX.item(k + 1).getTextContent()
                                                                .trim();
                                                        endTime = GeneralUtil.getEndTime(remainTime);
                                                    }
                                                }
                                                if ("style".equals(divContentList.item(l).getNodeName())) {
                                                    // Giá gốc
                                                    if ("text-decoration:line-through".equals(divContentList.item(l)
                                                            .getTextContent())) {
                                                        content = content + "Giá gốc: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        basicPrice = GeneralUtil.getPriceFromString(divContentListX
                                                                .item(k).getTextContent().trim());
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Kết thúc một item
                                    content = content + "______________\n";
                                    Deal deal = new Deal(title, description, address, link, imageLink, price,
                                            basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                                    //deal = GeneralUtil.encodeDeal(deal);
                                    DealBLO.INSTANCE.insert(deal);
                                }
                                item++;
                            }
                        }
                    }
                }
            }
        } catch(SocketTimeoutException ex) {
            getFrom123doVn(url, type);
        } catch (IOException ex) {
            getFrom123doVn(url, type);
        } catch (Exception ex) {
            //throw ex;
            content += ex.getMessage();
        }
        return content;
    }
    
    public static String getFrom123doVnX(String address) throws Exception {        
        URL url = new URL("http://123do.vn/");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
        connection.setRequestProperty("Content-Encoding", "ISO-8859-1");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String temp = "";
        StringBuffer stringHtml = new StringBuffer();
        while((temp = br.readLine()) != null){
            stringHtml.append(temp);
        }
        br.close();
        return new UtilHtmlToXML().Convert2XML(stringHtml.toString());
    }
    
    

    public static String getFromMuaChungVn() throws Exception {
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
        String linkList[] = {"http://muachung.vn/danh-muc/c-999999997/deal-dang-ban/trang-1.html",
                "http://muachung.vn/danh-muc/c-999999997/deal-dang-ban/trang-2.html"};
        int count = 0;
        try {
            URL url;
            URLConnection connection;
            for (int i = 0; i < linkList.length; i++) {
                url = new URL(linkList[i]);
                connection = url.openConnection();

                String data = "";
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
                connection.setRequestProperty("Content-Encoding", "ISO-8859-1");

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuffer stringHtml = new StringBuffer();
                while((data = br.readLine()) != null){
                    stringHtml.append(data);
                }
                br.close();
                data = stringHtml.toString();

                String regex = "<div\\s+class=\"itemSelling\"(.*?)>"// 1
                        + "(.*?)<div\\s+class=\"SellingDate\"(.*?)>(.*?)</div>"// 4
                        + "(.*?)<div\\s+class=\"SellingLeft\"(.*?)>(.*?)"// 7
                        + "<a\\s+href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))[^>]*>(.*?)"
                        + "<div\\s+class=\"fixPNG discount_new\"(.*?)>(.*?)"// 13
                        + "<div(.*?)>(.*?)</div>(.*?)</div>(.*?)"// 17
                        + "<img\\s+src\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))(.*?)/></a>(.*?)</div>"// 22
                        + "<div\\s+class=\"SellingTitle\"(.*?)>(.*?)<a[^>]+>(.+?)</a>(.*?)</div>"// 26
                        + "<div\\s+class=\"realTitle mTop5\"(.*?)>(.*?)<a[^>]+>(.+?)</a>(.*?)</div>"// 30
                        + "<div\\s+class=\"realPrice\"(.*?)>(.*?)"
                        + "<div\\s+class=\"fl\"(.*?)>(.*?)"// 34
                        + "<div\\s+class=\"SellingPrice\"(.*?)>(.*?)<span>(.*?)</span>(.*?)</div>"// 38
                        + "<div\\s+class=\"rootPrice\"(.*?)>(.*?)<strike>(.*?)</strike>(.*?)</div>(.*?)</div><div[^>]*(.*?)>(.*?)</div>(.*?)</div>"// 46
                        + "<div\\s+class=\"bSelling\"(.*?)>(.*?)"
                        + "<div\\s+class=\"bSellingLeft\"(.*?)>(.*?)"
                        + "<div\\s+class=\"bSellingRight\"(.*?)>(.*?)"
                        + "<div\\s+class=\"SellingInfoValue\"(.*?)>(.*?)"// 54
                        + "<span>(.*?)</span>(.*?)</div>(.*?)<div(.*?)>(.*?)</div>(.*?)<div(.*?)>(.*?)</div>(.*?)</div>(.*?)</div>(.*?)</div>"
                        + "(.*?)</div>";
                Pattern patt = Pattern.compile(regex);
                Matcher match = patt.matcher(data);
                while (match.find()) {
                    count++;

                    address = match.group(4).substring(6).trim();
                    content += "\nPosition: " + address;

                    link = match.group(8).replace("\"", "").trim();
                    content += "\nURl: " + link;

                    save = Float.parseFloat(match.group(15).trim().replace("%", ""));
                    content += "\nSave: " + save;

                    imageLink = match.group(18).replace("\"", "").trim();
                    content += "\nLink image: " + imageLink;

                    title = match.group(25).trim();
                    content += "\nTitle: " + title;

                    description = match.group(29).trim();
                    content += "\nDescription: " + description;

                    price = Double.parseDouble(match.group(36).replace(".", "").trim());// GeneralUtil.getPriceFromString(match.group(36).trim());
                    unitPrice = match.group(37).trim();
                    content += "\nPrice: " + price + " " + unitPrice;

                    String basicPriceString = match.group(41);
                    basicPrice = Double.parseDouble(basicPriceString.substring(0, basicPriceString.lastIndexOf(" ") - 1).trim()
                            .replace(".", ""));
                    content += "\nReal price: " + basicPrice + " " + unitPrice;

                    numberBuyer = Integer.parseInt(match.group(55).trim());
                    content += "\nSold: " + numberBuyer;
                    content += "\n=================================" + String.valueOf(count);


                    Deal deal = new Deal(title,  description, address, link, imageLink, price, basicPrice, unitPrice,
                            save, numberBuyer, endTime, isVoucher);
                    //deal = GeneralUtil.encodeDeal(deal);
                    DealBLO.INSTANCE.insert(deal);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return content;
    }

    public static String getFromCongDongMuaCom(String url) {
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
        // Deal deal;
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Lấy toàn bộ nội dung HTML và chuyển sang XML
            String html = util.HtmlToXML(url);
            
            UtilReadXML reader = new UtilReadXML();
            Document document = reader.ReadContentXML(html);

            // Duyệt danh sách tất cả các thẻ div
            NodeList itemList = document.getElementsByTagName("div");
            content = content + url + "\n";
            int item = 0;
            for (int i = 0; i < itemList.getLength(); i++) {
                // Kiểm tra nếu thẻ div không rỗng
                if (itemList.item(i).hasAttributes()) {
                    // Lấy các thuộc tính của thẻ div hiện thời
                    NamedNodeMap divContent = itemList.item(i).getAttributes();

                    // Duyệt qua các thuộc tính
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            // Lấy thẻ có class = "deal_list"
                            if ("cdm_recentDeal_item".equals(divContent.item(j).getTextContent())) {
                                if (true) {
                                    content = content + Integer.toString(item) + ": \n";
                                    
                                    Element element = (Element) itemList.item(i);                                     

                                    // Lấy Tiêu đề
                                    title = element.getElementsByTagName("a").item(1).getTextContent();
                                    content += "Tiêu đề: " + title;
                                    
                                    // Lấy link
                                    NamedNodeMap divContentList = element.getElementsByTagName("a").item(0)
                                            .getAttributes();
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        if ("href".equals(divContentList.item(k).getNodeName())) {
                                            link = divContentList.item(k).getTextContent();
                                            content += "Link: " + link + "\n";
                                        }
                                    }
                                    // Lấy Link ảnh
                                    divContentList = element.getElementsByTagName("img").item(0)
                                            .getAttributes();                                    
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        if ("src".equals(divContentList.item(k).getNodeName())) {
                                            imageLink = divContentList.item(k).getTextContent().trim();
                                            content += "Link ảnh: " + imageLink + "\n";
                                        }
                                    }
                                    NodeList divContentListX = element.getElementsByTagName("div");
                                    for (int k = 0; k < divContentListX.getLength(); k++) {
                                        if (divContentListX.item(k).hasAttributes()) {
                                            divContentList = divContentListX.item(k).getAttributes();
                                            for (int l = 0; l < divContentList.getLength(); l++) {
                                                if ("class".equals(divContentList.item(l).getNodeName())) {
                                                    // Mô tả
                                                    if ("recent_deal_titlelong".equals(divContentList.item(l).getTextContent())) {
                                                        description = divContentListX.item(k).getTextContent().trim();
                                                        content += "Mô tả: " + description + "\n";
                                                    }
                                                    
                                                    // Giá
                                                    if ("recentDeal_price_details".equals(divContentList.item(l).getTextContent())) {
                                                        String priceString = divContentListX.item(k).getTextContent().trim();
                                                        content += "Giá: " + priceString + "\n";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    
                                    /*
                                    for (int k = 0; k < divContentListX.getLength(); k++) {
                                        if (divContentListX.item(k).hasAttributes()) {
                                            divContentList = divContentListX.item(k).getAttributes();
                                            for (int l = 0; l < divContentList.getLength(); l++) {
                                                if ("class".equals(divContentList.item(l).getNodeName())) {
                                                    // Tiêu đề
                                                    if ("cdm_recentDeal_item_left".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "titlemaildealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Tiêu đề: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        title = divContentListX.item(k).getTextContent().trim();
                                                    }

                                                    // Mô tả
                                                    if ("systemreviewdealnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "systemreviewdealcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Mô tả: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        description = divContentListX.item(k).getTextContent().trim();
                                                    }

                                                    // Link gốc
                                                    if ("buttonmaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buttonmaindealteamcurrent".equals(divContentList
                                                                    .item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k);
                                                        NamedNodeMap attxy = element.getElementsByTagName("a").item(0)
                                                                .getAttributes();
                                                        for (int m = 0; m < attxy.getLength(); m++) {
                                                            if ("href".equals(attxy.item(m).getNodeName())) {
                                                                content = content + "Link gốc: " + "http://123do.vn"
                                                                        + attxy.item(m).getTextContent().trim() + "\n";
                                                                link = "http://123do.vn"
                                                                        + attxy.item(m).getTextContent().trim();
                                                                // Lấy địa chỉ
                                                                address = getAddressFrom123doVn(link);
                                                            }
                                                        }
                                                    }

                                                    // Giá
                                                    if ("pricemaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "pricemaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        content = content + "Giá: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        String priceString = divContentListX.item(k).getTextContent()
                                                                .trim();
                                                        price = GeneralUtil.getPriceFromString(priceString);
                                                        // Đơn vị
                                                        unitPrice = priceString.substring(priceString.length() - 3);
                                                    }

                                                    // Tiết kiệm
                                                    if ("dismaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "dismaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Tiết kiệm: "
                                                                + GeneralUtil.getTagValue("span", element, 0).trim()
                                                                + "\n";
                                                        String saveString = GeneralUtil.getTagValue("span", element, 0)
                                                                .trim();
                                                        save = Float.parseFloat(saveString.substring(0,
                                                                saveString.length() - 1));
                                                    }

                                                    // Số người mua
                                                    if ("buyermaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buyermaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Số người mua: "
                                                                + GeneralUtil.getTagValue("span", element, 0).trim()
                                                                + "\n";
                                                        numberBuyer = Integer.parseInt(GeneralUtil.getTagValue("span",
                                                                element, 0).trim());
                                                    }

                                                    // Thời gian còn lại
                                                    if ("timemaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "timemaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        content = content + "Thời gian còn lại: "
                                                                + divContentListX.item(k + 1).getTextContent().trim()
                                                                + "\n";
                                                        remainTime = divContentListX.item(k + 1).getTextContent()
                                                                .trim();
                                                        endTime = GeneralUtil.getEndTime(remainTime);
                                                    }
                                                }
                                                if ("style".equals(divContentList.item(l).getNodeName())) {
                                                    // Giá gốc
                                                    if ("text-decoration:line-through".equals(divContentList.item(l)
                                                            .getTextContent())) {
                                                        content = content + "Giá gốc: "
                                                                + divContentListX.item(k).getTextContent().trim()
                                                                + "\n";
                                                        basicPrice = GeneralUtil.getPriceFromString(divContentListX
                                                                .item(k).getTextContent().trim());
                                                    }
                                                }
                                            }
                                        }
                                    }*/

                                    // Kết thúc một item
                                    content = content + "______________\n";
                                    /*Deal deal = new Deal(title, description, address, link, imageLink, price,
                                            basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                                    DealBLO.INSTANCE.insert(deal);*/
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

    public static String getFromNhomMuaCom() {
        String content = "";
        /*String link[] = {"http://www.nhommua.com/tp-ho-chi-minh/", "http://www.nhommua.com/ha-noi/",
                "http://www.nhommua.com/hai-phong/", "http://www.nhommua.com/can-tho/"};
        int count = 0;
        for (int num = 0; num < link.length; num++) {
            try {
                UtilHtmlToXML util = new UtilHtmlToXML();
                String html = util.HtmlToXML(link[num]);
                UtilReadXML reader = new UtilReadXML();
                Document document = reader.ReadContentXML(html);

                NodeList nList = document.getElementsByTagName("div");

                for (int i = 0; i < nList.getLength(); i++) {
                    if (nList.item(i).hasAttributes()) {
                        NamedNodeMap att = nList.item(i).getAttributes();
                        for (int j = 0; j < att.getLength(); j++) {
                            if (att.item(j).getNodeName().equals("class")) {
                                if (att.item(j).getTextContent().equals("small-box-white")) {
                                    Element element = (Element) nList.item(i);
                                    NodeList listItem = element.getElementsByTagName("div");
                                    for (int p = 0; p < listItem.getLength(); p++) {
                                        if (listItem.item(p).hasAttributes()) {
                                            NamedNodeMap nodeatt = listItem.item(p).getAttributes();
                                            for (int q = 0; q < nodeatt.getLength(); q++) {
                                                if (nodeatt.item(q).getNodeName().equals("class")) {
                                                    if (nodeatt.item(q).getTextContent().equals("small-box-img")) {
                                                        NodeList tempNode = listItem.item(p).getChildNodes();
                                                        for (int k = 0; k < tempNode.getLength(); k++) {
                                                            if (tempNode.item(k).getNodeName().equals("a")) {
                                                                NamedNodeMap attlink = tempNode.item(k).getAttributes();
                                                                for (int u = 0; u < attlink.getLength(); u++) {
                                                                    // link
                                                                    if (attlink.item(u).getNodeName().equals("href")) {
                                                                        String tempLink = attlink.item(u).getTextContent().trim();
                                                                        content += "Link : " + tempLink;
                                                                        
                                                                        String dataHtml = util.HtmlToXML(tempLink);
                                                                        Document doc = reader.ReadContentXML(dataHtml);

                                                                        NodeList cList = doc.getElementsByTagName("div");
                                                                        for (int x = 0; x < cList.getLength(); x++) {
                                                                            if (cList.item(x).hasAttributes()) {
                                                                                NamedNodeMap catt = cList.item(x).getAttributes();
                                                                                for (int y = 0; y < catt.getLength(); y++) {
                                                                                    if (catt.item(y).getNodeName().equals("class")) {
                                                                                        if (catt.item(y).getTextContent().equals("detail-deal detail-deal-trans")) {
                                                                                            Element cElement = (Element) cList.item(x);
                                                                                            // description
                                                                                            NodeList cList1 = cElement.getElementsByTagName("h2");
                                                                                            for (int z = 0; z < cList1.getLength(); z++) {
                                                                                                if (cList1.item(z).hasAttributes()) {
                                                                                                    NamedNodeMap catt1 = cList1.item(z).getAttributes();
                                                                                                    for (int w = 0; w < catt1.getLength(); w++) {
                                                                                                        if (catt1.item(w).getNodeName().equals("class")) {
                                                                                                            if (catt1.item(w).getTextContent().equals("titledeal")) {
                                                                                                                Element cElement1 = (Element) cList1.item(z);
                                                                                                                content += "Description: "
                                                                                                                   + cElement1.getElementsByTagName("a").item(0).getTextContent();
                                                                                                                break;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            // price
                                                                                            cList1 = cElement.getElementsByTagName("p");
                                                                                            for (int z = 0; z < cList1.getLength(); z++) {
                                                                                                if (cList1.item(z).hasAttributes()) {
                                                                                                    NamedNodeMap catt1 = cList1.item(z).getAttributes();
                                                                                                    for (int w = 0; w < catt1.getLength(); w++) {
                                                                                                        // price
                                                                                                        if (catt1.item(w).getNodeName().equals("class")) {
                                                                                                            if (catt1.item(w).getTextContent().equals("margin-negative")) {
                                                                                                                content += "Price : "
                                                                                                                    + cList1.item(z).getTextContent().trim();
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            // real price, deal, sold, time remain
                                                                                            cList1 = cElement.getElementsByTagName("div");
                                                                                            for (int z = 0; z < cList1.getLength(); z++) {
                                                                                                if (cList1.item(z).hasAttributes()) {
                                                                                                    NamedNodeMap catt1 = cList1.item(z).getAttributes();
                                                                                                    for (int w = 0; w < catt1.getLength(); w++) {
                                                                                                        if (catt1.item(w).getNodeName().equals("class")) {
                                                                                                            // real price
                                                                                                            if (catt1.item(w).getTextContent().equals("box-white-info-price")) {
                                                                                                                content += "Real Price : "
                                                                                                                                + cList1.item(
                                                                                                                                        z)
                                                                                                                                        .getFirstChild()
                                                                                                                                        .getChildNodes()
                                                                                                                                        .item(1)
                                                                                                                                        .getTextContent()
                                                                                                                                        .trim()
                                                                                                                                + cList1.item(
                                                                                                                                        z)
                                                                                                                                        .getFirstChild()
                                                                                                                                        .getChildNodes()
                                                                                                                                        .item(2)
                                                                                                                                        .getTextContent()
                                                                                                                                        .trim();

                                                                                                            }
                                                                                                            // deal
                                                                                                            if (catt1
                                                                                                                    .item(w)
                                                                                                                    .getTextContent()
                                                                                                                    .equals("box-white-info-save")) {
                                                                                                                content += "Deal : "
                                                                                                                                + cList1.item(
                                                                                                                                        z)
                                                                                                                                        .getFirstChild()
                                                                                                                                        .getTextContent()
                                                                                                                                        .trim();
                                                                                                            }
                                                                                                            // sold
                                                                                                            if (catt1
                                                                                                                    .item(w)
                                                                                                                    .getTextContent()
                                                                                                                    .equals("box-white-info-buyer")) {
                                                                                                                content += "Sold : "
                                                                                                                                + cList1.item(
                                                                                                                                        z)
                                                                                                                                        .getFirstChild()
                                                                                                                                        .getTextContent()
                                                                                                                                        .trim();
                                                                                                            }
                                                                                                            // time remain
                                                                                                            if (catt1
                                                                                                                    .item(w)
                                                                                                                    .getTextContent()
                                                                                                                    .equals("box-white-info-time")) {
                                                                                                                content += "Time Remain : "
                                                                                                                                + cList1.item(
                                                                                                                                        z)
                                                                                                                                        .getFirstChild()
                                                                                                                                        .getTextContent()
                                                                                                                                        .trim();
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        // address
                                                                        cList = doc.getElementsByTagName("p");
                                                                        for (int z = 0; z < cList.getLength(); z++) {
                                                                            if (cList.item(z).hasAttributes()) {
                                                                                NamedNodeMap catt = cList.item(z)
                                                                                        .getAttributes();
                                                                                for (int w = 0; w < catt.getLength(); w++) {
                                                                                    // address
                                                                                    if (catt.item(w).getNodeName()
                                                                                            .equals("itemprop")) {
                                                                                        if (catt.item(w)
                                                                                                .getTextContent()
                                                                                                .equals("address")) {
                                                                                            content += "Address : "
                                                                                                            + cList.item(
                                                                                                                    z)
                                                                                                                    .getTextContent()
                                                                                                                    .trim();
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    // title
                                                                    if (attlink.item(u).getNodeName().equals("name")) {
                                                                        content += "Title : "
                                                                                + attlink.item(u).getTextContent()
                                                                                        .trim();
                                                                    }
                                                                }
                                                                Element ele = (Element) tempNode.item(k)
                                                                        .getFirstChild();
                                                                NamedNodeMap attimg = ele.getAttributes();
                                                                for (int u = 0; u < attimg.getLength(); u++) {
                                                                    // link image
                                                                    if (attimg.item(u).getNodeName().equals("src")) {
                                                                        content += "Link image : "
                                                                                + attimg.item(u).getTextContent()
                                                                                        .trim();
                                                                    }
                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    content += "==========" + String.valueOf(++count);
                                }
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.err.println("Couldn't open url " + ex.toString());
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }*/
        return content;
    }

    public static String getFromCungMuaCom() {
        String content = "";
        String link = "http://www.cungmua.com";
        //String dataHtml = getHtmlFromLink(link);
        try{
            UtilHtmlToXML util = new UtilHtmlToXML();

            String dataHtml = util.HtmlToXML(link);
            String regex = "<li>(.*?)" // 1
                    + "<a\\s+href\\s*=\\s*\"(.*?)\">(.*?)"// 3
                    + "<h3>(.*?)</h3>(.*?)"// 5
                    + "<div\\s+class\\s*=\\s*\"percent1\">(.*?)</div>(.*?)" // 7
                    + "<img(.*?)src\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))[^>]*>"// 8
                    + "(.*?)</a>(.*?)</li>";

            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(dataHtml);
            
            while (match.find()) {
                String url = link + match.group(2).trim();

                System.out.println("title : " + match.group(4).trim());
                System.out.println("link : " + url);
                System.out.println("deal : " + match.group(6).trim());
                System.out.println("link image : " + match.group(9).trim());
                System.out.println("=================");
                
                
                try {
                    util = new UtilHtmlToXML();
                    UtilReadXML reader = new UtilReadXML();
                    Document document = reader.ReadContentXML(util.HtmlToXML(url));
                    // get description
                    NodeList nList = document.getElementsByTagName("h2");

                    for (int i = 0; i < nList.getLength(); i++) {
                        if (nList.item(i).hasAttributes()) {
                            // Lấy các thuộc tính của thẻ div hiện thời
                            NamedNodeMap att = nList.item(i).getAttributes();
                            for (int j = 0; j < att.getLength(); j++) {
                                if (att.item(j).getNodeName().equals("class")) {
                                    if (att.item(j).getTextContent().equals("product_detail_name")) {
                                        System.out.println("Description : " + nList.item(i).getTextContent().trim());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // get price
                    nList = document.getElementsByTagName("div");
                    for (int i = 0; i < nList.getLength(); i++) {
                        if (nList.item(i).hasAttributes()) {
                            NamedNodeMap att = nList.item(i).getAttributes();
                            for (int j = 0; j < att.getLength(); j++) {
                                if (att.item(j).getNodeName().equals("class")) {
                                    if (att.item(j).getTextContent().equals("product_detail_price")) {
                                        System.out.println("Price : " + nList.item(i).getTextContent().trim());
                                        // System.out.println("Real Price : " +
                                        // nList.item(i).getNextSibling().getTextContent().trim());
                                        // System.out.println("Sold : " +
                                        // nList.item(i).getParentNode().getLastChild().getLastChild().getTextContent().trim());
                                        break;
                                    }

                                }
                            }
                        }
                    }
                    // get real price, sold
                    nList = document.getElementsByTagName("p");
                    for (int i = 0; i < nList.getLength(); i++) {
                        if (nList.item(i).hasAttributes()) {
                            NamedNodeMap att = nList.item(i).getAttributes();
                            for (int j = 0; j < att.getLength(); j++) {
                                if (att.item(j).getNodeName().equals("class")) {
                                    if (att.item(j).getTextContent().equals("info_price")) {
                                        System.out.println("Real Price : " + nList.item(i).getTextContent().trim());
                                    }
                                    if (att.item(j).getTextContent().equals("info_time")) {
                                        NodeList list = nList.item(i).getChildNodes();
                                        for (int k = 0; k < list.getLength(); k++) {
                                            if (list.item(k).getNodeName().equals("span")) {
                                                System.out.println("Sold : " + list.item(k).getTextContent());
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // get time remain
                    nList = document.getElementsByTagName("span");
                    for (int i = 0; i < nList.getLength(); i++) {
                        if (nList.item(i).hasAttributes()) {
                            NamedNodeMap att = nList.item(i).getAttributes();
                            for (int j = 0; j < att.getLength(); j++) {
                                if (att.item(j).getNodeName().equals("id")) {
                                    if (att.item(j).getTextContent().equals("countbox")) {
                                        System.out.println("Time remain : " + nList.item(i).getTextContent().trim());
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            }
        } catch(Exception ex) {
            System.err.println(ex.toString());
        }
        return content;
    }
}

