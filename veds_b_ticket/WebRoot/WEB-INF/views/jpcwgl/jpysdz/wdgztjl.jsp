<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<tr>
	<c:if test="${index.count eq 1}">
		<td rowspan="${len}">
			<img src="${logo}" height="75" width="78"/>
		</td>
	</c:if>
	<td>${tb.ex.WDID.wdmc}</td>
	<td>
		<c:set var="dzztimg" value=""></c:set>
		<c:if test="${tb.dzZt eq '0'}">
			<c:set var="dzztimg" value="${ctx}/static/images/wdgzt/wdzn.png"></c:set>
		</c:if>
		<c:if test="${tb.dzZt eq '1'}">
			<c:set var="dzztimg" value="${ctx}/static/images/wdgzt/dzwwn.png"></c:set>
		</c:if>
		<c:if test="${tb.dzZt eq '2'}">
			<c:set var="dzztimg" value="${ctx}/static/images/wdgzt/dzcyn.png"></c:set>
		</c:if>
		<c:if test="${tb.dzZt eq '3'}">
			<c:set var="dzztimg" value="${ctx}/static/images/wdgzt/wdzld.png"></c:set>
		</c:if>
		<c:if test="${not empty dzztimg}">
			<img src="${dzztimg}"  onclick="towddz('${tb.dzrq}','${tb.wdpt}','${tb.wdid}','${tb.id}','${tb.ex.WDID.wdmc}')" style="cursor:pointer;"/>
		</c:if>
	</td>
	<td>${tb.ex.DZUSERID.xm}</td>
	<td>
		<c:set var="shzttimg" value=""></c:set>
		<c:if test="${tb.shZt eq '0'}">
			<c:set var="shzttimg" value="${ctx}/static/images/wdgzt/wdzn.png"></c:set>
		</c:if>
		<c:if test="${tb.shZt eq '1'}">
			<c:set var="shzttimg" value="${ctx}/static/images/wdgzt/dzwwn.png"></c:set>
		</c:if>
		<c:if test="${tb.shZt eq '2'}">
			<c:set var="shzttimg" value="${ctx}/static/images/wdgzt/dzcyn.png"></c:set>
		</c:if>
		<c:if test="${not empty shzttimg}">
			<img src="${shzttimg}"  onclick="toHlx('${tb.ex.WDID.wdmc}','${tb.wdid}','${tb.wdpt}')" style="cursor:pointer;" />
		</c:if>
	</td>
	<td>${tb.ex.SHUSERID.xm}</td>
	<%--<td><input type="button" value=" 查看 " class="blue_button" onclick="showlrbb('${tb.ex.WDID.wdmc}','${tb.wdid}')"></td> --%>
</tr>