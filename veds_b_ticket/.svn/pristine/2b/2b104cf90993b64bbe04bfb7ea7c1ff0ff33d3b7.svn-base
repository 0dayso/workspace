//网页地址
var _purl=$ctx+"/vedsb/jzcp/b2bcp";

waitInfo = function(info){
	if($("#b2b_info_div") && $("#b2b_info_div")[0]){
		$("#b2b_info_div").html(info);
	}
	//按钮禁止
}

cleanInfo = function(){
	if($("#b2b_info_div") && $("#b2b_info_div")[0]){
		$("#b2b_info_div").html("");
	}
}

//获取验证码
loadValidatenoImg = function(imgObj, ddbh,hkgs,hszh){
	waitInfo('正在获取验证码');
	$.post(_purl+"/getYzmImage",{ddbh:ddbh,hkgs:hkgs,hszh:hszh},function(result){
		cleanInfo();
		var error = result.error;
		var img = result.result;
		if(error != null && error != ""){
	   	    layer.msg('获取验证码失败'+error, 2, -1); //2秒后自动关闭，-1代表不显示图标
		}else{
			 $(imgObj).attr('src',$ctx+img);
		}
	});
}
//提交登陆
b2bLogin = function(ddbh,hkgs,hszh,hsmm,yzm){
	waitInfo('正在登陆航空公司');
	$.post(_purl+"/b2bLogin",{ddbh:ddbh,hkgs:hkgs,hszh:hszh,hsmm:hsmm,yzm:yzm},function(result){
		cleanInfo();
		var error = result.error;
		var rst = result.result;
		if(error != null && error != ""){
			 layer.msg('登陆航空公司失败'+error, 2, -1); 
	   	    //重新刷新验证码
	   	    loadValidatenoImg($("#validatenoImg"),ddbh,hkgs,hszh);
		}else{
			//转到导航页面
			indexB2bPage(ddbh,$("#b2b_policy_div"));
		}
	});	
}
//提交登陆
b2bLogout = function(ddbh,hkgs,hszh){
	waitInfo('正在退出登陆航空公司');
	$.post(_purl+"/b2bLogout",{ddbh:ddbh,hkgs:hkgs,hszh:hszh},function(result){
		cleanInfo();
		var error = result.error;
		var rst = result.result;
		 //转到导航页面
		indexB2bPage(ddbh,$("#b2b_policy_div"));
	});	
}
//导航页面
indexB2bPage = function(ddbh,divObj){
	if(divObj && divObj[0]){
	   $(divObj).html("正在加载B2B出票信息...").load(_purl+"/index?ajax=true",{ddbh:ddbh},function(){});
	}
}

//--- 设置cookie
function setCookie_b2b(name,value,minute) { 
	if (minute) {
	    var date = new Date();
	    date.setTime(date.getTime()+(minute*60*1000));
	    var expires = "; expires="+date.toGMTString();
	}else {
		expires = "";
	}
	document.cookie = name+"="+value+expires+"; path=/";
}

//
quickcp = function(ddbh){
	$.layer({
		  type : 2,
          title: '',
          shadeClose: true,
          maxmin: true,
          fix : false,  
          area: ['1024px', 500], 
	    iframe: {src: _purl+'/b2bmain?ddbh='+ddbh}
	}); 
}

//账号密码下拉时触发
function sethkgszh(zhselect){
	for(i=0;i<zhselect.length;i++){
		if(zhselect[i].selected){
			$("#hszh").val(zhselect[i].text);
			$("#hsmm").val(zhselect[i].value);
			$("#office").val(zhselect[i].getAttribute("officeno"));
		}
	}
}


//登陆B2B提取政策
function b2bLoginPage(ddbh,hkgs) {
	var hszh = $("#hszh").val();
	var hsmm = $("#hsmm").val();
	if(hszh==""){
		alert("登陆账号不能为空！");
		$("#username").focus();
		return false;
	}
	if(hsmm==""){
		alert("登陆密码不能为空！");
		$("#hsmm").focus();
		return false;
	}
	var yzm = "";
	if($("#yzm")){
		yzm = $("#yzm").val()
		if(yzm==""){
			alert("验证码不能为空！");
			$("#yzm").focus();
			return false;
		}
	}
	var office="";
	if($("#office")){
		office = $("office").val();
	}
	var sfxyh ="";
	if($("#sfxyh")){
 		sfxyh = $("#sfxyh").val();
	}
	b2bLogin(ddbh,hkgs,hszh,hsmm,yzm);	 
}


function orderb2b(ddbh,payhand){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:'AIRB2B',platzcId:'B2BZC'},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _orderb2b(ddbh,payhand);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,3,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        _orderb2b(btn);
			});
		}else{
			_orderb2b(ddbh,payhand);
		}
	});
}
function payb2b(ddbh,payhand){
	displayBtn("dgbutton",true);
	var b2bpolicyid="";
	var zflx = $("#b2b_zflx").val();
	if(isBlank(zflx)){
	   	alert("请选择一种支付方式.");
	   	$("#b2b_zflx").focus();
	   	displayBtn("dgbutton",false);
	    return false;
	}
   	var sfxyh ="";
	if($("#sfxyh")&&$("#sfxyh")[0]){
   		sfxyh = $("#sfxyh").val();
	}
	var ifzhzg = "";
	if($('#ifzhzg:checked').size()>0){
		ifzhzg = "1";
	}
   if(payhand!="1"){
   	  var b2bzfzh_ = $("#b2b_zfzh").val();
   	  if(isBlank(b2bzfzh_)){
   		alert("请输入支付账号");
   		displayBtn("dgbutton",false);
	    return false;
	  }else{
		new $.ajax({url:$ctx+'/vedsb/b2bsz/jpb2bzfzh/check?randid='+new Date().getTime(),
			type: 'post',
			data: {zflx:zflx,zfzh:b2bzfzh_},
			success: function(dataTxt){
			   json = eval("("+dataTxt+")");
			   var info = json.info;
			   if(!isBlank(info)){//支付账号有错误提示
			   		displayBtn("dgbutton",false);
				   	alert(info);
				   	return false;
			   }else{
			   		var zfzhid = json.zfzhid;
			   		sendDataB2bPay(ddbh,zfzhid,"0",zflx,b2bpolicyid,ifzhzg,sfxyh);
			   }
			}
		});
	 }  
   }else{
   		if($("#b2b_zf_fklx").val()==""){
 			alert("手动支付时请选择一个与系统对应的支付科目，以方便自动转机票。");
 			$("#b2b_zf_fklx").focus();
 			displayBtn("dgbutton",true);
 			return false;
 		}
 		//手工支付要传系统支付科目，方便后续操作，这里存zfzhid节点
 		var zfzhid = $("#b2b_zf_fklx").val();
 	  	sendDataB2bPay(ddbh,zfzhid,"1",zflx,b2bpolicyid,ifzhzg,sfxyh);
   }
   //setDefaultB2b_zf_fklx(hkgs);
}
//
function _orderb2b(ddbh,payhand){
	displayBtn("dgbutton",true);
	var b2bpolicyid="";
	var objZc=document.getElementsByName("zcradio");
	if(objZc){
		var boolChecked=false;
		for(var i=0;i<objZc.length;i++){
			if(objZc[i].checked==true){
				b2bpolicyid=objZc[i].value;
				boolChecked=true;
				break;
			}
		}
		if(boolChecked==false){
			alert("请选择一条政策");
			displayBtn("dgbutton",true);
			return false;
		}
	}else{
	    displayBtn("dgbutton",true);
		return false
	}
	var zflx = $("#b2b_zflx").val();
	if(isBlank(zflx)){
	   	alert("请选择一种支付方式.");
	   	$("#b2b_zflx").focus();
	   	displayBtn("dgbutton",false);
	    return false;
	}
   	var sfxyh ="";
	if($("#sfxyh")){
   		sfxyh = $("#sfxyh").val();
	}
	var ifzhzg = "";
	if($('#ifzhzg:checked').size()>0){
		ifzhzg = "1";
	}
   if(payhand!="1"){
   	  var b2bzfzh_ = $("#b2b_zfzh").val();
   	  if(isBlank(b2bzfzh_)){
   		alert("请输入支付账号");
   		displayBtn("dgbutton",false);
	    return false;
	  }else{
		  debugger;
		new $.ajax({url:$ctx+'/vedsb/b2bsz/jpb2bzfzh/check?randid='+new Date().getTime(),
			type: 'post',
			data: {zflx:zflx,zfzh:b2bzfzh_},
			success: function(dataTxt){
			   json = eval("("+dataTxt+")");
			   var info = json.info;
			   if(!isBlank(info)){//支付账号有错误提示
			   		displayBtn("dgbutton",false);
				   	alert(info);
				   	displayBtn("dgbutton",true);
				   	return false;
			   }else{
			   		var zfzhid = json.zfzhid;
			   		sendDataB2b(ddbh,zfzhid,"0",zflx,b2bpolicyid,ifzhzg,sfxyh);
			   }
			}
		});
	 }  
   }else{
   		if($("#b2b_zf_fklx").val()==""){
 			alert("手动支付时请选择一个与系统对应的支付科目，以方便自动转机票。");
 			$("#b2b_zf_fklx").focus();
 			displayBtn("dgbutton",true);
 			return false;
 		}
 		//手工支付要传系统支付科目，方便后续操作，这里存zfzhid节点
 		var zfzhid = $("#b2b_zf_fklx").val();
 	  	sendDataB2b(ddbh,zfzhid,"1",zflx,b2bpolicyid,ifzhzg,sfxyh);
   }
   //setDefaultB2b_zf_fklx(hkgs);
}
//手动支付或只获得票号保存asms系统的支付方式
function setDefaultB2b_zf_fklx(hkgs){
	var xt_b2b_zf_fklx = hkgs+"_b2b_zf_fklx";
	var xt_b2b_zflx = hkgs+"_b2b_zflx";
	var xt_b2b_zfzh = hkgs+"_b2b_zfzh";
	var v = "";var v2="";var v3 = "";
	if($("#b2b_zf_fklx")) {
		v = $("#b2b_zf_fklx").val();
	}
	if($("#b2b_zflx")) {
		v2 = $("#b2b_zflx").val();
	}
	if($("#b2b_zfzh")) {
		v3 = $("#b2b_zfzh").val();
	}
	setCookie(xt_b2b_zf_fklx,v,360);
	setCookie(xt_b2b_zflx,v2,360);
	setCookie(xt_b2b_zfzh,v3,360);
}
function getDefaultB2b_zf_fklx(hkgs){
	var xt_b2b_zf_fklx = hkgs+"_b2b_zf_fklx";
	var xt_b2b_zflx = hkgs+"_b2b_zflx";
	var xt_b2b_zfzh = hkgs+"_b2b_zfzh";
	var v = getCookie_b2c(xt_b2b_zf_fklx);
	var v2 = getCookie_b2c(xt_b2b_zflx);
	var v3 = getCookie_b2c(xt_b2b_zfzh)
	if(!isBlank(v)&&$("#b2b_zf_fklx")){
		$("#b2b_zf_fklx").val(v);
	}
	if(!isBlank(v2)&&$("#b2b_zflx")){
		$("#b2b_zflx").val(v2);
	}
	if(!isBlank(v3)&&$("#b2b_zfzh")){
		$("#b2b_zfzh").val(v3);
	}
}
function sendDataB2bPay(ddbh,zfzhid,payhand,zflx,b2bpolicyid,ifzhzg,sfxyh){
	setCookie_b2b("asms_b2b_isopen","1",60);
	var url = _purl+"/b2bpay?ajax=true&t="+new Date().getTime();
	//	window.open(url+"?ddbh="+ddbh+"&zfzhid="+zfzhid+"&payhand="+payhand+"&zflx="+zflx+"&b2bpolicyid="+b2bpolicyid+"&ifzhzg="+ifzhzg+"&sfxyh="+sfxyh);
	$("#b2b_policy_div").html("正在支付...").load(url,{ddbh:ddbh,zfzhid:zfzhid,payhand:payhand,zflx:zflx,b2bpolicyid:b2bpolicyid,ifzhzg:ifzhzg,sfxyh:sfxyh},function(){});
}
function sendDataB2b(ddbh,zfzhid,payhand,zflx,b2bpolicyid,ifzhzg,sfxyh){
	setCookie_b2b("asms_b2b_isopen","1",60);
	var url = _purl+"/b2bPnr2Db?t="+new Date().getTime();
	$.ajax({url: url,
		type: 'post',
		data: {ddbh:ddbh,zfzhid:zfzhid,payhand:payhand,zflx:zflx,b2bpolicyid:b2bpolicyid,ifzhzg:ifzhzg,sfxyh:sfxyh},
		success: function(result){
		   var error = result.error;
		   var rst = result.result;
		   if(rst =="0") {
		       layer.msg('提交代购成功', 2, -1);
		       indexB2bPage(ddbh,$("#b2b_policy_div"));
		   }else {
		   	   layer.msg('提交代购失败 '+error, 2, -1);
		   }
		 //转到导航页面
		   indexB2bPage(ddbh,$("#b2b_policy_div"));
		   displayBtn("dgbutton",false);	
		}
	});
}
function strTrim(str){
 return str.replace(/(^\s*)|(\s*$)/g, "");
}
function isBlank(s){
	if(s==null){
		return true;
	}
	return strTrim(s)=="";
}
function displayBtn(name,status) {
	var btn = document.getElementsByName(name);
	for(var i = 0 ; i <btn.length ; i++) {
		btn[i].disabled=status;
	}
}