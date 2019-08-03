package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
