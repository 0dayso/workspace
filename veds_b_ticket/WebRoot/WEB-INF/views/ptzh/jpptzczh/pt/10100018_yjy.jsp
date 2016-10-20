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
			<td class="ptzh_right">平台代码：</td>
			<td class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>   
			<td class="ptzh_right">用户名：</td>
			<td class="ptzh_left">
				<input name="user1" type="text"  style="width: 220px" title="" value="${ptzh.user1 }" size="10">
			</td>
			<td class="ptzh_right">安全码：</td>
			<td class="ptzh_left">
				 <input type="text" name="pwd1"   style="width: 220px" value="${ptzh.pwd1 }" >	
			</td>
		</tr>
		<!--第3行 -->
		<tr>	
			<td class="ptzh_right">是否开启自动代扣：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfkqdk"  value="1"  ${ptzh.sfkqdk eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="sfkqdk"  value="0"  ${ptzh.sfkqdk eq '0' ? 'checked' : ''}/>关闭
			</td>
			<td class="ptzh_right">自动代扣方式：</td>
			<td class="ptzh_left">
				<select name="zddklx" id="${one.ptzcbs }zddklx" onChange="hideZfzh(this.value,'${one.ptzcbs }');">
				 	<option value="1" ${one.zddklx eq '1' ? 'selected' : '' } >支付宝</option>
				 	<option value="2" ${one.zddklx eq '2' ? 'selected' : '' } >财付通</option>
				</select>
			</td>
		</tr>
		<!--第4行 -->
		<tr>
			<td class="ptzh_right">是否匹配特殊政策：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfpptxzc"  value="1"  ${empty ptzh.sfpptxzc or ptzh.sfpptxzc eq '1' ? 'checked' : ''}/>是
				<input type="radio" name="sfpptxzc"  value="0"  ${ptzh.sfpptxzc eq '0' ? 'checked' : ''}/>否
			</td>
			<td class="ptzh_right">地址：</td>
			<td class="ptzh_left">
				 <input name="url1"  type="text" style="width: 220px" value="${empty ptzh.url1 ? 'http://119.254.20.38:18000' : ptzh.url1 }" title="查询政策地址">&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<!--第5行 -->
		<tr>
			<td class="ptzh_right"> 是否允许换编码：</td>
			<td colspan="3"  class="ptzh_left">
				<select name="xx5">
					<option value="0" ${empty ptzh.xx5 or ptzh.xx5 eq '0' ? 'selected' : '' }>不限制</option>
					<option value="1" ${ptzh.xx5 eq '1' ? 'selected' : '' }>不允许换编码</option>
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
 