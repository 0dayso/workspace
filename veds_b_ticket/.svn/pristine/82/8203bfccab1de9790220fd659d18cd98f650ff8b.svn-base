<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 98%;margin-bottom: 2px; background:#f1f1f1">
	<tr>
     	<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/kh.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">乘机人信息</span>
		</td>
		<td style="background:#efefef;">
			<table class="t_tab" cellpadding="0" cellspacing="0" style="width: 100%;height:100%;margin-top: 0px;">
				<tr>
				  	<th>乘机人</th>
					<th>证件号码</th>
				   	<th>票号 </th>
				   	<th>票证类型</th>		
			 	</tr>
				<c:forEach items="${jpGqdCjrMap}" var="jpGqdCjr" varStatus="i">
					<c:set var="cjrlx" value=""></c:set>
						<c:choose>
							<c:when test="${jpGqdCjrMx.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:when>
							<c:when test="${jpGqdCjrMx.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_et.gif"></c:set>
							</c:when>
							<c:when test="${jpGqdCjrMx.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_ye.gif"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:otherwise>	
						</c:choose>
					<tr>
				 		<c:set var="cjrjps" value="${fn:length(jpGqdCjr.value)}"></c:set>
						<c:forEach items="${jpGqdCjr.value}" var="jpGqdCjrMx" varStatus="j">
				 			<c:if test="${j.index eq 0}">
						 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjr.key}<img src="${cjrlx}"/></td>
						 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjrMx.zjhm}</td>
							</c:if>
							<td align="center">
								${jpGqdCjrMx.tkno}
							</td>
					 		<td>
					 		</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>		
</table>