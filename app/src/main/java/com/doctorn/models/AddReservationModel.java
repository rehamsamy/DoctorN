package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class AddReservationModel{

	@SerializedName("reservation")
	private Addreservationitem addreservationitem;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("Payment_response")
	private PaymentResponse paymentResponse;

	public void setAddreservationitem(Addreservationitem addreservationitem){
		this.addreservationitem = addreservationitem;
	}

	public Addreservationitem getAddreservationitem(){
		return addreservationitem;
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

	public void setPaymentResponse(PaymentResponse paymentResponse){
		this.paymentResponse = paymentResponse;
	}

	public PaymentResponse getPaymentResponse(){
		return paymentResponse;
	}

	@Override
 	public String toString(){
		return 
			"AddReservationModel{" + 
			"addreservationitem = '" + addreservationitem + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",payment_response = '" + paymentResponse + '\'' + 
			"}";
		}
}