package org.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.StudentService;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryAllStudentsServlet0
 */
@WebServlet("/QueryAllStudentsServlet0")
public class QueryAllStudentsServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllStudentsServlet0() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		StudentService service  = new StudentService();
		List<Student> students = service.queryAllStudents() ;
		
		//System.out.println(students);
		request.setAttribute("students", students);
		//因为request域中有数据，因此需要通过请求转发的方式跳转
		//pageContext<request<session<application
		
		request.getRequestDispatcher( "index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
