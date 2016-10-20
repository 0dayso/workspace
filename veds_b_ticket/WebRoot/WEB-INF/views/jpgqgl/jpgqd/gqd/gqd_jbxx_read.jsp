<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
	<c:when test="${jpgqd.wdpt eq '10100010'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/qunaerLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100011'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/taobaoLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100012'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/kunxunLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100050'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/xiechengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100024'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/tongchengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100001'}">
		<c:set var="wdlogo" value="${ctx}/static/images/wdimages/piaomengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpgqd.wdpt eq '10100002'}">
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
		<td style="margin-left: 5px">
			<c:if test="${jpkhdd.SFZDD eq '1'}">
		  		<span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zj.png"></span>
		  	</c:if>
		  	<c:if test="${jpkhdd.SFZDD ne '1'}">
		  		<span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zd.png"></span>
		  	</c:if>
		  	&nbsp;改签单号：${jpgqd.gqdh}&nbsp;原订单编号：${jpgqd.ddbh}&nbsp;外部单号：${vfn:cut(jpgqd.wbdh,12)}&nbsp;
	  	</td>
	</tr>
	<tr>
		<td style="margin-left: 5px">
			<span>
				PNR:${jpgqd.xsPnrNo}
			</span>
			
			<c:if test="${not empty jpgqd.gqXsPnrNo and jpgqd.xsPnrNo ne jpgqd.gqXsPnrNo}">
				/<span style="color: red">${jpgqd.gqXsPnrNo}</span>
			</c:if>
			<span>
				大编码:${jpgqd.xsHkgsPnr}
			</span>
			<c:if test="${not empty jpgqd.gqXsHkgsPnr and jpgqd.xsHkgsPnr ne jpgqd.gqXsHkgsPnr}">
				/<span style="color: red">${jpgqd.gqXsHkgsPnr}</span>
			</c:if>
			<span>
				&nbsp;改签状态：
				<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
		 			<c:if test="${oneZt.value eq jpgqd.gqzt}">
		 				${oneZt.mc}
		 			</c:if>
          		</c:forEach>
			</span>
		</td>
	</tr>
	<tr>
		<td style="margin-left: 5px">
			<span>
				&nbsp;申请部门：${jpgqd.ddbm}
			</span>
			<span>
				&nbsp;申请人：${jpgqd.ddyh}
			</span>
			<span>
				&nbsp;申请时间：${vfn:format(jpgqd.ddsj,'yyyy-MM-dd HH:mm:ss')}
			</span>
			<span>
				&nbsp;采购改签状态：${jpgqd.gqCgzt}
			</span>
		</td>
	</tr>
	<tr>
		<td align="center" style="font-size:14px; font-weight:bold; color:#1195db">${jpgqd.ex.WDID.wdmc}</td>
	</tr>
</table>
