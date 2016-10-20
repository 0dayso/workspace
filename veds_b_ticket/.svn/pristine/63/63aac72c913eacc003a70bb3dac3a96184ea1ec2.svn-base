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
		<display:column title="到账状态" style="text-align:center" expfield="DZ_SFDZ">
			<c:if test="${vc.DZ_SFDZ eq '1'}">已到账</c:if>
			<c:if test="${vc.DZ_SFDZ eq '0'}">未到账</c:if>
		</display:column>
		<display:column title="到账金额" property="BY6" total="true" style="text-align:right;background-color:#00E000;color:#000000" format="{0,number,#0.00}"/>
		<display:column title="到账日期" property="DZ_DZRQ"  style="text-align:center" />
		<display:column title="到账人" property="DZR"  style="text-align:left" />
		<display:column title="外部单号" property="GYDDH" style="text-align:center"/>
		<display:column title="订单编号" property="DDBH" style="text-align:center" maxLength="20"/>
		<display:column title="PNR" property="PNRNO" style="text-align:center"/>
		<display:column title="票号" property="TKNO"  style="text-align:center" maxLength="12"/>
		<display:column title="金额" property="ZFJE" total="true" style="text-align:right;background-color:#00E000;color:#000000" format="{0,number,#0.00}"/>
		<display:footer>
		<tr>
			<td colspan="3" align="right">合计：</td>
			<td align="right">${empty sumMap.ZBY6 ? '0.00' : vfn:format(sumMap.ZBY6,'#0.00')}</td>
			<td colspan="6"></td>
			<td align="right">${empty sumMap.ZZFJE ? '0.00' : vfn:format(sumMap.ZZFJE,'#0.00')}</td>
			<td></td>
		</tr>
	</display:footer>