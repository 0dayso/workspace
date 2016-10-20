<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>采购对账报表</title>
<script type="text/javascript">
	function reportsearch(){
		if($("#ksrq").val() == '' || $("#ksrq").val() == null){
			layer.msg("出票日始不能为空");
		}else if($("#jsrq").val() == '' || $("#jsrq").val() == null){
			layer.msg("出票日止不能为空");
		}
		layer.load("正在执行你的操作,请稍候!");
		$("#searchForm").submit();
	}
	
	function getBSPETCheck(office,agent,printno){
		var params = "?office="+office;
		params+="&printno="+printno;
		params+="&agent="+agent;
		params+"&ksrq="+$("#ksrq").val();
		window.open("${ctx}/vedsb/cgdzbb/jpcgdz/viewcgdzBspetMain"+params);
	}
	
	function detail(tjfs,cplx,type,fz1,fz2,printno){
		var ksrq = $("#ksrq").val();
		var jsrq = $("#jsrq").val();
		var hcglgj = "${param.hcgngj}";
		var params = "?cplx="+cplx+"&tjfs="+tjfs+"&ksrq="+ksrq+"&jsrq="+jsrq+"&type="+type+"&printno="+printno+"&hcglgj="+hcglgj;
		var url = "${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzMxReport"+params;
		if("3" == tjfs){
			window.open(url+'&zkfx='+fz1,'',1000,600);
		}else{
			if("BSPET" == cplx || cplx=='BSP'){
				window.open(url+'&office='+fz1+'&cp_pid='+fz2,'',1000,600);
			}else if(cplx=='B2B'||cplx=='BP'||cplx=='TB'||cplx=='BOP'){
				window.open(url+'&cg_zfkm='+fz1+'&hkgs='+fz2,'',1000,600);
			}else if(cplx=='OP'||cplx=='ODT'){
				window.open(url+'&cg_zfkm='+fz1+'&hzdw='+fz2,'',1000,600);
			}else{
				window.open(url,'',1000,600);
			}
		}
	}
	
	function tobankdzNew(){
		window.open("${ctx}/vedsb/cgdzbb/jpcgyhdz/viewbankmain");
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
	
	 function toExport(){
       var url="${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzReport";
       $.download(url,"export="+$("#vcexpfield").val()+"&"+$("#searchForm").serialize(),"post");
    }
	function tocgdzNew(){
		window.open("${ctx}/vedsb/cgdzbb/jpcgalldz/viewlist");
	}
</script>
<link rel="stylesheet" href="${ctx}/static/css/displayTag.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cgdzbb/cgdzlable.jsp"%>
	<div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/cgdzbb/jpcglyreport/getCgdzReport" id="searchForm" name="searchForm" method="post">
				<table class="table01" border="0" cellpadding="0" cellspacing="0" >
  					<tr>
  						<td class="xsys" style="text-align: right;">出票日始</td>
						<td>
						  <input type="text" name="kssj" id="ksrq" value="${empty param.kssj?vfn:dateShort():param.kssj }" onClick="WdatePicker()" size="10">
						 </td>
					    <td class="xsys" style="text-align: right;">出票日止</td>
						<td>
						  <input type="text" name="jssj" id="jsrq" value="${empty param.jssj?vfn:dateShort():param.jssj }" onClick="WdatePicker()" size="10">
						</td>
					    <td class="xsys" style="text-align: right;">国内国际</td> 
					    <td>
					      <select name="hcgngj" style="width:95px" >
					        <option value="" ${empty param.hcgngj ?'selected':'' }>==请选择==</option>
						    <option value="1" ${param.hcgngj eq '1'?'selected':'' }>国内</option>
							<option value="0" ${param.hcgngj eq '0'?'selected':'' }>国际</option>
						  </select>
						</td>
					    <td class="xsys" style="text-align: right;">统计方式</td>
					    <td>
						   <select name="tjfs" style="width:220px">
						     <option value="0" ${param.tjfs eq '0' ? 'selected' : '' }>按类型/支付科目/航空公司/打票机</option>
						     <option value="1" ${param.tjfs eq '1' or empty param.tjfs ? 'selected' : '' }>按类型/支付科目</option>
						   </select>
					    </td>
					    <td>
					    	<input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="reportsearch()">
					    </td>
  				  </tr>
				  <tr>
					<td colspan="8">
					   &nbsp;
					   	 	<input type="button" value="导出" class="ext_btn ext_btn_success" onClick="toExport()">
					   &nbsp;
					   		<input type="button" value="银行对账 " class="ext_btn ext_btn_success" onClick="tobankdzNew()">
					   		
					   &nbsp;
					   		<input type="button" value="采购对账" class="ext_btn ext_btn_success" onClick="tocgdzNew()">
				    </td>
				  </tr>
			</table>
			</form>
            </div>
          </div>
        </div>
      <div  class="mt10">
      	<table width="130%">
      	<c:if test="${not empty leftlist}">
			<tr><td>提交时间：${startdate}  返回时间：${enddate}</td></tr>
		</c:if>
		  <tr>
		   <td valign="top">
		      <%@include file="/WEB-INF/views/cgdzbb/jpcglyreport/cglyreport.jsp" %>
		   </td>
		  </tr>
		</table>
     </div>
   </div>
</div>
</body>

