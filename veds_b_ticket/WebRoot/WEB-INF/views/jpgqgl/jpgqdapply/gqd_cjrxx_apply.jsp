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
			 	<!-- 根据查询申请改签页面用到 -->
			 	<c:if test="${not empty jpKhddCjrList}">
			 		<c:set var="cjrs" value="${fn:length(jpKhddCjrList)}"></c:set>
			 		<tr>
					 	<th align="center">
					 		<c:if test="${cjrs ne 1}">
					 			<input type="checkbox" id="cjrcheckall" onclick="checkAllCjr(this)"/>
					 		</c:if>
					 	</th>
					  	<th align="center">乘机人</th>
					  	<th align="center">证件号码</th>
						<th align="center">票号</th>
					   	<th align="center">销售价</th>
						<th align="center">机建</th>
						<th align="center">税费</th>
					   	<th align="center">改签费用</th>
			 		</tr>
			 		<c:forEach items="${jpKhddCjrList}" var="jpKhddCjr" >
			 			<c:set var="jpList" value="${jpKhddCjr.jpList}"></c:set>
			 			<c:set var="num" value="${fn:length(jpList)}"></c:set>
			 			<c:set var="cjrlx" value=""></c:set>
						<c:choose>
							<c:when test="${jpKhddCjr.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:when>
							<c:when test="${jpKhddCjr.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_et.gif"></c:set>
							</c:when>
							<c:when test="${jpKhddCjr.cjrlx eq '1'}">
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_ye.gif"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="cjrlx" value="${ctx}/static/images/wdimages/dd_cr.gif"></c:set>
							</c:otherwise>	
						</c:choose>
					  	<c:forEach items="${jpList}" var="jp" varStatus="j">
					  		<c:if test="${jpKhddCjr.nfgq eq '0' or jpKhddCjr.nfgq eq '1'}">
					  			<c:if test="${jp.nfgq eq '-1'}">
									<font color=red>[已退废]</font>
								</c:if>
								<c:if test="${jp.nfgq eq '-2'}">
									<font color=red>[改签中]</font>
								</c:if>
					  		</c:if>
				  			<tr>
				  				<c:if test="${j.index eq 0}">
				  					<td style="text-align: center;vertical-align: middle;" rowspan="${num}" >
				  						<c:if test="${jpKhddCjr.nfgq eq '-1'}">
											<font color=red>[已退废]</font>
										</c:if>
										<c:if test="${jpKhddCjr.nfgq eq '-2'}">
											<font color=red>[改签中]</font>
										</c:if>
				  						<c:if test="${jpKhddCjr.nfgq eq '0' or jpKhddCjr.nfgq eq '1' or jpKhddCjr.nfgq eq '2'}">
					  						<c:if test="${cjrs eq 1}">
					  							<input type="hidden" name="khddCjrIdArr" value="${jpKhddCjr.id}" />
					  						</c:if>
					  						<c:if test="${cjrs ne 1}">
					  							<input type="checkbox" id="cjrcheck${jpKhddCjr.id}" name="khddCjrIdArr" value="${jpKhddCjr.id}" tkno="${jp.tkno}" onclick="checkCjr('${jpKhddCjr.id}')" />
					  						</c:if>
				  						</c:if>
				  					</td>
						  			<td style="text-align: center;vertical-align: middle;" rowspan="${num}">${jpKhddCjr.cjr}<img src="${cjrlx}"/></td>
						  			<td style="text-align: center;vertical-align: middle;" rowspan="${num}">${jpKhddCjr.zjhm}</td>
					  			</c:if>
							  	<td style="text-align: center;vertical-align: middle;">${jp.tkno}</td>
							  	<td style="text-align: center;vertical-align: middle;">${jp.xsPj}</td>
							  	<td style="text-align: center;vertical-align: middle;">${jp.xsJsf}</td>
								<td style="text-align: center;vertical-align: middle;">${jp.xsTax}</td>
							  	<td style="text-align: center;vertical-align: middle;">
							  		<input type="text" name="gqXsfyArr" id="gqXsfy${jp.tkno}${jpKhddCjr.id}" value="0" datatype="*" nullmsg="请填写值" style="height: 20px;width:50px" ${cjrs eq 1 ? '' : 'disabled'} onblur="changeYsje()"/>
							  	</td>
				  			</tr>				  		
					  	</c:forEach>
			 		</c:forEach>	
			 	</c:if>
			 	
			 	<!-- 手工申请改签页面用到 -->
			 	<c:if test="${empty jpGqdCjrMap and empty jpKhddCjrList}">
			 		<tr>
					 	<th align="center">序号</th>
					  	<th align="center">乘机人</th>
					  	<th align="center">票号 </th>
						<th align="center">航程</th>
					   	<th align="center">乘机人类型</th>
						<th align="center">证件号码</th>
					   	<th align="center">采购改签费用</th>
					   	<th align="center">销售改签费用</th>
					   	<th align="center">
					   		操作<img src="${ctx}/static/images/wdimages/drop-add.gif" />
 						</th>
			 		</tr>
			 		<tr>
					 	<td align="center">1</td>
					  	<td align="center"><input type="text" value="" name="cjr" style="height: 20px;width:100px"/></td>
					  	<td align="center"><input type="text" value="" name="tkno" style="height: 20px;width:100px"/></td>
					  	<td align="center"><input type="text" value="" name="hc" style="height: 20px;width:100px"/></td>
					  	<td align="center">
					  		<select name="cjrlx" class="select">
					  			<option>==请选择==</option>
					  			<option>成人</option>
					  			<option>儿童</option>
					  			<option>婴儿</option>
					  		</select>
					  	</td>
					  	<td align="center"><input type="text" value="" name="zjhm" style="height: 20px;width:100px"/></td>
					  	<td align="center"><input type="text" value="" name="gqCgfy" style="height: 20px;width:100px"/></td>
					  	<td align="center"><input type="text" value="" name="gqXsfy" style="height: 20px;width:100px" class="xsfy"/></td>
					  	<td align="center"></td>
			 		</tr>
			 	</c:if>
			</table>
		</td>
	</tr>
</table>
