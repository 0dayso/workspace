<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="height:70px; width: 100%;margin-top: 1px;" id="skInfo">
	<tr>
	    <td style="background:#efefef;height:70px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/skxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">收款信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:70px;border:1px;background:#f1f1f1;">
				<c:if test="${jpkhdd.SKZT eq '0'}">
					<tr>
						<td style="border:none;">
							<span class="headtitle">应收金额</span>
							<span class="wb_td02"><font color="red"><b>￥ ${sumYsje+jpkhdd.XS_YJF}</b></font></span>
							<span class="headtitle">付款状态</span>
							<span class="wb_td02">未付</span>
							<span class="headtitle">收款科目</span>
							<span class="wb_td02">
								<select name="skkm" id="skkm" class="required" style="width: 200px">
								</select>
							</span>
						</td>
					</tr>
				</c:if>
				<c:if test="${jpkhdd.SKZT ne '0'}">
					<tr>
						<td style="border:none;">
							<span class="headtitle">应收金额</span>
							<span class="wb_td02"><font color="red"><b>￥ ${sumYsje+jpkhdd.XS_YJF}</b></font></span>
							<span class="headtitle">已收</span>
							<span class="wb_td02" style="text-align:right;">
									<font color="red"><b>￥ ${sumYsje+jpkhdd.XS_YJF}</b></font>
							</span>
							<span class="headtitle">收款科目</span>
							<span class="wb_td02">
								${jpkhdd.SKKM}
							</span>
						</td>
					</tr>
				</c:if>
				<tr>
					<td>
						联系人
						<input type="text" name="nxr" value="${jpkhdd.NXR}" datatype="*"  size="4" class="input1 max-length-200"  onblur="value=$.trim(this.value);"/>
						<font color="red">*</font>
						电话
						<input type="text" class="input1 max-length-60" id="nxdh" name="nxdh" ignore="ignore" value="${jpkhdd.NXDH}" size="9" maxlength="60"  onblur="value=$.trim(this.value);;checkNxdh();" datatype = "/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/" ignore="ignore" errormsg="请输入正确的电话号码（【3-4位区号】-【7-8位直播号码】-【1－4位分机号】）" maxlength="30"/>
						手机
						<input type="text" name="nxsj" id="nxsj" ignore="ignore" value="${jpkhdd.NXSJ}" datatype = "m" class="input1 validate-more-mobile-phone max-length-11" size="10" maxlength="11" onblur="value=$.trim(this.value);checkNxdh();"/>
						<font id="skxx_nxdh_error" style="display: none;" color='red'>联系电话机手机号码必须填一个</font>
					<td>
				</tr>
				<tbody id="xykInfo">
				</tbody>
			</table>
		</td>
	</tr>
</table>