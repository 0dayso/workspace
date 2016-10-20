<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>TRFD</title>
<%@include file="/asms/common/meta.jsp"%>
<script type="text/javascript">
	
	function initPage(){
		
	}
	
	function toSave(){
		
		return;
		lockScreen("正在保存数据，请稍候。。。");
	}
	
	function trfd(szdh,tkno,sfdetr){
		lockScreen("正在保存数据，请稍候。。。");
		if(sfdetr){
			
		}else{
		
		}
		unlockScreen();
	}
	
	function detr(szdh,tkno){
		
	}
	
	
</script>
</head>
<body onload="initPage();">
	<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="99%">
		<tr>
			<th><c:if test="${apply}"><input type="checkbox" onclick="selectAll(this,'cjrBox')"></c:if>乘机人</th>
			<th>
				票号<br>【OFFICE-工作号-打票机】
			</th>
			<th>航程<br>退航程</th>
			<th>出票时间</th>
			<th>账单价</th>
			<th>代理费</th>
			<c:if test="${ticket_return.jp_hcglgj eq '1'}">
				<th>机建</th>
			</c:if>
			<th>税费</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${mxList}" var="vc">
			<tr id="jptr${vc.TKNO}">
				<td class="red">
					<MD:cut value="${vc.JP_CJR}" length="4" autopoint="true"></MD:cut><br>${vfn:cjrlxImg(vc.JP_CJRLX,'','','')}
				</td>
				<td align="center" class="red">
					${vc.SZDH}-${vc.TKNO}<br>【${vc.CP_OFFICEID}-${vc.CP_PID}-${vc.PRINTNO}】
				</td>
				<td>${vc.JP_HC}<br><span style="color:red;text-decoration:line-through;">${vc.TP_HC}<span></td>
				<td align="center">${fn:replace(fn:substring(vc.CP_DATETIME,0,16),' ','<br>')}</td>
				<td align="right" class="red">${vc.PJ_CGJ}</td>
				<td align="right" class="red">${vc.PJ_CGJ-vc.PJ_HJSJJSJ}(<fmt:formatNumber value="${vc.SJJSFL}" pattern="0.####%" />)</td>
				<c:if test="${ticket_return.jp_hcglgj eq '1'}">
					<td align="right" class="red">${vc.PJ_JSF}</td>
				</c:if>
				<td align="right" class="red">${vc.PJ_TAX}</td>
				<td>
					<c:if test="${vc.HX_TFZT eq '1'}">
						【已退】退票单号：${vc.HX_TFDH}
					</c:if>
					<c:if test="${vc.HX_TFZT ne '1'}">
						<span id="first">选择指令</span>
						->
						<span id="second">检查客票状态及行程单</span>
						->
						<span id="third">TRFD预览</span>
						->
						<span id="fourth">TRFD</span>
					</c:if>
				</td>
			</tr>
			<c:if test="${vc.HX_TFZT ne '1'}">
				<tr>
					<td colspan="${ticket_return.jp_hcglgj eq '1'  ? 9 : 8}">
						<table class="t_tab" cellpadding="0" cellspacing="0" align="center" width="100%" >
							<tr>
								<th style="text-align: left;font-weight: bolder;">
									退票指令：
									<label for="operateType1">
										<input type="radio" id="operateType1" name="operateType" value="1" checked>TRFD:Z/票号/打票机/D（全自动）
									</label>
									<label for="operateType2">
										<input type="radio" id="operateType2" name="operateType" value="2">TRFD:L/票号/AM/打票机/D（半自动）
									</label>
									<label for="operateType3">
										<input type="radio" id="operateType3" name="operateType" value="3">TRFD:AM/8/D（手工）
									</label>
									<input type="button" name="savebut" value="开始退票" class="asms_button" onclick="trfd('${vc.SZDH}','${vc.TKNO}',true);">
									<input type="button" name="savebut" value="跳过检查" class="asms_button" onclick="trfd('${vc.SZDH}','${vc.TKNO}',false);">
								</th>
							</tr>
							<tbody id="second_tb">
							
							</tbody>
							<tbody id="third_tb">
							
							</tbody>
							<tbody id="fourth_tb">
							
							</tbody>
							<tr>
								<td class="title" >
									检查客票状态：<img src='/images/load_tss.gif' id='img1' height="16" />
								</td>
							</tr>
							<tr>
								<td class="title" >
									检查行程单：<img src='/images/load_tss.gif' id='img2' height="16" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>