
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/background.css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>

<script type="text/javascript">
	window.onload = function(){
		
		var ques = new Object();
		
		
		document.getElementById("send").onclick = function(){
			console.log("send start");
			var out = document.getElementById("out");
			if (document.getElementById("in").value == "") {
				
			} else {
				if (document.getElementById("in").value == "你好") {
					out.innerHTML += "我：  " + document.getElementById("in").value+"<br>";
					document.getElementById("in").value = "";
					out.innerHTML += "客服： "+"你好<br><br>";
				} else {
					out.innerHTML += "我：  " + document.getElementById("in").value+"<br>";
					document.getElementById("in").value = "";
					
					out.innerHTML += "客服： " + "您的问题超过范围，请联系人工客服" + "<br>" +
					 "联系方式如下:<br>" + 
					 "手机：17226182096   &nbsp;&nbsp;&nbsp;   QQ：1045449196       &nbsp;&nbsp;&nbsp;    邮箱：17326182096@163.com<br><br>";
				}
				
			}
			
		}
	}

	
</script>
</head>
<body>
	在线客服为您服务··········	
	<hr color="black">
	<div style="width:1000px;height:300px;border:1px solid #000" id="out">
	
	</div> 
	<br>
	<br>
	<br>
	<br>
	<input type="text" name="in" id="in" style="width:1000px;height:30px">
	<input type="button" id="send" value="发送">
	
</body>
</html>