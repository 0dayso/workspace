<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div id="tbl-container" style="height: 100%">
<c:choose>
<c:when test="${not empty detailList or fn:length(detailList) gt 0}">
	<display:table id="vc_asms" name="detailList" class="list_table" decorator="org.displaytag.decorator.TotalTableDecorator" style="width:100%;white-space: nowrap;" >
		<display:column title="序号">${vc_asms_rowNum}</display:column>
		<display:column  title="PNR" property="PNRNO" maxLength="10"/>
		<display:column  title="票证状态" property="PZZTMC" />
		<c:if test="${empty param.zctp or param.zctp eq '1' or param.zctp eq '2'}">
			<display:column  title="流水号" property="CG_ZFLSH" maxLength="10"/>
		</c:if>
		<c:if test="${empty param.zctp or param.zctp eq '1' or param.zctp eq '2'}">
			<display:column property="CGJE" format="{0,number,#0.00}" title="采购金额" total="true" style="text-align:right;background-color:#00E000;color:#000000"/>
		</c:if>
		<c:if test="${param.zctp eq '3'}">
			<display:column property="CGJE" format="{0,number,#0.00}" title="${param.rqtj eq '1' ? '应退金额' : '实退金额'}" total="true" style="text-align:right;background-color:#00E000;color:#000000"/>
		</c:if>	
		<display:column title="票号" property="TKNO" maxLength="20"/>
		<display:column title="张数" property="TKCOUNT"  total="true" format="{0,number,#}" style="text-align:right;"/>
		<display:column property="CJR" title="乘机人" maxLength="8"/>
		<display:column property="HC" title="航程" maxLength="6" />
		<display:column property="CGKM" title="采购科目" />
		<display:column title="出票时间">${vfn:format(vc_asms.CP_DATETIME,'MM-dd HH:mm')}</display:column>
		<display:column property="CG_HKGS_PNR" title="航空公司编码" />
		<display:column property="CGLY" title="票证类型" />
	</display:table>
</c:when>
<c:otherwise>
	没查询到数据！
</c:otherwise>
</c:choose>
</div>
<script type="text/javascript">
	$(function(){
		if($('#vc_asms')){
			highlightTableRows('vc_asms');
		}
	})
</script>