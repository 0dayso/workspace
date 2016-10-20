<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="list_table" cellpadding="0" cellspacing="0"  width="100%">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/cg.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">采购信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="list_table" cellpadding="0" cellspacing="0" align="center"  style="height:90px;">
				<tr>
					<th align="center">账单价</th>
					<th align="center">采购价</th>
					<th align="center">机建</th>
					<th align="center">税费</th>
					<c:if test="${sfGq}">
						<th align="center">升舱费</th>
					</c:if>
					<th align="center">退票费率</th>
					<th align="center">退票费</th>
					<th align="center">应退金额</th>
					<c:if test="${param.forward eq 'cgwc' or param.forward eq 'wc'}">
						<th id="stth"   style="${param.forward eq 'wc' ? 'display: none;' : ''}">实退金额</th>
						<th id="stsjth" style="${param.forward eq 'wc' ? 'display: none;' : ''}">实际回款时间</th>
					</c:if>
				</tr>
                
                <!-- 单条采购信息 -->
				<tr>
					<td align="center">
					<input type="text" name="cgZdj" id="cgZdj${tkno}" value="0" size="4"
						class="required validate-number red min-value-${empty tfje.CG_ZDJ ? 0 : tfje.CG_ZDJ} max-value-0" readonly></td>
					<td align="center">
					<input type="text" name="cgPj" id="cgPj${tkno}" value="0" size="4"
						class="required validate-number red min-value-${empty tfje.CG_PJ ? 0 : tfje.CG_PJ} max-value-0" readonly></td>
					<td align="center"><input type="text" name="cgJsf"
						id="cgJsf${tkno}" value="0" size="4"
						class="required validate-number red min-value-${empty tfje.CG_JSF ? 0 : tfje.CG_JSF} max-value-0"
						readonly>
					</td>
					<td align="center">
					<input type="text" name="cgTax"
						id="cgTax${tkno}" value="0"
						onblur="if(!readOnly){checkNum(this,true,2);toXjPj('${tkno}')}"
						size="4"
						class="required validate-number red min-value-${empty tfje.CG_TAX ? 0 : tfje.CG_TAX} max-value-0"
						readonly>
					</td>
					<c:if test="${sfGq}">
						<td align="center">
						<input type="text" name="cj_gqfy" id="cj_gqfy${tkno}" cgkhflag="cg" value="0" size="4"
							onclick="setGqfy(this,'${cg}','')" readonly="readonly"
							title="点击修改应退升舱费用" 
							class="required validate-number "></td>
					</c:if>
					<td align="center">
						<input type="text" name="cgTpfl" id="cgTpfl${tkno}" value="0" onblur="toTpfl('cg')" size="1" 
						 datatype="*,number,dotformat,minvalue,maxvalue" dotformat="###.#" minvalue="0.0"  maxvalue="100.0">%
					</td>
					<td align="center">
						<input type="text" name="cgTpf" id="cgTpf${tkno}"   size="1" onblur="ytje();"
						class="required validate-number">
					</td>

					<td align="center">
						<span  name="cgTkje" id="cgTkje${tkno}" class="red">0</span>
					</td>
				</tr>

                <!-- 合计采购信息 -->
				<tr style="font-weight: bolder;background: #f6f6f6" class="red">
					<td align="center" id="hj_cgZdj"></td>
					<td align="center" id="hj_cgPj"></td>
					<td align="center" id="hj_cgJsf"></td>
					<td align="center" id="hj_cgTax"></td>
					<c:if test="${sfGq}">
						<td align="center" id="hj_cj_gqfy"></td>
					</c:if>
					<td align="center"></td>
					<td align="center" id="hj_cgTpf"></td>
					<td align="center" id="hj_cgTkje"></td>
				</tr>
			</table>
		</td>
</table>