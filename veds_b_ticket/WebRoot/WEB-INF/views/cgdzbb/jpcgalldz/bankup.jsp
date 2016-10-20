<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  </head>
  <body>
    <c:if test="${not empty error }">
    	<script type="text/javascript">
    		alert("${error}");
    	</script>
    </c:if>
  </body>
</html>
