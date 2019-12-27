package com.doctorn.models;

import com.google.gson.annotations.SerializedName;


public class UserspecialtiesItem{

	@SerializedName("specialization_en_name")
	private String specialtiesNameEn;



	@SerializedName("id")
	private int id;

	@SerializedName("specialization_ar_name")
	private String specialtiesNameAr;

	public void setSpecialtiesNameEn(String specialtiesNameEn){
		this.specialtiesNameEn = specialtiesNameEn;
	}

	public String getSpecialtiesNameEn(){
		return specialtiesNameEn;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSpecialtiesNameAr(String specialtiesNameAr){
		this.specialtiesNameAr = specialtiesNameAr;
	}

	public String getSpecialtiesNameAr(){
		return specialtiesNameAr;
	}

	@Override
 	public String toString(){
		return 
			"UserspecialtiesItem{" + 
			"specialties_name_en = '" + specialtiesNameEn + '\'' + 
			",id = '" + id + '\'' + 
			",specialties_name_ar = '" + specialtiesNameAr + '\'' + 
			"}";
		}
}