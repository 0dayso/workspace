<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table  class="t_tab" cellpadding="0" cellspacing="0" style="width: 100%;height:60px;margin-top: 0px;" >
	<tr>
		<td style="background:#efefef;width:100px; border:1px #efefef solid;text-align:center;">
			<img src="${ctx}/static/images/wdimages/blxx.png" /><br /> 
			<span style="font-size:14px; font-weight:bold; color:#1195db">改签信息</span>
		</td>
		<td style="background:#efefef;">
			<table cellpadding="0" cellspacing="0" style="width: 680px;height:100%;margin-top: 0px;">
				<tr>
					<td>
						<span>
							改签类型：${jpgqd.gqlx eq '1' ? '改期' : '升舱'}
						</span>
						&nbsp;
						<span>
							改签原因：${empty jpgqd.gqyy ? '无' : jpgqd.gqyy}
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>