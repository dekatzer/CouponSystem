package com.coupons.dao;

import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.exceptions.DaoException;

public interface CouponDao {
	public void createCoupon(Coupon coupon) throws DaoException;
	public void removeCoupon(Coupon coupon) throws DaoException;
	public void updateCoupon(Coupon coupon) throws DaoException;
	public Coupon getCouponById(long id)throws DaoException;
	public List<Coupon> getAllCoupon() throws DaoException;
	public List<Coupon> getCouponByType(CouponType type)throws DaoException;
	public List<Coupon> getCouponByPrice(long price,long id)throws DaoException;
	
	

}