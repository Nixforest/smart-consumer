/**
 * Status.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.util;

/**
 * Enum represent Status of Deal
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum Status {
    WAITTOCHECK,    // Status of deal when just insert
    SELLING,        // Status of deal "Selling"
    OUTOFTIME,      // Status of deal "Out of time"
    DELETED,        // Status of record "Deleted"
}
