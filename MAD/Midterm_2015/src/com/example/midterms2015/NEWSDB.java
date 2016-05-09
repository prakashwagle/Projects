package com.example.midterms2015;

public class NEWSDB {
	
	private long _id;
	private String url,title;
	
	
	
	public NEWSDB(String url, String title,long id) {
		super();
		this._id=id;
		this.url = url;
		this.title = title;
	}
	public NEWSDB()
	{
		
	}
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
