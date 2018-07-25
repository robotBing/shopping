
<%@page import="java.net.URLDecoder"%>
<%@page import="com.usts.shopdemo.enti.MyOrderList"%>
<%@page import="java.util.List"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的订单</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	

	<%
		// 2. 从cookie中取出name，userId2个数据
		Cookie[] cks = request.getCookies();
		String name = null;
		String userId = null;
		boolean bLogin = false;
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
			bLogin = true;
		}
	%>

	<%
		if (bLogin == true) {
			// 显示数据
			List<MyOrderList> lst = new DBImpl().getOrderListByUserId(userId);
	%>

	<%=name%>的订单：<br>
	<%-- <%=lst %> --%>
	<table border="1">
		<tr>
			<td>订单号</td>
			<td>状态</td>
			<td>收件人</td>
			<td>地址</td>
			<td>电话</td>
			<td>总价格</td>
			<td>预定时间</td>
			<td>明细</td>
		</tr>
		<%
			for (MyOrderList order : lst) {
		%>
		<tr>
			<td><%=order.getOrderId()%></td>
			<td><%=order.getStatus()%></td>
			<td><%=order.getReceiveName()%></td>
			<td><%=order.getReceiveAddress()%></td>
			<td><%=order.getReceiveTele()%></td>
			<td><%=order.getAllMoney()%></td>
			<td><%=order.getBuyTime()%></td>
			<td><a
				href="orderListDetail.jsp?orderId=<%=order.getOrderId()%>">明细</a></td>
		</tr>
		<%
			}
		%>

	</table>
	<%
		} else {
	%>
	<a href="login.jsp">登陆</a>
	<%
		}
	%>








</body>
</html>