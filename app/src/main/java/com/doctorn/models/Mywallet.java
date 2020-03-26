package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class Mywallet{

    @SerializedName("wallet_id")
    private int walletId;

    @SerializedName("balance")
    private int balance;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("created_at")
    private String createdAt;

    public void setWalletId(int walletId){
        this.walletId = walletId;
    }

    public int getWalletId(){
        return walletId;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public int getBalance(){
        return balance;
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

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    @Override
    public String toString(){
        return
                "Mywallet{" +
                        "wallet_id = '" + walletId + '\'' +
                        ",balance = '" + balance + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        "}";
    }
}