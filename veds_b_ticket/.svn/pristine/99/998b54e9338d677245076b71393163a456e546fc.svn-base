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
				<input name="user1" type="text"  id="yi8kuser1" style="width: 220px"  title="" value="${ptzh.user1 }" >
			</td>
			<td class="ptzh_right">密码：</td>
			<td class="ptzh_left">
				 <input type="text" name="pwd1" id="yi8kpwd1" style="width: 220px" value="${ptzh.pwd1 }" size="6">	
			</td>
		</tr>
		<!--第3行 -->
		<tr>
			<td class="ptzh_right">是否开启自动代扣：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfkqdk"  value="1"  ${ptzh.sfkqdk eq '1' ? 'checked' : ''}/>开启
				<input type="radio" name="sfkqdk"  value="0"  ${ptzh.sfkqdk eq '0' ? 'checked' : ''}/>关闭
			</td>
			<td class="ptzh_right">支付宝账号：</td>
			<td class="ptzh_left">
				<input name="url2" id="yi8kby1" type="text"  style="width: 220px" title="" value="${ptzh.url2 }" size="12">
				<a style="${ptzh.sfkqdk eq '1' ? '' : 'display:nptzh' }" href="javascript:void(0);" id="yi8000qianyue" onclick="getpaySignOnUrl();">签约</a>
			</td>
		</tr>
		<!--第4行 -->
		<tr>
			<td class="ptzh_right">地址：</td>
			<td class="ptzh_left">
				<input name="url1" id="yi8kurl1" type="text" style="width: 220px" value="${empty ptzh.url1 ? '' : ptzh.url1 }" title="查询政策地址"> 
			</td>
			<td class="ptzh_right">是否匹配特殊政策：</td>
			<td class="ptzh_left">
				<input type="radio" name="sfpptxzc"  value="1"  ${empty ptzh.sfpptxzc or ptzh.sfpptxzc eq '1' ? 'checked' : ''}/>是
				<input type="radio" name="sfpptxzc"  value="0"  ${ptzh.sfpptxzc eq '0' ? 'checked' : ''}/>否&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
     </tbody>
 </table>
 