<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td>
			<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;background:#f1f1f1;">
				<c:if test="${not empty jpGqdHdList}">
					<c:forEach items="${jpGqdHdList}" var="jphd" varStatus="i">
						<c:set var="cfcity" value="${vfc:getBcity(jphd.cfcity)}"></c:set>
						<c:set var="ddcity" value="${vfc:getBcity(jphd.ddcity)}"></c:set>
						<tr>
							<td style="text-align:center;vertical-align:middle;" rowspan="3" >${fn:substring(jphd.oCfsj,0,10)}</td>
							<td>${cfcity.mc}</td>
							<td rowspan="2"><img src="${ctx}/static/images/wdimages/hc_plane.png" /></td>
							<td>${ddcity.mc}</td>
							<td rowspan="3"><img src="${ctx}/static/images/wdimages/gqx.png" /></td>
							<td style="text-align:center;vertical-align:middle;" rowspan="3" >
								<span style="${fn:substring(jphd.oCfsj,0,10) ne fn:substring(jphd.nCfsj,0,10) ? 'color: red' : ''}">${fn:substring(jphd.nCfsj,0,10)}</span>
							</td>
							<td>${cfcity.mc}</td>
							<td rowspan="2"><img src="${ctx}/static/images/wdimages/hc_plane.png" /></td>
							<td>${ddcity.mc}</td>
							<td rowspan="3">${vfn:cut(jphd.oXsTgq,20)}</td>
						</tr>
						<tr>
							<td>(${jphd.cfcity})</td>
							<td>(${jphd.ddcity})</td>
							<td>(${jphd.cfcity})</td>
							<td>(${jphd.ddcity})</td>
						</tr>
						<tr>
							<td>${fn:substring(jphd.oCfsj,11,16)}</td>
							<td align="center">
								${jphd.oXsHbh}&nbsp;${jphd.oXsCw}
							</td>
							<td>${fn:substring(jphd.oDdsj,11,16)}</td>
							<td>
								<span style="${fn:substring(jphd.nCfsj,11,16) ne fn:substring(jphd.oCfsj,11,16) ? 'color: red' : ''}">${fn:substring(jphd.nCfsj,11,16)}</span>
							</td>
							<td align="center">
								<span style="${jphd.oXsHbh ne jphd.nXsHbh ? 'color: red' : ''}">${jphd.nXsHbh}&nbsp;</span>
								<span style="${jphd.oXsCw ne jphd.nXsCw ? 'color:red' : ''}">${jphd.nXsCw}</span>
							</td>
							<td>
								<span style="${fn:substring(jphd.oDdsj,11,16) ne fn:substring(jphd.nDdsj,11,16) ? 'color: red' : ''}">${fn:substring(jphd.nDdsj,11,16)}</span>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>
