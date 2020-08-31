package com.dxc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dxc.beans.Student;
import com.dxc.util.ConnectionManager;

public class StudentJdbcDAO extends JdbcDAO implements DAO<Student> {
	
	

	public StudentJdbcDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save(Student e) throws SQLException {
		boolean res = false;
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?)");
		pstmt.setInt(1, e.getId());
		pstmt.setString(2, e.getName());
		pstmt.setDate(3, new Date(e.getDob().getTime()));
		pstmt.setString(4, e.getEmail());
		pstmt.setString(5, e.getMobile());
		
		if(1== pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean edit(Student e) throws SQLException {
		boolean res = false;
		
		PreparedStatement pstmt = con.prepareStatement("UPDATE student SET name=?, dob=?, email=?, mobile=? WHERE id = ?");
		pstmt.setString(1, e.getName());
		pstmt.setDate(2, new Date(e.getDob().getTime()));
		pstmt.setString(3, e.getEmail());
		pstmt.setString(4, e.getMobile());
		pstmt.setInt(5, e.getId());
		
		if(1 == pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean res = false;
		
		PreparedStatement pstmt = con.prepareStatement("DELETE FROM student WHERE id = ?");
		pstmt.setInt(1, id);
		if(1== pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public Student find(int id) throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException {
		Student student = null;
		
		try {
			Connection con = ConnectionManager.getConnection();
			con.commit();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM student WHERE id = ?");
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString(2);
				
				java.util.Date dd = rs.getDate(3);
				String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
				String email = rs.getString(4);
				String mobile = rs.getString("mobile");
				
				student = new Student(id, name, strDob, email, mobile);
			}
		}
		finally {
			con.close();
		}
		
		return student;
	}

	@Override
	public List<Student> findAll() throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Student> students = new ArrayList<>();
		try {
			Connection con = ConnectionManager.getConnection();
			con.commit();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				java.util.Date dd = rs.getDate("dob");
				String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				
				Student student = new Student(id, name, strDob, email, mobile);
				students.add(student);
			}
		}
		finally {
			con.close();
		}
		
		
		return students;
	}
	
	
	public String csvAll() throws SQLException, ParseException {
		String fullcsv="";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			java.util.Date dd = rs.getDate("dob");
			String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
			String email = rs.getString("email");
			String mobile = rs.getString("mobile");
			
			Student student = new Student(id, name, strDob, email, mobile);
			fullcsv+=student.toCSV()+"\n";
		}
		
		
		return fullcsv;
	}

}
