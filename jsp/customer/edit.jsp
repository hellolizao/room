﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="/struts-tags" prefix="s"%>
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
			$("#cust_source option[value='${baseDictSource.dict_id}']").prop("selected","selected");//在cust_source中的查找value的属性值是“~”的option元素(这里“~”是在jsp页面中选中的那个选项的id。选中哪个就是哪个)
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
	//使用的EL来获取到值栈中的数据
	$("#cust_industry option[value='${baseDictIndustry.dict_id}']").prop("selected","selected");//这里的value属性的值取的是baseDictionaryIndustry.dict_id,
	//不同于上面的遍历使用的n.dict_id是因为这个是在比遍历的函数之外。不能是用遍历得到的data。
	//这里的回显的原理：因为在customerAction中在edit的函数(点击“修改”时访问该action）中进行了一次对customer的查询。
	//这里是将查询得到的放置在值栈中的customer中的属性的值和下拉框中对应的属性值一致时，就将该属性值固定到下拉框中
	},"json");
	
	
});



</script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>


	<s:actionerror/><!-- 回显 -->
	<s:fielderror/>
	<s:form action="customer_update" name="form1" id="form1" method="post" enctype="multipart/form-data" theme="simple">
	<!-- 表单中默认的文件上传是只能上传名称，并不能将文件的内容上传。这里是设置了可以上传内容 ,并取消UI标签的默认的嵌套的表格的格式效果-->
	
	<!-- 因为的修改后要保存。这里还要将客户的id和上传的图片的路径隐藏式的提交 -->	
	<s:hidden name="cust_id" value="%{cust_id}"/>
	<s:hidden name="cust_iamge" value="%{cust_image}"/>
	
	
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
								
								<!-- 使用Struts2的UI标签进行回显
						注释：这里回显的机制是：当出现了错误时。会回去对比值栈中的customer中的属性的名称：比如这里的cust_name，和UI标签中的name的值相比较，如果是一样就进行回显
								
								 -->
								<s:textfield cssClass="textbox" cssStyle="WIDTH: 180px"  maxLength="50" name="cust_name"/><!-- 这里的name 就是为了可以对比找出值栈中的存储着数据的modele模型中对应别的属性值，进行回显到这UI标签中的value的值中 -
								
								
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
									<option value="">--请选择行业--</option>
									<!-- 剩下的以动态的形式获取到 -->
								</select>						
														
														
								</td>
							</TR>
							
							<TR>
							
							
							
								
								<td>固定电话 ：</td>
								<td>
								
								<s:textfield cssClass="textbox" cssStyle="WIDTH: 180px"  maxLength="50" name="cust_phone"/>
								
								
								</td>
								<td>移动电话 ：</td>
								<td>
								
								<s:textfield cssClass="textbox" cssStyle="WIDTH: 180px"  maxLength="50" name="cust_mobile"/>
								
								</td>
							</TR>
							
							<TR>
								
								<td>客户资质 ：</td><!-- 这个是添加的上传文件 -->
								<td colspan="3"><!-- 这里是将原本的两个<td>标签整合了。在加上自己的这个一起是3列 -->
									<input type="file" name="upload"/> <!-- upload是文件上传项的名称 -->
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
	</s:form>
</BODY>
</HTML>
