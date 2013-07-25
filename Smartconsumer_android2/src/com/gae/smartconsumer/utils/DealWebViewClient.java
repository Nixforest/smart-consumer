/**
 * SmartConsumer_Fsoft
 * com.gae.smartconsumer.utils
 * Jul 24, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Class Deal Webview client.
 * @author NguyenPT
 *
 */
public class DealWebViewClient extends WebViewClient {
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return true;
	}
}
