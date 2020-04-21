<%--
  Created by IntelliJ IDEA.
  User: 刘思淼
  Date: 2020/4/13
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
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
                        <h1 style="text-align: center;">Edit The Fee</h1>
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
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/editFee" id="editfrom" method="post">
                        <div class="form-group ">
                            <label for="inputEmail3" class="col-sm-2 control-label" >Fee ID</label>
                            <div class="col-sm-10">
                                <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" readonly="readonly" type="number" class="form-control" id="inputEmail3" name="fid" placeholder="Enter ID"
                                <c:if test='${fee!=null}'>
                                    value="${fee.fid}"
                                </c:if>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">Fee</label>
                            <div class="col-sm-10">
                                <input onmouseover="this.style.borderColor='black';this.style.backgroundColor='#fff4f3'"
                                       style="width: 106; height: 21"
                                       onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'" style="border-width:1px;border-color:black" type="text" class="form-control" id="inputPassword3" name="famount" placeholder="Enter Fee" value="${fee.famount}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">To Whom</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="fpayerusername" id="name">
                                    <c:forEach items="${userloginList}" var="item">
                                        <option value="${item.username}">${item.username}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
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
    $("#nav li:nth-child(2)").addClass("active")

    var nameSelect = $("#name option");
    for (var i=0; i<nameSelect.length; i++) {
        if (nameSelect[i].value == '${fee.fpayerusername}') {
            nameSelect[i].selected = true;
        }
    }
</script>
</html>
