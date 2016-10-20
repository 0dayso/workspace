<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="height:70px; width: 100%;margin-top: 1px;" id="skInfo">
	<tr>
	    <td style="background:#efefef;height:70px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/skxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">收款信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table cellpadding="0" cellspacing="0" align="center" style="float:left;width:100%;height:70px;border:none;background:#f1f1f1;">
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
							<span>
								${empty jpkhdd.SKKM ? "无" : jpkhdd.EX.SKKM.kmmc}
							</span>
						</td>
					</tr>
				</c:if>
				<tr>
					<td>
					    <span class="fl">
							<label class="headtitle">联&nbsp;系&nbsp;人</label>
							<span>
								<input maxlength="30" style="width:70px;" value="${jpkhdd.NXR}" id="ct_nxr" name="nxr" datatype="*1-20"/>
								<font color='red'>*</font>
							</span>
						</span>
						<span class="fl">
							<label class="headtitle">电话号码</label>
							<span class="red">
							     <%--支持电话号码格式: 3-4位区号，7-8位直播号码，1－4位分机号--%>
								<input id="ct_nxrdh" name="nxdh" value="${jpkhdd.NXDH}" style="width:80px;" onblur="checkNxdh();" datatype = "/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/" ignore="ignore" errormsg="请输入正确的电话号码（【3-4位区号】-【7-8位直播号码】-【1－4位分机号】）" maxlength="30"/>
							</span>
						</span>
						<span class="fl">
							<label class="headtitle">手机号码</label>
							<span class="red">
								<input maxlength="30" style="width:90px;" value="${jpkhdd.NXSJ}" datatype = "m" ignore="ignore" id="nxsj" onblur="checkNxdh();" name="nxsj"/>
							</span>
						</span>
						<font id="skxx_nxdh_error" style="display: none;" color='red'>联系电话机手机号码必须填一个</font>
				   </td>
				</tr>
			</table>
		</td>
	</tr>
</table>