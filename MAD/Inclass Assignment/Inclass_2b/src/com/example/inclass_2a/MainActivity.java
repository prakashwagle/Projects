/* 
  Inclass_2b
  Prakash Wagle*/

package com.example.inclass_2a;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState)   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button add =(Button) findViewById(R.id.button1);
		
		
		
		EditText ed1 = (EditText) findViewById(R.id.editText1);
		EditText ed2 = (EditText) findViewById(R.id.editText2);
		TextView tv = (TextView) findViewById(R.id.textView1);
	
		add.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText ed1 = (EditText) findViewById(R.id.editText1);
		EditText ed2 = (EditText) findViewById(R.id.editText2);
		TextView tv = (TextView) findViewById(R.id.textView1);
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
		Log.d("demo", String.valueOf(rb.getId()));
		if(ed1.getText().toString().trim().length() <= 0 || ed2.getText().toString().trim().length() <= 0 || rg.getCheckedRadioButtonId()==-1
				|| ed1.getText().toString().equals("-") || ed2.getText().toString().equals("-"))
		{
			tv.setText("Result: Error");
			Toast.makeText(getApplicationContext(), "Error",
					   Toast.LENGTH_LONG).show();
		}
		else
		{
		if(String.valueOf(rb.getTag()).equals("Add (+)"))
		{
			float rs = Float.parseFloat(ed1.getText().toString()) + Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(String.valueOf(rb.getTag()).equals("Substract (-)"))
		{
			float rs = Float.parseFloat(ed1.getText().toString()) - Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(String.valueOf(rb.getTag()).equals("Multiply (*)"))
		{
			float rs = Float.parseFloat(ed1.getText().toString()) * Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
		}
		else if(String.valueOf(rb.getTag()).equals("Division (/)"))
		{
			try
			{
				float rs = Float.parseFloat(ed1.getText().toString()) / Float.parseFloat(ed2.getText().toString());
			tv.setText("Result: "+String.valueOf(rs));
			if(Float.parseFloat(ed2.getText().toString())==0)
			{
				Toast.makeText(getApplicationContext(), "Invalid Opeation!!!",
						   Toast.LENGTH_LONG).show();
				tv.setText("Result: "+"Invalid Operation");
			}
			}
			catch(Exception e)
			{
				tv.setText("Result: "+"Invalid Operation");
				Toast.makeText(getApplicationContext(), "Invalid Opeation!!! =)",
						   Toast.LENGTH_LONG).show();
			}
			
		}
		else if(String.valueOf(rb.getTag()).equals("Clear All"))
		{
			ed1.setText("");
			ed2.setText("");
			tv.setText("Result:");
		}
		}
	}
}
