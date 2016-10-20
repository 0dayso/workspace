<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
function checkCplx(){
    var cplx=$("#cplx_select option:selected").attr("cpid");
    var url="${ctx}/vedsb/cpkzt/cpkzt/cglyValue?cgly="+cplx;
    $("#cgly_tr").load(url);
}

function getcglmByoffice(obj){
	var $obj = $(obj);
	var objval = $obj.val();
	var objs = $("#"+objval+"_index").val();
	$("select option[value='"+objs+"']").attr("selected","selected");
	$("#cgkmhidden").val(objs); 
}
</script>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:75px;margin-top: 1px;" id="jpInfo">
	<tr>
			<td style="background:#efefef;height:75px; width:100px; border:1px;text-align:center;">
		    	<img src="${ctx}/static/images/wdimages/cg.png" /><br />
		    	<span style="font-size:14px; font-weight:bold; color:#1195db">出票信息</span>
			</td>
			<td style="position: relative; top:0px;">
				<table style="width:100%;height:75px;background:#f1f1f1;" align="center">
					<tr>
						<td>
							<span>采购来源</span>
							<span>
								<select name="cgly" id="cplx_select" style="width: 80px" onchange="checkCplx();"  class="required">
									<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
									    <c:if test="${oneLx.id ne '10014'}">
											<option value="${oneLx.ywmc}" ${(param.cgly eq oneLx.ywmc or oneLx.ywmc eq 'BOP') ? 'selected' : ''} cpid="${oneLx.ywmc}">${oneLx.ywmc }</option>
										</c:if>
									</c:forEach>
								</select>
								<font color="red">*</font>
							</span>&nbsp;&nbsp;&nbsp;&nbsp;
					    	<span><input type="checkbox" name="ddly" value="7" onclick="if(checked){$('#hbmcp').show();}else{$('#hbmcp').hide();}">换PNR出票</span>
					    	<span id = "hbmcp" style="display:none">
						    	<span id = "npnr">新PNR</span>
								<span >
									<input type="text" id="newpnr" name="newpnr" onblur="value=$.trim(this.value).toUpperCase();" size="7" />
									<font color="red">*</font>
								</span>
						    	<span >新大编码</span>
								<span >
									<input type="text" id="newhkgs_pnr" name="newhkgs_pnr" onblur="value=$.trim(this.value).toUpperCase();" size="8" />
								</span>
							</span>
						</td>
					</tr>
					<tr  id="cgly_tr">
					</tr>
				</table>
			</td>
		</tr>
	 <tbody id="cpxx"></tbody>
</table>