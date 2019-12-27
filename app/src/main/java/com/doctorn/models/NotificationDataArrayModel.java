package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class NotificationDataArrayModel {

	@SerializedName("title_ar")
	private String titleAr;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("user_id")
	private Object userId;

	@SerializedName("description_en")
	private String descriptionEn;

	@SerializedName("read_at")
	private Object readAt;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("title_en")
	private String titleEn;

	@SerializedName("id")
	private int id;

	@SerializedName("description_ar")
	private String descriptionAr;

	public void setTitleAr(String titleAr){
		this.titleAr = titleAr;
	}

	public String getTitleAr(){
		return titleAr;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(Object userId){
		this.userId = userId;
	}

	public Object getUserId(){
		return userId;
	}

	public void setDescriptionEn(String descriptionEn){
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionEn(){
		return descriptionEn;
	}

	public void setReadAt(Object readAt){
		this.readAt = readAt;
	}

	public Object getReadAt(){
		return readAt;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setTitleEn(String titleEn){
		this.titleEn = titleEn;
	}

	public String getTitleEn(){
		return titleEn;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDescriptionAr(String descriptionAr){
		this.descriptionAr = descriptionAr;
	}

	public String getDescriptionAr(){
		return descriptionAr;
	}

	@Override
 	public String toString(){
		return 
			"NotificationDataArrayModel{" +
			"title_ar = '" + titleAr + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",description_en = '" + descriptionEn + '\'' + 
			",read_at = '" + readAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",title_en = '" + titleEn + '\'' + 
			",id = '" + id + '\'' + 
			",description_ar = '" + descriptionAr + '\'' + 
			"}";
		}
}