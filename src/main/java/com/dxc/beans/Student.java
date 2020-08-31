package com.dxc.beans;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements  Comparable<Student> {
	int id;
	String name;
	Date dob;
	String email;
	String mobile;
	

	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String strDob, String email, String mobile) throws ParseException {
		super();
		this.id = id;
		this.name = name;
		dob = sdf.parse(strDob);
		this.email = email;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(String dob) {
		try {
			this.dob = sdf.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dob);
		return "Student [id=" + id + ", name=" + name + ", dob=" + strDob + ", email=" + email + ", mobile=" + mobile
				+ "]";
	}

	public String toCSV() {
		String strDob = new SimpleDateFormat("dd-MM-yyyyy").format(dob);
		return id + ", " + name + ", " + strDob + ", " + email + ", " + mobile;
	}
	
	public static Student parseStudent(String csvStudent) throws ParseException {
		
		String[] values = csvStudent.split(",");
		int id = Integer.parseInt(values[0].trim());
		String name = values[1].trim();
		String strDob = values[2].trim();
		String email = values[3].trim();
		String mobile = values[4].trim();
		
		Student student = new Student(id, name, strDob, email, mobile);
		
		return student;
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return this.id - o.getId();
	}

}
