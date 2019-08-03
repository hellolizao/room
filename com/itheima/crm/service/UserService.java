package com.itheima.crm.service;

import com.itheima.crm.domain.User;

public interface UserService {

	void save(User user);

	User login(User user);

}
