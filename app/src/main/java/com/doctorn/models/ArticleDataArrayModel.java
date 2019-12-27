package com.doctorn.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ArticleDataArrayModel implements Parcelable {

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

	@SerializedName("messages")
	private  String message;

	@SerializedName("status")
	private  Boolean status;

	public String getMessage() {
		return message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	protected ArticleDataArrayModel(Parcel in) {
		image = in.readString();
		commentNo = in.readInt();
		id = in.readInt();
		title = in.readString();
		body = in.readString();
		likesNo = in.readInt();
	}

	public static final Creator<ArticleDataArrayModel> CREATOR = new Creator<ArticleDataArrayModel>() {
		@Override
		public ArticleDataArrayModel createFromParcel(Parcel in) {
			return new ArticleDataArrayModel(in);
		}

		@Override
		public ArticleDataArrayModel[] newArray(int size) {
			return new ArticleDataArrayModel[size];
		}
	};

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
			"ArticleDataArrayModel{" + 
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeInt(commentNo);
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(body);
		dest.writeInt(likesNo);
	}
}