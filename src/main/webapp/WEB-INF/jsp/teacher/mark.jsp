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
					    	<h1 style="text-align: center;">Give Marks</h1>
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
						<form name="reset" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/teacher/mark" id="editfrom" method="post" onsubmit="return check()">
							<div class="form-group">
								<div class="col-sm-10">
									<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black"  readonly="readonly" type="hidden" class="form-control" name="courseid" id="inputEmail3" value="${selectedCourse.courseid}">
								</div>
							</div>
							<div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">Stu ID</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black"  readonly="readonly" type="text" class="form-control" name="studentid" id="inputEmail3" value="${selectedCourse.studentid}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">Stu Name</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black"  readonly="readonly" type="text" name="name" class="form-control" id="inputPassword3" value="${selectedCourse.studentCustom.username}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">Score</label>
							    <div class="col-sm-10">
							      <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="number" name="mark" class="form-control" id="inputPassword3" placeholder="Enter Score">
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
        if(reset.mark.value==""||reset.mark.value==null)
        {alert("Please Enter a Mark");return false;}
    }
</script>
</html>