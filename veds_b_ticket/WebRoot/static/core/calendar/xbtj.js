//不适用日期格式验证
function bsyrqValidate(obj){
	
	var _value = $(obj).val();
	
	if(_value == "") return true;
	
	if(_value.indexOf("|") == -1){	
		if(_value.indexOf("/") == -1){
			return _dateValid(_value);
		}else{
			var _arr = _value.split("/");
			for(var i=0;i<_arr.length;i++){
				if(!_dateValidXg(_arr[i])){
					return false;
				}
			}
		}
	}else{
		var _arr = _value.split("|");
		
		if(_value.indexOf("/") == -1){
			if(!_dateValidHg(_value)){
				return false;						
			}
		}else{
			
			var _arrT = _value.split("/");
			for(var i=0;i<_arrT.length;i++){
				if(_arrT[i].indexOf("|") == -1){
					if(!_dateValidXg(_arrT[i])){
						return false;
					}
				}else{
					if(!_dateValidHg(_arrT[i])){
						return false;						
					}
				}
			}
		}
	}
	
	return true;
}

//单独的斜杠[/]格式验证
function _dateValidXg(_value){
	if(_value == "") return true;
	
	if(_value != "" && !_dateValid(_value)){
		return false;
	}
	
	return true;
}

//单独的竖杠[|]格式验证
function _dateValidHg(_value){
	
	if(_value == "") return true;
	
	var _arr = _value.split("|");
	
	if(_arr.length != 2) return false;
	
	var begin = _arr[0];
	var end   = _arr[1];
	
	if(begin != "" && !_dateValid(begin)){//单个日期格式验证
		return false;
	}
	
	if(end != "" && !_dateValid(end)){//单个日期格式验证
		return false;
	}
	
	//比较大小
	return $.dateDiff(begin,end) > 0;
}
//单个日期格式验证
function _dateValid(_value){
	var reg = /^\d{4}[\/\-](0[1-9]|1[012])[\/\-](0[1-9]|[12][0-9]|3[01])$/;
	return (_value.replace(reg, "").length == 0);
}

//不适用日期选择控件专用函数
function _bsyrqFocus(obj){
	
	$('#cpsytj_bsyrqDiv').show();
	
	var _cfrqs = $("#c_cjrqs").val();
	var _cfrqz = $("#c_cprqz").val();
	
    WdatePicker({eCont:'cpsytj_bsyrqDiv',
	    onpicked:function(dp){
			
	    	var _valueBef = $("#tjzcForm_jp_jzyxcp_cpsytj_bsyrq").val();
	    	
	    	if(_valueBef != "" && _valueBef.indexOf("|") > -1){
				_valueBef = _getBsyrqStrByHg(_valueBef);
			}
			
			var _newDateStr = dp.cal.getNewDateStr();
				
			if(_valueBef != "") {
				if(_valueBef.indexOf(_newDateStr) == -1){
					$("#tjzcForm_jp_jzyxcp_cpsytj_bsyrq").val(_bsyrqOrder(_valueBef,_newDateStr));
				}else{
				
					_valueBef += "/";
					
					_valueBef = _valueBef.replace(_newDateStr + "/","");
					
					if(_valueBef.lastIndexOf("/") == _valueBef.length-1){
						_valueBef = _valueBef.substring(0,_valueBef.length-1);
					}
				
					$("#tjzcForm_jp_jzyxcp_cpsytj_bsyrq").val(_valueBef);
				}
			}else{
				$("#tjzcForm_jp_jzyxcp_cpsytj_bsyrq").val(_newDateStr);
			}
		
	    },minDate:_cfrqs,maxDate:_cfrqz
	});
}

//当不适用日期的输入框点击时，让cpsytj_bsyrqDiv隐藏
$(document).click(function(e){ 
	if(window.event.srcElement.id=='tjzcForm_jp_jzyxcp_cpsytj_bsyrq')return;
	$('#cpsytj_bsyrqDiv').hide();
})


//获取不适用日期字符串
function _getBsyrqStrByHg(_value){

	if(_value.indexOf("|") == -1) return _value;
	
	var _arr = _value.split("|");
	
	if(_value.indexOf("/") == -1){
		return _getBsyrqDetail(_value);
	}else{
	
		var _retStr = "";
		
		var _arrT = _value.split("/");
		for(var i=0;i<_arrT.length;i++){
			if(_arrT[i].indexOf("|") == -1){
				_retStr += _arrT[i] + "/";
			}else{
				_retStr += _getBsyrqDetail(_arrT[i]);	
			}
		}
		
		if(_retStr.lastIndexOf("/") == _retStr.length-1){
			_retStr = _retStr.substring(0,_retStr.length-1);
		}
		
		return _retStr;
	}
}
//获取竖线区间所有的日期字符串
function _getBsyrqDetail(_value){
	var _arrT2 = _value.split("|");
	
	var begin = _arrT2[0];
	var end   = _arrT2[1];
	
	var days = parseInt($.dateDiff(begin,end),10);
	
	var _retStr = "";
				
	for(var k=1;k<=days+1;k++){
		_retStr += getNewD(begin,k).split(" ")[0] + "/";
	}
	
	return _retStr;
}

//不适用日期重新排序
function _bsyrqOrder(_value,_valNew){

	var _arrT2 = _value.split("/");
	
	var _retStr = "";
	
	var _isSpell = false;
	
	var _len = _arrT2.length;
	
	for(var i=0;i<_len;i++){
		if(!_isSpell){
			if((parseInt($.dateDiff(_valNew,_arrT2[i]),10) > 0)){//如果当前值大于传入的值
				_retStr += _valNew +"/" + _arrT2[i] +"/";
				_isSpell = true;
			}else if(i==_len-1 && parseInt($.dateDiff(_valNew,_arrT2[i]),10) < 0){//如果是最后一次循环，并且当前值小于传入的值
				_retStr += _arrT2[i] +"/" + _valNew +"/";
				_isSpell = true;
			}else{
				_retStr += _arrT2[i] +"/";
			}
		}else{
			_retStr += _arrT2[i] +"/";
		}
	}
	
	if(_retStr.lastIndexOf("/") == _retStr.length-1){
		_retStr = _retStr.substring(0,_retStr.length-1);
	}
	
	return _retStr;
}
