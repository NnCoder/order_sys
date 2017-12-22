<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel" style="border-radius: 30px;">
	
	<div class="panel-body">
	<div style="min-height:150px;height: auto; color: #000;">
	<img src='<s:property value="shop.imgSrc"/>' style="width: 228px;height:200px; float: left;max-width: 100%;margin-top: 5px;margin-left: 25px; border-radius: 40px;">
		<div style="float: left;margin-left: 15px;">
			<h3 style="color: #666;"><s:property value="shop.name"/></h3>
			<p style="word-break:break-all; width: 250px;">简介:<s:property value="shop.description"/></p>
		</div>
		</div>
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
				<s:iterator value="shop.foods" var="food">
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
								class="btn btn-danger" onclick="foodToOrder(<s:property value='#food.id'/>,<s:property value='shop.id'/>)">立即下单</button></td>
						<td style="vertical-align: middle;">
							<button class="btn btn-danger" onclick="addShopcart(<s:property value="#food.id"/>,<s:property value="shop.id"/>)">加入购物车</button>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
	</div>
</div>
<div class="panel" style="border-radius: 30px;">

	<div class="panel-body">
		<div style="min-height:150px;height: auto; color: #000;">
			<h3>评论</h3>
			<div style="margin-top: 60px;">
				<table class="table" style="background-color: #FFF;border-radius: 15px;box-shadow:#666 0px 0px 15px;">
					<thead>
					<tr>
						<th></th>
						<th>餐品名</th>
						<th>评论</th>
						<th>评论时间</th>
						<th>回复</th>
					</tr>
					</thead>
					<tbody id="commentTable">
					<s:iterator value="comments" var="cs">
						<tr>
							<td style="vertical-align: middle;"><img
									src='<s:property value="#cs.food.imgSrc"/>' width="64" height="64" />
							</td>
							<td style="vertical-align: middle;"><s:property
									value="#cs.food.name" />
							</td>
							<td
									style="vertical-align: middle;word-wrap:break-word;word-break:break-all;"
									width="25%">
								<p style="word-break: break-all;"><s:property value="#cs.comment" /></p></td>
							<td style="vertical-align: middle;"><s:property
									value="#cs.ctime" /></td>
							<td style="vertical-align: middle;"><s:property
									value="#cs.response" /></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<div id="foodToOrderModalDiv"></div>
