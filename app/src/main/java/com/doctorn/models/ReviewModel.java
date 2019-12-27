package com.doctorn.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ReviewModel{

	@SerializedName("reviews")
	private List<ReviewsItem> reviews;

	@SerializedName("review")
	private ReviewObjectModel review;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public ReviewObjectModel getReview() {
		return review;
	}

	public String getMessage() {
		return message;
	}

	public void setReview(ReviewObjectModel review) {
		this.review = review;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
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
			"ReviewModel{" + 
			"reviews = '" + reviews + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}