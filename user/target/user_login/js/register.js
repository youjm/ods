/**
 * Created by youjm on 2015/7/13.
 */


//最终提交时弹出对话框，并且让提交不成功
//判断提示
function check(){
    var userErr=document.getElementById("userErr").textContent;
    var passwordErr = document.getElementById("passwordErr").textContent;
    var passwordErr2= document.getElementById("passwordErr2").textContent;
    if(userErr==""&&passwordErr==""&&passwordErr2==""){
        return true;
    } else{
        alert(userErr+" "+passwordErr+" "+passwordErr2);
        return false;
    }
}

//失去焦点是，用户名检查
function checkUser(ssn){
    var  apos=ssn.indexOf("@");
    var  dotpos=ssn.lastIndexOf(".");
    if(ssn==""||ssn==null){
        document.getElementById("userErr").innerHTML="用户名不得为空";
    }else if(apos<1||dotpos-apos<2){
        //输入的数据必须包含 @ 符号和点号(.)。同时，@ 不可以是邮件地址的首字符，并且 @ 之后需有至少一个点号：
        document.getElementById("userErr").innerHTML="邮箱格式不正确";
    }else{
        document.getElementById("userErr").innerHTML="";
    }
}
//失去焦点时密码第一次长度检查
function checkPassword(ssn){
    if(ssn.length<8){
        document.getElementById("passwordErr").innerHTML="密码长度不得小于8位";
    }else{
        document.getElementById("passwordErr").innerHTML="";
    }
}

//判断两次密码是否一样
function checkPassword2(ssn){
    var password1=document.form1.passWord.value;
    if(ssn!=password1){
        document.getElementById("passwordErr2").innerHTML="两次密码输入不一样";
    }else {
        document.getElementById("passwordErr2").innerHTML="";
    }
}