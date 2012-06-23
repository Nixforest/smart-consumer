package com.gae.android.smartconsumer.gae;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import com.gae.android.smartconsumer.util.Common;

import android.os.AsyncTask;
import android.util.Log;

public class Engine extends AsyncTask<String, Integer, JSONArray> {
    @Override
    protected JSONArray doInBackground(String... params) {
        try{
            StringBuilder fullUrl = new StringBuilder(Common.SERVICE_URL);
            fullUrl.append(params[0]);
            fullUrl.append(Integer.parseInt(params[1]));
            HttpGet get = new HttpGet(fullUrl.toString());
            HttpClient client = new DefaultHttpClient();
            HttpResponse res = client.execute(get);
            int statusCode = res.getStatusLine().getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = res.getEntity();
                String json = EntityUtils.toString(entity);
                return new JSONArray(json);
            }else{
                String reason = res.getStatusLine().getReasonPhrase();
                throw new RuntimeException(reason);
            }
        }catch (Exception ex) {
            Log.w("error" ,ex);
            throw new RuntimeException(ex);
        }
    }    
}
