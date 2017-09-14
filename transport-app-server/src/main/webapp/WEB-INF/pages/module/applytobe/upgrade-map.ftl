<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>地图</title>

    <link rel="stylesheet" href="${staticUrl}/css/apply_lanrenzhijia.css"/>
    <script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=gWxT9jkqtI6RYudtqygsl6gM&s=1"></script>
    <style type="text/css">
        #allmap{width:auto;height:650px;}
    </style>
    <link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_index.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_upgrade.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_mstation.css"/>
</head>
<body class="single" style="-webkit-overflow-scrolling: touch;">
<section class="wrapper">
    <input type="hidden" name="staLongitude" id="staLongitude" value="${mapReq.staLongitude}">
    <input type="hidden" name="staLatitude" id="staLatitude" value="${mapReq.staLatitude}">
    <input type="hidden" name="address" id="address" value="">
    <div class="top">
        <span class="tp-goback"></span>
        <span class="tp-title">地图</span>
    </div>
    <section class="search-con">
        <input placeholder="搜索地址" type="text" name="" id="searchKey" value="${mapReq.address}">
        <i class="btn-icsear"></i>
    </section>
    <div id="allmap"></div>
    <div class="btn-map" id="btn-confirm">确&nbsp;定</div>
</section>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js"></script>
</body>
<#include "common/script.ftl">
</html>
<script charset="UTF-8">
    var address = $("#address").val();
    var longitude = $("#staLongitude").val();
    var latitude = $("#staLatitude").val();
    var setBestView = new Array();
    var templat  ;
    var tempLong ;
    var initIcon=new BMap.Icon("${staticUrl}/images/mobileApply/images/marker_red.png", new BMap.Size(32, 32));

    var map = new BMap.Map("allmap");
    var geoc = new BMap.Geocoder();
    map.enableScrollWheelZoom();
    map.enableAutoResize();
    map.disableInertialDragging();
    if(longitude && latitude){
        var tempPoint = new BMap.Point(longitude,latitude);
        map.centerAndZoom(tempPoint, 16);
        var marker = new BMap.Marker(new BMap.Point(longitude,latitude),{icon: initIcon});
        map.addOverlay(marker);
    }else{
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
        map.centerAndZoom(new BMap.Point(tempLong, templat), 11);
    }

    var navigationControl = new BMap.NavigationControl({

        anchor: BMAP_ANCHOR_TOP_LEFT,

        type: BMAP_NAVIGATION_CONTROL_LARGE,

        enableGeolocation: true
    });

    function myFun(result){
        templat = result.center.lat;
        tempLong = result.center.lng;
        var cityName = result.name;
        map.setCenter(cityName);
    }
    function showInfo(e){
        map.clearOverlays();
        var clickPoint = new BMap.Point(e.point.lng,e.point.lat);
        var marker = new BMap.Marker(clickPoint,{icon: initIcon});

        map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE);
        longitude= e.point.lng;
        latitude= e.point.lat;
        geoc.getLocation(e.point, function(rs){
            var addComp = rs.addressComponents;
            address = addComp.province + "-" + addComp.city  + addComp.district + addComp.street +  addComp.streetNumber;
        });

        $("#staLongitude").val(longitude);
        $("#staLatitude").val(latitude);
        $("#address").val(address);
    }

    map.addEventListener("click", showInfo);

    $("#btn-confirm").click(function(){
        if($("#address").val() == ""){
            showTips("无法获取地址");
        }else{
            var url = globalStaticUrl + "/msApp/mobileStation/apply/mzInfo2?address=" + $("#address").val()
                    + "&staLongitude=" + $("#staLongitude").val() + "&staLatitude=" + $("#staLatitude").val();
            url = encodeURI(url);
            url = encodeURI(url);
            window.location.href = url;
        }

    });

    $(".btn-icsear").click(function(){
        var searchKey = jQuery.trim($("#searchKey").val());
        map.clearOverlays();
        var local = new BMap.LocalSearch(map, {
            renderOptions:{map: map}
        });
        local.search(searchKey);
    });
</script>