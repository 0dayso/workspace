<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<span id="tsl_list">
<div id="tbl-container" style="height:200px;width:100%">
<display:table id="tsl_info" name="list"  class="list_table" style="width:750px;">
	       <display:column title="序号">${tsl_info_rowNum }</display:column>
	       <display:column title="类型">
                <c:if test="${tsl_info.isVoid eq '0' && tsl_info.isRefund eq '0'}">正常票</c:if>
                <c:if test="${tsl_info.isVoid eq '1' && tsl_info.isRefund eq '0'}">退票</c:if>
                <c:if test="${tsl_info.isVoid eq '0' && tsl_info.isRefund eq '1'}">退票</c:if>
			</display:column>
	       <display:column  title=" 票号<br>TKT-NUMBER" style="text-align:left;" property="ticketno" >
		   </display:column>
	       <display:column title=" 航程<br>ORIG-DEST" style="text-align:left;" property="origdest">
			</display:column>
			<display:column property="collection" title=" 账单价/实退金额<br>collection" style="text-align:right;"/>
			<display:column property="taxs" title=" 税费<br>taxs" style="text-align:right;"/>
			<display:column property="comm" title=" C值<br>comm%" style="text-align:right;"/>
			<display:column property="pnr" title=" pnr" style="text-align:left;"/>
			<display:column title=" 工作号<br>agent" property="agent" style="text-align:left;"/>
			<display:column title=" OFFICE号" property="office" style="text-align:left;"/>
			<display:column title=" PID" property="pid" style="text-align:left;"/>
			<display:column title=" 打印机号<br>printno" property="printno" style="text-align:right;"/>
			<display:column property="date" title="出票日期<br>date" maxLength="15" style="text-align:left;"/>
	    </display:table>
</div>
<script type="text/javascript">
 // var button1=parent.document.getElementById("tslButton");
  //var button2=document.getElementById("find_tsl_button");
  //if(button1!=null){
  //	button1.disabled="";
 // }
  //if(button2!=null){
   // button2.disabled="";
 // } 
  var td_msg=parent.document.getElementById("last_get_datetime");
  if(td_msg!=null){
  	td_msg.innerHTML="${lastTime}";
  }
</script>

</span>