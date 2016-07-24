package com.coupons.beans;

import java.util.Collection;


public class Company {
	//attributes
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	//constructor
	public Company(){
		
	}
	public Company(long id, String compName, String password, String email) {
		
		this.id=id;
		this.compName=compName;
		this.password=password;
		this.email=email;
	}
	//getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	//to string
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		if (email == null) {
			if (other.email != null)
				return false;	
		} else if (!compName.equals(other.compName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		
	}
	return true;
	

}
}