<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- CPS-官网 -->
<input type="hidden" name="sfkq"  value="1" >
<table  border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td>
			乘机人手机号码<input type="text" id="cgCjrSjhm" class="input1" name="cgCjrSjhm" size="80" value="${cpymsz.cgCjrSjhm}"/>
			<br><font color="gray">注:用于C站代购时航司要求输入乘机人手机号码，但是订单上乘机人手机号码为空时取值内容，多个手机号码用‘/’分隔。</font>
		</td>
	</tr>
	<tr>
		<td>
			是否开启自动XEPNR:<input type="radio" name="cgXepnr" value="1" ${cpymsz.cgXepnr eq '1' ? 'checked' : ''}>开启
			<input type="radio" name="cgXepnr" value="0" ${cpymsz.cgXepnr ne '1' ? 'checked' : ''} >不开启
			XE用户编号<input type="text" id="cgXeYhbh" class="input1" name="cgXeYhbh" size="15" value="${cpymsz.cgXeYhbh}"/>
		</td>
	</tr>
	<tr>
		<td colspan="2"  height="30" style="text-align: center;">
			<input type="button" value=" 保 存 " class="ext_btn ext_btn_submit" onclick="toSave();">
			<input type="button" value=" 关 闭 " class="ext_btn ext_btn_submit" onclick="closeLayer();">
		</td>
	</tr>
</table>

<table  border="0" id="czshow"  class="list_table">
	<tr>
		<td style="width:15%">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getHkgsB2Btree?pt=CZ&bca=720102" style="overflow-y:hidden;overflow-x:hidden;width:100%" name="czleft" id="czleft" height="680px" frameBorder="0"></iframe>
		</td>
		<td style="width:85%;text-align:right">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/viewcps_gwinfo" name="czright" id="czright" width="99%" height="680px" frameBorder="0"></iframe>
		</td>
	</tr>
</table>