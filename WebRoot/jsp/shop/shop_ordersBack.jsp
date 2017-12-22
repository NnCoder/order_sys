<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="panel">
	<div class="panel-heading">
	退单中的
		<h3 class="panel-title"><s:if test="order_type==0">外卖订单</s:if>
		<s:else>店内订单</s:else></h3>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>餐品名</th>
					<th>价格</th>
					<th>顾客姓名</th>
					<th>顾客地址</th>
					<th>顾客联系方式</th>
					<th>备注</th>
					<th>状态</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="foodTable">
				<s:iterator value="shop.ordeers" var="order">
					<tr>
						<td style="vertical-align: middle;"><img
							src='<s:property value="#order.food.imgSrc"/>' width="64" height="64" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.name" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.price" />元</td>
						<td style="vertical-align: middle;"><s:property
								value="#order.customer.name" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.customer.address" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.customer.phoneNum" /></td>
						<td style="vertical-align: middle;">
							<p style="word-break:break-all; width: 120px;">
								<s:property value="#order.remarks"/>
							</p>
						</td>
						<td style="vertical-align: middle;"><span style="color:red;">退单中</span></td>
						<td style="vertical-align: middle;"><button
								class="btn btn-default" onclick="confirmOrderBack(event,<s:property value="#order.customer.id"/>,<s:property value='#order.id'/>)">确认退单</button></td>
						
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
