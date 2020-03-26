package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class RegisterDoctorModel{

	@SerializedName("doctorinformation")
	private Doctorinformation doctorinformation;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setDoctorinformation(Doctorinformation doctorinformation){
		this.doctorinformation = doctorinformation;
	}

	public Doctorinformation getDoctorinformation(){
		return doctorinformation;
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
			"RegisterDoctorModel{" + 
			"doctorinformation = '" + doctorinformation + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}