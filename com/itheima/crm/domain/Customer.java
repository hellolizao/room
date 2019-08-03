package com.itheima.crm.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Long cust_id;
	private String cust_name;
	/*
	 * private String cust_source; private String cust_industry; private String cust_level;/
	 * 这三个都是以字典表中主键作为外键的。其数据的来源都是来自字典表，所以这里的需要维护其对象与之产生关系
	 * 
	 */
	private String cust_image;//设置的上传文件时将文件的保存路径存储到数据库
	//因为再创建的linkman中的对customer是多对一。一个customer对应着的多个linkman
	private Set<LinkMan> linkManSet=new HashSet<LinkMan>();//***这里的创建这个set集合会在级联的一系列的操作中的起到重要作用

	public Set<LinkMan> getLinkManSet() {
		return linkManSet;
	}

	public void setLinkManSet(Set<LinkMan> linkManSet) {
		this.linkManSet = linkManSet;
	}

	public String getCust_image() {
		return cust_image;
	}
	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}

	private BaseDict baseDictIndustry;
	private BaseDict baseDictLevel;
	private BaseDict baseDictSource;
	public BaseDict getBaseDictSource() {
		return baseDictSource;
	}
	public void setBaseDictSource(BaseDict baseDictSource) {
		this.baseDictSource = baseDictSource;
	}
	public BaseDict getBaseDictIndustry() {
		return baseDictIndustry;
	}
	public void setBaseDictIndustry(BaseDict baseDictIndustry) {
		this.baseDictIndustry = baseDictIndustry;
	}
	public BaseDict getBaseDictLevel() {
		return baseDictLevel;
	}
	public void setBaseDictLevel(BaseDict baseDictLevel) {
		this.baseDictLevel = baseDictLevel;
	}
	
	private String cust_phone;
	private String cust_mobile;
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	

}
