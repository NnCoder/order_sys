<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>modifyPsw.html</title>
    <meta charset="GB18030">
   	<meta content="IE=edge,chrome=1">
   	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Cache" content="no-cache">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
    <div class="panel" style="margin-left:29%; margin-top: 100px; width: 40%; border-radius:15px; box-shadow: #666 0px 0px 10px;">
    	<div class="panel-body">
    		<form id="modifyPswForm">
    			旧密码:<input type="password" id="oldPsw" required="required" class="form-control"/><br>
    			新密码:<input type="password" id="newPsw" required="required" class="form-control"/><br>
    			再次输入密码:<input type="password" id="newPswAgain" required="required" class="form-control"/><br>
    			<input type="submit" class="btn btn-danger" value="修改密码"/>
    		</form> 
    	</div>
    </div>
  </body>
</html>
