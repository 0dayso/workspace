<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<table class="list_table" cellpadding="0" cellspacing="0"  margin-top: 0px;" id="hcInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td style="position: relative; top:0px;">
		<table class="list_table" cellpadding="0" cellspacing="0"  align="center"  style="height:90px;">
			<tr>
				<td class="title" colspan="${fn:length(hdList) > 1 ? 8 : 7}">
					<c:if test="${fn:length(hdList) > 1}">
						<span class="shuomin" style="${param.tp_type eq '2' ? 'display:none;' : ''}" id="hcWarn">请选择退票航段</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<c:if test="${fn:length(hdList) > 1}">
					<th style="${param.tp_type eq '2' and !flhd ? 'display:none;' : ''}" id="hcTh">
					      <input type="checkbox" onclick="selectAll(this,'hc')">
					</th>
				</c:if>
				<th>出发城市</th>
				<th>到达城市</th>
				<th>起飞时间</th>
				<th>到达时间</th>
				<th>航班</th>
				<th>舱位</th>
				<th style="width:40%">退票规定</th>
			</tr>
			<c:set var="hc" value="" />
			<c:set var="hc_id" value="" />
			<c:set var="hb_Dynamic" value="false"/>
			<c:if test="${not empty hdList}">
				<c:forEach items="${hdList}" var="vc" varStatus="vs">
				   <c:set var="cfbcity" value="${vfc:getBcity(vc.cfcity)}" />
				   <c:set var="ddbcity" value="${vfc:getBcity(vc.ddcity)}" />
				    <%--  ARNK  缺口程 --%>
					<c:if test="${vc.xsHbh ne 'ARNK'}">
						<tr id="hctr${vc.id}" style="background: ${not empty hdgqList ? '#FFFFCC' : '' }">
							<c:if test="${fn:length(hdList) >= 2}">
								<td align="center" style="${param.tp_type eq '2' and !flhd ? 'display:none;' : ''}">
									<input type="checkbox" id="hcCheckBox_${vc.id}" name="hcCheckBox" value="${vc.id}" hcid="${vc.id}" 
										hc="${vc.cfcity}${vc.ddcity}" hcqm = "${vc.cfcity}（${cfbcity.mc}）-${vc.ddcity}（${ddbcity.mc}）" onclick="selectHcOrCjr()" >
								</td>
							</c:if>
							<td align="center" class="red">
								${vc.cfcity}（${cfbcity.mc }） 
								<input type="hidden" id="hczw${vc.id}" name="hczw" value="${vc.cfcity}${vc.ddcity}" >
								<input type="hidden" id="hcid${vc.id}" name="hcid" value="${vc.id}" hc="${vc.cfcity}${vc.ddcity}">
								<input type="hidden" id="cfsj${vc.id}" name="cfsj" value="${vc.cfsj}" >
								<input type="hidden" id="hbh${vc.id}" name="hbh" value="${vc.xsHbh}" >
								<input type="hidden" id="cw${vc.id}" name="cw" value="${vc.xsCw}" >
							</td>
							<td align="center" class="red">${vc.ddcity}（${ddbcity.mc}）</td>
							<td align="center" class="red">${fn:substring(vc.cfsj,0,16)}</td>
							<td align="center" class="red">${fn:substring(vc.ddsj,0,16)}</td>
							<td align="center" class="red">${vc.xsHbh}
							<%--  
								<c:set var="dynamic" value="${fn:split(vc.HB_DYNAMIC,'&&')}"/>
								<c:if test="${dynamic[0] eq '1'}">
									<c:set var="hb_Dynamic" value="true"/>
									<img src="/asms/images/flight_delay.gif" title="航班延误：<font color=red>${dynamic[1] }</font><br>"/>
								</c:if>
								<c:if test="${dynamic[0] eq '2'}">
									<c:set var="hb_Dynamic" value="true"/>
									<img src="/asms/images/flight_cancel.gif" title="航班取消"/>
								</c:if>
								--%>
							</td>
							<td align="center" class="red">${vc.xsCw}</td>
							<td class="red">
							    ${vfn:cut(vc.xsTgq,20)}
							</td>
						</tr>
						<tbody id="hctb${vc.id}"></tbody>
						<c:set var="hc_id" value="${hc_id},${vc.id}" />
						<c:set var="hc" value="${hc},${vc.cfcity}${vc.ddcity}" />
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
<input type="hidden" name="hb_Dynamic" id = "hb_Dynamic" value="${hb_Dynamic}" />