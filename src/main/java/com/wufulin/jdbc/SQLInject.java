package com.wufulin.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLInject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		read("��Զ��");
		long end = System.currentTimeMillis();
		System.out.println("read:" + (end - start));
	}

	static void read(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		try {
			// ��������
			conn = JdbcUtilsSingle.getInstance().getConnection();

			// �������
			sql = "select * from tb_user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			// ִ�����
			rs = ps.executeQuery();

			// ������
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t" + rs.getInt("age")
						+ "\t" + rs.getString("sex"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtilsSingle.free(rs, ps, conn);
		}

	}
}
