package com.coupons.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.coupons.exceptions.DaoException;
import com.coupons.pool.Pool;

public class CleanTableCompany {

	public static void main(String[] args) throws SQLException {
	
		
		//Remove companies
		Connection con = Pool.getConnection();
		PreparedStatement stat = null;
		
		for (int i=0;i<100;i++){
			String sql = "Delete from company where comp_id=?";
			try {
				stat=con.prepareStatement(sql);
			
			
			stat.setInt(1, i);
		    int result = stat.executeUpdate();
		    if( result == 1){
			System.out.println("Company Record Removed Successfully.");
		}
		else{
			System.err.println("Error While Removing Company Record.");
		}
		
		
		
	
		} catch (SQLException e) {
			DaoException.showErrorMessage(e);
			}
			
	}
		stat.close();
		con.close();
	}}
