<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
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
					<th>销售佣金</th>
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
					<td align="center" class="wb_td02">${tfje.XS_ZDJ}</td>
					<td align="center" class="wb_td02">${tfje.XS_PJ}</td>
					<td align="center" class="wb_td02">${tfje.XS_YJ}</td>
					<td align="center" class="wb_td02">${tfje.XS_JSF}</td>
					<td align="center" class="wb_td02">${tfje.XS_TAX}</td>
					<c:if test="${sfGq}">
						<td align="center" class="wb_td02">
							<a href="###" onclick="setGqfy(this,'${cg}','read')" >${tfje.XS_TGQ}</a>
						</td>
					</c:if>
					<td align="center" class="wb_td02">${vfn:format(tfje.XS_TPFL,'#.#%')}</td>
					<td align="center" class="wb_td02">${tfje.XS_TPSXF}</td>
					<td align="center" class="wb_td02">${tfje.XS_TKJE}</td>
					<td width="60">&nbsp;</td>
				</tr>
				
				<!--客户退款合计 -->
				<tr style="font-weight: bolder;background: #f6f6f6" class="wb_td02">
					<td align="center" id="hj_xsZdj" class="red">${empty jptpd.xsZdj ? 0 : jptpd.xsZdj}</td>
					<td align="center" id="hj_xsPj" class="red" >${empty jptpd.xsPj  ? 0 : jptpd.xsPj}</td>
					<td align="center" id="hj_xsYj" class="red" >${empty jptpd.xsYj  ? 0 : jptpd.xsYj}</td>
					<td align="center" id="hj_xsJsf" class="red">${empty jptpd.xsJsf ? 0 : jptpd.xsJsf}</td>
					<td align="center" id="hj_xsTax" class="red">${empty jptpd.xsTax ? 0 : jptpd.xsTax}</td>
					<c:if test="${sfGq}">
						<td align="center" id="hj_xsTgq">${empty jptpd.xsTgq ? 0 : jptpd.xsTgq}</td>
					</c:if>
					<td align="center" class="red"></td>
					<td align="center" id="hj_xsTpsxf" class="red">${empty jptpd.xsTpsxf ? 0 : jptpd.xsTpsxf}</td>
					<td align="center" id="hj_xsTkje" class="red">${empty jptpd.xsTkje ? 0 : jptpd.xsTkje}</td>
					<td align="center" class="wb_td02" width="60">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>