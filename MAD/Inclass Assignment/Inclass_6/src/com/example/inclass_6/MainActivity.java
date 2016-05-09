package com.example.inclass_6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	static EditText ed;
	static Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed=(EditText) findViewById(R.id.editText1);
		submit=(Button) findViewById(R.id.button1);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ed.getText().toString().isEmpty())
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Search Key", 100);
					toast.show();
				}
				else
				{
					String url ="https://api.500px.com/v1/photos/search?consumer_key=9OkfOh9XjKwcPXljAYqPVysqnIrCHwpCoEFcTz49&term="+ed.getText().toString()+"&rpp=50&image_size=4";
					Intent intent = new Intent(MainActivity.this, ViewPhotoList.class);
					intent.putExtra("url", url);
					startActivity(intent);
				}
				
			}
		});
	}
}
