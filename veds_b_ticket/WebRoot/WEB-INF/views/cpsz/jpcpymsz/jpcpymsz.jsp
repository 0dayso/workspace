<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>出票页面参数设置</title>
<!-- 取部门 --> 
<c:remove var="cgdeptname"/>
<c:if test="${not empty csList.CSZ210227}">
<MD:dept var="cgdept" bh="${csList.CSZ210227}"></MD:dept>
<c:set var="cgdeptname" value="${cgdept[0]}"/>
</c:if>

<script type="text/javascript">
	function closeLayer(){
		window.close();
	}
	
	function  toSave(){
	    var url="${ctx}/vedsb/cpsz/jpcpymsz/jpCpymSzSave";
	    $.ajax({
	    		type:"post",
	    		data:$("#listForm").serialize(),
	  			url:url,
				success:function(result){
					if("1" == result){
						layer.msg("保存成功！",2,1);
					}else{
						layer.msg("保存失败！",2,1);
					}
				}
     	     });
	}
	//CPS淘宝 联系人信息拼接
	function linkNxr(){
	    var  nxr=$("#lxr").val()+"||"+$("#lxrsj").val()+"||"+$("#lxremail").val();
		$("#tbNxr").val(nxr);
	}
	
	$(function () {
		$("input[name='cgXeYhbh']").each(function(){
			$(this).autocompleteDynamic('shyhb');
		})
	})
	
	//解除淘宝余额不足限制状态
	function tbunfreeze() {
		var url = "${ctx}/vedsb/cpsz/jpcpymsz/tbunfreeze";
		$.ajax({
			url : url,
			type : "post",
			success : function(result) {
				if ("1" == result) {
					layer.msg("解除余额不足限制成功！",2,1);
				} else {
					layer.msg(result,2,1);
				}
			}
		})
	}
</script>	
</head>
<body  onLoad="initPage();" bgcolor="#f2f2f2">
	<div id="titlePage"><%@include file="jpcpymsz_title.jsp"%></div>
	<form action="${ctx}/vedsb/cpsz/jpcpymsz/save" method="post" id="listForm" name="listForm">
		<input type="hidden" id="cgly" name="cgly" value="${empty param.cgly ? 'BSPET' : param.cgly}">
		<input type="hidden" name = "flag" id = "flag" value="${flag}">
		<c:choose>
			<c:when test="${cgly eq 'BOP'}"> <%@include file="lable/bop.jsp"%> </c:when>
			<c:when test="${cgly eq 'OP'}">  <%@include file="lable/op.jsp"%> </c:when>
			<c:when test="${cgly eq 'BPET'}"><%@include file="lable/bpet.jsp"%> </c:when>
			<c:when test="${cgly eq 'CZ'}">  <%@include file="lable/cps_gw.jsp"%> </c:when>
			<c:when test="${cgly eq 'TB'}">  <%@include file="lable/cps_tb.jsp"%> </c:when>
			<c:otherwise><%@include file="lable/bspet.jsp"%></c:otherwise>
		</c:choose>
	</form>
</body>
</html>