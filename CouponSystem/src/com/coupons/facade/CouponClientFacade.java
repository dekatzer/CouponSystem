package com.coupons.facade;

import com.coupons.exceptions.DaoException;

public interface CouponClientFacade {
	
	public CouponClientFacade login(String name,String password,clientType type) throws DaoException;

	
}
