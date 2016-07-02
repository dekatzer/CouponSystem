package com.coupons.tests;

import java.sql.SQLException;

import com.coupons.beans.Company;
import com.coupons.dbdao.CompanyDBDao;
import com.coupons.exceptions.DaoException;

public class TestCompany {

	public static void main(String[] args) {
		
			
		CompanyDBDao compDBDao= new CompanyDBDao();
		
		for(int i=0;i<10;i++){
			
			Company comp = new Company (i,"comp"+i,"1234"+i,("comp"+i+"@comp"+i+".local"));
			System.out.println(comp);
			try {
			compDBDao.createCompany(comp);
			} catch (DaoException e) {	
		    	 DaoException.showErrorMessage(e);
		}
			
			

	}

}
}
