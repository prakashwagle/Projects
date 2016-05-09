package com.example.inclass_6;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
static TextView name;
static TextView owner;
static ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		name=(TextView) findViewById(R.id.textView1);
		owner=(TextView) findViewById(R.id.textView2);
		img=(ImageView) findViewById(R.id.imageView1);
		
		String photoname = getIntent().getExtras().getString("name");
		String photourl = getIntent().getExtras().getString("url");
		String photoowner = getIntent().getExtras().getString("owner");
		name.setText("Name:"+photoname.toString());
		owner.setText("Owner: "+photoowner.toString());
		Picasso.with(this).load(photourl.toString()).into(img);
		
		
	}
}
