<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<style>

.wb_td_input{
   width:50px;
}
</style>
<c:set var="ps" value="${fn:length(mxList)}"></c:set>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="cgInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/kh.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">客户退款</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="100%" style="height:90px;">
				<tr style="background:#f1f1f1;">
					<th>账单价</th>
					<th>销售价</th>
					<th>机建</th>
					<th>税费</th>
					<c:if test="${sfGq}">
						<th>升舱费</th>
					</c:if>
					<th>退票费率</th>
					<th>退票费</th>
					<th>应退金额</th>
					<th width="60">&nbsp;</th>
				</tr>
				<!-- 第一条明细 -->
				<tr>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="xsZdj" name="xsZdj" value="${tfje.XS_ZDJ}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="xsPj" name="xsPj"  onblur="this.value=getFs(this.value);ytje('xs',${ps})" value="${tfje.XS_PJ}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="xsJsf" name="xsJsf" onblur="this.value=getFs(this.value);ytje('xs',${ps})" value="${tfje.XS_JSF}" >
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="xsTax" name="xsTax" onblur="this.value=getFs(this.value);ytje('xs',${ps})" value="${tfje.XS_TAX}" >
					</td>
					<c:if test="${sfGq}">
						<td align="center" class="wb_td02">
							<a href="###" onclick="setGqfy(this,'${cg}','read')" >${tfje.XS_TGQ}</a>
						</td>
					</c:if>
					<td align="center" class="wb_td02">
						<fmt:formatNumber var="xsTpfl" value="${empty tfje.XS_TPFL ? 0 : tfje.XS_TPFL*100}" pattern="0.##" />
						<input class="wb_td_input" id="xsTpfl" name="xsTpfl" onblur="toTpfl('xs',${ps})" value="${xsTpfl}" >%
					</td>
					<td align="center" class="wb_td02">
						<input class="wb_td_input" id="xsTpsxf" name="xsTpsxf" value="${tfje.XS_TPSXF}" onblur="this.value=getZs(this.value);ytje('xs',${ps})" >
					</td>
					<td align="center" class="wb_td02" id="xsTkje">
						${tfje.XS_TKJE}
					</td>
					<td width="60">&nbsp;</td>
				</tr>
				
				<!--客户退款合计 -->
				<c:if test="${ps >1 }">
					<tr style="font-weight: bolder;background: #f6f6f6" class="wb_td02">
						<td align="center" id="hj_xsZdj">
							<fmt:formatNumber value="${empty jptpd.xsZdj ? 0 : jptpd.xsZdj}" pattern="0.##" />
						</td>
						<td align="center" id="hj_xsPj" >
							<fmt:formatNumber value="${empty jptpd.xsPj  ? 0 : jptpd.xsPj}"  pattern="0.##" />
						</td>
						<td align="center" id="hj_xsJsf">
							<fmt:formatNumber value="${empty jptpd.xsJsf ? 0 : jptpd.xsJsf}" pattern="0.##" />
						</td>
						<td align="center" id="hj_xsTax">
							<fmt:formatNumber value="${empty jptpd.xsTax ? 0 : jptpd.xsTax}" pattern="0.##" />
						</td>
						<c:if test="${sfGq}">
							<td align="center" id="hj_xsTgq">
								<fmt:formatNumber value="${empty jptpd.xsTgq ? 0 : jptpd.xsTgq}" pattern="0.##" />
							</td>
						</c:if>
						<td align="center"></td>
						<td align="center" id="hj_xsTpsxf">
							<fmt:formatNumber value="${empty jptpd.xsTpsxf ? 0 : jptpd.xsTpsxf}" pattern="0.##" />
						</td>
						<td align="center" id="hj_xsTkje">
							<fmt:formatNumber value="${empty jptpd.xsTkje ? 0 : jptpd.xsTkje}" pattern="0.##" />
						</td>
						<td align="center" class="wb_td02" width="60">&nbsp;</td>
					</tr>
				</c:if>
			</table>
		</td>
	</tr>
</table>