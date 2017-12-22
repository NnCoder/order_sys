<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel" style="border-radius: 30px;">
	
	<div class="panel-body">
	<div style="min-height:150px;height: auto; color: #000;">
		<h3>你已评价过的食物</h3>
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
						<td style="vertical-align: middle;">
							<s:if test="#cs.response==null">暂无回复</s:if>
							<s:else>
							<s:property
								value="#cs.response" />
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
	</div>
</div>

