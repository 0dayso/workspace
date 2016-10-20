/**
  *method:调用的controller中的方法名
  *data ：请求参数,如{hkgs:'CZ',gngj:'1'}
  *callback : 异步请求成功后的回调函数
  *如果返回的数据是json格式字符串,可如下转换：var json=$.parseJSON(data);
  */
function getAjax(method,data,callback){
	var url = $ctx+"/vedsb/common/ajax/"+method;
	$.post(url, data,callback);
}
(function($){
	/**
	 *lb  'shyhb' 商户用户
	 *<input type="text" name="test" id="test"/>
	 *<input type="hidden" name="name" id="valueToInput"/>
	 *调用举例：$('#test').autocompleteDynamic('shyhb','valueToInput'); $('#test')为展示控件的input,valueToInput为赋值的input的ID
	 */
	$.fn.autocompleteDynamic=function(lb,inputcode){
		var title="";
		if("shyhb"==lb){
			title="可输商户用户编号/姓名";
			getAjax('genDataByLb',{lb:'shyhb'},dataCallBack);
		}
		var input=this;
		function dataCallBack(json){
			var data=$.parseJSON(json);
			input.autocompleteAll(inputcode,title,data);
		}
	}
	
})(jQuery);
/**
  *显示或影藏元素 lb 0显示，1影藏
  *trid显示或影藏的元素，多个用逗号拼接(可以不填)
  *inputId为需要清空值的以及去掉验证的元素id，多个逗号拼接(可以不填)
  */
function showOrHideElememt(lb,trid,inputId){
	var trids={};
	var inputIds={};
	if(!!trid){
		trids=trid.split(",");
	}
	if(!!inputId){
		inputIds=inputId.split(",");
	}
	if(lb=='0'){
		if(!$.isEmptyObject(trids)){
			$(trids).each(function(){
				$('#'+this).show();
			});
		}
		if(!$.isEmptyObject(inputIds)){
			$(inputIds).each(function(){
				$('#'+this).removeAttr('ignore');
			});
		}
	}else if(lb=='1'){
		if(!$.isEmptyObject(trids)){
			$(trids).each(function(){
				$('#'+this).hide();
			});
		}
		if(!$.isEmptyObject(inputIds)){
			$(inputIds).each(function(){
				$('#'+this).attr('ignore','ignore').val('');
			});
		}
	}
}

// --- 设置cookie
function setCookie(name,value,days) {
	if (days) {
	    var date = new Date();
	    date.setTime(date.getTime()+(days*24*60*60*1000));
	    var expires = "; expires="+date.toGMTString();
	}else {
		expires = "";
	}
	document.cookie = name+"="+value+expires+"; path=/";
}
//--- 获取cookie
function getCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
	    var c = ca[i];
	    while (c.charAt(0)==' ') c = c.substring(1,c.length);
	    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function getCjrlxImg(cjrlx){
	if ('1' == cjrlx) {
		document.write("<img src='"+$ctx+"/static/images/dd_cr.gif' style='vertical-align:middle'>");
	} else if ('2' == cjrlx) {
		document.write("<img src='"+$ctx+"/static/images/dd_et.gif' style='vertical-align:middle'>");
	} else if ('3' == cjrlx) {
		document.write("<img src='"+$ctx+"/static/images/dd_ye.gif' style='vertical-align:middle'>");
	}
}