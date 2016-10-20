<%@ page language="java" pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${jpkhdd.WDPT eq '10100010'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/qunaerLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100011'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/taobaoLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100012'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/kunxunLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100050'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/xiechengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100024'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/tongchengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100001'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/piaomengLogo.png"></c:set>
	</c:when>
	<c:when test="${jpkhdd.WDPT eq '10100002'}">
		<c:set var="srcs" value="${ctx}/static/images/wdimages/jingriLogo.png"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="srcs" value="0"></c:set>
	</c:otherwise>	
</c:choose>
<%-- 10100010 去哪儿;10100011 淘宝;10100012 酷讯;10100024 同程;10100050 携程--%>
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="height:90px;width: 100%;margin-bottom: 1px; background:#f1f1f1">
	               <!--是否主单 外部订单号：1799340232 政策代码：VCZBJ_P1CZLDSPEKE2221441 政策说明：51BOOK[E,FD680,返点1.4,留点0.0,留钱-2.72,136900933][09 10:53]比价包机，比价出票。 -->
	               <!--PNR/大编码：NW2E00/EF3JJ 订单状态：已出票 订单编号：728989807866160309 航司/采购订单号：1799340232 主订单编号：728989807866160309 -->
	               <!--紧急程度   临近最晚出票时间 平台采购状态 -->
	<tr>
	  <c:if test="${not empty srcs and srcs ne '0'}">
		  <td rowspan="3" style="background:#efefef;height:90px; width:100px; border:1px;text-align:center;">
		 	 <img width="100%" height="78%" src="${srcs}" /></br>
		 	 <c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
       	 		<c:if test="${onewdpt.parid ne 'none' and onewdpt.id eq jpkhdd.WDPT}">
       	 			<span class="red">${onewdpt.mc}</span>
       	 		</c:if>
       	 	</c:forEach>
		  </td>
	  </c:if>
	  <td class="wb_td03" style="border:none;">
		 <c:if test="${jpkhdd.SFZDD eq '1'}">
	  	 	<span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zj.png"></span>
	  	 </c:if>
	  	 <c:if test="${jpkhdd.SFZDD ne '1'}">
	  	 	<span class="wb_td02"><img id="zdzd" class="img " src="${ctx}/static/images/wdimages/zd.png"></span>
	  	 </c:if>
	  	 <span class="headtitle">政策代码</span>
	 	 <span class="wb_td02">${jpkhdd.WD_ZCDM}</span>
	 	 
	  	 <span class="headtitle">&nbsp;&nbsp;外部单号</span>
	  	 <span class="wb_td02">
			<input type="text" name="wbdh" value="${jpkhdd.WBDH }"  class="input1  max-length-100 " size="10"/>
			, ${fn:substring(jpkhdd.DDSJ,0,16)}
		</span>
		
		<span class="wb_td02">
			<input type="radio" name="jjcd" id="jjcd0" value="0" ${empty jpkhdd.JJCD or jpkhdd.JJCD eq '0' ? 'checked' : '' } /><img alt="" style="height: 14px;width: 14px" src="${ctx}/static/images/wdimages/putong.gif">
			<input type="radio" name="jjcd" id="jjcd1" value="1" ${jpkhdd.JJCD eq '1' ? 'checked' : '' } /><img alt="" style="height: 14px;width: 14px" src="${ctx}/static/images/wdimages/jiaji.gif">
			<input type="radio" name="jjcd" id="jjcd2" value="2" ${jpkhdd.JJCD eq '2' ? 'checked' : '' } /><img alt="" style="height: 14px;width: 14px" src="${ctx}/static/images/wdimages/teji.gif">
		</span>
	  </td>
	</tr>
	<tr>
		<td class="wb_td03" style="border:none;">
			<span class="headtitle">政策说明</span>
		  	 <span class="wb_td02">
				<span>${jpkhdd.ZC_QD},${jpkhdd.ZC_LY}[投放ID：${jpkhdd.ZC_TFID}，返点${jpkhdd.ZC_FD}，留点${jpkhdd.ZC_LD}，留钱${jpkhdd.ZC_LQ}，${jpkhdd.ZC_SFBJ eq "0" ? '非比价政策' : '比价政策'}]</span>
			</span>
		</td>
	</tr>
	<tr>
	  <td style="border:none">
		<span class="headtitle">PNR/大编码</span>
		<span class="wb_td02">${jpkhdd.XS_PNR_NO}/${jpkhdd.XS_HKGS_PNR}</span>
		
 		<span class="headtitle">订单编号</span>	
	 	<span class="wb_td02">${jpkhdd.DDBH}</span>
	 	
	 	<span class="headtitle">PNR/订单状态</span>	
	 	<span class="wb_td02">${jpkhdd.XS_PNR_ZT}/
	 		<c:forEach items="${vfn:dictList('JPDDZT')}" var="oneZt">
	 			<c:if test="${oneZt.value eq jpkhdd.DDZT}">
	 				${oneZt.mc}
	 			</c:if>
           	</c:forEach>
		</span>
		
		<span class="headtitle">采购订单号</span>	
	 	<span class="wb_td02">${jpkhdd.CG_DDBH}</span>
	 	
	 	<span class="headtitle">平台采购状态</span>
		<span class="wb_td02">
			<c:choose>
				<c:when test="${jpkhdd.CGZT eq '0'}">待确认</c:when>
				<c:when test="${jpkhdd.CGZT eq '1'}">已订座</c:when>
				<c:when test="${jpkhdd.CGZT eq '2'}">出票中</c:when>
				<c:when test="${jpkhdd.CGZT eq '3'}">已完成</c:when>
				<c:when test="${jpkhdd.CGZT eq '4'}">客户消</c:when>
				<c:otherwise>已取消</c:otherwise>	
			</c:choose>
		</span>
	 	
	 	<c:if test="${jpkhdd.sfzdd eq '1'}">
			<span class="headtitle">主订单</span>
			<span class="wb_td02">
				<c:if test="${jpkhdd.zddlx eq '1003601'}">正常单</c:if>
				<c:if test="${jpkhdd.zddlx eq '1003602'}">退废单</c:if>
				<c:if test="${jpkhdd.zddlx eq '1003603'}">改签单</c:if>
				${jpkhdd.zddbh}
			</span>
		</c:if>
		
	  </td>
	</tr>
	<tr>
	  <td> 
		<span class="headtitle" style="display:none">订&nbsp;票&nbsp;员</span>
		<span class="wb_td02" style="display:none">
			<MD:yhb var="dpy" bh="${jpkhdd.dp_dpyid}"></MD:yhb>
			${jpkhdd.dp_compmc} / <MD:cut value="${jpkhdd.dp_deptmc}" length="8" autopoint="true"></MD:cut> / ${jpkhdd.dp_dpyid}&nbsp;${dpy.xm}
		</span>
  	  	<%-- 平台采购状态 --%>
	  	<c:if test="${not empty jpkhdd.ptzcbs and jpkhdd.ptzcbs ne '0'}">
			<span class="headtitle" style="display:none">平台采购状态</span>
			<span class="wb_td02" style="display:none">
		  	 	<font color="#CCCC33" size="4" style="font-style:italic;"> 
		  	  		<MD:airsczzt czzt="${jpkhdd.czzt}" tjlx="${jpkhdd.tjlx}"></MD:airsczzt>
			 	</font>&nbsp;&nbsp; 
		 	</span>
	  	</c:if>
	  	<span class="wb_td02">
			<MD:b_class kind="7504" code="7504" var="urgentDegree"/>
	   		<c:forEach items="${urgentDegree}" var="urgent">
				<c:if test="${jpkhdd.jjcd eq '1' and urgent.YWMC eq jpkhdd.jjcd}"><font style="width:18px;height:6px;background:#8e0707;"></font>&nbsp;${urgent.MC}</c:if>
				<c:if test="${jpkhdd.jjcd eq '2' and urgent.YWMC eq jpkhdd.jjcd}"><font style="width:18px;height:6px;background:#fd0106;"></font>&nbsp;${urgent.MC}&nbsp;<b style="font-size:12px;">${jpkhdd.cpcs}次</b></c:if>
				<c:if test="${jpkhdd.jjcd eq '3' and urgent.YWMC eq jpkhdd.jjcd}"><font style="width:18px;height:6px;background:#097efb;"></font>&nbsp;${urgent.MC}</c:if>
				<c:if test="${jpkhdd.jjcd eq '4' and urgent.YWMC eq jpkhdd.jjcd}"><font style="width:18px;height:6px;background:#fcc102;"></font>&nbsp;${urgent.MC}</c:if>
				<c:if test="${jpkhdd.jjcd eq '5' and urgent.YWMC eq jpkhdd.jjcd}"><font style="width:18px;height:6px;background:#ee7ae9;"></font>&nbsp;${urgent.MC}</c:if>
	    	</c:forEach>
    	</span>
    	<c:if test="${jpkhdd.tjlx eq '6' or (not empty jpkhdd.ptzcbs and jpkhdd.ptzcbs ne '0') or sfptgy eq '1'}">
	    	<c:if test="${jpkhdd.tjlx eq '6' or jpkhdd.ptzcbs eq '10100011'}">
		    	<span class="headtitle">航司订单号</span>
				<span class="wb_td02">
					${empty jpkhdd.hkgs_ddbh ? '无' : jpkhdd.hkgs_ddbh}
				</span>
			</c:if>
			<c:if test="${not empty jpkhdd.ptzcbs and jpkhdd.ptzcbs ne '0' and jpkhdd.ptzcbs ne '10100011'}">
				<span class="headtitle">采购订单号</span>
				<span class="wb_td02">
					${empty jpkhdd.cg_ddbh ? '无' : jpkhdd.cg_ddbh}
				</span>
			</c:if>
    	</c:if>
		<%-- 平台供应状态 --%>
		<c:if test="${sfptgy eq '1'}">
			<span class="headtitle">平台供应状态</span>
			 	<font color="#CCCC33" size="4" style="font-style:italic;"> 
			 		<MD:airsczzt czzt="${jpkhdd.gy_ptzt}" tjlx="GY"></MD:airsczzt>
			 	</font> 
			</span>
	  	</c:if>
	</tr>
</table>