<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>自动出票规则设置</title>
    <link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/core/jmultiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/core/jqueryui/jquery-ui.css" />
	<script type="text/javascript" src="${ctx}/static/core/jmultiselect/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/core/jmultiselect/jquery.multiselect.js"></script>
	<script type="text/javascript" src="${ctx}/static/core/calendar/xbtj.js"></script>
	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
	<style type="text/css">
		.td_right{
		    width: 228px;
		    line-height: 20px;
		}
		.td_righthclx{
			width: 132px;
			line-height: 20px;
			text-align: right;
		}
	</style>
 	<script type="text/javascript">
 		var validator;
 		$(function(){
 		 	validator = $('#zdcpgzsz').Validform({
				tiptype:3,
				beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交; 
					return false;   
				}
			});
			var cpszdbjzclx = '${entity.ptbjCpzclx}';
			var tbszdbjzclx = '${entity.ptbjTbzclx}';
			$('input[name="ptcpszdbj"]:checked').each(function(){
				if(cpszdbjzclx.indexOf($(this).val())!=-1){
					$(this).attr("checked","checked");
				}
			});
			$('input[name="zdbjtb"]:checked').each(function(){
				if(tbszdbjzclx.indexOf($(this).val())!=-1){
					$(this).attr("checked","checked");
				}
			});
 		})
 		//关闭当前页面
 		function closecpgz(){
 			window.close();
 		}
 		
 		function savecpgz(){
 			$("#cpgzszform").submit();
 		}
 		
 		function laststep(index){
 			if(index == 3){//最后一页往上翻
 				$("#cpgzptbjtab").hide();
 				$("#zdcpgzsztab").hide();
 				$("#hcsytjtab").show();
 			}else if(index == 2){//中间也往上翻
 				$("#cpgzptbjtab").hide();
 				$("#zdcpgzsztab").show();
 				$("#hcsytjtab").hide();
 			}
 		}
 		
 		function nextstep(index){
 			if(index == 2){
 				$("#cpgzptbjtab").show();
 				$("#zdcpgzsztab").hide();
 				$("#hcsytjtab").hide();
 			}else if(index == 1){
 				$("#cpgzptbjtab").hide();
 				$("#zdcpgzsztab").hide();
 				$("#hcsytjtab").show();
 			}
 		}
 		
 		function changecpszclx(obj){
 			var cpszdbj = '';
 			$('input[name="ptcpszdbj"]:checked').each(function(){
 				cpszdbj+=$(this).val()+"/";
 			});
 			$("#zdbjzclx").val(cpszdbj);
 		}
 		
 		function changetbzclx(obj){
 			var tbszdbj = '';
 			$('input[name="zdbjtb"]:checked').each(function(){
 				tbszdbj+=$(this).val()+"/";
 			});
 			$("#cytbbjzclx").val(tbszdbj);
 		}
 	</script>
  </head>
  <body>
    <div class="container">
      <div id="forms">
        <div class="box">
          <div class="box_border">
            <div class="box_center">
            <form action="" method="post" id="cpgzszform">
            <input type="hidden" name="closeDiv" value="true"/>
            <input type="hidden" name="callback"  value="parent.refresh()" />
            <input type="hidden" name="id" value="${entity.id }">
            <!-- 自动出票规则设置开始 -->
            	<form id="zdcpgzsz">
            		<table  class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="zdcpgzsztab">
            			
            		</table>
            	</form>
            <!-- 自动出票规则设置结束 -->
            <!-- 航程适用条件开始 -->
            	<form id="hcsytj" >
            		<table  class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="hcsytjtab">
            			<tr>
            				<td class="td_righthclx">航程类型：</td>
	            			<td>
	            				<input type="radio" name="hclx" value="1" id="hclxdc" ${entity.hclx eq '1' ? 'checked' : '' }/><label for="hclxdc">单程</label>
	            				<input type="radio" name="hclx" value="2" id="hclxwf" ${entity.hclx eq '2' ? 'checked' : '' }/><label for="hclxwf">往返</label>
	            				<input type="radio" name="hclx" value="3" id="hclxlc" ${entity.hclx eq '3' ? 'checked' : '' }/><label for="hclxlc">联程</label>
	            				<input type="radio" name="hclx" value="4" id="hclxqk" ${entity.hclx eq '4' ? 'checked' : '' }/><label for="hclxqk">缺口</label>
	            			</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">适用乘机人类型：</td>
            				<td>
            					<input type="radio" name="cjrlx" value="1" id="cjrlxcr" ${entity.cjrlx eq '1' ? 'checked' : '' }/><label for="cjrlxcr">成人</label>
            					<input type="radio" name="cjrlx" value="2" id="cjrlxet" ${entity.cjrlx eq '2' ? 'checked' : '' }/><label for="cjrlxet">儿童</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">乘机日期：</td>
            				<td>
            					<input type="text" name="cjrqs" value="${entity.cjrqs}" size=10 id="cjrqs" onFocus="WdatePicker({onpicked:function(){cjrqz.focus();},minDate:'cal.getDateStr()'})"/>&nbsp;&nbsp;
            					至&nbsp;&nbsp;<input type="text" name="cjrqz" value="${entity.cjrqz}" size=10 id="cjrqz" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'cjrqs\')}'})"/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">不享受日期：</td>
            				<td>
            					<input type="text" name="bxsrq" value="${entity.bxsrq}" size=20 onclick="_bsyrqFocus(this);" id="tjzcForm_jp_jzyxcp_cpsytj_bsyrq"/><font color="gray">(格式：YYYY-MM-DD，多个使用"/"分隔)</font>
            					<!-- 不适用日期div显示隐藏 -->	 
								<div id="cpsytj_bsyrqDiv" style="display: none;top:121px; left:139px;position:absolute;width:200px;height:200px;" align="right"></div>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号适用条件：</td>
            				<td>
            					<input type="radio" name="hbhsfsy" value="0" id="hbhqbsy" ${entity.hbhqbsy eq '0' ? 'checked' : '' }/><label for="hbhqbsy">全部适用</label>
            					<input type="radio" name="hbhsfsy" value="1" id="hbhsy" ${entity.hbhqbsy eq '1' ? 'checked' : '' }/><label for="hbhsy">适用</label>
            					<input type="radio" name="hbhsfsy" value="2" id="hbhbsy" ${entity.hbhqbsy eq '2' ? 'checked' : '' }/><label for="hbhbsy">不适用</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">航班号：</td>
            				<td>
            					<input type="text" name="hbh" value="${entity.hbh}" size=15 datatype="*,multihbh"/><font color="gray">(多个使用/分隔)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">共享航班是否适用：</td>
            				<td>
            					<input type="radio" name="gxhbsfsy" value="1" id="hbhsy" ${entity.hbhqbsy eq '1' ? 'checked' : '' }/><label for="hbhsy">适用</label>
            					<input type="radio" name="gxhbsfsy" value="0" id="hbhbsy" ${entity.hbhqbsy eq '0' ? 'checked' : '' }/><label for="hbhbsy">不适用</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">出发城市：</td>
            				<td><input type="text" value="${entity.cfcity}" name="cfcity" size=18 datatype="*,multicity"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">到达城市：</td>
            				<td><input type="text" value="${entity.ddcity}" name="ddcity" size=18 datatype="*,multicity"/><font color="gray">(三字码，多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">舱位：</td>
            				<td><input type="text" value="${entity.cw}" name="cw" size=15 datatype="*,multicw"/><font color="gray">(多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">适用班期：</td>
            				<td><input type="text" value="${entity.sybq}" size=10 name="sybq"/><font color="gray">(多个使用/分隔)</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">最晚提前出票天数：</td>
            				<td>
            					<input type="text" name="zwtqcpts" value="${entity.zwtqcpts}"/><font color="gray">(天)</font>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>最早提前出票天数：&nbsp;&nbsp;</font> <input type="text" name="zztqcpts" value="${entity.zwtqcpts}"/><font color="gray">(天)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">订单最少旅客人数限制：</td>
            				<td>
            					<input type="text" name="zslkrs" value="${entity.zslkrs}"/><font color="gray">(人)(最小值为1，订单人数需要大于等于此值)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">协议号</td>
            				<td><input type="text" name="xyh" value="${entity.xyh }" size=16/><font color="gray">支持录入多个协议号，使用英文"/"分隔</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">乘机人名中含：</td>
            				<td><input type="text" name="" value="" size=12/>字符不能自动出票<font color="gray">支持录入多个，使用英文"/"分隔</font></td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">OSI项检查：</td>
            				<td>
            					<input type="checkbox" value="1" name="" id="checksjh" onclick="changetbzclx(this)"><label for="checksjh">检查订单乘机人手机号</label>
			                   	<input type="checkbox" value="2" name="" id="zdcpjxcp" onclick="changetbzclx(this)"><label for="zdcpjxcp">检查PNR内容CTCT项</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">是否换编码出票：</td>
            				<td>
            					<input type="radio" value="0" name="sfhpnr" id="nhbmcp"><label for="nhbmcp">否</label>
            					<input type="radio" value="1" name="sfhpnr" id="hbmcp"><label for="hbmcp">是</label>&nbsp;&nbsp;
            					<input type="checkbox" name="" id="kqybmcc"><label for="kqybmcc">开启原编码重出</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_righthclx">换编码OSI项：</td>
            				<td>
            					<input type="text" name="hbmosi" value="${entity.hbmosi}" size=9/>&nbsp;&nbsp;&nbsp;&nbsp;
            					<font>换编码OFFICE：</font>&nbsp;&nbsp;<input type="text" name="hbmoffice" value="${entity.hbmoffice}" size=9/>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_center" colspan="2">
            					<input type="button" value="上一步" class="ext_btn ext_btn_submit" onclick="laststep(3)"/>
            					<input type="button" value="下一步" class="ext_btn ext_btn_submit" onclick="nextstep(2)"/>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>
								<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            <!-- 航程适用条件结束 -->
            <!-- 平台比价配置form开始 -->
            	<form id="cpgzptbj">
            		<table class="table01" width="100%" border="0" cellpadding="0" cellspacing="0" id="cpgzptbjtab" style="display: none">
            			<tr>
            				<td class="td_right">参与自动比价出票的cps政策类型：</td>
            				<td>
            					<input type="hidden" name="ptbjCpzclx" id="zdbjzclx"/>
            					<input type="checkbox" value="1" id="zdcpptzc" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcpptzc">普通政策</label>
			                   	<input type="checkbox" value="2" id="zdcptjzc" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcptjzc">特价政策</label>
			                   	<input type="checkbox" value="3" id="zdcplytb" onclick="changecpszclx(this)" name="ptcpszdbj"><label for="zdcplytb">特殊政策</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">参与自动比价出票的淘宝旗舰店政策类型：</td>
            				<td>
            					<input type="hidden" name="ptbjTbzclx" id="cytbbjzclx"/>
            					<input type="checkbox" value="1" name="zdbjtb" id="zdcpzdjcp" onclick="changetbzclx(this)"><label for="zdcpzdjcp">全网最低价产品</label>
			                   	<input type="checkbox" value="2" name="zdbjtb" id="zdcpjxcp" onclick="changetbzclx(this)"><label for="zdcpjxcp">精选产品</label>
			                   	<input type="checkbox" value="3" name="zdbjtb" id="zdcpjpcp" onclick="changetbzclx(this)"><label for="zdcpjpcp">金牌产品</label>
			                   	<input type="checkbox" value="4" name="zdbjtb" id="zdcphscp" onclick="changetbzclx(this)"><label for="zdcphscp">航司产品</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">换编码政策是否参与比价：</td>
            				<td>
            					<input type="radio" name="ptbjHbmzc" id="hbmcybj" value="1" ${entity.ptbjHbmzc eq '1' ? 'checked' : ''}><label for="hbmcybj">参与</label>
            					<input type="radio" name="ptbjHbmzc" id="hbmbcybj" value="0" ${entity.ptbjHbmzc eq '0' ? 'checked' : ''}><label for="hbmbcybj">不参与</label><font color="gray">(非淘宝旗舰店政策时会判断此设置)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">非CPS政策特殊政策是否参与比价：</td>
            				<td>
            					<input type="radio" name="ptbjTszc" id="fcpscybj" value="1" ${entity.ptbjTszc eq '1' ? 'checked' : '' }><label for="fcpscybj">参与</label>
            					<input type="radio" name="ptbjTszc" id="fcpsbcybj" value="0" ${entity.ptbjTszc eq '0' ? 'checked' : '' }><label for="fcpsbcybj">不参与</label>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">废票时间点限制：</td>
            				<td><input type="text" name="ptbjFpsjxz" id="fpsjxz" value="${entity.ptbjFpsjxz}" size="10" datatype="/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/"/><font color="gray">(格式22:00，比价自动出票时，只取废票工作时间超过此时间点的平台政策)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">废票时间差忽略范围要求：</td>
            				<td><input type="text" name="ptbjFpsjc" id="fpsjhlfw" value="${entity.ptbjFpsjc}" size="10" datatype="number,dotformat" dotformat="###."/><font color="gray">(分钟)(若两政策废票时间差小于此时间，则认为废票时间相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">出票时间差忽略范围要求：</td>
            				<td><input type="text" name="ptbjCpsjc" id="fpsjhlfw" value="${entity.ptbjCpsjc}" size="10" datatype="number,dotformat" dotformat="###."/><font color="gray">(分钟)(若两政策出票时间差小于此时间，则认为出票时间相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">按起飞时间限制：</td>
            				<td><input type="text" name="ptbjQfsjxz" id="qfsjxz" value="${entity.ptbjQfsjxz}" size="10" datatype="number"/><font color="gray">(小时)(设置几小时内起飞的航班不能该出票渠道自动出票，支持小数，0表示不控制)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">排序忽略返点差：</td>
            				<td><input type="text" name="ptbjPxhlfd" id="pxhlfdc" value="${entity.ptbjPxhlfd}" size="10" datatype="number,dotformat" dotformat="###.##"/><font color="gray">(%)(若两政策返点差值差小于此值，则认为返点相等)</font></td>
            			</tr>
            			<tr>
            				<td class="td_right">出票类型优先级：</td>
            				<td>BSPET >> BPET</td>
            			</tr>
            			<tr>
            				<td class="td_right">采购平台优先级别：</td>
            				<td>CPS >> 百拓 >> 8000yi >> 51book >> 517 >> 今日</td>
            			</tr>
            			<tr>
            				<td class="td_right">平台自动比价优先级：</td>
            				<td>废票时间最晚   >>   出票类型优先  >>  采购平台优先级别  >>  出票时间最晚</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到OP政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjOpyccl" id="nopjxcp" value="1" ${entity.ptbjOpyccl eq '1' ? 'checked' : ''}><label for="nopjxcp">继续出票</label>
            					<input type="radio" name="ptbjOpyccl" id="nzcztcp" value="2" ${entity.ptbjOpyccl eq '2' ? 'checked' : ''}><label for="nzcztcp">任何一个参与比价的平台未取到政策则暂停比价出票</label>
            					<input type="radio" name="ptbjOpyccl" id="nzczzcp" value="3" ${entity.ptbjOpyccl eq '3' ? 'checked' : ''}><label for="nzczzcp">任何一个参与比价的平台未取到政策则终止全自动出票</label></br>
            					<input type="radio" name="ptbjOpyccl" id="sycybjztcp" value="4" ${entity.ptbjOpyccl eq '4' ? 'checked' : ''}><label for="sycybjztcp">所有参与比价的平台未取到政策则暂停比价出票</label>
            					<input type="radio" name="ptbjOpyccl" id="sycybjzzcp" value="5" ${entity.ptbjOpyccl eq '5' ? 'checked' : ''}><label for="sycybjzzcp">所有参与比价的平台未取到政策则终止全自动出票</label><font color="gray">(勾选OP平台参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到BPET政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjB2byccl" id="nbpetjxcp" value="1" ${entity.ptbjB2byccl eq '1' ? 'checked' : '' }><label for="nbpetjxcp">继续出票</label>
            					<input type="radio" name="ptbjB2byccl" id="nbpetztcp" value="2" ${entity.ptbjB2byccl eq '2' ? 'checked' : '' }><label for="nbpetztcp">暂停比价出票</label>
            					<input type="radio" name="ptbjB2byccl" id="nbpetztzzcp" value="3" ${entity.ptbjB2byccl eq '3' ? 'checked' : '' }><label for="nbpetztzzcp">终止全自动出票</label><font color="gray">(勾选BPET平台参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_right">未取到淘宝旗舰店政策是否继续自动出票：</td>
            				<td>
            					<input type="radio" name="ptbjTbyccl" id="ntbjxcp" value="1" ${entity.ptbjTbyccl eq '1' ? 'checked' : '' }><label for="ntbjxcp">继续出票</label>
            					<input type="radio" name="ptbjTbyccl" id="ntbztcp" value="2" ${entity.ptbjTbyccl eq '2' ? 'checked' : '' }><label for="ntbztcp">暂停比价出票</label>
            					<input type="radio" name="ptbjTbyccl" id="ntbztzzcp" value="3" ${entity.ptbjTbyccl eq '3' ? 'checked' : '' }><label for="ntbztzzcp">终止全自动出票</label><font color="gray">(勾选淘宝旗舰店政策参与比价时，此设置才生效)</font>
            				</td>
            			</tr>
            			<tr>
            				<td class="td_center" colspan="2">
            					<input type="button" value="上一步" class="ext_btn ext_btn_submit" onclick="laststep(3)"/>
								<input type="button" value="关闭" class="ext_btn ext_btn_submit" onclick="closecpgz()"/>
								<input type="button" value="保存" class="ext_btn ext_btn_submit" onclick="savecpgz()"/>            				
            				</td>
            			</tr>
            		</table>
            	</form>
            	<!-- 平台比价配置form结束 -->
            	</form>
            </div>
          </div>
        </div>
     </div>
   </div>
  </body>
</html>
