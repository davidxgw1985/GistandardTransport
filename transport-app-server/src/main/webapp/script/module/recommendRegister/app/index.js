/**
    http:localhost:8080/index.html?uptp=(1,2)
    uptp 参数   默认没有参数表示正常升级流程
                1：升级快递员
                2：升级司机
**/

$(function() {
    var index = {
        uperArr:['司机','快递员'],
        touperArr:['快递员','司机'],
        uptp:0,
        init: function() {
            this.initHtml();
            this.initEvent();
        },
        initHtml: function() {
            if(!getQueryString("uptp")){
                console.log(11);
                return;
            }
            // var uptp = parseInt(getQueryString("uptp"))-1;
            // this.uptp = uptp;
            // var that = $(".cou-img").eq(uptp);
            // var src = that.find("img").attr("src");
            // var index = src.lastIndexOf(".png");
            // var cdsrc = src.substring(0, index);
            // that.find("img").attr("src", cdsrc + "-mo.png");
            // that.next(".tp-mes").addClass("activecolor");
            // that.addClass("up-reqired-flag");
            // $(".tips-title").text("您已经是"+this.uperArr[uptp]).removeClass("none");
            // $(".tips-desc").text("您可以就继续申请成为"+this.touperArr[uptp]);

        },
        initEvent: function() {
            var that = this;
            /*商户类型选择 鼠标点下样式变换*/
            $(".cou-img").mousedown(function() {
                // if($(this).hasClass("up-reqired-flag")){
                //     return;
                // }
                // var src = $(this).find("img").attr("src");
                // var index = src.lastIndexOf(".png");
                // var cdsrc = src.substring(0, index);
                // $(this).find("img").attr("src", cdsrc + "-mo.png");
                // $(this).next(".tp-mes").addClass("activecolor");
            });

            /*商户类型选择 页面跳转*/
            $(".cou-img").click(function() {

                if($(this).hasClass("up-reqired-flag")){
                    showTips("您已经是"+that.touperArr[that.uptp])
                    return;
                }

                var delay = 1000;
                showTips("页面跳转");
                var type =($(this).parents('.con-type').index()-1);
                // type ： 0- 个人 2 -企业 next页面区分也跳转
                window.location.href = "register-explain.html?type=" + type;
            })

            $(".tp-goback").click(function() {
                window.history.go(-1);
            });
        }
    }

    index.init();
})