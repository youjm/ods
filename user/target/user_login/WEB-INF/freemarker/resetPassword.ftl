
<html>
<head>
    <title>重置密码</title>
    <meta charset="UTF-8"/>
    <script src="${absoluteContextPath}/js/resetPassword.js"></script>
</head>
<body>
<form action="resetPasswordJudge"  method="post" onsubmit=" return check()">
    <input type="text" hidden="hidden" name="username"  value=${username!""} />
    请输入您的新密码: <input type="password" name="password1" id="password1" /><br>
    请再次输入新密码: <input type="password" name="passWord2" id="password2" /><br>
    <input type="submit" value="修改密码"/>
<#-- 如果两次密码不一样输出如下 -->
    <span style="color: red;" id="message">
    ${passError!""}
  ${username!""}
    </span>
</form>
</body>
</html>