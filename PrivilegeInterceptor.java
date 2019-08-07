package com.itheima.web.interceptor;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        /*拦截器：没有登录就无法查看页面，提示必须去登录才行
        思想：判断session是否存在的客户信息（这个信息是客户端带上来的）
         */
        User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(existUser==null){
            ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("请先去登录！！！");
            return actionSupport.LOGIN;

        }else {
            //登录了就放行去执行被的拦截器(拦截器是相互的调用执行）
         return    actionInvocation.invoke();
        }

    }
}
