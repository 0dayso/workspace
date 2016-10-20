<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>退款待回款</title>
<script type="text/javascript">
	function selSfhk(obj){
		var $obj = $(obj);
		if($obj.val() == '1'){
			$("#sjhkdatetimes").show();
			$("#sjhkdatetime").show();
			$("#cgwguserids").show();
			$("#cgwguserid").show();
		}else{
			$("#sjhkdatetimes").hide();
			$("#sjhkdatetime").hide();
			$("#cgwguserids").hide();
			$("#cgwguserid").hide();
			$("#sjhkdatetime").val("");
			$("#cgwguserid").val("");
		}
	}
	function searchFrom(){
		if($("#sfhk").val() == '2'|| $("#sfhk").val()==''){
			$("#sjhkdatetime").val("");
			$("#cgwguserid").val("");
		}
		layer.load("正在执行你的操作,请稍后!");
		$("#searchForm").submit();
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cgdzbb/cgdzlable.jsp"%>
	<div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/cgdzbb/jpcgdztpdhk/getCgdzTpdhkList" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="DEF153FE4578E5324CF1681EB65C1D4A" id="view"/>
	            <input type="hidden"  name="pageNum" value="${empty param.pageNum ? 1 : param.pageNum}" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="${empty param.pageSize ? 10 : param.pageSize}" id="pageSize"/>
				<!--<input type="hidden"  name="orderBy" value="cj_Datetime desc" id="orderBy"/>  -->
				<input type="hidden"  name="lx" value="2"/>
				<table class="table01" border="0" cellpadding="0" cellspacing="0">
				   <tr>
					    <td class="xsys" style="text-align: right;">采购提交时间始</td> 
					    <td>
					      <input type="text" style="width:92px" name="cgtjsjbegin" value="${empty param.cgtjsjbegin ? vfn:dateShort():param.cgtjsjbegin }" onblur="value = replaceAll(value,'%','')" onClick="WdatePicker()">
					    </td>
					    <td class="xsys" style="text-align: right;">采购提交时间止</td> 
					    <td>
					      <input type="text" style="width:92px" name="cgtjsjend" value="${empty param.cgtjsjend ? vfn:dateShort():param.cgtjsjend }" onblur="value = replaceAll(value,'%','')" onClick="WdatePicker()">
					    </td>
					    <td class="xsys" style="text-align: right;">是否自愿退票</td>
					    <td>
				          <select name="sfzytp" id="sfzytp" style="width:92px" >
				                <option value="" ${empty param.sfzytp ?"selected" : ""}>==请选择==</option>
						        <option value="1" ${param.sfzytp eq "1"?"selected" : ""}>自愿</option>
						        <option value="2" ${param.sfzytp eq "2"?"selected" : ""} >非自愿</option>
					      </select>
						</td>
						 <td class="xsys" style="text-align: right;">票号</td>
					    <td>
					        <input id="tkno" name="tkno" class="input1" style="width:92px" value="${param.tkno}"/>
					    </td>
					     <td class="xsys" style="text-align: right;">票证</td>
					    <td>
					          <select name="cgly" id="cgly" style="width:92px" onchange="selCplx(this);" >
					                <option value="" ${param.cgly eq ""?"selected" : ""}>==请选择==</option>
							        <option value="OP" ${param.cgly eq "OP"?"selected" : ""}>OP票</option>
							        <option value="BPET" ${param.cgly eq "BPET"?"selected" : ""} >BPET票</option>
						      </select>
						</td>
					</tr>     
				    <tr>
						<td class="xsys" style="text-align: right;">PNR</td>
						<td>
					        <input id="pnrno" name="pnrno"  onblur="value=$.trim(this.value).toUpperCase();" class="input1" style="width:92px" value="${param.pnrno}"/>
						</td>
						<td class="xsys" style="text-align: right;">是否已回款</td>
						<td>     
					          <select name="sfhk" id="sfhk" style="width:92px" onchange="selSfhk(this);">
					                <option value="" ${param.sfhk eq "" ? "selected" : ""}>==请选择==</option>
							        <option value="1" ${param.sfhk eq "1" ? "selected" : ""}>已回款</option>
							        <option value="2" ${param.sfhk eq "2" ? "selected" : ""} >未回款</option>
						      </select>
						</td>    
						<td class="xsys" style="text-align: right;">采购单位</td>
					    <td>
					        <input id="cgdw" name="cgdw" class="input1" style="width:92px" value="${param.cgdw}"/>
					    </td>
					    <td class="xsys" style="text-align: right;display: none" id="sjhkdatetimes">实际回款时间</td>
					    <td>
					        <input type="text" style="width:92px;display: none" id="sjhkdatetime" name="sjhkdatetime" class="inputDate" value="${empty param.sjhkdatetime ? vfn:dateShort() : param.sjhkdatetime}" onblur="value = replaceAll(value,'%','')">
					    </td>
					     <td class="xsys" style="text-align: right;display: none" id="cgwguserids">完成人</td>
						<td>
					        <input id="cgwguserid" name="cgwguserid" style="width:92px;display: none" value="${param.cgwguserid}"/>	          
						</td>
				  </tr>
				  <tr>
					  	<td colspan="5">
						    <input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="searchFrom();"> 
						     <input type="button" class="ext_btn ext_btn_success" value="导出">
					    </td>
				  </tr>
				</table>
				</form>
            </div>
          </div>
        </div>
      <div  class="mt10">
      	<c:if test="${not empty page.list}">
      		<multipage:pone page="${ctx}/vedsb/cgdzbb/jpcgdztpdhk/getCgdzTpdhkList" actionFormName="page" var="surl"></multipage:pone>
      		${surl}
      		<display:table id="vc" name="page.list" class="list_table" style="width:99%" requestURI="" decorator="org.displaytag.decorator.TotalTableDecorator" >
			  <display:column title="航司/外出票单位" property="CGDW" style="text-align:left;" group="1" />
			  <display:column title="票号" property="TKNO"  style="text-align:left;"  />
			  <display:column title="PNR" property="CG_PNR_NO"  style="text-align:left;"  />
			  <display:column title="是否自愿退票" style="text-align:center;">${vc.CG_SFZYTP eq "1"?'自愿':'非自愿'}</display:column>
			  <display:column title="采购提交时间" style="text-align:center;">${fn:substring(vc.CG_TJSJ,0,10)}</display:column>  
			  <display:column title="预计回款时长" property="CG_YJHKSC" format="{0,number,##0.00}" style="text-align:right;"   />
			  <display:column title="预计回款时间" style="text-align:center;">${fn:substring(vc.CG_YJHKSJ,0,10)}</display:column>
			  <display:column title="应退金额" property="CG_TKJE" format="{0,number,##0.00}" style="text-align:right;" total="true"   /> 
  			  <display:column title="是否已回款" style="text-align:center;">${vc.CG_TPZT eq "3"?"已退款":"未退款"}</display:column> 
  			  <display:column title="实际回款金额" property="CG_STJE" format="{0,number,##0.00}" style="text-align:right;" total="true" />
  			  <display:column title="实际回款时间"  style="text-align:center;">${fn:substring(vc.CG_BLSJ,0,10)}</display:column> 
  			  <display:column title="完成人" property="CG_BLR" style="text-align:center;" /> 
			</display:table>
			${surl}
		</c:if>
		<c:if test="${empty page.list}">
			没有查询到相关数据!
		</c:if>
     </div>
   </div>
</div>
</body>

