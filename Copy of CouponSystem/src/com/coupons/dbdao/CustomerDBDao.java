package com.coupons.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.beans.Customer;
import com.coupons.dao.CustomerDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.ConnectionPool;

public class CustomerDBDao implements CustomerDao
{
	PreparedStatement stat=null;
	 Connection con= null;
	 ResultSet rs=null;
	@Override
	public void createCustomer(Customer customer) throws DaoException {
	
	
		try {
			
		
			String sql = 
					"INSERT INTO customer VALUES (?,?,?)";
			PreparedStatement stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
			
			stat.setLong(1, customer.getId());
			stat.setString(2, customer.getName());
			stat.setString(3, customer.getPassword());
			
			stat.executeUpdate();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		

	}

	@Override
	public void removeCustomer(Customer customer) throws DaoException {
		
		String sql ="DELETE FROM company"
				+ " WHERE cust_name=?";
	
		
		try {
	    	stat=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
	    	stat.setString(1, customer.getName());
	    	stat.executeQuery();
      } catch (SQLException e) {
    	  DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
	}

	@Override
	public void updateCustomer(Customer customer) throws DaoException {
		String sql ="UPDATE customer "
				+ "SET password=? "
				+ "WHERE cust_name=? ";
		
		try {
			stat=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		
		stat.setString(1, customer.getPassword());
		stat.setString(2, customer.getName());
		stat.executeUpdate();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}	
		finally {CloseConnection(stat,con,rs);}
		}
	
	@Override
	public Customer getCustomer(long id) throws DaoException {
		//1.get connection
		Customer customer=null;
		
		String sql= "SELECT cust_name,password FROM customer WHERE cust_id=?";
		PreparedStatement stat=null;
		try {
		
		stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		stat.setLong(1, id);
		ResultSet rs=stat.executeQuery();
		rs.next();
		String cust_name=rs.getString(1);
		String password=rs.getString(2);
	    customer = new Customer(cust_name,id,password);
		
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		
		return customer;
	}
	
	@Override
	public List<Customer> getAllCustomers() throws DaoException {
		List<Customer> customers=new ArrayList<Customer>();
		
		String sql="SELECT * FROM customer ";
		
		try {
			stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		
		ResultSet rs = stat.executeQuery();
		while(rs.next()){
			customers.add(new Customer(rs.getString(2),rs.getLong(1),rs.getString(3)));
		}
		} catch (SQLException e) {
		
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		
		return customers;
	}

	@Override
	public List<Coupon> getCoupons(long id) throws DaoException {
		List<Coupon> couponsList= new ArrayList<>(); 
		Coupon coupon =null;
		String title = null;
        Date start_date = null;
		Date end_date = null;
		Integer amount=null;
		CouponType type=null;
		Double price =null;
		String message=null;
		String image=null;
		String sql="SELECT * FROM coupon,customer_coupon"
				+ "WHERE coupon.coupon_id=customer_coupon.coupon_id"
				+ "AND customer_coupon.cust_id=?";
		
		try {
			stat = ConnectionPool.getInstance().getConnection().prepareStatement(sql);
			stat.setLong(1, id);
		    ResultSet rs=stat.executeQuery();
		while(rs.next()){
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
			 couponsList.add(coupon);
		}
	
		
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		
		return couponsList;
	}

	@Override
	public boolean login(String custName, String password) throws DaoException {
		boolean check=false;
		
		String sql = "SELECT custName,password FROM company WHERE cust_name=? AND password=?";
		try {
		PreparedStatement stat=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		stat.setString(2, custName);
		stat.setString(3,password);
		ResultSet rs=stat.executeQuery();
		
			check=rs.next();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		return check;
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
	
	