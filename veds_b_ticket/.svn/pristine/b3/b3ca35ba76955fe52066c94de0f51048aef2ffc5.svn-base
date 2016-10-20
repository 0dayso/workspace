<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动出票国际规则设置</title>
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
		a{
			text-decoration:none;
		}
	</style>
 	<script type="text/javascript">
 		var lay;
 		var validator;
 		$(function(){
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
			//osi项检查
			var osixjc = '${entity.osixjc}';
			$('input[name="checkosis"]').each(function(){//检查osi项回显
				if(osixjc.indexOf($(this).val())>-1){
					$(this).attr("checked","checked");
				}
			});
			
 			$("#choozichp").autocompleteDynamic('shyhb','valueTozdcp');
 		 	validator = $('#zdcpgzsz').Validform({
				tiptype:3
			});
			
			 var bsygj = '${entity.bsygj}';
			 if(bsygj != ''){
				$("#bsygj_tr").show();
			}else{
				$("#sygj_tr").show();
			}
			
			var kqbop = '${entity.kqbop}';//开启bop 0 关闭 1 开启
			var kqbspet = '${entity.kqbspet}';//开启bspet出票 0 关闭 1 开启
			if(kqbop == '1'){
				$("#kqbopc").attr("checked","checked");
				$("#kqbops").show();
			}
			if(kqbspet == '1'){
				$("#kqbspetc").attr("checked","checked");
				$("#kqbspets").show();
			}
			var yxjsize = '${yxjszList.size()}';
			if(yxjsize > 0){
				cpyxjsztrxs();
			}
			//是否换编码出票
			var sfhbmcp = '${entity.sfhbmcp}';
			if(sfhbmcp == 1){
				$("#hbmosix").removeAttr("ignore");
				$("#hbmoffice").removeAttr("ignore");
				$("#hbmosix_tr").show();
			}
 		})
 		//关闭当前页面
 		function closecpgz(){
 			window.close();
 		}
 		//保存出票规则设置
 		function savecpgz(){
 			if(validator.check()){
	 			var cpmc = $("#gzmc").val();
	 			cpmc = encodeURI(encodeURI(cpmc));//中文转码
	 			$("#gzmc").val(cpmc);
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
 						url : '${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/verifyOnly',
 						data : {"gzmc":gzmc,"id" : id},
 						success: function(msg){
					     if(msg == '0'&&$("#zj").val() == ''){
					     	$("#gzmc").focus();
					     	layer.msg('出票规则名称不能重复!',2,3);
					     }else{
					     	if((!$("#kqbopc").is(":checked"))&&(!$("#kqbspetc").is(":checked"))){
			 						layer.msg('至少选择一个出票渠道!',2,3);
			 					}else if(hszhflag == '0'){
			 						layer.msg('请选择航司对应的出票账号!',2,3);
			 					}else{
			 						lay = layer.load('系统正在处理你的操作,请稍后!');
			 						//出票优先级table隐藏，将pjz置空，不让其重新保存
			 						//$("#zdcpgzsztab").hide();
			 						if($("#zdcpgzsztab").is(':visible') && $("#cpyxjtable").is(":hidden")){
						 				$("input[name=pjz]").each(function(){
						 					$(this).val("");
						 				});
						 			}
			 						var hcsytjval = $("#hcsytj").serialize();
						 			var zdcpgzszval = $("#zdcpgzsz").serialize();
						 			var forma = $("#cpgzszform").attr("action");
						 			forma = forma +'?' + zdcpgzszval + '&' + hcsytjval;
						 			$("#cpgzszform").attr("action",forma);
	 								$("#cpgzszform").submit();
			 					}
			 				}
					    }
 					});
			}else{
				validator.submitForm(false);
			}
 		}
 		
 		function laststep(index){
 			 if(index == 2){//中间也往上翻
 				if(validator.check()){
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
 					$.ajax({
 						type : 'GET',
 						url : '${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/verifyOnly',
 						data : {"gzmc":gzmc,"id" : id},
 						success: function(msg){
					     if(msg == '0'&&$("#zj").val() == ''){
					     	$("#gzmc").focus();
					     	layer.msg('出票规则名称不能重复!',2,3);
					     }else{
					     	if((!$("#kqbopc").is(":checked")) && (!$("#kqbspetc").is(":checked"))){
			 						layer.msg('至少选择一个出票渠道!',2,3);
			 					}else if(hszhflag == '0'){
			 						layer.msg('请选择航司对应的出票账号!',2,3);
			 					}else{
					 				$("#zdcpgzsztab").hide();
					 				$("#hcsytjtab").show();
					 				validator = $('#hcsytj').Validform({
										tiptype:3
									});
			 					}
					     	}
					    }
 					});
 				}else{
 					validator.submitForm(false);
 				}
 			}
 		}
 		
 		function sfkqhbm(obj){
 			var $obj = $(obj);
 			if($obj.is(':checked')){
 				$("#sfkqhbmcc").val("1");
 			}else{
 				$("#sfkqhbmcc").val("0");
 			}
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
		//是否开启bop
		function changekqbop(){
			var $obj = $("#kqbopc");
			if($obj.is(':checked')){
				$("#kqbops").show();
				$("#kqbop").val(1);
				$("#bopoffice").removeAttr("ignore");
				$("#bopagent").removeAttr("ignore");
				if(!$("#kqbspetc").is(":checked")){
					$("#dtchoose").append('<font id="bjcp2" class="cpyxjsz">BOP</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj2"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp2" class="cpyxjsz">BOP</font>');
				}
				//当BSP和BOP同时开启时，才显示出票优先级设置
				if($("#kqbspetc").is(':checked')){
					cpyxjsztrxs();
				}
			}else{
				//取消开启时，隐藏优先级设置
				cpyxjsztryc();
				if(!$("#kqbspetc").is(":checked")){
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
				if(!$("#kqbopc").is(":checked")){
					$("#dtchoose").append('<font id="bjcp3" class="cpyxjsz">BSPET</font>');
				}else{
					$("#dtchoose").append('<font id="kzyxj3"><a onclick="changesz(this)" style="text-decoration:none;cursor:pointer;padding: 0 10px;"><img src="${ctx}/static/images/jzcp/change_arrow.png"/></a></font><font id="bjcp3" class="cpyxjsz">BSPET</font>');
				}
				//当BSP和BOP同时开启时，才显示出票优先级设置
				if($("#kqbopc").is(':checked')){
					cpyxjsztrxs();
				}
			}else{
				//取消开启时，隐藏优先级设置
				cpyxjsztryc();
				if( (!$("#kqbpetc").is(":checked")) && (!$("#kqbopc").is(":checked")) ){
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
		
		function changezdcp(obj){//选择自动出票
			var id = $(obj).val();
			window.location.href="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/cpgjgzedit?id="+id;
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
			return '1';
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
		
		//优先级动态添加行
		function yxjtj(obj){
			var pjz = $(obj).parent().parent("tr").find("input[name=pjz]").val();
			//开启优先级设置的个数
			var kqszgs = $(".yxjsz").size();
			var trgs = $("#cpyxjtable").find("tr").size();
			if(trgs-1 < kqszgs){
				var $tr = '<tr>'
            		+'<td align="center">'
            		+'<input type="text" name="pjs" value="'+pjz+'" size=5 datatype="*,n" ignore="ignore"><font color="red" onblur="pjfwyz(this);value=$.trim(this.value);">*</font>'
            		+' &lt; '
            		+'<select name="pjlx">'
            		+'<option value="0">票面价</option>'
            		+'<option value="1">销售价</option>'
            		+'</select>'
            		+' &lt; = '
            		+'<input type="text" name="pjz" value="" size=5 datatype="*,n" ignore="ignore" onblur="pjfwyz(this);value=$.trim(this.value);"><font color="red">*</font>'
            		+'</td>'
            		+'<td align="center"><input type="text" name="cplx" value="" datatype="*" ignore="ignore" onblur="value=$.trim(this.value).toUpperCase();cplxyz(this);" title="优先级输入格式：优先级高>优先级低，如BSP>BOP"><font color="red">*</font></td>'
            		+'<td align="center">'
            		+'<a href="###" onclick="yxjtj(this)">添加</a>'
            		+'	${not empty entity.id ? "&nbsp;<a href=### onclick=saveysjsz(this)>保存</a>" : ""}'
            		+'	&nbsp;'
            		+'	<a href="###" onclick="yxjdel(this)">删除</a>'
            		+'</td>'
            		+'</tr>';
            	$("#cpyxjtable").append($tr);
			}else{
				layer.msg("不能再添加",1,3);
			}
		}
		//优先级动态删除行
		function yxjdel(obj){
			//获取此table下tr的个数
			var trgs = $("#cpyxjtable").find("tr").size();
			if(trgs <= 2){
				layer.msg("不能再删除",1,3);
				return;
			}
			$(obj).parent().parent("tr").remove();
		}
		//票价范围验证
		function pjfwyz(obj){
			var $td = $(obj).parent("td");
			var pjs = $td.find("input[name=pjs]").val();
			var pjz = $td.find("input[name=pjz]").val();
			if(pjs != '' && pjz != '' ){
				if(parseInt(pjs) >= parseInt(pjz)){
					layer.msg("请输入正确的数据",1,3);
					$(obj).focus();
				}
			}
			
		}
		//出票优先级类型验证
		function cplxyz(obj){
			var temp = $(obj).val();
			if(temp == ''){
				return;
			}
			var s = arr.join(",")+",";
			for(var i=0;i<length;i++){
				if(arr[i] == ''){
					layer.msg("请输入格式不正确",1,3);
					return;
				}
				if(s.replace(arr[i]+",","").indexOf(arr[i]+",")>-1) {
					layer.msg("输入重复，请重新输入",1,3);
					return;
				}
			}
		}
		//出票优先级tr显示
		function cpyxjsztrxs(){
			$("input[name=pjs]").each(function(){
				$(this).removeAttr("ignore");
			});
			$("input[name=pjz]").each(function(){
				$(this).removeAttr("ignore");
			});
			$("input[name=cplx]").each(function(){
				$(this).removeAttr("ignore");
			});
			$("#cpyxjsztr").show();
		}
		//出票优先级tr隐藏
		function cpyxjsztryc(){
			$("input[name=pjs]").each(function(){
				$(this).attr("ignore","ignore").val(' ');
			});
			$("input[name=pjz]").each(function(){
				$(this).attr("ignore","ignore").val(' ');
			});
			$("input[name=cplx]").each(function(){
				$(this).attr("ignore","ignore").val(' ');
			});
			$("#cpyxjsztr").hide();
		}
		//规则名称唯一性验证
		function gzmcyz(obj){
			var gzmc = $(obj).val();
			var id = $("#zj").val();
			$.ajax({
				type : 'GET',
				url : '${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/verifyOnly',
				data : {"gzmc":gzmc,"id" : id},
				success: function(msg){
	     			if(msg == '0'&&$("#zj").val() == ''){
	     				$(obj).focus();
	     				layer.msg('出票规则名称不能重复!',2,3);
	     			}
	    		}
			});
		}
		//保存当前优先级设置
		function saveysjsz(obj){
			var $td = $(obj).parent().parent("tr");
			var yxjid = $td.find("input[name=yxjid]").val();
			var pjs = $td.find("input[name=pjs]").val();
			var pjz = $td.find("input[name=pjz]").val(); 
			var cplx = $td.find("input[name=cplx]").val(); 
			var sxh = $td.find("input[name=sxh]").val(); 
			var pjlx = $td.find("select[name=pjlx]").val();
			var gjgzid = $("#zj").val();
			$.ajax({
				type:"GET",
				url:"${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/saveyxjsz",
				data:{id:yxjid,gjgzid:gjgzid,pjs:pjs,pjz:pjz,cplx:cplx,sxh:sxh,pjlx:pjlx},
				success:function(result){
					
				}
			});
		}
		//开启换编码出票
		function kqhbmcp(){
			$("#hbmosix").removeAttr("ignore");
			$("#hbmoffice").removeAttr("ignore");
			$("#hbmosix_tr").show();
		}
		
		//关闭换编码出票
		function gbhbmcp(){
			$("#hbmosix_tr").hide();
		}
		
		//适用国籍/不适用国籍
		function showsygj(val){
			if(val == "0"){
				$("#sygj_tr").show();
				$("#bsygj_tr").hide();
			}else{
				$("#bsygj_tr").show();
				$("#sygj_tr").hide();
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
            <form action="${ctx}/vedsb/jpzdcp/jpzdcpgjgzsz/saveCpgz" method="POST" id="cpgzszform">
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
            					<input type="text" name="gzmc" size=12 value="${entity.gzmc}" datatype="*" id="gzmc" onblur="gzmcyz(this);value=$.trim(this.value);"><font color="red">*</font>&nbsp;&nbsp;&nbsp;
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
            					<input type="text" name="sysjs" size=5 value="${entity.sysjs}" datatype="*,/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/" onblur="value=$.trim(this.value);"/><font color="red">*</font>&nbsp;&nbsp;至&nbsp;&nbsp;
            					<input type="text" name="sysjz" size=5 value="${entity.sysjz}" datatype="*,/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/" onblur="value=$.trim(this.value);"/><font color="red">*</font><font color="gray">(时间格式：00:00，例00:00至23:59)</font>
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
            					<input type="text" name="zcdm" size=18 value="${entity.zcdm}" onblur="value=$.trim(this.value);"/><font color="gray">(多个使用"/"分隔，如ptzC/Tjzc，注严格区分大小写)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">单人最小利润：</td>
            				<td>
	            				<input type="text" name="drzxlr" size=18 value="${entity.drzxlr}" datatype="*,n" onblur="value=$.trim(this.value);"/>
	            				<font color="red">*</font>
	            				<font color="gray">（元，比价出票时，1，选择票面价比价：需判断【单人票面价-QTE票面价】大于或等于此值才可自动出票 2，选择销售价比价：需判断【单人销售价-QTE票面价*（1-代理费率）】大于或等于此值才可自动出票 ）</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">是否选择低价出票：</td>
            				<td>
            					<input type="radio" name="sfdjcp" value="1" ${empty entity.sfdjcp or entity.sfdjcp eq '1' ? 'checked' : ''}>按低价出票
            					<input type="radio" name="sfdjcp" value="0" ${entity.sfdjcp eq '0' ? 'checked' : ''}>按订单张单价出票
            				</td>
            			</tr>
            			<tr>
            				<td class="td_rightzdcp">出票渠道设置：</td>
            				<td>
            					<input type="checkbox" name="kqbopc" id="kqbopc" onclick="changekqbop()" class="yxjsz"/><label for="kqbopc">开启BOP</label>
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
            								</select><font color="red">*</font>&nbsp;&nbsp;&nbsp;
            								<font>工作号：</font>&nbsp;&nbsp;
            								<select name="bopAgent" datatype="*" class="bopselect" ignore="ignore" id="bopagent">
            									<option value="">==请选择工作号==</option>
            									<c:forEach items="${list}" var="list">
			            							<option value="${list.agent}" ${list.agent eq entity.bopAgent ? 'selected' : '' }>${list.agent}</option>
			            						</c:forEach>
            								</select><font color="red">*</font>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			<tr>
            				<td></td>
            				<td>
            					<input type="checkbox" name="kqbspetc" id="kqbspetc" onclick="changekqbspet()" class="yxjsz"/><label for="kqbspetc">开启BSPET</label>
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
            								</select><font color="red">*</font>&nbsp;&nbsp;&nbsp;
            								<font>工作号：</font>&nbsp;&nbsp;
            								<select name="bspAgent" datatype="*" ignore="ignore" class="bspselect" id="bspetagent">
            									<option value=" ">==请选择工作号==</option>
           										<c:forEach items="${list}" var="list">
			            							<option value="${list.agent}" ${list.agent eq entity.bspAgent ? 'selected' : '' }>${list.agent}</option>
			            						</c:forEach>
            								</select><font color="red">*</font>&nbsp;&nbsp;
            								<font>打票机号：</font>&nbsp;&nbsp;&nbsp;
            								<select name="bspPrintno" datatype="*" ignore="ignore" class="bspselect" id="bspetprintno">
            									<option value=" ">==请选择打票机号==</option>
            									<c:forEach items="${list}" var="list">
			            							<option value="${list.printno}" ${list.printno eq entity.bspPrintno and not empty list.printno ? 'selected' : '' }>${list.printno}</option>
			            						</c:forEach>
            								</select><font color="red">*</font>
            							</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            			
            			<!-- 修改 -->
            			<c:if test="${not empty entity.id or yxjszList.size() > 0 }">
            				<tr id="cpyxjsztr" style="display: none;">
	            				<td class="td_rightzdcp">出票优先级设置：</td>
	            				<td>
	            					<font color="gray">当前一种自动出票失败后，会自动跳转到下一种出票方式进行出票 ；当BSPET出票失败后，间隔30秒自动重新BSP出票。</font>
	            					<table id="cpyxjtable"  border="1" bordercolor="#a0c6e5" width="50%" style="border-collapse:collapse;">
	            						<tr>
	            							<td align="center">票价范围</td>
	            							<td align="center">优先级</td>
	            							<td align="center">操作</td>
	            						</tr>
	            						<c:forEach items="${yxjszList }" var="list">
	            							<tr>
		            							<td align="center">
		            								<input type="hidden" name="yxjid" value="${list.id }">
		            								<input type="hidden" name="sxh" value="${list.sxh }">
		            								<input type="text" name="pjs" value="${list.pjs }" size=5 datatype="*,n" ignore="ignore" onblur="pjfwyz(this);value=$.trim(this.value);"><font color="red">*</font>
		            								&lt; 
		            								<select name="pjlx">
		            									<option value="0" ${list.pjz eq '0' ? 'selected' : '' }>票面价</option>
		            									<option value="1" ${list.pjz eq '1' ? 'selected' : '' }>销售价</option>
		            								</select>
		            								&lt; =
		            								<input type="text" name="pjz" value="${list.pjz }" size=5 datatype="*,n" ignore="ignore" onblur="pjfwyz(this);value=$.trim(this.value);"><font color="red">*</font>
		            							</td>
		            							<td align="center"><input type="text" name="cplx" value="${list.cplx }" datatype="*" ignore="ignore" onblur="value=$.trim(this.value).toUpperCase();cplxyz(this);" title="优先级输入格式：优先级高>优先级低，如BSP>BOP"><font color="red">*</font></td>
		            							<td align="center">
		            								<a href="###" onclick="yxjtj(this)">添加</a>
		            								&nbsp;<a href="###" onclick="saveysjsz(this);">保存</a>
		            								&nbsp;<a href="###" onclick="yxjdel(this)">删除</a>
		            							</td>
	            							</tr>
	            						</c:forEach>
	            					</table>
	            				</td>
            				</tr>
            			</c:if>
            			<!-- 新增 -->
            			<c:if test="${empty entity.id or yxjszList.size() <= 0 }">
            				<tr id="cpyxjsztr" style="display: none;">
	            				<td class="td_rightzdcp">出票优先级设置：</td>
	            				<td>
	            					<font color="gray">当前一种自动出票失败后，会自动跳转到下一种出票方式进行出票 ；当BSPET出票失败后，间隔30秒自动重新BSP出票。</font>
	            					<table id="cpyxjtable"  border="1" bordercolor="#a0c6e5" width="50%" style="border-collapse:collapse;">
	            						<tr>
	            							<td align="center">票价范围</td>
	            							<td align="center">优先级</td>
	            							<td align="center">操作</td>
	            						</tr>
	            						<tr>
	            							<td align="center">
	            								<input type="text" name="pjs" value="0" size=5 datatype="*,n" ignore="ignore" onblur="pjfwyz(this);value=$.trim(this.value);"><font color="red">*</font>
	            								&lt; 
	            								<select name="pjlx">
	            									<option value="0">票面价</option>
	            									<option value="1">销售价</option>
	            								</select>
	            								&lt; =
	            								<input type="text" name="pjz" value="" size=5 datatype="*,n" ignore="ignore" onblur="pjfwyz(this);value=$.trim(this.value);"><font color="red">*</font>
	            							</td>
	            							<td align="center"><input type="text" name="cplx" value="" datatype="*" ignore="ignore" onblur="value=$.trim(this.value).toUpperCase();cplxyz(this);" title="优先级输入格式：优先级高>优先级低，如BSP>BOP"><font color="red">*</font></td>
	            							<td align="center">
	            								<a href="###" onclick="yxjtj(this)">添加</a>
	            							</td>
	            						</tr>
	            					</table>
	            				</td>
            				</tr>
            			</c:if>
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
            				<td align="center" colspan="2">
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
	            				<input type="radio" name="hclx" value="0" id="hclxdc" ${entity.hclx eq '0' ? 'checked' : '' } /><label for="hclxdc">单程</label>
	            				<input type="radio" name="hclx" value="1" id="hclxwf" ${entity.hclx eq '1' or empty entity.hclx ? 'checked' : '' } /><label for="hclxwf">往返</label>
	            				<input type="radio" name="hclx" value="2" id="hclxlc" ${entity.hclx eq '2' ? 'checked' : '' } /><label for="hclxlc">单程和往返</label>
	            			</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">适用乘机人类型：</td>
            				<td>
            					<select name="cjrlx">
            						<option value="0" ${entity.cjrlx eq '0' or empty entity.cjrlx ? 'selected' : '' }>成人</option>
            						<option value="1" ${entity.cjrlx eq '1' ? 'selected' : '' }>儿童</option>
            						<option value="2" ${entity.cjrlx eq '2' ? 'selected' : '' }>婴儿</option>
            						<option value="3" ${entity.cjrlx eq '3' ? 'selected' : '' }>老人</option>
            						<option value="4" ${entity.cjrlx eq '4' ? 'selected' : '' }>劳务</option>
            						<option value="5" ${entity.cjrlx eq '5' ? 'selected' : '' }>移民</option>
            						<option value="6" ${entity.cjrlx eq '6' ? 'selected' : '' }>海员</option>
            					</select>
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
            				<td class="td_righthclx">出发城市：</td>
            				<td><input type="text" value="${empty entity.cfcs ? '---' : entity.cfcs }" name="cfcs" size=18 datatype="*,multicity" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">到达城市：</td>
            				<td><input type="text" value="${empty entity.ddcs ? '---' : entity.ddcs}" name="ddcs" size=18 datatype="*,multicity" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号适用条件：</td>
            				<td>
            					<input type="radio" name="hbhsytj" value="0" id="hbhqbsy" ${entity.hbhsytj eq '0' or empty entity.hbhsytj ? 'checked' : '' }/><label for="hbhqbsy">全部适用</label>
            					<input type="radio" name="hbhsytj" value="1" id="hbhsy" ${entity.hbhsytj eq '1' ? 'checked' : '' }/><label for="hbhsy">适用</label>
            					<input type="radio" name="hbhsytj" value="2" id="hbhbsy" ${entity.hbhsytj eq '2' ? 'checked' : '' }/><label for="hbhbsy">不适用</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号：</td>
            				<td>
            					<input type="text" name="hbh" value="${entity.hbh}" size=18 datatype="*,multihbh" onkeyup="this.value=this.value.toUpperCase()"/><font color="red">*</font><font color="gray">(多个使用/分隔)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">不适用航班号：</td>
            				<td>
            					<input type="text" name="bsyhbh" value="${entity.bsyhbh}" size=18 datatype="*,multihbh" onkeyup="this.value=this.value.toUpperCase()"/><font color="red">*</font><font color="gray">(多个使用/分隔)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">舱位：</td>
            				<td><input type="text" value="${empty entity.cw ? '---' : entity.cw}" name="cw" size=18 datatype="*,multicw" onkeyup="this.value=this.value.toUpperCase()"/><font color="gray">(多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">最晚提前出票天数：</td>
            				<td>
            					<input type="text" name="zwtqcpts" value="${entity.zwtqcpts}" size=5 datatype="*,number,minvalue,maxvalue" minvalue=0 maxvalue=999 id="zwtqcpts"/><font color="red">*</font><font color="gray">(天)</font>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>最早提前出票天数：&nbsp;</font> <input type="text" name="zztqcpts" value="${empty entity.zztqcpts ? 365:entity.zztqcpts}" size=5 datatype="*,number,minvalue,maxvalue" minvalue=0 maxvalue=999 id="zztqcpts"/><font color="red">*</font><font color="gray">(天)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">订单人数限制：</td>
            				<td>
	            				<input type="text" name="ddrsxzs" size="5"  datatype = "n" ignore="ignore" value="${empty entity.ddrsxzs ? '1' : entity.ddrsxzs}" onblur="value=$.trim(this.value);">
	            				&nbsp;&nbsp;至&nbsp;&nbsp;
	            				<input type="text" name="ddrsxzz" size="5"  datatype = "n" ignore="ignore" value="${entity.ddrsxzz }" onblur="value=$.trim(this.value);">
	            				<font color="gray">(为空代表不限制)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">年龄限制：</td>
            				<td>
	            				<input type="text" name="nlxzs" size="5" datatype = "n" ignore="ignore" value="${empty entity.nlxzs ? '0' : entity.nlxzs}" onblur="value=$.trim(this.value);">
	            				&nbsp;&nbsp;至&nbsp;&nbsp;
	            				<input type="text" name="nlxzz" size="5" datatype = "n" ignore="ignore" value="${entity.nlxzz }" onblur="value=$.trim(this.value);">
	            				<font color="gray">(为空代表不限制)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">乘机人名中含：</td>
            				<td><input type="text" name="cjrmzh" value="${empty entity.cjrmzh ? 'VIP' : entity.cjrmzh }" size=16 id="cjrmzh" onblur="value=$.trim(this.value);"/>字符不能自动出票<font color="gray">(支持录入多个，使用英文"/"分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">国籍：</td>
            				<td>
            					<input type="radio" name="ifsygj" id="gjsy" onclick="showsygj('0');"  ${empty entity.bsygj ? 'checked':''}/><label for="gjsy">适用</label>
            					<input type="radio" name="ifsygj" id="gjbsy" onclick="showsygj('-1');" ${empty entity.bsygj ? '':'checked'}/><label for="gjbsy">不适用</label>
            				</td>
            			</tr>
            			<tr id="sygj_tr" style="display: none;">
            				<td class="td_righthclx">适用国籍：</td>
            				<td>
            					<input type="text" name="sygj" value="${entity.sygj }" onblur="value=$.trim(this.value).toUpperCase();">
            					<font color="gray">(国家二字码，多个用/分隔)</font>
            				</td>
            			</tr>
            			<tr id="bsygj_tr" style="display: none;">
            				<td class="td_righthclx">不适用国籍：</td>
            				<td>
	            				<input type="text" name="bsygj" value="${entity.bsygj }" onblur="value=$.trim(this.value).toUpperCase();">
	            				<font color="gray">(国家二字码，多个用/分隔)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">OSI项检查：</td>
            				<td>
            					<input type="checkbox" value="1" name="checkosis" id="checksjh" onclick="changecheck(this)"><label for="checksjh">检查订单乘机人手机号</label>
			                   	<input type="checkbox" value="2" name="checkosis" id="checkctct" onclick="changecheck(this)"><label for="checkctct">检查PNR内容CTCT项</label>
			                   	<input type="hidden" name="osixjc" id="checkosi" value="${empty entity.osixjc ? '0' : entity.osixjc}"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">是否换编码出票：</td>
            				<td>
            					<input type="radio" name="sfhbmcp"  value="1" ${entity.sfhbmcp eq '1' ? 'checked' : '' } onclick="kqhbmcp();">是
            					<input type="radio" name="sfhbmcp" value="0" ${empty entity.sfhbmcp or entity.sfhbmcp eq '0' ? 'checked' : '' } onclick="gbhbmcp()">否
            					<input type="checkbox" id="kqybmcc" ${entity.kqybmcc eq '1' or empty entity.kqybmcc  ? 'checked' : '' } onclick="sfkqhbm(this);"/><label for="kqybmcc">开启原编码重出</label>
            					<input type="hidden" name="kqybmcc" value="${empty entity.kqybmcc ? '0' : entity.kqybmcc}" id="sfkqhbmcc"/>
            				</td>
            			</tr>
            			
            			<tr id="hbmosix_tr" style="display: none;">
            				<td class="td_righthclx">换编码OSI项：</td>
            				<td>
            					<input type="text" name="hbmosix" value="${entity.hbmosix}" id="hbmosix" datatype="*"  ignore="ignore" size=9 onblur="value=$.trim(this.value);"/><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>换编码OFFICE：</font>&nbsp;&nbsp;
            					<select name="hbmoffice" id="hbmoffice" datatype="*" ignore="ignore">
            						<option value="">==请选择OFFICE==</option>
            						<c:forEach items="${list}" var="list">
            							<option value="${list.officeid}" ${list.officeid eq entity.hbmoffice ? 'selected' : '' }>${list.officeid}</option>
            						</c:forEach>
            					</select><font color="red">*</font>
            				</td>
            			</tr>
            			<tr>
            				<td align="center" colspan="2"> 
            					<input type="button" value="${empty entity.id ? '上一步' : '上一页'}" class="ext_btn ext_btn_submit" onclick="laststep(2)"/>
								<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            <!-- 航程适用条件结束 -->
            </div>
          </div>
        </div>
     </div>
   </div>
  </body>
</html>
