<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 1000px">
<tbody>   
   <tr class="odd">
     <td style="width: 60px"><b style="color:green">采购</b><br><select name="open1" onchange="changeopen('${one.ptzcbs }','cg',this.value)">
       <option value="1" ${one.open1 eq '1' ? 'selected':'' }>开启</option>
       <option value="0" ${one.open1 eq '0' ? 'selected':'' }>关闭</option>
     </select></td>
     <td> <span id="cg${one.ptzcbs}" style="display:${one.open1 eq '1' ? '' : 'none' }">
      接口账号
	   <input name="user1" type="text"  class="input1" title="" value="${one.user1 }" size="7">
	  接口密匙
	   <input type="text" name="pwd1" class="input1" value="${one.pwd1 }" size="10">	
	 登陆网赢账号
	 <input type="text" name="user2" value="${one.user2}"  size="6" class="input1">
	 
	   政策地址
       <input name="url1" type="text" class="input1" value="${empty one.url1 ? 'http://121.37.42.89:8080/opps/UserInterfaceServlet' : one.url1 }" size="20" title="政策及航班接口请求地址">
       订单地址
       <input name="url2" type="text" class="input1" value="${empty one.url2 ? 'http://opps.et-win.com/opps/UserInterfaceServlet' : one.url2 }" size="20" title="订单接口请求地址">
	 <br>支付方式
	 <c:set var="wy_pwd2" value="${fn:split(one.pwd2, ':')}"></c:set>
	 <select id="wy_paymentType" >
	 	<option value="03" ${wy_pwd2[0] eq '03' ? 'selected' : '' } >03:支付宝代扣</option>
	 	<option value="02" ${wy_pwd2[0] eq '02' ? 'selected' : '' } >02:预付金</option>
	 </select>
	预付金/支付宝账号
	 <input value="${wy_pwd2[1]}" id="wy_account" size="20" class="input1">
	 预付金密码(选择预付金时必填)
	 <input value="${wy_pwd2[2]}" id="wy_password" size="20" class="input1">
	 
     <input name="pwd2" type="hidden" id="wy_pwd2" class="input1" value="${one.pwd2}" size="10">
     
      外出票单位
       <input type="text" id="cgdeptinput${one.ptzcbs}" class="input1" size="7" value="${cgdeptname.MC}"
	    onkeyup="getDeptListCg(this)" onClick="getDeptListCg(this,'1')" value_to_input="cgdept${one.ptzcbs}" onblur="checkCgdept(this,'cgdept','${one.ptzcbs}');">
	    <span id="cgdeptinput${one.ptzcbs}_span" style="color:#ff0000;display:none;" class="cgdept_msg" open="${one.open1 }"></span>
	    <input name="cgdept"  type="hidden" class="input1" id="cgdept${one.ptzcbs}" value="${one.cgdept }" open="${one.open1 }">
	     <br>
		<select name="by1">
			<option value="1" ${one.by1 eq '1' ? 'selected' : '' }>对分销开放</option>
			<option value="0" ${one.by1 eq '0' ? 'selected' : '' }>不对分销开放</option>
		</select>
		
		是否对分销开启自动代扣<input type="checkbox" value="1" ${one.sfkqdk eq '1' ? 'checked' : '' } onclick="$('sfkqdk${one.ptzcbs}').value=(this.checked ? '1' : '0')">
		<input name="sfkqdk" value="${one.sfkqdk}" type="hidden" id="sfkqdk${one.ptzcbs}">
		<input type="hidden" name="xx3">
		是否对分销开启自动退款<input type="checkbox" value="1" ${one.xx4 eq '1' ? 'checked' : '' } onclick="$('xx4${one.ptzcbs}').value=(this.checked?'1':'0')">
		<input name="xx4" value="${one.xx4}" type="hidden" id="xx4${one.ptzcbs}">
		<br>
		默认采购用户<input type="text" id="by3input${one.ptzcbs}" class="input1" size="6" value="${by3name.xm }" 
		onclick="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" onkeyup="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" value_to_input="by3${one.ptzcbs}" />
		<input type="hidden" name="by3" id="by3${one.ptzcbs}" value="${one.by3 }">
		
		是否匹配特殊政策<input type="checkbox" value="1" ${one.sfpptxzc eq '1' ? 'checked' : '' } onclick="$('sfpptxzc${one.ptzcbs}').value=(this.checked?'1':'0')">
		<input name="sfpptxzc" value="${one.sfpptxzc}" type="hidden" id="sfpptxzc${one.ptzcbs}">
		</span>
		</td>
     </tr>
     </tbody>
 </table>
 