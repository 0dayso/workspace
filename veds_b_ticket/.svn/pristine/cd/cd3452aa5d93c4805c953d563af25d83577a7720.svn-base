var keypressCnt=0;//回车键连续按键次数
var kzdy="";//行程单内容是否可修改
var currenttkno="";//当前点击的行程单票号
var xcdprinttype="1";//行程单打印方式 默认1单张连打

//对pnr转大写
 function toUpperCaseo(o){
    var value = o.value;
    o.value = value.toUpperCase();
 }


//设置行程单失败状态和提示信息
function setXcdztSpanInnerHTML(title,tipinfo,tknoTemp){
	$("spanxcdzt"+tknoTemp).innerHTML=title;
	$("spanxcdzt"+tknoTemp).title=tipinfo;
	if(!isBlank(tipinfo)){
		$("spanxcdzt"+tknoTemp).className="red";
		$("spanxcdzt"+tknoTemp).style.textDecoration="line-through";
	}else{
		$("spanxcdzt"+tknoTemp).className="";
		$("spanxcdzt"+tknoTemp).style.textDecoration="";
	}
	/**
	if(!isBlank(tipinfo)){
		alert(tipinfo);
	}
	**/
}

//票号全选 
function selAll(obj){
	var selOneObj=document.getElementsByName("selOne");
	for(var i=0;i<selOneObj.length;i++){
		if(selOneObj[i].jppzzt=="3"){
			if(obj.checked==true){
				selOneObj[i].checked=true;
			}else{
				selOneObj[i].checked=false;
			}
		}
	}
}



//创建时票号和行程单号信息验证
function valiOnCreate(tknoTemp){
	if($("xcdzt"+tknoTemp).value=="3"){
		var xcdhold=$("xcdhold"+tknoTemp).value;
		setXcdztSpanInnerHTML("已创建","该票号已创建过行程单:行程单号:"+xcdhold,tknoTemp);
		return false;
	}
	if(tknoTemp.length!=13){
		var tipinfo="票号"+tknoTemp+"不正确,正确的票号应该是13位数字";
		setXcdztSpanInnerHTML("创建失败",tipinfo,tknoTemp);
		return false;
	}
	var xcdhTemp=""; 
	if($("xcdh"+tknoTemp)){
		xcdhTemp=$("xcdh"+tknoTemp).value;
		if(isBlank(xcdhTemp)){
			var tipinfo="票号"+tknoTemp+"没有对应的行程单号";
			setXcdztSpanInnerHTML("创建失败",tipinfo,tknoTemp);
			return false;
		}
	}
	if(xcdhTemp.length!=10){
		var tipinfo="行程单号"+xcdhTemp+"不正确,正确的行程单号应该是10位数字";
		setXcdztSpanInnerHTML("创建失败",tipinfo,tknoTemp);
		return false;
	}
	if(asmsAndagent=="agent"){//***分销特有*** 国际行程单能否创建和打印  0标示不能打印 空或1标示能打
		var jphcglgj=$("jphcglgj"+tknoTemp).value;
		if("0"==nfdygjxcd&&jphcglgj=="0"){
			var tipinfo="票号"+tknoTemp+"属于国际票,部门权限已限制不可创建国际行程单，如需创建，请联系客服人员！";
			setXcdztSpanInnerHTML("权限不足",tipinfo,tknoTemp);
			return false;
		}
	}
	setXcdztSpanInnerHTML("等待创建","",tknoTemp);
	return true;
}
//开启或禁用页面所有按钮 type 1 开启 0 禁用
function disabledButton(type){
	var inputNodes=document.getElementsByTagName("input");
	for(var i=0;i<inputNodes.length;i++){
		if(inputNodes[i].type=="button"||inputNodes[i].type=="radio"){
			if(type==1){
				inputNodes[i].disabled=false;
			}else{
				inputNodes[i].disabled=true;
			}
		}
	}
}

//验证行程单信息 type=2 时提示信息已title显示
function validateXcd(tkno,type){
		var xcdzt=$("xcdzt"+tkno).value;
		
		var preVar="票号"+tkno;
		if("2"==$("tkno_or_cjr").value){//乘机人
			preVar="该乘机人行程单";
		}
		if(kzdy!="1"){//不可修改时
			if(xcdzt!="3"){
				var title=preVar+"还未在航信创建行程单";
				if(type=="2"){
					setXcdztSpanInnerHTML("不能打印",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
		}
		var formObj= $("xcdform"+tkno);
    	if(formObj){
    		if(isBlank($("xcdhTemp").value)){
				var title=preVar+"上对应的行程单号为空,请核实并补填后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(isBlank(formObj.lkxm.value)){
				var title=preVar+"上对应的旅客姓名为空,请核实并补填后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}	
			/**外入票号或B2B的票可能没有编码或证件号码 此处验证忽略
			if(isBlank(formObj.zjhm.value)){
				var title=preVar+"的上的有效身份证件号码为空,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}	
			if(isBlank(formObj.pnr_no.value)){
				var title=preVar+"的上的订座记录为空,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}	
			**/
			if(!isNumber(formObj.pj_zdj.value)){
				var title=preVar+"上对应的票价必须是数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(!isNumber(formObj.pj_jsf.value)){
				var title=preVar+"上对应的机建必须是数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if( formObj.pj_tax.value.replace(/(^\s*)|(\s*$)/g,"") != "EXEMPT" && !isNumber(formObj.pj_tax.value)){
				var title=preVar+"上对应的税费必须是数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(!isNumber(formObj.pj_qt.value)){
				var title=preVar+"上对应的其他金额必须是数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			calPjhj();
			if(!isNumber(formObj.pj_hj.value)){
				var title=preVar+"上对应的合计金额必须是数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(isBlank(formObj.tkno.value)){
				var title=preVar+"上对应的电子客票号码为空,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}	
			if(formObj.tkno.value.length!=13&&formObj.tkno.value.length!=16){
	    		var title=preVar+"上对应的电子客票号码必须是13位客票号或16位的连续客票号,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
    		}
    		if(formObj.tkno.value.length==16){
    			var title=preVar+"上对应的电子客票号码必须是13位客票号或16位的连续客票号,连续客票以'/'或'-'分割,请核实并修正后打印";
    			if(formObj.tkno.value.indexOf("/")!=13&&formObj.tkno.value.indexOf("-")!=13){
    				if(type=="2"){
						setXcdztSpanInnerHTML("数据错误",title,tkno);
					}else{
						alert(title);
					}
					return false;
    			}
    		}
    		
			if(!isInteger(formObj.tkno.value.replace("/","").replace("-",""))){
	    		var title=preVar+"上对应的电子客票号码必须是数字,连续客票以'/'或'-'分隔,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
    		}
    		if(isBlank(formObj.yzm.value)){
				var title=preVar+"上对应的验证码不能为空,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(isBlank(formObj.yzm.value)){
				if(!isBlank($("xcdhTemp").value)&&$("xcdhTemp").value.length==10){
					formObj.yzm.value=$("xcdhTemp").value.substring(6);
				}
			}
			if(!isNumber(formObj.yzm.value)||formObj.yzm.value.length!=4){
				var title=preVar+"上对应的验证码必须是四位数值,请核实并修正后打印";
				if(type=="2"){
					setXcdztSpanInnerHTML("数据错误",title,tkno);
				}else{
					alert(title);
				}
				return false;
			}
			if(asmsAndagent=="agent"){//***分销特有*** 国际行程单能否创建和打印  0标示不能打印 空或1标示能打
				var jphcglgj=$("jphcglgj"+tkno).value;
				if("0"==nfdygjxcd&&jphcglgj=="0"){
					var tipinfo="票号"+tkno+"属于国际票,部门权限已限制不可打印国际行程单，如需打印，请联系客服人员！";
					setXcdztSpanInnerHTML("权限不足",tipinfo,tkno);
					return false;
				}
			}
    	}else{
    		setXcdztSpanInnerHTML("数据错误","行程单信息还在加载中,请重新点击打印",tkno);
    		return false;
    	}
    	setXcdztSpanInnerHTML("数据正确","",tkno);
    	return true;
}



 function lockScreenticket(str){
    fun=false;
    var selectTag=document.getElementsByTagName('select');
    for(var i=0;i<selectTag.length;i++){
        selectTag[i].style.display='none';
    }
    
    document.oncontextmenu= function(){event.returnValue=false;}
	var msgw,msgh,bordercolor;
	msgw=400;//提示窗口的宽度
	msgh=100;//提示窗口的高度
	titleheight=25 //提示窗口标题高度
	bordercolor="#336699";//提示窗口的边框颜色
	titlecolor="#99CCFF";//提示窗口的标题颜色
	
	var sWidth,sHeight;
	sWidth=document.body.clientWidth;
	sHeight=document.body.scrollHeight;

	var bgObj=document.createElement("div");
	bgObj.setAttribute('id','bgDiv');
	bgObj.style.position="absolute";
	bgObj.style.top="0";
	bgObj.style.background="#efefef";
	bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=0,finishOpacity=0";
	bgObj.style.opacity="0.6";
	bgObj.style.left="0";
	bgObj.style.width=sWidth + "px";
	bgObj.style.height=sHeight + "px";
	bgObj.style.zIndex = "10000";
	document.body.appendChild(bgObj);
	
//	document.body.style.overflow="hidden";
	
	var msgObj=document.createElement("div")
	msgObj.setAttribute("id","msgDiv");
	msgObj.setAttribute("align","center");
//	msgObj.style.background="white";
	//msgObj.style.border="1px solid " + bordercolor;
   	msgObj.style.position = "absolute";
	msgObj.style.left = "50%"; 
	msgObj.style.top = "50%"; 
    msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
	msgObj.style.marginLeft = "-225px" ;
	msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
    msgObj.style.width = msgw + "px";
    msgObj.style.height =msgh + "px";			
    msgObj.style.textAlign = "left";
    msgObj.style.lineHeight ="150%";
    msgObj.style.zIndex = "10001";
	//msgObj.style.padding = "0 0 20px 0";
   document.body.appendChild(msgObj);
   var str="<table width='300' height='60' border='0' align='center' cellpadding='0' cellspacing='1' style='border:1px solid #ccc;'>"+
  "<tr><td bgcolor='#E1F8FF'><table width='94%' border='0' align='center' cellpadding='0' cellspacing='0'>"+
   "<tr><td width='16%'><img src='/ticketcommon/loading.gif' width='50' height='50'></td>"+
  "<td width='84%'>"+str+"</td></tr></table></td></tr></table>"
   var txt=document.createElement("p");
   txt.style.margin="1em 0"
   txt.setAttribute("id","msgTxt");
   txt.style.padding="5px";
   txt.innerHTML=str;
   document.getElementById("msgDiv").appendChild(txt);
}


function setPreprint(){
	var str="";
	if($("asms_xcd_preprint_box").checked){
		str="1";
	}else{
		str="0";
	}
	setCookie("asms_xcd_preprint_box",str,360);
}

function onloadsetpreprint(){
	var temp = getCookie("asms_xcd_preprint_box");
	if(temp=="1"){
		$("asms_xcd_preprint_box").checked=true;
	}
}
function getprintAction(){
	if($("asms_xcd_preprint_box").checked){
		return "S";
	}
	return "P";
}
