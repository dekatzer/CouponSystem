package com.coupons.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.dao.CouponDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.Pool;

public class CouponDBDao implements CouponDao{
	
	@Override
	public void createCoupon(Coupon coupon) throws DaoException {
		// get connection from pool
		Connection con = Pool.getConnection();
		try {
			
		
			String sql = 
					"INSERT INTO coupon VALUES (coupon_id,title,start_date,end_date,amount,type,message,price,image)VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setLong(1, coupon.getId());
			stat.setString(2, coupon.getTitle());
			stat.setDate(3,(java.sql.Date)coupon.getStartDate());
			stat.setDate(3,(java.sql.Date)coupon.getEndDate());
			stat.setInt(3,coupon.getAmount());
			stat.setString(3,coupon.getType().toString());
			stat.setString(3,coupon.getMessage());
			stat.setDouble(3,coupon.getPrice());
			stat.setString(3,coupon.getImage());
			
			stat.executeUpdate();
		} catch (SQLException e) {
			// Translation exception
			throw new DaoException("Coupon was not created ", e);
		}
	
	}
		@Override
		public void removeCoupon(Coupon coupon) throws DaoException{
			
			Connection con = Pool.getConnection();
			String sql ="DELETE FROM coupon"
					+ " WHERE title=?";
			PreparedStatement stat;
			
			try {
			stat=con.prepareStatement(sql);
			stat.setString(1, coupon.getTitle());
			stat.executeQuery();
	        } catch (SQLException e) {
				throw new DaoException("Coupon was not removed "+e);
			}
		}
		
	@Override
		public void updateCoupon(Coupon coupon) throws DaoException{
			Connection con = Pool.getConnection();
			String sql ="UPDATE coupon "
					+ "SET end_date=?,amount=?,type=?,message=?,price=?,image=? "
					+ "WHERE title=? ";
			PreparedStatement stat;
			try {
				stat=con.prepareStatement(sql);
			
			stat.setDate(1,(java.sql.Date) coupon.getEndDate());
			stat.setInt(2, coupon.getAmount());
			stat.setString(3, coupon.getType().toString());
			stat.setString(3, coupon.getMessage());
			stat.setDouble(3, coupon.getPrice());
			stat.setString(3, coupon.getImage());
			
			stat.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("Update was not successful"+e);
			}		
			
		}
	
	@Override
	public Coupon getCouponById(long id)throws DaoException{
		Coupon coupon =null;
		String title = null;
		Date start_date = null;
		Date end_date = null;
		Integer amount=null;
		CouponType type=null;
		Double price =null;
		String message=null;
		String image=null;
		
		Connection con = Pool.getConnection();//setting up connections from class pool
		String sql = "SELECT * FROM coupon WHERE coupon_id=?";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);
		
		stat.setLong(1, id); //1 -first question mark, id- variable to replace ?
		ResultSet rs = stat.executeQuery();
		rs.next();//if not added return error and null WHY!!!!
		title=rs.getString(1);// number of column
		start_date=rs.getDate("Start Date");
		end_date=rs.getDate("End Date");
		amount=rs.getInt("Ammount");
		type=CouponType.valueOf(rs.getString("Type"));
		price=rs.getDouble("Price");
		message=rs.getString("Message");
		image=rs.getString("Image");
		coupon = new Coupon(id, title, start_date,
				end_date, type,amount, message, price, image);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("failed to retrieve from the database"+e);
		}
		// obj company gets its values 
		
		return coupon;
		
	}
		

		
	
		
		
		
//		finally{
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}


}