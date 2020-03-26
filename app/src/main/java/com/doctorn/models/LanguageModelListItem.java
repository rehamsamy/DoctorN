package com.doctorn.models;

import com.google.gson.annotations.SerializedName;


public class LanguageModelListItem{

	@SerializedName("language_en_name")
	private String languageEnName;

	@SerializedName("language_ar_name")
	private String languageArName;

	@SerializedName("language_id")
	private int languageId;

	public void setLanguageEnName(String languageEnName){
		this.languageEnName = languageEnName;
	}

	public String getLanguageEnName(){
		return languageEnName;
	}

	public void setLanguageArName(String languageArName){
		this.languageArName = languageArName;
	}

	public String getLanguageArName(){
		return languageArName;
	}

	public void setLanguageId(int languageId){
		this.languageId = languageId;
	}

	public int getLanguageId(){
		return languageId;
	}

	@Override
 	public String toString(){
		return 
			"LanguageModelListItem{" + 
			"language_en_name = '" + languageEnName + '\'' + 
			",language_ar_name = '" + languageArName + '\'' + 
			",language_id = '" + languageId + '\'' + 
			"}";
		}
}