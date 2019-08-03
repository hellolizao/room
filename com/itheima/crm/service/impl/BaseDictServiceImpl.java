package com.itheima.crm.service.impl;

import java.util.List;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.itheima.crm.service.CustomerService;

public class BaseDictServiceImpl implements BaseDictService {
	
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		//这里的负责的是在加载add.jsp页面的同时，异步的加载字典的数据的读取，并将其显示在页面中
		return baseDictDao.findByTypeCode(dict_type_code);
		
		 
	}
	

}
