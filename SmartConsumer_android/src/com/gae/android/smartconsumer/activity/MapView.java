package com.gae.android.smartconsumer.activity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gae.android.smartconsumer.gae.Engine;
import com.gae.android.smartconsumer.util.FixedMyLocationOverlay;
import com.gae.android.smartconsumer.util.MyItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MyLocationOverlay;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MapView extends MapActivity// implements android.content.DialogInterface.OnClickListener {
{
    private com.google.android.maps.MapView mapview;
    /*private LocationManager locationManager;
    private EditText showLocation;
    private Button btnGetLocation;
    private LocationListener locListener = new myLocationListener();
    private ProgressBar progress;
    private boolean gps_enabled = false;
    private boolean networl_enabled = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.map_view);
      //mapview = (com.google.android.maps.MapView)findViewById(R.id.mapview);
      //mapview.setBuiltInZoomControls(true);
      showLocation = (EditText)findViewById(R.id.txtShowLoc);
      showLocation.setEnabled(false);
      
      progress = (ProgressBar)findViewById(R.id.progressBar);
      progress.setEnabled(false);
      
      btnGetLocation = (Button)findViewById(R.id.btnlocation);
     // btnGetLocation.setOnClickListener(this);
      progress.setVisibility(View.VISIBLE);
      try{
          gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
      }catch(Exception ex){
          System.out.println(ex.toString());
      }
      try{
          networl_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
      }catch(Exception ex){
          System.out.println(ex.toString());
      }
      if(!gps_enabled && !networl_enabled){
          AlertDialog.Builder builder = new Builder(this);
          builder.setTitle("Attention!");
          builder.setMessage("Sorry, location is not determined. Please enable location providers");
          builder.setPositiveButton("OK", this);
          builder.create().show();
          progress.setVisibility(View.GONE);
      }
      if(gps_enabled){
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
      }
      if(networl_enabled){
          locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
      }
    }
    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    private class myLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            if(location != null){
                locationManager.removeUpdates(locListener);
                String speed = "Device Speed : " + location.getSpeed();
                String longitude = "Longitude : " + location.getLongitude();
                String latitude = "Latitude : " + location.getLatitude();
                String accuracy = "Accuracy : " + location.getAccuracy();
                String time = "Time : " + location.getTime();
                
                String cityName = "";
                
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                
                List<Address> addresses;
                
                try{
                        addresses = gcd.getFromLocation(location.getLatitude() , location.getLongitude(), 1);
                        if(addresses.size() > 0){
                            System.out.println(addresses.get(0).getLocality());
                            cityName = addresses.get(0).getLocality();
                        }
                    }catch(IOException ex){
                        ex.printStackTrace();
                }
                showLocation.setText("city name : " + cityName + "\n" + speed + "\n" + longitude + "\n" + latitude + "\n" + latitude + "\n" + accuracy + "\n" + time);
                progress.setVisibility(View.GONE);
            }
                
            }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            
        }
        
   }
    @Override  
    public void onClick(DialogInterface dialog, int which) {  
     // TODO Auto-generated method stub  
     if(which == DialogInterface.BUTTON_NEUTRAL){  
      showLocation.setText("location is not getting due to location provider");  
        
     }  
     else if (which == DialogInterface.BUTTON_POSITIVE){  
      startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));  
        
     }  
    } */
    /*@Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.map_view);
        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
    }
    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    public class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc)
        {
            loc.getLatitude();
            loc.getLongitude();
 
            String Text = "My current location is: " +
                          "Latitude = " + loc.getLatitude() +
                          "Longitude = " + loc.getLongitude();
 
            Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
        }
 
        @Override
        public void onProviderDisabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        }
 
        @Override
        public void onProviderEnabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }
 
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
 
    }*/
    @Override
    protected void onCreate(Bundle arg0) {
        
        super.onCreate(arg0);
        setContentView(R.layout.map_view);
        
      //get position
        try {
            resultJson = new Engine().execute("/getListAddress.app?limit=","10").get();
        } catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
        mapview = (com.google.android.maps.MapView)findViewById(R.id.mapview);
        mapview.setClickable(true);
        mapview.setBuiltInZoomControls(true);
        
        Drawable marker = getResources().getDrawable(R.drawable.map_pin);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);
        
        myItemizedOverlay = new MyItemizedOverlay(marker, MapView.this);
        mapview.getOverlays().add(myItemizedOverlay);
        
        //search
        searchText = (EditText)findViewById(R.id.tbxSearch);
        searchButton = (Button)findViewById(R.id.btnSearch);
        listviewResult = (ListView)findViewById(R.id.listviewposition);
        
        searchButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String searchString = searchText.getText().toString();
                searchFromLocationName(searchString);
            }
        });
        
        myGeocoder = new Geocoder(this);
        if(!Geocoder.isPresent()){
            Toast.makeText(MapView.this, "Sorry! Geocoder service not Present.", Toast.LENGTH_LONG).show();
        }
        listviewResult.setOnItemClickListener(listviewResultOnItemClickListener);
        
        //position
        try{
            if(resultJson.length()>0){
                for(int i=0;i<resultJson.length();i++){
                    JSONObject json = new JSONObject();
                    json = resultJson.getJSONObject(i);
                    if(json != null){
                        myItemizedOverlay.addItem(new GeoPoint((int)(json.getDouble("latitude")*1E6),
                            (int)(json.getDouble("longitude")*1E6)), json.getString("title"), json.getString("link"));
                    }
                }
            }
        }catch (JSONException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }catch(Exception ex){
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
        GeoPoint myPoint1 = new GeoPoint((int)(10.827784*1000000), (int)(106.691848*1000000));
        myItemizedOverlay.addItem(myPoint1, "myPoint1", "description");
        GeoPoint myPoint2 = new GeoPoint((int)(10.798856*1000000), (int)(106.666646*1000000));
        myItemizedOverlay.addItem(myPoint2, "myPoint2", "description");
        myLocationOverLay = new FixedMyLocationOverlay(this, mapview);
        
        mapview.getOverlays().add(myLocationOverLay);
        mapview.postInvalidate();
        
        zoomToMyLocation(myPoint1);
    }
    OnItemClickListener listviewResultOnItemClickListener = new OnItemClickListener() {
        public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
            double lat = ((Address)parent.getItemAtPosition(position)).getLatitude();
            double lon = ((Address)parent.getItemAtPosition(position)).getLongitude();
            //String loc = "lat: " + lat + "\n" + "lon:" + lon;
            //Toast.makeText(MapView.this, loc, Toast.LENGTH_LONG).show();
            
            listviewResult.setVisibility(View.GONE);
            mapview.setVisibility(View.VISIBLE);
            GeoPoint point = new GeoPoint((int)(lat*1E6),(int)(lon*1E6));
            zoomToMyLocation(point);
            myItemizedOverlay.addItem(point, "", "");
        };
    };
    private void searchFromLocationName(String name){
        try {
         List<Address> result
         = myGeocoder.getFromLocationName(name, 10);
          
         if ((result == null)||(result.isEmpty())){
          Toast.makeText(MapView.this,
            "No matches were found or there is no backend service!",
            Toast.LENGTH_LONG).show();
         }else{
          mapview.setVisibility(View.GONE);
          listviewResult.setVisibility(View.VISIBLE);
          MyArrayAdapter adapter = new MyArrayAdapter(this,
                android.R.layout.simple_list_item_1, result);
          listviewResult.setAdapter(adapter);
           
          Toast.makeText(MapView.this, "Finished!", Toast.LENGTH_LONG).show();
         }       
          
        } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         Toast.makeText(MapView.this,
           "The network is unavailable or any other I/O problem occurs!",
           Toast.LENGTH_LONG).show();
        }
       }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myLocationOverLay.enableMyLocation();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myLocationOverLay.disableMyLocation();
    }
    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    private void zoomToMyLocation(GeoPoint myLocationGeoPoint) {
        //GeoPoint myLocationGeoPoint = myLocationOverLay.getMyLocation();
        //GeoPoint myLocationGeoPoint = new GeoPoint((int)(10.827784*1000000), (int)(106.691848*1000000));
        if(myLocationGeoPoint != null) {
            mapview.getController().animateTo(myLocationGeoPoint);
            mapview.getController().setZoom(17);
        }
        else {
            Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
        }
    }
    public class MyArrayAdapter extends ArrayAdapter<Address> {
        Context mycontext;
        public MyArrayAdapter(Context context, int textViewResourceId,
          List<Address> objects) {
         super(context, textViewResourceId, objects);
         // TODO Auto-generated constructor stub
         mycontext = context;
        }
       
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
         // TODO Auto-generated method stub
          
         int maxAddressLineIndex = getItem(position).getMaxAddressLineIndex();
         String addressLine = "";
          
         for (int j = 0; j <= maxAddressLineIndex; j++){
          addressLine += getItem(position).getAddressLine(j) + ",";
         }
          
         TextView rowAddress = new TextView(mycontext);
         rowAddress.setText(addressLine);
          
         return rowAddress;
       
        }
       
       }
    private FixedMyLocationOverlay myLocationOverLay;
    private MyItemizedOverlay myItemizedOverlay;
    private JSONArray resultJson = new JSONArray();
    private EditText searchText;
    private Button searchButton;
    private ListView listviewResult;
    private Geocoder myGeocoder;
}


