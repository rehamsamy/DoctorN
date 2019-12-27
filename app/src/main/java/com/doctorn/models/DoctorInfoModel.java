package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class DoctorInfoModel{

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

	@SerializedName("show")
	private int show;

	@SerializedName("work_end_time")
	private String workEndTime;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("consultation_price")
	private int consultationPrice;

	@SerializedName("doctor_id")
	private int doctorId;

	@SerializedName("specialization_id")
	private int specializationId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("graduation_universty")
	private String graduationUniversty;

	@SerializedName("consultation_duration")
	private int consultationDuration;

	@SerializedName("id")
	private int id;

	@SerializedName("work_days")
	private String workDays;

	@SerializedName("email")
	private String email;

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

	public void setShow(int show){
		this.show = show;
	}

	public int getShow(){
		return show;
	}

	public void setWorkEndTime(String workEndTime){
		this.workEndTime = workEndTime;
	}

	public String getWorkEndTime(){
		return workEndTime;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setConsultationPrice(int consultationPrice){
		this.consultationPrice = consultationPrice;
	}

	public int getConsultationPrice(){
		return consultationPrice;
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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setWorkDays(String workDays){
		this.workDays = workDays;
	}

	public String getWorkDays(){
		return workDays;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"DoctorInfoModel{" + 
			"account_Activation = '" + accountActivation + '\'' + 
			",work_start_time = '" + workStartTime + '\'' + 
			",profession_license = '" + professionLicense + '\'' + 
			",languages = '" + languages + '\'' + 
			",graduation_year = '" + graduationYear + '\'' + 
			",degree = '" + degree + '\'' + 
			",show = '" + show + '\'' + 
			",work_end_time = '" + workEndTime + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",consultation_price = '" + consultationPrice + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",specialization_id = '" + specializationId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",graduation_universty = '" + graduationUniversty + '\'' + 
			",consultation_duration = '" + consultationDuration + '\'' + 
			",id = '" + id + '\'' + 
			",work_days = '" + workDays + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}