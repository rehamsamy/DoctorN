package com.doctorn.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DoctorItemModel implements Parcelable {

	@SerializedName("specialization_id")
	private int specializationId;

	@SerializedName("gender")
	private String gender;

	@SerializedName("name")
	private String name;

	@SerializedName("specialization_ar_name")
	private String specializationArName;

	@SerializedName("id")
	private int id;

	@SerializedName("specialization_en_name")
	private String specializationEnName;

	protected DoctorItemModel(Parcel in) {
		specializationId = in.readInt();
		gender = in.readString();
		name = in.readString();
		specializationArName = in.readString();
		id = in.readInt();
		specializationEnName = in.readString();
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

	public void setSpecializationId(int specializationId){
		this.specializationId = specializationId;
	}

	public int getSpecializationId(){
		return specializationId;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSpecializationEnName(String specializationEnName){
		this.specializationEnName = specializationEnName;
	}

	public String getSpecializationEnName(){
		return specializationEnName;
	}

	@Override
 	public String toString(){
		return 
			"DoctorItemModel{" + 
			"specialization_id = '" + specializationId + '\'' + 
			",gender = '" + gender + '\'' + 
			",name = '" + name + '\'' + 
			",specialization_ar_name = '" + specializationArName + '\'' + 
			",id = '" + id + '\'' + 
			",specialization_en_name = '" + specializationEnName + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(specializationId);
		dest.writeString(gender);
		dest.writeString(name);
		dest.writeString(specializationArName);
		dest.writeInt(id);
		dest.writeString(specializationEnName);
	}
}