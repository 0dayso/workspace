<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
	<c:when test="${jpKhdd.wdpt eq '10100010'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/qunaerLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100011'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/taobaoLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100012'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/kunxunLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100050'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/xiechengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100024'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/tongchengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100001'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/piaomengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpKhdd.wdpt eq '10100002'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/jingriLogo.png"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="wdlogo" value="0"></c:set>
	</c:otherwise>	
</c:choose>
<%-- 10100010 去哪儿;10100011 淘宝;10100012 酷讯;10100024 同程;10100050 携程--%>
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-bottom: 2px; background:#f1f1f1">
	<tr>
		<td rowspan="3" style="width:10%;background:#efefef;height:80px;text-align:center;">
			<img width="100%" height="78%" src="${wdlogo}">
		</td>
	</tr>
	<tr>
	    <td>
			<span>PNR：${jpKhdd.xsPnrNo}&nbsp;</span> 
			<span>订单编号：${jpKhdd.ddbh}&nbsp;</span>
			<span>外部单号：<input type="text" name="wbdh" value="${jpKhdd.wbdh} "/></span>
	    </td>
	</tr>
	<tr>
	    <td>
	    	<span>预订部门：${jpKhdd.ddbm}&nbsp;</span> 
	    	<span>预订员：${jpKhdd.ddyh}&nbsp;</span>
	    	<span>预订时间：${vfn:format(jpKhdd.ddsj,'yyyy-MM-dd HH:mm:ss')}</span>
	    </td>
	</tr>
	<tr>
		<td align="center">${jpKhdd.ex.WDID.wdmc}</td>
	</tr>
</table>