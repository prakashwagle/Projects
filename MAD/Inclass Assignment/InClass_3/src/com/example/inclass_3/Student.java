package com.example.inclass_3;

import java.io.Serializable;

public class Student implements Serializable{
	
	String name="Jhon";
	public Student(String name, String email, String programing_language,
			String account_search, int mood) {
		super();
		this.name = name;
		this.email = email;
		this.programing_language = programing_language;
		this.account_search = account_search;
		this.mood = mood;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email
				+ ", programing_language=" + programing_language
				+ ", account_search=" + account_search + ", mood=" + mood + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrograming_language() {
		return programing_language;
	}
	public void setPrograming_language(String programing_language) {
		this.programing_language = programing_language;
	}
	public String getAccount_search() {
		return account_search;
	}
	public void setAccount_search(String account_search) {
		this.account_search = account_search;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	String email="jhondow@gmail.com";
	String programing_language="Java";
	String account_search="Unsearchable";
	int mood=0;
	
	

}
