package com.coupons.expirationtask;

import java.time.LocalDate;

import com.coupons.beans.Company;
import com.coupons.beans.Coupon;

import com.coupons.dao.CouponDao;

import com.coupons.exceptions.DaoException;

public class DailyExpirationTask extends Thread {
	


    
       private CouponDao coupdao=null;
       
       private Coupon coupon=null;
       private Company company=null;
         boolean run=true;
       //Constructor
        public DailyExpirationTask(CouponDao coupdao)
        {
            this.coupdao=coupdao;
        }
        
        // Function
        @Override
        public void run() {
            
      
          ;
			while (run = true) {
            	
              company.getCoupons(); 
              if (coupon.getEndDate().isBefore(LocalDate.now())){
            	  try {
					coupdao.removeCoupon(coupon);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	  
              }
                try {
                    sleep(Integer.valueOf(60000));
                    
                } catch (InterruptedException ex) {
                   
                    run = false;
                }
            }
            
        }
     


}

