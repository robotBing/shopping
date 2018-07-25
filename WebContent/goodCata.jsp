
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@page import="com.usts.shopdemo.enti.GoodsInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示商品列表</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body onload="checkAmount()">

	<%
		String type = request.getParameter("type");
		String goodsName = request.getParameter("goodsName");
		System.out.println("aaa:"+goodsName);

		DBDao dao = new DBImpl();
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();
		if (type == null) {
			list = dao.getFirstTypeGoodsList();
		}else if(type.equalsIgnoreCase("search")){
			goodsName = new String(goodsName.getBytes("iso-8859-1"), "utf-8");
			list = dao.getGoodsListByName(goodsName);
		} else{
			String tp = URLDecoder.decode(
					URLDecoder.decode(type,"utf-8"),"utf-8");
			
			list = dao.getGoodsListByType(tp);
		}
	%>
	<script type="text/javascript">
		function checkAmount(){
			<%
				String checkAmount = request.getParameter("result");
				if(checkAmount != null){
					if(checkAmount.equalsIgnoreCase("false")){
			%>
					alert("商品库存不够");
			<%
					}
				}
			%>
		}
		function buyGoods(){
			var targetAmount = document.getElementById("idTargetAmount").value;
			if(targetAmount.trim().length == 0){
				alert("请输入正整数");
				return false;
			}
			return true;
		}
	</script>
	<table width="98%">
		<%
			String ty = "";
			for (GoodsInfo gi : list) {
				ty = URLEncoder.encode(URLEncoder.encode(gi.getGoodsType(), "utf-8"), "utf-8");
		%>
		<tr>
			<td align="center"><a href="detail.jsp?id=<%=gi.getGoodsId()%>"><img
					alt="图片" width="120" height="140" src="<%=gi.getGoodsImgUrl()%>"></a></td>
			<!-- 显示图片 -->

			<td>
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
						<td>商品数量：<%=gi.getGoodsAmount() %></td>
					</tr>
					<tr><td>
						<form name="frmNum" action="buyInList.action">
							输入购买数量：<input type="number" value="1" min="1"
									 id="idTargetAmount" name="targetAmount">
							<input type="submit" onclick="return buyGoods()" value="购买">
							<input type="hidden" name="goodsId" value=<%=gi.getGoodsId() %>>
							<input type="hidden" name="type" value=<%=ty %>>
						</form>
					</td></tr>
				</table>
			</td>

		</tr>
		<%
			}
		%>

	</table>



</body>
</html>