<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">已申请的顾客</h3>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					
					<th>顾客名</th>
					<th>联系方式</th>
					<th>地址</th>
					<th>密码</th>
				</tr>
			</thead>
			<tbody id="customerTable">
				<s:iterator value="customers" var="customer">
					<tr>
						<td style="vertical-align: middle;">
							<s:property value="#customer.name"/></td>
						<td style="vertical-align: middle;"><s:property
								value="#customer.phoneNum" /></td>
	
						<td style="vertical-align: middle;"><s:property
								value="#customer.address" /></td>
						<td style="vertical-align: middle;">
							<s:property value="#customer.password"/>
						</td>
						
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>

