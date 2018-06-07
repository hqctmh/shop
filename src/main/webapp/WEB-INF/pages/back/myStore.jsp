<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String lockUrl = basePath + "pages/back/admin/lockStore";
    String findStoreUrl=basePath + "pages/back/admin/findStore";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <link rel="shortcut icon" type="image/x-icon" href="resources/admin/dist/img/favicon.ico">
    <!-- Bootstrap 3.3.6 -->
    <link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="resources/admin/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="resources/admin/dist/css/ionicons.min.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="resources/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="resources/admin/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="resources/admin/dist/css/skins/_all-skins.min.css">


    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>
    <!-- FastClick -->
    <script src="resources/admin/plugins/fastclick/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="resources/admin/dist/js/app.min.js"></script>
    <!-- Sparkline -->
    <script src="resources/admin/plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="resources/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="resources/admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- SlimScroll 1.3.0 -->
    <script src="resources/admin/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="resources/admin/plugins/chartjs/Chart.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="resources/admin/plugins/echarts.common.min.js"></script>
    <script type="text/javascript">
        function bind() {
            $("button[id*=lock-]").each(function () {
                var storeId=this.id.split("-")[1];
                $(this).click(function () {
                    $.post("<%=lockUrl%>",{storeId:storeId},function (data) {
                        if(data){
                            alert("操作成功！");
                            location.reload(true);
                        }else{
                            alert("操作失败！");
                            location.reload(true);
                        }
                    },"json")
                })
            });
        }
        $(function () {
            $("#search").click(function () {
                var key=$("#searchVal").val();
                $.post("<%=findStoreUrl%>",{key:key},function (data) {
                    $("#123").empty();
                    $("#123").append("<tbody>");
                    $("#123").append("<tr>\n" +
                        "                                <th>#</th>\n" +
                        "                                <th>店铺名</th>\n" +
                        "                                <th>关联用户</th>\n" +
                        "                                <th>店铺余额</th>\n" +
                        "                                <th>店铺状态</th>\n" +
                        "                                <th>店铺操作</th>\n" +
                        "                            </tr>");
                    if(data){
                        for(var i=0;i<data.length;i++){
                            var j=i+1;
                            var storeId=data[i].id;
                            var storeName=data[i].storeName;
                            var userName=data[i].userName;
                            var balance=data[i].balance;
                            var status=data[i].status=="0"?"正常":"锁定";
                            var statusclass=data[i].status=="0"?"success":"danger";
                            var do1=data[i].status=="0"?"锁定":"解锁";
                            var do1class=data[i].status=="0"?"danger":"info";
                            $("#123").append("<tr>\n" +
                                "                                <td>"+j+"</td>\n" +
                                "                                <td>"+storeName+"</td>\n" +
                                "                                <td>"+userName+"</td>\n" +
                                "                                <td>"+balance+"</td>\n" +
                                "                                <td><span class=\"label label-"+statusclass+"\">"+status+"</span></td>\n" +
                                "                                <td>\n" +
                                "                                    <button id=\"lock-"+storeId+"\"  type=\"button\" class=\"btn btn-"+do1class+"\">"+do1+"</button>\n" +
                                "                                </td>\n" +
                                "                            </tr>");
                            $("#123").append("</tbody>");
                        }
                        bind();
                    }else{
                        $("#123").append("<h3>查询为空</h3>");
                        $("#123").append("</tbody>");
                    }
                },"json")

            });
            bind();
        })

    </script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">

        <!-- Logo -->
        <a href="index.html" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>R</b>C</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>RonCoo-admin </b>LTE</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="<%=basePath%>/resources/admin/dist/img/user2-160x160.jpg" class="user-image"
                                 alt="User Image">
                            <span class="hidden-xs">Alexander Pierce</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="<%=basePath%>/resources/admin/dist/img/user2-160x160.jpg" class="img-circle"
                                     alt="User Image">

                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="<%=basePath%>/resources/admin/dist/img/user2-160x160.jpg" class="img-circle"
                         alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Admin</p>

                </div>
            </div>
            <!-- search form -->

            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="active"><a href="#"><i class="fa fa-circle-o"></i>一级菜单</a></li>
                <li class=""><a href="#"><i class="fa fa-circle-o"></i>一级菜单</a></li>
                <li class=""><a href="#"><i class="fa fa-circle-o"></i>一级菜单</a></li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">


        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">店铺管理</h3>

                        <div class="box-tools">
                            <div class="input-group input-group-sm" style="width: 150px;">
                                <input type="text" name="table_search" class="form-control pull-right" placeholder="Search" id="searchVal">

                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default" id="search"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover" id="123">
                            <tr>
                                <th>#</th>
                                <th>店铺名</th>
                                <th>关联用户</th>
                                <th>店铺余额</th>
                                <th>店铺状态</th>
                                <th>店铺操作</th>
                            </tr>
                            <%int i=1;%>
                            <c:forEach items="${StoreList}" var="store">
                            <tr>
                                <td><%=i++%></td>
                                <td>${store.storeName}</td>
                                <td>${store.userName}</td>
                                <td>${store.balance}</td>
                                <td><span class="label label-${store.status=="0"?"success":"danger"}">${store.status=="0"?"正常":"已锁定"}</span></td>
                                <td>
                                    <button id="lock-${store.id}"  type="button" class="btn btn-${store.status=="0"?"danger":"info"}">${store.status=="0"?"锁定":"解锁"}</button>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>


    </div>
    <!-- /.content-wrapper -->


</div>
<!-- ./wrapper -->

<footer class="main-footer">
    <div class="pull-right hidden-xs">
        <b>Version</b> 2.3.6
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
    reserved.
</footer>
</body>
</html>

