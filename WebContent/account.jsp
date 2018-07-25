<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结账</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>

<%
	// 展示一些用户信息,name,total
	request.setCharacterEncoding("UTF-8");  

	String name = request.getParameter("name");
	name = new String(name.getBytes("iso-8859-1"),"utf-8");
	String total = request.getParameter("total");
	name = URLDecoder.decode(name,"utf-8");
%>	
<form action="payBill.action">
	<table>
		<tr>
			<td align="right">用户姓名：</td>
			<td><span><%=name %></span></td>
		</tr>
		<tr>
			<td align="right">支付金额：</td>
			<td><span><%=total %>
			<input type="hidden" name="total" value="<%=total %>" required="required">
			</span></td>
		</tr>
		<tr>
			<td align="right">收件人：</td>
			<td><input type="text" name="receivePerson" required="required"></td>
		</tr>
		<tr>
			<td align="right">收件地址：</td>
			<td><input type="text" name="receiveAddress" required="required"></td>
		</tr>
		<tr>
			<td align="right">用户手机号码：</td>
			<td><input type="number" name="receiveCall" required="required"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交">
			</td>
		</tr>
	</table>		
</form>
</body>
</html>