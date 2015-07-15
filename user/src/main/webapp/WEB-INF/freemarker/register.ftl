<html>
<head>
    <title>用户注册</title>
    <#--<script language="JavaScript" src="jquery-2/jquery-2.1.1.min.js"></script>-->
    <script src="${absoluteContextPath}/js/register.js"></script>
</head>
<body onLoad="document.form1.userName.focus()">
欢迎来注册<br>
<form action="inputRegister"  method="post"  name="form1" onsubmit="return check()">
    username: <input type="text" name="userName" onblur="checkUser(this.value)"/>
    <span id="userErr" style="color: red"></span><br>
    password: <input type="password" name="passWord" onblur="checkPassword(this.value)"/>
    <span id="passwordErr" style="color: red"></span><br>
    &nbsp;&nbsp;&nbsp;again&nbsp;&nbsp;&nbsp;&nbsp;:
    <input type="password" name="passWord2" onblur="checkPassword2(this.value)"/>
    <span id="passwordErr2" style="color: red"></span><br>
    <input type="submit" value="注册"/><span style="color:red;">${registerError!""}</span>
</form>
</body>
</html>