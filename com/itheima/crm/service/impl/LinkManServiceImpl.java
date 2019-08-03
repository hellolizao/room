package com.itheima.crm.service.impl;
import java.util.List;
import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
       PageBean<LinkMan> pageBean= new PageBean<LinkMan>();
       pageBean.setCurrPage(currPage);
       pageBean.setPageSize(pageSize);
       //设置总的记录数

        Integer totalCount=linkManDao.findCount(detachedCriteria);

        pageBean.setTotalCount(totalCount);
        //设置总的页数
        Double tc=totalCount.doubleValue();

        Double totalPage=Math.ceil(tc/pageSize);
        pageBean.setTotalPage(totalPage.intValue());
        //获取到每页的linkman的list集合
        Integer begin=(currPage-1)*pageSize;


        List<LinkMan> list= linkManDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {

        return linkManDao.findById(lkm_id);
    }

    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }
}
