package com.doctorn.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class User implements Parcelable{

	@SerializedName("user_type")
	private String userType;

	@SerializedName("name")
	private String name;

	@SerializedName("user_phone")
	private String userPhone;

	@SerializedName("user_age")
	private int userAge;

	@SerializedName("user_gender")
	private String userGender;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	protected User(Parcel in) {
		userType = in.readString();
		name = in.readString();
		userPhone = in.readString();
		userAge = in.readInt();
		userGender = in.readString();
		id = in.readInt();
		email = in.readString();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

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

	public void setUserAge(int userAge){
		this.userAge = userAge;
	}

	public int getUserAge(){
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
			"user_type = '" + userType + '\'' + 
			",name = '" + name + '\'' + 
			",user_phone = '" + userPhone + '\'' + 
			",user_age = '" + userAge + '\'' + 
			",user_gender = '" + userGender + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userType);
		dest.writeString(name);
		dest.writeString(userPhone);
		dest.writeInt(userAge);
		dest.writeString(userGender);
		dest.writeInt(id);
		dest.writeString(email);
	}
}