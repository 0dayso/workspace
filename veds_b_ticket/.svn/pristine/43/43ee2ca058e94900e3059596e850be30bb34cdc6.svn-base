<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/kh.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">乘机人信息</span>
		</td>
		<td>
			<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;background:#f1f1f1;">
			 	<tr>
				 	<th>序号</th>
				  	<th>乘机人</th>
					<th>证件号码</th>
				   	<th>票号 </th>
				   	<th>采购升舱费</th>
				   	<th>采购改签费</th>
				   	<th>销售升舱费</th>
				   	<th>销售改签费</th>
				   	<th>应收金额</th>		
			 	</tr>
			 	<c:set var="sumGqCgfy" value="0"></c:set>
				<c:set var="sumGqXsfy" value="0"></c:set>
			 	<c:forEach items="${jpGqdCjrMap}" var="jpGqdCjr" varStatus="i">
					<tr>
				 		<!-- 累计采购改签费用和销售改签费用 -->
				 		<c:set var="cjrjps" value="${fn:length(jpGqdCjr.value)}"></c:set>
				 		<td align="center" rowspan="${cjrjps}">${i.count}</td>
						<c:forEach items="${jpGqdCjr.value}" var="jpGqdCjrMx" varStatus="j">
				 			<c:if test="${j.index eq 0}">
						 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjr.key}<script type="text/javascript">getCjrlxImg('${jpGqdCjrMx.cjrlx}')</script></td>
						 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjrMx.zjhm}</td>
							</c:if>
							<td align="center">
								${jpGqdCjrMx.tkno}
								<c:if test="${(not empty jpGqdCjrMx.gqTkno) and (jpGqdCjrMx.tkno ne jpGqdCjrMx.gqTkno)}">
									-->${jpGqdCjrMx.gqTkno}
								</c:if>
							</td>
					 		<td align="center">${jpGqdCjrMx.gqCgscfy}</td>
					 		<td align="center">${jpGqdCjrMx.gqCgfy}</td>
					 		<td align="center">${jpGqdCjrMx.gqXsscfy}</td>
					 		<td align="center">${jpGqdCjrMx.gqXsfy}</td>
					 		<td align="center">${jpGqdCjrMx.gqXsfy + jpGqdCjrMx.gqXsscfy}</td>
					 		<!-- 累计应收金额 -->
							<c:set var="sumGqfy" value="${sumGqfy + jpGqdCjrMx.gqXsfy + jpGqdCjrMx.gqXsscfy}"></c:set>
							<!-- 累计改签销售费 -->
							<c:set var="sumGqXsfy" value="${sumGqXsfy + jpGqdCjrMx.gqXsfy}"></c:set>
							<!-- 累计销售升舱费 -->
							<c:set var="sumGqXsscfy" value="${sumGqXsscfy + jpGqdCjrMx.gqXsscfy}"></c:set>
							
							<!-- 累计采购升舱费 -->
							<c:set var="sumGqCgscfy" value="${sumGqCgscfy + jpGqdCjrMx.gqCgscfy}"></c:set>
							<!-- 累计改签采购费 -->
							<c:set var="sumGqCgfy" value="${sumGqCgfy + jpGqdCjrMx.gqCgfy}"></c:set>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					 <!-- 合计列 -->
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="color: red" align="center">${sumGqCgscfy}</td>
					<td style="color: red" align="center">${sumGqCgfy}</td>
					<td style="color: red" align="center">${sumGqXsscfy}</td>
					<td style="color: red" align="center">${sumGqXsfy}</td>
					<td style="color: red" align="center">${sumGqfy}</td>
				</tr>	
			</table>
		</td>
	</tr>
</table>
