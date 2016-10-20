<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="jpKhddHdList" value="${gqdData.jpKhddHdList}"></c:set>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/hc_flight.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">航程信息</span>
		</td>
		<td>
			<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;background:#f1f1f1;">
				<!-- 改签页面及改签详用到 -->
				<c:if test="${not empty jpGqdHdList}">
					<tr>
						<td style="text-align: center;vertical-align: middle;" rowspan="2">序号</td>
						<td colspan="6" align="center">原航程</td>
						<td colspan="6" align="center">新航程</td>
					</tr>
					<tr>
						<td colspan="4" align="center">航程</td>
						<td align="center">航班号</td>
						<td align="center">舱位</td>
						<td align="center" colspan="2">起飞时间<span style="color:red"> * </span></td>
						<td align="center" colspan="2">到达时间<span style="color:red"> * </span></td>
						<td align="center">航班号<span style="color:red"> * </span></td>
						<td align="center">舱位<span style="color:red"> * </span></td>
					</tr>
					<c:forEach items="${jpGqdHdList}" var="jpgqhd" varStatus="i">
						<c:set var="cfcity" value="${vfc:getBcity(jpgqhd.cfcity)}"></c:set>
						<c:set var="ddcity" value="${vfc:getBcity(jpgqhd.ddcity)}"></c:set>
						<tr>
							<input type="hidden" name="jpGqdHdIdArr" value="${jpgqhd.id}"/>
							<td style="text-align: center;vertical-align: middle;">${i.count}</td>
							<td colspan="4" style="text-align: center;vertical-align: middle;">
								${fn:substring(jpgqhd.oCfsj,0,10)}&nbsp;
								${fn:substring(jpgqhd.oCfsj,11,16)}
								${cfcity.mc}(${jpgqhd.cfcity})
								&nbsp;-&nbsp;
								${fn:substring(jpgqhd.oDdsj,11,16)}
								${ddcity.mc}(${jpgqhd.ddcity})
								&nbsp;&nbsp;&nbsp;
								<span title="">退改签</span>
							</td>
							<td style="text-align: center;vertical-align: middle;">
								${jpgqhd.oXsHbh}
							</td>
							<td style="text-align: center;vertical-align: middle;">
								${jpgqhd.oXsCw}
								<input type="hidden" class="oXsCw" id="oXsCw${jpgqhd.id}${jpgqhd.gqdh}" value="${jpgqhd.oXsCw}"/>
							</td>
							<td colspan="2" align="center">
								<span>
								<input name="nCfsjArr" value="${fn:substring(jpgqhd.nCfsj,0,16)}" class="input-text Wdate" style="width:120px"  size="10"    
								 id="ncfsj${jpgqhd.id}${jpgqhd.gqdh}"  bh="${jpgqhd.id}${jpgqhd.gqdh}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
								</span>
							<td colspan="2" align="center">
								<span>
								<input name="nDdsjArr" value="${fn:substring(jpgqhd.nDdsj,0,16)}" class="input-text Wdate" style="width:120px"  size="10"  
								     id="nddsj${jpgqhd.id}${jpgqhd.gqdh}" bh="${jpgqhd.id}${jpgqhd.gqdh}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
								 </span>
							</td>
							<td align="center">
								<input type="text" value="${jpgqhd.nXsHbh}" name="nXsHbhArr" datatype="*" nullmsg="请填写值"  style="height: 20px;width:60px" onblur="this.value=this.value.replace('%','').toUpperCase()" id="hbhidCase"/>
							</td>
							<td align="center">
								<span id="nxscw${jpgqhd.id}${jpgqhd.gqdh}">
									<c:if test="${jpgqd.gqlx eq '1'}">
										${jpgqhd.oXsCw}
										<input type="hidden" id="nXsCw${jpgqhd.id}${jpgqhd.gqdh}" value="${jpgqhd.oXsCw}" name="nXsCwArr" style="height: 20px;width:30px" />
									</c:if>
									<c:if test="${jpgqd.gqlx eq '2'}">
										<input type="text" id="nXsCw${jpgqhd.id}${jpgqhd.gqdh}" value="${jpgqhd.nXsCw}" name="nXsCwArr" datatype="*" nullmsg="请填写值" style="height: 20px;width:30px" onblur="this.value=this.value.replace('%','').toUpperCase()" id="cwidCase"/>
									</c:if>
								</span>
							</td>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>