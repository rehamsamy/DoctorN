package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class MyFinancailModel{

	@SerializedName("Transactions")
	private Transactions transactions;


	@SerializedName("Bank_Transactions")
	private  BankTransaction bankTransaction;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setTransactions(Transactions transactions){
		this.transactions = transactions;
	}

	public Transactions getTransactions(){
		return transactions;
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
			"MyFinancailModel{" + 
			"transactions = '" + transactions + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}


	public BankTransaction getBankTransaction() {
		return bankTransaction;
	}

	public void setBankTransaction(BankTransaction bankTransaction) {
		this.bankTransaction = bankTransaction;
	}
}