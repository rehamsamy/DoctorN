package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class WithdrawModel{

	@SerializedName("requests")
	private Requests requests;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setRequests(Requests requests){
		this.requests = requests;
	}

	public Requests getRequests(){
		return requests;
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
			"WithdrawModel{" + 
			"requests = '" + requests + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}