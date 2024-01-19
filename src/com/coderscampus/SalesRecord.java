package com.coderscampus;

public class SalesRecord {
	
	private String model;
	private String date;
	private String month;
	private int unitsSold;
	private double revenue;
	
	public SalesRecord(String date, String model, int unitsSold, double revenue) {
        this.date = date;
        this.model = model;
        this.unitsSold = unitsSold;
        this.revenue = revenue;
    }
	
	public String getModel() {
	return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	

}
	
//	public Tesla (String month, int year,)
//	this
	
	
	