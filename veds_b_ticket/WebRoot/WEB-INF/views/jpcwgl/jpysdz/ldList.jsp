<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/views/jpzwgl/gzlable.jsp"%>
<!doctype html>
<html>
  <head>
   <!-- 日期选择控件  -->
   <script type="text/javascript" src="/static/My97DatePicker/WdatePicker.js"></script>
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
	//导出
	function toExport(){
       var url="${ctx}/vedsb/jpcwgl/jpysdz/ldList";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }	
   </script>
  </head>
  
  <body>
  <%@include file="/WEB-INF/views/jpcwgl/wdzlable.jsp"%>
  	<div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">搜索</b></div>
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpcwgl/jpysdz/ldList" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="6268220CA0C0EF77F5307BD9F5359442" />
	           	<input type="hidden"  name="lx" value="2" />
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden" name="jglx" value="6"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                   <td class="xsys" style="text-align: right;">网店平台</td>
	                  	 <td>
	                  	 	<select name="wdpt" class="select">
	                  	 		<option value="">==全部==</option> 
                  	 			<option value="10100010" ${param.wdpt eq '10100010' ? 'selected' : '' }>去哪儿</option> 
                  	 			<option value="10100011" ${param.wdpt eq '10100011' ? 'selected' : '' }>淘宝</option> 
                  	 			<option value="10100050" ${param.wdpt eq '10100050' ? 'selected' : '' }>携程</option> 
                  	 			<option value="10100024" ${param.wdpt eq '10100024' ? 'selected' : '' }>同程</option>
	                  	 	</select>
	                   </td>
	                   <td class="xsys" style="text-align: right;">网店</td>
	                   <td>
	                  	 	<input type="text" value="${param.wdid}" name="wdid" class="input-text lh30" size="10"/>
	                   </td>
	                   <td class="xsys" style="text-align: right;">账期日始</td>
	                   <td>
		                	<input type="text" name="syrqs" value="${param.syrqs }" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	                   </td>
	                   <td class="xsys" style="text-align: right;">账期日止</td>
	                   <td>
	                  		<input type="text" name="syrqz" value="${param.syrqz }" size="10" class="input-text Wdate" id="maxdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	                   </td>
	                   <td class="xsys" style="text-align: right;">订单类型</td>   
	                  	 <td>
	                  	 	<select name="ddlx" class="select">
	                  	 		<option value="">==全部==</option> 
                  	 			<option value="1" ${param.ddlx eq '1' ? 'selected' : '' }>正常单</option> 
                  	 			<option value="2" ${param.ddlx eq '2' ? 'selected' : '' }>退票单</option> 
                  	 			<option value="3" ${param.ddlx eq '3' ? 'selected' : '' }>改签单</option> 
                  	 			<option value="4" ${param.ddlx eq '4' ? 'selected' : '' }>补差单</option> 
	                  	 	</select>
	                   </td>
	                   <td class="xsys" style="text-align: right;">外部单号</td>
	                   <td>
            	           <input type="text" value="${param.gyddh}" name="gyddh" class="input-text lh30" size="10"/>
	                   </td>
	                </td>
	                   <td class="xsys" style="text-align: right;">补单状态</td>   
	                   <td>
	                  	 	<select name="bd_sfybd" class="select">
	                  	 		<option value="">==全部==</option> 
                  	 			<option value="0" ${param.bd_sfybd eq '0' ? 'selected' : '' }>未补单</option> 
                  	 			<option value="1" ${param.bd_sfybd eq '1' ? 'selected' : '' }>已补单</option>
	                  	 	</select>
	                   </td>
	                	 <td align="right">
	                      	<input type="submit" id="searchFormButton" class="ext_btn ext_btn_submit" value="查询"/> 
	                      	<input type="button"  class="ext_btn ext_btn_submit" value="导出" onclick="toExport()"/> 
	                  	 </td>
	                </tr>
	              </table>
	              </form>
            </div>
          </div>
        </div>
      <!-- 列表显示 -->
    <div class="mt10" id="list_table">
	<c:if test="${not empty page.list}">
		<multipage:pone page="${ctx}/vedsb/jpcwgl/jpysdz/ldList" actionFormName="page" var="surl"></multipage:pone>
		${surl}
		<display:table id="vc" name="page.list" class="list_table" style="width:100%;"  requestURI="${ctx}/vedsb/jpcwgl/jpysdz/ldList?dtableid=vc" 
			decorator="org.displaytag.decorator.TotalTableDecorator" sort="external" excludedParams="dtableid"   reportid="${reportid}">
	       <display:column title="序号" style="text-align:center;width:3%;" expfield="RO">${vc_rowNum }</display:column>
		    <display:column  title="网店平台"  style="text-align:center;width:6%;" property="WDPTMC"/>
		    <display:column  title="网店"  style="text-align:center;width:10%;" expfield="WDID">
		    	${vc.EX.WDID.wdmc }
		    </display:column>
		    <display:column  title="订单类型"  style="text-align:center;width:7%;" expfield="DDLX">
		    	<c:choose> 
		            <c:when test="${vc.DDLX  eq 1}">正常单</c:when> 
		            <c:when test="${vc.DDLX  eq 2}">退票单</c:when> 
		            <c:when test="${vc.DDLX  eq 3}">改签单</c:when> 
		            <c:otherwise>补差单</c:otherwise> 
				</c:choose>
		    </display:column>
		    <display:column  title="补单状态"  style="text-align:center;width:6%;" expfield="BD_SFYBD">
		    	${vc.BD_SFYBD eq "0" ? '未补单' : '已补单'}
		    </display:column>
		    <display:column  title="发生金额"  style="text-align:center;width:7%;" property="WD_FSJE"/>
		    <display:column  title="外部单号"  style="text-align:center;width:10%;" property="GYDDH"/>
		    <display:column  title="账务时间"  style="text-align:center;width:8%;" property="WD_ZWSJ"/>
		    <display:column  title="账务流水号"  style="text-align:center;width:10%;" property="WD_ZWLSH"/>
		    <display:column  title="到账金额"  style="text-align:center;width:7%;" property="BY6"/>
		    <display:column  title="商品名称"  style="text-align:center;width:8%;" property="WD_SPMC"/>
		    <display:column  title="备注"  style="text-align:center;width:20%;" property="WD_BZ"/>
	    </display:table>
	${surl}
	<input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
	</c:if>
    </div>
   </div>
  </div>
  </body>
</html>
