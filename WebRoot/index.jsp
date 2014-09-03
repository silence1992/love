<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title>首页</title>
    <script src="js/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
  	<script src="js/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
	<script src="js/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"
		href="js/jquery-easyui-1.3.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css"
		href="js/jquery-easyui-1.3.1/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<script src="js/commens.js"></script>
	<script src="js/index.js"></script>
  </head>
  
  <body>
    <div region="center" title="我的空间" href="blog/getBlog.action" iconCls="icon-blog">
    </div>
    <div id="body_east" region="east" title="我的社交" href="friend.jsp" iconCls="icon-group"></div>
    
    <div id="login">
		<div style="margin-left:20px;margin-top:10px;font-size:15px;">
			<form method="post" action="userLogin.action" id="loginform">
				<div>&nbsp;</div>
				用户名:<input type="text" name="user.username" id="username"><br />
				<div>&nbsp;</div>
				密&nbsp;&nbsp;码:<input type="password" name="user.password" id="password"><br />
				<div>&nbsp;</div>
			</form>
		</div>
	</div>
	
	<div id="regist">
		<div style="margin-left:20px;margin-top:30px;font-size:15px;">
			<form method="post" action="user/regist.action" id="registform">
				<table>
					<tr>
						<td>用户名:</td>
						<td><input type="text" name="user.username" id="rusername"
							onblur="hasUsername();"></td>
						<td id="rcheckUsername" style="font-size:12px;color:red;"></td>
					</tr>
					<tr>
						<td>昵&nbsp;&nbsp;称:</td>
						<td><input type='text' name='user.nickName' id='niname'>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;码:</td>
						<td><input type="password" name="user.password" id="rpassword">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>确&nbsp;&nbsp;认:</td>
						<td><input type="password" id="confirm">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>性&nbsp;&nbsp;别:</td>
						<td>男<input type="radio" name="user.sex" value="m" checked/>
							女<input type="radio" name="user.sex" value="f"/>
						</td><td></td>
					</tr>
					<tr>
						<td>年&nbsp;&nbsp;龄:</td>
						<td><input type="text" name="user.age" id="spinner_age"/></td>
						<td></td>
					</tr>
					<tr>
						<td align="right" height=60>签&nbsp;&nbsp;名:</td>
						<td><textarea name="user.signature" style="height:100%;width:155px;"></textarea></td>
					</tr>
					<tr>
						<td>验证码:</td>
						<td><input name='checkcode' id='checkcode' style="width:40px;"> <img
							src="getCheckCode" id="code_pic" /><a href="javascript:;"
							onclick="changeCode();">换一张?</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
  
  
</html>
