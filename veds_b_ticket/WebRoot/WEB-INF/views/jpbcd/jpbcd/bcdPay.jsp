<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript">
    	function qxPay(){
    		var index=parent.layer.getFrameIndex();
			parent.layer.close(index);
    	}
    	
    	function pay(){
    		$("#payForm").submit();
    	}
    </script>
  </head>
  <body>
	  <div>
	  	<table class="list_table">
	  		<tr><td style="background: #e7effe" colspan="2"><span style="text-align: left;color: #666"">订单信息</span></td></tr>
	  		<tr>
		  		<td colspan="2">
		  			<span>网店名称</span>&nbsp;<span style="color: red">${t1.wdmc}</span>&nbsp;
		  			<span>补差单号</span>&nbsp;<span style="color: red">${entity.id}</span>&nbsp;&nbsp;
		  			<span>补差金额</span>&nbsp;<span style="color: red">￥${entity.bcje}</span>&nbsp;&nbsp;
		  			<span>补差单类型</span>&nbsp;<span style="color: red">${shjcsj.mc}</span>&nbsp;&nbsp;
		  		</td>
	  		</tr>
	  		<tr>
	  			<td>补差说明</td>
	  			<td><textarea rows="3" cols="55" disabled="disabled">${entity.bcsm}</textarea></td>
	  		</tr>
	  	</table>
	  	<form action="${ctx}/vedsb/jpbcd/jpbcd/bcdPay" method="post" id="payForm">
	  	<table align="center" cellspacing="0" cellpadding="0" border="0" class="list_table">
	  		<tr>
	  			<td style="background: #e7effe"><span style="text-align: left;color: #666">支付信息</span></td>
	  		</tr>
	  		<tr>
	  			<td>
	  				<span>付款状态</span>
	  				<span style="color: red">未付</span>&nbsp;
	  				<span>应收金额</span>
	  				<span style="color: red">￥${entity.bcje}</span>&nbsp;
	  				<span>支付科目</span>
	  				<span>
	  				<select style="180px" name="skkm">
	  					<c:forEach items="${zfkmlist}" var="list">
		  					<option value="${list.kmbh}">${list.kmmc}</option>
	  					</c:forEach>
	  				</select><font color="red">*</font>
	  				</span>
	  			</td>
	  		</tr>
	  		<tr>
	  		<td style="text-align: center;">
	  			<input type="hidden" name="callback"  value="parent.refresh()" />
		        <input type="hidden" name="closeDiv" value="true"/>
		        <input type="hidden" name="ref" value="true"/>
	  			<input type="hidden" name="id" value="${entity.id}">
	  			<input type="button" value="确认" class="ext_btn ext_btn_submit" onclick="pay()"/>&nbsp;&nbsp;&nbsp;
	  			<input type="button" value="取消" class="ext_btn ext_btn_submit" onclick="qxPay();"/>
	  		</td>
	  		</tr>
	  	</table>
	  	</form>
	  </div>
  </body>
</html>
