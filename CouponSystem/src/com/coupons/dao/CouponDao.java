package com.coupons.dao;

import com.coupons.beans.Coupon;
import com.coupons.exceptions.DaoException;

public interface CouponDao {
	public void createCoupon(Coupon coupon) throws DaoException;
	public void removeCoupon(Coupon coupon) throws DaoException;
	void updateCoupon(Coupon coupon) throws DaoException;
	Coupon getCouponById(long id)throws DaoException;
	

}