<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@page import="com.usts.shopdemo.enti.GoodsInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示商品列表</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	显示商品列表
	<%=session.getAttribute("GoodsListByType")%>
	<%
		List<GoodsInfo> list = (List<GoodsInfo>) session.getAttribute("GoodsListByType");
	%>
	<%
		if (list == null) {
			DBDao dao = new DBImpl();
			list = dao.getFirstTypeGoodsList();
		}
	%>
	<table border="1" width="98%">
		<%
			for (GoodsInfo gi : list) {
		%>
		<tr>
			<td align="center"><img alt="图片" width="100%" height="100%"
				src="<%=gi.getGoodsImgUrl()%>"></td>
			<!-- 显示图片 -->

			<td>
				<table border="1" width="100%">

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
					<tr>
						<td align="center"><button>购买</button></td>
					</tr>

				</table>
			</td>

		</tr>
		<%
			}
		%>

	</table>



</body>
</html>