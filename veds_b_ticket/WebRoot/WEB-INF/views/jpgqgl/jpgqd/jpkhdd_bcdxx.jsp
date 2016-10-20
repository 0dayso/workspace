<%@ page language="java" pageEncoding="UTF-8"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 0px" id="bcInfo">
	<tr>
	    <td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/bcxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">补差信息</span>
	    </td>
		<td style="position: relative; top:0px;">
			<table class="t_tab" style="width: 100%;height:100%;margin-top: 0;background:#f1f1f1;">
				<c:forEach items="${bcdList}" var="bcd">
					<c:set var="bcsm" value="${user.xm}(${bcd.cjyh}) 于 ${bcd.cjsj} 由于 ${bcd.bcsm} 创建" scope="request"></c:set>
						<tr>
							<td height="25px;" style="border:none">
								&nbsp;&nbsp;
								<span class="headtitle">
									<font color="#999">${bcd.bclxname}</font>&nbsp;&nbsp;&nbsp;补差金额 <font color="#999">${bcd.bcje}</font> 元&nbsp;&nbsp;&nbsp;
									<font color="#999">${bcd.skzt eq '1' ? '已收款' : '未收款' }</font>&nbsp;&nbsp;&nbsp;
									<c:if test="${ bcd.skzt eq '1' }">
										付款科目：<font color="#999">${bcd.skkm}</font>&nbsp;&nbsp;&nbsp;
									</c:if>
									<img src="${ctx}/static/images/wdimages/gantanhao.png" width="14" height="14" style="cursor: pointer;" title="补差说明：${bcsm}" />
								</span>
							</td>
						</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>