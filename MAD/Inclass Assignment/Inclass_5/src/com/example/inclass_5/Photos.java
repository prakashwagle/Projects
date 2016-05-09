package com.example.inclass_5;

public class Photos {
	
	String photoId="";
	String owner="";
	String url="";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhotoId() {
		return photoId;
	}
	@Override
	public String toString() {
		return "Photos [photoId=" + photoId + ", owner=" + owner + ", url="
				+ url + "]";
	}
//	public Photos(String photoId, String owner) {
//		super();
//		this.photoId = photoId;
//		this.owner = owner;
//	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

}
