package com.doctorn.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DoctorItemModel implements Parcelable {

	@SerializedName("DoctorTotalRate")
	private String doctorTotalRate;

	@SerializedName("show")
	private int show;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("languages_ar")
	private List<String> languagesAr;

	@SerializedName("specialization_en_name")
	private String specializationEnName;

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

	@SerializedName("email")
	private String email;

	@SerializedName("Account_Activation")
	private String accountActivation;

	@SerializedName("work_start_time")
	private String workStartTime;

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

	@SerializedName("workdays_en")
	private List<String> workdaysEn;

	@SerializedName("name")
	private String name;

	@SerializedName("specialization_ar_name")
	private String specializationArName;

	@SerializedName("workdays_ar")
	private List<String> workdaysAr;

	@SerializedName("work_days")
	private String workDays;

	protected DoctorItemModel(Parcel in) {
		doctorTotalRate = in.readString();
		show = in.readInt();
		createdAt = in.readString();
		languagesAr = in.createStringArrayList();
		specializationEnName = in.readString();
		doctorId = in.readInt();
		specializationId = in.readInt();
		languagesEn = in.createStringArrayList();
		updatedAt = in.readString();
		graduationUniversty = in.readString();
		consultationDuration = in.readInt();
		id = in.readInt();
		email = in.readString();
		accountActivation = in.readString();
		workStartTime = in.readString();
		professionLicense = in.readString();
		languages = in.readString();
		graduationYear = in.readString();
		degree = in.readString();
		workEndTime = in.readString();
		consultationPrice = in.readInt();
		workdaysEn = in.createStringArrayList();
		name = in.readString();
		specializationArName = in.readString();
		workdaysAr = in.createStringArrayList();
		workDays = in.readString();
	}

	public static final Creator<DoctorItemModel> CREATOR = new Creator<DoctorItemModel>() {
		@Override
		public DoctorItemModel createFromParcel(Parcel in) {
			return new DoctorItemModel(in);
		}

		@Override
		public DoctorItemModel[] newArray(int size) {
			return new DoctorItemModel[size];
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

	public void setSpecializationEnName(String specializationEnName){
		this.specializationEnName = specializationEnName;
	}

	public String getSpecializationEnName(){
		return specializationEnName;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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

	public void setWorkdaysEn(List<String> workdaysEn){
		this.workdaysEn = workdaysEn;
	}

	public List<String> getWorkdaysEn(){
		return workdaysEn;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSpecializationArName(String specializationArName){
		this.specializationArName = specializationArName;
	}

	public String getSpecializationArName(){
		return specializationArName;
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
			"DoctorItemModel{" + 
			"doctorTotalRate = '" + doctorTotalRate + '\'' + 
			",show = '" + show + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",languages_ar = '" + languagesAr + '\'' + 
			",specialization_en_name = '" + specializationEnName + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",specialization_id = '" + specializationId + '\'' + 
			",languages_en = '" + languagesEn + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",graduation_universty = '" + graduationUniversty + '\'' + 
			",consultation_duration = '" + consultationDuration + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",account_Activation = '" + accountActivation + '\'' + 
			",work_start_time = '" + workStartTime + '\'' + 
			",profession_license = '" + professionLicense + '\'' + 
			",languages = '" + languages + '\'' + 
			",graduation_year = '" + graduationYear + '\'' + 
			",degree = '" + degree + '\'' + 
			",work_end_time = '" + workEndTime + '\'' + 
			",consultation_price = '" + consultationPrice + '\'' + 
			",workdays_en = '" + workdaysEn + '\'' + 
			",name = '" + name + '\'' + 
			",specialization_ar_name = '" + specializationArName + '\'' + 
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
		parcel.writeString(specializationEnName);
		parcel.writeInt(doctorId);
		parcel.writeInt(specializationId);
		parcel.writeStringList(languagesEn);
		parcel.writeString(updatedAt);
		parcel.writeString(graduationUniversty);
		parcel.writeInt(consultationDuration);
		parcel.writeInt(id);
		parcel.writeString(email);
		parcel.writeString(accountActivation);
		parcel.writeString(workStartTime);
		parcel.writeString(professionLicense);
		parcel.writeString(languages);
		parcel.writeString(graduationYear);
		parcel.writeString(degree);
		parcel.writeString(workEndTime);
		parcel.writeInt(consultationPrice);
		parcel.writeStringList(workdaysEn);
		parcel.writeString(name);
		parcel.writeString(specializationArName);
		parcel.writeStringList(workdaysAr);
		parcel.writeString(workDays);
	}
}