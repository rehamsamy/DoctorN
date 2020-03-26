package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class Addreservationitem{

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("doctor_id")
	private String doctorId;

	@SerializedName("coupon")
	private Object coupon;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("reservation_datetime")
	private String reservationDatetime;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("paid_amount")
	private String paidAmount;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("payment_method")
	private String paymentMethod;

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setDoctorId(String doctorId){
		this.doctorId = doctorId;
	}

	public String getDoctorId(){
		return doctorId;
	}

	public void setCoupon(Object coupon){
		this.coupon = coupon;
	}

	public Object getCoupon(){
		return coupon;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setReservationDatetime(String reservationDatetime){
		this.reservationDatetime = reservationDatetime;
	}

	public String getReservationDatetime(){
		return reservationDatetime;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPaidAmount(String paidAmount){
		this.paidAmount = paidAmount;
	}

	public String getPaidAmount(){
		return paidAmount;
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

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	@Override
 	public String toString(){
		return 
			"Addreservationitem{" + 
			"transaction_id = '" + transactionId + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",coupon = '" + coupon + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",reservation_datetime = '" + reservationDatetime + '\'' + 
			",user_id = '" + userId + '\'' + 
			",paid_amount = '" + paidAmount + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",payment_method = '" + paymentMethod + '\'' + 
			"}";
		}
}