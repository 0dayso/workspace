<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>出票信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ticket_cp.css"/>
	<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
	</head>
	<body>
	<table width="100%"  class="loginboxzdcp">
		<tr>
			<td align="center"><h3 class="red">
			${b2bmsg.hkgs }航空公司PNR[${b2borderresponse.stateInfo.pnr}/${b2borderresponse.stateInfo.hkgspnr }]<br>
			订单支付支付成功，支付金额为【${b2borderresponse.orderInfo.paymentMoney }】。
			</h3></td>
		</tr>
	</table>
	<table width="95%" class="loginboxzdcp" align="center">
		<tr>
			<td>订单编号：<b>${b2borderresponse.orderInfo.airwaysOrderNo }</b></td>
			<td>支付金额：<b>${b2borderresponse.orderInfo.paymentMoney }</b></td>
			<td>总票价：<b>${b2borderresponse.orderInfo.totalPrice }</b></td>
			<td>机建燃油：<b>${b2borderresponse.orderInfo.totalPrice}</b></td>
			<td>代理费：<b>${b2borderresponse.orderInfo.totalTax }</b></td>
	    </tr>
		<tr>
			<td colspan="5">
				乘机人票号：<br>
				<c:set value="${fn:length(b2borderresponse.orderInfo.passengerInfos)>0 ? '1' : '0'}" var="hasph"></c:set>
				<c:forEach items="${b2borderresponse.orderInfo.passengerInfos}" var="one" varStatus="vst">
				   <c:if test="${not empty one.ticketNo }">
				   		<input name="ticketnocp" value="${one.ticketNo }" type="hidden">
				   		<b>${one.passenger }${one.ticketNo }</b> &nbsp;
				   </c:if>
				   <c:if test="${empty one.ticketNo }">
				   	    <c:set value="0" var="hasph"></c:set>
				   	    <input name="ticketnocp_no" value="${one.passenger }" type="hidden">
				   	    <b>${one.passenger }&nbsp; 暂未获取到票号</b> &nbsp;
				   </c:if>
				   <c:if test="${vst.count % 4 eq 0}">
				   	<br>
				   </c:if>
				</c:forEach>
			</td>
		</tr>
	</table>
	</body>
</html>