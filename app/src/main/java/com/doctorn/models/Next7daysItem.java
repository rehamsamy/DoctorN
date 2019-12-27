package com.doctorn.models;


import com.google.gson.annotations.SerializedName;


public class Next7daysItem{

	@SerializedName("Day7")
	private Day7 day7;

	@SerializedName("Day6")
	private Day6 day6;

	@SerializedName("Day5")
	private Day5 day5;

	@SerializedName("Day4")
	private Day4 day4;

	@SerializedName("Day3")
	private Day3 day3;

	@SerializedName("Day2")
	private Day2 day2;

	@SerializedName("Day1")
	private Day1 day1;

	public void setDay7(Day7 day7){
		this.day7 = day7;
	}

	public Day7 getDay7(){
		return day7;
	}

	public void setDay6(Day6 day6){
		this.day6 = day6;
	}

	public Day6 getDay6(){
		return day6;
	}

	public void setDay5(Day5 day5){
		this.day5 = day5;
	}

	public Day5 getDay5(){
		return day5;
	}

	public void setDay4(Day4 day4){
		this.day4 = day4;
	}

	public Day4 getDay4(){
		return day4;
	}

	public void setDay3(Day3 day3){
		this.day3 = day3;
	}

	public Day3 getDay3(){
		return day3;
	}

	public void setDay2(Day2 day2){
		this.day2 = day2;
	}

	public Day2 getDay2(){
		return day2;
	}

	public void setDay1(Day1 day1){
		this.day1 = day1;
	}

	public Day1 getDay1(){
		return day1;
	}

	@Override
 	public String toString(){
		return 
			"Next7daysItem{" + 
			"day7 = '" + day7 + '\'' + 
			",day6 = '" + day6 + '\'' + 
			",day5 = '" + day5 + '\'' + 
			",day4 = '" + day4 + '\'' + 
			",day3 = '" + day3 + '\'' + 
			",day2 = '" + day2 + '\'' + 
			",day1 = '" + day1 + '\'' + 
			"}";
		}
}