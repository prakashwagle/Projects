package com.example.inclass_6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

public class Photos {
	
	int photoId = 0;
	String photoName="";
	String photoUrl="";
	String photoOwner="";
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	@Override
	public String toString() {
		return photoName;
	}
		
	public static Photos createPhotos(JSONObject jo) throws JSONException
	{
		Photos photo = new Photos();
		JSONObject user = jo.getJSONObject("user");
		photo.setPhotoId(jo.getInt("id"));
		photo.setPhotoName(jo.getString("name"));
		photo.setPhotoUrl(jo.getString("image_url"));
		photo.setPhotoOwner(user.getString("username"));
		
		return photo;
		
	}
	public String getPhotoOwner() {
		return photoOwner;
	}
	public void setPhotoOwner(String photoOwner) {
		this.photoOwner = photoOwner;
	}
//	@Override
//	public String toString() {
//		return "Photos [photoId=" + photoId + ", photoName=" + photoName
//				+ ", photoUrl=" + photoUrl + ", photoOwner=" + photoOwner + "]";
//	}
	

}
