/**
 * Created by youjm on 2015/7/15.
 */

//看不清楚，再换一张的功能。
function myReload() {

    document.getElementById("CreateCheckCode").src = document.getElementById("CreateCheckCode").src
    + "?nocache=" + new Date().getTime();
}


