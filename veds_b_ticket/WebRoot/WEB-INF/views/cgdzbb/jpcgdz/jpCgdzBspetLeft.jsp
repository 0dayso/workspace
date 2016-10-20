<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div id="tbl-container" style="height:200px;width:100%">
	<c:if test="${not empty list}">
		<display:table id="ticketInfo" name="list" class="list_table" style="width:750px;" >
	       <display:column title="序号">${ticketInfo_rowNum }</display:column>
		   <display:column title=" 类型"  style="text-align:left;">
			        ${ticketInfo.TYPE eq '1' ? '正常票' : '' }
			        ${ticketInfo.TYPE eq '2' ? '退票' : '' }
			        ${ticketInfo.TYPE eq '3' ? '退票' : '' }
		    </display:column>
		    <display:column  title=" 票号"  style="text-align:left;" property="TKNO"></display:column>
		    <display:column property="HC" title=" 航程" style="text-align:left;"/>
		    <display:column property="ZDJ" format="{0,number,#0.00}" style="text-align:right;" title=" 账单价<br>实退金额" />
		    <display:column  style="text-align:right;" title="税费" property="TAX"></display:column>
		    <display:column  style="text-align:right;" title="代理费率" property="HXFL" ></display:column>
		    <display:column property="CG_PNR_NO" title=" PNR" style="text-align:left;"/>
		    <display:column title=" 工作号<br>" property="WORKNO" style="text-align:left;"/>
		    <display:column title=" OFFICE号" property="CP_OFFICEID" style="text-align:left;"/>
			<display:column property="CG_HBH" title=" 航班号" style="text-align:left;"/>
			<display:column property="CG_CW" title=" 舱位" style="text-align:left;"/>
	    </display:table>
	</c:if>
	<c:if test="${empty list}">
		<span>没有找到相关数据</span>
	</c:if>
</div>