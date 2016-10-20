<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- BOP -->
<table border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td height="30" width="190">出票页面是否开启BOP出票功能</td>
		<td title="是否开启BOP自动出票功能  ">
			<input type="radio" name="sfkq" id="sfkq_0" value="0" ${cpymsz.sfkq eq '0' ? 'checked' : '' } class="validate-one-required" onclick="$('#bopshow').hide()"/><label for="bopzdcp0">不开启</label>
			<input type="radio" name="sfkq" id="sfkq_1" value="1" ${empty cpymsz.sfkq or cpymsz.sfkq eq '1' ? 'checked' : '' } class="validate-one-required" onclick="$('#bopshow').show()"/><label for="bopzdcp1">开启</label>
		</td>
	</tr>
	
	<tr>
		<td colspan="2"  height="30" style="text-align: center;">
			<input type="button" value=" 保 存 " class="ext_btn ext_btn_submit" onclick="toSave();">
			<input type="button" value=" 关 闭 " class="ext_btn ext_btn_submit" onclick="closeLayer();">
		</td>
	</tr>
</table>

<table border="0" id="bopshow" style="display: ${cpymsz.sfkq eq '0' ? 'none' : '' }" class="list_table">
	<tr>
		<td>
			 <iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getOfficeList?pt=BOP" style="width:100%" name="bopleft" id="bopleft" height="380px" frameBorder="0"></iframe>
		</td>
	</tr>
</table>