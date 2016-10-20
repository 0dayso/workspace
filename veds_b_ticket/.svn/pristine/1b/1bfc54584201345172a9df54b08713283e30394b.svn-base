<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
  		//关闭当前弹出窗
		  function closeJp(){
		  	var index=parent.layer.getFrameIndex();
			parent.layer.close(index);
		  }
		  
		
	
		function showDetr(command) {
			var url = "${ctx}/vedsb/bbzx/cpbb/detr";
			$.ajax({
				type : "post",
				url : url,
				data : {
					command : command
				},
				success : function(result) {
					$("#showhp").html(result);
				}
			});
		}
</script>
  </head>
  <body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<td style="width:150px;">票号:&nbsp;${ticket.tkno}</td>
			<td style="width:150px;">PNR:&nbsp;${ticket.xsPnrNo }</td>
			<td style="width:150px;">行程单号:&nbsp;${ticket.xcdh}</td>
		</tr>
		<tr>
			<td style="width:150px;">旅客姓名:&nbsp;${ticket.cjr}(
			<c:if test="${ticket.cjrlx eq 1}">成人</c:if>
			<c:if test="${ticket.cjrlx eq 2}">儿童</c:if>
			<c:if test="${ticket.cjrlx eq 3}">婴儿</c:if>
			)</td>
			<td style="width:150px;">有效证件号:&nbsp;${ticket.zjhm }</td>
			<td style="width:150px;">退改签规定:&nbsp;${ticket.xsTgq }</td>
		</tr>
	</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th>航程</th>
			<th>航班号</th>
			<th>舱位</th>
			<th>机型</th>
			<th>出发时间</th>
			<th>到达时间</th>
			<th>票证状态</th>
		</tr>
		<c:if test="${!empty hdlist and fn:length(hdlist)>0 }">
		<c:forEach items="${hdlist}" var="hd" varStatus="hdStatus">
			<tr>
				<td>${hd.cfcs } -> ${hd.ddcs }</td>
				<td>${hd.xsHbh }</td>
				<td>${hd.xsCw }</td>
				<td>${hd.fjjx }</td>
				<td>${hd.cfsj }</td>
				<td>${hd.ddsj }</td>
				<td align="center">${ticket.jpzt eq 1 ? "正常票" :(ticket.jpzt eq 2 ? "退票" : "废票") }</td>
			</tr>	                  	 		 
		</c:forEach>
		</c:if>
		<c:if test="${empty hdlist or fn:length(hdlist)<1 }">
			<tr>
				<td colspan="7">没有找到对应航程</td>
			</tr>
		</c:if>
		<tr style="background:#F9E9E8;">
			<td></td>
			<td>销售价:&nbsp;${ticket.xsPj }</td>
			<td colspan="3">机建/税费: &nbsp;${ticket.xsJsf }/${ticket.xsTax}</td>
			<td>保险: &nbsp;${ticket.xsHyxlr + ticket.xsYwxlr}</td>
			<td>合计: &nbsp;${ticket.xsPj + ticket.xsJsf + ticket.xsTax + ticket.xsHyxlr + ticket.xsYwxlr}</td>
		</tr>
	</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<td style="width:150px;">连续客票:&nbsp;${ticket.lxkp }</td>
			<td>付款状态:&nbsp;${ticket.skzt eq 1 ?"已付款":"未付款" }&nbsp;&nbsp;</td>
			<td style="width:250px;">付款科目:&nbsp;${ticket.ex.SKKM.kmmc }</td>
			<td style="width:150px;">支付金额:&nbsp;${ticket.xsJe }</td>
		</tr>
		<tr>
			<td colspan="4">订票信息:&nbsp;${jpkhdd.ex.WDID.wdmc}/${jpkhdd.ex.DDYH.xm}/${vfn:format(jpkhdd.ddsj,'')}</td>
		</tr>
		<tr>
			<td colspan="4">
				出票信息:&nbsp;${ticket.cgly}/${ticket.cgdw}/${ticket.ex.CPYHBH.xm}/${vfn:format(ticket.cpDatetime,'')}
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center;">
				<input type="button" class="ext_btn ext_btn_success" value="detr" onclick="showDetr('detr:tn/${ticket.tkno}');">
				<input type="button" class="ext_btn ext_btn_success" value="detr,h" onclick="showDetr('detr:tn/${ticket.tkno},h');">
				<input type="button" class="ext_btn ext_btn_success" value="detr,f" onclick="showDetr('detr:tn/${ticket.tkno},f');">
				<input type="button" class="ext_btn ext_btn_success" value="detr,s" onclick="showDetr('detr:tn/${ticket.tkno},s');">
          		<input type="button" class="ext_btn ext_btn_submit" value="关闭" onclick="closeJp()">
			</td>
		</tr>
	</table>
	<div id="showhp" style="background-color:#000000;color: #00FF00;padding-left: 50px;">
  </body>
</html>
