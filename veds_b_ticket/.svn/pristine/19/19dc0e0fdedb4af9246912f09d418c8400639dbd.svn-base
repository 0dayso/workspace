<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="jpInfo">
	<tr>
		<td style="background:#f1f1f1;height:90px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/cg.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">出票信息</span>
		</td>
		<td style="position: relative; top:0px;background:#f1f1f1;">
			<span class="headtitle">出票方式</span>
			<span id="icpfs" class="red">
					<select name="ct_cpfs" style="width: 80px" onchange="setConfirmTime(this.value,'${kh_khdd_pn.ddzt}','${kh_khdd_pn.ps_pszt}');">
						<option value="1" ${kh_khdd_pn.ct_cpfs eq '1' or empty kh_khdd_pn.ct_cpfs ? 'selected' : ''}>直接出票</option>
						<option value="0" ${kh_khdd_pn.ct_cpfs eq '0' ? 'selected' : ''}>暂缓出票</option>
					</select>
			</span>
			<span class="headtitle" id="tcplx">采购来源</span>
			<span class="red" id="icplx">
				
				
				<select name="cplx" id="cplx_select" style="width: 80px" onchange="checkCplx(this);"  class="required">
					<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
					    <c:if test="${oneLx.id ne '10014'}">
							<option value="${oneLx.id }">${oneLx.mc }</option>
						</c:if>
					</c:forEach>
				</select>
				
				<font color="red">*</font>
			</span>
		</td>
	</tr>
</table>