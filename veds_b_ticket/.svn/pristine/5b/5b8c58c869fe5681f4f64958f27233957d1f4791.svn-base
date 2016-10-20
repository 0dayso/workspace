<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="sumYsje"  value="0"></c:set>
<tr id="cjrtr${index}" align="center">
	<td>
		<input name="cjr_cjrxm" id="cjr_cjrxm${index }" type="text"  value="${cjr.cjr}" class="input1  UpperCase" size="8" datatype="s"/> &nbsp;<font color='red'>*</font>
	</td>
	<td>
		<select name="cjr_cjrlx" id="cjr_cjrlx${index }">
			<option  value="1" ${(empty param.cjrlx or param.cjrlx eq  '1' or cjr.cjrlx eq '1' ) ? 'selected' : ''}>成人</option>
			<option  value="2" ${(param.cjrlx eq  '2' or cjr.cjrlx eq '2' ) ? 'selected' : ''}>儿童</option>
			<option  value="3" ${(param.cjrlx eq  '3' or cjr.cjrlx eq '3' ) ? 'selected' : ''}>婴儿</option>
		</select>
	</td>
	<td>
		<input name="cjr_zjhm" id="cjr_zjhm${index }" type="text"  value="${cjr.zjhm}" size="17" datatype="s"/><font color='red'>*</font>
	</td>
	<td><input name="cjr_sjhm" id="cjr_sjhm${index }" type="text"  value="${cjr.sjhm}" datatype="m" ignore="ignore" size="8"/></td> 
	<td>
		<input type="text" name="cjr_zdj"  id="cjr_zdj${index }" value="${cjr.pj_mj}" style="width: 50px;text-align:right;" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
	</td>
	<td>
		<input type="text" id="cjr_cgj${index}" name="cjr_cgj" value="${cjr.pj_cgj}"  style="width: 50px;text-align:right;" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
	</td>
	<td>
		<input type="text" name="cjr_xsj"  id="cjr_xsj${index }" value="${cjr.pj_xsj}" onblur="jsje('${index}')"  style="width: 50px;text-align:right;;"   datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
	</td>
	<td>
		<input type="text" id="cjr_jsf${index}" name="cjr_jsf" value="${cjr.pj_jsf}" onblur="jsje('${index}')"  style="width: 50px;text-align:right;" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
	</td>
	<td>
		<input type="text" id="cjr_tax${index}" name="cjr_tax" value="${cjr.pj_tax}" onblur="jsje('${index}')"  style="width: 50px;text-align:right;" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
	</td>
	<c:set var="sumYsje"  value="${sumYsje+cjr.pj_xsj+cjr.pj_jsf+cjr.pj_tax}"></c:set>
	<td align="center" class="red"  id="fpj_hj${index }">
		${cjr.pj_xsj+cjr.pj_jsf+cjr.pj_tax}
	</td>
	<input type="hidden" name="cjr_xsje" value="${cjr.pj_xsj+cjr.pj_jsf+cjr.pj_tax}"/>
	<td><img src="${ctx}/static/images/wdimages/drop-add.gif" title="点击添加乘机人" onclick="addTr('insertRow1','tpl1', -1);"/>&nbsp;
	<img src="${ctx}/static/images/wdimages/drop-no.gif" title="点击删除乘机人" onclick="delTr('insertRow1', this);"/></td>
</tr>
