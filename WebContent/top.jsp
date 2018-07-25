<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>

</head>

<link type="text/css" rel="stylesheet" href="css/background.css">


<%
	boolean bLogin = false;
	Cookie[] cks = request.getCookies();
	String name = null;
	String userId = null;
	if(cks != null){	
	for (Cookie ck : cks) {
		if (ck.getName().equalsIgnoreCase("name")) {
			name = ck.getValue();
			if (name != null) {
				name = URLDecoder.decode(name, "utf-8");
			}

		}
		if (ck.getName().equalsIgnoreCase("userId")) {
			userId = ck.getValue();
		}
	}
	}

	if (name != null && name.trim().length() > 0) {
		bLogin = true;
	}
%>
<script type="text/javascript">

function logoffConfirm(){
	if(<%=bLogin%>){
		return  confirm("用户:<%=name%>,确定注销？");

		} else {
			alert("您尚未登录，请登录！");
			return false;
		}
	}
function resetConfirm(){
	if(<%=bLogin%>){
		return  confirm("用户:<%=name%>,确定重置密码？");

		} else {
			alert("您尚未登录，请登录！");
			return false;
		}
	}
	function registeConfirm() {
		if (
<%=bLogin%>
	) {
			alert("您已登录，请注销后刷新重试！");
			return false;
		} else {
			return true;
		}
	}
</script>

<body>

	<center>
		<h4>在线商城</h4>
	</center>
	<div align="right">
	<%
	if(bLogin){%>
		用户【<%=name %>】<a href="personCenter.jsp" target="frmMain">个人中心</a>
		<% 
	}
	
	
	%>
	</div>
	<div align="right">
		<table>
			<tr>
				<td><a href="goodCata.jsp" target="frmMain"> <img id="zy"
						alt="主页" src="img/app/zy1.gif"
						onmousedown="document.all.zy.src = 'img/app/zy2.gif'"
						onmousemove="document.all.zy.src = 'img/app/zy3.gif'"
						onmouseout="document.all.zy.src = 'img/app/zy1.gif'">
				</a></td>
				<td><a href="shoppingCar.jsp" target="frmMain"><img
						alt="购物车" src="img/app/gwc1.gif" id="gwc"
						onmousedown="document.all.gwc.src = 'img/app/gwc2.gif'"
						onmousemove="document.all.gwc.src = 'img/app/gwc3.gif'"
						onmouseout="document.all.gwc.src = 'img/app/gwc1.gif'"></a></td>



				<td><a href="orderList.jsp" target="frmMain"><img
						alt="订单列表" src="img/app/wddd1.gif" id="wddd"
						onmousedown="document.all.wddd.src = 'img/app/wddd2.gif'"
						onmousemove="document.all.wddd.src = 'img/app/wddd3.gif'"
						onmouseout="document.all.wddd.src = 'img/app/wddd1.gif'"></a></td>

				<td><a href="registe.jsp?status=1" target="frmMain"
					onclick="return registeConfirm()"><img alt="用户注册"
						src="img/app/yhzc1.gif" id="yhzc"
						onmousedown="document.all.yhzc.src = 'img/app/yhzc2.gif'"
						onmousemove="document.all.yhzc.src = 'img/app/yhzc3.gif'"
						onmouseout="document.all.yhzc.src = 'img/app/yhzc1.gif'"></a></td>


				<td><a href="login.jsp" target="frmMain"><img alt="用户登录"
						src="img/app/yhdl11.gif" id="yhdl"
						onmousedown="document.all.yhdl.src = 'img/app/yhdl12.gif'"
						onmousemove="document.all.yhdl.src = 'img/app/yhdl13.gif'"
						onmouseout="document.all.yhdl.src = 'img/app/yhdl11.gif'"></a></td>
				<td><a href="resetPwd.jsp?name=<%=name%>&status=1"
					target="frmMain" onclick="return resetConfirm()" id="logoff"><img
						alt="密码重置" src="img/app/mmcz1.gif" id="mmcz"
						onmousedown="document.all.mmcz.src = 'img/app/mmcz2.gif'"
						onmousemove="document.all.mmcz.src = 'img/app/mmcz3.gif'"
						onmouseout="document.all.mmcz.src = 'img/app/mmcz1.gif'"></a></td>

				<td><a href="logoff.jsp?name=<%=name%>" target="frmMain"
					onclick="return logoffConfirm()" id="logoff"> <img alt="注销"
						src="img/app/zx1.gif" id="zx"
						onmousedown="document.all.zx.src = 'img/app/zx2.gif'"
						onmousemove="document.all.zx.src = 'img/app/zx3.gif'"
						onmouseout="document.all.zx.src = 'img/app/zx1.gif'"></a></td>
			</tr>


		</table>
	</div>
</body>
</html>