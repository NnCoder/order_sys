<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel" style="border-radius: 30px;">
	
	<div class="panel-body">
	<div style="min-height:150px;height: auto; color: #000;">
		<h3>已被评价过的食物</h3>
		<div style="margin-top: 60px;">
		<table class="table" style="background-color: #FFF;border-radius: 15px;box-shadow:#666 0px 0px 15px;">
			<thead>
				<tr>
					<th></th>
					<th>餐品名</th>
					<th>评论人</th>
					<th>评论</th>
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
						<td style="vertical-align: middle;"><s:property
								value="#cs.customer.name" />
						</td>
						<td
							style="vertical-align: middle;word-wrap:break-word;word-break:break-all;"
							width="25%">
							<p style="word-break: break-all;"><s:property value="#cs.comment" /><br/><s:property
									value="#cs.ctime" /></p></td>
						<td style="vertical-align: middle;">
							<s:if test="#cs.response==null">
								<button onclick="response(event,<s:property value="#cs.id"/>)" class="btn btn-info">回复</button>
							</s:if>
							<s:else>
								<p style="word-break: break-all;"><s:property value="#cs.response"></s:property></p>
							</s:else>
						</td>

					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
	</div>
</div>
	<div>
		<div class="modal fade" id="responseModal" tabindex="-1" role="dialog"
			 aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">回复</h4>
					</div>
					<textarea rows="5" cols="" class="form-control col-md-8" id="content">感谢您的评价，我们会继续加油的~~</textarea>
					<div class="modal-footer">
						<button id="responseBtn" class="btn btn-info">回复</button>
						<button id="closeResponseBtn" type="button"
								class="btn btn-default" data-dismiss="modal">关闭</button>

					</div>
					</form>
				</div>

				<!-- /.modal-content -->
			</div>
		</div>

	</div>