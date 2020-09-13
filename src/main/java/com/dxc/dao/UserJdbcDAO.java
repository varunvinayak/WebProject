package com.dxc.dao;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.dxc.beans.User;
import com.dxc.util.ConnectionManager;

public class UserJdbcDAO extends JdbcDAO implements DAO<User> {

	public UserJdbcDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean validate(String username, String password) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();

	}

	@Override
	public boolean edit(User e) throws Exception {
		boolean res = false;
		PreparedStatement pstmt = con.prepareStatement("update exam set password = ? where username=?");
		pstmt.setString(1, e.getUsername());
		pstmt.setString(2, e.getPassword());
		if (pstmt.executeUpdate() == 1) {
			res = true;
		}

		return res;
	}

	@Override
	public boolean delete(int id) throws Exception {
		return false;
	}

	@Override
	public User find(int id) throws Exception {

		return null;
	}

	@Override
	public boolean save(User user) throws Exception {
		boolean res = false;

		PreparedStatement pstmt = con.prepareStatement("insert into user values(?,?)");
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		int n = pstmt.executeUpdate();
		con.commit();
		if (n == 1) {
			res = true;
		}

		return res;
	}

}
