package com.coupons.facade;

import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.dbdao.CompanyDBDao;
import com.coupons.dbdao.CouponDBDao;
import com.coupons.dbdao.CustomerDBDao;
import com.coupons.exceptions.DaoException;

public class CompanyFacade implements CouponClientFacade {
	
	private CompanyDBDao  compDBDao;
	private CustomerDBDao custDBDao;
	private CouponDBDao   coupDBDao;
	
	public CompanyFacade ()
	{
		
	}
	
	@Override
	public CompanyFacade login(String compName, String password, clientType type) throws DaoException {
	if(compDBDao.login(compName, password)==true&&type.equals(clientType.COMPANY)){
		
		return new CompanyFacade();
	}else{
	return null;
	}
	}
	
	public void createCoupon (Coupon coupon) throws DaoException
	{
		coupDBDao.createCoupon(coupon);
	}
	
	public void removeCoupon (Coupon coupon) throws DaoException
	{
		coupDBDao.removeCoupon(coupon);
	}

	public void updateCoupon (Coupon coupon) throws DaoException
	{
		coupDBDao.updateCoupon(coupon);
	}
	
	public Coupon getCoupon(long id) throws DaoException
	{
		return coupDBDao.getCouponById(id);
	}
	
	public List<Coupon> getAllCoupon() throws DaoException
	{
		return coupDBDao.getAllCoupon();
		
	}
	
	public List<Coupon> getCouponsByType(CouponType type) throws DaoException
	{
	    return coupDBDao.getCouponByType(type);	
	}
}
