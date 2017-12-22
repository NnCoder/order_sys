<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="modal fade" id="foodToOrderModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">下单</h4>
			</div>
			<form id="foodToOrderForm"
					enctype="multipart/form-data" onsubmit="return placeOrder(<s:property value='food.id'/>,<s:property value='msg'/>)">
			<div class="modal-body">

					<img src="<s:property value='food.imgSrc'/>" 
						width="128px" height="128px" />
					<br>
					<h3><s:property value="food.name"/></h3> <br /> 价格:
					<div class="input-group">
						<span class="input-group-addon">$</span><input name="food.price"
							type="number" class="form-control"
							value='<s:property value="food.price"/>' disabled="disabled" /><span
							class="input-group-addon">元</span>
					</div>
					<br>
					<p style="word-break:break-all;">
					描述:<s:property value="food.description" />
					</p>
										
					<div class="input-group col-sm-12">
					选择订单类型:
					<br>
					<input type="radio" name="orderType" value="0" checked="checked">外卖订餐
					<input type="radio" name="orderType" value="1">店内订餐
						<select name="insideOrderType" id="insideOrderType" >
							<option value="即时取餐">
							即时取餐
							</option>
							<option value="打包">
							打包
							</option>
						</select>
						<p style="color: red;word-break:break-all;">
							&nbsp;&nbsp;*店内订餐时可选择打包或即时取餐
						</p>
					</div>
					<div class="input-group col-sm-12">
						备注:<textarea class="form-control" id="orderRemarks"></textarea>
					</div>
					<br>
				
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary" value="下单"/>
				<button id="closeFoodToOrderBtn" type="button"
					class="btn btn-default" data-dismiss="modal">关闭</button>

			</div>
			</form>
		</div>

		<!-- /.modal-content -->
	</div>
</div>
