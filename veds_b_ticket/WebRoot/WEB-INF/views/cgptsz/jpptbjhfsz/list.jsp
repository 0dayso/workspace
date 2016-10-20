<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>平台后返设置</title>
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
	var validator;
	function toSave(){
		var check = "";
		$("input[name='pthfds']").each(function(){
			if($(this).val() < 0 || $(this).val() > 100) {
				alert("后返范围在0%到100%之间");
				check = "false";
			}
		})
		$("input[name='pthfje']").each(function(){
			if ($(this).val() < 0 || $(this).val() > 9999) {
				alert("后返金额在0到9999之间");
				check = "false";
			}
		}) 
		if (check == "") {
			validator.submitForm(false);
		}
	}
	
	var ii;
	$(function(){
		validator = $("#hfszForm").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(transport){
				layer.close(ii);
				if(transport.status=="0"){
			   		location.reload();
				}else{
				   alert(transport.result);
				}
			  },
			beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交;
				ii = layer.load('系统正在处理您的操作,请稍候!');
			}
		});
	})
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/cgptsz/list_title.jsp"%>
	<c:set var="ptzcbs" value="10100000,10100002,10100003,10100004,10100005,10100007,10100015,10100018"></c:set>
	<form action="${ctx}/vedsb/cgptsz/jpptbjhfsz/savePthf" id="hfszForm" method="post" >
		<table cellpadding="0" class="list_table" cellspacing="0" style="width: 800px">
			<tr>
				<td align="center">平台名称</td>
				<td align="center">平台标识</td>
				<td align="center">平台后返点数</td>
				<td align="center">平台后返金额</td>
				<td align="center">是否参与自动比价出票</td>
			</tr>
			<c:if test="${not empty hfList}">
				<c:forEach items="${hfList}" var ="pthf"  varStatus="i">
					<c:if test="${fn:contains(ptzcbs,pthf.id)}">
						<input type="hidden" name="ptid" value="${pthf.id}" />
						<c:set var="id" value="${pthf.id}"></c:set>
						<tr>
							<td align="center">
								<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz">
										<c:if test="${ptsz.id ne '100021' and  pthf.id eq ptsz.ywmc}">${ptsz.mc }</c:if>
								</c:forEach>
							</td>
							<td align="center">
								${pthf.id }
							</td>
							<td align="center">
								<input type="text" name="pthfds" value="${empty hfList ? '0' : not empty pthf.pthfds ? pthf.pthfds : '0'}"  style="width: 50px"/>&nbsp;(%)
							</td>
							<td align="center">
								<input  type="text" name="pthfje" value="${empty hfList ? '0' : not empty pthf.pthfje ? pthf.pthfje : '0'}"  style="width: 50px"/>&nbsp;(元)
							</td>
							<td align="center">
								<input  type="radio" name="sfkqbj_${id }"  value="0" ${empty pthf.sfkqbj or pthf.sfkqbj eq '0' ? 'checked' : '' }/>不参与
			    				<input  type="radio" name="sfkqbj_${id }" value="1" ${pthf.sfkqbj eq '1' ? 'checked' : '' }/>参与
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty hfList}">
				<c:forEach items="${ptzhList }" var ="pthf"  varStatus="i">
					<c:if test="${fn:contains(ptzcbs,pthf.ptzcbs)}">
						<input type="hidden" name="ptid" value="${pthf.ptzcbs}" />
						<c:set var="id" value="${pthf.ptzcbs}"></c:set>
						<tr>
							<td align="center">
								<c:forEach items="${vfc:getVeclassLb('100021')}" var="ptsz">
										<c:if test="${ptsz.id ne '100021' and  pthf.ptzcbs eq ptsz.ywmc}">${ptsz.mc }</c:if>
								</c:forEach>
							</td>
							<td align="center">
								${pthf.ptzcbs }
							</td>
							<td align="center">
								<input type="text" name="pthfds" value="0" style="width: 50px"/>&nbsp;(%)
							</td>
							<td align="center">
								<input  type="text" name="pthfje" value="0" style="width: 50px"/>&nbsp;(元)
							</td>
							<td align="center">
								<input  type="radio" name="sfkqbj_${id }"  value="0"  checked="checked" />不参与
			    				<input  type="radio" name="sfkqbj_${id }" value="1"  />参与
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</table>
	</form>
	<div style="width: 800px;text-align: center;"><input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();"></div>
</body>
</html>