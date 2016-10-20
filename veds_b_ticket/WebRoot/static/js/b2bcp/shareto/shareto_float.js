//是否点击的搜索，如果点击了，那么在60秒后才检索,防止检索的时候刷新
var currenttime=5000;
var searchtimeid = null;
var b2bcppath = $ctx+'/static/js/b2bcp';
function initB2bIframe() {
    var d = document,
        isStrict = d.compatMode == "CSS1Compat",
        dd = d.documentElement,
        db = d.body,
        m = Math.max,
        na = navigator.userAgent.toLowerCase(),
        ie = !! d.all,
        head = d.getElementsByTagName('head')[0],
        getWH = function () {
            return {
                h: (isStrict ? dd : db).clientHeight,
                w: (isStrict ? dd : db).clientWidth
            }
        },
        getS = function () {
            return {
                t: m(dd.scrollTop, db.scrollTop),
                l: m(dd.scrollLeft, db.scrollLeft)
            }
        },
        creElm = function (o, t, a) {
            var b = d.createElement(t || 'div');
            for (var p in o) {
                p == 'style' ? b[p].cssText = o[p] : b[p] = o[p]
            }
            return (a || db).insertBefore(b, (a || db).firstChild)
        },
        div, div1 = creElm({
            style: "height:auto;position:absolute;z-index:8887;display:none;top:50%;left:85%;overflow:auto;"
        }),
        inputTimer, list, clist, as, texts = {},
        script, timerover, timerout, timerloop, loop = function () {
            var t = 1000,
                st = getS().t,
                c, wh = getWH();
            c = st - div.offsetTop + (wh.h / 2 - sharetoFloatContain.pop.offsetWidth / 2);
            c != 0 && (div.style.top = div.offsetTop + c / 10 + 'px', t = 10);
            timerloop = setTimeout(loop, t)
        },
        iframe = creElm({
            style: 'position:' + (/firefox/.test(na) ? 'fixed' : 'absolute') + ';display:none;',
            frameBorder: 0
        }, 'iframe'),
        conf = {
            align: 'right',
            move: '1'
        },
        scripts = d.getElementsByTagName('script');
    for (var i = 0, ci; ci = scripts[i++];) {
        if (/shareto_float.js/.test(ci.src)) {
            ci.src.replace(/(align|color|move)=([^&]+)/g, function (a, p, v) {
                conf[p] = v
            });
            break;
        }
    };
    div = creElm({
        style: "height:auto;display:none;position:" + (conf.move != '0' || /msie 6/i.test(na) ? 'absolute' : 'fixed') + ";z-index:8887;top:" + (conf.move != '0' || /msie 6/i.test(na) ? '0' : '150px') + ";" + (conf.align == 'right' ? "right:0;overflow:hidden;width:26px;" : "left:-242px;overflow:auto;"),
        innerHTML: conf.align == 'right' ? "<table cellPadding=0 cellSpacing=0 ><tbody style='background:transparent'><tr><td style='background:transparent' ><img name='shareto_float_btn' src="+b2bcppath+"/r0.gif style='cursor:pointer;'  onclick='sharetoFloatContain.center(this)'/></td><td><div ></div></td></tr></tbody></table>" : "<table cellPadding=0 cellSpacing=0 ><tbody style='background:transparent'><tr><td><div ></div></td><td><img name='shareto_float_btn' src=images/" +(conf.color?'l'+conf.color+'.gif':'l0.gif') + " style='cursor:pointer;' onmouseover='sharetoFloatContain.over()' onclick='sharetoFloatContain.center(this)'/></td></tr></tbody></table>",
        id:"b2bcpsharebar"
    });
    creElm({
        href: b2bcppath+'/shareto/css1.css',
        rel: 'stylesheet',
        type: 'text/css'
    }, 'link');
    sharetoFloatContain = {
        pop: div.getElementsByTagName('div')[0],
        centerpop: div1,
        disappear: function (a) {
            var b = window.event || a,
                t = b.srcElement || b.target,
                contain = div1.contains ? div1.contains(t) : !! (div1.compareDocumentPosition(t) & 16),
                contain1 = div.contains ? div.contains(t) : !! (div.compareDocumentPosition(t) & 16);
            if (!contain && !contain1) {
                iframe.style.display = div1.style.display = 'none';
            }
        },
        over: conf.align == 'right' ?
        function () {
            if (div.offsetWidth > 26) return;
            clearTimeout(timerloop);
            clearInterval(timerout);
            div.style.cssText += ";height:" + this.pop.offsetHeight + "px;width:26px;left:" + (getWH() - 26) + "px";
            var t = 10,
                tmp = 0,
                step = this.pop.offsetWidth / 55;
            timerover = setInterval(function () {
                if (t == 0) {
                    clearInterval(timerover);
                    (conf.move != '0' || /msie 6/i.test(na)) && loop()
                } else {
                    var n = Math.round(step * t--);
                    div.style.left = div.offsetLeft - n + 'px';
                    div.style.width = div.offsetWidth + n + 'px'
                }
            }, 10)
        } : function () {
            clearTimeout(timerloop);
            clearInterval(timerout);
            clearInterval(timerover);
            var t = 10,
                tmp = 0,
                step = Math.abs(div.offsetLeft / 55);
            timerover = setInterval(function () {
                if (t == 0) {
                    clearInterval(timerover);
                    (conf.move != '0' || /msie 6/i.test(na)) && loop()
                } else {
                    var n = Math.round(step * t--);
                    div.style.left = div.offsetLeft + n + 'px'
                }
            }, 10)
        },
        out: conf.align == 'right' ?
        function () {
            if (div.offsetWidth > sharetoFloatContain.pop.offsetWidth) {
                clearInterval(timerover);
                clearTimeout(timerloop);
                div.style.cssText += ";height:" + sharetoFloatContain.pop.offsetHeight + "px;width:" + (sharetoFloatContain.pop.offsetWidth + 26) + "px;left:" + (getWH() - 26 - sharetoFloatContain.pop.offsetWidth) + "px";
                var t = 10,
                    tmp = 0,
                    step = (div.offsetWidth - 26) / 55;
                timerout = setInterval(function () {
                    if (t == 0) {
                        clearInterval(timerout);
                        div.style.left = '';
                        (conf.move != '0' || /msie 6/i.test(na)) && loop()
                    } else {
                        var n = Math.round(step * t--);
                        div.style.width = div.offsetWidth - n < 26 ? 26 : div.offsetWidth - n + 'px';
                        div.style.left = div.offsetLeft + n + 'px'
                    }
                }, 10)
            }
        } : function () {
            clearInterval(timerover);
            clearInterval(timerout);
            clearTimeout(timerloop);
            var t = 10,
                tmp = 0,
                step = Math.abs((div.offsetLeft + 242) / 55);
           		timerout = setInterval(function () {
                if (t == 0) {
                    clearInterval(timerout);
                    div.style.left = '-242px';
                    (conf.move != '0' || /msie 6/i.test(na)) && loop()
                } else {
                    var n = Math.round(step * t--);
                    div.style.left = div.offsetLeft - n + 'px'
                }
            }, 10)
        },
        center: function (a) {
            if (a) {
            	div1.style.display == "block" ? div1.style.display = "none" : div1.style.display = "block";
             }
                db.style.position = 'static';
                var b = getS();
                
                div1.style.margin = (-div1.offsetHeight / 2 + b.t) + "px " + (-div1.offsetWidth / 2 + b.l) + "px";
                list = d.getElementById('ckelist');
                clist = list.cloneNode(true);
                as = clist.getElementsByTagName('input');
                for (var i = 0, ci; ci = as[i++];) {
                    texts[ci.value] = ci.parentNode
                };
                with(iframe.style) {
                    left = top = '50%';
                    width = div1.offsetWidth + 'px';
                    height = div1.offsetHeight + 'px';
                    margin = div1.style.margin;
                    display = 'none'
                }
           
        },
        choose: function (o) {
            clearTimeout(inputTimer);
            inputTimer = setTimeout(function () {
                var s = o.value.replace(/^\s+|\s+$/, ''),frag = d.createDocumentFragment();
                for (var p in texts) {
                    eval("var f = /" + (s || '.') + "/ig.test(p)");
                    f && frag.appendChild(texts[p].cloneNode(true))
                }
                list.innerHTML = '';
                list.appendChild(frag)
            }, 100)
        },
        centerClose: function () {
            iframe.style.display = div1.style.display = 'none'
        }
    };
    if (ie) {
        //div.onmouseleave = sharetoFloatContain.out;
       // d.attachEvent("onclick", sharetoFloatContain.disappear)
    } else {
       // div.onmouseout = function (e) {
       //     !(this === e.relatedTarget || (this.contains ? this.contains(e.relatedTarget) : this.compareDocumentPosition(e.relatedTarget) == 20)) && sharetoFloatContain.out.call(this)
       // };
       // d.addEventListener("click", sharetoFloatContain.disappear, false)
    };
    (conf.move != '0' || /msie 6/i.test(na)) && loop()
}; 
// function googleTranslateElementInit() {
// new google.translate.TranslateElement({}, 'google_translate_element');
// }
function st_addBookmark(title){
    var url = parent.location.href;
    if (window.sidebar) { // Mozilla Firefox Bookmark
        window.sidebar.addPanel(title, url,"");
    } else if(document.all) { // IE Favorite
        window.external.AddFavorite( url, title);
    } else if(window.opera) { // Opera 7+
        return false; // do nothing
    } else { 
         alert('请按 Ctrl + D 为chrome浏览器添加书签!');
    }
}

function b2baddAll(json){
    var a="";
    var statecount_pre=100;
    var statecountminddbh="";
    for(var i=0;i<json.count;i++){
    	var onedd = json.stateList[i];
    	var stateenumordinal = onedd.stateenumordinal;
    	if(stateenumordinal<5){
    		continue;
    	}
    	var szt = onedd.stateenum;
    	var statemc = getStatemc(szt,onedd.isisloginok);
    	var asmsddbh = onedd.outOrderNo;
    	var hkgs  = onedd.airways;
    	var pnrno = onedd.pnr;
    	var hkgs_pnrno = onedd.hkgspnr;
    	var searchtext = hkgs+'_'+pnrno+'_'+hkgs_pnrno;
    	var statecount = onedd.statecount;
    	var asmspaykind = onedd.payhand;
	   	a+='<a href="#"  class="stitle"><span class="stico" onclick="quickcp(\''+asmsddbh+'\');">['+hkgs+statemc+']'+pnrno+'</span><input type="hidden" value="'+searchtext+'" /></a>';
	   	//入库失败 支付失败 超时未出票的、出票成功的
	   	if( ((szt=='SAVE1' || szt=='PAY1' || szt=='TSS1') && statecount<1) || ((szt=='TICKET2' || szt=='TSS2') && statecount<1 ) ){
	   		//如果已经有个弹出的层没有关闭
	   		if($("#backDivB2b")){
	   			
	   		}else{
		   		//弹出通知次数最小的
		   		if(statecount<statecount_pre){
		   		  	statecountminddbh = asmsddbh;
		   		  	statecount_pre = statecount;
		   		}
	   		}
	   	}
	   	var _purl=$ctx+"/vedsb/jzcp/b2bcp";
	   	//手动支付弹出
		if(asmspaykind=="1" && (szt=="PAY0") && statecount<1){
		    var url_pay= _purl+"/paypage?ajax=true&ddbh="+asmsddbh+"&asmsrandid="+new Date().getTime();
			window.open(url_pay,"b2bcpnewwindow"+asmsddbh);
		}
	}
	if(statecountminddbh!=""){
		window.setTimeout('quickcpinfo("'+statecountminddbh+'","")',400);
	}
	var s= '<div style="border:3px solid #7F7F7F; width:300px;">'+
		'<div class="shareto_div_01" style="width:100%;">'+
			'<div style="background:#F2F2F2;line-height:200%;padding-left:5px;">'+
				'<div style="font-weight:bold;font-size:12px;float:left;"><span ondblclick="setstateurl()">[</span>当前用户出票  <b style="color: green">绿字成功</b>  <b style="color: red">红字失败</b>](<span id="timelostb2b">'+(currenttime/1000)+'</span>)</div>'+
				'<div style="float:right"><img src="'+b2bcppath+'/img_exit.gif" border="0" style="margin:4px 4px 0 0;cursor:pointer;" onclick="sharetoFloatContain.centerClose()"/></div>'+
				'<div style="clear:both;"></div>'+
			'</div>'+
			'<div style=" background:#F2F2F2;border-bottom:1px solid #E5E5E5;padding-left:5px;">'+
				'<div style="background:url('+b2bcppath+'/img_so.gif) no-repeat center;height:35px; width:281px">'+
				'<input name="" type="text" onclick="clicksearchb2b();this.value==\'输入PNR或航空公司二字码\'?this.value=\'\':\'\'" value="输入PNR或航空公司二字码" style="background:#FFFFFF;border:none;margin:7px 0 0 28px;width:240px;" onkeyup = "sharetoFloatContain.choose(this)" />'+
				'</div>'+
			'</div>'+
			'<div id="ckelist" class="shareto_div_03" style="height:300px;overflow-y:auto;">'+
				a+
'<div style="clear:both"></div>'+
			'</div>'+
			'<div style="background:#F2F2F2;border-top:1px solid #E5E5E5;line-height:120%;padding-left:5px;">'+
				'<div style="width:80px;float:right;font-size:11px">'+
					'&nbsp;'+
				'</div>'+
				'<div style="clear:both"></div>'+
			'</div>'+
		'</div>'+
	'</div>';
	sharetoFloatContain.centerpop.innerHTML = "";
	sharetoFloatContain.centerpop.innerHTML = s;
	sharetoFloatContain.center();
}
/*INIT,
POLICY0, POLICYWORK, POLICY1, POLICY2,
SAVE0, SAVEWORK, SAVE1, SAVE2,
PAY0, PAYWORK, PAY1, PAY2,
TICKET0, TICKETWORK, TICKET1, TICKET2,
TSS0, TSSWORK, TSS1, TSS2*/
function getStatemc(stateenum,isisloginok){
	var s="";
	if(stateenum=='INIT'){
		s="初始";
	}else if(stateenum=='POLICY0' || stateenum=='POLICYWORK'){
		s="<img src='"+b2bcppath+"/work.gif'>取政策";
	}else if(stateenum=='POLICY1'){
		s="<span style='color: red'>政策失败</span>";
	}else if(stateenum=='POLICY2'){
		s="<span style='color: green'>政策成功</span>";
	}else if(stateenum=='SAVE0' || stateenum=='SAVEWORK'){
		s="<img src='"+b2bcppath+"/work.gif'>入库中";
	}else if(stateenum=='SAVE1'){
		s="<span style='color: red'>入库失败</span>";
	}else if(stateenum=='SAVE2'){
		s="<span style='color: green'>入库成功</span>";
	}else if(stateenum=='PAY0' || stateenum=='PAYWORK'){
		s="<img src='"+b2bcppath+"/work.gif'>支付中";
	}else if(stateenum=='PAY1'){
		s="<span style='color: red'>支付失败</span>";
	}else if(stateenum=='PAY2'){
		s="<span style='color: green'>支付成功</span>";
	}else if(stateenum=='TICKET0' || stateenum=='TICKETWORK'){
		s="<img src='"+b2bcppath+"/work.gif'>出票中";
	}else if(stateenum=='TICKET1'){
		s="<span style='color: red'>取票号中</span>";
	}else if(stateenum=='TICKET2'){
		s="<span style='color: green'>出票成功</span>";
	}else if(stateenum=='TSS0' || stateenum=='TSSWORK'){
		s="<img src='"+b2bcppath+"/work.gif'>挂票中";
	}else if(stateenum=='TSS1'){
		s="<span style='color: red'>挂票失败</span>";
	}else if(stateenum=='TSS2'){
		s="<span style='color: green'>挂票成功</span>";
	}
	if(!isisloginok&&stateenum!='INIT'){
		s="<img src='"+b2bcppath+"/offline.gif'>"+s;
	}
	return s;
}

var stateurl___ = $ctx+'/vedsb/jzcp/b2bcp/b2bstate';
//stateurl___ = $ctx+'/static/js/b2bcp/a.json';
function setstateurl(){
	stateurl___ = $ctx+'/vedsb/jzcp/b2bcp/b2bstate';
}
//--- 获取cookie
function getCookie_b2b(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
	    var c = ca[i];
	    while (c.charAt(0)==' ') c = c.substring(1,c.length);
	    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function b2bStateAjax(){
	//是否点击了入库,在b2bcp.js文件中设置
	var b2b_isopen =  getCookie_b2b("asms_b2b_isopen");
	if(b2b_isopen=="1"){
		$.getJSON(stateurl___,function(json){
			try{
				 if(json!=null && json.count>0){
						b2baddAll(json);
						$("#b2bcpsharebar").show();
						 currenttime = 10000;
					  	 searchtimeid = window.setTimeout("b2bStateAjax()",currenttime);
					  	 return;
				 }
			}catch(e){}
		    window.setTimeout("b2bStateAjax()",60000);
		});
	}else{
		window.setTimeout("b2bStateAjax()",5000);
	}
}
function clicksearchb2b(){
	if(searchtimeid){
		clearTimeout(searchtimeid);
		currenttime = 60000;
		searchtimeid = window.setTimeout("b2bStateAjax()",currenttime);
		var lostid=document.getElementById("timelostb2b");
		if(lostid){
			lostid.innerText = currenttime/1000;
		}
	}
}
function timelostfun(){
	var lostid=document.getElementById("timelostb2b");
	if(lostid){
		var i= parseInt(lostid.innerText)-1;
		if(i<0){i=0;}
		lostid.innerText = i;
	}
}
$(function(){
	 initB2bIframe();
	 window.setTimeout("b2bStateAjax()",5000);
	 window.setInterval("timelostfun()",1000);
});