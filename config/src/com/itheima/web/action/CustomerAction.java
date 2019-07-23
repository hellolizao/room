package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import sun.net.www.content.text.plain;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>  {
	private Customer customer =new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String saveUI() {//单纯的页面跳转
		
		return "saveUI";
		
		
	}
	public String save() {
		//这里负责的是将页面中表单提交的数据保存到的数据库中
		customerService.save(customer);
		return NONE;
		
	}
	
	private Integer currPage=1;//这个是struts2中的属性驱动的set方法来获取的页面中提交的数据
	public void setCurrPage(Integer currPage) {
		//对提供的currPage的初始化状态作判断
		if(currPage==null) {
			currPage=1;
		}
		this.currPage = currPage;
	}
	private Integer pageSize=3;//这个是通过在list.jsp中的<select>标签的事件提交的
	public void setPageSize(Integer pageSize) {//这里是设置一个下拉列表来实现每页的显示的记录数
		//对提供的pageSize的初始化状态作判断
		if(pageSize==null) {
			pageSize=3;
		}
		
		this.pageSize = pageSize;
	}

	public String findAll() {
		//这里的实现的在menu.jsp中点击“客户列表”时分页的查询出所有的客户的信息
		//利用的Struts2中的set方法来接收currPage;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		PageBean<Customer> pageBean=customerService.findByPage(detachedCriteria,currPage, pageSize);
		//将pageBean存放到valueStack中
		ActionContext.getContext().getValueStack().push(pageBean);//这样就可以在Struts2中的任何的地方都可以获取值栈中的数据
		
		return "findAll";
	}
	

	
}
