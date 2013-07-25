/**
 * SmartConsumer
 * com.gae.smartconsumer.utils
 * Jun 27, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import java.util.ArrayList;

import com.gae.smartconsumer.R;
import com.gae.smartconsumer.activity.ViewDealActivity;
import com.gae.smartconsumer.model.Deal;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Adapter for list deals items.
 * @author NguyenPT
 *
 */
public class ListDealsAdapter extends ArrayAdapter<Deal> {
	/**
	 * Deals array.
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
		super(context, resourceId, listDeals);
		this.mContext = context;
		this.mListDeals = listDeals;
		this.mResourceId = resourceId;
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
		// Create deal item holder
		//DealItemHolder viewHolder = null;
		View dealView = convertView;
		if (dealView == null) {
			dealView = new DealItemLayout(mContext);
		}
		// Get Current deal
		final Deal deal = this.mListDeals.get(position);
		if (deal != null) {
			TextView txtTitle = ((DealItemLayout)dealView).mTxtVTitle;
			ImageView imgVoucher = ((DealItemLayout)dealView).mImgVoucher;
			TextView txtVoucherTitle = ((DealItemLayout)dealView).mTxtVVoucherTitle;
			ImageView img = ((DealItemLayout)dealView).mImgMain;
			TextView txtDescription = ((DealItemLayout)dealView).mTxtVDescription;
			TextView txtPrice = ((DealItemLayout)dealView).mTxtVPrice;
			TextView txtBasicPrice = ((DealItemLayout)dealView).mTxtVBasicPrice;
			//Button btnView = ((DealItemLayout)dealView).mBtnView;
			dealView.findViewById(R.id.btnview_listdeal).setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "View map", Toast.LENGTH_SHORT).show();
                }
            });
			dealView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//Toast.makeText(mContext, "View Deal", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(mContext, ViewDealActivity.class);
					intent.putExtra("link", deal.getLink());
					mContext.startActivity(intent);
				}
			});
			txtTitle.setText(deal.getTitle());
			if (deal.isVoucher()) {
				imgVoucher.setImageResource(R.drawable.isvoucher_icon1);
				txtVoucherTitle.setText(GlobalVariable.VOUCHER);
			} else {
				imgVoucher.setImageResource(R.drawable.isvoucher_icon);
				txtVoucherTitle.setText(GlobalVariable.PRODUCT);
			}
			new LoadImagesTask(img).execute(deal.getImageLink());
			//convertView.setTag(viewHolder);
			txtDescription.setText(deal.getDescription());
			txtPrice.setText(GlobalVariable.PRICE + String.valueOf(deal.getPrice()) + GlobalVariable.UNIT);
			txtBasicPrice.setText(GlobalVariable.PRICE + String.valueOf(deal.getBasicPrice()) + GlobalVariable.UNIT);
		}
		return dealView;
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
