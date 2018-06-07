<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
<html>
<head>
    <base href="<%=basePath%>">
    <link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="resources/css/login.css">
    <link type="text/css" rel="stylesheet" href="resources/zhuce_files/reset.css">
    <link type="text/css" rel="stylesheet" href="resources/zhuce_files/layout.css">
    <link type="text/css" rel="stylesheet" href="resources/zhuce_files/registerpwd.css">
    <link href="resources/css/base.css" type="text/css" rel="stylesheet">
    <link href="resources/css/mycss.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="resources/js/echarts.common.min.js"></script>
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <script>
        function Trim(str)
        {
            return str.replace(/^\s+|\s+$/g,"");
        }
        function toDecimal2(x) {
            var f = parseFloat(x);
            if (isNaN(f)) {
                return false;
            }
            var f = Math.round(x * 100) / 100;
            var s = f.toString();
            var rs = s.indexOf('.');
            if (rs < 0) {
                rs = s.length;
                s += '.';
            }
            while (s.length <= rs + 2) {
                s += '0';
            }
            return s;
        }
        $(function () {
            var sell = echarts.init(document.getElementById('sell'),'light');
            var goodsSell=echarts.init(document.getElementById('goodsSell'),'light');
            option1 = {
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [${list[0]}, ${list[1]}, ${list[2]}, ${list[3]}, ${list[4]}, ${list[5]}, ${list[6]}],
                    type: 'line',
                    smooth: true
                }]
            };
            option2 = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['${GoodsList[0].name}','${GoodsList[1].name}','${GoodsList[2].name}','${GoodsList[3].name}','${GoodsList[4].name}','${GoodsList[5].name}']
                },
                series: [
                    {
                        name:'商品',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:${GoodsList[0].price}, name:'${GoodsList[0].name}'},
                            {value:${GoodsList[1].price}, name:'${GoodsList[1].name}'},
                            {value:${GoodsList[2].price}, name:'${GoodsList[2].name}'},
                            {value:${GoodsList[3].price}, name:'${GoodsList[3].name}'},
                            {value:${GoodsList[4].price}, name:'${GoodsList[4].name}'},
                            {value:${GoodsList[5].price}, name:'${GoodsList[5].name}'}
                        ]
                    }
                ]
            };
            sell.setOption(option1);
            goodsSell.setOption(option2);
        })
    </script>
    <style>
        h1{
            font-size: 30px;
        }
        .myslider{
            height: 905px;
        }
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/pages/plugins/header.jsp">
    <jsp:param name="basePath" value="<%=basePath%>"/>
    <jsp:param name="itemList" value="${itemList}"/>
    <jsp:param name="subItemMap" value="${subItemMap}"/>
</jsp:include>


<!--index content start-->
<div class="row content" >
    <div class="lf myslider">
        <div class="head_name">
            <p class="headimg"><img src="<%=basePath%>resources/upload/portrait/${user.pic}"></p>
            <p>${user.name} </p>
        </div>

        <c:if test="${user.userType==0}">
            <a class="${active=="my_index"?"actives":""}" href="<%=basePath%>pages/front/member/myIndex"><img src="<%=basePath%>/resources/images/icon2.png"> 我的信息</a>
            <a class="${active=="my_order"?"actives":""}" href="<%=basePath%>pages/front/member/myAllOrder?type=myAllOrder"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的订单</a>
            <a class="${active=="my_return_order"?"actives":""}" href="<%=basePath%>pages/front/member/findMyReturn?type=all_return"><img src="<%=basePath%>/resources/images/icon2.png"> 我的退货记录</a>
            <a class="${active=="my_index"?"actives":""}" href="my_index.html"><img src="<%=basePath%>/resources/images/icon2.png"> 我的收货地址</a>
        </c:if>
        <c:if test="${user.userType==1}">
            <a href="<%=basePath%>pages/front/member/myIndex"><img src="<%=basePath%>/resources/images/icon2.png"> 我的信息</a>
            <a href="my_smallstore.html"><img src="<%=basePath%>/resources/images/icon3.png"> 我的微店</a>
            <a class="${active=="my_order"?"actives":""}" href="href=<%=basePath%>pages/front/member/myAllOrder?type=myAllOrder"><img src="<%=basePath%>/resources/images/icon3.png"> 我的订单</a>
            <a class="${active=="my_return_order"?"actives":""}" href="<%=basePath%>pages/front/member/findMyReturn?type=all_return"><img src="<%=basePath%>/resources/images/icon2.png"> 我的退货记录</a>
            <a class="${active=="add_goods"?"actives":""}" href="<%=basePath%>pages/front/member/addGoodsPro"><img src="<%=basePath%>/resources/images/icon3.png"> 添加商品</a>
            <a class="${active=="my_goods"?"actives":""}" href="<%=basePath%>pages/front/member/myGoods"><img src="<%=basePath%>/resources/images/icon3.png"> 我的商品</a>
            <a class="${active=="statistics"?"actives":""}" href="<%=basePath%>pages/front/member/myStatistics"><img src="<%=basePath%>/resources/images/icon3.png"> 统计</a>
        </c:if>

    </div>

    <div class="col-lg-10">

        <div>
            <h1>近七天销售额</h1>
            <div id="sell" style="width: 600px;height:400px;">

            </div>
        </div>
        <div>
            <h1>商品销售统计</h1>
            <div id="goodsSell" style="width: 600px;height:400px;">

            </div>
        </div>

    </div>
</div>
<!--index content end-->

<!--index service start-->
<div class="nav-footer">
    <ul class="row">
        <li>
            <div></div>
            <strong> <a href="">正品保障</a> </strong>
            <p>正品保障，提供发票</p></li>
        <li>
            <div></div>
            <strong> <a href="">急速物流</a> </strong>
            <p>急速物流，急速送达</p></li>
        <li>
            <div></div>
            <strong> <a href="">无忧售后</a> </strong>
            <p>七天无理由退货</p></li>
        <li>
            <div></div>
            <strong> <a href="">特设服务</a> </strong>
            <p>私人定制套餐</p></li>
        <li>
            <div></div>
            <strong> <a href="">帮助中心</a> </strong>
            <p>您的购物中心</p></li>
    </ul>
</div>
<div class="help">
    <div class="row">
        <ul class="footer2">
            <li class="tel"><p class="working"><img src="<%=basePath%>/resources/images/icon14.png"> 工作日 9:00~18:00</p>
                <p class="tels">400-6653-666</p></li>
            <li><h4>新手指南</h4><a href="zhuce.html">注册新用户</a><a href="#">家具订购流程</a><a href="#">支付方式</a><a
                    href="#">了解产品工艺</a></li>
            <li><h4>购物保障</h4><a href="#">正品保证</a><a href="#">注册协议</a><a href="#">隐私保护</a><a href="#">免责申明</a><a
                    href="#">商品保障</a></li>
            <li><h4>配送安装</h4><a href="#">配送安装</a><a href="#">收货指南</a><a href="#">包装说明</a></li>
            <li><h4>售后服务</h4><a href="#">30天退换货说明</a><a href="#">退款说明</a><a href="#">维修补件说明</a><a href="#">家具保养说明</a><a
                    href="#">假一赔三</a></li>
            <li class="code"><h4>微信公众号</h4>
                <p><img src="<%=basePath%>/resources/images/gaoliuxuerweima.jpg"></p></li>
        </ul>
        <div class="clr"></div>
    </div>
</div>

<!--footer start-->
<div class="footer">
    <div class="row">
        &copy; 2016 广州鼎瀛计算机科技有限公司
    </div>
</div>
<!--footer end-->


</body>
</html>
