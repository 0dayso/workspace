<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/core/jmultiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/core/jqueryui/jquery-ui.css" />
<script type="text/javascript" src="${ctx}/static/core/jmultiselect/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/core/jmultiselect/jquery.multiselect.js"></script>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/core/js/common.js?_=<%=(new Date()).getTime()%>"></script>

<script type="text/javascript">
    var validator;
	$(function(){
		validator = $("#tfpEditForm").Validform(
		{
		   tiptype:function(msg){  
           	if(msg != ''){  
                layer.msg(msg,2,0);
            }  
           }  
		});
		var syptbs = "${tfp.syptbs}";
		if(syptbs){
			syptbs = syptbs.split(",");
		}
	    //下拉多选框
		$("#wdid").multiselect({
		    height: 200,
		    minWidth: 200,
		    checkAllText: '全选', 
			uncheckAllText: '全不选', 
			selectedList: 100,//预设值最多显示10被选中项
		    hide: ["explode", 500],
		    noneSelectedText: '==请选择==',
		    close: function(){
		      var values= $("#wdid").val();
			  $("#sywd").val(values);
			}
	 	})
	 	$('#wdid').val(syptbs);
		$('#wdid').multiselect("refresh");
	});
  
  function saveTfp(zt){
   // validator.submitForm(false);
  //	var gzmc=document.getElementById("gzmcid").value;
  	var hkgs=document.getElementById("hkgsid").value;
  	var cw=document.getElementById("cwid").value;
  	var qs=document.getElementById("mindate").value;
  	var qz=document.getElementById("maxdate").value;
  	var cfcs=document.getElementById("cfcs").value;
  	var ddcs=document.getElementById("ddcs").value;
  	var qtime=document.getElementById("qtime").value;
  	var ztime=document.getElementById("ztime").value;
  	var tqtss=document.getElementById("tqtss").value;
  	var tqtsz=document.getElementById("tqtsz").value;
  	var qfsjxz=document.getElementById("qfsjxz").value;
  	var iscopy=document.getElementById("flagcopy").value;
  /*
  	if(!checkIs(gzmc)){
  		layer.msg("规则名称必填！",2,0);
  		return;
  	}*/
  	
  	if(!checkIs(qs)){
  		layer.msg("乘机日起必填！",2,0);
  		return;
  	}
  	if(!checkIs(qz)){
  		layer.msg("乘机日止必填！",2,0);
  		return;
  	}
  	if(!validhkgs(hkgs)){
  		return;
  	}
  	if(!validcw(cw)){
  		return;
  	}
  	if(!validjcszm(cfcs,"cfcs")){
  		return;
  	}
  	if(!validjcszm(ddcs,"ddcs")){
  		return;
  	}

  	if(!checkTime(qtime)){
  		layer.msg("有效时间起必填且格式必须是00:00！",2,0);
  		return;
  	}
  	if(!checkTime(ztime)){
  		layer.msg("有效时间止必填且格式必须是00:00！",2,0);
  		return;
  	}
  	if(!checkNum(tqtss)){
  		layer.msg("提前天数起必须是整数！",2,0);
  		return;
  	}
  	if(!checkNum(tqtsz)){
  		layer.msg("提前天数止必须是整数！",2,0);
  		return;
  	}
  	if(!checkNum(qfsjxz)){
  		layer.msg("起飞时间提交限制必须是整数！",2,0);
  		return;
  	}
  	
  	var url="${ctx}/vedsb/cpsz/jpzdtfpgzsz/saveTfp";
  	if(iscopy==2){
  		url="${ctx}/vedsb/cpsz/jpzdtfpgzsz/saveTfp?flag="+iscopy;
  	}
  	var data="zt="+zt+"&"+$("#tfpEditForm").serialize();
  		$.ajax({
     	 		type:"post",
				url:url,
				data:data,
				success:function(result){
					var id=document.getElementById("tfpid").value;
					var tpfp=document.getElementById("tpfp").value;
					if("1" == result){
						if(iscopy==2&&tpfp==1){
							layer.msg("复制退票规则成功！",2,1);
						}else if(iscopy==2&&tpfp==2){
							layer.msg("复制废票规则成功！",2,1);
						}
						else if(id&&tpfp==1){
							layer.msg("修改退票规则成功！",2,1);
						}else if(id&&tpfp==2){
							layer.msg("修改废票规则成功！",2,1);
						}else if(!id&&tpfp==1){
							layer.msg("增加退票规则成功！",2,1);
						}
						else{
							layer.msg("增加废票规则成功！",2,1);
						}
						parent.flush();
						closeEdit();
					}else{
						if(iscopy==2&&tpfp==1){
							layer.msg("复制退票规则失败！",2,1);
						}else if(iscopy==2&&tpfp==2){
							layer.msg("复制废票规则失败！",2,1);
						}
						else if(id&&tpfp==1){
							layer.msg("修改退票规则失败！",2,0);
						}else if(id&&tpfp==2){
							layer.msg("修改废票规则失败！",2,0);
						}else if(!id&&tpfp==1){
							layer.msg("增加退票规则失败！",2,0);
						}
						else{
							layer.msg("增加废票规则失败！",2,0);
						}
					}
					
				}
     	 	});
     	 	
  }
  
  	//关闭编辑窗口
	function closeEdit(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	} 
	//验证航空公司二字码格式
	function validhkgs(hkgs){
		if(!hkgs){
			layer.msg("航空公司2字码必填！",2,0);
			return false;
		}
		if(hkgs!="---"){
			hkgs=hkgs+"/";
		 	if(hkgs.replace(/\w{2}\//g, "").length!=0){
		 		layer.msg("请输入航空公司2字码,多个/分隔，全部可输入---！",2,0);
		 		document.getElementById("hkgsid").focus();
		 		return false;
		 	}
	 	}
	 	return true;
	}

	//验证舱位格式
	function validcw(cw){
		if(!cw){
	 		layer.msg("舱位输入必填！",2,0);
			return false;
	 	}else if(cw!="---"){
	 		var cwarr=cw.replace(/-/g,"/").split("/");
			for(var i=0;i<cwarr.length;i++){
				var regxCw = /^[A-Z]{1}[0-9]?$/;
				if(!cwarr[i]||regxCw.test(cwarr[i]) == false){
					layer.msg("舱位输入格式不正确,请以 \"/\" 分隔舱位,例如:F/V/G1！",2,0);
					return false;
				}
			}
	 	}
	 	return true;
	}
	//对城市三字码进行验证
	function validjcszm(city,id){
		if(city!="---"&&city!=""){
	 		city=city+"/";
		 	if(city.replace(/\w{3}\//g, "").length!=0){
		 		layer.msg("请按格式输入城市机场三字码,多个/分隔，全部可输入---！",2,0);
		 		document.getElementById(id).focus();
		 		return false;
		 	}
	 	}
	 	return true;
	}
	//判断是否为空
	function checkIs(obj){
		if(obj){
			return true;
		}
		return false;
	}
	//验证时分格式:10:00
	function checkTime(obj){
		var time = /^(\d{2}):(\d{2})$/;
		if (!time.test(obj)) { 
			layer.msg("有效时间起的时间点限制的格式不正确！",2,0);
			return false;
		}
		return true;
	}
	//验证是否是数字
	function checkNum(obj){
		var reg = new RegExp("^[0-9]*$");  
		  if(obj&&!reg.test(obj)){ 
		  		layer.msg("数字格式不正确！",2,0);  
		        return false;
		  }
		  return true; 
	}
	//票证类型取值
	function getValue(){
		var pzlx=getStr("pzlxls");
		if(!pzlx){
			pzlx=document.getElementById("getpzlxDefault").value;
		}
		document.getElementById("pzlxid").value=pzlx;
	}
	//乘机人取值
	function getCjrvalue(){
		var cjr=getStr("cjrlxls");
		if(!cjr){
			cjr="1/2/3";
		}
		document.getElementById("cjrlxid").value=cjr;
	}
	//批量取得复选框的值
   function getStr(name){
		var str="";
		$('input[type="checkbox"][name="'+name+'"]').each(function(){
			if($(this).prop("checked")){
				str += $(this).val()+"/";
			}
		});
		if(str){
			str = str.substring(0,str.length-1);
		}
		return str;
  }
  
  function upperCase(x)
	{
		var y=document.getElementById(x).value;
		document.getElementById(x).value=y.toUpperCase();
	}
</script>
</head>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
          	<form id="tfpEditForm" name="tfpEditForm">
       			<input type="hidden" name="tpfp" id="tpfp" value="${param.tpfp }">
       			<input type="hidden" name="id" id="tfpid" value="${tfp.id }">
       			<input type="hidden" name="isdel" value="${empty tfp.isdel ? '0' : tfp.isdel }">
       			<input type="hidden" id="getpzlxDefault">
       			<input type="hidden" id="flagcopy" value="${param.flag }">
       			
       			<!-- 基础信息设置 -->
          		<fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
					<legend>自动${param.tpfp eq 1 ? '退' : '废' }票规则<font color="red">基础信息设置</font>：</legend>
					<table class="ddxtab" align="center" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
						<tr>
							<td align="right">规则名称：</td>
							<td colspan="3">
								<input type="text" id="gzmcid" name="gzmc" value="${tfp.gzmc}" class="input-text lh25" style="width:280px;" datatype="*1-60"  nullmsg="请填写规则名称" >
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="right">适用网店：</td>
							  <td colspan="3">
							  	 <select name="wdid" id="wdid" class="select" title="请选择适用网店" multiple="multiple" style="width:288px;" datatype="*" nullmsg="请选择适用网店">
						       	 	<c:forEach items="${wdzlszList}" var="onewd">
						         		<option value="${onewd.id }" ${fn:contains(tfp.syptbs,onewd.id) ? 'selected' : ''}>${onewd.wdmc }</option> 
						       	 	</c:forEach>
						         </select>
						         <input type="hidden" name= "syptbs" id="sywd" value="${tfp.syptbs }"/>
							  </td>
						</tr>
						
						<c:if test="${param.tpfp eq 1 }">
							<tr>
								<td align="right">客人申请类型：</td>
								<td colspan="3">
									  <input type="radio" name="tplx"  value="1" ${empty tfp.tplx or tfp.tplx eq '1' ? 'checked' : '' }/>自愿退票&nbsp;&nbsp;
									  <input type="radio" name="tplx"  value="0"  ${tfp.tplx eq '0' ? 'checked' : '' }/>非自愿退票
								</td>
							</tr>
						</c:if>
						
						<tr>
							<td align="right">检查客票状态：</td>
							<td colspan="3">
								  <input type="radio" value="0" name="isjckpzt" ${empty tfp.isjckpzt or tfp.isjckpzt eq '0' ? 'checked' : '' }/>检查&nbsp;&nbsp;
								  <input type="radio" value="1" name="isjckpzt" ${tfp.isjckpzt eq '1' ? 'checked' : '' }/>不检查
								  <font color='gray'>(默认校验，非OPEN FOR USE状态走手工退票流程)</font>
							</td>
						</tr>
						
						<tr>
							<td align="right">航空公司：</td>
							<td colspan="3">
								<input type="text" style="width:280px" class="input-text lh25 required max-length-100" name="hkgs" id="hkgsid" value="${tfp.hkgs}" datatype="*"  nullmsg="请输入航空公司" onblur="upperCase(this.id);validhkgs(this.value)">
								<font color="red">*</font>
								<font color='gray'>(支持多个，用 "/"分割。例如：CZ/3U，---表示全部)</font>
							</td>							 
						</tr>
						<tr>
							<td align="right" title="如果选择多个航空公司则手动填写仓位">&nbsp;适用舱位：</td>
						  	<td colspan="3">
						  		<input type="text" style="width:280px" class="input-text lh25 required max-length-1000" name="cw" id="cwid" value="${tfp.cw}" onblur="upperCase(this.id);validcw(this.value);" />&nbsp;<font color="red">*</font>
						  		<font color='gray'>(支持多个，用 "/"分割。例如： F/B/Y,---表示全部)</font>
						  	</td>
						</tr>
						<tr>
							<td align="right">采购来源：</td>
							<td colspan="3">
								<c:forEach items="${vfc:getVeclassLb('10014')}" var="onepzlx" varStatus="pzlxStatus">
		                  	 		 <c:if test = "${onepzlx.id ne '10014'}">
		                  	 			<input type="checkbox" name="pzlxls" value="${onepzlx.ywmc}" ${fn:contains(tfp.pzlx,onepzlx.ywmc) ? 'checked' : ''} onclick="getValue()">${onepzlx.mc }&nbsp;&nbsp;
		                  	 		 </c:if>
		                  	 	</c:forEach>
		                  	 	<font color='gray'>(可多选,不选则视为全选)</font>
		                  	 	<input type="hidden" id="pzlxid" name="pzlx" value="${tfp.pzlx}">
							</td>
						</tr>
						<tr>
							<td align="right">乘机人类型：</td>
							<td colspan="3">
								<label>
									<input type="checkbox" name="cjrlxls" value="1" onclick="getCjrvalue();" ${fn:contains(tfp.cjrlx,'1') ? 'checked' : '' } />成人&nbsp;&nbsp;
								</label>
								<label>
									<input type="checkbox" name="cjrlxls" value="2" onclick="getCjrvalue();" ${fn:contains(tfp.cjrlx,'2') ? 'checked' : '' } />儿童&nbsp;&nbsp;
								</label>
								<label>
									<input type="checkbox" name="cjrlxls" value="3" onclick="getCjrvalue();" ${fn:contains(tfp.cjrlx,'3') ? 'checked' : '' }/>婴儿&nbsp;&nbsp;
								</label>
								<font color='gray'>(可多选,不选则视为全选)</font>
								<input type="hidden" name="cjrlx" value="${tfp.cjrlx }" id="cjrlxid">
							</td>
						</tr>
						<tr>  
						    <td align="right">乘机日始：</td>
							<td>
								<input type="text" name="cfrqs" value="${tfp.cfrqs}" class="input-text required Wdate" size="10" id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 
								<font color="red">*</font>
								乘机日止：<input type="text" name="cfrqz" value="${tfp.cfrqz}" class="input-text required Wdate" size="10" id="maxdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								<font color="red">*</font>
							</td>
						</tr>
					   	<tr>
						  <td align="right" title="可以通过<font color='red'>多个/分隔，全部可输入---.可以通过多选框中出发城市选择</font>辅助添加">&nbsp;出发城市：</td>
						  <td colspan="3">
						  	  <input id="cfcs" type="text" name="cfcity" class="input-text lh25 max-length-4000" maxlength="4000" onblur="upperCase(this.id);validjcszm(this.value,this.id);" style="width:280px" value="${empty tfp.cfcity ? '---' : tfp.cfcity }"><font color='gray'>(支持多个，用 "/"分割。例如： WUH/SHA，---表示全部)</font>
						  </td>	 
						</tr>
					  	<tr>
						  <td align="right" title="可以通过<font color='red'>多个/分隔，全部可输入---.可以通过多选框中到达城市选择</font>辅助添加">&nbsp;到达城市：</td>
						  <td colspan="3">
						      <input id="ddcs" type="text" name="ddcity" class="input-text lh25 max-length-4000" maxlength="4000" onblur="upperCase(this.id);validjcszm(this.value,this.id);" style="width:280px" value="${empty tfp.ddcity ? '---' : tfp.ddcity }"><font color='gray'>(支持多个，用 "/"分割。例如： WUH/SHA，---表示全部)</font>
						  </td>	 
						</tr>
						<tr>
							<td align="right">
								提前天数：
							</td>
							<td colspan="2">
								<input type="text" name="tqtss" value="${tfp.tqtss}" class="input-text lh25" id="tqtss" style="width:60px;" onblur="checkNum(this.value)">&nbsp;至
								<input type="text" name="tqtsz" value="${tfp.tqtsz}" class="input-text lh25" id="tqtsz" style="width:60px;" onblur="checkNum(this.value)">
							          有效时间起 <input type="text" name="yssjq" value="${empty tfp.yssjq ? '00:00' : tfp.yssjq}" size="1" class="input-text lh25" style="width:40px;" onblur="checkTime(this.value)" id="qtime"><font color="red">*</font>&nbsp;至
								<input type="text" name="yssjz" value="${empty tfp.yssjz ? '23:00' : tfp.yssjz}"  class="input-text lh25" style="width:40px;" onblur="checkTime(this.value)" id="ztime"><font color="red">*</font>
							</td>
							<td>
								起飞时间提交限制 <input type="text" name="qfsjxz" value="${tfp.qfsjxz }" id="qfsjxz" class="input-text lh25" style="width:60px;" onblur="checkNum(this.value)">
							</td>
						</tr>
					</table>
             	</fieldset>
             	
				<!-- 自愿转非自愿设置 -->
			    <fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
					<legend>自动退票规则<font color="red">自愿转非自愿设置</font>：</legend>
					<table class="ddxtab"  width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
						  <tr>
			                  <td align="right">是否赌航变：</td>
						  	  <td colspan="3">
						  	  	<input type="radio" value="0" name="ishb" onclick="$('#isbhshow').show();"  ${empty tfp.ishb or tfp.ishb eq '0' ? 'checked' : '' } /> 赌航变
						  	    <input type="radio" value="1" name="ishb" onclick="$('#isbhshow').hide();"  ${tfp.ishb eq '1' ? 'checked' : '' } />不赌航变
						  	    <font color='gray'>(默认赌航变)</font>
						  	  </td>
				  	  	</tr>
				  	  	<tr><td>&nbsp;</td><td colspan="3" width="600px">&nbsp;
				  	  	<table id="isbhshow" style="display:${tfp.ishb eq '1' ? 'none' : '' }"  class="ddxtab" align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
						  	<tr>
		                    	<td>&nbsp;</td>
						  		<td colspan="3">
						  			<fmt:formatNumber var="hbTqsj" value="${empty tfp.hbTqsj ? 0 : tfp.hbTqsj}" pattern="0.##" />
							  		起飞前<input type="text" name="hbTqsj" value="${hbTqsj}" class="input-text lh25" size="1">小时提交退票
							  		<font color='gray'>（在设定时间内有航变提交非自愿退票，否则提交自愿退票）</font>
							  	</td>
					  	    </tr>
						  	<tr>
						  		<td>&nbsp;</td>
						  		<td colspan="3">
						  			<fmt:formatNumber var="hbZxtpf" value="${empty tfp.hbZxtpf ? 0 : tfp.hbZxtpf}" pattern="0.##" />
						  	  		设置最大风险值为<input type="text" name="hbZxtpf" value="${hbZxtpf}" class="input-text lh25" size="1">
						  	    	<font color='gray'>（退票费大于等于设置值时，赌航变） </font>
								</td>
					  	   	</tr>
						   	<tr>
						  	  <td>&nbsp;</td>
						  	  <td colspan="3">
						  	  		<fmt:formatNumber var="hbZxlrl" value="${empty tfp.hbZxlrl ? 0 : tfp.hbZxlrl}" pattern="0.##" />
						  	     	设置利润率最小为<input type="text" name="hbZxlrl" value="${hbZxlrl}" class="input-text lh25" size="1">%
						  	     	<font color='gray'>（利润率高于或等于设定值时，赌航变） </font></td>
					  	   	</tr>
						  	<tr>
								<td>&nbsp;</td>
								<td colspan="3">
									<fmt:formatNumber var="hbZxlr" value="${empty tfp.hbZxlr ? 0 : tfp.hbZxlr}" pattern="0.##" />
							     	设置利润值最小为<input type="text" name="hbZxlr" value="${hbZxlr}" class="input-text lh25" size="1">
							  		<font color='gray'>（利润值高于或等于设定值时，赌航变）</font></td>	
							</tr>
						</table>
						</td></tr>
					</table>
             	</fieldset>
             	
             	
             	<!-- BSP退票设置 -->
			    <fieldset style="border:1px solid black; width: 690px; margin-top:10px;">
					<legend>自动退票规则<font color="red">BSP退票取消座位设置</font>：</legend>
					<input type="hidden" name="jpBspTpszId" id="id" value="${bspsz.id}">
					<table class="ddxtab"  width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
						  <tr>
						  	  <td colspan="4">
						  	   	是否验证乘机人证件号码
						  	  	<input type="radio" value="0" name="yzzjhm"  id="yzzjhm_0"  ${empty bspsz.yzzjhm or bspsz.yzzjhm eq '0' ? 'checked' : '' } /> 验证
						  	    <input type="radio" value="1" name="yzzjhm"  id="yzzjhm_1" ${bspsz.yzzjhm eq '1' ? 'checked' : '' } />不验证
						  	    <font color='gray'>(默认不验证)</font>
						  	  </td>
				  	  	</tr>
				  	  	<tr>
				  	  		<td colspan="3">
				  	  			自动取消座位失败是否提醒
						  	  	<input type="radio" value="0" name="sfsbtx" onclick="$('#issbtxshow').show();"  ${bspsz.sfsbtx eq '0' ? 'checked' : '' } /> 提醒
						  	    <input type="radio" value="1" name="sfsbtx" onclick="$('#issbtxshow').hide();"  ${empty bspsz.sfsbtx or bspsz.sfsbtx eq '1' ? 'checked' : '' } />不提醒
						  	    <font color='gray'>(默认不提醒)</font>
						  	  </td>
						 </tr>
				  	  	<tr><td>&nbsp;</td><td colspan="3" width="600px">&nbsp;
				  	  	<table id="issbtxshow" style="display:${bspsz.sfsbtx  ne '0' ? 'none' : '' }"  class="ddxtab" align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
						  	<tr>
		                    	<td>&nbsp;</td>
						  		<td colspan="3">
							  		短信提醒<input type="text" name="dxtx" value="${bspsz.dxtx}" class="input-text lh25" >
							  		<font color='gray'>（多个号码使用‘/’隔开）</font>
							  	</td>
					  	    </tr>
						  	<tr>
						  		<td>&nbsp;</td>
						  		<td colspan="3">
						  	  		邮箱提醒<input type="text" name="yjtx" value="${bspsz.yjtx}" class="input-text lh25" >
						  	    	<font color='gray'>（多个邮箱使用‘/’隔开） </font>
								</td>
					  	   	</tr>
						   	
						</table>
						</td>
						</tr>
					</table>
             	</fieldset>
             	
           		<table style="margin-top:5px;margin-bottom: 5px;">
           			<tr>
          				<td>
							<input type="button"  value="保 存 " class="ext_btn ext_btn_submit" onclick="saveTfp(${empty tfp.zt ? '0':tfp.zt})" style="margin-left:260px;">
							<c:if test="${tfp.zt eq '0'}">
					    	  	<input type="button"  value="保存并启用"  class="ext_btn ext_btn_submit" onclick="saveTfp(1)"> 
					    	 </c:if>
							<input type="button"  value="关 闭 " class="ext_btn ext_btn_success" onClick="closeEdit()">
						</td> 
           			</tr>
           		</table>
            </form>
          </div>
        </div>
   </div>
  </div>
</body>

</html>