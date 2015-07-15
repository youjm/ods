<html>
<body>
<h2>welcome to findPassword</h2>

<form action="/findPassword" name="form1" method="post">
    请输入您的邮箱：<input type="text" name="username"/>
    <input type="submit" value="验证"/> <span style="color: red"> ${check ! ""}</span>
</form>
</body>
</html>