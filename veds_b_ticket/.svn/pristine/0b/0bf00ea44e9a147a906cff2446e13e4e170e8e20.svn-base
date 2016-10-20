<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>采购对账--BSPET票TSL自动对账</title>
		<style type="text/css">
		     table{
		        border-collapse: separate;
		     }
			.fb_tab_dz {
				width: 99%;
				border-collapse: collapse;
				background: #fff;
			}
			
			.fb_tab_dz th {
				line-height: 16px;
				background: #f7f6f6;
				font-weight: normal;
			}
			
			.fb_tab_dz .bt {
				margin: 10px 0;
				height: 23px;
				line-height: 23px;
				filter: progid : DXImageTransform . Microsoft .
					gradient(startColorstr = '#ffffff', endColorstr = '#efefef');
				/* IE6,IE7 */
				-ms-filter:
					"progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff'}, endColorstr='#ebf1fd')"
					;
				padding-left: 5px;
				color: #000;
				font-weight: bold
			}
			.bt{
				padding:10px 0;
				color:#e56c00;
				font-size:14px;
				font-family: '微软雅黑'；
				}
			.bsptitle{
			    background: #EBF2FF;
			    line-height: 25px;
			    font-size: 14px;
			}
			.bot_line{
			    border-bottom:1px dashed #cbcbcb;
			}
			.td_bg{
			    background: #F6F9FD;
			    border: 1px solid #E4E9F3;
			    padding: 10px 0;
			}
		</style>
		<script type="text/javascript">
			var lay;
			function searchBspet(){
				var office = $("#office").val();
				var printno = $("#printno").val();
				var cp_pid = $("#cp_pid").val();
				var ksrq = $("#searchdate").val(); 
				if(office == "" || printno == "" || ksrq == ""){
					 layer.msg('所有参数都必填:office/打印机号/查询日期');
					 return;
				}
				//var layers = layer.load('加载中');
				$("#xtForm").submit();
				//layer.close(layers);
				//$("#bspetDataid").load(
				//"${ctx}/vedsb/cgdzbb/jpcgdz/searchBspet",
				//{"office" : office,"printno" : printno,"cp_pid" : cp_pid,"ksrq" : ksrq}
				//);
			}
			
			function searchTsl(type){
				var office = $("#office").val();
				var printno = $("#printno").val();
				var cp_pid = $("#cp_pid").val();
				var ksrq = $("#searchdate").val(); 
				if(office == "" || printno == "" || ksrq == ""){
					 layer.msg('所有参数都必填:office/打印机号/查询日期');
					 return;
				}
				$("#offices").val(office);
				$("#printnos").val(printno);
				$("#tsldates").val(ksrq);
				$("#agents").val(cp_pid);
				//var layers = layer.load('加载中');
				$("#tslForm").submit();
				//layer.close(layers);
			}
			
			function tslResult(){
				$("#compareoffice").val($("#office").val());
				$("#compareprint").val($("#printno").val());
				$("#comparetsldate").val($("#searchdate").val());
				var office = $("#office").val();
				var printno = $("#printno").val();
				var searchdate = $("#searchdate").val();
				lay = layer.load('系统正在处理你的操作,请稍后!');
				$.ajax({
	  				url : "${ctx}/vedsb/cgdzbb/jpcgdz/cgdzResult",
	  				type: "POST",
	  				data: {"office" :office,"printno":printno,"tsldate": searchdate},
	  				dataType : "json",
	  				success: function(result){
	  					if(result == '1'){
	  						lay = layer.load('您之前已经对过账,正在为你跳转对比结果页面,请稍等。。。');
	  						$("#compareForm").attr("action","${ctx}/vedsb/cgdzbb/jpcgdz/cgdzDbResult");
	  						$("#compareForm").submit();
	  					}else{
	  						lay = layer.load('已完成对比,正在为你跳转到对比结果页面,请稍等。。。');
	  						$("#compareForm").submit();
	  					}
	   				}
  			    });
			}
		</script>
	</head>
	<body>
			<table width="98%" style="background:#fff;">
				<!-- 标题行 开始 -->
				<tr>
					<td colspan="2" align="center" class="bt bsptitle">
					<strong>BSPET票对账</strong>
					</td>
				</tr>
				<tr class="bot_line">
					<td width="50%" class="bt">系统数据  &nbsp;&nbsp;  <!--  <input type="button" value=">>转旧版银行对账" class="asms_button" onClick="tobankdzOld()">--></td>
					<td width="50%" class="bt">TSL/TPR数据&nbsp;&nbsp; <!-- <input type="button" value=">>TSL数据查询" class="asms_button" onClick="tobankdzOld()">--></td>
				</tr>
				<!-- 标题行 结束 -->
				<!--  数据过滤查询区域   开始-->
				<tr>
				
				   <!--  数据过滤查询区域  左边 电商系统数据过滤区  开始-->
					<td class="td_bg">
					<form name="xtForm" id="xtForm" action="${ctx}/vedsb/cgdzbb/jpcgdz/searchBspet"
						method="post" target="xtframe">
						<input type="hidden" name="cplx" value="BSPET"/>
						<table class="table01" border="0" cellpadding="0" cellspacing="0">
							<tr>
							   <td class="tab_in_td_h" >office</td>
								<td class="tab_in_td_f">
									<input type="text" id="office" class="input1" name="office" style="width: 100px" value="${param.office }" />&nbsp;<font color="red">*</font>
								</td>
								
								<td class="tab_in_td_h" id="rqtjxs">打印机号</td>
								<td class="tab_in_td_f">
									<input type="text" id="printno" class="input1" name="printno"  style="width: 100px" value="${param.printno }"  />&nbsp;<font color="red">*</font>
								</td>
							<td class="tab_in_td_f" ></td>
							</tr>
							<tr>
								<td class="tab_in_td_h" id="rqtjxs">工作号</td>
								<td class="tab_in_td_f">
									<input type="text" id="cp_pid" class="input1" name="cp_pid" style="width: 100px" value="${param.agent }" />
								</td>
								<td class="tab_in_td_h">查询日期</td>
								<td class="tab_in_td_f">
								    <input type="text" name="ksrq" class="inputDate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" style="width: 100px"
								         	onClick="WdatePicker()" id="searchdate"/>&nbsp;<font color="red">*</font>
								</td> 
								<td class="tab_in_td_f" ><input name="button2" type="button" class="ext_btn ext_btn_submit" onClick="searchBspet();" value=" 查 询 " /></td>
							</tr>
						</table>
						</form>
					</td>
					<!--  数据过滤查询区域  左边 电商系统数据过滤区  结束-->
					 <form name="tslForm" id="tslForm" action="${ctx}/vedsb/cgdzbb/jpcgdz/serachTslData"
						method="post" target="hiddeniframe">
						<input type="hidden" name="querytype" value=""/>
						<input type="hidden" name="office" value="${param.office }" id="offices"/>
						<input type="hidden" name="printno" value="${param.printno }" id="printnos"/>
						<input type="hidden" name="agent" value="${param.agent }" id="agents"/>
						<input type="hidden" name="tsldate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" id="tsldates"/>
					
					<!--  数据过滤查询区域  右边边 TSL数据过滤区  开始-->
					<td width="50%" valign="top" class="td_bg">
						<input name="p" id="p" value="bankdzdup" type="hidden">
						<table class="table01" border="0" cellpadding="0" cellspacing="0">
							<td class="tab_in_td_h" style="text-align:left;"><input type="button" value="提取TSL数据" title="" class="ext_btn ext_btn_submit" id="tslButton" onClick="searchTsl(1)"></td>
							<td class="tab_in_td_f" ></td>
							<td class="tab_in_td_h" style="text-align:left;"></td>
							<td class="tab_in_td_f" ></td>
							</tr>
							<tr>
							<td class="tab_in_td_h" style="text-align:left;">最后提取时间为：</td>
							<td class="tab_in_td_f" style="text-align:left;" ><font size="3" color="#0000ff"><span id="last_get_datetime" title="最后一次调用接口获取航信数据的时间">${lastTime}</span></font></td>
							<td class="tab_in_td_h" style="text-align:left;"></td>
							<td class="tab_in_td_f" ><input type="button" value="重新提取" style="display:none;" title="" class="asms_button" id="find_tsl_button" onClick="searchTsl(2)"></td>
							</tr>
						</table>
						</form>
					</td>
					<!--  数据过滤查询区域  右边边 TSL数据过滤区  结束-->
				</tr>
				<!--  数据过滤查询区域   开始-->
				
				<!--  数据显示区域   开始-->
				<tr height="200px">
					<td width="50%" height="250" align="left" valign="top" style="position:relative !important;">
						<!-- 数据库数据 -->
						<span id="bspetDataid" > 
						<iframe width="100%" name="xtframe" id="xtframe" height="200" frameborder="1"></iframe>
						</span>
					</td>
					<td width="50%" height="250" align="left" valign="top">
						<!-- 导入数据 -->
						<span id="tslDataSpanid">
						<iframe width="100%" name="hiddeniframe" id="hiddeniframe" height="200" frameborder="1"></iframe>
						</span>
					</td>
					
				</tr>
				<!--  数据显示区域   结束-->
				
				<!--  自动对比操作按钮区域   结束-->
				<tr>
				
					<td colspan="2" align="center" style="padding:10px 0">
					<form name="compareForm" id="compareForm" action="${ctx}/vedsb/cgdzbb/jpcgdz/compareToTSl" method="post">
						<input type="hidden" name="office" value="${param.office }" id="compareoffice"/>
						<input type="hidden" name="dbjg" value="0"/>
						<input type="hidden" name="printno" value="${param.printno }" id="compareprint"/>
						<input type="hidden" name="tsldate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" id="comparetsldate"/>
						<input id="compare_button" type="button" class="ext_btn ext_btn_submit" value="开始对比两边数据" onClick="tslResult()"/>
						<input type="button" name="diff_button" id="diff_button" style="display:none" class="asms_button" title='导出对比的数据' onClick="diffExport('110809','01')" value="导  出" >
					</form>
					</td>
				</tr>
				<!--  自动对比操作按钮区域   结束-->
				
				<!--  自动对比结果显示区域   开始-->
				<!-- <tr>
					<td align="left" valign="top" colspan="2">
						<div id="tbl-container_new" >
						<iframe width="100%" name="iframe_compare_result" id="iframe_compare_result" height="320" frameborder="1"></iframe>
						
						
						</div><br/>
						<textarea name="textleft_new" id="textleft_new" cols="45" rows="12"
							style="width: 840px; height: 120px; display: none"></textarea>

					</td>
				</tr>
				 -->
				<!--  自动对比结果显示区域   结束-->
			</table>
		</form>
</body>
</html>
