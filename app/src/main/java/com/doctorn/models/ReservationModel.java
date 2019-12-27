package com.doctorn.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ReservationModel{

	@SerializedName("Account_Activation")
	private String accountActivation;

	@SerializedName("work_start_time")
	private String workStartTime;

	@SerializedName("workingdays")
	private List<String> workingdays;

	@SerializedName("work_end_time")
	private String workEndTime;

	@SerializedName("consultation_duration")
	private int consultationDuration;

	@SerializedName("consultation_price")
	private int consultationPrice;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setAccountActivation(String accountActivation){
		this.accountActivation = accountActivation;
	}

	public String getAccountActivation(){
		return accountActivation;
	}

	public void setWorkStartTime(String workStartTime){
		this.workStartTime = workStartTime;
	}

	public String getWorkStartTime(){
		return workStartTime;
	}

	public void setWorkingdays(List<String> workingdays){
		this.workingdays = workingdays;
	}

	public List<String> getWorkingdays(){
		return workingdays;
	}

	public void setWorkEndTime(String workEndTime){
		this.workEndTime = workEndTime;
	}

	public String getWorkEndTime(){
		return workEndTime;
	}

	public void setConsultationDuration(int consultationDuration){
		this.consultationDuration = consultationDuration;
	}

	public int getConsultationDuration(){
		return consultationDuration;
	}

	public void setConsultationPrice(int consultationPrice){
		this.consultationPrice = consultationPrice;
	}

	public int getConsultationPrice(){
		return consultationPrice;
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
			"ReservationModel{" + 
			"account_Activation = '" + accountActivation + '\'' + 
			",work_start_time = '" + workStartTime + '\'' + 
			",workingdays = '" + workingdays + '\'' + 
			",work_end_time = '" + workEndTime + '\'' + 
			",consultation_duration = '" + consultationDuration + '\'' + 
			",consultation_price = '" + consultationPrice + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}