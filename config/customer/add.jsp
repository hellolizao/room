<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
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
	},"json");
	
	$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(data,status){
		//这个是“级别”		
		$(data).each(function(i,n) {
			$("#cust_level").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")		
		});
	},"json");
	
	$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"001"},function(data,status){
		//这个是行业
		$(data).each(function(i,n) {
			$("#cust_industry").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")		
		});
	},"json");
	
	
});



</script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/customer_save.action"
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
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_name">
								</td>
								
								
								
								
								<td>客户级别 ：</td>
								<td>
																						
								<select id="cust_level" name="baseDictLevel.dict_id">
									<option value="">--请选择级别--</option>
									<!-- 剩下的以动态的形式获取到 -->
								</select>						
														
								</td>
							</TR>
							
							<TR>
								 
								<td>信息来源 ：</td>
								<td>
																						
								<!-- 将这里作为一个下拉选项，将上面异步的从的baseDictAction中获取数据加载到的这里以供选择 -->						
								<select id="cust_source" name="baseDictSource.dict_id">
									<option value="">--请选择来源--</option>
									<!-- 剩下的以动态的形式获取到 -->
								</select>
														
								</td>
								<td>所属行业 ：</td>
								<td>
													
								<select id="cust_industry" name="baseDictIndustry.dict_id">
									<option value="">--请选择行 业--</option>
									<!-- 剩下的以动态的形式获取到 -->
								</select>						
														
														
								</td>
							</TR>
							
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile">
								</td>
							</TR>
							
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
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
	</FORM>
</BODY>
</HTML>
