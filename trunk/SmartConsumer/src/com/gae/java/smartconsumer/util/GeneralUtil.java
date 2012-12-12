/**
 * General Util.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gae.java.smartconsumer.model.Deal;

/**
 * A class provide some method for utility.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public final class GeneralUtil {
    /**
     * Constructor.
     */
    protected GeneralUtil() {
    }
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
     * Get price value (double) from string.
     * @param price string price
     * @return value of price string in double
     */
    public static double getPriceFromString(String price) {
        double result = 0;
        try {
            /*if (price.contains(",")) {
                price = price.replace(",", "");
            }*/
            if (price.contains(".")) {
                price = price.replace(".", "");
            }
            if (price.contains(GlobalVariable.VND)) {
                price = price.replace(GlobalVariable.VND, "");
            }
            if (price.contains(GlobalVariable.NON_BREAKING_SPACE)) {
                price = price.replace(GlobalVariable.NON_BREAKING_SPACE, "");
            }
            price = price.trim();
            price = price.substring(0, price.length() - 2);
            result = Double.parseDouble(price);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /**
     * Get the value in tag (read XML).
     * @param sTag tag name
     * @param eElement Element to read
     * @param location location of tag in element
     * @return string of value in tag
     */
    public static String getTagValue(String sTag, Element eElement, int location) {
        if (eElement.getElementsByTagName(sTag).getLength() != 0) {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(location);
            return nValue.getNodeValue();
        }
        return "";
    }

    /**
     * Method get the EndTime of Deal from string remainTime.
     * @param remainTime String represent remain time get from deal (hh:mm:ss)
     * @return a Date object represent EndTime of Deal
     */
    public static java.util.Date getEndTime(String remainTime) {
        Date result = Calendar.getInstance().getTime();
        int hour = 0;
        int minute = 0;
        int second = 0;
        hour = Integer.parseInt(remainTime.substring(0, remainTime.indexOf(":")));
        remainTime = remainTime.substring(remainTime.indexOf(":") + 1, remainTime.length());
        minute = Integer.parseInt(remainTime.substring(0, remainTime.indexOf(":")));
        remainTime = remainTime.substring(remainTime.indexOf(":") + 1, remainTime.length());
        second = Integer.parseInt(remainTime);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        result = calendar.getTime();
        return result;
    }

    /**
     * Method get string is value sub operation of two date.
     * @param d1 Date one
     * @param d2 Date two
     * @return String of result
     */
    public static String subDateTime(Date d1, Date d2) {
        String result = "";
        long difmillisecond = d1.getTime() - d2.getTime();
        int hour = (int) (difmillisecond / (3600 * 1000));
        difmillisecond = difmillisecond - hour * 3600 * 1000;
        int minute = (int) (difmillisecond / (60 * 1000));
        difmillisecond = difmillisecond - minute * 60 * 1000;
        int second = (int) (difmillisecond / 1000);
        result = String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);
        return result;
    }

    /**
     * Method get the string remain Time from end time.
     * @param endTime end time of deal
     * @return String represent remainTime (hh:mm:ss)
     */
    public static String getRemainTime(Date endTime) {
        String result = "";
        Calendar calendar = Calendar.getInstance();
        long difmillisecond = endTime.getTime() - calendar.getTime().getTime();
        int hour = (int) (difmillisecond / (3600 * 1000));
        difmillisecond = difmillisecond - hour * 3600 * 1000;
        int minute = (int) (difmillisecond / (60 * 1000));
        difmillisecond = difmillisecond - minute * 60 * 1000;
        int second = (int) (difmillisecond / 1000);
        result = String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);
        return result;
    }

    /**
     * Check if a string can convert to a number.
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

    /**
     * Cut a string if its length too 500.
     * @param str String to cut
     * @return String after cut
     */
    public static String cutOff(String str) {
        if (str.length() >= 500) {
            str = str.substring(0, 499);
        }
        return str;
    }

    /**
     * Encode a deal.
     * @param deal deal to encode
     * @return Deal after encode
     * @throws Exception exception when encode maybe happen
     */
    public static Deal encodeDeal(Deal deal) throws Exception {
        try {
            String title = URLEncoder.encode(deal.getTitle(), "UTF-8");
            deal.setTitle(cutOff(title));

            String description = URLEncoder.encode(deal.getDescription(), "UTF-8");
            deal.setDescription(cutOff(description));

            String unit = URLEncoder.encode(deal.getUnitPrice(), "UTF-8");
            deal.setUnitPrice(cutOff(unit));
            return deal;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Decode a deal.
     * @param deal deal to decode
     * @return Deal after decode
     * @throws Exception exception when decode maybe happen
     */
    public static Deal decodeDeal(Deal deal) throws Exception {
        try {
            deal.setTitle(URLDecoder.decode(deal.getTitle(), "UTF-8"));
            deal.setDescription(URLDecoder.decode(deal.getDescription(), "UTF-8"));
            deal.setUnitPrice(URLDecoder.decode(deal.getUnitPrice(), "UTF-8"));
            return deal;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /** String array to remove sign in Vietnamese. */
    private static String[] vietnameseSigns = new String[]{"aAeEoOuUiIdDyY", "áàạảãâấầậẩẫăắằặẳẵ", "ÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴ",
            "éèẹẻẽêếềệểễ", "ÉÈẸẺẼÊẾỀỆỂỄ", "óòọỏõôốồộổỗơớờợởỡ", "ÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠ", "úùụủũưứừựửữ", "ÚÙỤỦŨƯỨỪỰỬỮ",
            "íìịỉĩ", "ÍÌỊỈĨ", "đ", "Đ", "ýỳỵỷỹ", "ÝỲỴỶỸ"};

    /**
     * Method remove sign in Vietnamese string.
     * @param text String to remove sign
     * @return String after remove sign
     */
    public static String removeSign4VietNameseString(String text) {
        for (int i = 1; i < vietnameseSigns.length; i++) {
            for (int j = 0; j < vietnameseSigns[i].length(); j++) {
                text = text.replace(vietnameseSigns[i].charAt(j), vietnameseSigns[0].charAt(i - 1));
            }
        }
        return text;
    }

    /**
     * Replace notation.
     * @param text String to replace notation
     * @param notation1 notation 1
     * @param notation2 notation 2
     * @return String after replace notation
     */
    public static String replaceNotation(String text, String notation1, String notation2) {
        return text.replace(notation1, notation2);
    }

    /**
     * Normalization String method.
     * @param str String to normalize
     * @return String after normalize
     */
    public static List<String> normalizationString(String str) {
        while (str.indexOf("  ") != -1) {
            str = str.replace("  ", " ");
        }
        List<String> resutl = new ArrayList<String>();

        int index = 0;
        for (char item : str.toCharArray()) {
            if (" ".equals(item)) {
                resutl.add(str.substring(index, str.indexOf(item) - index));
                index = str.indexOf(item);
            }
        }
        resutl.add(str.substring(index, str.length() - index));
        return resutl;
    }

    /**
     * Convert price to text with format Vietnamese.
     * @param price Price to convert
     * @return String result
     */
    public static String convertPriceToText(double price) {
        String strTextPrice = "";
        int priceBillion = (int) (price / 1000000000.0);
        int priceMillion = (int) ((price % 1000000000) / 1000000.0);
        int priceThousand = (int) (((price % 1000000000) % 1000000) / 1000.0);
        if (priceBillion > 0 && price > 900000000) {
            strTextPrice = strTextPrice + priceBillion + " tỷ ";
        }
        if (priceMillion > 0) {
            strTextPrice = strTextPrice + priceMillion + " triệu ";
        }
        if (priceThousand > 0) {
            strTextPrice = strTextPrice + priceThousand + " ngàn ";
        }

        return strTextPrice;
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
        String address = result.trim();
        address = address.replaceFirst("Địa chỉ:", "");
        address = address.replaceAll("\u00a0", "");
        address = address.replaceAll("<br />", " ");
        int location = GeneralUtil.removeSign4VietNameseString(address).toLowerCase().indexOf("dt");
        if (location != -1) {
            address = address.substring(0, location);
        }
        /*location = GeneralUtil.removeSign4VietNameseString(address).toLowerCase().indexOf("dien thoai");
        if (location != -1) {
            address = address.substring(0, location);
        }
        location = GeneralUtil.removeSign4VietNameseString(address).toLowerCase().indexOf("Hotline");
        if (location != -1) {
            address = address.substring(0, location);
        }
        location = GeneralUtil.removeSign4VietNameseString(address).toLowerCase().indexOf("fax");
        if (location != -1) {
            address = address.substring(0, location);
        }
        for (char c : address.toCharArray()) {
            if (isNumber(c)) {
                return address.substring(address.indexOf(String.valueOf(c)));
            }
        }*/
        for (char c : address.toCharArray()) {
            if (isNumber(c)) {
                return address.substring(address.indexOf(String.valueOf(c)));
            }
        }
        return address;
    }
    /**
     * Convert a address string to latitude and longitude.
     * @param address Address string
     * @return String represent latitude and longitude.
     * Example: 10.8230989,106.6296638
     * @throws Exception Exception maybe happen when Status response is not OK
     */
    public static String convertAddressToLatitudeLongitude(String address) throws Exception {
        String result = "";
        // Using google map web service API
        String geocoderUri = String.format("http://maps.googleapis.com/maps/api/geocode/xml?address="
                + address.replace(" ", "+").replace(GlobalVariable.NON_BREAKING_SPACE, "") + "&sensor=false");
        // Convert to XML response return
        String html = new UtilHtmlToXML().htmlToXML(geocoderUri);
        UtilReadXML reader = new UtilReadXML();
        Document document = reader.readContentXML(html);
        // Read status of response
        String status = document.getElementsByTagName("status").item(0).getTextContent();
        if (!status.equals("OK")) {
            if (status.equals("ZERO_RESULTS")) {
                return result;
            }
            //throw new Exception(status);
        } else {
            String latitudeString = document.getElementsByTagName("lat").item(0).getTextContent();
            String longitudeString = document.getElementsByTagName("lng").item(0).getTextContent();
            result = latitudeString + "," + longitudeString;
        }
        return result;
    }

    /**
     * Send mail method.
     * @param from From
     * @param to To
     * @param subject Subject
     * @param body Content email
     */
    public static void sendMessage(String from, String to, String subject, String body) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Get List div tag in url.
     * @param url url to get
     * @return NodeList contain div tags
     * @throws Exception maybe happen
     */
    public static NodeList getListDivTag(String url) throws Exception {
        try {
            UtilHtmlToXML util = new UtilHtmlToXML();
            // Get content HTML and convert to XML
            String html = util.htmlToXML(url);

            UtilReadXML reader = new UtilReadXML();
            Document document = reader.readContentXML(html);
            return document.getElementsByTagName("div");
        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * Create matcher.
     * @param stringToMatch Data string to match
     * @param regularExpression Regular expression string
     * @param timeoutMillis Timeout by millisecond
     * @return Matcher object
     */
    public static Matcher createMatcherWithTimeout(String stringToMatch,
            String regularExpression,
            int timeoutMillis) {
        Pattern pattern = Pattern.compile(regularExpression);
        return createMatcherWithTimeout(stringToMatch, pattern, timeoutMillis);
    }
    /**
     * Create matcher..
     * @param stringToMatch Data string to match
     * @param regularExpressionPattern Pattern
     * @param timeoutMillis Timeout by millisecond
     * @return Matcher object
     */
    public static Matcher createMatcherWithTimeout(String stringToMatch,
            Pattern regularExpressionPattern,
            int timeoutMillis) {
        CharSequence charSequence = new TimeoutRegexCharSequence(stringToMatch, timeoutMillis, stringToMatch,
                regularExpressionPattern.pattern());
        return regularExpressionPattern.matcher(charSequence);
    }
}
