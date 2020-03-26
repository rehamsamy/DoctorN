package com.doctorn.models;

;
import com.google.gson.annotations.SerializedName;

public class DaysArrayModelItem{

    @SerializedName("day_ar_name")
    private String dayArName;

    @SerializedName("day_en_name")
    private String dayEnName;

    @SerializedName("day_id")
    private int dayId;

    public void setDayArName(String dayArName){
        this.dayArName = dayArName;
    }

    public String getDayArName(){
        return dayArName;
    }

    public void setDayEnName(String dayEnName){
        this.dayEnName = dayEnName;
    }

    public String getDayEnName(){
        return dayEnName;
    }

    public void setDayId(int dayId){
        this.dayId = dayId;
    }

    public int getDayId(){
        return dayId;
    }

    @Override
    public String toString(){
        return
                "DaysArrayModelItem{" +
                        "day_ar_name = '" + dayArName + '\'' +
                        ",day_en_name = '" + dayEnName + '\'' +
                        ",day_id = '" + dayId + '\'' +
                        "}";
    }
}