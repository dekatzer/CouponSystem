package com.coupons.beans;

import java.util.Collection;

;

public class Customer 
{
	//
	// Attributes
	//
	private String name;
	private long id; 
	private String password;
	private Collection<Coupon> coupons;
	

	//
	// Constructor
	//
	// Java bean standard demands an empty constructor
	public Customer() {
		// NOT A MUST
	}
	
	public Customer(String name, long id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	//
	// Functions
	//
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	// Setters need more checks
	public void setPassword(String password) {
		// TODO: should we check?
		this.password = password;
	}
	
	// TODO: 
//	public void setId(long id) {
//		this.id = id;
//	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", id=" + id + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
	
	
	
	
 }
