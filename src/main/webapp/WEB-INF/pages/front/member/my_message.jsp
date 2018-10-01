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
        function bind(){
            $("a[id*=updatePrice-]").each(function(){
                var id=this.id.split("-")[1];
                $(this).click(function(){
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.prompt({
                            formType: 2,
                            title: '请输入价格',
                            offset: ['250px','783px'],
                            area: ['300px', '40px']
                        }, function(value, index, elem){
                            $.post("<%=basePath%>pages/front/member/updateGoods",{id:id,price:value},function(data){
                                if(Trim(data)=="true"){
                                    $("#price-"+id).text(toDecimal2(value));
                                    layer.alert("修改成功！");
                                }else{
                                    layer.alert("修改失败！");
                                }
                            },"text");
                            layer.close(index);
                        });
                    });
                })
            });
            $("a[id*=updateCount-]").each(function(){
                var id=this.id.split("-")[1];
                $(this).click(function(){
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.prompt({
                            formType: 2,
                            title: '请输入进货数量',
                            offset: ['250px','783px'],
                            area: ['300px', '40px']
                        }, function(value, index, elem){
                            var newCount=parseInt(value);
                            var oldCount=parseInt($("#count-"+id).text());
                            var lastCount=newCount+oldCount;
                            $.post("<%=basePath%>pages/front/member/updateGoods",{id:id,count:lastCount},function(data){
                                if(Trim(data)=="true"){
                                    $("#count-"+id).text(lastCount);
                                    layer.alert("修改成功！");
                                }else{
                                    layer.alert("修改失败！");
                                }
                            },"text");
                            layer.close(index);
                        });
                    });
                })
            });
            $("a[id*=delGoods-]").each(function(){
                var id=this.id.split("-")[1];
                $(this).click(function(){
                    $.post("<%=basePath%>pages/front/member/updateGoods",{id:id,delFlag:"1"},function(data){
                        if(Trim(data)=="true"){
                            alert("删除成功！");
                            window.location="<%=basePath%>pages/front/member/myGoods";
                        }else{
                            alert("删除失败！");
                        }
                    },"text")
                })
            });
        }
        $(function(){
            bind();
        })
    </script>
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

        <table class="table table-hover" id="goodsTable">
            <tr>
                <th>#</th>
                <th>商品名</th>
                <th>留言用户</th>
                <th>内容</th>
                <th>操作</th>
            </tr>
            <%int i=1;%>
            <c:forEach items="${modelList}" var="model">
            <tr>
                <td><%=i++%></td>
                <td>${model.goodsName}</td>
                <td>${model.userName}</td>
                <td>${model.content}</td>
                <td class="operation">
                    <a id="leaveMessage-${model.id}">回复</a>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div id="myGoodsPage">

        </div>
        <script>
            layui.use('laypage', function(){
                var laypage = layui.laypage;

                //执行一个laypage实例
                laypage.render({
                    elem: 'myGoodsPage' //注意，这里的 test1 是 ID，不用加 # 号
                    ,count: ${count} //数据总数，从服务端得到
                    ,limit:14
                    ,jump: function(obj, first){
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数

                        //首次不执行
                        if(!first){
                            $("#goodsTable").empty();
                            $.post("<%=basePath%>/pages/front/member/myMessageForPage",{pageNum:obj.curr},function (data) {
                                $("#goodsTable").append("<tr>\n" +
                                    "                <th>#</th>\n" +
                                    "                <th>商品名</th>\n" +
                                    "                <th>留言用户</th>\n" +
                                    "                <th>内容</th>\n" +
                                    "                <th>操作</th>\n" +
                                    "            </tr>");
                                for(var x=0;x<data.length;x++){
                                    var goodsName=data[x].goodsName;
                                    var userName=data[x].userName;
                                    var content=data[x].content;
                                    var id=data[x].id;
                                    var i=x+1;
                                    $("#goodsTable").append("<tr>\n" +
                                        "                <td >"+ i +"</td>\n" +
                                        "                <td>"+goodsName+"</td>\n" +
                                        "                <td id='price-"+id+"'>"+userName+"</td>\n" +
                                        "                <td id='count-"+id+"'>"+content+"</td>\n" +
                                        "                <td class=\"operation\">\n" +
                                        "                    <a id='updatePrice-"+id+"'>留言</a>\n" +
                                        "                </td>\n" +
                                        "            </tr>")
                                }
                                bind();
                            },"json");

                        }
                    }
                });
            });
        </script>
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
