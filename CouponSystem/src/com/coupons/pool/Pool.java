package com.coupons.pool;

import java.sql.*;


     public class Pool {
    	 
	
	public static Connection getConnection(){
		
		//LOADING DRIVER
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) 
		{e.printStackTrace();}
		System.out.println("----------- DRIVER LOADED -----------------");
		
		//PREPAIRING CONNECTION
		String url = "jdbc:mysql://localhost:3306/couponschema";
		String username = "root";
    	String password = "password";
    	
    	
    	//GETTING CONNECTED
    	Connection con= null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("------------ Connection established ----------");
    	
		
		return con;
	}
	
		

}
