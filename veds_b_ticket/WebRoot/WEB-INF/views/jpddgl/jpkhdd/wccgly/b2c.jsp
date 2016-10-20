<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<td>
	出 票 员
	 <select name="cpy" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${shyhbList}" var="pz">
   			<option value="${pz.bh}" ${pz.bh eq param.yh ? 'selected' : ''}>${pz.bh}</option>
   		</c:forEach>
   	</select>
	采购订单编号
	<input type="text" name="b2c_hkgsddh" datatype="*" value="${jpJpList[0].cgDdbh}" style="width:80px" class="input1 max-length-30" maxlength="30">
</td>
