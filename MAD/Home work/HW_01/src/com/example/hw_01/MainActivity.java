package com.example.hw_01;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private static SeekBar sb;
	private static TextView tv;
	private static TextView tip_perc;
	private static TextView tip;
	private static TextView total;
	private static EditText ed;
	private static RadioGroup rg;
	private int total_val=0;
	private boolean custom=false;
	private int tip_percentage=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tip_perc=(TextView) findViewById(R.id.textView2);
		tip=(TextView) findViewById(R.id.textView5);
		total=(TextView) findViewById(R.id.textView6);
		ed = (EditText) findViewById(R.id.editText1);
		Button bt = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView4);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		rg = (RadioGroup) findViewById(R.id.RadioGroup1);
		
		ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				tip_perc.setText("Tip: "+String.valueOf(tip_percentage)+" %");
				int tip_dollar = calculateTip(tip_percentage, Integer.parseInt(ed.getText().toString()));
				total_val=Integer.parseInt(ed.getText().toString())+tip_dollar;
				tip.setText("Tip: "+String.valueOf(tip_dollar));
				total.setText("Total: " + String.valueOf(total_val));
				return false;
			}
		});
		
	    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Log.d("demo",String.valueOf(checkedId));
				int tip_dollar=0;
				if(checkedId==2131099654)
				{
					tip_percentage=10;
					tip_perc.setText("Tip: 10%");
					tip_dollar=calculateTip(10, Integer.parseInt(ed.getText().toString()));
					total_val=Integer.parseInt(ed.getText().toString())+tip_dollar;
					tip.setText("Tip: "+String.valueOf(tip_dollar));
					total.setText("Total: " + String.valueOf(total_val));
					custom=false;
				}
				else if(checkedId==2131099655)
				{
					tip_percentage=15;
					tip_perc.setText("Tip: 15%");
					tip_dollar=calculateTip(15, Integer.parseInt(ed.getText().toString()));
					total_val=Integer.parseInt(ed.getText().toString())+tip_dollar;
					tip.setText("Tip: "+String.valueOf(tip_dollar));
					total.setText("Total: " + String.valueOf(total_val));
					custom=false;
				}
				else if(checkedId==2131099656)
				{
					tip_percentage=18;
					tip_perc.setText("Tip: 18%");
					tip_dollar=calculateTip(18, Integer.parseInt(ed.getText().toString()));
					total_val=Integer.parseInt(ed.getText().toString())+tip_dollar;
					tip.setText("Tip: "+String.valueOf(tip_dollar));
					total.setText("Total: " + String.valueOf(total_val));
					custom=false;
				}
				else if(checkedId==2131099657)
				{
					custom=true;
				}
				
			}
		});
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress_value=0;
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if(custom)
				{
					tip_perc.setText("Tip: "+String.valueOf(progress_value)+" %");
					int tip_dollar = calculateTip(progress_value, Integer.parseInt(ed.getText().toString()));
					total_val=Integer.parseInt(ed.getText().toString())+tip_dollar;
					tip.setText("Tip: "+String.valueOf(tip_dollar));
					total.setText("Total: " + String.valueOf(total_val));
					
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress_value= progress;
				tip_percentage=progress;
				tv.setText(" "+String.valueOf(progress)+" %");
				
				
			}
		});
		
		
		bt.setOnClickListener(this);
		if(ed.getText().toString().isEmpty()==true)
		{
			ed.setError("This Field cannot remain Empty");
		}
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId()==findViewById(R.id.button1).getId())
		{
			System.exit(0);
			Log.d("demo", "Got it");
		}
		
	}
	
	public int calculateTip(int tip,int total)
	{
		int tip_perc=0;
		tip_perc=(total*tip)/100;
		System.out.println("Tip"+tip_perc);
		return tip_perc;
	}
}
