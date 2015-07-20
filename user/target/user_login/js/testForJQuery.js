/**
 * Created by youjm on 2015/7/20.
 */

$(document).ready(function() {
    $("#letter-a" ).click(function(event) {
        alert("fdas");
        event.preventDefault();
        alert("bbbbb");
        $('#dictionary').load();
        alert("cccc");
    });
});


