<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <td>
 	<c:forEach items="${bopcgkmlist}" var="pz" varStatus="s">
 		<c:if test="${s.index eq 0}">
			<input type="hidden" name="cgkm" id="cgkmhidden" value="${pz.bopcgkm}"/>
		</c:if>
			<input type="hidden" id="${pz.officeid}_index" value="${pz.bopcgkm}"/>
	</c:forEach>
 	&nbsp;采购科目
 	<select name="cgkms" id="cgkm" class="select" style="width:100px;" disabled="disabled">
        <c:forEach items="${bopcgkmlist}" var="one">
           	<option value="${one.bopcgkm }" ${param.cgkm eq one.bopcgkm ? 'selected':''}>${one.ex.BOPCGKM.kmmc}</option>
        </c:forEach>
	</select>&nbsp;<font color="red">*</font>
	office号
	<select id="office" name="cpOfficeid" onchange="getcglmByoffice(this)">
   		<option value="">==请选择==</option>
   		<c:forEach items="${bopcgkmlist}" var="pz">
   			<option value="${pz.officeid}" ${pz.officeid eq param.cpOfficeid ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	工作号
	<input type="text" id="workno" name="workno" value="${param.workno }" class="input-text lh25" datatype="*"/>
	&nbsp;<font color="red">*</font>
</td>