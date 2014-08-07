package com.nyul.androidgroupapp3;

public class Book {
	String author;
	String title;
	String blurb;
	String pictureName;
	String category;
	
	Book(String author, String title, String pictureName, String category){
		this.pictureName = pictureName;
		this.author = author;
		this.title = title;
		this.category = category;
	}

}
