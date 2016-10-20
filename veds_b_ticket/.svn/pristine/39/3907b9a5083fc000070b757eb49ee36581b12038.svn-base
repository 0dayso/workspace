<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>黑屏</title> 
<!--设置网页的文件编码-->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="黑屏,机票预定,keyword3">
<meta http-equiv="description" content="黑屏">
<script src="${ctx}/static/js/prototype.js" type="text/javascript"></script>
<SCRIPT language=javascript src="${ctx}/static/js/eterm/key.js"></SCRIPT>
<script language=javascript src="${ctx}/static/js/eterm/eterm.js"></script>
<script language=javascript src="${ctx}/static/js/eterm/eterm_position.js"></script>

<style>
.hbh{
	color:#00FF00;
}
.cw{
	color:#5b6161;
}
.hc{
	color:#00FF00;
}

body {
	font-size: 20px; 
	font-family:宋体; 
	background-color: #000000;
	scroll=no;
	color:#00FF00;
	letter-spacing:1px;
	margin:0;
	
}

p{
	margin-top:0px;
	margin-bottom:0px;}
table{
	color:#00FF00;
	font-size:20px;
	letter-spacing:1px;
}
br{
	margin-top:0px;
	margin-bottom:0px;
}
pre{
	margin-top:0px;
	margin-bottom:0px;
}

td{white-space: nowrap;padding-left:4px; }

@media screen and (-webkit-min-device-pixel-ratio:0) {
	body{
		letter-spacing:0px;
	}
}

.cxClass{
	font-size:12px;
	color:#FF0;
}
</style>
<script type="text/javascript">
var $ctx = "${ctx}";
var mainBody;
function ajax(a, b, c) {
	var d = ["MSXML2.XMLHTTP", "Microsoft.XMLHTTP"], xmlObj;
	try {
		xmlObj = new XMLHttpRequest();
	}catch (e) {
		for (var i = 0; i < d.length; i++) {
			try {
				xmlObj = new ActiveXObject(d[i]);
				break;
			}
			catch (e) {
			}
		}
	}
	if (!xmlObj) {
		return;
	}
	xmlObj.open(b ? "POST" : "GET", a || _.location.href, !!c);
	xmlObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlObj.setRequestHeader("If-Modified-Since", new Date(0));
	if (c) {
		xmlObj.onreadystatechange = function () {
			if (xmlObj.readyState == 4) {
				c(xmlObj.status == 200 ? xmlObj.responseText : null)
			}
		};
	}
	xmlObj.send(b || "");
	return c ? xmlObj : (xmlObj.status == 200 ? xmlObj.responseText : null);
}
	

function gNKey(){
	var event = getEvent();
	var keyCode = event.which ? event.which : event.keyCode;
	
	if(event.srcElement.type=="password"&&event.srcElement.className=="BOPPWD"){
		/**
		if(keyCode!="123"){
			return;
		}else{
			mainBody.setAttribute("contentEditable",true);
		}
		**/
		return;
	}
	
	
	if(mainBody.getAttribute("contentEditable") == "true"){		//可编辑状态才执行指令
		if(keyCode==27){   //Esc  按这个键在光标处插入一个开始字符
			//insertChar();
			setRangeText(commandset);
		}
	}
	var vkey="";
	//if(key.length>0){
		vkey=returnkey();
	//}
	if(commandList.length>0){
		if(vkey=='UP'){//ctrl+上建
			commandCount--;
			if(commandCount>=0){
				editStartToCursor(commandList[commandCount]);
				//insertCurrentLine(commandList[commandCount]);
			
			}else{
				commandCount=commandList.length-1;//将下标指向最后一个数组位置
				editStartToCursor(commandList[commandCount]);
				//insertCurrentLine(commandList[commandCount]);
				
			}
		}else if(vkey=='DOWN'){//ctrl+下建
			commandCount++;
			if(commandCount<=(commandList.length-1)){
				//insertCurrentLine(commandList[commandCount]);
				editStartToCursor(commandList[commandCount]);
				
			}else{
				commandCount=0;
				//insertCurrentLine(commandList[commandCount]);
				editStartToCursor(commandList[commandCount]);
			}
		}
	}
	if(vkey=="CTRL+V"){
		try{
			var t=window.clipboardData.getData("Text");
			window.clipboardData.setData("Text", strTrim(t));
		}catch(e){}
		return;
	}
	for(var i=0;i<key.length;i++){
		if(key[i][1]=='0'){
			if(mainBody.getAttribute("contentEditable") == "true"){		//可编辑状态才执行指令
				if(key[i][0]=='0001' && key[i][2]==vkey){
					getCursorPos();
					disableContextEditor();
					break;
				}
			}else{
				if(key[i][0]=='0003' && key[i][2]==vkey){		//f6执行指令
					mainBody.setAttribute("contentEditable",true);
					mainBody.focus();
					//event.keyCode=0;
					//event.returnValue=false;
					disableContextEditor();
					break;
				}else{
					alert("指令还在执行中，请稍后！！");
					break;
				}
			}
			if(key[i][0]=='0002' && key[i][2]==vkey){
				createFiles(mainBody.innerText);
				commandList=null;
				commandList=new Array();
				clear();
				insertChar();
				//event.keyCode=0;
				//event.returnValue=false;
				disableContextEditor();
				break;
			}else if(key[i][0]=='0004' && key[i][2]==vkey){
				//event.keyCode=0;
				//event.returnValue=false;
				disableContextEditor();
				break;
			}
		}else{
			if(key[i][2]==vkey){
				insertCurrentLine(key[i][3]);
				if(key[i][4]=='1'){
					excuteCommand(key[i][3]);
				}
				//event.keyCode=0;
				//event.returnValue=false;
				disableContextEditor();
				break;
			}
		}
	}
	
	//如果没有快捷键 
	if(key.length<=0){
		if(mainBody.getAttribute("contentEditable") == "true"){		//可编辑状态才执行指令
			//if(event.keyCode==27){   //Esc  按这个键在光标处插入一个开始字符
			//		insertChar();
			//	}
			if(keyCode=='123'){		//f12执行指令
				getCursorPos();
				//event.keyCode=0;
				//event.returnValue=false;
				disableContextEditor();
			}
		}else{
			if(keyCode=='117'){		//f6执行指令
				mainBody.setAttribute("contentEditable",true);
				mainBody.focus();
				//event.keyCode=0;
				//event.returnValue=false;
				disableContextEditor();
			}else{
				alert("指令还在执行中，请稍后！！");
				//return;
			}
		}
		if(keyCode=='114' || keyCode=='115' || keyCode=='112' || keyCode=='116' 
		 || keyCode=='122' || keyCode=='119'){
			//event.keyCode=0;
			//event.returnValue=false;
			disableContextEditor();
		}
		if(keyCode=='113' ){	//f2
			disableContextEditor();
		}
		if( keyCode=='120'){	//f9 执行 PN
			insertCurrentLine("PN");
			excuteCommand("PN");
			
			//event.keyCode=0;
			//event.returnValue=false;
			disableContextEditor();
		}
		if( keyCode=='121'){	//f9 执行 PB
			insertCurrentLine("PB");
			excuteCommand("PB");
			//event.keyCode=0;
			//event.returnValue=false;
			disableContextEditor();
		}
		if( keyCode=='118'){	//f7
			createFiles(mainBody.innerText);
			commandList=null;
			commandList=new Array();
			clear();
			insertChar();
			//event.keyCode=0;
			//event.returnValue=false;
			disableContextEditor();
		}
	}
}
	
function init(){
	mainBody = document.getElementById('mainbody');
	insertChar('load');
}




var key=new Array();

document.onkeydown=gNKey;  //功能键

</script>

</head>
<body contenteditable=true id="mainbody" onload="init()">
</body>
</html> 