<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<div id="tbl-container" style="height: 100%">
<c:choose>
<c:when test="${not empty detailList or fn:length(detailList) gt 0}">
	<display:table id="vc_asms" name="detailList" class="list_table" decorator="org.displaytag.decorator.TotalTableDecorator" style="width:100%;white-space: nowrap;" >
		<display:column title="序号" style="text-align: center;">${vc_asms_rowNum}</display:column>
		<display:column title="外部单号" property="WBDH" style="text-align: center;"/>
		<display:column  title="订单类型" property="DDLXMC" style="text-align: center;"/>
		<display:column title="金额" property="ZFJE" total="true" style="text-align:right;background-color:#00E000;color:#000000" format="{0,number,#0.00}"/>
		<display:column title="PNR" property="PNRNO" style="text-align: center;"/>
		<display:column property="TKNOS" title="票号" maxLength="10"/>
		<display:column property="CJRS" title="乘机人" maxLength="6" />
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