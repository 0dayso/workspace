<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%--集中出票页面的js都放在此页面--%>
<!--航司B2B Start -->
<table width="100%" border="0" >
	<input type="hidden" id="dkhbm" name="dkhbm" value="${jpB2bHkgs.dkhbm}" />
	<input type="hidden" id="yhbh" name="yhbh" value="${jpB2bHkgs.dkhbm}" />	
	<c:forEach items="${b2bpolicyBeanList}" varStatus="vst" var="b2bpolicyBean">
	<c:set value="${vst.index}" var="ix"></c:set>
	<c:set var="ifxyj" value="${not empty b2bpolicyBean.agreementfee and b2bpolicyBean.agreementfee ne '-'}"></c:set>
         <tr>
         	<c:if test="${ix eq 0}">
         	<td width="" rowspan="${fn:length(b2bpolicyBeanList)}" class="compare_color">
            <input type="button" name="dgbutton" id="zdzf" value="自动支付" onclick="orderb2b('${jpKhdd.ddbh}','0')"; class="ext_btn ext_btn_submit">
            <input type="button" name="dgbutton" id="sdzf" value="手动支付" onclick="orderb2b('${jpKhdd.ddbh}','1');" class="ext_btn ext_btn_submit">
			</td>
           	</c:if>
           <td width="" class="compare_color">
           	<label>
                  <input type="radio" name="zcradio"  checked="${ix eq 0 ? 'checked':''}" value="${b2bpolicyBean.b2bpolicyid}" />B2B[${jpKhdd.hkgs}]
              </label>
           </td>
           <td width="" class="compare_color">
          	   BPET
           </td>
           <td width="" class="compare_color"><div class="font_orange font14">
           	<c:if test="${ifxyj}">${b2bpolicyBean.agreementfee}</c:if>
			<c:if test="${not ifxyj}">${b2bpolicyBean.pjhj }</c:if>
           </div></td>
           <td width="" class="compare_color"><div class="font_orange font14">
           	${empty b2bpolicyBean.dlfl ? 0.0 : b2bpolicyBean.dlfl}%<c:if test="${not empty b2bpolicyBean.qtfl}">+${b2bpolicyBean.qtfl}%</c:if>
           </div></td>
           <td width="" class="compare_color"><div class="font_orange font14">
           	<span title="票价*代理费率">${b2bpolicyBean.dlf}</span>
           </div></td>
           <td width="" class="compare_color"><div class="font_orange font16"><b>
           	<span title="票价+机建+税费-代理费">${b2bpolicyBean.totalfee}</span>
           </b></div></td>
           <td width="">按航司规定</td>
           <c:if test="${ix eq 0}">
           <td class="compare_color" rowspan="${fn:length(b2bpolicyBean.dlfl)}">
             	<c:set var="hassdzf" value="false"></c:set>
            <select class="select01" style="width:100px" id="b2b_zflx" name="zflx" onchange="">
            	<option value="" >=支付方式=</option>
            	<c:forEach items="${zffsStr}" var="payone">
            	<c:if test="${payone eq '1' && jpB2bHkgsxx.fs1 eq '1'}">
            	<option value="1" >支付宝</option>
            	</c:if>
            	<c:if test="${payone eq '2' && jpB2bHkgsxx.fs2 eq '1'}">
            	<option value="2" >财付通</option>
            	</c:if>
            	<c:if test="${payone eq '3' && jpB2bHkgsxx.fs3 eq '1'}">
            	<option value="3" >汇付天下</option>
            	</c:if>
            	<c:if test="${payone eq '4' && jpB2bHkgsxx.fs4 eq '1'}">
            	<option value="4" > 易 宝 </option>
            	</c:if>
            	<c:if test="${payone eq '5' && jpB2bHkgsxx.fs5 eq '1'}">
            	<option value="5" > 快 钱 </option>
            	</c:if>
            	<c:if test="${payone eq '6' && jpB2bHkgsxx.fs6 eq '1'}">
            	<option value="6" >御航宝</option>
            	</c:if>
            	<c:if test="${payone eq '7' && jpB2bHkgsxx.fs7 eq '1'}">
            	<option value="7" >易商旅</option>
            	</c:if>
            	<c:if test="${payone eq '8' && jpB2bHkgsxx.fs8 eq '1'}">
            	<option value="8" >易生卡</option>
            	</c:if>
            	<c:if test="${payone eq '9' && jpB2bHkgsxx.fs9 eq '1'}">
            	<option value="9" >易航宝</option>
            	</c:if>
            	</c:forEach>
            </select>
            <input type="text" class="input01 width100" id="b2b_zfzh" name="zfzh"/>
            <br>
           <select name="b2b_zf_fklx" id="b2b_zf_fklx" class="width120 select01" onchange="setDefaultB2b_zf_fklx('${jpKhdd.hkgs}')">
			   <option value="" style="color:blue;font-weight: bold;">选择系统对应科目</option>
			   <c:forEach  var="shzfkm" items="${shzfkmList}">
			   <option value="${shzfkm.kmbh}">${shzfkm.kmmc}</option>
			   </c:forEach>
			</select>
        	<input id="hszh" name="hszh" value="${hszh}" type="hidden" />
        	<input id="hsmm" name="hsmm" value="${hsmm}" type="hidden" />
        	<input id="office" name="office" value="${office}" type="hidden" />
			<input class="ext_btn ext_btn_submit" id="refpolicy" onclick="indexB2bPage('${jpKhdd.ddbh}',$('#b2b_policy_div'));" type="button" value="刷新"/>
           </td>
           </c:if>
         </tr>	
       </c:forEach>
   		<c:if test="${jpKhdd.hkgs eq 'CZ' and jpKhdd.hclx ne '1'}">
   		<tr>
   			<td width="" class="compare_color"></td>
   			<td colspan="8" class="compare_color">
   				<input type="checkbox" id="ifzhzg" name="ifzhzg"/>&nbsp;多段纵横中国（此选择有优惠,适用于多个运价的政策,勾选则表示不以上述价格支付）
   			</td>
   		</tr>
   		</c:if>
</table>
<script type="text/javascript">
	getDefaultB2b_zf_fklx(hkgs);
</script>