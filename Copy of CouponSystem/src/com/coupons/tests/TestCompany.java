package com.coupons.tests;

import java.util.List;

import com.coupons.beans.Company;
import com.coupons.dbdao.CompanyDBDao;
import com.coupons.exceptions.DaoException;


public class TestCompany {

	public static void main(String[] args) throws DaoException {
		CompanyDBDao compDBDao= new CompanyDBDao();
		
		//Add companies			
		
		for(int i=1;i<11;i++){			
			Company comp = new Company (i,"comp"+i,"1234"+i,("comp"+i+"@comp"+i+".local"));
			System.out.println(comp);
		try {
			compDBDao.createCompany(comp);
			} catch (DaoException e) {	
		    	 DaoException.showErrorMessage(e);
		}			

	}
        //Remove companies
		
		Company comp;
		comp=compDBDao.getCompany(1);
		System.out.println(comp);
		
		//Update company
		comp=new Company(1,"update1","update2","update3@comp1.local");
		compDBDao.updateCompany(comp);
		System.out.println(comp);
		
		//get all companies list
		List<Company> companyList;
		companyList=compDBDao.getAllCompanies();
		for(int i = 0; i < companyList.size(); i++) {   
		    System.out.print(companyList.get(i)+ "\t"+"\n");
		}  
		
		
}
}