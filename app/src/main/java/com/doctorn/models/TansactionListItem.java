package com.doctorn.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class TansactionListItem implements Parcelable {

	@SerializedName("transaction_id")
	private int transactionId;

	@SerializedName("transaction_date")
	private String transactionDate;

	@SerializedName("transaction_status")
	private String transactionStatus;

	@SerializedName("bank_sender_name")
	private String bankSenderName;

	@SerializedName("transaction_amount")
	private String transactionAmount;

	@SerializedName("bank_transfer_reference_number")
	private String bankTransferReferenceNumber;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("transaction_method")
	private String transactionMethod;

	@SerializedName("transaction_type")
	private String transactionType;

	@SerializedName("transaction_reason")
	private String transactionReason;

	@SerializedName("reservation_id")
	private int reservationId;

	@SerializedName("bank_acc_number")
	private String bankAccNumber;

	@SerializedName("bank_receipt_link")
	private String bankReceiptLink;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("credit_transaction_id")
	private Object creditTransactionId;

	@SerializedName("bank_name")
	private String bankName;

	protected TansactionListItem(Parcel in) {
		transactionId = in.readInt();
		transactionDate = in.readString();
		transactionStatus = in.readString();
		bankSenderName = in.readString();
		transactionAmount = in.readString();
		bankTransferReferenceNumber = in.readString();
		createdAt = in.readString();
		transactionMethod = in.readString();
		transactionType = in.readString();
		transactionReason = in.readString();
		reservationId = in.readInt();
		bankAccNumber = in.readString();
		bankReceiptLink = in.readString();
		updatedAt = in.readString();
		userId = in.readInt();
		bankName = in.readString();
	}

	public static final Creator<TansactionListItem> CREATOR = new Creator<TansactionListItem>() {
		@Override
		public TansactionListItem createFromParcel(Parcel in) {
			return new TansactionListItem(in);
		}

		@Override
		public TansactionListItem[] newArray(int size) {
			return new TansactionListItem[size];
		}
	};

	public void setTransactionId(int transactionId){
		this.transactionId = transactionId;
	}

	public int getTransactionId(){
		return transactionId;
	}

	public void setTransactionDate(String transactionDate){
		this.transactionDate = transactionDate;
	}

	public String getTransactionDate(){
		return transactionDate;
	}

	public void setTransactionStatus(String transactionStatus){
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionStatus(){
		return transactionStatus;
	}

	public void setBankSenderName(String bankSenderName){
		this.bankSenderName = bankSenderName;
	}

	public String getBankSenderName(){
		return bankSenderName;
	}

	public void setTransactionAmount(String transactionAmount){
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionAmount(){
		return transactionAmount;
	}

	public void setBankTransferReferenceNumber(String bankTransferReferenceNumber){
		this.bankTransferReferenceNumber = bankTransferReferenceNumber;
	}

	public String getBankTransferReferenceNumber(){
		return bankTransferReferenceNumber;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTransactionMethod(String transactionMethod){
		this.transactionMethod = transactionMethod;
	}

	public String getTransactionMethod(){
		return transactionMethod;
	}

	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}

	public String getTransactionType(){
		return transactionType;
	}

	public void setTransactionReason(String transactionReason){
		this.transactionReason = transactionReason;
	}

	public String getTransactionReason(){
		return transactionReason;
	}

	public void setReservationId(int reservationId){
		this.reservationId = reservationId;
	}

	public int getReservationId(){
		return reservationId;
	}

	public void setBankAccNumber(String bankAccNumber){
		this.bankAccNumber = bankAccNumber;
	}

	public String getBankAccNumber(){
		return bankAccNumber;
	}

	public void setBankReceiptLink(String bankReceiptLink){
		this.bankReceiptLink = bankReceiptLink;
	}

	public String getBankReceiptLink(){
		return bankReceiptLink;
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

	public void setCreditTransactionId(Object creditTransactionId){
		this.creditTransactionId = creditTransactionId;
	}

	public Object getCreditTransactionId(){
		return creditTransactionId;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	@Override
 	public String toString(){
		return 
			"TansactionListItem{" + 
			"transaction_id = '" + transactionId + '\'' + 
			",transaction_date = '" + transactionDate + '\'' + 
			",transaction_status = '" + transactionStatus + '\'' + 
			",bank_sender_name = '" + bankSenderName + '\'' + 
			",transaction_amount = '" + transactionAmount + '\'' + 
			",bank_transfer_reference_number = '" + bankTransferReferenceNumber + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",transaction_method = '" + transactionMethod + '\'' + 
			",transaction_type = '" + transactionType + '\'' + 
			",transaction_reason = '" + transactionReason + '\'' + 
			",reservation_id = '" + reservationId + '\'' + 
			",bank_acc_number = '" + bankAccNumber + '\'' + 
			",bank_receipt_link = '" + bankReceiptLink + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",credit_transaction_id = '" + creditTransactionId + '\'' + 
			",bank_name = '" + bankName + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(transactionId);
		parcel.writeString(transactionDate);
		parcel.writeString(transactionStatus);
		parcel.writeString(bankSenderName);
		parcel.writeString(transactionAmount);
		parcel.writeString(bankTransferReferenceNumber);
		parcel.writeString(createdAt);
		parcel.writeString(transactionMethod);
		parcel.writeString(transactionType);
		parcel.writeString(transactionReason);
		parcel.writeInt(reservationId);
		parcel.writeString(bankAccNumber);
		parcel.writeString(bankReceiptLink);
		parcel.writeString(updatedAt);
		parcel.writeInt(userId);
		parcel.writeString(bankName);
	}
}