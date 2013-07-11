/**
 * SmartConsumer
 * com.gae.smartconsumer.utils
 * Jun 27, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import java.util.ArrayList;

import com.gae.smartconsumer.R;
import com.gae.smartconsumer.model.Deal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter for list deals items.
 * @author NguyenPT
 *
 */
public class ListDealsAdapter extends BaseAdapter {
	/**
	 * Titles array.
	 */
	private ArrayList<Deal> mListDeals;
	/**
	 * Context.
	 */
	private Context mContext;
	/**
	 * Resource id;
	 */
	private int mResourceId;
	/**
	 * Url.
	 */
	private String mUrl;
	/**
	 * Constructor.
	 * @param context Context
	 * @param titles Titles array
	 */
	public ListDealsAdapter(Context context,
			ArrayList<Deal> listDeals,
			int resourceId) {
		this.mContext = context;
		this.mListDeals = listDeals;
		this.mResourceId = resourceId;
	}
	/**
	 * Get count of items.
	 * @return Count of items
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Get item in a position.
	 * @param position position of item
	 * @return Object at position
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get item id.
	 * @param arg0 id
	 * @return Item id
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Get view adapter.
	 * @param position Position of item
	 * @param convertView Convert view
	 * @param arg2 arg2
	 * @return View object
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		DealItemHolder viewHolder = null;
		final Deal deal = this.mListDeals.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(com.gae.smartconsumer.R.layout.activity_listdeals, null);
			viewHolder = new DealItemHolder();
			viewHolder.mTxtVTitle = (TextView) convertView.findViewById(com.gae.smartconsumer.R.id.title_listdeal);
			viewHolder.mImgVoucher = (ImageView) convertView.findViewById(R.id.isvoucherimage_listdeal);
			viewHolder.mTxtVVoucherTitle = (TextView) convertView.findViewById(R.id.isvouchertitle_listdeal);
			viewHolder.mImgMain = (ImageView) convertView.findViewById(R.id.image_listdeal);
			viewHolder.mTxtVDescription = (TextView) convertView.findViewById(R.id.descripttion_listdeal);
			viewHolder.mTxtVPrice = (TextView) convertView.findViewById(R.id.price_listdeal);
			viewHolder.mTxtVBasicPrice = (TextView) convertView.findViewById(R.id.basicprice_listdeal);
			viewHolder.mBtnView = (Button) convertView.findViewById(R.id.btnview_listdeal);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (DealItemHolder) convertView.getTag();
		}
		// Set value
		viewHolder.mTxtVTitle.setText(deal.getTitle());
		if (deal.isVoucher()) {
			viewHolder.mImgVoucher.setImageResource(R.drawable.isvoucher_icon1);
			viewHolder.mTxtVVoucherTitle.setText(GlobalVariable.VOUCHER);
		} else {
			viewHolder.mImgVoucher.setImageResource(R.drawable.isvoucher_icon);
			viewHolder.mTxtVVoucherTitle.setText(GlobalVariable.PRODUCT);
		}
		new LoadImagesTask(viewHolder.mImgMain).execute(deal.getImageLink());
		viewHolder.mTxtVDescription.setText(deal.getDescription());
		viewHolder.mTxtVPrice.setText(GlobalVariable.PRICE + String.valueOf(deal.getPrice()) + GlobalVariable.UNIT);
		viewHolder.mTxtVBasicPrice.setText(GlobalVariable.PRICE + String.valueOf(deal.getBasicPrice()) + GlobalVariable.UNIT);
		return convertView;
	}
	/**
	 * Class hold deal item content.
	 * @author NguyenPT
	 *
	 */
	private class DealItemHolder {
		/**
		 * TextView for title.
		 */
		TextView mTxtVTitle;
		ImageView mImgVoucher;
		TextView mTxtVVoucherTitle;
		ImageView mImgMain;
		TextView mTxtVDescription;
		TextView mTxtVPrice;
		TextView mTxtVBasicPrice;
		Button mBtnView;
		
	}
}
