<%@ page language="java" pageEncoding="UTF-8"%>
<input type="hidden" name="ps_yqdatetime" value="${jpkhdd.YJSJ}" >
<c:set var="ps_lx" value="${empty jpkhdd.SFYJXCD ? '0' : jpkhdd.SFYJXCD}" />
<script type="text/javascript">
	function changeSfyj(sfyj){
		if(sfyj =="0"){
			$("#yjtr").hide();
			$("#addtr").hide();
		} else {
			$("#yjtr").show();
			$("#addtr").show();
		}
	}
</script>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:70px;margin-top: 1px;" id="psInfo">
	<tr>
	    <td style="background:#efefef;height:70px; width:100px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/yjxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">邮寄信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:70px;background:#f1f1f1;" id="_bzInfo" >
				<tr>
					<td>
						<span id="sfyj"> 
							<input type="radio" id="sfyjxcd0" name="sfyjxcd" value="0" ${ps_lx eq '0' ? 'checked' : ''} onclick="changeSfyj('0');" >无需行程单
							<input type="radio" id="sfyjxcd1" name="sfyjxcd" value="1" ${ps_lx eq '1' ? 'checked' : ''} onclick="changeSfyj('1');" >需要行程单
				 		</span>&nbsp;
						<span id="yjtr" style="display: ${ps_lx eq '1' ? '' : 'none'}">
							<span class="headtitle" id="thsjr" >收件人</span>
							<span id="tdsjr">
								<input type="text" name="sjr" class="required max-length-60" value="${jpkhdd.SJR}" size="9" maxlength="60">
								<font color="red">*</font>
							</span>
							
							<span class="headtitle" id="thlxrdh">收件人电话</span>
							<span id="tdlxrdh">
								<input type="text" name="sjrdh" id="sjrdh" class="required validate-mobile-phone max-length-60" 
									value="${empty jpkhdd.NXSJ ? jpkhdd.NXDH : jpkhdd.NXSJ}" style="width: 100px">
								<font color="red">*</font>
							</span>
							<span class="headtitle" id="thyjfy">邮寄费用</span>
							<span id="tdyjfy" style="margin-left:6px;">
								<input type="text" id="xs_yjf" name="xs_yjf" value="${jpkhdd.XS_YJF}" onblur="sumGqfy();" 
									class="validate-number min-value--9999999999.99 max-value-9999999999.99 dot-max-length-2" style="width: 50px" />
							</span>
							<span class="headtitle" id="thyzbm" style="width: 70px;">邮政编码</span>
							<span id="tdyzbm" style="margin-left:6px;">
								<input type="text" name="yzbm" value="${jpkhdd.YZBM }" class="input1 max-length-10 validate-zip" style="width: 50px">
							</span>
						</span>
					</td>
				</tr>
				<tr id="addtr" style="display: ${ps_lx  eq '1' ? '' : 'none'}">
					<td>
						<span class="headtitle" id="thpsdz">
							邮寄地址
						</span>
						<span id="yjfytr">
							<span id="tdsjdz" style="margin-left:6px;">
								<input type="text" name="xjdz" style="width: 650px;" value="${jpkhdd.XJDZ}" 
									class="required max-length-120 inputSelect" onlyselect="false" >&nbsp;<font color="red">*</font>
							</span>
						</span>
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>