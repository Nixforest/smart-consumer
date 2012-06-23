package com.gae.android.smartconsumer.blo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gae.android.smartconsumer.activity.R;

public class ListItem_ListDeal extends LinearLayout{
    protected TextView title;
    protected ImageView voucherIcon;
    protected TextView voucherTitle;
    protected ImageView image;
    protected TextView description;
    protected TextView price;
    protected TextView basicPrice;
    protected Button btnview;
    public ListItem_ListDeal(Context context){
        super(context);
        LayoutInflater listItem = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItem.inflate(R.layout.list_deal_items, this, true);
        this.title = (TextView)findViewById(R.id.title_listdeal);
        this.voucherIcon = (ImageView)findViewById(R.id.isvoucherimage_listdeal);
        this.voucherTitle = (TextView)findViewById(R.id.isvouchertitle_listdeal);
        this.image = (ImageView)findViewById(R.id.image_listdeal);
        this.description = (TextView)findViewById(R.id.descripttion_listdeal);
        this.price = (TextView)findViewById(R.id.price_listdeal);
        this.basicPrice = (TextView)findViewById(R.id.basicprice_listdeal);
        this.btnview = (Button)findViewById(R.id.button_view);
    }
}
