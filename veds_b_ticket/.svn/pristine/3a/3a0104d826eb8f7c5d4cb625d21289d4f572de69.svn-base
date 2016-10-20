<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:if test="${empty cgdd}">
	尚未发起平台采购
</c:if>
<c:if test="${not empty cgdd}">
	最后从【${cgdd.ex.PLATNAME}】采购
	<b id="ptzccommonb"> 
		<c:if test="${cgdd.jyzt eq '1'}">
			已经支付到平台,单号是${cgdd.tradeNo}
			<input type="button" class="ext_btn_submit3" onclick="clearZfbj('${cgdd.id}');" value="清除标记重新支付"
				title="如果已经在平台支付成功，但平台拒单等情况，可以清除支付标记，重新采购.">
		</c:if> 
		<c:if test="${cgdd.jyzt ne '1'}">未支付或支付没有成功或被撤销</c:if> 
		<c:if test="${cgdd.jyzt eq '4'}">(订单已经撤销，请登录平台查看详细情况)</c:if> 
		<c:if test="${cgdd.platzt eq '12'}">无法出票</c:if> 
		<c:if test="${cgdd.platzt eq '13'}">出票完成</c:if> 
		<c:if test="${cgdd.platzt eq '14'}">更换编码出票完成(新编码：${cgdd.pnrno})</c:if>
	</b>
	<c:if test="${cgdd.platsqzt eq '1'}">
		已经授权的OFFICE号有：
		<c:if test="${empty cgdd.platoffice}">无</c:if>
		<c:if test="${not empty cgdd.platoffice}">${cgdd.platoffice}</c:if>
	</c:if>
	<c:if test="${empty cgdd.platoffice}">
		没有返回OFFICE无需授权
	</c:if>
	<c:if test="${not empty cgdd.platoffice}">
		<c:if test="${cgdd.platsqzt eq '-1' }">
			授权失败原因${cgdd.sqsbyy }请手工对【${cgdd.platoffice }】授权，然后继续采购
		</c:if>
	</c:if>
</c:if>
