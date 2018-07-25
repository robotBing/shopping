<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注销</title>
</head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<body>
	<%
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		name = new String(name.getBytes("iso-8859-1"),"utf-8");

		Cookie[] cks = request.getCookies();
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("name")) {
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
	<%=name%>注销成功，点击
	<a href="login.jsp" target="frmMain">这里</a>重新登录...
</body>
</html>