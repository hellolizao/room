package com.itheima.crm.dao;

import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface LinkManDao {

    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    void save(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
