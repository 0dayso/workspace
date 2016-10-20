<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" border="0" width="99%">
	<tr>
		<td>
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:100px;border:none;background:#f1f1f1;" id="_bzInfo" >
			<c:if test="${(param.forward eq 'cgbl') 
					and (ticket_return.cplx eq 'OP' or ticket_return.cplx eq 'BPET' or ticket_return.cplx eq 'BP' or ticket_return.cplx eq 'GP')}">
				采购退款科目
				<MD:zffskm varoption="option" pt="CG" userid="${VEASMS.bh}" cplx="${ticket_return.cplx}" defaultkm="${mxList[0].CG_ZFKM}"></MD:zffskm>
				<input type="hidden" name="cg_zffs" id="cg_zffs" value="${mxList[0].CG_ZFFS}">
				<select name="cg_zfkm" id="cg_zfkm" style="width: 135px" class="required" onchange="setZffs(this,'cg_zffs');">
					<option value="">==请选择==</option>
					${option}
				</select>&nbsp;<font color="red">*</font>
			</c:if>
	
			<c:if test="${ticket_return.tp_tpzt ne '7' and ticket_return.tp_tpzt ne '5' and ticket_return.tp_tpzt ne '4' and ticket_return.ps_pszt ne '4'}">
				<tr>
					 <td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    				<img src="${ctx}/static/images/wdimages/bzxx.png" /><br />
	    				<span style="font-size:14px; font-weight:bold; color:#1195db">退票备注</span>
	      			</td>
					<td style="border:none;">
						<textarea  rows="4" name="bzbz" class="input1 max-length-2000" cols="95" style="background:#fff;">${ticket_return.bzbz}</textarea>
					</td>
				</tr>
			</c:if>
			<c:if test="${ticket_return.tp_tpzt eq '7' or ticket_return.tp_tpzt eq '5' or ticket_return.tp_tpzt eq '4' or ticket_return.ps_pszt eq '4'}">
				<tr>
				    <input type="hidden" id="obzbz" name="obzbz" value="${ticket_return.bzbz}" />
					<td class="headtitle" style="border:none;">原备注</td>
					<td class="wb_td02" width=768 style="border:none;word-break:break-all;">${empty ticket_return.bzbz ? '无' : ticket_return.bzbz}</td>
				</tr>
				<tr>
					<td class="headtitle" style="border:none;">新备注</td>
					<td style="border:none;">
						<textarea rows="2" name="nbzbz" id="nbzbz" class="input1 max-length-1000 cols="90" style="background:#fff;"></textarea>
					</td>
				</tr>
			</c:if>
		 </table>
	  </td>
	</tr>
</table>
