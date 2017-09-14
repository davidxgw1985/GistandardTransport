$(function() {
    var register = {
        proId:"",
        cityId:"",

        init: function() {
            this.initEvent();
            this.initCity();
            this.initHtml();
            changeCode();
        },
        initHtml:function(){
            var down_url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.transport.mobilestation";
            if(is_android()){
                $(".btn-down-app").removeClass("none").find("span").addClass("andr").text("下载Android版本");
                down_url="http://a.app.qq.com/o/simple.jsp?pkgname=com.transport.mobilestation";
            }else{
                $(".btn-down-app").removeClass("none").find("span").addClass("ios").text("下载IOS版本");
                down_url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.transport.mobilestation";
            }
            $(".btn-down-app").click(function(){
                window.location.href=down_url;
            });
        },
        initEvent: function() {
            //返回上个页面
            $(".tp-goback").on('touchstart click',function(e){
                if(typeof JavaScriptObject !="undefined"){
                    JavaScriptObject.FinshBack();
                }else{
                    window.history.go(-1);
                }
            });
            //账号八位数点击 - 弹窗
            /*$("#login-num").click(function() {
                var that = $(this);
                doAjaxReq({
                    url:globalReqUrl + "/msApp/checkRegister/o2IdLayer",
                    data:{
                    },
                    success:function(data){
                        var html = "";
                        for(var i = 0;i < data.length;i++){
                            html +=('<li class="">')
                            html +=('<input type="radio" name="userNo" value="' + data[i].accountId + '">')
                            html +=('<span>' + data[i].accountValue +'</span>')
                            html +=('</li>')
                        }
                        showDialogEndNum(null,html,
                            function() { //确定的回调
                                var rad = $(".showDialog input[type='radio']:checked");
                                if (rad.length < 1) {
                                    showTips("请选择一个八位数");
                                    return false;
                                }
                                var val = rad.next("span").text();
                                that.val(val);
                                $("#accountId").val(rad.val());
                                $(".showDialog").remove();
                                return true;
                            },
                            function() { //取消的回调
                                $(".showDialog").remove();
                                return false;
                            }
                        );
                    },
                    error:function(){
                        result = false;
                    }
                });

            });*/

            //page：个人用户注册/账号设置  提交按钮
            $(".btn-sets-submit").click(function() {
                window.location.href = "register-success.html"
            });
                //选择框checkbox
            $(".mycheckbox").click(function() {
                if ($(this).hasClass("ckbox-active")) {
                    $(this).removeClass("ckbox-active")
                } else {
                    $(".ckbox-active").removeClass("ckbox-active") && $(this).addClass("ckbox-active");
                }
            });

            $(".btn-register-next").click(function() {
                if (!$(".exp-green").hasClass("ckbox-active")) {
                    showTips("请同意服务条款");
                    return;
                }
                window.location.href = globalReqUrl + "/msApp/recommendRegister/personRegister";
            });


            //个人用户注册/实名认证 提交
            $(".btn-regauth-next").click(function() {
                    window.location.href = "register-personal-sets.html?tp=1";
            });
            //个人用户注册/实名认证 提交
            $(".btn-comauth-next").click(function() {
                    window.location.href = "register-company-sets.html?tp=2";
            });
            //input text文本输入框转date输入框
            $(".text2date").change(function(e) {
                $(this).removeAttr('placeholder');
                $(".tenz").val($(this).val());
            });


            $(".btn-person-first-submit").click(function() {
                var id = $(this).attr("id");
                if (id !="person-first-submit"){
                    return false;
                }
                var bol = "";
                $('.i_required').each(function () {
                    var msg = $(this).attr("placeholder");
                    bol = checkValShow($(this).val(),1,msg);
                    if(bol){
                        return false;
                    }
                });

                if(bol){
                    return false;
                }
                if(!checkTelephone()){
                    showTips("手机号已被注册");
                    return;
                }
                if(!checkValidateCode($('#picValidateCode').val())){
                    showTips("图片验证码错误");
                }else{
                    if(checkVal($('#county').val(),1)){
                        $('#provinceAndCity_sel').val($('#county').val());
                        $('#provinceAndCity').val($('#province').find("option:selected").text()
                        + "-" + $('#city').find("option:selected").text() + "-" + $('#county').find("option:selected").text());
                    }else{
                        $('#provinceAndCity_sel').val($('#city').val());
                        $('#provinceAndCity').val($('#province').find("option:selected").text()
                        + "-" + $('#city').find("option:selected").text());
                    }

                    $('#person-first-fill').submit();
                }
            });

            $(".btn-person-second-submit").click(function() {

                var id = $(this).attr("id");
                if (id !="person-second-submit"){
                    return false;
                }
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

                var bol = "";
                $('.i_required').each(function () {
                    var msg = $(this).attr("placeholder");
                    bol = checkValShow($(this).val(),1,msg);
                    if(bol){
                        return false;
                    }
                });

                if(bol){
                    return false;
                }

                if(!checkIdentityNo()){
                    showTips("身份证号已被注册");
                    return false;
                }

                if(!checkIdentityNoOcrSame() || !checkRealNameOcrSame()){
                    showTips("与身份证照片上的信息不相符");
                    return false;
                }

                $('#person-second-fill').submit();
            });

            $(".btn-person-third-submit").click(function() {
                var id = $(this).attr("id");
                if (id !="person-third-submit"){
                    return false;
                }

                var bol = "";
                $('.i_required').each(function () {
                    var msg = $(this).attr("placeholder");
                    bol = checkValShow($(this).val(),1,msg);
                    if(bol){
                        return false;
                    }
                });

                if(checkValShow($('#acctPassword').val(),6,"密码格式不正确")){
                    return false;
                }

                if(jQuery.trim($('#acctPassword').val()) != jQuery.trim($('#confirmPassword').val())){
                    showTips("密码不一致");
                    return false;
                }

                $('#person-third-fill').submit();
            });

            $(".btn-company-submit").click(function() {
                var delay = 0;
                setTimeout(function() {
                    window.location.href = "register-company-auth.html";
                }, delay);
            });

            //企业管理员新增input按钮
            $(".ctr-add").click(function(){
                $(".ctr-del").removeClass("none");
                var len = $(".item-admin-sets").length;
                len >=2 ? ( $(".ctr-add").addClass("none") ): ( $(".ctr-add").removeClass("none") );
                var t = simpleTpl();
                t._('<li class="item-mes item-admin-sets cf ">')
                   ._('<p>设置企业管理员'+(len+1)+'</p>')
                 ._('</li>')
                 ._('<li class="item-inp item-o2id ">')
                   ._('<input type="text" placeholder="管理员的O2ID(必填)"  name="">')
                 ._('</li>')
                 ._('<li class="item-inp item-username">')
                   ._('<input type="text" placeholder="真实姓名(必填)"  name="">')
                 ._('</li>')
                 ._('<li class="item-inp item-cardnum">')
                   ._('<input type="text" placeholder="身份证号码(必填)"  name="">')
                 ._('</li>')
                $(".item-btn-ctr").before(t.toString());
            });
            $(".ctr-del").click(function(){
                var len = $(".item-admin-sets").length;
                if(len<=1){
                    showTips("企业管理员至少为一个!");
                    return false;
                }else{
                    $(".ctr-add").removeClass("none");
                    $(".item-admin-sets:last").nextUntil(".item-btn-ctr").remove();
                    $(".item-admin-sets:last").remove();
                }
            });

            //验证码刷新
            $('#validateCodeImg').click(function () {
                changeCode();
            });
        },
        getCity4Proid:function(city){
            return city.provinceId == register.proId;
        },
        getAre4CityId:function(area){
            return area.cityId == register.cityId;
        },
        initCity: function() {
            // console.log(globalCityData.cities.filter(register.getCity4Proid));
            if ($('#province').length < 1) {
                return;
            }
            if( globalProvinceData && globalProvinceData.success){
                var provinceHtml = "<option value=''>居住省份(必填)</option>";
                for (var i in globalProvinceData.provinces) {
                    var provinces = globalProvinceData.provinces[i];
                    provinceHtml += "<option value='" + provinces.id + "'>" + provinces.provinceName + "</option>";
                }
                $('#province').html(provinceHtml);

            }
            $('#province').change(function(data) {
                var cityHtml = "<option value=''>居住城市(必填)</option>";
                register.proId = $(this).val();
                var cityDatas = globalCityData.cities.filter(register.getCity4Proid);
                for (var i in cityDatas) {
                    cityHtml += "<option value='" + cityDatas[i].id + "'>" + cityDatas[i].name + "</option>";
                }
                $('#city').html(cityHtml);
                $('#area').html("<option value=''>居住区县(必填)</option>");
            });
            $('#city').change(function(data) {
                var areaHtml = "<option value=''>居住区县(必填)</option>";
                register.cityId = $(this).val();
                var areaDatas = globalAreaData.areas.filter(register.getAre4CityId);
                for (var i in areaDatas) {
                    areaHtml += "<option value='" + areaDatas[i].id + "'>" + areaDatas[i].areaName + "</option>";
                }
                $('#county').html(areaHtml);
            });
            return;
        }
    };

    register.init();

    $('.i_required').blur(function () {
        var msg = $(this).attr("placeholder");
        checkValShow($(this).val(),1,msg);
    });

    if(checkVal($("#errorInfo").val(),1)){
        showTips($("#errorInfo").val());
    }

    $("#getTelMsg").bind("click",getTelMsgClick);
    $("#getValidateCode").show();
    $("#getValidateCode2").hide();

    //给选择文件按钮添加点击事件
    $("div[id$='Select']").each(function(index, obj){
        var objId = $(this).attr("id");
        var fileObjId = objId.replace("Select","File");
        $(this).click(function(){
            $("#" + fileObjId).trigger('click');
        });

        $('#' + fileObjId).fileupload({
            replaceFileInput: false,
            dataType: 'json',
            url: globalReqUrl + '/msApp/checkRegister/fileUpload',
            sequentialUploads: true,
            done: function (e, data) {
            },
            add: function (e, data) {
                $.each(data.files, function (index, file) {
                    var fileName = file.name;
                    var fileId =  objId.replace("Select","");
                    if (!fileCheck(fileName, fileId)) {
                        return;
                    }
                    $('#' + fileObjId).fileupload('send', {files: data.files})
                        .success(function (result, textStatus, jqXHR) {
                            if(!result.state){
                                showTips(result.message);
                            }
                            else {
                                $("#" + fileId + "Img").attr("src", globalStaticUrl + result.message);
                                $("#" + fileId + "Url").val(globalStaticUrl + result.message);
                                $("#" + fileId + "FileId").val(result.data);
                                $("#"+ objId).attr("class","upload-img up-right");
                                $("#"+ objId).css({
                                    "background":"url("+ globalStaticUrl + result.message +") center no-repeat"
                                });
                            }
                        })
                        .error(function (jqXHR, textStatus, errorThrown){
                            showTips("上传失败");
                        });
                });
            }
        });
    });
    $("#telephone").blur(function(){
        if(!checkTelephone()){
            showTips("手机号已被注册");
        }
    });
});

function checkValShow (val,type,msg) {
    if(!checkVal(val,type)){
        showTips(msg);
        return true;
    }else{
        return false;
    }
}

var leftSeconds = 60;
function getTelMsgClick(){
    if(!checkVal($('#telephone').val(),1)){
        showTips("手机号不能为空");
        return;
    }
    if(!checkVal($('#picValidateCode').val(),1)){
        showTips("图片验证码不能为空");
        return;
    }
    if(!checkValidateCode($('#picValidateCode').val())){
        showTips("图片验证码错误");
        changeCode();
        return;
    }else{
        $("#getTelMsg").unbind("click");
        doAjaxReq({
            url:globalReqUrl + "/msApp/checkRegister/sendTelMessage",
            data:{
                telephone:$('#telPrefix').val() + $('#telephone').val()
            },
            success:function(data){
                if(data.retCode==1){
                    setTimeout(reGetTelMsg(),1000);
                }else{
                    $("#getTelMsg").bind("click",getTelMsgClick);
                    showTips(data.retMsg);
                }
            },
            error:function(){
                showTips("获取短信验证码失败");
                $("#getTelMsg").bind("click",getTelMsgClick);
            }
        });
    }
}

function reGetTelMsg(){
    if( leftSeconds == 0){
        leftSeconds = 60;
        $('#getTelMsg').removeAttr("disabled");
        $("#getValidateCode").show();
        $("#getValidateCode2").hide();
        $("#getTelMsg").bind("click",getTelMsgClick);
    }
    else {
        $("#getTelMsg").attr("disabled","disabled");
        $("#getValidateCode").hide();
        $("#getValidateCode2").show();
        $("#seconds").text(leftSeconds);
        leftSeconds --;
        setTimeout(reGetTelMsg,1000);
    }
}

function checkValidateCode(code){
    var result = false;
    doAjaxReq({
        url: globalReqUrl + "/checkValidateCode",
        'async': false,
        data: {
            'validateCode': code
        },
        success: function (data) {
            if (data.state){
                result = true;
            }
        }
    });
    return result;
}

function changeCode() {
    var time = new Date();
    $("#validateCodeImg").attr("src",globalReqUrl + "/validateCode?t=" + time.getTime());
}

function fileCheck(fileName,fileId) {
    var fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
    fileType = fileType.toLowerCase();
    if (!(fileType && /^(jpg|png|jpeg)$/.test(fileType))){
        showTips("请上传jpg、png、jpeg格式文件");
        return false;
    }
    else {
        return true;
    }
}

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

function checkTelephone(){
    var result = false;
        doAjaxReq({
            url:globalReqUrl + "/msApp/checkRegister/checkTelephone",
            async:false,
            data:{
                telephone:$("#telephone").val(),
                acctType: 1
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