<%@ page language="java" pageEncoding="UTF-8"%>
<!--  100143 BOP 德付通  -->
<td>
	<c:forEach items="${bopcgkmlist}" var="pz" varStatus="s">
		<c:if test="${s.index eq 0}">
			<input type="hidden" name="cgkm" id="cgkmhidden" value="${pz.bopcgkm}"/>
		</c:if>
			<input type="hidden" id="${pz.officeid}_index" value="${pz.bopcgkm}"/>
	</c:forEach>
	出票员
	 <select name="cpy" datatype="*" style="width:80px">
   			<option value="">==请选择==</option>
   			<c:forEach items="${shyhbList}" var="shyhb">
   				<option value="${shyhb.bh}" ${shyhb.bh eq BUSER.bh ? 'selected' : ''}>${shyhb.bh}</option>
   			</c:forEach>
   		</select>
	OFFICE号
	<select name="officeid" datatype="*" style="width:80px" onchange="getcglmByoffice(this)">
   		<c:forEach items="${bopcgkmlist}" var="pz">
   				<option value="${pz.officeid}">${pz.officeid}</option>
   		</c:forEach>
   	</select>
	采购科目
	 <select name="cgkms" id="cgkm" class="select" style="width:80px;" datatype="*" disabled="disabled">
       <c:forEach items="${bopcgkmlist}" var="one">
          		<option value="${one.bopcgkm}" >${one.ex.BOPCGKM.kmmc}</option>
       </c:forEach>
	</select>
</td>
