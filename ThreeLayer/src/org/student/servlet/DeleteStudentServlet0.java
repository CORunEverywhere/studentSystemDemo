package org.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.service.IStudentService;
import org.student.service.StudentService;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class DeleteStudentServlet0
 */
@WebServlet("/DeleteStudentServlet0")
public class DeleteStudentServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除
		request.setCharacterEncoding("utf-8");
		//接受前端传来的学号
		int no = Integer.parseInt( request.getParameter("sno") );
		
		StudentService service = new StudentService();
		boolean result = service.deleteStudentBySno(no) ;
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
//			out.print();
			//response.getWriter().println("删除成功！");
			response.sendRedirect("QueryStudentByPageServlet0");//閲嶆柊鏌ヨ 鎵�鏈夊鐢�
			
		}else {
			response.getWriter().println("删除失败");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
