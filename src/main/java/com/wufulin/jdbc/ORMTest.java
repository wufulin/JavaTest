package com.wufulin.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.wfl.domain.User;

public class ORMTest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws IllegalAccessException, Exception {
		User user=(User)getObject(
				"select id as Id,name as Name,age as Age,sex as Sex from tb_user where id=6", 
				User.class);
		System.out.println(user);
	}

	static Object getObject(String sql, Class clazz) throws Exception
			 {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtilsSingle.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = rsmd.getColumnCount();
			String[] colNames = new String[count];
			for (int i = 1; i <= count; i++) {
				colNames[i - 1] = rsmd.getColumnLabel(i);
			}

			Object obj = null;
			Method[] ms = clazz.getDeclaredMethods();
			if (rs.next()) {
				obj = clazz.newInstance();
				for (int i = 0; i < colNames.length; i++) {
					String colName = colNames[i];
					String methodName = "set" + colName;
					for (Method m : ms) {
						if (methodName.equals(m.getName())) {
							m.invoke(obj, rs.getObject(colName));
						}
					}
				}
			}
			return obj;
		} finally {
			JdbcUtilsSingle.free(rs, ps, conn);
		}
	}
}
