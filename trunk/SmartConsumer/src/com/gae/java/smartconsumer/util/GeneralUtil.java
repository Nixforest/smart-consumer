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

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gae.java.smartconsumer.model.Deal;

/**
 * @author Nixforest
 *
 * A class provide some method for utility
 */
public class GeneralUtil {
    /**
     * Method get present time.
     * @param dateFormat String represent date format
     * @return String represent present time
     */
    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
    /**
     * Method get a sub string from a string.
     * @param str String to get substring
     * @param maxLength Maximum of substring's length
     * @return Substring
     */
    public static String getSubString(String str, int maxLength) {
        String result = str;
        if (str.length() >= maxLength) {
            str = str.substring(0, maxLength - 1);
            int index = str.lastIndexOf(" ");
            if (index != -1) {
                result = str.substring(0, index) + "...";
            }
        }
        return result;
    }
    /**
     * 
     * [Give the description for method].
     * @param price
     * @return
     * @throws Exception 
     */
    public static double getPriceFromString(String price) throws Exception {
        double result = 0;
        try {
            price = price.substring(0, price.lastIndexOf("0") + 1);
            price = price.replace(",", "");
            price = price.replace(".", "").trim();
            //price = price.replace(" VNĐ", "");
            result = Double.parseDouble(price.trim());
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     * 
     * [Give the description for method].
     * @param sTag
     * @param eElement
     * @param location
     * @return
     */
    public static String getTagValue(String sTag, Element eElement, int location) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node)nlList.item(location);
        return nValue.getNodeValue();
    }
    /**
     * 
     * Method get the EndTime of Deal from string remainTime.
     * @param remainTime String represent remaintime get from deal (hh:mm:ss)
     * @return a Date object represent EndTime of Deal
     */
    public static java.util.Date getEndTime(String remainTime) {
        Date result = Calendar.getInstance().getTime();
        int hour = 0;
        int minute = 0;
        int second = 0;
        hour = Integer.parseInt(remainTime.substring(0, remainTime.indexOf(":")));
        remainTime = remainTime.substring(remainTime.indexOf(":")+1, remainTime.length());
        minute = Integer.parseInt(remainTime.substring(0, remainTime.indexOf(":")));
        remainTime = remainTime.substring(remainTime.indexOf(":")+1, remainTime.length());
        second = Integer.parseInt(remainTime);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        result = calendar.getTime();
        return result;
    }
    /**
     * 
     * Method get the string remainTime from endtime.
     * @param endTime endtime of deal
     * @return String represent remainTime (hh:mm:ss)
     */
    public static String getRemainTime(Date endTime) {
        String result = "";
        Calendar calendar = Calendar.getInstance();
        long difmillisecond = endTime.getTime() - calendar.getTime().getTime();
        int hour = (int) (difmillisecond/(3600*1000));
        difmillisecond = difmillisecond - hour*3600*1000;
        int minute = (int) (difmillisecond/(60*1000));
        difmillisecond = difmillisecond - minute*60*1000;
        int second = (int) (difmillisecond/1000);
        result = String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);
        return result;
    }
    

    /**
     * Check if a string can convert to a number
     * @param s string input
     * @return return string s if it's not null
     */
    public static String checkNumber(final String s) {
        String result = s;
        if (result == null || result == "") {
            return "0";
        }
        result = result.replace(",", "");
        return result;
    }
    public static String cutOff(String str) {
        if (str.length() >= 500) {
            str = str.substring(0, 499);
        }
        return str;
    }
    public static Deal encodeDeal(Deal deal) throws Exception {
        try {
            String title = URLEncoder.encode(deal.getTitle(), "UTF-8");            
            deal.setTitle(cutOff(title));
            
            String description = URLEncoder.encode(deal.getDescription(), "UTF-8");
            deal.setDescription(cutOff(description));
            
            String address = URLEncoder.encode(deal.getAddress(), "UTF-8");
            deal.setAddress(cutOff(address));

            String unit = URLEncoder.encode(deal.getUnitPrice(), "UTF-8");
            deal.setUnitPrice(cutOff(unit));
            return deal;
        } catch (Exception ex) {
            throw ex;
        }
    }
    /*public static Deal encodeDeal(Deal deal) throws Exception {
        try {
            String title = URLEncoder.encode(deal.getTitle(), "UTF-8");
            if (title.length() >= 500) {
                title.substring(0, 499);
            }
            deal.setTitle(title);
            deal.setDescription(URLEncoder.encode(deal.getDescription(), "UTF-8"));
            deal.setAddress(URLEncoder.encode(deal.getAddress(), "UTF-8"));
            deal.setUnitPrice(URLEncoder.encode(deal.getUnitPrice(), "UTF-8"));
            return deal;
        } catch (Exception ex) {
            throw ex;
        }
    }*/
    
    public static Deal decodeDeal(Deal deal) throws Exception {
        try {
            deal.setTitle(URLDecoder.decode(deal.getTitle(), "UTF-8"));
            deal.setDescription(URLDecoder.decode(deal.getDescription(), "UTF-8"));
            deal.setAddress(URLDecoder.decode(deal.getAddress(), "UTF-8"));
            deal.setUnitPrice(URLDecoder.decode(deal.getUnitPrice(), "UTF-8"));
            return deal;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    private static String[] VietnameseSigns = new String[] { 
        "aAeEoOuUiIdDyY",
        "áàạảãâấầậẩẫăắằặẳẵ",
        "ÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴ",
        "éèẹẻẽêếềệểễ",
        "ÉÈẸẺẼÊẾỀỆỂỄ",
        "óòọỏõôốồộổỗơớờợởỡ",
        "ÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠ",
        "úùụủũưứừựửữ",
        "ÚÙỤỦŨƯỨỪỰỬỮ",
        "íìịỉĩ",
        "ÍÌỊỈĨ",
        "đ",
        "Đ",
        "ýỳỵỷỹ",
        "ÝỲỴỶỸ"
    };
    public static String RemoveSign4VietNameseString(String text)
    {
        for (int i = 1; i < VietnameseSigns.length; i++)
        {
            for (int j = 0; j < VietnameseSigns[i].length(); j++)
            {
                text = text.replace(VietnameseSigns[i].charAt(j), VietnameseSigns[0].charAt(i - 1));
            }
        }
        return text;
    }

    public static List<String> NormalizationString(String str)
    {
        while (str.indexOf("  ") != -1)
        {
            str = str.replace("  ", " ");
        }
        List<String> resutl = new ArrayList<String>();

        int index = 0;
        for (char item : str.toCharArray())
        {
            if (" ".equals(item))
            {
                resutl.add(str.substring(index, str.indexOf(item) - index));
                index = str.indexOf(item);
            }
        }
        resutl.add(str.substring(index, str.length() - index));
        return resutl;
    }
    public static String convertPriceToText(double price) {
        String strTextPrice = "";
        int priceBillion = (int) (price / 1000000000.0);
        int priceMillion = (int) ((price % 1000000000) / 1000000.0);
        int priceThousand = (int) (((price % 1000000000) % 1000000) / 1000.0);
        if (priceBillion > 0 && price > 900000000) {
            strTextPrice = strTextPrice + "<b>" + priceBillion + "</b> tỷ ";
        }
        if (priceMillion > 0) {
            strTextPrice = strTextPrice + "<b>" + priceMillion + "</b> triệu ";
        }
        if (priceThousand > 0) {
            strTextPrice = strTextPrice + "<b>" + priceThousand + "</b> ngàn ";
        }

        return strTextPrice + "<b>VNĐ</b>";
    }

    /**
     * Check if a char is a number.
     * @param c character to check
     * @return True if c is a number, false otherwise
     */
    public static boolean isNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Normalize a address.
     * @param result Address before normalization
     * @return Address after normalization
     */
    public static String addressNormalization(String result) {
        String address = result;
        address = address.replaceFirst("Địa chỉ:", "");
        int location = GeneralUtil.RemoveSign4VietNameseString(address).toLowerCase().indexOf("dt");
        if (location != -1) {
            address = address.substring(0, location);
        }
        location = GeneralUtil.RemoveSign4VietNameseString(address).toLowerCase().indexOf("dien thoai");
        if (location != -1) {
            address = address.substring(0, location);
        }
        location = GeneralUtil.RemoveSign4VietNameseString(address).toLowerCase().indexOf("Hotline");
        if (location != -1) {
            address = address.substring(0, location);
        }
        location = GeneralUtil.RemoveSign4VietNameseString(address).toLowerCase().indexOf("fax");
        if (location != -1) {
            address = address.substring(0, location);
        }
        for (char c : address.toCharArray()) {
            if (isNumber(c)) {
                return address.substring(address.indexOf(String.valueOf(c)));
            }
        }
        return address;
    }
}
