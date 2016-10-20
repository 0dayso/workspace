<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	$(function(){
		var leng="${fn:length(jpList)}";
		if(leng==1){
			$('input[type="checkbox"][name="cjrCheckBox"]').each(function(){
				if($(this).attr("checked")){
					$(this).click();
				}
				$(this).attr("checked",true);
			});
		}
		if(leng>1){
			$("#savebutton").hide();
		}
	});
</script>
<table class="list_table" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="jpInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/jpxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">机票信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="list_table" cellpadding="0" cellspacing="0"  style="float:left;height:90px;">
				<tr style="display: none;">
					<td colspan="11" id="jptitle">
						<span class="wb_td02" style="${mxList[0].TP_ISVALID eq '1' and ticket_return.lxkpzt ne '1' ? '' : 'display:none;'}" id="hdtpWarn">
							您选择了【按航段退票】<c:if test="${tfjeedit eq '0'}">，请输入退航段账单价/机建/税费</c:if>
						</span>
						<span class="wb_td02" style="${ticket_return.lxkpzt eq '1' ? '' : 'display:none;'}" id="lxkpWarn">
							您选择的机票为连续客票，且为【按航段退票】<c:if test="${tfjeedit eq '0'}">，请输入退账单价/机建/税费</c:if>
						</span>
					</td>
				</tr>
				<tr style="background:#f1f1f1;">
					<th>
						<input type="checkbox" onclick="selectAll(this,'cjr')">乘机人
					</th>
					<th>证件号码</th>
					<th>票号 票证</th>
					<th>账单价</th>
					<th>采购价</th>
					<th>销售价</th>
					<th>机建</th>
					<th>税费</th>
					<c:if test="${tfInfo.sfGq}">
						<th>升舱<br>费用</th>
					</c:if>
					<th title="销售价${khdd.pnr_hcglgj eq '1' ? '+机建' : ''}+税费${khdd.pnr_hcglgj ne '1' and PJSZ.XSFWFQY eq '1' ? '+销售服务费' : ''}+保险金额${PJSZ.JECQY eq '1' ? '+接车' : ''}${PJSZ.QTQY eq '1' ? '+其他' : ''}${tfInfo.sfGq ? '+升舱费用' : ''}">小计</th>
					<th>支付</th>
				</tr>
				<c:set var="tmp" value="" />
				
				<c:set var="listSize" value="${fn:length(jpList)}" />
				<c:forEach items="${jpList}" var="vc">
					<tr id="jptr${vc.TKNO}">
						<td align="center" class="wb_td02">
						    <input type="checkbox" name="cjrCheckBox" tkno="${vc.TKNO}" id="cjr_${vc.TKNO}" onclick="selectHcOrCjr()" ${listSize eq 1 ? 'checked' : '' }>
						    ${vc.CJR}<c:set var="cjrlx" value=""></c:set>
							<c:choose>
								<c:when test="${vc.cjrlx eq '1'}">
									<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
								</c:when>
								<c:when test="${vc.cjrlx eq '2'}">
									<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_et.gif"></c:set>
								</c:when>
								<c:when test="${vc.cjrlx eq '3'}">
									<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_ye.gif"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
								</c:otherwise>	
							</c:choose>
						    <%@include file="tpd_add_tkno_hidden.jsp"%>
						</td>
						<td align="center" class="wb_td02">${vc.ZJHM}</td>
						<td align="center">
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
										<c:when test="${vc.CPLX eq 'BSPET' or vc.CPLX eq 'BSP'}">【${vc.CP_OFFICEID}-${vc.CP_PID}-${vc.PRINTNO}】</c:when>
									</c:choose>
								</span>
						</td>
						<td align="center" class="wb_td02">${vc.XS_ZDJ}</td>
						<td align="center" class="wb_td02">${vc.XS_PJ}</td>
						<td align="center" class="wb_td02">${vc.CG_PJ}</td>
						<td align="center" class="wb_td02">${vc.XS_JSF}</td>
						<td align="center" class="wb_td02">${vc.XS_TAX}</td>
						<c:if test="${tfInfo.sfGq}">
							<td align="center" class="wb_td02">
							  <a href="javascript:void(0)" onclick="showGqxx(this,'${vc.TKNO}')" title="点击查看改签信息">${vc.GQFY}</a>
							</td>
						</c:if>
						<td align="center" class="wb_td02">${vc.PJ_XSJ+vc.PJ_JSF+vc.PJ_TAX+vc.QT2+(empty vc.JP_BXFS ? vc.PJ_BX : vc.JP_BX)+vc.JEC+vc.QT1+vc.GQFY}</td>
						<td align="center" class="wb_td02">
							<c:choose>
								<c:when test="${vc.ZF_FKF eq '1'}">
									<a href="javascript:void(0)" onclick="showZfxx(this,'${vc.TKNO}')" title="${vc.ZF_FKFSMC}-${vc.ZF_FKLXMC}<br>点击查看支付信息">已付</a>
								</c:when>
								<c:otherwise><span title="${vc.ZF_FKFSMC}-${vc.ZF_FKLXMC}">未付</span></c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>	
	</tr>
</table>		