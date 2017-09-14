<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>升级成为快递员</title>

	<link rel="stylesheet" href="${staticUrl}/css/apply_lanrenzhijia.css"/>
	<script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
	<script src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
    <link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_index.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_upgrade.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_mstation.css"/>
</head>
<body class="single" style="-webkit-overflow-scrolling: touch;" class="single">
	<section class="wrapper">
		<div class="top">
			<span class="tp-goback"></span>
			<span class="tp-title">升级成为快递员</span>
		</div>
        <form id="courier_fill" action="${globalReqUrl}/msApp/mobileStation/apply/courier" method="post">
            <input type="hidden" id="token" name="token" value="${token}"/>
            <div class="content">
                <div class="driver-items">
                <#include "module/applytobe/identityPersonal.ftl">
                    <p class="item-title none"></p>
                    <ul>
                        <li class="item-inp">
                            <input class="" maxlength="20" type="text" name="urgentLinkUser" placeholder="紧急联系人" id="urgentLinkUser" value="${merchantCourierBean.urgentLinkUser}">
                        </li>

                        <li class="item-inp">
                            <input class="" maxlength="20" type="text" name="urgentLinkTel" placeholder="紧急联系人电话" id="urgentLinkTel" value="${merchantCourierBean.urgentLinkTel}">
                        </li>

                        <li class="item-yzm ">
                            <div class="yzm-dv">
                                <div class="yzm-num">
                                    <input type="text" placeholder="验证码" maxlength="4" name="validateCode" id="validateCode" >
                                </div>
                                <img id="validateCodeImg" class="img-yzm" src=""/>
                            </div>
                        </li>
                        <li class="item-btn ">
                            <div class="btn-submit btn-courier-submit" id="courier-submit">下一步</div>
                        </li>
                    </ul>
                </div>
            </div>
        </form>
	</section>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js?v=1.0.4"></script>
</body>
<#include "common/script.ftl">
</html>