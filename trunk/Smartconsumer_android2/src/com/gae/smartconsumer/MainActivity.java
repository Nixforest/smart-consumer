package com.gae.smartconsumer;

import com.gae.smartconsumer.activity.ListDealsActivity;
import com.gae.smartconsumer.utils.JSONParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.textOnMain);
        //tv.setText(JSONParser.INSTANCE.getJsonObjectsFromHttp());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onBtnListDealsClick(View v) {
    	Intent intent = new Intent(this, ListDealsActivity.class);
    	startActivity(intent);
    }
    
}
