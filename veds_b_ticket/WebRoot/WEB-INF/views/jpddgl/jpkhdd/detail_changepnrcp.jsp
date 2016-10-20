<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">	
	//关闭页面
	function closePage(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	
	var validator;
	var ii;
	$(function(){
		validator = $("#changePnrForm").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(transport){
				if(transport.code=="1"){
				   if(confirm("换PNR成功！"+transport.msg)){
				   		window.close();
				   }
				}else{
				   alert(transport.msg);
				}
				layer.close(ii);
			},
			beforeSubmit:function(curform){
			    ii = layer.load('系统正在处理您的操作,请稍候!');
		    }
		});
	})
	
	function saveCsrq(ddbh, flag){
		var pnr_osi = $("#pnr_osi");
		var msg = "确认换PNR出票，";
		if(flag == '1'){
			msg = "确认换PNR出票并授权，";
			document.changePnrForm.sfsq.value = "1";
		}
		if (!pnr_osi) {
			msg = "您没有输入OSI CTCT项，确认将默认使用PID设置补全该指令后换PNR出票，";
		}
		if(confirm(msg+"是否继续？")){
			validator.submitForm(false);
		}
	}
</script>	
<div style="height: auto;overflow-y:auto;">
	<form id="changePnrForm" name="changePnrForm" action="${ctx}/vedsb/jpddgl/jpkhdd/changePnrAndCw" method="post" >
		<input type="hidden" name="id" value="${jpkhdd.DDBH}" />
		<input type="hidden" name="dp_officeid" value="${jpkhdd.dp_officeid}" />
		<input type="hidden" name="pnr_no" value="${jpkhdd.pnr_no}" />
		<input type="hidden" name="sfsq" value="" />
		<input type="hidden" name="jcbs" value="${param.jcbs}" />
		<input type="hidden" name="cwdh" id="cwdh" value="${param.cwdh }" /><!-- 新的舱位代代号 -->
		<input type="hidden" name="gqdh" id="gqdh" value="${param.gqdh }" /><!-- 改签详点降舱 -->
		<input type="hidden" name="fjjx" id="fjjx" value="${param.fjjx }" />
		<input type="hidden" name="cfsj" id="cfsj" value="${param.cfsj }" />
		<input type="hidden" name="ddsj" id="ddsj" value="${param.ddsj }" />
		<input type="hidden" name="hzl" id="hzl" value="${param.hzl }" />
		<input type="hidden" name="hbh" id="hbh" value="${param.hbh }" />
		<input type="hidden" name="cw" id="cw" value="${param.cw }" /><!-- 改签详点降舱 -->
		<input type="hidden" name="price" id="price" value="${param.price }" /><!-- 改签详点降舱 -->
		<c:set var="osi" value="${jpkhdd.NXSJ}" />
		<table class="t_tab2" cellpadding="0" cellspacing="0" align="center" width="99%">
			<tr>
				<td class="shuomin" colspan="3">
					&nbsp;&nbsp;您可以选择录入手机号补全OSI CTCT项指令
				</td>
			</tr>
			<tr>
				<td align="right">
					<b>OSI&nbsp;${fn:replace(jpkhdd.pnr_hkgs,'*','')}&nbsp;CTCT&nbsp;&nbsp;</b>
				</td>
				<td>
					<input type="text" id="pnr_osi" name="pnr_osi" class="input1 validate-mobile-phone max-length-13" size="16" >
				</td>
				<td>
					<input type="button" id="lxrBtn" name="lxrBtn" value="使用订单联系手机" onclick="$('#pnr_osi').val('${osi}')" class="ext_btn ext_btn_submit" />
				</td>
			</tr>
			<tr>
				<td align="right">
				    <b>OFFICE&nbsp;号&nbsp;&nbsp;</b>
				</td>
				<td colspan="2">
				    <input type="text" id="dz_officeid" name="dz_officeid" size="16" value="${jppz.officeid}">
				</td>
			</tr>
			<tr>
				<td align="right">
				    <b>保留时限&nbsp;&nbsp;</b>
				</td>
				<td colspan="2">
					<input type="text" name="hd_cfsj" id="cfsj${index}" value="${fn:substring(((empty jpkhdd.NOSJ or jpkhdd.NOSJ <= (vfn:getPreMin(vfn:longDate(),30))) ? (vfn:getPreMin(vfn:longDate(),180)) : jpkhdd.NOSJ),0,16) }" class="input-text Wdate" size="16" id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">&nbsp;
					<font color='red'>*</font>
				</td>
			</tr>
		</table>
		<div style="text-align: center;margin-top: 5px;padding-top:5px;width: 100%;background: #fff;">
		   <c:if test="${param.frompage eq 'gqxq'}">
		   		<input type="button" value="确认生成新订单" class="asms_button" onclick="generateNewOrder();">&nbsp;&nbsp;
		   		<input type="button" value=" 取 消 " class="asms_button" onclick="hideQuickDiv()">&nbsp;&nbsp;
		   </c:if>
		   <c:if test="${param.frompage ne 'gqxq'}">
			    <input type="button" value="换PNR出票" class="ext_btn ext_btn_submit" onclick="saveCsrq('${jpkhdd.DDBH}')">&nbsp;&nbsp;
				<input type="button" value="换PNR出票并授权" class="ext_btn ext_btn_submit" onclick="saveCsrq('${jpkhdd.DDBH}', '1')" title="换编码成功后，将自动授权给【${jpkhdd.dp_officeid }】">&nbsp;&nbsp;
				<input type="button" value=" 关闭" class="ext_btn ext_btn_submit" onclick="closePage()">&nbsp;&nbsp;
				<c:if test="${not empty param.cwdh}">
					<input type="button" value="重选舱位" class="ext_btn ext_btn_submit" onclick="parent.reChooseCw()">
				</c:if>
		   </c:if>
		</div>
	</form>
</div>
