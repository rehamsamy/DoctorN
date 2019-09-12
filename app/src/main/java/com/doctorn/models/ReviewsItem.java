package com.doctorn.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ReviewsItem implements Parcelable {

	@SerializedName("user_review")
	private String userReview;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("show")
	private int show;

	@SerializedName("active")
	private int active;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("rat_sum")
	private Object ratSum;

	@SerializedName("user_rate")
	private int userRate;

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("rat_count")
	private int ratCount;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	@SerializedName("doctor_name")
	private String doctorName;

	protected ReviewsItem(Parcel in) {
		userReview = in.readString();
		userName = in.readString();
		show = in.readInt();
		active = in.readInt();
		createdAt = in.readString();
		userRate = in.readInt();
		doctorId = in.readInt();
		updatedAt = in.readString();
		ratCount = in.readInt();
		userId = in.readInt();
		id = in.readInt();
		doctorName = in.readString();
	}

	public static final Creator<ReviewsItem> CREATOR = new Creator<ReviewsItem>() {
		@Override
		public ReviewsItem createFromParcel(Parcel in) {
			return new ReviewsItem(in);
		}

		@Override
		public ReviewsItem[] newArray(int size) {
			return new ReviewsItem[size];
		}
	};

	public void setUserReview(String userReview){
		this.userReview = userReview;
	}

	public String getUserReview(){
		return userReview;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setShow(int show){
		this.show = show;
	}

	public int getShow(){
		return show;
	}

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setRatSum(Object ratSum){
		this.ratSum = ratSum;
	}

	public Object getRatSum(){
		return ratSum;
	}

	public void setUserRate(int userRate){
		this.userRate = userRate;
	}

	public int getUserRate(){
		return userRate;
	}

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setRatCount(int ratCount){
		this.ratCount = ratCount;
	}

	public int getRatCount(){
		return ratCount;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDoctorName(String doctorName){
		this.doctorName = doctorName;
	}

	public String getDoctorName(){
		return doctorName;
	}

	@Override
 	public String toString(){
		return 
			"ReviewsItem{" + 
			"user_review = '" + userReview + '\'' + 
			",user_name = '" + userName + '\'' + 
			",show = '" + show + '\'' + 
			",active = '" + active + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",rat_sum = '" + ratSum + '\'' + 
			",user_rate = '" + userRate + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",rat_count = '" + ratCount + '\'' + 
			",user_id = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",doctor_name = '" + doctorName + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userReview);
		dest.writeString(userName);
		dest.writeInt(show);
		dest.writeInt(active);
		dest.writeString(createdAt);
		dest.writeInt(userRate);
		dest.writeInt(doctorId);
		dest.writeString(updatedAt);
		dest.writeInt(ratCount);
		dest.writeInt(userId);
		dest.writeInt(id);
		dest.writeString(doctorName);
	}
}