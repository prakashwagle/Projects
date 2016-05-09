package com.example.midterms2015;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class NEWSUtil {
	
	static public class PullParser extends DefaultHandler
	{
		
		static public ArrayList<NEWS>parser(InputStream ip) throws XmlPullParserException, IOException
		{
			ArrayList<NEWS> newsList = new ArrayList<NEWS>();
			NEWS news = new NEWS();
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setInput(ip,"UTF-8");
			int event= pullParser.getEventType();
			
			while(event!=XmlPullParser.END_DOCUMENT)
			{
				switch (event) {
				
				case XmlPullParser.START_TAG:
					//Log.d("DEMO", pullParser.nextText().trim());
					if(pullParser.getName().equals("item"))
					{
						news= new NEWS();
						
					}
					else if(pullParser.getName().equals("title"))
					{
						news.setTitle(pullParser.nextText().trim());
					//	Log.d("GOA",pullParser.nextText().trim());
					}
					else if(pullParser.getName().equals("description"))
					{
						news.setDescription(pullParser.nextText().trim());
					}
					else if(pullParser.getName().equals("link"))
					{
						news.setLink(pullParser.nextText().trim());
					}
					else if(pullParser.getName().equals("pubDate"))
					{
						news.setPubDate(pullParser.nextText().trim());
					}
					else if(pullParser.getName().equals("media:thumbnail"))
					{
						
						if(pullParser.getAttributeValue(null,"height").equals("81"))
						{
							news.setMedia_Small_URL(pullParser.getAttributeValue(null, "url"));
						}
						
					} 

					break;
					
				case XmlPullParser.END_TAG:
					
					if(pullParser.getName().equals("item"))
					{
						newsList.add(news);
						news=null;
						
					}
					break;

				default:
					break;
				}
				event=pullParser.next();
			}
			
			return newsList;
		//	return null;
		}
	}

}
