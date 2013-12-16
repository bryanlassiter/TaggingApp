package com.example.tagging;

public class Tag {
	
	private long id;
	private String tagName;
	private String creator;
	private String description;
	
	public Tag(long id, String tagName, String creator, String description){
		this.id = id;
		this.tagName = tagName;
		this.creator = creator;
		this.description = description;		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
}