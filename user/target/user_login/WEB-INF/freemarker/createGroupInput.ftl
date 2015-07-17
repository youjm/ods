<html>
<body>
<h2>login sucessful!</h2>

hello!${curUser.userName}
<hr>

<form action="/createGroupInputJudge" name="form1" method="post">
    请输入组名：<input type="text" name="name" /><br>
    请上传头像：<input type="file" name="icon"/><br>
    <input type="submit" value="确认"/>${message !""}
</form>


</body>
</html>