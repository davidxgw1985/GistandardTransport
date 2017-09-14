<#escape valueX as valueX?html>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>升级成为司机</title>

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
<body style="-webkit-overflow-scrolling: touch;" class="single">
	<section class="wrapper">
		<div class="top">
			<span class="tp-goback"></span>
			<span class="tp-title">升级成为司机</span>
		</div>
		<form id="driver_fill" action="${globalReqUrl}/msApp/mobileStation/apply/carOwner" method="post">
            <input type="hidden" id="token" name="token" value="${token}"/>
		<div class="content" style="">
			<div class="driver-items">
                <#include "module/applytobe/identityPersonal.ftl">

				<p class="item-title">车辆信息</p>
				<ul>
                    <li class="item-inp">
                        <input type="text" class="text i_required" maxlength="20" placeholder="驾驶证号（必填）"  name="driverCard" value="${carOwnerBean.driverCard}">
                    </li>
                    <li class="item-img">
                        <div id="driverLicense" class="upload-img cardnum-img">
                            <span class="up-status"></span>
                        </div>
                        <p>驾驶证照片（必填）</p>
                        <input type="hidden" class="" id="driverLicenseFileId"  name="driverLicenseFileId" value="${carOwnerBean.driverLicenseFileId}">
                        <input type="hidden" id="driverLicenseUrl"  name="driverLicenseUrl" value="${carOwnerBean.driverLicenseUrl}">
                    </li>

                    <li class="item-inp">
                        <input type="text" placeholder="紧急联系人" maxlength="20" class="" name="urgentLinkUser" value="${carOwnerBean.urgentLinkUser}">
                    </li>

                    <li class="item-inp">
                        <input type="text" placeholder="紧急联系电话" maxlength="20" class="" name="urgentLinkTel" value="${carOwnerBean.urgentLinkTel}">
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
						<div class="btn-submit btn-car-submit" id="driver-submit">提&nbsp;交</div>
					</li>
				</ul>
			</div>

		</div>
        </form>
	</section>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js?v=1.0.1"></script>
</body>
	<#include "common/script.ftl">
</html>
</#escape>