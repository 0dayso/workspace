<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 800px" border="0">
	<tbody>
		<!--第1行 -->
		<tr>
			<c:if test="${ptzh.open1 eq '-1'}">
				<td colspan="2" style="color: red">系统已不支持该平台</td>
			</c:if>
			<c:if test="${ptzh.open1 ne '-1'}">
				<td style="width:160px" class="ptzh_right">是否开启：</td>
				<td style="width:200px" class="ptzh_left">
					<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)" value="1"  ${ptzh.open1 eq '1' ? 'checked' : ''}/>开启
					<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)"  value="0"  ${ptzh.open1 eq '0' ? 'checked' : ''}/>关闭
				</td>
			</c:if>
			<td style="width:140px" class="ptzh_right">平台代码：</td>
			<td style="width:300px" class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>
			<td class="ptzh_right">主账号：</td>
			<td class="ptzh_left">
				<input name="user1" type="text" class="input1" style="width:150px" title="用于查询政策" value="${ptzh.user1 }" >
			</td>
			<td class="ptzh_right">签名键值：</td>
			<td class="ptzh_left">
				<input type="text" name="pwd1" style="width:150px" value="${ptzh.pwd1 }" >
			</td>
		</tr>
		<!--第3行 -->
		<tr>
			<td class="ptzh_right">用户名：</td>
			<td class="ptzh_left">
				<input name="user2" type="text"  class="input1" value="${ptzh.user2 }" style="width:150px">
			</td>
			<td class="ptzh_right">地址：</td>
			<td class="ptzh_left">
				<span>
					<input name="url1" id="jinriurl1" type="text" style="width:150px" value="${empty ptzh.url1 ? 'http://policy.jinri.cn' : ptzh.url1 }" size="20">
					&nbsp;<br/><a href="http://www.jinri.cn/open.aspx?system=JRSYKJ" target="_blank" >点击后，用【用户名】登陆今日授权</a>
				</span>
			</td>
		</tr>
		<!--第4行 -->
		<tr>
			<td class="ptzh_right">是否开启自动代扣：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfkqdk"  value="1"  ${ptzh.sfkqdk eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="sfkqdk"  value="0"  ${ptzh.sfkqdk eq '0' ? 'checked' : ''}/>关闭
			</td>
			<td class="ptzh_right">自动代扣方式：</td>
			<td class="ptzh_left">
				<select name="zddklx" id="${ptzh.ptzcbs }zddklx" onChange="hideZfzh(this.value,'${ptzh.ptzcbs }');">
				 	<option value="1" ${ptzh.zddklx eq '1' ? 'selected' : '' } >支付宝</option>
				 	<option value="2" ${ptzh.zddklx eq '2' ? 'selected' : '' } >财付通</option>
				</select>
			</td>
		</tr>
		<!--第5行 -->
		<tr>
			<td class="ptzh_right">联系人：</td>
			<td class="ptzh_left">
				<input type="text" name="xx1" class="input1" value="${ptzh.xx1}" style="width:150px" />
			</td>
			<td class="ptzh_right">手机：</td>
			<td class="ptzh_left">
				<input type="text" name="xx2" style="width:150px" title="多个手机号用|分隔" value="${ptzh.xx2}"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
	</tbody>	      
</table>
