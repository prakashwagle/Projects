package com.parse.starter;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
static public EditText usertext;
static public EditText passtext;
static public Button login;
static public Button signup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		usertext = (EditText) findViewById(R.id.editText1);
		passtext = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button1);
		signup = (Button) findViewById(R.id.button2);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			Log.d("GOA",currentUser.getUsername());
		  Intent intent = new Intent(this, ToDo.class);
		  startActivity(intent);
		} 
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("GOA", "YoYo");
				if(usertext.getText().toString().isEmpty()!=true||passtext.getText().toString().isEmpty()!=true)
				{
					ParseUser.logInInBackground(usertext.getText().toString(), passtext.getText().toString(), new LogInCallback() {
						
						@Override
						public void done(ParseUser user, ParseException e) {
							if (e == null) {
								Log.d("GOA","Login Up Sucessfully");
								Intent intent = new Intent(MainActivity.this, ToDo.class);
								startActivity(intent);
							      
							    } else {
							    	// Toast.makeText(MainActivity.this, "Login UNSucessfully", Toast.LENGTH_SHORT).show();
							    	Log.d("GOA","Login UN Sucessfully");
							    }
							
						}
					});
				}
				
			}
		});
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, SignUp.class);
				startActivity(intent);
			}
		});
	}
}
