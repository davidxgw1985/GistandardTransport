<!doctype html>
<html lang="zh">
<head>
    <#include "common/pageTitle.ftl">
    <link rel="stylesheet" type="text/css" href="${staticUrl}/css/login.css">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="psc_logo"></div>

        <div class="container">
            <h1 class=" psc_top"></h1>
            <div class="tips"><div class="error_tips" style="display: none;"><span class="err_m">提示错误！</span></div></div>
            <form class="form">
                <input type="text" placeholder="Username" value="" id="userName" name="userName">
                <input type="password" placeholder="Password" value="" id="userPassword" name="userPassword">
                <button type="button" id="loginBtn">Login</button>
            </form>
        </div>
        <div class="earth">
        </div>
    </div>
</div>
<script src="${staticUrl}/script/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script>
    $('#loginBtn').click(function(event){
        $.ajax({
            url:"${reqUrl}/checkLogin",
            type:"post",
            dataType:"JSON",
            data:{
                userName: jQuery.trim($("#userName").val()),
                userPassword: jQuery.trim($("#userPassword").val())
            },
            success:function(data){
                if(data.state){
                    $(".error_tips").hide();
                    $('.wrapper').addClass('form-success');
                    $('form').fadeOut(500,function(){
                        setTimeout(function(){
                            window.document.location.href = "${reqUrl}/frame/"
                        },800);
                    });
                }
                else {
                    $(".error_tips").fadeIn(500);
                    $(".err_m").html("");
                    $(".err_m").html(data.message);
                    setTimeout(function(){
                        $(".error_tips").fadeOut(1000);
                    },3000);
                }
            }
        });
    });

    document.onkeydown= function(e){
        var e = e || window.event;
        var kc = e.keyCode || e.charCode;
        if (kc == 13){
            $('#loginBtn').click();
        }

    }
</script>
</body>
</html>
