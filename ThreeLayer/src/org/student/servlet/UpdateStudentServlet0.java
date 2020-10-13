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
 * Servlet implementation class UpdateStudentServlet0
 */
@WebServlet("/UpdateStudentServlet0")
public class UpdateStudentServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet0() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取待修改的人的学号
		int no = Integer.parseInt( request.getParameter("sno") ) ;
		//获取修改后的内容
		String name =  request.getParameter("sname") ;
		int age  = Integer.parseInt( request.getParameter("sage")) ;
		String address =  request.getParameter("saddress") ;
		//将修改后的内容封装到一个实体类中
		Student student = new Student(name,age,address) ; 
		
		StudentService service = new StudentService();
		boolean result = service.updateStudentBySno(no, student);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
			//response.getWriter().println("修改成功");
			response.sendRedirect("QueryStudentByPageServlet0");//修改完毕后，再次重新查询全部的学生并显示
		}else {
			response.getWriter().println("修改失败");
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
