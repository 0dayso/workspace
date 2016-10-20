//展示更多价格
function showmore(obj,className){
	var show=$(obj).attr('show');
	if(show=='0'){//原来是影藏，则是做展开操作
		$(obj).removeClass('down_arrow').addClass('up_arrow').attr({show:'1'});
		$('.'+className).show();
	}else{//原来是展开，则是做收起操作
		$(obj).removeClass('up_arrow').addClass('down_arrow').attr({show:'0'});
		$('.'+className).hide();
	}
	
}

//-------------------平台下单开始-----------------------------
//平台下单前检查
function orderPlat(ptzcbs,platzcId,jsj,policytype){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:ptzcbs,platzcId:platzcId},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        isAutoPay(ptzcbs,platzcId,jsj,result.autopay,policytype);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,3,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		       isAutoPay(ptzcbs,platzcId,jsj,result.autopay,policytype);
			});
		}else{
			isAutoPay(ptzcbs,platzcId,jsj,result.autopay,policytype);
		}
	});
}
//是否自动支付
function isAutoPay(ptzcbs,platzcId,jsj,ptSfzddk,policytype){
	if(isZddkFlag=='1' && ptSfzddk=='1'){
		var confl=$.layer({
	    	 area: ['340px', '150px'],
	    	 closeBtn:false,
	         dialog: {
	             msg: "平台开启了自动支付，如果自动支付点击【确定】,如果手动支付点击【取消】？",
	             btns: 2,                    
	             type: 4,
	             btn: ['确定','取消'],
	             yes: function(){
	             	layer.close(confl);
	             	toOrder(ptzcbs,platzcId,jsj,'1',policytype);
	             },no: function(){
	             	toOrder(ptzcbs,platzcId,jsj,'0',policytype);
	             }
	         }
		});
	}else{
		toOrder(ptzcbs,platzcId,jsj,'0',policytype);
	}
}
//下单到平台
var layerOrder;
function toOrder(ptzcbs,platzcId,jsj,isZddk,policytype){
	var url=$ctx+'/vedsb/jzcp/platpolicy/platOrder?ddbh='+ddbh+'&platcode='+ptzcbs+'&policyId='+platzcId+'&jsj='+jsj+'&isZddk='+isZddk+'&policytype='+policytype;
	layerOrder=$.layer({
		type: 2,
		title: ['平台出票'],
		area: ['520px', '260px'],
		iframe: {src: url}
    });
}
function toPlatPay(url){
	layer.close(layerOrder);
	window.open(url);
}
//-------------------平台下单结束-----------------------------
//-------------------淘宝下单开始----------------------------
//淘宝下单前检查
function orderTb(ptzcbs,platzcId,jsj){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:ptzcbs,platzcId:platzcId},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        toTbOrder(ptzcbs,platzcId,jsj);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,3,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        toTbOrder(ptzcbs,platzcId,jsj);
			});
		}else{
			toTbOrder(ptzcbs,platzcId,jsj);
		}
	});
}
function toTbOrder(ptzcbs,platzcId,jsj){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/platpolicy/tbOrder',{ddbh:ddbh,platcode:ptzcbs,policyId:platzcId,jsj:jsj},
		function(result){
			layer.close(ii);
			if('0'== result.status){
				layer.msg('淘宝代购下单成功',3,1);
			}else if('-1'== result.status){
				layer.alert(result.error,8);
			}
		}
	);
}
//-------------------淘宝下单结束-----------------------------
//-------------------CPS下单开始----------------------------
//CPS下单前检查
function orderCps(ptzcbs,platzcId,jsj,zdj,zclx,policytype){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/cpmain/checkBeforeOrder',{ddbh:ddbh,ptzcbs:ptzcbs,platzcId:platzcId},function(result){
		layer.close(ii);
		if('1'== result.status){
			var msg=result.error+'，是否继续下单？';
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		    	isCpsAutoPay(ptzcbs,platzcId,jsj,zdj,zclx,result.autopay,policytype);
			});
		}else if('-1'== result.status){
			layer.msg(result.error,2,3);
		}else if('-2'==result.status){
			var msg=result.error;
			var confl=layer.confirm(msg,function(){
				layer.close(confl);
		        isCpsAutoPay(ptzcbs,platzcId,jsj,zdj,zclx,result.autopay,policytype);
			});
		}else{
			isCpsAutoPay(ptzcbs,platzcId,jsj,zdj,zclx,result.autopay,policytype);
		}
	});
}
//是否自动支付
function isCpsAutoPay(ptzcbs,platzcId,jsj,zdj,zclx,ptzddk,policytype){
	if(isZddkFlag=='1' && ptzddk=='1'){
		var confl=$.layer({
	    	 area: ['340px', '150px'],
	    	 closeBtn:false,
	         dialog: {
	             msg: "平台开启了自动支付，如果自动支付点击【确定】,如果手动支付点击【取消】？",
	             btns: 2,                    
	             type: 4,
	             btn: ['确定','取消'],
	             yes: function(){
	             	layer.close(confl);
	             	toCpsOrder(ptzcbs,platzcId,jsj,'1',zdj,zclx,policytype);
	             },no: function(){
	             	toCpsOrder(ptzcbs,platzcId,jsj,'0',zdj,zclx,policytype);
	             }
	         }
		});
	}else{
		toCpsOrder(ptzcbs,platzcId,jsj,'0',zdj,zclx,policytype);
	}
}
//下单到CPS
function toCpsOrder(ptzcbs,platzcId,jsj,isZddk,zdj,zclx,policytype){
	var ii = layer.load('系统正在处理您的操作,请稍候!');
	$.getJSON($ctx+'/vedsb/jzcp/platpolicy/cpsOrder',{ddbh:ddbh,platcode:ptzcbs,policyId:platzcId,jsj:jsj,isZddk:isZddk,zclx:zclx,zdj:zdj,policytype:policytype},
		function(result){
			layer.close(ii);
			if('0'== result.status){
				if("1"==result.zfzt){
					layer.msg('下单到CPS成功，请等待出票。',3,1);
					return true;
				}
				if("true"==result.jezq || true==result.jezq){
					layer.msg('下单到CPS成功，请及时支付。',3,1);
					window.open(result.payurl);
				}else{
					var msg='下单到CPS成功,系统金额'+result.jsj+'与平台应付金额'+result.payfee+'不一致，是否继续支付？'
					var conf=layer.confirm(msg,function(){
						window.open(result.payurl);
						layer.close(conf);
					});
				}
			}else if('-1'== result.status){
				layer.alert(result.error,8);
			}
		}
	);
}
//-------------------CPS下单结束----------------------------



function loadOpStauts(){
	$('#platstatus').load($ctx+'/vedsb/jzcp/platpolicy/loadOpStatus?ajax=true',{ddbh:ddbh},function(){
		window.setTimeout(loadOpStauts,20000);
	});
}
//该选择的平台设置背景
function setBgColorToSelPt(ptzcbs){
	$("#ptfilter_list_div span.current").removeClass("current");
	$("#ptzcbs_"+ptzcbs+"_span").addClass("current");
}
//获得当前着色平台的平台编号
function getCurrentSelPt(){
	return currentSpans=$("#ptfilter_list_div span.current").attr('ptzcbs');
}
//切换显示各个平台的政策
function changeAnyPlat(obj){
	var ptzcbs=$(obj).attr("ptzcbs");
	//处理选中底色
	setBgColorToSelPt(ptzcbs);
	if(ptzcbs=="all"){//如果切换的是全部,重新获取
		loadOpPolicy();
	}else{
		if(ptzcbs=="10100000"){
			loadCps();
		}else if(ptzcbs=="10100011"){
			loadTb();
		}else{
			loadOneOp(ptzcbs);
		}
	}
}

//加载全部平台政策
function loadOpPolicy(){
	var cpsSpan=$('#ptzcbs_10100000_span');
	var platCode="";
	var platName="";
	if(cpsSpan){//cps开启了
		platCode="10100000";
		platName='CPS';
	}
	var tbSpan=$('#ptzcbs_10100011_span');
	if(tbSpan){//tb开启了
		if(!platCode){
			platCode='10100011';
			platName='淘宝';
		}else{
			platCode+=","+'10100011';
			platName+=","+'淘宝';
		}
	}
	$('#ptfilter_list_div input[name="ptzcbs_chk"]:checked').each(function(){
		if(!platCode){
			platCode=$(this).val();
			platName=$(this).attr('platName');
		}else{
			platCode+=","+$(this).val();
			platName+=","+$(this).attr('platName');
		}
	});
	if(!platCode){
		return false;
	}
	var hbmCk=$('#filter_change_pnr_chk').attr('checked');
	var tjCk=$('#filter_thzc_chk').attr('checked');
	var sfglHbm=hbmCk ? '1' : '0';
	var sfglTj=tjCk ? '1' :'0';
	$("#op_policy_div").html("正在加载【<span style='color:red'>"+platName+"</span>】的政策，请稍后......")
		.load($ctx+'/vedsb/jzcp/platpolicy/searchPolicy?ajax=true',{ddbh:ddbh,platCode:platCode,sfglHbm:sfglHbm,sfglTj:sfglTj});
}
//加载cps政策
function loadCps(){
	var hbmCk=$('#filter_change_pnr_chk').attr('checked');
	var tjCk=$('#filter_thzc_chk').attr('checked');
	var sfglHbm=hbmCk ? '1' : '0';
	var sfglTj=tjCk ? '1' :'0';
	var platCode="10100000";
	$("#op_policy_div").html("正在加载【<span style='color:red'>CPS</span>】的政策，请稍后......")
		.load($ctx+'/vedsb/jzcp/platpolicy/searchPolicy?ajax=true',{ddbh:ddbh,platCode:platCode,sfglHbm:sfglHbm,sfglTj:sfglTj});
}
//加载淘宝政策
function loadTb(){
	var hbmCk=$('#filter_change_pnr_chk').attr('checked');//换编码
	var tjCk=$('#filter_thzc_chk').attr('checked');//特惠
	var sfglHbm=hbmCk ? '1' : '0';
	var sfglTj=tjCk ? '1' :'0';
	var platCode="10100011";
	$("#op_policy_div").html("正在加载【<span style='color:red'>淘宝</span>】的政策，请稍后......")
		.load($ctx+'/vedsb/jzcp/platpolicy/searchPolicy',{ddbh:ddbh,platCode:platCode,sfglHbm:sfglHbm,sfglTj:sfglTj});
}
//加载单个平台政策
function loadOneOp(platCode){
	var isChecked=$('#ptzcbs_'+platCode+'_chk').attr('checked');
	var hbmCk=$('#filter_change_pnr_chk').attr('checked');
	var tjCk=$('#filter_thzc_chk').attr('checked');
	var sfglHbm=hbmCk ? '1' : '0';
	var sfglTj=tjCk ? '1' :'0';
	if(!isChecked){
		$("#op_policy_div").html("<span style='color:red'>该平台未勾选，请先勾选！</span>");
		return false;
	}
	var platName=$('#ptzcbs_'+platCode+'_chk').attr('platname');
	$("#op_policy_div").html("正在加载【<span style='color:red'>"+platName+"</span>】的政策，请稍后......")
		.load($ctx+'/vedsb/jzcp/platpolicy/loadOneOp?ajax=true',{ddbh:ddbh,platCode:platCode,sfglHbm:sfglHbm,sfglTj:sfglTj});
}
