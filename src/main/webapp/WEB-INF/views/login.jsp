<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆</title>
</head>
<body>

<%--<form action="/calibration">   改为异步表单提交 --%>
<form id="formId">
    验证码：
    <input type="text" placeholder="验证码" name="checkCode">

    <input type="hidden" id="checkCodeKey" name="key">
    <img id="checkCodeImg" alt="checkCodeImg" src="">

    <br>
    <button id="button" type="button">
        &nbsp;&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;&nbsp;
    </button>
</form>

<script type="text/javascript" src="/js/jquery-2.2.3.min.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        // 页面加载时发起请求
        $.ajax({
            url: "/getCaptcha",
            method: "GET",
            success: function (response) {
                if (response && response.img && response.key) {
                    $('#checkCodeImg').attr("src", response.img);
                    $('#checkCodeKey').val(response.key);
                }
            },
            error: function () {
                console.error('验证码获取失败 请联系管理员');
            }
        });
    });


    $(function () {
        refreshCheckCode();
        toCalibration();
    });

    // 刷新图形验证码
    function refreshCheckCode() {
        $('#checkCodeImg').click(function () {
            // 发起 AJAX 请求
            $.ajax({
                url: "/getCaptcha",
                method: "GET",
                success: function (response) {
                    $('#checkCodeImg').attr("src", response.img);
                    $('#checkCodeKey').val(response.key);
                },
                error: function () {
                    console.error('验证码获取失败 请联系管理员');
                }
            });
        });
    }


    function toCalibration() {

        $('#button').click(function () {
            $.ajax({
                url: "http://localhost/calibration",
                type: "POST",
                data: $("#formId").serialize(),
                success: function (data) {
                    alert("校验结果: " + data);
                }
            })
        })
    }

</script>

</body>
</html>