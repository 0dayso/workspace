<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<c:if test="${not empty leftlist}">
<display:table id="vc1" name="leftlist" class="list_table" decorator="org.displaytag.decorator.TotalTableDecorator" >
    <display:column title="采购<br/>来源" group="1" property="CPLX" ></display:column>
    
    <c:if test="${tjfs eq '0' or tjfs eq '1'}">
    <display:column title="OFFICE号<br/>支付科目" >
		<c:if test="${vc1.CPLX eq 'BSP' || vc1.CPLX eq 'BSPET'}">
			<a href="javascript:void(0);" onclick="getBSPETCheck('${vc1.fz1 }','${vc1.fz2 }','${vc1.PRINTNO }')" title="点击进入BSPET票自动对账页面">
			${vc1.fz1 }
			</a>
		</c:if>    
    	<c:if test="${vc1.CPLX ne 'BSP' && vc1.CPLX ne 'BSPET'}">
			${vc1.fz1}
		</c:if>
    </display:column>
	</c:if>

    <c:if test="${tjfs eq '0' }">
    	<display:column title="工作号<br/>航空公司<br/>合作单位" property="fz2"></display:column>
    	<display:column title="打票<br/>机号" property="PRINTNO"></display:column>
    </c:if>
    
    <c:if test="${tjfs eq '3' }">
    	<display:column title="网店平台" property="PTMC" group="3"></display:column>
    	<display:column title="网店名称" property="KHMC" group="4"></display:column>
    </c:if>
    
    <display:merge title="正常票">
		<display:column title=" 张数" property="ZS" href="javascript:detail('${param.tjfs}')" paramId="vefun,CPLX,1,FZ1,FZ2,PRINTNO"
			 style="text-align:right;" format="{0,number,#0}" total="true"	/>
		<display:column title=" 采购<br/>金额" property="CGJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 账单价" property="ZDJ" style="text-align:right;" format="{0,number,#0.00}" total="true" expfield="ZDJ"></display:column>
		<display:column title=" 销售价" property="XSJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 机建<br/>税费" property="TAX" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" <span title='销售价+机建税费'>小计</span>" property="PJHJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
	</display:merge>
	
	<display:merge title="废票">
		<display:column title=" 张数" property="FP_ZS" href="javascript:detail('${param.tjfs}')" paramId="vefun,CPLX,3,FZ1,FZ2,PRINTNO"
			style="text-align:right;" format="{0,number,#0}" total="true"	/>
		<display:column title=" 采购<br/>金额" property="FP_CGJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 账单价" property="FP_ZDJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 销售价" property="FP_XSJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 机建<br/>税费" property="FP_TAX" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" <span title='销售价+机建税费'>小计</span>" property="FP_PJHJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 采购<br/>实退" property="FP_YTJE" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 采购<br/>工本" property="FP_CGGBF" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 客户<br/>实退" property="FP_KHST" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 客户<br/>工本" property="FP_KHGBF" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
	</display:merge>
	
	<display:merge title="退票">
		<display:column title=" 张数" property="TP_ZS" href="javascript:detail('${param.tjfs}')" paramId="vefun,CPLX,2,FZ1,FZ2,PRINTNO"
			 style="text-align:right;" format="{0,number,#0}" total="true"	/>
		<display:column title=" 采购<br/>金额" property="TP_CGJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 账单价" property="TP_ZDJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 销售价" property="TP_XSJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 机建<br/>税费" property="TP_TAX" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" <span title='销售价+机建税费'>小计</span>" property="TP_PJHJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 采购<br/>实退" property="TP_YTJE" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 采购<br/>手续" property="TP_CGSXF" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 客户<br/>实退" property="TP_KHST" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
		<display:column title=" 客户<br/>手续" property="TP_KHSXF" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
	</display:merge>
	<display:column title="<span title='正常票小计+废票小计+退票小计'>合计</span>" property="ZHJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
	<display:column title="<span title='正常采购金额+废票采购金额+退票采购金额'>采购金额<br>合计</span>" property="ZCGHJ" style="text-align:right;" format="{0,number,#0.00}" total="true"></display:column>
</display:table>
</c:if>
<input type="hidden" id="vcexpfield" value="${vfn:urid(vc1expfield)}">
<c:if test="${empty leftlist}">
	没有找到相关数据
</c:if>