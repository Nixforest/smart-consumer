/**
 * SmartConsumer_Fsoft
 * com.gae.smartconsumer.activity
 * Jul 24, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.activity;

import com.gae.smartconsumer.R;
import com.gae.smartconsumer.utils.DealWebViewClient;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Class show deal info to web browser.
 * @author NguyenPT
 *
 */
public class ViewDealActivity extends Activity {
	/**
	 * Web viewer.
	 */
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_deal_browser);
		String url = getIntent().getExtras().getString("link");
		mWebView = (WebView) findViewById(R.id.viewdealbrowser);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setWebViewClient(new DealWebViewClient());
		mWebView.loadUrl(url);
		
		final Activity activity = this;
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				activity.setProgress(newProgress * 100);
			}
		});
	}
}
