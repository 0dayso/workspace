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
		if($("#tknos").val() == '' && $("#pnrNo").val() == '' && $("#gqdh").val() == ''){
			layer.msg('请输入PNR或者票号或者改签单号');
			return false;
		}
		layer.load("正在执行你的操作,请稍后!");
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
               <form action="${ctx}/vedsb/jpcwgl/jpgqdhistory/jpHistoryGqList" id="searchForms" name="searchForm" method="post">
                <input type="hidden"  name="lx" value="2" />
	           	<input type="hidden"  name="VIEW" value="86CCE6D3ABA73E6DF478565F8F052708" />
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
						   <td style="text-align: right;">改签单号</td>
		                   <td>
		                  		<input type="text" value="${not empty param.gqdh ? param.gqdh : gqdh}" class="input-text lh30" size="10" name="gqdh" onkeyup="this.value=this.value.replace('%','').toUpperCase()" id="gqdh"/>
		                   </td>		              
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
        <form action="${ctx}/vedsb/jpcwgl/jpgqdhistory/jpHistoryGqSave" id="saveform" method="post">
        <input type="hidden" value="${param.pnrNo}" name="pnrNo">
        <input type="hidden" value="${param.tkno}" name="tkno">
         <input type="hidden" value="${param.gqdh}" name="gqdh">
        <table width="780px" border="0" cellpadding="0" cellspacing="0" class="list_table">
			<tr>
				<th width="30">乘机人</th>
				<th width="45">航程</th>
				<th width="35">票号</th>
				<th width="20">采购改签费用</th>
				<th width="20">客户改签费用</th>
				<th width="30">收款科目</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="v">
			<input type="hidden" name="gqdh_${list.TKNO}" value="${list.GQDH}"/>
			<input type="hidden" name="itkno" value="${list.TKNO}"/>
				<tr>
				<td width="20">${list.CJR}</td>
				<td width="45">${list.HC }</td>
				<td width="45">${list.TKNO}</td>
				<td width="55"><input type="text" value="${list.GQ_CGFY}" name="jpgqcgfy_${list.TKNO}" style="width: 50px" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"/></td>
				<td width="20"><input type="text" value="${list.GQ_XSFY}" name="jpgqxsfy_${list.TKNO}" style="width: 50px" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"/></td>
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