package com.doctorn.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class AvailableSessionModel{

	@SerializedName("next7days")
	private List<Next7daysItem> next7days;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setNext7days(List<Next7daysItem> next7days){
		this.next7days = next7days;
	}

	public List<Next7daysItem> getNext7days(){
		return next7days;
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
			"AvailableSessionModel{" + 
			"next7days = '" + next7days + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}