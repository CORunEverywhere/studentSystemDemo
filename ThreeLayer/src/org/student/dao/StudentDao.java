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



//���ݷ��ʲ㣺ԭ���Ե���ɾ
public class StudentDao {
	private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //���ݿ��ַ
    private static final String DBURL = "jdbc:sqlserver://localhost:1434;DataBaseName=exp1";
    //���ݿ��¼�û���
    private static final String DBUSER = "sa";
    //���ݿ��û�����
    private static final String DBPASSWORD = "19991203OYXQ";
    //���ݿ�����
    
    //��ѯ�����Ƿ����
    public boolean isExist(int sno) {
    	return queryStudentBySno(sno)==null?false:true;
    }
    //���ѧ��
    public boolean addStudent(Student student) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {//������������
			Class.forName(DBDRIVER);
			System.out.println("�����ɹ�zengjia");
			//�������ݿ�
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
    
    //�޸�ѧ����Ϣ������sno�ҵ����޸ĵ��ˣ��ٰ�������޸�
    public boolean updateStudentBySno(int sno,Student student) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {//������������
			Class.forName(DBDRIVER);
			System.out.println("�����ɹ�shanchu");
			//�������ݿ�
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
    
    //����ѧ��ɾ��ѧ��
    public boolean deleteStudentBySno(int sno) {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {//������������
			Class.forName(DBDRIVER);
			System.out.println("�����ɹ�shanchu");
			//�������ݿ�
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
    //��ѯȫ��ѧ��
	public List<Student> queryAllStudentBySno() {
		List<Student> students = new ArrayList();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//������������
		Class.forName(DBDRIVER);
		System.out.println("�����ɹ�chaxun");
		//�������ݿ�
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
    
    //����������ѯ
    //���������ѯ
    //����ѧ�Ų�ѧ��
	public Student queryStudentBySno(int sno) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//������������
		Class.forName(DBDRIVER);
		System.out.println("�����ɹ�chaxun");
		//�������ݿ�
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
	//����ҳ���ѯ
	public List<Student> queryStudentsByPage(int currentPage,int pageSize) {
		List<Student> students = new ArrayList();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		Student student = null;
		
		try {//������������
		Class.forName(DBDRIVER);
		System.out.println("�����ɹ�yemianchaxun");
		//�������ݿ�
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		/*select * from student  order by sno 
		offset (ҳ��-1)*ҳ���С+1  rows fetch next ҳ���С  rows only ;*/
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
	
	//��ѯ����
	public static int getTotalCount() {
		 Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
			int count = -1;
			try {//������������
			Class.forName(DBDRIVER);
			System.out.println("�����ɹ�chaxun");
			//�������ݿ�
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
