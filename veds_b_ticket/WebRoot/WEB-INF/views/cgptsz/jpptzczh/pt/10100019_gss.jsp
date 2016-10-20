<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
  		<!-- GSS -->
			<tbody>    
			   <tr class="odd">
			       <td style="width: 60px"><b style="color:green">采购</b><br><select name="open1" onchange="changeopen('${one.ptzcbs }','cg',this.value)">
			       <option value="1" ${one.open1 eq '1' ? 'selected':'' }>开启</option>
			       <option value="0" ${one.open1 eq '0' ? 'selected':'' }>关闭</option>
			       <input type="hidden" name="cpsOpen1" id="cpsOpen1">
			     </select></td>
			     <td><span id="cg${one.ptzcbs}" style="display:${one.open1 eq '1' ? '' : 'none' }">
				     	接口账号<input type="text" id="user1${one.ptzcbs}" name="user1" class="input1 max-length-200 required" value="${one.user1}" /><font color="red">*</font>
				     	登陆账号<input type="text" id="user2${one.ptzcbs}" name="user2" class="input1 max-length-200 required" value="${one.user2}" /><font color="red">*</font>
				     	登陆密码<input type="text" id="xx5${one.ptzcbs}" name="xx5" class="input1 max-length-200 required" value="${one.xx5}" /><font color="red">*</font>
				     	密钥<input type="text" id="pwd1${one.ptzcbs}" name="pwd1" class="input1 max-length-200 required" value="${one.pwd1}" /><font color="red">*</font>
				     	<br>
				     	地址 <input type="text" id="url1${one.ptzcbs}" name="url1" class="input1 max-length-200 required" value="${one.url1}" /><font color="red">*</font>
				     	待授权OFFICE <input name="xx1"   type="text"  class="input1" title="" value="${one.xx1 }" size="8">
			   			自动代扣类型
			   			<select name="zddklx">
							<option value="0" ${one.zddklx eq '0' ? 'selected' : '' }>支付宝账号</option>
							<option value="1" ${one.zddklx eq '1' ? 'selected' : '' }>GSS信用额度</option>
						</select>
			   			支付宝代扣账号
	 					<input name="url2" id="url2${one.ptzcbs}" type="text"  class="input1" title="" value="${one.url2 }" size="22">
	 					<span>(请登录GSS系统进行支付代扣账号的签约)</span>
	 					<br>
				   		是否对分销开启自动代扣
				   		<input type="checkbox" value="1" ${one.sfkqdk eq '1' ? 'checked' : '' } onclick="$('sfkqdk${one.ptzcbs}').value=(this.checked?'1':'0')">
						<input name="sfkqdk" value="${one.sfkqdk}" type="hidden" id="sfkqdk${one.ptzcbs}">
						<input type="hidden" name="xx3">
						<select name="by1">
							<option value="1" ${one.by1 eq '1' ? 'selected' : '' }>对分销开放</option>
							<option value="0" ${one.by1 eq '0' ? 'selected' : '' }>不对分销开放</option>
						</select>
					     外出票单位
				        <input type="text" id="cgdeptinput${one.ptzcbs}" class="input1" size="7" value="${cgdeptname.MC}"
					    onkeyup="getDeptListCg(this)" onClick="getDeptListCg(this,'1')" value_to_input="cgdept${one.ptzcbs}" onblur="checkCgdept(this,'cgdept','${one.ptzcbs}');">
	    				<span id="cgdeptinput${one.ptzcbs}_span" style="color:#ff0000;display:none;" class="cgdept_msg" open="${one.open1}"></span>
					    <input name="cgdept"  type="hidden" class="input1" id="cgdept${one.ptzcbs}" value="${one.cgdept}" open="${one.open1 }">
						默认采购用户<input type="text" id="by3input${one.ptzcbs}" class="input1 required" size="6" value="${by3name.xm }" 
						onclick="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" onkeyup="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" value_to_input="by3${one.ptzcbs}" /><font color="red">*</font>
						<input type="hidden" name="by3" id="by3${one.ptzcbs}" value="${one.by3 }"><br/>
						联系人<input type="text" name="xx2" class="input1 max-length-20" value="${one.xx2}" />手机<input type="text" name="xx4" class="input1 max-length-20" value="${one.xx4}" /><!-- 
						--><span style="color:gray">[传给GSS的联系人和手机，如果不设置，则使用订单上的]</span>
					</span>
					
					<br>
						<span style="color:gray">GSS平台待授权office号默认为[SZX485]，</span>
						<span style="color:gray">默认地址为[http://www.gssok.com]，如有变更，请以变更为准；</span>
						<br>
						平台登记通知地址：http:///webcontent/agent/gss.shtml
					</td>
			   </tr>
				</tbody>     
			 </table>
	