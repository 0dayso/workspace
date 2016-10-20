
var layer_index = "";
/*
 * 构建小看板
 * <a onclick='toCZ();'>&nbsp;&nbsp;&nbsp;&nbsp;在线充值</a>
 */
var html_right = "<table cellPadding=0 cellSpacing=0 >" +
					"<tbody style='background:transparent'>" +
						"<tr>" +
							"<td style='background:transparent' >" +
								"<img id='img_' name='shareto_float_btn' src="+$ctx+"/static/images/jzcp/vetps.png style='cursor:pointer;'/>" +
							"</td>" +
							"<td>" +
								"<div id='opdiv_' style='border:solid 1px #7F7F7F; width:300px; height:320px;'>" +
								    "<div class='shareto_div_01' style='width:100%;'>" +
								       	"<div style='background:#F2F2F2;line-height:200%;padding-left:5px;'>" +
				                       		"<div style='float:left;margin-bottom:5px; margin-top:2px;'>" +
				                        	"<img id='czdgtz' src='"+$ctx+"/static/images/jzcp/h_czrw_icon.png' title='C站代购' style='cursor:pointer; vertical-align:bottom;' onclick='showCz()'/>" +
				                        	"<label>&nbsp;&nbsp;【可用余额:<span id='cpsMoney'>--</span>】</label>" +
				                        "</div>" +
				                       	"<div style='float:right'></div>" +
				                       	"<div style='clear:both;'></div>" +
			                        "</div>" +
								    "<div style='background:#F2F2F2;border-bottom:1px solid #E5E5E5;padding-left:5px;'></div>" +
								    "<div id='ckelist1' class='shareto_div_03' style='height:320px; overflow-y:auto;'>" +
				                        "<iframe id='b2ciframe' src='' scrolling='no' height='320px' width='292px' frameborder=0></iframe>" +
										"<div style='clear:both'></div>"+
									"</div>"+
								"</div>" +
							"</td>" +
						"</tr>" +
					"</tbody>" +
				"</table>";
	
var html_left = "<table cellPadding=0 cellSpacing=0 ><tbody style='background:transparent'><tr><td><div ></div></td><td><img name='shareto_float_btn' src='' style='cursor:pointer;' onmouseover='sharetoFloatContain1.over()' onclick='sharetoFloatContain1.center(this)'/></td></tr></tbody></table>";

function initIframe() {
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
            style: "height:auto;position:absolute;z-index:8886;display:none;top:50%;left:85%;overflow:auto;"
        }),
        inputTimer, list, clist, as, texts = {},
        script, timerover, timerout, timerloop, loop = function () {
            var t = 1000,
                st = getS().t,
                c, wh = getWH();
            c = st - div.offsetTop + (wh.h / 2 - sharetoFloatContain1.pop.offsetWidth / 2)+100;
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
        if (/shareto_float1.js/.test(ci.src)) {
            ci.src.replace(/(align|color|move)=([^&]+)/g, function (a, p, v) {
                conf[p] = v
            });
            break;
        }
    };
    div = creElm({
        style: "height:auto;display:none;position:" + (conf.move != '0' || /msie 6/i.test(na) ? 'absolute' : 'fixed') + ";z-index:8886;top:" + (conf.move != '0' || /msie 6/i.test(na) ? '0' : '150px') + ";" + (conf.align == 'right' ? "right:-305px;overflow:hidden;width:330px;" : "left:-242px;overflow:auto;"),
        innerHTML: conf.align == 'right' ? html_right : html_left,
        id:"b2cBoard"
    });
    creElm({
    	href: $ctx+'/static/js/b2bcp/shareto/css1.css',
        rel: 'stylesheet',
        type: 'text/css'
    }, 'link');
    sharetoFloatContain1 = {
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
            div.style.cssText += ";height:" + this.pop.offsetHeight + 100 + "px;width:26px;left:" + (getWH() - 26) + "px";
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
            if (div.offsetWidth > sharetoFloatContain1.pop.offsetWidth) {
                clearInterval(timerover);
                clearTimeout(timerloop);
                div.style.cssText += ";height:" + sharetoFloatContain1.pop.offsetHeight  + "px;width:" + (sharetoFloatContain1.pop.offsetWidth + 26) + "px;left:" + (getWH() - 26 - sharetoFloatContain1.pop.offsetWidth) + "px";
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
                list = d.getElementById('ckelist1');
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
    (conf.move != '0' || /msie 6/i.test(na)) && loop()
}; 

$(function(){
	initIframe();
	/*
	 * 鼠标点击事件
	 */
	$("#img_").live("click", function () {
	   	$(this).toggle(function (){$("#b2cBoard").animate({right: "-6px"}, 600); iframeShow();},function(){$("#b2cBoard").animate({right: "-305px"}, 600); ifraameHide();});
	  	$(this).trigger('click');
	});
	/*
	 * 小看板鼠标移动事件 
	 */
//	$('#img_').live('mouseover',function(){
//		if($('#b2cBoard').css('right') == '-305px'){
//			$("#b2cBoard").animate({right: "-6px"}, 600);
//		}
//	});
//	
//	$('#opdiv_').live('mouseleave',function(){
//		$("#b2cBoard").animate({right: "-305px"}, 600);
//	});
	
});
//显示代购信息
function showTPS(key,shbh,url,userid){
	var href_ = url + '/vetps/dsyzm/pendingorder/listPendingOrder?userid=' + userid + '&shbh=' + shbh + '&my=' + key;
	$('#b2ciframe').attr('src', href_);
}


//获取秘钥信息
function getSecretKey(){
	$.ajax({url:$ctx+'/vedsb/jzcp/b2cpurchas/getSecretKey',
			type:'post',
			success: function(dataTxt){
				var jsonResult=eval("("+dataTxt+")");
				if("-1" == jsonResult.status){//检查失败
					return false;
				}
				showTPS(jsonResult.secretkey,jsonResult.shbh,jsonResult.czurl,jsonResult.userid);
			}
		}
	);
}

function iframeShow(){
	getSecretKey();
	getCpsMoney();
}

function ifraameHide(){
	$('#b2ciframe').attr('src',"");
}


//获取cps的可用余额
function getCpsMoney(){
	$.ajax({url:$ctx+'/vedsb/jzcp/b2cpurchas/getCpsMoney',
			type: 'post',
			success: function(dataTxt){
				var jsonResult=eval("("+dataTxt+")");
				if("-1"==jsonResult.status){//检查失败
					return false;
				}else{
					document.getElementById('cpsMoney').innerHTML = '￥' + jsonResult.kyye;
				}
			}
		}
	);
}

/*
 * 判断c站代购浮动框是否显示
 */
function b2cStateAjax(){
	if(getCookie_vetps()){
		if(document.getElementById("b2cBoard").style.display == 'none'){
			$("#b2cBoard").show();
		}
	}
}

function showCz(){
	$.ajax({url:$ctx+'/vedsb/jzcp/b2cpurchas/getSecretKey',
			type:'post',
			success:function(dataTxt){
				var jsonResult=eval("("+dataTxt+")");
				if("-1"==jsonResult.status){//检查失败
					return false;
				}
				showczByMy(jsonResult.secretkey,jsonResult.shbh,jsonResult.czurl,jsonResult.userid);
			}
		}
	);
}


function showczByMy(my,shbh,czurl,userid){
	var url=czurl+'/vetps/dsyzm/pendingorder/listDsyzm?userid='+ userid +'&shbh='+shbh+'&my='+my;
	layer_index = submitToIframe(url,'C站代购',750,500,80,20);
}
function submitToIframe(url,title,w,h,t,l){
	$.layer({type: 2,title: title,area: [w, h],iframe: {src: url}});	
}
/*
 * 根据cookie判断用户是否使用了c站代购
 */
$(function(){
	 window.setInterval('b2cStateAjax()', 1000 * 5);
});

/*
 * 在线充值界面
 */
function toCZ() {
	alert("暂未实现！");
	var url=$ctx+'/vedsb/jzcp/b2cpurchas/zxcz';
	submitToIframe(url,"CPS账户在线充值",500,400);
}
function getCookie_vetps() {
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
	    var c = ca[i];
	    if(c.indexOf('b2c_czdg') > 0){
	    	return '1';
	    }
	}
	return null;
}
