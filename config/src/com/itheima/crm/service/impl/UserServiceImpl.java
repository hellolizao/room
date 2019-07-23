package com.itheima.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
@Transactional
public class UserServiceImpl implements UserService {

	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(User user) {
		//这里负责的是用户注册保存
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		//这里的负责的用户登录处理
		/**
		 * 1:因为保存在数据库中的用户的密码都是经过md5加密的。为了能过对比查询这里必须先将其同样加密
		 */
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		
		return userDao.login(user);
	}

}
