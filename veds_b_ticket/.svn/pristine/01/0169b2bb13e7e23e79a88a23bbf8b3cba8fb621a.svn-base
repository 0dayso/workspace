<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<td>
	&nbsp;工 作 号     
	<input type="text" name="workno" id="workno" class="input-text lh25" value="${param.workno}" style="width:100px;">&nbsp;<font color="red">*</font>
	office号 
	<select id="office" name="office">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.officeid}" ${pz.officeid eq param.cpOfficeid ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	<font color="red">*</font>
	打票机   
	<input type="text" name="printno" id="printno" class="input-text lh25" value="${param.printno}" style="width:100px;">  
	<font color="red">*</font> 
</td>