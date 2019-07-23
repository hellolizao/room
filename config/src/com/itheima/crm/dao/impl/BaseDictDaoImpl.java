package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.BaseDict;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		//这里的负责的是在加载add.jsp页面的同时，异步的加载字典的数据的读取，并将其显示在页面中
		
		return	(List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?",dict_type_code );
		
	}

}
