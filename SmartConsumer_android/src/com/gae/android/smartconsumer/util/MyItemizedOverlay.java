package com.gae.android.smartconsumer.util;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
    private Context context;
    public MyItemizedOverlay(Drawable marker, Context c) {
    super(boundCenterBottom(marker));
    // TODO Auto-generated constructor stub
     context = c;
    populate();
    }
    @Override
    protected boolean onTap(int index) {
        Toast.makeText(context, overlayItemList.get(index).getTitle(), Toast.LENGTH_LONG).show();
        return true;
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
