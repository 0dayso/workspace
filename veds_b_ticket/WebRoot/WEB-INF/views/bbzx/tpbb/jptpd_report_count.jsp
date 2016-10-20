<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:if test="${fn:length(list)>0 }">
	<multipage:pone page="${ctx}/vedsb/bbzx/tpbb/query" actionFormName="page" var="surl"></multipage:pone>
	<c:if test="${param.tjfs eq '2'}">
		 ${surl}
	</c:if>
	<div id="tbl-container" style="height:expression(pagesize[1]-200);">
    <display:table id="vc" name="page.list" class="list_table" style="width:100%;" requestURI="${ctx}/vedsb/bbzx/tpbb/query" 
	decorator="org.displaytag.decorator.TotalTableDecorator" sort="external" excludedParams="dtableid"   reportid="${reportid}">
      	<c:if test="${param.tjfs eq '2'}">
      		<display:column title="航程"  style="text-align:center;" expfield="HC">
      			${vfc:getBcity(fn:substring(vc.HC,0,3)).mc}-${vfc:getBcity(fn:substring(vc.HC,3,6)).mc}
				<c:if test="${not empty fn:substring(vc.HC,6,9)}">
				-${vfc:getBcity(fn:substring(vc.HC,6,9)).mc}
				</c:if>	
      		</display:column>
      	</c:if>
      	<c:if test="${param.tjfs eq '4'}">
      		<display:column title="网店" sortName="WDID" style="text-align:center;" expfield="WDID">
      			${vc.EX.WDID.wdmc}
      		</display:column> 
      		<%--<display:column title="网店平台" property="WDPT" style="text-align:center;"></display:column>--%> 
      	</c:if>
      	<c:if test="${param.tjfs eq '5'}">
      		<display:column title="采购来源" property="CGLY" style="text-align:center;"/> 
      	</c:if>
      	<c:if test="${param.tjfs eq '7'}">
      		<display:column title="采购办理人"    sortName="CG_BLR" expfield="CG_BLR" >
		   		${vc.CG_BLR}&nbsp;&nbsp;${vc.EX.CG_BLR.xm}
		   	</display:column>
      	</c:if>
      	<c:if test="${param.tjfs eq '8'}">
      		<display:column title="销售办理人" sortName="XS_BLR" expfield="XS_BLR" > 
		   	   ${vc.XS_BLR}&nbsp;&nbsp;${vc.EX.XS_BLR.xm}
		   	</display:column>
      	</c:if>
      	<display:column title="退票单数" property="TPDS" sortable="true" format="{0,number,###}" style="text-align:center;"  total="true"/>
      	<display:column title="退票数" property="TPS" href="javascript:detail()" sortable="true" format="{0,number,###}" style="text-align:center;"
      		paramId="vefun,WDPT,WDID,HC,CGLY,CG_BLR,XS_BLR" total="true"/>
		<display:merge title="与客户退票">
		 	<display:column title=" 账单价" property="XS_ZDJ" sortable="true" format="{0,number,#0.00}" sortName="tr.tp_cgj" style="text-align: right;" total="true"/>
		 	<display:column title=" 销售价" property="XS_PJ" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_xsj" style="text-align: right;" total="true"/>
		 	<c:if test="${param.gngj eq '1' or empty param.gngj}">
		 		<display:column title=" 机建" property="XS_JSF" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_jsf" style="text-align: right;" total="true"/>
		 	</c:if>
		 	<display:column title=" 税费" property="XS_TAX" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_tax" style="text-align: right;" total="true"/>
		 	<display:column title=" 退票手续费" property="XS_TPXSF" format="{0,number,#0.00}" sortable="true" sortName="XS_TPXSF" style="text-align: right;" total="true"/>
		 	<display:column title=" 改签<br>费用" property="XS_TGQ" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_gqfy" style="text-align: right;" total="true"/>
		 	<display:column title=" 应退金额" property="XS_TKJE" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_custje" style="text-align: right;" total="true"/>
		</display:merge>
		
		<display:merge title="与采购退票">
		 	<display:column title=" 账单价" sortable="true" property="CG_ZDJ" format="{0,number,#0.00}" sortName="tr.cj_xsj" style="text-align: right;" total="true"/>
		 	<c:if test="${param.gngj eq '1' or empty param.gngj}">
		 		<display:column title=" 机建" property="CG_JSF" sortable="true" format="{0,number,#0.00}" sortName="tr.cj_jsf" style="text-align: right;" total="true"/>
			</c:if>
			<display:column title=" 税费" property="CG_TAX" sortable="true" format="{0,number,#0.00}" sortName="tr.cj_tax" style="text-align: right;" total="true"/>
		 	<display:column title=" 退票手续费<br>/废票工本" property="CG_TPF" format="{0,number,#0.00}" sortable="true" sortName="tr.JP_PJHK" style="text-align: right;"  total="true"/>
		 	<display:column title=" 改签<br>费用" property="CG_TGQ" format="{0,number,#0.00}" sortable="true" sortName="tr.CJ_GQFY" style="text-align: right;"  total="true"/>
		 	<c:if test="${param.gngj eq '1'}">
			 	<display:column title="<span title='采购价+机建+税费'>采购金额</span>" sortable="true" sortName="cgxj" format="{0,number,##0.00}"  style="text-align:right;">
			 		${vc.CG_PJ+vc.CG_JSF+vc.CG_TAX}
			 	</display:column>
		 	</c:if>
		 	<c:if test="${param.gngj eq '0'}">
		 		<display:column title="<span title='采购价+税费+采购服务费'>采购金额</span>"  sortable="true" sortName="cgxj" format="{0,number,##0.00}"  style="text-align:right;" total="true">
		 			${vc.CG_PJ+CG_TAX}
		 		</display:column>
		 	</c:if>
		 	<c:if test="${empty param.gngj}">
		 		<display:column title="<span title='采购价+机建+税费+采购服务费'>采购金额</span>"  sortable="true" sortName="cgxj" format="{0,number,##0.00}"  style="text-align:right;" >
		 			${vc.CG_PJ+vc.CG_JSF+vc.CG_TAX}
		 		</display:column>
		 	</c:if>
		 	<display:column title=" 应退金额" property="CG_TKJE" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_hxje" style="text-align: right;" total="true"/>
			<display:column title=" 实退金额" property="CG_STJE" format="{0,number,#0.00}" sortable="true" sortName="tr.tp_cgst" style="text-align: right;" total="true"/>
		</display:merge>
    </display:table>
    <c:if test="${param.tjfs eq '2'}">
		 ${surl}
	</c:if>
</c:if>