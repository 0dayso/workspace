<%@ page language="java" pageEncoding="UTF-8"%>	
<td>
	出 票 员
	<select name="cpy" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${shyhbList}" var="pz">
   			<option value="${pz.bh}" ${pz.bh eq param.yh ? 'selected' : ''}>${pz.bh}</option>
   		</c:forEach>
   	</select>
	OFFICE号
	<select id="office" name="bsp_officeid1" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.officeid}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.officeid}</option>
   		</c:forEach>
   	</select>
	
	工作号
	<select name="bsp_workid" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.agent}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.agent}</option>
   		</c:forEach>
   	</select>
	打票机
	<select name="bsp_cpj" datatype="*" style="width:80px">
   		<option value="">==请选择==</option>
   		<c:forEach items="${pzList}" var="pz">
   			<option value="${pz.printno}" ${pz.sfmr eq '1' ? 'selected' : ''}>${pz.printno}</option>
   		</c:forEach>
   	</select>
</td>