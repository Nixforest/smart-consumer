package com.gae.android.smartconsumer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewDealBrowser extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_deal_browser);
        String url = getIntent().getExtras().getString("link");
        webview = (WebView)findViewById(R.id.viewdealbrowser);
        //webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebViewClient(new MyWebViewClient());
        webview.loadUrl(url);
        
        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient(){
           @Override
        public void onProgressChanged(WebView view, int progress) {
            activity.setProgress(progress*100);
        } 
        });
        //System.out.println(url);
        //webview.loadUrl("http://vnexpress.net");
    }
    private WebView webview;
    public class MyWebViewClient extends WebViewClient {  
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true; 
        }
    }
}
