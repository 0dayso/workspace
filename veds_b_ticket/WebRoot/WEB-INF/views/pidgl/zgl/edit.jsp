<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css"
			rel="stylesheet" />
		<script
			src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}"
			type="text/javascript"></script>

		<script type="text/javascript">
			var validator;
			function save(){
				validator.submitForm(false);
			}
			function shutwin(){
				var index=parent.layer.getFrameIndex();
				parent.layer.close(index);
				} 
			$(function(){
				validator = $("#zgl").Validform({
					tiptype:3
				});
				
			})
</script>
	</head>
	<body>
		<div class="container">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">用户组</b>
					</div>
					<div class="box_center">
						<form action="${ctx}/vedsb/pidgl/zgl/save" class="jqtransform" id="zgl"method="POST">
							<input type="hidden" name="turningUrl" value="${ctx}/vedsb/pidgl/zgl/viewlist?lx=${param.lx}"/>
							<input type="hidden" name="callback" value="parent.refresh()"/>
							<input type="hidden" name="closeDiv" value="true"/>
							<input type="hidden" name="${empty entity.yhzbh ? '':'yhzbh'}" value="${empty entity.yhzbh ? '':entity.yhzbh}"/>
							<input type="hidden" name="method" value="${param.method}">
							<table class="form_table pt15 pb15" width="80%" border="0"cellpadding="0" cellspacing="0" size="40">
								<tr>
									<td class="td_right">组编号：</td>
									<td class="">
										<input type="text" datatype="*" name="yhzbh" value="${entity.yhzbh}" ${!empty entity.yhzbh ? 'disabled':'' } class="input-text lh30" size="40" />
										(说明：新增组后组编号等于商户编号+输入的组编号)
									</td>
								</tr>
								<tr>
									<td class="td_right">组名称：</td>
									<td>
										<input type="text" datatype="*" name="yhzmc" value="${entity.yhzmc }" class="input-text lh30" size="40" />
									</td>
								</tr>
								<tr>
									<td class="td_right">PID组编号：</td>
									<td>
										<input type="text" datatype="*" name="pidzbh" value="${entity.pidzbh }" class="input-text lh30" size="40" />
									</td>
								</tr>
								<tr>
									<td class="td_right">PID编号：</td>
									<td>
										<input type="text" datatype="*" name="pidbh" value="${entity.pidbh }" class="input-text lh30" size="40" />
									</td>
								</tr>
								<tr>
									<td class="td_right">&nbsp;</td>
									<td class="">
										<input type="button" name="button" onclick="save()"class="ext_btn ext_btn_submit" value="保存">
										<input type="button" name="button" onclick="shutwin()"class="ext_btn ext_btn_submit" value="取消">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>
