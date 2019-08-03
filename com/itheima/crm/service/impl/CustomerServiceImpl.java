package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		//保存表单中的提交的数据到数据库中
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		
		//在这里将Dao层需要的参数在这里的全部的获取的并封装到的PageBean中
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrPage(currPage);
		//这里设置的currpage是指的第几页开始，
		
		pageBean.setPageSize(pageSize);
		
		//封装总的记录数到pageBean中(这里总的记录数必须是通过dao去查询数据库得到
		Integer	totalCount =customerDao.findCount(detachedCriteria);//这里是必须将这离线条件传入
		pageBean.setTotalCount(totalCount);
		
		//封装总的 页数
		Double tc = totalCount.doubleValue();
		Double num=Math.ceil(tc/pageSize);//这个是向上取整：3.1就给取成4
		pageBean.setTotalPage(num.intValue());//这里要将Double的转为Int类型
		
		
		//根据当前页来显示从第几条数据开始显示
		Integer begin=(currPage-1)*pageSize;
		//通过调用的dao层来获取到每页的数据
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		//封装list（这个是每页中的客户的信息
		
		
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {//删除客户前先进行的查询
		return customerDao.findById(cust_id);
		
	}

	@Override
	public void delete(Customer customer) {//根据查询得到的客户进行删除
		customerDao.delete(customer);
		
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}

	@Override
	public List<Customer> findAll() {

		return customerDao.findAll();
	}


}
