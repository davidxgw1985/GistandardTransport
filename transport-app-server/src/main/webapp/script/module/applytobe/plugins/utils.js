
//判断手机号underfind
var is_undefined_null = function(data) {
	if (data == null || data.result == false) {
		return data;
	}
	if (data.pt <= 0) {
		return data;
	}
	data.ad = DataNum(data.ad);
	data.ph = DataNum(data.ph);
	data.rn = data.rn ? data.rn : '';
	data.rn = data.rn != 'undefined' ? data.rn : '';

	function DataNum(Num) {
		Num = Num ? Num : '';
		Num = Num == "undefined" ? '' : Num;
		return Num;
	}

	return data;
};
/**
 * 判断是否数据类型
 * @param {Object} s 
 */
var is_Num = function(s) {
	if (s != null && s != "") {
		return !isNaN(s);
	}
	return false;
}

var checkVal = function(val,type){
	val = val+"";
	type = type?type:1;
	switch (type){
		case 1:
			if(!val || val.trim()=="" || val.trim()=="undefined" || val.trim().length<=0){
				return false;
			}
			return true;
			break;
		case 2:
			 if(!/^\d{11}$/.test(val)) {
				return false;
			}
			return true;
			break;
		case 3:
			if (!/(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val)) {
                return false;
            };
            return true;
			break;
		case 4:
			if(!/^[0-9]+$/.test(val)){
		        return false;
		    }
			return true;
			break;
		case 5:
			if(/^(:?(:?\d+.\d+)|(:?\d+))$/.test(val)) {
				return true;
			}
			return false;
			break;
		default:
			if(!val || val.trim()=="" || val.trim()=="undefined" || val.trim().length<=0){
				return false;
			}
			return true;
	}
	return false;
}


/**
 * 获取链接参数
 * @param {Object} name:字段
 */
var getQueryString = function(name) {
	var currentSearch = decodeURIComponent(location.search.slice(1));
	if (currentSearch != '') {
		var paras = currentSearch.split('&');
		for (var i = 0, l = paras.length, items; i < l; i++) {
			items = paras[i].split('=');
			if (items[0] === name) {
				return items[1];
			}
		}
		return '';
	}
	return '';
};




/**
 * 
 * ajax跨域请求
 * @param {Object} url :请求接口
 * @param {Object} data :接口参数 {key:value,...}
 * @param {Object} callback 接口回调函数 callBackxxxHandle
 * @param {Object} showloading 是否显示loading等待框
 * @param {Object} $target 指定元素容器内
 * @param {Object} isAsync 是否异步请求
 */
var getResult = function(url, data, callback, showloading, $target, isAsync) {
	if (showloading) {
		showLoading($target);
	}
	$.ajax({
		type: 'GET',
		async: typeof isAsync === 'undefined' ? false : isAsync,
		url: domain_url + url,
		data: data,
		dataType: "jsonp",
		jsonp: callback,
		complete: function() {
			if (showloading) {
				hideLoading($target);
			}
		},
		success: function(data) {}
	});
};
/**
 * StringBuffer 追加字符串
 * @param {Object} tpl 字符串
 */
var simpleTpl = function( tpl ) {
    tpl = $.isArray( tpl ) ? tpl.join( '' ) : (tpl || '');

    return {
        store: tpl,
        _: function() {
            var me = this;
            $.each( arguments, function( index, value ) {
                me.store += value;
            } );
            return this;
        },
        toString: function() {
            return this.store;
        }
    };
};

/**
 * 统一时间格式 yyyy/mm/dd hh:ii:ss
 * @param {Object} str时间字符串 2016-03-02 11:00:00
 */
var str2date = function(str) {
	str = str.replace(/-/g, '/');
	return new Date(str);
};

/**
 * 获取毫秒数
 * @param {Object} str 日期时间字符串
 */
var timestamp = function(str) {
	var timestamp = Date.parse(str2date(str));
	return timestamp;
};



/**
 * 时间比较
 * @param {Object} 
 *  beginTime,endTime  yy-mm-dd hh:mm:ss
	return -1   beginTime > endTime
	return 0    beginTime = endTime
	return 1    beginTime < endTime
 * 
 */
function comptime(beginTime, endTime) {
	var beginTimes = beginTime.substring(0, 10).split('-');
	var endTimes = endTime.substring(0, 10).split('-');
	beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + beginTime.substring(10, 19);
	endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
	var a = (timestamp(endTime) - timestamp(beginTime)) / 3600 / 1000;
	if (a < 0) {
		return -1;
	} else if (a > 0) {
		return 1;
	} else if (a == 0) {
		return 0;
	} else {
		return -2
	}
}


/**
 * 格式化时间
 * @param {Object} TimeMillis 毫秒数
 * @param {Object} format 时间格式 yyyy-mm-dd hh:ii:ss
 */
var dateFormat = function(TimeMillis, format) {
	var data = is_Num(TimeMillis) ? new Date(TimeMillis) : new Date(TimeMillis.replace(/-/g, '/'));
	var year = data.getFullYear(); //获取年
	var month = data.getMonth() >= 9 ? (data.getMonth() + 1).toString() : '0' + (data.getMonth() + 1); //获取月
	var day = data.getDate() > 9 ? data.getDate().toString() : '0' + data.getDate(); //获取日
	var hours = data.getHours() > 9 ? data.getHours().toString() : '0' + data.getHours();
	var minutes = data.getMinutes() > 9 ? data.getMinutes().toString() : '0' + data.getMinutes();
	var second = data.getSeconds() > 9 ? data.getSeconds().toString() : '0' + data.getSeconds();
	var format = format ? format : "yyyy-mm-dd hh:ii:ss",

		formatDate = format.replace(/yyyy/i, year).replace(/mm/i, month).replace(/dd/i, day)
		.replace(/hh/i, hours).replace(/ii/i, minutes).replace(/ss/i, second);
	return formatDate;

};
/**
 * 將毫秒轉化為yyyy-MM-dd HH:mm:ss格式的日期时间
 * @param {Object} TimeMillis ：毫秒
 */
var timeTransform = function(TimeMillis) {
	var data = new Date(TimeMillis.replace(/-/g, '/'));
	var year = data.getFullYear(); //获取年
	var month = data.getMonth() >= 9 ? (data.getMonth() + 1).toString() : '0' + (data.getMonth() + 1); //获取月
	var day = data.getDate() > 9 ? data.getDate().toString() : '0' + data.getDate(); //获取日
	var hours = data.getHours() > 9 ? data.getHours().toString() : '0' + data.getHours();
	var minutes = data.getMinutes() > 9 ? data.getMinutes().toString() : '0' + data.getMinutes();
	var ss = data.getSeconds() > 9 ? data.getSeconds().toString() : '0' + data.getSeconds();
	var time = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + ss;
	return time;
};

/**
 * 格式化时间
 * @param {Object} stamp
 * @param {Object} format
 * @param {Object} zero
 */
var normalDate = function(stamp, format, zero) {
	var stamp = Number(stamp),
		date = new Date(stamp),
		formatDate,
		format = format ? format : "yyyy-mm-dd hh:ii:ss",
		zero = (zero === undefined) ? true : zero,
		dateNum = function(num) {
			return num < 10 ? '0' + num : num;
		},
		_d = zero ? dateNum : function(s) {
			return s;
		};

	var year = _d(date.getFullYear()),
		month = _d(date.getMonth() + 1),
		day = _d(date.getDate()),
		hour = _d(date.getHours()),
		minute = _d(date.getMinutes()),
		second = _d(date.getSeconds());

	formatDate = format.replace(/yyyy/i, year).replace(/mm/i, month).replace(/dd/i, day)
		.replace(/hh/i, hour).replace(/ii/i, minute).replace(/ss/i, second);
	return formatDate;
};

/**
 * 返回给定范围内的随机数
 * @param {Object} min 起始值
 * @param {Object} max 结束值
 */
function getRandomArbitrary(min, max) {
	return parseInt(Math.random() * (max - min) + min);
}



/**
 * 安卓机型判断 
 */
var is_android = function() {
	var ua = navigator.userAgent.toLowerCase();
	return ua.indexOf("android") > -1;
};

//获取安卓系统版本号
var getAndroidVer = function(){
	if (/Android (\d+\.\d+)/.test(navigator.userAgent)) {
  		var version = parseFloat(RegExp.$1); 
  		return version;
  	}else{
  		return -1;
  	}
}

/**  JS 原生扩展     **/
/**
 * 时间函数扩展
 * @param {Object} format 格式化时间类型
 */
Date.prototype.dataFormat = function(format) {
	var tm = this.getTime();
	dateFormat(TimeMillis, format);
};

/**
 * String类型扩展：替换所有字符串
 * @param {Object} s1
 * @param {Object} s2
 */
String.prototype.replaceAll = function(s1, s2) { //replaceAll替换所有
	//	console.log(this.toString());
	return this.replace(new RegExp(s1, "gim"), s2);
}

var showDialog = function ($container, tips) {
    var t = simpleTpl(),
        $container = $container || $('body'),
        $loading = $container ? $container.find('#gis_loading') : $('body').children('#gis_loading'),
        tips = tips || '是否允许获取位置...';

    if ($loading.length > 0) {
        $loading.remove();
    };
    t._('<div class="showTips">')
        ._('<div class="st-content" style="margin-top:'+(($(window).height()-300)/2)+'px">')
            ._('<p class="st-title">提&nbsp;示</p>')
            ._('<p class="st-tips">'+tips+'</p>')
            ._('<div class="st-btn">')
            	._('<div class=" btn">')
            	    ._('<div class="btn-confirm">确&nbsp;定</div>')
            	._('</div>')
            	._('<div class=" btn">')
            	    ._('<div class="btn-cancle">取&nbsp;消</div>')
            	._('</div>')
            ._('</div>')
        ._('</div>')
      ._('</div>')
    $container.append(t.toString());
    $(".btn-confirm").unbind("click").click(function(){
    	$(".showTips").remove();
    	return true;
    });
    $(".btn-cancle").unbind("click").click(function(){
    	$(".showTips").remove();
    	return false;
    })
};

var showLoading = function ($container, tips, iste) {
	var t = simpleTpl(),
		$container = $('body'),
		$loading = $('body').children('#gis_loading'),
		$backdrop = $('body').children('.gis-popup-backdrop'),
		tips = tips || '努力加载中...';

	if ($loading.length > 0) {
		$loading.show();
		$backdrop.show();
		return true;
	}
	t._('<section id="gis_loading" class="gis-loading">')
		._('<section class="gis-loading-logo">')
		._('<div class="gis-logo logo1"></div>')
		._('<div class="gis-logo logo2"></div>')
		._('<div class="gis-logo logo3"></div>')
		._('<div class="gis-logo logo4"></div>')
		._('<div class="gis-logo logo5"></div>')
		._('<div class="gis-logo logo6"></div>')
		._('</section>')
		._('<section class="gis-loading-tips">' + tips + '</section>')
		._('</section>')
		._('<section class="gis-popup-backdrop">')
		._('</section>')
	$container.append(t.toString());
	iste > 1000 ? (setTimeout(function () {
		hideLoading();
	}, iste + 350)) : "";
	return true;
};

var hideLoading = function () {

	$('body').children('#gis_loading').hide();
	$('body').children('.gis-popup-backdrop').hide();

}