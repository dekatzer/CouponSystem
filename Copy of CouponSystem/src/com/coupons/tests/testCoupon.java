package com.coupons.tests;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.coupons.beans.*;
import com.coupons.dbdao.CouponDBDao;
import com.coupons.exceptions.DaoException;

public class testCoupon {
	public static void main(String[] args) throws DaoException {
		
		
		
		Coupon c1 = new Coupon("Coupon1",LocalDate.now(),LocalDate.of(2018, 7, 29), 6,CouponType.HEALTH,  "Car Coupon", 19.90, "Users/MATH/Desktop/project/picture.jpg");
		Coupon c2 = new Coupon("Coupon2",LocalDate.now(),LocalDate.of(2017, 05, 15),7, CouponType.CAMPING, "Electronics Coupon", 89.90, "Users/MATH/Desktop/project/picture.jpg");
		Coupon c3 = new Coupon( "Coupon3",LocalDate.now(),LocalDate.of(2018,9 , 30),3, CouponType.FOOD,  "Food Coupon", 12.90, "Users/MATH/Desktop/project/picture.jpg");
		Coupon c4 = new Coupon("test", LocalDate.now(), LocalDate.of(2017, 6, 17),9, CouponType.FOOD, "my message", 19.90, "Users/MATH/Desktop/project/picture.jpg");
		
		Set<Coupon> coupons = new HashSet<>();
		coupons.add(c1);
		coupons.add(c2);
		coupons.add(c3);
		
		Set<Coupon> coupons2 = new HashSet<>();
		coupons2.add(c4);
		
		CouponDBDao couponDB = new CouponDBDao();
		couponDB.createCoupon(c1);
		couponDB.createCoupon(c2);
		couponDB.createCoupon(c3);
		couponDB.createCoupon(c4);
		
		
		
		
		
		
	}
	
	  

}