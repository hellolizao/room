<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript"src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

//这里采用的Ajax的JQuery的json的格式的post格式来异步的处理的页面加载（查询字典数据并显示）
$(function() {
	$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"002"},function(data,status){
		//将异步获取的data里的数据遍历出来，并逐个的添加到的option中
		//这个是来源
		$(data).each(function(i,n) {
			//选中要被操作的节点
			$("#cust_source").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")		
		});
		$("#cust_source option[value='${baseDictSource.dict_id}']").prop("selected","selected");
	},"json");
	
	$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(data,status){
		//这个是“级别”		
		$(data).each(function(i,n) {
			$("#cust_level").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")		
		});
		$("#cust_level option[value='${baseDictLevel.dict_id}']").prop("selected","selected");
	},"json");
	
	$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"001"},function(data,status){
		//这个是行业
		$(data).each(function(i,n) {
			$("#cust_industry").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")		
		});
		
		$("#cust_industry option[value='${baseDictIndustry.dict_id}']").prop("selected","selected");
		
	},"json");	
});
</script>




<SCRIPT language=javascript>
	function to_page(page){//这里会根据选中页数时将currPage提交到customerAction中
		if(page){//如果没有提交的page就直接的执行的document.customerForm.submit()提交表单
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findAll.action"
		method=post>
		
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="cust_name" value="<s:property value="cust_name" />" ></TD><!-- 进行数据的回显 -->
													<TD>客户来源：</TD>
													<TD>
														<select id=cust_source name="baseDictSource.dict_id"><!-- 这里的name的属性值必须是该客户id -->
															<option>--请选择--</option>
															
														</select>
													
													</TD>
													<TD>客户级别：</TD>
													<TD>
														<select id=cust_level name="baseDictLevel.dict_id">
															<option>--请选择--</option>
															
														</select>
													
													</TD>
													<TD>客户行业：</TD>
													<TD>
														<select id=cust_industry name="baseDictIndustry.dict_id">
															<option>--请选择--</option>
															
														</select>
													
													</TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list"><!-- 这里可以从list取值是因为在action中将pageBean以push的方式存放到值栈中，在栈顶，可以直接的取pageBean中的属性list -->
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="cust_name"/></TD>
													<TD><s:property value="baseDictLevel.dict_item_name"/></TD>
													<TD><s:property value="baseDictSource.dict_item_name"/></TD>
													<TD><s:property value="baseDictIndustry.dict_item_name"/></TD>
													<TD><s:property value="cust_phone"/></TD>
													<TD><s:property value="cust_mobile"/></TD>
													
													
													<TD>
													<a href="${pageContext.request.contextPath }/customer_edit.action?cust_id=<s:property value="cust_id"/>">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customer_delete.action?cust_id=<s:property value="cust_id"/>">删除</a>
													</TD>
												</TR>
												</s:iterator>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"/></B>]页
												,每页显示
												
												<select name="pageSize" onchange="to_page()"><!--以供选择下拉选项，决定每页显示多少条的数据 当value的值改变了就会自动的取访问to_page()并提交表单-->
												
													<!-- <option>标签里的s:if test标签的pageSize是取值栈中pageBean中的pageSize的实际的值是多少：如是2，就在jsp中的下拉选项显示2:
														比如这里的在jsp页面中的选择的的数字的3，那么提交上去后在pageBean中得到的pageSize就是3，如是回到的jsp中获取值的时候是的pageSize=3,如是此时显示的数字2。
														
														总结：jsp中的数字的显示是根据表单提交后的读取的pageSize的值。
													
													
													-->
													<option value="1"	<s:if test="pageSize==1">selected</s:if>  >1</option>
													<option value="2"   <s:if test="pageSize==2">selected</s:if>  >2</option>
													<option value="3"   <s:if test="pageSize==3">selected</s:if>  >3</option>
												
													<!-- <option value="1"	<s:if test="pageSize==2">selected</s:if>   >1</option>
													<option value="2"   <s:if test="pageSize==3">selected</s:if>   >2</option>
													<option value="3"   <s:if test="pageSize==1">selected</s:if>   >3</option>
													 -->
													
												</select>
												条
												<s:if test="currPage!=1">
												[<A href="javascript:to_page(<s:property value="1"/>)">首页</A>]
												[<A href="javascript:to_page(<s:property value="currPage-1"/>)">前一页</A>]
												</s:if>&nbsp;&nbsp;
												<B>
													<!-- 这里的页数 -->
													<s:iterator var="i" begin="1" end="totalPage">
														<s:if test="#i==currPage">
															<s:property value="#i"/>
														</s:if>

														<s:else>
															<!-- 这里选中的的时候就会自动的取请求的action,并将当前页提交到的to_page()中 -->
															<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/></a>
														
														</s:else>													
													</s:iterator>
												
												</B>&nbsp;&nbsp;
												
												<s:if test="currPage!=totalPage">
												[<A href="javascript:to_page(<s:property value="currPage+1"/>)">后一页</A>] 
												[<A href="javascript:to_page(<s:property value="totalPage"/>)">尾页</A>]
												</s:if> 
												到
												<input type="text" size="3" id="page" name="currPage" /> <!-- 这里获取到是function中的to_page()中 -->
												页
												
												<input type="button" value="Go" onclick="to_page()"/><!-- 这个是在下拉选项中的选择每页显示的记录数。并将记录数赋到id=page的input中 -->
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
	</FORM>
</BODY>
</HTML>


