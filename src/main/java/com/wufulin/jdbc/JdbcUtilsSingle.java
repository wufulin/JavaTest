/**
 * 
 */
package com.wufulin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wufulin
 * 
 */
public final class JdbcUtilsSingle {

	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "root";
	private static String password = "wufulin";
	private static JdbcUtilsSingle instance = null;

	private JdbcUtilsSingle() {

	}

	static {
		// ע����
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static JdbcUtilsSingle getInstance() {
		if (instance == null) {
			synchronized (JdbcUtilsSingle.class) {
				if (instance == null) {
					instance = new JdbcUtilsSingle();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {

		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
