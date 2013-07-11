/**
 * SmartConsumer
 * com.gae.smartconsumer.utils
 * Jul 8, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

/**
 * Global variable.
 * @author NguyenPT
 *
 */
public final class GlobalVariable {
	/**
	 * Constructor.
	 */
	private GlobalVariable() {
	}
	public static final String HOST = 
			"http://fsmartconsumer.appspot.com";
	/**
	 * List deals link.
	 */
	public static final String GET_LIST_DEALS =
			"/getListDeals.app?limit=";
	/**
	 * Number of deals on a load time.
	 */
	public static final int NUMBER_DEALS = 30;
	/**
	 * Voucher string.
	 */
	public static final String VOUCHER = "Giao voucher";
	/**
	 * Product string.
	 */
	public static final String PRODUCT = "Giao sản phẩm";
	/**
	 * Price string.
	 */
	public static final String PRICE = "Giá bán";
	/**
	 * Basic price string.
	 */
	public static final String BASIC_PRICE = "Giá gốc";
	/**
	 * Unit string.
	 */
	public static final String UNIT = "VNĐ";
}
