package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;



public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>  {
	private BaseDict baseDict =new BaseDict();
	
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	public String findByTypeCode() throws IOException {
	//这里负责的是在加载add.jsp页面时异步的查询字典数据（是在add.jsp中的请求），
		
		System.out.println("asdfasdfasd");
		//调用业务层来查询字典
		List<BaseDict> list=baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		//到这里已经经过了service和dao层获取到查询数据库的得到字典的数据了。。在jsp中的请求这个action的目的就是为了获取到baseDict的数据，然后保存在的作用域中再在jsp页面获取到并显示
		//因为在jsp中的采用的异步是post的响应回来的必须是json格式
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String []{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		String json = jsonArray.toString();
		
		//将异步的获取的数据打印到页面上
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		System.out.println(json+"..................");
		return NONE;
		
		
		
		
	}
	
	
	
	
	
}
