package com.gae.android.smartconsumer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttonList = (Button)findViewById(R.id.button_view);
        buttonList.setOnClickListener(this);
        buttonMap = (Button)findViewById(R.id.button_mapview);
        buttonMap.setOnClickListener(this);
        buttonAbout = (Button)findViewById(R.id.button_about);
        buttonAbout.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        
        if(v == buttonList){
            Intent i = new Intent(this, ListDeal.class);
            startActivity(i);
        }else if(v == buttonMap){
            Intent i = new Intent(this, MapView.class);
            startActivity(i);
        }else if(v == buttonAbout){
            Intent i = new Intent(this, About.class);
            startActivity(i);
        }
    }
    /*private String getData(){
        String text = "123";
        HttpGet request = new HttpGet(Common.SERVICE_URL + "/getDealId.app?id=1");
        //request.setHeader("Accept", "application/json");
        //request.setHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String theString = "123123";
        try{
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            InputStream stream = responseEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            stream.close();
            theString = builder.toString();
        }catch(Exception e){
            Log.i("test", e.getMessage());
        }
        return theString;
    }
    public String mQueryURL(String q) {
        String qResult = null;
        String qString = q;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(qString);
        try {
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Reader in = new InputStreamReader(inputStream);
                BufferedReader bufferedreader = new BufferedReader(in);
                StringBuilder stringBuilder = new StringBuilder();
                String stringReadLine = null;
                while ((stringReadLine = bufferedreader.readLine()) != null) {
                    stringBuilder.append(stringReadLine + "\n");
                }
                qResult = stringBuilder.toString();
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i("value", qResult);
        return qResult;
    }*/
    private Button buttonList;
    private Button buttonMap;
    private TextView edittext;
    private Button buttonAbout;
}