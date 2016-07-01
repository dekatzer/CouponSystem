package com.coupons.beans;

import java.time.LocalDate;
import java.sql.Date;

public class Coupon {
	//attributes
	private long id=0;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private String message;
	private double price;
	private String image;
	private CouponType type;
	//constructor
	public Coupon(){
		
	}

public Coupon(long id,String title,LocalDate start_date,LocalDate end_date,int amount,CouponType type,  String message, double price, String image){
	this.id=id;
	this.title=title;
	this.startDate=start_date;
	this.endDate=end_date;
	this.type=type;
	this.amount=amount;
	this.message=message;
	this.price=price;
	this.image=image;
	}
public Coupon(String title, LocalDate startDate, LocalDate endDate, int amount,CouponType type,  String message,
		double price, String image) {
	this.title = title;
	this.startDate = startDate;
	this.endDate = endDate;
	this.amount = amount;
	this.type = type;
	this.message = message;
	this.price = price;
	this.image = image;
}


	//getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
		
			}
		
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public CouponType getType() {
		return type;
	}
	public void setType(CouponType type) {
		this.type = type;
	}
	
	//to string
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", message=" + message + ", price=" + price +", type="+type+ ", image=" + image + "]";
	}
	
	
	
	

}
