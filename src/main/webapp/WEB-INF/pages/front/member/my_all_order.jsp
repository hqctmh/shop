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
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <script src="resources/js/hmJs.js"></script>
    <script>
        function Trim(str) {
            return str.replace(/^\s+|\s+$/g, "");
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
            $("a[id*=message-]").each(function () {
                var goodsId = this.id.split("-")[1];
                $(this).click(function () {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.prompt({
                            formType: 2,
                            btn: '确定',
                            offset: ['182px', '809px'],
                            title: '请输入商品评价',
                            area: ['400px', '200px'] //自定义文本域宽高
                        }, function (value, index, elem) {
                            $.post("<%=basePath%>pages/front/member/message", {
                                goodsId: goodsId,
                                msg: value
                            }, function (data) {
                                if (Trim(data) == "true") {
                                    alert("留言成功！");
                                    //window.location=""
                                } else {
                                    alert("留言失败！");
                                }
                            }, "text");
                            //alert(value); //得到value
                            layer.close(index);
                        });
                    })
                })
            })
        })
    </script>
    <style>
        #myGoodsPage {
            position: absolute;
            top: 900px;
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
<div class="row content">
    <div class="lf myslider">
        <div class="head_name">
            <p class="headimg"><img src="<%=basePath%>resources/upload/portrait/${user.pic}"></p>
            <p>${user.name} </p>
        </div>

        <c:if test="${user.userType==0}">
            <a class="${active=="my_index"?"actives":""}" href="<%=basePath%>pages/front/member/myIndex"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的信息</a>
            <a class="${active=="my_order"?"actives":""}" href="<%=basePath%>pages/front/member/myAllOrder?type=myAllOrder"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的订单</a>
            <a class="${active=="my_return_order"?"actives":""}" href="<%=basePath%>pages/front/member/findMyReturn?type=all_return"><img src="<%=basePath%>/resources/images/icon2.png"> 我的退货记录</a>
            <a class="${active=="my_address"?"actives":""}" href="<%=basePath%>pages/front/member/myAddress"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的收货地址</a>
            <a class="${active=="registStorePro"?"actives":""}" href="<%=basePath%>pages/front/member/registStorePro"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 申请店铺</a>
            <a class="${active=="myApply"?"actives":""}" href="<%=basePath%>pages/front/member/myApply"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的申请</a>
        </c:if>
        <c:if test="${user.userType==1}">
            <a class="${active=="my_index"?"actives":""}" href="<%=basePath%>pages/front/member/myIndex"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的信息</a>
            <a class="${active=="my_order"?"actives":""}" href="<%=basePath%>pages/front/member/myAllOrder?type=myAllOrder"><img
                    src="<%=basePath%>/resources/images/icon2.png"> 我的订单</a>
            <a class="${active=="my_return_order"?"actives":""}" href="<%=basePath%>pages/front/member/findMyReturn?type=all_return"><img src="<%=basePath%>/resources/images/icon2.png"> 我的退货记录</a>
            <a class="${active=="add_goods"?"actives":""}" href="<%=basePath%>pages/front/member/addGoodsPro"><img
                    src="<%=basePath%>/resources/images/icon3.png"> 添加商品</a>
            <a class="${active=="my_goods"?"actives":""}" href="<%=basePath%>pages/front/member/myGoods"><img src="<%=basePath%>/resources/images/icon3.png"> 我的商品</a>
            <a class="${active=="statistics"?"actives":""}" href="<%=basePath%>pages/front/member/myStatistics"><img src="<%=basePath%>/resources/images/icon3.png"> 统计</a>
        </c:if>

    </div>

    <div class="lf mycontent">
        <div class="rfcon_tit">
            <li class="${orderActive=="myAllOrder"?"lihover":""}"><a
                    href="<%=basePath%>pages/front/member/myAllOrder?type=myAllOrder">全部订单</a></li>
            <i class="lf">|</i>
            <li class="${orderActive=="shouldPayOrder"?"lihover":""}"><a
                    href="<%=basePath%>pages/front/member/myAllOrder?type=shouldPayOrder">待付款</a></li>
            <i class="lf">|</i>
            <li class="${orderActive=="havePayOrder"?"lihover":""}"><a
                    href="<%=basePath%>pages/front/member/myAllOrder?type=havePayOrder">已付款</a></li>
            <i class="lf">|</i>
            <li class="${orderActive=="shouldReceiveOrder"?"lihover":""}"><a
                    href="<%=basePath%>pages/front/member/myAllOrder?type=shouldReceiveOrder">待收货</a></li>
            <i class="lf">|</i>
            <li class="${orderActive=="comOrder"?"lihover":""}"><a
                    href="<%=basePath%>pages/front/member/myAllOrder?type=comOrder">已完成</a></li>
            <div class="clr"></div>
        </div>
        <div class="od_info">
            <table border="0" cellpadding="0" cellspacing="0" id="orderTable">
                <c:forEach items="${list}" var="item">
                    <tr class="IndentTitle">
                        <th class="f1">
                            <div class="num">订单号：${item.orderNum}</div>
                        </th>
                        <th class="f2">
                            <div class="store">${item.storeName}</div>
                        </th>
                        <th class="f3">
                            <div class="store">订单状态：${item.statusName}</div>
                        </th>
                        <th class="f4">
                            <div class="store">
                                <c:if test="${item.doModelList!=null}">
                                    操作：
                                    <c:forEach items="${item.doModelList}" var="doModel">
                                        <a href="<%=basePath%>pages/front/member/${doModel.url}&storeIndentId=${item.storeIndentId}">${doModel.name}</a>&nbsp;
                                    </c:forEach>
                                </c:if>
                            </div>
                        </th>
                    </tr>
                    <c:forEach items="${item.modelList}" var="model">
                        <tr>
                            <td class="first-child">
                                <div class="t1">
                                    <div class="lf od-pic"><img
                                            src="<%=basePath%>/resources/upload/goodsPic/${model.pic}"></div>
                                    <div class="lf od-txt">
                                        <p>${model.goodsName}</p>
                                        <p class="color02">下单时间：${model.createTime}</p>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </td>
                            <td class="color04">
                                <div class="d2">￥${model.price}</div>
                            </td>
                            <td>
                                <div class="t2">x${model.detailAmount}</div>
                            </td>

                            <c:if test="${user.userType=='0'}">
                                <td>
                                    <div class="t3"><a id="message-${model.goodsId}">留言</a></div>
                                </td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </c:forEach>
                <div id="myGoodsPage">

                </div>
                <script>
                    layui.use('laypage', function () {
                        var laypage = layui.laypage;

                        //执行一个laypage实例
                        laypage.render({
                            elem: 'myGoodsPage' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: ${count} //数据总数，从服务端得到
                            , limit: 5
                            , jump: function (obj, first) {
                                //obj包含了当前分页的所有参数，比如：
                                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                                console.log(obj.limit); //得到每页显示的条数

                                //首次不执行
                                if (!first) {
                                    $("#orderTable").empty();
                                    $.post("<%=basePath%>pages/front/member/myAllOrderForPage?type=${orderActive}", {pageNum: obj.curr}, function (data) {

                                        for (var i = 0; i < data.length; i++) {
                                            var orderNum = data[i].orderNum;
                                            var storeName = data[i].storeName;
                                            var statusName = data[i].statusName;
                                            var doModelList = data[i].doModelList;
                                            var storeIndentId = data[i].storeIndentId;
                                            var modelList = data[i].modelList;
                                            var ddo = "";
                                            if (doModelList) {
                                                ddo = "操作：";

                                                for (var x = 0; x < doModelList.length; x++) {
                                                    ddo = ddo + "<a href=\"<%=basePath%>pages/front/member/" + doModelList[x].url + "&storeIndentId=" + storeIndentId + "\">" + doModelList[x].name + "</a>&nbsp;"
                                                }
                                            }
                                            console.log(ddo)
                                            $("#orderTable").append("<tr class=\"IndentTitle\">\n" +
                                                "                    <th class=\"f1\"><div class=\"num\">订单号：" + orderNum + "</div></th>\n" +
                                                "                    <th class=\"f2\"><div class=\"store\">" + storeName + "</div></th>\n" +
                                                "                    <th class=\"f3\"><div class=\"store\">订单状态：" + statusName + "</div></th>\n" +
                                                "                    <th class=\"f4\"><div class=\"store\">" + ddo + "</div></th>\n" +
                                                "                </tr>");
                                            for (var j = 0; j < modelList.length; j++) {
                                                var type = '';
                                                type = "${user.userType}";
                                                if (type == "0") {
                                                    $("#orderTable").append("<tr>\n" +
                                                        "                \t<td class=\"first-child\">\n" +
                                                        "                        <div class=\"t1\">\n" +
                                                        "                        \t<div class=\"lf od-pic\"><img src=\"<%=basePath%>/resources/upload/goodsPic/" + modelList[j].pic + "\"></div>\n" +
                                                        "                            <div class=\"lf od-txt\">\n" +
                                                        "                            \t" + modelList[j].goodsName + "</p>                    \n" +
                                                        "                                <p class=\"color02\">下单时间：" + modelList[j].createTime + "</p>\n" +
                                                        "                            </div>\n" +
                                                        "                            <div class=\"clr\"></div>\n" +
                                                        "                        </div>\n" +
                                                        "                    </td>\n" +
                                                        "                    <td class=\"color04\"><div class=\"d2\">￥" + modelList[j].price + "</div></td>\n" +
                                                        "                    <td><div class=\"t2\">x" + modelList[j].detailAmount + "<div></td>\n" +
                                                        "                    <td><div class=\"t3\"><a href=\"#\">留言</a></div></td>\n" +
                                                        "                    \n" +
                                                        "                </tr>");
                                                } else {
                                                    $("#orderTable").append("<tr>\n" +
                                                        "                \t<td class=\"first-child\">\n" +
                                                        "                        <div class=\"t1\">\n" +
                                                        "                        \t<div class=\"lf od-pic\"><img src=\"<%=basePath%>/resources/upload/goodsPic/" + modelList[j].pic + "\"></div>\n" +
                                                        "                            <div class=\"lf od-txt\">\n" +
                                                        "                            \t" + modelList[j].goodsName + "</p>                    \n" +
                                                        "                                <p class=\"color02\">下单时间：" + modelList[j].createTime + "</p>\n" +
                                                        "                            </div>\n" +
                                                        "                            <div class=\"clr\"></div>\n" +
                                                        "                        </div>\n" +
                                                        "                    </td>\n" +
                                                        "                    <td class=\"color04\"><div class=\"d2\">￥" + modelList[j].price + "</div></td>\n" +
                                                        "                    <td><div class=\"t2\">x" + modelList[j].detailAmount + "<div></td>\n" +
                                                        "                    \n" +
                                                        "                </tr>");
                                                }
                                            }
                                        }
                                    }, "json");

                                }
                            }
                        });
                    });
                </script>
            </table>
        </div>
    </div>
    <div class="clr"></div>
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
