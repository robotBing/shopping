<%@page import="com.usts.shopdemo.enti.GoodsInfo"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	<%
		String id = request.getParameter("id");

		DBDao dao = new DBImpl();
		GoodsInfo gi = dao.getGoodDetail(id);
	%>
	<table width="98%">

		<tr>
			<td align="center" width="30%"><img alt="图片" width="80%"
				height="100%" src="<%=gi.getGoodsImgUrl()%>"></td>
			<!-- 显示图片 -->

			<td width="70%">
				<table width="100%">

					<tr>
						<td>商品id：<%=gi.getGoodsId()%></td>
					</tr>
					<tr>
						<td>商品名称：<%=gi.getGoodsName()%></td>
					</tr>
					<tr>
						<td>商品类型：<%=gi.getGoodsType()%></td>
					</tr>
					<tr>
						<td>商品价格：<%=gi.getGoodsPrice()%></td>
					</tr>
					<tr>
						<td>商品数量：<%=gi.getGoodsAmount()%></td>
					</tr>

					
				</table>
			</td>


		</tr>
		<tr>
			<td colspan="2">商品描述：<br><%=gi.getGoodsIntroduction()%></td>
		</tr>

	</table>
</body>
</html>