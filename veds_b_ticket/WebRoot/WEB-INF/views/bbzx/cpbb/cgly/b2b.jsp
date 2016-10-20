<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <td>
 	&nbsp;采购科目
 	<select name="cgkm" id="cgkm" class="select" style="width:100px;" datatype="*">
        <c:forEach items="${zfkms}" var="one">
           	<option value="${one.kmbh }" ${param.cgkm eq one.kmbh ? 'selected':''}>${one.kmmc }</option> 
         </c:forEach>
	</select>&nbsp;<font color="red">*</font>
	office号
	<select id="office" name="cpOfficeid" datatype="*">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.officeid}" ${pz.officeid eq param.cpOfficeid ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	采购订单编号
	<input type="text" id="cgDdbh" name="cgDdbh" datatype="*"  value="${param.cgDdbh }" class="input-text lh25" />
	&nbsp;<font color="red">*</font>
</td>