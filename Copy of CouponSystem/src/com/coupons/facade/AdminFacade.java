package com.coupons.facade;

import java.util.Collection;
import java.util.List;

import com.coupons.beans.Company;
import com.coupons.beans.Coupon;
import com.coupons.beans.Customer;
import com.coupons.dao.CompanyDao;
import com.coupons.dao.CouponDao;
import com.coupons.dao.CustomerDao;

import com.coupons.exceptions.AdminFacadeException;
import com.coupons.exceptions.DaoException;


public class AdminFacade {
//Attributes
		//
	 
	
	  
	
		private CompanyDao compDao = null;
		private CustomerDao custDao = null;
		private CouponDao coupDao = null;
		private static final String PasswordAdmin = "polina";
	
	public AdminFacade() {
		
	}
//Functions
	//
	
	public  AdminFacade login (String name,String password){
		try {
		if(name.equals("admin")&& password.equals(PasswordAdmin)){
		return new AdminFacade();
		}
		
			throw new AdminFacadeException();
		} catch (AdminFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	//Functions
	
	public void createCompany(Company company) throws AdminFacadeException {
		try {
			compDao.createCompany(company);
			if(company.getCompName().equals("")){
				throw new AdminFacadeException("the field name is empty");
			}
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void removeCompany(Company company) throws AdminFacadeException {
		try {
			compDao.removeCompany(company);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new AdminFacadeException();
	}

	
	public void updateCompany(Company company) throws AdminFacadeException {
		try {
			compDao.updateCompany(company);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public Company getCompany(long id) throws DaoException {
		
		return compDao.getCompany(id);
	}


	public List<Company> getAllCompanies() throws DaoException {
		
		return compDao.getAllCompanies();
	}

	
	public void createCustomer(Customer c) throws DaoException {
		custDao.createCustomer(c);
		
	}

	
	public void removeCustomer(Customer c) throws DaoException {
	custDao.removeCustomer(c);
		
	}

	
	public void updateCustomer(Customer c) throws DaoException {
		custDao.updateCustomer(c);
		
	}

	
	public Customer getCustomer(long id) throws DaoException {
		
		return custDao.getCustomer(id);
	}

	
	public List<Customer> getAllCustomers() throws DaoException {
		
		return custDao.getAllCustomers();
	}


	public Collection <Coupon> getCoupons(long id) throws DaoException {
		
		return coupDao.getAllCoupon();
	}
	

	
}
