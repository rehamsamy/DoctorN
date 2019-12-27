package com.doctorn.models;


import android.icu.text.IDNA;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel implements Parcelable{

	@SerializedName("message")
	private String message;

	@SerializedName("messages")
	private  String messages;

	@SerializedName("user")
	private User user;

	@SerializedName("card info")
    CardInfoModel cardInfoModel;

	@SerializedName("status")
	private boolean status;

	@SerializedName("doctors")
    private  List<DoctorModel> doctorModels;


	@SerializedName("token")
	private String token;

	@SerializedName("Termsandconditions")
	private  ConditionsModel conditionsModel;

	@SerializedName("privacy")
	private  PrivacyModel privacyModel;

	@SerializedName("errors")
	private ErrorModel errorModel;

	@SerializedName("notifications")
	private List<NotificationModel> notificationModel;

	@SerializedName("doctorinformation")
	private DoctorInfoModel doctorInfo;


	public List<DoctorModel> getDoctorModels() {
        return doctorModels;
    }

    public void setDoctorModels(List<DoctorModel> doctorModels) {
        this.doctorModels = doctorModels;
    }



	public List<NotificationModel> getNotificationModel() {
		return notificationModel;
	}

	public void setNotificationModel(List<NotificationModel> notificationModel) {
		this.notificationModel = notificationModel;
	}



	protected UserModel(Parcel in) {
		message = in.readString();
		messages = in.readString();
		user = in.readParcelable(User.class.getClassLoader());
		status = in.readByte() != 0;
		token = in.readString();
	}

	public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
		@Override
		public UserModel createFromParcel(Parcel in) {
			return new UserModel(in);
		}

		@Override
		public UserModel[] newArray(int size) {
			return new UserModel[size];
		}
	};

	public DoctorInfoModel getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfoModel doctorInfo) {
		this.doctorInfo = doctorInfo;
	}


    public CardInfoModel getCardInfoModel() {
        return cardInfoModel;
    }

    public void setCardInfoModel(CardInfoModel cardInfoModel) {
        this.cardInfoModel = cardInfoModel;
    }

    public ErrorModel getErrorModel() {
		return errorModel;
	}

	public void setErrorModel(ErrorModel errorModel) {
		this.errorModel = errorModel;
	}

	public PrivacyModel getPrivacyModel() {
        return privacyModel;
    }

    public void setPrivacyModel(PrivacyModel privacyModel) {
        this.privacyModel = privacyModel;
    }

    public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public ConditionsModel getConditionsModel() {
		return conditionsModel;
	}

	public void setConditionsModel(ConditionsModel conditionsModel) {
		this.conditionsModel = conditionsModel;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"UserModel{" + 
			"message = '" + message + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(message);
		dest.writeString(messages);
		dest.writeParcelable(user, flags);
		dest.writeByte((byte) (status ? 1 : 0));
		dest.writeString(token);
	}
}