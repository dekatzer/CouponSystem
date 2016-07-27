package com.coupons.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.coupons.exceptions.DaoException;

class Pool{
	
	private static Pool poolInstance= null;
	private static Connection con = null;
	private static String url = "jdbc:mysql://localhost:3306/couponschema";
	private static String username = "root";
	private static String password = "password";
	static Vector<Connection> connectionPool = new Vector<Connection>();
	static List<Connection> busyConnections = new ArrayList<>();
	
	private Pool() 
	{}
    public static Pool getInstance(){
		
		if(poolInstance==null)
			poolInstance = new Pool();
		    try {
				initializeConnectionPool();
			} catch (DaoException e) {
				
				e.printStackTrace();
			}
		return poolInstance;
		}
	
	private static Connection openConnection() throws DaoException{
		String driver = "com.mysql.jdbc.Driver";
	    try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return con;
		}
	

	private static void initializeConnectionPool() throws DaoException
	{
		while(!checkIfConnectionPoolIsFull())
		{
			
			connectionPool.addElement(openConnection());
		}
		
	}

	private synchronized static boolean checkIfConnectionPoolIsFull()
	{
		final int MAX_POOL_SIZE = 5;

		//Check the pool size
		if(connectionPool.size() < MAX_POOL_SIZE)
		{
			return false;
		}

		return true;
	}
	
	public synchronized Connection getConnection()
	{
		Connection con = null;

		//Check  connection available
		if(connectionPool.size() > 0)
		{
			con = (Connection) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
			busyConnections.add(con);
		}
		
		return con;
	}

	public synchronized void returnConnection(Connection con)
	{
		
		connectionPool.addElement(con);
		busyConnections.remove(con);
	}
	
	public void closeAllConnections()
	{
		for (Connection con : connectionPool) {
			closeAllBusyConnections();
					try {
						if (!con.isClosed()) {
						con.close();
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
		}		
	}
		
	public void closeAllBusyConnections()
	{
		for (Connection con : busyConnections) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
    
}

