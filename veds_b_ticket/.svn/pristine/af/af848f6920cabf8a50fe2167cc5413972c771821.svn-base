<%@ page language="java" pageEncoding="UTF-8"%>
<input type="hidden" name="ps_yqdatetime" value="${jpkhdd.YJSJ}" >
<c:set var="ps_lx" value="${empty jpkhdd.SFYJXCD ? '0' : jpkhdd.SFYJXCD}" />
<script type="text/javascript">
	function changeSfyj(sfyj){
		if(sfyj =="0"){
			$("#yjtr").hide();
			$("#addtr").hide();
			$("#sjr").removeAttr("datatype");
			$("#sjrdh").removeAttr("datatype");
			$("#yjf").removeAttr("datatype");
			$("#xjdz").removeAttr("datatype");
			$("#yzbm").removeAttr("datatype");
			$("#yzbm").removeAttr("ignore");
		} else {
			$("#yjtr").show();
			$("#addtr").show();
			$("#sjr").attr("datatype","*1-20");
			$("#sjrdh").attr("datatype","m");
			$("#yjf").attr("datatype","n1-5");
			$("#xjdz").attr("datatype","*1-60");
			$("#yzbm").attr("datatype","p");
			$("#yzbm").attr("ignore","ignore");
		}
	}
</script>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:75px;margin-top: 1px;" id="psInfo">
	<tr>
	    <td style="background:#efefef;height:75px; width:90px; border:1px;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/yjxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">邮寄信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:75px;border:none;background:#f1f1f1;" id="_bzInfo" >
				<tr>
					<td>
						<span class="fl">
							<span id="sfyj">
								<input type="radio" id="yjxcd0" name="sfyjxcd" value="0" ${ps_lx eq '0' ? 'checked' : ''} onclick="changeSfyj('0');" >无需行程单
								<input type="radio" id="yjxcd1" name="sfyjxcd" value="1" ${ps_lx eq '1' ? 'checked' : ''} onclick="changeSfyj('1');" >需要行程单
					 		</span>&nbsp;&nbsp;
				 		</span>
						<span id="yjtr" style="display: ${ps_lx eq '1' ? '' : 'none'}">
							<span class="fl">
								<label class="headtitle" id="thsjr" >收件人</label>
								<span id="tdsjr">
									<input type="text" name="sjr" id="sjr" required="required" value="${jpkhdd.SJR}" size="9" maxlength="60" ${ps_lx eq '1' ? "datatype='*1-20' " : ''} />
									<font color="red">*</font>
								</span>&nbsp;&nbsp;
							</span>
							
							<span class="fl">
								<label class="headtitle" id="thlxrdh">收件人电话</label>
								<span id="tdlxrdh">
									<input type="text" name="sjrdh" id="sjrdh" ${ps_lx eq '1' ? "datatype='m' " : ''}
										value="${empty jpkhdd.NXSJ ? jpkhdd.NXDH : jpkhdd.NXSJ}" style="width: 80px"/>
									<font color="red">*</font>
								</span>&nbsp;&nbsp;
							</span>
							
							<span class="fl">
								<label class="headtitle" id="thyjfy">邮寄费用</label>
								<span id="tdyjfy" style="margin-left:6px;">
									<input type="text" id="yjf" name="xs_yjf" value="${jpkhdd.XS_YJF}" onblur="" ${ps_lx eq '1' ? "datatype='n1-5' " : ''}  style="width: 50px" />
								</span>&nbsp;&nbsp;
							</span>
							<label class="headtitle" id="thyzbm" style="width: 70px;">邮政编码</label>
							<span id="tdyzbm" style="margin-left:6px;">
								<input type="text" id = "yzbm" name="yzbm" value="${jpkhdd.YZBM }" ${ps_lx eq '1' ? "datatype='p' ignore='ignore'" : ''}  style="width: 50px"/>
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
								<input type="text" id = "xjdz" name="xjdz" style="width: 650px;" value="${jpkhdd.XJDZ}" 
									${ps_lx eq '1' ? "datatype='*1-60'" : ''} onlyselect="false"/>&nbsp;<font color="red">*</font>
							</span>
						</span>
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>