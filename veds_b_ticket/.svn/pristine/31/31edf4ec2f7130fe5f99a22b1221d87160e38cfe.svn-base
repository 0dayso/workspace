<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<tr>
	<td class="title" >
		<c:if test="${not empty error}">
			<font color="red">退票失败：</font>
		</c:if>
		<c:if test="${empty error}">
			<font color="green">完成退票，退票单号【${trfd.refundNo}】</font>
		</c:if>
	</td>
</tr>
<tr>
	<td>
		<table class="no_border_table" cellpadding="0" cellspacing="0" border="0">
			<c:if test="${empty error}">
				<%-- 
				<c:if test="${not empty trfd.result1}">
					<tr><td><pre>${trfd.result1}</pre></td></tr>
				</c:if>--%>
				<c:if test="${not empty trfd.result2}">
					<tr><td><pre>${trfd.result2}</pre></td></tr>
				</c:if>
				<tr><td><pre>${trfd.result}</pre></td></tr>
			</c:if>
			<c:if test="${not empty error}">
				<tr><td><pre>${error}</pre></td></tr>
			</c:if>
		</table>
	</td>
</tr>
