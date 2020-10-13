package org.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.student.entity.Student;



//数据访问层：原子性的增删
public class StudentDao {
	private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //数据库地址
    private static final String DBURL = "jdbc:sqlserver://localhost:1434;DataBaseName=exp1";
    //数据库登录用户名
    private static final String DBUSER = "sa";
    //数据库用户密码
    private static final String DBPASSWORD = "19991203OYXQ";
    //数据库连接
    
    //查询此人是否存在
    public boolean isExist(int sno) {
    	return queryStudentBySno(sno)==null?false:true;
    }
    //添加学生
    public boolean addStudent(Student student) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {//加载驱动程序
			Class.forName(DBDRIVER);
			System.out.println("操作成功zengjia");
			//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			         
			String sql = "insert into stu(sno,sname,sage,saddress) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSno());
			pstmt.setString(2, student.getSname());
			pstmt.setInt(3, student.getSage());
			pstmt.setString(4, student.getSaddress());
			int count = pstmt.executeUpdate();
			if(count>0)
				return true;
			else
				return false;
			
			 } catch(ClassNotFoundException e) {
				 e.printStackTrace();
				 return false;
			 }catch (SQLException e) {
					e.printStackTrace();
					return false;
			 }catch (Exception e) {
				    e.printStackTrace();
				    return false;
			}
			finally {
				try {
					if(rs!=null) rs.close();
			        if(pstmt!=null) pstmt.close();
			        if(conn!=null) conn.close();
			    }catch(Exception e) {
			       e.printStackTrace();
			       }
			}
    }
    
    //修改学生信息：根据sno找到待修改的人，再把这个人修改
    public boolean updateStudentBySno(int sno,Student student) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {//加载驱动程序
			Class.forName(DBDRIVER);
			System.out.println("操作成功shanchu");
			//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			         
			String sql = "update stu set sname = ?,sage=?,saddress=? where sno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddress());
			
			pstmt.setInt(4, sno);
			int count = pstmt.executeUpdate();
			if(count>0)
				return true;
			else
				return false;
			
			 } catch(ClassNotFoundException e) {
				 e.printStackTrace();
				 return false;
			 }catch (SQLException e) {
					e.printStackTrace();
					return false;
			 }catch (Exception e) {
				    e.printStackTrace();
				    return false;
			}
			finally {
				try {
			        if(pstmt!=null) pstmt.close();
			        if(conn!=null) conn.close();
			    }catch(Exception e) {
			       e.printStackTrace();
			       }
			}
    }
    
    //根据学号删除学生
    public boolean deleteStudentBySno(int sno) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {//加载驱动程序
			Class.forName(DBDRIVER);
			System.out.println("操作成功shanchu");
			//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			         
			String sql = "delete from stu where sno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			int count = pstmt.executeUpdate();
			if(count>0)
				return true;
			else
				return false;
			
			 } catch(ClassNotFoundException e) {
				 e.printStackTrace();
				 return false;
			 }catch (SQLException e) {
					e.printStackTrace();
					return false;
			 }catch (Exception e) {
				    e.printStackTrace();
				    return false;
			}
			finally {
				try {
			        if(pstmt!=null) pstmt.close();
			        if(conn!=null) conn.close();
			    }catch(Exception e) {
			       e.printStackTrace();
			       }
			}
    }
    //查询全部学生
	public List<Student> queryAllStudentBySno() {
		List<Student> students = new ArrayList();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//加载驱动程序
		Class.forName(DBDRIVER);
		System.out.println("操作成功chaxun");
		//连接数据库
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		         
		String sql = "select* from stu";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int no = rs.getInt("sno");
		    String name = rs.getString("sname");
		    int age = rs.getInt("sage");
		    String address = rs.getString("saddress");
		    student = new Student(no,name,age,address); 
		    students.add(student);
		 }
		return students;
		 } catch(ClassNotFoundException e) {
			 e.printStackTrace();
		     return null;
		 }catch (SQLException e) {
				e.printStackTrace();
			    return null;
		 }catch (Exception e) {
			    e.printStackTrace();
			    return null;
		}
		finally {
			try {
				if(rs!=null) rs.close();
		        if(pstmt!=null) pstmt.close();
		        if(conn!=null) conn.close();
		    }catch(Exception e) {
		       e.printStackTrace();
		       }
		}
	}
    
    //根据姓名查询
    //根据年龄查询
    //根据学号查学生
	public Student queryStudentBySno(int sno) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//加载驱动程序
		Class.forName(DBDRIVER);
		System.out.println("操作成功chaxun");
		//连接数据库
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		         
		String sql = "select* from stu where sno=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, sno);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			int no = rs.getInt("sno");
		    String name = rs.getString("sname");
		    int age = rs.getInt("sage");
		    String address = rs.getString("saddress");
		    student = new Student(no,name,age,address); 
		 }
		return student;
		 } catch(ClassNotFoundException e) {
			 e.printStackTrace();
		     return null;
		 }catch (SQLException e) {
				e.printStackTrace();
			    return null;
		 }catch (Exception e) {
			    e.printStackTrace();
			    return null;
		}
		finally {
			try {
				if(rs!=null) rs.close();
		        if(pstmt!=null) pstmt.close();
		        if(conn!=null) conn.close();
		    }catch(Exception e) {
		       e.printStackTrace();
		       }
		}
	}
	//根据页面查询
	public List<Student> queryStudentsByPage(int currentPage,int pageSize) {
		List<Student> students = new ArrayList();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//加载驱动程序
		Class.forName(DBDRIVER);
		System.out.println("操作成功yemianchaxun");
		//连接数据库
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		/*select * from student  order by sno 
		offset (页数-1)*页面大小+1  rows fetch next 页面大小  rows only ;*/
		String sql = "select * from stu order by sno "
				+ "offset ("
				+ currentPage
				+ "-1)*"
				+ pageSize
				+ "  rows fetch next "
				+ pageSize
				+ "  rows only ;";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		Object[] params = {currentPage*pageSize,(currentPage-1)*pageSize+1};
		while(rs.next()) {
			int no = rs.getInt("sno");
		    String name = rs.getString("sname");
		    int age = rs.getInt("sage");
		    String address = rs.getString("saddress");
		    student = new Student(no,name,age,address); 
		    students.add(student);
		 }
		return students;
		 } catch(ClassNotFoundException e) {
			 e.printStackTrace();
		     return null;
		 }catch (SQLException e) {
				e.printStackTrace();
			    return null;
		 }catch (Exception e) {
			    e.printStackTrace();
			    return null;
		}
		finally {
			try {
				if(rs!=null) rs.close();
		        if(pstmt!=null) pstmt.close();
		        if(conn!=null) conn.close();
		    }catch(Exception e) {
		       e.printStackTrace();
		       }
		}
	}
	
	//查询总数
	public static int getTotalCount() {
		 Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
			int count = -1;
			try {//加载驱动程序
			Class.forName(DBDRIVER);
			System.out.println("操作成功chaxun");
			//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			         
			String sql = "select count(*) from stu";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); 
			 }
			
			 } catch(ClassNotFoundException e) {
				 e.printStackTrace();
			 }catch (SQLException e) {
					e.printStackTrace();
			 }catch (Exception e) {
				    e.printStackTrace();
			}
			finally {
				try {
					if(rs!=null) rs.close();
			        if(pstmt!=null) pstmt.close();
			        if(conn!=null) conn.close();
			    }catch(Exception e) {
			       e.printStackTrace();
			       }
			}
			return count;
	}
}
