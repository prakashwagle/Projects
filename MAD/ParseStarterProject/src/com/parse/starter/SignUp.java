package com.parse.starter;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {
static EditText username;
static EditText email;
static EditText password;
static EditText confpassword;
static Button signup;
static Button cancel;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		username =(EditText) findViewById(R.id.editText1);
		email =(EditText) findViewById(R.id.editText2);
		password =(EditText) findViewById(R.id.editText3);
		confpassword =(EditText) findViewById(R.id.editText4);
		
		signup = (Button) findViewById(R.id.button1);
		cancel = (Button) findViewById(R.id.button2);
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUp.this);
				if(username.getText().toString().isEmpty()||password.getText().toString().isEmpty()||
						email.getText().toString().isEmpty()||confpassword.getText().toString().isEmpty())
				{
					alertDialogBuilder.setMessage("Feild Cannot be Empty");
					alertDialogBuilder.show();
				}
				
				else
				{
					//if(password.getText().equals(confpassword.getText()))
					if(true)
					{
						ParseUser user = new ParseUser();
						user.setUsername(email.getText().toString());
						user.setEmail(email.getText().toString());
						user.setPassword(password.getText().toString());
						user.signUpInBackground(new SignUpCallback() {
							
							@Override
							public void done(ParseException e) {
								if (e == null) {
								      
									Log.d("GOA","Singed Up Sucessfully");
									Intent intent = new Intent(SignUp.this, ToDo.class);
									startActivity(intent);
								      
								    } else {
								    	// Toast.makeText(MainActivity.this, "Singed Up UNSucessfully", Toast.LENGTH_SHORT).show();
								    	Log.d("GOA", "Singed Up UNSucessfully");
								    }
								
							}
						});
					}
					else
					{
						alertDialogBuilder.setMessage("Password does nor match");
						alertDialogBuilder.show();
					}
				}
			}
		});
	}
}
