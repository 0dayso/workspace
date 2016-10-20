<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title></title>
    	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
    	<script type="text/javascript">
    		var validator;
    		$(function(){
    			if('${param.ddlx}' == '1' || '${ddlx}' == '1'){
    				$("#changeje").text("网店票价:");
    				$('input[type="radio"][name="ddxh"]').each(function(){
    					if($(this).prop("checked")){
    						$(this).click();
    					}
    				});
    			}else if('${param.ddlx}' == '2' || '${ddlx}' == '2'){
    				$("#changeje").text("网店退票费:");
    				$('input[type="radio"][name="tfxh"]').each(function(){
    					if($(this).prop("checked")){
    						$(this).click();
    					}
    				});
    			}else if('${param.ddlx}' == '3' || '${ddlx}' == '3' ){
    				$("#changeje").text("网店改签费:");
    				$('input[type="radio"][name="gqxh"]').each(function(){
    					if($(this).prop("checked")){
    						$(this).click();
    					}
    				});
    			}
    			//修改差异单时赋值
    			if('${updateType}' == 'edit'){
	    			if('${ddlx}' == '1'){
	    				$("#jpzzpj").val('${list[0].XS_PJ}');
	    				$("#jpzzjj").val('${list[0].XS_JSF}');
	    				$("#jpzzsf").val('${list[0].XS_TAX}');
	    				if('${cylx}' == '1'){
	    					$("#xt_tkje").val('${list[0].XS_PJ}');
	    				}else if('${cylx}' == '2'){
	    					$("#xt_tkje").val('${list[0].XS_JSF}');
	    				}else if('${cylx}' == '3'){
	    					$("#xt_tkje").val('${list[0].XS_TAX}');
	    				}
	    				$("#tpblInput").attr("ignore","ignore");//表单元素不验证
	    			}else if('${ddlx}' == '2'){
	    				$("#tpbl").show();
	    				$("#tpblInput").removeAttr("ignore");
	    				$("#xt_tkje").val('${list[0].XS_TKJE}');
	    			}else if('${ddlx}' == '3'){
	    				$("#tpblInput").attr("ignore","ignore");
	    				$("#xt_tkje").val('${list[0].GQ_XSFY}');
	    			}
    			}else{
	    			if('${isnull}' == '0'){
	    				layer.msg('该订单不存在或不是网店订单请检查');
	    				if('${param.ddlx}' == '1'){
							$("#pj").attr("selected","selected");
							$("#tpblInput").attr("ignore","ignore");
	    				}else if('${param.ddlx}' == '2'){
	    					$("#tpbl").show();
	    					$("#tpblInput").removeAttr("ignore");//表单元素验证
							$("#tp").attr("selected","selected");
	    				}else if('${param.ddlx}' == '3'){
	    					$("#tpblInput").attr("ignore","ignore");
							$("#gq").attr("selected","selected");
	    				}
	    			}else{
		    			if('${param.ddlx}' == '1'){
							$("#pj").attr("selected","selected");
		    				$("#jpzzflag").val("1");
		    				$("#jptfflag").val("");
		    				$("#jpgqflag").val("");
							$("#jpzzdd").show();
							$("#tpblInput").attr("ignore","ignore");
						}else if('${param.ddlx}' == '2'){
							$("#tpbl").show();
							$("#tpblInput").removeAttr("ignore");
							$("#tp").attr("selected","selected");
							$("#jpzzflag").val("");
		    				$("#jptfflag").val("1");
		    				$("#jpgqflag").val("");
							$("#jptfdd").show();
						}else if('${param.ddlx}' == '3'){
							$("#tpblInput").attr("ignore","ignore");
							$("#gq").attr("selected","selected");
							$("#jpzzflag").val("");
		    				$("#jptfflag").val("");
		    				$("#jpgqflag").val("1");
							$("#jpgqdd").show();
						}
					}
				}
    			validator = $("#jpcyd").Validform({
						tiptype:3
					});
    		});
    		//关闭当前弹窗
    		function closepj(){
    			var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
    		}
    		//保存
			function saveJpcyd(id){
				if(id==null || id == "" || id == undefined){
					if($("#searchddlx").val() == '1'){
						if($("#jpzzflag").val() == "" || $("#jpzzflag").val() == null){
							layer.msg('请先查询并选择订单');
							return;
						}else if($("#jpzzpj").val() == "" && $("#jpzzjj").val() == "" && $("#jpzzsf").val() == ""){
							layer.msg('请选择订单');
							return;
						}
					}else if($("#searchddlx").val() == '2'){
						if($("#jptfflag").val() == "" || $("#jptfflag").val() == null){
							layer.msg('请先查询并选择订单');
							return;
						}else if($("#jptpsf").val() == ""){
							layer.msg('请选择订单');
							return;
						}
					}else if($("#searchddlx").val() == '3'){
						if($("#jpgqflag").val() == "" || $("#jpgqflag").val() == null){
							layer.msg('请先查询并选择订单');
							return;
						}else if($("#jpgqsf").val() == ""){
							layer.msg('请选择订单');
							return;
						}
					}
				}else{
					$("#jpcyd").attr("action","${ctx}/vedsb/jpbcd/jpcyd/updateCyd");
				}
				validator.submitForm(false);
			}
			
			function saveShJpcyd(){
				$("#jpcyd").attr("action","${ctx}/vedsb/jpbcd/jpcyd/updateCyd?sh=1");
				validator.submitForm(false);
			}
			function changeDdlx(obj){
				var $obj = $(obj);
				if($obj.val() == "1"){
					$("#changeje").text("网店票价:");
					$("#jpdd").html("<option value='1' id='pj'>票价</option><option value='2'>机建</option><option value='3'>税费</option>");
					$("#tpbl").hide();
					$("#tpblInput").attr("ignore","ignore").val('');
					$("#jpgqdd").hide();
					$("#jptfdd").hide();
				}else if($obj.val() == "2"){
					$("#changeje").text("网店退票费:");
					$("#jpdd").html("<option value='4' id='tp'>退票费</option>");
					$("#tpbl").show();
					$("#tpblInput").removeAttr("ignore");
					$("#jpzzdd").hide();
					$("#jpgqdd").hide();
				}else if($obj.val() == "3"){
					$("#changeje").text("网店改签费:");
					$("#jpdd").html("<option value='5' id='gq'>改签费</option>");
					$("#tpbl").hide();
					$("#tpblInput").attr("ignore","ignore").val('');
					$("#jpzzdd").hide();
					$("#jptfdd").hide();
				}
			}
			
			function jpSearch(){
				if($("#jpPnr").val()=="" && $("#jpWbdh").val() == ""){
					layer.msg('PNR和外部单号必须一个有值');
					return;
				}
				$("#searchform").submit();
			}
			
			function jpzzddclick(obj){
				var $obj = $(obj);
				$("#wbdh").val($obj.attr("wbdh"));
				$("#pnr").val($obj.attr("pnr"));
				if($("#jpdd").val() == '1'){
					$("#xt_tkje").val($obj.attr("xtpj"));
				}else if($("#jpdd").val() == '2'){
					$("#xt_tkje").val($obj.attr("xtjj"));
				}else if($("#jpdd").val() == '3'){
					$("#xt_tkje").val($obj.attr("xttax"));
				}
				$("#wdpt").val($obj.attr("wdpt"));
				$("#wdid").val($obj.attr("wdid"));
				$("#ddbh").val($obj.attr("ddbh"));
				$("#jpzzpj").val($obj.attr("xtpj"));
				$("#jpzzjj").val($obj.attr("xtjj"));
				$("#jpzzsf").val($obj.attr("xttax"));
				var xtje = $("#xt_tkje").val();//计算价格
				if(xtje == ""){
					xtje = 0;
				}
				var dsje = $("#wd_tkje").val();
				if(dsje == ""){
					dsje = 0;
				}
				$("#cyje").val((xtje-dsje).toFixed(2));
			}
			
			function jptfddclick(obj){
				var $obj = $(obj);
				$("#wbdh").val($obj.attr("wbdh"));
				$("#pnr").val($obj.attr("pnr"));
				$("#xt_tkje").val($obj.attr("xttpf"));
				$("#wdpt").val($obj.attr("wdpt"));
				$("#wdid").val($obj.attr("wdid"));
				$("#ddbh").val($obj.attr("ddbh"));
				$("#jptpsf").val($obj.attr("xttpf"));
				var xtje = $("#xt_tkje").val();//计算价格
				if(xtje == ""){
					xtje = 0;
				}
				var dsje = $("#wd_tkje").val();
				if(dsje == ""){
					dsje = 0;
				}
				$("#cyje").val((xtje-dsje).toFixed(2));
			}
			
			function jpgqddclick(obj){
				var $obj = $(obj);
				$("#wbdh").val($obj.attr("wbdh"));
				$("#pnr").val($obj.attr("pnr"));
				$("#xt_tkje").val($obj.attr("xtgqf"));
				$("#wdpt").val($obj.attr("wdpt"));
				$("#wdid").val($obj.attr("wdid"));
				$("#ddbh").val($obj.attr("ddbh"));
				$("#jpgqsf").val($obj.attr("xtgqf"));
				var xtje = $("#xt_tkje").val();//计算价格
				if(xtje == ""){
					xtje = 0;
				}
				var dsje = $("#wd_tkje").val();
				if(dsje == ""){
					dsje = 0;
				}
				$("#cyje").val((xtje-dsje).toFixed(2));
			}
			
			function changecylx(obj){
				var $obj = $(obj);
				if($obj.val() == "1"){
					$("#changeje").text("网店票价:");
					$("#xt_tkje").val($("#jpzzpj").val());
				}else if($obj.val() == "2"){
					$("#changeje").text("网店机建:");
					$("#xt_tkje").val($("#jpzzjj").val());
				}else if($obj.val() == "3"){
					$("#changeje").text("网店税费:");
					$("#xt_tkje").val($("#jpzzsf").val());
				}else if($obj.val() == "4"){
					$("#changeje").text("网店退票费:");
				}else if($obj.val() == "5"){
					$("#changeje").text("网店改签费:");
				}
				var xtje = $("#xt_tkje").val();//计算价格
				if(xtje == ""){
					xtje = 0;
				}
				var dsje = $("#wd_tkje").val();
				if(dsje == ""){
					dsje = 0;
				}
				$("#cyje").val((xtje-dsje).toFixed(2));
			}
			
			function getWdCyJe(obj){
				var $obj = $(obj);
				var dsje = $obj.val();
				var xtje = $("#xt_tkje").val();//计算价格
				if(xtje == ""){
					xtje = 0;
				}
				if(dsje == ""){
					dsje = 0;
				}
				$("#cyje").val((xtje-dsje).toFixed(2));
			}
    	</script>
  </head>
  <body>
    <div class="container">
 		<div id="forms">
        	<div class="box">
          		<div class="box_border">
            		<div class="box_center">
            			<c:if test="${updateType eq 'add'}">
	            			<form action="${ctx}/vedsb/jpbcd/jpcyd/searchJpdd" id="searchform" method="post">
	            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	            				<tr>
	            					<td style="text-align: right;">
	            					订单类型:<select name="ddlx" style="width: 79px" id="searchddlx" onchange="changeDdlx(this);">
	           								<option value="1" <c:if test="${param.ddlx eq '1'}">selected</c:if>>正常订单</option>
	           								<option value="2" <c:if test="${param.ddlx eq '2'}">selected</c:if>>退废订单</option>
	           								<option value="3" <c:if test="${param.ddlx eq '3'}">selected</c:if>>改签订单</option>
	            						</select>
	            					</td>
	            					<td style="text-align: right;">
	            					PNR:<input type="text" name="pnrNo" id="jpPnr" value="${param.pnrNo}" size="6" onblur="value=$.trim(this.value).toUpperCase();"/>
	            					</td>
	            					<td style="text-align: right;">
	            					外部单号:<input type="text" name="wbdh" id="jpWbdh" value="${param.wbdh}"/>
	            					</td>
	            					<td colspan="2">
	            						 <input type="button" id="" class="ext_btn ext_btn_submit" value="查询" onclick="jpSearch();">
	            					</td>
	            				</tr>
	            			</table>
	            			</form>
            			</c:if>
            			<c:if test="${updateType eq 'edit'}">
            				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
           						<tr>
           							<c:if test="${ddlx eq '1'}">
           								<td style="text-align: center;">订单编号</td>
           							</c:if>
           							<c:if test="${ddlx eq '2'}">
           								<td style="text-align: center;">退废单号</td>
           							</c:if>
           							<c:if test="${ddlx eq '3'}">
           								<td style="text-align: center;">改签单号</td>
           							</c:if>
           							<td style="text-align: center;">外部单号</td>
           							<td style="text-align: center;">PNR</td>
           							<c:if test="${ddlx eq '2' or ddlx eq '3'}">
           								<td style="text-align: center;">票号</td>
           							</c:if>
           							<td style="text-align: center;">乘机人</td>
           							<c:if test="${ddlx eq '1'}">
           								<td style="text-align: center;">票价</td>
	           							<td style="text-align: center;">机建</td>
	           							<td style="text-align: center;">税费</td>
           							</c:if>
           							<c:if test="${ddlx eq '2'}">
	           							<td style="text-align: center;">退票比例</td>
	           							<td style="text-align: center;">退票费</td>
           							</c:if>
           							<c:if test="${ddlx eq '3'}">
           								<td style="text-align: center;">改签费用</td>
           							</c:if>
           						</tr>
           					<c:forEach items="${list}" var="list">
           						<tr>
           							<c:if test="${ddlx eq '1'}">
           								<td style="text-align: center;">${list.DDBH}</td>
           							</c:if>
           							<c:if test="${ddlx eq '2'}">
           								<td style="text-align: center;">${list.TPDH}</td>
           							</c:if>
           							<c:if test="${ddlx eq '3'}">
           								<td style="text-align: center;">${list.GQDH}</td>
           							</c:if>
           							<td style="text-align: center;">${list.WBDH}</td>
           							<td style="text-align: center;">${list.XS_PNR_NO}</td>
           							<c:if test="${ddlx eq '2' or ddlx eq '3'}">
           								<td style="text-align: center;">${list.TKNO}</td>
           							</c:if>
           							<td>${list.CJR}</td>
           							<c:if test="${ddlx eq '1'}">
           								<td style="text-align: center;">${list.XS_PJ}</td>
	           							<td style="text-align: center;">${list.XS_JSF}</td>
	           							<td style="text-align: center;">${list.XS_TAX}</td>
           							</c:if>
           							<c:if test="${ddlx eq '2'}">
	           							<td style="text-align: center;">${vfn:format(list.XS_TPFL,'#.#%')}</td>
	           							<td style="text-align: center;">${list.XS_TKJE}</td>
           							</c:if>
           							<c:if test="${ddlx eq '3'}">
           								<td style="text-align: center;">${list.GQ_XSFY}</td>
           							</c:if>
           						</tr>
           					</c:forEach>
            				</table>
            			</c:if>
            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" style="display: none" id="jpzzdd">
            				<tr>
            					<th>选择</th>
            					<th>外部单号</th>
            					<th>PNR</th>
            					<th>乘机人</th>
            					<th>票价</th>
            					<th>机建</th>
            					<th>税费</th>
            				</tr>
            				<c:forEach items="${mapkhdd}" var="map" varStatus="s">
            				<tr>
            					<td class='td_center'><input type='radio' name='ddxh' onclick="jpzzddclick(this)" ddbh="${map.DDBH}" wbdh="${map.WBDH}" 
            					wdpt="${map.WDPT}" wdid="${map.WDID}" xtpj="${map.XS_PJ}" xtjj="${map.XS_JSF}" xttax="${map.XS_TAX}" pnr="${map.XS_PNR_NO}" ${s.index eq 0 ? 'checked' : ''}/></td>
                   				<td class="td_center" title="${fn:length(map.WBDH) gt 12 ? map.WBDH : '' }">
            					<c:choose>
            						<c:when test="${fn:length(map.WBDH) gt 12}">
            							${fn:substring(map.WBDH, 0, 12)}...
            						</c:when>
            						<c:otherwise>
            							${map.WBDH}
            						</c:otherwise>
            					</c:choose>
            					</td>
                   				<td class='td_center'>${map.XS_PNR_NO }</td>
                   				<td class='td_center'>${map.CJR }</td>
                   				<td class='td_center'>${map.XS_PJ }</td>
                   				<td class='td_center'>${map.XS_JSF }</td>
                   				<td class='td_center'>${map.XS_TAX }</td>
            				</tr>
            				</c:forEach>
            			</table>
            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" style="display: none" id="jptfdd">
            				<tr>
            					<th>选择</th>
            					<th>外部单号</th>
            					<th>PNR</th>
            					<th>票号</th>
            					<th>乘机人</th>
            					<th>票价</th>
            					<th>退票比例</th>
            					<th>退票费</th>
            				</tr>
            				<c:forEach items="${maptpdd}" var="map" varStatus="s">
            				<tr>
            					<td class="td_center"><input type="radio" name="tfxh" onclick="jptfddclick(this)" ddbh="${map.TPDH}" wbdh="${map.WBDH}" wdpt="${map.WDPT}" 
            					wdid="${map.WDID}" xttpf="${map.XS_TKJE}" pnr="${map.XS_PNR_NO}" ${s.index eq 0 ? 'checked' : '' }/></td>
            					<td class="td_center" title="${fn:length(map.WBDH) gt 12 ? map.WBDH : '' }">
            					<c:choose>
            						<c:when test="${fn:length(map.WBDH) gt 12}">
            							${fn:substring(map.WBDH, 0, 12)}...
            						</c:when>
            						<c:otherwise>
            							${map.WBDH}
            						</c:otherwise>
            					</c:choose>
            					</td>
            					<td class="td_center">${map.XS_PNR_NO}</td>
            					<td class="td_center">${map.TKNO}</td>
            					<td class="td_center">${map.CJR}</td>
            					<td class="td_center">${map.XS_PJ}</td>
            					<td class="td_center">${vfn:format(map.XS_TPFL,'#.#%')}</td>
            					<td class="td_center">${map.XS_TKJE}</td>
            				</tr>
            				</c:forEach>
            			</table>
            			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" style="display: none" id="jpgqdd">
            				<tr>
            					<th>选择</th>
            					<th>外部单号</th>
            					<th>PNR</th>
            					<th>票号</th>
            					<th>乘机人</th>
            					<th>改签费用</th>
            				</tr>
            				<c:forEach items="${mapgqdd}" var="map" varStatus="s">
            				<tr>
            					<td class="td_center"><input type="radio" name="gqxh" onclick="jpgqddclick(this)" ddbh="${map.GQDH}" wbdh="${map.WBDH}" wdpt="${map.WDPT}"
            					wdid="${map.WDID}" xtgqf="${map.GQ_XSFY}" pnr="${map.XS_PNR_NO}" ${s.index eq 0 ? 'checked' : ''}/></td>
            					<td class="td_center" title="${fn:length(map.WBDH) gt 12 ? map.WBDH : '' }">
            					<c:choose>
            						<c:when test="${fn:length(map.WBDH) gt 12}">
            							${fn:substring(map.WBDH, 0, 12)}...
            						</c:when>
            						<c:otherwise>
            							${map.WBDH}
            						</c:otherwise>
            					</c:choose>
            					</td>
            					<td class="td_center">${map.XS_PNR_NO}</td>
            					<td class="td_center">${map.TKNO}</td>
            					<td class="td_center">${map.CJR}</td>
            					<td class="td_center">${map.GQ_XSFY}</td>
            				</tr>
            				</c:forEach>
            			</table>
            			<input type="hidden" id="jpzzflag"/>
            			<input type="hidden" id="jptfflag"/>
            			<input type="hidden" id="jpgqflag"/>
            			<form action="${ctx}/vedsb/jpbcd/jpcyd/save" class="jqtransform" id="jpcyd" method="POST">
           				  <input type="hidden" name="callback"  value="parent.refresh()" />
			              <input type="hidden" name="closeDiv" value="true"/>
			              <input type="hidden" name="ref" value="true"/>
			              <input type="hidden" id="id" name="id" value="${jpcyd.id }"/>
			              <input type="hidden" name="wbdh" id="wbdh"/><!-- 外部单号 -->
			              <input type="hidden" name="pnrNo" id="pnr"/><!-- PNR -->
			              <input type="hidden" name="xtTkje" id="xt_tkje"/><!-- 系统金额 -->
			              <input type="hidden" name="ddlx" id="ddlx" value="${param.ddlx}"/><!-- 订单类型 -->
			              <input type="hidden" name="wdpt" id="wdpt"/><!-- 网店平台 -->
			              <input type="hidden" name="wdid" id="wdid"/><!-- 网店id -->
			              <input type="hidden" name="ddbh" id="ddbh"/><!-- 订单编号 -->
			              <input type="hidden" id="jpzzpj"/><!-- 机票正常票价 -->
			              <input type="hidden" id="jpzzjj"/><!-- 机票正常机建 -->
			              <input type="hidden" id="jpzzsf"/><!-- 机票正常税费 -->
			              <input type="hidden" id="jptpsf"/><!-- 机票退票费 -->
			              <input type="hidden" id="jpgqsf"/><!-- 机票改签费 -->
            				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
            					<tr>
            						<td class="xsys" width="170" style="text-align: center">
            							差异类型:<select name="cylx" style="width: 80px" id="jpdd" onchange="changecylx(this);">
           											<c:if test="${param.ddlx eq '1' or ddlx eq '1'}">
			            								<option value="1" class="zzd" id="pj" <c:if test="${cylx eq '1'}">selected</c:if>>票价</option>
			            								<option value="2" class="zzd" <c:if test="${cylx eq '2'}">selected</c:if>>机建</option>
			            								<option value="3" class="zzd" <c:if test="${cylx eq '3'}">selected</c:if>>税费</option>
		            								</c:if>
		            								<c:if test="${param.ddlx eq '2' or ddlx eq '2'}">
		            									<option value="4" id="tp" <c:if test="${param.ddlx eq '2' or cylx eq '4'}">selected</c:if>>退票费</option>
		            								</c:if>
		            								<c:if test="${param.ddlx eq '3' or ddlx eq '3'}">
		            									<option value="5" id="gq" <c:if test="${param.ddlx eq '3' or cylx eq '5'}">selected</c:if>>改签费</option>
           										  	</c:if>
            								 </select>
            						</td>
            						<td style="text-align: center">
            						<font id="changeje">网店金额:</font><input type="text" name="wdTkje" size="3" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="请填写网店金额!" id="wd_tkje" onkeyup="getWdCyJe(this)" value="${jpcyd.wdTkjeString}"/><font style="color: red">*</font>
            						</td>
            						<td style="display: none;text-align: center" id="tpbl">
            						网店退票比例:<input type="text" id="tpblInput" name="wdTpfl" size="3" ignore="ignore" dotformat="##.#" datatype="number,dotformat,minvalue,maxvalue" minvalue="0.0"  maxvalue="100" value="${jpcyd.wdTpflDouble}"/><font>%</font>
            						</td>
            						<td style="text-align: center">
            						差异金额:<input type="text" name="cyje" size="3" id="cyje" readonly="readonly" value="${jpcyd.cyjeString}"/>
            						</td>
            					</tr>
            				</table>
           					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	           					<tr>
	           						<td style="text-align: right;">&nbsp;差异说明:</td>
	           						<td>
	           						    <textarea rows="3" cols="61" name="cysm" datatype="s1-500" nullmsg="请填写差异说明">${jpcyd.cysm}</textarea><font style="color: red">*</font>
	           						</td>
	           					</tr>
	           					<tr>
	           						<td class="td_center" colspan="2">
	           							 <input type="button" name="button" onclick="saveJpcyd('${jpcyd.id}')" class="ext_btn ext_btn_submit" value="保存">  &nbsp;
	           							 <c:if test="${sh eq '1'}">
	           							 	<input type="button" name="button" onclick="saveShJpcyd('${jpcyd.id}')" class="ext_btn ext_btn_submit" value="保存并审核">  &nbsp;
	           							 </c:if>
	           							 <input type="button" name="button" onclick="closepj()" class="ext_btn" value="关闭">
	           						</td>
	           					</tr>
           					</table>
            			</form>
            		</div>
            	</div>
            </div>
        </div>
    </div>
  </body>
</html>
