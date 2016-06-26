package com.coupons.dao;

import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.Customer;
import com.coupons.exceptions.DaoException;

public interface CustomerDao {
	
	public void createCustomer(Customer c) throws DaoException;
	public void removeCustomer(Customer c) throws DaoException;
	public void updateCustomer(Customer c) throws DaoException;
	public Customer getCustomer(long id) throws DaoException;
	public List<Customer> getAllCustomers() throws DaoException;
	public List<Coupon> getCoupons()throws DaoException;
	public boolean login(String compName,String password)throws DaoException;
	
}
