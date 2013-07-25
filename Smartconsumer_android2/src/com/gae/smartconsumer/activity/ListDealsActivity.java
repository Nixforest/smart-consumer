/**
 * SmartConsumer
 * com.gae.smartconsumer.activity
 * Jun 27, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.activity;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gae.smartconsumer.model.Deal;
import com.gae.smartconsumer.utils.GlobalVariable;
import com.gae.smartconsumer.utils.JSONParser;
import com.gae.smartconsumer.utils.ListDealsAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Class List Deals activity.
 * @author NguyenPT
 *
 */
public class ListDealsActivity extends Activity {
	/**
	 * JSON array list of deal items.
	 */
	private JSONArray m_JSONArr = new JSONArray();
	/**
	 * List deals adapter.
	 */
	private ListDealsAdapter m_ListDealAdapter;
	/**
	 * onCreate method implement.
	 * @param savedInstanceState Bundle object
	 */
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		// Get Json array
		try {
			m_JSONArr = new JSONParser().execute(
					GlobalVariable.GET_LIST_DEALS,
					String.valueOf(GlobalVariable.NUMBER_DEALS)).get();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (ExecutionException ex) {
			ex.printStackTrace();
		}
		// Create list adapter
		m_ListDealAdapter = new ListDealsAdapter(this,
					toArrayDeal(m_JSONArr),
					com.gae.smartconsumer.R.layout.listdeals_item);
		super.onCreate(savedInstanceState);
		setContentView(com.gae.smartconsumer.
				R.layout.activity_listdeals);
		ListView listview = (ListView) findViewById(com.gae.smartconsumer.R.id.listview_listdeal);
		listview.setAdapter(m_ListDealAdapter);
	}
	/**
	 * Convert a JSONArray to List deals.
	 * @param jsonArr JSONArray object
	 * @return ListDeals
	 */
	private ArrayList<Deal> toArrayDeal(JSONArray jsonArr) {
		ArrayList<Deal> arrDeal = new ArrayList<Deal>();
		try {
			if (jsonArr.length() > 0) {
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject obj = jsonArr.getJSONObject(i);
					@SuppressWarnings("deprecation")
					Date date = new Date(obj.getString("endTime"));
					
					Deal deal = new Deal(obj.getLong("id"),
							obj.getString("title"),
							obj.getString("description"),
							obj.getString("link"),
							obj.getString("imageLink"),
							obj.getDouble("price"),
							obj.getDouble("basicPrice"),
							obj.getString("unitPrice"),
							obj.getDouble("save"),
							obj.getInt("numberBuyer"),
							date,
							obj.getBoolean("isVoucher"),
							obj.getLong("categoryId"));
					arrDeal.add(deal);
				}
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return arrDeal;
	}
}
