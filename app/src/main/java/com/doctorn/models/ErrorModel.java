package com.doctorn.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ErrorModel{

	@SerializedName("user_phone")
	private List<String> userPhone;

	@SerializedName("email")
	private List<String> email;

	public void setUserPhone(List<String> userPhone){
		this.userPhone = userPhone;
	}

	public List<String> getUserPhone(){
		return userPhone;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ErrorModel{" + 
			"user_phone = '" + userPhone + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}