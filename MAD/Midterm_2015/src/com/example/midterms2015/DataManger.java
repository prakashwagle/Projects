package com.example.midterms2015;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManger {
	
	Context mContext;
    DataBaseOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	NEWSDao newsdao;
	public DataManger(Context mContext){
	this.mContext = mContext;
	dbOpenHelper = new DataBaseOpenHelper(mContext);
	db = dbOpenHelper.getWritableDatabase();
	newsdao = new NEWSDao(db);
	}
	public void close(){
	db.close();
	}
	public long saveNews(NEWSDB news){
	return newsdao.save(news);
	}
	public boolean updateNews(NEWSDB news){
	return newsdao.update(news);
	}
//	public boolean deleteNote(NEWSDB news){
//	return newsdao.delete(news);
//	}
	public NEWSDB getNews(String url){
	return newsdao.get(url);
	}
	public List<NEWSDB> getAllNews(){
		return newsdao.getAll();
		}

}
