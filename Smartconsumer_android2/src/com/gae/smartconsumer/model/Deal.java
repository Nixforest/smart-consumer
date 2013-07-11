package com.gae.smartconsumer.model;




public class Deal {
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
    private double save;
    /** Number of buyer for deal. */
    private int numberBuyer;
    /** End time of deal. */
    private java.util.Date endTime;
    /** Method of delivery. */
    private boolean isVoucher;
    /** Category of deal. */
    private Long categoryId;
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
    public Deal(Long id, String title, String description,
    		String link, String imageLink, double price,
            double basicPrice, String unitPrice,
            double save, int numberBuyer, java.util.Date endTime,
            boolean isVoucher, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.imageLink = imageLink;
        this.price = price;
        this.basicPrice = basicPrice;
        this.unitPrice = unitPrice;
        this.save = save;
        this.numberBuyer = numberBuyer;
        this.setEndTime(endTime);
        this.isVoucher = isVoucher;
        this.setCategoryId(categoryId);
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
     * Get value of endTime.
     * @return the endTime
     */
	public java.util.Date getEndTime() {
		return endTime;
	}
	/**
	 * Set the value of endTime.
	 * @param endTime the endTime to set
	 */
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Get the value of categoryId.
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * Set the value of categoryId.
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
}
