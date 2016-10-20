<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0"  style="width: 1000px">
<tbody> 
	<!--第1行 -->
		<tr>
			 <td class="ptzh_right">是否开启：</td>
			 <td class="ptzh_left">
				<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)" value="1"  ${ptzh.open1 eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)"  value="0"  ${empty ptzh.open1 or ptzh.open1 eq '0' ? 'checked' : ''}/>关闭
			</td>
			 <td class="ptzh_right">平台代码：</td>
			 <td class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>
			 <td class="ptzh_right">账号：</td>
			 <td class="ptzh_left">
				<input name="user1" type="text" class="input1" value="${ptzh.user1 }" style="width:220px">
			</td>
			 <td class="ptzh_right">密码：</td>
			 <td class="ptzh_left">
				<input name="pwd1" type="password" class="input1" value="${ptzh.pwd1 }" style="width:220px">
			</td>
		</tr>
		<!--第3行 -->
		<tr>
			 <td class="ptzh_right">是否开启自动代扣：</td>
			 <td class="ptzh_left">
				<input type="radio" name="sfkqdk"  value="1"  ${ptzh.sfkqdk eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="sfkqdk"  value="0"  ${empty ptzh.sfkqdk or ptzh.sfkqdk eq '0' ? 'checked' : ''}/>关闭
			</td>
			 <td class="ptzh_right">自动代扣方式：</td>
			<td class="ptzh_left">
				<select name="zddklx" id="${ptzh.ptzcbs }zddklx" onChange="hideZfzh(this.value,'${ptzh.ptzcbs }');">
				 	<option value="1" ${ptzh.zddklx eq '1' ? 'selected' : '' } >支付宝</option>
				 	<option value="2" ${ptzh.zddklx eq '2' ? 'selected' : '' } >财付通</option>
				</select>
			</td>
		</tr>
		<!--第4行 -->
		<tr>
			 <td class="ptzh_right">地址：</td>
			 <td class="ptzh_left">
				<input name="url1" type="text" class="input1" value="${empty ptzh.url1 ? 'http://webservice.517na.com/BenefitInterface.asmx' : ptzh.url1 }" style="width:220px">
			</td>
			<td class="ptzh_right">是否匹配特殊政策：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfpptxzc"  value="1"  ${empty ptzh.sfpptxzc or ptzh.sfpptxzc eq '1' ? 'checked' : '' }/>是
				<input type="radio" name="sfpptxzc"  value="0"  ${ptzh.sfpptxzc eq '0' ? 'checked' : '' }/>否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>  
</tbody>     
 </table>
 