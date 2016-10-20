<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	$(function(){
		//if('${saveflag}' == '1'){
			//$("#searchForms").submit();
		//}
		validator = $("#saveform").Validform({
					tiptype:3
				});
		//$("#searchFormButton").click();
	});
	
	function toSave(){
		validator.submitForm(false);
	}
	
	function historySearch(){
		if($("#pnrNo").val() == ''){
			layer.msg('请输入PNR');
			return false;
		}
		layer.load("正在执行你的操作,请稍后!");
		$("#searchForms").submit();
	}
	
	function changexttk(id){
		var xtje = $("#xttks_"+id).val();
		var wdje = $("#wdtks_"+id).val();
		var cy = (xtje-wdje).toFixed(2);
		$("#cyje_"+id).text(cy);
		$("#cyjehidden_"+id).val(cy);
	}
	
	function changewdtk(id){
		var wdje = $("#wdtks_"+id).val();
		var xtje = $("#xttks_"+id).val();
		var cy = (xtje-wdje).toFixed(2);
		$("#cyje_"+id).text(cy);
		$("#cyjehidden_"+id).val(cy);
	}
</script>
</head>
<body>
 <%@ include file="/WEB-INF/views/jpcwgl/historylable.jsp"%>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpcwgl/jpcydhistory/jpHistoryCyList" id="searchForms" name="searchForm" method="post">
                <input type="hidden"  name="lx" value="4" />
	           	<input type="hidden"  name="VIEW" value="2E7CA5F977DDBE62EB4373EC4A869818" />
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                   <td style="text-align: right;">P N R</td>
		                   <td>
		                  		<input type="text" value="${not empty param.pnrNo ? param.pnrNo : pnrNo}" class="input-text lh30" size="10" name="pnrNo" onblur="value=$.trim(this.value).toUpperCase();" id="pnrNo"/>
		                   </td>
		                   <td><input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="historySearch();"/></td>
		               </tr>
	              </table>
	              </form>
            </div>
          </div>
        </div>
      <div  class="mt10">
        <div class="">
        <c:if test="${not empty list}">
        <form action="${ctx}/vedsb/jpcwgl/jpcydhistory/jpHistoryCySave" id="saveform" method="post">
        <input type="hidden" value="${param.pnrNo}" name="pnrNo">
        <table width="780px" border="0" cellpadding="0" cellspacing="0" class="list_table">
			<tr>
				<th width="30">状态</th>
				<th width="30">订单类型</th>
				<th width="30">外部单号</th>
				<th width="30">PNR</th>
				<th width="45">差异类型</th>
				<th width="35">系统金额</th>
				<th width="35">网店金额</th>
				<th width="35">差异金额</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="v">
			<input type="hidden" name="ids" value="${list.ID}"/>
				<tr>
				<td width="45">${list.ZT eq '0' ? '待审核' : (list.XS_TPZT eq '1' ? '已审核' : (list.XS_TPZT eq '2' ? '已确认' : '已取消'))}</td>
				<td width="55">${list.DDLX eq '1' ? '机票正常单' : (list.DDLX eq '2' ? '退票单' : '改签单')}</td>
				<td width="55">${list.WBDH}</td>
				<td width="55">${list.PNR_NO}</td>
				<td width="55">${list.CYLX eq '1' ? '票价' : (list.CYLX eq '2' ? '机建' : (list.CYLX eq '3' ? '税费' : (list.CYLX eq '4' ? '退票费' : '改签费')))}</td>
				<td width="55"><input type="text" id="xttks_${list.ID}" onkeyup="changexttk('${list.ID}');" value="${list.XT_TKJE}" name="xttkje_${list.ID}" style="width: 50px" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"/></td>
				<td width="55"><input type="text" id="wdtks_${list.ID}" onkeyup="changewdtk('${list.ID}');" value="${list.WD_TKJE}" name="wdtkje_${list.ID}" style="width: 50px" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"/></td>
				<td width="20"><span id="cyje_${list.ID}">${list.CYJE}</span></td>
				<input type="hidden" value="${list.CYJE}" name="cyjes_${list.ID}" id="cyjehidden_${list.ID}"/>
				</tr>
			</c:forEach>
			<tr>
				<td style="text-align: center;" colspan="10"><input type="button" class="ext_btn ext_btn_submit" value="保  存" onclick="toSave();" /></td>
			</tr>
		</table>
		</form>
		</c:if>
		<c:if test="${empty list}">
			没有查询到任何数据!
		</c:if>
        </div>
     </div>
   </div>
  </div>
</body>
</html>