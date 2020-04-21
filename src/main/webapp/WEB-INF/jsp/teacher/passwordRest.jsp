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
			background: url("${pageContext.request.contextPath}/images/2.jpg")repeat;
		}

		/*.text*/
		/*{*/
		/*    text-decoration: none;*/
		/*    color: black;*/
		/*}*/

		#legend
		{
			float: left;
			width: 250px;
			height:400px;
		}

		#legend div
		{
			height: 25px;
		}

		#legend span
		{
			margin: 5px 5px 5px 0;
		}
		#wrapper{
			width:1590px;
			height: 840px;
			/*margin:120px 250px;*/
			/*margin-bottom:15px;*/
			z-index: -10;
			position: absolute;
		}

		.main2{
			width:1590px;
			height: 840px;
			background-color:white;
			border-radius:8px;
			position: absolute;
			z-index: -10;
			padding:40px;
		}
	</style>
</head>
<body>
	
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="nav.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">Password Reset</h1>
						</div>
				    </div>
					<%--                <div class="content">--%>
					<%--                    <div class="input-group form-group-no-border input-lg">--%>
					<%--                                <span class="input-group-addon">--%>
					<%--                                    <i class="now-ui-icons users_circle-08"></i>--%>
					<%--                                </span>--%>
					<%--                        <input type="text" class="form-control" placeholder="Account" id = "account"/>--%>
					<%--                    </div>--%>
					<%--                    <div class="input-group form-group-no-border input-lg">--%>
					<%--                                <span class="input-group-addon">--%>
					<%--                                    <i class="now-ui-icons text_caps-small"></i>--%>
					<%--                                </span>--%>
					<%--                        <input type="password" placeholder="Password" class="form-control" id="password"/>--%>
					<%--                    </div>--%>
					<%--                </div>--%>
					<%--                <div class="footer text-center" id = "app">--%>
					<%--                    <button class="btn btn-primary btn-round btn-lg btn-block" onclick="login()">Login</button>--%>

					<%--                </div>--%>
				    <div class="panel-body">
						<form name="reset" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/passwordRest" id="editfrom" method="post" onsubmit="return check()">
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Old Password</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="text" class="form-control" name="oldPassword" id="inputEmail3" placeholder="Enter Old Password" >
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">New Password</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="password" name="password1" class="form-control" id="inputPassword3" placeholder="Enter New Password">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">Enter Again</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="password" name="password2" class="form-control" id="inputPassword3" placeholder="Enter Again">
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								  <button class="btn btn-default" type="submit">Submit</button>
								  <button class="btn btn-default" type="reset">Reset</button>
							  </div>
						</form>
				    </div>
				    
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
	<div class="row">
		<div class="col-md-12"></div>
	</div>
	</div>
</body>
<script>
    function check() {
		if(reset.oldPassword.value==""||reset.oldPassword.value==null)
		{alert("Please Enter Old Password");return false;}
		if(reset.password1.value==""||reset.password1.value==null)
		{alert("Please Enter New Password");return false;}
		if(reset.password2.value==""||reset.password2.value==null)
		{alert("Please Affirm New Password");return false;}
		if(reset.password1.value != reset.password2.value)
		{alert("Pleass Check Password Again");return false;}
    }
</script>
</html>