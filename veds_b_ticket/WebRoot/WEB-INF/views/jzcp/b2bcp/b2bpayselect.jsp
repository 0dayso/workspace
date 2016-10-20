<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>支付方式选择页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ticket_cp.css"/>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
	</head>
	<body>
		<table width="100%"  class="loginboxzdcp">
		<tr>
			<td colspan="5"><h3 class="red">${stateInfo.airways}航空公司PNR[${b2borderresponse.stateInfo.pnr}/${b2borderresponse.stateInfo.hkgspnr }]已经入库成功，等待支付。 航空公司订单编号：<b>${b2borderresponse.orderInfo.airwaysOrderNo }</b> </h3></td>
		</tr>
		<tr>
			<td>
				支付金额：<b>${b2borderresponse.orderInfo.paymentMoney }</b>
			</td>
			<td>
			    总票价：<b>${b2borderresponse.orderInfo.totalPrice }</b>
			</td>
			<td>
			    机建燃油：<b>${b2borderresponse.orderInfo.totalTax}</b>
			</td>
			<td>
			   代理费：<b>${b2borderresponse.orderInfo.agencyMoney }</b>
			</td>
			<td>
				
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td class="compare_color" >
				<input type="button" name="dgbutton" id="zdzf" value="自动支付" onclick="payb2b('${param.ddbh}','0')"; class="ext_btn ext_btn_submit">
            	<select class="select01" style="width:100px" id="b2b_zflx" name="zflx" onchange="">
            	<option value="" >=支付方式=</option>
            	<c:forEach items="${zffsStr}" var="payone">
            	<c:if test="${payone eq '1' && jpB2bHkgsxx.fs1 eq '1'}">
            	<option value="1" >支付宝</option>
            	</c:if>
            	<c:if test="${payone eq '2' && jpB2bHkgsxx.fs2 eq '1'}">
            	<option value="2" >财付通</option>
            	</c:if>
            	<c:if test="${payone eq '3' && jpB2bHkgsxx.fs3 eq '1'}">
            	<option value="3" >汇付天下</option>
            	</c:if>
            	<c:if test="${payone eq '4' && jpB2bHkgsxx.fs4 eq '1'}">
            	<option value="4" > 易 宝 </option>
            	</c:if>
            	<c:if test="${payone eq '5' && jpB2bHkgsxx.fs5 eq '1'}">
            	<option value="5" > 快 钱 </option>
            	</c:if>
            	<c:if test="${payone eq '6' && jpB2bHkgsxx.fs6 eq '1'}">
            	<option value="6" >御航宝</option>
            	</c:if>
            	<c:if test="${payone eq '7' && jpB2bHkgsxx.fs7 eq '1'}">
            	<option value="7" >易商旅</option>
            	</c:if>
            	<c:if test="${payone eq '8' && jpB2bHkgsxx.fs8 eq '1'}">
            	<option value="8" >易生卡</option>
            	</c:if>
            	<c:if test="${payone eq '9' && jpB2bHkgsxx.fs9 eq '1'}">
            	<option value="9" >易航宝</option>
            	</c:if>
            	</c:forEach>
            </select>
            <input type="text" class="input01 width100" id="b2b_zfzh" name="zfzh"/>
            <input type="button" name="dgbutton" id="sdzf" value="手动支付" onclick="payb2b('${param.ddbh}','1');" class="ext_btn ext_btn_submit">
	           <select name="b2b_zf_fklx" id="b2b_zf_fklx" class="width120 select01" onchange="setDefaultB2b_zf_fklx('${jpKhdd.hkgs}')">
				   <option value="" style="color:blue;font-weight: bold;">选择系统对应科目</option>
				   <c:forEach  var="shzfkm" items="${shzfkmList}">
				   <option value="${shzfkm.kmbh}">${shzfkm.kmmc}</option>
				   </c:forEach>
			   </select>
		</tr>
	</table>
	<script type="text/javascript">
		<c:if test="${not empty errormsg}">
			alert("${errormsg}");
		</c:if>
	</script>
	</body>
</html>