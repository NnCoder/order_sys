<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="panel shadow">
	<div class="panel-heading">
		<h3 class="panel-title">已完成的<s:if test="order_type==0">外卖订单</s:if>
		<s:else>店内订单</s:else></h3>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>餐品名</th>
					<th>价格</th>
					<th>备注</th>
					<th>时间</th>
					<th>商家</th>
					<th>商家地址</th>
					<th>商家联系方式</th>
					<th>状态</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="orderTable">
				<s:iterator value="customer.orders" var="order">
					<tr>
						<td style="vertical-align: middle;"><img
							src='<s:property value="#order.food.imgSrc"/>' width="64" height="64" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.name" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.food.price"/>元</td>
						
						<td style="vertical-align: middle;">
							<p style="word-break:break-all; width: 120px;">
								<s:property value="#order.remarks"/>
							</p>
						</td>
						<td style="vertical-align: middle;"><s:property
								value="#order.orderTime"/></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.shop.name" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.shop.address" /></td>
						<td style="vertical-align: middle;"><s:property
								value="#order.shop.phoneNum" /></td>
						<td style="vertical-align: middle;"><span style="color:green;">已送达</span></td>
						<td style="vertical-align: middle;">
							<s:if test="#order.status==100"><button class="btn" disabled>已评价</s:if>
								<s:else>
								<button class="btn btn-info" onclick="comment(event,<s:property value="#order.id"/>,<s:property value="#order.food.id"/>)" >给个评价吧~</button>
								</s:else>

						</td>
						
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
<!-- 评论modal框-c -->
<div>
	<div class="modal fade" id="commentModal" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">评论</h4>
				</div>
				<select id="selectComment" class="form-control" onchange="changeComment()">
					<option value="好评" selected>好评</option>
					<option value="满意">满意</option>
					<option value="差评">差评</option>
				</select>
				<textarea rows="5" cols="" class="form-control col-md-8" id="content">好吃好吃~</textarea>
					<div class="modal-footer">
						<button id="commentBtn" class="btn btn-info">评论</button>
						<button id="closeCommentBtn" type="button"
								class="btn btn-default" data-dismiss="modal">关闭</button>

					</div>
				</form>
			</div>

			<!-- /.modal-content -->
		</div>
	</div>

</div>