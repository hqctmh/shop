<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String createIndentUrl = basePath + "pages/front/member/createIndentPro";
%>
<!doctype html>
<html>
<head>
    <base href="<%=basePath%>">
    <link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>

</head>


<div class="col-lg-4">

</div>
<div class="col-lg-4">
    <h1>订单支付</h1>
    <form method="post" action="<%=basePath%>pages/front/member/payByBtn">
        <div class="form-group">
            <h3>订单号：</h3>
            <h3>${storeIndent.orderNum}</h3>
        </div>
        <div class="form-group">
            <h3>交易金额：</h3>
            <h3>${storeIndent.price}</h3>
        </div>
        <div class="form-group">
            <h3>支付密码：</h3>
            <input type="password" class="form-control" id="pass" name="password" placeholder="请输入支付密码">
        </div>
        <input type="hidden" name="storeIndentId" value="${storeIndent.id}">
        <button type="submit" class="btn btn-default">确认支付</button>
    </form>
</div>
<div class="col-lg-4">

</div>


</body>
</html>
