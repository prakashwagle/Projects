package com.example.inclass_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideShow extends Activity {
private URL url=null;
private ProgressDialog pd;
private ProgressDialog pd2;
private ImageView iv;
private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_show);
		iv = (ImageView)findViewById(R.id.imageView1);
		tv = (TextView)findViewById(R.id.textView1);
		String msg= (String) getIntent().getExtras().get("URL");
		
		try {
			url = new URL(msg);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new getUrlCollection().execute();
		
		
		
	}
	
	private boolean isConnectionOnline()
	{
		ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nw = cm.getActiveNetworkInfo();
		if(nw !=null && nw.isConnected())
		{
			return true;
		}
		
		return false;
		
	}
	
	public class getUrlCollection extends AsyncTask<Void, Void, String>
	{
		List<String> list =new ArrayList<String>();
		Iterator<String> itr = list.iterator();
		double result_number=0;
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.d("IMAGE", "SIZE: "+String.valueOf(list.size()));
			pd.dismiss();
			new showImage().execute(list.get(0));
			new showImage().execute(list.get(1));
			new showImage().execute(list.get(2));
			new showImage().execute(list.get(3));
			new showImage().execute(list.get(4));
			new showImage().execute(list.get(5));
			new showImage().execute(list.get(6));
			new showImage().execute(list.get(7));
			new showImage().execute(list.get(8));
			new showImage().execute(list.get(9));
			new showImage().execute(list.get(10));
			//new showImage().execute(list.get(11));
			

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(SlideShow.this);
			pd.setMessage("Loding Image Link");
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.show();
			tv.postDelayed(null, 30000);
			
		}

		
		

		@Override
		protected String doInBackground(Void... params) {
				try {
				
				HttpURLConnection con =(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line="";
				while((line = bf.readLine())!=null)
				{
					list.add(line);
					Log.d("DEMO", "Line: "+line);
				//	new showImage().execute(line);
				}
				Log.d("DEMO", "Line: "+String.valueOf(list.size()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null; 
		}

		
		
	}
	
	
	public class showImage extends AsyncTask<String, Integer, Bitmap>
	{

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			iv.setImageBitmap(result);
			pd2.dismiss();
			iv.postDelayed(null, 50000);
			
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			
           AndroidHttpClient apc = AndroidHttpClient.newInstance("AdroidAgent");
           HttpGet hp = new HttpGet(params[0]);
           try
           {
        	   HttpResponse hpr = apc.execute(hp);
        	   InputStream ips = hpr.getEntity().getContent();
        	   Bitmap image = BitmapFactory.decodeStream(ips);
        	   return image;
           }
           catch(Exception io)
           {
        	   
           }
           finally
           {
            apc.close();
           }
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd2=new ProgressDialog(SlideShow.this);
			pd2.setMessage("Loding Image");
//			pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//	pd2.show();
		}
		
	}
}
