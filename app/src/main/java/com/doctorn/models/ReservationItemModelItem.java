package com.doctorn.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ReservationItemModelItem implements Parcelable {

	@SerializedName("DoctorTotalRate")
	private String doctorTotalRate;

	@SerializedName("show")
	private int show;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("languages_ar")
	private List<String> languagesAr;

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("specialization_id")
	private int specializationId;

	@SerializedName("languages_en")
	private List<String> languagesEn;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("graduation_universty")
	private String graduationUniversty;

	@SerializedName("consultation_duration")
	private int consultationDuration;

	@SerializedName("id")
	private int id;

	@SerializedName("payment_method")
	private String paymentMethod;

	@SerializedName("email")
	private String email;

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("Account_Activation")
	private String accountActivation;

	@SerializedName("work_start_time")
	private String workStartTime;

	@SerializedName("coupon")
	private String coupon;

	@SerializedName("profession_license")
	private String professionLicense;

	@SerializedName("languages")
	private String languages;

	@SerializedName("graduation_year")
	private String graduationYear;

	@SerializedName("degree")
	private String degree;

	@SerializedName("work_end_time")
	private String workEndTime;

	@SerializedName("consultation_price")
	private int consultationPrice;

	@SerializedName("reservation_id")
	private int reservationId;

	@SerializedName("workdays_en")
	private List<String> workdaysEn;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("reservation_datetime")
	private String reservationDatetime;

	@SerializedName("paid_amount")
	private String paidAmount;

	@SerializedName("name")
	private String name;

	@SerializedName("workdays_ar")
	private List<String> workdaysAr;

	@SerializedName("work_days")
	private String workDays;

	protected ReservationItemModelItem(Parcel in) {
		doctorTotalRate = in.readString();
		show = in.readInt();
		createdAt = in.readString();
		languagesAr = in.createStringArrayList();
		doctorId = in.readInt();
		specializationId = in.readInt();
		languagesEn = in.createStringArrayList();
		updatedAt = in.readString();
		graduationUniversty = in.readString();
		consultationDuration = in.readInt();
		id = in.readInt();
		paymentMethod = in.readString();
		email = in.readString();
		transactionId = in.readString();
		accountActivation = in.readString();
		workStartTime = in.readString();
		coupon = in.readString();
		professionLicense = in.readString();
		languages = in.readString();
		graduationYear = in.readString();
		degree = in.readString();
		workEndTime = in.readString();
		consultationPrice = in.readInt();
		reservationId = in.readInt();
		workdaysEn = in.createStringArrayList();
		userId = in.readInt();
		reservationDatetime = in.readString();
		paidAmount = in.readString();
		name = in.readString();
		workdaysAr = in.createStringArrayList();
		workDays = in.readString();
	}

	public static final Creator<ReservationItemModelItem> CREATOR = new Creator<ReservationItemModelItem>() {
		@Override
		public ReservationItemModelItem createFromParcel(Parcel in) {
			return new ReservationItemModelItem(in);
		}

		@Override
		public ReservationItemModelItem[] newArray(int size) {
			return new ReservationItemModelItem[size];
		}
	};

	public void setDoctorTotalRate(String doctorTotalRate){
		this.doctorTotalRate = doctorTotalRate;
	}

	public String getDoctorTotalRate(){
		return doctorTotalRate;
	}

	public void setShow(int show){
		this.show = show;
	}

	public int getShow(){
		return show;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setLanguagesAr(List<String> languagesAr){
		this.languagesAr = languagesAr;
	}

	public List<String> getLanguagesAr(){
		return languagesAr;
	}

	public void setDoctorId(int doctorId){
		this.doctorId = doctorId;
	}

	public int getDoctorId(){
		return doctorId;
	}

	public void setSpecializationId(int specializationId){
		this.specializationId = specializationId;
	}

	public int getSpecializationId(){
		return specializationId;
	}

	public void setLanguagesEn(List<String> languagesEn){
		this.languagesEn = languagesEn;
	}

	public List<String> getLanguagesEn(){
		return languagesEn;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setGraduationUniversty(String graduationUniversty){
		this.graduationUniversty = graduationUniversty;
	}

	public String getGraduationUniversty(){
		return graduationUniversty;
	}

	public void setConsultationDuration(int consultationDuration){
		this.consultationDuration = consultationDuration;
	}

	public int getConsultationDuration(){
		return consultationDuration;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setAccountActivation(String accountActivation){
		this.accountActivation = accountActivation;
	}

	public String getAccountActivation(){
		return accountActivation;
	}

	public void setWorkStartTime(String workStartTime){
		this.workStartTime = workStartTime;
	}

	public String getWorkStartTime(){
		return workStartTime;
	}

	public void setCoupon(String coupon){
		this.coupon = coupon;
	}

	public String getCoupon(){
		return coupon;
	}

	public void setProfessionLicense(String professionLicense){
		this.professionLicense = professionLicense;
	}

	public String getProfessionLicense(){
		return professionLicense;
	}

	public void setLanguages(String languages){
		this.languages = languages;
	}

	public String getLanguages(){
		return languages;
	}

	public void setGraduationYear(String graduationYear){
		this.graduationYear = graduationYear;
	}

	public String getGraduationYear(){
		return graduationYear;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setWorkEndTime(String workEndTime){
		this.workEndTime = workEndTime;
	}

	public String getWorkEndTime(){
		return workEndTime;
	}

	public void setConsultationPrice(int consultationPrice){
		this.consultationPrice = consultationPrice;
	}

	public int getConsultationPrice(){
		return consultationPrice;
	}

	public void setReservationId(int reservationId){
		this.reservationId = reservationId;
	}

	public int getReservationId(){
		return reservationId;
	}

	public void setWorkdaysEn(List<String> workdaysEn){
		this.workdaysEn = workdaysEn;
	}

	public List<String> getWorkdaysEn(){
		return workdaysEn;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setReservationDatetime(String reservationDatetime){
		this.reservationDatetime = reservationDatetime;
	}

	public String getReservationDatetime(){
		return reservationDatetime;
	}

	public void setPaidAmount(String paidAmount){
		this.paidAmount = paidAmount;
	}

	public String getPaidAmount(){
		return paidAmount;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setWorkdaysAr(List<String> workdaysAr){
		this.workdaysAr = workdaysAr;
	}

	public List<String> getWorkdaysAr(){
		return workdaysAr;
	}

	public void setWorkDays(String workDays){
		this.workDays = workDays;
	}

	public String getWorkDays(){
		return workDays;
	}

	@Override
 	public String toString(){
		return 
			"ReservationItemModelItem{" + 
			"doctorTotalRate = '" + doctorTotalRate + '\'' + 
			",show = '" + show + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",languages_ar = '" + languagesAr + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",specialization_id = '" + specializationId + '\'' + 
			",languages_en = '" + languagesEn + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",graduation_universty = '" + graduationUniversty + '\'' + 
			",consultation_duration = '" + consultationDuration + '\'' + 
			",id = '" + id + '\'' + 
			",payment_method = '" + paymentMethod + '\'' + 
			",email = '" + email + '\'' + 
			",transaction_id = '" + transactionId + '\'' + 
			",account_Activation = '" + accountActivation + '\'' + 
			",work_start_time = '" + workStartTime + '\'' + 
			",coupon = '" + coupon + '\'' + 
			",profession_license = '" + professionLicense + '\'' + 
			",languages = '" + languages + '\'' + 
			",graduation_year = '" + graduationYear + '\'' + 
			",degree = '" + degree + '\'' + 
			",work_end_time = '" + workEndTime + '\'' + 
			",consultation_price = '" + consultationPrice + '\'' + 
			",reservation_id = '" + reservationId + '\'' + 
			",workdays_en = '" + workdaysEn + '\'' + 
			",user_id = '" + userId + '\'' + 
			",reservation_datetime = '" + reservationDatetime + '\'' + 
			",paid_amount = '" + paidAmount + '\'' + 
			",name = '" + name + '\'' + 
			",workdays_ar = '" + workdaysAr + '\'' + 
			",work_days = '" + workDays + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(doctorTotalRate);
		parcel.writeInt(show);
		parcel.writeString(createdAt);
		parcel.writeStringList(languagesAr);
		parcel.writeInt(doctorId);
		parcel.writeInt(specializationId);
		parcel.writeStringList(languagesEn);
		parcel.writeString(updatedAt);
		parcel.writeString(graduationUniversty);
		parcel.writeInt(consultationDuration);
		parcel.writeInt(id);
		parcel.writeString(paymentMethod);
		parcel.writeString(email);
		parcel.writeString(transactionId);
		parcel.writeString(accountActivation);
		parcel.writeString(workStartTime);
		parcel.writeString(coupon);
		parcel.writeString(professionLicense);
		parcel.writeString(languages);
		parcel.writeString(graduationYear);
		parcel.writeString(degree);
		parcel.writeString(workEndTime);
		parcel.writeInt(consultationPrice);
		parcel.writeInt(reservationId);
		parcel.writeStringList(workdaysEn);
		parcel.writeInt(userId);
		parcel.writeString(reservationDatetime);
		parcel.writeString(paidAmount);
		parcel.writeString(name);
		parcel.writeStringList(workdaysAr);
		parcel.writeString(workDays);
	}
}