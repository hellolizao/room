package com.itheima.web.action;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.util.Date;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private  SaleVisit saleVisit=new SaleVisit();
    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }
    //使用属性注解的方式
   @Resource(name="saleVisitService")
   private SaleVisitService saleVisitService;

    private Integer currPage=1;
    private Integer pageSize=3;

    public void setCurrPage(Integer currPage) {
        if (currPage==null){
            currPage=1;
        }

        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize==null){
            pageSize=3;
        }
        this.pageSize = pageSize;
    }

    private Date visit_end_time;//这个是在jsp中进行拜访时间的筛选和回显在action中定义的使用的set属性驱动方式获取的属性值的方式，这样才能在jsp中从存放在的vvalueStack的action对象中的通过get方式获取进行回显（必须提供get方式）
    public Date getVisit_end_time() {
        return visit_end_time;
    }

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public String findAll(){//查询客户拜访列表

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);

        //添加条件(进行list。jsp中的日期的筛选客户拜访记录
        if (saleVisit.getVisit_time()!=null){//如果jsp中这个是下拉选项则还要并列的加上对""的情况的判断
            detachedCriteria.add(Restrictions.ge("visit_time",saleVisit.getVisit_time()));//大于这个时间
        }
        if (visit_end_time!=null){
            detachedCriteria.add(Restrictions.le("visit_end_time",visit_end_time));//小于这个时间
        }

        PageBean<SaleVisit> pageBean=saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);

        return "findAll";

    }


    public  String saveUI(){//新增客户拜访的的跳转

        return "saveUI";

    }
    public String save(){//新增客户拜访的保存

        saleVisitService.save(saleVisit);

        return "saveSuccess";
    }


}
