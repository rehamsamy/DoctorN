package com.doctorn.models;

public class ConditionsModel{
	private String descAr;
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
			"ConditionsModel{" + 
			"desc_ar = '" + descAr + '\'' + 
			",desc_en = '" + descEn + '\'' + 
			"}";
		}
}
