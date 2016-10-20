<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>采购对账明细报表</title>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	jQuery.download = function(url, data, method){    // 获得url和data
		    if( url && data ){ 
		        // data 是 string 或者 array/object
		        data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
		        var inputs = '';
		        jQuery.each(data.split('&'), function(){ 
		            var pair = this.split('=');
		            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
		        });        // request发送请求
		        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
		    };
	};
	$(function(){
		 $("#hkgsmc").autocompleteGnHkgs("hkgs");
	})
	function clearValue(obj,hkgs_id){
		   if(obj.value==""){
			   $("#"+hkgs_id).val("");
		   }
	   }
   function reportmxsearch(){
   	layer.load("正在执行你的请求,请稍后!");
   	$("#cgdzmxform").submit();
   }
   
   function toExcelmx(){
   	 var url="${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzMxReport";
     $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#cgdzmxform").serialize(),"post");
   }
   
   function checkTkno(tkno){
	  var length=tkno.length;
	  if(length<13){
	     layer.alert("请输入正确的票号");
	     return false;
	  }
	  tkno=tkno.replace("-","");
	  tkno=tkno.substring(0,3)+"-"+tkno.substring(3,13);
	  $("#tkno").val(tkno);
 	}
</script>
</head>
<body>
	<div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzMxReport" id="cgdzmxform" name="searchForm" method="post">
               <input type="hidden" name="type" value="${param.type }"/><!--  1为正常票 3为废票 2为退票 4改签 -->
			   <input type="hidden" name="tjfs" value="${param.tjfs}"/>
			   <input type="hidden" name="zkfx" value="${param.zkfx}"/>
				<c:choose>
					<c:when test="${param.cplx ne 'BSPET' and param.cplx ne 'BSP' and param.cplx ne 'BPET' and param.cplx ne 'OP' and param.cplx ne 'ODT' and param.cplx ne 'BP'}">
						<%@ include file="/WEB-INF/views/cgdzbb/jpcglyreport/cgdzbbHeadAll.jsp"%>
					</c:when>
					<c:otherwise>
						<%@ include file="/WEB-INF/views/cgdzbb/jpcglyreport/cgdzbbHead.jsp"%>
					</c:otherwise>
				</c:choose>
			   </form>
	        </div>
          </div>
        </div>
      <div class="mt10">
      		<div id="tbl-container" style="height: 360px;">
      		<c:if test="${not empty page.list}">
      		<multipage:pone page="${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzMxReport" actionFormName="page" var="surl"></multipage:pone>
      		${surl}
	    	<display:table id="ticketInfo" name="page.list" class="list_table" style="width:1700px;" decorator="org.displaytag.decorator.TotalTableDecorator">
	       <display:column title="序号">${ticketInfo_rowNum }</display:column>
	       <display:merge title="票面">
		        <display:column title=" 国内/国际" expfield="GNGJ" style="text-align:left;">
		        	${ticketInfo.GNGJ eq '1'?'国内':'' }
			        ${ticketInfo.GNGJ eq '0'?'国际':'' }
		        </display:column>
		        <display:column title=" 采购<br>来源" property="CGLY" style="text-align:left;"/>
		        <display:column title=" 大编码" property="CG_HKGS_PNR" style="text-align:left;"/>
		        <display:column property="CG_PNR_NO" title=" PNR" style="text-align:left;"/>
		        <display:column title=" OFFICE号<br>支付科目" property="FZ1" style="text-align:left;"/>
		        <display:column title=" 工作号<br>航空公司<br>合作单位" property="FZ2" style="text-align:left;"/>
		        <display:column title="打票机号" property="PRINTNO"/><!-- bspet票 -->
		        <display:column  title=" 票号" sortable="true" style="text-align:left;" property="TKNO" expfield="TKNO"></display:column>
				<display:column property="CJR" title=" 乘机人" maxLength="15" style="text-align:left;"/>
				<display:column property="HC" title=" 航程" style="text-align:left;"/>
				<display:column title=" 航程名称" style="text-align:left;">
					${(vfc:getBcity(fn:substring(ticketInfo.HC, 0, 3))).mc}-${(vfc:getBcity(fn:substring(ticketInfo.HC, 3, 6))).mc}
				</display:column>
				<display:column property="CG_HBH" title=" 航班号" style="text-align:left;"/>
				<display:column property="CG_CW" title=" 舱位" style="text-align:left;"/>
			    <display:column  title=" 出发日期" expfield="CFRQ" style="text-align:left;">
					${fn:substring(ticketInfo.CFRQ,5,16)}
				</display:column>
	         </display:merge>
	         
	        <display:merge title="出票">
	            <display:column title=" 出票部门" property="CP_BMBH" style="text-align:left;" />
			    <display:column title=" 出票员"  property="CP_YHBH" style="text-align:left;" /> 
	            <display:column  title=" 出票日期" expfield="CP_DATETIME" style="text-align:left;">
					${fn:substring(ticketInfo.CP_DATETIME,5,16)}
			   </display:column>
			</display:merge>
			
			 <display:merge title="采购">		
				<display:column property="CG_DLFL" format="{0,number,#.#%}" style="text-align:right;" title=" 代理费率" expfield="CG_DLFL"/>
				<display:column property="CG_PJ" format="{0,number,#0.00}" style="text-align:right;" title=" 采购价" total="true"/>
				<display:column property="CG_XJJE" format="{0,number,#0.00}" style="text-align:right;" title=" 采购金额" total="true"/>
		    </display:merge>
		    
		  <display:merge title="销售">
			  <display:column property="XS_ZDJ" format="{0,number,#0.00}" style="text-align:right;" title=" 账单价" total="true"/>
			  <display:column property="XS_JSF" format="{0,number,#0.00}" style="text-align:right;" title=" 机建" total="true"/>
			  <display:column property="XS_TAX" format="{0,number,#0.00}" style="text-align:right;" title=" 税费" total="true"/>
			  <display:column property="XS_PJ" format="{0,number,#0.00}" style="text-align:right;" title=" 销售价" total="true"/>
			  <display:column property="XS_XJJE" format="{0,number,#0.00}" style="text-align:right;" title=" <span title='销售价+机建+税费'>小计</span>" total="true"/>
		  </display:merge> 
		  
		<c:if test="${param.type ne '1' }"> 
		  <display:merge title="退废申请">
			  <display:column title=" 申请员"  property="CG_TJR" style="text-align:left;" /> 
	          <display:column  title=" 申请日期" expfield="" style="text-align:left;">
				${fn:substring(ticketInfo.TJSJ,5,16)} 
			  </display:column>
		  </display:merge>
		  
		  <display:merge title="退废信息">	
				<display:column property="CG_TPF" format="{0,number,#0.00}" style="text-align:right;" title=" 与采购<br>手续费/工本费" total="true"/>
				<display:column property="XS_TPSXF" format="{0,number,#0.00}" style="text-align:right;" title=" 与客户<br>手续费/工本费" total="true"/>
				<display:column property="CG_STJE" format="{0,number,#0.00}" style="text-align:right;" title=" 实退金额" total="true"/>
		  </display:merge>	
		</c:if>
		  <display:footer>
		  		<tr>
		  			<c:if test="${param.cplx eq 'BSPET'}">
		  				<td colspan="17"></td>
		  			</c:if>
		  			<c:if test="${param.cplx ne 'BSPET'}">
		  				<td colspan="16"></td>
		  			</c:if>
		  			<td>合 计：</td>
		  			<td></td>
		  			<td></td>
		  			<td>${sumMap.CG_PJ}</td><!-- 采购价合计 -->
		  			<td>${sumMap.CG_XJJE}</td><!-- 采购金额合计 -->
		  			<td>${sumMap.XS_ZDJ}</td><!-- 账单价合计 -->
		  			<td>${sumMap.XS_JSF}</td><!-- 机建合计 -->
		  			<td>${sumMap.XS_TAX}</td><!-- 税费合计 -->
		  			<td>${sumMap.XS_PJ }</td><!-- 销售合计 -->
		  			<td>${sumMap.XS_XJJE }</td><!-- 小计合计 -->
		  			<c:if test="${param.type ne '1' }"> 
		  				<td></td>
		  				<td></td>
		  				<td>${sumMap.CG_TPF}</td><!-- 与采购手续费合计 -->
		  				<td>${sumMap.XS_TPSXF}</td><!-- 与客户手续费合计 -->
		  				<td>${sumMap.CG_STJE}</td><!-- 实退金额合计 -->
		  			</c:if>
		  		</tr>
		  </display:footer>
	    </display:table>
	    ${surl}
	    </c:if>
	    <input type="hidden" id="vcexpfield" value="${vfn:urid(ticketInfoexpfield)}">
	    <c:if test="${empty page.list}">
	    	没有找到相关数据!
	    </c:if>
	</div>
     </div>
   </div>
</div>
</body>

