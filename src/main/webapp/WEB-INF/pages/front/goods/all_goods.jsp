<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <style>
        .paixu {
            height: 25px;
            line-height: 25px;
            padding: 6px 8px;
            bac0kground-color: rgb(250, 249, 249);
            margin-bottom: 5px;
        }

        .paixu ul {
            float: left;
            height: 25px;
            background-color: white;
            margin-right: 30px;
        }

        .paixu ul li {
            padding: 0px 9px;
            cursor: pointer;
        }

        .paixu-right {
            float: right;
        }

        .paixu-right * {
            float: left;
        }

        .paixu-prev {
            width: 25px;
            height: 25px;
            border: 1px solid rgb(221, 222, 221);
            background-color: rgb(239, 239, 239);
            font-size: 20px;
            text-align: center;
            margin-left: 20px;
        }

        .paixu-next {
            width: 25px;
            height: 25px;
            border: 1px solid rgb(221, 222, 221);
            margin-right: 20px;
            margin-left: 5px;
            font-size: 20px;
            text-align: center;
        }

        .paixu-select {
            /*background-color: rgb(135,48,118);*/
            background-color: orange;
            color: white;
        }

        .paixu-price {
            position: relative;
        }

        .paixu-price * {
            float: left;
        }

        .paixu-price input {
            line-height: 25px;
            width: 85px;
            border: 1px solid rgb(204, 204, 204);
        }

        .paixu-price .sure {
            display: none;
            width: 170px;
            height: 25px;
            padding: 5px;
            text-align: center;
            position: absolute;
            top: 29px;
            left: 0;
            background-color: #EFECEB;
            z-index: 999;
            /*overflow: hidden;*/
        }

        .sure div {
            /*display: inline-block;*/
            float: left;
            width: 33px;
            height: 22px;
            line-height: 22px;
            border: 1px solid #ccc;
            margin: 0px 13px;
            padding: 0px 12px;
            cursor: pointer;
            background-color: white;
            color: black;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        .crumb {
            padding-bottom: 8px;
            margin-bottom: 0;
            height: 38px;
            line-height: 38px;
            background: #fff;
            z-index: 100;
            border-top: 1px solid #e5e5e5;
            margin-top: 10px;
            /*font-size: 14px;*/
        }

        .crumb * {
            font-size: 14px;
        }

        .pingpai {
            border: 2px solid #D1CCC7;
            overflow: hidden;
        }

        .attr {
            background-color: #F7F5F5;
            float: left;
            line-height: 40px;
            width: 10%;
            padding-left: 2%;
        }

        .style-right {
            float: left;
            width: 85%;
            margin-left: 3%;
        }

        .style-right a {
            margin: 0px 10px;
            line-height: 40px;
            display: inline-block;
        }

        .fenlei {
            overflow: hidden;
            border: 1px solid #D1CCC7;
            /*border-color: #E6E2E1 #E6E2E1 #D1CCC7;*/
            /*border-style: solid solid dotted;*/
            /*border-width: 0 1px 1px;*/
            border-top: 0px;
        }
        #allGoodsPage{
            margin-left: 339px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/plugins/header.jsp">
    <jsp:param name="basePath" value="<%=basePath%>"/>
    <jsp:param name="itemList" value="${itemList}"/>
    <jsp:param name="subItemMap" value="${subItemMap}"/>
</jsp:include>


<div class="row">
    <div class="crumb">
        <c:if test="${item!=null}">
            <a href="<%=basePath%>Goods/allGoods">全部</a>
            <span>></span>
            <a href="<%=basePath%>Goods/allGoods?iid=${item.id}">${item.title}</a>
        </c:if>

        <c:if test="${subItem!=null}">
            <span>></span>
            <a href="<%=basePath%>Goods/allGoods?subId=${subItem.id}">${subItem.title}</a>
        </c:if>
    </div>
</div>


<div class="home-main page-main">
    <div class="container">
        <!--<div class="box-ht">-->
        <!--<h2 class="tittle">团购</h2>-->
        <!--<div class="more">-->
        <!--<a href="#"><span>查看全部 </span><i class="icon">></i></a>-->
        <!--</div>-->
        <!--</div>-->
        <div class="main" id="main1">
            <c:forEach var="goods" varStatus="status" begin="0" end="4" items="${goodsList}">
                <a href="<%=basePath%>/Goods/goodsDetail?goodsId=${goods.id}">
                    <div class="part ${status.index==0?"row-first":""}">
                        <img src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}" alt=""/>
                        <div class="black">${goods.goodsName}</div>
                        <div class="gray">&nbsp</div>
                        <div class="red">${goods.price}元</div>
                    </div>
                </a>
            </c:forEach>
        </div>
        <div class="main" id="main2">
            <c:forEach var="goods" varStatus="status" begin="5" end="9" items="${goodsList}">
                <a href="<%=basePath%>/Goods/goodsDetail?goodsId=${goods.id}">
                    <div class="part ${status.index==5?"row-first":""}">
                        <img src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}" alt=""/>
                        <div class="black">${goods.goodsName}</div>
                        <div class="gray">&nbsp</div>
                        <div class="red">${goods.price}元</div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>

</div>


<div id="allGoodsPage">

</div>
<script>
    layui.use('laypage', function(){
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'allGoodsPage' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: ${count} //数据总数，从服务端得到
            ,limit:10
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if(!first){
                    $("#main1").empty();
                    $("#main2").empty();
                    var str="row-first";
                    var empty="";
                    var iid="${iid}";
                    var subId="${subId}";
                    var goodsName="${goodsName}"
                    $.post("<%=basePath%>/Goods/findGoodsForPage",{pageNum:obj.curr,iid:iid,subId:subId,goodsName:goodsName},function (data) {
                       for(var x=0;x<data.length;x++){
                            console.log(data[x]);
                           if(x<5) {
                               if(x==0) {
                                   $("#main1").append("<a href=\"<%=basePath%>/Goods/goodsDetail?goodsId="+data[x].id+"\">\n" +
                                       "<div class='part row-first'>\n"+
                                       "                <img src='<%=basePath%>/resources/upload/goodsPic/" + data[x].pic + "' alt='' />\n" +
                                       "                <div class='black'>" + data[x].goodsName + "</div>\n" +
                                       "                <div class='gray'>&nbsp </div>\n" +
                                       "                <div class='red'>" + data[x].price + "元</div>\n" +
                                       "            </div>\n" +
                                       "</a>");
                               }else{
                                   $("#main1").append("<a href=\"<%=basePath%>/Goods/goodsDetail?goodsId="+data[x].id+"\">\n" +
                                       "<div class='part'>\n"+
                                       "                <img src='<%=basePath%>/resources/upload/goodsPic/" + data[x].pic + "' alt='' />\n" +
                                       "                <div class='black'>" + data[x].goodsName + "</div>\n" +
                                       "                <div class='gray'>&nbsp </div>\n" +
                                       "                <div class='red'>" + data[x].price + "元</div>\n" +
                                       "            </div>\n" +
                                       "</a>");
                               }
                           }else{
                               if(x==5){
                                   $("#main2").append("<a href=\"<%=basePath%>/Goods/goodsDetail?goodsId="+data[x].id+"\">\n" +
                                       "<div class='part row-first'>\n"+
                                       "                <img src='<%=basePath%>/resources/upload/goodsPic/" + data[x].pic + "' alt='' />\n" +
                                       "                <div class='black'>" + data[x].goodsName + "</div>\n" +
                                       "                <div class='gray'>&nbsp </div>\n" +
                                       "                <div class='red'>" + data[x].price + "元</div>\n" +
                                       "            </div>\n" +
                                       "</a>");
                               }else{
                                   $("#main2").append("<a href=\"<%=basePath%>/Goods/goodsDetail?goodsId="+data[x].id+"\">\n" +
                                       "<div class='part'>\n"+
                                       "                <img src='<%=basePath%>/resources/upload/goodsPic/" + data[x].pic + "' alt='' />\n" +
                                       "                <div class='black'>" + data[x].goodsName + "</div>\n" +
                                       "                <div class='gray'>&nbsp </div>\n" +
                                       "                <div class='red'>" + data[x].price + "元</div>\n" +
                                       "            </div>\n" +
                                       "</a>");
                               }

                           }
                       }
                    },"json");
                }
            }
        });
    });
</script>
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
            <li class="tel"><p class="working"><img src="images/icon14.png"> 工作日 9:00~18:00</p>
                <p class="tels">400-6653-666</p></li>
            <li><h4>新手指南</h4><a href="zhuce.html">注册新用户</a><a href="#">家具订购流程</a><a href="#">支付方式</a><a href="#">了解产品工艺</a></li>
            <li><h4>购物保障</h4><a href="#">正品保证</a><a href="#">注册协议</a><a href="#">隐私保护</a><a href="#">免责申明</a><a href="#">商品保障</a></li>
            <li><h4>配送安装</h4><a href="#">配送安装</a><a href="#">收货指南</a><a href="#">包装说明</a></li>
            <li><h4>售后服务</h4><a href="#">30天退换货说明</a><a href="#">退款说明</a><a href="#">维修补件说明</a><a href="#">家具保养说明</a><a href="#">假一赔三</a></li>
            <li class="code"><h4>微信公众号</h4>
                <p><img src="images/gaoliuxuerweima.jpg"></p></li>
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