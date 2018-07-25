package com.usts.shopdemo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String url = "jdbc:mysql://47.100.195.190:3306/tedu?useSSL=true";
	private static String user = "root";
	private static String password = "Tc15200141107";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
