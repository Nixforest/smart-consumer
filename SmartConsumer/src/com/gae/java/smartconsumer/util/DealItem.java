package com.gae.java.smartconsumer.util;

public class DealItem {

	private long deal_id;
	private String title;
	private double price;
	private String unitPrice;
	private int quantity;
	
	public DealItem(long deal_id, String title, double price, String unitPrice, int quantity)
	{
		this.deal_id = deal_id;
		this.title = title;
		this.price = price;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	/**
	 * @return the deal_id
	 */
	public Long getDeal_id() {
		return deal_id;
	}
	/**
	 * @param deal_id the deal_id to set
	 */
	public void setDeal_id(Long deal_id) {
		this.deal_id = deal_id;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the unitPrice
	 */
	public String getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((String.valueOf(deal_id) == null) ? 0 : String.valueOf(deal_id).hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DealItem other = (DealItem) obj;
		if (String.valueOf(deal_id) == null) {
			if (String.valueOf(other.deal_id) != null)
					return false;
		} else if (!String.valueOf(deal_id).equals(String.valueOf(other.deal_id)))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.valueOf(deal_id);
	}
}
