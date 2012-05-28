/**
 * Bill.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class describe "Bill" object, contain data about Bill
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
@Entity(name="Bill")
public class Bill {
	/** Id - Primary key*/
	@Id
    private Long id;
	
	/** Customer's name */
	private String customerName;
	
	/** Customer's email */
	private String customerEmail;
	
	/** Customer's phone number */
	private String customerPhone;
	
	/** Customer's address */
	private String customerAddress;
	
	/** Payment method */
	private int payment;
	
	/** Card number for payment */
	private String cardNumber;
	
	/** Holder's name */
	private String holderName;
	
	/** Expiration date */
	private Date expirationDate;
	
	/**
	 * Constructor
	 * @param customerName Customer's name
	 * @param customerEmail Customer's email
	 * @param customerPhone Customer's phone
	 * @param customerAddress Customer's address
	 * @param payment Payment method
	 * @param cardNumber Card number for payment
	 * @param holderName Holder's name
	 * @param expirationDate Expiration date
	 */
	public Bill(String customerName, String customerEmail,
	        String customerPhone, String customerAddress,
	        int payment, String cardNumber,
	        String holderName, Date expirationDate) {
	    this.customerName = customerName;
	    this.customerEmail = customerEmail;
	    this.customerPhone = customerPhone;
	    this.customerAddress = customerAddress;
	    this.payment = payment;
	    this.cardNumber = cardNumber;
	    this.holderName = holderName;
	    this.expirationDate = expirationDate;
	}
	
    /**
    * Get value of id.
    * @return the id
    */
    public Long getId() {
        return id;
    }
    
    /**
    * Get value of customerName.
    * @return the customerName
    */
    public String getCustomerName() {
        return customerName;
    }
    
    /**
     * Set the value for customerName.
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
    * Get value of customerEmail.
    * @return the customerEmail
    */
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    /**
     * Set the value for customerEmail.
     * @param customerEmail the customerEmail to set
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    /**
    * Get value of customerPhone.
    * @return the customerPhone
    */
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    /**
     * Set the value for customerPhone.
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    /**
    * Get value of customerAddress.
    * @return the customerAddress
    */
    public String getCustomerAddress() {
        return customerAddress;
    }
    
    /**
     * Set the value for customerAddress.
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    /**
    * Get value of payment.
    * @return the payment
    */
    public int getPayment() {
        return payment;
    }
    
    /**
     * Set the value for payment.
     * @param payment the payment to set
     */
    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    /**
    * Get value of cardNumber.
    * @return the cardNumber
    */
    public String getCardNumber() {
        return cardNumber;
    }
    
    /**
     * Set the value for cardNumber.
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    /**
    * Get value of holderName.
    * @return the holderName
    */
    public String getHolderName() {
        return holderName;
    }
    
    /**
     * Set the value for holderName.
     * @param holderName the holderName to set
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    
    /**
    * Get value of expirationDate.
    * @return the expirationDate
    */
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    /**
     * Set the value for expirationDate.
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }	
}
