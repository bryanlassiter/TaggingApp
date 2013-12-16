package com.example.tagging;

public class Photo {
	
	private long id;
	private String fileName;
	private String tagName;
	private String dateCreated;
	private String photoDesc;
	
	public Photo(long id, String fileName, String tagName, String dateCreated, String photoDesc){
		this.id = id;
		this.fileName = fileName;
		this.tagName = tagName;
		this.dateCreated = dateCreated;
		this.photoDesc = photoDesc;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
        this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	public String getTagName(){
		return tagName;
	}
	public void setTagName(String tagName){
		this.tagName = tagName;
	}
	public String getDateCreated(){
		return dateCreated;
	}
	public void setDatecreated(String dateCreated){
		this.dateCreated = dateCreated;
	}	
	public String getPhotoDesc(){
		return photoDesc;
	}
	public void setPhotoDesc(String photoDesc){
		this.photoDesc = photoDesc;
	}
	
}