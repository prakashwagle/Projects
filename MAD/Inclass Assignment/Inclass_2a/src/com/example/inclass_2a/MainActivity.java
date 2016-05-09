/* 
  Inclass_2a
  Prakash Wagle*/
package com.example.inclass_2a;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState)   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button add =(Button) findViewById(R.id.button1);
		Button sub =(Button) findViewById(R.id.button2);
		Button mult =(Button) findViewById(R.id.button3);
		Button div =(Button) findViewById(R.id.button4);
		Button clear =(Button) findViewById(R.id.button5);
		
		
		EditText ed1 = (EditText) findViewById(R.id.editText1);
		EditText ed2 = (EditText) findViewById(R.id.editText2);
		TextView tv = (TextView) findViewById(R.id.textView1);
	
		add.setOnClickListener(this);
		sub.setOnClickListener(this);
		mult.setOnClickListener(this);
		div.setOnClickListener(this);
		clear.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText ed1 = (EditText) findViewById(R.id.editText1);
		EditText ed2 = (EditText) findViewById(R.id.editText2);
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		if(ed1.getText().toString().trim().length() <= 0 || ed2.getText().toString().trim().length() <= 0 
				|| ed1.getText().toString().equals("-") || ed2.getText().toString().equals("-"))
		{
			tv.setText("Result: Error");
		}
		else
		{
		if(v.getId()==R.id.button1)
		{
			float rs = Float.parseFloat(ed1.getText().toString()) + Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(v.getId()==R.id.button2)
		{
			float rs = Float.parseFloat(ed1.getText().toString()) - Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(v.getId()==R.id.button3)
		{
			float rs = Float.parseFloat(ed1.getText().toString()) * Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(v.getId()==R.id.button4)
		{
			try
			{
				float rs = Float.parseFloat(ed1.getText().toString()) / Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
			if(Float.parseFloat(ed2.getText().toString())==0)
			{
				Toast.makeText(getApplicationContext(), "Invalid Opeation!!! =)",
						   Toast.LENGTH_LONG).show();
				tv.setText("Result: "+"Invalid Operation");
			}
			}
			catch(Exception e)
			{
				tv.setText("Result: "+"Invalid Operation");
			}
			
		}
		else if(v.getId()==R.id.button5)
		{
			ed1.setText("");
			ed2.setText("");
			tv.setText("Result:");
		}
		}
	}
}
