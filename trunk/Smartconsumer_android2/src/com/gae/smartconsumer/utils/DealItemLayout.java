/**
 * SmartConsumer_Fsoft
 * com.gae.smartconsumer.utils
 * Jul 13, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Deal item layout.
 * @author NguyenPT
 *
 */
public class DealItemLayout extends LinearLayout {
	/**
	 * Title textview.
	 */
	protected TextView mTxtVTitle;
	/**
	 * Image voucher.
	 */
	protected ImageView mImgVoucher;
	/**
	 * Voucher title.
	 */
	protected TextView mTxtVVoucherTitle;
	/**
	 * Image deal.
	 */
	protected ImageView mImgMain;
	/**
	 * Description textview.
	 */
	protected TextView mTxtVDescription;
	/**
	 * Price textview.
	 */
	protected TextView mTxtVPrice;
	/**
	 * Basic price textview.
	 */
	protected TextView mTxtVBasicPrice;
	/**
	 * Button view.
	 */
	protected Button mBtnView;
	/**
	 * Constructor.
	 * @param context Context
	 */
	public DealItemLayout(Context context) {
		super(context);
		LayoutInflater listItem = (LayoutInflater)this.getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		listItem.inflate(com.gae.smartconsumer.R.layout.listdeals_item,
				this, true);
		this.mTxtVTitle = (TextView) findViewById(com.gae.smartconsumer.R.id.title_listdeal);
		this.mImgVoucher = (ImageView) findViewById(com.gae.smartconsumer.R.id.isvoucherimage_listdeal);
		this.mTxtVVoucherTitle = (TextView) findViewById(com.gae.smartconsumer.R.id.isvouchertitle_listdeal);
		this.mImgMain = (ImageView) findViewById(com.gae.smartconsumer.R.id.image_listdeal);
		this.mTxtVDescription = (TextView) findViewById(com.gae.smartconsumer.R.id.descripttion_listdeal);
		this.mTxtVPrice = (TextView) findViewById(com.gae.smartconsumer.R.id.price_listdeal);
		this.mTxtVBasicPrice = (TextView) findViewById(com.gae.smartconsumer.R.id.basicprice_listdeal);
		this.mBtnView = (Button) findViewById(com.gae.smartconsumer.R.id.btnview_listdeal);
	}
}
