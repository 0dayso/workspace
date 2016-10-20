<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- BPET -->
<table  border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td height="30" width="190">
			出票页面是否开启BPET代购功能
		</td>
		<td title="出票页面是否开启BPET出票功能 ">
			<input type="radio" name="sfkq" id="sfkq_0" value="0" ${cpymsz.sfkq eq '0' ? 'checked' : '' } onclick="$('#bpetshow').hide()" class="validate-one-required" />
			<label for="sfkq_0">不开启</label> 
			<input type="radio" name="sfkq" id="sfkq_1" value="1" ${empty cpymsz.sfkq or cpymsz.sfkq eq '1' ? 'checked' : '' } onclick="$('#bpetshow').show()" class="validate-one-required" />
			<label for="sfkq_1">开启</label>
		</td>
	</tr>
	<tr>
		<td colspan="2"  height="30" style="text-align: center;">
			<input type="button" value=" 保 存 " class="ext_btn ext_btn_submit" onclick="toSave();">
			<input type="button" value=" 关 闭 " class="ext_btn ext_btn_submit" onclick="closeLayer();">
		</td>
	</tr>
</table>

<table  border="0" id="bpetshow" style="display: ${cpymsz.sfkq eq '0' ? 'none' : '' }" class="list_table">
	<tr>
		<td style="width:15%">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getHkgsB2Btree?pt=BPET&bca=720102" style="overflow-y:hidden;overflow-x:hidden;width:100%" name="bpetleft" id="bpetleft" height="680px" frameBorder="0"></iframe>
		</td>
		<td style="width:85%;text-align:right">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getB2bzffs" name="bpetright" id="bpetright" width="99%" height="680px" frameBorder="0"></iframe>
		</td>
	</tr>
</table>