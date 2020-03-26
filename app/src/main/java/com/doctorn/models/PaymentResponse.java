package com.doctorn.models;


import com.google.gson.annotations.SerializedName;

public class PaymentResponse{

	@SerializedName("result")
	private String result;

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("response_code")
	private String responseCode;

	@SerializedName("amount")
	private String amount;

	@SerializedName("currency")
	private String currency;

	@SerializedName("pt_invoice_id")
	private Object ptInvoiceId;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("card_last_four_digits")
	private String cardLastFourDigits;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setResponseCode(String responseCode){
		this.responseCode = responseCode;
	}

	public String getResponseCode(){
		return responseCode;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setPtInvoiceId(Object ptInvoiceId){
		this.ptInvoiceId = ptInvoiceId;
	}

	public Object getPtInvoiceId(){
		return ptInvoiceId;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setCardLastFourDigits(String cardLastFourDigits){
		this.cardLastFourDigits = cardLastFourDigits;
	}

	public String getCardLastFourDigits(){
		return cardLastFourDigits;
	}

	@Override
 	public String toString(){
		return 
			"PaymentResponse{" + 
			"result = '" + result + '\'' + 
			",transaction_id = '" + transactionId + '\'' + 
			",response_code = '" + responseCode + '\'' + 
			",amount = '" + amount + '\'' + 
			",currency = '" + currency + '\'' + 
			",pt_invoice_id = '" + ptInvoiceId + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",card_last_four_digits = '" + cardLastFourDigits + '\'' + 
			"}";
		}
}