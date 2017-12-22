<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="modal fade" id="shopUpdateModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">修改商店信息</h4>
			</div>
			<div class="modal-body">
				<form method="post" id="shopUpdateForm" enctype="multipart/form-data">
					<img src="<s:property value='shop.imgSrc'/>" id="shopUpdatepreview" width="128px"
						height="128px" />
						<br/>
					<%-- <div class="form-group">

						<input type="file" name="img" id="headImgSrc" value="<s:property value='shop.imgSrc'/>"
							tabindex="-1"
							style="position: absolute; clip: rect(0px 0px 0px 0px);">
						<div class="bootstrap-filestyle input-group">
							<span class="group-span-filestyle " tabindex="0"><label
								for="headImgSrc" style="margin-bottom: 0;" class="btn btn-primary "><span
									class="oi oi-f`older"></span> <span class="buttonText">选择图片</span></label>
							</span>
						</div>
					</div> --%>
					商店名:<input name="shop.name" type="text" class="form-control"
						 value='<s:property value="shop.name"/>'> <br /> 手机号:
					<div class="input-group">
						<input name="shop.phoneNum"
							type="number" class="form-control" value='<s:property value="shop.phoneNum"/>' disabled="disabled" />
					</div>
					地址:
					<input type="text" name="shop.address" class="form-control" value='<s:property value="shop.address"/>'/>
					身份证:
					<input type="text" class="form-control" value='<s:property value="shop.idcard"/>'  disabled="disabled"/>
					<br> 描述:
					<textarea rows="3" name="shop.description" class="form-control"><s:property value="shop.description"/></textarea>
					<br>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="updateShop()">修改</button>
				<button id="closeShopUpdateBtn" type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>

			</div>
			

		</div>
		
		<!-- /.modal-content -->
	</div>
	</div>
	<script type="text/javascript">
	$("#headImgSrc").change(function() {
		var objUrl = getObjectURL(this.files[0]);
		if (objUrl) {
			$("#shopUpdatepreview").attr("src", objUrl);
		}
	});
</script>
