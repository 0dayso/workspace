<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="sumZdj" value="0"></c:set>
<c:set var="sumCgj" value="0"></c:set>
<c:set var="sumXsj" value="0"></c:set>
<c:set var="sumJsf" value="0"></c:set>
<c:set var="sumTax" value="0"></c:set>
<c:set var="sumHyxFs" value="0"></c:set>
<c:set var="sumYwxFs" value="0"></c:set>
<c:set var="sumYsje"  value="0"></c:set>
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
					<th>采购价</th>
					<th>销售价</th>
					<th>机建</th>
					<th>税费</th>
					<th>航意险<br>份数</th>
					<th>延误险<br>份数</th>
					<th>应收金额</th>
				</tr>
				<c:set var="tmp" value="" />
				<c:forEach items="${jpkhddCjrList}" var="vc">
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
							<input type="text" size="10" value="" datatype="*14-14" errormsg="请填写正确的票号格式"  maxlength="14" name="tknoArr"/>
							<input type="hidden" name="cjrArr" value="${vc.id}">
						</td>
						<td align="center" class="wb_td02">${vc.zjhm}</td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.cgZdj}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.cgPj}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.xsPj}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.xsJsf}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.xsTax}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.xsHyxfs}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.xsYwxfs}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99"  nullmsg="请输入值"/></td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.xsJe}" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"  nullmsg="请输入值"/></td>
						<c:set var="sumZdj" value="${sumZdj+vc.cgZdj}"></c:set>
						<c:set var="sumCgj" value="${sumCgj+vc.cgPj}"></c:set>
						<c:set var="sumXsj" value="${sumXsj+vc.xsPj}"></c:set>
						<c:set var="sumJsf" value="${sumJsf+vc.xsJsf}"></c:set>
						<c:set var="sumTax" value="${sumTax+vc.xsTax}"></c:set>
						<c:set var="sumHyxFs" value="${sumHyxFs+vc.xsHyxfs}"></c:set>
						<c:set var="sumYwxFs" value="${sumYwxFs+vc.xsYwxfs}"></c:set>
						<c:set var="sumYsje"  value="${sumYsje+vc.xsJe}"></c:set>
					</tr>
				</c:forEach>
				<!-- 合计列 -->
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td style="color: red" align="center">${sumZdj}</td>
					<td style="color: red" align="center">${sumCgj}</td>
					<td style="color: red" align="center">${sumXsj}</td>
					<td style="color: red" align="center">${sumJsf}</td>
					<td style="color: red" align="center">${sumTax}</td>
					<td style="color: red" align="center">${sumHyxFs}</td>
					<td style="color: red" align="center">${sumYwxFs}</td>
					<td style="color: red" align="center">${sumYsje}</td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
</table>		