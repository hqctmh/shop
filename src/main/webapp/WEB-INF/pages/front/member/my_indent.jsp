<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String submitUrl=basePath+"pages/front/member/createIndent";
    String payUrl=basePath+"pages/front/member/pay";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>团购</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <link href="resources/css/base.css" type="text/css" rel="stylesheet">
    <link href="resources/css/index.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/dingdan.css"/>
    <link rel="stylesheet" href="resources/css/from.css"/>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/hmJs.js"></script>
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

        function Trim(str) {
            return str.replace(/^\s+|\s+$/g, "");
        }

        function address(addressId) {
            var province = $("#province-" + addressId).text();
            var city = $("#city-" + addressId).text();
            var ad = $("#ad-" + addressId).text();
            var name = $("#name-" + addressId).text();
            var phone = $("#phone-" + addressId).text();
            $("#Lprovince").text(province);
            $("#Lcity").text(city);
            $("#Laddress").text(ad);
            $("#Lname").text(name);
            $("#Lphone").text(phone);
        }

        $(function () {
            $("span[id*=simPrice-]").each(function () {
                var goodsId = this.id.split("-")[1];
                var goodsPrice = parseFloat($("#goodsPrice-" + goodsId).text());
                var input = parseInt($("#count-" + goodsId).val());
                $(this).text(toDecimal2(goodsPrice * input));
            })
            addAll();
            address("${address[0].id}");

            function addAll() {
                var allP = 0.0;
                $("em[id*=storePrice-]").each(function () {
                    var storeId = this.id.split("-")[1];
                    var goodsList = $(".storePrice-" + storeId);
                    var storePrice = 0.0;

                    for (var i = 0; i < goodsList.length; i++) {
                        var item = goodsList[i];
                        var simPrice = parseFloat($(item).text());
                        storePrice += simPrice;
                    }
                    allP += storePrice;
                    $("#storePrice-" + storeId).text(toDecimal2(storePrice))
                })
                $("#allP").text(toDecimal2(allP));
            }

            $(".opadd").click(function () {
                var goodsId = this.id.split("-")[1];
                var input = $(this).prev();
                var text = parseInt(input.val()) + 1;
                var goodsPrice = parseFloat($("#goodsPrice-" + goodsId).text());
                var simplePrice = text * goodsPrice;
                console.log(simplePrice);
                input.val(text);
                $("#simPrice-" + goodsId).text(toDecimal2(simplePrice));
                addAll();
            })
            $(".opjian").click(function () {
                var goodsId = this.id.split("-")[1];
                var input = $(this).next();
                var text = parseInt(input.val()) - 1;
                if (text <= 1) {
                    text = 1;
                }
                input.val(text);
                var goodsPrice = parseFloat($("#goodsPrice-" + goodsId).text());
                console.log(goodsPrice)
                var simplePrice = text * goodsPrice;
                $("#simPrice-" + goodsId).text(toDecimal2(simplePrice));
                addAll();
            })
            $(".myadd").click(function () {
                $(".myadd").removeClass("addr-cur");
                $(this).addClass("addr-cur");
                var addressId = this.id.split("-")[1];
                address(addressId);
            })

            function submit(){
                var addressId;
                $("div[id*=address-]").each(function(){
                    if($(this).hasClass("addr-cur")){
                        addressId=this.id.split("-")[1];
                    }
                });
                var param=[];
                $("div[id*=storeGoods-]").each(function(){
                    var goodsId=this.id.split("-")[1];
                    var count=parseInt($("#count-"+goodsId).val());
                    param.push({goodsId:goodsId,count:count});
                })
                var json=JSON.stringify({addressId:addressId,goods:param});
                console.log(json);
                $.post("<%=submitUrl%>",{"json":json},function (data) {
                    if(Trim(data)=="true"){
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.prompt({
                                btn:'确定',
                                offset:['182px0','809px'],
                                formType: 1,
                                title: '请输入密码',
                                area: ['400px', '20px'], //自定义文本域宽高
                                cancel: function(index, layero){
                                    if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
                                        layer.close(index)
                                        window.location="<%=basePath%>"
                                    }
                                    return false;
                                }
                            }, function(value, index, elem){
                                $.post("<%=payUrl%>",{password:value},function(data){
                                    //var obj=JSON.parse(data);
                                    if(data.flag==true){
                                        alert("支付成功");
                                        window.location="<%=basePath%>";
                                    }else{
                                        alert(data.message);
                                        window.location="<%=basePath%>";
                                    }
                                },"json");
                                //alert(value); //得到value
                                layer.close(index);
                            });
                        });
                    }else{
                        alert("订单生成失败！");
                        window.location="<%=basePath%>";
                    }
                },"text");

            }
            $("#submit").click(submit);

        })

    </script>

</head>
<body>
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
    <!--topnav end-->

    <!--search start-->
    <div class="row">
        <div class=" lf logo"><img src="resources/images/gaoliuxutop.png"></div>
        <div class="lf search">

            <div class="ch_ul">
                <input class="search_txt" type="text" value="" placeholder="请输入关键词">
                <a href="goods_list.html"><input class="search_sub" type="submit" value="搜 索"></a>
                <div class="clr"></div>
            </div>
            <ul class="ch_ul ch_classify">
                <li class="chft">沙发</li>
                <li class="line">|</li>
                <li>餐桌</li>
                <li class="line">|</li>
                <li>扇形椅</li>
                <li class="line">|</li>
                <li>屏风</li>
                <li class="line">|</li>
                <div class="clr"></div>
            </ul>
            <div class="clr"></div>
        </div>
        <div class="clr"></div>
    </div>
    <!--search enf-->

</div>


<!--index content start-->
<div class="home-main page-main">
    <div class="container">
        <div class="wrap1">

            <!--收货地址 开始-->
            <div class="order-address addr-much" id="address_1">
                <h2>选择收货地址</h2>
                <div class="list">
                    <c:forEach begin="0" end="3" items="${address}" step="1" var="item">
                        <div class="addr suggest-address ${item.status=="1"?"addr-def addr-cur":""} myadd"
                             id="address-${item.id}">
                            <div class="inner">
                                <div class="addr-hd" title="${item.provinceName} ${item.cityName} (${item.name}收)">
                                    <span class="prov" id="province-${item.id}">${item.provinceName}</span>
                                    <span class="city" id="city-${item.id}">${item.cityName}</span>
                                    <span>（</span>
                                    <span class="name" id="name-${item.id}">${item.name}</span>
                                    <span> 收）</span>
                                </div>
                                <div class="addr-bd" title="${item.address}">
                                    <span class="street" id="ad-${item.id}">${item.address}</span>
                                    <span class="phone" style="display:inline;"
                                          id="phone-${item.id}">${item.phone}</span>
                                    <span class="last">&nbsp;</span>
                                </div>
                                <div class="addr-toolbar">
                                    <a title="修改地址" class="modify" data-id="7286976133">&nbsp;</a>
                                </div>

                            </div>
                            <div class="curMarker"></div>

                            <div class="defaultTip">
                                默认地址
                            </div>
                            <div class="floater"></div>
                            <div class="option"></div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!--收货地址结束 -->

            <!-- 地址描述 开始-->
            <div class="order-orderDesc" id="orderDesc_0">
                <h2 class="buy-th-title">确认订单信息</h2>
                <div class="buy-th buy-th-column-6">
                    <div class="buy-td td-0">
                        店铺宝贝
                    </div>
                    <div class="buy-td td-1">
                        单价
                    </div>
                    <div class="buy-td td-2">
                        数量
                    </div>
                    <div class="buy-td td-3">
                        小计
                    </div>
                </div>
            </div>
            <!-- 地址描述 结束-->

            <!-- 订单开始 -->
            <div id="order" class="order-order">
                <c:forEach items="${storeList}" var="store">
                    <div id="storeOrder-${store.id}">
                        <div class="order-orderInfo">
                            <span class="shop-name">店铺:&nbsp;</span>
                            <a class="shop-url" href="" target="_blank">${store.storeName}</a>

                        </div>
                        <c:forEach items="${goodsMap[store.id]}" var="goods">
                            <div class="order-item order-item-column-4 storeGoods-${store.id}" id="storeGoods-${goods.id}">
                                <div class="order-itemInfo">
                                    <div class="info-detail info-cell">
                                        <div class="info-cell">
                                            <a class="info-img" href="" target="_blank">
                                                <img class="info-img"
                                                     src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}"/></a>
                                        </div>
                                        <div class="info-cell info-msg">
                                            <a href="" class="info-title" target="_blank">${goods.goodsName}</a>
                                            <div class="info-icon-list">
                                                <div>
                                                    <a class="icon-main" target="_blank"><img
                                                            src="resources/images/789456.png"/></a>
                                                    <a class="icon-main" target="_blank"><img
                                                            src="resources/images/789456.png"/></a>
                                                    <a class="icon-main" target="_blank"><img
                                                            src="resources/images/789456.png"/></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="info-price info-cell pprice" id="goodsPrice-${goods.id}">
                                            ${goods.price}
                                    </div>
                                </div>
                                <div class="order-quantity qquantity" id="quantity_50f851b16e15a7c12511fa909f6eedf0">
                                    <div class="quantity-inner">
                                        <span class="operate opjian" id="jian-${goods.id}">-</span>
                                        <input class="amount" value="${goods.count}" id="count-${goods.id}" pattern=""/>
                                        <span class="operate opadd" id="add-${goods.id}">+</span>
                                    </div>
                                    <span></span>
                                    <span></span>
                                </div>

                                <div class="order-itemPay iitemPay">
                                    <span class="simple-price storePrice-${store.id}" id="simPrice-${goods.id}"></span>
                                </div>
                                <div class="seperator"
                                     data-reactid=".0.$confirmOrder_1.$order_45c2018d8533483efd9ec1f2673c2f85.$item_50f851b16e15a7c12511fa909f6eedf0.$5"></div>
                            </div>
                        </c:forEach>

                        <div class="orderPay">
                            <span>店铺合计</span>
                            <span>(含运费)</span>
                            <span class="price g_price"><span>￥</span><em id="storePrice-${store.id}"
                                                                          class="style-middle-bold-red J_ShopTotal"></em></span>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 订单结束-->
            <div class="order-payInfo" id="payInfo_1">
                <div class="payInfo-wrapper">
                    <div class="payInfo-shadow">
                        <div class="order-realPay" id="realPay_1">
                            <div>
                                <span class="realPay-title">实付款：</span>
                                <span class="order-price">￥</span>
                                <span class="realPay-price" id="allP"></span>
                            </div>
                        </div>
                        <div class="order-confirmAddr" id="confirmAddr_1">
                            <div class="confirmAddr-addr">
                                <span class="confirmAddr-title">寄送至：</span>
                                <span class="confirmAddr-addr-bd"><span class="prov" id="Lprovince"></span><span
                                        class="city" id="Lcity"></span>
            <span class="street" data-street="棠安路119号 棠韵大厦 2楼 216室" id="Laddress"></span></span>
                            </div>
                            <div class="confirmAddr-addr-user">
                                <span class="confirmAddr-title">收货人：</span>
                                <span class="confirmAddr-addr-bd"><span id="Lname"></span><span
                                        id="Lphone"></span></span>
                            </div>
                        </div>
                        <div></div>
                        <div class="order-confirmAddr"></div>
                    </div>
                </div>
            </div>
            <div class="order-submitOrder" id="submitOrder_1">
                <div class="wrapper">
                    <button title="提交订单" class="go-btn " id="submit">提交订单</button>
                </div>
            </div>

        </div>

    </div>

</div>

<!--index content end-->


</body>
</html>
