<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
		<display:column title="序号" style="text-align:center">
			<span id="xh${vc.ID}">${vc_rowNum}</span>
		</display:column>
		<display:column title="订单类型" style="text-align:center" expfield="DDLX">
			<c:if test="${vc.DDLX eq '1'}">正常单</c:if>
			<c:if test="${vc.DDLX eq '2'}">退废单</c:if>
			<c:if test="${vc.DDLX eq '3'}">改签单</c:if>
			<c:if test="${vc.DDLX eq '4'}">补差单</c:if>
		</display:column>
		<display:column title="补单状态" style="text-align:center" expfield="BD_SFYBD">
			<c:if test="${vc.BD_SFYBD eq '1'}">已补单</c:if>
			<c:if test="${vc.BD_SFYBD eq '0'}">未补单</c:if>
			<c:if test="${vc.BD_SFYBD eq '2'}">未完成</c:if>
		</display:column>
		<display:column title="补单人" property="BDR" style="text-align:center"></display:column>
		<display:column title="补单时间" expfield="BD_BDRQ" style="text-align:center">
			${fn:substring(vc.BD_BDRQ,5,16)}
		</display:column>
		<c:choose>
		<c:when test="${param.wdpt eq '10100050'}">
			<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/bank_xc.jsp"%>
		</c:when>
		<c:when test="${param.wdpt eq '10100010'}">
			<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/bank_qnr.jsp"%>
		</c:when>
		<c:when test="${param.wdpt eq '10100011'}">
			<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/bank_tb.jsp"%>
		</c:when>
		<c:when test="${param.wdpt eq '10100024'}">
			<%@include file="/WEB-INF/views/jpcwgl/jpysdz/dbreult/bank_tc.jsp"%>
		</c:when>
	</c:choose>
	<display:footer>
		<tr>
			<td colspan="5"  align="right">合计：</td>
			<td align="right">${empty sumMap.ZWD_FSJE ? '0.00' : vfn:format(sumMap.ZWD_FSJE,'#0.00')}</td>
			<td colspan="14"></td>
		</tr>
	</display:footer>
