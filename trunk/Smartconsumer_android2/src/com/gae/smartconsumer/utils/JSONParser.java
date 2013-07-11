/**
 * SmartConsumer
 * com.gae.smartconsumer.utils
 * Jul 8, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Class JSON parse.
 * @author NguyenPT
 *
 */
public class JSONParser extends AsyncTask<String, Integer, JSONArray> {
	@Override
	protected JSONArray doInBackground(String... params) {
		StringBuilder builder = new StringBuilder(GlobalVariable.HOST);
		builder.append(params[0]);
		builder.append(Integer.parseInt(params[1]));
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(builder.toString());
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity);
				return new JSONArray(json);
			} else {
				String reason = statusLine.getReasonPhrase();
				throw new RuntimeException(reason);
			}
		/*} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();*/
		} catch (Exception e) {
			Log.w("error", e);
			throw new RuntimeException(e);
		}
	}
}
