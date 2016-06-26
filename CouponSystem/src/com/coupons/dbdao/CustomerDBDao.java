package com.coupons.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupons.beans.Coupon;
import com.coupons.beans.Customer;
import com.coupons.dao.CustomerDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.Pool;

public class CustomerDBDao implements CustomerDao
{
	@Override
	public void createCustomer(Customer customer) throws DaoException {
		// get connection from pool
		Connection con = Pool.getConnection();
		try {
			
		
			String sql = 
					"INSERT INTO customer VALUES (?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setLong(1, customer.getId());
			stat.setString(2, customer.getName());
			stat.setString(3, customer.getPassword());
			
			stat.executeUpdate();
		} catch (SQLException e) {
			// Translation exception
			throw new DaoException("Something", e);
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

	@Override
	public void removeCustomer(Customer customer) throws DaoException {
		Connection con = Pool.getConnection();
		String sql ="DELETE FROM company"
				+ " WHERE cust_name=?";
		PreparedStatement stat;
		
		try {
	    	stat=con.prepareStatement(sql);
	    	stat.setString(1, customer.getName());
	    	stat.executeQuery();
      } catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws DaoException {
		
		Connection con=Pool.getConnection();
		String sql ="UPDATE customer "
				+ "SET password=? "
				+ "WHERE cust_name=? ";
		PreparedStatement stat;
		try {
			stat=con.prepareStatement(sql);
		
		stat.setString(1, customer.getPassword());
		stat.setString(2, customer.getName());
		stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		}
	
	@Override
	public Customer getCustomer(long id) throws DaoException {
		//1.get connection
		Customer customer=null;
		Connection con=Pool.getConnection();
		String sql= "SELECT cust_name,password FROM customer WHERE cust_id=?";
		PreparedStatement stat=null;
		try {
		
		stat = con.prepareStatement(sql);
		stat.setLong(1, id);
		ResultSet rs=stat.executeQuery();
		rs.next();
		String cust_name=rs.getString(1);
		String password=rs.getString(2);
	    customer = new Customer(cust_name,id,password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return customer;
	}
	
	@Override
	public List<Customer> getAllCustomers() throws DaoException {
		List<Customer> customers=new ArrayList<Customer>();
		Connection con = Pool.getConnection();
		String sql="SELECT * FROM customer ";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);
		
		ResultSet rs = stat.executeQuery();
		while(rs.next()){
			customers.add(new Customer(rs.getString(2),rs.getLong(1),rs.getString(3)));
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public List<Coupon> getCoupons() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String custName, String password) throws DaoException {
		boolean check=false;
		Connection con = Pool.getConnection();//setting up connections from class pool
		String sql = "SELECT custName,password FROM company WHERE cust_name=? AND password=?";
		try {
		PreparedStatement stat=con.prepareStatement(sql);
		stat.setString(2, custName);
		stat.setString(3,password);
		ResultSet rs=stat.executeQuery();
		
			check=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	}
	
//	// A function that creates connection
//	// TODO: Use the pool instead later on
//	private Connection getConnection() throws SQLException
//	{
//		String url = "jdbc:mysql://localhost:3306/world";
//				
//		Connection con = 
//				DriverManager.getConnection(url, 
//						"user", "password");
//		return con;
//				
//	}
	

