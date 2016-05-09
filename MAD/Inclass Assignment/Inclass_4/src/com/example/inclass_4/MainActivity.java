package com.example.inclass_4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.StaticLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private SeekBar sb;
	private TextView tv1;
	private TextView tv2;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private ProgressDialog pd;
	int progress_value=0;
	private Handler handler;
    ExecutorService threadpool;
    Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sb= (SeekBar)findViewById(R.id.seekBar1);
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		bt3=(Button)findViewById(R.id.button3);
		
		
		pd=new ProgressDialog(MainActivity.this);
		pd.setMessage("Retreving the Number");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		
		threadpool = Executors.newFixedThreadPool(2);
			
			
		
		
		handler = new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what)
				{
				case genrateNumber_thread.STATUS_START:
					pd.show();
					break;
				case genrateNumber_thread.STATUS_STEP:
					pd.setProgress((Integer)msg.obj);
					break;
				case genrateNumber_thread.STATUS_DONE:
					pd.dismiss();
					tv2.setText("      "+String.valueOf(msg.obj));
					break;
					
				}
				return false;
			}
		});
		
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				String msg= "Selected Complexity:     "+String.valueOf(progress_value)+" times";
				tv1.setText(msg);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				progress_value=arg1;
			}
		});
		
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new genrateNumber().execute(progress_value);
			}
		});
		
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				threadpool.execute(new genrateNumber_thread(progress_value));
			}
		});
		
		bt3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getBaseContext(), SlideShow.class);
				intent.putExtra("URL", " http://dev.theappsdr.com/apis/photos.php");
				startActivity(intent);
			}
		});
	}
	
	public  class genrateNumber extends AsyncTask<Integer, Integer, Double>
	{

		
		double result_number=0;
		@Override
		protected void onPostExecute(Double result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv2.setText("      "+String.valueOf(result_number));
			pd.dismiss();
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(MainActivity.this);
			pd.setMessage("Retreving the Number");
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.setMax(100);
			pd.show();
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pd.setProgress(values[0]);
		}

		@Override
		protected Double doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			HeavyWork hw= new HeavyWork();
			for(int i=0;i<params[0];i++)
			{
				result_number=result_number+hw.getNumber();
				publishProgress(i+1);
			}
			return (result_number/params[0]);
			//return null;
		}
		
	}
	
	public  class genrateNumber_thread implements Runnable
	{
		static final int STATUS_START=0X00; 
		static final int STATUS_STEP=0X01; 
		static final int STATUS_DONE=0X02; 
		int progress=0;
		
		public genrateNumber_thread(int value)
		{
			progress=value;
		}
       HeavyWork hw = new HeavyWork();
       Message msg ;
       double result_msg=0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			msg =new Message();
			msg.what=STATUS_START;
			handler.sendMessage(msg);
			for(int i=0;i<progress;i++)
			{
			result_msg=result_msg+hw.getNumber();
			msg =new Message();
			msg.what=STATUS_STEP;
			msg.obj=i+1;
			handler.sendMessage(msg);
			} 
			
			msg =new Message();
			msg.what=STATUS_DONE;
			msg.obj=(result_msg/progress);
			handler.sendMessage(msg);
			
		}
		
	}
	
	
}
