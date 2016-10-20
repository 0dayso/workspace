<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
    <%-- 去哪儿 --%>
	<c:when test="${jptpd.wdpt eq '10100010'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/qunaerLogo.png"></c:set>
	</c:when>
	<%-- 淘宝网 --%>
	<c:when test="${jptpd.wdpt eq '10100011'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/taobaoLogo.png"></c:set>
	</c:when>
	<%-- 酷讯旅游 --%>
	<c:when test="${jptpd.wdpt eq '10100012'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/kunxunLogo.png"></c:set>
	</c:when>
	<%-- 携程 --%>
	<c:when test="${jptpd.wdpt eq '10100050'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/xiechengLogo.png"></c:set>
	</c:when>
	<%-- 同程 --%>
	<c:when test="${jptpd.wdpt eq '10100024'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/tongchengLogo.png"></c:set>
	</c:when>
	<%-- 票盟 --%>
	<c:when test="${jptpd.wdpt eq '10100001'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/piaomengLogo.png"></c:set>
	</c:when>
	<%-- 今日 --%>
	<c:when test="${jptpd.wdpt eq '10100002'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/jingriLogo.png"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="srcs" value="0"></c:set>
	</c:otherwise>	
</c:choose>

<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width:100%;margin-bottom: 2px;background:#f1f1f1;">
	<tr>
	 	<c:if test="${not empty srcs and srcs ne '0'}">
		  	<td rowspan="${khdd.tjlx eq '3' ? '4': '3' }" style="width:10%;background:#efefef;height:80px;text-align:center;">
			 	 <img width="100%" height="78%" src="${srcs}" /></br>
		  	</td>
	  	</c:if>
		<td>
			<c:if test="${jptpd.sfzdd eq '1'}">
		  	 	<span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zj.png"></span>
		  	</c:if>
		  	<c:if test="${jptpd.sfzdd ne '1'}">
		  	    <span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zd.png"></span>
		  	</c:if>
		  	<span class="headtitle">退废单号：</span>
			<span class="red">${jptpd.tpdh}</span>
			<span class="headtitle">PNR：</span>
			<span class="red">
				<span class="red">${jptpd.xsPnrNo}</span><c:if test="${jptpd.xsPnrNo ne jptpd.cgPnrNo}"><font color="green">->${jptpd.cgPnrNo}</font></c:if>
			</span>
			<span class="headtitle">大编码：</span>
			<span class="red">${empty jptpd.xsHkgsPnr ? '无' : jptpd.xsHkgsPnr}</span>
			<span class="red">${jptpd.gngj eq '1' ? '国内' : '国际'}</span>
			<span class="headtitle">政策说明</span>
		  	 <span class="wb_td02">
				<span class="red">${jpkhdd.zcQd},${jpkhdd.zcLy}[投放ID：${jpkhdd.zcTfid}，返点${jpkhdd.zcFd}，留点${jpkhdd.zcLd}，留钱${jpkhdd.zcLq}，${jpkhdd.zcSfbj eq "0" ? '非比价政策' : '比价政策'}]</span>
			</span>
		</td>
	</tr>
	<tr>
		<td>
			<span class="headtitle">销售退废票状态：</span>
			<span class="red">
				<c:forEach items="${vfn:dictList('XSTFPZT')}" var="oneZt">
	 				<c:if test="${oneZt.value eq jptpd.xsTpzt}">
	 					${oneZt.mc}
	 				</c:if>
           		</c:forEach>
			</span>
			<span class="headtitle">销售退废票理由：</span>
			<span class="red">${vfc:getVeclass(jptpd.xsTply).mc}</span>
			<span class="headtitle">采购退废票状态：</span>
			<span class="red">
				<c:forEach items="${vfn:dictList('CGTFPZT')}" var="oneZt">
	 				<c:if test="${oneZt.value eq jptpd.cgTpzt}">
	 					${oneZt.mc}
	 				</c:if>
           		</c:forEach>
			</span>
		</td>
	</tr>
	<tr>
		<td>
			<span class="headtitle">订单编号：</span>
			<span class="red">${jptpd.ddbh}</span>
			<span class="headtitle">采购来源：</span>
			<span class="red">${jptpd.cgly}</span>
			<span class="headtitle">外部单号：</span>
			<span class="red">${jptpd.wbdh}</span>
			<span class="headtitle">预订员：</span>
			<span class="red">${jptpd.ddyh}&nbsp;<MD:yhb var="dpy" bh="${khdd.ddyh}"></MD:yhb>${dpy.xm}</span>
			<span class="headtitle" title="${blsm}" style ="color:#0433A0;">预订时间：</span>
			<span class="wb_td02" title="${blsm}" style ="color:#0433A0;">${vfn:format(jptpd.ddsj,'')}</span>
		</td>
	</tr>
</table>	
