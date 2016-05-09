package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("Name", "Prakash");
		testObject.saveInBackground();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		ParseAnalytics.trackAppOpenedInBackground(getIntent());
	}
}
