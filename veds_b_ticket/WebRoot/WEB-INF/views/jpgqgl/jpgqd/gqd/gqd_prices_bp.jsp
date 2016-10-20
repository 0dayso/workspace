<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
	<c:when test="${not empty gqDetails.ERROR}">
		${gqDetails.ERROR}
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${fn:length(personList) > 0}">
				<div style="height: 300px;overflow-y:auto;">
					<table class="fb_tab"  cellspacing="0" cellpadding="0"  align="center" style="width: 98%;">
						<tr>
							<th style="width:20%;">价格</th>
							<th style="width:20%;">账单价</th>
							<th style="width:20%;">税费</th>
							<th style="width:20%;">YQ</th>
							<th style="width:20%;">总费用</th>
						</tr>
						<tr>
							<td colspan="5">
								<c:forEach items="${personList}" var="vc" varStatus="vs">
									<table style="width: 100%" border="0" cellspacing="0" cellpadding="0" align="center" class="fb_tab">
									    <tr><td colspan="5">${vc.INDEX}. ${vc.PERSON}&nbsp;&nbsp;${vc.FOID}</td></tr>
								    	<c:set var="priceArr" value="${vc.PRICEARR}" />
								    	<c:set var="fareArr" value="${vc.FAREARR}" />
								    	<c:set var="taxArr" value="${vc.TAXARR}" />
								    	<c:set var="yqArr" value="${vc.YQARR}" />
								    	<c:set var="totalArr" value="${vc.TOTALARR}" />
								    	
								    	<c:forEach items="${priceArr}" var="price" varStatus="ps">
								    		<c:if test="${not empty priceArr[ps.index] }">
												<tr>
										    		<td>
										    			<c:set var="priceStr" value="${vc.INDEX}&${vc.FOID}&${vc.PERSON}&${priceArr[ps.index]}" />
										    			<input type="radio" name="price${vs.index}" value="${priceStr}" ${ps.index eq 0 ? 'checked' : '' }/>		    			
										    		</td>
										    		<td>${fareArr[ps.index] }</td>
										    		<td>${taxArr[ps.index] }</td>
										    		<td>${yqArr[ps.index] }</td>
										    		<td>${totalArr[ps.index] }</td>
										    	</tr>
									    	</c:if>
								    	</c:forEach>
								    </table>
							    </c:forEach>
							</td>
					   	</tr>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<b>${trdxPriceErr}</b><br/><br/><br/>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<p align="center">
	<c:if test="${fn:length(personList) > 0}">
		<input type="button" value="继续改签" class="asms_button" onclick="goonGq(${fn:length(personList)});">
	</c:if>
	<input type="button" value="取 消" class="asms_button" onclick="cancelGq();">
</p>



