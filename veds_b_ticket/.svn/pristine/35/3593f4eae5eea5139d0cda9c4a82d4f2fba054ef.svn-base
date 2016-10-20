<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html> 
<head>
<title>退废票报表</title>
<link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
<style type="text/css">
	.popupcontent{
			position: absolute;
			visibility: hidden;
			overflow: hidden;
			border:1px solid #CCC;
			background-color:#F9F9F9;
			border:1px solid #333;
			padding:5px;
			z-index:1024;
		}
		
	.input: {
		width: 85px;
	}
	
	.text_right {
		text-align: right;
	}
	
	.text_left {
		text-align: left;
	}
	a {
		text-decoration: none;
	}
</style>
<script type="text/javascript">
	XSTFPZT = '${vfn:toJSON(vfn:dictList('XSTFPZT'))}';
    CGTFPZT = '${vfn:toJSON(vfn:dictList('CGTFPZT'))}';
	$(function(){
		
		var url="${ctx}/vedsb/bbzx/tpbb/queryWdList";
			$.ajax({
				type:"post",
				url:url,
				success:function(result){
					var zfkm=result.SHZFKMDATA;
					for(var i=0;i<zfkm.length;i++){
						var sel='${param.cg_tkkm}'==zfkm[i].kmbh ? 'selected' : '';
						var $opt="<option value="+zfkm[i].kmbh+" "+sel+">"+zfkm[i].kmmc+"</option>";
						$("#cg_tkkm").append($opt);
					}
				}
			});
		
		var load="${param.load}";
	    if(load=="1"){
	      if('${param.wdid}' != ''){
	      	$("#mrwdid").val('${param.wdid}');
	      }
		  $("#searchForm").submit();
		}
		
	});
	
	jQuery.download = function(url, data, method){    // 获得url和data
	    if( url && data ){ 
	        // data 是 string 或者 array/object
	        data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
	        var inputs = '';
	        jQuery.each(data.split('&'), function(){ 
	            var pair = this.split('=');
	            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
	        });        // request发送请求
	        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
	    };
	};
	 $(document).ready(function(){
		  $("#hide").click(function(){
		  	$("#tr1").hide();
		  	$("#tr2").hide();
		  	$("#tr3").hide();
		  	$("#tr4").hide();
		  	$("#tr5").hide();
		  	$("#td3").show();
		  	
		  });
		  $("#show").click(function(){
		  	$("#tr1").show();
		  	$("#tr2").show();
		  	$("#tr3").show();
		  	$("#tr4").show();
		  	$("#tr5").show();
		  	$("#td3").hide();
		  });
		  
	});
	function detail(wdpt,wdid,hc,cgly,cg_blr,xs_blr) {
		var tjfs = "${param.tjfs}";
		var csval="";
		var url="${ctx}/vedsb/bbzx/tpbb/viewjptpd_report?load=1&tjfs=1";
		if (tjfs == "2") {
			csval = "&hc=" + hc;
		}  else if (tjfs == "4") {
			csval = "&wdid="+ wdid;
		} else if (tjfs == "5") {
			csval = "&cgly=" + cgly;
		} else if (tjfs == "7") {
			csval = "&cg_blr=" + cg_blr;
		}else if (tjfs == "8") {
			csval = "&xs_blr=" + xs_blr;
		} 
		url  = url + csval + "&" + $("#searchForm").serialize();
		window.open(url);
	}

    //退票单详
	function detailTpd(tpdh){
	   var url="${ctx}/vedsb/jptpgl/jptpd/tpdDetail_"+tpdh;
	   window.open(url);
	}
    
    function toExport(){
       var url="${ctx}/vedsb/bbzx/tpbb/query";
       $.download(url,"export="+$("#vcexpfield").val()+"&countType=1&"+$("#searchForm").serialize(),"post");
    }
    
    function toSearch(){
		var ksrq=$("#ksrq").val();
    	var jsrq=$("#jsrq").val();
    	if(!ksrq){
    		layer.msg("起始日期不能为空！",2,0);
    		return false;
    	}
    	
    	if(!jsrq){
    		layer.msg("结束日期不能为空！",2,0);
    		return false;
    	}
    	var flag = checkRqkd(ksrq,jsrq,6);
		if(!flag){
			layer.msg("日期跨度不能大于6个月。",2,0);
			return false;
		}
		var ii = layer.load('系统正在处理您的操作,请稍候!');
 		document.searchForm.action = "${ctx}/vedsb/bbzx/tpbb/query?time=1";  
	   	document.searchForm.submit();
	}
   
	   //根据cs参数检查日期跨度
	function checkRqkd(ksrq,jsrq,cs){
		var flag = true;
		var ksrqDate = strToDate(ksrq);
		var jsrqDate = strToDate(jsrq);
		var checkDate = ksrqDate.addMonth(parseInt(cs));
		if(parseInt(cs)>0){
			if(jsrqDate.getTime() > checkDate.getTime()){
				flag=false;
				return flag;
			}
		}
		return flag;
	}
	//传入日期格式字符串转化为Date型   支持yyyy-mm-dd , yyyy-mm-dd hh24:mi:ss两种格式
	function strToDate(str){
		var date;
		// yyyy-mm-dd
		var strArr = str.split("-");
		var strYear = strArr[0];//年	
		var strMonth= strArr[1];//月
		var strDay  = strArr[2];//日
	    date = new Date(strYear,strMonth-1,strDay);
		return date;
	}
	
	//获得当前日期经过num个月后的日期
	Date.prototype.addMonth = function(num) { 
		var tempDate=this.getDate(); 
		this.setMonth(this.getMonth()+num); 
		if(tempDate!=this.getDate()){
			this.setDate(0);
		}
		return this; 
	} 
</script>
</head>
<body>
	<div class="container_clear">
		<div id="search_bar" class="mt10">
			<div class="box">
				<div class="box_border">
					<div class="box_center pt10 pb10">
						<form action="${ctx}/vedsb/bbzx/tpbb/query" id="searchForm" name="searchForm" method="post">
							<input type="hidden" name="pageNum" value="1" id="start" /> 
							<input type="hidden" name="orderBy" value="ddsj desc" id="orderBy" /> 
							<input type="hidden" name="pageSize" value="10" id="pageSize" /> 
							<input type="hidden" name="hkgs" value="${param.hkgs}">
							<input type="hidden" name="cg_blr" value="${param.cg_blr}"/>
							<input type="hidden" name="xs_blr" value="${param.xs_blr}"/>
							<table class="table01" border="0" cellpadding="0" cellspacing="0">
								<!-- 第一行-->
								<tr>
									<td class="text_right">
									<select id="rqtj" name="rqtj" style="width:105px;height: 25px;">
											<option value="1"  ${empty param.rqtj or param.rqtj eq '1' ? 'selected' : ''}>申请日期</option>
											<option value="2"  ${param.rqtj eq '2' ?  'selected' : ''}>销售办理日期</option>
											<option value="3"  ${param.rqtj eq '3' ?  'selected' : ''}>采购提交日期</option>
											<option value="4"  ${param.rqtj eq '4' ?  'selected' : ''}>采购办理日期</option>
											<option value="5"  ${param.rqtj eq '5' ?  'selected' : ''}>实际回款日期</option>
											<option value="6" ${param.rqtj eq '6' ? 'selected' : ''}>起飞日期</option>
									</select></td>
									<td class="text_left">
										<input type="text" id="ksrq" name="ksrq" style="width:90px" class="input-text Wdate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right" id="dd2">至&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td class="text_left">
								        <input type="text" id="jsrq" name="jsrq" style="width:90px" class="input-text Wdate" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" size="10"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
									</td>
									<td class="text_right">P&nbsp;N&nbsp;R</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width:90px"  value="${param.pnr}" name="pnr" onblur="value=$.trim(this.value).toUpperCase();" /> 
									</td>
									<td class="text_right">大编码</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width:90px"  value="${param.bigpnr}" name="bigpnr" onblur="value=$.trim(this.value).toUpperCase();" /> 
									</td>
									<td class="text_right">国内国际</td>
									<td class="text_left">
										<select style="width:120px;height: 25px;" name="gngj" >
												<option value="">全部</option>
												<option value="1" ${param.gngj eq '1' ? 'selected' : ''}>国内</option>
												<option value="0" ${param.gngj eq '0' ? 'selected' : ''}>国际</option>
										</select>
									</td>
								</tr>
								<!-- 第2行-->
								<tr>
									<td class="text_right">采购来源</td>
									<td class="text_left">
										<select name="cgly" style="width:90px;height: 25px;">
											<option value="">==请选择==</option>
											<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
											   <c:if test="${oneLx.id ne '10014'}">
											   		<option value="${oneLx.ywmc}" ${oneLx.ywmc eq param.cgly ? 'selected' : ''}>${oneLx.ywmc}</option>
											   </c:if>
											</c:forEach>
										</select>
									</td>
									<td class="text_right"><span id="cgzfkm1">采购退款科目</span></td>
									<td class="text_left">
										<span id="cgzfkm2">
											<select name="cg_tkkm" id="cg_tkkm" style="width:90px;height: 25px;" class="required">
												<option value="">==请选择==</option>
											</select>
										</span>
									</td>
									<td class="text_right">退废类型</td>
									<td class="text_left">
										<select id="tflx" name="tflx" style="width:92px;height: 25px;" onchange="setSfzytp();">
											<option value="">=请选择=</option>
											<option value="1" ${param.tflx eq '1'  ? 'selected' : ''}>退票</option>
											<option value="2" ${param.tflx eq '2'  ? 'selected' : ''}>废票</option>
										</select>
									</td>
									<td class="text_right">订单编号</td>
									<td class="text_left">
										<input type="text" id="ddbh" name="ddbh" class="input-text lh25" style="width:90px" class="input-text lh25" value="${param.ddbh}" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td class="text_right">统计方式</td>
									<td class="text_left">
										<select id="countType" name="tjfs" style="width:120px;height: 25px;">
											<option value="1"  ${empty param.tjfs or param.tjfs eq '1' ? 'selected' : ''}>按明细统计</option>
											<option value="2"  ${param.tjfs eq '2' ? 'selected' : ''}>按航程统计</option>
											<option value="4"  ${param.tjfs eq '4' ? 'selected' : ''}>按网店统计</option>
											<option value="5"  ${param.tjfs eq '5' ? 'selected' : ''}>按采购来源统计</option>
											<option value="7"  ${param.tjfs eq '7' ? 'selected' : ''}>按采购办理人统计</option>
											<option value="8"  ${param.tjfs eq '8' ? 'selected' : ''}>按销售办理人统计</option>
										</select>
									</td>
								</tr>
								<!-- 第3行-->
								<tr>
								    <td class="text_right">政策代码</td>
									<td class="text_left">
										<input type="text" class="input-text lh25"  style="width:88px" value="${param.wd_zcdm}" name="wd_zcdm" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td class="td_right">网店</td>
					   				 <td class="ddgl">
						                  <select name="wdid" id="wdid" class="select" style="width:90px;">
							                  	<option value="" id="mrwdid">全部</option>
							                  	<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
													<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
												</c:forEach>
									      </select>
				      				  </td>
									<td class="text_right">乘 机 人</td>
									<td class="text_left">
										<input type="text" style="width:90px" class="input-text lh25"  value="${param.cjr}" name="cjr" onblur="value=$.trim(this.value);" />
									</td>
									<td class="text_right">航程类型</td>
									<td class="text_left">
										<select id="tp_hclx" name="tp_hclx" style="width:92px;height: 25px;" class="input1">
											<option value="" ${empty param.tp_hclx ? 'selected' : ''}>==全部==</option>
											<option value="1" ${param.tp_hclx eq '1' ? 'selected' : ''}>单程</option>
											<option value="2" ${param.tp_hclx eq '2' ? 'selected' : ''}>往返</option>
											<option value="3" ${param.tp_hclx eq '3' ? 'selected' : ''}>联程</option>
											<option value="4" ${param.tp_hclx eq '4' ? 'selected' : ''}>缺口</option>
										</select>
									</td>
									<td class="text_right">航&nbsp;&nbsp;程</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 120px" value="${param.hc}" name="hc" />
									</td>
								</tr>
								<!-- 第4行 -->
							
								<tr id="tr2" >
									<td class="text_right">销售状态</td>
									<td class="text_left">
										<select style="width:90px;height: 25px;"  id="xs_tpzt" name="xs_tpzt"   >
											<option value=""  ${empty param.xs_tpzt ? 'selected' : ''}>全部</option> 
	              							<c:forEach items="${vfn:dictList('XSTFPZT')}" var="oneZt">
						                        <option value="${oneZt.value}" ${ param.xs_tpzt eq oneZt.value ? 'selected' : ''} >${oneZt.mc}</option>
							                </c:forEach>
										</select>
									</td>
									<td class="text_right">采购状态</td>
									<td class="text_left">
										<select style="width:90px;height: 25px;" id="cg_tpzt" name="cg_tpzt" >
											<option value=""  ${empty param.cg_tpzt ? 'selected' : ''}>全部</option> 
	              							<c:forEach items="${vfn:dictList('CGTFPZT')}" var="oneZt">
						                        <option value="${oneZt.value}" ${ param.cg_tpzt eq oneZt.value ? 'selected' : ''}>${oneZt.mc}</option>
							                </c:forEach>
										</select>
									</td>
									<td class="text_right">外部退单号</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 90px" value="${param.wbdh}" name="wbdh" onblur="value=$.trim(this.value).toUpperCase();"/>
									</td>
									<td class="text_right">外部订单号</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 90px" value="${param.wbddbh}" name="wbddbh" onblur="value=$.trim(this.value).toUpperCase();" />
									</td>
									<td class="text_right">票&nbsp;&nbsp;号</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 120px" value="${param.tkno}" name="tkno" />
									</td>
								</tr>
								
								<!-- 第5行  -->
								
								<tr id="tr4" >
									<td align="center" colspan="2">
									<input type="checkbox" id="ykbs1" name="ykbs" value="1" ${ ykbs[0] eq '1' or ykbs[1] eq '1' or ykbs[2] eq '1'? 'checked' : ''}/>
									<font color="blue">盈</font> 
									<input type="checkbox" id="ykbs2" name="ykbs" value="2"  ${ ykbs[0] eq '2' or ykbs[1] eq '2' or ykbs[2] eq '2'? 'checked' : ''}/>
									<font color="red">亏</font> 
									<input type="checkbox" id="ykbs3" name="ykbs" value="3"  ${ ykbs[0] eq '3' or ykbs[1] eq '3' or ykbs[2] eq '3'? 'checked' : ''}/>
									<font color="green">平</font></td>
									<%--<td class="text_right">航班状态</td>
									<td class="text_left">
										<input type="checkbox"  name="hbzt" value="1" ${hbzt[0] eq '1' or hbzt[1] eq '1' ? 'checked': ''}/>延误
										<input type="checkbox"  name="hbzt" value="2" ${hbzt[0] eq '2' or hbzt[1] eq '2' ? 'checked': ''}/>取消
									</td>	--%>
									<td class="text_right" colspan="10" >
										<input type="button" id="tosearch" class="ext_btn ext_btn_submit" value=" 查 询 " onclick="toSearch();"> 
										<input type="button" value=" 导 出 " class="ext_btn ext_btn_submit" onclick="toExport();">
										<!--  <a href="###" id="hide"><<隐藏</a> -->
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="mt10">
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
	<!-- 按明细统计 -->
	<c:if test="${empty param.tjfs or param.tjfs eq '1'}">
		<%@include file="jptpd_report_mx.jsp"%>
    </c:if>
    <!-- 按分组统计 -->
	<c:if test="${param.tjfs ne '1'}">
	    <%@include file="jptpd_report_count.jsp"%>
	</c:if>
<input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
</body>
<script type="text/javascript">
	function initPage(){
		changeZkfx("${param.zkfx}",{width:90,defaultNameVlaue:'${param.khmc}',defaultHiddenValue:'${param.khid}'});
		setCplx("${param.cplx}");
		setSfzytp();
		//setCountType();
		_showMore(5,"${param.more}");
		initCheckBox();
	}
	
	/*
	function toExport(){
		var countType = "${param.countType}";
		if(countType == "1"){
			<c:if test="${listForm.allCount > 0}">
				document.exportForm.submit();
			</c:if>
			<c:if test="${empty listForm or listForm.allCount <= 0}">
				alert("没有需要导出的数据，请先查询！");
			</c:if>
		}else{
			<c:if test="${fn:length(reportList) > 0}">
				document.exportForm.submit();
			</c:if>
			<c:if test="${empty reportList or fn:length(reportList) <= 0}">
				alert("没有需要导出的数据，请先查询！");
			</c:if>
		}
	}
	*/
	function toExport2(mkbh,bbh){
		var countType = "${param.countType}";
		if(countType == "1"){
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
		}else{
			<c:if test="${fn:length(reportList) > 0}">
				var curl = '${curl}';
			</c:if>
			<c:if test="${empty reportList or fn:length(reportList) <= 0}">
				alert("没有需要导出的数据，请先查询！");
			</c:if>
		} 
  	} 
	
	function setCplx(cplx){
		if(cplx == "BPET" || cplx == "OP" || cplx == "GP"){
			$("cgzfkm1").show();
			$("cgzfkm2").show();
		  	setZfkm('cgzfkm',{pt:'CG',cplx:cplx,userid:'${VEASMS.bh}',defaultkm:'${param.cgzfkm}'},'cgzffs');
	  	}else{
	  		$("cgzfkm1").hide();
			$("cgzfkm2").hide();
	  	}
	}
	
	function setSfzytp(){
		var tflx = $("tflx").value;
		if(tflx == "1"){
			$('sfzytp').show();
			$('sfzytp1').show();
		}else{
			$('sfzytp').hide();
			$('sfzytp1').hide();
			$('sfzytp').value = "";
		}
	}
	
	function setCountType(){
		var rqtj = $("rqtj");
		var counttype = $("countType");
		if(rqtj.value == "5"){
			counttype.options.add(new Option("按票证类型/采购退款科目统计","27"));
		}else{
			$A(counttype.options).each(
				function(a){
					if(a.value == 27){
						counttype.removeChild(a);
					}
				}
			);
		}
		counttype.value = "${empty param.countType ? '1' : param.countType}";
	}
	
</script>


<!-- 客户状态 -->
<div class="popupcontent" style="width: 90px" id="div2" onmouseout="hidePanel(this.id)" onmouseover="showPanel('khztShow',this.id)">
	<input type="checkbox" onclick="saveValue()" name="checkBox" value="1"/><span id='1' value="已申请">已申请</span></br>
	<input type="checkbox" onclick="saveValue()" name="checkBox" value="3"/><span id='3' value="已调度">已调度</span></br>
	<input type="checkbox" onclick="saveValue()" name="checkBox" value="6"/><span id='6' value="已打单">已打单</span></br>
	<input type="checkbox" onclick="saveValue()" name="checkBox" value="4"/><span id='4' value="配送中">配送中</span></br>
	<input type="checkbox" onclick="saveValue()" name="checkBox" value="5"/><span id='5' value="已完成">已完成</span></br>
</div>
<!-- 采购状态 -->
<div class="popupcontent" style="width: 90px"  id="div1" onmouseout="hidePanel(this.id)" onmouseover="showPanel('showValue',this.id)" >
	<input type="checkbox" onclick="saveValue1()" name="checkBox2" value="0"/><span id='01' value="未办理">未办理</span></br>
	<input type="checkbox" onclick="saveValue1()" name="checkBox2" value="8"/><span id='81' value="提交中">提交中</span></br>
	<input type="checkbox" onclick="saveValue1()" name="checkBox2" value="2"/><span id='21' value="已提交">已提交</span></br>
	<input type="checkbox" onclick="saveValue1()" name="checkBox2" value="3"/><span id='31' value="拒绝">拒绝</span></br>
	<input type="checkbox" onclick="saveValue1()" name="checkBox2" value="1"/><span id='11' value="已完成">已完成</span></br>
</div>
<script type="text/javascript">
	//获得input框的绝对位置
	function showPanel(valueId,divId){
		var l=getLocation($(valueId),'aa');
		var div=$(divId);
		div.style.left=l.left;
		div.style.top=l.top+20+"px";
		div.style.width="90px";
		div.style.visibility = "visible";
	}
	//隐藏div
	function hidePanel(divId){
		var e = e||window.event;
		var obj=$(divId);
		if(obj.contains(e.toElement)) return;
		obj.style.visibility = "hidden";
	}
	
	//保存值
	function saveValue1(){
		var show=$("showValue");
		var hide=$("hideValue");
		var value="";
		var hideValue="";
		var arr=document.getElementsByName("checkBox2");
		for(var i=0;i<arr.length;i++){
			if(arr[i].checked==true){
				value+=$(arr[i].value+"1").value+",";
				hideValue+=arr[i].value+"_";
			}
		}
		show.value=value;
		show.title=value;
		hide.value=hideValue;
	}
	//保存值
	function saveValue(){
		var show=$("khztShow");
		var hide=$("khztHide");
		var value="";
		var hideValue="";
		var arr=document.getElementsByName("checkBox");
		for(var i=0;i<arr.length;i++){
			if(arr[i].checked==true){
				value+=$(arr[i].value).value+",";
				hideValue+=arr[i].value+"_";
			}
		}
		show.value=value;
		show.title=value;
		hide.value=hideValue;
	}
	//初始化多选下拉框
	function initCheckBox(){
		var tpzt="${param.tp_tpzt}";
		var cgzt="${param.cg_wcf}";
		var arr1=tpzt.split("_");
		var arr2=cgzt.split("_");
		var c1=document.getElementsByName("checkBox");
		if(arr1!=null&&arr1.length>0){
			for(var i=0;i<c1.length;i++){
				if(arr1.indexOf(c1[i].value)>-1){
					c1[i].checked=true;
				}
			}
			saveValue();
		}
		var c2=document.getElementsByName("checkBox2");
		if(arr2!=null&&arr2.length>0){
			for(var i=0;i<c2.length;i++){
				if(arr2.indexOf(c2[i].value)>-1){
					c2[i].checked=true;
				}
			}
			saveValue1();
		}
	}
</script>
</html>