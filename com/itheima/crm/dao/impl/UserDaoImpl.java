package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		
		HibernateTemplate template = this.getHibernateTemplate();
		template.save(user);
		
		
		
	}

	@Override
	public User login(User user) {
		//这里负责的额用户登录时请求的处理
		/**
		 *1： 这里的查询数据库时会出现用户存在或是不存在的情况
		 *	1-1：直接调用继承得到的HibernateTemplate模板查询
		 *2：对查询得到的user对象判断(
		 *
		 */
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		if(list.size()>0) {
			return list.get(0);
		}
		return null;//不存在就放回null；
	}

}
