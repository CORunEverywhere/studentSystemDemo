<%@page import="org.student.entity.Page"%>
<%@page import="org.student.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("tr:odd").css("background-color","lightgray");//隔行变色
		});
	</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息列表</title>
</head>
<body>

	<%
		/*在分页显示的前提需要显示的数据
			当前页currentPage
			当前页数据集合
			页面大小pageSize
			总页数totalPage
			总数据totalPage
			*/
	%>
	<%
		//error:adderror 失败
		//否则：1 确实执行了增加    2直接访问查询全部页面
		String error = (String)request.getAttribute("error") ;//addError
		if(error!=null){
			if(error.equals("addError")){
				out.print("增加失败！");
			}else if(error.equals("noaddError")){
				out.print("增加成功！");
			}//根本没有执行增加
		}	
		
	%>
		<table border="1px">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>地址</th>
				<th>操作</th>
			</tr>
			
			<%
				//获取request域中的数据
				Page page0 = (Page)request.getAttribute("p") ;
				for(Student student:page0.getStudents()){
			%>
					<tr>
						<td><a href="QueryStudentBySnoServlet0?sno=<%=student.getSno() %>"><%=student.getSno() %></a></td>
						
						<td><%=student.getSname() %></td>
						<td><%=student.getSage() %></td>
						<td><%=student.getSaddress() %></td>
						<td> <a href="DeleteStudentServlet0?sno=<%=student.getSno() %>   ">删除</a> </td>
						
						
					</tr>
			<%
				}
			%>
		<%=page0.getCurrentPage()%>/<%=page0.getTotalPage()%><br>
		</table>
		<a href="add.jsp">新增</a>
		<%
				if(page0.getCurrentPage()==1){
		%>
				<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getCurrentPage()+1%>">下一页</a>
		<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getTotalPage()%>">尾页</a>
		<%	
				}
				else if(page0.getCurrentPage()==page0.getTotalPage()){
					%>
				<a href="QueryStudentByPageServlet0?currentPage=1">首页</a>
		<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getCurrentPage()-1%>">上一页</a>
		<%
				}
				else{
		%>
		<a href="QueryStudentByPageServlet0?currentPage=1">首页</a>
		<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getCurrentPage()-1%>">上一页</a>
		<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getCurrentPage()+1%>">下一页</a>
		<a href="QueryStudentByPageServlet0?currentPage=<%=page0.getTotalPage()%>">尾页</a>
		<%
				}
		%>
		
		
</body>
</html>