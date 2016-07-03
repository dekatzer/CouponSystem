package com.coupons.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupons.beans.Company;
import com.coupons.beans.Coupon;
import com.coupons.beans.CouponType;
import com.coupons.dao.CompanyDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.Pool;

public class CompanyDBDao implements CompanyDao {

	@Override
	public void createCompany(Company company) throws DaoException {
		Connection con = Pool.getConnection();
		String sql = "INSERT INTO company VALUES (?,?,?,?)";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);		
		    stat.setLong(1, company.getId());
		    stat.setString(2, company.getCompName());
		    stat.setString(3, company.getPassword());
		    stat.setString(4, company.getEmail());		
		    stat.executeUpdate();
		    stat.close();
			con.close();
     } catch (SQLException e) {	
    	 DaoException.showErrorMessage(e);
	       
         	}
		
		}
	
	@Override
    public void removeCompany(Company company) throws DaoException {
		
		Connection con = Pool.getConnection();
		String sql ="DELETE FROM company"
				+ " WHERE comp_name=?";
		PreparedStatement stat;
		
		try {
	    	stat=con.prepareStatement(sql);
	    	stat.setString(1, company.getCompName());
	    	stat.executeQuery();
	    	stat.close();
			con.close();
      } catch (SQLException e) {
    	  DaoException.showErrorMessage(e);
		}
	}
	
	@Override
	public void updateCompany(Company company) throws DaoException {
		Connection con = Pool.getConnection();
		String sql ="UPDATE company "
				+ "SET password=?,email=? "
				+ "WHERE comp_name=? ";
		PreparedStatement stat;
		try {
			stat=con.prepareStatement(sql);
		
		stat.setString(1, company.getPassword());
		stat.setString(2, company.getEmail());
		stat.setString(3, company.getCompName());
		stat.executeUpdate();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}		
		// TODO Auto-generated method stub

	}
	
	@Override
	public Company getCompany(long id) throws DaoException {
		Company company=null;
		String compName = null;
		String password = null;
		String email = null;
		Connection con = Pool.getConnection();//setting up connections from class pool
		String sql = "SELECT comp_name,password,email FROM company WHERE comp_id=?";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);		
		    stat.setLong(1, id); //1 -first question mark, id- variable to replace ?
		    ResultSet rs = stat.executeQuery();
		    rs.next();//if not added return error and null WHY!!!!
		    compName=rs.getString(1);// number of column
		    password=rs.getString(2);
		    email=rs.getString(3);
	} catch (SQLException e) {
		 DaoException.showErrorMessage(e);
		}
		// obj company gets its values 
		   company = new Company(id,compName,password,email);
		    
		   return company;
		
		
	}
		
	@Override
	// maybe better add full company with all attributes?? like customer??
	public List<Company> getAllCompanies() throws DaoException {
		List<Company> companyList= new ArrayList<>(); 
		Connection con = Pool.getConnection();
		String sql="SELECT comp_id FROM company";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);
		
		ResultSet rs = stat.executeQuery();
		
		while (rs.next()){
			//getCompany- method used for getting 1 company will make a list
			//using id
			companyList.add(getCompany(rs.getLong(1)));		//getLong(1)getting long from column number1(id)
			}
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		
		return companyList;
	}

	@Override
	public List<Coupon> getCoupons(long id) throws DaoException {
		List<Coupon> couponsList= new ArrayList<>(); 
		Connection con = Pool.getConnection();
		String sql="SELECT * FROM coupon,company_coupon"
				+ "WHERE coupon.coupon_id=company_coupon.coupon_id"
				+ "AND company_coupon.comp_id=?";
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);
			stat.setLong(1, id);
		    ResultSet rs=stat.executeQuery();
		while(rs.next()){
			 Coupon coupon = new Coupon(
					 rs.getLong("coupon_id"),
					 rs.getString("title"),
					 rs.getDate("start_date"),
					 rs.getDate("end_date"),
					 CouponType.valueOf(rs.getString("type")),
					 rs.getInt("amount"),
					 rs.getString("message"),
					 rs.getDouble("price"),
					 rs.getString("image"));
			 couponsList.add(coupon);
		}
	
		
		} catch (SQLException e) {	
	    	 DaoException.showErrorMessage(e);
		}
		return couponsList;
	}

	@Override
	public boolean login(String compName, String password) throws DaoException {
		boolean check=false;
		Connection con = Pool.getConnection();//setting up connections from class pool
		String sql = "SELECT comp_name,password FROM company WHERE comp_name=? AND password=?";
		try {
		PreparedStatement stat=con.prepareStatement(sql);
		stat.setString(2, compName);
		stat.setString(3,password);
		ResultSet rs=stat.executeQuery();
		
			check=rs.next();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		
		return check;
	}

}

