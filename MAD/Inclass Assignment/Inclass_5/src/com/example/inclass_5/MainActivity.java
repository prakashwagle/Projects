package com.example.inclass_5;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.inclass_5.FlickerParser.Data;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements Data{
	public static ImageView imv;
	public static Button go;
	public static ImageButton next;
	public static ImageButton prev;
	public static EditText et;
	public ArrayList<Photos> photolist;
	public Iterator<Photos> itr;
	public int counter=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 imv = (ImageView)findViewById(R.id.imageView1);
		 next = (ImageButton)findViewById(R.id.imageButton2);
		 prev = (ImageButton)findViewById(R.id.imageButton1);
		 go = (Button)findViewById(R.id.button1);
		 et = (EditText)findViewById(R.id.editText1);
		// new FlickerParser(this).execute("UNCC");
		 go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new FlickerParser(MainActivity.this).execute(et.getText().toString());
			}
		});
		 
		 prev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter--;
				if(counter>0)
				{
					Photos ph =new Photos();
					ph = photolist.get(counter);
					Picasso.with(MainActivity.this).load(ph.getUrl()).into(imv);
				}
				
			}
		});
		 next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter++;
				if(counter<20)
				{
					Photos ph =new Photos();
					ph = photolist.get(counter);
					Picasso.with(MainActivity.this).load(ph.getUrl()).into(imv);
				}
				
			}
		});
	}
	
	
	 
	
	@Override
	public void setResult(ArrayList<Photos> res) {
		// TODO Auto-generated method stub
		 photolist = new ArrayList<Photos>();
		photolist=res;
		Photos photo = new Photos();
	    itr = photolist.iterator();
		photo = itr.next();
		Picasso.with(this).load(photo.getUrl()).into(imv);
		counter++;
	}
}
