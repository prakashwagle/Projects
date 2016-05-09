package com.example.inclass_5;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpConnection;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.util.Log;

public class FlickerParser extends AsyncTask<String, Void, ArrayList<Photos>>{
	
	Photos photo;
	Data data;
	MainActivity main;
    public  FlickerParser(Data data)
    {
    	this.data=data;
    }
 /*   public  FlickerParser(Data data)
    {
    	this.data=data;
    } */
	@Override
	protected ArrayList<Photos> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String search_keyword=params[0];
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=b637f8222b596f4c277618ec54968efc&text="+search_keyword+"&extras=url_m&per_page=20");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int response= connection.getResponseCode();
			if(response==HttpURLConnection.HTTP_OK)
			{
				Log.d("DEMO", "HELLO");
				InputStream in = connection.getInputStream();
				return PhotosUtil.PhotosSAXParser.parsePhotos(in);
				 
			}
			
		} 
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
  		e.printStackTrace();
			}
		
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Photos> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	//	Log.d("DEMO", result.toString());
		 data.setResult(result);
		Iterator iterator = result.iterator();
       while (iterator.hasNext()) {
            photo=(Photos)iterator.next();
           Log.d("DEMO", photo.toString());
        } 
      
       
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	
  static public interface Data
  {
	  public void setResult(ArrayList<Photos> res);
  }
}
