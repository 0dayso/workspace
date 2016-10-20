<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>支付方式对应</title>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<style type="text/css">
	.short1{
			width:100%;
		 	white-space:nowrap;
			overflow:hidden;
		 	text-overflow:ellipsis;
		}
	a {
		text-decoration:none;
	}
	.ptzh_right{
		text-align: right;
	}
	.ptzh_left{
		text-align: left;
	}
</style>
<script type="text/javascript">
	function toSave() {
		var url = "${ctx}/vedsb/cpsz/jpcpymsz/saveb2bzffs";
		$.ajax({
			url : url,
			type : "post",
			data : $("#zffsszForm").serialize(),
			success:function(result) {
				if ("1" == result) {
					layer.msg("保存成功！",2,1);
					location.reload();
				} else {
					layer.msg(result,2,1);
				}
			}
		})
	}
</script>
</head>
<body>
	<form action="${ctx}/vedsb/cpsz/jpcpymsz/saveb2bzffs" id="zffsszForm" method="post" >
		<input id="del" type="hidden" name="del" value=""/>
		<input  type="hidden" name="ywlx" value="2"/>
		<table cellpadding="0" class="list_table" cellspacing="0" style="width: 500px" id="insertRow1">
			<tr>
				<td align="center">序号</td>
				<td align="center">航司B2B支付方式</td>
				<td align="center">系统采购科目</td>
			</tr>
			<c:forEach items="${zfList }" var ="b2bzf"  varStatus="i">
				<tr>
					<input type="hidden" name="id" value="${b2bzf.id}"/>
					<td align="center" class="sxh">${i.count}</td>
					<td align="center">
						${b2bzf.zffsB2b }
					</td>
					<td align="center">
						<select name="zfkmXt" >
							<option value="">==请选择==</option>
							<c:forEach items="${zfkmList}" var="zfkm">
									<option value="${zfkm.kmbh}" ${b2bzf.zfkmXt eq zfkm.kmbh ? 'selected' : '' }>${zfkm.kmmc}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<div style="width: 500px;text-align: center;"><input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave()"></div>
</body>
</html>