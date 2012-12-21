package com.wufulin.orm;

public class UserSimilar {

	private int userId;
	private double similar;
	
	public UserSimilar(int userId,double similar){
		this.userId=userId;
		this.similar=similar;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getSimilar() {
		return similar;
	}
	public void setSimilar(double similar) {
		this.similar = similar;
	}
	
	
}
