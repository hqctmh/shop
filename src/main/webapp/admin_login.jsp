
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String loginUrl=basePath+"adminLogin";
%>
<!DOCTYPE html>

<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/log/login.css">
    <style>
        #btn_sub{
            margin-top: 70px;
        }
        #div1{
            margin-top: 40px;
        }
    </style>
</head>
<body background="resources/css/images/log/hamish-weir-593423-unsplash.jpg">
<%--<script src="resources/js/main.js"></script>--%>
<div class="container box">
    <!-- Content here -->


    <div class="logo">
        <h1 class="center">LOGO</h1>
    </div>

    <form action="<%=loginUrl%>" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">用户名</label>
            <input autocomplete="off" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="请输入用户名" name="phone">

        </div>
        <div class="form-group" id="div1">
            <label for="exampleInputPassword1">密码</label>
            <input autocomplete="off" type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码" name="password">
        </div>
        <input type="submit" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#Modal1" id="btn_sub" value="登录">
    </form>

</div>



</body>
</html>
