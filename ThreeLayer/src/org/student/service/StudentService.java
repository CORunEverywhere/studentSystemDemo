package org.student.service;

import java.util.List;

import org.student.dao.StudentDao;
import org.student.entity.Student;

//业务逻辑层，对dao进行组装
public class StudentService {
	StudentDao studentDao = new StudentDao();
	
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	
	public List<Student> queryAllStudents() {
		return studentDao.queryAllStudentBySno();
	}
	
	public List<Student> queryStudentsByPage(int currentPage,int pageSize) {
		return studentDao.queryStudentsByPage(currentPage,pageSize);
	}
	
	public boolean updateStudentBySno(int sno,Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno,student);
		}
		return false;
	}
	
	public boolean deleteStudentBySno(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno);
		}else {
			return false;
		}
	}
	
	public boolean addStudent(Student student) {
		if(!studentDao.isExist(student.getSno())) {
			studentDao.addStudent(student);
			return true;
		}else {
			System.out.println("此人已经存在！");
			return false;
		}
	}
	
	public int getTotalCount() {
		return StudentDao.getTotalCount();
	}

}
