<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>采购银行对账</title>
	<script type="text/javascript" src="${ctx}/static/js/bankdb.js"></script>
	<style type="text/css">	
		.div_h_top{width:100%;text-align: center;cursor: pointer;margin-top:5px;}
		.div_s_top_left{clear:left;width:40%;text-align: center;cursor: pointer;float:left;color:#FD5C00;font-size:14px;font-family:'微软雅黑';}
		.div_s_top_right{width:55%;text-align: center;cursor: pointer;float:right;color:#FD5C00;font-size:14px;font-family:'微软雅黑';}
		.div_h_data{height:200px;width:100%;}
		.div_h_data1{height:300px;width:100%;overflow:auto;scrollbar-base-color:#bce3f9;}
		.div_s_data_left{clear:left;height:310px;width:49%;float:left;}
		.div_s_data_right{height:310px;width:50%;overflow:auto;float:left;margin-left:1%;}
		.bank_form{background: #F1F5F7;height:84px;}
		.bank_form td{padding-left:5px;height:20px;}
		.srk{width:120px;}
	</style>
	<script type="text/javascript">
		$(function(){
			var code=new Date();
			$('#codesys').val(code);
			$('#codebank').val(code);
		})
		/**影藏或展开头部*/
		var sqFlag=false;
		function hideSearch(){
			if(sqFlag){
				$('#shouqi').html('<img src="${ctx}/static/images/ic_up.jpg" style="cursor:pointer;" height="11;" onclick="hideSearch()" title="收起"/>');
				$('#hideTop').slideDown();
				sqFlag=false
			}else{
				$('#hideTop').slideUp();
				$('#shouqi').html('<img src="${ctx}/static/images/ic_down.jpg" style="cursor:pointer;" height="11;" onclick="hideSearch()" title="展开"/>');
				sqFlag=true;
			}
		}
		/**包含退废*/
		function bhtf(obj){
			if(obj.checked){
				$('#zctp').val('2');
			}else{
				$('#zctp').val('1');
			}
		}
		/**系统数据查询*/
		function search(){
			var zctp=$('#zctp').val();
			if(zctp==3){
				var rqs=$('#rqs').val();
				var rqz=$('#rqz').val();
				if(!rqs || !rqz){
					layer.msg('请输入日期',3,3);
					return false;
				}else{
					if(rqz < rqs){
						layer.msg('日期始不能大于日期止',3,3);
						return false;
					}
				}
			}else{
				var cprq=$('#cprq').val();
				if(!cprq){
					layer.msg('请输入出票日期',3,3);
					return false;
				}
			}
			$('#asmsForm').submit();
		}
	</script>
</head>
<body>
	<div style="background: #fff;">
		<div id="hideTop">
			<div id="cxtj_div" style="margin-bottom:8px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td style="width:49%;padding-top:4px;">
							<form id="asmsForm" name="asmsForm" action="${ctx}/vedsb/cgdzbb/jpcgyhdz/genSystemData" method="post" target="asmsiframe">
							<input type="hidden" id="zctp" name="zctp" value="${empty param.zctp ? '1' : param.zctp}">
							<input type="hidden" id="codesys" name="code" value="">
							<table class="bank_form" width="100%" cellpadding="0" cellspacing="0">
								<c:if test="${empty param.zctp or param.zctp eq '1'}">
									<tr>
										<td colspan="6">
											<span style="font-weight: bold;color: #3E75D0;font-size:14px;">
												第一步：查询系统数据
											</span>
											<span style="margin-left:50px;font-size:14px;font-weight: bold;">
												<a href="${ctx}/vedsb/cgdzbb/jpcgyhdz/viewbankmain?zctp=3" style="text-decoration:none">只对退废票>></a>
											</span>
										</td>
									</tr>
									<tr>
										<td align="right">采购科目</td>
										<td id="td_zfkm_nr">
											<select name="cgkm" id="cgzfkm" class="select srk">
												<option value="">==选择科目==</option>
												<option value="10078">支付宝</option>
												<option value="10079">易航宝</option>
											</select>
										</td>						
										<td align="right">出票日期</td>
										<td >
											<input type="text" id="cprq" name="cprq" value="${empty param.cprq ? vfn:getPreDay(vfn:dateShort(),-1) : param.cprq}" class="input-text lh25 srk Wdate" 
												onClick="WdatePicker()"/>
										</td>
										<td>
											<label></label><input type="checkbox" onclick="bhtf(this)">包含退废票</label>
										</td>
										<td>
											<input type="button" name="serach" value=" 查 询 " class="ext_btn ext_btn_submit" onclick="search()">
										</td>					
									</tr>
								</c:if>
								<c:if test="${param.zctp eq '3'}">
									<tr>
										<td colspan="5">
											<span style="font-weight: bold;color: #3E75D0;font-size:14px;">
												第一步：查询系统数据
											</span>
											<span style="margin-left:50px;font-size:14px;font-weight: bold;">
												<a href="${ctx}/vedsb/cgdzbb/jpcgyhdz/viewbankmain" style="text-decoration:none">只对正常票>></a>
											</span>
										</td>
									</tr>
									<tr>
										<td align="right">采购科目</td>
										<td id="td_zfkm_nr">
											<select name="cgkm" id="cgzfkm" class="select srk">
												<option value="">==选择科目==</option>
												<option value="10078">支付宝</option>
												<option value="10079">易航宝</option>
											</select>
										</td>						
										<td align="right">
											<select name="rqtj" id="rqtj" class="select" style="width:90px;">
												<option value="1">提交日期</option>
												<option value="2">回款日期</option>
											</select>
										</td>
										<td >
											<input type="text" id="rqs" name="rqs" value="${vfn:getPreDay(vfn:dateShort(),-1)}" class="input-text lh25 srk Wdate" 
												onClick="WdatePicker()"/> 至
											<input type="text" id="rqz" name="rqz" value="${vfn:getPreDay(vfn:dateShort(),-1)}" class="input-text lh25 srk Wdate" 
												onClick="WdatePicker()"/>
										</td>
										<td>
											<input type="button" name="serach" value=" 查 询 " class="ext_btn ext_btn_submit" onclick="search()">
										</td>					
									</tr>
								</c:if>
							</table>
							</form>
						</td>
						<td style="width:1%;">&nbsp;</td>
						<td valign="top" style="padding-top:4px;width:50%;">
							<input type="hidden" id="codebank" name="code" value="">
							<table class="bank_form" height="84px;" width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="3">
										<span style="font-weight: bold;color: #3E75D0;font-size:14px;">
											第二步：导入银行账单数据
										</span>
									</td>
								</tr>
								<tr>
									<td>
										<select name="gsbh" id="gsbh" class="select" style="width:140px;">
											<option value="">==选择格式==</option>
											<option value="10078458777">支付宝南航</option>
											<option value="10079685444">易航宝东航</option>
										</select>
									</td>						
									<td>
										<input name="file" type="file" id="file" class="input-text lh30" style="width:400px;">
										<input type="button" name="sub" value="执行导入" class="ext_btn ext_btn_submit" onClick="upfile();" style="margin-left:10px;">
										<input type="button" name="sub" value="自定义格式" class="ext_btn ext_btn_submit" onClick="" style="margin-left:10px;">
									</td>					
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div id="asms_date" class="div_s_data_left">
				<iframe width="99%" id="asmsiframe" name="asmsiframe" height="300" frameborder="1" scrolling="auto" style="posotion:relative" onload="iframOnloadEven()"></iframe>
			</div>
			<div id="bank_date" class="div_s_data_right">
				<iframe width="99%" id="hiddeniframe" name="hiddeniframe" height="300" frameborder="1" scrolling="auto" style="posotion:relative" onload="iframOnloadEven()"></iframe>
			</div>
			<div class="clear"></div>
			<div style="display:none;">
				<form name="dbjgForm" id="dbjgForm" action="${ctx}/vedsb/jpcwgl/jpysdz/genDbResult" method="post">
					<input type="hidden" name="wdid" value="${param.wdid}">
					<input type="hidden" name="wdpt" value="10100011">
					<input type="hidden" name="zbid" value="${param.dzzbid}">
					<input type="hidden" name="jglx" value="1">
				</form>
			</div>
			<div id="compare_ti" style="text-align:center;">
				<input type="button" value="对比数据" onclick="compareData()" name="compareButton" class="ext_btn ext_btn_submit"/>
			</div>
		</div>
		<div style="text-align: center;margin-top:3px;margin-bottom:2px;" id="shouqi">
			<img src="${ctx}/static/images/ic_up.jpg" style="cursor:pointer;" height="11;" title="收起" onclick="hideSearch()"/>
		</div>
	<div>
	
</body>
</html>