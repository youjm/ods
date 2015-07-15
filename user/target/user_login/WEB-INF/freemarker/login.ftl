
<html>
<head>
    <title>ods用户登录</title>
    <script src="${absoluteContextPath}/js/login.js"></script>
    <meta charset="UTF-8"/>
</head>
<body >
<form action="inputLogin"  method="post">
    username: <input type="text" name="userName" id="username" autocomplete="on"/><br>
    password: <input type="password" name="passWord" id="password"/><br>

    <#--验证码-->
    <input name="checkCode" type="text" id="checkCode"
           size="8" ,maxlength="4" />
    <span style="COLOR: #ff0000"> <img src="PictureCheckCode" id="CreateCheckCode" align="middle"></span>
    <a href="" onclick="myReload()"> 看不清,换一个</a><br>

    &nbsp;&nbsp;&nbsp;<input type="checkbox"  />记住密码&nbsp;&nbsp;&nbsp;
    <a href="/forgetPassword">忘记密码</a><br>&nbsp;&nbsp;&nbsp;
    <input type="submit" value="登录"/>
<#-- 如果账号密码错误输出如下 -->
    <span style="color: red;">
     ${passError!""}
     ${message!""}
    </span>
</form>
没有账号，<a href='/register'>去注册</a>
</body>
</html>