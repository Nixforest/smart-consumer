/**
 * GetDealFunction.java
 * 27/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.util;

import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.CategoryBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Category;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Deal collector class.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class GetDealFunction {
    /**
     * Constructor.
     */
    protected GetDealFunction() {
    }
    /**
     * Get address from http://muachung.vn/ link.
     * @param url link a deal from http://muachung.vn/
     * @return a string represent address get from url
     * @throws Exception Exception threw
     */
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
                                        double lng = Double.parseDouble(latlng.substring(latlng.indexOf(",") + 1,
                                                latlng.length()));
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
        String itemContent = "";
        String title = "";
        String description = "";
        String link = "";
        String imageLink = "";
        double price = 0;
        double basicPrice = 0;
        String unitPrice = "";
        int numberBuyer = 0;
        String remainTime = "";
        java.util.Date endTime = Calendar.getInstance().getTime();
        boolean isVoucher = true;
        String addressString = "";
        String addressDescription = "";
        String data = new String();
        int tryNumber = 0;
        // Loop for get html content (try 5 times)
        while (data.isEmpty()
                && (tryNumber < GlobalVariable.MAX_TRY)) {
            try {
                if (tryNumber == 0) {
                    System.out.println("Đang connect tới server để lấy dữ liệu, bạn đợi chút nhé...");
                }
                tryNumber++;
                data = new UtilHtmlToXML().readHtmlToBuffer(url).toString();
            } catch (Exception e) {
                System.out.println("Tèo lần " + tryNumber + " rồi! Đang thử lại...");
                continue;
            }
        }
        // Convert "Html Entities" character to Unicode
        // NguyenPT - 2012/12/24 - No need remove unescape characters - Del Start
        //data = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(data);
        // NguyenPT - 2012/12/24 - No need remove unescape characters - Del End
        if (!data.isEmpty()) {
            System.out.println("Đã lấy dữ liệu thành công. Đừng nóng, đang lọc mớ hỗn độn đó đây. Plz wait...");
            Pattern patt = Pattern.compile(GlobalVariable.HOTDEAL_REGEX);
            //Matcher match = patt.matcher(data);
            Matcher match = GeneralUtil.createMatcherWithTimeout(data, patt, GlobalVariable.GET_DEAL_PAGE_TIMEOUT);
            int count = 0;
            // Find matcher
            try {
                while (match.find()) {
                    // ----- Get Deal's info -----
                    // Link
                    link = "http://www.hotdeal.vn" + match.group(1).trim().replace("\"", "");
                    // Deal has exist in datastore
                    if (DealBLO.INSTANCE.isLinkExist(link)) {
                        continue;
                    }
                    // Image link
                    imageLink = match.group(4).replace("\"", "").replace("'", "").trim();
                    // Title
                    title = match.group(7).trim();
                    // Isvoucher
                    String isVoucherString = match.group(8).trim();
                    isVoucher = (isVoucherString.compareToIgnoreCase(GlobalVariable.VOUCHER) == 0);
                    // Description
                    description = match.group(9).trim();
                    // Price
                    price = GeneralUtil.getPriceFromString(match.group(10).trim());
                    // Basic price
                    String basicPriceString = match.group(11);
                    basicPrice = GeneralUtil.getPriceFromString(basicPriceString.trim());
                    // Unit price
                    unitPrice = GlobalVariable.VND;
                    // Number buyer
                    numberBuyer = Integer.parseInt(match.group(13).trim());
                    // EndTime
                    remainTime = match.group(14).trim();
                    endTime = GeneralUtil.getEndTime(remainTime);
                    // Address
                    String contentHtml = "";
                    contentHtml = getHtmlData(link);
                    try {
                        addressString = getAddressFromHotDealVn(contentHtml);
                    } catch (RuntimeException ex) {
                        continue;
                    }
                    // ----- Write log Deal's info -----
                    itemContent = "+ Deal thứ: " + String.valueOf(count + 1);
                    itemContent += "\n Title: " + title;
                    itemContent += "\n Description: " + description;
                    itemContent += "\n URl: " + link;
                    itemContent += "\n Link image: " + imageLink;
                    itemContent += "\n Price: " + match.group(10).trim();
                    itemContent += "\n Basic price: " + basicPriceString;
                    itemContent += "\n Unit: " + unitPrice;
                    itemContent += "\n Time: " + remainTime;
                    itemContent += "\n Is voucher: " + isVoucher;
                    itemContent += "\n Sold: " + numberBuyer;
                    itemContent += "\nAddress: " + addressString;
                    count++;
                    System.out.println(itemContent);
                    Deal deal = new Deal(title, description, link,
                            imageLink, price, basicPrice, unitPrice, 0.0f,
                            numberBuyer, endTime, isVoucher,
                            Status.SELLING.ordinal(),
                            getCategoryFromHotDealVn(contentHtml));
                    Long dealId = DealBLO.INSTANCE.insert(deal);
                    // Convert to latitude and longitude
                    String latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
                    if (!latlng.equals("")) {   // Convert success
                        double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                        double lng = Double
                                .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                        Address address = new Address(addressString, lng, lat, addressDescription);
                        // Insert address to data store
                        Long addressId = AddressBLO.INSTANCE.insert(address);
                        if (addressId != 0) {
                            AddressDetail addressDetail = new AddressDetail(dealId, addressId);
                            // Insert address detail to data store
                            AddressDetailBLO.INSTANCE.insert(addressDetail);
                        }
                    }
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.toString());
            }
            content += "\n" + itemContent;
        }
        return content;
    }
    /**
     * Get html content from an url.
     * @param url Url to get content
     * @return Html content from an url
     */
    public static String getHtmlData(String url) {
        String data = new String();
        int tryNumber = 0;
        // Loop for get html content (try 5 times)
        while (data.isEmpty()
                && (tryNumber < GlobalVariable.MAX_TRY)) {
            try {
                if (tryNumber == 0) {
                    System.out.println("Đang connect tới server để lấy dữ liệu, bạn đợi chút nhé...");
                }
                tryNumber++;
                data = new UtilHtmlToXML().readHtmlToBuffer(url).toString();
            } catch (Exception e) {
                System.out.println("Tèo lần " + tryNumber + " rồi! Đang thử lại...");
                continue;
            }
        }
        return data;
    }
    /**
     * Get address from http://www.hotdeal.vn/ link.
     * @param data Html content
     * @return a string represent address get from url
     * @throws Exception Exception threw
     */
    public static String getAddressFromHotDealVn(String data) throws Exception {
        String address = "";
        Pattern patt = null;
        Matcher match = null;
        int timeoutMillis = GlobalVariable.GET_ADDRESS_TIMEOUT;
        if (!data.isEmpty()) {
            int type = 0;
            String regexAddress = "";
            int location = 0;
            // Address in bottom
            if (data.contains("box_stand_star")) {
                type = 0;
            } else {
                // Address has no image
                regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B;
                patt = Pattern.compile(regexAddress);
                //match = patt.matcher(data);
                match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                if (match.find()) {
                    type = 1;
                    // Address has Website info
                    if (match.group(0).trim().contains("Website")) {
                        type = 3;
                    }
                } else {
                    // Address has image
                    type = 2;
                }
            }
            switch (type) {
                case 0 :
                    regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_A;
                    location = 2;
                    patt = Pattern.compile(regexAddress);
                    //match = patt.matcher(data);
                    match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                    if (match.find()) {
                        address = match.group(location).trim();
                        // Address so greater
                        if (address.length() > 500) {
                            regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_A1;
                            location = 2;
                            patt = Pattern.compile(regexAddress);
                            //match = patt.matcher(data);
                            match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                            if (match.find()) {
                                address = match.group(location).trim();
                            } else {
                                address = GlobalVariable.NO_ADDRESS;
                            }
                        }
                    } else {
                        address = GlobalVariable.NO_ADDRESS;
                    }
                    break;
                case 1:
                    regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B;
                    location = 3;
                    patt = Pattern.compile(regexAddress);
                    //match = patt.matcher(data);
                    match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                    if (match.find()) {
                        address = match.group(location).trim();
                        if (address.contains("ĐT")) {
                            address = match.group(location - 1).trim();
                        } else if (address.length() > 200) {
                            regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B3;
                            location = 2;
                            patt = Pattern.compile(regexAddress);
                            //match = patt.matcher(data);
                            match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                            if (match.find()) {
                                address = match.group(2).trim();
                            }
                        }
                    } else {
                        address = GlobalVariable.NO_ADDRESS;
                    }
                    break;
                case 2:
                    regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B1;
                    patt = Pattern.compile(regexAddress);
                    //match = patt.matcher(data);
                    match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                    if (match.find()) {
                        address = match.group(4).trim() + match.group(5).trim();
                        if (address.length() > 100) {
                            regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B4;
                            patt = Pattern.compile(regexAddress);
                            //match = patt.matcher(data);
                            match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                            if (match.find()) {
                                address = match.group(4).trim();
                            }
                        }
                    } else {
                        address = GlobalVariable.HOTDEAL_DEFAULT_ADDRESS;
                    }
                    break;
                case 3:
                    regexAddress = GlobalVariable.HOTDEAL_REGEX_ADDRESS_TYPE_B2;
                    patt = Pattern.compile(regexAddress);
                    //match = patt.matcher(data);
                    match = GeneralUtil.createMatcherWithTimeout(data, patt, timeoutMillis);
                    if (match.find()) {
                        address = match.group(2).trim();
                    } else {
                        address = GlobalVariable.NO_ADDRESS;
                    }
                    break;
                default :
                    break;
            }
        }
        // Convert "Html Entities" character to Unicode
        address = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(
                address.replace(GlobalVariable.NON_BREAKING_SPACE, ""));
        if (address.equals(GlobalVariable.NO_ADDRESS)) {
            address = "";
            // Write log
        }
        return address.trim();
    }
    /**
     * Get category from hotdeal.
     * @param data Html content
     * @return Id of category
     * @throws Exception Exception
     */
    public static Long getCategoryFromHotDealVn(String data) throws Exception {
        Long categoryId = (long) 0;
        String categoryName = "";
        String description = "";
        Long parentId = (long) 0;
        String categoryLink = "";
        Pattern patt = null;
        Matcher match = null;
        if (!data.isEmpty()) {
            patt = Pattern.compile(GlobalVariable.HOTDEAL_REGEX_CATEGORY);
            match = GeneralUtil.createMatcherWithTimeout(data, patt, GlobalVariable.GET_ADDRESS_TIMEOUT);
            try {
                while (match.find()) {
                    // ----- Get Deal's info -----
                    categoryLink = match.group(14).trim();
                    categoryName = match.group(17).trim();
                    System.out.println("Category Link: " + categoryLink);
                    System.out.println("Category Name: " + categoryName);
                    if (!categoryName.isEmpty()) {
                        if (!CategoryBLO.INSTANCE.isCategoryNameExist(categoryName)) {
                            Category category = new Category(categoryName, description, parentId, categoryLink);
                            categoryId = CategoryBLO.INSTANCE.insert(category);
                        } else {
                            Category category = CategoryBLO.INSTANCE.getCategoryByName(categoryName);
                            if (category != null) {
                                categoryId = category.getId();
                            }
                        }
                    }
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.toString());
            }
        }
        return categoryId;
    }
    /**
     * Get deal information from http://www.nhommua.com.
     * @return String represent content collected
     * @throws Exception Exception maybe happen
     */
    public static String getFromNhomMuaCom() throws Exception {
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
        Calendar cal = Calendar.getInstance();
        java.util.Date endTime = cal.getTime();
        boolean isVoucher = true;
        String[] linkList = {
                "http://old.nhommua.com/tp-ho-chi-minh/mua-hang-gia-re.html",
        };
        String regexItem1 =  "<span\\s+class=\'amo\'>.*?"
                               + "<i\\s+class=\'text\'>(.*?)</i>"
                           + "</span>.*?<i.*?>.*?</i>.*?"
                           + "<span\\s+class=\'time\'>"
                               + "<i.*?>.*?</i>.*?"
                               + "<i\\s+class=\'text\'.*?>(.*?)</i></span>";
        String regexItem2 =   "<div\\s+class=\'save-amo-time smallbox\'>.*?"
                                + "<span.*?>.*?"
                                    + "<i.*?>.*?</i>.*?<i.*?>.*?</i>.*?"
                                + "</span>.*?<i.*?>.*?</i>.*?"
                                + "<span\\s+class=\'time\'>"
                                    + "<i.*?>.*?</i>.*?<i\\s+class=\'text\'.*?>(.*?)</i>.*?"
                                + "</span>.*?</div>.*?"
                            + "<div\\s+class=\'save-amo-time smallbox1\'>.*?"
                                + "<span.*?>.*?"
                                    + "<i.*?>.*?</i>.*?<i.*?>.*?</i>.*?"
                                + "</span>.*?<i.*?>.*?</i>.*?"
                                + "<span\\s+class=\'time\'>.*?"
                                    + "<i.*?>.*?</i>.*?<i\\s+class=\'text\'>(.*?)</i>.*?"
                                + "</span>.*?</div>";
        int count = 0;
        try {
            for (int i = 0; i < linkList.length; i++) {
                String data = "";
                data = new UtilHtmlToXML().readHtmlToBuffer(linkList[i]).toString();
                String regex = "<div\\s+class=\'small-box-white\'>.*?"
                                 + "<h1><a\\s+href=\'(.*?)\'.*?>(.*?)</a></h1>.*?"
                                 + "<div\\s+class=\'small-box-img\'>.*?"
                                     + "<a.*?>"
                                         + "<img\\s+src=\'(.*?)\'.*?/>"
                                         + "<span\\s+class=\'(.*?)\'></span>"
                                     + "</a><i></i>.*?"
                                 + "</div>.*?"
                                 + "<div\\s+class=\'small-box-text\'>.*?"
                                     + "<p>(.*?)</p>.*?</div>.*?"
                                 + "<div\\s+class=\'small-box-button\'>.*?"
                                     + "<a.*?></a>.*?</div>.*?"
                                 + "<div\\s+class=\'small-box-sale\'>.*?"
                                     + "<p>(.*?)<span>(.*?)</span></p>.*?"
                                     + "<div\\s+class=\'small-box-save\'>.*?"
                                         + "<span.*?>(.*?)%</span>.*?"
                                         + "<span>.*?</span>.*?"
                                     + "</div>.*?"
                                     + "<div\\s+class=\'small-box-price\'>.*?"
                                         + "<span.*?>(.*?)</span>.*?"
                                         + "<span>.*?</span>.*?"
                                     + "</div>.*?"
                                 + "</div>.*?<div.*?></div>.*?"
                             + "</div>";
                Pattern patt = Pattern.compile(regex);
                Matcher match = patt.matcher(data);
                while (match.find()) {
                    count++;
                    boolean flagNumberBuyer = false;
                    link = match.group(1).trim();
                    content += "\nLink: " + link;
                    // Get info from local link
                    String dataItem = new UtilHtmlToXML().readHtmlToBuffer(link).toString();
                    Pattern pattItem = Pattern.compile(regexItem1);
                    Matcher matchItem = pattItem.matcher(dataItem);
                    String remainTime = "";
                    String numberBuyerString = "";
                    if (matchItem.find()) {
                        numberBuyerString = matchItem.group(1).trim();
                        numberBuyer = Integer.parseInt(numberBuyerString);
                        remainTime = matchItem.group(2).trim();
                        endTime = GeneralUtil.getEndTime(remainTime);
                        flagNumberBuyer = true;
                    }
                    if (!flagNumberBuyer) {
                        pattItem = Pattern.compile(regexItem2);
                        matchItem = pattItem.matcher(dataItem);
                    }
                    if (matchItem.find()) {
                        remainTime = matchItem.group(1).trim();
                        endTime = GeneralUtil.getEndTime(remainTime);
                        numberBuyerString = matchItem.group(2).trim();
                        numberBuyer = Integer.parseInt(numberBuyerString);
                    }
                    content += "\nRemain time: " + remainTime;
                    content += "\nNumber buyer: " + numberBuyerString;
                    title = match.group(2).trim();
                    content += "\nTitle: " + title;
                    imageLink = match.group(3).trim();
                    content += "\nImage: " + imageLink;
                    String voucher = match.group(4).trim();
                    isVoucher = voucher.equals("giaovoucher");
                    content += "\nIs voucher: " + isVoucher;
                    description = match.group(5).trim();
                    content += "\nDescription: " + description;
                    price = GeneralUtil.getPriceFromString(match.group(6).trim());
                    content += "\nPrice: " + price;
                    unitPrice = match.group(7).trim();
                    content += "\nUnit price: " + unitPrice;
                    String saveString = match.group(8).trim();
                    save = Float.parseFloat(saveString);
                    content += "\nSave: " + saveString;
                    basicPrice = price / (1 - save / 100);
                    NumberFormat df = new DecimalFormat("0");
                    String basicPriceString = df.format(basicPrice);
                    basicPrice = Double.parseDouble(basicPriceString);

                    content += "\n=================================" + String.valueOf(count);

                    Deal deal = new Deal(title, description, link, imageLink, price,
                            basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher);
                    Long dealId = DealBLO.INSTANCE.insert(deal);
                    getAddressFromNhomMua(dataItem, dealId);
                }
            }
        } catch (Exception ex) {
            content += ex.getMessage();
        }
        return content;
    }
    /**
     * Get address from http://www.nhommua.com.
     * @param data Data html
     * @param dealId Deal's Id
     * @throws Exception Exception
     */
    public static void getAddressFromNhomMua(String data, Long dealId) throws Exception {
        String addressString = "";
        String addressDescription = "";
        String regex = "<li>.*?"
                         + "<a.*?>.*?"
                             + "<em>.*?</em>.*?"
                         + "</a>.*?"
                         + "<p>"
                             + "<span\\s+itemprop=\'tel\'>(.*?)</span>.*?"
                         + "</p>.*?"
                         + "<span.*?>.*?</span>.*?"
                         + "<a.*?>.*?</a>.*?"
                     + "</li>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            // Get address content
            addressString = matcher.group(1).trim();
            addressString = GeneralUtil.addressNormalization(addressString);
            //System.out.println(addressString);
            // Convert to latitude and longitude
            String latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
            //System.out.println(latlng);
            if (!latlng.equals("")) {   // Convert success
                double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                double lng = Double
                        .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                Address address = new Address(addressString, lng, lat, addressDescription);
                // Insert address to data store
                Long addressId = AddressBLO.INSTANCE.insert(address);
                if (addressId != 0) {
                    AddressDetail addressDetail = new AddressDetail(dealId, addressId);
                    // Insert address detail to data store
                    AddressDetailBLO.INSTANCE.insert(addressDetail);
                }
            }
        }
    }
    /**
     * Get deal information from http://www.muachung.vn.
     * @return String represent content collected
     * @throws Exception Exception maybe happen
     */
    public static String getFromMuaChungVnNew() throws Exception {
        String content = "";
        String title = "";
        String description = "";
        String addressString = "";
        String link = "";
        String imageLink = "";
        double price = 0;
        double basicPrice = 0;
        String unitPrice = "";
        Calendar cal = Calendar.getInstance();
        java.util.Date endTime = cal.getTime();
        float save = 0;
        int numberBuyer = 0;
        boolean isVoucher = true;
        String[] listLink = new String[] {
                "http://muachung.vn/danh-muc/c-999999997/deal-dang-ban/trang-1.html"
                };
        List<Integer> cityId = new ArrayList<Integer>();
        cityId.add(GlobalVariable.CITY_HANOI);
        cityId.add(GlobalVariable.CITY_TPHCM);
        cityId.add(GlobalVariable.CITY_DANANG);
        cityId.add(GlobalVariable.CITY_NHATRANG);
        cityId.add(GlobalVariable.CITY_HAIPHONG);
        cityId.add(GlobalVariable.CITY_VUNGTAU);
        cityId.add(GlobalVariable.CITY_CANTHO);
        for (int k = 0; k < cityId.size(); k++) {
            try {
                String data = new String();
                while (data.isEmpty()) {
                    try {
                        data = new UtilHtmlToXML().readHtmlToBufferCookie(listLink[0].toString(),
                                (Integer) cityId.get(k)).toString();
                    } catch (SocketTimeoutException ex) {
                        continue;
                    }
                }
                String regex = "<div\\s+class=\"box-frame-center\".*?>"
                        + ".*?<div\\s+class=\"v6ItemHotImg\".*?>"
                        + ".*?<div.*?>"
                        //+".*?</div>"//end div
                        + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"//link
                        + ".*?<div.*?>.*?</div>"
                        + ".*?<div\\s+class=\"v6ItemHotHoverTextT\".*?>"
                        + ".*?<div>.*?<b>(.*?)</b>.*?</div>"//save 4
                        + "(.*?)"//address 5
                        //+"<span.*?>.*?</span>"
                        + "</div>"//end div v6ItemHotHoverTextT
                        + ".*?</a>"//end a
                        + ".*?<div\\s+class=\"v6HotItemHoverButton\".*?>"
                        + ".*?<div.*?>"
                        + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"//link image 6
                        + ".*?</a>"//end a
                        + ".*?</div>"//end div
                        + ".*?</div>"//end div v6HotItemHoverButton
                        + ".*?</div>"//add
                        + ".*?</div>"//end div v6ItemHotImg
                        //+".*?<div\\s+class=\"v6ContentHot\".*?>"
                        + ".*?<div.*?>"
                        + "<h3.*?>"
                        + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>(.*?)</a>"// link 9 title 12
                        + "</h3>"
                        + "(.*?)</div>"// 13end div description
                        + ".*?<div\\s+class=\"v6TopBorder\".*?>"
                        + ".*?<div.*?>"
                        + ".*?<div.*?>(.*?)</div>"//price 14
                        + ".*?<div.*?>(.*?)</div>"//basic price 15
                        + ".*?</div>"//end div f1
                        + ".*?<div.*?>"
                        + ".*?<div.*?>.*?</div>"
                        + ".*?<div.*?>(.*?)</div>"//number buy 16
                        + ".*?</div>"//end div
                        + ".*?<div.*?>.*?</div>"
                        + ".*?</div>"//end div v6TopBorder
                       // +".*?</div>"//end div v6ContentHot
                       + "</div>"; //end div
                Pattern patt = Pattern.compile(regex);
                Matcher match = patt.matcher(data);
                while (match.find()) {
                    //get address
                    String stringHtml = new String();
                    String endTimeNum = "";
                    while (stringHtml.isEmpty()) {
                        try {
                            stringHtml = new UtilHtmlToXML().readHtmlToBuffer(
                                    match.group(9).replace("\"", "").trim()).toString();
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    if (!stringHtml.isEmpty()) {
                        Document document = new UtilReadXML().readContentXML(stringHtml);
                        NodeList nList = document.getElementsByTagName("a");
                        for (int i = 0; i < nList.getLength(); i++) {
                            if (nList.item(i).hasAttributes()) {
                                //L?y các thu?c tính c?a th? div hi?n th?i
                                NamedNodeMap att = nList.item(i).getAttributes();
                                for (int j = 0; j < att.getLength(); j++) {
                                    if (att.item(j).getNodeName().equals("class")) {
                                        if (att.item(j).getTextContent().equals("adrname")) {
                                            System.out.println("Address : "
                                        + nList.item(i).getFirstChild().getNextSibling().getTextContent().trim());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        //get long end time
                        String endTimeString = "shop.product.init";
                        int indexStringEndTime = stringHtml.indexOf(endTimeString) + 18;
                        StringBuilder stringEndTime = new StringBuilder();
                        int countComma = 0;
                        Boolean getString = false;
                        while (true) {
                            indexStringEndTime++;
                            /*if (stringHtml.charAt(indexStringEndTime) == ')') {
                                break;
                            }*/
                            if (stringHtml.charAt(indexStringEndTime) == ',') {
                                if (countComma == 1) {
                                    getString = true;
                                }
                                if (countComma == 2) {
                                    break;
                                }
                                countComma++;
                            }
                            if (getString) {
                                stringEndTime.append(stringHtml.charAt(indexStringEndTime));
                            }
                        }
                        endTimeNum = String.valueOf(Long.valueOf(stringEndTime.toString().replace(",", "")) * 1000);
                    }
                    ScriptEngineManager factory = new ScriptEngineManager();
                    ScriptEngine engine = factory.getEngineByName("js");
                    StringBuilder script = new StringBuilder();
                    script.append("var time  = new Date(");
                    script.append(endTimeNum);
                    script.append(");");
                    script.append("var date = time.getDate();");
                    script.append("var month = time.getMonth() + 1;");
                    script.append("var year = time.getFullYear();");
                    script.append("var hour = time.getHours();");
                    script.append("var minute = time.getMinutes();");
                    script.append("var second = time.getSeconds();");
                    engine.eval(script.toString());
                    int date = Integer.valueOf(
                            engine.get("date").toString().substring(0, engine.get("date").toString().indexOf(".")));
                    int month = Integer.valueOf(
                            engine.get("month").toString().substring(0, engine.get("month").toString().indexOf(".")));
                    int year = Integer.valueOf(
                            engine.get("year").toString().substring(0, engine.get("year").toString().indexOf(".")));
                    int hour = Integer.valueOf(
                            engine.get("hour").toString().substring(0, engine.get("hour").toString().indexOf(".")));
                    int minute = Integer.valueOf(
                            engine.get("minute").toString().substring(0, engine.get("minute").toString().indexOf(".")));
                    int second = Integer.valueOf(
                            engine.get("second").toString().substring(0, engine.get("second").toString().indexOf(".")));
                    //Date _date = new Date(year, month, date, hour, minute, second);
                    cal.set(year, month, date, hour, minute, second);
                    endTime = cal.getTime();
                    title = match.group(12);
                    description = match.group(13).replace("-", "").trim();
                    //address = "";
                    link = match.group(9).replace("\"", "").trim();
                    imageLink = match.group(6).replace("\"", "").trim();
                    price = Double.parseDouble(match.group(14).trim().replace(".", "").replace("d", ""));
                    basicPrice = Double.parseDouble(match.group(15).trim().replace(".", "").replace("d", ""));
                    unitPrice = "";
                    //float save = 0;
                    numberBuyer = Integer.valueOf(match.group(16));
                    unitPrice = GlobalVariable.VND;
                    Deal deal = new Deal(title, description, link, imageLink, price, basicPrice, unitPrice, save,
                            numberBuyer, endTime, isVoucher);
                    Long dealId = DealBLO.INSTANCE.insert(deal);
                    addressString = GeneralUtil.addressNormalization(addressString);
                    String addressDescription = "";
                    String latlng = GeneralUtil.convertAddressToLatitudeLongitude(addressString);
                    if (!latlng.equals("")) {
                        double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                        double lng = Double.parseDouble(latlng.substring(latlng.indexOf(",") + 1,
                                latlng.length()));
                        Address address = new Address(addressString, lng, lat, addressDescription);
                        Long addressId = AddressBLO.INSTANCE.insert(address);
                        if (addressId != 0) {
                            AddressDetail detail = new AddressDetail(dealId, addressId);
                            AddressDetailBLO.INSTANCE.insert(detail);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return content;
    }
    /**
     * Get deal from dealvip.vn.
     * @param url Url
     * @return Content
     */
    public static String getFromDealVip(String url) {
        String content = "";
        String itemContent = "";
        String title = "";
        String description = "";
        String link = "";
        String imageLink = "";
        double price = 0;
        double basicPrice = 0;
        String unitPrice = "";
        int numberBuyer = 0;
        String remainTime = "";
        java.util.Date endTime = Calendar.getInstance().getTime();
        boolean isVoucher = true;
        String addressString = "";
        String addressDescription = "";
        String data = getHtmlData(url);
        if (!data.isEmpty()) {
        data = data.replace(GlobalVariable.DEALVIP_SPAN, "");
        data = data.replace(GlobalVariable.DEALVIP_ENDFEATURE, "");
            System.out.println("Đã lấy dữ liệu thành công. Đừng nóng, đang lọc mớ hỗn độn đó đây. Plz wait...");
            Pattern patt = Pattern.compile(GlobalVariable.DEALVIP_REGEX);
            Matcher match = GeneralUtil.createMatcherWithTimeout(data, patt, GlobalVariable.GET_DEAL_PAGE_TIMEOUT);
            int count = 0;
            // Find matcher
            try {
                while (match.find()) {
                    // ----- Get Deal's info -----
                    // Link
                    link = "http://www.dealvip.vn" + match.group(1).trim().replace("\"", "");
                    // Deal has exist in datastore
                    if (DealBLO.INSTANCE.isLinkExist(link)) {
                        continue;
                    }
                    // Image link
                    imageLink = match.group(12).replace("\"", "").trim();
                    // Title
                    title = match.group(4).trim();
                    // Isvoucher
                    String isVoucherString = match.group(8).trim();
                    isVoucher = (isVoucherString.compareToIgnoreCase(GlobalVariable.DEALVIP_GIAOVOUCHER) == 0);
                    // Description
                    description = match.group(15).trim();
                    // Price
                    String priceString = match.group(17).trim();
                    price = GeneralUtil.getPriceFromString(priceString);
                    // Basic price
                    String basicPriceString = match.group(16).trim();
                    basicPrice = GeneralUtil.getPriceFromString(basicPriceString);
                    // Unit price
                    unitPrice = GlobalVariable.VND;
                    // Number buyer
                    numberBuyer = Integer.parseInt(match.group(23).trim());
                    // EndTime
                    String remainTimeString = match.group(24).trim();
                    endTime = GeneralUtil.getEndTimeFromDealVip(remainTimeString);
                    // Address
                    String contentHtml = "";
                    contentHtml = getHtmlData(link);
                    
                    // ----- Write log Deal's info -----
                    itemContent = "+ Deal thứ: " + String.valueOf(count + 1);
                    itemContent += "\n Title: " + title;
                    itemContent += "\n Description: " + description;
                    itemContent += "\n URl: " + link;
                    itemContent += "\n Link image: " + imageLink;
                    //itemContent += "\n Price: " + priceString;
                    priceString = String.valueOf(price);
                    itemContent += "\n Price: " + priceString.replace(".0", "");
                    //itemContent += "\n Basic price: " + basicPriceString;
                    basicPriceString = String.valueOf(basicPrice);
                    itemContent += "\n Basic price: " + basicPriceString.replace(".0", "");
                    itemContent += "\n Unit: " + unitPrice;
                    itemContent += "\n Time: " + remainTimeString;
                    //itemContent += "\n Is voucher: " + isVoucherString;
                    itemContent += "\n Is voucher: " + isVoucher;
                    itemContent += "\n Sold: " + numberBuyer;
                    //itemContent += "\nAddress: " + addressString;
                    count++;
                    System.out.println(itemContent);
                    System.out.println(getCategoryFromDealVip(contentHtml));
                    Deal deal = new Deal(title, description, link,
                            imageLink, price, basicPrice, unitPrice, 0.0f,
                            numberBuyer, endTime, isVoucher,
                            Status.SELLING.ordinal(),
                            getCategoryFromDealVip(contentHtml));
                    Long dealId = DealBLO.INSTANCE.insert(deal);
                    // Convert to latitude and longitude
                    String latlng = getAddressFromDealVip(contentHtml);
                    if (!latlng.equals("")) {   // Convert success
                        double lat = Double.parseDouble(latlng.substring(0, latlng.indexOf(",")));
                        double lng = Double
                                .parseDouble(latlng.substring(latlng.indexOf(",") + 1, latlng.length()));
                        Address address = new Address("", lng, lat, "");
                        // Insert address to data store
                        Long addressId = AddressBLO.INSTANCE.insert(address);
                        if (addressId != 0) {
                            AddressDetail addressDetail = new AddressDetail(dealId, addressId);
                            // Insert address detail to data store
                            AddressDetailBLO.INSTANCE.insert(addressDetail);
                        }
                    }
                }
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //System.out.println(data);
        return content;
    }
    /**
     * Get category content from DealVip.
     * @param data Html content
     * @return Id of category
     */
    public static Long getCategoryFromDealVip(String data) {
        Long categoryId = (long) 0;
        Pattern patt = null;
        Matcher match = null;
        if (!data.isEmpty()) {
            patt = Pattern.compile(GlobalVariable.DEALVIP_REGEX_CATEGORY);
            match = GeneralUtil.createMatcherWithTimeout(data, patt, GlobalVariable.GET_ADDRESS_TIMEOUT);
            try {
                while (match.find()) {
                    String content = match.group(1).trim();
                    System.out.println(" Category: " + content);
                    if (content.contains(GlobalVariable.DEALVIP_CATEGORY_FOOD)) {
                        return (long) 1;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_PREEN)) {
                        return (long) 2;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_FASHION)) {
                        return (long) 3;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_TECHNOLOGY)) {
                        return (long) 4;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_HOUSEHOLD)) {
                        return (long) 5;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_CHILDREN)) {
                        return (long) 6;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_TETHOLIDAY)) {
                        return (long) 7;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_TRAVEL)) {
                        return (long) 8;
                    } else if (content.contains(GlobalVariable.DEALVIP_CATEGORY_OTHER)) {
                        return (long) 9;
                    } else {
                        return (long) 0;
                    }
                    /*String regex = "|\\s(.*?)\\s+|";
                    Pattern pattX = Pattern.compile(regex);
                    Matcher matchX = pattX.matcher(content);
                    while (matchX.find()) {
                        categoryName = matchX.group(1);
                        break;
                    }*/
                    /*String[] listString = categoryName.split(" | ");
                    if (listString.length > 1) {
                        categoryName = listString[1];
                    }*/
                    /*if (!categoryName.isEmpty()) {
                        if (!CategoryBLO.INSTANCE.isCategoryNameExist(categoryName)) {
                            Category category = new Category(categoryName, description, parentId, categoryLink);
                            categoryId = CategoryBLO.INSTANCE.insert(category);
                        } else {
                            Category category = CategoryBLO.INSTANCE.getCategoryByName(categoryName);
                            if (category != null) {
                                categoryId = category.getId();
                            }
                        }
                    }*/
                }
            } catch (RuntimeException ex) {
                //System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return categoryId;
    }
    public static String getAddressFromDealVip(String data) {
        String addressString = "";
        Pattern patt = null;
        Matcher match = null;
        if (!data.isEmpty()) {
            data.replace(GlobalVariable.DEALVIP_VIEWMAP, "");
            patt = Pattern.compile(GlobalVariable.DEALVIP_REGEX_ADDRESS);
            match = GeneralUtil.createMatcherWithTimeout(data, patt, GlobalVariable.GET_ADDRESS_TIMEOUT);
            try {
                while (match.find()) {
                    addressString = match.group(1).trim();
                    addressString = addressString.substring(addressString.lastIndexOf(GlobalVariable.DEALVIP_MARKER) + GlobalVariable.DEALVIP_MARKER.length(), addressString.lastIndexOf(GlobalVariable.DEALVIP_SENSOR));
                    System.out.println(addressString);
                }
            } catch (RuntimeException ex) {
                //System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return addressString;
    }
}
