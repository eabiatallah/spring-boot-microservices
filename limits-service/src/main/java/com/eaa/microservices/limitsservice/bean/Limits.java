package com.eaa.microservices.limitsservice.bean;

public class Limits {

	private int min;
	private int max;
	private String password;

	public Limits() {
	}

	public Limits(int min, int max, String password) {
		super();
		this.min = min;
		this.max = max;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
