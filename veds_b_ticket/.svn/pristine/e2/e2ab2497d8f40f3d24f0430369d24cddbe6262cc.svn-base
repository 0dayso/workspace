<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
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
	function savePz() {
		var url = "${ctx}/vedsb/cpsz/jpcpymsz/savePz";
		$.ajax({
			url : url,
			type : "post",
			data : $("#pzForm").serialize(),
			success : function(result) {
				if ("1" == result) {
					layer.msg("保存成功！",2,1);
				} else {
					layer.msg(result,2,1);
				}
			}
		})
	}
</script>
</head>
<body>
	<form action="${ctx}/vedsb/cpsz/jpcpymsz/savePz" id="pzForm">
		<input  type="hidden" name="id" value="${jo.id}"/>
		<input  type="hidden" name="officeid" value="${jo.officeid}"/>
		<input  type="hidden" name="cjsj" value="${vfn:format(jo.cjsj,'')}"/>
		<table cellpadding="0" class="list_table" cellspacing="0" style="width: 400px" id="insertRow1">
			<tr>
				<td align="right" width="120px">OFFICE：</td>
				<td align="left">${jo.officeid}</td>
			</tr>
			<tr>
				<td align="right">是否支持BOP出票：</td>
				<td align="left">
					<input type="radio" name="sfbopcp" value="1" ${jo.sfbopcp eq '1' ? 'checked' : ''} onclick="$('#bopmm').show()"/>是
					<input type="radio" name="sfbopcp" value="0" ${jo.sfbopcp ne '1' ? 'checked' : ''} onclick="$('#bopmm').hide()"/>否
				</td>
			</tr>
			<tr id="bopmm" style="display:  ${jo.sfbopcp eq '1' and jo.sfbopcp eq '1' ? '' : 'none'}">
				<td align="right">BOP支付密码：</td>
				<td align="left"><input type="password" name="bopmm"  style="width: 100px" value="${jo.bopmm}"/></td>
			</tr>
			
			<tr>
				<td align="right">系统采购科目：</td>
				<td align="left">
					<select name="bopcgkm" >
							<option value="">==请选择==</option>
							<c:forEach items="${zfkmList}" var="zfkm">
									<option value="${zfkm.kmbh}" ${jo.bopcgkm eq zfkm.kmbh ? 'selected' : '' }>${zfkm.kmmc}</option>
							</c:forEach>
						</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savePz()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>