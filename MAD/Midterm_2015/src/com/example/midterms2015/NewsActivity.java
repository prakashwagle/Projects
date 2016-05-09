package com.example.midterms2015;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends Activity {
static ListView listview;
DataManger dm ;
NEWSDB newsdb ;
NEWSDB newsdb2 ;
static ArrayList<NEWS> newsList = new ArrayList<NEWS>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		String url=getIntent().getExtras().getString("URL");
		listview = (ListView) findViewById(R.id.listView1);
		Log.d("URL", url);
		 dm = new DataManger(this);
		 newsdb = new NEWSDB();
		
		new getNews().execute(url);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				NEWS news = newsList.get(arg2);
				Intent intent =new Intent(NewsActivity.this, NewsDetailsActivity.class);
				intent.putExtra("title", news.getTitle());
				intent.putExtra("description", news.getDescription());
				intent.putExtra("date", news.getPubDate());
				intent.putExtra("media", news.getMedia_Small_URL());
				intent.putExtra("link",news.getLink());
				newsdb.set_id(0);
				newsdb.setTitle(news.getTitle());
				newsdb.setUrl(news.getLink());
				long id=dm.saveNews(newsdb);
				if(id==-1)
				{
					newsdb2=dm.getNews(newsdb.getUrl());
					long id1=newsdb2.get_id();
					id1+=1;
					newsdb2.set_id(id1);
					boolean id_up=dm.updateNews(newsdb2);
					Log.d("GOA", "Update Saved"+id_up+" "+newsdb2.get_id());
					
				}
				else
				{
					Log.d("GOA", "Saved"+id);
				}
				startActivity(intent);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_sort_title_a2z) {
			//Sort by title A-Z
			return true;
		} else if(id == R.id.action_sort_title_z2a){
			//Sort by title Z-A
			return true;
		} else if(id == R.id.action_sort_pubdate_ascending){
			//Sort by pubDate Ascending
			return true;
		} else if(id == R.id.action_sort_pubdate_descending){
			//Sort by pubDate Descending
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class getNews extends AsyncTask<String, Void, ArrayList<NEWS>>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected ArrayList<NEWS> doInBackground(String... params) {
			
			try {
				URL url = new URL(params[0]);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				int response= connection.getResponseCode();
				if(response==HttpURLConnection.HTTP_OK)
				{
					Log.d("DEMO", url.toString());
					InputStream in = connection.getInputStream();
					try {
						return NEWSUtil.PullParser.parser(in);
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<NEWS> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//Log.d("DEMO", result.toString());
			newsList=result;
			NEWS news;
			ArrayList<String> news_title= new ArrayList<String>();
			ArrayAdapter<String> news_adapter;
			for(int i=0;i<result.size();i++)
			{
			  news_title.add(result.get(i).getTitle());
			}
			news_adapter = new ArrayAdapter<String>(NewsActivity.this,android.R.layout.simple_list_item_1, news_title);
			listview.setAdapter(news_adapter);
			
		}

		
		
	}
}
