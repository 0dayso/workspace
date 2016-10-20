<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>追位基础设置</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
function saveHbydmx(){
	var ydid = "${param.id }";
	var clbz = $("#clbz").val();
	$.ajax({
		type:"post",
		url:"${ctx}/vedsb/jphbyd/jphbyd/jphbydmxEditSave?ydid="+ydid+"&clbz="+clbz,
		success:function(result){
			if("1" == result){
				layer.msg("航班异动处理成功！",2,1);
			}else{
				layer.msg("航班异动处理失败！",2,0);
			}
			parent.location.reload();
		}
	})
}
//关闭弹出层
function closeHbydmx(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
</script>
</head>
<body>
<div class="container clear">
	<form class="jqtransform" id="hbydmxForm" method="POST">
	<input type="hidden" name="callback" value="parent.refresh()"/>
	<input type="hidden" name="closeDiv" value="true"/>
	<input type="hidden" name="ydid" value="${param.id }">
	<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0">	
		<tr>
		  	<td>
		  		处理说明:<textarea id="clbz" name="clbz" rows="3" cols="40" style="resize: none;" ></textarea>
		  	</td>
	  	</tr>
	  	<tr>
			<td colspan="2" align="center">
				<input type="button" onclick="saveHbydmx()" class="ext_btn ext_btn_submit" value="确定">&nbsp;
				<input type="button" onclick="closeHbydmx()" class="ext_btn ext_btn_error" value="取消">
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>
