package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class DoctorModel{

	@SerializedName("name")
	private String name;

	@SerializedName("specialization")
	private String specialization;

	@SerializedName("id")
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSpecialization(String specialization){
		this.specialization = specialization;
	}

	public String getSpecialization(){
		return specialization;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DoctorModel{" + 
			"name = '" + name + '\'' + 
			",specialization = '" + specialization + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}