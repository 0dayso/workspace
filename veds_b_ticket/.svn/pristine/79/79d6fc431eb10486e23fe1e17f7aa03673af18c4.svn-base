<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>前往支付页面</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body >

<c:set var="info">${vfn:urie(b2borderresponse.orderInfo.payInfo.airurl,'UTF-8')}</c:set>
<script type="text/javascript">
<c:if test="${fn:startsWith(info, 'http')}">
  	window.location.href="${info}"; 
</c:if>
</script>
<c:if test="${not fn:startsWith(info, 'http')}">
	${info}
</c:if>
<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;height: 100%;overflow: visible;text-align: center;">
  <tbody>
    <tr>
      <td>
        <table width="600"  border="0"  class="loginboxzdcp"  style="margin: 0px auto;text-align: left;">
         <tr>
			<td>
				<h3 class="red">航空公司出票提示：</h3>
			</td>
	    </tr>
	    <tr>
			<td>
				正在前往支付页面请稍后》》》》》》》》》》》》 
		    </td>
        </tr>
        </table>
      </td>
    </tr>
  </tbody>
</table>
 </body>
</html>