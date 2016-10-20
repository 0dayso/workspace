<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<tr class="red" id="hctr${hd_index }" align="center">
	<td>
		<input name="hd_id" id="id${hd_index }" value="" type="hidden">
		<input name="hc_index" id="hc_index${hd_index }" value="${hd_index }" type="hidden">
		<input name="hd_hbh" id="hbh${hd_index }"  maxlength="6" type="text" style="width:60px;"  onkeyup="value =value.toUpperCase();" value="${hd.hbh }" datatype="*1-7">&nbsp;<font color='red'>*</font>
	</td>
	<td align="center">
		<input name="hd_cityname" class = "city" id="cityname___" type="text"  style="width: 70px" 
		        value_to_input="cfcity${hd_index }" 
		        value = "${hd.cfcityName }" onfocus="showCity('cityname___', 'cfcity___');" datatype="s"/>
		<font color='red'>*</font>
		<input id="cfcity___" class = "city"  type="hidden" name="hd_cfcity" value="${hd.cfcity }"/>
	</td>
    <td align="center">
         <input name="hd_ddcityname" class = "city" id="ddcityname___" type="text"  style="width: 70px"
         			value_to_input="ddcity${hd_index }"  
			        value = "${hd.ddcityName }" onfocus="showCity('ddcityname___', 'ddcity___');" datatype="s"/>
		<font color='red'>*</font>
		<input  id="ddcity___" class = "city" type="hidden" name="hd_ddcity" value="${hd.ddcity }"/>
	</td>
	<td align="center">
		<input type="text" name="hd_cfsj" id="cfsj${hd_index }" value="${hd.cfsj}" class="input-text Wdate" size="13" id="mindate" datatype="*" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>&nbsp;
		<font color='red'>*</font>
	</td>
	<td align="center">
		<input type="text" name="hd_ddsj" id="ddsj${hd_index }" value="${hd.bzbz}" class="input-text Wdate" size="13" id="mindate" datatype="*" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>&nbsp;
		<font color='red'>*</font>
	</td>
	<td>
		<input name="hd_cw" id="cw${hd_index }"  type="text" maxlength="1" style="width:40px;display: ${empty hd.hbh or hd.hbh ne 'ARNK' ? '' : 'none' }" value="${empty hd.hbh or hd.hbh ne 'ARNK' ? hd.cw : '-'}" datatype="s"  onblur="value = value.toUpperCase();"/>
		&nbsp;<span id="cwspan${hd_index }" style="display: ${empty hd.hbh or hd.hbh ne 'ARNK' ? '' : 'none' }"><font color='red'>*</font></span>
	</td>
	<td><input name="hd_fjjx" id="fjjx${hd_index }" value="${hd.fjjx }" style="width:40px;"  onblur="value = value.toUpperCase();"/></td>
	<td><input name="hd_cfhzl" value="${hd.cfhzl }" style="width:40px;"  onblur="value = value.toUpperCase();"/></td>
	<td><input name="hd_ddhzl" value="${hd.ddhzl }" style="width:40px;"  onblur="value = value.toUpperCase();"/></td>
	<td><img src="${ctx}/static/images/wdimages/drop-add.gif" title="点击添加航程" onclick="addTr('insertRow', 'tpl', -1);"/>&nbsp;
	<img src="${ctx}/static/images/wdimages/drop-no.gif" title="点击删除航程" onclick="delTr('insertRow', this);"/></td>
</tr>