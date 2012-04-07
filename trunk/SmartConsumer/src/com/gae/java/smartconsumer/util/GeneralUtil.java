package com.gae.java.smartconsumer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            str = str.substring(0, maxLength);
            int index = str.lastIndexOf(" ");
            result = str.substring(0, index) + "...";
        }
        return result;
    }
    /**
     * 
     * [Give the description for method].
     * @param price
     * @return
     */
    public static double getPriceFromString(String price) {
        double result = 0;
        try {
        price = price.substring(0, price.length() - 3);
        price = price.replace(",", "");
        price = price.replace(".", "");
        result = Double.parseDouble(price.trim());
        } catch (Exception e) {
            System.out.println(e.toString());
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
}
