<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="tkno_or_cjr" value="${sfcp eq 'N' ? '2' : '1'}"></c:set>
<c:if test="${empty cjrList}">
<div style="width:99%;margin-top: 4px;text-align: center;color: red;font-weight: bolder;font-size: 24px;" align="center">
	订单未出票或未进系统，请联系平台处理！
</div>
</c:if>
<c:if test="${not empty cjrList}">
	<br>
	<input type="hidden" id="ddbh" name="ddbh" value="${jpjp_list[0].DDBH}" />
	<span style="font-weight:bold;color:blue;font-size:15px">PNR/票号:</span>
	<input type="text" name="tkno" title="请输入13位票号，提取行程单信息" value="${param.tkno}" style="width: 100px" onkeyup="doTkno(this.value)"/>[输完票号之后请按回车键提取]
	<br>
	<table cellpadding="0" cellspacing="0" style="width:99%;margin-top: 4px;" align="center" class="tab_jf_in" >
		<tr>
			<th width="9%">
				<input type="checkbox"  id="checkSelAll" onclick="selAll(this);" title="点击全选"/>
			</th>
			<th>乘机人</th>
			<th>票号</th>
			<th>行程单号</th>
		</tr>
		<c:set var="cnt" value="0"></c:set><!-- 行程单计数器 -->
		<c:forEach items="${jpjp_list}" var="tk" varStatus="v">
			<tr>
				<td align="center">
					<input jppzzt="${tk.JPZT }" type="checkbox" value="${tk.SZDH }${tk.TKNO }" name="selOne" id="selOne${v.index }" />
					<input type="hidden" id="jpzt${tk.SZDH }${tk.TKNO }" value="${tk.JPZT}"><!-- 机票票证状态 -->
					<input type="hidden" id="gngj${tk.SZDH }${tk.TKNO }" value="${tk.GNGJ}"><!-- 0国际 1国内 -->
					<!-- 已经打印过的，原始行程单号 -->
					<input type="hidden" id="xcdhold${tk.SZDH}${tk.TKNO}" value="${tk.XCDH}">
					<input type="hidden" id="xcdh${tk.SZDH}${tk.TKNO}" value="${tk.XCDH}">
					<input type="hidden" id="xcdh${tk.SZDH}${tk.TKNO}" value="${tk.XCDH}">
					<input type="hidden"  id="xcdzt${tk.SZDH}${tk.TKNO }" value="${tk.XCDZT}">
   					<input type="hidden"  id="xcdprintnum${tk.SZDH }${tk.TKNO }" value="${tk.PRINT_NUM}">
				</td>
				<td align="center">
					${tk.CJR}
				</td>
				<td align="center">
					${tk.SZDH}${tk.TKNO}
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${empty tk.XCDH or tk.XCDH eq '0'}">--未创建--</c:when>
						<c:otherwise>${tk.XCDH}</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<div align="center" style="margin-top: 8px;">
	<input type="button" value="创建行程单" class="ext_btn ext_btn_submit" onclick="createXcd();">
	<input onclick="printXcd();" value="打印行程单" type="button" class="ext_btn ext_btn_submit" />
</div>
