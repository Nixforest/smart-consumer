/**
 * DealForm.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.gae.java.smartconsumer.model.Deal;
/**
 * DealForm action form.
 * @version 2.0 03/06/2012 - Create - NguyenPT
 * @author NguyenPT
 */
public class DealForm extends ActionForm implements Serializable {
    /**  . */
    private static final long serialVersionUID = 1L;
    /** Title of deal. */
    private String title;
    /** Description of deal. */
    private String description;
    /** Address of deal. */
    private String address;
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
    /** End time of deal. */
    private String endTime;
    /** Method of delivery. */
    private boolean isVoucher;
    /** List of deal. */
    private List<Deal> listDeal;
    /**
     * Get value of listDeal.
     * @return the listDeal
     */
    public List<Deal> getListDeal() {
        return listDeal;
    }
    /**
     * Set the value for listDeal.
     * @param listDeal the listDeal to set
     */
    public void setListDeal(List<Deal> listDeal) {
        this.listDeal = listDeal;
    }
    /**
     * Get value of isVoucher.
     * @return the isVoucher
     */
    public boolean isVoucher() {
        return isVoucher;
    }
    /**
     * Get value of title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the value for title.
     * @param title the title to set
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
     * Get value of endTime.
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }
    /**
     * Set the value for endTime.
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**
     * Get value of isVoucher.
     * @return the isVoucher
     */
    public boolean getVoucher() {
        return isVoucher;
    }
    /**
     * Set the value for isVoucher.
     * @param isVoucher the isVoucher to set
     */
    public void setVoucher(boolean isVoucher) {
        this.isVoucher = isVoucher;
    }
}
