/**
 * GetDealFunction.java
 * 27/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Deal collector class.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class GetDealFunction {
    /** Voucher string. */
    private static final String VOUCHER = "(Giao Voucher)";
    /**
     * Constructor.
     */
    protected GetDealFunction() {
    }
    /**
     * Get address from http://123do.vn.
     * @param url link a deal from http://123do.vn
     * @param website Website
     * @return a string represent address get from url
     * @throws Exception Exception threw
     */
    public static Long getAddress(String url, int website) throws Exception {
        Long result = (long) 0;
        try {
            // Scan list div tag
            NodeList divList = GeneralUtil.getListDivTag(url);
            for (int i = 0; i < divList.getLength(); i++) {
                // Check if div tag content not null
                if (divList.item(i).hasAttributes()) {
                    // Get all content in div tag present
                    NamedNodeMap divContent = divList.item(i).getAttributes();

                    // Scan through attributes
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("style".equals(divContent.item(j).getNodeName())) {
                            if ("margin: 25px 15px 6px 15px;overflow:hidden; width:179px".equals(divContent.item(j)
                                    .getTextContent())) {
                                Element element = (Element) divList.item(i);
                                String addressString = GeneralUtil.getTagValue("p", element, 0);
                                addressString = GeneralUtil.addressNormalization(addressString);
                                String latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
                                double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                                double lng = Double
                                        .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                                Address address = new Address(addressString, lng, lat, "");
                                result = AddressBLO.INSTANCE.insert(address);
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
     * Get address from http://www.hotdeal.vn/ link.
     * @param url link a deal from http://www.hotdeal.vn/
     * @return a string represent address get from url
     * @throws Exception Exception threw
     */
    public static Long getAddressFromHotDealVn(String url) throws Exception {
        Long result = (long) 0;
        try {
            // Scan list div tag
            NodeList divList = GeneralUtil.getListDivTag(url);
            for (int i = 0; i < divList.getLength(); i++) {
                // Check if div tag content not null
                if (divList.item(i).hasAttributes()) {
                    // Get all content in div tag present
                    NamedNodeMap divContent = divList.item(i).getAttributes();

                    // Scan through attributes
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            if ("product-location".equals(divContent.item(j).getTextContent())) {
                                String addressString = divList.item(i).getTextContent();
                                String addressDescription = "";
                                if (GeneralUtil.removeSign4VietNameseString(addressString).toLowerCase()
                                        .contains("van phong giao hang hotdeal")) {
                                    addressString = "294 Hòa Bình - Hiệp Tân - Tân Phú - TPHCM";
                                    addressDescription = "Văn phòng giao hàng HotDeal";
                                } else {
                                    Element element = (Element) divList.item(i);
                                    addressString = GeneralUtil.getTagValue("p", element, 0);
                                    if (addressString.isEmpty()) {
                                        addressString = element.getTextContent();
                                    }
                                    addressString = GeneralUtil.addressNormalization(addressString);
                                    addressDescription = GeneralUtil.getTagValue("h2", element, 0);
                                }
                                if (!addressString.isEmpty()) {
                                    String latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
                                    if (latlng.equals("")) {
                                        return result;
                                    }
                                    double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                                    double lng = Double
                                            .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                                    Address address = new Address(addressString, lng, lat, addressDescription);
                                    result = AddressBLO.INSTANCE.insert(address);
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
    public static Long getAddressFromMuaChungVN(String url) throws Exception {
        Long result = (long) 0;
        try {
         // Scan list div tag
            NodeList divList = GeneralUtil.getListDivTag(url);
            for (int i = 0; i < divList.getLength(); i++) {
                // Check if div tag content not null
                if (divList.item(i).hasAttributes()) {
                    // Get all content in div tag present
                    NamedNodeMap divContent = divList.item(i).getAttributes();

                    // Scan through attributes
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            if ("blueTitleDetail mTop5".equals(divContent.item(j).getTextContent())) {
                                String addressString = "";
                                String addressDescription = "";
                                String latlng = "";
                                Element element = (Element) divList.item(i);
                                NodeList pTagList = element.getElementsByTagName("p");
                                for (int k = 0; k < pTagList.getLength(); k++) {
                                    addressString = pTagList.item(k).getTextContent();
                                    addressString = GeneralUtil.addressNormalization(addressString);
                                    latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
                                    if (!latlng.equals("")) {
                                        double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                                        double lng = Double
                                                .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                                        Address address = new Address(addressString, lng, lat, addressDescription);
                                        result = AddressBLO.INSTANCE.insert(address);
                                        return result;
                                    }
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
     * @param url link from http://www.hotdeal.vn
     * @return String represent content collected
     * @throws Exception Exception maybe happen
     */
    public static String getFromHotDealVn(String url) throws Exception {
        String content = "";
        String title = "";
        String description = "";
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
            // Scan list div tag
            NodeList nList = GeneralUtil.getListDivTag(url);
            content = content + url + "\n";
            int item = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                // Check if div tag content not null
                if (nList.item(i).hasAttributes()) {
                    // Get all content in div tag present
                    NamedNodeMap att = nList.item(i).getAttributes();

                    // Scan through attributes
                    for (int j = 0; j < att.getLength(); j++) {
                        if ("class".equals(att.item(j).getNodeName())) {
                            // Get tag has class is "product"
                            if ("product".equals(att.item(j).getTextContent())) {
                                content = content + Integer.toString(item) + ": \n";
                                item++;
                                // Title
                                Element element = (Element) nList.item(i + 2);
                                title = GeneralUtil.getTagValue("a", element, 0).trim();
                                content = content + "Tiêu đề: " + title + "\n";

                                // Method
                                element = (Element) nList.item(i + 3);
                                String voucher = element.getTextContent().trim();
                                isVoucher = VOUCHER.equals(voucher);
                                content = content + "Phương thức: " + voucher + "\n";

                                // Link
                                element = (Element) nList.item(i + 1);
                                NamedNodeMap attx = element.getElementsByTagName("a").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("href".equals(attx.item(k).getNodeName())) {
                                        link = "http://www.hotdeal.vn" + attx.item(k).getTextContent().trim();
                                        content = content + "Link gốc: " + link + "\n";
                                    }
                                }

                                // Image link
                                attx = element.getElementsByTagName("img").item(0).getAttributes();
                                for (int k = 0; k < attx.getLength(); k++) {
                                    if ("src".equals(attx.item(k).getNodeName())) {
                                        content = content + "Link ảnh: " + attx.item(k).getTextContent().trim() + "\n";
                                        imageLink = attx.item(k).getTextContent().trim();
                                    }
                                }
                                // Description
                                element = (Element) nList.item(i + 4);
                                description = element.getTextContent();
                                content = content + "Mô tả: " + description + "\n";

                                // Price
                                element = (Element) nList.item(i + 5);
                                String priceString = element.getElementsByTagName("span").item(0).getTextContent();
                                price = GeneralUtil.getPriceFromString(priceString);
                                content = content + "Giá : " + priceString + "\n";

                                // Unit price
                                unitPrice = priceString.substring(priceString.length() - 3);

                                // Basic price
                                String basicPriceString = element.getElementsByTagName("span").item(1).getTextContent();
                                content = content + "Giá gốc: " + basicPriceString + "\n";
                                basicPrice = GeneralUtil.getPriceFromString(basicPriceString);

                                // Save
                                element = (Element) nList.item(i + 7);
                                String saveString = element.getTextContent().trim();
                                save = Float.parseFloat(saveString.substring(9, saveString.length() - 1));
                                content = content + "Tiết kiệm: " + save + "% \n";

                                // Number of buyer
                                element = (Element) nList.item(i + 8);
                                numberBuyer = Integer.parseInt(element.getElementsByTagName("span").item(1)
                                        .getTextContent());
                                content = content + "Số người đã mua: " + numberBuyer + "\n";

                                // Remain time
                                element = (Element) nList.item(i + 9);
                                remainTime = element.getElementsByTagName("span").item(1).getTextContent();
                                content = content + "Thời gian còn lại: " + remainTime + "\n";
                                endTime = GeneralUtil.getEndTime(remainTime);

                                // End of an item
                                content = content + "______________\n";
                                Deal deal = new Deal(title, description, link, imageLink, price,
                                        basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                                Long dealId = DealBLO.INSTANCE.insert(deal);
                                if (dealId != 0) {
                                    Long addressId = getAddressFromHotDealVn(link);
                                    if (addressId != 0) {
                                        AddressDetail detail = new AddressDetail(dealId, addressId);
                                        AddressDetailBLO.INSTANCE.insert(detail);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketTimeoutException ex) {
            getFromHotDealVn(url);
        } catch (IOException ex) {
            getFromHotDealVn(url);
        } catch (Exception ex) {
            throw ex;
        }
        System.out.println(content);
        return content;
    }
    /**
     * Get deal information from http://123do.vn.
     * @param url Link to get content
     * @param type If 0: http://123do.vn/, or 1: http://123do.vn/dealhot.php
     * @throws Exception Exception maybe happen
     */
    public static void getFrom123doVn(String url, int type) throws Exception {
        String title = "";
        String description = "";
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
            NodeList divList = GeneralUtil.getListDivTag(url);
            int item = 0;
            for (int i = 0; i < divList.getLength(); i++) {
                // Check if div tag content not null
                if (divList.item(i).hasAttributes()) {
                    // Get all content in div tag present
                    NamedNodeMap divContent = divList.item(i).getAttributes();
                    // Scan through attributes
                    for (int j = 0; j < divContent.getLength(); j++) {
                        if ("class".equals(divContent.item(j).getNodeName())) {
                            // Get tag has class is "deal_list"
                            if ("box-white".equals(divContent.item(j).getTextContent())) {
                                if ((item != 1 && item != 0 && type == 0) || (type == 1 && item != 0)) {
                                    // Image link
                                    Element element = (Element) divList.item(i);
                                    NamedNodeMap divContentList = element.getElementsByTagName("img").item(0)
                                            .getAttributes();
                                    for (int k = 0; k < divContentList.getLength(); k++) {
                                        if ("src".equals(divContentList.item(k).getNodeName())) {
                                            imageLink = divContentList.item(k).getTextContent().trim();
                                        }
                                    }
                                    // Title
                                    NodeList divContentListX = element.getElementsByTagName("div");
                                    for (int k = 0; k < divContentListX.getLength(); k++) {
                                        if (divContentListX.item(k).hasAttributes()) {
                                            divContentList = divContentListX.item(k).getAttributes();
                                            for (int l = 0; l < divContentList.getLength(); l++) {
                                                if ("class".equals(divContentList.item(l).getNodeName())) {
                                                    // Title
                                                    if ("titlemaildealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "titlemaildealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        title = divContentListX.item(k).getTextContent().trim();
                                                    }
                                                    // Description
                                                    if ("systemreviewdealnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "systemreviewdealcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        description = divContentListX.item(k).getTextContent().trim();
                                                    }
                                                    // Link
                                                    if ("buttonmaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buttonmaindealteamcurrent".equals(divContentList
                                                                    .item(l).getTextContent())) {
                                                        element = (Element) divContentListX.item(k);
                                                        NamedNodeMap attxy = element.getElementsByTagName("a").item(0)
                                                                .getAttributes();
                                                        for (int m = 0; m < attxy.getLength(); m++) {
                                                            if ("href".equals(attxy.item(m).getNodeName())) {
                                                                link = "http://123do.vn"
                                                                        + attxy.item(m).getTextContent().trim();
                                                            }
                                                        }
                                                    }
                                                    // Price
                                                    if ("pricemaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "pricemaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        String priceString = divContentListX.item(k).getTextContent()
                                                                .trim();
                                                        price = GeneralUtil.getPriceFromString(priceString);
                                                        // Unit price
                                                        unitPrice = priceString.substring(priceString.length() - 3);
                                                    }
                                                    // Save
                                                    if ("dismaindealteamnext".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "dismaindealteamcurrent".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        String saveString = GeneralUtil.getTagValue("span", element, 0)
                                                                .trim();
                                                        save = Float.parseFloat(saveString.substring(0,
                                                                saveString.length() - 1));
                                                    }
                                                    // Number of buyer
                                                    if ("buyermaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "buyermaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        numberBuyer = Integer.parseInt(GeneralUtil.getTagValue("span",
                                                                element, 0).trim());
                                                    }
                                                    // Remain time
                                                    if ("timemaindealteamcurrent".equals(divContentList.item(l)
                                                            .getTextContent())
                                                            || "timemaindealteamnext".equals(divContentList.item(l)
                                                                    .getTextContent())) {
                                                        element = (Element) divContentListX.item(k + 1);
                                                        remainTime = divContentListX.item(k + 1).getTextContent()
                                                                .trim();
                                                        endTime = GeneralUtil.getEndTime(remainTime);
                                                    }
                                                }
                                                if ("style".equals(divContentList.item(l).getNodeName())) {
                                                    if ("text-decoration:line-through".equals(divContentList.item(l)
                                                            .getTextContent())) {
                                                        basicPrice = GeneralUtil.getPriceFromString(divContentListX
                                                                .item(k).getTextContent().trim());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    Deal deal = new Deal(title, description, link, imageLink, price,
                                            basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                                    Long dealId = DealBLO.INSTANCE.insert(deal);
                                    if (dealId != 0) {
                                        Long addressId = getAddress(link, ListWebsite.DO123VN.ordinal());
                                        if (addressId != 0) {
                                            AddressDetail detail = new AddressDetail(dealId, addressId);
                                            AddressDetailBLO.INSTANCE.insert(detail);
                                        }
                                    }
                                }
                                item++;
                            }
                        }
                    }
                }
            }
        } catch (SocketTimeoutException ex) {
            getFrom123doVn(url, type);
        } catch (IOException ex) {
            getFrom123doVn(url, type);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Get deal information from http://muachung.vn.
     * @return String content
     * @throws Exception Exception maybe happen
     */
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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 100);
        java.util.Date endTime = cal.getTime();
        boolean isVoucher = true;
        String[] linkList = {"http://muachung.vn/danh-muc/c-999999997/deal-dang-ban/trang-1.html",
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
                while ((data = br.readLine()) != null) {
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
                        + "<div\\s+class=\"rootPrice\"(.*?)>(.*?)<strike>(.*?)"
                        + "</strike>(.*?)</div>(.*?)</div><div[^>]*(.*?)>(.*?)</div>(.*?)</div>"// 46
                        + "<div\\s+class=\"bSelling\"(.*?)>(.*?)"
                        + "<div\\s+class=\"bSellingLeft\"(.*?)>(.*?)"
                        + "<div\\s+class=\"bSellingRight\"(.*?)>(.*?)"
                        + "<div\\s+class=\"SellingInfoValue\"(.*?)>(.*?)"// 54
                        + "<span>(.*?)</span>(.*?)</div>(.*?)<div(.*?)>(.*?)</div>(.*?)"
                        + "<div(.*?)>(.*?)</div>(.*?)</div>(.*?)</div>(.*?)</div>" + "(.*?)</div>";
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

                    price = Double.parseDouble(match.group(36).replace(".", "").trim());
                    unitPrice = match.group(37).trim();
                    content += "\nPrice: " + price + " " + unitPrice;

                    String basicPriceString = match.group(41);
                    basicPrice = Double.parseDouble(basicPriceString
                            .substring(0, basicPriceString.lastIndexOf(" ") - 1).trim().replace(".", ""));
                    content += "\nReal price: " + basicPrice + " " + unitPrice;

                    numberBuyer = Integer.parseInt(match.group(55).trim());
                    content += "\nSold: " + numberBuyer;
                    content += "\n=================================" + String.valueOf(count);

                    Deal deal = new Deal(title, description, link, imageLink, price,
                            basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                    Long dealId = DealBLO.INSTANCE.insert(deal);
                    if (dealId != 0) {
                        Long addressId = getAddressFromMuaChungVN(link);
                        if (addressId != 0) {
                            AddressDetail detail = new AddressDetail(dealId, addressId);
                            AddressDetailBLO.INSTANCE.insert(detail);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return content;
    }
}
