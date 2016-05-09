package com.example.inclass_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Display extends Activity {

	public TextView name ;
	public TextView email ;
	public TextView account ;
	public TextView program ;
	public TextView mood ;
	public ImageView Ename;
	public ImageView Eemail;
	public Student st=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		name = (TextView) findViewById(R.id.textView5);
		email = (TextView) findViewById(R.id.textView2);
		account = (TextView) findViewById(R.id.textView3);
		program = (TextView) findViewById(R.id.textView4);
		mood = (TextView) findViewById(R.id.textView6);
		Ename= (ImageView) findViewById(R.id.imageView1);
		Eemail= (ImageView) findViewById(R.id.imageView2);
		if(getIntent().getExtras()!=null)
		{
			st = (Student) getIntent().getExtras().getSerializable("Student");
			name.setText("Name: "+st.getName());
			email.setText("Email: "+st.getEmail());
			account.setText("Account State"+ st.getAccount_search());
			program.setText("Prog. Language: "+ st.getPrograming_language());
			mood.setText("Mood: "+ st.getMood() +"% Positive");
			
		}
		Ename.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Intent intent = new Intent("com.example.inclass_3.intent.action.VIEW");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.putExtra("Edit", "Name");
				startActivityForResult(intent, 1);
				
			
			}
		});
		
		Eemail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.example.inclass_3.intent.action.VIEW");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.putExtra("Edit", "Email");
				startActivityForResult(intent, 2);
			
			}
				
		});
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==1)
		{
			st.setName(data.getExtras().getString("EditName"));
		}
		else if(requestCode==2)
		{
			st.setEmail(data.getExtras().getString("EditEmail"));
		}
		else if(requestCode==3)
		{
			
		}
		else if(requestCode==4)
		{
			
		}
		else if(requestCode==5)
		{
			
		}
		
	/*	st = (Student) getIntent().getExtras().getSerializable("Student"); */
		name.setText("Name: "+st.getName());
		email.setText("Email: "+st.getEmail());
		account.setText("Account State"+ st.getAccount_search());
		program.setText("Prog. Language: "+ st.getPrograming_language());
		mood.setText("Mood: "+ st.getMood() +"% Positive");
		
	}
}
