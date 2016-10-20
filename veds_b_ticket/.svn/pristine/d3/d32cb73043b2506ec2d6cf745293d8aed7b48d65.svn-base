<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;">
	<tr>
	    <td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/bzxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">备注信息</span>
	    </td>
	    <td style="background:#f1f1f1;">
	    	<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:100%;border:none;" >
				<c:if test="${jpgqd.gqzt ne '4' and jpgqd.gqzt ne '7' and jpgqd.gqzt ne '8'}">
					<tr>
						<td class="headtitle" style="border:none;width:40px;">备注：</td>
						<td style="border:none;">
							<textarea rows="2" name="bzbz" class="input1 max-length-2000" cols="90" style="background:#fff;">${jpgqd.bzbz}</textarea>
							<br>
							<span class="wb_td02">此备注只作内部使用，限1000个汉字</span>
						</td>
					</tr>
				</c:if>
				<c:if test="${jpgqd.gqzt eq '4' or jpgqd.gqzt eq '7' or jpgqd.gqzt eq '8'}">
					<input type="hidden" name="gqbz" value="" >
					<tr>
						<td style="border:none;width:50px">原备注:</td>
						<td style="border:none;">${empty jpgqd.bzbz ? '无' : jpgqd.bzbz}</td>
					</tr>
				</c:if>
			</table>
	    </td>
	</tr>
</table>