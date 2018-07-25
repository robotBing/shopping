<%@page import="com.usts.shopdemo.enti.MyOrderList"%>
<%@page import="java.util.List"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">
<body>
<%
		Cookie[] cks = request.getCookies();
		String name = null;
		String email = null;
		String addr = null;
		String callPhone = null;
		String userId = null;
		boolean bLogin = false;
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("name")) {
				name = ck.getValue();
				name = URLDecoder.decode(name, "utf-8");
			}
			if (ck.getName().equalsIgnoreCase("address")) {
				addr = ck.getValue();
				addr = URLDecoder.decode(addr, "utf-8");

			}
			if (ck.getName().equalsIgnoreCase("callPhone")) {
				callPhone = ck.getValue();
				callPhone = URLDecoder.decode(callPhone, "utf-8");
			}
			if (ck.getName().equalsIgnoreCase("email")) {
				email = ck.getValue();
				email = URLDecoder.decode(email, "utf-8");
				System.out.println("aaaaaaa"+email);
			}		
			if (ck.getName().equalsIgnoreCase("userId")) {
				userId = ck.getValue();
			}
		}
		if (name != null && name.trim().length() > 0) {
			bLogin = true;
		}
	%>
	<table border="0">
	<tr>
	<td><img alt="头像" src="img/touxiang/default.png"></td>
	<td><%=name %></td>
	</tr>
	
	<tr>
	<td>电话：</td>
	<td><%=callPhone %></td>
	</tr>
	
	<tr>
	<td>地址：</td>
	<td><%=addr %></td>
	</tr>
	
	<tr>
	<td>email：</td>
	<td><%=email %></td>
	</tr>
	</table>
	
	<div style="height: 50px"></div>
	
	<%
		if (bLogin == true) {
			// 显示数据
			List<MyOrderList> lst = new DBImpl().getOrderListByUserId(userId);
	%>

	订单信息：<br>
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
		}
		%>

	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>