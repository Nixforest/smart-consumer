/**
 * SmartConsumer
 * com.gae.smartconsumer
 * Jun 27, 2013
 * NguyenPT
 */
package com.gae.smartconsumer;

import com.gae.smartconsumer.activity.ListDealsActivity;
import com.gae.smartconsumer.activity.MapDealActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Main activity class
 * @author NguyenPT
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	/**
	 * Called when the activity is first created.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get button List deal
        this.m_BtnListDeal = (Button) findViewById(R.id.btn_listDeal);
        this.m_BtnListDeal.setOnClickListener(this);
        // Get button Map
        this.m_BtnMap = (Button) findViewById(R.id.btn_map);
        this.m_BtnMap.setOnClickListener(this);
        // Get button About
        this.m_BtnAbout = (Button) findViewById(R.id.btn_about);
        this.m_BtnAbout.setOnClickListener(this);
    }
    /**
     * Called when choose options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * Button list deals.
     */
    private Button m_BtnListDeal;
    /**
     * Button map.
     */
    private Button m_BtnMap;
    /**
     * Button about.
     */
    private Button m_BtnAbout;
    /**
     * Handle when click on buttons.
     */
	@Override
	public void onClick(View view) {
		if (view == m_BtnListDeal) {
			Intent intent = new Intent(this, ListDealsActivity.class);
			startActivity(intent);
		} else if (view == m_BtnMap) {
			Intent intent = new Intent(this, MapDealActivity.class);
			startActivity(intent);
		} else if (view == m_BtnAbout) {
			
		}
	}
    
}
