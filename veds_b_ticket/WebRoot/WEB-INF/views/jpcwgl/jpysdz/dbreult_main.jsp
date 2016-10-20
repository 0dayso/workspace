<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<style>
			.srk{
				width:120px;
			}
	   </style>
		<script type="text/javascript">
			$(function(){
				if($('#vc')){
					highlightTableRows('vc');
				}
			})
			/**标签切换*/
			function loadContent(lx){
				$('#yq_jglx').val(lx);
				layer.load('系统正在处理您的操作,请稍候!');
				$('#asmsForm').submit();
			}
			/**查询*/
			function search(){
				layer.load('系统正在处理您的操作,请稍候!');
				$('#listForm').submit();
			}
			/**票价调整*/
			function tzpj(ddlx,pnrno){
				var pnr=pnrno.split(",");
				if(ddlx=='1'){
					window.open('${ctx}/vedsb/jpcwgl/jphistory/jpHistoryList?pnrNo='+pnr[0]);
				}else if(ddlx=='2'){
					window.open('${ctx}/vedsb/jpcwgl/jptpdhistory/jpHistoryTpList?pnrNo='+pnr[0]);
				}else if(ddlx=='3'){
					window.open('${ctx}/vedsb/jpcwgl/jpgqdhistory/jpHistoryGqList?pnrNo='+pnr[0]);
				}else if(ddlx=='4'){
					window.open('${ctx}/vedsb/jpcwgl/jpcydhistory/jpHistoryCyList?pnrNo='+pnr[0]);
				}
			}
			function delDbresult(){
				$.layer({
			    	 area: ['300px', '150px'],
			         dialog: {
			             msg: '重新对账会删除${wddzzb.dzrq}的对账结果，确定删除结果重新对账吗？',
			             btns: 2,                    
			             type: 4,
			             btn: ['确定','取消'],
			             yes: function(){
		             		var ii = layer.load('系统正在处理您的操作,请稍候!');
		            	 	$.getJSON('${ctx}/vedsb/jpcwgl/jpysdz/delDbresult?zbid=${wddzzb.id}&dzrq=${wddzzb.dzrq}&wdpt=${param.wdpt}&wdid=${wddzzb.wdid}',
		           	 			function(json){
			             			layer.close(ii);
		           	 				if(json.status=="0"){
			           	 				var url='';
										var param='dzrq=${wddzzb.dzrq}&wdpt=${wddzzb.wdpt}&wdid=${wddzzb.wdid}&dzzbid=${wddzzb.id}&wdmc='+encodeURI('${wddzzb.ex.WDID.wdmc}');
										param=encodeURI(param);
										if('${wddzzb.wdpt}'=='10100011'){
											url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbtb'
										}else if('${wddzzb.wdpt}'=='10100050'){
											url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbxc';
										}else if('${wddzzb.wdpt}'=='10100010'){
											url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbqn';
										}else if('${wddzzb.wdpt}'=='10100012'){
											url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbkx'
										}else if('${wddzzb.wdpt}'=='10100024'){
											url='${ctx}/vedsb/jpcwgl/jpysdz/viewbankdbtc';
										}
										url+='?'+param;
										window.open(url);
										window.close();
		           	 				}else{
			           	 				layer.msg("删除失败："+json.error,3,3);
		           	 				}
		           	 			}
		           	 		);
			             }, no: function(){
			                 layer.msg('放弃重新对账', 2, 3);
			             }
			         }
			     });
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
		
			function toExport2(){
			  var url="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult";
      	      $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#listForm").serialize(),"post");
			}
		</script>
	</head>
	<body>
		<table  style="width:100%;">
			<tr>
				<td rowspan="2" style="width:90px;">
					<c:choose>
						<c:when test="${param.wdpt eq '10100050'}">
							<div width="100%">
								<img src="${ctx}/static/images/wdimages/xiechengLogo.png" height="75" width="78"/>
							</div>
						</c:when>
						<c:when test="${param.wdpt eq '10100010'}">
							<img src="${ctx}/static/images/wdimages/qunaerLogo.png" height="75" width="78"/>
						</c:when>
						<c:when test="${param.wdpt eq '10100011'}">
							<img src="${ctx}/static/images/wdimages/taobaoLogo.png" height="75" width="78"/>
						</c:when>
						<c:when test="${param.wdpt eq '10100024'}">
							<img src="${ctx}/static/images/wdimages/tongchengLogo.png" height="75" width="78"/>
						</c:when>
					</c:choose>
				</td>
				<td>
					<span style="font-size:14px;color:blue;">${wddzzb.ex.WDID.wdmc}</span>
					<span style="font-size:14px;margin-left:20px;">您的账期:${wddzzb.dzrq}</span>
				</td>
			</tr>
			<tr>
				<td>
					<span >对账人:${wddzzb.ex.DZUSERID.xm}</span>
					<span style="margin-left:20px;">对账时间:${vfn:format(wddzzb.dzDatetime,'yyyy-MM-dd HH:mm:ss')}</span>
					<span style="margin-left:20px;">
						对账状态:
							<c:if test="${wddzzb.dzZt eq '1'}">
								<img src="${ctx}/static/images/wdgzt/dzwwn.png" style="vertical-align:middle;"/>对账无误
							</c:if>
							<c:if test="${wddzzb.dzZt eq '2'}">
								<img src="${ctx}/static/images/wdgzt/dzcyn.png" style="vertical-align:middle;"/>对账金额有异或漏单
							</c:if>
							<c:if test="${wddzzb.dzZt eq '3'}">
								<img src="${ctx}/static/images/wdgzt/wdzld.png" style="vertical-align:middle;"/>对账无误有未到账
							</c:if>
					</span>
					<span style="margin-left:50px;">
						<input type="button" value="重新对账" class="ext_btn ext_btn_submit" onClick="delDbresult()"/>
					</span>
				</td>
			</tr>
		</table>
		<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_head.jsp"%>
	<div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
		<form id="listForm" name="listForm" action="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" method="post">
			<input type="hidden" name="wdpt" value="${param.wdpt}">
			<input type="hidden" name="wdid" value="${param.wdid}">
			<input type="hidden" name="zbid" value="${param.zbid}">
			<input type="hidden" name="jglx" value="${param.jglx}"/>
		<c:if test="${param.jglx eq '1' or param.jglx eq '2' or param.jglx eq '3' or param.jglx eq '4' or param.jglx eq '5' or param.jglx eq '7'}">
			<table class="table01" border="0" cellpadding="0" cellspacing="0"/>
				<tr>
					<td class="td_right">订单类型</td>
					<td >
						<select name="ddlx" id="tj_ddlx" class="select srk">
							<option value="">全部</option>
							<option value="1" ${param.ddlx eq '1' ? 'selected' : ''}>正常单</option>
							<option value="2" ${param.ddlx eq '2' ? 'selected' : ''}>退票单</option>
							<option value="3" ${param.ddlx eq '3' ? 'selected' : ''}>改签单</option>
							<option value="4" ${param.ddlx eq '4' ? 'selected' : ''}>补差单</option>
						</select>
					</td>
					<td class="td_right">外部单号</td>
					<td >
						<input type="text" id="tj_gyddh" name="gyddh" value="${param.gyddh}" class="input-text lh25 srk">
					</td>
					<td class="td_right">订单编号</td>
					<td >
						<input type="text" id="tj_ddbh" name="ddbh" value="${param.ddbh}" class="input-text lh25 srk">
					</td>
					<td class="td_right">P&nbsp;&nbsp;N&nbsp;&nbsp;R</td>
					<td >
						<input type="text" id="tj_pnrno" name="pnrno" value="${param.pnrno}" class="input-text lh25 srk"
							onblur="this.value=this.value.replace('%','').toUpperCase()" />
					</td>
					<td rowspan="2" class="td_right">
						<input type="button" name="serach" value=" 查 询 " class="ext_btn ext_btn_submit" onclick="search()">
						<c:if test="${not empty page and fn:length(page.list) gt 0}">
							<br><br>
						 <input type="button"  value=" 导 出 " name="export" class="ext_btn ext_btn_submit" onClick="toExport2();"/>
						</c:if>
					</td>
				</tr>
					<tr>
						<td class="td_right">票&nbsp;&nbsp;号</td>
			            <td >
			              	<input type="text" id="tj_tkno" name="tkno" value="${param.tkno}" class="input-text lh25 srk"/>
			            </td>
			          <c:if test="${param.jglx eq '3'}">
							<td class="td_right">到账状态</td>
							<td >
								<select name="dz_sfdz" id="tj_dzzt"  class="select srk">
									<option value="" >全部</option>
									<option value="0" ${param.dz_sfdz eq '0' ? 'selected' : ''}>未到账</option>
									<option value="1" ${param.dz_sfdz eq '1' ? 'selected' : ''}>已到账</option>
								</select>
							</td>
							<td class="td_right">到账日期</td>
							<td >
								<input type="text" id="tj_dzrq" name="dzrq" value="${param.dzrq}" class="input-text lh25 srk Wdate" 
									onClick="WdatePicker()"/>
							</td>
							<td colspan="2">&nbsp;</td>
						</c:if>
						<c:if test="${param.jglx eq '7'}">
							<td class="td_right">金额正确否</td>
							<td >
								<select name="jesfzq" id="tj_jezq"  class="select srk">
									<option value="">全部</option>
									<option value="1" ${param.jesfzq eq '1' ? 'selected' : ''}>正确</option>
									<option value="2" ${param.jesfzq eq '2' ? 'selected' : ''}>不正确</option>
								</select>
							</td>
							<td colspan="4">&nbsp;</td>
						</c:if>
						<c:if test="${param.jglx ne '7' and param.jglx ne '3'}">
							<td colspan="6">&nbsp;</td>
						</c:if>
					</tr>
			</table>
		</c:if>
		<c:if test="${param.jglx eq '6'}">
			<table class="table01" border="0" cellpadding="0" cellspacing="0"/>
				<tr>
					<td class="td_right">订单类型</td>
					<td >
						<select name="ddlx" id="tj_ddlx"  class="select srk">
							<option value="">全部</option>
							<option value="1" ${param.ddlx eq '1' ? 'selected' : ''}>正常单</option>
							<option value="2" ${param.ddlx eq '2' ? 'selected' : ''}>退票单</option>
							<option value="3" ${param.ddlx eq '3' ? 'selected' : ''}>改签单</option>
							<option value="4" ${param.ddlx eq '4' ? 'selected' : ''}>补差单</option>
						</select>
					</td>
					<td class="td_right">外部单号</td>
					<td >
						<input type="text" id="tj_gyddh" name="gyddh" value="${param.gyddh}" class="input-text lh25 srk">
					</td>
					<td class="td_right">补单状态</td>
					<td >
						<select name="bd_sfybd" id="tj_bdzt" class="select srk">
							<option value="">全部</option>
							<option value="0" ${param.bd_sfybd eq '0' ? 'selected' : ''}>未补单</option>
							<option value="2" ${param.bd_sfybd eq '2' ? 'selected' : ''}>未收银</option>
							<option value="3" ${param.bd_sfybd eq '3' ? 'selected' : ''}>未退款</option>
							<option value="1" ${param.bd_sfybd eq '1' ? 'selected' : ''}>已补单</option>
						</select>
					</td>
					<td class="td_right">补单日期</td>
					<td >
						<input type="text" id="tj_bdrq" name="bdrq" value="${param.bdrq}" class="input-text lh25 srk Wdate" 
							onClick="WdatePicker()"/>
					</td>
					<td  style="text-align:center;">
						<input type="button" name="serach" value=" 查 询 " class="ext_btn ext_btn_submit" onclick="search()">
						<c:if test="${not empty page and fn:length(page.list) gt 0}">
							<input type="button"  value=" 导 出 " name="export" class="ext_btn ext_btn_submit" onClick="toExport2();"/>
						</c:if>
					</td>
			</table>
		</c:if>
		</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div>
		<c:if test="${not empty page and not empty page.list and fn:length(page.list) gt 0}">
		<multipage:pone page="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" actionFormName="page" var="fyurl"></multipage:pone>
		${fyurl}
		<div style="overflow-x:scroll;">
		<display:table name="page.list" id="vc" class="list_table"  decorator="org.displaytag.decorator.TotalTableDecorator"
 				requestURI="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" excludedParams="dtableid" style="width:100%;white-space: nowrap;">
			<c:choose>
				<c:when test="${empty param.jglx or param.jglx eq '1'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_1.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '2'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_2.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '3'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_3.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '4'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_4.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '5'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_5.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '6'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_6.jsp"%>
				</c:when>
				<c:when test="${param.jglx eq '7'}">
					<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/dbresult_7.jsp"%>
				</c:when>
			</c:choose>
		</display:table>
		<input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}"/>
		</div>
		${fyurl}
		</c:if>
		<c:if test="${not empty page and (empty page.list or fn:length(page.list) eq 0)}">
			没找到相关数据
		</c:if>
	</div>
		<form id="asmsForm" name="asmsForm" action="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" method="post">
			<input type="hidden" name="wdpt" value="${param.wdpt}">
			<input type="hidden" name="wdid" value="${param.wdid}">
			<input type="hidden" name="zbid" value="${param.zbid}">
			<input type="hidden" name="jglx" id="yq_jglx" value="${param.jglx}"/>
		</form>
	</body>
</html>