<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="tkno" value="${param.tkno}" />
<tr>
	<td class="title" >
		退票确认：
		<c:if test="${not empty error}"><font color="red">${error}</font></c:if>
		<c:if test="${empty error}">
			<font color="green">请认真核对以下信息，其中“<font color="red">*</font><input type="text" name="xxx" class="bottom_border" style="width:30px;background: #fff" />”为可输入项。  点击</font>
			<input type="button" name="savebut" value="完成退票" class="asms_button" onclick="toSave('${tkno}')">
			<font color="green">完成退票</font>
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="changeLanguage(this);" style="color:red;">切换到英文模式</a>
		</c:if>
	</td>
</tr>
<c:if test="${empty error}">
	<tr>
		<td id="fourth_td_${tkno}" style="padding-left: 20px;padding-top: 3px;padding-bottom: 3px;">
			<form id="trfdForm_${tkno}" name="trfdForm" action="" method="post">
				<input type="hidden" name="operation" value="${param.operation}" />
				<input type="hidden" name="mxid" value="${param.id}" />
				<input type="hidden" name="tkno" value="${tkno}" />
				<c:if test="${param.operation eq '1'}">
					<%@include file="trfd_yl_auto.jsp"%>
				</c:if>
				<c:if test="${param.operation eq '2'}">
					<%@include file="trfd_yl_halfauto.jsp"%>
				</c:if>
				<c:if test="${param.operation eq '3'}">
					<%@include file="trfd_yl_manual.jsp"%>
				</c:if>
			</form>
		</td>
	</tr>
</c:if>
