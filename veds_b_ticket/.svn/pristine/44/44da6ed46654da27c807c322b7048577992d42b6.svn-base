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
	function edit(id) {
		var url ="${ctx}/vedsb/cpsz/jpcpymsz/preEditOffice_" + id;
	    $.layer({
			type: 2,
			title: ['<b>编辑配置</b>'],
			area: ['400px', '260px'],
			iframe: {src: url}
	    });
	}
	function add() {
		var url ="${ctx}/vedsb/cpsz/jpcpymsz/viewbopoffice_add";
	    $.layer({
			type: 2,
			title: ['<b>新增配置</b>'],
			area: ['400px', '260px'],
			iframe: {src: url}
	    });
	}
</script>
</head>
<body>
		<input id="del" type="hidden" name="del" value=""/>
		<input  type="hidden" name="ywlx" value="2"/>
		<input  type="button" class="ext_btn ext_btn_submit"  value="新增"  onclick="add();"/>
		<table cellpadding="0" class="list_table" cellspacing="0" style="width: 800px" id="insertRow1">
			<tr>
				<td align="center">操作</td>
				<td align="center">OFFICE号</td>
				<td align="center">是否支持BOP出票</td>
				<td align="center">发布商户</td>
				<td align="center">采购科目</td>
				<td align="center">修改用户</td>
				<td align="center">创建时间</td>
				<td align="center">修改时间</td>
			</tr>
			<c:forEach items="${officelist}" var ="ofc"  varStatus="i">
				<tr>
					<td align="center"><a href="javascript:edit('${ofc.id}')">编辑</a></td>
					<td align="center">${ofc.officeid}</td>
					<td align="center">${ofc.sfbopcp eq '1' ? '是' : '否'}</td>
					<td align="center">${ofc.shbh}</td>
					<td align="center">${ofc.ex.BOPCGKM.kmmc}</td>
					<td align="center">${ofc.xgyh}</td>
					<td align="center">${vfn:format(ofc.cjsj,'')}</td>
					<td align="center">${vfn:format(ofc.xgsj,'')}</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>