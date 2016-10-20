<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:if test="${ not empty YEINFO}">
	<input type="hidden" id="yeinfo" value="0" name="yeinfo" />
</c:if>
<c:if test="${empty YEINFO}">
	<input type="hidden" id="yeinfo" value="1" name="yeinfo" />
</c:if>
<input type="hidden" id="kyye" value="${CPSKYYE}" name="kyye" />
<c:set value="true" var="hasTr"></c:set>
<table width="100%" border="0" class="odd">
	<c:forEach items="${segment.flights}" var="flight">
	    <c:if test="${jpKhdd.cgHbh eq flight.flightNo}">
			<c:set var="i" value="${flight.cabins[0].type == '掌尚飞' ? 1 : 0}"></c:set>
		</c:if>
		<c:forEach items="${flight.cabins}" var="cabin" varStatus="c">
			<c:if test="${jpKhdd.cgHbh eq flight.flightNo && c.index eq i}">
				<c:set value="false" var="hasTr"></c:set>
				<c:set var="cabins">${cabins}${cabin.cabin},</c:set>
				<c:set var="zfje">${(cabin.price)* adtnum + jpKhdd.cgJsf + param.cgTax}</c:set>
				<tr id="cztr${c.index}">
				  <td width="" class="compare_color">
				  	<input type="button" name="dgbutton" value="自动代购" json="{hkgs:'${segment.airline}',flightno:'${flight.flightNo}',cabin:'${cabin.cabin}',price:'${cabin.price }',ddbh:'${param.ddbh}',userid:'${shyhb.bh}',type:'${cabin.type}',coupon:'${cabin.discountCode}'}" onclick="autoorder(this);" class="ext_btn ext_btn_submit">
				  	<input type="button" name="dgbutton" value="手动代购" json="{hkgs:'${segment.airline}',flightno:'${flight.flightNo}',cabin:'${cabin.cabin}',price:'${cabin.price }',ddbh:'${param.ddbh}',userid:'${shyhb.bh}',type:'${cabin.type}',coupon:'${cabin.discountCode}'}" onclick="order(this);" class="ext_btn ext_btn_submit">
				  </td>
				  <td width="" class="compare_color">OP[CPS-官网]</td>
				  <td width=""><a style="color:#0433a0" onclick="gotohkgs('${param.asmsddbh}','${hkgs}','${cfcs}','${ddcs}','${cfsj}');"><span>${param.hkgs}</span></a></td>
				  <td width="" class="compare_color"><div class="font_orange font14"><fmt:formatNumber value="${cabin.price * adtnum}" pattern="#.##" /></div></td>
				  <td width="" class="compare_color">${cabin.type}${cabin.cabin}</td>
				  <td width="" class="compare_color">
				  		<span class="font_orange font16"><a onclick="loadB2CPolicy('${param.ddbh}');"><b>${zfje}</b></a></span>
				  		<span class="product01"  style="display:inline-block;">
							<c:if test="${not empty cabin.discountCode && not empty cabin.discountMoney}">
								<div title="优惠名称:${cabin.discountName }&#10;优惠金额:${cabin.discountMoney }(元)&#10;优惠码:${cabin.discountCode }&#10具体以官网活动为准"; style="cursor: pointer;">优惠${cabin.discountMoney}元</div>
							</c:if>
						</span>
				  </td>
				  <td width="">按航司规定</td>
				  <td width="">
				  	<div style="margin-bottom: 5px">
				  	<select name="auto_paymethod" id="auto_paymethod" class="width120 select01">
				  		<option value="">=选择支付方式=</option>
				  		<c:forEach items="${asmspaykindzd}" var="payone">
						<c:set var="fsname" value="${papykindmap[payone] }"></c:set>
							<c:if test="${payone ne '0'}">
								<option value="${payone }">${fsname }</option>
					        </c:if>
					   </c:forEach>
				  	</select>
				  	<input type="text" name="auto_zfzh" id="auto_zfzh" class="input01" width="140px"/>
				  	</div>
				  	<div>
			     	<select name="b2c_zf_fklx" id="b2c_zf_fklx" style="width: 170px"  class="width120 select01">
					   <option value="" style="color:blue;font-weight: bold;">选择系统对应科目</option>
						<c:forEach  var="shzfkm" items="${shzfkmList}">
							<option value="${shzfkm.kmbh}">${shzfkm.kmmc}</option>
						</c:forEach>
					</select>
				  	<select name="b2c_hs_fkkm" id="b2c_hs_fkkm" class="width120 select01">
				  		<option value="">航司支付科目</option>
						
						<c:if test="${param.hkgs eq 'MF'}">
				  			<option value="商旅卡">商旅卡</option>
			  			</c:if>
			  			<c:if test="${param.hkgs eq 'CA'}">
				  			<option value="凤凰卡">凤凰卡</option>
						</c:if>
			  			<c:if test="${param.hkgs eq 'PN'}">
				  			<option value="ICBCB2C">工商银行银联支付</option>
						</c:if>
						<c:if test="${param.hkgs eq 'MU'}">
				  			<option value="E行卡">E行卡</option>
						</c:if>
						<c:forEach items="${asmspaykindsd}" var="payone">
						<c:set var="fsname" value="${papykindmap[payone] }"></c:set>
							<c:if test="${payone ne '0'}">
								<option value="${payone }">${fsname }</option>
					        </c:if>
					   </c:forEach>
				  	</select>
				  	</div>
				  </td>
				</tr>
			</c:if>
		</c:forEach>
	</c:forEach>
	<c:if test="${hasTr}">
	    <tr id="cztr">
	      <td width="" class="compare_color"><input type="button" name="button" id="button" value=" 代 购 " class="ext_btn ext_btn_submit"></td>
	      <td width="" class="compare_color">OP[CPS-官网]</td>
	      <td width="" class="compare_color">
	      </td>
	      <td width="" class="compare_color"><input type="button" name="button" value="获取价格" class="ext_btn ext_btn_submit" onclick="loadB2CPolicy('${param.asmsddbh}', '${hkgs}', '${hkgspnr}', '${hbh}', '${tax}', '${yq}', '${hclx}', '${cjr}', '${cw}');"></td>
	      <td width="" >&nbsp;</td>
	      <td><a href="#">&nbsp;</a></td>
	    </tr>
	</c:if>	
</table>
