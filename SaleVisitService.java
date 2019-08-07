package com.itheima.crm.service;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService    {
     PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

     void save(SaleVisit saleVisit);
}
