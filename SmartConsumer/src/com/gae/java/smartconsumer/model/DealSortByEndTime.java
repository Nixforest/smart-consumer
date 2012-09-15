/**
 * DealSortByEndTime.java
 * 15/09/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

/**
 * Comparator for Deal sort by endTime property.
 * @author NguyenPT
 */
public class DealSortByEndTime implements java.util.Comparator<Deal> {
    /**
     * Override compare method from Comparator interface.
     * @param deal1 Deal 1 to compare
     * @param deal2 Deal 2 to compare
     * @return The value 0 if deal2's endTime is equal to deal1's endTime; a value less than 0 if deal1's endTime is
     *         before deal2's endTime; and a value greater than 0 if deal1's endTime is after deal2's endTime.
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Deal deal1, Deal deal2) {
        return deal1.getEndTime().compareTo(deal2.getEndTime());
    }
}
