<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">
function saveJpztjk(){
	var clbz = $("#clBz").val();
	$.ajax({
		type:"post",
		url:"${ctx}/vedsb/bbzx/jpztjk/jpztjkEditSave?tknos=${param.tknos }&clbz="+clbz,
		success:function(result){
			console.log(result);
			if("1" == result){
				layer.msg("办理成功！",2,1);
			}else{
				layer.msg("办理失败！",2,0);
			}
			parent.location.href="${ctx}/vedsb/bbzx/jpztjk/viewlist?kssj=${param.kssj }&jssj=${param.jssj }";
		}
	})
}
//关闭弹出层
function closeJpztjk(){
	var index=parent.layer.getFrameIndex();
	parent.layer.close(index);
}
</script>
</head>
<body>
<div class="container clear">
	<div class="box_border">
		<input type="hidden" name="closeDiv" value="true"/>
		<input type="hidden" name="tknos" value="${param.tknos }">
		<input type="hidden" name="kssj" value="${param.kssj }">
		<input type="hidden" name="jssj" value="${param.jssj }">
		<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0">	
			<tr>
				<td style="vertical-align: middle;">处理原因:</td>
			  	<td>
			  		<textarea id="clBz" name="clBz" rows="3" cols="40" style="resize: none;" ></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" onclick="saveJpztjk()" class="ext_btn ext_btn_submit" value="确定">&nbsp;
					<input type="button" onclick="closeJpztjk()" class="ext_btn ext_btn_error" value="取消">
				</td>
			</tr>
		</table>
    </div>
</div>
</body>
</html>