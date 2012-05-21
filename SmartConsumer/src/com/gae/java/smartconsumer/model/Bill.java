package com.gae.java.smartconsumer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CartInfo")
public class Bill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="CartInfoId")
    private Long id;
	
	//Properties
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String customerAddress;
	private int payment;
	private String cardNumber;
	private String holderName;
	private Date expirationDate;
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
}
