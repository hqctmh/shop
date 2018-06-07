<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
</head>
<body>
<div id="test">


    <%
        String fenye=request.getParameter("fenye");
    %>

</div>
<%="fenye:"%>
<%=fenye%>
<script>
    layui.use('laypage', function(){
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'test' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: 1000 //数据总数，从服务端得到
            ,curr: location.hash.replace('?fenye=', '') //获取起始页
            ,hash: 'fenye'
        });
    });
</script>
</body>
</html>

