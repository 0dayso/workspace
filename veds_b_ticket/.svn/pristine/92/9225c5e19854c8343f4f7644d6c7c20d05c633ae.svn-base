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
				<!-- 根据查询申请改签页面用到 -->
				<c:if test="${not empty jpKhddHdList}">
						<c:set var="jphds" value="${fn:length(jpKhddHdList)}"></c:set>
						<tr>
							<td style="text-align: left;vertical-align: middle;" rowspan="2">
							<c:if test="${jphds ne 1}">
								<input type="checkbox" id="hccheckall" onclick="checkAllHc(this)"/>
							</c:if>
							</td>
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
						<c:forEach items="${jpKhddHdList}" var="jpKhddHd" varStatus="i">
							<c:set var="cfcity" value="${vfc:getBcity(jpKhddHd.cfcity)}"></c:set>
							<c:set var="ddcity" value="${vfc:getBcity(jpKhddHd.ddcity)}"></c:set>
							<tr>
								<c:set var="num" value="${i.index}"></c:set>
								<td style="text-align: left;vertical-align: middle;">
									<c:if test="${jphds eq 1}">
										<input type="hidden" name="jpHdIdArr" value="${jpKhddHd.id}"/>
									</c:if>
									<c:if test="${jphds ne 1}">
										<input type="checkbox" id="hccheck${jpKhddHd.id}" name="jpHdIdArr" value="${jpKhddHd.id}" ddbh="${jpKhddHd.ddbh}" onclick="checkHc('${jpKhddHd.id}')" />
									</c:if>
								</td>
								<td style="text-align: center;vertical-align: middle;" colspan="4" >
									${fn:substring(jpKhddHd.cfsj,0,10)}&nbsp;
									${fn:substring(jpKhddHd.cfsj,11,16)}
									${cfcity.mc}(${jpKhddHd.cfcity})
									&nbsp;-&nbsp;
									${fn:substring(jpKhddHd.ddsj,11,16)}
									${ddcity.mc}(${jpKhddHd.ddcity})
									&nbsp;&nbsp;&nbsp;
									<span title="">退改签</span>
								</td>
								<td style="text-align: center;vertical-align: middle;">
									${jpKhddHd.xsHbh}
								</td>
								<td style="text-align: center;vertical-align: middle;">
									${jpKhddHd.xsCw}
									<input type="hidden" class="oXsCw" id="oXsCw${jpKhddHd.id}${jpKhddHd.ddbh}" value="${jpKhddHd.xsCw}"/>
								</td>
								<td colspan="2" align="center">
									<span id="ncfsj${jpKhddHd.id}${jpKhddHd.ddbh}">
										<c:if test="${jphds eq 1}">
											<input  type="text" name="nCfsjArr" value="" style="width:120px" class="input-text Wdate" size="10" datatype="*" nullmsg="请填写值" id="mindate${jpKhddHd.id}${jpKhddHd.ddbh}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'maxdate${jpKhddHd.id}${jpKhddHd.ddbh}\')}'})">
										</c:if>
									</span>
								</td>
								<td colspan="2" align="center">
									<span id="nddsj${jpKhddHd.id}${jpKhddHd.ddbh}">
										<c:if test="${jphds eq 1}">
											<input  type="text" name="nDdsjArr" value="" style="width:120px" class="input-text Wdate" size="10" datatype="*" nullmsg="请填写值"  id="maxdate${jpKhddHd.id}${jpKhddHd.ddbh}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'mindate${jpKhddHd.id}${jpKhddHd.ddbh}\')}'})">
										</c:if>	
									</span>
								</td>
								<td align="center">
									<span id="nhbh${jpKhddHd.id}${jpKhddHd.ddbh}">
										<c:if test="${jphds eq 1}">
											<input type="text" value="" name="nXsHbhArr" datatype="*" nullmsg="请填写值" style="height: 20px;width:60px"/>
										</c:if>
									</span>
								</td>
								<td align="center">
									<span id="nxscw${jpKhddHd.id}${jpKhddHd.ddbh}">
										<c:if test="${jphds eq 1}">
											${jpKhddHd.xsCw}
											<input type="hidden" value="${jpKhddHd.xsCw}" name="nXsCwArr" style="height: 20px;width:40px"/>
										</c:if>
									</span>
								</td>
							</tr>
							<tr id="ddhdtr_${jpKhddHd.id}" style="display: none;">
								<td></td>
								<td id="gqlsjl_${jpKhddHd.id}" colspan="6" style="background: #e6e6e6;"></td>
							</tr>
						</c:forEach>
				</c:if>
				
				<!-- 手工申请改签页面用到 -->
				<c:if test="${empty jpGqdHdList and empty jpKhddHdList}">
						<tr>
							<td style="text-align: center;vertical-align: middle;" rowspan="2">序号</td>
							<td colspan="6" align="center">原航程</td>
							<td colspan="6" align="center">新航程</td>
							<td style="text-align: center;vertical-align: middle;" rowspan="2">
								操作<img src="${ctx}/static/images/wdimages/drop-add.gif" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">航程</td>
							<td align="center">航班号</td>
							<td align="center">舱位</td>
							<td align="center">起飞时间</td>
							<td align="center" colspan="2">起飞时间</td>
							<td align="center" colspan="2">到达时间</td>
							<td align="center">航班号</td>
							<td align="center">舱位</td>
						</tr>
						<tr>
							<td align="center" rowspan="2">1</td>
							<td colspan="3" align="center">
								<input type="text" value="" name="hc" style="height: 20px;width:100px"/>
							</td>
							<td align="center">
								<input type="text" value="" name="xsHbh" style="height: 20px;width:100px"/>
							</td>
							<td align="center">
								<input type="text" value="" id="nXsCw" name="xsCw" style="height: 20px;width:30px"/>
							</td>
							<td align="center">
								<span><input name="oCfsj" value="" class="input-text Wdate" size="10"  onFocus="WdatePicker()" ></span>
							</td>
							<td colspan="2" align="center">
								<span><input name="nCfsj" value="" style="width:150px" class="input-text Wdate" size="10"  id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'maxdate\')}'})"></span>
							</td>
							<td colspan="2" align="center">
								<span><input name="nDdsj" value="" style="width:150px" class="input-text Wdate" size="10"  id="maxdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'mindate\')}'})"></span>
							</td>
							<td align="center">
								<input type="text" value="" name="nXsHbh" style="height: 20px;width:60px"/>
							</td>
							<td align="center">
								<input type="text" value="" name="nXsCw" style="height: 20px;width:30px" title="改签类型为升舱时才能编辑新舱位"/>
							</td>
							<td align="center">
							</td>
						</tr>
				</c:if>
			</table>
		</td>
	</tr>
</table>
