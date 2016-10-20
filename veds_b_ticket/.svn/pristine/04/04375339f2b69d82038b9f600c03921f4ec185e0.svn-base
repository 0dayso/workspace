<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>正在作废，中途请不要关闭本页面</title>
<%@include file="/asms/common/meta.jsp"%>
<script type="text/javascript" src="/js/hotelsale/tooltips.js"></script>
<script type="text/javascript" src="/js/loaddiv/loaddiv.js"></script>
<link rel="stylesheet" type="text/css" href="/js/loaddiv/loaddiv.css"/>
<link rel="stylesheet" type="text/css" href="/js/hotelsale/tooltips.css"/>
<style type="text/css">
<!--
	.lc_ing{color:blue}
	.lc_wx{color:#ccc}
	.lc_yes{color:green}
	.lc_error{color:red}
-->

input.required, textarea.required {
	border: none;
}
input.validation-failed, textarea.validation-failed {
	border: 1px solid #FF3300;
	color : #FF3300;
}

.no_border{border: none;background: #000;color: #00ff00;font-family: 'Courier New';font-size: 14px;}
.bottom_border{border-bottom: 1px solid #00ff00;border-left: none;border-right: none;border-top: none;background: #000;color: #00ff00;font-family: 'Courier New';font-size: 14px;}


.no_border_table{border: none;border: 0px solid #000;width: 650px;}
.no_border_table td{color: #00ff00;background: #000;border-left: 0px solid #000;border-right: 0px solid #000;border-top: 0px solid #000;border-bottom: 0px solid #000;}
.no_border_table tr{border: none;border: 0px solid #000;}
.t_tab td .no_border_table td{border: none;border: 0px solid #000;font-family: 'Courier New';font-size: 14px;}
</style>
<script type="text/javascript">
	var tknoObj = {};//票号：退票状态（1表示已废，0表示未废）
	function toClose(lx){
		if(lx == 1){
			window.returnValue = "1";
		}
		window.close();
	}
	
	function checkTpzt(){
		var fpsb = "";
		var wfp = "";
		for(var i in tknoObj){
			if(tknoObj[i] == "0" || tknoObj[i] == "2"){
				if(wfp == ""){
					wfp = i;
				}else{
					wfp += "," + i;
				}
			}
			if(tknoObj[i] == "2"){
				if(fpsb == ""){
					fpsb = i;
				}else{
					fpsb += "," + i;
				}
			}
		}
		return {fpsb:fpsb,wfp:wfp};
	}
	
	function toSave(sfdetr){
		var tkno = checkTpzt();
		if(tkno['wfp'] == ""){
			alert("没有需要作废的机票");
		}else{
			var tknos = tkno['wfp'].split(",");
			startVt(tknos,sfdetr,0);
		}
	}
	
	function startVt(tknos,sfdetr,i){
		$("vtBtn1").disabled = true;
		$("vtBtn2").disabled = true;
		$("closeBtn1").disabled = true;
		$("closeBtn2").disabled = true;
		var len = tknos.length;
		if(i == len){
			var tkno = checkTpzt();
			if(tkno['wfp'] == ""){
				toClose(1);
			}else{
				$("vtBtn1").disabled = false;
				$("vtBtn2").disabled = false;
				$("closeBtn1").disabled = false;
				$("closeBtn2").disabled = false;
			}
			//else if(tkno['fpsb'] != ""){
			//	if(window.confirm("机票【"+tkno['fpsb']+"】作废失败，点击确认跳过"))
			//}
			return;
		}
		var tkno = tknos[i];
		var mxidObj = $("mxid_" + tkno);
		var mxid = mxidObj.value;
		var szdh = mxidObj.getAttribute("szdh");
		if(sfdetr){
			checkKpzt(mxid,szdh,tkno);
		}else{
			vt(mxid,szdh,tkno);
		}
		i++;
		startVt(tknos,sfdetr,i);
	}
	
	function checkKpzt(mxid,szdh,tkno){
		setLc(1,tkno);
		new Ajax.Request("/asms/cpzx/ticket_return_hxtp.shtml",{
			method: 'post',
			requestHeaders:{Accept:'application/json'},
			parameters: {p:"checkKpzt",mxid:mxid,szdh:szdh,tkno:tkno},
			asynchronous:false,
			onComplete: function(transport){
				var json = eval("(" + transport.responseText + ")");
				var status = json.STATUS;
				var detr = json.DETR;
				var kpzt = json.KPZT;
				if(detr != ""){
					tknoObj[tkno+"DETR"] = detr;
					var firstObj = $("first_"+tkno);
					
					if (firstObj.getAttribute("title") == "") {
						firstObj.setAttribute("title","点击查看DETR内容");
						firstObj.style.cursor = "pointer";
						AddFunToObj(firstObj,"onclick","showDetr('"+tkno+"',1)");
					}
				}
				if(status == "0"){
					setLc(1,tkno,true);
					checkXcd(mxid,szdh,tkno);
				}else{
					setLc(1,tkno,false);
					if (kpzt == "SUSPENDED") {
						if (window.confirm(json.ERROR + "，点击【确认】解挂，点击【取消】忽略错误，继续检查行程单！")) {
							jg(mxid,szdh,tkno);
						} else {
							checkXcd(mxid,szdh,tkno);
						}
					} else {
						if(window.confirm(json.ERROR+"，确认是否继续？")){
							checkXcd(mxid,szdh,tkno);
						}
					}
				}
			}
		});
	}
	
	//解挂
	function jg(mxid,szdh,tkno) {
		submitAjax("/asms/cpzx/ticket_return_hxtp.shtml",{p:"jg",tkno:tkno},function(transport){
			var json = eval("(" + transport.responseText + ")");
			var status = json.STATUS;
			var detr = json.DETR;
			var kpzt = json.KPZT;
			if (!detr) {
				detr = json.ERROR;
			}
			if(status == "0"){
				if(detr != ""){
					tknoObj[tkno+"DETR"] = detr;
				}
				alert("解挂成功，点击【确认】继续检查行程单");
				checkXcd(mxid,szdh,tkno);
			}else{
				alert("解挂失败，【" + json.ERROR + "】");
			}
		});
	}
	
	function showDetr(tkno,lx){
		var key;
		if(lx == 1){
			key = tkno+"DETR";
		}else{
			key = tkno+"DETR2";
		}
		loadQuickDiv({title:'DETR内容',top:2,w:700,h:320,url:'/asms/cpzx/tfd/vt_detr.jsp',param:{detr:tknoObj[key]}});
	}
	
	function checkXcd(mxid,szdh,tkno){
		setLc("2",tkno);
		new Ajax.Request("/asms/cpzx/ticket_return_hxtp.shtml",{
			method: 'post',
			requestHeaders:{Accept:'application/json'},
			parameters: {p:"checkXcd",mxid:mxid,szdh:szdh,tkno:tkno},
			asynchronous:false,
			onComplete: function(transport){
				var json = eval("(" + transport.responseText + ")");
				var status = json.STATUS;
				var detr = json.DETR;
				if(detr != ""){
					tknoObj[tkno+"DETR2"] = detr;
					var secObj = $("second_"+tkno);
					secObj.setAttribute("title","点击查看DETR内容");
					secObj.style.cursor = "pointer";
					AddFunToObj(secObj,"onclick","showDetr('"+tkno+"',2)");
				}
				if(status == "0"){
					setLc(2,tkno,true);
					vt(mxid,szdh,tkno);
				}else{
					setLc(2,tkno,false);
					if(window.confirm(json.ERROR+"，确认是否继续？")){
						vt(mxid,szdh,tkno);
					}
				}
			}
		});
	}
	
	function vt(mxid,szdh,tkno){
		setLc(3,tkno);
		new Ajax.Request("/asms/cpzx/ticket_return_hxtp.shtml",{
			method: 'post',
			requestHeaders:{Accept:'application/json'},
			parameters: {p:"vt",mxid:mxid,szdh:szdh,tkno:tkno},
			asynchronous:false,
			onComplete: function(transport){
				var json = eval("(" + transport.responseText + ")");
				var status = json.STATUS;
				if(status == "0"){
					tknoObj[tkno] = "1";
					setLc(3,tkno,true);
				}else{
					tknoObj[tkno] = "2";
					alert(json.ERROR);
					var thirdObj = $("third_"+tkno);
					thirdObj.setAttribute("title",json.ERROR);
					thirdObj.style.cursor = "pointer";
					setLc(3,tkno,false);
				}
			}
		});
	}
	
	function setLc(lc,tkno,isSuccess){
		if(lc == 1){
			if(isSuccess == undefined){
				$("first_"+tkno).className = "lc_ing";
				$("first_img_"+tkno).show();
			}else{
				if(isSuccess){
					$("first_"+tkno).className = "lc_yes";
				}else{
					$("first_"+tkno).className = "lc_error";
				}
				$("first_img_"+tkno).hide();
			}
		}else if(lc == 2){
			if(isSuccess == undefined){
				$("second_"+tkno).className = "lc_ing";
				$("second_img_"+tkno).show();
			}else{
				if(isSuccess){
					$("second_"+tkno).className = "lc_yes";
				}else{
					$("second_"+tkno).className = "lc_error";
				}
				$("second_img_"+tkno).hide();
			}
		}else if(lc == 3){
			if(isSuccess == undefined){
				$("third_"+tkno).className = "lc_ing";
				$("third_img_"+tkno).show();
			}else{
				if(isSuccess){
					$("third_"+tkno).innerHTML = "作废成功";
					$("third_"+tkno).className = "lc_yes";
				}else{
					$("third_"+tkno).innerHTML = "作废失败";
					$("third_"+tkno).className = "lc_error";
				}
				$("third_img_"+tkno).hide();
			}
		}
	}
	
</script>
</head>
<body>
	<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="99%">
		<tr>
			<th>乘机人</th>
			<th>
				票号<br>【OFFICE-工作号-打票机】
			</th>
			<th>航程<br>退航程</th>
			<th>出票时间</th>
			<th>账单价</th>
			<th>代理费</th>
			<c:if test="${ticket_return.jp_hcglgj eq '1'}">
				<th>机建</th>
			</c:if>
			<th>税费</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${mxList}" var="vc">
			<input type="hidden" name="mxid_${vc.TKNO}" value="${vc.ID}" szdh="${vc.SZDH}" />
			<tr id="jptr${vc.TKNO}">
				<td class="red">
					<MD:cut value="${vc.JP_CJR}" length="4" autopoint="true"></MD:cut><br>${vfn:cjrlxImg(vc.JP_CJRLX,'','','')}
				</td>
				<td align="center" class="red">
					${vc.SZDH}-${vc.TKNO}<br>【${vc.CP_OFFICEID}-${vc.CP_PID}-${vc.PRINTNO}】
				</td>
				<td>${vc.JP_HC}<br><span style="color:red;text-decoration:line-through;">${vc.TP_HC}<span></td>
				<td align="center">${fn:replace(fn:substring(vc.CP_DATETIME,0,16),' ','<br>')}</td>
				<td align="right" class="red">${vc.PJ_CGJ}</td>
				<td align="right" class="red">${vc.PJ_CGJ-vc.PJ_HJSJJSJ}(<fmt:formatNumber value="${vc.SJJSFL}" pattern="0.####%" />)</td>
				<c:if test="${ticket_return.jp_hcglgj eq '1'}">
					<td align="right" class="red">${vc.PJ_JSF}</td>
				</c:if>
				<td align="right" class="red">${vc.PJ_TAX}</td>
				<td>
					<c:if test="${vc.HX_TFZT eq '1'}">
						【已退】退票单号：${vc.HX_TFDH}
						<script type="text/javascript">
							tknoObj['${vc.TKNO}'] = "1";
						</script>
					</c:if>
					<c:if test="${vc.HX_TFZT ne '1'}">
						<span id="first_${vc.TKNO}" class="lc_wx">检查客票状态</span>
						<img src='/images/load_tss.gif' id='first_img_${vc.TKNO}' height="16" style="display: none;" />
						->
						<span id="second_${vc.TKNO}" class="lc_wx">检查行程单</span>
						<img src='/images/load_tss.gif' id='second_img_${vc.TKNO}' height="16" style="display: none;" />
						->
						<span id="third_${vc.TKNO}" class="lc_wx">作废</span>
						<img src='/images/load_tss.gif' id='third_img_${vc.TKNO}' height="16" style="display: none;" />
						<script type="text/javascript">
							tknoObj['${vc.TKNO}'] = "0";
						</script>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align: center;margin-top: 5px;padding-top:5px;width: 100%;background: #fff;">
		<input type="button" id="vtBtn1" name="vtBtn1" value="作废" class="asms_button" 
			onclick="toSave(false);">
		<input type="button" id="vtBtn2" name="vtBtn2" value="作废(状态检查)" class="asms_button" 
			onclick="toSave(true);">
		<input type="button" id="closeBtn1" name="closeBtn1" value="作废遇到问题，点击跳过问题完成与采购办理" 
			class="asms_button" onclick="toClose(1);">
		<input type="button" id="closeBtn2" name="closeBtn2" value="作废遇到问题，点击关闭本页面" class="asms_button" 
			onclick="toClose();">
	</div>
</body>
</html>