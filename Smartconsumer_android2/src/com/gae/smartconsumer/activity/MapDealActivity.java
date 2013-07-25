/**
 * SmartConsumer_Fsoft
 * com.gae.smartconsumer.activity
 * Jul 25, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.activity;
import android.os.Bundle;
import com.gae.smartconsumer.R;

import com.google.android.maps.MapActivity;

/**
 * Class to show map.
 * @author NguyenPT
 *
 */
public class MapDealActivity extends MapActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_map);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
