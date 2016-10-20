<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
	    <table cellpadding="0" class="list_table" cellspacing="0" style="width: 1000px">
			<tbody>    
			   <tr class="odd">
			       <td style="width: 60px"><b style="color:green">采购</b><br><select name="open1" onchange="changeopen('${one.ptzcbs }','cg',this.value)">
			       <option value="1" ${one.open1 eq '1' ? 'selected':'' }>开启</option>
			       <option value="0" ${one.open1 eq '0' ? 'selected':'' }>关闭</option>
			       <input type="hidden" name="cpsOpen1" id="cpsOpen1">
			     </select></td>
			     <td><span id="cg${one.ptzcbs}" style="display:${one.open1 eq '1' ? '' : 'none' }">
			     	用户号<input type="text" id="user1${one.ptzcbs}" name="user1" class="input1 max-length-200 required" value="${one.user1}" title="由平台提供"/><font color="red">*</font>
			     	密 码 <input type="text" id="pwd1${one.ptzcbs}" name="pwd1" class="input1 max-length-200 required" value="${one.pwd1}" /><font color="red">*</font>
					地 址 <input type="text" id="url1${one.ptzcbs}" name="url1" class="input1 max-length-200 required" value="${one.url1}" /><font color="red">*</font>
			   		
			   		
      			 在线支付方式
      			 <select title="asms后台支付时，默认的支付方式" style="width: 140px" name="pwd2">
	       				<option value="ALIPAY" ${one.pwd2 eq 'ALIPAY' ? 'selected' : ''}>支付宝(ALIPAY)</option>
	       				<option value="TENPAY" ${one.pwd2 eq 'TENPAY' ? 'selected' : ''}>财付通(TENPAY)</option>
	       				<option value="CHINAPNR" ${one.pwd2 eq 'CHINAPNR' ? 'selected' : ''}>汇付(CHINAPNR)</option>
       			</select>
			   		
			   		
			   			<br>
			   			是否开启自动代扣
				   		<input type="checkbox" value="1" ${one.sfkqdk eq '1' ? 'checked' : '' } onclick="$('sfkqdk${one.ptzcbs}').value=(this.checked? '1' : '0')">
						<input name="sfkqdk" value="${one.sfkqdk}" type="hidden" id="sfkqdk${one.ptzcbs}">
			   			自动代扣类型
			   			<select name="zddklx" id="zddklx${one.ptzcbs}">
							<option value="ACCOUNTPAY" ${one.zddklx eq 'ACCOUNTPAY' ? 'selected' : ''}>账户支付(ACCOUNTPAY)</option>
	       					<option value="ALIAGENTPAY" ${one.zddklx eq 'ALIAGENTPAY' ? 'selected' : ''}>支付宝(ALIAGENTPAY)</option>
	       					<option value="TENPAY" ${one.zddklx eq 'TENPAY' ? 'selected' : ''}>财付通(TENPAY)</option>
	       					<option value="CHINAPNR" ${one.zddklx eq 'CHINAPNR' ? 'selected' : ''}>汇付(CHINAPNR)</option>
						</select>
						<a href="###" onclick="tdxsign();">签约</a>&nbsp;&nbsp;&nbsp;
						<script type="text/javascript">
							function tdxsign(){
								alert("当前平台不支持在线签约，请联系平台进行线下签约");
							}
						</script>
						是否开启自动改签<input type="checkbox" value="1" ${one.xx3 eq '1' ? 'checked' : '' } onclick="$('xx3${one.ptzcbs}').value=(this.checked? '1' : '0')">
						<input name="xx3" value="${one.xx3}" type="hidden" id="xx3${one.ptzcbs}">
					     外出票单位
				        <input type="text" id="cgdeptinput${one.ptzcbs}" class="input1" size="7" value="${cgdeptname.MC}"
					    onkeyup="getDeptListCg(this)" onClick="getDeptListCg(this,'1')" value_to_input="cgdept${one.ptzcbs}" onblur="checkCgdept(this,'cgdept','${one.ptzcbs}');">
	    				<span id="cgdeptinput${one.ptzcbs}_span" style="color:#ff0000;display:none;" class="cgdept_msg" open="${one.open1 }"></span>
					    <input name="cgdept"  type="hidden" class="input1" id="cgdept${one.ptzcbs}" value="${one.cgdept}" open="${one.open1 }">
						默认采购用户<input type="text" id="by3input${one.ptzcbs}" class="input1 required" size="6" value="${by3name.xm }" 
						onclick="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" onkeyup="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" value_to_input="by3${one.ptzcbs}" /><font color="red">*</font>
						<input type="hidden" name="by3" id="by3${one.ptzcbs}" value="${one.by3 }"><br>
						<select name="by1">
							<option value="1" ${one.by1 eq '1' ? 'selected' : '' }>对分销开放</option>
							<option value="0" ${one.by1 eq '0' ? 'selected' : '' }>不对分销开放</option>
						</select>
						<br>
						联系人<input type="text" name="xx1" class="input1 max-length-200 required" value="${one.xx1}"/>
						手机  <input type="text" name="xx2" class="input1 max-length-200 required" value="${one.xx2}"/>
						<span style="color:gray">[传给平台的联系人和手机，如果不设置，则使用订单上的]</span>
						<br>
					</span>
					</td>
			   </tr>
				</tbody>     
			 </table>
	  
	  