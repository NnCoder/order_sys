<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="modal fade" id="customerUpdateModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<form method="post" id="customerUpdateForm" enctype="multipart/form-data">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
			</div>
			<div class="modal-body">
				
					
					昵称名:<input id="customer_name" name="customer.name" type="text" required="required" class="form-control"
						 value='<s:property value="customer.name"/>'>
				       手机号:
					
						<input name="customer.phoneNum"
							type="number" class="form-control" value='<s:property value="customer.phoneNum"/>' disabled="disabled" />
					
					地址:
					<input name="customer.address" id="customer_address" value="<s:property value='customer.address'/>" class="form-control" type="text" required="required"/>
					<br> 
				
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary" value="修改"/>
				<button id="closeShopUpdateBtn" type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>

			</div>
			

		</div>
		</form>
		<!-- /.modal-content -->
	</div>
	</div>
