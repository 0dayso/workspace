<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- OP -->
<table border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td  height="30" width="190" align="right">
			出票页面是否开启OP出票功能
		</td>
		<td title="是否开启OP自动出票功能 ">
			<input type="radio" name="sfkq" id="sfkq_0" value="0" ${cpymsz.sfkq eq '0' ? 'checked' : '' } onclick="$('#opshow').hide();$('#opshow_j').hide();" class="validate-one-required" />
			<label for="sfkq_0">不开启</label>
			<input type="radio" name="sfkq" id="sfkq_1" value="1" ${empty cpymsz.sfkq or cpymsz.sfkq eq '1' ? 'checked' : '' }  onclick="$('#opshow').show();$('#opshow_j').show();" class="validate-one-required" />
			<label for="sfkq_1">开启</label>
		</td>
	</tr>
</table>

<table id="opshow" style="display: ${empty cpymsz.sfkq or cpymsz.sfkq eq '1' ? '' : 'none' }"  border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr id="opdk">
		<td  height="30" width="190" align="right">
			平台出票时是否开启代扣
		</td>
		<td title="集中出票设置 OP平台出票时是否开启代扣  ">
			<input type="radio" name="opDq" id="opDq_0" value="0" ${cpymsz.opDq eq '0' ? 'checked' : '' } class="validate-one-required">
			<label for="opDq_0">不开启</label>
			<input type="radio" name="opDq" id="opDq_1" value="1" ${cpymsz.opDq eq '1' ? 'checked' : '' } class="validate-one-required" <label for="opDq_1">开启</label>
		</td>
	</tr>
	<tr id="opsq">
		<td  height="30" width="190" align="right">
			采购支付异常订单是否专人处理
		</td>
		<td title="集中出票设置  采购支付异常订单是否专人处理>
			<input type="radio" name="opZfyc" id="opZfyc_0" value="0" ${cpymsz.opZfyc eq '0' ? 'checked' : '' } class="validate-one-required" onclick="$('#opZfycYhbh').hide();$('#opZfycYhbhSpan').hide();"/>
			<label for="opZfyc_0">不开启</label>
			<input type="radio" name="opZfyc" id="opZfyc_1" value="1" ${cpymsz.opZfyc eq '1' ? 'checked' : '' } class="validate-one-required" onclick="$('#opZfycYhbh').show();$('#opZfycYhbhSpan').show();"/>
			<label for="opZfyc_1">开启</label>
			<input type="text" name="opZfycYhbh" id="opZfycYhbh"  value="${cpymsz.opZfycYhbh}" style="width: 140px;display:${empty cpymsz.opZfyc or cpymsz.opZfyc eq '0' ? 'none' : ''};"
			class="inputred required " title="针对支付不成功，或支付成功后出票失败的订单，指定专人来进行处理授权，无论订单是否支付成功，除了指定的工号外，其他工号都不允许再次支付出票。"/>
			<br/><span id="opZfycYhbhSpan"  style="display:${empty cpymsz.opZfyc or cpymsz.opZfyc eq '0' ? 'none' : ''}">(多个工号之间用半角逗号隔开:例如<font color="red">0000,1111,2222</font>)</span>
		</td>
	</tr>
	<tr id="op">
		<td height="30" width="190" align="right">
			按出发城市过滤
		</td>
		<td>
			<input type="text" name="opGlCfcs" id="opGlCfcs" value="${cpymsz.opGlCfcs}" style="width: 140px;"
			class="inputred required " onblur="this.value=(this.value).toUpperCase();"
			title="出票时，根据设置的出发城市判断，是否不调用平台接口获取政策,以节省平台流量,用于代理人出港政策一般不使用平台的情况.设置的是城市三字 码，多个城市用逗号分割">
			<br/>(为空表示不限，多个城市之间用‘/’分隔:例如<font color="red">WUH/PEK/SHA</font>)
		</td>
	</tr>
	<tr>
		<td height="30" width="190" align="right">
			按航空公司过滤
		</td>
		<td>
        	<input type="text" name="opGlHkgs" id="opGlHkgs" value="${cpymsz.opGlHkgs}" style="width: 140px;"
        	 	class="inputred required " onblur="this.value=(this.value).toUpperCase();"
        	 	title="出票时,根据设置的航空公司判断,是否不调用平台接口获取政策,以节省平台流量,用于代理人出港政策一般不使用平台的情况.设置的是航空公司码,多个城市用逗号分割">
        	<br/>(为空表示不限，多个航空公司之间用‘/’分隔:例如<font color="red">AA/BB/CC</font>)
		</td>
	</tr>
	<tr>
		<td height="30" width="190" align="right">
			按OFFICE号过滤
		</td>
		<td>
       	 	<input type="text" name="opGlOffice" id="opGlOffice" value="${cpymsz.opGlOffice}" style="width: 140px;"
       	 	class="inputred required" onblur="this.value=(this.value).toUpperCase();"
       	 	title="出票时,根据设置的OFFICE号判断,是否不调用平台接口获取政策,以节省平台流量,用于代理人出港政策一般不使用平台的情况.设置的是OFFICE号,多个OFFICE号用逗号分割">
       	 	<br/>(为空表示不限，多个OFFICE号之间用‘/’分隔:例如<font color="red">OFFICE1/OFFICE2</font>)
		</td>
	</tr>
	<tr>
		<td align="right">
			授权office是否取设置
		</td>
		<td>
			<input type="radio" name="officeSqsz" onclick="$('.officepz').hide();" value="0" ${cpymsz.officeSqsz ne '1' ? 'checked' : ''}/>取订单
			<input type="radio" name="officeSqsz" onclick="$('.officepz').show();" value="1" ${cpymsz.officeSqsz eq '1' ? 'checked' : ''}/>取设置
		</td>
	</tr>
	<tr class="officepz" style="display:${cpymsz.officeSqsz ne '1' ? 'none' : ''}">
		<td align="right">PNR授权office</td>
		<td><input type="text" name="pnrOffice" value="${cpymsz.pnrOffice}" style="width: 140px;"/></td>
	</tr>
	<tr class="officepz" style="display:${cpymsz.officeSqsz ne '1' ? 'none' : ''}">
		<td align="right">票号授权office</td>
		<td><input type="text" name="tknoOffice" value="${cpymsz.pnrOffice}" style="width: 140px;"/></td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td colspan="2"  height="30" style="text-align: center;">
			<input type="button" value=" 保 存 " class="ext_btn ext_btn_submit" onclick="toSave();">
			<input type="button" value=" 关 闭 " class="ext_btn ext_btn_submit" onclick="closeLayer();">
		</td>
	</tr>
</table>

<table border="0" id="opshow_j" style="display: ${empty cpymsz.sfkq or cpymsz.sfkq eq '1' ? '' : 'none' };width:98%" >
	<tr>
		<td style="width:200px">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getPtzhtree?pt=OP" style="overflow-y:hidden;overflow-x:hidden;width:100%" name="ptzcleft" id="ptzcleft" height="680px" frameBorder="0"></iframe>
		</td>
		<td style="width:1000px;text-align:right">
			<iframe src="${ctx}/vedsb/cpsz/jpcpymsz/getPtFzsz" name="ptzcright" id="ptzcright" width="99%" height="680px" frameBorder="0"></iframe>
		</td>
	</tr>
</table>