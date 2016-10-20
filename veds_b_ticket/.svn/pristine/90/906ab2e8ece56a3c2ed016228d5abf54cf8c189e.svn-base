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
	OFFICE号
	<select id="office" name="gp_officeid1" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.officeid}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	采购科目
	<select name="gp_cgkm" id="gp_cgkm" class="select" style="width:80px;" datatype="*">
       <c:forEach items="${zfkms}" var="one">
          	<option value="${one.kmbh }" ${param.cgkm eq one.kmbh ? 'selected':''}>${one.kmmc }</option> 
       </c:forEach>
	</select>&nbsp;<font color="red">*</font>
	
	采购金额
	<input type="text" name="gp_cgje" datatype="*" value="${jpJpList[0].cgPj}" style="width:30px" class="input1 max-length-30" maxlength="30">
</td>