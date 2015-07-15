/**
 * Created by youjm on 2015/7/14.
 */

function check(){
    var password1 = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    if(password1.length<8){
        document.getElementById("message").innerHTML="密码长度不得小于8位";
        return false;
    }else if(password1!=password2){
        document.getElementById("message").innerHTML="两次输入的密码不一样，请重新输入";
        return false;
    }else{
        return true;
    }
}

