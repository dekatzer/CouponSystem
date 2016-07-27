package com.coupons.facade;

import java.util.List;

import com.coupons.beans.*;
import com.coupons.dbdao.*;
import com.coupons.exceptions.DaoException;

public class AdminFacade implements CouponClientFacade {

	private CompanyDBDao  compDBDao;
	private CustomerDBDao custDBDao;
	//private CouponDBDao   coupDBDao;
	
	public AdminFacade()
	{
		
		
	}

	@Override
	public AdminFacade login(String name, String password, clientType type) {
      if(name.equals("admin")&&password.equals("1234")&&type.equals(clientType.ADMIN))
      {	  
		return  new AdminFacade();
      }else{
	return null;}
	}
	
	public void createCompany(Company company) throws DaoException
	{
		compDBDao.createCompany(company);
	}
	
	public void removeCompany(Company company) throws DaoException
	{
		compDBDao.removeCompanyCoupon(company);
		compDBDao.removeCompany(company);
	}
	public void updateCompany(Company company) throws DaoException
	{
		compDBDao.updateCompany(company);
	}
	
	public Company getCompany(long id) throws DaoException
	{
		
		return compDBDao.getCompany(id);
	}
	
	public List<Company> getAllCompanies() throws DaoException
	{
		return compDBDao.getAllCompanies();
	}
	
	public void createCustomer(Customer customer) throws DaoException
	{
		custDBDao.createCustomer(customer);
	}
	public void removeCustomer(Customer customer) throws DaoException
	{
		custDBDao.removeCustomer(customer);
	}
	
	public void  updateCustomer(Customer customer) throws DaoException 
	{
	    custDBDao.updateCustomer(customer);	
	}
	
	public Customer getCustomer(long id) throws DaoException
	{
		return custDBDao.getCustomer(id);
	}
    
	public List<Customer> getAllCustomerss() throws DaoException
	{
		return custDBDao.getAllCustomers();
	}
}
