/**
 http:localhost:8080/index.html?uptp=(1,2)
 uptp 参数   默认没有参数表示正常升级流程
 1：升级快递员
 2：升级司机
 **/

$(function() {
    var index = {
        uperArr:['','快递员','司机','咪站'],
        init: function() {
            this.initEvent();
        },
        initEvent: function() {
            var that = this;
            /*商户类型选择 鼠标点下样式变换*/
            $(".cou-img").mousedown(function() {
                if($(this).hasClass("up-reqired-flag")){
                    return;
                }
                var src = $(this).find("img").attr("src");
                var index = src.lastIndexOf(".png");
                var cdsrc = src.substring(0, index);
                $(this).find("img").attr("src", cdsrc + "-mo.png");
                $(this).next(".tp-mes").addClass("activecolor");
            });

            /*商户类型选择 页面跳转*/
            $(".cou-img").click(function() {

                if($(this).hasClass("up-reqired-flag")){
                    showTips("您已经是"+that.uperArr[$(this).attr("id")])
                    return;
                }

                var delay = 1000;
                showTips("页面跳转");
                // type ： 1- 快递员 2 -司机 next页面区分也main跳转
                window.location.href= globalReqUrl + "/msApp/mobileStation/apply/explain?type="+$(this).attr("id") + getToken("&");
                setTimeout(function() {
                }, delay);
            });

            $(".tp-goback").click(function() {
                if ($(this).attr("id") == "exit") {
                    JavaScriptObject.exitFinish(1);
                } else {
                    window.history.go(-1);
                }
            });
        }
    };

    index.init();
});