package com.gae.android.smartconsumer.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gae.android.smartconsumer.activity.R;
import com.gae.android.smartconsumer.activity.ViewDealBrowser;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
    private Context context;
    private TextView tbxcustomDialog;
    private Button btncustomDialog;
    private int index_id;
    public MyItemizedOverlay(Drawable marker, Context c) {
    super(boundCenterBottom(marker));
    // TODO Auto-generated constructor stub
     context = c;
    populate();
    }
    @Override
    protected boolean onTap(int index) {
        //ImageView img = new ImageView(context);
        /*TextView text = new TextView(context);
        
        index_id = index;
        //new LoadImage(img).execute(overlayItemList.get(index).getSnippet());
        Toast toast = new Toast(context);
        LinearLayout toastLayout = new LinearLayout(context);
        text.setText(overlayItemList.get(index).getTitle());
        //toastLayout.addView(img);
        toastLayout.setBackgroundResource(R.drawable.my_background1);
        text.setOnClickListener(onclickviewdeal);
        
        toastLayout.addView(text);
        toast.setView(toastLayout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();*/
        //Toast.makeText(context, overlayItemList.get(index).getTitle(), Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        index_id = index;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = new LinearLayout(context);
        View v = inflater.inflate(R.layout.toast_map, layout);

        TextView textName = (TextView) v.findViewById(R.id.text);
        textName.setText(overlayItemList.get(index).getTitle());
        //textName.setOnClickListener(onclickviewdeal);

        ImageView image = (ImageView) v.findViewById(R.id.image);
        String linkimg = overlayItemList.get(index_id).getSnippet().substring(
                overlayItemList.get(index_id).getSnippet().indexOf("*")+1);
        
        if (isValidUrl(linkimg)) {
            new LoadImage(image).execute(linkimg);
            image.setOnClickListener(onclickviewdeal);
        }else{
            image.setImageResource(R.drawable.ic_launcher);
        }
        
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();

        return super.onTap(index);
    }
    OnClickListener onclickviewdeal = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String link = overlayItemList.get(index_id).getSnippet().substring(0, overlayItemList.get(index_id).getSnippet().indexOf("*"));
            //String linkimg = overlayItemList.get(index_id).getSnippet().substring(overlayItemList.get(index_id).getSnippet().indexOf("*")+1);
            if (isValidUrl(link)) {
                Intent intent = new Intent(context, ViewDealBrowser.class);
                intent.putExtra("link", overlayItemList.get(index_id).getSnippet());
                context.startActivity(intent);
             }
        }
    };
    /**
     * 
     * [Give the description for method].
     * @param url
     * @return
     */
    public static boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
    public void addItem(GeoPoint p, String title, String snippet){
    OverlayItem newItem = new OverlayItem(p, title, snippet);
    overlayItemList.add(newItem);
       populate();
    }
    @Override
    protected OverlayItem createItem(int i) {
    // TODO Auto-generated method stub
    return overlayItemList.get(i);
    }
     
    @Override
    public int size() {
    // TODO Auto-generated method stub
    return overlayItemList.size();
    }
     
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    // TODO Auto-generated method stub
    super.draw(canvas, mapView, shadow);
    //boundCenterBottom(marker);
    }
}
