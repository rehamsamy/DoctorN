package com.doctorn.models;

import com.google.gson.annotations.SerializedName;

public class NotificationModel{

	@SerializedName("message")
	private String message;

	@SerializedName("notifications")
	private Notifications notifications;

	@SerializedName("status")
	private boolean status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setNotifications(Notifications notifications){
		this.notifications = notifications;
	}

	public Notifications getNotifications(){
		return notifications;
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
			"NotificationModel{" + 
			"message = '" + message + '\'' + 
			",notifications = '" + notifications + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}