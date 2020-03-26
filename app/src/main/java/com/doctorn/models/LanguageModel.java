package com.doctorn.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class LanguageModel{

	@SerializedName("data")
	private List<LanguageModelListItem> languageModelList;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setLanguageModelList(List<LanguageModelListItem> languageModelList){
		this.languageModelList = languageModelList;
	}

	public List<LanguageModelListItem> getLanguageModelList(){
		return languageModelList;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
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
			"LanguageModel{" + 
			"languageModelList = '" + languageModelList + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}