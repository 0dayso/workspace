<%@page language="java" pageEncoding="UTF-8"%>
<div class='div_1'>
<tr id="tr_${param.xh}">
	<td>起始单号：</td>
	<td>
	<input type="text" name="startno" id="startno_${param.xh}"  value="${param.startno}"
	 datatype="integer,maxvalue,minvalue"  maxvalue="${param.endno}" minvalue="${param.startno}" onblur="calcnum(this)">
	<font color="red">*</font>
	 <div class="Validform_checktip"></div>
	</td>
	<td>终止单号：</td>
	<td>
	<input type="text" name="endno" id="endno_${param.xh}"  value="${param.endno}"  
		datatype="integer,maxvalue,minvalue" maxvalue="${param.endno}" minvalue="${param.startno}" onblur="calcnum(this)">
		<font color="red">*</font>
		<div class="Validform_checktip"></div>
	</td>
	<td>数&nbsp; 量：</td>
	<td>
	<input type="text" class="input1 required  max-length-5 validate-digits" name="ffsl"
		id="ffsl_${param.xh}" value="${param.ffsl}" size="2" readOnly="true">
		<input type="hidden" name="pztype" id="pztype_${param.xh}" value="${param.pztype}"/>
		<input type="hidden" name="officeid" id="officeid_${param.xh}" value="${param.officeid}"/>
		<input type="hidden" name="id" id="id_${param.xh}" value="${param.id}"/>
	</td>
</tr>
</div>