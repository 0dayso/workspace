<%@page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav_junior">
	<div class="nav_junior_con">
		<p>
			<a href="/asms/yhdz/wddz/dbreult/dbreult_bb.jsp" class="${empty param.jglx or param.jglx eq '3' ? 'nonce' : ''}"> 未到账 </a>
			<a href="/asms/yhdz/wddz/dbreult/dbreult_bb_ld.jsp?jglx=6" class="${param.jglx eq '6' ? 'nonce' : ''}"> 漏 单 </a>
		</p>
	</div>
</div>