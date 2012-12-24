/**
 * TimeoutRegexCharSequence.java
 * 18 Nov 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.util;

/**
 * Char sequence for timeout regular expression.
 * @version 1.0 18/11/2012
 * @author NguyenPT
 */
public final class TimeoutRegexCharSequence implements CharSequence {
    /**
     * Inner charsequence object.
     */
    private final CharSequence inner;
    /**
     * Timeout by millisecond.
     */
    private final int timeoutMillis;
    /**
     * Timeout by time.
     */
    private final long timeoutTime;
    /**
     * Data string to match.
     */
    private final String stringToMatch;
    /**
     * Regular expression string.
     */
    private final String regularExpression;
    /**
     * Constructor.
     * @param inner Inner charsequence object
     * @param timeoutMillis Timeout by millisecond
     * @param stringToMatch Data string to match
     * @param regularExpression Regular expression string
     */
    public TimeoutRegexCharSequence(CharSequence inner, int timeoutMillis,
            String stringToMatch, String regularExpression) {
        super();
        this.inner = inner;
        this.timeoutMillis = timeoutMillis;
        this.stringToMatch = stringToMatch;
        this.regularExpression = regularExpression;
        timeoutTime = System.currentTimeMillis() + timeoutMillis;
    }
    /**
     * Char at index.
     * @param index Index of char
     * @return Char object
     * @see java.lang.CharSequence#charAt(int)
     */
    public char charAt(int index) {
        if (System.currentTimeMillis() > timeoutTime) {
            throw new RuntimeException();
        }
        return inner.charAt(index);
    }
    /**
     * Get Length.
     * @return Length of charsequence object
     * @see java.lang.CharSequence#length()
     */
    public int length() {
        return inner.length();
    }
    /**
     * Get sub sequence.
     * @param start Start index
     * @param end End index
     * @return Char Sequence object
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    public CharSequence subSequence(int start, int end) {
        return new TimeoutRegexCharSequence(inner.subSequence(start, end),
                timeoutMillis,
                stringToMatch,
                regularExpression);
    }
    @Override
    public String toString() {
        return inner.toString();
    }
}
