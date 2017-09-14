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
        <form id="person-first-fill" action="${globalReqUrl}/msApp/recommendRegister/acctRegisterSave" method="post">
            <input type="hidden" id="errorInfo" name="errorInfo" value="${errorInfo}"/>
            <input type="hidden" id="token" name="token" value="${token}"/>
            <input type="hidden" id="acctType" name="acctType" value="1"/>
            <input type="hidden" id="area" name="area" value="CN"/>
            <input type="hidden" id="provinceAndCity" name="provinceAndCity" value=""/>
            <input type="hidden" id="provinceAndCity_sel" name="provinceAndCity_sel" value=""/>
            <input type="hidden" id="telPrefix" name="telPrefix" value="+86"/>

            <input type="hidden" id="provinceSelect" name="provinceSelect" value="${accountRegisterBean.province}"/>
            <input type="hidden" id="citySelect" name="citySelect" value="${accountRegisterBean.city}"/>
            <input type="hidden" id="countySelect" name="countySelect" value="${accountRegisterBean.county}"/>
			<div class="content">
				<div class="con-site new-site ">
					<select id="province" name="province" placeholder="居住省份(必填)" class="i_required">
					</select>
				</div>
				<div class="con-site new-site ">
					<select id="city"  name="city" placeholder="居住城市(必填)" class="i_required">
						<option value="">居住城市(必填)</option>
					</select>
				</div>
                <div class="con-site new-site ">
                    <select id="county"  name="county" placeholder="居住区县(必填)" class="i_required">
                        <option value="">居住区县(必填)</option>
                    </select>
                </div>

				<div class="con-site new-site ">
					<input type="text" class="i_required" maxlength="20" placeholder="手机号码(必填)" name="telephone" id="telephone" value="${accountRegisterBean.telephone}">
				</div>
				<div class="con-site con-me">
					<div class="site-yzm">
						<input type="text" class="i_required" maxlength="4" placeholder="图片验证码(必填)" name="picValidateCode" id="picValidateCode">
					</div>
                    <img id="validateCodeImg" class="img-yzm" src=""/>
				</div>
				<div class="con-site new-site pht-yzm">
					<input type="text" class="i_required" maxlength="6" placeholder="短信验证码(必填)" name="validateCode" id="validateCode">
					<a href="javascript:void(0);" id="getTelMsg">
						<span id="getValidateCode">
							获取验证码
                        </span>
                        <span id="getValidateCode2">
                            <span id ="seconds"></span>秒后重新获取
                        </span>
					</a>
				</div>

				<div class="btn-next btn-person-first-submit" id="person-first-submit">下一步
				</div>
			</div>
		</form>
	</section>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/plugins/cityData.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/recommendRegister/app/register.js"></script>
</body>
<#include "common/script.ftl">
</html>