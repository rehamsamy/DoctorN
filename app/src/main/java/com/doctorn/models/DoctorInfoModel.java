package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class DoctorInfoModel{

	@SerializedName("doctor_id")
	private String doctorId;

	@SerializedName("profession_license")
	private String professionLicense;

	@SerializedName("languages")
	private String languages;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("graduation_year")
	private String graduationYear;

	@SerializedName("graduation_universty")
	private String graduationUniversty;

	@SerializedName("degree")
	private String degree;

	@SerializedName("specialization")
	private String specialization;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setDoctorId(String doctorId){
		this.doctorId = doctorId;
	}

	public String getDoctorId(){
		return doctorId;
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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setGraduationYear(String graduationYear){
		this.graduationYear = graduationYear;
	}

	public String getGraduationYear(){
		return graduationYear;
	}

	public void setGraduationUniversty(String graduationUniversty){
		this.graduationUniversty = graduationUniversty;
	}

	public String getGraduationUniversty(){
		return graduationUniversty;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setSpecialization(String specialization){
		this.specialization = specialization;
	}

	public String getSpecialization(){
		return specialization;
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

	@Override
 	public String toString(){
		return 
			"DoctorInfoModel{" + 
			"doctor_id = '" + doctorId + '\'' + 
			",profession_license = '" + professionLicense + '\'' + 
			",languages = '" + languages + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",graduation_year = '" + graduationYear + '\'' + 
			",graduation_universty = '" + graduationUniversty + '\'' + 
			",degree = '" + degree + '\'' + 
			",specialization = '" + specialization + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}