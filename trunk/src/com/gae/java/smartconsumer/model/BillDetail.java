/**
 * BillDetail.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class describe "Bill Detail" object, contain data about Bill's detail.
 * @version 1.0 27/5/2012
 * @author Nixforest
 */
@Entity(name = "BillDetail")
public class BillDetail {
    /** Id - Primary key. */
    @Id
    private Long id;
    /** Bill's Id. */
    private Long billId;

    /** Deal's Id. */
    private Long dealId;

    /** Quantity. */
    private int quantity;

    /**
     * Constructor.
     * @param billId Bill's Id
     * @param dealId Deal's Id
     * @param quantity Quantity
     */
    public BillDetail(Long billId, Long dealId, int quantity) {
        this.billId = billId;
        this.dealId = dealId;
        this.quantity = quantity;
    }

    /**
     * Get value of billId.
     * @return the billId
     */
    public Long getBillId() {
        return billId;
    }

    /**
     * Set the value for billId.
     * @param billId the billId to set
     */
    public void setBillId(Long billId) {
        this.billId = billId;
    }

    /**
     * Get value of dealId.
     * @return the dealId
     */
    public Long getDealId() {
        return dealId;
    }

    /**
     * Set the value for dealId.
     * @param dealId the dealId to set
     */
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    /**
     * Get value of quantity.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value for quantity.
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get value of id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
