<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码重置</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	<%
		//status状态码：1表示正常修改密码
		//2表示原密码错误，需要显示重新输入
		String name = null;
		Cookie[] cks = request.getCookies();
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("name")) {
				name = ck.getValue();
				name = URLDecoder.decode(name, "utf-8");

			}
		}
		String status = request.getParameter("status");
		if (status.equals("2")) {
	%>
	原密码错误，请重新输入...
	<br>
	<%
		}
	%>

	<script type="text/javascript">
		function resetPwdConfirm() {
			var oldPwd = document.getElementsByName("oldPwd")[0].value;
			var newPwd = document.getElementsByName("newPwd")[0].value;
			var confirmPwd = document.getElementsByName("confirmPwd")[0].value;
			
			if (newPwd != confirmPwd) {
				document.all.idConfirmPwdTip.innerHTML = "两次输入密码不一致";
				
				return false;

			}else if(newPwd == oldPwd){
				document.all.idNewPwdTip.innerHTML = "新旧密码一致";
				return false;
			} 
			else {
				document.all.idConfirmPwdTip.innerHTML = "";
			}
			return true;
		}
	</script>

	<form name="resetPwd" action="resetPwd.action"
		onsubmit="return resetPwdConfirm()">
		<table border="0" width="90%">
			<tr>
				<td width="25%" align="right">原密码：</td>
				<td width="45%"><input id="oldPwd" style="width: 96%;"
					type="text" name="oldPwd" required="required"></td>
				<td width="30%" align="left"><span id="idOldPwdTip"></span></td>
			</tr>
			<tr>
				<td width="25%" align="right">新密码：</td>
				<td width="45%"><input id="newPwd" style="width: 96%;"
					type="password" name="newPwd" required="required"></td>
				<td width="30%" align="left"><span id="idNewPwdTip"></span></td>
			</tr>
			<tr>
				<td width="25%" align="right">确认新密码：</td>
				<td width="45%"><input id="confirmPwd" style="width: 96%;"
					type="password" name="confirmPwd" required="required"></td>
				<td width="30%" align="left"><span id="idConfirmPwdTip"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit"
					value="提 &nbsp;交"></td>
			</tr>
		</table>

	</form>

</body>
</html>