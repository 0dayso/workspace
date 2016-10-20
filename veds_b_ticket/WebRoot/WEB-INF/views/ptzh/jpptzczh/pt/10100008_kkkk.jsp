<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
  <input type="hidden" name="qqczok" value="${one.qqczok }">
  <input type="hidden" name="xx1" value="">
  <input type="hidden" name="xx2" value="">
  <input type="hidden" name="xx5" value="">
  <input type="hidden" name="xx6" value="">
  <input type="hidden" name="xx7" value="">
  <input type="hidden" name="zddklx" value="">
<table cellpadding="0" class="list_table" cellspacing="0">
<tbody>   
   <tr class="odd">
     <td style="width: 60px"><b style="color:green">采购</b><br><select name="open1" onchange="changeopen('${one.ptzcbs }','cg',this.value)">
       <option value="1" ${one.open1 eq '1' ? 'selected':'' }>开启</option>
       <option value="0" ${one.open1 eq '0' ? 'selected':'' }>关闭</option>
     </select></td>
     <td> <span id="cg${one.ptzcbs}" style="display:${one.open1 eq '1' ? '' : 'none' }">
      单位ID
	   <input name="user1" type="text"    class="input1" title="" value="${one.user1 }" size="10">
	   
	 安全码
	   <input type="text" name="pwd1"  class="input1" value="${one.pwd1 }" size="10">	
	   地址
       <input name="url1"   type="text" class="input1" value="${empty one.url1 ? '' : one.url1 }" size="10" title="查询政策地址">
        版本号
        <input type="text" name="user2" class="input1" value="${one.user2}" size="10" >	
        联系人电话：
        <input type="text" name="pwd2" class="input1" value="${one.pwd2}" size="10" title="航班变更的通知号码">	
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
		
		是否对分销开启自动代扣<input type="checkbox" value="1" ${one.sfkqdk eq '1' ? 'checked' : '' } onclick="$('sfkqdk${one.ptzcbs}').value=(this.checked ? '1' : '0');">
		<input name="sfkqdk" value="${one.sfkqdk}" type="hidden" id="sfkqdk${one.ptzcbs}">
		<input type="hidden" name="xx3">
		是否对分销开启自动退款<input type="checkbox" value="1" ${one.xx4 eq '1' ? 'checked' : '' } onclick="$('xx4${one.ptzcbs}').value=(this.checked?'1':'0')">
		<input name="xx4" value="${one.xx4}" type="hidden" id="xx4${one.ptzcbs}">
		支付地址
       <input name="url2"   type="text"  class="input1" title="" value="${empty one.url2 ? 'http://www.kkkk.com.cn/InterfacePay.aspx' : one.url2}" size="12">
		<br>
		默认采购用户<input type="text" id="by3input${one.ptzcbs}" class="input1" size="6" value="${by3name.xm }" 
		onclick="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" onkeyup="getUserList(this,{compid:'${VEASMS.compid }',qx:'1'})" value_to_input="by3${one.ptzcbs}" />
		<input type="hidden" name="by3" id="by3${one.ptzcbs}" value="${one.by3 }">
		
		是否匹配特殊政策<input type="checkbox" value="1" ${one.sfpptxzc eq '1' ? 'checked' : '' } onclick="$('sfpptxzc${one.ptzcbs}').value=(this.checked?'1':'0')">
		<input name="sfpptxzc" value="${one.sfpptxzc}" type="hidden" id="sfpptxzc${one.ptzcbs}">
		<br>平台登记支付通知地址：http:///webcontent/agent/kkkk.shtml
		</span>
		</td>
     </tr>
     </tbody>
 </table>
 
 