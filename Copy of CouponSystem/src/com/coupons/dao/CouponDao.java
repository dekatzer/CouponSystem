package com.coupons.dao;

import java.util.Collection;

import com.coupons.beans.Coupon;

import com.coupons.beans.CouponType;

import com.coupons.exceptions.DaoException;

public interface CouponDao {


	public long createCoupon(Coupon coupon) throws DaoException;
	public void removeCoupon(Coupon coupon) throws DaoException;
	void updateCoupon(Coupon coupon) throws DaoException;
	Coupon getCouponById(long id)throws DaoException;
	Collection<Coupon> getAllCoupon() throws DaoException;
	Collection<Coupon> getCouponByType(CouponType type) throws DaoException;

	

}