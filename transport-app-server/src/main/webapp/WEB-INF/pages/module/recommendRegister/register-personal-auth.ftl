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
			<span class="tp-title">个人用户注册/实名认证</span>
		</div>
        <form id="person-second-fill" action="${globalReqUrl}/msApp/recommendRegister/normalPersonalSave" method="post">
            <input type="hidden" name="identityPositiveFileId" id="identityPositiveFileId" value="${personalRegister.identityPositiveFileId}">
            <input type="hidden" name="identityNegativeFileId" id="identityNegativeFileId" value="${personalRegister.identityNegativeFileId}">
            <input type="hidden" name="identityHalfFileId" id="identityHalfFileId" value="${personalRegister.identityHalfFileId}">
            <input type="hidden" name="identityPositiveUrl" id="identityPositiveUrl" value="${personalRegister.identityPositiveUrl}">
            <input type="hidden" name="identityNegativeUrl" id="identityNegativeUrl" value="${personalRegister.identityNegativeUrl}">
            <input type="hidden" name="identityHalfUrl" id="identityHalfUrl" value="${personalRegister.identityHalfUrl}">
            <input type="hidden" name="portraitFileId" id="portraitFileId" value="${personalRegister.portraitFileId}">
            <input type="hidden" name="userImg" id="userImg" value="${personalRegister.userImg}">
            <input type="hidden" name="portraitUrl" id="portraitUrl" value="${personalRegister.portraitUrl}">
            <input type="hidden" name="picIdentityNo" id="picIdentityNo" value="">
            <input type="hidden" name="picUserName" id="picUserName" value="">
			<div class="content" style="">
				<div class="driver-items">
					<p class="item-title none"></p>
					<ul>
                        <li class="item-inp none">
                            <input id="identityPositiveFile" name="identityPositiveFile" type="file"/>
                        </li>

                        <li class="item-inp none">
                            <input id="identityNegativeFile" name="identityNegativeFile" type="file"/>
                        </li>

                        <li class="item-inp none">
                            <input id="identityHalfFile" name="identityHalfFile" type="file"/>
                        </li>

						<li class="item-img required">
							<div id="identityPositiveSelect" class="upload-img card-zm-img up-right ">
								<span class="up-status"></span>
							</div>
							<p>身份证正面照</p>
						</li>

						<li class="item-img required">
							<div id="identityNegativeSelect" class="upload-img card-fm-img">
								<span class="up-status"></span>
							</div>
							<p>身份证反面照</p>
						</li>

						<li class="item-img required">
							<div id="identityHalfSelect" class="upload-img bs-img">
								<span class="up-status"></span>
							</div>
							<p>手持身份证半身照</p>
						</li>
						<li class="item-inp ">
							<input type="text" class="i_required" maxlength="18" placeholder="身份证号(必填)"  name="identno" id="identno" value="${personalRegister.identno}">
						</li>

						<li class="item-inp ">
							<input type="text" class="i_required" maxlength="200" placeholder="真实姓名(必填)"  name="realName" id="realName" value="${personalRegister.realName}">
						</li>

						<li class="item-btn ">
							<div class="btn-submit btn-person-second-submit" id="person-second-submit">下一步</div>
						</li>
					</ul>
				</div>
			</div>
        </form>
	</section>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
    <script type="text/javascript" src="${staticUrl}/script/jQueryUpload/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="${staticUrl}/script/jQueryUpload/js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${staticUrl}/script/jQueryUpload/js/jquery.fileupload.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/app/register.js"></script>
</body>
<#include "common/script.ftl">
</html>