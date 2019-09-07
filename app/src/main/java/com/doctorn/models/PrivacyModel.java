package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class PrivacyModel{

	@SerializedName("desc_ar")
	private String descAr;

	@SerializedName("desc_en")
	private String descEn;

	public void setDescAr(String descAr){
		this.descAr = descAr;
	}

	public String getDescAr(){
		return descAr;
	}

	public void setDescEn(String descEn){
		this.descEn = descEn;
	}

	public String getDescEn(){
		return descEn;
	}

	@Override
 	public String toString(){
		return 
			"PrivacyModel{" + 
			"desc_ar = '" + descAr + '\'' + 
			",desc_en = '" + descEn + '\'' + 
			"}";
		}
}