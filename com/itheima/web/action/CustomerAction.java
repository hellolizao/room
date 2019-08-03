package com.itheima.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer>  {
	
	/**
	 * 提供三个用于文件上传需要的变量（这些名称都是由action中默认的拦截器规定的） , 因为不能用的模型驱动将这些数据封装，于是用的属性驱动的set方法将其封装。
	 */
	private String uploadFileName;//文件名称
	private String uploadContentType;//类型
	private File upload;//文件本身....这个文件本身是来自在表单中的上传的文件。我们需要的做的是确定的文件保存到服务器的具体的路径。和将上传文件复制到该路径上，还有就是保存该路径到数据库中
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	
	
	private Customer customer =new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	private Integer currPage=1;//这个是struts2中的属性驱动的set方法来获取的页面中提交的数据
	public void setCurrPage(Integer currPage) {
		//对提供的currPage的初始化状态作判断
		if(currPage==null) {
			currPage=1;
		}
		this.currPage = currPage;
	}
	private Integer pageSize=3;//这个是通过在list.jsp中的<select>标签的事件提交的
	public void setPageSize(Integer pageSize) {//这里是设置一个下拉列表来实现每页的显示的记录数
		//对提供的pageSize的初始化状态作判断
		if(pageSize==null) {
			pageSize=3;
		}
		
		this.pageSize = pageSize;
	}
	
	
	
	
	public String saveUI() {//单纯的页面跳转
		return "saveUI";
	}
	
	
	
	
	public String save() throws Exception {//这里负责的是将页面中表单提交的数据保存到的数据库中，并将上传的图片的保存到服务器的路径保存到数据库中
		
		if(upload!=null) {//如果上传了文件
			/**
			 * 这里完成文件的上传。
			 * 	1：为了避免文件名重复造成的覆盖。这里将文件名弄成随机的
			 * 	2：明确这些文件上传到的目的地在哪
			 * 	3：将上传的文件弄到指定的的保存路径
			 * 	4：将图片的路径也保存到的数据库中
			 */
			String path="F:\\upload\\"+uploadFileName;
			File destFile=new File(path);
			FileUtils.copyFile(upload, destFile);
			customer.setCust_image(path);//将图片的保存的路径也保存到的数据库中	
		}
		//保存客户
		customerService.save(customer);
		return "saveSuccess";
	}
	
	
	
	
	public String findAll() {
		//这里的实现的在menu.jsp中点击“客户列表”时分页的查询出所有的客户的信息
		//利用的Struts2中的set方法来接收currPage;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//在web层设置条件查询的
//		if(customer.getCust_name()!=null){
//			detachedCriteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
//		}
		
	
		
		
		if(customer.getBaseDictIndustry()!=null&&customer.getBaseDictIndustry().getDict_id()!=null){
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id",customer.getBaseDictIndustry().getDict_id()));
			}
			
		}

		
		
//		if(customer.getBaseDictSource()!=null&&customer.getBaseDictSource().getDict_id()!=null){
//			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())){
//			
//			detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id",customer.getBaseDictSource().getDict_id()));
//				}
//			
//		}
//		
//		
//		if(customer.getBaseDictLevel()!=null&&customer.getBaseDictLevel().getDict_id()!=null){
//			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())){
//				
//				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id",customer.getBaseDictLevel().getDict_id()));
//			}
//			
//		}
//		 
		
		PageBean<Customer> pageBean=customerService.findByPage(detachedCriteria,currPage, pageSize);
		//将pageBean存放到valueStack中
		ActionContext.getContext().getValueStack().push(pageBean);//这样就可以在Struts2中的任何的地方都可以获取值栈中的数据
		
		return "findAll";
	}
	
	
	public String delete() {//实现后台的删除客户
		
		//先进行查询，在尽心删除
		
		customer=customerService.findById(customer.getCust_id());//这里的必须是要先进行查询才行。这样才可以在配置文件中配置级联删除的参数cascade=delete"
		
		//先删除图片
		if(customer.getCust_image()!=null) {
			File file=new File(customer.getCust_image()); //知识点：
			if(file.exists()){
				file.delete();
			}
			
		}
		//删除客户
		customerService.delete(customer);
		
		return "deleteSuccess";
	}
	
	public String edit() {
		//要想实现修改就必须要进行修改。这样还可以实现在异步的情况下实现回显的动作（jsp中遍历得到的数据和查询得到的数据一样，就将这个数据selected选中固定在jsp的框中，这样就实现了回显
		customer=customerService.findById(customer.getCust_id());

		//将查询得到的customer存放到valueStack中
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	
	public String update() throws IOException {
		//进行对客户修改后的保存的动作
		/*1:判断的修改的页面中是否对上传的文件作出了修改
		 *2:因为在表单中的已经的隐藏了cust_image的上传。所以如果客户修改了上传的文件，就要将原有的文件删除，重新的上传
		 *
		 * 
		 */
		if(upload!=null){//客户重新上传了文件
			//获取到原本隐藏上传的图片路径
			String cust_iamge=customer.getCust_image();//这个是服务器中保存的图片的路径
			if(cust_iamge!=null||!"".equals(cust_iamge)) {
				//将服务器中的保存的路径上的文件删除
				File file = new File(cust_iamge);
				file.delete();//已经将该服务器中的以前的文件删除了
			}
			//将重新上传的文件保存到的数据库中保存的路径上
			String path="F:\\upload\\"+uploadFileName;
			File destFile=new File(path);
			FileUtils.copyFile(upload, destFile);
			
			customer.setCust_image(path);

		}
		customerService.update(customer);//没有修改时就还是用原来的
		return "updateSuccess";
		
	}

}
