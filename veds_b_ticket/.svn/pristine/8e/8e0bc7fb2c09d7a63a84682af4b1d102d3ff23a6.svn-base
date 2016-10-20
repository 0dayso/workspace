<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" style="width: 98%;margin-bottom: 2px; background:#f1f1f1">
	<tr>
     	<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">基本信息</span>
		</td>
		<td style="background:#efefef;">
			<table class="t_tab" cellpadding="0" cellspacing="0" style="width: 100%;height:100%;margin-top: 0px;">
				<tr>
					<td style="margin-left: 5px">
					  	<span>原订单信息：&nbsp;</span>
					  	<span>预定时间：${vfn:format(jpKhdd.ddsj,'yyyy-MM-dd HH:mm:ss')}&nbsp;</span>
					  	<span>出票时间：${vfn:format(jpKhdd.cpSdsj,'yyyy-MM-dd HH:mm:ss')}</span>
				  	</td>
				</tr>
				<tr>
					<td style="margin-left: 5px">
						<span>改签单号：${jpgqd.gqdh}&nbsp;</span>
						<span>改签类型：${jpgqd.gqlx eq '1' ? '改期' : '升舱'}&nbsp;</span>
						<span>
							改签状态：	
							<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
					 			<c:if test="${oneZt.value eq jpgqd.gqzt}">
					 				${oneZt.mc}
					 			</c:if>
			          		</c:forEach>
						</span>	
				  	</td>
				</tr>
				<tr>
					<td style="margin-left: 5px">
					  	<span>PNR：${jpgqd.xsPnrNo}&nbsp;</span>
					  	<span>大编码：${empty jpgqd.xsHkgsPnr ? '无' : jpgqd.xsHkgsPnr}</span>
					  	<span id="sp_pnrnr"  style="display: none;"><a href="###" onclick="showPnrnr()">查看PNR内容</a></span>
				  	</td>
				</tr>
			</table>
		</td>
	</tr>		
</table>