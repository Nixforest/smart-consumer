package com.gae.android.smartconsumer.activity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gae.android.smartconsumer.blo.ListAdapter_ListDeal;
import com.gae.android.smartconsumer.gae.Engine;
import com.gae.android.smartconsumer.model.Deal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ListDeal extends Activity {    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            resultJson = new Engine().execute("/getListDeal.app?limit=","10").get();
        } catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        listAdapter = new ListAdapter_ListDeal(this, R.layout.list_deal_items, toArray(resultJson));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_deal);        
        ListView listview = (ListView)findViewById(R.id.listview_listdeal);System.out.println("testsetsetest");
        listview.setAdapter(listAdapter);
    }
    private TextView textview;
    private JSONArray resultJson = new JSONArray();
    private ListAdapter_ListDeal listAdapter;
    protected void displayResult(JSONArray result) throws JSONException{
        textview.setText(result.toString());
    }
    private ArrayList<Deal> toArray(JSONArray json){
        ArrayList<Deal> array = new ArrayList<Deal>();
        try{
            if(json.length()>0){
                for(int i=0;i<json.length();i++){
                    JSONObject obj = json.getJSONObject(i);
                    Deal deal = new Deal( obj.getString("title"), obj.getString("description"),
                            obj.getString("link"), obj.getString("imageLink"), obj.getDouble("price"),
                            obj.getDouble("basicPrice"), obj.getBoolean("isVoucher"));
                    array.add(deal);                                   
                }
            }
        } catch (JSONException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return array;
    }
}

