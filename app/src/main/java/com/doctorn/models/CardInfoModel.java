package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class CardInfoModel{

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("check_number")
	private String checkNumber;

	@SerializedName("card_number")
	private String cardNumber;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("exp_date")
	private String expDate;

	@SerializedName("name_in_card")
	private String nameInCard;

	@SerializedName("id")
	private int id;

	@SerializedName("card_type")
	private String cardType;

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setCheckNumber(String checkNumber){
		this.checkNumber = checkNumber;
	}

	public String getCheckNumber(){
		return checkNumber;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
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

	public void setExpDate(String expDate){
		this.expDate = expDate;
	}

	public String getExpDate(){
		return expDate;
	}

	public void setNameInCard(String nameInCard){
		this.nameInCard = nameInCard;
	}

	public String getNameInCard(){
		return nameInCard;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCardType(String cardType){
		this.cardType = cardType;
	}

	public String getCardType(){
		return cardType;
	}

	@Override
 	public String toString(){
		return 
			"CardInfoModel{" + 
			"doctor_id = '" + doctorId + '\'' + 
			",check_number = '" + checkNumber + '\'' + 
			",card_number = '" + cardNumber + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",exp_date = '" + expDate + '\'' + 
			",name_in_card = '" + nameInCard + '\'' + 
			",id = '" + id + '\'' + 
			",card_type = '" + cardType + '\'' + 
			"}";
		}
}