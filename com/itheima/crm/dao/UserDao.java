package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

public interface UserDao {

	void save(User user);

	User login(User user);


}
