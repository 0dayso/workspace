<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<style type="text/css">
			.leftdate{
				background:url(${ctx}/static/images/wdgzt/btnleft.png) no-repeat center center;
				width:25px;
				height:26px;
				vertical-align:middle;
				cursor:pointer;
			}
			.rightdate{
				background:url(${ctx}/static/images/wdgzt/btnright.png) no-repeat center center;
				width:25px;
				height:26px;
				cursor:pointer;
				vertical-align:middle;
			}
			.inter_con{width: 800px;background: #fff;padding: 10px;border: 1px solid #d3dbde;}
			.int_title{font-size:22px;font-weight:bold;color:#0A0A0A;text-align:center;}
		</style>
		<script type="text/javascript">
			function search(){
				layer.load('系统正在处理您的操作,请稍候!');
				$('#dzform').submit();
			}
			<c:if test="${empty param.dzrq}">
				$(function(){
					search();
				})
			</c:if>
			function towddz(dzrq,wdpt,wdid,wdgztid,wdmc){
					var url;
					var param='dzrq='+dzrq+'&wdpt='+wdpt+'&wdid='+wdid+"&dzzbid="+wdgztid+'&wdmc='+encodeURI(encodeURI(wdmc));
					//param=encodeURI(param);
					if(wdpt=='10100011'){
						url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbtb'
					}else if(wdpt=='10100050'){
						url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbxc';
					}else if(wdpt=='10100010'){
						url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbqn';
					}else if(wdpt=='10100012'){
						url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbkx'
					}else if(wdpt=='10100024'){
						url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbtc';
					}
					
					url+='?'+param;
					
					var checkparam='dzrq='+dzrq+'&wdpt='+wdpt+'&zbid='+wdgztid+'&wdid='+wdid;
					$.ajax({url:'${ctx}/vedsb/jpcwgl/jpysdz/checkDzFlag',
							type:'POST',
							async: false,
							data:checkparam,
	           	 			success:function(data){
	           	 				if(data=="0"){
		           	 				window.open(url);
		           	 				/*
		           	 				 * 
		           	 				 $.layer({
										type: 2,
										title: ['营收对账'],
										area: ['1000px', '700px'],
										iframe: {src:url}
	     							});*/
	           	 				}else if(data=="1"){
		           	 				url='${ctx}/vedsb/jpcwgl/jpysdz/genDbResult?jglx=1&zbid='+wdgztid+'&wdpt='+wdpt;
								/*	$.layer({
										type: 2,
										title: [''],
										area: ['1000px', '700px'],
										iframe: {src:url}
	     							});*/
	     							window.open(url);
	           	 				}else if(data=="-1"){
	           	 					var error=dzrq+"日之后已存在对账，不可对账，对账必须按日期前后顺序对账!"
	           	 					layer.msg(error,3,3);
	           	 				}else{
	           	 					var error='系统异常，请联系管理员';
	           	 					layer.msg(error,3,3);
	           	 				}
	           	 			}
	           	 	});
			}
			//获取当前日期前后天数后的日期 兼容FF 谷歌 by shenjianxin
			function getDateByInterval(dval,AddDayCount){
				var strArr = dval.split("-");
				var strYear = strArr[0];//年	
				var strMonth= strArr[1];//月
				var strDay  = strArr[2];//日
			    var dd = new Date(strYear,strMonth-1,strDay);
			    var increadDay=parseInt(AddDayCount);
				dd.setDate(dd.getDate()+increadDay);//获取AddDayCount天后的日期 
				var y = dd.getYear(); 
				if(y<1900){
					y=y+1900;
				}
				var m = dd.getMonth()+1;//获取当前月份的日期 
				var d = dd.getDate(); 
				if(m<10){
					m="0"+m;
				}
				if(d<10){
					d="0"+d;
				}
				return y+"-"+m+"-"+d; 
			}
			//对账日期向前或向后推移几天
			function nextDay(pre){
				var day=getDateByInterval($('#dzrq').val(),pre);
				if(day=='${vfn:dateShort()}'){
					$('#rightdate').attr('disabled','disabled')
				}else{
					$('#rightdate').removeAttr('disabled','disabled');
				}
				$('#dzrq').val(day);
			}
			function getTodayDz(){
				$.getJSON('${ctx}/vedsb/jpcwgl/jpysdz/creatTodayDz',
		           	 function(json){
		           	 	if(json.stauts=='-1'){
		           	 		layer.msg("生成失败："+json.error,3,3);
						}else{
							search();
						}
		           	 }
		        );
			}
		</script>
 	</head>
	<body>
		<div class="inter_con">
			<div>
				<div class="int_title">
					网店财务工作台
				</div>
				<div style="border-bottom: 1 solid #0A0A0A;width:100%;font-size:14px;text-align:right;">
					<form action="${ctx}/vedsb/jpcwgl/jpysdz/wdgzt" method="post" id="dzform" name="dzform">
						您的账期为：
						<input type="button"  onclick="nextDay('-1')" class="leftdate">
						<input type="text"  name="dzrq" id="dzrq"  onClick="WdatePicker({maxDate:'${vfn:dateShort()}'})" 
							value="${empty param.dzrq ? vfn:getPreDay(vfn:dateShort(),-1) : param.dzrq}" size="10" class="Wdate"
							style="height:22px;line-height:22px;vertical-align:middle;"/>
						<input type="button" onclick="nextDay('1')" class="rightdate" id="rightdate">
						<input type="button" value=" 查询 " class="ext_btn ext_btn_submit" onclick="search();">
					</form>
				</div>
				<div style="margin-top:20px;margin-left:5px;width:100%;">
					<span>营收对账：确保无漏单、账实相符<img src="${ctx}/static/images/wdgzt/hjt.png" style="margin-left:20px;margin-right:15px;vertical-align:middle;" /> 业务审核：确保业务处理合理</span>
					<c:if test="${vfn:dateShort() eq param.dzrq or (vfn:dateShort() lt param.dzrq and count eq 0)}">
						<span style="margin-left:150px;font-size: 15px;"><a href="###" onclick="getTodayDz();">生成今日对账数据对账</a></span>
					</c:if>
					<br>
					<span>
						<img src="${ctx}/static/images/wdgzt/wdzn.png" style="vertical-align:middle;"/>：未对账或未审核&nbsp;&nbsp;&nbsp;
						<img src="${ctx}/static/images/wdgzt/dzwwn.png" style="vertical-align:middle;"/>：对账无误或审核通过&nbsp;&nbsp;&nbsp;
						<img src="${ctx}/static/images/wdgzt/dzcyn.png" style="vertical-align:middle;"/>：对账金额有异、漏单或审核不通过&nbsp;&nbsp;&nbsp;
						<img src="${ctx}/static/images/wdgzt/wdzld.png" style="vertical-align:middle;"/>：对账无误有未到账&nbsp;&nbsp;&nbsp;
					</span>
				</div>
				<div style="margin-top:15px;">
					
				</div>
				<div style="margin-top:15px;">
				<c:if test="${count gt 0}">
					<table class="list_table"  width="100%" style="text-align:center;">
						<tr>
							<th width="8%">网店平台</th>
							<th width="12%">网店</th>
							<th width="5%">营收<br>对账</th>
							<th width="12%">对账员</th>
							<th width="5%">业务<br>审核</th>
							<th width="12%">审核员</th>
							<%--<th width="5%">利润分析</th> --%>
						</tr>
						<c:forEach items="${tb10100011}" var="tb" varStatus="index">
							<c:set var="logo" value="${ctx}/static/images/wdimages/taobaoLogo.png"/>
							<c:set var="len" value="${fn:length(tb10100011)}"/>
							<%@include file="/WEB-INF/views/jpcwgl/jpysdz/wdgztjl.jsp"%>
						</c:forEach>
						<c:forEach items="${xc10100050}" var="tb" varStatus="index">
							<c:set var="logo" value="${ctx}/static/images/wdimages/xiechengLogo.png"/>
							<c:set var="len" value="${fn:length(xc10100050)}"/>
							<%@include file="/WEB-INF/views/jpcwgl/jpysdz/wdgztjl.jsp"%>
						</c:forEach>
						<c:forEach items="${qnr10100010}" var="tb" varStatus="index">
							<c:set var="logo" value="${ctx}/static/images/wdimages/qunaerLogo.png"/>
							<c:set var="len" value="${fn:length(qnr10100010)}"/>
							<%@include file="/WEB-INF/views/jpcwgl/jpysdz/wdgztjl.jsp"%>
						</c:forEach>
						<c:forEach items="${tc10100024}" var="tb" varStatus="index">
							<c:set var="logo" value="${ctx}/static/images/wdimages/tongchengLogo.png"/>
							<c:set var="len" value="${fn:length(tc10100024)}"/>
							<%@include file="/WEB-INF/views/jpcwgl/jpysdz/wdgztjl.jsp"%>
						</c:forEach>
						<c:forEach items="${kx10100012}" var="tb" varStatus="index">
							<c:set var="logo" value="${ctx}/static/images/wdimages/kunxunLogo.png"/>
							<c:set var="len" value="${fn:length(kx10100012)}"/>
							<%@include file="/WEB-INF/views/jpcwgl/jpysdz/wdgztjl.jsp"%>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${count eq 0 and not empty count}">
					没有找到相关数据！
				</c:if>
			</div>
		</div>
	</body>
</html>