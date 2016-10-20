<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="khInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/kh.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">乘机人信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:90px;">
				<tr style="background:#f1f1f1;">
					<th>乘机人</th>
					<th>票号</th>
					<th>证件号码</th>
					<th>账单价</th>
					<th>机建</th>
					<th>税费</th>
				</tr>
				<c:forEach items="${jpkhddCjrList}" var="vc" varStatus="vs">
				 	<input type="hidden" name ="cjrid" value="${vc.sxh}"/>
					<tr id="jptr${vc.tkno}">
						<c:set var="cjrlx" value=""></c:set>
						<c:choose>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:when>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_et.gif"></c:set>
							</c:when>
							<c:when test="${vc.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_ye.gif"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:otherwise>
						</c:choose>
						<td align="center" class="wb_td02">${vc.cjr}<span><img src= "${cjrlx}" style='vertical-align:middle'></span></td>
						<td align="center" class="wb_td02">
						     <input type="text" id="tkno_${vc.id}" name = "tkno" maxlength = "14" size="12" value="${vc.tkno}" datatype="*14-14" errormsg="请填写正确的票号格式"  onblur="checkTkno('tkno_${vc.id}',$.trim(this.value).toUpperCase());value=$.trim(this.value);" /><font color="red">*</font></td>
						<td align="center" class="wb_td02">${vc.zjhm}</td>
						<td align="center" >
							<input type="text" name="cgZdj" value="${vc.cgZdj}" style="width: 60px;text-align:right;" datatype=" /^\+?[1-9]\d*$/"  nullmsg="请输入值" errormsg="请输入正确格式的值"/>
						</td>
						<td align="center" >
							<input type="text" name="cgJsf" value="${vc.cgJsf}" style="width: 60px;text-align:right;" datatype="n"  nullmsg="请输入值" errormsg="请输入正确格式的值"/>
						</td>
						<td align="center" >
							<input type="text" name="cgTax" value="${vc.cgTax}" style="width: 60px;text-align:right;" datatype="n"  nullmsg="请输入值" errormsg="请输入正确格式的值"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>		