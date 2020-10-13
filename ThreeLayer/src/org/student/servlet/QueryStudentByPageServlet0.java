package org.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Page;
import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.StudentService;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentByPageServlet0
 */
@WebServlet("/QueryStudentByPageServlet0")
public class QueryStudentByPageServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentByPageServlet0() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		
		StudentService  studentService = new StudentService();
		int count = studentService.getTotalCount();
		
		
		//int currentPage = 2 ;
		Page page = new Page();
		String cPage = request.getParameter("currentPage");
		
		if(cPage == null) {
			cPage = "1";
		}
		int currentPage = Integer.parseInt(cPage);
		page.setCurrentPage(currentPage);
		
		page.setTotalCount(count);
		
		int pageSize = 3 ;
		page.setPageSize(pageSize);
		
		List<Student> students = studentService.queryStudentsByPage(currentPage, pageSize) ;
		System.out.println(students);
		System.out.println(count);
		page.setStudents(students);
		page.setTotalPage();
		
		request.setAttribute("p", page);
		
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
