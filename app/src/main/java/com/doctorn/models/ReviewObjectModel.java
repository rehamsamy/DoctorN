package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class ReviewObjectModel{

	@SerializedName("reservation_id")
	private String reservationId;

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("user_review")
	private String userReview;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("user_rate")
	private String userRate;

	public void setReservationId(String reservationId){
		this.reservationId = reservationId;
	}

	public String getReservationId(){
		return reservationId;
	}

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setUserReview(String userReview){
		this.userReview = userReview;
	}

	public String getUserReview(){
		return userReview;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUserRate(String userRate){
		this.userRate = userRate;
	}

	public String getUserRate(){
		return userRate;
	}

	@Override
 	public String toString(){
		return 
			"ReviewObjectModel{" + 
			"reservation_id = '" + reservationId + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",user_review = '" + userReview + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",user_rate = '" + userRate + '\'' + 
			"}";
		}
}