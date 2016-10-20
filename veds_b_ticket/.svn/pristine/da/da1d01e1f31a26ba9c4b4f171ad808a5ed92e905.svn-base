<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="jpInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/jpxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">机票信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:90px;">
				<tr>
					<td colspan="${col}" id="jptitle">
						<span class="wb_td02" style="${jpList[0].TP_ISVALID eq '1' and ticket_return.lxkpzt ne '1' ? '' : 'display:none;'}" id="hdtpWarn">
							您选择了【按航段退票】<c:if test="${tfjeedit eq '0'}">，请输入退航段账单价/机建/税费</c:if>
						</span>
						<span class="wb_td02" style="${ticket_return.lxkpzt eq '1' ? '' : 'display:none;'}" id="lxkpWarn">
							您选择的机票为连续客票，且为【按航段退票】<c:if test="${tfjeedit eq '0'}">，请输入退账单价/机建/税费</c:if>
						</span>
					</td>
				</tr>
				<tr style="background:#f1f1f1;">
					<th>乘机人</th>
					<th>证件号码</th>
					<th>票号 票证</th>
					<th>账单价</th>
					<th>采购价</th>
					<th>销售价</th>
					<th>销售佣金</th>
					<th>机建</th>
					<th>税费</th>
					<c:if test="${tfInfo.sfGq}">
						<th>升舱<br>费用</th>
					</c:if>
					<th>支付</th>
				</tr>
				<c:set var="tmp" value="" />
				<c:forEach items="${jpList}" var="vc">
					<tr id="jptr${vc.TKNO}">
						<td align="center" class="red">${vc.CJR}<script type="text/javascript">getCjrlxImg('${vc.CJRLX}')</script>
						</td>
						<td align="center" class="red">${vc.ZJHM}</td>
						<td align="center" class="red">
						   		<span title="出票：<font color=red>${vc.CP_COMPMC}|${vc.CP_DEPTMC}|${vc.CP_USERMC}|${fn:substring(vc.CP_DATETIME,0,16)}</font><br>
									机票航程：<font color=red>${vc.JP_HC}</font>
									<c:if test='${!apply}'>
										退航程：<font color=red>${vc.TP_HC}</font><br>
										<c:if test="${not empty vc.CG_ZFFSMC}">采购办理方式：<font color=red>${vc.CG_ZFFSMC}</font>采购办理科目：<font color=red>${vc.CG_ZFKMMC }</font><br></c:if>
										<c:if test="${ticket_return.tp_type eq '1' and vc.HX_TFZT eq '1'}">黑屏退票单号：<font color=red>${vc.HX_TFDH}</font><br></c:if>
										<c:if test="${ticket_return.tp_type eq '2' and vc.HX_TFZT eq '1'}">VT状态：<font color=red>VT成功</font><br></c:if>
									</c:if>
									">
									${vc.TKNO}
									<c:choose>
										<c:when test="${vc.CPLX eq 'OP'}">【${vc.CU_JSDWMC}】</c:when>
										<c:when test="${vc.CPLX eq 'BOP' or vc.CPLX eq 'BSP'}">【${vc.CP_OFFICEID}-${vc.CP_PID}-${vc.PRINTNO}】</c:when>
									</c:choose>
								</span>
						</td>
						<td align="center" class="red">${vc.XS_ZDJ}</td>
						<td align="center" class="red">${vc.CG_PJ}</td>
						<td align="center" class="red">${vc.XS_PJ}</td>
						<td align="center" class="red">${vc.XS_YJ}</td>
						<td align="center" class="red">${vc.XS_JSF}</td>
						<td align="center" class="red">${vc.XS_TAX}</td>
						<c:if test="${tfInfo.sfGq}">
							<td align="center" class="red"><a href="javascript:void(0)" onclick="showGqxx(this,'${vc.TKNO}')" title="点击查看改签信息">${vc.GQFY}</a></td>
						</c:if>
						<td align="center" class="red">
							<c:if test="${vc.SKZT eq '0'}">
								未付
							</c:if>
							<c:if test="${vc.SKZT eq '1'}">
								已付
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>	
	</tr>
</table>		