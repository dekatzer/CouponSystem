package com.coupons.dao;

import java.util.List;

import com.coupons.beans.*;
import com.coupons.exceptions.DaoException;

public interface CompanyDao {

	public void createCompany(Company company) throws DaoException;
	public void removeCompany(Company company) throws DaoException;
	public void updateCompany(Company company) throws DaoException;
	public Company getCompany(long id) throws DaoException;
	public List<Company> getAllCompanies() throws DaoException;
	public List<Coupon> getCoupons(long id)throws DaoException;
	public boolean login(String compName,String password)throws DaoException;
}
