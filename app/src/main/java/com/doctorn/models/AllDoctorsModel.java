package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class AllDoctorsModel{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("doctors")
	private DoctorObjectModel doctorObjectModel;

	@SerializedName("doctorinfo")
	DoctorInfoModel doctorInfoModel;

	public DoctorObjectModel getDoctorObjectModel() {
		return doctorObjectModel;
	}

	public void setDoctorObjectModel(DoctorObjectModel doctorObjectModel) {
		this.doctorObjectModel = doctorObjectModel;
	}

	public DoctorInfoModel getDoctorInfoModel() {
		return doctorInfoModel;
	}

	public void setDoctorInfoModel(DoctorInfoModel doctorInfoModel) {
		this.doctorInfoModel = doctorInfoModel;
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
			"AllDoctorsModel{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}