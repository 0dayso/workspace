<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
	<title>Q信息日志</title>
	<style type="text/css">
		a{
			text-decoration: none;
		}
	</style>
</head>
<body>
	<c:if test="${not empty page.list}">
	<multipage:pone page="${ctx}/vedsb/qxxgl/bqinfo/showLog" actionFormName="page" var="surl"></multipage:pone>
	    ${surl}
		<display:table id="vc" name="page.list" class="list_table" style="width:100%" decorator="org.displaytag.decorator.TotalTableDecorator">
	        <display:column title="序号">${vc_rowNum }</display:column>
		    <display:column property="ckDatetime" title="查看时间" style="text-align:center;"/>
		    <display:column title="操作方式" style="text-align:center;">
		    	<c:if test="${vc.type eq '0' }">
		    		查看
		    	</c:if>
		    	<c:if test="${vc.type eq '1' }">
		    		处理
		    	</c:if>
		    	<c:if test="${vc.type eq '2' }">
		    		处理完成
		    	</c:if>
		    </display:column>
			<display:column property="by1" title="日志详情" style="text-align:center;"/>
			<display:column title="操作人" style="text-align:center;">${vc.yhbh}/${vc.ex.YHBH.xm}</display:column>
			<display:column title="操作部门" style="text-align:center;">${vc.ex.YHBH.bmmc}</display:column>
		    <display:column property="shbh" title="商户编号" style="text-align:center;"/>
		 </display:table>
		${surl}
	</c:if>
	<c:if test="${empty page.list}">
		<span>没有找到相关数据</span>
	</c:if>
</body>
</html>