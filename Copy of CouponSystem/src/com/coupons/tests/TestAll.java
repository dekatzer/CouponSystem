package com.coupons.tests;

import com.coupons.facade.CustomerFacade;
import com.coupons.system.CouponSystem;

public class TestAll {
	public static void main(String[] args) {
		// Test Customer
		
		CustomerFacade cFac = CouponSystem.getInstance().
				loginAsCustomer("Moshe", "Musa");
		// test the facade
	}
}
