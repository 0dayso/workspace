<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:set var="tkno_or_cjr" value="${sfcp eq 'N' ? '2' : '1'}"></c:set>
<!-- right -->
<table border="0" align="center" cellpadding="0" cellspacing="1" style="width:75%;border:0px solid #5A7D4A; top:40px;height:20px">
<tr style="text-align:right">
	<td>
		<span style="font-weight:bold;font-size:14px;color:black;">航空运输电子客票行程单</span>
		<span  style="margin:0px 10px;">
			行程单号：<input type="text" id="xcdhTemp"   class="input1" style="width:90px;color:#00f;" value="${jpxcd.xcdNo}"  maxlength="10"/>
		</span>
	</td>
</tr>
</table>
<div id="allDetailDiv">
  <span id="emptyXcdPage" disabled="disabled">
	 	<%@include file="printxcd_detail.jsp" %>
  </span>
  <c:forEach items="${jpjp_list}" var="tkno" varStatus="v">
  	<c:set var="xcdDIVid">${tkno.SZDH }${tkno.TKNO}</c:set>
	<div id="xcdprintinfoshowDIV${xcdDIVid}" style="display:none" alreadyload="false"></div>
 </c:forEach>
</div>
<div>
 	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" style="border:1px dotted gray;">
		<tr>
			<td align="right">
				<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
			      <tbody>
			        <tr>
			          <td align="left">
			         	&nbsp;&nbsp;&nbsp;温馨提示：
			          </td>
			          <td align="right">
			          	<span title="勾选表示每次打印前都进行预览"><input type="checkbox" id="asms_xcd_preprint_box" title="勾选表示每次打印前都进行预览" onclick="setPreprint();" >预览打印</span>
			          	&nbsp;
			            <span title="勾选表示固定使用210*102大小的行程单纸张，注意有个别打印机不支持，请不要勾选。"><input type="checkbox" id="asms_xcd_pagesize_fixed_box" title="勾选表示固定使用210*102大小的行程单纸张，注意有个别打印机不支持，请不要勾选。" onclick="setPageFontSize();" >行程单纸张</span>
				       &nbsp; 字体
				       <select onchange="setPageFontSize();" id="asms_xcd_pagesize_font_select">
				           <option value="TEC">航信行程单字体</option>
				           <option value="2">方正姚体简体</option>
				           <option value="3">长城小姚体</option>
				           <option value="4">宋体</option>
			           </select>
				        &nbsp;大小
				        <select onchange="setPageFontSize();" id="asms_xcd_pagesize_fontsize_select">
				           <option value="14">14</option>
				           <option value="15">15</option>
				           <option value="16">16</option>
				           <option value="17">17</option>
				           <option value="18">18</option>
			           </select>
			           &nbsp;
			             <c:if test="${param.asmsAndagent eq 'asms'}">
				    		 <c:if test="${false and not empty MK418746.ve_mkgnMap['07'] and param.asmsAndagent eq 'asms'}">
				     		 	<input onclick="printSet();" value="打印参数设置" type="button" class="asms_button" style="width:80px;" id="printSetBtn">
				    		 </c:if>
			    		 </c:if>
			          </td>
			        </tr>
			      </tbody>
		    </table>
			</td>
		</tr>
		<tr>
		  <td colspan="2">
			 &nbsp;&nbsp;&nbsp;如果没有安装打印控件或没有安装字体点击此处<a href="javascript:void(0);" onclick="enterFontTestPage();"><font color="red">[下载安装]</font></a>
			      ，第一次使用请务必阅读<a class="red" href="javascript:void(0)" onclick="enterPrintSmPage();">[行程单打印使用说明]</font></a>
		  </td>
		</tr>
		<tr>
			<td align="center">
				<input onclick="printXcdTest(this,'1','');" value="国内票模板" title="点击调整国内机票的打印模板，点击应用即可保存" type="button" class="ext_btn ext_btn_submit" id="printTestBtn1">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input onclick="printTestAjax(this,'0','');" value="国际票模板" title="点击调整国际机票的打印模板，点击应用即可保存" type="button" class="ext_btn ext_btn_submit" id="printTestBtn3">
			</td>
		</tr>
</table>
</div>
