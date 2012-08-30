package com.wufulin.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wfl.domain.User;
import com.wfl.jdbc.JdbcUtilsSingle;
import com.wfl.jdbc.dao.DaoException;
import com.wfl.jdbc.dao.UserDao;

public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtilsSingle.getInstance().getConnection();
			String sql="insert into tb_user values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setString(3, user.getSex());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtilsSingle.free(rs, ps, conn);
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUser(String loginName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
