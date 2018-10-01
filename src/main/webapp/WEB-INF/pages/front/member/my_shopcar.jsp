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
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <link href="resources/css/base.css" type="text/css" rel="stylesheet">
    <link href="resources/css/gouwuche.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <style>
        .operate {
            display: inline-block;
            text-align: center;
            width: 20px;
            border: 1px solid #9DC1E4;
            cursor: pointer;
            line-height: 24px
        }

        .countInput {
            width: 42px;
        }

        #dingdan {
            width: 546px;

        }

        .jiesuan {
            width: 1226px;
            height: 50px;
            line-height: 50px;
            position: fixed;
            bottom: 0;
            background-color: rgb(229, 229, 229);

        }

        .jiesuan .jiesuan_button {
            float: left;
            margin-top: 20px;
            margin-right: 5px;
            margin-left: 20px;
        }

        /*.jiesuan ul{*/
        /*width: 1226px;*/
        /*height: 50px;*/
        /*}*/
        .jiesuan > ul li {
            margin-right: 30px;
            font-size: 14px;
        }

        .jiesuan > ul li:hover {
            color: rgb(245, 102, 0);
            text-decoration: underline;
        }

        .jiesuan_right {
            float: right;
        }

        .jiesuan_right > div {
            float: left;
            font-size: 14px;
            margin-right: 10px;
        }

        .jiesuan_right .footer_button {
            float: left;
            background: #B0B0B0;
            color: #fff;
            border-left: 1px solid #e7e7e7;
            width: 119px;
            cursor: not-allowed;
            text-decoration: none;
            text-align: center;
            margin-right: 0px;
            font-size: 22px;
            /*font-weight: bold;*/
        }

        .jiesuan_right .big_orange {
            font-size: 22px;
            font-weight: bold;
            color: rgb(245, 102, 0);
        }

        .jiesuan_right .orange_white {
            color: white;
            background-color: rgb(245, 102, 0);
            cursor: pointer;
        }

        .dingdan-caozuo {
            position: relative;
            left: 1062px;
            top: -113px;
        }
    </style>
    <script>

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
        function Trim(str)
        {
            return str.replace(/^\s+|\s+$/g,"");
        }

        $(function () {
            $(".opadd").click(function () {
                var input = $(this).prev();
                var text = parseInt(input.val(), 10) + 1;
                input.val(text);
                var allPriceDiv = $(this).parent().next();
                var price = parseFloat($(this).parent().prev().text());
                var allprice = price * text;
                allPriceDiv.text(toDecimal2(allprice));
                $("input[id*=goodsCheck-]").trigger("change");
                var id=this.id.split("-")[1];
                $.post("<%=basePath%>pages/front/member/updateShopCar",{id:id,account:text},function(data){},"text");
            });
            $(".op-").click(function () {
                var input = $(this).next();
                var text = parseInt(input.val(), 10) - 1;

                if (text <= 1) {
                    text = 1;
                }
                input.val(text);
                var allPriceDiv = $(this).parent().next();
                var price = parseFloat($(this).parent().prev().text());
                var allprice = price * text;
                allPriceDiv.text(toDecimal2(allprice));
                $("input[id*=goodsCheck-]").trigger("change");
                var id=this.id.split("-")[1];
                console.log(text)
                $.post("<%=basePath%>pages/front/member/updateShopCar",{id:id,account:text},function(data){
                    console.log(data);
                },"text");
            });
            var lastPrice=0.00;
            $("input[id*=goodsCheck-]").each(function(){
                $(this).on("change",function(){
                    lastPrice=0.00;
                    $("input[id*=goodsCheck-]").each(function(){
                        var gid=this.id.split("-")[1];
                        if(this.checked==true){
                            lastPrice+=parseFloat($("#allPrice-"+gid).text());
                        }
                    });
                    console.log(lastPrice);
                    $("#lastPrice").text(lastPrice);
                    if(lastPrice>0){
                        $("#jiesuan").css("cursor","pointer");
                        $("#jiesuan").css("background","#f56600");
                    }else{
                        $("#jiesuan").css("cursor","");
                        $("#jiesuan").css("background","#B0B0B0");
                    }
                });
            });
            $(".allCheck").on("change",function(){
                if (this.checked == true) {
                    $("input[type='checkbox']").prop("checked", true);
                    $("input[id*=goodsCheck-]").trigger("change");
                } else {
                    $("input[type='checkbox']").prop("checked", false);
                    $("input[id*=goodsCheck-]").trigger("change");
                }
            });

            $("input[id*=storeCheck-]").each(function(){
                var  storeId=this.id.split("-")[1];
                $(this).on("change",function(){
                    console.log(this.checked);
                    if(this.checked==true){
                        $(".goodsCheck-"+storeId).prop("checked",true);
                        $(".goodsCheck-"+storeId).trigger("change");
                    }else{
                        $(".goodsCheck-"+storeId).prop("checked",false);
                        $(".goodsCheck-"+storeId).trigger("change");
                    }
                });
            });
            $("div[id*=delGoods-]").each(function(){
                var goodsId=this.id.split("-")[1];
                var storeId=$(this).attr("class").split("-")[1];
                var shopCarId=$(this).next().text();
                $(this).click(function(){

                    $.post("<%=basePath%>pages/front/member/delShopCar",{shopCarId:shopCarId},function(data){
                        if(Trim(data)=="true"){
                            layui.use('layer', function(){
                                var layer = layui.layer;
                                layer.alert("删除成功！")
                            });
                            $("#goodRow-"+goodsId).remove();
                            var length=$(".goodsRow-"+storeId).length;
                            if(length==0){
                                $("#storeDiv-"+storeId).remove();
                            }
                        }else{
                            layui.use('layer', function(){
                                var layer = layui.layer;
                                layer.alert("删除失败！")
                            });
                        }

                    },"text");

                });
            })
            $("#jiesuan").click(function(){
                var price=parseFloat($("#lastPrice").text());
                if(price>0){
                    var str="";
                    $("input[id*=goodsCheck]").each(function(){
                        if(this.checked==true){
                            var goodsId=this.id.split("-")[1];
                            var account=$("#account-"+goodsId).val();
                            str+=goodsId+":"+account+",";
                        }
                    });
                    $("#hidden").val(str);
                    $("form").submit();
                }
            });

        })
    </script>
</head>

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
        </ul>
        <ul class="rf">

            <a href="${basePath}pages/front/member/myIndex">我的信息 <i class="iconfont"></i></a><span class="line">|</span>
            <a href="${basePath}pages/front/member/myShopCar">购物车 <i class="iconcar"></i></a>
            <sapn class="line">|</sapn>
            <a>全国热线:<span class="color03">400-6653-666</span></a>
        </ul>
        <div class="clr"></div>
    </div>
</div>


<div class="row">
    <ul class="nav-dingdan">
        <li><a href="${basePath}Goods/allGoods">全部商品</a></li>
    </ul>
</div>
<div class="row">

    <ul class="nav-type">
        <input class="top-button allCheck" type="checkbox"><span class="button-text">全选</span>
        <li class="goodsnew">商品信息</li>
        <li class="price">单价</li>
        <li>数量</li>
        <li>金额</li>
        <li>操作</li>
    </ul>

</div>
<c:forEach items="${map}" var="list">
    <div id="storeDiv-${list.value[0].storeId}">
        <div class="row">
            <input class="top-button" id="storeCheck-${list.value[0].storeId}" type="checkbox">
            <span class="dianpu">店铺：${list.value[0].storeName}</span>
        </div>
        <c:forEach items="${list.value}" var="model">
            <div class="row goodsRow-${model.storeId}" id="goodRow-${model.goodsId}" >
                <div class="dingdan-detail">
                    <div class="dingdan-show">
                        <div class="dingdan-pictrue" id="dingdan">
                            <input class="top-button goodsCheck-${model.storeId}" id="goodsCheck-${model.goodsId}" type="checkbox">
                            <img src="<%=basePath%>/resources/upload/goodsPic/${model.pic}" alt="">
                            <span>${model.goodsName}</span>
                        </div>
                        <div class="dingdan-munber">${model.price}</div>
                        <div class="dingdan-tuihuo">
                            <button class="operate op-" id="jian-${model.id}">-</button>
                            <input type="text" disabled="disabled" class="countInput" id="account-${model.goodsId}" value="${model.account}">
                            <button class="operate opadd" id="jia-${model.id}">+</button>
                        </div>
                        <div class="dingdan-money" id="allPrice-${model.goodsId}">${model.allPrice}</div>
                        <div class="dingdan-caozuo">
                            <div class="delGoods-${model.storeId}" id="delGoods-${model.goodsId}" style="curosr:pointer">删除</div>
                            <hidden hidden>${model.id}</hidden>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</c:forEach>


<div class="row">
    <div class="jiesuan">
        <ul>
            <input class="jiesuan_button allCheck" type="checkbox">
            <li>全选</li>
        </ul>
        <form action="<%=createIndentUrl%>" method="post">
        <div class="jiesuan_right">

            <div>
                <span>合计：</span>
                <span class="big_orange" id="lastPrice">0.00</span>
            </div>
                <input type="hidden" name="str" id="hidden" >
                <div class="footer_button" id="jiesuan" >结算</div>
        </div>
        </form>
    </div>
</div>

</body>
</html>
