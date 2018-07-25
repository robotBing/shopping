<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.usts.shopdemo.db.DBImpl"%>
<%@page import="com.usts.shopdemo.db.DBDao"%>
<%@page import="java.util.List"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Left</title>
<link rel="stylesheet" type="text/css" href="common/css/layout.css" />
<script type="text/javascript" src="common/js/base.js"></script>
<script type="text/javascript" src="common/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="style/style.css">
   <style>
   #d{
		width:40px; height: 300px; border: 1px solid #D4CD49; position:fixed;left:30px;top:50% }
#nav{
		width:120px; height: 350px; border: 1px solid #D4CD49; position:fixed;left:30px;top:10% }
#top{
		width:100%; height:30%;}
#middle{
		background-image:url(img/app/bg.pdf);width:100%;  height:50%; background-repeat: no-repeat; }
#buttom{width:100%;  height:20%; background-repeat:no-repeat; }
</style>

</head>




<body>
<% 
	boolean bLogin = false;
	Cookie[] cks = request.getCookies();
	String name = null;
	String userId = null;
	if(cks != null){	
	for ( Cookie ck : cks){
		if ( ck.getName().equalsIgnoreCase("name")){
			name = ck.getValue();
			
			name = URLDecoder.decode(name);
		}
		if ( ck.getName().equalsIgnoreCase("userId")){
			userId = ck.getValue();
		}
	}
	}
	
	if ( name != null && name.trim().length() > 0 ){
		// 确实是登陆状态
		bLogin = true;
	}
%>

<script type="text/javascript">
	function checkInput(){
		// 通过getElementsByName方式取出name=userCode的元素标签，
		//    返回是数组，我们从数组中取出第1个元素
		var userCode = document
				.getElementsByName("userCode")[0];
		// 从文档中取出name=frmLogin的标签，该标签是form表单。
		//   再从frmLogin中取出name=userPwd的标签，
		var userPwd = document.frmLogin.userPwd;
		if (  userCode.value.trim().length == 0 ){
			// 从文档中取出all，所有的ID中id=idUserNameTip的标签
			document.all.idUserNameTip.innerHTML="请输入用户姓名";
			return false;
		}else{
			// 通过getElementById方式取出id=idUserNameTip
			// 的元素标签，返回是一个元素，
			// 在一个jsp中，id定义不能重发，否则不能取到元素数据。
			document.getElementById("idUserNameTip")
				.innerHTML = "";
			//document.all.idUserNameTip.innerHTML="";
		}
		if (  userPwd.value.trim().length == 0 ){
			document.all.idUserPwdTip.innerHTML="<i>请输入用户密码</i>";
			return false;
		}else{
			document.all.idUserPwdTip.innerHTML="";
		}
		return true;
	}
</script>


<%
	DBDao dao = new DBImpl();
	List<String> lst = dao.getGoodsType();
	
%>

<table border="0" width="100%">
	<!-- 搜索框 -->
	<tr><td>
		<form action="goodCata.jsp" target="frmMain">
			<input type="text" name="goodsName">
			<input type="hidden" name="type" value="search">
			<input type="submit" value="搜索">
		</form>
	</td></tr>
	<tr><td><a href="">项目介绍</a></td></tr>
	<tr>
	<td align="center">商品列表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	
<% for(String name1:lst){
	String decode = URLEncoder.encode(URLEncoder.encode(name1,"utf-8"),"utf-8");

%>
	<tr><td align="center">
		<%--<a href="showGoodsList.action?type=<%=decode %>" target="frmMain"><%=name %></a>--%> 
		<a href="goodCata.jsp?type=<%=decode %>" target="frmMain"><%=name1 %></a>
	</td></tr>
<%}%>
</table>
<div class="suspend" id="d">
  <dl>
    <dt class="IE6PNG"></dt>
    <dd>
        
                <div id="middle">
             
     <%if(bLogin== false){ %>
<form action="login1.action" onsubmit="return checkInput()">
<table>
			<tr><td>用户姓名</td></tr>
			<tr><td><input style="width: 96%;" type="text" required="required"
					name="userCode"></td></tr>
			<tr><td>用户密码</td></tr>
			<tr><td><input style="width: 96%;" type="password" required="required"
					name="userPwd"></td></tr>
			<tr><td><input type="submit"
					value="登 &nbsp;陆"></td></tr>
		</table>

</form>
<% }else{ %>
<div>
<%name = URLDecoder.decode(name); %>
	【<%=name %>】用户    欢迎！
</div><br><br>
<a href="logoff1.jsp">注销</a><br><br>
<% }  %>
</div>
      <div id="buttom">
      <a href="contact_service.jsp" target="frmMain"  >联系客服</a><br><br>
           <a target="blank" href="http://wpa.qq.com/msgrd?v=3&uin=975754729&site=qq&menu=yes">
			<img border="0" src=http://wpa.qq.com/pa?p=1:975754729:10 alt="点击这里给我发消息">
</a>
<br><br>
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=2052bddb0a5cfbaadc3c8b93904da4e979c94d294c92df05e295dc0e328a8ff5">
<img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="去抽烟" title="去抽烟"></a>  
    </div>
    </dd>
  </dl>
</div>
<!--[if IE 6]><script type="text/javascript" src="common/js/IE6PNG.js"></script>
<script type="text/javascript">PNGmin.fix(".IE6PNG");</script><![endif]-->
<script type="text/javascript">           
Common.init();
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">

</div>
</body>
</html>