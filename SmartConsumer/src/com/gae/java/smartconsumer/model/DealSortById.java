/**
 * DealSortById.java
 * 15/09/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;
/**
 * Comparator for Deal sort by Id property.
 * @author NguyenPT
 */
public class DealSortById implements java.util.Comparator<Deal> {
    /**
     * Override compare method from Comparator interface.
     * @param deal1 Deal 1 to compare
     * @param deal2 Deal 2 to compare
     * @return The value 0 if deal2's Id is equal to deal1's Id; a value less than 0 if deal1's Id is
     *         less than deal2's Id; and a value greater than 0 if deal1's Id is greater than deal2's Id.
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Deal deal1, Deal deal2) {
        return deal1.getId().compareTo(deal2.getId());
    }
}
