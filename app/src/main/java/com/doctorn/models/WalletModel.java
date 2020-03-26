package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class WalletModel{

    @SerializedName("mywallet")
    private Mywallet mywallet;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setMywallet(Mywallet mywallet){
        this.mywallet = mywallet;
    }

    public Mywallet getMywallet(){
        return mywallet;
    }

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

    @Override
    public String toString(){
        return
                "WalletModel{" +
                        "mywallet = '" + mywallet + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}