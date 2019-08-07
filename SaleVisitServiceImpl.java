package com.itheima.crm.service.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import javax.annotation.Resource;
import java.util.List;

public class SaleVisitServiceImpl implements SaleVisitService {

    @Resource(name="saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        //完善pageBean的属性设置
        PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount=saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        double tc=totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num. intValue());
        //每页要从哪条数据开始显示
        Integer begin=(currPage-1)*pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
