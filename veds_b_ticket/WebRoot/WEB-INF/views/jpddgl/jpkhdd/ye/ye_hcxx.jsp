<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" id="hcInfo">
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td>
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:90px;background:#f1f1f1;">
				<c:if test="${not empty jpkhddHdList}">
					<c:forEach items="${jpkhddHdList}" var="vc" varStatus="vs">
						<c:set var="cfbcity" value="${vfc:getBcity(vc.cfcity)}" />
						<c:set var="ddbcity" value="${vfc:getBcity(vc.ddcity)}" />
						
					    <!-- ARNK 缺口程 -->
						<c:if test="${vc.xsHbh ne 'ARNK'}">
							<tr id="hctr${vc.id}" style="background: ${not empty hdgqList ? '#efefef' : '' }">
								<td width="" style="border:none"></td>
								<td width="" style="font-size:14px; font-weight:bold;text-align:center;border:none;">
									${vc.cfcity}（${cfbcity.mc }） 
									<input type="hidden" id="hczw${vc.id}" name="hczw" value="${vc.cfcity}${vc.ddcity}">
									<input type="hidden" id="hcid${vc.id}" name="hcid" value="${vc.id}" hc="${vc.cfcity}${vc.ddcity}"> 
									<input type="hidden" id="cfsj${vc.id}" name="cfsj" value="${vc.cfsj}">
									<input type="hidden" id="hbh${vc.id}"  name="hbh"  value="${vc.xsHbh}"> 
									<input type="hidden" id="cw${vc.id}"   name="cw"   value="${vc.xsCw}">
								</td>
								<td width="" style="border:none"></td>
								<td width="" style="font-size:14px; font-weight:bold;text-align:center;border:none;">
								${vc.ddcity}（${ddbcity.mc }）</td>
								<td style="border:none;text-align:center;" rowspan = "3">
									<c:if test="${vc.cgHbh eq vc.xsHbh}">
									  ${vc.xsHbh}
									</c:if>
									<c:if test="${vc.cgHbh ne vc.xsHbh}">
									 	 销售：${vc.xsHbh}<br>采购：${vc.cgHbh}
									</c:if>
								</td>
								<td align="center" style="border:none;text-align:center;" rowspan = "3">
									<c:if test="${vc.xsCw eq vc.cgCw}">
									  ${vc.xsCw}
									</c:if>
									<c:if test="${vc.xsCw ne vc.cgCw}">
									 	 销售：${vc.xsCw}<br>采购：${vc.cgCw}
									</c:if>
								</td>
								<td style="border:none;text-align:left;" rowspan = "3">
									<c:if test="${vc.xsTgq eq vc.cgTgq}">
									  	${vfn:cut(vc.xsTgq, 8)}
									</c:if>
									<c:if test="${vc.xsTgq ne vc.cgTgq}">
										 销售：${vfn:cut(vc.xsTgq, 8)}<br>
									 	 采购：${vfn:cut(vc.cgTgq, 8)}
									</c:if>
								</td>
							</tr>
							<tr>
								<td height="30px" style="font-size:14px; font-weight:bold; text-align:center;border:none;">${fn:substring(vc.cfsj,0,10)}</td>
								<td style="text-align:center;border:none;">${cfbcity.jcmc} (${vc.cfhzl})</td>
								<td style="border:none;text-align:center;" rowspan = "2">
								<img src="${ctx}/static/images/wdimages/hc_plane.png" />
								<br>${vc.fjjx}</td>
								<td style="text-align:center;border:none;">${ddbcity.jcmc} (${vc.ddhzl})</td>
							</tr>
							<tr>
								<td height="30px" style="border:none"></td>
								<td style="font-size:14px; font-weight:bold;text-align:center;border:none;">${fn:substring(vc.cfsj,10,16)}</td>
								<td style="font-size:14px; font-weight:bold;text-align:center;border:none;">${fn:substring(vc.ddsj,10,16)}</td>
							</tr>
							<c:set var="hc_id" value="${hc_id},${vc.id}" />
							<c:set var="hc" value="${hc},${vc.cfcity}${vc.ddcity}" />
						</c:if>
						<c:if test="${fn:length(jpkhddHdList) ne vs.count }">
							<tr>
								<td style="border:none;height:2px;">&nbsp;</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>

<c:if test="${not empty hc}">
	<c:set var="hc" value="${fn:substring(hc,1,fn:length(hc))}" />
</c:if>
<input type="hidden" name="qhc" value="${hc}" />
<input type="hidden" name="qhc_id" value="${hc_id}" />
<input type="hidden" name="hb_Dynamic" id="hb_Dynamic" value="${hb_Dynamic}" />