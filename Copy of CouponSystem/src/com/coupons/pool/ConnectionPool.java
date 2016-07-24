package com.coupons.pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import com.coupons.exceptions.DaoException;




public class ConnectionPool {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/couponschema";
	static final String USER = "root";
	static final String PASS = "1234";

	private final int MAX_CONNECTIONS = 5;
	private static ConnectionPool instance = null;
	private ArrayList<Connection> inUseList = new ArrayList<>();
	private ArrayList<Connection> freeList = new ArrayList<>();
	private Semaphore listSemaphore = new Semaphore(MAX_CONNECTIONS);

	private ConnectionPool() throws DaoException {
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				freeList.add(conn);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DaoException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public static ConnectionPool getInstance() throws DaoException {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (DaoException e) {
				e.printStackTrace();
				throw new DaoException();
			}
		}
		return instance;
	}

	public Connection getConnection() throws DaoException {
		Connection conn;
		try {
			listSemaphore.acquire();
		} catch (InterruptedException e) {
			throw new DaoException();
		}
		synchronized (this) {
			conn = freeList.remove(0);
			inUseList.add(conn);
		}
		return conn;
	}

	public void returnConnection(Connection conn) {
		for (int i = 0; i < inUseList.size(); i++) {
			if (conn == inUseList.get(i)) {
				synchronized (this) {
					inUseList.remove(i);
					freeList.add(conn);
				}
				listSemaphore.release();
				break;
			}
		}
	}

	public synchronized void closeAllConnections() throws DaoException {
		try {
			for (int i = 0; i < freeList.size(); i++) {
				freeList.remove(i).close();
			}
			for (int i = 0; i < inUseList.size(); i++) {
				inUseList.remove(i).close();
			}
		} catch (Exception e) {
			e.printStackTrace();
	//		throw new CouponApplicationException(ErrType.SOCKET_CONNECTION_ERROR);
		}
	}

}


