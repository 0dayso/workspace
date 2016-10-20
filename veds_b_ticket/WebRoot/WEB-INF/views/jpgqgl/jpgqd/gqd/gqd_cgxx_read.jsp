<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:forEach items="${jpGqdCjrMap}" var="jpGqdCjr" varStatus="vc">
	<c:if test="${vc.index eq '0'}">
		<c:set var="jpGqdCjrMx" value="${jpGqdCjr.value}"></c:set>
		<c:set var="cgly" value="${jpGqdCjrMx[0].gqCgly}"></c:set>
	</c:if>
</c:forEach>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/cg.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">采购信息</span>
		</td>
		<td style="background:#efefef;">
			<table class="form_table pt15 pb15" border="0" cellpadding="0" cellspacing="0" >
	           <tr>
	               <td>采购改签费：
	               		<span style="color: red">¥${sumGqCgfy}</span>
	               </td>
	               <td>采购科目：
              			<c:forEach items="${zfkmList}" var="zfkm"> 
					   		<c:if test="${jpgqd.gqCgkm eq zfkm.kmbh}"><font color="red">${zfkm.kmmc}</font></c:if>
					    </c:forEach>
	               </td>
	               <td>
	               		 票证类型：${cgly}
	               </td>
	               <td style="${cgly eq 'OP' ? '' : 'display: none'}">
	               		外出票单位：${jpgqd.gqCgdw}
	               </td>
	           </tr>
	        </table>
		</td>
	</tr>
</table>
