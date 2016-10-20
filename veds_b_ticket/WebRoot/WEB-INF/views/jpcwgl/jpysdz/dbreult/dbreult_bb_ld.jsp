<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/asms/common/taglibs.jsp"%>
<html>
	<head>
		<%@include file="/asms/common/meta.jsp"%>
		<script language=javascript src="/js/city/address.js"></script>
		<script type="text/javascript" src="/js/city3/city.js"></script>
	</head>
	<body onload="highlightTableRows('vc');">
		<%@include file="/asms/yhdz/wddz/dbreult/dbresult_bb_head.jsp"%>
		<form id="listForm" name="listForm" action="/asms/wdcw/yhdz.shtml" method="post" onsubmit="return search()">
			<input type="hidden" name="p" value="dzjgReprot">
			<input type="hidden" name="jglx" value="6">
			<table class="tab_jf_in" align="center" width="100%"/>
				<tr>
					<td class="tab_in_td_h">网店平台</td>
		            <td class="tab_in_td_f">
		              	<select name="wdpt" value="${param.wdpt}" style="width:100px;">
		              		<option  value="">全部</option>
		              		<option value="10100011" ${param.wdpt eq '10100011' ? 'selected' : ''}>淘宝</option>
		              		<option value="10100050" ${param.wdpt eq '10100050' ? 'selected' : ''}>携程</option>
		              		<option value="10100010" ${param.wdpt eq '10100010' ? 'selected' : ''}>去哪儿</option>
		              		<option value="10100024" ${param.wdpt eq '10100024' ? 'selected' : ''}>同程</option>
		              	</select>
		            </td>
		            <td class="tab_in_td_h">网&nbsp;&nbsp;&nbsp;&nbsp;店</td>
    				<td class="tab_in_td_f">
			    	    <input type="text" id="wdmc" name="wdmc" class="input1" style="width:100px" value="${param.wdmc}" 
									onclick="getDeptList(this,{qx:'1',isopenwd:'1'})" onkeyup="getDeptList(this,{qx:'1',isopenwd:'1'})" value_to_input="wdbh" />
						<input type="hidden" class="input1" id="wdbh" name="wdbh" value="${param.wdbh}" />
					</td>
					<td class="tab_in_td_h">账期日始</td>
					<td class="tab_in_td_f">
						<input type="text" id="syrqs" name="syrqs" value="${empty param.syrqs ? vfn:getNextDay(vfn:dateShort(),'-7') : param.syrqs}" style="width:100px" class="inputDate" 
							onClick="event.cancelBubble=true;showCalendar('syrqs',true,'syrqs')"/>
					</td>
					<td class="tab_in_td_h">账期日止</td>
					<td class="tab_in_td_f">
						<input type="text" id="syrqz" name="syrqz" value="${empty param.syrqz ? vfn:dateShort() : param.syrqz}" style="width:100px" class="inputDate" 
							onClick="event.cancelBubble=true;showCalendar('syrqz',true,'syrqz')"/>
					</td>
					<td rowspan="2" class="tab_in_td_f">
						<input type="submit" name="serach" value=" 查 询 " class="asms_button">
						<c:if test="${not empty listForm and fn:length(listForm.list)>0}">
							<br><br>
							<input type="button"  value=" 导 出 " name="export" class="asms_button" onClick="toExport2('508147','02');"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="tab_in_td_h">订单类型</td>
					<td class="tab_in_td_f">
						<select name="ddlx" id="tj_ddlx"  style="width:100px;">
							<option value="">全部</option>
							<option value="1" ${param.ddlx eq '1' ? 'selected' : ''}>正常单</option>
							<option value="2" ${param.ddlx eq '2' ? 'selected' : ''}>退票单</option>
							<option value="3" ${param.ddlx eq '3' ? 'selected' : ''}>改签单</option>
							<option value="4" ${param.ddlx eq '4' ? 'selected' : ''}>补差单</option>
						</select>
					</td>
					<td class="tab_in_td_h">外部单号</td>
					<td class="tab_in_td_f">
						<input type="text" id="tj_gyddh" name="gyddh" value="${param.gyddh}" class="input1" style="width:100px;">
					</td>
					<td class="tab_in_td_h">补单状态</td>
					<td class="tab_in_td_f">
						<select name="bdzt" id="tj_bdzt" style="width:100px;" onchange="showDzsj(this.value)">
							<option value="5">全部</option>
							<option value="0" ${empty param.bdzt or param.bdzt eq '0' ? 'selected' : ''}>未补单</option>
							<option value="2" ${param.bdzt eq '2' ? 'selected' : ''}>未收银</option>
							<option value="3" ${param.bdzt eq '3' ? 'selected' : ''}>未退款</option>
							<option value="1" ${param.bdzt eq '1' ? 'selected' : ''}>已补单</option>
						</select>
					</td>
					<td id="dzsjl1" colspan="2" class="tab_in_td_f" style="display:${param.bdzt eq '1' ? '' : 'none'}">
						补单日期
						<input type="text" id="bdrqs" name="bdrqs" value="${param.bdrqs}" class="inputDate" 
							onClick="event.cancelBubble=true;showCalendar('bdrqs',true,'bdrqs')"/>
						至
						<input type="text" id="bdrqz" name="bdrqz" value="${param.bdrqz}"  class="inputDate" 
							onClick="event.cancelBubble=true;showCalendar('bdrqz',true,'bdrqz')"/>
					</td>
				</tr>
			</table>
			<MultiPages:pone actionFormName="listForm" page="/asms/wdcw/yhdz.shtml" dtableid="vc" exportUrl="export" var="fyurl" curl="commonex"/>
			${fyurl}
		<c:if test="${not empty listForm}">
			<display:table name="listForm.list" id="vc" class="its"  decorator="org.displaytag.decorator.TotalTableDecorator"
 				requestURI="/asms/wdcw/yhdz.shtml?dtableid=vc" sort="external" excludedParams="dtableid" style="width:100%;">
 				<display:column title="序号" style="text-align:center">${vc_rowNum}</display:column>
 				<display:column title="网店平台" property="WDPTMC" style="text-align:center"/>
 				<display:column title="网店" property="WDMC" style="text-align:center"/>
				<display:column title="订单类型" style="text-align:center" expfield="{DDLX,1,正常单,2,退废单,3,改签单,补差单}">
					<c:if test="${vc.DDLX eq '1'}">正常单</c:if>
					<c:if test="${vc.DDLX eq '2'}">退废单</c:if>
					<c:if test="${vc.DDLX eq '3'}">改签单</c:if>
					<c:if test="${vc.DDLX eq '4'}">补差单</c:if>
				</display:column>
				<display:column title="补单状态" style="text-align:center" expfield="{CPZT,1,已补单,0,未补单,2,未收银,3,未退款}">
					<c:if test="${vc.BD_SFYBD eq '1'}">已补单</c:if>
					<c:if test="${vc.BD_SFYBD eq '0'}">未补单</c:if>
					<c:if test="${vc.BD_SFYBD eq '2'}">未收银</c:if>
					<c:if test="${vc.BD_SFYBD eq '3'}">未退款</c:if>
				</display:column>
				<display:column title="发生金额" property="WD_FSJE" total="true"  style="text-align:right;background-color:#00E000;color:#000000;" format="{0,number,#0.00}"/>
				<display:column title="外部单号" property="WD_DDH" style="text-align:center"/>
				<display:column title="账务时间" property="WD_ZWSJ"  style="text-align:left;"/>
				<display:column title="账务流水号" property="WD_ZWLSH"  style="text-align:left;"/>
				<display:column title="商品名称" property="WD_SPMC" style="text-align:left;"/>
				<display:column title="备注" property="WD_BZ" style="text-align:left;"/>
				<display:footer>
					<tr>
						<td colspan="5">合计：</td>
						<td align="right"><fmt:formatNumber pattern="##0.00" value="${sumMap.ZWD_FSJE}"></fmt:formatNumber></td>
						<td colspan="5"></td>
					</tr>
				</display:footer>
 			</display:table>
 		</c:if>
 		${fyurl}
	</body>
		<script type="text/javascript">
			//自定义列头导出
			function toExport2(mkbh,bbh){
				var curl = '${commonex}&ifexport=1';
				var alltitle = '${vfn:urid(vcalltitle)}';
				var allexpfield = '${vfn:urid(vcallexpfield)}';
				exportTo(mkbh,bbh,curl,alltitle,allexpfield);
			}
			function showDzsj(dzzt){
				if(dzzt=='1'){
					$('dzsjl1').show();
				}else{
					$('dzsjl1').hide();
					$('bdrqs').value="";
					$('bdrqz').value="";
				}
			}
			function search(){
				var syrqs=$('syrqs').value;
				var syrqz=$('syrqz').value;
				if(isBlank(syrqs)){
					alert("账期日始不能为空");
					return false;
				}
				if(isBlank(syrqz)){
					syrqz='${vfn:dateShort()}';
				}
				var cha= DateDiff(syrqz, syrqs);
				if(cha>90){
					alert('账期跨度不能超过90天!');
					return false;
				}else if(cha<0){
					alert('结束日期不能小于开始日期！');
					return false;
				}
				document.listForm.submit();
				lockScreen('系统正在处理中，请稍后。。。');
			}
		</script>
</html>