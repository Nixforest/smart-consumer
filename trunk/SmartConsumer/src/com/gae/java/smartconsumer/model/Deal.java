package com.gae.java.smartconsumer.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.Status;


/**
 * Model class which will store the Deal Items
 * @author Nixforest
 *
 */
@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Properties    
    private String title;                       // Title of deal
    private String description;                 // Description of deal
    private String address;                     // Address of deal
    private String link;                        // Url of deal
    private String imageLink;                   // Url of deal's image
    private double price;                       // Price of deal
    private double basicPrice;                  // Basic price of deal
    private String unitPrice;                   // Unit price of deal
    private float save;                         // Save of deal
    private int numberBuyer;                    // Number of buyer for deal
    private java.util.Date endTime;             // End time of deal
    private boolean isVoucher;                  // Method of delivery
    private java.util.Date updateDate;          // Updated date
    private Integer status;                         // Status of record
    
    // Constructor
    public Deal(String title, String description, String address,
            String link, String imageLink, double price,
            double basicPrice, String unitPrice, float save,
            int numberBuyer, 
            Date endTime,
            boolean isVoucher) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.link = link;
        this.imageLink = imageLink;
        this.price = price;
        this.basicPrice = basicPrice;
        this.unitPrice = unitPrice;
        this.save = save;
        this.numberBuyer = numberBuyer;
        this.endTime = endTime;
        this.isVoucher = isVoucher;
        this.setUpdateDate(java.util.Calendar.getInstance().getTime());
        this.status = Status.SELLING.ordinal();
    }
    
    /**
     * Get value of Title.
     * @return the Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value for Title.
     * @param title the Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get value of description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get value of address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value for address.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get value of link.
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the value for link.
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get value of imageLink.
     * @return the imageLink
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * Set the value for imageLink.
     * @param imageLink the imageLink to set
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     * Get value of price.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the value for price.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get value of basicPrice.
     * @return the basicPrice
     */
    public double getBasicPrice() {
        return basicPrice;
    }

    /**
     * Set the value for basicPrice.
     * @param basicPrice the basicPrice to set
     */
    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    /**
     * Get value of unitPrice.
     * @return the unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * Set the value for unitPrice.
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Get value of save.
     * @return the save
     */
    public float getSave() {
        return save;
    }

    /**
     * Set the value for save.
     * @param save the save to set
     */
    public void setSave(float save) {
        this.save = save;
    }

    /**
     * Get value of numberBuyer.
     * @return the numberBuyer
     */
    public int getNumberBuyer() {
        return numberBuyer;
    }

    /**
     * Set the value for numberBuyer.
     * @param numberBuyer the numberBuyer to set
     */
    public void setNumberBuyer(int numberBuyer) {
        this.numberBuyer = numberBuyer;
    }

    /**
    * Get value of endTime.
    * @return the endTime
    */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * Set the value for endTime.
     * @param endTime the endTime to set
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Get value of isVoucher.
     * @return the isVoucher
     */
    public boolean isVoucher() {
        return isVoucher;
    }

    /**
     * Set the value for isVoucher.
     * @param isVoucher the isVoucher to set
     */
    public void setVoucher(boolean isVoucher) {
        this.isVoucher = isVoucher;
    }

    /**
    * Get value of updateDate.
    * @return the updateDate
    */
    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Set the value for updateDate.
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
    * Get value of status.
    * @return the status
    */
    public int getStatus() {
        return status;
    }

    /**
     * Set the value for status.
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
    * Get value of id.
    * @return the id
    */
    public Long getId() {
        return id;
    }    
}