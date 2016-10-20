<%@ page language="java" pageEncoding="UTF-8"%>
<!--  100141 BSP BSPET  -->
<td>
	出票员
	<select name="cpy" datatype="*" style="width:80px">
		<option value="">==请选择==</option>
		<c:forEach items="${shyhbList}" var="shyhb">
			<option value="${shyhb.bh}" ${shyhb.bh eq BUSER.bh ? 'selected' : ''}>${shyhb.bh}</option>
		</c:forEach>
	</select>
	OFFICE号
	<select name="officeid" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.officeid}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	工作号
	<select name="workid" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.agent}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.agent}</option>
   		</c:forEach>
   	</select>
	打票机
	<select name="cpj" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.printno}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.printno}</option>
   		</c:forEach>
   	</select>
</td>
	