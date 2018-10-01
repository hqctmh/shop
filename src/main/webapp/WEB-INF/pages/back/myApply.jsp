<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String findUrl = basePath + "pages/back/admin/findUser";
    String lockUrl = basePath + "pages/back/admin/lockUser";
    String chongzhiUrl=basePath + "pages/back/admin/chognzhiUser";
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
                <ul class="sidebar-menu">
                    <li class="${active=="index"?"active":""}"><a href="<%=basePath%>pages/back/admin/index"><i class="fa fa-circle-o"></i>店铺销量统计</a></li>
                    <li class="${active=="myUser"?"active":""}"><a href="<%=basePath%>pages/back/admin/myUser"><i class="fa fa-circle-o"></i>用户管理</a></li>
                    <li class="${active=="myStore"?"active":""}"><a href="<%=basePath%>pages/back/admin/myStore"><i class="fa fa-circle-o"></i>店铺管理</a></li>
                    <li class="${active=="myApply"?"active":""}"><a href="<%=basePath%>pages/back/admin/myApply"><i class="fa fa-circle-o"></i>店铺申请</a></li>
                </ul>

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
                        <h2 class="box-title">用户管理</h2>

                        <div class="box-tools">
                            <div class="input-group input-group-sm" style="width: 150px;">
                                <input type="text" name="table_search" class="form-control pull-right"
                                       placeholder="Search" id="keyWord">

                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default" id="search"><i
                                            class="fa fa-search"></i></button>
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
                                <th>申请用户</th>
                                <th>操作</th>
                            </tr>
                            <%int i = 1;%>
                            <c:forEach items="${applyList}" var="apply">
                                <tr>
                                    <td><%=i++%></td>
                                    <td>${apply.storeName}</td>
                                    <td>${apply.userName} </td>
                                    <td><a href="<%=basePath%>pages/back/admin/updateApplyStatus?status=1&applyId=${apply.applyId}">同意</a>　<a href="<%=basePath%>pages/back/admin/updateApplyStatus?status=0&applyId=${apply.applyId}">拒绝</a></td>
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

