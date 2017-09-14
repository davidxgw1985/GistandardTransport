<#escape valueX as valueX?html>
<!DOCTYPE html>
<html lang="en" style="font-size:16px;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>审核结果</title>

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
<body class="single ">
<section class="wrapper">
    <div class="top">
        <span class="tp-goback" id="exit"></span>
        <span class="tp-title">审核结果</span>
    </div>
    <div class="content">
        <#if status == 3>
            <div class="con-success">
                <div class="remind suc-img anm-rollIn">
                </div>
                <div class="remind suc-desc">
                    <p>${msg}</p>
                </div>
                <div class="upgrade-exp">
                    <div class="btn-upgrade">继续升级</div>
                </div>
            </div>
        <#elseif status == 1>
            <div class="con-success">
                <div class="wait suc-img anm-rollIn">
                </div>
                <div class="wait suc-desc">
                    <p>${msg}</p>
                </div>
            </div>
        <#elseif status == 2>
            <div class="con-success">
                <div class="suc-img anm-rollIn">
                </div>
                <div class="suc-desc">
                    <p>${msg}</p>
                </div>
                <div class="upgrade-exp">
                    <div class="btn-upgrade">继续升级</div>
                </div>
            </div>
        </#if>
    </div>
</section>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/exit.js"></script>
<script>
    $(function(){
        //JavaScriptObject.inputAccountId();//获取客户端accountId
        var desc = {
            init:function(){
                this.initEvent();
            },
            initEvent:function() {
                //返回上个页面
                $(".tp-goback").click(function () {
                    if ($(this).attr("id") == "exit") {
                        JavaScriptObject.exitFinish(1);
                    } else {
                        window.history.go(-1);
                    }
                });
            }
        };

        desc.init();
    });

    //客户端，调用js，想页面传输用户id
    function getAccountId(acctId){
        $('#accountId').val(acctId);
    }

    $('.btn-upgrade').click(function () {
        var delay = 0;
        setTimeout(function(){
            window.location.href = globalStaticUrl + "/msApp/mobileStation/apply/infoByChose?access_token=" + JavaScriptObject.getAccessToken();
        }, delay);
    });
</script>
</body>
    <#include "common/script.ftl">
</html>
</#escape>