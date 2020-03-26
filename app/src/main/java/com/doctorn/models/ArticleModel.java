package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class ArticleModel{

	@SerializedName("image")
	private String image;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("comment_no")
	private int commentNo;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("likes_no")
	private int likesNo;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setCommentNo(int commentNo){
		this.commentNo = commentNo;
	}

	public int getCommentNo(){
		return commentNo;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setLikesNo(int likesNo){
		this.likesNo = likesNo;
	}

	public int getLikesNo(){
		return likesNo;
	}

	@Override
 	public String toString(){
		return 
			"ArticleModel{" + 
			"image = '" + image + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",comment_no = '" + commentNo + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",body = '" + body + '\'' + 
			",likes_no = '" + likesNo + '\'' + 
			"}";
		}
}