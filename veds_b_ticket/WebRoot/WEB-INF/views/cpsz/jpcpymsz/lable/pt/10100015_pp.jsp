<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<table cellpadding="0" class="list_table" cellspacing="0" style="width: 700px" border="0">
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
			 <td style="width:140px" class="ptzh_right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台代码：</td>
			 <td style="width:300px" class="ptzh_left">${ptzh.ptzcbs}</td>
		</tr>
		<!--第2行 -->
		<tr>   
			 <td class="ptzh_right">账号：</td>
			 <td class="ptzh_left">
				<input name="user1" type="text"  style="width: 150px" title="" value="${ptzh.user1 }" size="10">
			</td>
			 <td class="ptzh_right">密码：</td>
			 <td class="ptzh_left">
				 <input type="password" name="pwd1"   style="width: 150px" value="${ptzh.pwd1 }" >	
			</td>
		</tr>
		<!--第3行 -->
		<tr>	
			 <td class="ptzh_right">安全码：</td>
			 <td class="ptzh_left">
				<input type="password" name="pwd2" style="width: 150px" value="${ptzh.pwd2}" size="10">
			</td>
			 <td class="ptzh_right">用户账号：</td>
			 <td class="ptzh_left">
				<input type="text" name="user2" style="width: 150px" value="${ptzh.user2}" >
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
				 <input name="url1"  type="text" style="width: 150px" value="${empty ptzh.url1 ? '' : ptzh.url1 }" title="查询政策地址">
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input value=" 保 存 " type="button" class="ext_btn ext_btn_submit" onclick="toSave();">
			</td>
		</tr>
     </tbody>
 </table>
 