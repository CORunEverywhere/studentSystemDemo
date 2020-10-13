<%@page import="org.student.entity.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><!-- el表达式语言，可以替代jsp中的java代码 -->
	<%
		Student student = (Student)request.getAttribute("student");
		out.print(student);
		out.print(student.getSname());
	%>
	<br>-----点操作符-------<br>
	${requestScope.student }<br>
	${requestScope.student.sname }<br>
	
	<br>-----中括号操作符-------<br>
	${requestScope.student['sage'] }<br>
</body><!-- 传统用java代码的弊端，需要类型转换，需要处理null
		代码参杂（html中参杂java代码）
		${requestScope.student }
		${域对象.域对象中的属性.属性.属性.级联属性 }
		点操作符：简单易用，map元素
		中括号操作符：功能强大，可以包含特殊字符（。、-），可以获取数组元素，获取变量值，map元素
		并且能使用运算符,例如empty运算发,判断是否为空，为空返回true，反之为false
		
		el表达式的隐式对象，内置对象，不需要new直接使用	例如jsp：request/response
		a、作用域访问对象（el域对象）：pageScope requestScope sessionScope applicationScope
			如果不指定对象，则按从小到大顺序拿元素对象
		b、参数访问对象：获取表单数据${param}和${paramValus}用来代替request.getParameter()和request.getParameterValues()
			另外能够获得超链接中传的值
		c、jsp隐式对象：pageContext,在jsp中可以通过这个对象获取其他jsp中的隐式对象
 -->
</html>