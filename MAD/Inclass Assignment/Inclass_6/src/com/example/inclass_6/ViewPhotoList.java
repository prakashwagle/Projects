package com.example.inclass_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewPhotoList extends Activity {
	static ListView listView;
	static ArrayAdapter<Photos> photoAdapter;
	static ArrayList<Photos> photoList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_photo_list);
		String url = getIntent().getExtras().getString("url");
		listView = (ListView) findViewById(R.id.listView1);
		Log.d("demo", url.toString());
		new GetPhoto().execute(url);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Photos photo = (Photos)photoList.get(arg2);
				Intent intent = new Intent(ViewPhotoList.this, DetailsActivity.class);
				intent.putExtra("name",photo.getPhotoName());
				intent.putExtra("url",photo.getPhotoUrl());
				intent.putExtra("owner",photo.getPhotoOwner());
				startActivity(intent);
				//Log.d("demo", photo.getPhotoName().toString());
				
				
			}
		});
	}
	
	public class GetPhoto extends AsyncTask<String, Void, ArrayList<Photos>> {

		@Override
		protected ArrayList<Photos> doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			try {
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int status = con.getResponseCode();
				if(status==HttpURLConnection.HTTP_OK)
				{
					BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
					StringBuilder sb =new StringBuilder();
					String line =new String();
					line= bf.readLine();
					while(line!=null)
					{
						sb.append(line);
						line=bf.readLine();
					}
					return PhotosUtil.JasonParser.parser(sb.toString());
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}


		@Override
		protected void onPostExecute(ArrayList<Photos> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.d("DEMO", result.toString());
			photoList=result;
			photoAdapter = new ArrayAdapter<Photos>(ViewPhotoList.this,android.R.layout.simple_list_item_1,photoList);
			listView.setAdapter(photoAdapter);
		}



		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		

	}
}
