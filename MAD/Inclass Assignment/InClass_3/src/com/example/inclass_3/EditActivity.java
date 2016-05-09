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

public class EditActivity extends Activity {
	
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
		// setContentView(R.layout.activity_edit);
		
		name = (TextView) findViewById(R.id.textView5);
		email = (TextView) findViewById(R.id.textView2);
		account = (TextView) findViewById(R.id.textView3);
		program = (TextView) findViewById(R.id.textView4);
		mood = (TextView) findViewById(R.id.textView6);
		Ename= (ImageView) findViewById(R.id.imageView1);
		Eemail= (ImageView) findViewById(R.id.imageView2);
		
		    if(getIntent().getExtras().toString().length()==0)
		    {
		    	setResult(RESULT_CANCELED);
		    }
		    else if (getIntent().getExtras().getString("Edit").toString().equalsIgnoreCase("name"))
		    {
			RelativeLayout rv = new RelativeLayout(getBaseContext());
			rv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			final EditText Ename = new EditText(getBaseContext());
			Ename.setText(R.string.Name);
			Ename.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			Ename.setId(100);
			
			Button save =new Button(getBaseContext());
			RelativeLayout.LayoutParams btLayout = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			btLayout.addRule(RelativeLayout.BELOW, Ename.getId());
			save.setText(R.string.submit);
			save.setLayoutParams(btLayout);
			
			rv.addView(Ename);
			rv.addView(save);
			setContentView(rv);
			
			save.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String name=Ename.getText().toString();
					if(name.length()==0)
					{
						setResult(RESULT_CANCELED);
					}
					else
					{
						
						Intent intent = new Intent();
						intent.putExtra("EditName", Ename.getText().toString());
						setResult(RESULT_OK, intent);
						
						
					}
					finish();
				}
			});
		   }
		    
		    else if (getIntent().getExtras().getString("Edit").toString().equalsIgnoreCase("Email"))
		    {
			RelativeLayout rv = new RelativeLayout(getBaseContext());
			rv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			final EditText Eemail = new EditText(getBaseContext());
			Eemail.setText(R.string.Email);
			Eemail.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			Eemail.setId(100);
			
			Button save =new Button(getBaseContext());
			RelativeLayout.LayoutParams btLayout = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			btLayout.addRule(RelativeLayout.BELOW, Eemail.getId());
			save.setText(R.string.submit);
			save.setLayoutParams(btLayout);
			
			rv.addView(Eemail);
			rv.addView(save);
			setContentView(rv);
			
			save.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String name=Eemail.getText().toString();
					if(name.length()==0)
					{
						setResult(RESULT_CANCELED);
					}
					else
					{
						
						Intent intent = new Intent();
						intent.putExtra("EditEmail", Eemail.getText().toString());
						setResult(RESULT_OK, intent);
						
						
					}
					finish();
				}
			});
		   }
	
		
		
	}
	
	@Override
	public Intent getIntent() {
		// TODO Auto-generated method stub
		return super.getIntent();
	}
}
