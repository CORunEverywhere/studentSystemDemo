package org.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.StudentService;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentBySnoServlet0
 */
@WebServlet("/QueryStudentBySnoServlet0")
public class QueryStudentBySnoServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentBySnoServlet0() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no  = Integer.parseInt( request.getParameter("sno"))  ;
		StudentService service  = new StudentService();
		Student student = service.queryStudentBySno(no) ;
		System.out.println(student);
		//灏嗘浜虹殑鏁版嵁 閫氳繃鍓嶅彴jsp鏄剧ず:studentInfo.jsp
		
		request.setAttribute("student", student);//将查询到的学生 放入request域中
		
		//如果request域没有数据，使用重定向response.sendRedirect();
		//如果request域有数据(request.setAttribute()  )，使用请求转发
		request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
