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
    <meta charset="utf-8">
    <title>特卖详情</title>
    <base href="<%=basePath%>">
    <link href="resources/css/base.css" type="text/css" rel="stylesheet">
    <link href="resources/css/mycss.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <link href="resources/css/goods_detail_css.css" type="text/css" rel="stylesheet">
    <script src="resources/js/goods_detail_js.js"></script>
    <link href="resources/css/index.css" type="text/css" rel="stylesheet">
    <link href="resources/css/new_index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/slider.js"></script>
    <script src="resources/js/hmJs.js"></script>
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>



    <script>
        function Trim(str)
        {
            return str.replace(/^\s+|\s+$/g,"");
        }
        //加减
        $(function(){
            $(".add").click(function(){
                var t=$(this).parent().find('input[class*=text_box]');
                var val=parseInt(t.val())+1;
                var count=parseInt(${goods.count});
                if(val>count){
                    val=count;
                }
                t.val(val);
                setTotal();
            });
            $(".min").click(function(){
                var t=$(this).parent().find('input[class*=text_box]');
                t.val(parseInt(t.val())-1)
                if(parseInt(t.val())<0){
                    t.val(0);
                }
                setTotal();
            });
            function setTotal(){
                var s=0;
                $("#tab span").each(function(){
                    s+=parseInt($(this).find('input[class*=text_box]').val())*parseFloat($('.xddz_01').find('span[class*=price]').text());
                });
                $("#total").html(s.toFixed(2));
            }
            setTotal();

            $("#joinCar").click(function(){
                var goodsId="${goods.id}";
                var storeId="${goods.storeId}";
                var account=$("#account").val();
                $.post("<%=basePath%>pages/front/member/addShopCar",{goodsId:goodsId,storeId:storeId,account:account},function(data){
                    if(Trim(data)=="true"){
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.msg('添加成功', {icon: 1,offset: '300px',time:1500});

                        });
                    }else{
                        alert("您还未登录，请先登录！");
                        window.location="<%=basePath%>login.jsp";

                    }
                },"text")
            });

        });
    </script>
    <style>
        .shangjia_left{
            width: 210px;
            min-height: 500px;
            margin-right: 10px;
            float: left;
            border:1px solid #ccc;
        }
        .shangjia_left ul li{
            margin-left: 4px;
        }
        .shangpin_right{
            width: 1225px;
            overflow: hidden;
            /*border:1px solid #ccc;*/
            float: left;
        }
        .shangjia_tittle{
            text-align: center;
            /*font-weight: bold;*/
            font-size: 15px;
            height: 48px;
            line-height: 48px;
            border-bottom: 1px solid #ccc;
            background-color: rgb(250,250,250);
            margin-bottom: 10px;
        }
        .tab{
            overflow: hidden;
            border: 1px solid #ccc;

            /*border-bottom: 0px solid black;*/
        }
        #tab1{
            float: left;

        }
        #tab2{
            float: left;
        }
        .tab_tittle{
            /*background: orange;*/
            color: black;
            font-size: 16px;
            padding: 0 30px;
            line-height: 40px;
            font-weight: 500;
            cursor: pointer;
        }
        .tab_tittle:hover{
            color: orange;
        }
        .con{
            overflow: hidden;
        }
        #con2 .mt {
            position: relative;
            padding: 10px;
            _zoom: 1;
            background-color: #f7f7f7;
            border: 1px solid #eee;
            margin-top: 10px;
        }
        #con2 .mt  h3 {
            font: 700 14px "microsoft yahei";
        }

        #con2 .comment-info {
            overflow: hidden;
            zoom: 1;
            padding: 25px 0;
        }

        #con2 div.small {
            border: 1px solid #f5f5f5;
            background-color: #fafafa;
            padding-left: 10px;
            height: 24px;
            margin-top: 10px;
        }
        #con2 .tab-main ul {
            margin-top: -1px;
        }
        #con2 div.small li.current,#con2 div.small li.current a {
            background: #fafafa;
            color: #e4393c;
        }
        li.current a {
            background: #fafafa;
            color: #e4393c;
        }
        #con2 div.small li {
            padding: 5px 10px;
        }
        #con2 .tab-main li {
            position: relative;
            display: inline-block;
            font-family: "microsoft yahei";
            cursor: pointer;
        }
        .comment-item {
            zoom: 1;
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .comment-item .user-column {
            width: 140px;
            float: left;
        }
        .comment-item .user-info {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
        .comment-item .user-info img {
            border-radius: 50%;
            margin-right: 5px;
        }
        .comment-item .user-level {
            padding-top: 3px;
        }
        .comment-item .user-level {
            padding-top: 3px;
        }
        .comment-item .comment-column {
            margin-left: 150px;
        }
        .comment-item .comment-star {
            width: 78px;
            height: 14px;
            background:url(<%=basePath%>/resources/images/star.png) no-repeat;
        }
        .comment-item .star5 {
            background-position: 0 0;
        }
        .comment-item .comment-con {
            font-size: 14px;
            padding: 10px 0;
            line-height: 180%;
            color: #333;
        }
        .comment-item .pic-list {
            padding-bottom: 15px;
        }
        .comment-item .pic-list img {
            border: 1px solid #e2e2e2;
            padding: 1px;
            margin-right: 6px;
        }
        #test{
            margin-top: -50px;
        }
        #price{
            margin-top: 45px;
        }
        #num{
            margin-top: 45px;
        }
        #buy{
            margin-top: 45px;
        }
        .user-column{
            margin-left: -365px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/plugins/header.jsp">
    <jsp:param name="basePath" value="<%=basePath%>"/>
    <jsp:param name="itemList" value="${itemList}"/>
    <jsp:param name="subItemMap" value="${subItemMap}"/>
</jsp:include>

</div>
<!--topad start-->
</div>

<!--index content start-->
<div class="row detail_con">
    <div class="lf lfpic">
        <div class="preview">
            <div id="vertical" class="bigImg">
                <img src="<%=basePath%>/resources/upload/goodsPic/${goods.pic}" width="auto" height="auto" alt="" id="midimg" />
                <div style="display:none;" id="winSelector"></div>
            </div><!--bigImg end-->

            <div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>
        </div>
        <!--preview end-->
    </div>
    <div class="lf rfword">
        <h2 class="title">${goods.goodsName}</h2>

        <div class="price" id="price">
            <p class="price-num"><i>￥</i>${goods.price}</p>
        </div>

        <div class="buy_num" id="num">
            <span class="color02 lf">数量： </span>
            <span  class="lf pro_num">
            <input class="min" type="button" value="-">
            <input class="text_box" type="text" id="account" value="1">
            <input class="add" type="button" value="+">
            </span>
            <span class="color02 lf">库存${goods.count}件</span>
        </div>

        <div class="clr tg_explain"id="buy">
            <button style="cursor:pointer" class="btnClass joinCar" id="joinCar"><img src="<%=basePath%>/resources/images/icon_08.png" > 加入购物车</button>
        </div>
    </div>
    <div class="clr"></div>
</div>
<div class="row detail_con" id="test">
    <div class="shangpin_right">
        <div class="tab">
            <div id="tab2" class="h2-title">累计评价 213</div>
        </div>
        <div class="con">

            <div id="con2" >
                <div class="mt">
                    <h3>商品评价</h3>
                </div>

                <div class="mc">

                    <div class="J-comments-list comments-list ETab" >
                        <div class="tab-main small">
                            <ul>
                                <li  class="current"><a href="javascript:;">全部评论<em>(154514)</em></a></li>
                                <li ><a href="javascript:;">晒图<em>(7)</em></a></li>
                                <li ><a href="javascript:;">好评<em>(100)</em></a></li>
                                <li ><a href="javascript:;">中评<em>(1)</em></a></li>
                                <li ><a href="javascript:;">差评<em>(0)</em></a></li>

                            </ul>
                            <!--<div class="extra">
                                <div class="sort-select J-sort-select hide">
                                    <div class="current"><span class="J-current-sortType">推荐排序</span><i></i></div>
                                    <div class="others">
                                        <div class="curr"><span class="J-current-sortType">推荐排序</span><i></i></div>
                                        <ul>
                                            <li class="J-sortType-item" data-sorttype="5" clstag="shangpin|keycount|product|morenpaixu">推荐排序</li>
                                            <li class="J-sortType-item" data-sorttype="6" clstag="shangpin|keycount|product|shijianpaixu">时间排序</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>-->
                        </div>
                        <div class="tab-con">
                            <div id="comment-0" data-tab="item">
                                <div class="comment-item" >
                                    <div class="user-column">
                                        <div class="user-info">
                                            <img src="<%=basePath%>/resources/images/code2.png" width="25" height="25" alt="JY-林佳玮" class="avatar" /> JY-林佳玮
                                        </div>

                                    </div>
                                    <div class="comment-column J-comment-column">
                                        <div class="comment-star star5"></div>
                                        <p class="comment-con">感谢东哥的物流服务在元宵节当天还依然送货上门，给你赞到底！大牌子的产品始终还是好，纸巾用起来手感不会太粗糙，也不会有异味，“清风”对你信得过！</p>
                                        <div class="pic-list J-pic-list">
                                            <a class="J-thumb-img" href="" data-ind="0"><img src="<%=basePath%>/resources/images/mangguo.jpg" width="48" height="48" alt="JY-林佳玮的晒单图片" /></a>
                                            <a class="J-thumb-img" href="" data-ind="0"><img src="<%=basePath%>/resources/images/mangguo.jpg" width="48" height="48" alt="JY-林佳玮的晒单图片" /></a>
                                            <a class="J-thumb-img" href="" data-ind="0"><img src="<%=basePath%>/resources/images/mangguo.jpg" width="48" height="48" alt="JY-林佳玮的晒单图片" /></a>
                                        </div>
                                        <div class="J-pic-view-wrap clearfix" data-rotation="0">
                                        </div>
                                        <div class="comment-message">
                                            <div class="order-info">
                                                <span>【爆款】200抽*20包</span>
                                                <span></span>
                                                <span>2017-02-14 00:11</span>
                                            </div>
                                            <div class="comment-op">
                                                <a class="J-report" data-login="1" href="https://item.jd.com/2015246.html#none"  >举报</a>
                                                <a class="J-nice" data-login="1" href="https://item.jd.com/2015246.html#none"  title="55"><i class="sprite-praise"></i>55</a>
                                                <a href="" target="_blank"><i class="sprite-comment"></i>18</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="comment-1" data-tab="item" class="hide"><div class="iloading">正在加载中，请稍候...</div></div>
                            <div id="comment-2" data-tab="item" class="hide"><div class="iloading">正在加载中，请稍候...</div></div>
                            <div id="comment-3" data-tab="item" class="hide"><div class="iloading">正在加载中，请稍候...</div></div>
                            <div id="comment-4" data-tab="item" class="hide"><div class="iloading">正在加载中，请稍候...</div></div>
                        </div>
                    </div>
                </div>




            </div>
        </div>


    </div>

</div>
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
            <li><h4>新手指南</h4><a href="zhuce.html">注册新用户</a><a href="#">家具订购流程</a><a href="#">支付方式</a><a href="#">了解产品工艺</a></li>
            <li><h4>购物保障</h4><a href="#">正品保证</a><a href="#">注册协议</a><a href="#">隐私保护</a><a href="#">免责申明</a><a href="#">商品保障</a></li>
            <li><h4>配送安装</h4><a href="#">配送安装</a><a href="#">收货指南</a><a href="#">包装说明</a></li>
            <li><h4>售后服务</h4><a href="#">30天退换货说明</a><a href="#">退款说明</a><a href="#">维修补件说明</a><a href="#">家具保养说明</a><a href="#">假一赔三</a></li>
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
</body>
</html>