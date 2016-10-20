<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>采购异常处理</title>
		<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
    	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
		<script type="text/javascript">
			var validator;
			$(function(){
				validator = $("#checkForm").Validform({
						tiptype:3
					});
			});
			//关闭当前弹窗
    		function closepj(){
    			var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
    		}
    		function tosave(){
    			validator.submitForm(false);
    		}
		</script>
	</head>
	<body style="background-color:rgb(243, 247, 253);">
		<form action="${ctx}/vedsb/cgdzbb/jpcgdzyc/cgycClSave" id="checkForm" name="saveForm" method="post">
			 <input type="hidden" name="callback"  value="parent.refresh()" />
             <input type="hidden" name="closeDiv" value="true"/>
			<input type="hidden" name="id" value="${param.id}">
			<table class="table01" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>处理说明：</td>
					<td>
						<TEXTAREA cols="45" rows="4" name="clsm" datatype="s"></TEXTAREA>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						 <input type="button" name="button" onclick="tosave();" class="ext_btn ext_btn_submit" value="保存">  &nbsp;
						 <input type="button" name="button" onclick="closepj()" class="ext_btn" value="关闭">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>