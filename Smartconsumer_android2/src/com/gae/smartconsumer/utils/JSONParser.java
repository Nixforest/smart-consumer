/**
 * SmartConsumer
 * com.gae.smartconsumer.utils
 * Jul 8, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import java.io.IOException;
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
		// String builder result
		StringBuilder builder = new StringBuilder(GlobalVariable.HOST);
		// Get first parameter (/getListDeals.app?limit=)
		builder.append(params[0]);
		// Get second parameter (n*30)
		// Builder like this: "http://fsmartconsumer.appspot.com/getListDeals.app?limit=n*30"
		builder.append(Integer.parseInt(params[1]));
		// Http client object
		HttpClient client = new DefaultHttpClient();
		// Http get object
		HttpGet httpGet = new HttpGet(builder.toString());
		try {
			// Execute parser
			HttpResponse response = client.execute(httpGet);
			// Get status
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			// Get success
			if (statusCode == HttpStatus.SC_OK) {
				// Convert to json array object
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity);
				return new JSONArray(json);
			} else {
				String reason = statusLine.getReasonPhrase();
				//throw new RuntimeException(reason);
				Log.w("error", reason);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Log.w("error", e);
			//throw new RuntimeException(e);
		}
		return null;
	}
}
