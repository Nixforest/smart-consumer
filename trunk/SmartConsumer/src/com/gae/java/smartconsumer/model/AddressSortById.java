/**
 * AddressSortById.java
 * 15/09/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;


/**
 * Comparator for Address sort by Id property.
 * @author NguyenPT
 */
public class AddressSortById implements java.util.Comparator<Address> {
    /**
     * Override compare method from Comparator interface.
     * @param o1 Object 1
     * @param o2 Object 2
     * @return the value 0 if o1's Id is equal to o2's Id;
     * a value less than 0 if o1's Id is numerically less than o2's Id;
     * and a value greater than 0 if o1's Id is numerically greater than
     * o2's Id (signed comparison).
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getId().compareTo(o2.getId());
    }

}
