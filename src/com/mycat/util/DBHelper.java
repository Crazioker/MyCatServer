package com.mycat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
	public static Connection connect() {
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/MyCat?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName(driverClassName);
			Properties pro = new Properties();

			Connection con = DriverManager.getConnection(url, "root", "root123");

			return con;
		} catch (Exception ex) {
			System.out.println("  " + ex.toString());
			return null;
		}
	}

	public static void closeResult(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
		}
	}

	public static void closePreparedStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
		}
	}

	public static void closeConneciton(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
		}
	}
}
