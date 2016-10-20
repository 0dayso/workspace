<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
  	<title>详细信息</title>
	<style type="text/css">
		.ltd{text-align :right}
	</style>
	<script type="text/javascript">
		function closeWindow(){
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
		}
	</script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="list_table">
		<tr>
			<td class="bt" colspan="6">Q信息详情</td>
		</tr>
		<tr>
			<td style="width:14%">类型：</td>
			<td style="width:19">${b_qinfo.qtype}</td>
			<td style="width:14%">PNR：</td>
			<td style="width:19%">${b_qinfo.pnrno}</td>
			<td style="width:14%">OFFICE号：</td>
			<td style="width:20%">${b_qinfo.officeid}</td>
		</tr>
		<tr>
			<td style="text-align:center">航程</td>
			<td style="text-align:center">航班号</td>
			<td style="text-align:center" colspan="3">起飞到达时间</td>
			<td style="text-align:center">航段状态</td>
		</tr>
	<c:forEach items="${exlist}" var="item">
		<tr>
			<td style="text-align:center">${item.PNR_HC}</td>
			<td style="text-align:center">${item.PNR_HBH}</td>
			<td style="text-align:center" colspan="3">${item.PNR_CFDATETIME1}</td>
			<td style="text-align:center">${item.PNR_HDZT}</td>
		</tr>
	</c:forEach>
		<tr>
			<td>信息内容：</td>
			<td colspan="5" style="background-color: #000000;color: #00FF00">
				${b_qinfo.content}
			</td>
		</tr>	
		<tr>
			<td>乘机人：</td>
			<td>${b_qinfo.pnrCjr }</td>
		</tr>
		<tr>
			<td>联系人：</td>
			<td>${b_qinfo.lxr }</td>
			<td>联系电话：</td>
			<td>${b_qinfo.lxrdh}&nbsp;${b_qinfo.msmobilno}</td>
			<td>清Q时间：</td>
			<td>${fn:substring(b_qinfo.createDatetime,5,16) }
		</tr>
		<c:if test="${'1' eq b_qinfo.clZt}">
		<tr>
			<td>处理人：</td>
			<td>
				<c:if test="${not empty b_qinfo.clUserid}">${b_qinfo.clUserid}</c:if>
			</td>
			<td>处理时间：</td>
			<td><fmt:formatDate value="${b_qinfo.clDatetime}" type="both" pattern="MM-dd HH:mm"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>处理情况：</td>
			<td colspan="5">
				${b_qinfo.clQk}
			</td>
		</tr>	
		</c:if>	 	    		  							 		
	</table>
	 	
	 	
	 	<br>
	 	<center>
 		<div class="bottom" > 
			<input type="button" class="ext_btn ext_btn_submit" value="关 闭" onclick="closeWindow()">
		</div>
		</center>
		<br>
</body>
</html>
