package com.coupons.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupons.beans.Company;
import com.coupons.beans.Coupon;
import com.coupons.dao.CompanyDao;
import com.coupons.exceptions.DaoException;
import com.coupons.pool.ConnectionPool;

public class CompanyDBDao implements CompanyDao {
	
	PreparedStatement stat=null;
	 Connection con= null;
	 ResultSet rs=null;
	
	@Override
	public void createCompany(Company company) throws DaoException {
		
        
		String sql = "INSERT INTO company VALUES (?,?,?,?)";
	
		
		try {
			
			ConnectionPool.getInstance().getConnection().prepareStatement(sql);	
		    stat.setLong(1, company.getId());
		    stat.setString(2, company.getCompName());
		    stat.setString(3, company.getPassword());
		    stat.setString(4, company.getEmail());		
		    stat.executeUpdate();
		    stat.close();
		      
			
     } catch (SQLException e) {	
	       
         	}
		finally {CloseConnection(stat,con,rs);}
		}
	
	@Override
    public void removeCompany(Company company) throws DaoException {
		
	
		
		String sql ="DELETE FROM company"
				+ " WHERE comp_name=?";
		
		
		try {
	    	ConnectionPool.getInstance().getConnection().prepareStatement(sql);
	    	stat.setString(1, company.getCompName());
	    	stat.executeQuery();
	    	stat.close();
			
      } catch (SQLException e) {
    	  DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
	}
	
	@Override
	public void updateCompany(Company company) throws DaoException {
	
		String sql ="UPDATE company "
				+ "SET password=?,email=? "
				+ "WHERE comp_name=? ";
		
		try {
			ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		
		stat.setString(1, company.getPassword());
		stat.setString(2, company.getEmail());
		stat.setString(3, company.getCompName());
		stat.executeUpdate();
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		// TODO Auto-generated method stub

	}
	
	@Override
	public Company getCompany(long id) throws DaoException {
		Company company=null;
		String compName = null;
		String password = null;
		String email = null;
		
		String sql = "SELECT comp_name,password,email FROM company WHERE comp_id=?";
		
		try {
			ConnectionPool.getInstance().getConnection().prepareStatement(sql);		
		    stat.setLong(1, id); //1 -first question mark, id- variable to replace ?
		    ResultSet rs = stat.executeQuery();
		    rs.next();//if not added return error and null WHY!!!!
		    compName=rs.getString(1);// number of column
		    password=rs.getString(2);
		    email=rs.getString(3);
	} catch (SQLException e) {
		 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		// obj company gets its values 
		   company = new Company(id,compName,password,email);
		    
		   return company;
		
		
	}
		
	@Override
	// maybe better add full company with all attributes?? like customer??
	public List<Company> getAllCompanies() throws DaoException {
		List<Company> companyList= new ArrayList<>(); 
		
		String sql="SELECT comp_id FROM company";
	
		
		try {
		ConnectionPool.getInstance().getConnection().prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		
		while (rs.next()){
			//getCompany- method used for getting 1 company will make a list
			//using id
			companyList.add(getCompany(rs.getLong(1)));		//getLong(1)getting long from column number1(id)
			}
		} catch (SQLException e) {
			 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		
		return companyList;
	}

	@Override
	public List<Coupon> getCoupons(long id) throws DaoException {
		List<Coupon> couponsList= new ArrayList<>(); 
		CouponDBDao coupon=new CouponDBDao();
		
		String sql="SELECT * FROM coupon,company_coupon"
				+ "WHERE coupon.coupon_id=company_coupon.coupon_id"
				+ "AND company_coupon.comp_id=?";
		
		try {
			ConnectionPool.getInstance().getConnection().prepareStatement(sql);
			stat.setLong(1, id);
		    ResultSet rs=stat.executeQuery();
		while(rs.next()){		
			 couponsList.add(coupon.getCouponById(rs.getLong(1)));
		}
	
		
		} catch (SQLException e) {	
	    	 DaoException.showErrorMessage(e);
		}
		finally {CloseConnection(stat,con,rs);}
		return couponsList;
	}

	@Override
	public boolean login(String compName, String password) throws DaoException {
		boolean check=false;
		Connection con = ConnectionPool.getInstance().getConnection();//setting up connections from class pool
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