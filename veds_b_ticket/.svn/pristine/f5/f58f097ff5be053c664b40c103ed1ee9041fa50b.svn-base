<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
 	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
   <script type="text/javascript">
   		var validator;
   		$(function(){
   			if('${bcdDetail}' == '1'){
   				$("#bclx").attr("disabled",true);
   				$("#bcje").attr("disabled",true);
   				$("#bcsm").attr("disabled",true);
   				$("#save").hide();
   				$("#savesh").hide();
   			}
   			if($("#ddbh").val() != '' && $("#ddbh").val() != null){
   				$("#jpbcd").attr("action","${ctx}/vedsb/jpbcd/jpbcd/saveBcd");
   			}
   			 validator = $("#jpbcd").Validform({
				tiptype:3
			 });
   		});
   		
   		function closewindow(){
   			var index=parent.layer.getFrameIndex();
			parent.layer.close(index);
   		}
   		
   		function saveJpbcd(){
   			validator.submitForm(false);
   		}
   		
   		function shJpbcd(){
   			$("#jpbcd").attr("action","${ctx}/vedsb/jpbcd/jpbcd/shBcd");
   			validator.submitForm(false);
   		}
   </script>
   <title></title>
 </head>
  <body>
  <form action="${ctx}/vedsb/jpbcd/jpbcd/save" id="jpbcd" method="post">
  	  <input type="hidden" id="id" name="id" value="${bcd.id}"/>
  	  <input type="hidden" name="callback"  value="parent.refresh()" />
      <input type="hidden" name="closeDiv" value="true"/>
      <input type="hidden" name="ref" value="true"/>
      <input type="hidden" name="ddbh" value="${ddbh}" id="ddbh">
      <input type="hidden" name="ywlxs" value="${ywlxs}" id="ddbh">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<td colspan="2">
				补差类型:&nbsp;<select name="bclx" id="bclx" style="width: 130px;height: 24px;">
								<option value="">==请选择==</option>
								<c:forEach items="${list}" var="bcdlx">
								<option value="${bcdlx.bh}" ${bcd.bclx eq bcdlx.bh ? 'selected' : ''}>${bcdlx.mc}</option>
								</c:forEach >
				              </select><font style="color: red">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;补差金额:<input type="text" name="bcje" value="${bcd.bcje}" style="width: 40px;height: 20px;" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" id="bcje">元<font style="color: red">*</font>
			</td>
		</tr>
		<tr>
			<td>补差事由:</td><td><textarea rows="5" cols="32" datatype="s" name="bcsm" id="bcsm">${bcd.bcsm}</textarea><font style="color: red">*</font></td>
		</tr>
		<tr>
			<td style="text-align: center;" colspan="2">
				<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="saveJpbcd();" id="save"/> &nbsp;
				<c:if test="${sh eq '1'}">
					<input type="button" value="保存并审核" class="ext_btn ext_btn_submit" onclick="shJpbcd();" id="savesh"/> &nbsp;
				</c:if>
				<input type="button" value="关闭" class="ext_btn" onclick="closewindow();"/>
			</td>
		</tr>
	</table>
	</form>
  </body>
</html>
