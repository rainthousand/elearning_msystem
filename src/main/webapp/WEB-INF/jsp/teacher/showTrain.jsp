<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Training Info</title>

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
                        <h1 class="col-md-5">My Training</h1>
                        <button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='${pageContext.request.contextPath}/teacher/addTrain'">
                            Add a Train
                            <sapn class="glyphicon glyphicon-plus"/>
                        </button>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Training ID</th>
                        <th>Course ID</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach  items="${trainList}" var="item">
                        <tr>
                            <td>${item.trainid}</td>
                            <td>${item.courseName}</td>
                            <td>${item.trdescription}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onClick="location.href='${pageContext.request.contextPath}/teacher/editTrain?id=${item.trainid}'">Edit</button>
                                <button class="btn btn-default btn-xs btn-danger btn-primary" onClick="location.href='${pageContext.request.contextPath}/teacher/removeTrain?id=${item.trainid}'">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.upPageNo}">&laquo;Previous</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/teacher/showTrain?page=${pagingVO.totalCount}">Last&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
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
    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function confirmd() {
        var msg = "Sure？";
        if (confirm(msg)==true){
            return true;
        }else{
            return false;
        }
    }

    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>