<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="bzInfo">
	<tr>
	    <td style="background:#efefef;height:90px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/bzxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">备注信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:100px;border:none;background:#f1f1f1;" id="_bzInfo" >
				<tr>
					<td class="headtitle" style="border:none;width:30px;">备注</td>
					<td style="border:none;">
						<textarea  rows="4" name="bzbz" datatype="*0-500" ignore="ignore" cols="95" style="background:#fff;">${kh_khdd_pn.BZBZ}</textarea>
						<font color='red'>最多500字符</font>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>