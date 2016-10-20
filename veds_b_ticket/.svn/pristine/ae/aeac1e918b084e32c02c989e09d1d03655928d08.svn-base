<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<c:if test="${param.ref eq 'true' && (not empty param.callback || empty param.submitForm)}">
  <script type="text/javascript">
   try{
    	opener.window.location.reload();
    }catch(e){
     try{
    	window.parent.location.reload();
     }catch(e1){}
    }
  </script>
</c:if>
<!-- 提交父页面指定的form -->
<c:if test="${empty param.callback and not empty param.submitForm}">
  <script type="text/javascript">
  	try{
		window.opener.document.${param.submitForm}.submit();
	}catch(e){
		try{
	    	window.parent.document.${param.submitForm}.submit();
	    }catch(e1){}
	}
  </script>
</c:if>
<c:if test="${not empty param.callback}">
	<script type="text/javascript">
			try{${param.callback};}catch(e){};
	</script>
</c:if>
<c:if test="${param.closeDiv eq 'true'}">
	<script type="text/javascript">
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	</script>
</c:if>

<c:if test="${param.close eq 'true'}">
  <script type="text/javascript">
  	<c:if test="${not empty param.callback}">
		try{window.opener.${param.callback}();}catch(e){};
	</c:if>
	window.close();
  </script>
</c:if>
<c:if test="${not empty param.turningUrl }">
<form action="${param.turningUrl}" method="POST" id="turningForm">
<input type="hidden" name="turning" id="turning" value="1">
<c:forEach items="${SEARCH_PARAM}" var="searchp">
<c:forEach items="${searchp.value }" var="valuep">
<input type="hidden" value="${valuep }" name="${searchp.key }">
</c:forEach>
</c:forEach>
</form>
<script>
document.getElementById("turningForm").submit();
</script>
</c:if>
<style type="text/css">
<!--
.Fback{
	border:1px solid #cc3e00;
	background:#fff;
	padding:10px;
	width:98%;
	text-align:left;
}
.Fback li{
	font-size:14px;
	
}
.Fback li a:link{
	font-size:14px;
	color:#cc3e00;
	text-decoration:underline;
}
.Fback li a:visited{
	font-size:14px;
	color:#cc3e00;
	text-decoration:underline;
}
.Fback li a:hover{
	font-size:14px;
	color:#000;
	text-decoration:none;
}
.Fback li a:active{
	font-size:14px;
	color:#0000;
}
.Fback h5{
	font-size:18px;
	font-weight:bold;
	color:#cc3e00;
}
-->
</style>
</head>
<body>
<center>
<br>
<div class="Fback">
  <div id="Ftext">
    <ul>
      <h5> 友情提醒：</h5>
      <li> 正在处理中请稍后...... </li>
      <li> 如果页面没有自动返回请点击
        <c:if test="${not empty p_surl}">
        <a href="${p_surl }">这里</a>
        </c:if>
        <c:if test="${ empty p_surl}">
        <a href="javascript:history.go(-1)">这里</a>
        </c:if>
      </li>
    </ul>
    <br/>
    <br/>
  </div>
</div>
</center>
</body>
</html>