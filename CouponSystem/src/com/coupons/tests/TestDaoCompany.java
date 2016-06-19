package com.coupons.tests;

import com.coupons.beans.Company;
import com.coupons.dao.CompanyDao;
import com.coupons.dbdao.CompanyDBDao;
import com.coupons.exceptions.DaoException;

public class TestDaoCompany {

	public static void main(String[] args) {
		CompanyDao compDao= new CompanyDBDao();
		Company comp1 = null;
		try {
			comp1 = compDao.getCompany(1);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(comp1);
		Company comp2=new Company(2,"comptest2","123456","comptest2@comptest.com");
		try {
			compDao.createCompany(comp2);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
