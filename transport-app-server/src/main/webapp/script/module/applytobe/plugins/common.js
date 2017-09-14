var __ns = function( fullNs ) {
    var nsArray = fullNs.split( '.' );
    var evalStr = '';
    var ns = '';
    for ( var i = 0, l = nsArray.length; i < l; i++ ) {
        i !== 0 && ( ns += '.' );
        ns += nsArray[i];
        evalStr += '( typeof ' + ns + ' === "undefined" && (' + ns + ' = {}) );';
    }
    evalStr !== '' && eval( evalStr );
}
var __noop = function() {};
var W = W || window;
__ns('H');

/** 弹出提示 start **/
var aniTrue = true;
var showTips = function(word, pos, timer) {
	if (aniTrue) {
		aniTrue = false;
		var pos = pos || '2',
		timer = timer || 1500;
		$('body').append('<div class="tips none"></div>');
		$('.tips').css({
			'position': 'fixed' ,
			'max-width': '80%' ,
			'top': '60%' ,
			'left': '50%' ,
			'z-index': '99999999' ,
			'color': 'rgb(255, 255, 255)' ,
			'padding': '20px 10px' ,
			'border-radius': '5px' ,
			'margin-left': '-120px' ,
			'background': 'rgba(0, 0, 0, 0.8)' ,
			'font-size':'22px',
			'text-align': 'center'
		});
		$('.tips').html(word);
		var winW = $(window).width(),
			winH = $(window).height();
		$('.tips').removeClass('none').css('opacity', '0');
		var tipsW = $('.tips').width(),
			tipsH = $('.tips').height();
		$('.tips').css({'margin-left': -tipsW/2,'top':(winH - tipsH)/(pos - 0.2)}).removeClass('none');
		$('.tips').animate({
			'opacity': '1',
			'top': (winH - tipsH)/pos}, 300, function() {
				setTimeout(function() {
					$('.tips').animate({'opacity':'0'}, 300, function() {
						$('.tips').addClass('none').css('top', (winH - tipsH)/(pos - 0.2));
					});
				}, timer);
				setTimeout(function() {
					$('.tips').remove();
					aniTrue = true;
				}, timer + 350);
		});
	};

};
/** 弹出提示 end **/
var str2date = function(str) {
	str = str.replace(/-/g, '/');
	return new Date(str);
};

var timestamp = function(str) {
	return Date.parse(str2date(str));
};






