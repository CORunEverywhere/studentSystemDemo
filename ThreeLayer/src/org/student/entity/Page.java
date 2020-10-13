package org.student.entity;

import java.util.List;

//��ҳ������
public class Page {
	private int currentPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	private List<Student> students;
	public Page() {

	}
	public Page(int currentPage, int pageSize, int totalCount, int totalPage, List<Student> students) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.students = students;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	//�Զ�������ҳ��
	//��ҳ��=��������%ҳ���С==0����������/ҳ����������������/ҳ������+1
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage() {
		this.totalPage = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
