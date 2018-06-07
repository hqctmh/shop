<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page="/WEB-INF/pages/plugins/include_head.jsp"/>

</head>
<body>
<jsp:include page="/WEB-INF/pages/plugins/header.jsp">
    <jsp:param name="basePath" value="<%=basePath%>"/>
    <jsp:param name="itemList" value="${itemList}"/>
    <jsp:param name="subItemMap" value="${subItemMap}"/>
</jsp:include>


<!--banner start-->

<%----%>
<!--banner end -->
<c:forEach var="item" items="${itemList}">
    <div class="home-main page-main">
        <div class="container">
            <div class="box-ht">
                <h2 class="tittle slidecon">${item.title}</h2>
                <div class="more">
                    <a href="<%=basePath%>Goods/allGoods?iid=${item.id}"><span>查看全部 </span><i class="icon">></i></a>
                </div>
            </div>
            <div class="main">
                <c:forEach var="goods" varStatus="status" begin="0" end="4" items="${goodsMap[item.id]}">
                    <a href="<%=basePath%>/Goods/goodsDetail?goodsId=${goods.id}">
                    <div class="part ${status.index==0?"row-first":""}">
                        <img src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}" alt=""/>
                        <div class="black">${goods.goodsName}</div>
                        <div class="gray">&nbsp</div>
                        <div class="red">￥${goods.price}元</div>
                    </div>
                    </a>
                </c:forEach>
            </div>
            <div class="main">
                <c:forEach var="goods" varStatus="status" begin="5" end="9" items="${goodsMap[item.id]}">
                    <a href="<%=basePath%>/Goods/goodsDetail?goodsId=${goods.id}">
                    <div class="part ${status.index==5?"row-first":""}">
                        <img src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}" alt=""/>
                        <div class="black">${goods.goodsName}</div>
                        <div class="gray">&nbsp</div>
                        <div class="red">￥${goods.price}元</div>
                    </div>
                    </a>
                </c:forEach>

            </div>
        </div>

    </div>
</c:forEach>

<div class="nav-footer">
    <ul class="row">
        <li>
            <div></div>
            <strong>
                <a href="">正品保障</a>
            </strong>
            <p>正品保障，提供发票</p>
        </li>
        <li>
            <div></div>
            <strong>
                <a href="">急速物流</a>
            </strong>
            <p>急速物流，急速送达</p>
        </li>
        <li>
            <div></div>
            <strong>
                <a href="">无忧售后</a>
            </strong>
            <p>七天无理由退货</p>
        </li>
        <li>
            <div></div>
            <strong>
                <a href="">特设服务</a>
            </strong>
            <p>私人定制套餐</p>
        </li>
        <li>
            <div></div>
            <strong>
                <a href="">帮助中心</a>
            </strong>
            <p>您的购物中心</p>
        </li>
    </ul>
</div>

<div class="help">
    <div class="row">
        <ul class="footer2">
            <li class="tel">
                <p class="working"><img src="<%=basePath%>/resources/images/icon14.png"> 工作日 9:00~18:00</p>
                <p class="tels">400-6653-666</p>
            </li>
            <li>
                <h4>新手指南</h4><a href="zhuce.html">注册新用户</a>
                <a href="footer_1_2.html" target="_blank">家具订购流程</a>
                <a href="footer_1_3.html" target="_blank">支付方式</a>
                <a href="footer_1_4.html" target="_blank">了解产品工艺</a>
            </li>
            <li>
                <h4>购物保障</h4><a href="footer_2_1.html" target="_blank">正品保证</a>
                <a href="footer_2_1.html" target="_blank">注册协议</a>
                <a href="footer_2_3.html" target="_blank">隐私保护</a>
                <a href="footer_2_4.html" target="_blank">免责申明</a>
                <a href="footer_2_5.html" target="_blank">商品保障</a>
            </li>
            <li>
                <h4>配送安装</h4><a href="footer_3_1.html" target="_blank">配送安装</a>
                <a href="footer_3_2.html" target="_blank">收货指南</a>
                <a href="footer_3_3.html" target="_blank">包装说明</a>
            </li>
            <li>
                <h4>售后服务</h4><a href="footer_4_1.html" target="_blank">30天退换货说明</a>
                <a href="footer_4_2.html" target="_blank">退款说明</a>
                <a href="footer_4_3.html" target="_blank">维修补件说明</a>
                <a href="footer_4_4.html" target="_blank">家具保养说明</a>
                <a href="footer_4_5.html" target="_blank">假一赔三</a>
            </li>
            <li class="code">
                <h4>微信公众号</h4>
                <p><img src="<%=basePath%>/resources/images/gaoliuxuerweima.jpg"></p>
            </li>
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