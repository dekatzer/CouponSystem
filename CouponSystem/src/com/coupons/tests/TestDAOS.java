package com.coupons.tests;

import com.coupons.beans.Customer;
import com.coupons.dao.CustomerDao;
import com.coupons.dao.db.CustomerDBDao;
import com.coupons.exceptions.DaoException;

public class TestDAOS {
	public static void main(String[] args) throws DaoException
	{
		CustomerDao custDao = new CustomerDBDao();
		Customer c1 = new Customer("Moshe", 6, "whatever");
		
		// create
		custDao.createCustomer(c1);
		
		
		// get
		Customer c2 = custDao.getCustomer(6);
		System.out.println(c2);
	}
}
