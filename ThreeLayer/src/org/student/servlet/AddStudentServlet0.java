package org.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.StudentService;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class AddStudentServlet0
 */
@WebServlet("/AddStudentServlet0")
public class AddStudentServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no =Integer.parseInt( request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(  request.getParameter("sage")) ;
		String address = request.getParameter("saddress");
		//封装到实体类Student
		Student student  = new Student(no,name,age,address) ;
		
		//鎺ュ彛 x = new 瀹炵幇绫�();
		StudentService studentService = new StudentService();
		boolean result = studentService.addStudent(student) ;
		
		/*
		 *   out  request  response session  application 
		 *   out:	PrintWriter out = response.getWriter() ;
		 *   session:	request.getSession()
		 *   application:	request.getServletContext()
		 */
		
		
		//编码错误（乱码）解决方法
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		/*if(result) {
			//PrintWriter out = response.getWriter() ;
			//out.println("增加成功");
			
		}else {
			PrintWriter out = response.getWriter() ;
			out.println("增加失败");
		}*/
		if(!result) {
			request.setAttribute("error", "addError"); 
		}else {//
			request.setAttribute("error", "noaddError");  
		}
		//response.sendRedirect("QueryAllStudentsServlet0");
		request.getRequestDispatcher("QueryStudentByPageServlet0").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
