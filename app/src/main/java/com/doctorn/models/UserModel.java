package com.doctorn.models;


import android.icu.text.IDNA;

import com.google.gson.annotations.SerializedName;

public class UserModel{

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private boolean status;

	@SerializedName("token")
	private String token;

	@SerializedName("Termsandconditions")
	private  ConditionsModel conditionsModel;

	@SerializedName("privacy")
    private  PrivacyModel privacyModel;

	@SerializedName("errors")
	private ErrorModel errorModel;

	public ErrorModel getErrorModel() {
		return errorModel;
	}

	public void setErrorModel(ErrorModel errorModel) {
		this.errorModel = errorModel;
	}

	public PrivacyModel getPrivacyModel() {
        return privacyModel;
    }

    public void setPrivacyModel(PrivacyModel privacyModel) {
        this.privacyModel = privacyModel;
    }

    public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public ConditionsModel getConditionsModel() {
		return conditionsModel;
	}

	public void setConditionsModel(ConditionsModel conditionsModel) {
		this.conditionsModel = conditionsModel;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"UserModel{" + 
			"message = '" + message + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}