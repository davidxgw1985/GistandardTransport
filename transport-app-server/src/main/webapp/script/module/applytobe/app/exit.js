$(function(){
    var desc = {
        init:function(){
            this.initEvent();
        },
        initEvent:function(){
            $('#exit').click(function () {
                JavaScriptObject.exitFinish(1);
            })
        }
    }

    desc.init();
    JavaScriptObject.exitReturn();
})
