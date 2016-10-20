<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>

<c:if test="${not empty zfzhList}">
<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="td_right" style="width: 19.5%;">主账号:</td>
		<td class="td_left" style="width: 50%;">
			<select name="zzh" id="zzh" style="width:170px;height: 24px;">
				<c:forEach var="item" items="${zfzhList}">
					<option value="${item.id}">${item.zfzh}[${item.zflxName}]</option>
				</c:forEach>
			</select>
			<font color="red"> *<span id="error1"></span></font>
		</td>
	</tr>
	<tr>
		<td class="td_right">备用账号:</td>
		<td class="td_left">
			<select name="byzh" id="byzh" style="width:170px;height: 24px;">
				<option value="">== 请选择 ==</option>
				<c:forEach var="item" items="${zfzhList}">
					<option value="${item.id}">${item.zfzh}[${item.zflxName}]</option>
				</c:forEach>
			</select>
			<span>主账号余额不足,使用备用账号</span>
		</td>
	</tr>
</table>
</c:if>