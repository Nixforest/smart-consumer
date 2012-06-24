package com.gae.android.smartconsumer.blo;

import java.util.ArrayList;

import com.gae.android.smartconsumer.model.Deal;
import com.gae.android.smartconsumer.util.LoadImage;

import com.gae.android.smartconsumer.activity.MapView;
import com.gae.android.smartconsumer.activity.R;
import com.gae.android.smartconsumer.activity.ViewDealBrowser;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter_ListDeal extends ArrayAdapter<Deal> {
    private ArrayList<Deal> arrayList;
    private int resource;
    private Context context;
    private String url;
    public ListAdapter_ListDeal(Context context, int textViewResourceId, ArrayList<Deal> object){
        super(context, textViewResourceId, object);
        this.arrayList = object;
        this.resource = textViewResourceId;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View dealView = convertView;
        if(dealView == null){
            dealView = new ListItem_ListDeal(context);
        }
        final Deal deal = arrayList.get(position);
        if(deal != null){
            TextView tbxTitle = ((ListItem_ListDeal)dealView).title;
            ImageView imgVoucher = ((ListItem_ListDeal)dealView).voucherIcon;
            TextView tbxVoucherTitle = ((ListItem_ListDeal)dealView).voucherTitle;
            ImageView img = ((ListItem_ListDeal)dealView).image;
            TextView tbxDescription = ((ListItem_ListDeal)dealView).description;
            TextView tbxPrice = ((ListItem_ListDeal)dealView).price;
            TextView tbxBasicPrice = ((ListItem_ListDeal)dealView).basicPrice;
            Button btnView = ((ListItem_ListDeal)dealView).btnview;
            dealView.findViewById(R.id.btnview_listdeal).setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapView.class);
                    intent.putExtra("id", deal.getId());
                    context.startActivity(intent);
                }
            });
            /*dealView.setOnLongClickListener(new OnLongClickListener() {
                
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(context, MapView.class);
                    intent.putExtra("id", deal.getId());
                    context.startActivity(intent);
                    return false;
                }
            });*/
            dealView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewDealBrowser.class);
                    intent.putExtra("link", deal.getLink());
                    context.startActivity(intent);
                }
            });
            tbxTitle.setText(deal.getTitle());
            if(deal.isVoucher() == true){
                imgVoucher.setImageResource(R.drawable.isvoucher_icon1);
                tbxVoucherTitle.setText("(Giao voucher)");
            }else{
                tbxVoucherTitle.setText("(Giao sản phẩm)");
            }
            new LoadImage(img).execute(deal.getImageLink());
            tbxDescription.setText(deal.getDescription());
            tbxPrice.setText("Giá bán : " + String.valueOf(deal.getPrice()) + " VNĐ");
            tbxBasicPrice.setText("Giá gốc : " + String.valueOf(deal.getBasicPrice()) + " VNĐ");
        }
        return dealView;
    }
}
