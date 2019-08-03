package com.itheima.crm.domain;

import java.util.List;

public class PageBean<T> {
	
	private Integer currPage;//当前页(比如这是第3页了
	private Integer pageSize;//每页显示的记录数
	private Integer totalCount;//总的记录数
	private Integer totalPage;//总的页数
	private List<T> list;//当前页的查询得到的客户的全部的信息数据
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	

}
