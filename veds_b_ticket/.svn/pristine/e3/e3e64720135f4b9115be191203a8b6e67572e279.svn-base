var commandList=new Array();
var commandCount=-1;//数组当前位置,默认不在-1
var avhFs="1";	//默认是1是表示价格提醒，0是直通
var commandset=">";
var searchDate="";		//avh（所有查询）的时候，我要通过解析获得，当前时间，然后给这个变量,用来点航班号，查舱位（分销商用返点）

var excutenum=1;

var mainBody;//body对象

/**
替换字符串
**/
String.prototype.replaceAll = function (AFindText,ARepText){
                raRegExp = new RegExp(AFindText,"g");
                return this.replace(raRegExp,ARepText);
}

//获取SelectionObj对象
function getSelectionObjex(){
	if (document.selection) { //IE浏览器下的处理，如果要获取内容，需要在selection 对象上加上text 属性
	   return document.selection.createRange();
	}else if (window.getSelection) { //主流的浏览器，包括mozilla，chrome，safari
	    return window.getSelection();
	}
}

//获取RangeObj对象
function getRangeObj(obj){
	try{
	    rangeObj = document.body.createTextRange();
	    if(obj){
			rangeObj = obj.document.body.createTextRange();
		}
	}catch(e){
		if(document.createRange){
			var range = document.createRange();
			
			var selectionObj = window.getSelection();
			selectionObj.removeAllRanges();
			selectionObj.addRange(range);
			
			range = selectionObj.getRangeAt(0);
			range.selectNodeContents(obj);
			return range;
		}
	}
	return rangeObj;
}

//获取Range文本
function getRangeText(selectionObj){
   if (document.selection) { //IE浏览器下的处理，如果要获取内容，需要在selection 对象上加上text 属性
	   return selectionObj.text;
	}else if (window.getSelection) { //主流的浏览器，包括mozilla，chrome，safari
	    if (!(text = selectionObj.text)) {
		    text = selectionObj.toString();
		}	
		return text;
	}
}


function toHtml(s) {
	s=s.replaceAll("&amp;", "&");
	s = s.replaceAll("&lt;","<");
	s = s.replaceAll("&gt;",">" );
	s = s.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;","\t");
	s = s.replaceAll("\n","\r\n");
	// s = Replace(s,"\"","'");
	s = s.replaceAll("<br>","\n" );
	s = s.replaceAll("&nbsp;&nbsp;","  " );
	// s = Replace(s,"'","");
	s = s.replaceAll("&#39;","'" );
	s = s.replaceAll("&#92;","\\");
	return s;
}
function enToNumMonth(m){//英文转数字月
	var mu=m.toUpperCase();
	var num="";
	if(mu=="JAN"){
		num="01";
	}else if(mu=="FEB"){
		num="02";
	}else if(mu=="MAR"){
		num="03";
	}else if(mu=="APR"){
		num="04";
	}else if(mu=="MAY"){
		num="05";
	}else if(mu=="JUN"){
		num="06";
	}else if(mu=="JUL"){
		num="07";
	}else if(mu=="AUG"){
		num="08";
	}else if(mu=="SEP"){
		num="09";
	}else if(mu=="OCT"){
		num="10";
	}else if(mu=="NOV"){
		num="11";
	}else if(mu=="DEC"){
		num="12";
	}
	return num;
}
function parseAv(command){
	 //指令格式
    //1.avh/xiypek/10nov
    //2.av h xiypek/10nov
    //3.av:xiypek/10nov
    //4.av xiypek/10nov
    command=command.replace(/\s{1,}/g,"");//去掉空格,包括多个空格
    var pos=command.indexOf("(");
    var sj=command.substring(0,pos);
    pos=command.indexOf(")");
    var hc=command.substring(pos+1,pos+1+6);
    searchZc(hc+sj);
}

function insertCurrentLine(_text){
	mainBody.focus();
	setRangeText(_text);
}

function insertChar(_isLoadding){
	mainBody = document.getElementById("mainbody");
	mainBody.focus();
	setRangeText(commandset);
	if(_isLoadding){
		var explorer = window.navigator.userAgent ;
		if(explorer.indexOf("MSIE") >= 0){
			setCaretPos(mainBody, parseInt(mainBody.innerHTML.length))
		}else{
			setCaretPos(mainBody, 1);
		}
	}else{
		setCaretPos(mainBody, parseInt(mainBody.innerHTML.length));
	}
}

function pingbi(){
	var event = getEvent();
	if(event.keyCode!=38 && event.keyCode!=40 && event.keyCode!=39 && event.keyCode!=37){
		//event.returnValue=false;
		disableContextEditor();
	}		
}
function callreBookvalidat(transport){
	mainBody.insertAdjacentHTML("beforeEnd",transport+"<br/>"+commandset);
	windowScroll();//返回数据时让滚动条在最后
	setCursor();
}
//执行命令
var iTimerID;
function excuteCommand(command,fn){
	try{
		window.clearTimeout(iTimerID);
	}catch(e){}
	setRangeText("         ");//在每个执行的指令后面追加空格，便于光标定位
	var kstime=new Date().getTime();
	
	
	
	mainBody.setAttribute("contentEditable",false);
	var showFail=function(transport){
		mainBody.setAttribute("contentEditable",true);
		if(transport.readyState==3 || transport.readyState==4){
			alert("请检查本地网络是否正常,请重新尝试."+transport.status+" "+transport.readyState+" "+transport.statusText);
		}
	}
	var showException=function(transport){
		//mainBody.setAttribute("contentEditable",true);
		//alert("showException系统错误!原因如下：\n1.应用服务器重启\n2.网络异常\n3.详细错误："+transport.responseText);
		//跨域没有权限也会进入这个方法
	}
	var showResponse=function(transport)
	{
		transport=transport.responseText;
		commandList.push(command);
		commandCount=commandList.length;//将下标移到当前指令执行在数组中的位置
		if(transport==null){
			mainBody.setAttribute("contentEditable",true);
			mainBody.insertAdjacentHTML("beforeEnd","系统检查到网络不稳定，重新发送指令，请稍等...</br>     <font style='font-family: 宋体;font-weight: normal;font-size:10px;color: gray'>网络传输总耗时："+(new Date().getTime()-kstime)+"毫秒</font>"+"<br/>"+commandset);
		 	
		 	windowScroll();//返回数据时让滚动条在最后
		 	
		 	setCursor();
		 	if(excutenum<=3){
			 	 //如果发现返回的是null，重新执行一次指令
			 	excuteCommand(command);
			 	excutenum=excutenum+1;
			}else{
				//如果执行了3次以上就不要执行了，将次数付为1
				excutenum=1;
			}
			return;
		}
		mainBody.setAttribute("contentEditable",true);
		
	 	mainBody.insertAdjacentHTML("beforeEnd",transport+"     <font style='font-family: 宋体;font-weight: normal;font-size:10px;color: gray'>网络传输总耗时："+(new Date().getTime()-kstime)+"毫秒</font>"+"<br/>"+commandset);
	 	
		windowScroll();//返回数据时让滚动条在最后
	 	
	 	setCursor();
		try{
			if($BOPPWD.getkey()!=""&&$("boppwd_"+$BOPPWD.getkey())){//密码框获取焦点
			//if(command.toUpperCase().indexOf("DPAY")==0){
				$("boppwd_"+$BOPPWD.getkey()).focus();
				//mainBody.setAttribute("contentEditable",false);
				//doBopPwd($BOPPWD.getkey());
				$BOPPWD.setLastKey();
			}
		}catch(e){}
		
        if(fn!=null){
        	fn();
        }
        excutenum=1;
        
	}

	if(command.indexOf("PLEASE INPUT YOUR PASSWORD:")>-1&&$BOPPWD.val()!=""){//BOP
		command=$BOPPWD.getDpayCommand()+"\rPLEASE INPUT YOUR PASSWORD:"+$BOPPWD.val();
	}
	//command为执行的指令 
	//avhFs默认是1是表示价格提醒，0是直通
	//asmsAndagent 来源 是asms或者agent
	var randamTimeTmp=new Date().getTime();
	try{
		if(command.toUpperCase().indexOf("DPAY")==0){
			$BOPPWD.key(randamTimeTmp);
			$BOPPWD.setDpayCommand(command);
		}
		if($BOPPWD.getLastKey()!=""){
			doBopPwd4();
		}
	}catch(e){}
	var myAjax = new Ajax.Request(
		$ctx+'/vedsb/eterm/hp/command?randamTime='+randamTimeTmp+"&command="+encodeURIComponent(command),
		{
			method: 'post',
			requestHeaders:{Accept:'application/html'},
			//onComplete: showResponse,
			onFailure: showFail,
			onException: showException,
			onSuccess: showResponse
		}
	);
}
//获得>的位置和，光标的位置
function getcommandsetPos(){
	var   pos=-1;
    var   selectionObj = getSelectionObjex();  
    var   rangeObj = getRangeObj(mainBody); 
    try{
    	selectionObj.select();  
    	rangeObj.setEndPoint("EndToStart",selectionObj); //从光标到文本的起始
    }catch(e){} 
    mainBody.focus();   
       
    var rangeText = getRangeText(selectionObj);
    
    //var s=tempText.text.replace(/<[^>]+>/g,"");//去掉所有html标签
    //tempText.text.lastIndexOf(">")获得得到的文本的'>'最后一个的位置
 	pos = rangeText.replaceAll("\r","").lastIndexOf(commandset);//设置起始位置，+1是lastIndexOf 0开始，
 	var pos1 = rangeText.replaceAll("\r","").length;  
 	var posAr=new Array();
 	posAr.push(pos+1);
  	posAr.push(pos1);
	return posAr;
}

//编辑>到光标的位置
function editStartToCursor(_text){
	setCursor();
	
	if(isIE){
		try{
			var selectionObj = getSelectionObjex();
	       	var rangeObj = getRangeObj(mainBody); 
	      	selectionObj.select();
	        rangeObj.setEndPoint("EndToStart",selectionObj); //从光标到文本的起始
	        
			var rangeText = getRangeText(rangeObj);
			var pos = rangeText.replaceAll("\r","").lastIndexOf(commandset);//设置起始位置，+1是lastIndexOf 0开始，
	 		var pos1 = rangeText.replaceAll("\r","").length;  
		
			var rng = document.selection.createRange();
		　 	rng.moveStart("character",-(pos1-(pos + 1)));
		　 	rng.moveEnd("character",0);
		　 	rng.select();
			rng.text=_text;
		}catch(e){
		}
	}else{
		//mainBody.setSelectionRange(pos,pos1);
		setRangeText(_text);
	}
	
	setCursor();
	
	//mainBody.focus();
	//alert(pos[0]+" "+pos[1]);
    //clipboardData.setData('text',v); 
    //document.execCommand('paste');  
    //var s=tempText.text.replace(/<[^>]+>/g,"");//去掉所有html标签
    //tempText.text.lastIndexOf(">")获得得到的文本的'>'最后一个的位置
  	// pos=tempText.text.lastIndexOf(commandset);//设置起始位置，+1是lastIndexOf 0开始，
  	// var s=tempText.text.substring(pos);//获得指令
}
	
//截取命令
function  getCursorPos(){
       var pos=-1;   
       var selectionObj = getSelectionObjex();
       var rangeObj = getRangeObj(mainBody);
       try{
       		selectionObj.select();
	        rangeObj.setEndPoint("EndToStart",selectionObj); //从光标到文本的起始
       }catch(e){}  
       
	   var rangeText = getRangeText(rangeObj);
	   
       //var s=tempText.text.replace(/<[^>]+>/g,"");//去掉所有html标签
       //tempText.text.lastIndexOf(">")获得得到的文本的'>'最后一个的位置
       //commandset为全局定义变量“>”，所以这里是的到从“>”到光标里面所有指令
	   pos = rangeText.lastIndexOf(commandset);//设置起始位置，+1是lastIndexOf 0开始，
	   var s = rangeText.substring(pos);//获得指令
	   
	   if(pos>-1){
		    var commands=s.replace(commandset,"");
		  	//全角转半角的
		    commands=toDBC(commands);
		    commands=strTrim(commands);//去掉前后空格
		    excuteCommand(commands);
        }else{//如果没有>执行所有内容
        	var commands=commands=toDBC(rangeText);
	        commands=strTrim(commands);
	        excuteCommand(commands);
        }
}  
//光标移到最后一行
function setCursor(){
	setCaretPos(mainBody,parseInt(mainBody.innerHTML.length));
}

//清屏
function clear(){
	_Filter.removeSxtj(_GetAATime.getLastTime());//清屏，将AA指令的筛选清掉
	_MoreCw.clear();//清除更多舱位的缓存数据
	var explorer = window.navigator.userAgent ;
	if(explorer.indexOf("MSIE") >= 0){
		mainBody.innerHTML='';
	}else{//FF Chrome浏览器清屏
		var nodes = mainBody.childNodes;
		var renodeArr = new Array();
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].nodeName != "INPUT" && nodes[i].nodeName != "FORM"){
				renodeArr.push(nodes[i]);
			}
		}
		
		for (var i = renodeArr.length-1; i >=0; i--) {
			mainBody.removeChild(renodeArr[i]);
		}
	}
	setCursor();
}



function initFont(){
	var time_initFont=null;
	if(parent.parent.topFrame.success_flag==1){
		clearTimeout(time_initFont);
		setTimeout("parent.parent.topFrame.init()",2000);
	}else{
		time_initFont=setTimeout("initFont()",3000);
	}
}

//全角转半角的
function toDBC(Str) {
	var DBCStr = "";    
 	for(var i=0; i<Str.length; i++){
  		var c = Str.charCodeAt(i);
  		if(c == 12288) {
      		DBCStr += String.fromCharCode(32);
   			continue;
  		}
  		if (c > 65280 && c < 65375) {
   			DBCStr += String.fromCharCode(c - 65248);
   			continue;
  		}
  		DBCStr += String.fromCharCode(c);
 	}
 	return DBCStr;
}

//去掉前后空格
function strTrim(str){
 return str.replace(/(^\s*)|(\s*$)/g, "");
}


var log="d:";
function createFiles(texts){
	try{
		var newDateObj = new Date();
		//var filename=newDateObj.getYear()+"-"+(newDateObj.getMonth()+1)+"-"+newDateObj.getDate();
		//var filename=newDateObj.getYear()+"-"+(newDateObj.getMonth()+1)+"-"+newDateObj.getDate()+newDateObj.getHours()+newDateObj.getMinutes()+newDateObj.getSeconds() ;
		var filename=newDateObj.getYear()+"-"+(newDateObj.getMonth()+1)+"-"+newDateObj.getDate();
		var fso=new ActiveXObject("Scripting.FileSystemObject");
		if (!fso.FolderExists("D:\\asmslogs")){ 
            var a = fso.CreateFolder("D:\\asmslogs"); 
        } 
		//var f1=fso.OpenTextFile(log+"//"+filename+".txt",8,true); 
		var f1=fso.OpenTextFile("d://asmslogs//"+filename+".txt",8,true); 
		f1.WriteLine(texts); 
	 	f1.Close(); 
	}catch(e){
	}
}



/*屏幕尺寸*/
 function getPageSizePtkjcp(){
	var xScroll, yScroll; 
	if (window.innerHeight && window.scrollMaxY) { 
		xScroll = document.body.scrollWidth; 
		yScroll = window.innerHeight + window.scrollMaxY; 
	} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac 
		xScroll = document.body.scrollWidth; 
		yScroll = document.body.scrollHeight; 
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari 
		xScroll = document.body.offsetWidth; 
		yScroll = document.body.offsetHeight; 
	} 
	var windowWidth, windowHeight; 
	if (self.innerHeight) { // all except Explorer 
		windowWidth = self.innerWidth; 
		windowHeight = self.innerHeight; 
	} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode 
		windowWidth = document.documentElement.clientWidth; 
		windowHeight = document.documentElement.clientHeight; 
	} else if (document.body) { // other Explorers 
		windowWidth = document.body.clientWidth; 
		windowHeight = document.body.clientHeight; 
	} 
// for small pages with total height less then height of the viewport 
	if(yScroll < windowHeight){ 
		pageHeight = windowHeight; 
	} else { 
		pageHeight = yScroll; 
	} 
// for small pages with total width less then width of the viewport 
	if(xScroll < windowWidth){ 
		pageWidth = windowWidth; 
	} else { 
		pageWidth = xScroll; 
	}
	var screen_height = window.screen.availHeight; 
	var screen_width = window.screen.availWidth; 
	arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight,screen_width,screen_height);
	return arrayPageSize; 
}

var isIE = (document.all) ? true : false;
//编辑器不能进行编辑
function disableContextEditor(){
	var event = getEvent();
	if(isIE){
		event.keyCode=0;
		event.returnValue=false;
	}else{
		//event.which=0;
		event.preventDefault();
	}
}

//兼容性处理insertAdjacentElement、insertAdjacentHTML和insertAdjacentText
if (typeof(HTMLElement) != "undefined" && !isIE) {
    HTMLElement.prototype.insertAdjacentElement = function(where, parsedNode) {
        switch (where) {
            case "beforeBegin":
                this.parentNode.insertBefore(parsedNode, this);
                break;
            case "afterBegin":
                this.insertBefore(parsedNode, this.firstChild);
                break;
            case "beforeEnd":
                this.appendChild(parsedNode);
                break;
            case "afterEnd":
                if (this.nextSibling)
                    this.parentNode.insertBefore(parsedNode, this.nextSibling);
                else
                    this.parentNode.appendChild(parsedNode);
                break;
        }
    }
    HTMLElement.prototype.insertAdjacentHTML = function(where, htmlStr) {
        var r = this.ownerDocument.createRange();
        r.setStartBefore(this);
        var parsedHTML = r.createContextualFragment(htmlStr);
        this.insertAdjacentElement(where, parsedHTML);
    }
    HTMLElement.prototype.insertAdjacentText = function(where, txtStr) {
        var parsedText = document.createTextNode(txtStr);
        this.insertAdjacentElement(where, parsedText);
    }
}

//设置可编辑区域值
function setRangeText(_text){
	if(window.getSelection){
		
		var dom = document.createTextNode(_text);
		
		var range = document.createRange();
		range.selectNodeContents(mainBody);
		
		var selectionObj = window.getSelection();
		
		try{
			selectionObj.getRangeAt(0);
		}catch(e){
			selectionObj.addRange(range);
		}
		
		var rangeObj = selectionObj.getRangeAt(0);
		if(_text.indexOf("\n") > -1){
			rangeObj.insertNode(document.createElement("br"));
		
			var explorer = window.navigator.userAgent ;
			if(explorer.indexOf("Chrome") >= 0){
				if(_text.indexOf("提醒：当前查询方式为直通！") > -1 && mainBody.innerHTML.indexOf("提醒：当前查询方式为直通！") == -1){
					rangeObj.insertNode(document.createElement("br"));
				}
			}

		}
		try{
			rangeObj.insertNode(dom);
		}catch(e){
			
		}
		
		rangeObj.collapse(true);
		
		mainBody.focus();
	}else{
		document.selection.createRange().text=_text;
	}
}

//窗口滚动条进行相对滚动
function windowScroll(){
	var topDoc = topWin.document;
	document.body.scrollTop=document.body.scrollHeight; //返回数据时让滚动条在最后
	
	if(parseFloat(document.body.scrollHeight) > parseFloat(window.screen.availHeight)){
		//topWin.scrollTo(0,parseFloat(document.body.scrollHeight) - parseFloat(window.screen.availHeight));
	}
}

//获取顶级父类window对象
var topWin= (function (p,c){
    while(p!=c){
        c = p;
        p = p.parent.parent;
    }
    return c;
})(window.parent.parent,window);



var cacheBopPwd=function(){
	var pwd={};
	var keyVal="";
	var lastKey="";
	var dpaycommandstr="";//dpay指令串
	return{
		set:function(val){
			pwd[keyVal]=val;
		},
		val:function(){
			if(!!!pwd[keyVal])return "";
			return pwd[keyVal];
		},
		key:function(val){
			keyVal=val;
		},
		getkey:function(){
			return keyVal;
		},
		setLastKey:function(){
			lastKey=keyVal;
		},
		getLastKey:function(){
			return lastKey;
		},
		setDpayCommand:function(val){
			dpaycommandstr=val;
		},
		getDpayCommand:function(){
			return dpaycommandstr;
		}
	}
}
var $BOPPWD=cacheBopPwd();

function doBopPwd(key){
	var spanObj=$("boppwdspan_"+key);
	if(spanObj){
		$BOPPWD.key(key);
		var pwdhtml="<input  title='取消输入请按ESC' id='boppwd_"+key+"' value='"+$BOPPWD.val()+"' class='BOPPWD' type='password' style='height:25px;color:#00ff00;background-color:black;text-align:center;border:0px;width:80px;' onKeydown=\"doBopPwd3('"+key+"')\" onBlur=\"doBopPwd2('"+key+"',false)\"   onMouseover='this.focus();'/>";
		pwdhtml+="<span id='boppwdtips_span"+key+"' style='color:gray;font-size:12px;margin-left:20px'>[取消输入请按ESC]</span>";
		if(!!!$("boppwd_"+key)){
			new Insertion.After(spanObj, pwdhtml);
		}
		$(spanObj).remove();
		$("boppwd_"+key).focus();
		//mainBody.setAttribute("contentEditable",false);
	}
}
//noevent   missIn:true 放弃输入密码
function doBopPwd2(key,noevent,tipInto){
	var pwdInObj=$("boppwd_"+key);
	if(pwdInObj){
		var pwdval=pwdInObj.value;
		$BOPPWD.set(pwdval);
		if(!!!tipInto){
			tipInto="请在此处输入密码...";
			if(pwdval!=""){
				if(noevent==false){
					tipInto="密码已输入,点击修改！";
				}else{
					tipInto="密码输入完成！";
				}
			}
		}
		
		var spanHtm="<span  id='boppwdspan_"+key+"' style='text-align:center;border-bottom:1px solid gray;color:#00ff00;background-color:black;' ";
		if(noevent==false){
			spanHtm+=" onMouseover=\"doBopPwd('"+key+"');\" ";
		}
		spanHtm+=">"+tipInto+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		
		if(!!!$("boppwdspan_"+key)){
			new Insertion.After(pwdInObj, spanHtm);
		}
		var boppwdtipsObj=$("boppwdtips_span"+key);
		if(boppwdtipsObj){
			$(boppwdtipsObj).remove();
		}
		
		$(pwdInObj).remove();
	}
}

function doBopPwd3(key){
	var pwdInObj=$("boppwd_"+key);
	var pwdval=pwdInObj.value;
	$BOPPWD.set(pwdval);
	var keycode=getEvent().keyCode;
	if(keycode>=112&&keycode<=123){//F12
		if(pwdval==""){
			alert("密码不能为空！");
			doBopPwd(key);
			return;
		}
		mainBody.setAttribute("contentEditable",true);
		doBopPwd2(key,true);
		
		excuteCommand($BOPPWD.getDpayCommand()+"\r PLEASE INPUT YOUR PASSWORD:");
	}else if(keycode=="27"){
		doBopPwd2(key,true,"密码已取消输入！");
		mainBody.setAttribute("contentEditable",true);
		setCursor();
	}
}

function doBopPwd4(){
	var spanObj=$("boppwdspan_"+$BOPPWD.getLastKey());
	spanObj.disabled=true;
	spanObj.setAttribute("onmouseover","");
	if(spanObj.innerHTML=="密码已输入,点击修改！"){
		spanObj.innerHTML="密码输入完成！";
	}else if(spanObj.innerHTML=="请在此处输入密码..."){
		spanObj.innerHTML="密码已取消输入！";
	}
}

//执行对象innerHTML的属性
function executInnerHTML(obj){
	if(!isBlank(obj.innerHTML)){
		setCursor();//将光标移动至最后一行,执行指令时会在光标后添加空格，避免空格将指令拆分开
		excuteCommand(obj.innerHTML);
	}
}











