<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<tr>
	<td style="vertical-align: middle;"><img
		src='<s:property value="food.imgSrc"/>' width="64" height="64" /></td>
	<td style="vertical-align: middle;"><s:property value="food.name" /></td>
	<td
		style="vertical-align: middle;word-wrap:break-word;word-break:break-all;"
		width="25%"><s:property value="food.description" /></td>
	<td style="vertical-align: middle;"><s:property
			value="food.price" />Ôª</td>
	<td style="vertical-align: middle;"><button
			class="btn btn-primary" onclick="">ÐÞ¸Ä</button></td>
	<td style="vertical-align: middle;"><button class="btn btn-danger"
			onclick="deleteFood(event,<s:property value='food.id'/>)">É¾³ý</button></td>
</tr>