<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>B2B出票</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ticket_cp.css"/>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
</head>
<body>
	<div id="b2b_policy_div" class="loginBox" > </div>
	<div id="b2b_info_div" class="loginBox" > </div>
	

<script type="text/javascript">
$(function(){
	indexB2bPage("${param.ddbh}",$("#b2b_policy_div"));
});
</script>	
	
</body>
</html>