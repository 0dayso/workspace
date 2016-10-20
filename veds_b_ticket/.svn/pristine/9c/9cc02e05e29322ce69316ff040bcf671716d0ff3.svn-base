<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab2"  cellpadding="0" cellspacing="0" align="center" style="height:90px; width: 100%;margin-top: 0px;" id="khInfo">
	<tr id="khtr3">
		<td style="background:#efefef;height:90px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/blxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">网店信息</span>
		</td>
		<td>
			<table style="float:left;width:100%;height:90px;background:#f1f1f1;">
				<tr>
					<td>
						<span class="fl">
							<label class="headtitle">网店</label>
						      <select name="wdid" id="wdid" class="select">
			                  	 	<c:forEach items="${wdzlszList}" var="onewd">
			                  	 		<option value="${onewd.id }" ${kh_khdd_pn.WDID eq onewd.id ? 'checked' : '' }>${onewd.wdmc }</option> 
			                  	 	</c:forEach>
			                  </select>
						</span>
						<span class="fl">
							<label class="headtitle">外部单号</label>
							<input type="text" id="wbdh" name="wbdh" value="" style="width:120px;" maxlength="30" datatype="*1-60"/><font color='red'>*</font>
						</span>
						<span class="fl">
							<label class="headtitle">政策代码</label>
							<input type="text" id="wd_zcdm" name="wd_zcdm" value="" style="width:120px;" maxlength="30"/>
						</span>
						<span class="fl">
							<span class="headtitle">收款科目</span>
						    <select name="wdpt" id="wdpt">
			                  	<option value="0" ${param.wdpt eq onewdpt.id ? 'checked' : '' }>支付宝</option>
			                </select>
						</span>
						<span class="fl">
							<label class="headtitle">收款金额</label>
							<input type="text" name="pj_hk" id="pj_hk" value="0" maxlength="8"  style="width:60px;" datatype="number,minvalue,maxvalue" minvalue="0" maxvalue="99999"/><font color='red'>*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td>
					    <span class="fl">
							<label class="headtitle">联&nbsp;系&nbsp;人</label>
							<span class="red">
								<input maxlength="30" style="width:70px;" value="${kh_khdd_pn.NXR}" id="ct_nxr" name="nxr" class="input1"/>
							</span>
						</span>
						<span class="fl">
							<label class="headtitle">电话号码</label>
							<span class="red">
							     <%--支持电话号码格式: 3-4位区号，7-8位直播号码，1－4位分机号--%>
								<input id="ct_nxrdh" name="nxdh" value="${kh_khdd_pn.NXDH}" style="width:80px;" datatype = "/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/" ignore="ignore" errormsg="请输入正确的电话号码（【3-4位区号】-【7-8位直播号码】-【1－4位分机号】）" maxlength="30"/>
							</span>
						</span>
						<span class="fl">
							<label class="headtitle">手机号码</label>
							<span class="red">
								<input maxlength="30" style="width:90px;" value="${kh_khdd_pn.NXSJ}" datatype = "m" ignore="ignore" id="nxsj" name="nxsj"/>
							</span>
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
