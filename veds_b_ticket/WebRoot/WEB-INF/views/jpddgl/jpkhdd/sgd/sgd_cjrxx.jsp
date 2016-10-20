<%@ page language="java" pageEncoding="UTF-8"%>
<div class="box_top"><b class="pl15">乘机人信息</b>
    <c:set var="cjr_gngj" value="${kh_khdd_pn.pnr_hcglgj}"></c:set>
	<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:90px;background:#f1f1f1;" id="insertRow1">
		<script id="tpl1" type="text/html">
				<%@include file="sgd_cjrxx_mb_tbody.jsp" %>
		</script>
		<tr style="background:#f1f1f1;">
			<th>乘机人<a href="javascript:setAllJe();" title="点击以第一个乘机人金额为准，计算其他乘机人金额" >↓</a></th>
			<th>类型</th>
			<th>证件号码</th>
			<th>手机(单个)</th>
			<th>账单价</th>
			<th>采购价</th>
			<th>销售价</th>
			<th>机建</th>
			<th>税费</th>
			<th id="ysxj">
				应收金额
			</th>
			<th>操作</th>
		</tr>
		<c:if test="${empty cjrList}">
			<%@include file="sgd_cjrxx_mb_tbody.jsp" %>
		</c:if>
		<c:if test="${not empty cjrList}">
			<c:forEach items="${cjrList}" var="cjr" varStatus="vs">
				<%@include file="sgd_cjrxx_mb_tbody.jsp" %>
			</c:forEach>
		</c:if>
	</table>
</div>
<div class="clear"></div>

