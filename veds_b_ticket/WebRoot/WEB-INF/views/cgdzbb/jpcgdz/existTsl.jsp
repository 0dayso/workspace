<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>采购对账只存在tsl数据</title>
<script type="text/javascript">

</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cgdzbb/jpcgdz/czdzlable.jsp"%>
	<div class="container clear">
  	  <div id="search_bar">
      <!--  <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
            
            </div>
          </div>
        </div>
        -->
      <div  class="mt10">
      	<c:if test="${not empty list}">
      	<display:table id="ticketInfo" name="list"  class="list_table">
			<display:column title="序号" style="width: 20px">${ticketInfo_rowNum}</display:column>
					<display:column title="类型" style="text-align:left;" expfield="{pzlx,1,正常票,2,退票,3,废票}" >
						${ticketInfo.pzlx eq '1' ? '正常票' : '' }
						${ticketInfo.pzlx eq '2' ? '退票' : '' }
						${ticketInfo.pzlx eq '3' ? '废票' : '' }
					 </display:column>
					 <display:column title="票号" style="text-align:left;" property="tkno" expfield="tkno"></display:column>
					 <display:column title="航程" style="text-align:left;" expfield="tslhc" property="tslhc"></display:column>
					 <display:column title="账单价/实退金额" property="tslzdj" style="text-align:right;"/>
					 <display:column title="税费" property="tsltax" style="text-align:right;"/>
					 <display:column title="C值" property="tsldlfl" style="text-align:right;"/>
					 <display:column title="PNR" property="tslpnr" style="text-align:left;"/>
					 <display:column title="工作号" property="tslworkno" style="text-align:left;"/>
					 <display:column title="OFFICE号" property="xtoffice" style="text-align:left;" />
					<display:column title="打印机号" property="xtpringtno" style="text-align:right;"/>
					<display:column title="出票日期" property="cpDate"  maxLength="15" style="text-align:left;"/>
	</display:table>
		</c:if>
		<c:if test="${empty list}">
			没有查询到相关数据!
		</c:if>
     </div>
   </div>
</div>
</body>

