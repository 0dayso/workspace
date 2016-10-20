<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="sumGqCgfy" value="0"></c:set>
<c:set var="sumGqXsfy" value="0"></c:set>
<c:set var="jpKhddCjrList" value="${gqdData.jpKhddCjrList}"></c:set>
<table  class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/kh.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">乘机人信息</span>
		</td>
		<td>
			<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;background:#f1f1f1;">		 	 	
			 	<!-- 改签页面用到 -->
			 	<c:if test="${not empty jpGqdCjrMap}">
			 		<tr>
					 	<th>序号</th>
					  	<th>乘机人</th>
						<th>证件号码</th>
					   	<th>票号 </th>
					   	<th>采购升舱费</th>
					   	<th>客户升舱费</th>
					   	<th>采购改签费</th>
					   	<th>客户改签费</th>
					   	<th>应收金额</th>		
			 		</tr>
			 		<c:set var="sumGqXsfy" value="0"></c:set>
				 	<c:forEach items="${jpGqdCjrMap}" var="jpGqdCjr" varStatus="i">
				 		<tr>
							<c:set var="cjrjps" value="${fn:length(jpGqdCjr.value)}"></c:set>
							<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${i.count}</td>
							<c:forEach items="${jpGqdCjr.value}" var="jpGqdCjrMx" varStatus="j">
								<input type="hidden" name="jpGqdCjrIdArr" value="${jpGqdCjrMx.id}"/>
								<c:if test="${j.index eq 0}">
							 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjr.key}<script type="text/javascript">getCjrlxImg('${jpGqdCjrMx.cjrlx}')</script></td>
							 		<td style="text-align: center;vertical-align: middle;" rowspan="${cjrjps}">${jpGqdCjrMx.zjhm}</td>
								</c:if>
								<td style="text-align: center;vertical-align: middle;">
									${jpGqdCjrMx.tkno}
									<c:if test="${param.forward eq 'review' or param.forward eq 'transact'}">	
										-->
										<input type="text" value="" name="gqTknoArr" datatype="*14-14" ignore="ignore" maxlength="14" style="height: 20px;width:120px"/>
									</c:if>
								</td>
								<td style="text-align: center;vertical-align: middle;">
						 			<span><!--  -->
						 				<input type="text" value="${empty jpGqdCjrMx.gqCgscfy ? '0' : jpGqdCjrMx.gqCgscfy}" name="gqCgscfyArr" datatype="number,dotformat,*" dotformat="###.##" nullmsg="请填写值" style="height: 20px;width:50px" onblur="changeCgscfySum()"/>
						 			</span>
						 		</td>
						 		<td style="text-align: center;vertical-align: middle;">
						 			<span><!--  -->
						 				<input type="text" value="${empty jpGqdCjrMx.gqXsscfy ? '0' : jpGqdCjrMx.gqXsscfy}" name="gqXsscfyArr" datatype="number,dotformat,*" dotformat="###.##" nullmsg="请填写值" style="height: 20px;width:50px" onblur="changeXsfySum();changeYsje('${jpGqdCjrMx.tkno}');"/>
						 			</span>
						 		</td>
								<td style="text-align: center;vertical-align: middle;">
						 			<span>
						 				<input type="text" value="${empty jpGqdCjrMx.gqCgfy ? '0' : jpGqdCjrMx.gqCgfy}" name="gqCgfyArr" datatype="number,dotformat,*" dotformat="###.##" nullmsg="请填写值" style="height: 20px;width:50px" onblur="changeCgfySum()"/>
						 			</span>
						 		</td>
						 		<td style="text-align: center;vertical-align: middle;">
						 			<span>
						 				<input type="text" value="${empty jpGqdCjrMx.gqXsfy ? '0' : jpGqdCjrMx.gqXsfy}" name="gqXsfyArr" datatype="number,dotformat,*" dotformat="###.##" nullmsg="请填写值" style="height: 20px;width:50px" onblur="changeXsfySum();changeYsje('${jpGqdCjrMx.tkno}');"/>
						 			</span>
						 		</td>
						 		<td style="text-align: center;vertical-align: middle;">
						 			<span id="ysje${jpGqdCjrMx.tkno}" style="color: red">
						 				${jpGqdCjrMx.gqXsfy + jpGqdCjrMx.gqXsscfy}
						 			</span>
						 		</td>
						 		<!-- 累计应收金额 -->
								<c:set var="sumGqfy" value="${sumGqfy + jpGqdCjrMx.gqXsfy + jpGqdCjrMx.gqXsscfy}"></c:set>
								<!-- 累计改签销售费 -->
								<c:set var="sumGqXsfy" value="${sumGqXsfy + jpGqdCjrMx.gqXsfy}"></c:set>
								<!-- 累计销售升舱费 -->
								<c:set var="sumGqXsscfy" value="${sumGqXsscfy + jpGqdCjrMx.gqXsscfy}"></c:set>
								
								<!-- 累计采购升舱费 -->
								<c:set var="sumGqCgscfy" value="${sumGqCgscfy + jpGqdCjrMx.gqCgscfy}"></c:set>
								<!-- 累计改签采购费-->
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
						<td style="color: red" align="center"><span id="cgschj">${sumGqCgscfy }</span></td>
						<td style="color: red" align="center"><span id="xsschj">${sumGqXsscfy }</span></td>
						<td style="color: red" align="center"><span id="cghj">${sumGqCgfy }</span></td>
						<td style="color: red" align="center"><span id="xshj">${sumGqXsfy}</span></td>
						<td style="color: red" align="center"><span id="yshj">${sumGqfy}</span></td>
					</tr>
				</c:if>
			</table>
		</td>
	</tr>
</table>