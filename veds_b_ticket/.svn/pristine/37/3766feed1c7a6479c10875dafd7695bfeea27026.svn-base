<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<style type="text/css">
	a {
		text-decoration: none;
	}
</style>
<script type="text/javascript">

	$(function(){
		if("${param.load}" == "1"){
			if('${param.wdid}' != ''){
	      		$("#mrwdid").val('${param.wdid}');
	      	}
			//$("#tosearch").click();
		}
	});

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
	function flush(){
		$("#searchForm").pageSearch();
	}//刷新表单
	
	//订单详情
	function detail(id){
		var url= "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	//点击票号查看机票信息详情
	function showTicketDetail(id,tkno){
		var url="${ctx}/vedsb/bbzx/cpbb/seeMoreJp?id="+id+"&tkno="+tkno;
  		 $.layer({
			type: 2,
			title: ['查看机票信息'],
			area: ['750px', '450px'],
			iframe: {src: url}
	   });
	}
  	//修改机票
  	function updateJp(id,ddbh){
  		//window.open("${ctx}/vedsb/bbzx/cpbb/updateJp?id="+id+"&ddbh="+ddbh);
  		var url="${ctx}/vedsb/bbzx/cpbb/updateJp?id="+id+"&ddbh="+ddbh;
  		 $.layer({
			type: 2,
			title: ['修改出票信息'],
			area: ['650px', '300px'],
			iframe: {src: url}
	   });
  	}
  	
  	//点击删除机票
  	function deleteJp(id,skzt){
  		if(skzt==1){
  			layer.msg("机票已收款不能删除！",2,0);
  			return;
  		}
   	  	var url="${ctx}/vedsb/bbzx/cpbb/deleteTicket";
	  	$.layer({
        area: ['250px', '150px'],
        dialog: {
        msg: '确定删除该机票，是否继续？',
        btns: 2,                    
        type: 4,
        btn: ['确定','取消'],
        yes: function(){
        	var ii = layer.load('系统正在处理您的操作,请稍候!');
  			 $.ajax({
        	 		type:"post",
  					url:url,
  					data:{"id":id},
  					success:function(result){
  						layer.close(ii);
  						if("1" == result){
  							layer.msg("删除机票成功！",2,1);
  						}else{
  							layer.msg("删除机票失败！",2,0);
  						}
  						flush();
  					}
        	 	});
        }, no: function(){
            layer.msg('放弃删除机票！', 2, 3);
        }
     }});
  	}
 //点击查询
 function queryPage(){
 		var starttime=document.getElementById("cpdates").value;
 		var endtime=document.getElementById("cpdatez").value;
 		if(!starttime){
 			layer.msg("出票日始不能为空。",2,0);
 			return false;
 		}
 		if(!endtime){
 			layer.msg("出票日止不能为空。",2,0);
 			return false;
 		}
 		var flag = checkRqkd(starttime,endtime,6);
		if(!flag){
			layer.msg("日期跨度不能大于6个月。",2,0);
			return false;
		}
 		var ii = layer.load('系统正在处理您的操作,请稍候!');
 		document.searchForm.action = "${ctx}/vedsb/bbzx/cpbb/viewlist?time=1";  
	   	document.searchForm.submit();
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

//查看异动日志
	function enterLogPage(ddbh){
		var url="${ctx}/vedsb/jpddgl/jpkhddczrz/enterLogPage_"+ddbh+"?ywlx=01";
		$.layer({
			type: 2,
			title: ['<b>订单异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}
	
    function toExport(){
       var url="${ctx}/vedsb/bbzx/cpbb/viewlist";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }	
    
    //根据统计方式中查看详
    function showDetailPs(wdpt,wdid,cgly,hc,hkgs,cpyhbh) {
		var tjfs = "${param.tjfs}";
		var url="${ctx}/vedsb/bbzx/cpbb/viewlist?load=1&tjfs=1";
		var csval="";
		if(tjfs == "2"){
			csval = "&wdpt=" + wdpt;
		}else if(tjfs == "3"){
			csval = "&wdid=" + wdid;
		}else if(tjfs == "4"){
			csval = "&cgly=" + cgly;
		}else if(tjfs == "5"){
			csval = "&hc=" + hc ;
		}else if(tjfs == "6"){
			csval = "&hkgs=" + hkgs;
		}else if(tjfs == "7"){
			csval = "&cpyhbh=" + cpyhbh;
		}
		url = url +csval + "&" + $("#searchForm").serialize().replace("hkgs","oldhkgs");
		window.open(url);
	}
	
</script>
</head>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/bbzx/cpbb/viewlist" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="827931810cad8945ef6eb71a850bb128" />
				<input type="hidden"  name="orderBy" value="cp_datetime desc" />
				<input type="hidden" name="type" value="${param.type}" >
				<input type="hidden" name="pageNum" value="1" >
				<input type="hidden" name="pageSize" value="10" >
				<input type="hidden" name="wdpt" id="wdpt" value="${param.wdpt }">
				<input type="hidden" name="cpyhbh" value="${param.cpyhbh }"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
                	<tr style="margin-left:-10px;">
					    <td class="td_right" id="rq1">出票日始</td>
					    <td class="tab_in_td_f" >
					    	<input type="text" name="ksrq" id="cpdates" class="input-text Wdate" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
										 style="width:100px;" onClick="WdatePicker()" >
					    </td>
					    <td class="td_right" id="rq2">出票日止</td>
					    <td class="tab_in_td_f" >
					    	<input type="text" name="jsrq" id="cpdatez" class="input-text Wdate" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}"
										 style="width:100px;" onClick="WdatePicker()" >
					    </td>
					    <td class="td_right">P&nbsp;N&nbsp;R</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="xsPnrNo" class="input-text" value="${param.xsPnrNo}" onblur="value=$.trim(this.value).toUpperCase();" style="width:100px;" size="6">      
					    </td>
					    <td class="td_right">票号</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="tkno" class="input-text" value="${param.tkno}" size="6" style="width:110px;" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
					    <td class="td_right">采购来源</td>
					    <td class="ddgl">
			                  <select name="cgly" id="cgly" class="select" style="width:100px;">
				                  	 	<option value="">全部</option>
				                  	 	<c:forEach items="${vfc:getVeclassLb('10014')}" var="onewdpt" varStatus="wdptStatus">
				                  	 		 <c:if test = "${onewdpt.id ne '10014'}">
				                  	 			<option value="${onewdpt.ywmc }" ${param.cgly eq onewdpt.ywmc ? 'selected' : '' }>${onewdpt.ywmc }</option> 
				                  	 		 </c:if>
				                  	 	</c:forEach>
						      </select>
				        </td>
					</tr>
					<tr style="margin-left:-10px;">
				        <td class="td_right">航程</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="hc" class="input-text" style="width:100px;" value="${param.hc}" id="hc" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
					    <td class="td_right">航空公司</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="hkgs" class="input-text" style="width:100px;" value="${param.hkgs}" id="hkgs" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
					   	<td class="td_right">订单编号</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="ddbh" class="input-text" style="width:100px;" value="${param.ddbh}" size="20" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
					    <td class="td_right">外部单号</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="wbdh" class="input-text" style="width:110px;" value="${param.wbdh}" size="20" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
					    <td class="td_right">网店政策代码</td>
					    <td class="tab_in_td_f">
					      <input type="text" name="wdzcdm" class="input-text" style="width:100px;" value="${param.wdzcdm}" size="20" onblur="value=$.trim(this.value).toUpperCase();">      
					    </td>
				  </tr>
				  <tr>
						<%-- <td class="td_right">网店平台</td>
					    <td class="ddgl">
			                  <select name="wdpt" id="wdpt" class="select" style="width:100px;">
				                  	 	<option value="">全部</option>
				                  	 	<c:forEach items="${vfc:getVeclassLb('10100')}" var="onewdpt" varStatus="wdptStatus">
				                  	 		 <c:if test = "${onewdpt.id ne '10100'}">
				                  	 			<option value="${onewdpt.id }" ${param.wdpt eq onewdpt.id ? 'selected' : '' }>${onewdpt.mc }</option> 
				                  	 		 </c:if>
				                  	 	</c:forEach>
						      </select>
				        </td> --%>
				        <td class="td_right">网店</td>
					    <td class="ddgl">
			                  <select name="wdid" id="wdid" class="select" style="width:100px;">
				                  	 	<option value="" id="mrwdid">全部</option>
				                  	 	<c:forEach items="${wdzlszList}" var="onewd">
						                  	<option value="${onewd.id }" ${param.wdid eq onewd.id ? 'selected' : '' }>${onewd.wdmc }</option> 
				                  	 	</c:forEach>
						      </select>
				        </td>
				        <td class="td_right">收款状态</td>
					    <td class="ddgl">
			                  <select name="skzt" id="skzt" class="select" style="width:100px;">
				                  	 	<option value="">全部</option>
				                  	 	<option value="1" ${param.skzt eq '1' ? 'selected' : '' }>已付款</option>
				                  	 	<option value="0" ${param.skzt eq '0' ? 'selected' : '' }>未付款</option>
						      </select>
				        </td>
				        <td class="td_right">票证状态</td>
					    <td class="ddgl">
			                  <select name="jpzt" id="jpzt" class="select" style="width:100px;">
				                  	 	<option value="">全部</option>
				                  	 	<option value="1" ${param.jpzt eq '1' ? 'selected' : '' }>正常票</option>
				                  	 	<option value="2" ${param.jpzt eq '2' ? 'selected' : '' }>退票</option>
				                  	 	<option value="3" ${param.jpzt eq '3' ? 'selected' : '' }>废票</option>
						      </select>
				        </td>
				        <td class="td_right">统计方式</td>
					    <td class="ddgl">
			                  <select name="tjfs" id="tjfs" class="select" style="width:110px;">
				                  	 	<option value="1" ${param.tjfs eq "1" ? "selected":""}>明细统计</option>
										<option value="2" ${param.tjfs eq "2" ? "selected":"" }>网店平台统计</option>
										<option value="3" ${param.tjfs eq "3" ? "selected":"" }>网店统计</option>
										<option value="4" ${param.tjfs eq "4" ? "selected":"" }>采购来源</option>
										<option value="5" ${param.tjfs eq "5" ? "selected":"" }>航程统计</option>
										<option value="6" ${param.tjfs eq "6" ? "selected":"" }>航空公司统计</option>
										<option value="7" ${param.tjfs eq "7" ? "selected":"" }>出票员统计</option>
						      </select>
				        </td>
				        <td colspan="2" align="center">
					  		<input type="button" id="tosearch" class="ext_btn ext_btn_submit" value="查询" onclick="queryPage()"> 
					  		<input type="button" class="ext_btn ext_btn_success" value="导出" onclick="toExport()">
					  	</td>
				  </tr>
				  
	              </table>
	              </form>
            </div>
          </div>
        </div>
       
        <div class="mt10" id="list_table">
        	<c:if test="${not empty page}">
        		<%@ include file="/WEB-INF/views/bbzx/cpbb/cpbb_mx.jsp"%>
        	</c:if>
        </div>
   </div>
  </div>
</body>
</html>