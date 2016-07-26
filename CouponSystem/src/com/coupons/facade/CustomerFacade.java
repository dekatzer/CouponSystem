package com.coupons.facade;

import java.util.ArrayList;
import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.beans.Customer;
import com.coupons.dbdao.CouponDBDao;
import com.coupons.dbdao.CustomerDBDao;
import com.coupons.exceptions.DaoException;

public class CustomerFacade implements CouponClientFacade {
	private CustomerDBDao custDBDao;
	private CouponDBDao   coupDBDao;
	
	public CustomerFacade ()
	{
		
	}

	@Override
	public CouponClientFacade login(String custName, String password, clientType type) throws DaoException {
		
		if(custDBDao.login(custName, password)==true&&type.equals(clientType.CUSTOMER)){
			
			return new CustomerFacade();
		}else{
		return null;
		}
		}	

	
	public void purchaseCoupon(Customer customer,Coupon coupon) throws DaoException
	{
		int amount=coupon.getAmount();
		if (amount>0){
			coupon.setAmount(amount-1);
			custDBDao.purchaseCoupon(customer, coupon);
					}
		else {
			throw new DaoException("No coupons for sale");
		}
	}	
	public List<Coupon> getAllPurchasedCoupons(long id ) throws DaoException
	{
		
		
		
		return custDBDao.getCoupons(id);
		
	}
	public List<Coupon> getAllPurchasedCouponsByType(CouponType type ) throws DaoException
	{
		List<Coupon> coupons =  new ArrayList<>();
		for(Coupon coupon :coupDBDao.getCouponByType(type))
		{
			if(coupon.getType().equals(type)){
				coupons.add(coupon);
		}
		}
		return coupons;
		
	}
	public List<Coupon> getAllPurchasedCouponsByPrice(long price,long id) throws DaoException
	{
		return coupDBDao.getCouponByPrice(price, id);
	}
	
	
	
}
