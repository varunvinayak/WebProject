package com.dxc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.beans.Student;
import com.dxc.dao.StudentJdbcDAO;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("un")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try {
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			

				StudentJdbcDAO std = new StudentJdbcDAO();
				Student student = new Student();
				
				student.setId(id);
				student.setName(name);
				student.setDob(dob);
				student.setEmail(email);
				student.setMobile(mobile);
				
				boolean o = std.edit(student);
				
				if(o) {
					out.println("Student details edited successfully");
				}
		
				else {
					out.println("details failed to update");
					out.println(id);
				}
		
		//	RequestDispatcher rd = request.getRequestDispatcher("DisplayStudents.jsp");
		//	rd.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
		else {
			response.sendRedirect("Login.jsp");
		}

  }
}
