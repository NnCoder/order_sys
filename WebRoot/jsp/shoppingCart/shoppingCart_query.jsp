<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel" style="border-radius: 30px;">
	
	<div class="panel-body">
	<div style="min-height:150px;height: auto; color: #000;">
		<h3>购物车</h3>
		<h5><s:if test="shoppingCarts.isEmpty()">你的购物车空空如也,快去找点吃的吧</s:if>
		<s:else>你的购物车里一共有<s:property value="shoppingCarts.size()"></s:property>个食物,尽快下单吧~</s:else>
		</h5>
		<div style="margin-top: 60px;">
		<table class="table" style="background-color: #FFF;border-radius: 15px;box-shadow:#666 0px 0px 15px;">
			<thead>
				<tr>
					<th></th>
					<th>餐品名</th>
					<th>描述</th>
					<th>价格</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="foodTable">
				<s:iterator value="shoppingCarts" var="sc">
					<tr>
						<td style="vertical-align: middle;"><img
							src='<s:property value="#sc.food.imgSrc"/>' width="64" height="64" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#sc.food.name" /></td>
						<td
							style="vertical-align: middle;word-wrap:break-word;word-break:break-all;"
							width="25%"><s:property value="#sc.food.description" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#sc.food.price" />元</td>
						<td style="vertical-align: middle;"><button
								class="btn btn-danger" onclick="foodToOrder(<s:property value='#sc.food.id'/>,<s:property value='#sc.shop.id'/>)">立即下单</button></td>
						<td style="vertical-align: middle;"><button class="btn btn-danger" onclick="removeShoppingCart(event,<s:property value="#sc.id"/>)">从购物车中移除</button></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
	</div>
</div>
<div id="foodToOrderModalDiv"></div>
