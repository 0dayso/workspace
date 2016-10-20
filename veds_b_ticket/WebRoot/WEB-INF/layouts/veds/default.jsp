<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>超级电商平台：<sitemesh:title/></title>
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" href="${ctx}/static/css/common.css">
<link rel="stylesheet" href="${ctx}/static/css/main.css">
<!-- jQuery  -->
<script type="text/javascript" src="${ctx}/static/core/jquery/jquery.min.js?ctx=${ctx}"></script>
<!-- 日期选择控件  -->
<script type="text/javascript" src="${ctx}/static/core/My97DatePicker/WdatePicker.js"></script>
<!-- 系统自定义工具 -->
<script type="text/javascript" src="${ctx}/static/core/js/common.js?_=<%=(new Date()).getTime()%>"></script>

<!-- 需要Ajax提交的表单 -->
<script type="text/javascript" src="${ctx}/static/core/jquery/jquery.form.js"></script>


<!-- 需要弹出层时用到 -->
<script type="text/javascript" src="${ctx}/static/core/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/static/core/layer/extend/layer.ext.js"></script>

<!-- 模板引擎 -->
<script type="text/javascript" src="${ctx}/static/core/laytpl/laytpl.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="${ctx}/static/core/laypage/laypage.js"></script>
<!-- 提供table的列可以通过鼠标移动宽度 -->
<script type="text/javascript" src="${ctx}/static/core/jquery/colResizable-1.3.min.js"></script>


<!-- 国际国内城市数据 -->
<script type="text/javascript" src="${ctx}/static/core/js/jquery.autocomplete.js"></script>
<!-- 机票系统公共js -->
<script type="text/javascript" src="${ctx}/static/js/vedsjp.js"></script>
<sitemesh:head />
</head>
<body>
	<c:if test="${!empty ERRORMESSAGE }">
	<div ondblclick="$('#ERRORMESSAGE_DETAIL').toggle('slow')">${ERRORMESSAGE }</div>
	<div id="ERRORMESSAGE_DETAIL" style="display: none"><pre>${ERRORMESSAGE_DETAIL }</pre></div>
	</c:if>
	<input type="hidden" id="turning"  value="${param.turning }">
	<sitemesh:body />
	<%@ include file="/WEB-INF/layouts/veds/footer.jsp"%>
</body>
</html>