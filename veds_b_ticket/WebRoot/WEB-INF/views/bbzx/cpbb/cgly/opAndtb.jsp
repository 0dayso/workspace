<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <td>
 	&nbsp;采购科目
 	<select name="cgkm" id="cgkm" class="select" style="width:100px;">
        <c:forEach items="${zfkms}" var="one">
           	<option value="${one.kmbh }" ${param.cgkm eq one.kmbh ? 'selected':''}>${one.kmmc }</option> 
        </c:forEach>
	</select>&nbsp;<font color="red">*</font>
 	外部单号
 	<input type="text" name="wbdh" value="${param.wbdh }" class="input-text lh25"/>
 	&nbsp;<font color="red">*</font> 
 </td>
 
	 
