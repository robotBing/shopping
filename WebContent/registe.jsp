<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">
<script type="text/javascript">
		function resetRegisteConfirm() {
			var userPwd = document.getElementsByName("userPwd")[0].value;
			var userPwdConfirm = document.getElementsByName("userPwdConfirm")[0].value;
			if (userPwd != userPwdConfirm) {
				document.all.idUserPwdConfirmTip.innerHTML = "两次输入密码不一致";
				return false;

			} else {
				document.all.idUserPwdConfirmTip.innerHTML = "";
			}
			return true;
		}
	</script>
<body>
<%
String status = request.getParameter("status");
if(status.equals("2") && status != null){
	%>
	用户名已存在，请重新输入...<br>
<% }%>
	<form action="registe.action" onsubmit="return resetRegisteConfirm()">
		<table border="0" width="90%">
			<tr>
				<td width="25%" align="right">用户姓名:</td>
				<td width="45%"><input style="width: 96%;" type="text" required="required"
					name="userName"></td>
				<td width="30%" align="left"><span id="idUserNameTip"></span></td>
			</tr>
			<tr>
				<td width="25%" align="right">用户密码:</td>
				<td width="45%"><input style="width: 96%;" type="password" required="required"
					name="userPwd"></td>
				<td width="30%" align="left"><span id="idUserPwdTip"></span></td>
			</tr>
			<tr>
				<td width="25%" align="right">确认密码:</td>
				<td width="45%"><input style="width: 96%;" type="password" required="required"
					name="userPwdConfirm"></td>
				<td width="30%" align="left"><span id="idUserPwdConfirmTip"></span>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">email:</td>
				<td width="45%"><input style="width: 96%;" type="text"
					name="email"></td>

			</tr>
			<tr>
				<td width="25%" align="right">地址:</td>
				<td width="45%"><input style="width: 96%;" type="text"
					name="address"></td>

			</tr>
			<tr>
				<td width="25%" align="right">电话:</td>
				<td width="45%"><input style="width: 96%;" type="number" required="required"
					name="callPhone"></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit"
					value="提 &nbsp;交"></td>
			</tr>
		</table>




	</form>

</body>
</html>