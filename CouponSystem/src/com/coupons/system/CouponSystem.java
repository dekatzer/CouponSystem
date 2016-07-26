package com.coupons.system;

import com.coupons.facade.*;


public class CouponSystem {
	private static CouponSystem instance = new CouponSystem();
	
	private CouponSystem()
	{
		// Maybe create daos here,
		// Maybe create pool

		// Launch the DailyExpirationTask thread
		
		
	}
	
	public static CouponSystem getInstance()
	{
//		if (instance == null)
//		{
//			instance = new CouponSystem();
//		}
		
		return instance;
	}
	
	public AdminFacade loginAsAdmin(String name, String password)
	{
		return null;
	}
	
	public CustomerFacade loginAsCustomer(String name, String password)
	{
		return null;
	}
	
	public CompanyFacade loginAsCompany(String name, String password)
	{
		return null;
	}
	
	
	
	
}
