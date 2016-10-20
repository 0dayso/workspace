var loadinghtml = "";
function loadB2CPolicy() {
	if("1"==hclx) {
		$("#b2c_policy_div").html(loadinghtml+"正在加载C站信息,请稍后......")
		.load($ctx+"/vedsb/jzcp/b2ccp/b2cPolicy?ajax=true",{ddbh:ddbh,randid:new Date().getTime()},function(){loadDefultSeting(hkgs)});
	}else {
		$("#b2c_policy_div").html("");
	}
}
//调用代购系统接口
//自动代购
function autoorder(btn){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:'AIRB2C',platzcId:'B2CZC'},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _autoorder(btn);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,3,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _autoorder(btn);
			});
		}else{
			 _autoorder(btn);
		}
	});
}
function _autoorder(btn) {
	displayBtn("dgbutton",true);
	var autopay = "0";//自动支付   0 自动1手动
	var source = "0";//0-官网 1-淘宝 2-携程
	var zfzh = document.getElementById("b2b_zfzh").value;//支付账号
	var paymethod = document.getElementById("b2b_zflx").value;;//支付方式
	var json = eval("("+btn.getAttribute("json")+")");
	b2cSetCookie(json.hkgs+"_autopay_paymethod",paymethod,1);
	b2cSetCookie(json.hkgs+"_autopay_zfzh",zfzh,1);
	
	if(isBlank(zfzh)) {
		alert("请输入支付账号！");
		displayBtn("dgbutton",false);
		return false;
	}
	new $.ajax({url:$ctx+'/vedsb/b2bsz/jpb2bzfzh/check?randid='+new Date().getTime(),
		type: 'get',
		data: {zflx:paymethod,zfzh:zfzh,hkgs:json.hkgs,ddbh:json.ddbh},
		success: function(dataTxt){
		   json = eval("("+dataTxt+")");
		   var info = json.info;
		   if(!isBlank(info)){//支付账号有错误提示
		   		displayBtn("dgbutton",false);
			   	alert(info);
			   	return ;
		   }else{
		   		sendData(zfzh,source,autopay,paymethod,btn);
		   }
		}
	});
}
function order(btn){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:'AIRB2C',platzcId:'B2CZC'},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _order(btn);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,3,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _order(btn);
			});
		}else{
			 _order(btn);
		}
	});
}
function _order(btn) {
	displayBtn("dgbutton",true);

	var json = eval("("+btn.getAttribute("json")+")");
	var autopay = "1";//自动支付   0 自动1手动
	var source = "0";//0-官网 1-淘宝 2-携程
	var asmszfkm = document.getElementById("b2c_zf_fklx").value;//asms支付科目
	var hkgszfkm = document.getElementById("b2c_hs_fkkm").value;//hkgs支付科目
	
	b2cSetCookie(json.hkgs+"_pay_asmszfkm",asmszfkm,1);
	b2cSetCookie(json.hkgs+"_pay_hkgszfkm",hkgszfkm,1);
	
	if(isBlank(asmszfkm)){
		alert("请选择系统对应的支付科目");
		displayBtn("dgbutton",false);
		return false;
	}
	if(isBlank(hkgszfkm)){
		alert("请选择航司的支付方式");
		displayBtn("dgbutton",false);
		return false;
	}
	sendData(asmszfkm,source,autopay,hkgszfkm,btn);
}

function sendData(zfzh,source,autopay,paymethod,btn) {
	var kyye = parseFloat(document.getElementById("kyye").value);
	var yeinfo = parseInt(document.getElementById("yeinfo").value);
	
	if(yeinfo==0) {
		if(confirm("C站代购账户余额不足，请充值后刷新页面重试!\n是否前往充值页面进行充值?")) {
			var url='/asms/special/tjsq/find.shtml?p=toZxcz';
			vopen(url);
			displayBtn("dgbutton",false);
			return false;
		}
		displayBtn("dgbutton",false);
		return false;
	}else {
		var url = $ctx+"/vedsb/jzcp/b2ccp/order2dgxt?t="+new Date().getTime();
		var json = eval("("+btn.getAttribute("json")+")");
		$.ajax({url: url,
			type: 'post',
			data: {zfzh:zfzh,source:source,autopay:autopay,paymethod:paymethod,ddbh:json.ddbh,flightno:json.flightno,cabin:json.cabin,price:json.price,type:json.type,coupon:json.coupon},
			success: function(dataTxt){
			   alert(dataTxt);
			   if(dataTxt.indexOf("代购成功")>-1) {
				   b2cSetCookie("b2c_czdg","czdg",1);
			       //window.close();
			   }else {
			       displayBtn("dgbutton",false);
			   }
			}
		});
	}
}


function displayBtn(name,status) {
	var btn = document.getElementsByName(name);
	for(var i = 0 ; i <btn.length ; i++) {
		btn[i].disabled=status;
	}
}
//保存此次代购时选中的支付科目
function b2cSetCookie(name,value,num) {
    var date = new Date();
    date.setTime(date.getTime()+(1*24*60*60*1000));
    var expires = "; expires="+date.toGMTString();
	document.cookie = name+"="+escape(value)+expires+"; path=/";
}
function loadDefultSeting(hkgs) {
	var b2c_autopay_paymethod = getCookie_b2c(hkgs+"_autopay_paymethod");
	var b2c_autopay_zfzh = getCookie_b2c(hkgs+"_autopay_zfzh");
	var b2c_asmszfkm = getCookie_b2c(hkgs+"_pay_asmszfkm");
	var b2c_hkgszfkm = getCookie_b2c(hkgs+"_pay_hkgszfkm");   
//    alert(b2c_autopay_paymethod+"=="+b2c_autopay_zfzh+"=="+b2c_asmszfkm+"=="+b2c_hkgszfkm);
	var autopaymethod = document.getElementById("auto_paymethod");
	if(autopaymethod) {
		if(isBlank(b2c_autopay_paymethod) || "null"==b2c_autopay_paymethod){
			autopaymethod.value= "";
		}else {
			autopaymethod.value= b2c_autopay_paymethod;
		}
	}
	var auto_zfzh = document.getElementById("auto_zfzh");
	if(auto_zfzh) {
		if(isBlank(b2c_autopay_zfzh) || "null"==b2c_autopay_zfzh){
			auto_zfzh.value= "";
		}else {
			auto_zfzh.value= b2c_autopay_zfzh;
		};
	}
	var b2c_zf_fklx = document.getElementById("b2c_zf_fklx");
	if(b2c_zf_fklx) {
		if(isBlank(b2c_asmszfkm) || "null"==b2c_asmszfkm){
			b2c_zf_fklx.value= "";
		}else {
			b2c_zf_fklx.value= b2c_asmszfkm;
		};
	}
	var b2c_hs_fkkm = document.getElementById("b2c_hs_fkkm");
	if(b2c_hs_fkkm) {
		if(isBlank(b2c_hkgszfkm) || "null"==b2c_hkgszfkm){
			b2c_hs_fkkm.value= "";
		}else {
			b2c_hs_fkkm.value= b2c_hkgszfkm;
		};
	}
	
}
function getCookie_b2c(name) { 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return null; 
}

/*
 del head and end space
*/
function strTrim(str){
 return str.replace(/(^\s*)|(\s*$)/g, "");
}


function setSfxyh(){
	//设置协议号
	var yxyhStr = $("#dkhbm").val();
	if(yxyhStr==null){
		return ;
	}
	var yxyh = yxyhStr.split(",");
	$("#sfxyhsel option").remove();
	for(i=0;i<yxyh.length;i++){
		if(i==0) {
			$("#sfxyhsel").append("<option value=''>==请选择协议号==</option>");
		}
		$("#sfxyhsel").append("<option value='"+yxyh[i]+"'>"+yxyh[i]+"</option>");
	}
}

