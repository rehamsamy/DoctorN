package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class ReservationsModel{

	@SerializedName("reservations")
	private Reservations reservations;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setReservations(Reservations reservations){
		this.reservations = reservations;
	}

	public Reservations getReservations(){
		return reservations;
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
			"ReservationsModel{" + 
			"reservations = '" + reservations + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}