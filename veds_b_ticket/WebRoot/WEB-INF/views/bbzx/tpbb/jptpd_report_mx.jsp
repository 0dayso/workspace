<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:if test="${fn:length(list)>0 }">
   
	<multipage:pone page="${ctx}/vedsb/bbzx/tpbb/query" actionFormName="page" var="surl"></multipage:pone>
     ${surl}
	<display:table id="vc" name="page.list" requestURI="${ctx}/vedsb/bbzx/tpbb/query?dtableid=vc" sort="external"
		excludedParams="dtableid"  style="width:3000px;"  class="list_table" decorator="org.displaytag.decorator.TotalTableDecorator" reportid="${reportid}">
		<display:column title="序<br>号"  expfield="RO" style="text-align:center;">${vc_rowNum}</display:column>
		<display:merge title="退废状态" >
			<display:column title="<span title='退票毛利:大于0 盈;小于0 亏;等于0 平'> 盈亏</span>" style="text-align: center;" expfield="YKBS">
				<c:choose>
					<c:when test="${(vc.XS_TPSXF-vc.CG_TPF) gt '0.00' }"><font color="blue" >盈</font></c:when>
					<c:when test="${(vc.XS_TPSXF-vc.CG_TPF) lt '0.00' }"><font color="red"  >亏</font></c:when>
					<c:when test="${(vc.XS_TPSXF-vc.CG_TPF) eq '0.00' }"><font color="green">平</font></c:when>
					
				</c:choose>
			</display:column>
			<display:column title="客户办理<br>状态"  style="text-align:center;"  sortName="d.xs_tpzt" expfield="XS_TPZT">
				<c:choose>
				  <c:when test="${vc.XS_TPZT eq '0'}">已申请</c:when>
				  <c:when test="${vc.XS_TPZT eq '1'}">已审核</c:when>
				  <c:when test="${vc.XS_TPZT eq '2'}">已办理</c:when>
				  <c:otherwise>已取消</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title=" 采购办理<br>状态"  style="text-align:center;"  sortName="d.cg_tpzt"
				expfield="CG_TPZT">
				<c:choose>
				  <c:when test="${vc.CG_TPZT eq '0'}">待提交</c:when>
				  <c:when test="${vc.CG_TPZT eq '1'}">提交中</c:when>
				  <c:when test="${vc.CG_TPZT eq '2'}">已提交</c:when>
				  <c:when test="${vc.CG_TPZT eq '3'}">已办理</c:when>
				  <c:otherwise>已拒单</c:otherwise>
				</c:choose>
   			</display:column>
		</display:merge>
		
		<display:merge title="票面">
			<display:column title="采购<br>来源" style="text-align:center;" property="CGLY"   sortName="CGLY" />
			<display:column title=" PNR" style="text-align:center;" property="XS_PNR_NO"   sortName="XS_PNR_NO" />
			<display:column title=" 大编码" style="text-align:center;" property="XS_HKGS_PNR" />
			<display:column title=" 航司<br>二字码 " style="text-align:center;" property="HKGS" />
			<display:column title=" 票号" style="width:90px; text-align:center;" expfield="TKNO"   sortName="d.tkno" >
				<a href="javascript:void(0)" onclick="detailTpd('${vc.TPDH}');" title="点击查看该退废票单的详细">${vc.TKNO}</a>
			</display:column>
			<display:column property="CJR" title="乘机人"  maxLength="6" sortName="CJR"/>
			<display:column title=" 乘机人<br>类型" style="text-align:center;"  sortName="CJRLX" expfield="CJRLX">
				<c:choose>
				  <c:when test="${vc.CJRLX eq '1'}">成人</c:when>
				  <c:when test="${vc.CJRLX eq '2'}">婴儿</c:when>
				  <c:when test="${vc.CJRLX eq '3'}">儿童</c:when>
				  <c:otherwise></c:otherwise>
				</c:choose>
			</display:column>
			<display:column title=" 航程<br>类型" style="text-align:center;"  sortName="HCLX" expfield="HCLX">
				<c:choose>
				  <c:when test="${vc.HCLX eq '1'}">单程</c:when>
				  <c:when test="${vc.HCLX eq '2'}">往返</c:when>
				  <c:when test="${vc.HCLX eq '3'}">联程</c:when>
				  <c:when test="${vc.HCLX eq '4'}">缺口</c:when>
				  <c:otherwise></c:otherwise>
				</c:choose>
			</display:column>
			<%--<display:column title=" 航段数"  property="HDS" />
			--%><display:column title=" 航程"  style="text-align:center;"   property="TP_HC"  sortName="TP_HC" />
			<display:column title=" 航程名称"  sortName="tr.tp_hc" style="text-align:center;" >
					${vfc:getBcity(fn:substring(vc.TP_HC,0,3)).mc}-${vfc:getBcity(fn:substring(vc.TP_HC,3,6)).mc}
					<c:if test="${not empty fn:substring(ticketInfo.TP_HC,6,9)}">
					-${vfc:getBcity(fn:substring(vc.TP_HC,6,9)).mc}
			        </c:if>
			</display:column>	
			<display:column title=" 航班号" style="text-align:center;"  expfield="XS_HBH"  sortName="XS_HBH" >
			 	${vc.XS_HBH }
				<c:if test="${vc.HB_DYNAMIC eq '3'}">
					<img src="/asms/images/flight_cancel.gif" title="航班延误取消"/>
				</c:if>
			 	<c:if test="${vc.HB_DYNAMIC eq '1'}">
					<img src="/asms/images/flight_delay.gif" title="航班延误"/>
				</c:if>
				<c:if test="${vc.HB_DYNAMIC eq '2'}">
					<img src="/asms/images/flight_cancel.gif" title="航班取消"/>
				</c:if>
			 </display:column>
			<display:column title=" 舱<br>位" property="CG_CW"  style="text-align:center;" sortName="CG_CW" />
			<%--<display:column title=" 起飞时间" property="CFRQ" sortName="CFRQ" expfield="CFRQ" />
			<display:column title=" 行程单号" property="XCDH" sortName="XCDH" />
   		--%></display:merge>
	    <display:merge title="与销售退票">
		 	<display:column title=" 账单价" property="XS_ZDJ" format="{0,number,#0.00}" sortName="XS_ZDJ" style="text-align: right;"  total="true"/>
		 	<display:column title=" 机建" property="XS_JSF"  format="{0,number,#0.00}" sortName="XS_JSF" style="text-align: right;" total="true" />
			<display:column title=" 税费" property="XS_TAX"  format="{0,number,#0.00}" sortName="XS_TAX" style="text-align: right;" total="true" />
			<c:if test="${param.gngj eq '1' or empty param.gngj}">
				<display:column title="<span title='机建+税费'>税费小计</span>" property="JJSF" format="{0,number,#0.00}" style="text-align: right;" total="true"></display:column>
		 	</c:if>
		 	<display:column title=" 退票<br>费率" 	property="XS_TPFL"  format="{0,number,#0.##%}"  style="text-align: right;" sortName="XS_TPFL"></display:column>
		 	<display:column title=" 退票<br>手续费"  property="XS_TPSXF" format="{0,number,#0.00}"   sortName="XS_TPSXF" style="text-align: right;" total="true" />
		 	<display:column title=" 改签<br>费用" 	property="XS_GQFY"   format="{0,number,#0.00}"   sortName="XS_GQFY" style="text-align: right;" total="true"/>
		 	<c:if test="${param.gngj eq '1'}">
		 		<display:column  property="XS_TKJE" format="{0,number,#0.00}"  sortName="XS_TKJE" style="text-align: right;" total="true"  title="<span title='账单价+机建+税费+代理费+退票手续费/废票工本费'>应退金额</span>"/>
		 	</c:if>
		 	<c:if test="${param.gngj eq '0'}">
		 		<display:column  property="XS_TKJE" format="{0,number,#0.00}"   sortName="XS_TKJE" style="text-align: right;" total="true" title="<span title='账单价+税费+服务费+代理费+退票手续费/废票工本费'>应退金额</span>"/>
		 	</c:if>
		 	<c:if test="${empty param.gngj}">
		 		<display:column  property="XS_TKJE" format="{0,number,#0.00}"   sortName="XS_TKJE" style="text-align: right;" total="true" title="<span title='账单价+机建+税费+服务费+代理费+退票手续费/废票工本费'>应退金额</span>"/>
		 	</c:if>
		   	<display:column title="退款科目" style="text-align:center;" sortName="XS_TKKM" expfield="XS_TKKM">
				${vc.EX.XS_TKKM.kmmc}
		   	</display:column>
		   	<display:column title="办理人"    sortName="XS_BLR" expfield="XS_BLR">
		   	   ${vc.XS_BLR}&nbsp;&nbsp;${vc.EX.XS_BLR.xm}
		   	</display:column>
			<display:column title="办理时间" style="text-align:center;"   expfield="XS_BLSJ"  sortName="XS_BLSJ" >${fn:substring(vc.XS_BLSJ,5,16)}</display:column>
			<display:column title="OFFICE号" style="text-align:center;"  property="CP_OFFICEID" />
		</display:merge>
	   
		 <display:merge title="与采购退票">
		 	<display:column title=" 账单价"  property="CG_ZDJ"  format="{0,number,#0.00}"   sortName="CG_ZDJ" style="text-align: right;" total="true"/>
		 	<display:column title=" 采购价"  property="CG_PJ"   format="{0,number,#0.00}"   sortName="CG_PJ" style="text-align: right;" total="true"/>
		 	<display:column title=" 机建"  property="CG_JSF"  format="{0,number,#0.00}"   sortName="CG_JSF" style="text-align: right;" total="true"/>
		 	<display:column title=" 税费" property="CG_TAX"  format="{0,number,#0.00}"   sortName="CG_TAX"    style="text-align: right;" total="true"/>
		    <display:column title=" 退票<br>费率" property="CG_TPFL" format="{0,number,#0.##%}"  sortName="CG_TPFL" style="text-align: right;"   />
		 	<display:column title=" 退票<br>手续费"   property="CG_TPF"  format="{0,number,#0.00}"   sortName="CG_TPF"   style="text-align: right;" total="true"/>
		 	<display:column title=" 改签费用"  property="CG_GQFY"  format="{0,number,#0.00}"   sortName="CG_GQFY"   style="text-align: right;" total="true"/>
		 	<display:column title=" 实退金额"  property="CG_STJE" format="{0,number,#0.00}"   sortName="CG_STJE"   style="text-align: right;" total="true"/>
		   	<display:column title=" 办理人"    sortName="CG_BLR" expfield="CG_BLR">
		   		${vc.CG_BLR}&nbsp;&nbsp;${vc.EX.CG_BLR.xm}
		   	</display:column>
			<display:column title=" 办理时间" style="text-align:center;"  sortName="CG_BLSJ"  expfield="CG_BLSJ">${fn:substring(vc.CG_BLSJ,5,16)}</display:column>
	    </display:merge>
	   <display:column property="DDBH" title="订单编号" style="text-align: center;"/>
	   <display:column property="WBDH" title="外部退单号" expfield="WBDH" style="text-align: center;"/>
	   <display:column property="WBDDBH" title="外部订单号"  expfield="WBDDBH" style="text-align: center;"/>
	  <display:merge title="申请">
			<display:column title="申请人"   sortName="DDYH"  expfield="DDYH">
				${vc.DDYH }&nbsp;&nbsp;${vc.EX.DDYH.xm}
			</display:column>
			<display:column title="申请时间" expfield="DDSJ"  style="text-align:center;" sortName="DDSJ" property="DDSJ" />
	   </display:merge>
		<display:footer>
			<tr style="text-align: right;">
				<td colspan="15" style="text-align: right;">合    计：</td>
				<td style="text-align: right;"></td>
				<td style="text-align: right;">${hjlist.HJ_XS_ZDJ}</td>
				<td style="text-align: right;">${hjlist.HJ_XS_JSF}</td>
				<td style="text-align: right;">${hjlist.HJ_XS_TAX}</td>
				<td style="text-align: right;">${hjlist.HJ_JJSF}</td>
				<td style="text-align: right;"></td>
				<td style="text-align: right;">${hjlist.HJ_XS_TPSXF}</td>
				<td style="text-align: right;">${hjlist.HJ_XS_GQFY}</td>
				<td style="text-align: right;">${hjlist.HJ_XS_TKJE}</td>
				<td colspan="4"></td>
				<td style="text-align: right;">${hjlist.HJ_CG_ZDJ}</td>
				<td style="text-align: right;">${hjlist.HJ_CG_PJ}</td>
				<td style="text-align: right;">${hjlist.HJ_CG_JSF}</td>
				<td style="text-align: right;">${hjlist.HJ_CG_TAX}</td>
				<td style="text-align: right;"></td>
				<td style="text-align: right;">${hjlist.HJ_CG_TPF}</td>
				<td style="text-align: right;">${hjlist.HJ_CG_GQFY}</td>
				<td style="text-align: right;">${hjlist.HJ_CG_STJE}</td>
				<td colspan="7"></td>
			</tr>
		</display:footer>
		
	</display:table>
	${surl}
</c:if>