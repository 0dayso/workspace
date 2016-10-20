<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 1000px" border="0">
	<input type="text" id="user1${ptzh.ptzcbs}" name="user1" readonly="readonly" style="display: none" class="input1 max-length-200 required" value="21549144" title="由平台提供"/>
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
					<input type="radio" name="open1" onclick="changeopen('${ptzh.ptzcbs }','cg',this.value)"  value="0"  ${empty ptzh.open1 or ptzh.open1 eq '0' ? 'checked' : ''}/>关闭
				</td>
			</c:if>
			<td class="ptzh_right">平台代码：</td>
			<td class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>
			<td class="ptzh_right">sessionkey：</td>
			<td class="ptzh_left">
				<input type="text" id="url2${ptzh.ptzcbs}" name="url2" class="input1 max-length-200 required" value="${ptzh.url2}" />
			</td>
			<td class="ptzh_right">地 址：</td>
			<td class="ptzh_left">
				<input type="text" id="url1${ptzh.ptzcbs}" name="url1" class="input1 max-length-200 required" value="${empty ptzh.url1 ? 'http://121.196.129.162:30001/airs/conver.shtml' : ptzh.url1}" /><font color="red">*</font>
			</td>
		</tr>
		<!--第3行 -->
		<tr> 
			<td class="ptzh_right">
					是否开启自动退票
					<input type="checkbox" name="xx4" id="xx4${ptzh.ptzcbs}" value="${empty ptzh.xx4 ? '0':'1'}" ${ptzh.xx4 eq '1' ? 'checked' : '' } onclick="this.value=(this.checked ? '1' : '0')">
			</td>
			<td class="ptzh_left">
					是否开启自动改签
					<input type="checkbox" name="xx3" id="xx3${ptzh.ptzcbs}" value="${empty ptzh.xx3 ? '0':'1'}" ${ptzh.xx3 eq '1' ? 'checked' : '' } onclick="this.value=(this.checked ? '1' : '0')">
			</td>
			<td class="ptzh_right">淘宝网采购登录账号：</td>
			<td class="ptzh_left">
				<input type="text" name="xx7" id="tbcgdlzh" class="input1" size="14" value="${ptzh.xx7}">
			</td>
		</tr>
		<!--第4行 -->
		<tr>
			<td class="ptzh_right"><a href="javascript:void(0)" onclick="taobaokey(${ptzh.ptzcbs})" >获取授权Sessionkey</a></td>
			<td class="ptzh_left">
				 请在淘宝注册这个回调地址：
			</td>
			<td class="ptzh_right">http:///webcontent/agent/wdsession.shtml</td>
			<td class="ptzh_left">
				 <a target="_blank" href="http://jipiao.trip.taobao.com/agreement.htm">去签约</a>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
	</tbody>   
</table>
	  
	  