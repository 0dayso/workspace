<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>平台出票</title>
<script type="text/javascript">
function toPay(payUrl){
	var jezq='${jezq}';
	if(jezq=='true' || jezq==true){
		parent.toPlatPay(payUrl);
	}else{
		var a=layer.confirm('系统金额${param.jsj}与平台应付金额${res.paymentAmount}不一致，是否继续支付？',function(){
			layer.close(a);
			parent.toPlatPay(payUrl);
		});
	}
}
</script>
<style type="text/css">
	.pay_div{
		margin: 10px 0;
		background: #fff;
		padding: 10px 0;
		border: 1px solid #DDDBDB;
	}
	.pay_div li{
		text-align: center;
		border-right: 1px solid #CBCBCB;
		display: inline-block;
		width: 128px;
	}
	.pay_div a{
		text-decoration: none;
	}
</style>
</head>
<body>
	<div>
		<div style="padding: 15px 10px;">
			<c:if test="${not empty error}">
				<strong style="color:#FF0000;">${error}</strong>
			</c:if>
			<c:if test="${empty error}">
				平台订单号：${res.orderNo}&nbsp;&nbsp;平台订单金额：<strong style="color:#F60;font-size:18px;">￥${res.paymentAmount}</strong>&nbsp;&nbsp;采购支付状态：
				<c:if test="${'1' eq res.orderStatus}">
					<strong style="color:#FF0000;">已支付</strong>
				</c:if>
				<c:if test="${'2' eq res.orderStatus}">
					<strong style="color:#008B00;">未支付(自动代扣失败)</strong>
				</c:if>
				<c:if test="${'3' eq res.orderStatus}">
					<strong style="color:#008B00;">未支付</strong>
				</c:if>
			</c:if>
		</div>
		<div class="pay_div">
			<ul>
				<c:forEach items="${res.payLinkList}" var="payLink">
					<li>
						<c:if test="${payLink.payment eq '10063971'}">
							<a href="###" onclick="toPay('${payLink.url}');"><img src="${ctx}/static/images/bank/1006397.gif"><p>支付宝支付</p></a>
						</c:if>
						<c:if test="${payLink.payment eq '16063781'}">
							<a href="###" onclick="toPay('${payLink.url}');"><img src="${ctx}/static/images/bank/16063781.gif"><p>财付通支付</p</a>
						</c:if>
						<c:if test="${payLink.payment eq '1006394'}">
							<a href="###" onclick="toPay('${payLink.url}');"><img src="${ctx}/static/images/bank/1006394.gif"><p>汇付支付</p</a>
						</c:if>
						<c:if test="${payLink.payment eq '1606362'}">
							<a href="###" onclick="toPay('${payLink.url}');"><img src="${ctx}/static/images/bank/1606362.gif"><p>快钱支付</p</a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html