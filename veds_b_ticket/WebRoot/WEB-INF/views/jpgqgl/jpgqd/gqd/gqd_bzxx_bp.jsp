<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<input type="hidden" name="bz_error" id="bz_error" value="${error }">
<c:forEach items="${list}" var="vc" varStatus="vs">
	<tr id="bz_${vs.count }">
		<td>
			${vs.count }、${vc.name }<span id="jg_${vc.id }"></span><br/>
		</td>
	</tr>
	<tr id="TRXQ_${vs.count}" style="display: none;">
		<td id="TDXQ_${vs.count}"></td>
	</tr>
</c:forEach>