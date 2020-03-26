package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class DoWithdrawRequestModel{

	@SerializedName("message")
	private String message;

	@SerializedName("withdraw_request")
	private WithdrawRequest withdrawRequest;

	@SerializedName("status")
	private boolean status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setWithdrawRequest(WithdrawRequest withdrawRequest){
		this.withdrawRequest = withdrawRequest;
	}

	public WithdrawRequest getWithdrawRequest(){
		return withdrawRequest;
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
			"DoWithdrawRequestModel{" + 
			"message = '" + message + '\'' + 
			",withdraw_request = '" + withdrawRequest + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}