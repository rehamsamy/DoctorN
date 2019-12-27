package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class AllArticleModel{

	@SerializedName("messages")
	private String messages;

	@SerializedName("articles")
	private Articles articles;

	@SerializedName("status")
	private boolean status;

	public void setMessages(String messages){
		this.messages = messages;
	}

	public String getMessages(){
		return messages;
	}

	public void setArticles(Articles articles){
		this.articles = articles;
	}

	public Articles getArticles(){
		return articles;
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
			"AllArticleModel{" + 
			"messages = '" + messages + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}