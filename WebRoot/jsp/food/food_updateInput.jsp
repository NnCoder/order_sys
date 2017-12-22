<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="modal fade" id="foodUpdateModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">修改餐品信息</h4>
			</div>
			<form id="foodUpdateForm"
					enctype="multipart/form-data" onsubmit="return updateFood()">
			<div class="modal-body">
					<input type="hidden" name="food.id" value='<s:property value="food.id"/>'>
					<img src="<s:property value='food.imgSrc'/>" id="foodUpdatePreview"
						width="128px" height="128px" />

					餐品名:<input name="food.name" type="text" class="form-control"
						value='<s:property value="food.name"/>'> <br /> 价格:
					<div class="input-group">
						<span class="input-group-addon">$</span><input name="food.price"
							type="number" class="form-control"
							value='<s:property value="food.price"/>' /><span
							class="input-group-addon">元</span>
					</div>
					<br> 描述:
					<textarea rows="3" name="food.description" class="form-control"><s:property
							value="food.description" /></textarea>
					<br>
				
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary" value="修改"/>
				<button id="closeFoodUpdateBtn" type="button"
					class="btn btn-default" data-dismiss="modal">关闭</button>

			</div>
			</form>
		</div>

		<!-- /.modal-content -->
	</div>
</div>
<script type="text/javascript">
	$("#updateImgSrc").change(function() {
		var objUrl = getObjectURL(this.files[0]);
		if (objUrl) {
			$("#foodUpdatePreview").attr("src", objUrl);
		}
	});
</script>
