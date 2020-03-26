package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class FavoriteDoctorsModel{

	@SerializedName("message")
	private String message;

	@SerializedName("Favorites")
	private Favorites favorites;

	@SerializedName("Favorite")
	private FavoriteDataArrayModel favoriteDataArrayModel;

	@SerializedName("status")
	private boolean status;

	@SerializedName("error")
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public FavoriteDataArrayModel getFavoriteDataArrayModel() {
		return favoriteDataArrayModel;
	}

	public void setFavoriteDataArrayModel(FavoriteDataArrayModel favoriteDataArrayModel) {
		this.favoriteDataArrayModel = favoriteDataArrayModel;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setFavorites(Favorites favorites){
		this.favorites = favorites;
	}

	public Favorites getFavorites(){
		return favorites;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"FavoriteDoctorsModel{" + 
			"message = '" + message + '\'' + 
			",favorites = '" + favorites + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}