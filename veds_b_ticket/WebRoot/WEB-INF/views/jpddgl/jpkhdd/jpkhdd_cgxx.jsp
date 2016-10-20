<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="sumZdj" value="0"></c:set>
<c:set var="sumJsf" value="0"></c:set>
<c:set var="sumTax" value="0"></c:set>
<c:set var="sumCgj" value="0"></c:set>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="jpInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/jpxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">采购信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:90px;">
				<!--表头 -->
				<tr style="background:#f1f1f1;">
					<th>乘机人</th>
					<th>帐单价</th>
					<th>机建</th>
					<th>税费</th>
					<th>采购价</th>
				</tr>
				<!--明细列 -->
				<c:forEach items="${jpkhddCjrList}" var="vc">
					<tr id="jptr${vc.tkno}">
						<td align="center" class="wb_td02">${vc.cjr}</td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.cgZdj}"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.cgJsf}"/></td>
						<td align="center" class="wb_td02"><input type="text" size="1" value="${vc.cgTax}"/></td>
						<td align="center" class="wb_td02"><input type="text" size="2" value="${vc.cgPj}"/></td>
						<c:set var="sumZdj" value="${sumZdj+vc.cgZdj}"></c:set>
						<c:set var="sumJsf" value="${sumJsf+vc.cgJsf}"></c:set>
						<c:set var="sumTax" value="${sumTax+vc.cgTax}"></c:set>
						<c:set var="sumCgj" value="${sumCgj+vc.cgPj}"></c:set>
					</tr>
				</c:forEach>
				<!-- 合计列 -->
				<tr>
					<td></td>
					<td style="color: red" align="center">${sumZdj}</td>
					<td style="color: red" align="center">${sumJsf}</td>
					<td style="color: red" align="center">${sumTax}</td>
					<td style="color: red" align="center">${sumCgj}</td>
				</tr>	
			</table>
		</td>	
	</tr>
</table>		