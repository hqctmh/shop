<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String loginUrl=basePath+"login";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <jsp:include page="/WEB-INF/pages/plugins/include_head.jsp"/>
    <style>
        body {
            /*background-image: url("/resources/css/images/pic_1.jpg");*/
        }

        #login-main {
            width: 336px;
            padding: 20px;
            background-color: #fff;
            position: absolute;
            right: 100px;
            top: 100px;
        }
    </style>
</head>
<body class="zh_CN win_banner_mistore">
<!--表单输入登录-->
<div class="mainbox" id="login-main">
    <!-- header s -->
    <!-- header e -->
    <!-- tab s -->
    <div class="nav_tabs_panel">
        <div id="nav-tabs" class="nav_tabs">
            <a class="navtab-link now" href="javascript:void(0);" data-tab="pwd">帐号登录</a>
            <!--<span class="line"></span>-->
            <!--<a class="navtab-link" href="javascript:void(0);" data-tab="qr">扫码登录</a>-->
        </div>
    </div>
    <!-- tab e -->
    <!-- tab con s -->
    <div class="tabs-con tabs_con now" data-con="pwd">
        <div>
            <div class="login_area">
                <form action="<%=loginUrl%>" method="post" id="login-main-form">
                    <div class="loginbox c_b">
                        <!-- 输入框 -->
                        <div class="lgn_inputbg c_b">
                            <!--验证用户名-->
                            <div class="single_imgarea" id="account-info">
                                <div class="na-img-area" id="account-avator" style="display:none">
                                    <div class="na-img-bg-area" id="account-avator-con"></div>
                                </div>
                                <p class="us_name" id="account-displayname"></p>
                                <p class="us_id"></p>
                            </div>
                            <label id="region-code" class="labelbox login_user c_b" for="">
                                <input class="item_account" autocomplete="off" type="text" name="phone" id="username"
                                       placeholder="手机号码/帐号">
                            </label>
                            <div class="country-container" id="countrycode_container" style="display: none;">
                                <div class="country_container_con" id="countrycode_container_con"></div>
                            </div>
                            <label class="labelbox pwd_panel c_b">
                                <input type="password" placeholder="密码" autocomplete="off" name="password" id="pwd">
                            </label>


                        </div>

                        <div class="lgncode c_b" id="captcha">
                        </div>
                        <!-- 错误信息 -->
                        <div class="err_tip" id="error-outcon">
                            <div class="dis_box"><em class="icon_error"></em><span class="error-con"></span></div>
                        </div>

                        <div class="btns_bg">
                            <input class="btnadpt btn_orange" id="login-button" type="submit" value="立即登录">
                            <span id="custom_display_8" class="sns-default-container sns_default_container" style="display: none;">
                          </span>
                        </div>

                    </div>
                </form>
            </div>
        </div>
        <!-- 其他登录方式 e -->
        <div class="n_links_area" id="custom_display_64">
            <a class="outer-link" href="${basePath}regist.jsp">注册帐号</a>
        </div>
    </div>

</div>
</body>
</html>