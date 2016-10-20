<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动出票规则设置</title>
    <link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/static/core/calendar/xbtj.js"></script>
	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
	<style type="text/css">
		.td_right{
		    width: 228px;
		    line-height: 20px;
		}
		.td_righthclx{
			width: 132px;
			line-height: 20px;
			text-align: right;
		}
		.td_rightzdcp{
			width: 170px;
			line-height: 20px;
			text-align: right;
		}
	</style>
 	<script type="text/javascript">
 		var lay;
 		var validator;
 		$(function(){
 			var sfkabjsz = '${entity.kqkhbbj}';
 			if(sfkabjsz=='1'){
 				$("#showorhidejjcj1").show();
 				$("#showorhidejjcj2").hide();
 				$("#jjcjflag").val('1');
 			}else if(sfkabjsz=='2'){
 				$("#showorhidejjcj1").hide();
 				$("#showorhidejjcj2").show();
 				$("#jjcjflag").val('2');
 			}else{
 				$("#showorhidejjcj1").hide();
 				$("#showorhidejjcj2").hide();
 				$("#jjcjflag").val('0');
 			}
 			$("#zwtqcpts").blur(function(){
				if(parseFloat($("#zwtqcpts").val())>=parseFloat($("#zztqcpts").val())){
					$("#zwtqcpts").val("0");
				}
			});
			$("#zztqcpts").blur(function(){
				if(parseFloat($("#zztqcpts").val())<parseFloat($("#zwtqcpts").val())){
					$("#zztqcpts").val("999");
				}
			});
			$("#zdtlts").blur(function(){
				if(parseFloat($("#zdtlts").val())>parseFloat($("#zctlts").val())){
					$("#zdtlts").val(0);
					layer.msg("最长停留天数需大于等于最短停留天数")
				}else if(parseFloat($("#zdtlts").val())>999 || parseFloat($("#zdtlts").val())<0){
					$("#zdtlts").val(0);
					layer.msg("最长停留天数在0至999之间")
				}
			});
			$("#zctlts").blur(function(){
				if(parseFloat($("#zdtlts").val())>parseFloat($("#zctlts").val())){
					$("#zctlts").val(999);
					layer.msg("最长停留天数需大于等于最短停留天数")
				}else if(parseFloat($("#zctlts").val())>999 || parseFloat($("#zctlts").val())<0){
					$("#zctlts").val(999);
					layer.msg("最长停留天数在0至999之间")
				}
			});
			
 			$("#choozichp").autocompleteDynamic('shyhb','valueTozdcp');
 		 	validator = $('#zdcpgzsz').Validform({
				tiptype:3
			});
			//航程类型
			var hclx = '${entity.hclx}';
			changehclx(hclx);
			var cpszdbjzclx = '${entity.ptbjCpzclx}';
			var tbszdbjzclx = '${entity.ptbjTbzclx}';
			var sfjcosi = '${entity.sfjcosi}';
			$('input[name="checkosis"]').each(function(){//检查osi项回显
				if(sfjcosi.indexOf($(this).val())>-1){
					$(this).attr("checked","checked");
				}
			});
			$('input[name="ptcpszdbj"]').each(function(){//参与自动比价出票的cps政策类型回显
				if(cpszdbjzclx.indexOf($(this).val())>-1){
					$(this).attr("checked","checked");
				}
			});
			$('input[name="zdbjtb"]').each(function(){//参与自动比价出票的淘宝旗舰店政策类型回显
				if(tbszdbjzclx.indexOf($(this).val())>-1){
					$(this).attr("checked","checked");
				}
			});
			var wdids = '${entity.wdid}';//适用的网店回显
			$('input[name="wdids"]').each(function(){
				if(wdids == '---'){
					$(this).attr("checked","checked");
				}else{
					if(wdids.indexOf($(this).val())>-1){
						$(this).attr("checked","checked");
					}
				}
			});
			var ddly = '${entity.syddly}';
			$('input[name="ddly"]').each(function(){//订单来源
				if(ddly == '1'){
					$("#zcdddr").attr("checked","checked");
				}else if(ddly == '2'){
					$("#wbcpsb").attr("checked","checked");
				}else if(ddly == '3'){
					$("#zcdddr").attr("checked","checked");
					$("#wbcpsb").attr("checked","checked");
				}
			});
			//出票渠道
			var kqbjcp = '${entity.kqbjcp}';//开启比价出票 0 关闭 1 开启
			var bjCybjzc = '${entity.bjCybjzc}';//参与比价的政策
			var kqbpet = '${entity.kqbpet}';//开启bpet出票 0 关闭 1 开启
			var kqbop = '${entity.kqbop}';//开启bop 0 关闭 1 开启
			var kqbspet = '${entity.kqbspet}';//开启bspet出票 0 关闭 1 开启
			var bpyxj = '${entity.bpyxj}';//出票优先级
			if(kqbjcp == '1'){
				$("#kqbjcps").show();
				$('input[name="bjCybjzcs"]').each(function(){
					if(bjCybjzc.indexOf($(this).val())>-1){
						$(this).attr("checked","checked");
						if(bjCybjzc.indexOf('BPET')>-1){
							$(".bpetzhhfce").show();
							$("#b2bCpzhs").removeAttr("ignore");
							$("#b2bHfs").removeAttr("ignore");
							$("#bjCgjjc").removeAttr("ignore");
						}
					}
				});
			}
			if(kqbpet == '1'){
				$("#kqbpets").show();
			}
			if(kqbop == '1'){
				$("#kqbops").show();
			}
			if(kqbspet == '1'){
				$("#kqbspets").show();
			}
			if(bpyxj!='' && bpyxj!=null && bpyxj!=undefined){//出票优先级设置回显
				var bpyxjarray= new Array(); 
				bpyxjarray = bpyxj.split("/");
				for(var i=0 ; i<bpyxjarray.length ;i++){
					if(bpyxjarray[i] == 'BJCP'){
						$("#kqbjcpc").attr("checked","checked");
						changekqbjcp();
					}else if(bpyxjarray[i] == 'BPET'){
						$("#kqbpetc").attr("checked","checked");
						changebpet();
					}else if(bpyxjarray[i] == 'BOP'){
						$("#kqbopc").attr("checked","checked");
						changekqbop();
					}else if(bpyxjarray[i] == 'BSPET'){
						$("#kqbspetc").attr("checked","checked");
						changekqbspet();
					}
				}
			}
 		})
 		//关闭当前页面
 		function closecpgz(){
 			window.close();
 		}
 		
 		function savecpgz(){//保存出票规则设置
 			var ddlys = '';
 			$('input[name="ddly"]:checked').each(function(){
				if(ddlys == ''){
					ddlys = $(this).val();
				}else{
					ddlys +="/"+$(this).val();
				}
			});
			var wdids = '';
			$('input[name="wdids"]:checked').each(function(){
				if($(this).val() != '---'){
					if(wdids == ''){
						wdids = $(this).val();
					}else{
						wdids +="/"+$(this).val();
					}
				}
			});
			if($("#allwd").is(":checked")){
				$("#wdid").val("---");
			}else{
				$("#wdid").val(wdids);
			}
 			var sfkabjsz = $("#jjcjflag").val();
 			if(sfkabjsz == '1'){
 				$("#jjcj").val($("#jjcj1").val());
 			}else if(sfkabjsz == '2'){
 				$("#jjcj").val($("#jjcj2").val());
 			}else{
 				$("#jjcj").val('0');
 			}
 			if(wdids == ''){
 				layer.msg('至少选择一个适用的网店!',2,3);
 			}else if(ddlys == ''){
 				layer.msg('至少选择一个适用的订单来源!',2,3);
 			}else{
 				if(ddlys == '1/2'){
 					$("#syddly").val("3");
 				}else{
 					$("#syddly").val(ddlys);
 				}
	 			if(validator.check()){
		 			var cpmc = $("#gzmc").val();
		 			cpmc = encodeURI(encodeURI(cpmc));//中文转码
		 			$("#gzmc").val(cpmc);
		 			var bnzdcp = $("#bnzdcp").val();
		 			bnzdcp = encodeURI(encodeURI(bnzdcp));//中文转码
		 			$("#bnzdcp").val(bnzdcp);
	 				var str = '';
	 				$('font[class="cpyxjsz"]').each(function(){
	 					if(str == ''){
	 						if($(this).text() == '比价出票'){
	 							str = 'BJCP'
	 						}else{
	 							str = $(this).text();
	 						}
	 					}else{
	 						if($(this).text() == '比价出票'){
	 							str+="/"+'BJCP';
	 						}else{
	 							str+="/"+$(this).text();
	 						}
	 					}
	 				});
	 				$("#bpyxjsz").val(str);
		 			var gzmc = $("#gzmc").val();
		 			var id = $("#zj").val();
		 			var hszhflag = valihszhzfcz();
		 			$.ajax({
	 						type : 'GET',
	 						url : '${ctx}/vedsb/jpzdcp/jpzdcpgzsz/verifyOnly',
	 						data : {"gzmc":gzmc,"id" : id},
	 						success: function(msg){
						     if(msg == '0'&&$("#zj").val() == ''){
						     	$("#gzmc").focus();
						     	layer.msg('出票规则名称不能重复!',2,3);
						     }else{
						     	if($("#kqbjcpc").is(':checked')){
				 					var s= '';
					 				$('input[name="bjCybjzcs"]:checked').each(function(){
											s+=$(this).val();
									});
									if(s == ''){
										layer.msg('至少选择一个参与比价的政策!',2,3);
									}else if(hszhflag == '0'){
				 						layer.msg('请选择航司对应的出票账号!',2,3);
				 					}else{
										lay = layer.load('系统正在处理你的操作,请稍后!');
										var hcsytjval = $("#hcsytj").serialize();
							 			var cpgzptbjval = $("#cpgzptbj").serialize();
							 			var zdcpgzszval = $("#zdcpgzsz").serialize();
							 			var forma = $("#cpgzszform").attr("action");
							 			forma = forma +'?' + zdcpgzszval + '&' + hcsytjval+'&' +cpgzptbjval;
							 			$("#cpgzszform").attr("action",forma);
		 								$("#cpgzszform").submit();
									}
				 				}else{
				 					if((!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))&&(!$("#kqbjcpc").is(":checked"))){
				 						layer.msg('至少选择一个出票渠道!',2,3);
				 					}else if(hszhflag == '0'){
				 						layer.msg('请选择航司对应的出票账号!',2,3);
				 					}else{
				 						lay = layer.load('系统正在处理你的操作,请稍后!');
				 						var hcsytjval = $("#hcsytj").serialize();
							 			var cpgzptbjval = $("#cpgzptbj").serialize();
							 			var zdcpgzszval = $("#zdcpgzsz").serialize();
							 			var forma = $("#cpgzszform").attr("action");
							 			forma = forma +'?' + zdcpgzszval + '&' + hcsytjval+'&' +cpgzptbjval;
							 			$("#cpgzszform").attr("action",forma);
		 								$("#cpgzszform").submit();
				 					}
				 				}
						     }
						    }
	 					});
	 			}else{
	 				validator.submitForm(false);
	 			}
 			}
 		}
 		
 		function laststep(index){
 			if(index == 3){//最后一页往上翻
 				if(validator.check()){
	 				$("#cpgzptbjtab").hide();
	 				$("#zdcpgzsztab").hide();
	 				$("#hcsytjtab").show();
	 				validator = $('#hcsytj').Validform({
						tiptype:3
					});
 				}else{
 					validator.submitForm(false);
 				}
 			}else if(index == 2){//中间也往上翻
 				if(validator.check()){
 					$("#cpgzptbjtab").hide();
	 				$("#zdcpgzsztab").show();
	 				$("#hcsytjtab").hide();
	 				validator = $('#zdcpgzsz').Validform({
							tiptype:3
					})
 				}else{
 					validator.submitForm(false);
 				}
 			}
 		}
 		
 		function nextstep(index){
 			if(index == 2){
 				if(validator.check()){
	 				$("#cpgzptbjtab").show();
	 				$("#zdcpgzsztab").hide();
	 				$("#hcsytjtab").hide();
	 				validator = $('#cpgzptbj').Validform({
						tiptype:3
					});
 				}else{
 					validator.submitForm(false);
 				}
 			}else if(index == 1){
 				var ddlys = '';
 				var wdids = '';
 				var gzmc = $("#gzmc").val();
 				var id = $("#zj").val();
 				var hszhflag = valihszhzfcz();
 				if(validator.check()){
 					$('input[name="ddly"]:checked').each(function(){
						if(ddlys == ''){
							ddlys = $(this).val();
						}else{
							ddlys +="/"+$(this).val();
						}
					});
					$('input[name="wdids"]:checked').each(function(){
						if(wdids == ''){
							wdids = $(this).val();
						}else{
							wdids +="/"+$(this).val();
						}
					});
					if(wdids == ''){
						layer.msg('至少选择一个适用的网店!',2,3);
					}else if(ddlys == ''){
						layer.msg('至少选择一个适用的订单来源!',2,3);
					}else{
	 					$.ajax({
	 						type : 'GET',
	 						url : '${ctx}/vedsb/jpzdcp/jpzdcpgzsz/verifyOnly',
	 						data : {"gzmc":gzmc,"id" : id},
	 						success: function(msg){
						     if(msg == '0'&&$("#zj").val() == ''){
						     	$("#gzmc").focus();
						     	layer.msg('出票规则名称不能重复!',2,3);
						     }else{
						     	if($("#kqbjcpc").is(':checked')){
				 					var s= '';
					 				$('input[name="bjCybjzcs"]:checked').each(function(){
											s+=$(this).val();
									});
									if(s == ''){
										layer.msg('至少选择一个参与比价的政策!',2,3);
									}else if(hszhflag == '0'){
				 						layer.msg('请选择航司对应的出票账号!',2,3);
				 					}else{
										$("#cpgzptbjtab").hide();
						 				$("#zdcpgzsztab").hide();
						 				$("#hcsytjtab").show();
						 				validator = $('#hcsytj').Validform({
											tiptype:3
										});
									}
				 				}else{
				 					if((!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))&&(!$("#kqbjcpc").is(":checked"))){
				 						layer.msg('至少选择一个出票渠道!',2,3);
				 					}else if(hszhflag == '0'){
				 						layer.msg('请选择航司对应的出票账号!',2,3);
				 					}else{
				 						$("#cpgzptbjtab").hide();
						 				$("#zdcpgzsztab").hide();
						 				$("#hcsytjtab").show();
						 				validator = $('#hcsytj').Validform({
											tiptype:3
										});
				 					}
				 				}
						     }
						    }
	 					});
 					}
 				}else{
 					validator.submitForm(false);
 				}
 			}
 		}
 		
 		function changecpszclx(obj){
 			var cpszdbj = '';
 			$('input[name="ptcpszdbj"]:checked').each(function(){
 				if(cpszdbj == ''){
 					cpszdbj = $(this).val();
 				}else{
 					cpszdbj+="/"+$(this).val();
 				}
 			
 			});
 			$("#zdbjzclx").val(cpszdbj);
 		}
 		
 		function changetbzclx(obj){
 			var tbszdbj = '';
 			$('input[name="zdbjtb"]:checked').each(function(){
 				if(tbszdbj == ''){
 					tbszdbj = $(this).val();
 				}else{
 					tbszdbj+="/"+$(this).val();
 				}
 			});
 			$("#cytbbjzclx").val(tbszdbj);
 		}
 		
 		function sfkqhbm(obj){
 			var $obj = $(obj);
 			if($obj.is(':checked')){
 				$("#sfkqhbmcc").val("1");
 			}else{
 				$("#sfkqhbmcc").val("0");
 			}
 		}
 		
 		//适用班期 格式1/2/3/4/5/6/7
		function doSybq(obj){
			var sybq=obj.value;
			var code=getEvent().keyCode;
			if(code>=49&&code<=55){
				code=code-48;
			}else if(code>=97&&code<=103){
				code=code-96;
			}else{
				obj.value=setSybqVal(sybq);
				return ;
			}
			if(sybq.indexOf(code)>-1){
				obj.value=setSybqVal(sybq.replace(code,""));
			}else{
				obj.value=setSybqVal(sybq+""+code);
			}
		}
		
		function setSybqVal(sybq){
			var retVal="";
			for(var i=1;i<=7;i++){
				if(sybq.indexOf(i)>-1){
				 retVal+="/"+i;
				}
			}
			if(retVal.indexOf("/")==0){
				retVal=retVal.substring(1)
			}
			return retVal;
		}
		
		function getEvent(){ 
     		if (document.all && window.event) return window.event;
		     	func = getEvent.caller;
		     	while (func != null) {
		        var arg0 = func.arguments[0];
		         if (arg0) {
		             if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
		                 return arg0;
		             }
		         }
		         func = func.caller;
		     }
     		return null;
		}
		
		function changecybjze(obj){
			var $obj = $(obj);
			if($obj.val() == 'BPET'){
				if($obj.is(':checked')){
					$(".bpetzhhfce").show();
					$("#b2bCpzhs").removeAttr("ignore");
					$("#b2bHfs").removeAttr("ignore");
					$("#bjCgjjc").removeAttr("ignore");
				}else{
					$("#b2bCpzhs").val('');
					$("#b2bHfs").val('');
					$("#bjCgjjc").val('');
					$(".bpetzhhfce").hide();
					$("#b2bCpzhs").attr("ignore","ignore");
					$("#b2bHfs").attr("ignore","ignore");
					$("#bjCgjjc").attr("ignore","ignore");
				}
			}
			var bjbjcs = '';
			$('input[name="bjCybjzcs"]:checked').each(function(){
				if(bjbjcs == ''){
					bjbjcs = $(this).val();
				}else{
					bjbjcs +="/"+$(this).val();
				}
			});
			$("#bjCybjzc").val(bjbjcs);
		}
		//是否开启比价
		function changekqbjcp(){
			var $obj = $("#kqbjcpc");
			if($obj.is(':checked')){
				$("#kqbjcps").show();
				$("#kqbjcp").val(1);
				if((!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#dtchoose").append('<font id="bjcp0" class="cpyxjsz" bjattr="BJCP">比价出票</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj0"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp0" class="cpyxjsz" bjattr="BJCP">比价出票</font>');
				}
				$("#cpyxjsztr").show();
			}else{
				if((!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#bjcp0").remove();
					$("#cpyxjsztr").hide();
				}else{
					var chil = $("#dtchoose").children().first();
					var s = chil.text();
					if(s == '比价出票'){
						chil.next().remove();
					}
					$("#bjcp0").remove();
					$("#kzyxj0").remove();
				}
				$('input[name="bjCybjzcs"]').each(function(){
						$(this).removeAttr("checked");
				});
				$(".bpetzhhfce").hide();
				$("#b2bCpzhs").val('');
				$("#b2bHfs").val('');
				$("#bjCgjjc").val('');
				$("#bjCpfdwc").val('');
				$("#kqbjcps").hide();
				$("#kqbjcp").val(0);
				$("#bjCybjzc").val('');
				$("#b2bCpzhs").attr("ignore","ignore");
				$("#b2bHfs").attr("ignore","ignore");
				$("#bjCgjjc").attr("ignore","ignore");
				$("#bjCpfdwc").attr("ignore","ignore");
			}
		}
		//是否开启bpet
		function changebpet(){
			var $obj = $("#kqbpetc");
			if($obj.is(':checked')){
				$("#kqbpets").show();
				$("#kqbpet").val(1);
				$("#b2bCpzh").removeAttr("ignore");
				$("#b2bHf").removeAttr("ignore");
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#dtchoose").append('<font id="bjcp1" class="cpyxjsz">BPET</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj1"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp1" class="cpyxjsz">BPET</font>');
				}
				$("#cpyxjsztr").show();
			}else{
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#cpyxjsztr").hide();
					$("#bjcp1").remove();
				}else{
					var chil = $("#dtchoose").children().first();
					var s = chil.text();
					if(s == 'BPET'){
						chil.next().remove();
					}
					$("#bjcp1").remove();
					$("#kzyxj1").remove();
				}
				$("#b2bCpzh").val('').attr("ignore","ignore");
				$("#b2bHf").val('').attr("ignore","ignore");
				$("#kqbpets").hide();
				$("#kqbpet").val(0);
			}
		}
		//是否开启bop
		function changekqbop(){
			var $obj = $("#kqbopc");
			if($obj.is(':checked')){
				$("#kqbops").show();
				$("#kqbop").val(1);
				$("#bopoffice").removeAttr("ignore");
				$("#bopagent").removeAttr("ignore");
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbpetc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#dtchoose").append('<font id="bjcp2" class="cpyxjsz">BOP</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj2"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp2" class="cpyxjsz">BOP</font>');
				}
				$("#cpyxjsztr").show();
			}else{
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbpetc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
					$("#cpyxjsztr").hide();
					$("#bjcp2").remove();
				}else{
					var chil = $("#dtchoose").children().first();
					var s = chil.text();
					if(s == 'BOP'){
						chil.next().remove();
					}
					$("#bjcp2").remove();
					$("#kzyxj2").remove();
				}
				$("#bopoffice").attr("ignore","ignore").val('');
				$("#bopagent").attr("ignore","ignore").val('');
				$("#kqbops").hide();
				$("#kqbop").val(0);
			}
		}
		//是否开启bspet
		function changekqbspet(){
			var $obj = $("#kqbspetc");
			if($obj.is(':checked')){
				$("#kqbspets").show();
				$("#kqbspet").val(1);
				$("#bspetoffice").removeAttr("ignore");
				$("#bspetagent").removeAttr("ignore");
				$("#bspetprintno").removeAttr("ignore");
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))){
					$("#dtchoose").append('<font id="bjcp3" class="cpyxjsz">BSPET</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj3"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp3" class="cpyxjsz">BSPET</font>');
				}
				$("#cpyxjsztr").show();
			}else{
				if((!$("#kqbjcpc").is(":checked"))&&(!$("#kqbpetc").is(":checked"))&&(!$("#kqbopc").is(":checked"))){
					$("#cpyxjsztr").hide();
					$("#bjcp3").remove();
				}else{
					var chil = $("#dtchoose").children().first();
					var s = chil.text();
					if(s == 'BSPET'){
						chil.next().remove();
					}
					$("#bjcp3").remove();
					$("#kzyxj3").remove();
				}
				$("#bspetoffice").attr("ignore","ignore").val(' ');
				$("#bspetagent").attr("ignore","ignore").val(' ');
				$("#bspetprintno").attr("ignore","ignore").val(' ');
				$("#kqbspets").hide();
				$("#kqbspet").val(0);
			}
		}
		
		//osi项检查
		function changecheck(obj){
			var str='';
			$('input[name="checkosis"]').each(function(){
				if($(this).is(":checked")){
					if(str == ''){
						str = $(this).val();
					}else{
						str +="/"+$(this).val();
					}
				}
			});
			if(str!=''){
				$("#checkosi").val(str);
			}else{
				$("#checkosi").val(0);
			}
		}
		
		//航程类型切换
		function changehclx(index){
			if(index == '1'){//单程
				$("#dczzcity").hide();
				$("#inputzzcity").attr("ignore","ignore");
				$("#inputzzcity").val('');
				$("#tlts").hide();
			}else if(index == '2'){//往返
				$("#dczzcity").hide();
				$("#inputzzcity").attr("ignore","ignore");
				$("#inputzzcity").val('');
				$("#tlts").show();
			}else if(index == '3'){//联程
				$("#dczzcity").show();
				$("#inputzzcity").removeAttr("ignore");
				$("#tlts").show();
			}else if(index == '4'){
				$("#dczzcity").show();
				$("#inputzzcity").removeAttr("ignore");
				$("#tlts").show();
			}
		}
		
		function choosecpzh(index){
			var hkgss = $("#cphkgs").val();
			var b2bCpzh = '';
			if(index == '1'){
				b2bCpzh = $("#b2bCpzhs").val();
			}else if(index == '2'){
				b2bCpzh = $("#b2bCpzh").val(); 
			}
			if(hkgss == '' || hkgss == null || hkgss == '---'){
				layer.msg('航空公司不能为空!',2,3);
			}else{
			   var url ="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/getb2bzh?hgks="+hkgss+"&index="+index+"&b2bCpzh="+b2bCpzh;
			   $.layer({
					type: 2,
					title: ['选择出票账号'],
					area: ['450px', '300px'],
					iframe: {src: url}
			   }); 
			}
		}
		
		function changesz(obj){//出票优先级设置
			var $obj = $(obj);
			var objstrid = $obj.parent().attr("id");
			var objstr = objstrid.substring(0,objstrid.length-1);
			var befores = $obj.parent().next();//后一个
			var afters = $obj.parent().prev();//前一个
			var temp0 = befores.text();
			var temp1 = befores.attr("id");
			var beforestr = afters.attr("id").substring(temp1.length-1);
			var str = objstr + beforestr;
			$obj.parent().attr("id",str);
			befores.text(afters.text()).attr("id",afters.attr("id"));;
			afters.text(temp0).attr("id",temp1);
			var pp = afters.prev().attr("id");
			if(pp!=undefined){
				var s0 = pp.substring(0,pp.length-1);
				var bb = $obj.parent().prev().attr("id");
				var s1 = bb.substring(bb.length-1);
				$obj.parent().prev().prev().attr("id",s0+s1);
			}
			
		}
		
		function cplxyxjpx(obj){//出票类型优先级设置
			var parsent = $(obj).parent();
			var beforejd = parsent.prev().text();
			var afterjd = parsent.next().text();
			$("#cplxyxjpxbsp").text(afterjd);
			$("#cplxyxjpxbp").text(beforejd);
			
			var str = $("#cplxyxjpxbsp").text() + "/" + $("#cplxyxjpxbp").text();
			$("#ptbjCplxyxj").val(str);
		}
		
		function ptcplxyxjpx(obj){//采购平台优先级别
			var parsent = $(obj).parent();
			var beforejd = parsent.prev();
			var afterjd = parsent.next();
			var beforejdattr = beforejd.attr("ptid");
			var beforejdtext = beforejd.text();
			var afterjdattr = afterjd.attr("ptid");
			beforejd.text(afterjd.text());
			beforejd.attr("ptid",afterjd.attr("ptid"));
			afterjd.text(beforejdtext);
			afterjd.attr("ptid",beforejdattr);
			var str = '';
			$('font[class="each"]').each(function(){
				if(str == ''){
					str = $(this).attr("ptid");
				}else{
					str += "/" + $(this).attr("ptid");
				}
			});
			$("#ptbjPtyxj").val(str);
		}
		
		function ptzdbjyxj(obj){//平台自动比价优先级
			var parsent = $(obj).parent();
			var beforejd = parsent.prev();
			var afterjd = parsent.next();
			var beforejdattr = beforejd.attr("ptzdbjyxj");
			var beforejdtext = beforejd.text();
			beforejd.text(afterjd.text());
			beforejd.attr("ptzdbjyxj",afterjd.attr("ptzdbjyxj"));
			afterjd.text(beforejdtext);
			afterjd.attr("ptzdbjyxj",beforejdattr);
			var str = '';
			$('font[class="eachptzdbj"]').each(function(){
				if(str == ''){
					str = $(this).attr("ptzdbjyxj");
				}else{
					str += "/" + $(this).attr("ptzdbjyxj");
				}
			});
			$("#ptbjZdxjyxj").val(str);
		}
		
		function changezdcp(obj){//选择自动出票
			var id = $(obj).val();
			window.location.href="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/cpgzEdit?id="+id;
		}
		
		function valiHszh(obj){
			var hkgs = $("#cphkgs").val();
			if(hkgs == '' || hkgs == null){
				return;
			}
			if($("#bjCybjzc0").is(":checked")){//比价
				var bjhszg = $("#b2bCpzhs").val();
				if(bjhszg!=''||bjhszg!=null){
					var bjhszgarray = bjhszg.split("/");
					var bjhkgsarray = hkgs.split("/");
					var s="";
					for(var i=0;i<bjhszgarray.length;i++){
						var zhs = bjhszgarray[i].split(":");
						if(hkgs.indexOf(zhs[0])>-1){
							if(s== ""){
								s = bjhszgarray[i];
							}else{
								s+="/"+bjhszgarray[i];
							}
						}
					}
					$("#b2bCpzhs").val(s);
				}
			}
			if($("#kqbpetc").is(":checked")){//开启bpet设置航司账号
				var bjhszg = $("#b2bCpzh").val();
				if(bjhszg!=''||bjhszg!=null){
					var bjhszgarray = bjhszg.split("/");
					var bjhkgsarray = hkgs.split("/");
					var s="";
					for(var i=0;i<bjhszgarray.length;i++){
						var zhs = bjhszgarray[i].split(":");
						if(hkgs.indexOf(zhs[0])>-1){
							if(s== ""){
								s = bjhszgarray[i];
							}else{
								s+="/"+bjhszgarray[i];
							}
						}
					}
					$("#b2bCpzh").val(s);
				}
			}
		}
		
		function valihszhzfcz(){
			var hszh = $("#cphkgs").val();
			if($("#bjCybjzc0").is(":checked")){//比价
				var bjhszg = $("#b2bCpzhs").val();
				if(bjhszg==null||bjhszg==''){
					return '0'
				}else{
					var hsarray = hszh.split("/");
					for(var i=0;i<hsarray.length;i++){
						if(bjhszg.indexOf(hsarray[i])<0){
							return '0'
						}
					}
				}
			}
			if($("#kqbpetc").is(":checked")){//比价
				var bjhszg = $("#b2bCpzh").val();
				if(bjhszg==null||bjhszg==''){
					return '0'
				}else{
					var hsarray = hszh.split("/");
					for(var i=0;i<hsarray.length;i++){
						if(bjhszg.indexOf(hsarray[i])<0){
							return '0'
						}
					}
				}
			}
			return '1';
		}
		
		function iskqtskhb(index){
			if(index == '1'){
				$("#showorhidejjcj1").show();
				$("#showorhidejjcj2").hide();
				$("#jjcjflag").val('1');
			}else if(index == '2'){
				$("#showorhidejjcj1").hide();
				$("#showorhidejjcj2").show();
				$("#jjcjflag").val('2');
			}else{
				$("#showorhidejjcj1").hide();
				$("#showorhidejjcj2").hide();
				$("#jjcjflag").val('0');
				$("#jjcj").val(0);
			}
		}
		
		function checkone(){
			var wdsize = $("#wdsize").val();
			var i = 0;
			$('input[name="wdids"]:checked').each(function(){
				i = i+1
			});
			if($("#allwd").is(":checked")){
				i= i-1;
			}
			if(i == wdsize){
				$('input[name="wdids"]').each(function(){
					$("#allwd").attr("checked","checked");
				});
			}else{
				$("#allwd").removeAttr("checked");
			}
		}
		
		function checkAll(obj){
			if($("#allwd").is(":checked")){
				$('input[name="wdids"]').each(function(){
					$(this).attr("checked","checked");
				});
			}else{
				$('input[name="wdids"]').each(function(){
					$(this).removeAttr("checked");
				});
			}
		}
 	</script>
  </head>
  <body>
    <div class="container">
      <div id="forms">
        <div class="box">
          <div class="box_border">
            <div class="box_center">
            <form action="${ctx}/vedsb/jpzdcp/jpzdcpgzsz/saveCpgz" method="POST" id="cpgzszform">
            <input type="hidden" name="close" value="true"/>
            <input type="hidden" name="callback"  value="parent.refresh()" />
            <input type="hidden" name="id" value="${entity.id}" id="zj"/>
            </form>
            <!-- 自动出票规则设置开始 -->
            	<form id="zdcpgzsz">
            		<table  class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="zdcpgzsztab">
            			<tr>
            				<td class="td_rightzdcp">自动出票规则名称：</td>
            				<td>
            					<input type="text" name="gzmc" size=12 value="${entity.gzmc}" datatype="*" id="gzmc"><font color="red">*</font>&nbsp;&nbsp;&nbsp;
            					<select id="selectgz" onchange="changezdcp(this)">
            						<option value="">==选择自动出票规则==</option>
            						<c:forEach items="${ids}" var="ids">
            						<option value="${ids.id}" ${ids.id eq cpgzid ? 'selected' : '' }>${ids.gzmc}</option>
            						</c:forEach>
            						<font color="gray">(可以选择已有的自动出票规则，如果选择后，修改了部分信息，则保存为新自动出票规则)</font>
            					</select>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">适用时间段：</td>
            				<td>
            					<input type="text" name="sysjs" size=5 value="${entity.sysjs}" datatype="*,/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/"/><font color="red">*</font>&nbsp;&nbsp;至&nbsp;&nbsp;
            					<input type="text" name="sysjz" size=5 value="${entity.sysjz}" datatype="*,/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/"/><font color="red">*</font><font color="gray">(时间格式：00:00，例00:00至23:59)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">航空公司：</td>
            				<td>
            					<input type="text" name="hkgs" id="cphkgs" size=12 value="${not empty entity.hkgs ? entity.hkgs : '---'}" datatype="*,multihs" 
            					onblur="valiHszh(this)" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(航司二字码，可设置多个，使用/分隔，---表示全部航司适用)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">适用政策代码：</td>
            				<td>
            					<input type="text" name="zcdm" size=18 value="${entity.zcdm}"/><font color="gray">(多个使用"/"分隔，如ptzC/Tjzc，注严格区分大小写)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">适用订单来源：</td>
            				<td>
            					<input type="checkbox" id="zcdddr" value="1" name="ddly" ${empty entity.syddly ? 'checked' : '' }/><label for="zcdddr">正常导入订单</label>
            					<input type="checkbox" id="wbcpsb" value="2" name="ddly"/><label for="wbcpsb">外部出票失败订单</label><font color="red">*</font>
            					<input type="hidden" name="syddly" id="syddly" value="${entity.syddly}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">适用网店：</td>
            				<td>
            					<input type="checkbox" id="allwd" value="---" name="wdids" onclick="checkAll(this)"/><label for="allwd">全部网店</label>
            					<c:forEach items="${jpWdZlsz}" var="s">
            						<input type="checkbox" name="wdids" value="${s.id}" id="${s.id}" onclick="checkone()"/><label for="${s.id}">${s.wdmc}</label>
            					</c:forEach>
            					<font color="red">*</font>
            					<input type="hidden" name="wdid" id="wdid"/>
            				</td>
            				<input type="hidden" id="wdsize" value="${wdsize}"/>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">单人最小利润：</td>
            				<td>
            					<input type="text" name="zxlr" size=5 value="${entity.zxlr}" datatype="*,number"><font color="gray"><font color="red">*</font>(元，可正可负。BPET或比价出票时，会判断【单人下级结算价 - （单人采购净价 - 后返）】 是否大于等于此值才能自动出票)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">是否选择低价出票：</td>
            				<td>
            					<input type="radio" name="sfdjcp" value="1" id="adjcp" ${entity.sfdjcp eq '1' or empty entity.sfdjcp ? 'checked' : '' }/><label for="adjcp">按低价出票</label>&nbsp;&nbsp;&nbsp;
            					<input type="radio" name="sfdjcp" value="0" id="addzdjcp" ${entity.sfdjcp eq '0' ? 'checked' : '' }/><label for="addzdjcp">按订单账单价出票</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">是否开启与同时刻的航班比价：</td>
            				<td>
            					<input type="radio" name="kqkhbbj" value="0" id="nkqbj" ${entity.kqkhbbj eq '0' or empty entity.kqkhbbj ? 'checked' : '' } onclick="iskqtskhb(0)"/><label for="nkqbj">关闭</label>
            					<div><input type="radio" name="kqkhbbj" value="1" id="kqbj" ${entity.kqkhbbj eq '1' ? 'checked' : '' } onclick="iskqtskhb(1)"/><label for="kqbj">开启同时刻航班比价</label>
            					<span id="showorhidejjcj1">比价差额：<input type="text" value="${empty entity.jjcj ? 0 : entity.jjcj}"} datatype="number" size=5 id="jjcj1"/><font color="gray">(开启同时刻航班比价,差值必须大于或者等于比价差值才按新编码出票)</font></span></div>
            					<div><input type="radio" name="kqkhbbj" value="2" id="gxkqbj" ${entity.kqkhbbj eq '2' ? 'checked' : '' } onclick="iskqtskhb(2)"/><label for="gxkqbj">开启共享航班换主飞航班比价</label>
            					<span id="showorhidejjcj2">比价差额：<input type="text" value="${empty entity.jjcj ? 0 : entity.jjcj}"} datatype="number" size=5 id="jjcj2"/><font color="gray">(开启共享航班换主飞航班比价,差值必须大于或者等于比价差值才按新编码出票)</font></span></div>
            					<input type="hidden" name="jjcj" id="jjcj" value="${empty entity.jjcj ? 0 : entity.jjcj}"/>
            					<input type="hidden" id="jjcjflag" value="${empty entity.jjcj ? 0 : ''}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">出票渠道设置：</td>
            				<td>
            					<input type="checkbox" id="kqbjcpc" onclick="changekqbjcp();"/><label for="kqbjcpc">开启比价出票</label>
            					<input type="hidden" name="kqbjcp" id="kqbjcp" value="${empty entity.kqbjcp ? '0' : entity.kqbjcp}"/>
            				</td>
            			</tr>
            			<tr id="kqbjcps" style="display: none">
            				<td></td>
            				<td>
            					<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
            						<tr>
            							<td class="td_rightzdcp">参与比价的政策：</td>
            							<td>
            								<input type="checkbox" name="bjCybjzcs" id="bjCybjzc0" value="BPET" onclick="changecybjze(this)"/><label for="bjCybjzc0">BPET</label>&nbsp;&nbsp;
            								 <c:forEach items="${vfc:getVeclassLb('100021')}" var="oneLx" varStatus="v">
            								 	<c:if test="${oneLx.id ne 100021}">
            								 		<input type="checkbox" name="bjCybjzcs" id="bjCybjzc${oneLx.ywmc}" value="${oneLx.ywmc}" onclick="changecybjze(this)"/><label for="bjCybjzc${oneLx.ywmc}">${oneLx.mc }</label>&nbsp;&nbsp;
            								 	</c:if>
            								 </c:forEach>
            								<input type="hidden" name="bjCybjzc" value="${entity.bjCybjzc}" id="bjCybjzc"/>
            							</td>
            						</tr>
            						<tr class="bpetzhhfce" style="display: none">
            							<td class="td_rightzdcp">BPET出票账号：</td>
            							<td>
            								<input type="text" name="bjB2bCpzh" id="b2bCpzhs" value="${entity.bjB2bCpzh}" size=24 readonly="readonly" datatype="*" ignore="ignore"/><input type="button" value="选择出票账号" onclick="choosecpzh(1)"/><font color="gray">(可选择多个航司账号，每个航司只能选择一个出票账号)</font>
            							</td>
            						</tr>
            						<tr class="bpetzhhfce" style="display: none">
            							<td class="td_rightzdcp">BPET后返：</td>
            							<td>
            								<input type="text" name="bjB2bHf" id="b2bHfs" value="${entity.bjB2bHf}" size=5 datatype="*,number" ignore="ignore"/><font color="gray">(%)</font>
            							</td>
            						</tr>
            						<tr class="bpetzhhfce" style="display: none">
            							<td class="td_rightzdcp">单人采购净价差额：</td>
            							<td>
            								<input type="text" name="bjCgjjc" id="bjCgjjc" value="${entity.bjCgjjc}" size=5 datatype="*,number" ignore="ignore"/><font color="gray">(元，可正可负，【航司B2B采购净价 - 后返 - OP采购净价】大于等于此值时才选择OP政策出票)</font>
            							</td>
            						</tr>
            						<tr>
            							<td class="td_rightzdcp">OP出票返点最大误差值：</td>
            							<td>
            								<input type="text" name="bjCpfdwc" id="bjCpfdwc" value="${entity.bjCpfdwc}" size=5 datatype="*,number" ignore="ignore"/><font color="gray">(%，可正可负，计算【采购净价/出票账单价】与【原始政策返点】的差值，在设置范围外的不自动出票)</font>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			<tr>
            				<td></td>
            				<td>
            					<input type="checkbox" id="kqbpetc" onclick="changebpet()"/><label for="kqbpetc">开启BPET</label>
            					<input type="hidden" name="kqbpet" id="kqbpet" value="${empty entity.kqbpet ? '0' : entity.kqbpet}"/>
            				</td>
            			</tr>
            			<tr id="kqbpets" style="display: none;">
            				<td></td>
            				<td>
            					<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
            						<tr>
            							<td class="td_rightzdcp">BPET出票账号：</td>
            							<td>
            								<input type="text" name="b2bCpzh" id="b2bCpzh" size=24 value="${entity.b2bCpzh}" readonly="readonly" datatype="*" ignore="ignore"/><input type="button" value="选择出票账号" onclick="choosecpzh(2)"/><font color="gray">(可选择多个航司账号，每个航司只能选择一个出票账号)</font>
            							</td>
            						</tr>
            						<tr>
            							<td class="td_rightzdcp">BPET后返：</td>
            							<td>
            								<input type="text" name="b2bHf" id="b2bHf" size=5 value="${entity.b2bHf}" datatype="*,number" ignore="ignore"/><font color="gray">(%)</font>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			<tr>
            				<td></td>
            				<td>
            					<input type="checkbox" name="kqbopc" id="kqbopc" onclick="changekqbop()"/><label for="kqbopc">开启BOP</label>
            					<input type="hidden" name="kqbop" id="kqbop" value="${empty entity.kqbop ? '0' : entity.kqbop}"/>
            				</td>
            			</tr>
            			<tr id="kqbops" style="display: none">
            				<td></td>
            				<td>
            					<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
            						<tr>
            							<td class="td_rightzdcp">OFFICE号：</td>
            							<td>
            								<select name="bopOffice" datatype="*" class="bopselect" ignore="ignore" id="bopoffice">
            									<option value="">==请选择OFFICE==</option>
            									<c:forEach items="${list}" var="list">
			            						<option value="${list.officeid}" ${list.officeid eq entity.bopOffice ? 'selected' : '' }>${list.officeid}</option>
			            						</c:forEach>
            								</select>&nbsp;&nbsp;&nbsp;
            								<font>工作号：</font>&nbsp;&nbsp;
            								<select name="bopAgent" datatype="*" class="bopselect" ignore="ignore" id="bopagent">
            									<option value="">==请选择工作号==</option>
            									<c:forEach items="${list}" var="list">
			            						<option value="${list.agent}" ${list.agent eq entity.bopAgent ? 'selected' : '' }>${list.agent}</option>
			            						</c:forEach>
            								</select>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			<tr>
            				<td></td>
            				<td>
            					<input type="checkbox" name="kqbspetc" id="kqbspetc" onclick="changekqbspet()"/><label for="kqbspetc">开启BSPET</label>
            					<input type="hidden" name="kqbspet" id="kqbspet" value="${empty entity.kqbspet ? '0' : entity.kqbspet}"/>
            				</td>
            			</tr>
            			<tr id="kqbspets" style="display: none">
            				<td></td>
            				<td>
            					<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0">
            						<tr>
            							<td class="td_rightzdcp">OFFICE号：</td>
            							<td>
            								<select name="bspOffice" datatype="*" ignore="ignore" class="bspselect" id="bspetoffice" >
            									<option value=" ">==请选择OFFICE==</option>
            									<c:forEach items="${list}" var="list">
			            						<option value="${list.officeid}" ${list.officeid eq entity.bspOffice ? 'selected' : '' }>${list.officeid}</option>
			            						</c:forEach>
            								</select>&nbsp;&nbsp;&nbsp;
            								<font>工作号：</font>&nbsp;&nbsp;
            								<select name="bspAgent" datatype="*" ignore="ignore" class="bspselect" id="bspetagent">
            									<option value=" ">==请选择工作号==</option>
           										<c:forEach items="${list}" var="list">
			            						<option value="${list.agent}" ${list.agent eq entity.bspAgent ? 'selected' : '' }>${list.agent}</option>
			            						</c:forEach>
            								</select>&nbsp;&nbsp;
            								<font>打票机号：</font>&nbsp;&nbsp;&nbsp;
            								<select name="bspPrintno" datatype="*" ignore="ignore" class="bspselect" id="bspetprintno">
            									<option value=" ">==请选择打票机号==</option>
            									<c:forEach items="${list}" var="list">
			            						<option value="${list.printno}" ${list.printno eq entity.bspPrintno and not empty list.printno ? 'selected' : '' }>${list.printno}</option>
			            						</c:forEach>
            								</select>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			<tr id="cpyxjsztr" style="display: none;">
            				<td class="td_rightzdcp">出票优先级设置：</td>
            				<td>
            					<font id="dtchoose">
            					</font><font color="gray">(当前一种自动出票失败后，会自动跳转到下一种出票方式进行出票)</font>
            					<input type="hidden" name="bpyxj" value="${entity.bpyxj}" id="bpyxjsz"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">自动出票员：</td>
            				<td>
            					<input type="text" name="choozichp" id="choozichp" datatype="*" value="${zdcpyname}"/><font color="red">*</font>
            					<input type="hidden" name="zdcpy" id="valueTozdcp" value="${entity.zdcpy}"/>
            					<font color="gray">(满足此规则的订单，会使用此出票员锁单)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">自动出票员登录密码：</td>
            				<td>
            					<input type="password" name="zdcpymm" value="${entity.zdcpymm}" size=5 datatype="*"/><font color="red">*</font><font color="gray">(必须与系统登录密码、PID密码一致，否则将导致BSPET自动出票时不能出票)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_center" colspan="2">
            					<input type="button" value="${empty entity.id ? '下一步' : '下一页' }" class="ext_btn ext_btn_submit" onclick="nextstep(1)"/>
								<c:if test="${not empty entity.id}">
									<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>
								</c:if>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            <!-- 自动出票规则设置结束 -->
            <!-- 航程适用条件开始 -->
            	<form id="hcsytj" >
            		<table  class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="hcsytjtab" style="display: none">
            			<tr>
            				<td class="td_righthclx">航程类型：</td>
	            			<td>
	            				<input type="radio" name="hclx" value="1" id="hclxdc" ${entity.hclx eq '1' or empty entity.hclx? 'checked' : '' } onclick="changehclx('1')"/><label for="hclxdc">单程</label>
	            				<input type="radio" name="hclx" value="2" id="hclxwf" ${entity.hclx eq '2' ? 'checked' : '' } onclick="changehclx('2')"/><label for="hclxwf">往返</label>
	            				<input type="radio" name="hclx" value="3" id="hclxlc" ${entity.hclx eq '3' ? 'checked' : '' } onclick="changehclx('3')"/><label for="hclxlc">联程</label>
	            				<input type="radio" name="hclx" value="4" id="hclxqk" ${entity.hclx eq '4' ? 'checked' : '' } onclick="changehclx('4')"/><label for="hclxqk">缺口</label>
	            			</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">适用乘机人类型：</td>
            				<td>
            					<input type="radio" name="cjrlx" value="1" id="cjrlxcr" ${entity.cjrlx eq '1' or empty entity.cjrlx ? 'checked' : '' }/><label for="cjrlxcr">成人</label>
            					<input type="radio" name="cjrlx" value="2" id="cjrlxet" ${entity.cjrlx eq '2' ? 'checked' : '' }/><label for="cjrlxet">儿童</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">乘机日期：</td>
            				<td>
            					<input type="text" name="cjrqs" value="${entity.cjrqs}" size=10 id="cjrqs" onFocus="WdatePicker({onpicked:function(){cjrqz.focus();},minDate:'cal.getDateStr()'})"/>&nbsp;&nbsp;
            					至&nbsp;&nbsp;<input type="text" name="cjrqz" value="${entity.cjrqz}" size=10 id="cjrqz" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'cjrqs\')}'})"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">不享受日期：</td>
            				<td>
            					<input type="text" name="bxsrq" value="${entity.bxsrq}" size=20 onclick="_bsyrqFocus(this);" id="tjzcForm_jp_jzyxcp_cpsytj_bsyrq"/><font color="gray">(格式：YYYY-MM-DD，多个使用"/"分隔)</font>
            					<!-- 不适用日期div显示隐藏 -->	 
								<div id="cpsytj_bsyrqDiv" style="display: none;top:121px; left:139px;position:absolute;width:200px;height:200px;" align="right"></div>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号适用条件：</td>
            				<td>
            					<input type="radio" name="hbhsfsy" value="0" id="hbhqbsy" ${entity.hbhsfsy eq '0' or empty entity.hbhsfsy ? 'checked' : '' }/><label for="hbhqbsy">全部适用</label>
            					<input type="radio" name="hbhsfsy" value="1" id="hbhsy" ${entity.hbhsfsy eq '1' ? 'checked' : '' }/><label for="hbhsy">适用</label>
            					<input type="radio" name="hbhsfsy" value="2" id="hbhbsy" ${entity.hbhsfsy eq '2' ? 'checked' : '' }/><label for="hbhbsy">不适用</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号：</td>
            				<td>
            					<input type="text" name="hbh" value="${entity.hbh}" size=18 datatype="*,multihbh" onkeyup="this.value=this.value.toUpperCase()"/><font color="red">*</font><font color="gray">(多个使用/分隔)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">共享航班是否适用：</td>
            				<td>
            					<input type="radio" name="gxhbsfsy" value="1" id="gxhbhsy" ${entity.gxhbsfsy eq '1'  or empty entity.gxhbsfsy ? 'checked' : '' }/><label for="gxhbhsy">适用</label>
            					<input type="radio" name="gxhbsfsy" value="0" id="gxhbhbsy" ${entity.gxhbsfsy eq '0' ? 'checked' : '' }/><label for="gxhbhbsy">不适用</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">出发城市：</td>
            				<td><input type="text" value="${empty entity.cfcity ? '---' : entity.cfcity }" name="cfcity" size=18 datatype="*,multicity" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr style="display: none" id="dczzcity">
            				<td class="td_righthclx">中转城市：</td>
            				<td><input type="text" value="${empty entity.zzcity ? '---' : entity.zzcity}" name="zzcity" size=18 datatype="*,multicity" ignore="ignore" id="inputzzcity" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">到达城市：</td>
            				<td><input type="text" value="${empty entity.ddcity ? '---' : entity.ddcity}" name="ddcity" size=18 datatype="*,multicity" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">舱位：</td>
            				<td><input type="text" value="${empty entity.cw ? '---' : entity.cw}" name="cw" size=18 datatype="*,multicw" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">适用班期：</td>
            				<td><input type="text" value="${empty entity.sybq ? '1/2/3/4/5/6/7' : entity.sybq}" size=10 name="sybq" onKeyDown="doSybq(this);" onKeyUp="doSybq(this);" onchange="doSybq(this);"/><font color="gray">(多个使用/分隔,只能输入数字1-7,重复输入即可删掉输入的数字)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">最晚提前出票天数：</td>
            				<td>
            					<input type="text" name="zwtqcpts" value="${entity.zwtqcpts}" size=5 datatype="*,number,minvalue,maxvalue" minvalue=0 maxvalue=999 id="zwtqcpts"/><font color="red">*</font><font color="gray">(天)</font>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>最早提前出票天数：&nbsp;</font> <input type="text" name="zztqcpts" value="${empty entity.zztqcpts ? 365:entity.zztqcpts}" size=5 datatype="*,number,minvalue,maxvalue" minvalue=0 maxvalue=999 id="zztqcpts"/><font color="red">*</font><font color="gray">(天)</font>
            				</td>
            			</tr>
            			<tr style="display: none" id="tlts">
            				<td class="td_righthclx">最短停留天数：</td>
            				<td>
            					<input type="text" name="zdtlts" value="${entity.zdtlts}" size=5 datatype="number" id="zdtlts"/><font color="gray">(天)</font>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>&nbsp;&nbsp;&nbsp;&nbsp;最长停留天数：&nbsp;</font> <input type="text" name="zctlts" value="${entity.zctlts}" size=5 datatype="number" id="zctlts"/><font color="gray">(天)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">订单最少旅客人数限制：</td>
            				<td>
            					<input type="text" name="zslkrs" value="${entity.zslkrs}" datatype="number,dotformat,minvalue" dotformat="###." minvalue=1 size=5/><font color="gray">(人)(最小值为1，订单人数需要大于等于此值)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">协议号：</td>
            				<td><input type="text" name="xyh" value="${entity.xyh }" size=16/><font color="gray">(支持录入多个协议号，使用英文"/"分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">乘机人名中含：</td>
            				<td><input type="text" name="bnzdcp" value="${entity.bnzdcp}" size=16 id="bnzdcp"/>字符不能自动出票<font color="gray">(支持录入多个，使用英文"/"分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">OSI项检查：</td>
            				<td>
            					<input type="checkbox" value="1" name="checkosis" id="checksjh" onclick="changecheck(this)"><label for="checksjh">检查订单乘机人手机号</label>
			                   	<input type="checkbox" value="2" name="checkosis" id="checkctct" onclick="changecheck(this)"><label for="checkctct">检查PNR内容CTCT项</label>
			                   	<input type="hidden" name="sfjcosi" id="checkosi" value="${empty entity.sfjcosi ? '0' : entity.sfjcosi}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">是否换编码出票：</td>
            				<td>
            					<input type="radio" value="0" name="sfhpnr" id="nhbmcp" ${entity.sfhpnr eq '0' or empty entity.sfhpnr ? 'checked' : ''  }><label for="nhbmcp">否</label>
            					<input type="radio" value="1" name="sfhpnr" id="hbmcp" ${entity.sfhpnr eq '1' ? 'checked' : ''  }><label for="hbmcp">是</label>&nbsp;&nbsp;
            					<input type="checkbox" id="kqybmcc" ${entity.ypnrcc eq '0' or empty entity.ypnrcc  ? '' : 'checked' } onclick="sfkqhbm(this);"/><label for="kqybmcc">开启原编码重出</label>
            					<input type="hidden" name="ypnrcc" value="${empty entity.ypnrcc ? '0' : entity.ypnrcc}" id="sfkqhbmcc"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">换编码OSI项：</td>
            				<td>
            					<input type="text" name="hbmosi" value="${entity.hbmosi}" size=9/>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>换编码OFFICE：</font>&nbsp;&nbsp;
            					<select name="hbmoffice" datatype="*">
            						<option value="">==请选择OFFICE==</option>
            						<c:forEach items="${list}" var="list">
            						<option value="${list.officeid}" ${list.officeid eq entity.hbmoffice ? 'selected' : '' }>${list.officeid}</option>
            						</c:forEach>
            					</select>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_center" colspan="2">
            					<input type="button" value="${empty entity.id ? '上一步' : '上一页' }" class="ext_btn ext_btn_submit" onclick="laststep(2)"/>
            					<input type="button" value="${empty entity.id ? '下一步' : '下一页' }" class="ext_btn ext_btn_submit" onclick="nextstep(2)"/>
								<c:if test="${not empty entity.id}">
									<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>
								</c:if>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            <!-- 航程适用条件结束 -->
            <!-- 平台比价配置form开始 -->
            	<form id="cpgzptbj">
            		<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="cpgzptbjtab" style="display: none">
            			<tr>
            				<td class="td_right">参与自动比价出票的cps政策类型：</td>
            				<td>
            					<input type="hidden" name="ptbjCpzclx" id="zdbjzclx" value="${entity.ptbjCpzclx}"/>
            					<input type="checkbox" value="1" id="zdcpptzc" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcpptzc">普通政策</label>
			                   	<input type="checkbox" value="2" id="zdcptjzc" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcptjzc">特价政策</label>
			                   	<input type="checkbox" value="3" id="zdcplytb" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcplytb">特殊政策</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">参与自动比价出票的淘宝旗舰店政策类型：</td>
            				<td>
            					<input type="hidden" name="ptbjTbzclx" id="cytbbjzclx" value="${entity.ptbjTbzclx }"/>
			                   	<c:forEach items="${vfc:getVeclassLb('7500')}" var="oneLx">
			                   			<input type="checkbox" value="${oneLx.ywmc}" name="zdbjtb" id="zdcp_${oneLx.ywmc}" onclick="changetbzclx(this)"><label for="zdcp_${oneLx.ywmc}">${oneLx.mc}</label>
			                   	</c:forEach>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">换编码政策是否参与比价：</td>
            				<td>
            					<input type="radio" name="ptbjHbmzc" id="hbmcybj" value="1" ${entity.ptbjHbmzc eq '1' or empty entity.ptbjHbmzc ? 'checked' : ''}><label for="hbmcybj">参与</label>
            					<input type="radio" name="ptbjHbmzc" id="hbmbcybj" value="0" ${entity.ptbjHbmzc eq '0' ? 'checked' : ''}><label for="hbmbcybj">不参与</label><font color="gray">(非淘宝旗舰店政策时会判断此设置)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">非CPS政策特殊政策是否参与比价：</td>
            				<td>
            					<input type="radio" name="ptbjTszc" id="fcpscybj" value="1" ${entity.ptbjTszc eq '1' or empty entity.ptbjTszc ? 'checked' : '' }><label for="fcpscybj">参与</label>
            					<input type="radio" name="ptbjTszc" id="fcpsbcybj" value="0" ${entity.ptbjTszc eq '0' ? 'checked' : '' }><label for="fcpsbcybj">不参与</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">废票时间点限制：</td>
            				<td><input type="text" name="ptbjFpsjxz" id="fpsjxz" value="${entity.ptbjFpsjxz}" size="10" datatype="/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/"/><font color="red">*</font><font color="gray">(格式22:00，比价自动出票时，只取废票工作时间超过此时间点的平台政策)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">按起飞时间限制：</td>
            				<td><input type="text" name="ptbjQfsjxz" id="qfsjxz" value="${entity.ptbjQfsjxz}" size="10" datatype="number"/><font color="gray">(小时)(设置几小时内起飞的航班不能使用平台政策自动出票，支持小数，0表示不控制)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">废票时间差忽略范围要求：</td>
            				<td><input type="text" name="ptbjFpsjc" id="fpsjhlfw" value="${entity.ptbjFpsjc}" size="10" datatype="number,dotformat" dotformat="###."/><font color="gray">(分钟)(若两政策废票时间差小于此时间，则认为废票时间相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">出票时间差忽略范围要求：</td>
            				<td><input type="text" name="ptbjCpsjc" id="fpsjhlfw" value="${entity.ptbjCpsjc}" size="10" datatype="number,dotformat" dotformat="###."/><font color="gray">(分钟)(若两政策出票时间差小于此时间，则认为出票时间相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">排序忽略返点差：</td>
            				<td><input type="text" name="ptbjPxhlfd" id="pxhlfdc" value="${entity.ptbjPxhlfd}" size="10" datatype="number,dotformat" dotformat="###.##"/><font color="gray">(%)(若两政策返点差值差小于此值，则认为返点相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">出票类型优先级：</td>
            				<td><font id="cplxyxjpxbsp">BSPET</font><font><a onclick="cplxyxjpx(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font> <font id="cplxyxjpxbp">BPET</font></td>
            				<input type="hidden" name="ptbjCplxyxj" id="ptbjCplxyxj" value="${empty entity.ptbjCplxyxj ? 'BSPET/BPET' : entity.ptbjCplxyxj}"/>
            			</tr>
            			<tr>
            				<td class="td_right">采购平台优先级别：</td>
            				<td>
            				<c:if test="${not empty ptbjPtyxjArray}">
            					<c:forEach items="${ptbjPtyxjArray}" var="array" varStatus="s">
            						<c:forEach items="${vfc:getVeclassLb('100021')}" var="oneLx" varStatus="v">
            							<c:if test="${array eq oneLx.ywmc and oneLx.id ne '100021'}">
            								<c:if test="${s.index ne 0}">
	            								<font><a onclick="ptcplxyxjpx(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
		           							</c:if>
		           							<font ptid="${oneLx.ywmc}" class="each">${oneLx.mc}</font>
            							</c:if>
            						</c:forEach>
            					</c:forEach>
            				</c:if>
            				<c:if test="${empty ptbjPtyxjArray}">
           						<c:forEach items="${vfc:getVeclassLb('100021')}" var="oneLx" varStatus="v">
           							<c:if test="${ oneLx.id ne '100021'}">
           							<c:if test="${v.index ne 0}">
	           							<font><a onclick="ptcplxyxjpx(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
	           						</c:if>
	           							<font ptid="${oneLx.ywmc}" class="each">${oneLx.mc}</font>
           							</c:if>
           						</c:forEach>
           					</c:if>
           					<input type="hidden" name="ptbjPtyxj" id="ptbjPtyxj" value="${empty entity.ptbjPtyxj ? '10100000/10100007/10100003/10100005/10100004/10100018/10100002/10100011' : entity.ptbjPtyxj}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">平台自动比价优先级：</td>
            				<td>
            				<c:if test="${empty ptbjZdxjyxjArray}">
            					<font ptzdbjyxj="1" class="eachptzdbj">废票时间最晚</font>
            					<font><a onclick="ptzdbjyxj(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
            					<font ptzdbjyxj="2" class="eachptzdbj">出票类型优先</font>
            					<font><a onclick="ptzdbjyxj(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
            					<font ptzdbjyxj="3" class="eachptzdbj">采购平台优先级别</font>
            					<font><a onclick="ptzdbjyxj(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
            					<font ptzdbjyxj="4" class="eachptzdbj">出票时间最晚</font>
            				</c:if>
            				<c:if test="${not empty ptbjZdxjyxjArray}">
            					<c:forEach items="${ptbjZdxjyxjArray}" var="array" varStatus="v">
            							<font ptzdbjyxj="${array}" class="eachptzdbj">
            								<c:if test="${array eq '1'}">废票时间最晚</c:if>
            								<c:if test="${array eq '2'}">出票类型优先</c:if>
            								<c:if test="${array eq '3'}">采购平台优先级别</c:if>
            								<c:if test="${array eq '4'}">出票时间最晚</c:if>
            							</font>
            							<c:if test="${v.index ne 3}">
            								<font><a onclick="ptzdbjyxj(this)" style="text-decoration:none;cursor:pointer;padding-left: 6px;padding-right: 6px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font>
            							</c:if>
            					</c:forEach>
            				</c:if>
            				<input type="hidden" name="ptbjZdxjyxj" id="ptbjZdxjyxj" value="${empty entity.ptbjZdxjyxj ? '1/2/3/4' : entity.ptbjZdxjyxj}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到OP政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjOpyccl" id="nopjxcp" value="1" ${entity.ptbjOpyccl eq '1' or empty entity.ptbjOpyccl ? 'checked' : ''}><label for="nopjxcp">继续出票</label>
            					<input type="radio" name="ptbjOpyccl" id="nzcztcp" value="2" ${entity.ptbjOpyccl eq '2' ? 'checked' : ''}><label for="nzcztcp">任何一个参与比价的平台未取到政策则暂停比价出票</label>
            					<input type="radio" name="ptbjOpyccl" id="nzczzcp" value="3" ${entity.ptbjOpyccl eq '3' ? 'checked' : ''}><label for="nzczzcp">任何一个参与比价的平台未取到政策则终止全自动出票</label></br>
            					<input type="radio" name="ptbjOpyccl" id="sycybjztcp" value="4" ${entity.ptbjOpyccl eq '4' ? 'checked' : ''}><label for="sycybjztcp">所有参与比价的平台未取到政策则暂停比价出票</label>
            					<input type="radio" name="ptbjOpyccl" id="sycybjzzcp" value="5" ${entity.ptbjOpyccl eq '5' ? 'checked' : ''}><label for="sycybjzzcp">所有参与比价的平台未取到政策则终止全自动出票</label><font color="gray">(勾选OP平台参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到BPET政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjB2byccl" id="nbpetjxcp" value="1" ${entity.ptbjB2byccl eq '1' or empty entity.ptbjB2byccl ? 'checked' : '' }><label for="nbpetjxcp">继续出票</label>
            					<input type="radio" name="ptbjB2byccl" id="nbpetztcp" value="2" ${entity.ptbjB2byccl eq '2' ? 'checked' : '' }><label for="nbpetztcp">暂停比价出票</label>
            					<input type="radio" name="ptbjB2byccl" id="nbpetztzzcp" value="3" ${entity.ptbjB2byccl eq '3' ? 'checked' : '' }><label for="nbpetztzzcp">终止全自动出票</label><font color="gray">(勾选BPET平台参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到淘宝旗舰店政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjTbyccl" id="ntbjxcp" value="1" ${entity.ptbjTbyccl eq '1' or empty entity.ptbjTbyccl? 'checked' : '' }><label for="ntbjxcp">继续出票</label>
            					<input type="radio" name="ptbjTbyccl" id="ntbztcp" value="2" ${entity.ptbjTbyccl eq '2' ? 'checked' : '' }><label for="ntbztcp">暂停比价出票</label>
            					<input type="radio" name="ptbjTbyccl" id="ntbztzzcp" value="3" ${entity.ptbjTbyccl eq '3' ? 'checked' : '' }><label for="ntbztzzcp">终止全自动出票</label><font color="gray">(勾选淘宝旗舰店政策参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_center" colspan="2">
            					<input type="button" value="${empty entity.id ? '上一步' : '上一页'}" class="ext_btn ext_btn_submit" onclick="laststep(3)"/>
								<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            	<!-- 平台比价配置form结束 -->
            </div>
          </div>
        </div>
     </div>
   </div>
  </body>
</html>
