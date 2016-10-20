<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js"></script>
<style type="text/css">
	a {
		text-decoration: none;
	}
</style>
<script type="text/javascript">

	$(function(){
		//航司控件
		$("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
	});
	
	//航司隐藏框写值
	function clearValue(obj,hkgs_id){
	   if(obj.value==""){
		   $("#"+hkgs_id).val("");
	   }
 	}

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
	 //点击查询
	 function queryPage(){
	 		var starttime=document.getElementById("cpdates").value;
	 		var endtime=document.getElementById("cpdatez").value;
	 		if(!starttime){
	 			layer.msg("出票日始不能为空。",2,0);
	 			return false;
	 		}
	 		if(!endtime){
	 			layer.msg("出票日止不能为空。",2,0);
	 			return false;
	 		}
	 		var flag = checkRqkd(starttime,endtime,1);
			if(!flag){
				layer.msg("日期跨度不能大于1个月。",2,0);
				return false;
			}
	 		var ii = layer.load('系统正在处理您的操作,请稍候!');
	 		document.searchForm.action = "${ctx}/vedsb/bbzx/cptjbb/query?time=1";  
		   	document.searchForm.submit();
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
	//导出
    function toExport(){
       var url="${ctx}/vedsb/bbzx/cptjbb/query";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }
    //订单数详  bs标识  0表示总订单数  1表示自动出票/退票订单数
    function ddsx(WDID,CGLY,HKGS,CPYH,DDSJ,BS){
    	var cplx = $("#cplx").val();
    	//DDSJ不为空则说明是按天统计
    	var ksrq = DDSJ != '' ? DDSJ : $("#cpdates").val();
	 	var jsrq = DDSJ != '' ? DDSJ : $("#cpdatez").val();
	 	//WDID不为空 则说明按网店统计
    	var wdid = $("#wdid").val() == '' ? WDID : $("#wdid").val();
    	//HKGS不为空则说明按照航空公司统计
    	var hkgs = $("#gn_hkgs").val() == '' ? HKGS : $("#gn_hkgs").val();
    	//CGLY不为空则说明按照采购来源统计
    	var cgly = $("#cgly").val() == '' ? CGLY : $("#cgly").val();
    	
    	var data = "&ksrq="+ksrq+"&jsrq="+jsrq+"&wdid="+wdid+"&hkgs="+hkgs+"&cgly="+cgly+"&cptjbbbs="+BS;
    	var url = "${ctx}/vedsb/cpkzt/cpkzt/viewzdcp_jk_list?lx=5";//自动出票
    	
    	if(cplx == '2'){//退票
    		data = "&ksrq="+ksrq+"&jsrq="+jsrq+"&wdid="+wdid+"&hkgs="+hkgs+"&cgly="+cgly;
    		url = "${ctx}/vedsb/cpsz/jpzdtfpgzsz/viewzdtp_jk_list?lx=6&cptjbbbs="+BS;
    	}
    	
    	window.open(url+data);
    }
</script>
</head>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/bbzx/cptjbb/query" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="827931810cad8945ef6eb71a850bb128" />
				<input type="hidden"  name="orderBy" value="cp_datetime desc" />
				<input type="hidden" name="pageNum" value="1" >
				<input type="hidden" name="pageSize" value="10" >
				<!-- 默认查询国内 -->
				<input type="hidden" name="gngj" value="1" >
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
                	<tr>
					    <td class="td_right" id="rq1">预订日始</td>
					    <td class="tab_in_td_f" >
					    	<input type="text" name="ksrq" id="cpdates" class="input-text Wdate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
										 style="width:98px;" onClick="WdatePicker()" >
					    </td>
					    <td class="td_right" id="rq2">预订日止</td>
					    <td class="tab_in_td_f" >
					    	<input type="text" name="jsrq" id="cpdatez" class="input-text Wdate" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}"
										 style="width:98px;" onClick="WdatePicker()" >
					    </td>
					    <td class="td_right">网&nbsp;&nbsp;店</td>
					    <td class="ddgl">
			                 <select name="wdid" id="wdid" style="width: 100px;height: 24px;" class="select">
		                  	 	<option value="" wdpt="">==全部==</option>
		                  	 	<c:forEach items="${jpfn:wdList(BUSER.shbh,param.gngj)}" var="wdBean">
									<option value="${wdBean.id}" ${param.wdid eq wdBean.id ? 'selected' : ''}>${wdBean.wdmc}</option>
								</c:forEach>
	                  		</select>
				        </td>
					    <td class="td_right">航空公司</td>
					    <td class="tab_in_td_f">
					      <input type="text" id="gn_hkgs_m" name="gn_hkgs_m" value="${param.gn_hkgs_m }" class="input-text lh25" style="width: 100px;" size="10" onchange="clearValue(this,'gn_hkgs');"/>
   					 	  <input type="hidden" id="gn_hkgs" name='hkgs' value="${param.hkgs }">
					    </td>
					</tr>
					<tr>
				         <td class="td_right">采购来源</td>
					    <td class="ddgl">
			                  <select name="cgly" id="cgly" class="select" style="width:100px;">
				                  	 	<option value="">==全部==</option>
				                  	 	<c:forEach items="${vfc:getVeclassLb('10014')}" var="onewdpt" varStatus="wdptStatus">
				                  	 		 <c:if test = "${onewdpt.id ne '10014'}">
				                  	 			<option value="${onewdpt.ywmc }" ${param.cgly eq onewdpt.ywmc ? 'selected' : '' }>${onewdpt.ywmc }</option> 
				                  	 		 </c:if>
				                  	 	</c:forEach>
						      </select>
				        </td>
					   	<td class="td_right">出票类型</td>
					    <td class="tab_in_td_f">
					      <select name="cplx" id="cplx" class="select" style="width:100px;">
		                  	 	<option value="1" ${param.cplx eq '1' ? 'selected' : '' }>正常票</option>
		                  	 	<option value="2" ${param.cplx eq '2' ? 'selected' : '' }>退票</option>
				      	  </select>    
					    </td>
					     <td class="td_right">统计方式</td>
					    <td class="ddgl">
			                  <select name="tjfs" id="tjfs" class="select" style="width:100px;">
				                  	 	<option value="1" ${param.tjfs eq "1" ? "selected":""}>网店统计</option>
										<option value="2" ${param.tjfs eq "2" ? "selected":"" }>采购来源</option>
										<option value="3" ${param.tjfs eq "3" ? "selected":"" }>航空公司统计</option>
										<option value="4" ${param.tjfs eq "4" ? "selected":"" }>出票员统计</option>
										<option value="5" ${param.tjfs eq "5" ? "selected":"" }>按天统计</option>
						      </select>
				        </td>
				        <td colspan="2" align="right">
					  		<input type="button" id="tosearch" class="ext_btn ext_btn_submit" value="查询" onclick="queryPage()"> 
					  		<input type="button" class="ext_btn ext_btn_success" value="导出" onclick="toExport()">
					  	</td>
				  	</tr>
	              </table>
	             </form>
            </div>
          </div>
        </div>
   </div>
  </div>
  <!-- 列表 -->
  <div  class="mt10">
        <c:if test="${not empty page && fn:length(page.list) != 0}">
        	<multipage:pone page="${ctx}/vedsb/bbzx/cptjbb/query" actionFormName="page" var="surl"></multipage:pone>
			${surl}
				<display:table id="vc" name="page.list" class="list_table" requestURI="${ctx}/vedsb/bbzx/gqbb/query?dtableid=vc" 
				sort="external" excludedParams="dtableid"   reportid="${reportid}">
				  <display:column title="序号" style="text-align:center;"  expfield="RO">${vc_rowNum }</display:column>
				  <c:choose>
				  	<c:when test="${param.tjfs eq '1'}">
				  		<display:column title="网店" expfield="WDID" style="text-align:center;" group="1">
				  			${vc.EX.WDID.wdmc }
				  		</display:column>
				  	</c:when>
				  	<c:when test="${param.tjfs eq '2'}">
				  		<display:column title="采购来源" style="text-align:center;" property="CGLY"/>
				  	</c:when>
				  	<c:when test="${param.tjfs eq '3'}">
				  		<display:column title="航空<br/>公司" property="HKGS" style="text-align:center;"/>
				  	</c:when>
				  	<c:when test="${param.tjfs eq '4'}">
				  		<display:column title="出票员"  style="text-align:center;"  expfield="CPYH">
				  			${vc.CPYH } ${vc.EX.CPYH.xm }
				  		</display:column>
				  	</c:when>
					<c:otherwise>
						<display:column title="按天数" style="text-align:center;" property="DDSJ"/>
					</c:otherwise>
				  </c:choose>
				  <display:column title="订单总数" style="text-align:right;" expfield="ZCPS">
				  	<a href="###" onclick="ddsx('${vc.WDID}','${vc.CGLY }','${vc.HKGS }','${vc.CPYH }','${vc.DDSJ }','0')">${vc.ZCPS }</a>
				  </display:column>
				  <display:column title="全自动订单数" expfield="ZDCPS" style="text-align:right;">
				  	<a href="###" onclick="ddsx('${vc.WDID}','${vc.CGLY }','${vc.HKGS }','${CPYH }','${vc.DDSJ }','1')">${vc.ZDCPS }</a>
				  </display:column>
				  <display:merge title="全自动${param.cplx eq '1' ? '出票' : '退票'}成功">
				  	<display:column title="订单数" property="CPCGS" style="text-align:right;"/>
				  	<display:column title="占比" style="text-align:right;" expfield="CPCGBL">
				  		${vfn:format(vc.CPCGBL,'#.##%')}
				  	</display:column>
				  </display:merge>
				   <display:merge title="0-30秒">
				  	<display:column title="订单数" property="CP30SL" style="text-align:right;"/>
				  	<display:column title="占比" style="text-align:right;" expfield="CP30BL">
				  		${vfn:format(vc.CP30BL,'#.##%')}
				  	</display:column>
				  </display:merge>
				   <display:merge title="30-60秒">
				  	<display:column title="订单数" property="CP30_60SL" style="text-align:right;"/>
				  	<display:column title="占比" style="text-align:right;" expfield="CP30_60BL">
				  		${vfn:format(vc.CP30_60BL,'#.##%')}
				  	</display:column>
				  </display:merge>
				   <display:merge title="60秒以上">
				  	<display:column title="订单数" property="CP60SL" style="text-align:right;"/>
				  	<display:column title="占比" style="text-align:right;" expfield="CP60BL">
				  		${vfn:format(vc.CP60BL,'#.##%')}
				  	</display:column>
				  </display:merge>
				  	<display:column title="全自动${param.cplx eq '1' ? '出票' : '退票'}成功<br>平均耗时" property="AVGES" style="text-align:right;"/>
			  		<display:merge title="全自动${param.cplx eq '1' ? '出票' : '退票'}失败">
			  			<display:column title="订单数" style="text-align:right;" expfield="SBDDS">
			  				${vc.ZDCPS-vc.CPCGS }
			  			</display:column>
					  	<display:column title="占比" style="text-align:right;" expfield="SBDDZB">
					  		${vc.CPCGBL eq 0 ? '0%' : vfn:format(1-vc.CPCGBL,'#.##%')}
					  	</display:column>
			  		</display:merge>
				  <display:footer>
					<tr>
						<fmt:formatNumber var="hj_zdcps" value="${list_sum.HJ_ZDCPS}" pattern="0.##" />
						<fmt:formatNumber var="hj_cpcgs" value="${list_sum.HJ_CPCGS}" pattern="0.##" />
						<fmt:formatNumber var="hj_cp30sl" value="${list_sum.HJ_CP30SL}" pattern="0.##" />
						<fmt:formatNumber var="hj_cp30_60sl" value="${list_sum.HJ_CP30_60SL}" pattern="0.##" />
						<fmt:formatNumber var="hj_cp60sl" value="${list_sum.HJ_CP60SL}" pattern="0.##" />
						<fmt:formatNumber var="hj_cpsbsl" value="${list_sum.HJ_ZDCPS-list_sum.HJ_CPCGS}" pattern="0.##" />
						
						
						<td colspan="2" style="text-align: right;">合    计：</td>
						<td style="text-align: right;">${list_sum.HJ_ZCPS }</td>
						<td style="text-align: right;">${list_sum.HJ_ZDCPS }</td>
						<td style="text-align: right;">${list_sum.HJ_CPCGS }</td>
						<td style="text-align: right;">${vfn:format(hj_cpcgs/hj_zdcps,'#.##%') }</td>
						<td style="text-align: right;">${list_sum.HJ_CP30SL }</td>
						<td style="text-align: right;">${vfn:format(hj_cp30sl/hj_zdcps,'#.##%') }</td>
						<td style="text-align: right;">${list_sum.HJ_CP30_60SL }</td>
						<td style="text-align: right;">${vfn:format(hj_cp30_60sl/hj_zdcps,'#.##%') }</td>
						<td style="text-align: right;">${list_sum.HJ_CP60SL }</td>
						<td style="text-align: right;">${vfn:format(hj_cp60sl/hj_zdcps,'#.##%') }</td>
						<td style="text-align: right;">${list_sum.HJ_AVGES }</td>
						<td style="text-align: right;">${list_sum.HJ_ZDCPS-list_sum.HJ_CPCGS}</td>
						<td style="text-align: right;">${vfn:format(hj_cpsbsl/hj_zdcps,'#.##%') }</td>
					</tr>
				</display:footer>
			</display:table>
			${surl}
		</c:if>
			<c:if test="${empty page || fn:length(page.list) == 0}">
				没有找到相关数据！
			</c:if>
	   </div>
	   <input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
</body>
</html>