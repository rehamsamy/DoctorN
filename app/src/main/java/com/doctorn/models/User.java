package com.doctorn.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User  {

	@SerializedName("is_information_found")
	private boolean isInformationFound;

	@SerializedName("user_type")
	private String userType;

	@SerializedName("name")
	private String name;

	@SerializedName("user_phone")
	private String userPhone;

	@SerializedName("user_age")
	private String userAge;

	@SerializedName("user_gender")
	private String userGender;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	protected User(Parcel in) {
		isInformationFound = in.readByte() != 0;
		userType = in.readString();
		name = in.readString();
		userPhone = in.readString();
		userAge = in.readString();
		userGender = in.readString();
		id = in.readInt();
		email = in.readString();
	}



	public void setIsInformationFound(boolean isInformationFound){
		this.isInformationFound = isInformationFound;
	}

	public boolean isIsInformationFound(){
		return isInformationFound;
	}

	public void setUserType(String userType){
		this.userType = userType;
	}

	public String getUserType(){
		return userType;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}

	public String getUserPhone(){
		return userPhone;
	}

	public void setUserAge(String userAge){
		this.userAge = userAge;
	}

	public String getUserAge(){
		return userAge;
	}

	public void setUserGender(String userGender){
		this.userGender = userGender;
	}

	public String getUserGender(){
		return userGender;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"is_information_found = '" + isInformationFound + '\'' + 
			",user_type = '" + userType + '\'' + 
			",name = '" + name + '\'' + 
			",user_phone = '" + userPhone + '\'' + 
			",user_age = '" + userAge + '\'' + 
			",user_gender = '" + userGender + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}

}