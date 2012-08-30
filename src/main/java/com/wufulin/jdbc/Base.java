/**
 * 
 */
package com.wufulin.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wufulin
 * 
 */
public class Base {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ��������
			conn = JdbcUtilsSingle.getInstance().getConnection();

			// �������
			st = conn.createStatement();

			// ִ�����
			rs = st.executeQuery("select * from tb_user");

			// ������
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2)
						+ "\t" + rs.getObject(3) + "\t" + rs.getObject(4));
			}
		} finally {
			JdbcUtilsSingle.free(rs, st, conn);
		}

	}
}
