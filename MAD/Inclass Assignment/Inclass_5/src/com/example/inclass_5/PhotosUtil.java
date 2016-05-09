package com.example.inclass_5;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;

public class PhotosUtil {
	
	static public class PhotosSAXParser extends DefaultHandler
	{
		ArrayList<Photos> photosList;
		StringBuilder xmlInnerlist; 
		
		Photos photo;
		int counter = 0;
		static public ArrayList<Photos> parsePhotos(InputStream in) throws IOException, SAXException
		{
			PhotosSAXParser parser = new PhotosSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getPhotoList();
			
		}
		
		public ArrayList<Photos> getPhotoList()
		{
			return photosList;
		}
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
			photosList = new ArrayList<Photos>();
			xmlInnerlist = new StringBuilder();
			
		}
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			if(localName.equals("photo"))
			{
			   photo = new Photos();
			   photo.setPhotoId(attributes.getValue("id"));
			   photo.setOwner(attributes.getValue("owner"));
			   photo.setUrl(attributes.getValue("url_m"));
			   photosList.add(photo);
			   
			}
			
		}
	
		
	}
	

}
