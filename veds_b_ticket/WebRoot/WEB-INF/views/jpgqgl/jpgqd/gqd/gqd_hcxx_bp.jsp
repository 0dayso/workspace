<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="jpKhddHdList" value="${gqdData.jpKhddHdList}"></c:set>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 98%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td>
			<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;background:#f1f1f1;">
					<tr>
						<th align="center">航程</th>
						<th align="center" colspan="2">起飞时间</th>
						<th align="center">到达时间</th>
						<th align="center">航班号</th>
						<th align="center">舱位</th>
					</tr>
					<c:forEach items="${jpGqdHdList}" var="jpgqhd" varStatus="i">
						<c:set var="cfcity" value="${vfc:getBcity(jpgqhd.cfcity)}"></c:set>
						<c:set var="ddcity" value="${vfc:getBcity(jpgqhd.ddcity)}"></c:set>
						<tr>
							<input type="hidden" name="jpGqdHdId" value="${jpgqhd.id}"/>
							<td style="text-align: center;vertical-align: middle;" rowspan="2">
								<span>${jpgqhd.cfcity}${jpgqhd.ddcity}(${cfcity.mc}-${ddcity.mc})</span>
							</td>
							<td align="center">
								<span>改签前</span>
							</td>
							<td align="center">
								<span style="${jpgqhd.oCfsj ne jpgqhd.nCfsj ? 'color:red;text-decoration:line-through;' : ''}">${fn:substring(jpgqhd.oCfsj,0,16)}</span>
							</td>
							<td align="center">
								<span style="${jpgqhd.oDdsj ne jpgqhd.nDdsj ? 'color:red;text-decoration:line-through;' : ''}">${fn:substring(jpgqhd.oDdsj,0,16)}</span>
							</td>
							<td style="text-align: center;vertical-align: middle;">
								<span style="${jpgqhd.oXsHbh ne jpgqhd.nXsHbh ? 'color:red;text-decoration:line-through;' : ''}">${jpgqhd.oXsHbh}</span>
							</td>
							<td style="text-align: center;vertical-align: middle;">
								<span style="${jpgqhd.oXsCw ne jpgqhd.nXsCw ? 'color:red;text-decoration:line-through;' : ''}">${jpgqhd.oXsCw}</span>
								<input type="hidden" class="oXsCw" id="oXsCw${jpgqhd.id}${jpgqhd.gqdh}" value="${jpgqhd.oXsCw}"/>
							</td>
						</tr>
						<tr>
							<td align="center">
								<span>改签后</span>
							</td>
							<td align="center">
								<span>${fn:substring(jpgqhd.nCfsj,0,16)}</span>
							</td>
							<td align="center">
								<span>${fn:substring(jpgqhd.nDdsj,0,16)}</span>
							</td>
							<td style="text-align: center;vertical-align: middle;">
								${jpgqhd.nXsHbh}
							</td>
							<td style="text-align: center;vertical-align: middle;">
								${jpgqhd.nXsCw}
								<input type="hidden" class="oXsCw" id="oXsCw${jpgqhd.id}${jpgqhd.gqdh}" value="${jpgqhd.oXsCw}"/>
							</td>
						</tr>
					</c:forEach>
			</table>
		</td>
	</tr>
</table>