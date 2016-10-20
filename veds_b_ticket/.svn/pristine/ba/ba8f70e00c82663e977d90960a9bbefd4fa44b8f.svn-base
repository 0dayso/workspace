<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="no_border_table" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="4">
			> ${trfd.cmd}
		</td>
	</tr>
	<tr>
		<td colspan="4">
			${trfd.trfu}
		</td>
	</tr>
	<tr>
		<td>
			<span class="chinese">航空公司</span><span class="english" style="display: none;">Airline Code</span> <font color="red">*</font>
			<input type="text" id="airlineCode_${tkno}" name="airlineCode" class="no_border" 
				value="${trfd.airlineCode}" style="width:30px;" readonly />
		</td>
		<td>
			<span class="chinese">票号</span><span class="english" style="display: none;">TKT Number</span> <font color="red">*</font>
			<input type="text" id="ticketNo_${tkno}" name="ticketNo" class="no_border" 
				value="${trfd.tktNumber}" style="width:90px;" readonly />
			-<font color="red">*</font>
			<input type="text" id="secondTicketNo_${tkno}" name="secondTicketNo" class="no_border" 
				value="${trfd.secondTicketNo}" style="width:60px;" readonly />
		</td>
		<td>
			<span class="chinese">检查号</span><span class="english" style="display: none;">Check</span> <font color="red">*</font>
			<input type="text" id="check_${tkno}" name="check" class="bottom_border" 
				value="${trfd.check}" style="width:30px;" />
		</td>
		<td style="width:10px;">
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td>
			<span class="chinese">关联号</span><span class="english" style="display: none;">Conjunction No.</span> <font color="red">*</font>
			<input type="text" id="conjunctionNo_${tkno}" name="conjunctionNo" class="no_border" 
				value="${trfd.conjunctionNo}" style="width:30px;" readonly />
		</td>
		<td colspan="2">
			<span class="chinese">退票联次</span><span class="english" style="display: none;">Coupon No.</span> <font color="red">*</font>
			1<font color="red">*</font><input type="text" id="couponNo1_${tkno}" name="couponNo" 
				class="bottom_border" value="${trfd.couponNo1}" style="width:40px;" />
			2<font color="red">*</font><input type="text" id="couponNo2_${tkno}" name="couponNo" 
				class="no_border" value="${trfd.couponNo2}" style="width:40px;" readonly />
			3<font color="red">*</font><input type="text" id="couponNo3_${tkno}" name="couponNo" 
				class="no_border" value="${trfd.couponNo3}" style="width:40px;" readonly />
			4<font color="red">*</font><input type="text" id="couponNo4_${tkno}" name="couponNo" 
				class="no_border" value="${trfd.couponNo4}" style="width:40px;" readonly />
		</td>
		<td>
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<span class="chinese">乘机人姓名</span><span class="english" style="display: none;">Passenger Name</span> <font color="red">*</font>
			<input type="text" id="passengerName_${tkno}" name="passengerName" class="no_border" value="${trfd.passengerName}" 
				style="width:400px;" readonly />
		</td>
		<td>
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td>
			<span class="chinese">货币类型</span><span class="english" style="display: none;">Currency Code</span> <font color="red">*</font>
			<input type="text" id="currencyCode_${tkno}" name="currencyCode" class="bottom_border" 
				value="${trfd.currencyCode}" style="width:40px;" />
		</td>
		<td colspan="2">
			<span class="chinese">付款方式</span><span class="english" style="display: none;">Form Of Payment</span> <font color="red">*</font>
			<input type="text" id="formOfPayment_${tkno}" name="formOfPayment" class="bottom_border" 
				value="${trfd.formOfPayment}" style="width:40px;" />
		</td>
		<td>
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<span class="chinese">票面价</span><span class="english" style="display: none;">Gross Refund</span> <font color="red">*</font>
			<input type="text" id="grossRefund_${tkno}" name="grossRefund" class="no_border" value="${tfd.cj_xsj}" 
				style="width:80px;" readonly />
		</td>
		<td>
			<span class="chinese">电子客票-(Y/N):</span><span class="english" style="display: none;">ET-(Y/N):</span> <font color="red">*</font>
			<input type="text" id="et_${tkno}" name="et" class="bottom_border" value="${trfd.et}" 
				style="width:30px;" />
		</td>
		<td>
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td>
			<span class="chinese">退票费</span><span class="english" style="display: none;">Deduction</span> <font color="red">*</font>
			<input type="text" id="deduction_${tkno}" name="deduction" class="no_border" value="${tfd.jp_pjhk}" 
				style="width:60px;" readonly />
		</td>
		<td colspan="2">
			<span class="chinese">代理费</span><span class="english" style="display: none;">Commission</span> <font color="red">*</font>
			<input type="text" id="commissionRate_${tkno}" name="commissionRate" class="no_border" 
				value="${empty trfd.commissionRate ? tfd.by1 : trfd.commissionRate}" style="width:40px;" readonly />% =
			<font color="red">*</font>
			<input type="text" id="commission_${tkno}" name="commission" class="no_border" value="${tfd.cj_dlf}" 
				style="width:160px;text-align: right;" readonly />
		</td>
		<td>
			<font color="red">*</font>
		</td>
	</tr>
</table>
<c:set var="taxs" value="${trfd.taxs}" />
<table class="no_border_table" cellpadding="0" cellspacing="0" border="0">
	<c:set var="v" value="0" />
	<c:forEach begin="0" end="8">
		<tr>
			<td>${v eq 0 ? '<span class="chinese">税费</span><span class="english" style="display: none;">TAX</span>' : ''}</td>
			<c:forEach begin="0" end="2">
				<td>[${v lt 9 ? ' ' : ''}${v+1}]</td>
				<td>
					<c:set var="taxType" value="${taxs[v].taxType}" />
					<font color="red">*</font>
					<input type="text" id="taxType_${tkno}_${v}" name="taxType" class="${taxType eq 'CN' ? 'no_border' : 'bottom_border'}" 
						value="${taxType}" style="width:20px;" ${taxType eq 'CN' ? 'readonly' : ''} />
				</td>
				<td>
					<font color="red">*</font>
					<c:choose>
						<c:when test="${taxType eq 'CN'}">
							<c:set var="tax" value="${tfd.cj_jsf}" />
						</c:when>
						<c:when test="${taxType eq 'YQ'}">
							<c:set var="tax" value="${tfd.cj_tax}" />
						</c:when>
						<c:otherwise>
							<c:set var="tax" value="${taxs[v].tax}" />
						</c:otherwise>
					</c:choose>
					<input type="text" id="tax_${tkno}_${v}" name="tax" class="${taxType eq 'CN' ? 'no_border' : 'bottom_border'}" 
						value="${tax}" style="width:80px;text-align: right" ${taxType eq 'CN' ? 'readonly' : ''} />
				</td>
				<c:set var="v" value="${v+1}" />
			</c:forEach>
			<td style="width:10px;">
				<font color="red">*</font>
			</td>
		</tr>
	</c:forEach>
</table>
<table class="no_border_table" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>
			<span class="chinese">备注</span><span class="english" style="display: none;">Remark</span> <font color="red">*</font>
			<input type="text" id="remark1_${tkno}" name="remark1" class="bottom_border" 
				value="${trfd.remark1}" style="width:40px;" />
			 <font color="red">*</font>
			<input type="text" id="remark2_${tkno}" name="remark2" class="bottom_border" 
				value="${trfd.remark2}" style="width:90px;" />
		</td>
		<td colspan="2">
			<span class="chinese">信用卡</span><span class="english" style="display: none;">Credit Card</span> <font color="red">*</font>
			<input type="text" id="creditCard_${tkno}" name="creditCard" class="bottom_border" 
				value="${trfd.creditCard}" style="width:200px;" />
		</td>
		<td style="width:10px;">
			<font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<span class="chinese">实退金额</span><span class="english" style="display: none;">Net Refund</span> = <font color="red">*</font>
			<input type="text" id="netRefund_${tkno}"  name="netRefund" class="no_border" 
				value="${tfd.tp_cgst}" style="width:60px;" readonly />
			 <font color="red">*</font>
			<input type="text" id="moneyType_${tkno}" name="moneyType" class="bottom_border" 
				value="${empty trfd.netRefund2 ? 'CNY' : trfd.netRefund2}" style="width:100px;" />
			 <font color="red">*</font>
			 <font color="red" style="padding-left: 100px;">*</font>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<font color="red" style="padding-left: 160px;">*</font>
			<font color="red" style="padding-left: 100px;">*</font>
		</td>
	</tr>
</table>