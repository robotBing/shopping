<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	请登录...
	
	<%
	boolean bLogin = false;
	Cookie[] cks = request.getCookies();
	String name = null;
	String userId = null;
	
	for (Cookie ck : cks) {
		if (ck.getName().equalsIgnoreCase("name")) {
			name = ck.getValue();
			name = URLDecoder.decode(name, "utf-8");

		}
		if (ck.getName().equalsIgnoreCase("userId")) {
			userId = ck.getValue();
		}
	}
	
	if (name != null && name.trim().length() > 0) {
		// 确实是登陆状态
		bLogin = true;
	}
%>
	<%
		if (bLogin == false) {
	%>
	<form name="frmLogin" action="login.action"
		onsubmit="return checkInput()">

		<table border="0" width="90%">
			<tr>
				<td width="25%" align="right">用户姓名</td>
				<td width="45%"><input style="width: 96%;" type="text" required="required"
					name="userCode"></td>
				<td width="30%" align="left"><span id="idUserNameTip"></span></td>
			</tr>
			<tr>
				<td width="25%" align="right">用户密码</td>
				<td width="45%"><input style="width: 96%;" type="password" required="required"
					name="userPwd"></td>
				<td width="30%" align="left"><span id="idUserPwdTip"></span></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit"
					value="登 &nbsp;陆"></td>
			</tr>
		</table>
	</form>
	<%
		} else {
	%>
	<div>
		用户【<%=name%>】已经登录，请使用。
	</div>
	<%
		}
	%>
</body>
</html>