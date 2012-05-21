package com.gae.java.smartconsumer.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gae.java.smartconsumer.util.Cart;

@Entity(name="Customer")
public class BillDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="CustomerId")
    private Long id;
	
	//Properties
	private Long billId;
	private Long dealId;
	private int quantity;
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
}
