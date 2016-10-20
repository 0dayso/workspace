<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="jpInfo">
	<tr>
			<td style="background:#efefef;height:90px; width:100px; border:1px;text-align:center;margin-top: 1px;">
		    	<img src="${ctx}/static/images/wdimages/cg.png" /><br />
		    	<span style="font-size:14px; font-weight:bold; color:#1195db">出票信息</span>
			</td>
			<td style="position: relative; top:0px;">
				<table style="width:100%;height:90px;background:#f1f1f1;margin-top: 1px;" align="center">
					<tr>
						<td>
							<span>出票类型</span>
							<span>
								<select name="cplx" id="cplx_select" style="width: 80px" onchange="checkCplx(this);"  class="required">
									<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
									    <c:if test="${oneLx.id ne '10014'}">
											<option value="${oneLx.id }">${oneLx.mc }</option>
										</c:if>
									</c:forEach>
								</select>
								<font color="red">*</font>
							</span>&nbsp;&nbsp;&nbsp;&nbsp;
					    	<span><input type="checkbox" name="ddly" value="7" onclick="if(checked){$('npnr').show();}else{$('npnr').hide();}">换PNR出票</span>
					    	<span id = "npnr">新PNR</span>
							<span >
								<input type="text" id="newpnr" name="newpnr" class="required validate-alphanum UpperCase min-length-6 max-length-7" onblur="value=$.trim(this.value).toUpperCase();" size="7" />
								<font color="red">*</font>
							</span>
					    	<span >新大编码</span>
							<span >
								<input type="text" id="newhkgs_pnr" name="newhkgs_pnr" class="input1 validate-alphanum UpperCase min-length-6 max-length-10" onblur="value=$.trim(this.value).toUpperCase();" size="8" />
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<span >外出票单位</span>
							<span >
								<input type="text" name="airkhh1" value="" style="width:120px" class="input1 max-length-30" maxlength="30">
							</span>
							<span >OFFICE号</span>
							<span >
								<select name="officeid1" style="width: 110px">
									<option value="">==请选择==</option>
									<c:set var="offtmp" value="0" />
									<c:forEach items="${officeList}" var="ofid">
							 			<c:if test="${ofid.BY2 eq '1'}">
							 				<option value="${ofid.SVALUE}" ${offtmp eq 0 ? 'selected' : ''}>${ofid.SVALUE}</option>
							 				<c:set var="offtmp" value="${offtmp+1}" />
							 			</c:if>
							 		</c:forEach>
								</select>
							</span>
							<span>采购科目</span>
							<span>
								<input type="text" name="airkhh1" value="" style="width:120px" class="input1 max-length-30" maxlength="30">
							</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	 <tbody id="cpxx"></tbody>
</table>