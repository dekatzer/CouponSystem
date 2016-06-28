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
    	String password = "1234";
    	
    	
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
	
		
//		try {
//			//String driverName = loadFromFIle();
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("----------- DRIVER LOADED -----------------");
//		
//	
//		// 2. get a connection
//		String url = "jdbc:mysql://localhost:3306/couponschema";
//		String username = "root";
//		String password = "password";
//	//	String query = 
//		
//		Connection connection = null;
//		Statement stmt=null;
//		
//		// ARM - Connection will be automatically closed
//		try (Connection con = 
//				DriverManager.getConnection(url, username, password))
//		{
//			
//			
//		System.out.println("------------ Connection established ----------");
//		
//		
//		// 3. create statement
//		Statement stat = con.createStatement();
//		
//		
//		
//		// 4. create query ((SELECT - query) or (INSERT, UPDATE, DELETE - update)
//		
////		String sql = "DELETE FROM world.city WHERE ID=3";
////		int result = stat.executeUpdate(sql);
////		System.out.println(result);
//		
////
//		//con.close(); // USE ARM
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
}
