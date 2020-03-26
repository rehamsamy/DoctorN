package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class WithdrawModelItemItem{

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("amount")
	private int amount;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"WithdrawModelItemItem{" + 
			"doctor_id = '" + doctorId + '\'' + 
			",amount = '" + amount + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}