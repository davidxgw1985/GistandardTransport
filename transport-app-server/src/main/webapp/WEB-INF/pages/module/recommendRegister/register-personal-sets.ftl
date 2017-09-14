<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Xvalueplus注册</title>

    <script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
    <link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/recommendRegister/register.css"/>
</head>
<body class="single" style="-webkit-overflow-scrolling: touch;">
	<section class="wrapper">
		<div class="top">
			<span class="tp-goback"></span>
			<span class="tp-title">个人用户注册</span>
		</div>
        <form id="person-third-fill" action="${globalReqUrl}/msApp/recommendRegister/o2IdRegisterSave" method="post">
            <input type="hidden" name="token" id="token" value="${token}">
			<div class="content">
				<div class="con-site my-site ">
					<input type="text" class="inp1" value="O2ID" name="" readonly>
					<input type="text" class="inp1" value="${accountRegisterBean.area}" name="" readonly>
					<input type="text" class="inp1" value="${accountRegisterBean.telCode}" name="" readonly>
					<input type="text" id="login-num"  class="inp1 i_required" placeholder="帐号八位数(必填)" name="userNo" value="${o2IdList[0].accountValue}" readonly></div>
					<input type="hidden" name="accountId" id="accountId" value="${o2IdList[0].accountId}">
				<div class="con-site new-site ">
					<input type="password" class="i_required" placeholder="设置密码(必填)" name="acctPassword" id="acctPassword" maxlength="20" ></div>
                <div class="con-site new-site ">
                    <p class="num-desc">密码长度8-20位,由数字、大小写字母组成</p>
                </div>
				<div class="con-site new-site ">
					<input type="password" class="i_required" placeholder="确认密码(必填)" name="confirmPassword" id="confirmPassword"  maxlength="20" >
				</div>

                <div class="con-site con-me">
                    <div class="site-yzm">
                        <input type="text" class="i_required" maxlength="4" placeholder="验证码(必填)" name="picValidateCode" id="picValidateCode">
                    </div>
                    <img id="validateCodeImg" class="img-yzm" src="${globalReqUrl}/validateCode"/>
                </div>

				<div class="btn-next btn-person-third-submit" id="person-third-submit">提交</div>
			</div>
		</form>
	</section>

    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/plugins/utils.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/app/register.js"></script>
</body>
<#include "common/script.ftl">
</html>