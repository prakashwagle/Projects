package com.example.midterms2015;



import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailsActivity extends Activity {
static TextView title;
static TextView date;
static TextView desctription;
static TextView countview;
static ImageView img;
NEWSDB newsdb;
DataManger dm;
NEWS news =new NEWS();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_details);
		title =(TextView) findViewById(R.id.textView1);
		date =(TextView) findViewById(R.id.textView2);
		desctription =(TextView) findViewById(R.id.textView3);
		countview =(TextView) findViewById(R.id.textView4);
		img = (ImageView) findViewById(R.id.imageView1);
		dm= new DataManger(this);
		newsdb= new NEWSDB();
		
		 
		news.setTitle(getIntent().getExtras().getString("title"));
		news.setDescription(getIntent().getExtras().getString("description"));
		news.setLink(getIntent().getExtras().getString("link"));
		news.setPubDate(getIntent().getExtras().getString("date"));
		news.setMedia_Small_URL(getIntent().getExtras().getString("media"));
		//long count = getIntent().getExtras().getLong("count");
		newsdb=dm.getNews(news.getLink());
		long count = newsdb.get_id();
		title.setText(news.getTitle());
		date.setText(news.getPubDate());
		desctription.setText(news.getDescription());
		countview.setText("Count: "+Long.toString(count));
		Log.d("Pune", news.getMedia_Small_URL());
		Picasso.with(getApplicationContext()).load(news.getMedia_Small_URL().toString()).into(img);
		
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewsDetailsActivity.this, NewsWebViewActivity.class);
				intent.putExtra("link", news.getLink());
				startActivity(intent);
			}
		});
	}
}
