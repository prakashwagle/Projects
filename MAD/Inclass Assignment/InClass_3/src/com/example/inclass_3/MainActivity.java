package com.example.inclass_3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	public Student student = new Student("","", "", "", 0);
	public EditText name ;
	public EditText email ;
	public RadioGroup rg;
	public Switch sw ;
	public static SeekBar seekbar;
	public int prog=0;
	public String search="Unsearchable" ;
	public String program="Java" ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 name = (EditText) findViewById(R.id.editText1);
		 email = (EditText) findViewById(R.id.editText2);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		 sw = (Switch) findViewById(R.id.switch1);
		 
		 rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==0)
				{
					 program="Java" ;
				}
				else if (checkedId==1)
				{
					 program="C" ;
				}
				else if (checkedId==2)
				{
					 program="C#" ;
				}
				Log.d("demo", program);
			}
		});
		 
		sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					search="Searchable" ;
				}
				else
				{
					search="Unsearchable" ;
				}
				
			}
		});
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				prog=progress;
			}
		});
		
		
	    
		((Button) findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				student.setName(name.getText().toString());
				student.setEmail(email.getText().toString());
				student.setMood(prog);
				student.setAccount_search(search);
				student.setPrograming_language(program);
				Intent intent =new Intent(MainActivity.this, Display.class);
				intent.putExtra("Student", student);
				startActivity(intent);
				
			}
		});
	}
}
