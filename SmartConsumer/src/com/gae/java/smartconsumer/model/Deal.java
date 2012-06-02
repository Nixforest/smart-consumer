/**
 * Deal.java
 * 27/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.gae.java.smartconsumer.util.Status;

/**
 * Class describe "Deal" object, contain data about Deal.
 * @version 1.0 27/5/2012
 * @author Nixforest
 * @update DuyKhoa
 */
@Entity(name = "Deal")
public class Deal {
    /** Id - Primary key. */
    @Id
    private Long id;
    /** Title of deal. */
    private String title;
    /** Description of deal. */
    private String description;
    /** Url of deal. */
    private String link;
    /** Url of deal's image. */
    private String imageLink;
    /** Price of deal. */
    private double price;
    /** Basic price of deal. */
    private double basicPrice;
    /** Unit price of deal. */
    private String unitPrice;
    /** Save of deal. */
    private float save;
    /** Number of buyer for deal. */
    private int numberBuyer;
    /** End time of deal. */
    private java.util.Date endTime;
    /** Method of delivery. */
    private boolean isVoucher;
    /** Updated date. */
    private java.util.Date updateDate;
    /** Status of record. */
    private Integer status;
    /**
     * Empty constructor.
     */
    public Deal() {
    }

    /**
     * Constructor of Deal class.
     * @param title Title
     * @param description Description
     * @param link Url
     * @param imageLink Image's url
     * @param price Price
     * @param basicPrice Basic price
     * @param unitPrice Unit price
     * @param save Save
     * @param numberBuyer Number of buyer
     * @param endTime End time
     * @param isVoucher Is voucher
     */
    public Deal(String title, String description, String link, String imageLink, double price,
            double basicPrice, String unitPrice, float save, int numberBuyer, Date endTime, boolean isVoucher) {
        this.title = title;
        this.description = description;
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
     * Constructor of Deal class.
     * @param title Title
     * @param description Description
     * @param link Url
     * @param imageLink Image's url
     * @param price Price
     * @param basicPrice Basic price
     * @param unitPrice Unit price
     * @param save Save
     * @param numberBuyer Number of buyer
     * @param endTime End time
     * @param isVoucher Is voucher
     * @param status Status of Deal
     */
    public Deal(String title, String description, String link, String imageLink, double price,
            double basicPrice, String unitPrice, float save, int numberBuyer, Date endTime, boolean isVoucher,
            int status) {
        this.title = title;
        this.description = description;
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
        this.status = status;
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
    public boolean getisVoucher() {
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

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
