package com.example.inclass_6;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhotosUtil {
	
	public static class JasonParser
	{
		public static ArrayList<Photos>parser(String js)
		{
			ArrayList<Photos> photoList = new ArrayList<Photos>();
			// Photos photo = new 
			try {
				JSONObject root = new JSONObject(js);
				JSONArray photoArray = root.getJSONArray("photos");
				for(int i=0;i<photoArray.length();i++)
				{
					JSONObject photo = photoArray.getJSONObject(i);
					photoList.add(Photos.createPhotos(photo));
				}
				
				return photoList;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
	}

}
