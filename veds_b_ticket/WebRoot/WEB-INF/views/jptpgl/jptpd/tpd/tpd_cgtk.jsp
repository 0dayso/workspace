<%@ page language="java" pageEncoding="UTF-8"%>
<style>

.wb_td_input{
   width:50px;
}
</style>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="ps" value="${fn:length(mxList)}"></c:set>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="cgInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/cg.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">采购退款</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="100%" style="height:90px;">
				<tr style="background:#f1f1f1;">
					<th>账单价</th>
					<th>采购价</th>
					<th>机建</th>
					<th>税费</th>
					<c:if test="${sfGq}">
						<th>升舱费</th>
					</c:if>
					<th>退票费率</th>
					<th>退票费</th>
					<th>应退金额</th>
					<th width="60">实退金额</th>
				</tr>
				<!-- 第一条明细 -->
				<tr>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="cgZdj" name="cgZdj" onblur="this.value=getFs(this.value);ytje('cg',${ps})" value="${tfje.CG_ZDJ}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="cgPj"  name="cgPj"  onblur="this.value=getFs(this.value);ytje('cg',${ps})" value="${tfje.CG_PJ}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="cgJsf" name="cgJsf" onblur="this.value=getFs(this.value);ytje('cg',${ps})" value="${tfje.CG_JSF}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="cgTax" name="cgTax" onblur="this.value=getFs(this.value);ytje('cg',${ps})" value="${tfje.CG_TAX}" >
					</td>
					<c:if test="${sfGq}">
						<td align="center" class="wb_td02">
							<a href="###" onclick="setGqfy(this,'${cg}','read')" >${tfje.CG_TGQ}</a>
						</td>
					</c:if>
					<td align="center" class="wb_td02">
						<fmt:formatNumber var="cgTpfl" value="${empty tfje.CG_TPFL ? 0 : tfje.CG_TPFL*100}" pattern="0.##" />
						<input class="wb_td_input" id="cgTpfl" name="cgTpfl"  onblur="toTpfl('cg',${ps})" value="${cgTpfl}">%
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="cgTpf"  name="cgTpf" onblur="this.value=getZs(this.value);ytje('cg',${ps})" value="${tfje.CG_TPF}" >
					</td>
					<td align="center" class="wb_td02" id="cgTkje">${tfje.CG_TKJE}</td>
				    <td align="center" class="wb_td02" width="60">
				    	<input class="wb_td_input" id="cgStje" name="cgStje" onblur="this.value=getFs(this.value);ytje('cg',${ps})" value="${empty tfje.CG_STJE  ? 0 : tfje.CG_STJE}" >
				    </td>
				</tr>
				
				<!-- 采购退款合计 -->
				<c:if test="${ps >1 }">
					<tr style="font-weight: bolder;background: #f6f6f6" class="wb_td02">
						<td align="center" id="hj_cgZdj"><fmt:formatNumber value="${empty jptpd.cgZdj ? 0 : jptpd.cgZdj}" pattern="0.##" /></td>
						<td align="center" id="hj_cgPj" ><fmt:formatNumber value="${empty jptpd.cgPj  ? 0 : jptpd.cgPj }" pattern="0.##" /></td>
						<td align="center" id="hj_cgJsf"><fmt:formatNumber value="${empty jptpd.cgJsf ? 0 : jptpd.cgJsf }" pattern="0.##" /></td>
						<td align="center" id="hj_cgTax"><fmt:formatNumber value="${empty jptpd.cgTax ? 0 : jptpd.cgTax}" pattern="0.##" /></td>
						<c:if test="${sfGq}">
							<td align="center" id="hj_cgTgq"><fmt:formatNumber value="${empty jptpd.cgTgq ? 0 : jptpd.cgTgq}" pattern="0.##" /></td>
						</c:if>
						<td align="center"></td>
						<td align="center" id="hj_cgTpf"><fmt:formatNumber value="${empty jptpd.cgTpf ? 0 : jptpd.cgTpf}" pattern="0.##" /></td>
						<td align="center" id="hj_cgTkje"><fmt:formatNumber value="${empty jptpd.cgTkje ? 0 : jptpd.cgTkje}" pattern="0.##" /></td>
						<td align="center" class="wb_td02"><fmt:formatNumber value="${empty ticket_return.tp_cgst or ticket_return.tp_cgst eq 0 ? ticket_return.tp_hxje : ticket_return.tp_cgst}" /></td>
					</tr>
			    </c:if>
			</table>
		</td>
	</tr>
</table>