package com.itheima.web.action;

import org.springframework.dao.support.DaoSupport;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	private User user=new User();
	@Override
	public User getModel() {
		return user;
	}

	
	public String regist() {
		//这里个是用用户注册时向数据库保存客户信息
		
		//将获取到的 用户的密码进行简单加密
		user .setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		
		userService.save(user);
		
		return LOGIN;
		
	}
	
	public String login() {
		//这里实现的用户 登录的请求处理
		/**
		 *1:登录的用户是否已经存在在数据库了。对这里两种状况做不同的处理
		 *	1-1：通过调用的业务层来实现对数据库的查询
		 * 
		 */
		User exitUser=userService.login(user);
		
		if(exitUser==null) {
			//这里的用户没有注册就来得登录。将提示信息"回显"到jsp页面中的
			this.addActionError("用户名或是密码错误");
			return LOGIN;
		}else {
			//登录成功将数据存放到的session中
			ActionContext.getContext().getSession().put("exitUser", exitUser);
			
			return SUCCESS;
		}
	}
	
	
	
}
