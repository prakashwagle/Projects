package com.example.midterms2015;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NEWSDao {
	
	private SQLiteDatabase db;

	public NEWSDao(SQLiteDatabase db) {
		super();
		this.db = db;
	}
	
	public long save(NEWSDB nw)
	{
		ContentValues cv = new ContentValues();
		Log.d("GOA",nw.get_id()+","+nw.getTitle()+","+ nw.getUrl());
		cv.put(NEWSTable.COLUMN_ID, nw.get_id());
		cv.put(NEWSTable.COLUMN_TITLE,nw.getTitle());
		cv.put(NEWSTable.COLUMN_URL,nw.getUrl());
		return db.insert(NEWSTable.TABLE_NAME, null, cv);
		
		//String query= "insert or replace into"+ NEWSTable.TABLE_NAME + "("+NEWSTable.COLUMN_ID+","+ NEWSTable.COLUMN_ID +","+ NEWSTable.COLUMN_TITLE +"," + NEWSTable.COLUMN_URL +") values ( (select id+1 from "+ NEWSTable.TABLE_NAME +" where "+ NEWSTable.COLUMN_URL + "=" + nw.getUrl() + "),"+ nw.get_id() + "," + nw.getTitle() + "," + nw.getUrl() + ");";
	}
	
	public boolean update(NEWSDB news){
		ContentValues values = new ContentValues();
		values.put(NEWSTable.COLUMN_ID, news.get_id());
		return db.update(NEWSTable.TABLE_NAME, values, NEWSTable.COLUMN_URL+" =\""+news.getUrl()+"\"", null) > 0;
		}
	
	public NEWSDB get(String url){
		NEWSDB news = null;
		Cursor c = db.query(true, NEWSTable.TABLE_NAME,
		new String[]{NEWSTable.COLUMN_ID, NEWSTable.COLUMN_TITLE, NEWSTable.COLUMN_URL},
		NEWSTable.COLUMN_URL+" =\""+url+"\"", null, null, null, null, null);
		if(c != null){
		c.moveToFirst();
		news = this.buildNEWSDBFromCursor(c);
		}
		if(!c.isClosed()){
		c.close();
		}
		return news;
		}
	
	public List<NEWSDB> getAll(){
		List<NEWSDB> list = new ArrayList<NEWSDB>();
		Cursor c = db.query(NEWSTable.TABLE_NAME,
		new String[]{NEWSTable.COLUMN_ID, NEWSTable.COLUMN_TITLE, NEWSTable.COLUMN_URL},
		null, null, null, null, null);
		if(c != null){
		c.moveToFirst();
		do{
		NEWSDB news = this.buildNEWSDBFromCursor(c);
		if(news != null){
		list.add(news);
		}
		} while(c.moveToNext());
		if(!c.isClosed()){
		c.close();
		}
		}
		return list;
		}
	

	private NEWSDB buildNEWSDBFromCursor(Cursor c) {
		NEWSDB news = null;
		if(c != null){
		news = new NEWSDB();
		news.set_id(c.getLong(0));
		news.setTitle(c.getString(1));
		news.setUrl(c.getString(2));
		}
		return news;
	}

}
