<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- CPS -->
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 1100px">
	<tbody>
		<!--第1行 -->
		<tr>
			<c:if test="${ptzh.open1 eq '-1'}">
				<td colspan="2" style="color: red">系统已不支持该平台</td>
			</c:if>
			<c:if test="${ptzh.open1 ne '-1'}">
				<td class="ptzh_right">是否开启：</td>
				<td class="ptzh_left">
					<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)" value="1"  ${ptzh.open1 eq '1' ? 'checked' : ''}/>开启
					<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)"  value="0"  ${ptzh.open1 eq '0' ? 'checked' : ''}/>关闭
				</td>
			</c:if>
			<td class="ptzh_right">平台代码：</td>
			<td class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>
			<td class="ptzh_right">商户号：</td>
			<td class="ptzh_left">
				<input type="text" id="user1${ptzh.ptzcbs}" name="user1"  style="width:220px" value="${ptzh.user1}" /><font color="red">*</font>
			</td>
			<td class="ptzh_right">用户号：</td>
			<td class="ptzh_left">
				<input type="text" id="user2${ptzh.ptzcbs}" name="user2" style="width:220px" value="${ptzh.user2}" /><font color="red">*</font>
			</td>
		</tr>
		<!--第3行 -->
		<tr>
			<td class="ptzh_right">地 址：</td>
			<td class="ptzh_left">
				<input type="text" id="url1${ptzh.ptzcbs}" name="url1" style="width:220px" value="${ptzh.url1}" /><font color="red">*</font>
			</td>
			<td class="ptzh_right">密 钥：</td>
			<td class="ptzh_left">
				<input type="text" id="pwd1${ptzh.ptzcbs}" name="pwd1" style="width:220px" value="${ptzh.pwd1}" /><font color="red">*</font>
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
				<select name="zddklx" id="${one.ptzcbs }zddklx" onChange="hideZfzh(this.value,'${one.ptzcbs }');">
				 	<option value="1" ${one.zddklx eq '1' ? 'selected' : '' } >支付宝</option>
				 	<option value="2" ${one.zddklx eq '2' ? 'selected' : '' } >财付通</option>
				</select>
			</td>
		</tr>
		<!--第5行 -->
		<tr>
			<td class="ptzh_right">取退废票证明IP地址：</td>
			<td class="ptzh_left">
				<input type="text" value="${tp_ipaddress}" name="tp_ipaddress" style="width:220px"/> <font color="red">*</font>
			</td>
			<td class="ptzh_right">端口：</td>
			<td class="ptzh_left">
				<input type="text" value="${tp_port}" name="tp_port" style="width:60px"/><font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
	</tbody>     
</table>
<b>注意事项</b><br>
取退废票证明地址请设置为外网访问地址，如果没有设置，会引起提交退废票申请时，上传退废证明失败！