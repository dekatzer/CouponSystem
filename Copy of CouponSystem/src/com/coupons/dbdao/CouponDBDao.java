package com.coupons.dbdao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.dao.CouponDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.ConnectionPool;
import java.util.HashSet;
import java.util.List;

public class CouponDBDao implements CouponDao{
	
	@Override
	public long createCoupon(Coupon coupon) throws DaoException {
		long id=-1;
		ResultSet rs = null;
		// get connection from pool
		
		try {
			
		
			String sql = 
					"INSERT INTO coupon(title,start_date,end_date,amount,type,message,price,image) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			// setting values into the DB
			
			stat.setString(1, coupon.getTitle());
			stat.setDate(2,Date.valueOf(coupon.getStartDate()));
			stat.setDate(3,Date.valueOf(coupon.getEndDate()));
			stat.setInt(4,coupon.getAmount());
			stat.setString(5,coupon.getType().toString());
			stat.setString(6,coupon.getMessage());
			stat.setDouble(7,coupon.getPrice());
			stat.setString(8,coupon.getImage());
			
			stat.executeUpdate();
			rs = stat.getGeneratedKeys();
			rs.next();
			id = rs.getLong(1);
			coupon.setId(id);
		} catch (SQLException e) {
			
			throw new DaoException("Coupon was not created ", e);
		}
		
		return id;
	
	}
		@Override
		public void removeCoupon(Coupon coupon) throws DaoException{
			
		
			String sql ="DELETE FROM coupon"
					+ " WHERE title=?";
			
			
			try {
			PreparedStatement stat=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
			stat.setString(2, coupon.getTitle());
			stat.executeUpdate();
	        } catch (SQLException e) {
				throw new DaoException("Coupon was not removed "+e);
			}
		}
		
		
	@Override
		public void updateCoupon(Coupon coupon) throws DaoException{
			
			String sql ="UPDATE coupon "
					+ "SET end_date=?,type=?,amount=?,message=?,price=?,image=? "
					+ "WHERE title=? ";
			PreparedStatement stat;
			try {
				stat=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
			
			stat.setDate(4,Date.valueOf(coupon.getEndDate()));
			stat.setInt(5, coupon.getAmount());
			stat.setString(6, coupon.getType().toString());
			stat.setString(7, coupon.getMessage());
			stat.setDouble(8, coupon.getPrice());
			stat.setString(9, coupon.getImage());
			
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
		
		 
		String sql = "SELECT * FROM coupon WHERE coupon_id=?";
		PreparedStatement stat;
		try {
			stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		
		
		ResultSet rs = stat.executeQuery();
		rs.next();//if not added return error and null WHY!!!!
		title=rs.getString(2);// number of column
		start_date=rs.getDate("start_date");
		end_date=rs.getDate("end_date");
		amount=rs.getInt("ammount");
		type=CouponType.valueOf(rs.getString("type"));
		price=rs.getDouble("price");
		message=rs.getString("message");
		image=rs.getString("image");
		coupon = new Coupon(id, title, start_date.toLocalDate(),
				end_date.toLocalDate(),amount, type, message, price, image);
		
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("failed to retrieve from the database"+e);
		}
		// obj company gets its values 
		
		return coupon;}

		
	
	
	@Override
	
	public Collection<Coupon> getAllCoupon() throws DaoException{
	
		
		String sql = "SELECT * FROM coupon";
		PreparedStatement stat;
			Collection <Coupon> coupons=new HashSet<>();
		try {
			stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		
		ResultSet rs = stat.executeQuery(sql);
		
	
		while (rs.next()){
			
					
					coupons.add(getCouponById(rs.getLong(1)));
	
					}
		
	} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		return coupons;
	}
		// obj company gets its values 
		
		@Override
		
		public List <Coupon> getCouponByType(CouponType type) throws DaoException{
		
			
			String sql = "SELECT * FROM coupon WHERE type=?";
			PreparedStatement stat;
			try {
				stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
				// requesting the type value from the DB
			stat.setString(7, type.name());
			// getting results from the DB
			ResultSet rs = stat.executeQuery();
			rs.next();//if not added return error and null WHY!!!!
			// create collection
			List <Coupon> coupons=new ArrayList<>();
			// get all the values from the DB
			while (rs.next()){
			
				coupons.add(getCouponById(rs.getLong(1)));
						}
			
			return coupons;}
			
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("failed to retrieve from the database"+e);
			}
		
	
	
		
	
		
		}
		

public void CloseConnection(PreparedStatement stat,Connection con,ResultSet rs) throws DaoException{
	
	if(stat!=null){
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
	



