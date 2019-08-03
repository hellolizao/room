package com.itheima.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DaoSupport;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
   private LinkMan linkMan =new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    private LinkManService linkManService;//注入servcie;
    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    //这里需要进行分页的操作,,//这里是通过struts2的属性驱动的set方法来获取属性(无法通过model的模型驱动来封装数据）
   private  Integer currPage=1;

    public void setCurrPage(Integer currPage) {
        if (currPage==null){
            currPage=1;
        }
        this.currPage = currPage;
    }

    private  Integer pageSize=3;

    public void setPageSize(Integer pageSize) {
        if (pageSize==null){
            pageSize=3;
        }
        this.pageSize = pageSize;
    }

    //注入customerService。。因为在linkMan 中的存在的customer的对象的引用
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //定义一个查询联系人的方法
    public String findAll(){
    //利用hibernate的离线条件来实现查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        /**
         * 实现在筛选查询
         */
        if (linkMan.getLkm_gender()!=null&&!"".equals(linkMan.getLkm_gender())){//注意这里的性别的查询条件的设置
            detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
        }

        if (linkMan.getLkm_name()!=null){
            detachedCriteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }

        PageBean<LinkMan> pageBean =linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);


        return "findAll";

    }

    public  String saveUI(){//联系人管理中的新增联系人

        List<Customer> list=customerService.findAll();

        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";


    }

    public  String save(){

        linkManService.save(linkMan);
        return "saveSuccess";

    }

    //实现对联系人管理中的联系人修改
    public String edit(){

        List<Customer> customers = customerService.findAll();

        linkMan=linkManService.findById(linkMan.getLkm_id());//这里的lim_id已经是要修改的联系人的id了，因为在list.jsp中的请求这个action的时候将其id带上来了（通过模型驱动将其封装）
        ActionContext.getContext().getValueStack().set("list",customers);

        ActionContext.getContext().getValueStack().push(linkMan);//linkMan也是可以不用这样的放置在值栈中，在model中存在linkMan（直接在值栈中方便回显）
        return "editSuccess";


    }

    public  String update(){
        linkManService.update(linkMan);//这里从model中获取到的linkMan 是edit中提交的内容
        return "updateSuccess";
    }

    public String delete(){
        LinkMan linkMan = linkManService.findById(this.linkMan.getLkm_id());

        linkManService.delete(linkMan);

        return "deleteSuccess";


    }


}
