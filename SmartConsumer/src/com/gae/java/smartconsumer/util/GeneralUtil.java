package com.gae.java.smartconsumer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeneralUtil {
    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
    
    public static String getSubString(String str, int maxLength) {
        String result = str;
        if (str.length() >= maxLength) {
            str = str.substring(0, maxLength);
            int index = str.lastIndexOf(" ");
            result = str.substring(0, index) + "...";
        }
        return result;
    }
    //01234567891011121314151617181920212223242526272829303132
    //Dịch vụ nối   m i   H à n   Q u ố c   t ạ i   t h ẩ m  mỹ viện Hồng Loan
}
