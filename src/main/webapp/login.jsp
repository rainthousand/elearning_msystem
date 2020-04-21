<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<style type="text/css">
	body{
	   background: url("${pageContext.request.contextPath}/images/1.jpg");
	}
	#login-box {
		width: 45rem;
		height: auto;
		background-color: #00000060;
		text-align: center;
		padding: 2rem 4rem;
	}

	</style>
	<script>
		var navhtml='';
		function getParams(key) {
			var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) {
				return unescape(r[2]);
			}
			return null;
		}
		// window.onload=function () {
		// 	alert("begin");
		// 	var auth=getParams("auth");
		//
		// 	alert("begin");
		// 	for(var i=0;i<auth.split(",").length;i++){
		// 		if(auth.split(",")[i]!=null){
		// 			var mynav=document.getElementById("js-nav");
		// 			var framea = document.createElement("a");
		// 			var framespan = document.createElement("span");
		// 			framespan.innerHTML=auth.split(",")[i];
		// 			framea.setAttribute("class","demo");
		// 			framea.setAttribute("href","index2.html");
		// 			framea.appendChild(framespan);
		//
		// 			mynav.appendChild(framea);
		// 		}
		//
		//
		// 	}
		// 	alert(navhtml);
		// };

	</script>
</head>
<body>
<%--<div class="hamburger js-hover">--%>
<%--	<div class="hamburger__line hamburger__line--01">--%>
<%--		<div class="hamburger__line-in hamburger__line-in--01"></div>--%>
<%--	</div>--%>
<%--	<div class="hamburger__line hamburger__line--02">--%>
<%--		<div class="hamburger__line-in hamburger__line-in--02"></div>--%>
<%--	</div>--%>
<%--	<div class="hamburger__line hamburger__line--03">--%>
<%--		<div class="hamburger__line-in hamburger__line-in--03"></div>--%>
<%--	</div>--%>
<%--	<div class="hamburger__line hamburger__line--cross01">--%>
<%--		<div class="hamburger__line-in hamburger__line-in--cross01"></div>--%>
<%--	</div>--%>
<%--	<div class="hamburger__line hamburger__line--cross02">--%>
<%--		<div class="hamburger__line-in hamburger__line-in--cross02"></div>--%>
<%--	</div>--%>
<%--</div>--%>
	<div class="container" id="top">
<%--		<div class="global-menu">--%>
<%--			<div class="global-menu__wrap">--%>
<%--				<a class="global-menu__item global-menu__item--demo-1" href="#">Login</a>--%>
<%--				<a class="global-menu__item global-menu__item--demo-1" href="#">Register</a>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="row" style="margin-top: 280px; ">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/login" id="from1" method="post">
				  <div class="form-group">
				    <label class="col-sm-3 control-label"><font color="#FFFFFF">Username:</font> </label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="userID" placeholder="Username" name="username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-3 control-label"><font color="#FFFFFF">Password:</font> </label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password" placeholder="Password" name="password">
				    </div>
				  </div>
				  <div class="form-group pull-right" style="margin-right: 15px;">
<%--					  <svg class="shape-overlays" viewBox="0 0 100 100" preserveAspectRatio="none">--%>
<%--						  <path class="shape-overlays__path"></path>--%>
<%--						  <path class="shape-overlays__path"></path>--%>
<%--						  <path class="shape-overlays__path"></path>--%>
<%--					  </svg>--%>
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default btn-info" style="width: 10rem;height: 4rem;font-size: 1rem;font-weight: 700;
				      color: #ffffff;border-radius: 1.5rem;background-image: linear-gradient(to right, #74ebd5 0%, #9face6 100%);">Login</button>
				    </div>
				  </div>
<%--			</div>--%>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>		
	</div>
</body>
</html>