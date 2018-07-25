
<%@page import="com.usts.shopdemo.enti.MyOrderDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的订单详情</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	我的订单详情
	

	<%
		String orderId = request.getParameter("orderId");
		List<MyOrderDetail> lst = new DBImpl().getOrderDetailByOrderId(orderId);
	%>
	<table border="1" width="80%">
		<tr>
			<td>图片</td>
			<td>商品名称</td>
			<td>种类</td>
			<td>价格</td>
			<td>购买数量</td>
		</tr>
		<%
			for (MyOrderDetail detail : lst) {
		%>
		<tr>
			<td><img alt="图片" width="120"
				src="<%=detail.getGoodsImgUrl()%>"></td>
			<td><%=detail.getGoodsName()%></td>
			<td><%=detail.getGoodsType()%></td>
			<td><%=detail.getGoodsPrice()%></td>
			<td><%=detail.getBuyAmount()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>