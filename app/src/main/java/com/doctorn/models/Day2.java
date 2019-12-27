package com.doctorn.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Day2{

	@SerializedName("date")
	private String date;

	@SerializedName("sessions24hours")
	private List<String> sessions24hours;

	@SerializedName("sessions")
	private List<String> sessions;

	@SerializedName("message")
	private String message;

	@SerializedName("day")
	private String day;

	@SerializedName("status")
	private boolean status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setSessions24hours(List<String> sessions24hours){
		this.sessions24hours = sessions24hours;
	}

	public List<String> getSessions24hours(){
		return sessions24hours;
	}

	public void setSessions(List<String> sessions){
		this.sessions = sessions;
	}

	public List<String> getSessions(){
		return sessions;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getDay(){
		return day;
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
			"Day2{" + 
			"date = '" + date + '\'' + 
			",sessions24hours = '" + sessions24hours + '\'' + 
			",sessions = '" + sessions + '\'' + 
			",message = '" + message + '\'' + 
			",day = '" + day + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}