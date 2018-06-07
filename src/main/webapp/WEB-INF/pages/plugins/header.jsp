<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <!--topnav start-->
    <div class="topnav">
        <div class="row">
            <ul class="lf">
                <c:if test="${user==null}">
                <a href="${basePath}login.jsp">登录</a><span class="line">|</span><a href="${basePath}regist.jsp">免费注册</a>
                </c:if>
                <c:if test="${user!=null}">
                    <a href="${basePath}logout">注销</a>
                </c:if>
                <!--login.html-->
                <!--register_user.html-->
            </ul>
            <ul class="rf">
                <a href="wodediingdan.html">我的订单 <i class="iconfont"></i></a><span class="line">|</span>
                <a href="${basePath}pages/front/member/myIndex">我的信息 <i class="iconfont"></i></a><span class="line">|</span>
                <a href="${basePath}pages/front/member/myShopCar">购物车 <i class="iconcar"></i></a>
            </ul>
            <div class="clr"></div>
        </div>
    </div>
    <!--topnav end-->

    <!--search start-->
    <div class="row ">
        <div class=" lf logo"><img src="${basePath}/resources/images/gaoliuxutop.png"></div>
        <div class="lf search">
            <!--<ul class="ch_ul ch_pro">-->
            <!--<li class="chLi">宝贝</li><li>店铺</li>-->
            <!--<div class="clr"></div>-->
            <!--</ul>-->
            <form method="post" action="${basePath}Goods/allGoods">
                <div class="ch_ul">
                    <input class="search_txt" autocomplete="off" type="text" value="" name="goodsName" placeholder="请输入关键词">
                    <input class="search_sub" type="submit" value="搜 索">
                    <div class="clr"></div>
                </div>
            </form>
            <div class="clr"></div>
        </div>

        <div class="clr"></div>
    </div>
    <!--search enf-->
    <!--topad start-->
    <div class="navigation">
        <div class="row">
            <div class="lf all_classify">
                <!--全部商品分类-->
                <a>全部商品分类</a>
                <div class="slider_bar">
                    <c:forEach items="${itemList}" var="item">
                        <li>
                            <h4>${item.title}</h4>
                            <c:forEach items="${subItemMap[item.id]}" var="subItem">
                                <a href="${basePath}Goods/allGoods?subId=${subItem.id}">${subItem.title}</a>
                            </c:forEach>
                        </li>
                    </c:forEach>

                    <div class="clr"></div>
                </div>
            </div>
            <nav class="lf nav">
                <a class="${active=="index"?"hover":""}" href="${basePath}">首页</a>
                <a class="${active=="allGoods"?"hover":""}" href="${basePath}Goods/allGoods">全部商品</a>
                <div class="clr"></div>
            </nav>
            <div class="clr"></div>
        </div>
    </div>
    <!--topad start-->
</div>