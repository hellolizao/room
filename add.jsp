﻿<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户拜访记录</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
    <script type="text/javascript"src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

    <script type="text/javascript">
    $(function () {/*异步加载拜访记录数据中的客户信息*/
        $.post("${pageContext.request.contextPath}/customer_findAllCustomer.action",{},function (date,status) {
            $(date).each(function (i,n) {/*异步加载拜访记录数据中的用户信息*/

                $("#customer").append("<option value='"+n.cust_id+"'>"+n.cust_name+"</option>");

            });

        },"json");

        $.post("${pageContext.request.contextPath}/user_findAllUser.action",{},function (date,status) {
            $(date).each(function (i,n) {/*异步加载拜访记录数据中的用户信息*/

                $("#user").append("<option value='"+n.user_id+"'>"+n.user_name+"</option>");

            });

        },"json");

    });



    </script>
    <!-- 日期插件，使用jquery -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#visit_time').datepick({dateFormat: 'yy-mm-dd'});
            $('#visit_nexttime').datepick({dateFormat: 'yy-mm-dd'});
        });
    </script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:form  id="form1" name="form1" action="saleVisit_save" namespace="/" method="post" theme="simple">
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访记录管理 &gt; 添加客户拜访记录</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>拜访客户：</td>
                                <td>
                                <select name="customer.cust_id" id="customer">
                                    <option value="">-请选择-</option>

                                </select>
								</td>

                                <td>业务员名称：</td>
                                <td>
                                    <select name="user.user_id" id="user">
                                        <option value="">-请选择-</option>
                                    </select>
                                </td>
							</tr>

                            <TR>

                                <td>拜访时间：</td>
                                <td>
<%--                                    <input type="text" id="visit_time" name="visit_time" readonly="readonly" value="<s:date name="visit_time" format="yyyy-MM-dd"/>"/>--%>

<%--                                    <input type="text"  id="visit_time" name="visit_time" value="<s:date name="visit_time" format="yyyy-MM-dd"/> " >--%>

                                    <s:textfield readonly="true" id="visit_time" cssClass="textbox" cssStyle="WIDTH: 180px" name="visit_time" />
                                </td>
                                <td>拜访地点 ：</td>
                                <td>
                                    <s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxLength="50" name="visit_addr"/>
                                </td>
                            </TR>
                            <TR>
                                <td>拜访详情 ：</td>
                                <td>
                                    <s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxLength="50" name="visit_detail"/>
                                </td>
                                <td>下次拜访时间 ：</td>
                                <td>
<%--                                    <s:date name="visit_nexttime" id="visit_nexttsme"/>--%>
                                        <s:textfield readonly="true" id="visit_nexttime" cssClass="textbox" cssStyle="WIDTH: 180px" maxLength="50" name="visit_nexttime"/>
                                </td>
                            </TR>

							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
</HTML>
