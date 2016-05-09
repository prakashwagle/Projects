package com.example.midterms2015;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NEWS {
	
	
	String description="";
	String link="";
	String media_Small_URL="";
	String media_Bigl_URL="";
	String pubDate; 
	
	String title="";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMedia_Small_URL() {
		return media_Small_URL;
	}
	public void setMedia_Small_URL(String media_Small_URL) {
		this.media_Small_URL = media_Small_URL;
	}
	public String getMedia_Bigl_URL() {
		return media_Bigl_URL;
	}
	public void setMedia_Bigl_URL(String media_Bigl_URL) {
		this.media_Bigl_URL = media_Bigl_URL;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		
		
		this.pubDate=pubDate;
	}
	@Override
	public String toString() {
		return "NEWS [title=" + title + ", description=" + description
				+ ", link=" + link + ", media_Small_URL=" + media_Small_URL
				+ ", media_Bigl_URL=" + media_Bigl_URL + ", pubDate=" + pubDate
				+ "]";
	}
	
	

}
