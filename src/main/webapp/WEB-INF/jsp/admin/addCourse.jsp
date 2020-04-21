<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		.black_overlay{
			display: none;
			position: absolute;
			top: 0%;
			left: 0%;
			width: 100%;
			height: 100%;
			background-color: black;
			z-index:1001;
			-moz-opacity: 0.8;
			opacity:.80;
			filter: alpha(opacity=80);
		}
		.white_content {
			display: none;
			position: absolute;
			top: 10%;
			left: 10%;
			width: 80%;
			height: 80%;
			border: 16px solid lightblue;
			background-color: white;
			z-index:1002;
			overflow: auto;
		}

		#wrapper{
			width:960px;
			margin:0 auto;
			margin-bottom:15px;
		}
	</style>

	<script type="text/javascript">
		//弹出隐藏层
		function ShowDiv(show_div,bg_div){
			document.getElementById(show_div).style.display='block';
			document.getElementById(bg_div).style.display='block' ;
			var bgdiv = document.getElementById(bg_div);
			bgdiv.style.width = document.body.scrollWidth;
// bgdiv.style.height = $(document).height();
			$("#"+bg_div).height($(document).height());
		};
		//关闭弹出层
		function CloseDiv(show_div,bg_div)
		{
			document.getElementById(show_div).style.display='none';
			document.getElementById(bg_div).style.display='none';

			location.reload();
		};
	</script>
</head>
<body>

<jsp:include page="head.jsp"></jsp:include>
<%--<div class="global-menu">--%>
<%--	<div class="global-menu__wrap">--%>
<%--		<a class="global-menu__item global-menu__item--demo-1" href="#">Login</a>--%>
<%--		<a class="global-menu__item global-menu__item--demo-1" href="#">Register</a>--%>
<%--	</div>--%>
<%--</div>--%>
<%--<svg class="shape-overlays" viewBox="0 0 100 100" preserveAspectRatio="none">--%>
<%--	<path class="shape-overlays__path"></path>--%>
<%--	<path class="shape-overlays__path"></path>--%>
<%--	<path class="shape-overlays__path"></path>--%>
<%--</svg>--%>
<div class="container" id="content">
	<div class="row">
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="col-md-10">
<%--			<div class="global-menu">--%>
<%--				<div class="global-menu__wrap">--%>
<%--					<a class="global-menu__item global-menu__item--demo-1" href="#">Login</a>--%>
<%--					<a class="global-menu__item global-menu__item--demo-1" href="#">Register</a>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<svg class="shape-overlays" viewBox="0 0 100 100" preserveAspectRatio="none">--%>
<%--				<path class="shape-overlays__path"></path>--%>
<%--				<path class="shape-overlays__path"></path>--%>
<%--				<path class="shape-overlays__path"></path>--%>
<%--			</svg>--%>
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<h1 style="text-align: center;">Add a Course</h1>
					</div>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/addCourse" id="editfrom" method="post">
						<div class="form-group">
							<label for="inputcourseid" class="col-sm-2 control-label">Course ID</label>
							<div class="col-sm-10">
								<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="number" class="form-control" id="inputcourseid" name="courseid" placeholder="Enter ID">
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label">Course Name</label>
							<div class="col-sm-10">
								<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="text" class="form-control" id="inputcoursename" name="coursename" placeholder="Enter Name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label" name="grade">Teacher</label>
							<div class="col-sm-10">
								<label>
									<select class="form-control" name="teacherid">
										<c:forEach items="${teacherList}" var="item">
											<option value="${item.userid}">${item.username}</option>
										</c:forEach>
									</select>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label">Course Time</label>
							<div class="col-sm-10">
								<label>
									<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="text" class="form-control" name="coursetime" placeholder="Enter Time">
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label">Classroom</label>
							<div class="col-sm-10">
								<label>
									<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="text" class="form-control" name="classroom" placeholder="Enter Classroom">
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcourseid" class="col-sm-2 control-label">Weeks</label>
							<div class="col-sm-10">
								<label>
									<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="number" class="form-control" name="courseweek" placeholder="Enter Weeks">
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label" name="coursetype">Type</label>
							<div class="col-sm-10">
								<label>
									<select class="form-control" name="coursetype">
										<option value="Required">Required</option>
										<option value="Elective">Elective</option>
										<option value="Common">Common</option>
									</select>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcoursename" class="col-sm-2 control-label" name="grade">Department</label>
							<div class="col-sm-10">
								<label>
									<select class="form-control" name="collegeid">
										<c:forEach items="${collegeList}" var="item">
											<option value="${item.collegeid}">${item.collegename}</option>
										</c:forEach>
									</select>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputcourseid" class="col-sm-2 control-label">Credit</label>
							<div class="col-sm-10">
								<label>
									<input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="number" class="form-control" name="score" placeholder="Enter Credit">
								</label>
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
<script type="text/javascript">

	// $.ajax({
	// 	type: 'POST',
	// 	url: "Role/add",
	// 	data: JSON.stringify(req),
	// 	contentType: 'application/json; charset=UTF-8',
	// 	dataType:"text",
	// 	success: function (data) {
	// 		alert("Successfully Returned Data!");
	// 		alert(data);
	//
	// 		document.getElementById("rName").value = "";
	// 		document.getElementById("rDescription").value = "";
	//
	// 		var items=document.getElementsByName("auth");
	// 		//遍历checkbox
	// 		for(var i=0;i<items.length;i++){
	// 			//当前checkbox实现勾选
	// 			items[i].checked=false;
	// 		}
	// 	};
	function toUpdatePage(SelectedUsername){
		// alert("toUpdatePage1");
		// getSelectedContent();
		var username = SelectedUsername;
		alert(username);
		window.location.href="UpdateCourse.html?values="+username;
	}
</script>
</html>