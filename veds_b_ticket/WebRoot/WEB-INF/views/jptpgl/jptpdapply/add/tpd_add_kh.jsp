<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="list_table" cellpadding="0" cellspacing="0"  width="100%">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/kh.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">客户信息</span>
		</td>
	    <td style="position: relative; top:0px;">
			<table class="list_table" cellpadding="0" cellspacing="0" align="center" width="100%" style="height:90px;">
				<tr>
					<th align="center">账单价</th>
					<th align="center">销售价</th>
					<th align="center">机建</th>
					<th align="center">税费</th>
					<c:if test="${sfGq}">
						<th align="center">升舱费</th>
					</c:if>
					<th align="center">退票费率</th>
					<th align="center">退票费</th>
					<c:if test="${if_fzytp}">
						<th align="center">参考退票费</th>
					</c:if>
					<th align="center">应退金额</th>
				</tr>
				<!-- 单条客户信息 -->
				<tr>
					<td align="center">
						<input type="text" name="xsZdj"
						id="xsZdj${tkno}" value="0" size="3"
						onblur="modifyXsJg('#xsZdj')"
						class="required validate-number min-value-${empty tfje.PJ_CGJ ? 0 : tfje.PJ_CGJ} max-value-0"></td>
					<td align="center">
						<input type="text" name="xsPj"
						id="xsPj${tkno}" value="0" size="3"
						class="required validate-number red min-value-${empty tfje.PJ_XSJ ? 0 : tfje.PJ_XSJ} max-value-0"
						></td>
					<td align="center">
						<input type="text" name="xsJsf"
						id="xsJsf${tkno}" value="0" size="1"
						onblur="modifyXsJg('#xsJsf')"
						class="required validate-number red min-value-${empty tfje.PJ_JSF ? 0 : tfje.PJ_JSF} max-value-0"></td>
					<td align="center">
						<input type="text" name="xsTax"
						id="xsTax${tkno}" value="0" size="1"
						onblur="modifyXsJg('#xsTax')"
						class="required validate-number red min-value-${empty tfje.PJ_TAX ? 0 : tfje.PJ_TAX} max-value-0"></td>
					<c:if test="${sfGq}">
						<td align="center">
							<input type="text" name="tp_gqfy"
							id="tp_gqfy${tkno}" cgkhflag="kh"
							value="${empty tfje.TP_GQFY ? 0 : tfje.TP_GQFY}" size="1"
							onclick="setGqfy(this,'${cg}','')" readonly="readonly"
							title="点击修改应退升舱费用" class="required validate-number "></td>
					</c:if>
					<td align="center">
						<input type="text" name="xsTpfl"
						id="xsTpfl${tkno}" value="0" size="1" onblur="toTpfl('xs')"
						class="required validate-number min-value-0 max-value-99999999.9">%
					</td>
					<td align="center">
						<input type="text" name="xsTpsxf"
						id="xsTpsxf${tkno}" value="0" size="1" onblur="ytje();"
						class="required validate-number">
					</td>
					<td align="center">
						<span  id="xsTkje"  id="xsTkje${tkno}" class="red">0</span>
					</td>
				</tr>
				
				<!-- 客户信息合计 -->
				<tr style="font-weight: bolder;background: #f6f6f6" class="red">
					<td align="center" id="hj_xsZdj"></td>
					<td align="center" id="hj_xsPj"></td>
					<td align="center" id="hj_xsJsf"></td>
					<td align="center" id="hj_xsTax"></td>
					<c:if test="${sfGq}">
						<td align="center"><input type="text" name="tp_gqfy"
							id="tp_gqfy${tkno}" cgkhflag="kh"
							value="${empty tfje.TP_GQFY ? 0 : tfje.TP_GQFY}" size="1"
							onclick="setGqfy(this,'${cg}','')" readonly="readonly"
							title="点击修改应退升舱费用" class="required validate-number "></td>
					</c:if>
					<td align="center" id="hj_xsTpfl"></td>
					<td align="center" id="hj_xsTpsxf"></td>
					<td align="center" id="hj_xsTkje"></td>
				</tr>
			</table>
		</td>
</table>