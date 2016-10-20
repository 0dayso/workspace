<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" id="hcInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td>
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:90px;background:#f1f1f1;">
				<c:if test="${not empty hdList}">
					<c:forEach items="${hdList}" var="vc" varStatus="vs">
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
								<td width="" style="border:none"></td>
								<td width="" style="border:none"></td>
								<td width="40%" style="border:none"></td>
							</tr>
							<tr>
								<td height="30px" style="font-size:14px; font-weight:bold; text-align:center;border:none;">${fn:replace(fn:substring(vc.cfsj,0,10),'/','-')}</td>
								<td style="text-align:center;border:none;">${cfbcity.jcmc} (${vc.cfhzl})</td>
								<td style="border:none">
								<img src="${ctx}/static/images/wdimages/hc_plane.png" /></td>
								<td style="text-align:center;border:none;">${ddbcity.jcmc} (${vc.ddhzl})</td>
								<td style="border:none;text-align:center;">${vc.xsHbh} 
								<%--  
								<c:set var="dynamic" value="${fn:split(vc.HB_DYNAMIC,'&&')}" /> 
									<c:if test="${dynamic[0] eq '1'}">
										<c:set var="hb_Dynamic" value="true" />
										<img src="/asms/images/flight_delay.gif"
											title="航班延误：<font color=red>${dynamic[1] }</font><br>" />
									</c:if> <c:if test="${dynamic[0] eq '2'}">
										<c:set var="hb_Dynamic" value="true" />
										<img src="/asms/images/flight_cancel.gif" title="航班取消" />
									</c:if>
								--%>	
								</td>
								<td align="center" style="border:none;text-align:center;">${vc.xsCw}</td>
								<td style="border:none;text-align:center;">
								  退票规定:${vfn:cut(vc.xsTgq,20)}
								</td>
							</tr>
							<tr>
								<td height="30px" style="border:none"></td>
								<td style="font-size:14px; font-weight:bold;text-align:center;border:none;">${fn:substring(vc.cfsj,10,16)}</td>
								<td style="border:none"></td>
								<td style="font-size:14px; font-weight:bold;text-align:center;border:none;">${fn:substring(vc.ddsj,10,16)}</td>
								<td colspan="3" style="border:none;"></td>
							</tr>
							<!--  
							<c:set var="hdIndex" value="1"></c:set>
							<c:forEach items="${tfInfo.hdgqList}" var="hd">
									<c:if
										test="${vc.CFCITY eq hd.CFCITY and vc.DDCITY eq hd.DDCITY}">
										<tr>
											<td colspan="2" style="border:none;">${hdIndex}、${fn:substring(hd.GQ_DATETIME,0,16)}
												办理${hd.GQLX eq '2' ? '升舱' : '改期' }</td>
											<td align='center' style="border:none;">${fn:substring(hd.N_CFDATETIME,10,16)}</td>
											<td align='center' style="border:none;">${fn:substring(hd.N_DDDATETIME,10,16)}</td>
											<td align='center' style="border:none;">${hd.N_HBH }</td>
											<td align='center' style="border:none;">${hd.N_CW }</td>
											<td style="border:none; width:50%;"><MD:cut
													value="${hd.TPGD}" length="35" autopoint="true"></MD:cut>
											</td>
										</tr>
										<c:set var="hdIndex" value="${hdIndex+1}"></c:set>
									</c:if>
								</c:forEach>
								-->
							<c:set var="hc_id" value="${hc_id},${vc.id}" />
							<c:set var="hc" value="${hc},${vc.cfcity}${vc.ddcity}" />
						</c:if>
						<c:if test="${fn:length(hdList) ne vs.count }">
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