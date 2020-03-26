package com.doctorn.models;

import com.google.gson.annotations.SerializedName;

public class FavoriteDataArrayModel{

	@SerializedName("article_id")
	private Object articleId;

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("favorite_type")
	private String favoriteType;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("favorite_id")
	private int favoriteId;

	@SerializedName("article")
	private  ArticleModel articleModel;

	@SerializedName("doctorinfo")
	private  DoctorItemModel doctorInfoModel;


	public DoctorItemModel getDoctorInfoModel() {
		return doctorInfoModel;
	}

	public void setDoctorInfoModel(DoctorItemModel doctorInfoModel) {
		this.doctorInfoModel = doctorInfoModel;
	}

	public ArticleModel getArticleModel() {
		return articleModel;
	}

	public void setArticleModel(ArticleModel articleModel) {
		this.articleModel = articleModel;
	}

	public void setArticleId(Object articleId){
		this.articleId = articleId;
	}

	public Object getArticleId(){
		return articleId;
	}

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setFavoriteType(String favoriteType){
		this.favoriteType = favoriteType;
	}

	public String getFavoriteType(){
		return favoriteType;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setFavoriteId(int favoriteId){
		this.favoriteId = favoriteId;
	}

	public int getFavoriteId(){
		return favoriteId;
	}

	@Override
 	public String toString(){
		return 
			"FavoriteDataArrayModel{" + 
			"article_id = '" + articleId + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",favorite_type = '" + favoriteType + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",favorite_id = '" + favoriteId + '\'' + 
			"}";
		}
}