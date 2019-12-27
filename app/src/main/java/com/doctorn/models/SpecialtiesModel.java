package com.doctorn.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class SpecialtiesModel{

	@SerializedName("specializations")
	private List<UserspecialtiesItem> userspecialties;

	@SerializedName("messages")
	private String messages;

	@SerializedName("status")
	private boolean status;

	public void setUserspecialties(List<UserspecialtiesItem> userspecialties){
		this.userspecialties = userspecialties;
	}

	public List<UserspecialtiesItem> getUserspecialties(){
		return userspecialties;
	}

	public void setMessages(String messages){
		this.messages = messages;
	}

	public String getMessages(){
		return messages;
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
			"SpecialtiesModel{" + 
			"userspecialties = '" + userspecialties + '\'' + 
			",messages = '" + messages + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}