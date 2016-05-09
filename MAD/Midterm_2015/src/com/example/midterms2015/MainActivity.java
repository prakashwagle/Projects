package com.example.midterms2015;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	static ArrayList<String> topics;
	static ArrayAdapter<String> topics_adapter;
	static ListView listView; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topics = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView1);
        topics.add("Top Stories");topics.add("World");topics.add("UK");
        topics.add("Business");topics.add("Politics");topics.add("Health");
        topics.add("Education & Family");topics.add("Science & Environment");
        topics.add("My History");
        topics_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,topics);
        listView.setAdapter(topics_adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 String topic_selected = topics.get(arg2);
				 String url="";
				 if(topic_selected.equalsIgnoreCase("Top Stories"))
				 {
					 url="http://feeds.bbci.co.uk/news/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("World"))
				 {
					 url="http://feeds.bbci.co.uk/news/world/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("UK"))
				 {
					 url="http://feeds.bbci.co.uk/news/uk/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("Business"))
				 {
					 url="http://feeds.bbci.co.uk/news/business/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("Politics"))
				 {
					 url="http://feeds.bbci.co.uk/news/politics/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("Health"))
				 {
					 url="http://feeds.bbci.co.uk/news/health/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("Education & Family"))
				 {
					 url="http://feeds.bbci.co.uk/news/education/rss.xml";
				 }
				 else if (topic_selected.equalsIgnoreCase("Science & Environment"))
				 {
					 url="http://feeds.bbci.co.uk/news/science_and_environment/rss.xml";
				 }
				 
				if(!topic_selected.equalsIgnoreCase("My History"))
				{
				 Intent newActivityIntent = new Intent(MainActivity.this, NewsActivity.class);
				 newActivityIntent.putExtra("URL", url);
				 startActivity(newActivityIntent);
				}
				else
				{
					Intent newActivityIntent = new Intent(MainActivity.this, MyHistory.class);
					// newActivityIntent.putExtra("URL", url);
					 startActivity(newActivityIntent);
				}
				
			}
		});
        
        
    }
}
