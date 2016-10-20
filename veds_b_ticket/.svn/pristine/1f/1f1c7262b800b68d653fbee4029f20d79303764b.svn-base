<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>正在退票，中途请不要关闭本页面</title>
<script type="text/javascript" src="/js/hotelsale/tooltips.js"></script>
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
	var tknoObj = {};//票号：退票状态（1表示已退，0表示未退）
	function toClose(lx){
		if(lx == "1"){
			var tkno = checkTpzt();
			if(tkno != ""){
				alert("票号【"+tkno+"】未退票成功");
				return;
			}
			window.returnValue = "1";
		}else if(lx == "2"){
			window.returnValue = "1";
		}
		window.close();
	}
	
	function checkTpzt(){
		var tkno = "";
		for(var i in tknoObj){
			if(tknoObj[i] == "0"){
				if(tkno == ""){
					tkno = i;
				}else{
					tkno += "," + i;
				}
			}
		}
		return tkno;
	}
	
	function toSave(tkno){
		//var validateForm = new Validation("trfdForm_" + tkno,{immediate:true});
		//_isFloatTip=true;
		//if(!validateForm.validate()){
		//	return;
		//}
		//return;
		setCloseBtn(true);
		setLc("5",tkno,true);
		/*
		$("trfdForm_" + tkno).request({
			onComplete:function(transport){
				var shtml = transport.responseText;
				var isSuccess;
				if(shtml.indexOf("退票失败") != -1){
					isSuccess = false;
					$("second_btn1_" + tkno).disabled = false;
				}else{
					isSuccess = true;
					tknoObj[tkno] = "1";
				}
				setLc("6",tkno,isSuccess);
				var obj = $("fourth_tb_"+tkno);
				obj.removeChild(obj.rows[1]);
				obj.removeChild(obj.rows[0]);
				new Insertion.Bottom(obj, shtml);
				setCloseBtn(false);
				unlockScreen();
			}
		});
		*/
		alert($("#trfdForm_" + tkno).serialize());
		var ii = layer.load('正在退票，请稍候。。。');
			var url="${ctx}/vedsb/jptpgl/jptpdhxtp/submitTrfd?ajax=true";
			$.ajax({
		            type: "POST",
		            url: url,
		            data:$("#trfdForm_" + tkno).serialize(),
		            success: function(data){
		                         layer.msg(data,2,1);
		            			//layer.close(ii);
		            			if(data=='ok'){
		            				//layer.msg("退票申请成功！",2,1);
		            				//window.location.href="${ctx}/vedsb/jptpgl/jptpd/viewlist?gngj=1";
		            			}else{
		            				//layer.msg("退票申请失败!",2,0);
		            			}
		                     }
		        });
		return;
	}
	
	function trfd(mxid,tkno,sfdetr){
		setBtnDisabled("second_btn1_" + tkno);
		setBtnDisabled("second_btn2_" + tkno);
/*
		$("input[type='radio'][name='operation_'"+tkno+"]").each(function() {
			$(this).attr("disabled",true);
	    });*/
		setCloseBtn(true);
		if(sfdetr){
			setLc("2",tkno,true);
			$("second_tb_"+tkno).show();
			submitAjax("/asms/cpzx/ticket_return_hxtp.shtml",{p:"checkKpzt",mxid:mxid,tkno:tkno},function(transport){
				var json = eval("(" + transport.responseText + ")");
				var status = json.STATUS;
				var detr = json.DETR;
				var kpzt = json.KPZT;
				if (!detr) {
					detr = json.ERROR;
				}
				if(detr != ""){
					$("second_detrtd_"+tkno).innerHTML = "<PRE>>" + detr + "</PRE>";
				}
				if(status == "0"){
					$("second_img_"+tkno).innerHTML = "<font color=green>客票状态为<a href=javascript:showOrHide('second_detr_"+tkno+"')" 
						+" title='点击显示/隐藏DETR信息'>【OPEN FOR USE】</a></font>";
					setCloseBtn(false);
					checkXcd(mxid,tkno,true);
				}else{
					$("second_detr_"+tkno).show();
					var shtml = "<font color=red>" + json.ERROR + "</font> &nbsp;&nbsp;";
					if (kpzt == "SUSPENDED") {
						shtml += "<input type='button' id='third_btn2_"+tkno+"' class='asms_button' onclick=jg('"+mxid+"','"+tkno+"',false) value='点击解挂'>&nbsp;&nbsp;"
					}
					shtml += "<input type='button' id='third_btn_"+tkno+"' class='asms_button' onclick=checkXcd('"+mxid+"','"+tkno+"',false) "
						+" value='点击忽略错误，继续检查行程单'>";
					$("second_img_"+tkno).innerHTML = shtml;
					setCloseBtn(false);
				}
			});
		}else{
			previewTrfd(mxid,tkno,false);
		}
		//unlockScreen();
	}
	
	//解挂
	function jg(mxid,tkno) {
		setCloseBtn(true);
		$("tss_imgtr_" + tkno).show();
		$("tss_imgtd_" + tkno).show();
		submitAjax("/asms/cpzx/ticket_return_hxtp.shtml",{p:"jg",tkno:tkno},function(transport){
				var json = eval("(" + transport.responseText + ")");
				var status = json.STATUS;
				var detr = json.DETR;
				var kpzt = json.KPZT;
				if (!detr) {
					detr = json.ERROR;
				}
				if(detr != ""){
					$("second_detrtd_"+tkno).innerHTML = "<PRE>>" + detr + "</PRE>";
				}
				if(status == "0"){
					$("second_detr_"+tkno).hide();
					$("second_img_"+tkno).innerHTML = "<font color=green>客票状态为<a href=javascript:showOrHide('second_detr_"+tkno+"')" 
						+" title='点击显示/隐藏DETR信息'>【OPEN FOR USE】</a></font>";
					$("tss_imgtd_"+tkno).innerHTML = "<font color=green>解挂成功</font>";
					setCloseBtn(false);
					checkXcd(mxid,tkno,true);
				}else{
					$("second_detr_"+tkno).show();
					$("tss_imgtd_" + tkno).innerHTML = "<font color=red>解挂失败&nbsp;&nbsp;</font> ";
					setCloseBtn(false);
				}
			});
	}
	
	function checkXcd(mxid,tkno,isSuccess){
		setCloseBtn(true);
		setBtnDisabled("third_btn_" + tkno);
		setLc("3",tkno,isSuccess);
		if(!isSuccess){
			if($("second_detr_"+tkno).visible()){
				$("second_detr_"+tkno).hide();
			}
		}
		$("third_tb_"+tkno).show();
		$("third_img_"+tkno).focus();
		submitAjax("/asms/cpzx/ticket_return_hxtp.shtml",{p:"checkXcd",tkno:tkno},function(transport){
			var json = eval("(" + transport.responseText + ")");
			var status3 = json.STATUS;
			var detr3 = json.DETR;
			if (!detr3) {
				detr3 = json.ERROR;
			}
			$("third_detrtd_"+tkno).innerHTML = "<PRE>" + detr3 + "</PRE>";
			if(status3 == "0"){
				$("third_img_"+tkno).innerHTML = "<font color=green><a href=javascript:showOrHide('third_detr_"+tkno+"') "
					+"title='点击显示/隐藏DETR信息'>无有效行程单</a></font>";
				setCloseBtn(false);
				previewTrfd(mxid,tkno,true);
			}else{
				$("third_detr_"+tkno).show();
				$("third_img_"+tkno).innerHTML = "<font color=red>"+json.ERROR+"</font> "
					+"<input type='button' id='fourth_btn_"+tkno+"' class='asms_button' onclick=previewTrfd('"+mxid+"','"+tkno+"',false) "
					+"value='点击忽略行程单，继续退票'>";
				setCloseBtn(false);
			}
		});
	}
	
	function previewTrfd(mxid,tkno,isSuccess){
		setCloseBtn(true);
		setBtnDisabled("fourth_btn_" + tkno);
		setLc("4",tkno,isSuccess);
		if(!isSuccess){
			if($("#third_detr_"+tkno).show()){
				$("#third_detr_"+tkno).hide();
			}
		}
		$("#fourth_tb_"+tkno).show();
		$("#fourth_tb_"+tkno).load("${ctx}/vedsb/jptpgl/jptpdhxtp/previewTrfd?ajax=true",
			{id:mxid,tkno:tkno,operation:getOperation(tkno),
			tp_cgst:"${param.tp_cgst}",cj_jsf:"${param.cj_jsf}",cj_tax:"${param.cj_tax}",
			cj_dlf:"${param.cj_dlf}",jp_pjhk:"${param.jp_pjhk}",tp_hxje:"${param.tp_hxje}"},
			function(){
				setCloseBtn(false);
			}
		);
	}
	
	function getOperation(tkno){
		var operation=$('input:radio[name="operation_' + tkno+'"]:checked').val();
		return operation;
	}
	
	function setLc(lc,tkno,isSuccess){
		if(lc == "2"){
			if(isSuccess){
				$("first_"+tkno).className = "lc_yes";
			}else{
				$("first_"+tkno).className = "lc_error";
			}
			$("second_"+tkno).className = "lc_ing";
			$("third_"+tkno).className = "lc_wx";
			$("fourth_"+tkno).className = "lc_wx";
			$("fifth_"+tkno).className = "lc_wx";
		}else if(lc == "3"){
			if(isSuccess){
				$("second_"+tkno).className = "lc_yes";
			}else{
				$("second_"+tkno).className = "lc_error";
			}
			$("third_"+tkno).className = "lc_ing";
			$("fourth_"+tkno).className = "lc_wx";
			$("fifth_"+tkno).className = "lc_wx";
		}else if(lc == "4"){
			if($("third_"+tkno).className == "lc_ing"){ 
				if(isSuccess){
					$("third_"+tkno).className = "lc_yes";
				}else{
					$("third_"+tkno).className = "lc_error";
				}
			}else{
				$("first_"+tkno).className = "lc_yes";
			}
			$("fourth_"+tkno).className = "lc_ing";
			$("fifth_"+tkno).className = "lc_wx";
		}else if(lc == "5"){
			$("fourth_"+tkno).className = "lc_yes";
			$("fifth_"+tkno).className = "lc_ing";
		}else if(lc == "6"){
			if(isSuccess){
				$("fifth_"+tkno).className = "lc_yes";
				$("fifth_"+tkno).innerHTML = "退票成功";
			}else{
				$("fifth_"+tkno).className = "lc_error";
				$("fifth_"+tkno).innerHTML = "退票失败";
			}
		}
	}
	
	function showOrHide(id){
		var obj = $(id);
		if(obj.visible()){
			obj.hide();
		}else{
			obj.show();
		}
	}
	
	function setBtnDisabled(id){
		if($(id)){
			$(id).disabled = true;
		}
	}
	
	function setCloseBtn(bl){
		$("closebut1").disabled = bl;
		$("closebut2").disabled = bl;
		$("closebut3").disabled = bl;
	}
	
	function changeLanguage(obj){
		if (obj.innerHTML == "切换到中文模式") {
			$(".english").hide();
			$(".chinese").show();
			obj.innerHTML = "切换到英文模式";
		} else {
			$(".english").show();
			$(".chinese").hide();
			obj.innerHTML = "切换到中文模式";
		}
	}
</script>
</head>
<body>
	<table class="list_table" cellpadding="0" cellspacing="0" align="center" >
		<tr>
			<th>乘机人</th>
			<th>
				票号<br>【OFFICE-工作号-打票机】
			</th>
			<th>航程<br>退航程</th>
			<th>出票时间</th>
			<th>账单价</th>
			<th>代理费</th>
			<c:if test="${jptpd.gngj eq '1'}">
				<th>机建</th>
			</c:if>
			<th>税费</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${mxList}" var="vc">
			<tr id="jptr${vc.TKNO}">
				<td class="red">
					${vc.CJR}
				</td>
				<td align="center" class="red">
					${vc.TKNO}<br>【${vc.CP_OFFICEID}-${vc.CP_PID}-${vc.PRINTNO}】
				</td>
				<td>${vc.HC}<br><span style="color:red;text-decoration:line-through;">${vc.HC}<span></td>
				<td align="center">${fn:replace(fn:substring(vc.CP_DATETIME,0,16),' ','<br>')}</td>
				<td align="right" class="red">${vc.CG_ZDJ}</td>
				<td align="right" class="red">${vc.PJ_CGJ-vc.PJ_HJSJJSJ}(${vfn:format(vc.SJJSFL,'0.####%')})</td>
				<c:if test="${jptpd.gngj eq '1'}">
					<td align="right" class="red">${vc.CG_JSF}</td>
				</c:if>
				<td align="right" class="red">${vc.CG_TAX}</td>
				<td>
					<c:if test="${vc.HX_TFZT eq '1'}">
						【已退】退票单号：${vc.HX_TFDH}
						<script type="text/javascript">
							tknoObj['${vc.TKNO}'] = "1";
						</script>
					</c:if>
					<c:if test="${vc.HX_TFZT ne '1'}">
						<span id="first_${vc.TKNO}" class="lc_ing">选择指令</span>
						->
						<span id="second_${vc.TKNO}" class="lc_wx">检查客票状态</span>
						->
						<span id="third_${vc.TKNO}" class="lc_wx">检查行程单</span>
						->
						<span id="fourth_${vc.TKNO}" class="lc_wx">退票确认</span>
						->
						<span id="fifth_${vc.TKNO}" class="lc_wx">退票完成</span>
						<script type="text/javascript">
							tknoObj['${vc.TKNO}'] = "0";
						</script>
					</c:if>
				</td>
			</tr>
			<c:if test="${vc.HX_TFZT ne '1'}">
				<tr>
					<td colspan="${jptpd.gngj eq '1'  ? 9 : 8}">
						<table class="list_table" cellpadding="0" cellspacing="0" align="center" >
							<tr>
								<th style="text-align: left;font-weight: bolder;">
									退票指令：
									<label for="operation_${vc.TKNO}_2">
										<input type="radio" id="operation_${vc.TKNO}_2" name="operation_${vc.TKNO}" value="2" checked>TRFD:L/${vc.TKNO}/AM/${vc.TP_PRINTNO}/D（半自动）
									</label>
									<label for="operation_${vc.TKNO}_1">
										<input type="radio" id="operation_${vc.TKNO}_1" name="operation_${vc.TKNO}" value="1" >TRFD:Z/${vc.TKNO}/${vc.TP_PRINTNO}/D（自动）
									</label>
									<label for="operation_${vc.TKNO}_3">
										<input type="radio" id="operation_${vc.TKNO}_3" name="operation_${vc.TKNO}" value="3">TRFD:AM/${vc.TP_PRINTNO}/D（手工）
									</label>
								</th>
							</tr>
							<tr>
								<td style="text-align: right;padding-right: 20px;">
									<input type="button" id="second_btn1_${vc.TKNO}" value="退票" class="asms_button" 
										onclick="trfd('${vc.ID}','${vc.TKNO}',false);">
									<input type="button" id="second_btn2_${vc.TKNO}" name="savebut" value="退票(状态检查)" class="asms_button" 
										onclick="trfd('${vc.ID}','${vc.TKNO}',true);">
								</td>
							</tr>
						</table>
						<table class="list_table" cellpadding="0" cellspacing="0" align="center" >
							<tbody id="second_tb_${vc.TKNO}" style="display: none">
								<tr>
									<td class="title" >
										检查客票状态：<span id="second_img_${vc.TKNO}"><img src='/images/load_tss.gif' 
											id='img1' height="16" /></span>
									</td>
								</tr>
								<tr id="tss_imgtr_${vc.TKNO}" style="display: none;">
									<td id="tss_imgtd_${vc.TKNO}" style="display: none;background: #ccc;font-weight: bolder;padding-left: 20px;">
										正在解挂<img src='/images/load_tss.gif' id='tssimg' height="16" />
									</td>
								</tr>
								<tr id="second_detr_${vc.TKNO}" style="display: none">
									<td id="second_detrtd_${vc.TKNO}" style="background: #000;color: #00ff00"></td>
								</tr>
							</tbody>
						</table>
						<table class="list_table" cellpadding="0" cellspacing="0" align="center" width="100%" >
							<tbody id="third_tb_${vc.TKNO}" style="display: none">
								<tr>
									<td class="title" >
										检查行程单：<span id="third_img_${vc.TKNO}"><img src='/images/load_tss.gif' 
											id='img1' height="16" /></span>
									</td>
								</tr>
								<tr id="third_detr_${vc.TKNO}" style="display: none">
									<td id="third_detrtd_${vc.TKNO}" style="background: #000;color: #00ff00"></td>
								</tr>
							</tbody>
						</table>
						<table class="list_table" cellpadding="0" cellspacing="0" align="center" width="100%" >
							<tbody id="fourth_tb_${vc.TKNO}" style="display: none">
								<tr>
									<td class="title" >
										退票确认：<img src='/images/load_tss.gif' id='img2' height="16" />
									</td>
								</tr>
							</tbody>
						</table>
						<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="100%" >
							<tbody id="fifth_tb_${vc.TKNO}" style="display: none">
								<tr>
									<td class="title" >
										退票完成：<img src='/images/load_tss.gif' id='img2' height="16" />
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div style="text-align: center;margin-top: 5px;padding-top:5px;width: 100%;background: #fff;">
		<input type="button" id="closebut1" name="closebut1" value="退票成功，点击完成与采购办理" 
			class="asms_button" disabled onclick="toClose(1);">
		<input type="button" id="closebut2" name="closebut2" value="退票遇到问题，点击跳过问题完成与采购办理" 
			class="asms_button" onclick="toClose(2);">
		<input type="button" id="closebut3" name="closebut3" value="退票遇到问题，点击关闭本页面" 
			class="asms_button" onclick="toClose(3);">
	</div>
</body>
</html>