<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 100145 OP 平台</option> -->
<td>
	出票员
	 <select name="cpy" datatype="*" style="width:80px">
   			<option value="">==请选择==</option>
   			<c:forEach items="${shyhbList}" var="shyhb">
   				<option value="${shyhb.bh}" ${shyhb.bh eq BUSER.bh ? 'selected' : ''}>${shyhb.bh}</option>
   			</c:forEach>
   	</select>
	采购单位
	<input type="text" name="cgdw" value="" datatype="*" style="width:80px" class="input1 max-length-30" maxlength="30">
	采购科目
	 <select name="cgkm" id="cgkm" class="select" style="width:80px;" datatype="*">
       		<c:forEach items="${zfkms}" var="one">
          		<option value="${one.kmbh }" ${param.cgkm eq one.kmbh ? 'selected':''}>${one.kmmc }</option> 
       		</c:forEach>
	</select>
	采购金额
	<input type="text" name="cgje" value="" datatype="*" style="width:30px" class="input1 max-length-30" maxlength="30">
</td>
