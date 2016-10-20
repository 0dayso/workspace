<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html> 
<head>
<title>退票报表明细</title>

<style>
	.input: {
		width: 85px;
	}
	
	.text_right {
		text-align: right;
	}
	
	.text_left {
		text-align: left;
	}
</style>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
		  $("#hide").click(function(){
		  	$("#tr1").hide();
		  	$("#tr2").hide();
		  	$("#tr3").hide();
		  	$("#tr4").hide();
		  	$("#td3").show();
		  });
		  $("#show").click(function(){
		  	$("#tr1").show();
		  	$("#tr2").show();
		  	$("#tr3").show();
		  	$("#tr4").show();
		  	$("#td3").hide();
		  });
	});
</script>
</head>
<body onload="initPage();">
<div class="container_clear">
 	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/jptpgl/jptpd/query" id="searchForm" name="searchForm" method="post">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="" id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx" value="${param.lx}" id="lx"/>
							<input type="hidden" id="moreid" name="more" value="${param.more }" >
							<input type="hidden" name="countType" value="A" >
							<input type="hidden" name="tp_compid" value="${param.tp_compid}" >
							<input type="hidden" name="tp_detpid" value="${param.tp_detpid}" >
							<input type="hidden" name="tp_userid" value="${param.tp_userid}" >
							<input type="hidden" name="tp_userid" value="${param.tp_userid}" >
							<input type="hidden" name="hkgs" value="${param.hkgs}" >
							<input type="hidden" name="tp_hbh" value="${param.tp_hbh}" >
							<input type="hidden" name="jp_ddcity" value="${param.jp_ddcity}" >
							<input type="hidden" name="cp_userid" value="${param.cp_userid}" >
							<input type="hidden" name="dp_userid" value="${param.dp_userid}" >
							<input type="hidden" name="lrly" value="${param.lrly}" >
							<input type="hidden" name="jp_compid" value="${param.jp_compid}" >
							<input type="hidden" name="jp_deptid" value="${param.jp_deptid}" >
							<input type="hidden" name="jp_userid" value="${param.jp_userid}" >
							<input type="hidden" name="ds_compid" value="${param.ds_compid}" >
							<input type="hidden" name="ds_deptid" value="${param.ds_deptid}" >
							<table  class="table01" border="0" cellpadding="0" cellspacing="0">   
								<tr>
									<td class="text_right" id="dd1">开始日期</td>
									<td class="text_left">
								    	<input type="text" name="ksrq"  class="input-text Wdate" value="${vfn:dateShort()}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>    
									<td class="text_right" id="dd2">结束日期</td>
									<td class="text_left">
								        <input type="text" name="jsrq"  class="input-text Wdate" value="${vfn:dateShort()}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">P&nbsp;&nbsp;&nbsp;N&nbsp;&nbsp;R</td>
									<td class="text_left">
								        <input type="text" class="input1 UpperCase" style="width:90px" value="${param.pnr}" name="pnr" onblur="value=$.trim(this.value).toUpperCase();" />
								        <input type="checkbox" id="ifdbm" name="ifdbm" value="1" ${param.ifdbm eq '1' ? 'checked' : ''} title="勾选表示同时匹配大小编码" >
									</td>
									<td class="text_right">票&nbsp;&nbsp;&nbsp;&nbsp;号</td>
									<td class="text_left">
								        <input type="text" class="input1" style="width: 90px" value="${param.tkno}" name="tkno" />
									</td>
									<td class="text_right">乘&nbsp;机&nbsp;人</td>
									<td class="text_left">
								        <input type="text" class="input1 UpperCase" style="width:80px" value="${param.cjr}" name="cjr" onblur="value=replaceAll(value,'%','').toUpperCase()" />
									</td>
								</tr>	
								<tr>
									<td class="text_right">日期条件</td>
									<td class="text_left">
										<select name="rqtj" style="width:90px">
											<option value="1" ${empty param.rqtj or param.rqtj eq '1' ? 'selected' : ''}>申请日期</option>
											<option value="2" ${param.rqtj eq '2' ? 'selected' : ''}>复核日期</option>
											<option value="3" ${param.rqtj eq '3' ? 'selected' : ''}>调度日期</option>
											<option value="8" ${param.rqtj eq '8' ? 'selected' : ''}>退款日期</option>
											<option value="4" ${param.rqtj eq '4' ? 'selected' : ''}>客户完成日期</option>
											<option value="5" ${param.rqtj eq '5' ? 'selected' : ''}>采购完成日期</option>
											<option value="7" ${param.rqtj eq '7' ? 'selected' : ''}>实际回款日期</option>
											<option value="10" ${param.rqtj eq '10' ? 'selected' : ''}>交票日期</option>
										</select>
									</td>
									<td class="text_right">票证类型</td>
									<td class="text_left">
										<MD:bclass var="cplxList" parid="1004"></MD:bclass>
										<select name="cplx" style="width:90px" onchange="setCplx(this.value);">
									    	<option value="">==请选择==</option>
									    	<c:forEach items="${cplxList}" var="lx">        		        	  
								        		<option value="${lx.YWMC}" ${lx.YWMC eq param.cplx ? 'selected' : ''}>${lx.YWMC}</option>
								        	</c:forEach> 
										</select>
									</td>
									<td class="text_right">复&nbsp;核&nbsp;人</td>
									<td class="text_left" >
											<input type="text" id="fh_usermc" name="fh_usermc" class="input1" style="width:90px" value="${param.fh_usermc}"
												onclick="getUserList(this,{qx:'2'})"
												onkeyup="getUserList(this,{qx:'2'})" value_to_input="fh_userid" />
											<input type="hidden" name="fh_userid" id="fh_userid" value="${param.fh_userid}">
									</td>
									<td class="text_right">外出票单位</td>
									<td class="text_left" >
										<input type="text" id="cu_jsdwmc" name="cu_jsdwmc" class="input1" style="width: 90px" value="${param.cu_jsdwmc}" 
											onclick="getDeptList(this,{qx:1,lx:105505})" onkeyup="getDeptList(this,{qx:1,lx:105505})" value_to_input="cu_jsdw" />
								    	<input type="hidden" name="cu_jsdw" value="${param.cu_jsdw}" />
									</td>
									<td class="text_right">退废类型</td>
									<td class="text_left">
										<select name="tp_type" style="width:80px">
									    	<option value="">=请选择=</option>
									    	<option value="1" ${param.tp_type eq '1' ? 'selected' : ''}>退票</option>
									    	<option value="2" ${param.tp_type eq '2' ? 'selected' : ''}>废票</option>
										</select>
									</td>
								</tr>
								<tr>	
									<td class="text_right" >收银部门</td>
									<td class="text_left">
										<input type="text" id="zf_deptmc" name="zf_deptmc" class="input1" style="width:90px" value="${param.zf_deptmc}" 
											onclick="getDeptList(this,{qx:1})" onkeyup="getDeptList(this,{qx:1})" value_to_input="zf_deptid" />
								    	<input type="hidden" name="zf_deptid"  value="${param.zf_deptid}" />
									</td>
									<td class="text_right">退款状态</td>
									<td class="text_left">
										<select name="tp_fkzt" style="width: 90px">
											<option value="" ${empty param.tp_fkzt ? 'selected' : ''}>==请选择==</option>
											<option value="1" ${param.tp_fkzt eq '1' ? 'selected' : ''}>已退款</option>
											<option value="0" ${param.tp_fkzt eq '0' ? 'selected' : ''}>未退款</option>
										</select>
									</td>
									<td class="text_right">退款科目</td>
									<td class="text_left">
										<MD:zffskm varoption="option" ywlx="all" userid="${VEASMS.bh}" zkfx="" defaultkm="${param.zfkm}"></MD:zffskm>
										<select name="zfkm" style="width: 90px">
											<option value="">==请选择==</option>
											${option}
										</select>
									</td>
									<td class="text_right">退款公司</td>
									<td class="text_left">
										<input type="text" id="tk_compmc" name="tk_compmc" class="input1" style="width:90px" size="10" value="${param.tk_compmc}"
											onclick="getCompList(this,{qx:'2'})" onkeyup="getCompList(this,{qx:'2'})" value_to_input="tk_compid" />
										<input type="hidden" id="tk_compid" name="tk_compid" value="${param.tk_compid}" />
									</td>		
									<td class="text_right" id="td1" style="display:none;">退款部门</td>
									<td class="text_left" id="td2" style="display:none;">
										<input type="text" id="tk_deptmc" name="tk_deptmc" class="input1" style="width:80px" value="${param.tk_deptmc}" 
											onclick="getDeptList(this,{qx:1,compid:$('tk_compid').value})" onkeyup="getDeptList(this,{qx:1,compid:$('tk_compid').value})" value_to_input="tk_deptid" />
								    	<input type="hidden" name="tk_deptid" value="${param.tk_deptid}" />
									</td>
									<td  class="text_right"  id="td3" style="text-align: center;" colspan="2">
							            <input type="submit"  name="tosearch" class="ext_btn ext_btn_submit" value=" 查 询 ">
										<input type="button"  value=" 导 出 " name="export" class="ext_btn ext_btn_submit" onClick="toExport('332875','${param.countType}');">
							            <a href="###"  id="show" >更多>></a>
							      	</td>
								</tr>
								<tr id="tr1" style="display:none;">
									<td class="text_right">客户类型</td>
									<td class="text_left">
									</td>
									<td class="text_right" id="kh1"></td>
									<td class="text_left" id="kh2"></td>
									<td class="text_right">
										订票公司
									</td>
									<td class="text_left">
										<input type="text" id="dp_compmc" name="dp_compmc" class="input1" style="width:90px" value="${param.dp_compmc}"
											onclick="getCompList(this,{qx:'2'})" onkeyup="getCompList(this,{qx:'2'})" value_to_input="dp_compid" />
										<input type="hidden" id="dp_compid" name="dp_compid" value="${param.dp_compid}" />
									</td>
							        <td class="text_right">订票部门</td>
									<td class="text_left">
									<c:if test="${empty param.dp_deptmc and not empty param.dp_deptid}">
										<MD:dept var="dp_deptlist" bh="${param.dp_deptid}"></MD:dept>			
									</c:if>
										<input type="text" id="dp_deptmc" name="dp_deptmc" class="input1" style="width: 90px" value="${empty param.dp_deptmc ? dp_deptlist[0].MC : param.dp_deptmc }" 
											onclick="getDeptList(this,{qx:1,compid:$('dp_compid').value})" onkeyup="getDeptList(this,{qx:1,compid:$('dp_compid').value})" value_to_input="dp_deptid" />
										<input type="hidden" id="dp_deptid" name="dp_deptid" value="${param.dp_deptid}" />
									</td>
									<td colspan="2" align="center">
										<select style="width:80px" name="glgj">
								        	<option value="">全部</option>
								        	<option value="1" ${param.glgj eq '1' ? 'selected' : ''}>国内</option>
								        	<option value="0" ${param.glgj eq '0' ? 'selected' : ''}>国际</option>
								        </select>
									</td>
								</tr>
								<tr id="tr2" style="display:none;">
									<td class="text_right" >采购状态</td>
									<td class="text_left" colspan="3">
										<c:choose>
							     			<c:when test="${empty paramValues.cg_wcf}">
							     				<c:set var="cgwcf" value="" /> 
							     			</c:when>
							     			<c:otherwise>
									     		<c:set var="cgwcf" value="${fn:join(paramValues.cg_wcf,',')}" />
							     			</c:otherwise>
							     		</c:choose>
									</td>
									<td class="text_right">提&nbsp;交&nbsp;人</td>  
								<td class="text_left" >
										<input type="text" id="cgsq_uermc" name="cgsq_uermc" class="input1" style="width:90px" value="${param.cgsq_uermc}"
											onclick="getUserList(this,{qx:'2'})"
											onkeyup="getUserList(this,{qx:'2'})" value_to_input="cgsq_uerid" />
										<input type="hidden" name="cgsq_uerid" id="cgsq_uerid" value="${param.cgsq_uerid}">
								</td>
									<td class="text_right">完成公司</td>
									<td class="text_left">
										<input type="text" id="tp_wccompmc" name="tp_wccompmc" class="input1" style="width:90px" size="10" value="${param.tp_wccompmc}"
											onclick="getCompList(this,{qx:'2'})" onkeyup="getCompList(this,{qx:'2'})" value_to_input="tp_wccompid" />
										<input type="hidden" id="tp_wccompid" name="tp_wccompid" value="${param.tp_wccompid}" />
									</td>		
									<td class="text_right">完成部门</td>
									<td class="text_left">
										<input type="text" id="tp_wcdeptmc" name="tp_wcdeptmc" class="input1" style="width:80px" value="${param.tp_wcdeptmc}" 
											onclick="getDeptList(this,{qx:1,compid:$('tp_wccompid').value})" onkeyup="getDeptList(this,{qx:1,compid:$('tp_wccompid').value})" value_to_input="tp_wcdeptid" />
								    	<input type="hidden" name="tp_wcdeptid" value="${param.tp_wcdeptid}" />
									</td>
								</tr>
								<tr id="tr3" style="display:none;">
									<td class="text_right">客户状态</td>
									<td class="text_left" colspan="3">
											<c:choose>
								     			<c:when test="${empty paramValues.tp_tpzt}">
								     				<c:set var="tptpzt" value="" /> 
								     			</c:when>
								     			<c:otherwise>
										     		<c:set var="tptpzt" value="${fn:join(paramValues.tp_tpzt,',')}" />
								     			</c:otherwise>
								     		</c:choose>
									</td>
									<td align="center" colspan="2">
										<c:set var="sykbs" value="${fn:join(paramValues.ykbs,',')}" />
										<input type="checkbox" name="ykbs" value="1" ${fn:indexOf(sykbs,'1') ne -1 ? 'checked' : ''}><font color="blue">盈</font>
										<input type="checkbox" name="ykbs" value="2" ${fn:indexOf(sykbs,'2') ne -1 ? 'checked' : ''}><font color="red">亏</font>
										<input type="checkbox" name="ykbs" value="3" ${fn:indexOf(sykbs,'3') ne -1 ? 'checked' : ''}><font color="green">平</font>
									</td>
									<td class="text_right">结算类型</td>
									<td class="text_left">
										<MD:bcommjslx compid="${VEASMS.zgscompid}" var="jslxList"></MD:bcommjslx>
										<select name="jslx" style="width:90px" class="input1 required">
							              <option value="">==全部==</option>
							              <c:forEach items="${jslxList}" var="mm">
							              	<option value="${mm.KHLX}" ${mm.KHLX eq param.jslx ? 'selected' : ''}>${mm.LXMC}</option>
								 		  </c:forEach>
							           	</select>
									</td>
									<td class="text_right">航班状态</td>
									<td class="text_left">
										<c:set var="hb_dynamics" value="${fn:join(paramValues.hb_dynamic,',')}" />
										<input type="checkbox" name="hb_dynamic" value="1" ${fn:indexOf(hb_dynamics,'1') ne -1  ? 'checked' : '' }/>延误
										<input type="checkbox" name="hb_dynamic" value="2" ${fn:indexOf(hb_dynamics,'2') ne -1  ? 'checked' : '' }/>取消
									</td>
								</tr>
								<tr id="tr4" style="display:none;">
									<td class="text_right">
										<span id="cgzfkm1">采购退款科目</span>
									</td>
									<td class="text_left" > 
										<span id="cgzfkm2"><select name="cgzfkm" id="cgzfkm" style="width: 100px" class="required">
											<option value="">==请选择==</option>
										</select></span>
									</td>
									<td class="text_right">外部单号</td>
									<td class="text_left">
								        <input type="text" class="input1" style="width: 160px" value="${param.gy_ddbh}" name="gy_ddbh" />
									</td>
									<td colspan="4"/>
									<td align="center" colspan="2">
										<input type="submit"  name="tosearch" class="ext_btn ext_btn_submit" value=" 查 询 ">
										<input type="button"  value=" 导 出 " name="export" class="ext_btn ext_btn_submit" onClick="toExport('332875','${param.countType}');">
										<a href="###"  id="hide"><<隐藏</a>
									</td>
								</tr>
							</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
       <div  class="mt10">
      	<div id="list_table_page1">
        	<!-- 分页  ID不能修改-->
        </div>
        <div class="box span10 oh" id="list_table">
             <!-- 显示列表 ID不能修改 -->
        </div>
        <br>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
        </div>
     </div>
	</div>

</body>
<script type="text/javascript">
	

	function initPage(){
		setCplx('${param.cplx}');
		_showMore(4,"${param.more}");
	}
  	
	function toExport(mkbh,bbh){
		<c:if test="${listForm.allCount > 0}">
				var cs2127 = "${cs2127}";
				var areaTime = 0;
				if(isNaN(cs2127) || isBlank(cs2127)){
					areaTime = 7;
				}else{
					areaTime = parseInt(cs2127);
				}
				var ksrq = "${param.ksrq}";
				var jsrq = "${param.jsrq}";
				if(DateDiff(jsrq,ksrq) > areaTime){
					alert("只能导"+areaTime+"天的数据!");
					return;
				}
			</c:if>
			<c:if test="${empty listForm or listForm.allCount <= 0}">
				alert("没有需要导出的数据，请先查询！");
			</c:if>
	}
	
		function toSearch(){
		lockScreen("系统正在处理您的操作,请稍候!");
		document.listForm.submit();
		}
		
//退废单详
function showTfDetail(id,mkbh,gnbh){
	if(isBlank(mkbh) || isBlank(gnbh)){
		return;
	}
		vopen("/asms/ydzx/ticket_return_add.shtml?p=detail&forward=readonly&id="+id+"&mkbh="+mkbh+"&gnbh="+gnbh,"",960,580);
	}
	function setCplx(cplx){
		if(cplx == "BPET" || cplx == "OP"){
			$("cgzfkm1").show();
			$("cgzfkm2").show();
		  	setZfkm('cgzfkm',{pt:'CG',cplx:cplx,userid:'${VEASMS.bh}',defaultkm:'${param.cgzfkm}'},'cgzffs');
	  	}else{
	  		$("cgzfkm1").hide();
			$("cgzfkm2").hide();
	  	}
	}
</script>
</html>
