<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 1100px">
<tbody> 
	<!--第1行 -->
		<tr>
			 <td class="ptzh_right">是否开启：</td>
			 <td class="ptzh_left">
				<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)" value="1"  ${ptzh.open1 eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)"  value="0"  ${ptzh.open1 eq '0' ? 'checked' : ''}/>关闭
			</td>
			 <td class="ptzh_right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台代码：</td>
			 <td class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>
			 <td class="ptzh_right">合作ID：</td>
			 <td class="ptzh_left">
				<input name="user1" type="text" style="width:220px" title="" value="${ptzh.user1 }" >	
			</td>
			 <td class="ptzh_right">  用户名：</td>
			 <td class="ptzh_left">
				<input name="user2" type="text"  style="width:220px" title="" value="${ptzh.user2 }" >
			</td>
		</tr>
		<!--第3行 -->
		<tr> 
			 <td class="ptzh_right">密码：</td>
			 <td class="ptzh_left">
				<input type="password" name="pwd1" style="width:220px" value="${ptzh.pwd1 }" >
			</td>
			 <td class="ptzh_right">地址：</td>
			 <td class="ptzh_left">
				 <input name="url1" type="text" style="width:220px" value="${empty ptzh.url1 ? 'http://ite2.baitour.com' : ptzh.url1 }"  title="查询政策和下单地址">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			</td>
		</tr>
		<!--第4行 -->
		<tr> 
			 <td class="ptzh_right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付：</td>
			 <td class="ptzh_left">
				<input type="text" name="url2" style="width:220px" value="${empty ptzh.url2 ? 'http://co.baitour.com/copayment/PortFlightPayment.aspx' : ptzh.url2}"  title="支付地址">
			</td>
			 <td class="ptzh_right">支付方式：</td>
			 <td class="ptzh_left">
				<select title="asms后台支付时，默认的支付方式" style="width: 140px" name="pwd2">
			       <option value="">==手工选择==</option>
			       <optgroup label="手动支付">
				       	<option value="alipay" ${ptzh.pwd2 eq 'alipay' ? 'selected' : ''}>支付宝手动(alipay)</option>
				       	<option value="99bill" ${ptzh.pwd2 eq '99bill' ? 'selected' : ''}>快钱手动(99bill)</option>
				       	<option value="creditCard" ${ptzh.pwd2 eq 'creditCard' ? 'selected' : ''}>信用卡手动(creditCard)</option>
			       	</optgroup>
			       	<optgroup label="自动支付">
				       	<option value="AUTOALIPAY" ${ptzh.pwd2 eq 'AUTOALIPAY' ? 'selected' : ''}>支付宝自动扣款(AUTOALIPAY)</option>
				       	<option value="AUTOTENPAY" ${ptzh.pwd2 eq 'AUTOTENPAY' ? 'selected' : ''}>财付通自动扣款(AUTOTENPAY)</option>
				       	<option value="AUTOBILLPAY" ${ptzh.pwd2 eq 'AUTOBILLPAY' ? 'selected' : ''}>	快钱自动扣款(AUTOBILLPAY)</option>
				       	<option value="AUTOCHINAPNR" ${ptzh.pwd2 eq 'AUTOCHINAPNR' ? 'selected' : ''}>	汇付自动扣款(AUTOCHINAPNR)</option>
			       	</optgroup>
		       	</select>
			</td>
		</tr> 
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
     </tbody>
 </table>
 