<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="tkInfo">
	<tr>
		<td style="background:#efefef;height:45px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/skxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">退款信息</span>
		</td>
		<td style="position: relative; top:1px;">
			<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width:100%;height:45px;border:none;background:#f1f1f1;">
				<tr>
					<td style="border:none">
						<span class="headtitle">退款状态：</span>
						<span class="red">${jptpd.xsTpzt eq '2' ? '已退款' : '未退款'}</span>
						<span class="headtitle">退款金额：</span>
						<span class="red">
							￥<span id="khythj"><fmt:formatNumber value="${jptpd.xsTkje}" pattern="0.##" /></span>
						</span>
						
						<c:if test="${not empty jptpd.xsTkkm }">
							<span class="headtitle">退款科目：</span>
							<span class="red">${jptpd.ex.XSTKKM.kmmc }</span>
						</c:if>
						
						<c:if test="${not empty jptpd.xsBlsj }">
							<span class="headtitle" style ="color:#0433A0;">退款时间：</span>
							<span class="wb_td02" style ="color:#0433A0;">${vfn:format(jptpd.xsBlsj,'')}</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<span class="headtitle">联&nbsp;系&nbsp;人：</span>
						<span class="red">${jptpd.nxr}</span>
						<c:if test="${not empty jptpd.nxdh}">
							<span class="headtitle">联系电话：</span>
							<span class="red">${jptpd.nxdh}</span>
						</c:if>
						<c:if test="${not empty jptpd.nxsj}">
							<span class="headtitle">手机：</span>
							<span class="red">${jptpd.nxsj}</span>
						</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>	