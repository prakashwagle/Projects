package com.example.midterms2015;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsWebViewActivity extends Activity {
	static WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_web_view);
		webView =(WebView) findViewById(R.id.webView1);
		webView.setWebViewClient(new MyWebViewClient());
		String link = getIntent().getExtras().getString("link");
		webView.loadUrl(link);
	}
	
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	Log.d("BOM", Uri.parse(url).getHost().toString());
	     
	    	return false;
	    }
	}
}
