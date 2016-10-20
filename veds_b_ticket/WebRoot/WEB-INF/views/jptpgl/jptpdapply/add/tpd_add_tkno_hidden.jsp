<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<input type="hidden" disabled="disabled" name="tkno"     id="tkno${vc.TKNO}"    value="${vc.TKNO}">
<input type="hidden" disabled="disabled" name="cjr"      id="cjr${vc.TKNO}"     value="${vc.CJR}">
<input type="hidden" disabled="disabled" name="cjrlx"    id="cjrlx${vc.TKNO}"   value="${vc.CJRLX}">
<input type="hidden" disabled="disabled" name="zjlx"     id="zjlx${vc.TKNO}"    value="${vc.ZJLX}">
<input type="hidden" disabled="disabled" name="zjhm"     id="zjhm${vc.TKNO}"    value="${vc.ZJHM}">
<input type="hidden" disabled="disabled" name="sjhm"     id="sjhm${vc.TKNO}"    value="${vc.SJHM}">
<input type="hidden" disabled="disabled" name="xb"       id="xb${vc.TKNO}"      value="${vc.SZDH}">
<input type="hidden" disabled="disabled" name="gj"       id="gj${vc.TKNO}"      value="${vc.GNGJ}">
<input type="hidden" disabled="disabled" name="csrq"     id="csrq${vc.TKNO}"    value="${vc.CSRQ}">
<input type="hidden" disabled="disabled" name="zjyxq"    id="zjyxq${vc.TKNO}"   value="${vc.ZJYXQ}">
<input type="hidden" disabled="disabled" name="zjqfg"    id="zjqfg${vc.TKNO}"   value="${vc.ZJQFG}">
<input type="hidden" disabled="disabled" name="wbcjrid"  id="wbcjrid${vc.TKNO}" value="${vc.WBCJRID}">
<input type="hidden" disabled="disabled" name="xs_cw"    id="xs_cw${vc.TKNO}"   value="${vc.XS_CW}">
<input type="hidden" disabled="disabled" name="cg_cw"    id="cg_cw${vc.TKNO}"   value="${vc.CG_CW}">

<%-- 可退票航段 --%>
<c:forEach items="${vc.TKHDLIST}" var="tkhd">
	<c:if test="${empty tkhd.tpmxid}">
		<input type="hidden" id="tkhdid_${vc.TKNO}_${tkhd.ddhdid}" name="tkhdid_${vc.TKNO}" value="${tkhd.id}" ddhdid="${tkhd.ddhdid}"  hc="${tkhd.cfcity}${tkhd.ddcity}" />
	</c:if>
</c:forEach>
