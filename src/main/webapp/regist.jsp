<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String registUrl = basePath + "register";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <jsp:include page="/WEB-INF/pages/plugins/include_head.jsp"/>
    <style>
        .facebook_area {
            display: none !important;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="wrap">
        <div class="layout">
            <div class="n-frame device-frame reg_frame" id="main_container">
                <div class="external_logo_area">
                    <!--<a class="milogo" href="javascript:void(0)"></a>-->
                </div>
                <div class="title-item t_c">
                    <h4 class="title_big30">注册帐号</h4>
                </div>
                <div>
                    <form action="<%=registUrl%>" method="post" enctype="multipart/form-data">
                        <div class="regbox">
                            <div class="phone_step1">
                                <div class="inputbg">
                                    <label class="labelbox" for="">
                                        <input type="tel" name="phone" data-type="PH" placeholder="请输入手机号码">
                                    </label>
                                </div>
                                <div class="err_tip">
                                    <div class="dis_box"><em class="icon_error"></em><span></span></div>
                                </div>
                                <div class="inputbg">
                                    <label class="labelbox" for="">
                                        <input type="tel" name="name" data-type="PH" placeholder="请输入用户名">
                                    </label>
                                </div>
                                <div class="err_tip">
                                    <div class="dis_box"><em class="icon_error"></em><span></span></div>
                                </div>
                                <div class="inputbg">
                                    <label class="labelbox" for="">
                                        <input type="password" name="password" data-type="PH" placeholder="请输入密码">
                                    </label>
                                </div>
                                <div class="inputbg">
                                    <label class="labelbox" for="">
                                        <input type="file" name="photo"  placeholder="请上传头像">
                                    </label>
                                </div>
                                <div class="err_tip">
                                    <div class="dis_box"><em class="icon_error"></em><span></span></div>
                                </div>
                                <div class="inputbg inputcode">
                                    <label class="labelbox" for="">
                                        <input class="code" type="text" name="code" autocomplete="off"
                                               placeholder="图片验证码">
                                    </label>
                                    <img alt="图片验证码" src="/ImageCode" title="看不清换一张" id="imageCode"
                                         class="icode_image code-image">
                                </div>
                                <div class="err_tip">
                                    <div class="dis_box"><em class="icon_error"></em><span></span></div>
                                </div>
                                <div class="err_tip send-left-times">
                                </div>
                                <div class="fixed_bot mar_phone_dis1">
                                    <input class="btn332 btn_reg_1 submit-step" data-to="phone-step2" type="submit"
                                           value="立即注册">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $("#imageCode").on("click", function () {
        $("#imageCode").attr("src", "ImageCode?p=" + Math.random());
    });
</script>