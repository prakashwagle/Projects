package com.example.midterms2015;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyHistory extends Activity {
	DataManger dm ;
	NEWSDB newsdb ;
	List<NEWSDB> lnews;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_history);
		listview =(ListView) findViewById(R.id.listView1);
		dm = new DataManger(this);
		 newsdb = new NEWSDB();
		 lnews=dm.getAllNews();
		 ArrayList<String> news_title= new ArrayList<String>();
			ArrayAdapter<String> news_adapter;
			for(int i=0;i<lnews.size();i++)
			{
			  news_title.add(lnews.get(i).getTitle());
			}
			news_adapter = new ArrayAdapter<String>(MyHistory.this,android.R.layout.simple_list_item_1, news_title);
			listview.setAdapter(news_adapter);
			
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					NEWS news = NewsActivity.newsList.get(arg2);
					Intent intent =new Intent(MyHistory.this, NewsDetailsActivity.class);
					intent.putExtra("title", news.getTitle());
					intent.putExtra("description", news.getDescription());
					intent.putExtra("date", news.getPubDate());
					intent.putExtra("media", news.getMedia_Small_URL());
					intent.putExtra("link",news.getLink());
					intent.putExtra("count",lnews.get(arg2).get_id());
					
					newsdb=dm.getNews(news.getLink());
					long id1=newsdb.get_id();
					id1+=1;
					newsdb.set_id(id1);
					boolean id_up=dm.updateNews(newsdb);
				//	Log.d("GOA", "Update Saved"+id_up+" "+newsdb.get_id());
					newsdb=null;
					startActivity(intent);
				}
			});
		
	}
	
	
}
