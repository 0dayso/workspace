<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="result"  value="${trfd.result}" />
<table class="no_border_table" cellpadding="0" cellspacing="0" border="0" style="width: 700px;">
    <!-- 第1行 -->
	<tr>
		<td colspan="4">
			> ${trfd.cmd}
		</td>
	</tr>
	
	<tr>
		<td colspan="4"></td>
	</tr>
	
	<!-- 第2行 -->
	<tr>
		<td colspan="4" style="text-align: center">
			<span class="chinese">航空公司/BSP退票资料表格</span><span class="english" style="display: none;">${result.header}</span>
		</td>
	</tr>
	
	<tr>
		<td colspan="4"></td>
	</tr>
	
	<!-- 第3行 -->
	<tr>
		<td>
			<span class="chinese">退票单号</span><span class="english" style="display: none;">REFUND NUMBER</span>:
		</td>
		<td>
			<span class="chinese">退票类型</span><span class="english" style="display: none;">REFUND TYPE</span>:
		</td>
		<td>
			<span class="chinese">打票机号</span><span class="english" style="display: none;">DEVICE-ID</span>:
		</td>
		<td>
		</td>
	</tr>
	
	<!-- 第4行 -->
	<tr>
		<td>
			<span class="chinese">时间</span><span class="english" style="display: none;">DATE</span>:${result.dateTime}
		</td>
		<td>
			<span class="chinese">工作号</span><span class="english" style="display: none;">AGENT</span>:${result.agent}
		</td>
		<td>
			<span class="chinese">航协号</span><span class="english" style="display: none;">IATA</span>:${result.iata}
		</td>
		<td>
			<span class="chinese">OFFICE</span><span class="english" style="display: none;">OFFICE</span>:${result.office}
		</td>
	</tr>
	<tr>
		<td colspan="4"></td>
	</tr>
	
	<!-- 第5行 -->
	<tr>
		<td>
			<span class="chinese">航空公司</span><span class="english" style="display: none;">AIRLINE CODE</span>:${result.airlineCode}
		</td>
		<td colspan="3">
			<span class="chinese">票号</span><span class="english" style="display: none;">TICKET NO.</span>:${trfd.ticketNo}-${trfd.secondTicketNo}
		</td>
	</tr>
	
	
	<!-- 第6行 -->
	<tr>
		<td>
			<span class="chinese">关联号</span><span class="english" style="display: none;">CONJUNCTION NO.</span>:${result.conjunctionNo}
		</td>
		<td colspan="3">
			<span class="chinese">退票联次</span><span class="english" style="display: none;">COUPON NO.</span>:
			1:${trfd.couponNo1} 2:${trfd.couponNo2} 3:${trfd.couponNo3} 4:${trfd.couponNo4} 
		</td>
	</tr>
	
	<!-- 第7行 -->
	<tr>
		<td colspan="4">
			<span class="chinese">乘机人姓名</span><span class="english" style="display: none;">PASSENGER NAME</span>:${result.passengerName}
		</td>
	</tr>
	
	<!-- 第8行 -->
	<tr>
		<td>
			<span class="chinese">票面价</span><span class="english" style="display: none;">GROSS  REFUND</span>:${result.grossRefund}
		</td>
		<td>
			<span class="chinese">付款方式</span><span class="english" style="display: none;">PAYMENT  FORM</span>:${result.formOfPayment}
		</td>
		<td>
			<span class="chinese">货币类型</span><span class="english" style="display: none;">CURRENCY  CODE</span>:${result.currencyCode}
		</td>
		<td>
		</td>
	</tr>
	
	<!-- 第9行 -->
	<tr>
		<td colspan="2">
			SN CD AMOUNT(SN-SEQUENCY NUMBER : ${result.sn_cd_amount})
		</td>
		<td colspan="2">
			<span class="chinese">电子客票-(Y/N)</span><span class="english" style="display: none;">ET-(Y/N)</span>:${result.et}
		</td>
	</tr>
	
	<!-- 第10行 -->
	<tr>
		<td colspan="4">
			<c:set var="taxs" value="" />
			<span class="chinese">税费</span><span class="english" style="display: none;">TAX</span>:
			<c:forEach var="vc" items="${trfd.taxs}" varStatus="vs">
			     ${vs.index+1}:${vc.taxType}&nbsp;${vc.tax}&nbsp;
			</c:forEach>
		</td>
	</tr>
	
	
	<!-- 第11行 -->
	<tr>
		<td>
			<span class="chinese">代理费率</span><span class="english" style="display: none;">COMMITMENT</span>:${result.commission}%
		</td>
		<td colspan="3">
			<span class="chinese">退票费</span><span class="english" style="display: none;">OTHER DEDUCTION</span>:${result.deduction}
		</td>
	</tr>
	
	
	<!-- 第12行 -->
	<tr>
		<td>
			<span class="chinese">实退金额</span><span class="english" style="display: none;">NET REFUND</span>:${result.netRefund}
		</td>
		<td colspan="3">
		    <span class="chinese">航司退费</span><span class="english" style="display: none;">AIRLINE REFUND</span>:${result.airlineRefund}
		</td>
	</tr>
	
	<!-- 第13行 -->
	<tr>
		<td colspan="4">
			<span class="chinese">代理费</span><span class="english" style="display: none;">COMMISSION AMOUNT</span>:${result.commissionAmount}
		</td>
	</tr>
	
	<!-- 第14行 -->
	<tr>
		<td colspan="4">
			<span class="chinese">信用卡</span><span class="english" style="display: none;">CREDIT CARD</span>:${result.creditCard}
		</td>
	</tr>
	
	<tr>
		<td colspan="4"></td>
	</tr>
	
	<!-- 第15行 -->
	<tr>
		<td colspan="1">
			<span class="chinese">确认退票</span><span class="english" style="display: none;"> CONFIRM  REFUND</span>     
		</td>
		<td colspan="3">
		    >RFIS:${result.airlineCode}-${trfd.ticketNo}
		</td>
	</tr>
</table>