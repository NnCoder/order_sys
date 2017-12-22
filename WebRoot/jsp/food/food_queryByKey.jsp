<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel" style="border-radius: 30px;">
	
	<div class="panel-body">
	<div style="min-height:150px;height: auto; color: #000;">
		<input type="text" class="input-lg" id="search_food" placeholder="搜索菜品"><button class="btn btn-info" onclick="searchFood()">搜索</button>
		<h3>
			共有<s:property value="foods.size()"></s:property>个搜索结果
		</h3>
		<div style="margin-top: 120px;">

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
				<s:iterator value="foods" var="food">
					<tr>
						<td style="vertical-align: middle;"><img
							src='<s:property value="#food.imgSrc"/>' width="64" height="64" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#food.name" /></td>
						<td
							style="vertical-align: middle;word-wrap:break-word;word-break:break-all;"
							width="25%"><s:property value="#food.description" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#food.price" />元</td>
						<td style="vertical-align: middle;"><button
								class="btn btn-danger" onclick="foodToOrder(<s:property value='#food.id'/>,<s:property value='#food.shop_id'/>)">立即下单</button></td>
						<td style="vertical-align: middle;">
							<button class="btn btn-danger" onclick="addShopcart(<s:property value="#food.id"/>,<s:property value="#food.shop_id"/>)">加入购物车</button>

						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
	</div>
</div>
<div id="foodToOrderModalDiv"></div>
