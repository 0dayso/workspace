<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>前往支付页面</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body >
<c:set var="info">${vfn:urie(b2borderresponse.orderInfo.payInfo.airurl,'GBK')}</c:set>
<script type="text/javascript">
<c:if test="${fn:startsWith(info, 'http')}">
  	window.location.href="${info}"; 
</c:if>
</script>
<c:if test="${not fn:startsWith(info, 'http')}">
	${info}
</c:if>

<script type="text/javascript">
	  //https://mapi.alipay.com/gateway.do?body=信用支付&notify_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxySServlet&out_trade_no=6948161608148000&partner=2088301094248164&payment_type=1&return_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxyCServlet&seller_email=cralipay@airchina.com&service=create_direct_pay_by_user&sign=4cc9ce227226e7263c0b35cd1e0b74e3&sign_type=MD5&subject=国航电子客票&total_fee=820.0 
	  //https://mapi.alipay.com/gateway.do?body=%D0%C5%D3%C3%D6%A7%B8%B6&notify_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxySServlet&out_trade_no=6948161608148000&partner=2088301094248164&payment_type=1&return_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxyCServlet&seller_email=cralipay@airchina.com&service=create_direct_pay_by_user&sign=4cc9ce227226e7263c0b35cd1e0b74e3&sign_type=MD5&subject=%B9%FA%BA%BD%B5%E7%D7%D3%BF%CD%C6%B1&total_fee=820.0 
</script>


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