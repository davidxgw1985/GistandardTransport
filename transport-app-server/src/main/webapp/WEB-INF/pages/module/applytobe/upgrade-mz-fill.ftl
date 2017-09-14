<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>升级成为咪站</title>

    <link rel="stylesheet" href="${staticUrl}/css/apply_lanrenzhijia.css"/>
    <script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
    <link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_index.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_upgrade.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_mstation.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/lCalendar.css"/>
</head>
<body class="single" style="-webkit-overflow-scrolling: touch;">
<section class="wrapper">
    <div class="top">
        <span class="tp-goback"></span>
        <span class="tp-title">升级成为咪站</span>
    </div>
    <form id="mstation_fill" action="${globalReqUrl}/msApp/mobileStation/apply/mstation" method="post">
        <input type="hidden" id="token" name="token" value="${token}"/>
        <input type="hidden" name="staLongitude" id="staLongitude" value="${mstationBean.staLongitude}">
        <input type="hidden" name="staLatitude" id="staLatitude" value="${mstationBean.staLatitude}">
        <input type="hidden" name="country" id="country" value="142">
        <input type="hidden" name="province" id="province" value="${mstationBean.province}">
        <input type="hidden" name="city" id="city" value="${mstationBean.city}">
        <input type="hidden" name="county" id="county" value="${mstationBean.county}">
        <div class="content">
            <div class="driver-items">
            <#include "module/applytobe/identityPersonal.ftl">
                    <p class="item-title none"></p>
                    <ul>
                        <li class="item-inp  icon-addres">
                            <input class="i_required" maxlength="50" type="text" name="address" placeholder="详细地址 （必填）" id="address" value="${mstationBean.address}" readonly>
                        </li>
                        <li class="item-inp">
                            <input class="i_required" maxlength="50" type="text" name="detailAdd" placeholder="具体楼层、门牌号 （必填）" id="detailAdd" value="${mstationBean.detailAdd}">
                        </li>
                        <li class="item-inp">
                            <input type="text" class="i_required" maxlength="8" name="businessHoursStart" placeholder="营业开始时间 （必填）" id="businessHoursStart" value="${mstationBean.businessHoursStart}" onClick="editTime(event);" readonly>
                        </li>
                        <li class="item-inp">
                            <input type="text" class="i_required" maxlength="8"  name="businessHoursEnd" placeholder="营业结束时间 （必填）" id="businessHoursEnd" value="${mstationBean.businessHoursEnd}" onClick="editTime(event);" readonly>
                        </li>
                        <li class="item-inp">
                            <input class="" maxlength="20" type="text" name="urgentLinkUser" placeholder="紧急联系人" id="urgentLinkUser" value="${mstationBean.urgentLinkUser}">
                        </li>
                        <li class="item-inp">
                            <input class="" maxlength="20" type="text" name="urgentLinkTel" placeholder="紧急联系人电话" id="urgentLinkTel" value="${mstationBean.urgentLinkTel}">
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
                            <div class="btn-submit btn-mstation-submit" id="mstation-submit">下一步</div>
                        </li>
                    </ul>
            </div>
        </div>
    </form>
</section>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js?v=1.0.9"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lCalendar.js"></script>
</body>
<#include "common/script.ftl">
</html>