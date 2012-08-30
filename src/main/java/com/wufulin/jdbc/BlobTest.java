package com.wufulin.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		create();
	}

	static void create() throws IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = JdbcUtilsSingle.getInstance().getConnection();
			sql = "insert into blob_test(big_bit) values(?)";
			ps = conn.prepareStatement(sql);
			File file = new File("gd.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));

			ps.setBinaryStream(1, in, (int) file.length());

			int i = ps.executeUpdate();

			in.close();

			System.out.println("i= " + i);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtilsSingle.free(rs, ps, conn);
		}
	}
}
