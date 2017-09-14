$(function(){
    var desc = {
        init:function(){
            this.initEvent();
            changeCode();
        },
        initEvent:function(){
            //返回上个页面
            $(".tp-goback").click(function() {
                if ($(this).attr("id") == "exit") {
                    JavaScriptObject.exitFinish(1);
                } else if($(this).attr("id") == "index"){
                	window.location.href = globalReqUrl + "/msApp/mobileStation/apply/infoByChose?access_token=" + JavaScriptObject.getAccessToken();
                } else{
                    window.history.go(-1);
                }
            });

            //选择框checkbox
            $(".mycheckbox").click(function(){
                    $(".ckbox-active").removeClass("ckbox-active") && $(this).addClass("ckbox-active");
                    // $(this).hasClass("ckbox-active") ? $(this).removeClass("ckbox-active") :  $(this).addClass("ckbox-active");
            });

            //上传文件
            $(".driver-img").click(function(){
                $("#uploadFile").trigger('click');
                // $(this).find("input[type='file']").trigger("click");
            });

             $("#uploadFile").change(function(e){
                // $(".driver-img img").attr("src",$(this).val());
                // $(this).val();
             });

            $(".btn-upgrade").click(function(){
                var tp = getQueryString("type");
                console.log(tp)
                // tp ： 1- 快递员 2 -司机 页面区分也main跳转
                if(!$(".exp-green").hasClass("ckbox-active")){
                    showTips("请同意服务条款");
                    return;
                }
                if(tp){
                    if(tp == 1){
                        window.location.href = globalReqUrl + "/msApp/mobileStation/apply/kdInfo1"+ getToken("?");
                    }else if(tp == 2){
                        window.location.href = globalReqUrl + "/msApp/mobileStation/apply/sjInfo1"+ getToken("?");
                    }else if(tp == 3){
                        window.location.href = globalReqUrl + "/msApp/mobileStation/apply/mzInfo1"+ getToken("?");
                    }
                }else{
                    showTips("非法进入...请刷新页面")
                }
            });

            //成为车主 - 下一步按钮 - 页面跳转
            $(".btn-driver-submit").click(function(){
                var picId = $('#transportAgreeFileId').val();
                if (picId.length==0){
                    showTips("请上传证件");
                    return false;
                }else{
                	$('#driver_upload').attr("action",$('#driver_upload').attr("action")+getToken("?"));
                    $('#driver_upload').submit();
                }
            });

            //车主车辆信息提交
            $(".btn-car-submit").click(function(){
                var id = $(this).attr("id");
                showLoading();
                setTimeout(function(){
                    if (id !="driver-submit"){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }
                    var bol = "";
                    $('.i_required').each(function () {
                        var msg = $(this).attr("placeholder")+"必填";
                        bol = checkValShow($(this).val(),1,msg);
                        if(bol){
                            setTimeout(function(){hideLoading();},1000);
                            return false;
                        }
                    });

                    if(bol){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }else{
                        msg = "请上传驾驶员证件";
                        bol = checkValShow($('#driverLicenseFileId').val(),1,msg)
                        if(bol){
                            setTimeout(function(){hideLoading();},1000);
                            return false;
                        }
                    }

                    $('.i_num').each(function () {
                        msg = $(this).attr("placeholder")+"必须为数字";
                        if($(this).val()){
                            bol = checkValShow($(this).val(),4,msg);
                            if(bol){
                                setTimeout(function(){hideLoading();},1000);
                                return false;
                            }
                        }
                    });

                    var code = $('#validateCode').val();
                    if (code==''){
                        setTimeout(function(){hideLoading();},1000);
                        showTips("验证码不能为空");
                        return false;
                    }

                    $(this).attr("id","");
                    var t = $(this);

                    doAjaxReq({
                        url: globalReqUrl + "/checkValidateCode",
                        data: {
                            'validateCode': code
                        },
                        success: function (data) {
                            if (!data.state){
                                setTimeout(function(){hideLoading();},1000);
                                t.attr("id","driver-submit");
                                showTips("验证码错误");
                                return false;
                            }else{
                                if(!checkUserAuthentication()){
                                    setTimeout(function(){hideLoading();},1000);
                                    return false;
                                }
                                $('#driver_fill').attr("action",$('#driver_fill').attr("action")+getToken("?"));
                                $('#driver_fill').submit();
                                setTimeout(function(){hideLoading();},1000);
                            }
                        }
                    });
                },10);
            });

            $(".text2date").change(function(){
                $(this).removeAttr('placeholder');
                // $(this).prop('placeholder','');
            });

            $(".btn-driver-next").click(function(){
                var delay = 0;
                setTimeout(function(){
                    window.location.href = globalReqUrl + "/msApp/mobileStation/apply/sjInfo2"+ getToken("?");
                }, delay);
            });

            $(".btn-courier-next").click(function(){
                var delay = 0;
                setTimeout(function(){
                    window.location.href = globalReqUrl + "/msApp/mobileStation/apply/kdInfo2"+ getToken("?");
                }, delay);
            });

            $(".btn-mz-next").click(function(){
                var delay = 0;
                setTimeout(function(){
                    window.location.href = globalReqUrl + "/msApp/mobileStation/apply/mzInfo2"+ getToken("?");
                }, delay);
            });

            $(".btn-courier-submit").click(function(){
                var id = $(this).attr("id");
                showLoading();
                setTimeout(function(){
                    if (id !="courier-submit"){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }
                    var bol = "";
                    $('.i_required').each(function () {
                        var msg = $(this).attr("placeholder")+"必填";
                        bol = checkValShow($(this).val(),1,msg);
                        if(bol){
                            setTimeout(function(){hideLoading();},1000);
                            return false;
                        }
                    });
                    if(bol){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }
                    var code = $('#validateCode').val();
                    if (code==''){
                        setTimeout(function(){hideLoading();},1000);
                        showTips("验证码不能为空");
                        return false;
                    }
                    $(this).attr("id","");
                    var t = $(this);

                    doAjaxReq({
                        url: globalReqUrl + "/checkValidateCode",
                        data: {
                            'validateCode': code
                        },
                        success: function (data) {
                            if (data.state){
                                if(!checkUserAuthentication()){
                                    setTimeout(function(){hideLoading();},1000);
                                    return false;
                                }
                                $('#courier_fill').attr("action",$('#courier_fill').attr("action")+getToken("?"));
                                $('#courier_fill').submit();
                            }else{
                                t.attr("id","courier-submit");
                                showTips("验证码错误");
                            }
                            setTimeout(function(){hideLoading();},1000);
                        }
                    });
                },10);
            });

            $(".btn-mstation-submit").click(function(){
                var id = $(this).attr("id");
                showLoading();
                setTimeout(function(){
                    if (id !="mstation-submit"){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }
                    var bol = "";
                    $('.i_required').each(function () {
                        var msg = $(this).attr("placeholder")+"必填";
                        bol = checkValShow($(this).val(),1,msg);
                        if(bol){
                            setTimeout(function(){hideLoading();},1000);
                            return false;
                        }
                    });
                    if(bol){
                        setTimeout(function(){hideLoading();},1000);
                        return false;
                    }

                    if(!checkTime()){
                        setTimeout(function(){hideLoading();},1000);
                        showTips("开始时间不能大于结束时间");
                        return false;
                    }

                    var code = $('#validateCode').val();
                    if (code==''){
                        setTimeout(function(){hideLoading();},1000);
                        showTips("验证码不能为空");
                        return false;
                    }
                    $(this).attr("id","");
                    var t = $(this);
                    doAjaxReq({
                        url: globalReqUrl + "/checkValidateCode",
                        data: {
                            'validateCode': code
                        },
                        success: function (data) {
                            if (data.state){
                                if(!checkUserAuthentication()){
                                    setTimeout(function(){hideLoading();},1000);
                                    return false;
                                }
                                $('#mstation_fill').attr("action",$('#mstation_fill').attr("action")+getToken("?"));
                                $('#mstation_fill').submit();
                            }else{
                                t.attr("id","mstation-submit");
                                showTips("验证码错误");
                            }
                            setTimeout(function(){hideLoading();},1000);
                        }
                    });
                },10);

            });

            //验证码刷新
            $('#validateCodeImg').click(function () {
                changeCode();
            });

            //身份证正面照上传
            $('#identityPositiveSelect').click(function () {
                JavaScriptObject.pictureUpdate("identityPositiveFile");
            });

            //身份证反面照上传
            $('#identityNegativeSelect').click(function () {
                JavaScriptObject.pictureUpdate("identityNegativeFile");
            });

            //手持身份证半身照上传
            $('#identityHalfSelect').click(function () {
                JavaScriptObject.pictureUpdate("identityHalfFile");
            });

            //道路资格证上传
            $('#transportAgree').click(function () {
                JavaScriptObject.pictureUpdate("transportAgreeFile");
            });
             //驾驶证上传
            $('#driverLicense').click(function () {
                JavaScriptObject.pictureUpdate("driverLicenseFile");
            });

            //货车照片1上传
            $('#firstCar').click(function () {
                JavaScriptObject.pictureUpdate("firstCarNoFile");
            });

            //货车照片2上传
            $('#secondCar').click(function () {
                JavaScriptObject.pictureUpdate("secondCarNoFile");
            });

            //行驶证证上传
            $('#drivingLicense').click(function () {
                JavaScriptObject.pictureUpdate("drivingLicenseFile");
            });

            // 地址图标
            $('.icon-addres').click(function () {
                JavaScriptObject.showMap();
                //var url = globalReqUrl + "/msApp/mobileStation/apply/map?staLongitude="
                //    + $("#staLongitude").val() + "&staLatitude=" + $("#staLatitude").val();
                //window.location.href = url;
            });

            $(".car-infos").click(function(){
                $(this).hasClass("active-arrow") ?
                    ( $(this).removeClass("active-arrow") && $(this).nextUntil(".bg-infos").addClass("none")  )
                    :
                    ( $(this).addClass("active-arrow") && $(this).nextUntil(".bg-infos").removeClass("none") );
            });

            $(".bg-infos").click(function(){
                $(this).hasClass("active-arrow") ?
                    ( $(this).removeClass("active-arrow") && $(this).nextUntil(".item-btn").addClass("none")  )
                    :
                    ( $(this).addClass("active-arrow") && $(this).nextUntil(".item-btn").removeClass("none") );
            });
        }
    };

    desc.init();

    $('.i_required').blur(function () {
        var msg = $(this).attr("placeholder")+"必填";
        if($(this).attr("placeholder").indexOf("营业")<0){
            checkValShow($(this).val(),1,msg);
        }
    });

    $('.i_num').blur(function () {
        var msg = $(this).attr("placeholder")+"必须为数字";
        checkValShow($(this).val(),4,msg);
    });

    //var msg = $('#msg').val();
    //if (msg.length>0){
    //    showTips(msg);
    //}

    $(".car-infos").nextUntil(".bg-infos").addClass("none");
    $(".bg-infos").nextUntil(".item-btn").addClass("none");
    addImage();
});

function checkIdentityPositiveFile(){
    var result = false;
    doAjaxReq({
        url:globalReqUrl + "/msApp/checkRegister/checkIdentityPositiveFile",
        async:false,
        data:{
            identityPositiveFileId:$("#identityPositiveFileId").val(),
            area:"CN"
        },
        success:function(data){
            if(data.state){
                $("#picIdentityNo").val(data.data.identno);
                $("#picUserName").val(data.data.realName);
            }
            result = data.state;
        },
        error:function(){
            result = false;
        }
    });
    return result;
}

function checkIdentityNegativeFile(){
    var result = false;
    doAjaxReq({
        url:globalReqUrl + "/msApp/checkRegister/checkIdentityNegativeFile",
        async:false,
        data:{
            identityNegativeFileId:$("#identityNegativeFileId").val(),
            area:"CN"
        },
        success:function(data){
            result = data.state;
        },
        error:function(){
            result = false;
        }
    });
    return result;
}

function checkIdentityNo(){
    var result = false;
    doAjaxReq({
        url:globalReqUrl + "/msApp/checkRegister/checkIdentityNo",
        async:false,
        data:{
            identityNo:$("#identno").val()
        },
        success:function(data){
            result = data;
        },
        error:function(){
            result = false;
        }
    });
    return result;
}

function checkIdentityNoOcrSame(element, param, field){
    var result = false;
    if(jQuery.trim($("#identno").val()).toLowerCase() == jQuery.trim($("#picIdentityNo").val()).toLowerCase()){
        result = true;
    }
    return result;
}

function checkRealNameOcrSame(element, param, field){
    var result = false;
    if(jQuery.trim($("#realName").val()) == jQuery.trim($("#picUserName").val())){
        result = true;
    }
    return result;
}

function changeCode() {
    var time = new Date();
    $("#validateCodeImg").attr("src",globalReqUrl + "/validateCode?t=" + time.getTime());
}


//客户端，调用js，向页面传输map获取的地址、经纬度
function getLatLonInfo(lat,lon,addr,province,city,county){
    $("#staLongitude").val(lon);
    $("#staLatitude").val(lat);
    $("#address").val(addr);

    $("#province").val(province);
    $("#city").val(city);
    $("#county").val(county);

}

//客户端，调用js，想页面传输道路资格证
function transportAgree(id,url) {
    $("#transportAgreeFileId").val(id);
    $("#transportAgreeUrl").val(url);
    $("#transportAgree").removeClass().addClass("upload-img car-img up-right");
    $("#transportAgree").css({
        "background":"url("+url+"?"+Math.random()+") center no-repeat",
        "background-position":"center",
        "background-size":"contain"
    })
}

//客户端，调用js，想页面传输 驾驶证 行驶证 货车照片1 货车照片2
function upFile(fileId,id,url) {
    if (fileId == "firstCarNoFile"){
        $("#firstCarNoFileId").val(id);
        $("#firstCarNoUrl").val(url);
        $('#firstCar').removeClass().addClass("upload-img car-img up-right");
        $('#firstCar').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }else if (fileId=="secondCarNoFile"){
        $("#secondCarNoFileId").val(id);
        $("#secondCarNoUrl").val(url);
        $('#secondCar').removeClass().addClass("upload-img car-img up-right");
        $('#secondCar').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }else if (fileId=="driverLicenseFile"){
        $("#driverLicenseFileId").val(id);
        $("#driverLicenseUrl").val(url);
        $('#driverLicense').removeClass().addClass("upload-img car-img up-right");
        $('#driverLicense').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }else if (fileId=="drivingLicenseFile"){
        $("#drivingLicenseFileId").val(id);
        $("#drivingLicenseUrl").val(url);
        $('#drivingLicense').removeClass().addClass("upload-img car-img up-right");
        $('#drivingLicense').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }else if (fileId=="identityPositiveFile"){
        $("#identityPositiveFileId").val(id);
        $("#identityPositiveUrl").val(url);
        $('#identityPositiveSelect').removeClass().addClass("upload-img up-right");
          $('#identityPositiveSelect').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }
    else if (fileId=="identityNegativeFile"){
        $("#identityNegativeFileId").val(id);
        $("#identityNegativeUrl").val(url);
        $('#identityNegativeSelect').removeClass().addClass("upload-img up-right");
        $('#identityNegativeSelect').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }else if (fileId=="identityHalfFile"){
        $("#identityHalfFileId").val(id);
        $("#identityHalfUrl").val(url);
        $('#identityHalfSelect').removeClass().addClass("upload-img up-right");
        $('#identityHalfSelect').css({
            "background":"url("+url+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        })
    }
}

function checkValShow (val,type,msg) {
    if(!checkVal(val,type)){
        showTips(msg);
        return true;
    }else{
        return false;
    }
}

function checkTruckCode(truckCode){
    var result = false;
    if(!truckCode || jQuery.trim(truckCode) == ""){
        return result;
    }
    doAjaxReq({
        url: globalReqUrl + "/msApp/mobileStation/apply/checkTruckCode"+ getToken("?"),
        data: {
            'truckcode': truckCode
        },
        'async': false,
        success: function (data) {
            result = data.data;
        },
        error:function(){
            result = false;
        }
    });
    return result;
}

function checkTime(){
    var start = $("#businessHoursStart").val();
    var end = $("#businessHoursEnd").val();
    if(start != "" && end != ""){
        var startPrefix = start.substr(0,2);
        var endPrefix = end.substr(0,2);
        if(startPrefix > endPrefix){
            return false;
        }else if(startPrefix == endPrefix){
            var startSuffix = start.substr(3,4);
            var endSuffix = end.substr(3,4);
            if(startSuffix > endSuffix){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }else{
        return false;
    }
}

function getToken(res){
	var access_token = res+"access_token="+JavaScriptObject.getAccessToken();
	return access_token;
}
	
function checkUserAuthentication(){
    var userInfoStatus = $("#userInfoStatus").val();
    if(userInfoStatus == undefined || userInfoStatus == '' || userInfoStatus==0 || userInfoStatus==1){
        if(checkValShow($('#identityPositiveFileId').val(),1,"请上传身份证正面照")){
            return false;
        }else{
            if(!checkIdentityPositiveFile()){
                showTips("身份证正面照识别失败，请确保照片真实、清晰");
                return false;
            }
        }

        if(checkValShow($('#identityNegativeFileId').val(),1,"请上传身份证反面照")){
            return false;
        }else{
            if(!checkIdentityNegativeFile()){
                showTips("身份证反面照识别失败，请确保照片真实、清晰");
                return false;
            }
        }
        if(checkValShow($('#identityHalfFileId').val(),1,"请上传手持身份证半身照")){
            return false;
        }

        var identno = $('#identno').val();
        if (identno==''){
            showTips("身份证不能为空");
            return false;
        }
        var realName = $('#realName').val();
        if (realName==''){
            showTips("真实姓名不能为空");
            return false;
        }
        if(!checkIdentityNoOcrSame()){
            showTips("身份证照片与身份证号不相符");
            return false;
        }
    }
    if(userInfoStatus == undefined || userInfoStatus == '' || userInfoStatus==0) {
        if(!checkIdentityNo()){
            showTips("身份证号已被注册");
            return false;
        }
        if(!checkIdVerify()){
            return false;
        }
    }
    return true;
}
function checkIdVerify() {
    var result = false;
    doAjaxReq({
        url:globalReqUrl + "/msApp/mobileStation/apply/checkIdVerify"+ getToken("?"),
        async:false,
        data:{
            identno:$("#identno").val(),
            realName:$('#realName').val()
        },
        success:function(data){
            if(!data.state){
                showTips(data.message);
                result = false;
            }else{
                result = true;
            }
        },
        error:function(){
            result = false;
        }
    });
    return result;
}

function addImage() {
    var state = $("#userInfoStatus").val();
    if(state == undefined || state == '' || state==0 || state==1){
        if($("#identityPositiveUrl").val() != ''){
            $('#identityPositiveSelect').css({
                "background":"url("+$("#identityPositiveUrl").val()+"?"+Math.random()+") center no-repeat",
                "background-position":"center",
                "background-size":"contain"
            });
            $('#identityPositiveSelect').removeClass().addClass("upload-img up-right");
        }
        if($("#identityNegativeUrl").val() != ''){
            $('#identityNegativeSelect').css({
                "background":"url("+$("#identityNegativeUrl").val()+"?"+Math.random()+") center no-repeat",
                "background-position":"center",
                "background-size":"contain"
            });
            $('#identityNegativeSelect').removeClass().addClass("upload-img up-right");
        }
        if($("#identityHalfUrl").val() != ''){
            $('#identityHalfSelect').css({
                "background":"url("+$("#identityHalfUrl").val()+"?"+Math.random()+") center no-repeat",
                "background-position":"center",
                "background-size":"contain"
            });
            $('#identityHalfSelect').removeClass().addClass("upload-img up-right");
        }
    }
    if($("#driverLicenseUrl").val() != ''){
        $('#driverLicense').css({
            "background":"url("+$("#driverLicenseUrl").val()+"?"+Math.random()+") center no-repeat",
            "background-position":"center",
            "background-size":"contain"
        });
        $('#driverLicense').removeClass().addClass("upload-img up-right");
    }
}