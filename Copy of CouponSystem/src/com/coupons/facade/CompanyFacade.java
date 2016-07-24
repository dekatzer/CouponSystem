package com.coupons.facade;

import java.util.Collection;

import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.dao.CompanyDao;
import com.coupons.dao.CouponDao;

import com.coupons.exceptions.DaoException;

public class CompanyFacade implements CouponDao{

	private long compId;
	private String compName;
	private CompanyDao compDao = null;
	private CouponDao coupDao = null;

	
	public CompanyFacade() {
		// TODO Auto-generated constructor stub
	}

	public CompanyFacade login (String compName,String password,ClientType clienttype){
		if(clienttype.equals(ClientType.Company)){
			
			try {
				compDao.login(compName, password);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new CompanyFacade();
	}
	@Override
	public long createCoupon(Coupon coupon) throws DaoException {
		
		return coupDao.createCoupon(coupon);
	}

	@Override
	public void removeCoupon(Coupon coupon) throws DaoException {
		coupDao.removeCoupon(coupon);
		
	}

	@Override
	public void updateCoupon(Coupon coupon) throws DaoException {
		coupDao.updateCoupon(coupon);
		
	}

	@Override
	public Coupon getCouponById(long id) throws DaoException {
		
		return coupDao.getCouponById(id);
	}

	@Override
	public Collection<Coupon> getAllCoupon() throws DaoException {
		
		return coupDao.getAllCoupon();
	}

	@Override
	public Collection<Coupon> getCouponByType(CouponType type) throws DaoException {
	
		return coupDao.getCouponByType(type);
	}
	public long getCompId() {
		return compId;
	}

	public String getCompName() {
		return compName;
	}

}
