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
    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
	<title>选择升级角色</title>

	<script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
	<link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
	<link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
	<link rel="stylesheet" href="${staticUrl}/css/apply_index.css?v=1.1"/>
</head>
<body style="-webkit-overflow-scrolling: touch;">
	<section class="wrapper">

		<div class="top">
			<span class="tp-goback" id="exit"></span>
			<span class="tp-title">选择升级角色</span>
		</div>
		<div class="content">
			<#if mobileRoleBean.hasRole != ''>
                <p class="con-tips  anm-fadeInDownBig tips-title ">您已经是${mobileRoleBean.hasRole}</p>
                <#if mobileRoleBean.noRole != ''>
                    <p class="con-tips  anm-fadeInDownBig tips-desc ">您可以继续申请成为${mobileRoleBean.noRole}</p>
                </#if>

                <section class="con-type">
                    <section class="tp-courier anm-bounceInLeft">
                        <#if mobileRoleBean.hasRoleMap["OPERATOR_COURIER"]>
                            <div class="cou-img up-reqired-flag" id="1">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-courier-mo.png">
                            </div>
                        <#else>
                            <div class="cou-img " id="1">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-courier.png">
                            </div>
                        </#if>
                        <p class="tp-mes">快递员</p>
                    </section>
                </section>

                <section class="con-type">
                    <section class="tp-courier anm-bounceInRight">
                        <#if mobileRoleBean.hasRoleMap["OPERATOR_CAR_OWNER"]>
                            <div class="cou-img up-reqired-flag" id="2">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-driver-mo.png">
                            </div>
                        <#else>
                            <div class="cou-img " id="2">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-driver.png">
                            </div>
                        </#if>
                        <p class="tp-mes">司机</p>
                    </section>
                </section>

                <section class="con-type">
                    <section class="tp-courier anm-bounceInLeft">
                        <#if mobileRoleBean.hasRoleMap["OPERATOR_MSTATION"]>
                            <div class="cou-img up-reqired-flag" id="3">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-mg-mo.png">
                            </div>
                        <#else>
                            <div class="cou-img " id="3">
                                <img src="${staticUrl}/images/mobileApply/images/up-type-mg.png">
                            </div>
                        </#if>
                        <p class="tp-mes">咪站</p>
                    </section>
                </section>
            <#else>
                <p class="con-tips  anm-fadeInDownBig">请选择您要升级的商户类型</p>
                <section class="con-type">
                    <section class="tp-courier anm-bounceInLeft">
                        <div class="cou-img " id="1">
                            <img src="${staticUrl}/images/mobileApply/images/up-type-courier.png">
                        </div>
                        <p class="tp-mes">快递员</p>
                    </section>
                </section>
                <section class="con-type">
                    <section class="tp-courier anm-bounceInRight">
                        <div class="cou-img " id="2">
                            <img src="${staticUrl}/images/mobileApply/images/up-type-driver.png">
                        </div>
                        <p class="tp-mes">司机</p>
                    </section>
                </section>
                <section class="con-type">
                    <section class="tp-courier anm-bounceInLeft">
                        <div class="cou-img " id="3">
                            <img src="${staticUrl}/images/mobileApply/images/up-type-mg.png">
                        </div>
                        <p class="tp-mes">咪站</p>
                    </section>
                </section>
            </#if>
		</div>
	</section>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/index.js"></script>
    <script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js"></script>
</body>
    <#include "common/script.ftl">
</html>
</#escape>