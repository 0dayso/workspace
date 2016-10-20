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
		if($("#tknos").val() == '' && $("#pnrNo").val() == ''){
			layer.msg('请输入PNR或者票号');
			return false;
		}
		layer.load("正在执行你的操作,请稍候!");
		$("#searchForms").submit();
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
               <form action="${ctx}/vedsb/jpcwgl/jptpdhistory/jpHistoryTpList" id="searchForms" name="searchForm" method="post">
                <input type="hidden"  name="lx" value="3" />
	           	<input type="hidden"  name="VIEW" value="B5D963EE5A67B69707F15B6A9CBAEF60" />
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                   <td style="text-align: right;">P N R</td>
		                   <td>
		                  		<input type="text" value="${not empty param.pnrNo ? param.pnrNo : pnrNo}" class="input-text lh30" size="10" name="pnrNo" onblur="value=$.trim(this.value).toUpperCase();" id="pnrNo"/>
		                   </td>
		                   <td class="xsys" style="text-align: right;">票号</td>
		                   <td>
		                  	 	<input type="text" value="${not empty param.tkno ? param.tkno : tkno}" name="tkno" class="input-text lh30" size="10" id="tknos"/>
		                   </td>
	                  	   <td><input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="historySearch();"/></td>
		               </tr>
	              </table>
	              </form>
            </div>
          </div>
        </div>
      <div  class="mt10">
        <div class="box span10 oh">
        <c:if test="${not empty list}">
        <form action="${ctx}/vedsb/jpcwgl/jptpdhistory/jpHistoryTpSave" id="saveform" method="post">
        <input type="hidden" value="${param.pnrNo}" name="pnrNo">
        <input type="hidden" value="${param.tkno}" name="tkno">
        <table width="780px" border="0" cellpadding="0" cellspacing="0" class="list_table">
			<tr>
				<th width="30">票号</th>
				<th width="30">PNR</th>
				<th width="30">退票状态</th>
				<th width="30">联系人</th>
				<th width="45">联系电话</th>
				<th width="35">航程</th>
				<th width="20">航班</th>
				<th width="20">舱位</th>
				<th width="30">乘机人</th>
				<th width="30">退票手续费</th>
				<th width="30">支付科目</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="v">
			<input type="hidden" name="itkno" value="${list.TKNO}"/>
				<tr>
				<td width="20">${list.TKNO}</td>
				<td width="45">${list.XS_PNR_NO }</td>
				<td width="45">${list.XS_TPZT eq '0' ? '已申请' : (list.XS_TPZT eq '1' ? '已审核' : (list.XS_TPZT eq '2' ? '已办理' : '已取消'))}</td>
				<td width="55">${list.NXR}</td>
				<td width="55">${list.NXDH}</td>
				<td width="55">${list.HC}</td>
				<td width="55">${list.XS_HBH}</td>
				<td width="55">${list.XS_CW}</td>
				<td width="55">${list.CJR}</td>
				<td width="20"><input type="text" value="${list.XS_TPSXF}" name="xstpsxf_${list.TKNO}" style="width: 50px" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"/></td>
				<td width="30">
					<select name="zfkm_${list.TKNO}">
					<c:forEach items="${zfkm}" var="z">
						<option value="${z.kmbh}" ${list.skkm eq z.kmbh ? selected : ''}>${z.kmmc}</option>
					</c:forEach>
					</select>
				</td>
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