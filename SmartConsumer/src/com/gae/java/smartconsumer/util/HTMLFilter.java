/**
 * HTMLFilter.java
 * 22/7/2012
 * Smart Consumer project
 */

package com.gae.java.smartconsumer.util;

/**
 * HTML filter utility.
 *
 * @author Nixforest
 * @version 1.0
 */

public final class HTMLFilter {
    /**
     * Constructor.
     */
    private HTMLFilter() {
    }
    /**
     * Filter the specified message string for characters that are sensitive
     * in HTML.  This avoids potential attacks caused by including JavaScript
     * codes in the request URL that is often reported in error messages.
     *
     * @param message The message string to be filtered
     * @return string filtered
     */
    public static String filter(String message) {
        if (message == null) {
            return (null);
        }

        char[] content = new char[message.length()];

        message.getChars(0, message.length(), content, 0);

        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(content[i]);
            }
        }
        return (result.toString());
    }
}

