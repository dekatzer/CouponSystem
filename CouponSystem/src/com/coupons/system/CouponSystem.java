package com.coupons.system;

import com.coupons.dao.CompanyDao;
import com.coupons.dao.CouponDao;
import com.coupons.dao.CustomerDao;
import com.coupons.dbdao.CompanyDBDao;
import com.coupons.dbdao.CouponDBDao;
import com.coupons.dbdao.CustomerDBDao;
import com.coupons.exceptions.DaoException;
import com.coupons.expirationtask.DailyExpirationTask;
import com.coupons.facade.AdminFacade;
import com.coupons.facade.CompanyFacade;
import com.coupons.facade.CustomerFacade;
import com.coupons.pool.ConnectionPool;
import com.coupons.facade.*;
public class CouponSystem {
	private static CouponSystem instance = new CouponSystem();
	private CompanyDao compDao = null;
	private CustomerDao custDao = null;
	private CouponDao couponDao = null;
	private DailyExpirationTask dailyTask = null;
	private AdminFacade adm;
	private CustomerFacade cust;
	private CompanyFacade comp;
	ConnectionPool con;
	private CouponSystem() 
	{
		CompanyDao compDao=new CompanyDBDao();
		CustomerDao custDao=new CustomerDBDao();
		CouponDao couponDao=new CouponDBDao();
		dailyTask.start();
		try {
			con.getConnection();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CouponSystem getInstance()
	{
	if (instance == null)
		{
			instance = new CouponSystem();
		}
		
		return instance;
	}
	
	public void closeCon(){
		try {
			con.closeAllConnections();
			dailyTask.stop();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AdminFacade loginAsAdmin(String name, String password)
	{
		
		return adm.login(name, password) ;
	}
	
	public CustomerFacade loginAsCustomer(String name, String password)
	{
		return null;
	}
	
	public CompanyFacade loginAsCompany(String compName, String password,ClientType clienttype)
	{
		return comp.login(compName, password, clienttype);
	}
	
	
	
	
}
