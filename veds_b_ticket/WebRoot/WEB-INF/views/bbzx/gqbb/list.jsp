<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title>改签报表</title>
<link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
<style type="text/css">
	.short1{
			width:100%;
		 	white-space:nowrap;
			overflow:hidden;
		 	text-overflow:ellipsis;
		}
	a {
		text-decoration:none;
	}
	.gqd_right {
		text-align: right;
	}
	.gqd_center {
		text-align: center;
	}
	.gqd_left {
		text-align: left;
	}
</style>
<script type="text/javascript">
	
	$(function(){
		changeDateType();
	});
	
	function changeDateType() {
        var rqtj = $("#rqtj").val();
		if (rqtj == "1") {
			$("#rs").html("申请日始");
			$("#rz").html("申请日止");
		} else if (rqtj == "2") {
			$("#rs").html("办理日始");
			$("#rz").html("办理日止");
		}
	}
	
	function showGqdMx(wdpt,wdid,hc,hkgs,cgly) {
		var tjfs = "${param.tjfs}";
		if (tjfs == "2") {
			window.open("${ctx}/vedsb/bbzx/gqbb/viewmx_list?wdpt=" + wdpt + "&" + $("#searchForm").serialize());
		} else if (tjfs == "3") {
			window.open("${ctx}/vedsb/bbzx/gqbb/viewmx_list?wdpt=" + wdpt + "&wdid=" + wdid + "&" + $("#searchForm").serialize());
		} else if (tjfs == "4") {
			window.open("${ctx}/vedsb/bbzx/gqbb/viewmx_list?hc=" + hc + "&" + $("#searchForm").serialize());
		} else if (tjfs == "5") {
			window.open("${ctx}/vedsb/bbzx/gqbb/viewmx_list?hkgs=" + hkgs + "&" + $("#searchForm").serialize());
		} else {
			window.open("${ctx}/vedsb/bbzx/gqbb/viewmx_list?gqCgly=" + cgly + "&" + $("#searchForm").serialize());
		}
		
	}
	
	function check() {
		return true;
	}
	
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
       var url="${ctx}/vedsb/bbzx/gqbb/query";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }
    
    //退票单详
	function detailGqd(gqdh){
	//弹出取消页面 
	    var url="${ctx}/vedsb/jpgqgl/jpgqd/detail_"+gqdh;
	    window.open(url);
	}
	
	//正常订单详
	function detailKhdd(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
	function toSearch(){
		var ksrq=$("#mindate").val();
    	var jsrq=$("#maxdate").val();
    	if(!ksrq){
    		layer.msg("申请日始不能为空！",2,0);
    		return false;
    	}
    	
    	if(!jsrq){
    		layer.msg("申请日止不能为空！",2,0);
    		return false;
    	}
    	var flag = checkRqkd(ksrq,jsrq,6);
		if(!flag){
			layer.msg("日期跨度不能大于6个月。",2,0);
			return false;
		}
		var ii = layer.load('系统正在处理您的操作,请稍候!');
 		document.searchForm.action = "${ctx}/vedsb/bbzx/gqbb/query?time=1";  
	   	document.searchForm.submit();
	}
   
	   //根据cs参数检查日期跨度
	function checkRqkd(ksrq,jsrq,cs){
		var flag = true;
		var ksrqDate = strToDate(ksrq);
		var jsrqDate = strToDate(jsrq);
		var checkDate = ksrqDate.addMonth(parseInt(cs));
		if(parseInt(cs)>0){
			if(jsrqDate.getTime() > checkDate.getTime()){
				flag=false;
				return flag;
			}
		}
		return flag;
	}
	//传入日期格式字符串转化为Date型   支持yyyy-mm-dd , yyyy-mm-dd hh24:mi:ss两种格式
	function strToDate(str){
		var date;
		// yyyy-mm-dd
		var strArr = str.split("-");
		var strYear = strArr[0];//年	
		var strMonth= strArr[1];//月
		var strDay  = strArr[2];//日
	    date = new Date(strYear,strMonth-1,strDay);
		return date;
	}
	
	//获得当前日期经过num个月后的日期
	Date.prototype.addMonth = function(num) { 
		var tempDate=this.getDate(); 
		this.setMonth(this.getMonth()+num); 
		if(tempDate!=this.getDate()){
			this.setDate(0);
		}
		return this; 
	}
	
	JPGQZT = '${vfn:toJSON(vfn:dictList('JPGQZT'))}';
</script>
</head>
<body>
<div class="container">
  	  	<div id="search_bar" class="mt10">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/bbzx/gqbb/query" id="searchForm" name="searchForm" method="post" onsubmit="return check();">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden" name="pageNum" value="1" >
							<input type="hidden" name="pageSize" value="10" >
            				<input type="hidden"  name="gngj" value="${not empty param.gngj ? param.gngj : '1' }" />
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td class="gqd_right" id="rs">
              							
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" style="width:85px"  class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"/>
              						</td>
              						<td class="gqd_right" id="rz">
              							
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" style="width:85px"   class="input-text Wdate" size="10" id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"/>
              						</td>
              						<td class="gqd_right">
              							乘机人
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="cjr" value="${param.cjr }" style="width:85px"  class="input-text" />
              						</td>		
              						<td class="gqd_right">
              							PNR
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="xspnrno" value="${param.xspnrno }"  onblur="value=$.trim(this.value).toUpperCase();" style="width:85px"  class="input-text" size="6" />
              						</td>
              						<td class="gqd_right">
              							航程
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="hc" value="${param.hc }" style="width:85px"  class="input-text" size="6" />
              						</td>
              					</tr>
              					<tr>
              						<td class="gqd_right">
              							日期条件
              						</td>
              						<td class="gqd_left">
              							<select name="rqtj" class="select" id="rqtj" style="width:87px" onChange="changeDateType();">
              								<option value="1" ${param.rqtj eq '1' ? 'selected' : ''}>申请日期</option>
              								<option value="2" ${param.rqtj eq '2' ? 'selected' : ''}>办理日期</option>
              							</select>
              						</td>
              						<td class="gqd_right">
              							改签状态
              						</td>
              						<td class="gqd_left">
              							<select name="gqzt" class="select" style="width:87px"> 
              							 	<option value="" selected>==全部==</option> 
              							  	<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
              							  		<c:if test="${oneZt.value ne '7' and oneZt.value ne '8'}">
              							  			<option value="${oneZt.value}" ${param.gqzt eq oneZt.value ? 'selected' : ''}>${oneZt.mc}</option>
              							  		</c:if>
						                   	</c:forEach>
					                     </select>
              						</td>
              						<td class="gqd_right">
              							改签类型
              						</td>
              						<td class="gqd_left">
              							<select name="gqlx" class="select" style="width:87px">
              								<option value="">==全部==</option>
              								<option value="1" ${param.gqlx eq '1' ? 'selected' : ''}>改期</option>
              								<option value="2" ${param.gqlx eq '2' ? 'selected' : ''}>升舱</option>
              							</select>
              						</td>
              						<td class="gqd_right">
              							航班号
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="xshbh" value="${param.xshbh}" style="width:85px"   class="input-text" size="10" />
              						</td>
              						<td class="gqd_right">
              							统计方式
              						</td>
              						<td class="gqd_left">
              							<select id="tjfs" name="tjfs" class="select" style="width: 87px" >
							     	         <option value="1" ${param.tjfs eq '1' ? 'selected' : ''}>按明细</option>
							     	         <option value="2" ${param.tjfs eq '2' ? 'selected' : ''}>按网店平台</option>
							     	         <option value="3" ${param.tjfs eq '3' ? 'selected' : ''}>按网店</option>
							     	         <option value="4" ${param.tjfs eq '4' ? 'selected' : ''}>按航程</option>
							     	         <option value="5" ${param.tjfs eq '5' ? 'selected' : ''}>按航空公司</option>
							     	         <option value="6" ${param.tjfs eq '6' ? 'selected' : ''}>按采购来源</option>
						     	         </select>
              						</td>
								</tr>
								<tr>
									<td class="gqd_right">
              							网店
              						</td>
              						<td class="text_left">
              							<select name="wdid" class="select" style="width:87px">
              								<option value="">==全部==</option>
              								<c:forEach items="${wdzlszList}" var="wdid">	
					                  	 		<option value="${wdid.id }" ${param.wdid eq wdid.id ? 'selected' : '' }>${wdid.wdmc }</option> 
					                  		</c:forEach>
              							</select>
              						</td>
              						<td class="gqd_right">
              							政策代码
              						</td>
              						<td class="gqd_left">
              							<input type="text" name="wdzcdm" value="${param.wdzcdm }" style="width:85px"   class="input-text"  />
              						</td>
              						<td class="text_right">外部改签单号</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 90px" value="${param.wbdh}" name="wbdh" />
									</td>
									<td class="text_right">外部订单号</td>
									<td class="text_left">
										<input type="text" class="input-text lh25" style="width: 90px" value="${param.wbddbh}" name="wbddbh" />
									</td>
									<td class="text_right">采购来源</td>
								    <td class="text_left">
						                  <select name="cgly" id="cgly" class="select" style="width:87px;">
						                  	 	<option value="">全部</option>
						                  	 	<c:forEach items="${vfc:getVeclassLb('10014')}" var="onewdpt" varStatus="wdptStatus">
						                  	 		 <c:if test = "${onewdpt.id ne '10014'}">
						                  	 			<option value="${onewdpt.ywmc }" ${param.cgly eq onewdpt.ywmc ? 'selected' : '' }>${onewdpt.ywmc }</option> 
						                  	 		 </c:if>
						                  	 	</c:forEach>
									      </select>
							        </td>
									<td>
										<input type="button" name="button" value="查询" class="ext_btn ext_btn_submit" onclick="toSearch()"/>
										&nbsp;&nbsp;
										<input type="button" name="export" value="导出" class="ext_btn ext_btn_submit" onclick="toExport()"/>
									</td>
								</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
      	<div  class="mt10">
        	<c:if test="${not empty page && fn:length(page.list) != 0}">
        		<c:if test="${page.pageAllCount eq -1}">
        			<multipage:pone page="${ctx}/vedsb/bbzx/gqbb/query" actionFormName="page" var="surl"></multipage:pone>
        			<display:table id="vc" name="page.list" class="list_table" requestURI="${ctx}/vedsb/bbzx/gqbb/query?dtableid=vc" sort="external"
					excludedParams="dtableid"  style="width:100%;" decorator="org.displaytag.decorator.TotalTableDecorator" reportid="${reportid}">
					  <c:choose>
					  	<c:when test="${param.tjfs eq '2'}">
					  		<display:column title="网店平台"  style="text-align:center;" expfield="WDPT">
					  			<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
						            <c:if test = "${onewdpt.id eq vc.WDPT}">
						                ${onewdpt.mc }
						                <input type="hidden" id="wdpt" value="${onewdpt.id}"/>
						            </c:if>
						        </c:forEach>
					  		</display:column>
					  	</c:when>
					  	<c:when test="${param.tjfs eq '3'}">
					  		<display:column title="网店平台" style="text-align:center;"  expfield="WDPT">
					  			${vc.EX.WDID.wdptmc }
					  		</display:column>
					  		<display:column title="网店" expfield="WDID" style="text-align:center;" group="1">
					  			${vc.EX.WDID.wdmc }
					  		</display:column>
					  	</c:when>
					  	<c:when test="${param.tjfs eq '4'}">
					  		<display:column title="航程" property="HC"  style="text-align:center;"  />
					  	</c:when>
					  	<c:when test="${param.tjfs eq '5'}">
					  		<display:column title="航空<br/>公司" property="HKGS" style="text-align:center;"/>
					  	</c:when>
						<c:otherwise>
							<display:column title="采购来源" style="text-align:center;" property="GQ_CGLY"/>
						</c:otherwise>
					  </c:choose>
					  <display:column title="单数" property="DS" href="javascript:showGqdMx()" paramId="vefun,WDPT,EX.WDID.id,HC,HKGS,GQ_CGLY" style="text-align:center;" total="true"/>
					  <display:column title="票数" property="PS" href="javascript:showGqdMx()" paramId="vefun,WDPT,EX.WDID.id,HC,HKGS,GQ_CGLY" style="text-align:center;" total="true"/>
		  			  <display:merge title="改签费用"> 
		  			  	  <display:column title="客户方收" property="GQ_XSFY" style="text-align:right;" format="{0,number,#0.00}" total="true"/> 
			  			  <display:column title="采购方收" property="GQ_CGFY" style="text-align:right;" format="{0,number,#0.00}" total="true"/>
		  			  </display:merge>
		  			  <display:column title="利润" property="LR" style="text-align:right;" format="{0,number,#0.00}" total="true"/> 
					</display:table>
        		</c:if>
        		<c:if test="${page.pageAllCount ne -1}">
		      		<multipage:pone page="${ctx}/vedsb/bbzx/gqbb/query" actionFormName="page" var="surl"></multipage:pone>
		      		${surl}
		      		<display:table id="vc" name="page.list" class="list_table" style="width:2500px" requestURI="${ctx}/vedsb/bbzx/gqbb/query?dtableid=vc" decorator="org.displaytag.decorator.TotalTableDecorator" >
					  <display:column title="序号" style="text-align:center;width:40px">${vc_rowNum}</display:column>
					  <display:column title="网店" expfield="WDID" style="text-align:center;width:100px" >
					  	${vc.EX.WDID.wdmc }
					  </display:column>
					  <display:column title="方案" property="FAID"  style="text-align:center;width:100px"  />
					  <display:column title="政策代码" property="WD_ZCDM" maxLength="8"  style="text-align:center;width:60px"  />
					  <display:column title="外部改签单号" style="text-align:center;width:100px" expfield="WBDH">
					  	<a href="###" onclick="detailGqd('${vc.GQDH}');">
	 		         		${vc.WBDH}
	 	         		</a>
					  </display:column>
	   				  <display:column title="外部订单号" style="text-align:center;width:100px" expfield="WBDDBH">
	   				  	<a href="###" onclick="detailKhdd('${vc.DDBH}');">
	 		         		${vc.WBDDBH}
	 	         		</a>
	   				  </display:column>
					  <display:column title="改签类型" style="text-align:center;width:50px" expfield="GQLX">
					  	${vc.GQLX eq '1' ? '改期' : '升舱'}
					  </display:column>  
					  <display:column title="改签状态" style="text-align:center;width:50px" expfield="GQZT">
					  	<c:forEach items="${vfn:dictList('JPGQZT')}" var="oneZt">
				 			<c:if test="${oneZt.value eq vc.GQZT}">
				 				${oneZt.mc}
				 			</c:if>
		          		</c:forEach>
					  </display:column>
					  <display:column title="乘机人" property="CJR" maxLength="4" style="text-align:left;width:40px"/>
					  <display:column title="票号" property="TKNO" style="text-align:left;width:200px"  /> 
		  			  <display:column title="采购来源" style="text-align:center;width:80px" property="GQ_CGLY"/> 
		  			  <display:column title="采购科目" expfield="GQ_CGKM"  style="text-align:center;width:80px" >
		  			  	${vc.EX.GQ_CGKM.kmmc }
		  			  </display:column>
		  			  <display:column title="出票部门" property="GQ_CPBM" style="text-align:left;width:80px"/> 
		  			  <display:column title="航程" property="HC" maxLength="6" style="text-align:center;width:60px" > </display:column>
		  			  <display:merge title="改签前航段信息"> 
		  			  	  <display:column title="PNR" property="XS_PNR_NO" style="text-align:center;width:40px"/> 
			  			  <display:column title="航班号" property="XS_HBH" style="text-align:center;width:40px" />
			  			  <display:column title="舱位" property="XS_CW"  style="text-align:center;width:20px"/> 
			  			  <display:column title="起飞时间" style="text-align:center;width:60px" expfield="CFRQ">
			  			  	${vfn:format(vc.CFRQ,'MM-dd')}</br>
			  			  	${vc.CFSJ}
			  			  </display:column>
		  			  </display:merge>
		  			  <display:merge title="改签后航段信息"> 
		  			  	  <display:column title="PNR" property="GQ_XS_PNR_NO" style="text-align:center;width:40px"/>
			  			  <display:column title="航班号" property="GQ_XS_HBH" style="text-align:center;width:40px" />
			  			  <display:column title="舱位" property="GQ_XS_CW"  style="text-align:center;width:20px"/> 
			  			  <display:column title="起飞时间" style="text-align:center;width:60px" expfield="GQ_CFRQ">
			  			  	${vfn:format(vc.GQ_CFRQ,'MM-dd')}</br>
			  			  	${vc.GQ_CFSJ}
			  			  </display:column>
		  			  </display:merge>
		  			  <display:column title="销售改签<br/>费用" property="GQ_XSFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="销售升舱<br/>费用" property="GQ_XSSCFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="收款状态" style="text-align:center;width:60px" expfield="SKZT">
		  			  	${vc.SKZT eq '0' ? '未收款' : '已收款'}
		  			  </display:column> 
		  			  <display:column title="收款科目" style="text-align:center;width:60px" expfield="SKKM">
		  			  	${vc.EX.SKKM.kmmc }
		  			  </display:column> 
		  			  <display:column title="采购改签<br/>费用" property="GQ_CGFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="采购升舱<br/>费用" property="GQ_CGSCFY" style="text-align:right;width:60px" format="{0,number,#0.00}" total="true"/> 
		  			  <display:column title="联系人" property="NXR"  style="text-align:left;width:80px"/> 
		  			  <display:column title="联系电话" style="text-align:center;width:60px" expfield="NXSJ">
		  			  	${empty vc.NXDH ? vc.NXSJ : vc.NXDH}
		  			  </display:column>
		  			  <display:column title="申请人" style="text-align:center;width:100px" expfield="DDYH">
		  			  	${vc.DDYH}
		  			  </display:column>
		  			  <display:column title="申请时间" style="text-align:center;width:100px" expfield="DDSJ">
		  			  	${vfn:format(vc.DDSJ,'MM-dd HH:mm')}
		  			  </display:column>  
		  			  <display:column title="办理人" style="text-align:center;width:100px" expfield="GQ_BLR">
		  			  	${vc.GQ_BLR}
		  			  </display:column> 
		  			  <display:column title="办理时间" style="text-align:center;width:100px" expfield="GQ_BLSJ">
		  			  	${vfn:format(vc.GQ_BLSJ,'MM-dd HH:mm')}
		  			  </display:column> 
					</display:table>
					${surl}
				</c:if>
			</c:if>
			<c:if test="${empty page || fn:length(page.list) == 0}">
				没有找到相关数据！
			</c:if>
	   </div>
	   <input type="hidden" id="vcexpfield" value="${vfn:urid(vcexpfield)}">
	</body>
</html>