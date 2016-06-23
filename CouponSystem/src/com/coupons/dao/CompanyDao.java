package com.coupons.dao;

import java.util.List;

import com.coupons.beans.Company;
import com.coupons.exceptions.DaoException;

public interface CompanyDao {

	public void createCompany(Company company) throws DaoException;
	public Company getCompany(long id) throws DaoException;
	public void updateCompany(Company company) throws DaoException;
	public void removeCompany(Company company) throws DaoException;
	
	public List<Company> getAllCompanies() throws DaoException;
}
