package com.doctorn.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class WorkDaysModel{

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    @SerializedName("data")
    private List<DaysArrayModelItem> daysArrayModel;

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

    public void setDaysArrayModel(List<DaysArrayModelItem> daysArrayModel){
        this.daysArrayModel = daysArrayModel;
    }

    public List<DaysArrayModelItem> getDaysArrayModel(){
        return daysArrayModel;
    }

    @Override
    public String toString(){
        return
                "WorkDaysModel{" +
                        "message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        ",daysArrayModel = '" + daysArrayModel + '\'' +
                        "}";
    }
}