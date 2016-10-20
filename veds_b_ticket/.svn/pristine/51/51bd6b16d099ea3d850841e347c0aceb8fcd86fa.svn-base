<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="bzInfo">
	<tr>
	    <td style="background:#efefef;height:80px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/bzxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">备注信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;height:85px;background:#f1f1f1;" id="_bzInfo" >
				<c:if test="${jpkhdd.ddzt ne '9' and jpkhdd.ddzt ne '7' and jpkhdd.ddzt ne '8'}">
					<tr>
						<td class="headtitle" style="border:none;width:30px;">备注</td>
						<td style="border:none;">
							<textarea  rows="4" name="bzbz" cols="95" style="background:#fff;">${jpkhdd.BZBZ}</textarea>
							<span><font color="red">最多500字</font></span>
						</td>
					</tr>
				</c:if>
				<c:if test="${jpkhdd.ddzt eq '9' or jpkhdd.ddzt eq '7' or jpkhdd.ddzt eq '8'}">
					<input type="hidden" name="bzbz" value="${jpkhdd.BZBZ}" >
					<tr>
						<td class="headtitle" style="border:none;">原备注</td>
						<td class="wb_td02" width=768 style="border:none;word-break:break-all;">${empty jpkhdd.bz ? '无' : jpkhdd.BZBZ}</td>
					</tr>
					<tr>
						<td class="headtitle" style="border:none;">新备注</td>
						<td style="border:none;">
							<textarea rows="2" name="abzbz" cols="90" style="background:#fff;"></textarea>
						</td>
					</tr>
				</c:if>
			</table>
		</td>
	</tr>
</table>