package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void save(Customer customer) {
		
		this.getHibernateTemplate().save(customer);
		
	}

	@Override
	
	//带条件detachedCriteria查询总的记录数
	public Integer findCount(DetachedCriteria detachedCriteria) {
		
		detachedCriteria.setProjection(Projections.rowCount());
		/**
		 * 这里传入进来的detachedCriteria相当于是select * from detachedCriteria 
		 */
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0) {
			return list.get(0).intValue();//list中只要关于客户的记录数，没有别的记录数（将Long转为int类型
		}
		return null;
	}

	@Override//获取到的当前页中的显示的所有的客户的信息。都是放置在list集合中。
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		/*
		 * 因为detachedCriteria都是从action中的创建的一个。在dao中的findCount（）中已经将这个detachedCriteria的做了改变：Projections.rowCount()
		 * detachedCriteria是共享的。所以这里需要将其设置成开始的样子
		 * 
		 */
		detachedCriteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);//这就查询到了该页中显示的客户的所有的信息
	}

}
