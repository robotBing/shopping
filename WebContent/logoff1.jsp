<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注销</title>
<link type="text/css" rel="stylesheet" href="style/style.css">
   <style>
#nav{
		width:120px; height: 350px; border: 1px solid #D4CD49; position:fixed;left:30px;top:10% }
#top{
		width:100%; height:30%;}
#middle{
		background-image:url(img/app/bg.pdf);width:100%;  height:50%; background-repeat: no-repeat; }
#buttom{background-image:url(img/app/test2.png);width:100%;  height:20%; background-repeat:no-repeat; }
</style>
</head>

<body>
	<%
		//String name = request.getParameter("userCode");
	//	name = URLDecoder.decode(name, "utf-8");
		Cookie[] cks = request.getCookies();
		String name = null;
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("name")) {
				name = ck.getValue();
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if (ck.getName().equalsIgnoreCase("userId")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if (ck.getName().equalsIgnoreCase("address")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if (ck.getName().equalsIgnoreCase("callPhone")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}

		}
	%>
	<%
	DBDao dao = new DBImpl();
	List<String> lst = dao.getGoodsType();
	
%>

<table border="0" width="100%">

<% for(String name1:lst){
	String decode = URLEncoder.encode(URLEncoder.encode(name1,"utf-8"),"utf-8");

%>
	<tr><td align="center">
		<%--<a href="showGoodsList.action?type=<%=decode %>" target="frmMain"><%=name %></a>--%> 
		<a href="goodsCata.jsp?type=<%=decode %>" target="frmMain"><%=name1 %></a>
	</td></tr>
<%}%>
</table>
	 <!-- 默认显示的浮窗 -->
    <div id="nav">
        <div id="top">
         	<img src="img/app/wangwang.png" width="100%">
        </div>
                <div id="middle">
                <%name = URLDecoder.decode(name); %>
                
                <%=name%>注销成功<br>
                <a href="left.jsp">重新登录</a>
</div>
      <div id="buttom">
      
           <a target="blank" href="http://wpa.qq.com/msgrd?v=3&uin=975754729&site=qq&menu=yes">
			<img border="0" src=http://wpa.qq.com/pa?p=1:975754729:10 alt="点击这里给我发消息">
</a>
<br><br>
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=2052bddb0a5cfbaadc3c8b93904da4e979c94d294c92df05e295dc0e328a8ff5">
<img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="去抽烟" title="去抽烟"></a>  
    </div>
    </div>
</body>
</html>