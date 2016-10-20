<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="skInfo">
	<tr>
	    <td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/skxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">收款信息</span>
	    </td>
	    <td style="background:#f1f1f1;">
			<table class="form_table pt15 pb15"  border="0" cellpadding="0" cellspacing="0" >
				   <tr>
					    <td >
					    	收款状态：
					    	<c:choose>
					    		<c:when test="${jpgqd.skzt eq '0'}"><font color="red">未收款</font></c:when>
					    		<c:when test="${jpgqd.skzt eq '1'}"><font color="red">已收款</font></c:when>
					    	</c:choose>
					    </td>
					    <td >收款科目：
					    	<c:forEach items="${zfkmList}" var="zfkm"> 
							   	<c:if test="${jpgqd.skkm eq zfkm.kmbh}"><font color="red">${zfkm.kmmc}</font></c:if>
							</c:forEach>
					    </td>
					    <td>
					 		联系人：<font color="red">${jpgqd.nxr}</font>
					    </td>
					    <td>
					  		  联系电话：<font color="red">${empty jpgqd.nxdh ? '无':jpgqd.nxdh}</font>
					    </td>
					    <td>
					   		 手机：<font color="red">${jpgqd.nxsj}</font>
					    </td>
				   </tr> 
			</table> 
	    </td>
	</tr>
</table>
