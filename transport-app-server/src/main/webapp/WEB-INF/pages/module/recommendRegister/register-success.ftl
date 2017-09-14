<!DOCTYPE html>
<html lang="en" style="font-size:16px;">
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
			<span class="tp-title">提交成功</span>
		</div>
		<div class="content">
			<div class="con-success">
				<div class="suc-desc">
					<p>恭喜,您已经成功注册!</p>
					<p class="result-num">
						O2ID:<span class="bluecl">${acctUserName}</span>
					</p>
					<p class="care-tips">请妥善保管您的O2ID和密码，以便登录我们的平台</p>
				</div>
			</div>
            <div class="btn-next btn-down-app none">
                <span class="">IOS下载</span>
            </div>
		</div>
	</section>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/plugins/utils.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/app/register.js"></script>
</body>
<#include "common/script.ftl">
</html>