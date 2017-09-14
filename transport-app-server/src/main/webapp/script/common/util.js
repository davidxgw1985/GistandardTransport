/**
 * @Description
 * @author yujie
 * @Version 1.0
 * @Date 2015-06-30
 */

/**
 * 系统ajax请求封装
 * @param options
 */
function doAjaxReq(options) {
    var defaultAjaxOptions = {
        'type': 'post',
        'cache': false,
        'async': true,
        'dataType': 'JSON',
        'data':{}
    };

    if (!options  || !options.url) return;
    var realOptions = $.extend(true,{}, defaultAjaxOptions, options);
    realOptions.url = realOptions.url.indexOf(globalReqUrl) == 0 ? realOptions.url : (globalReqUrl + realOptions.url) ;
    var _succFunc = realOptions.success;
    var _errFunc = realOptions.error;
    realOptions.success = function (ret) {
        if (_succFunc)  _succFunc.call(this, ret);
    };
    realOptions.error = function (jqXHR) {
        if (_errFunc) {
            _errFunc.call(this, jqXHR);
        }
        else {

        }
    };
    $.ajax(realOptions);
}

function doTokenAjax(options){
    var getTokenOption = {
        'type': 'post',
        'url':"/token/getToken",
        'cache': false,
        'async': false,
        success:function(result){
            if(result.data){
                var token = result.data;
                if(!options.data){
                    options.data = {};
                }
                options.data.token = token;
                doAjaxReq(options);
            }
        }
    };
    if(options.token){
        doAjaxReq(getTokenOption);
    }
    else {
        doAjaxReq(options);
    }
}

/**
 *解决IE8环境下不支持indexOf问题
 **/
if (!Array.prototype.indexOf)
{
    Array.prototype.indexOf = function(elt /*, from*/)
    {
        var len = this.length >>> 0;

        var from = Number(arguments[1]) || 0;
        from = (from < 0)
            ? Math.ceil(from)
            : Math.floor(from);
        if (from < 0)
            from += len;

        for (; from < len; from++)
        {
            if (from in this &&
                this[from] === elt)
                return from;
        }
        return -1;
    };
}