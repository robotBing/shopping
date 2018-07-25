<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Map"%>
<%@page import="com.usts.shopdemo.enti.MyCartGoods"%>
<%@page import="java.util.List"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的购物车</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body onload="checkAmount()">
	我的购物车
	<script type="text/javascript">
		
	<%boolean bLogin = false;

			Cookie[] cks = request.getCookies();
			String name = null;

			for (Cookie ck : cks) {
				if (ck.getName().equalsIgnoreCase("name")) {
					name = ck.getValue();
					name = URLDecoder.decode(name, "utf-8");

					break;
				}
			}
			if (name != null && name.trim().length() > 0) {
				bLogin = true;
			}%>
		function deleteConfirm(name) {
			return window.confirm("Delete? " + name);
		}
		function checkLogin() {
	<%if (bLogin == true) {%>
		//window.location = "account.jsp";
			return true;
	<%} else {%>
		window.location = "login.jsp";
			return false;
	<%}%>
		}
		
		function checkAmount(){
			<%
				String checkAmount = request.getParameter("checkAmount");
				String updateAmount = request.getParameter("updateAmount");
				if(checkAmount != null){
					if(checkAmount.equalsIgnoreCase("false")){
			%>
					alert("商品库存不够");
			<%
					}
				}
				if(updateAmount != null){
					if(updateAmount.equalsIgnoreCase("false")){
			%>
					alert("商品库存不足");
			<%
					}
				}
			%>
		}
		function updateOrderAmount(){
			var targetAmount = document.getElementById("idTargetAmount").value;
			if(targetAmount.trim().length == 0){
				alert("请输入正整数");
				return false;
			}
			return true;
		}
	</script>
	<%
		DBDao dao = new DBImpl();
		//第1种，数据使用类对象的方式，存放在list中
		// List<MyCartGoods> lst = dao.getMyCartGoods();
		// 第2种 ，数据使用map方式，存放在list中
		List<Map<String, Object>> lst = dao.getMyCartGoodsEx();
	%>

	<table border="1" width="96%">
		<thead>
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>商品类别</td>
				<td>商品价格</td>
				<td>商品数量</td>
				<td>选择数量</td>
			</tr>
		</thead>


		<%
			double total = 0;
			for (Map<String, Object> map : lst) {
				total += Double.parseDouble(map.get("goodsPrice").toString())
						* Integer.parseInt(map.get("orderAmount").toString());
		%>
		<tr>
			<td><%=map.get("goodsId")%></td>
			<td><%=map.get("goodsName")%></td>
			<td><%=map.get("goodsType")%></td>
			<td><%=map.get("goodsPrice")%></td>
			<td>
				<%=map.get("orderAmount")%>
				<a href="deleteOneGoods.action?goodsId=<%=map.get("goodsId") %>
					&orderAmount=1">-</a>
				<a href="addOneGoods.action?goodsId=<%=map.get("goodsId") %>
					&orderAmount=1">+</a>
				<a href="deleteMyCartGoods.action?goodsId=<%=map.get("goodsId") %>
					&orderAmount=<%=map.get("orderAmount") %>" 
					onclick="return deleteConfirm('<%=map.get("goodsName") %>')">x</a>
			</td>
			<td>
				<form name="frmNum" action="updateOrderAmount.action">
					<input type="hidden" name="goodsId" value=<%=map.get("goodsId") %>>
					<input type="hidden" name="orderAmount" value=<%=map.get("orderAmount") %>>
					<input type="number" min="0" id="idTargetAmount" name="targetAmount">
					<input type="submit" onclick="return updateOrderAmount()" value="修改"> 
				</form>
			</td>
		</tr>
		<%
			}
		%>

		<tr>
			<td colspan="6">商品总价 <%=total%> <a
				href="account.jsp?name=<%=name%>&total=<%=total%>"
				onclick="return checkLogin()">结账</a></td>
		</tr>

	</table>
</body>
</html>