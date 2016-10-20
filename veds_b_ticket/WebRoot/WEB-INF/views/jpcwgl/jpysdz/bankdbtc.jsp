<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>同程银行对账</title>
	<script type="text/javascript" src="${ctx}/static/js/bankdb.js"></script>
	<style type="text/css">	
		.div_h_top{width:100%;text-align: center;cursor: pointer;margin-top:5px;}
		.div_s_top_left{clear:left;width:40%;text-align: center;cursor: pointer;float:left;color:#FD5C00;font-size:14px;font-family:'微软雅黑';}
		.div_s_top_right{width:55%;text-align: center;cursor: pointer;float:right;color:#FD5C00;font-size:14px;font-family:'微软雅黑';}
		.div_h_data{height:200px;width:100%;}
		.div_h_data1{height:300px;width:100%;overflow:auto;scrollbar-base-color:#bce3f9;}
		.div_s_data_left{clear:left;height:460px;width:40%;float:left;}
		.div_s_data_right{height:460px;width:59%;overflow:auto;float:right;}
		.bank_form{background: #F1F5F7;height:84px;}
		.bank_form td{padding-left:5px;}
	</style>
</head>
<body>
	<div style="background: #fff;">
		<div id="cxtj_div">
			<table width="99%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="width:40%;padding-top:4px;">
						<table class="bank_form" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" width="80px;">
									<div width="100%">
										<img src="${ctx}/static/images/wdimages/tongchengLogo.png" height="75" width="78"/>
									</div>
								</td>
								<td>
									<span style="font-weight: bold;color: #3E75D0;font-size:14px;">
										${param.wdmc}
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<form id="asmsForm" name="asmsForm" action="${ctx}/vedsb/jpcwgl/jpysdz/genSystemData" method="post" target="asmsiframe">
										<input type="hidden" id="dzwdid" name="wdid" value="${param.wdid}">
										<input type="hidden" name="wdpt" value="10100024">
										<input type="hidden" name="dzrq" id="dzrq" value="${param.dzrq}"/>
										<input type="hidden" name="dzzbid" value="${param.dzzbid}">
										<span>您的账期为：${param.dzrq}</span>
										<span style="margin-left:50px;">
											<input type="button" value="查询系统数据" class="ext_btn ext_btn_submit" onclick="searchAsms()"/> 
										</span>
									</form>
								</td>
							</tr>
						</table>
					</td>
					<td style="width:15px;">&nbsp;</td>
					<td valign="top" style="padding-top:4px;">
						<table class="bank_form" height="84px;" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<span style="font-weight: bold;color: #3E75D0;font-size:14px;">
										导入同程账单数据
									</span>
								</td>
								<td>
									<span>
										<a href="https://www.alipay.com/" target="blank">点击前往支付宝后台下载账单</a>
									</span>
								</td>
								<td style="align:left;">
									
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<form id="upForm" name="upForm" action="${ctx}/vedsb/jpcwgl/jpysdz/bankup" method="post" target="hiddeniframe" enctype="multipart/form-data">
										<input type="hidden" name="wdid" value="${param.wdid}">
										<input type="hidden" name="dzzbid" value="${param.dzzbid}">
										<input type="hidden" name="wdpt" value="10100024">
										<input type="hidden" name="dzrq" value="${param.dzrq}"/>
										<input name="file" type="file" id="file" class="input-text lh30" style="width:500px;">
										<input type="button" name="sub" value="执行导入" class="ext_btn ext_btn_submit" onClick="upfile();" style="margin-left:13px;">
										<span id="wcydz" style="margin-left:10px;display:none;">
											<a href="###" onclick="window.open(document.getElementById('wsbfile').value)">导出未参与对账流水</a>
										</span>
									</form>
									<input type="hidden" name="wsbfile" id="wsbfile" value="">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div id="asms_ti" class="div_s_top_left" style="margin-top:5px;">系统数据</div>
		<div id="bank_ti" class="div_s_top_right" style="margin-top:5px;">同程账单</div>
		<div id="asms_date" class="div_s_data_left">
			<iframe width="98%" id="asmsiframe" name="asmsiframe" height="430" frameborder="1" scrolling="no" style="posotion:relative" onload="iframOnloadEven()"></iframe>
		</div>
		<div id="bank_date" class="div_s_data_right">
			<iframe width="98%" id="hiddeniframe" name="hiddeniframe" height="430" frameborder="1" scrolling="no" style="posotion:relative" onload="iframOnloadEven()"></iframe>
		</div>
		<div class="clear"></div>
		<div style="display:none;">
			<form name="dbjgForm" id="dbjgForm" action="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" method="post">
				<input type="hidden" name="wdid" value="${param.wdid}">
				<input type="hidden" name="wdpt" value="10100024">
				<input type="hidden" name="zbid" value="${param.dzzbid}">
				<input type="hidden" name="jglx" value="1">
			</form>
		</div>
	<div>
	<div id="compare_ti" class="div_h_top" style="display:none;">
		<span style="margin-right:17%;"><input type="button" value="对比数据" onclick="compareData()" name="compareButton" class="ext_btn ext_btn_submit"/></span>
	</div>
</body>
</html>