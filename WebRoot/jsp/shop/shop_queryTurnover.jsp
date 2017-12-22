<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
		<h4>
			总计已赚取<s:property value="turnover"></s:property> 元
		</h4>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>时间</th>
					<th>餐品名</th>
					<th>价格</th>
				</tr>
			</thead>
			<tbody id="foodTable">
				<s:iterator value="shop.ordeers" var="order">
					<tr>
						<td style="vertical-align: middle;"><s:property
								value="#order.orderTime" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.name" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.price" />元</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

