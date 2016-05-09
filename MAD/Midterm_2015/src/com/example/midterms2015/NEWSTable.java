package com.example.midterms2015;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NEWSTable  {
	
	static final String TABLE_NAME="news";
	static final String COLUMN_ID="id";
	static final String COLUMN_URL="url";
	static final String COLUMN_TITLE="title";
	
	static public void onCreate(SQLiteDatabase db)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("create table "+ TABLE_NAME+ "(");
		sb.append(COLUMN_ID + " integer,");
		sb.append(COLUMN_URL + " text primary key,");
		sb.append(COLUMN_TITLE +" text not null);");
		try
		{
		db.execSQL(sb.toString());
		}
		catch(SQLException eb)
		{
			Log.d("GOA", "Cannot create Table");
		}
	}
	
	static public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion)
	{
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		NEWSTable.onCreate(db);
	}

}
