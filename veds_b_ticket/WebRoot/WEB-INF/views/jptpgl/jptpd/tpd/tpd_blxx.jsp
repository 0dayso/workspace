<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px" id="bcInfo">
	<tr>
		<td style="background:#efefef;height:90px; width:100px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/blxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">办理信息</span>
		</td>
		<td style="position: relative; top:0px;">
			<table class="t_tab2" style="width: 100%;height:90px;margin-top: 0;background:#f1f1f1;">
				<tr>
					<td>
						<span class="headtitle" style ="color:#0433A0;">最晚采购办理时间：</span>
						<span style ="color:#0433A0;">${vfn:format(jptpd.cgZwblsj,'')}</span>
					</td>
				</tr>
				<tr style="display: ${empty jptpd.cgTjr ? 'none' : ''}">
					<td>
						<span class="headtitle">采购提交人：</span>
						<span class="red">${jptpd.cgTjr}</span>
						<span class="headtitle" style ="color:#0433A0;">采购提交时间：</span>
						<span style ="color:#0433A0;">${vfn:format(jptpd.cgTjsj,'')}</span>
					</td>
				</tr>
				<tr style="display: ${empty jptpd.xsBlr ? 'none' : ''}">
					<td>
						销售办理部门：<span class="red">${jptpd.ex.XSBLR.bmmc}</span>
						销售办理人：<span class="red">${jptpd.xsBlr}</span>
						<span class="headtitle" style ="color:#0433A0;">销售办理时间：</span>
						<span style ="color:#0433A0;">${vfn:format(jptpd.xsBlsj,'')}</span>
					</td>
				</tr>
				<tr style="display: ${empty jptpd.xsShr ? 'none' : ''}">
					<td>
						销售审核部门：<span class="red">${jptpd.ex.XSSHR.bmmc}</span>
						销售审核人：<span class="red">${jptpd.xsShr}</span>
						<span class="headtitle" style ="color:#0433A0;">销售审核时间：</span>
						<span style ="color:#0433A0;">${vfn:format(jptpd.xsShsj,'')}</span>
					</td>
				</tr>
				<tr style="${empty jptpd.ddyh ? 'none' : ''}">
					<td>
						<span class="headtitle">申请部门：</span>
						<span class="red">${jptpd.ex.DDYH.bmmc}</span>
						<span class="headtitle">申请人：</span>
						<span class="red">${jptpd.ddyh}</span>
						<span class="headtitle" style ="color:#0433A0;">申请时间：</span>
						<span style ="color:#0433A0;">${vfn:format(jptpd.ddsj,'')}</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>