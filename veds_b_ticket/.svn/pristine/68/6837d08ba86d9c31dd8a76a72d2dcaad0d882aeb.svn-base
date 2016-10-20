<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<style>
  
	.input: {
		width: 85px;
	}
	
	.text_right {
		text-align: right;
	}
	
	.text_left {
		text-align: left;
	}
	a{ 
		text-decoration: none;
	}
</style>
<script type="text/javascript">
	function flush(){
		$("#searchForm").pageSearch();
	}//刷新表单
	
	//点击了全选的复选框
	function checkAll(checkallbox){
	   if(checkallbox.checked){
		   $('input[type="checkbox"][name="onechkx"]').each(function(){
			   $(this).attr("checked",true);
			   <c:if test="${param.type eq 2}">
			   		var id="11"+$(this).val();
			  		var phone=document.getElementById(id).value;
			  		if(!phone||phone=="undefined"){
			  			$(this).attr("checked",false);
			  			$(this).attr("disabled",true);
			  			layer.msg("批量短信有未勾选的已邮寄单，原因：手机号为空！",2,0);
			  		}
			   </c:if>
		   });
	   }else{
		   $('input[type="checkbox"][name="onechkx"]').each(function(){
			   $(this).attr("checked",false);
		   });
	   }
   }
	//订单详情
	function detail(id){
		selectTheOne(id);
		var url= "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
  	//签注
  	function getQzxx(ddbh){
  		selectTheOne(ddbh);
  		var url ="${ctx}/vedsb/common/jpcommon/qzlist?ywdh="+ddbh+"&ywlx=01&from=list";
		   $.layer({
				type: 2,
				title: ['签注'],
				area: ['745px', '400px'],
				iframe: {src: url}
		   }); 
  	}
  	
  	//邮寄行程单
  	function youJi(ddbh,id,flag){
  		selectTheOne(ddbh);
  		var url ="${ctx}/vedsb/xcdgl/yjxcd/youYj?ddbh="+ddbh+"&flag="+flag;
  		if(id&&id!='undefined'){
  			url ="${ctx}/vedsb/xcdgl/yjxcd/youYj?ddbh="+ddbh+"&id="+id+"&flag="+flag;
  		}
	   $.layer({
			type: 2,
			title: ['邮寄行程单'],
			area: ['560px', '200px'],
			iframe: {src: url}
	   });
  	}
  	//打印快递单
  	function printOne(ddbh,flag){
  		var mbid=document.getElementById("mbid").value;
  		selectTheOne(ddbh);
  		window.open("${ctx}/vedsb/xcdgl/printxcd/viewprint?ddbh="+ddbh+"&mbid="+mbid+"&flag="+flag);
  	}
  //改变选择时间类型
  function setRqtj(){
  	var type = document.searchForm.type.value;
  	if(type == ""){
  		type = "0";
  	}
  	if(type == "2"){
	  	var rqtj = document.searchForm.rqtj.value;
	  	if(rqtj == "4"){
	  		document.getElementById("rq1").innerHTML="邮寄日始";
	  		document.getElementById("rq2").innerHTML="邮寄日止";	
	  	}else if(rqtj == "3"){
	  		document.getElementById("rq1").innerHTML="打印日始";
	  		document.getElementById("rq2").innerHTML="打印日止";	
	  	}else if(rqtj == "1"){
	  		document.getElementById("rq1").innerHTML="预订日始";
	  		document.getElementById("rq2").innerHTML="预订日止";
	  	}else{
	  		document.getElementById("rq1").innerHTML="起飞日始";
	  		document.getElementById("rq2").innerHTML="起飞日止";
	  	}
	}else if(type == "1"){
		var rqtj = document.searchForm.rqtj.value;
		if(rqtj == "1"){
	  		document.getElementById("rq1").innerHTML="预订日始";
	  		document.getElementById("rq2").innerHTML="预订日止";
	  	}else if(rqtj == "3"){
	  		document.getElementById("rq1").innerHTML="打印日始";
	  		document.getElementById("rq2").innerHTML="打印日止";
	  	}else{
	  		document.getElementById("rq1").innerHTML="起飞日始";
	  		document.getElementById("rq2").innerHTML="起飞日止";
	  	}
	}else if(type == "0"){
		var rqtj = document.searchForm.rqtj.value;
		if(rqtj == "1"){
	  		document.getElementById("rq1").innerHTML="预订日始";
	  		document.getElementById("rq2").innerHTML="预订日止";
	  	}else if(rqtj == "2"){
	  		document.getElementById("rq1").innerHTML="起飞日始";
	  		document.getElementById("rq2").innerHTML="起飞日止";
	  	}
	}
	document.searchForm.type.value=type;
  }
  
  //点击查询
   function toSearch(type){
		var ksrq=document.searchForm.startTime.value;
    	var jsrq=document.searchForm.endTime.value;
	  	if(type != "" && type != undefined){
	  		document.searchForm.type.value = type;
	  	}else{
	  		type=0;
	  	}
	  	document.searchForm.type.value=type;
	  	setRqtj();
	  	document.searchForm.action = "${ctx}/vedsb/xcdgl/yjxcd/viewlist?turning=1";  
	   	document.searchForm.submit(); 
	   	//$("ul li").removeClass("currentBtn");
	  	//$("#lx"+type).addClass("currentBtn");
	  	//$("#show"+type).show();
  }

  //取消邮寄行程单
  function cancelYj(flag){
  	var checkboxes = $('input[name="checkbox"]');
	  var checkeds = getStr();
	  if(!checkeds){
	  	  layer.msg("请选择一条记录",2,0);
		  return;
	  }
  	  var url="${ctx}/vedsb/xcdgl/yjxcd/cancelYj";
      $.layer({
        area: ['250px', '150px'],
        dialog: {
        msg: '确定将取消邮寄，是否继续？',
        btns: 2,                    
        type: 4,
        btn: ['确定','取消'],
        yes: function(){
        	var ii = layer.load('系统正在处理您的操作,请稍候!');
  			$.ajax({
        	 		type:"post",
  					url:url,
  					data:{"ddbhs":checkeds,"flag":flag},
  					success:function(result){
  						layer.close(ii);
  						flush();
  						if("1" == result){
  							layer.msg("取消邮寄成功！",2,1);
  						}else{
  							layer.msg("取消邮寄失败！",2,0);
  						}
  					}
        	 	});
        }, no: function(){
            layer.msg('放弃取消邮寄！', 2, 3);
        }
     }});
  }
   //批量取得复选框的值
   function getStr(){
		var str="";
		$('input[type="checkbox"][name="onechkx"]').each(function(){
			if($(this).prop("checked")){
				str += $(this).val()+",";
			}
		});
		if(str){
			str = str.substring(0,str.length-1);
		}
		return str;
  }
  //当操作某条记录时,就它所对应的复选框就选中
  function selectTheOne(ddbh){
  	$('input[type="checkbox"][name="onechkx"]').each(function(){
			   $(this).attr("checked",false);
		   });
	$("#"+ddbh).attr("checked",true);
  }
  //批量打印或合并打印
  function batchPrint(ddtype){
	  var checkeds = getStr();
	  var mbid=document.getElementById("mbid").value;
	  if(!checkeds){
	  	  layer.msg("请选择一条记录",2,0);
		  return;
	  }
	  var flag=ddtype;
	  if(ddtype==0){
	  	ddtype=1;
	  }
	window.open("${ctx}/vedsb/xcdgl/printxcd/viewprint?ddbhs="+checkeds+"&dytype="+ddtype+"&mbid="+mbid+"&flag="+flag);
  }
  //批量短信
  function batchMessage(flag){
  	var checkeds = getStr();
  	var size=1;
	  if(!checkeds){
	  	  layer.msg("请选择一条记录",2,0);
		  return;
	  }else{
	  	size=checkeds.split(",").length;
	  }
	var url="${ctx}/vedsb/xcdgl/printxcd/batchMessage?ddbhs="+checkeds+"&size="+size+"&flag="+flag;
	$.layer({
			type: 2,
			title: ['批量短信'],
			area: ['745px', '450px'],
			iframe: {src: url}
	   });
  }
  //点击复选框事件
  function isChoose(op){
  	if(op.checked){
  		var id="11"+$("#"+op.value).val();
  		var phone=document.getElementById(id).value;
  		if(!phone||phone=="undefined"){
  			op.checked=false;
  			op.disabled=true;
  			layer.msg("手机号为空,不能选择发短信！",2,0);
  		}
  	}
  }
  
    //进入打印形成单页面
  function enterXcdListPage(ddbh){
  	 var url = "${ctx}/vedsb/xcdgl/printxcd/enterXcdListPage?ddbh="+$(ddbh).val();
  	 window.open(url);
  	 /*
	 $.layer({
		type: 2,
		title: ['<b>行程单列表</b>'],
		area: ['1000px', '800px'],
		iframe: {src: url}
	 });*/
  }
</script>
<script id="tpl_list_table" type="text/html">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="18px">全选<input name="allSelected" type="checkbox" onclick="checkAll(this);"/></th>
			<th width="30px">操作</th>
			<th width="40px">网店</th>
			<th width="20px">PNR</th>
			{{#	var type = document.searchForm.type.value;
				var th;
					if(type==2){
						th="";
              		}else{
						th="<th width='30px'>打印<br>行程单</th>";
					}
				}}
			{{th}}
			<th width="40px">预定时间</th>
			{{#	
					if(type==2){
						th="<th width='30px'>邮寄单号</th><th width='35px'>邮寄人</th><th width='30'>短信</th>";
              		}else if(type==1){
						th="<th width='40px'>打印时间</th>";	
					}else{
						th="";
					}
				}}
			{{th}}
			<th width="25px">航程</th>
			<th width="28px">航班号</th>
			<th width="42px">起飞时间</th>
			<th width="40px">乘机人</th>
			<th width="40px">收件人</th>
			<th width="50px">收件人电话</th>
			<th width="33px">邮政编码</th>
			<th width="80px">收件地址</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center"><c:if test="${param.type ne 2}"><input name="onechkx" type="checkbox" value="{{d.list[i].DDBH}}" 
				id="{{d.list[i].DDBH}}"/></c:if>
				<c:if test="${param.type eq 2}"><input name="onechkx" type="checkbox" value="{{d.list[i].DDBH}}" 
				id="{{d.list[i].DDBH}}" onclick="isChoose(this)"/>
				<input type="hidden" value="{{d.list[i].NXDH}}" id="11{{d.list[i].DDBH}}"/></td>
				</c:if>
				<td class="td_center">
					<c:if test="${empty param.type or param.type eq '0'}">
						<input type='button' onclick = "printOne('{{ d.list[i].DDBH }}',0)" value='打印' title='点击打印邮寄/快递单'>&nbsp;
						{{#
							var qzcount = $.nullToEmpty(d.list[i].QZCOUNT);
							if(qzcount != 0 ){
						}}
							<a href="###" onclick="getQzxx('{{d.list[i].DDBH}}')"><img src='/static/images/zicon.gif' title='点击查看签注信息' ></a>&nbsp;
						{{#
							}else{
						}}
							<a href="###" onclick="getQzxx('{{d.list[i].DDBH}}')">注</a>&nbsp;
						{{#
							}
						}}
					</c:if>
					<c:if test="${param.type eq '1'}">
						<a href='javascript:void(0);' onclick='printOne("{{ d.list[i].DDBH }}",1)' >
			        	重新打印
			    	</a>&nbsp;
					<a href='javascript:void(0);' onclick='youJi("{{ d.list[i].DDBH }}","{{ d.list[i].YJID }}","1")' >
			        	邮寄
			    	</a>
					</c:if>

					<c:if test="${param.type eq '2'}">
						<a href='javascript:void(0);' onclick='youJi("{{ d.list[i].DDBH }}","{{ d.list[i].YJID }}","2")' >
			        	重新邮寄
			    		</a>
					</c:if>
					
				</td>
				<td class="td_center">{{$.nullToEmpty(d.list[i].EX.WDID.wdmc)}}</td>
				<td class="td_center"><a href="javascript:void(0);" onclick="detail('{{d.list[i].DDBH}}');">{{$.nullToEmpty(d.list[i].XSPNRNO)}}</a></td>
				{{#	
					var ddbh = $.nullToEmpty(d.list[i].DDBH);
					var td;
					if(d.list[i].YJZT==2){
						td="";
              		}else{
						td="<td class='td_center'><input type='button' onclick='enterXcdListPage("+$.nullToEmpty(d.list[i].DDBH)+")' value='创建行程单'/></td>";
					}
				}}
				{{td}}
				<td class="td_center">
				{{$.dateF(d.list[i].DDSJ,'MM-dd HH:mm')}}
				</td>
				{{#	
					if(d.list[i].YJZT==2){
						var a="未发送";
						if(d.list[i].DXZT==1){
							a="<font color='red'>已发送</font>";
						}
						td="<td class='td_center'>"+$.nullToEmpty(d.list[i].YJDH)+"</td><td class='td_center'>"
						+$.nullToEmpty(d.list[i].YJR)+"</td><td class='td_center'>"+a+"</td>";
              		}else if(d.list[i].YJZT==1){
						td="<td class='td_center'>"+$.dateF(d.list[i].DYSJ,'MM-dd HH:mm')+"</td>";
					}else{
						td="";
					}
				}}
				{{td}}
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XSHBH) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].CFRQ,'MM-dd HH:mm') }}</td>
				<td class="td_center">
					{{  $.nullToEmpty(d.list[i].CJR) }}
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].SJR) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].NXDH) }} {{  $.nullToEmpty(d.list[i].NXSJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].YZBM) }}</td>
				<td class="td_center">{{  $.cut($.nullToEmpty(d.list[i].XJDZ),15) }}</td>
    		</tr>
		{{# }
			var flag = document.searchForm.type.value;
			if(flag==0){
				document.getElementById("daidy").innerHTML="待打印("+d.totalCount+")";
				document.getElementById("daidy1").value=d.totalCount;
			}else if(flag==1){
				document.getElementById("daiyj").innerHTML="待邮寄("+d.totalCount+")";
				document.getElementById("daiyj1").value=d.totalCount;
			}else if(flag==2){
				document.getElementById("yiyj").innerHTML="已邮寄("+d.totalCount+")";
				document.getElementById("yiyj1").value=d.totalCount;
			}
			
		 }}
	</table>
</script>
</head>
<c:set var="today" value="${vfn:dateShort()}" />
<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
<body>
 <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/xcdgl/yjxcd/queryPage?gngj=${gngj }" id="searchForm" name="searchForm" method="post">
	           	<div class="tabContainer" style="margin-top:-20px;">
					<ul class="tabHead">
						<li lx='0' onclick="toSearch('0');" id="lx0" class="${empty param.type or param.type eq '0' ? 'currentBtn' : ''}">
						<a style="text-decoration:none;" id="daidy">待打印(${empty param.daidy1 ? countMap.DDY : param.daidy1})</a>
						<input type="hidden" id="daidy1" name="daidy1" value="${empty param.daidy1 ? countMap.DDY : param.daidy1}"></li>
						<li lx='1' onclick="toSearch('1');" id="lx1" class="${param.type eq '1' ? 'currentBtn' : ''}">
						<a style="text-decoration:none;" id="daiyj">待邮寄(${empty param.daiyj1 ? countMap.DYJ : param.daiyj1})</a>
						<input type="hidden" id="daiyj1" name="daiyj1" value="${empty param.daiyj1 ? countMap.DYJ : param.daiyj1}"></li>
						<li lx='2' onclick="toSearch('2');" id="lx2" class="${param.type eq '2' ? 'currentBtn' : ''}">
						<a style="text-decoration:none;" id="yiyj">已邮寄(${empty param.yiyj1 ? countMap.YYJ: param.yiyj1})</a>
						<input type="hidden" id="yiyj1" name="yiyj1" value="${empty param.yiyj1 ? countMap.YYJ: param.yiyj1}"></li>
					</ul>
					<div class="clear"></div>
				</div>
	           	<input type="hidden"  name="VIEW" value="827931810cad8945ef6eb71a850bb128" />
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden"  name="moban" value="${mbid }" id="mbid"/>
				<input type="hidden" name="type" value="${param.type}" id="typeforid" >
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
                	<tr>
			  			<td class="text_right">日期条件</td>
				   	    <td class="text_left">
				    	   <select name="rqtj" onchange="setRqtj()" style="width:100px;height:24px;" >
						    		<option value="1" ${param.rqtj eq '1' ? 'selected' : ''}>预订日期</option>
						    		<option value="2" ${param.rqtj eq '2' ? 'selected' : ''}>起飞日期</option>
						    		<c:if test="${param.type eq '1'}">
							    		<option value="3" ${param.rqtj eq '3' ? 'selected' : ''}>打印日期</option>
						    		</c:if>
						    		<c:if test="${param.type eq '2'}">
							    		<option value="3" ${param.rqtj eq '3' ? 'selected' : ''}>打印日期</option>
							    		<option value="4" ${param.rqtj eq '4' ? 'selected' : ''}>邮寄日期</option>
						    		</c:if>
				    	   </select>
				    	</td>	
					    <td class="text_right" id="rq1">预订日始</td>
					    <td class="text_left" >
					    	<input type="text" name="startTime" class="input-text Wdate" style="width:100px;height:24px;" value="${empty param.startTime ? today : param.startTime}"
										 style="width:100px;" onClick="WdatePicker()" >
					    </td>
					    <td class="text_right" id="rq2">预订日止</td>
					    <td class="text_left" >
					    	<input type="text" name="endTime" class="input-text Wdate" style="width:100px;height:24px;" value="${empty param.endTime ? today : param.endTime}"
										 style="width:100px;" onClick="WdatePicker()" >
					    </td>
					    <td class="text_right">P&nbsp;N&nbsp;R</td>
					    <td class="text_left">
					      <input type="text" name="xsPnrNo" class="input-text" style="width:100px;height:24px;" value="${param.xsPnrNo}" onblur="value=$.trim(this.value).toUpperCase();" style="width:100px;" size="6">      
					    </td>
					
					    <td class="text_right">乘机人</td>
					    <td class="text_left">
					      <input type="text" name="cjr" class="input-text" style="width:100px;height:24px;" value="${param.cjr}" size="6" style="width:100px;" >      
					    </td>
					</tr>
					<tr>
					    <td class="text_right">收件人</td>
					    <td class="text_left">
					      <input type="text" name="sjr" class="input-text" style="width:100px;height:24px;" value="${param.sjr}" size="6" style="width:100px;">      
					    </td>
					   	<td class="text_right">网店</td>
					    <td class="ddgl">
			                  <select name="wdid" id="wdpt" class="select" style="width:100px;height:24px;">
				                  	 	<option value="">全部</option>
				                  	 	<c:forEach items="${wdzlszList}" var="onewd">
						                  	<option value="${onewd.id }">${onewd.wdmc }</option> 
				                  	 	</c:forEach>
						      </select>
				        </td>
						 <c:if test="${empty param.type or param.type ne '2'}">
						    	<td class="text_right" colspan="3"></td>
						 </c:if>
						  <c:if test="${param.type eq '2'}">
						  		<td class="text_right">邮寄人</td>
							    <td class="text_left">
							      	<input type="text" name="yjr" class="input-text" style="width:100px;height:24px;" value="${param.yjr}" size="8">      
							    </td>
							    <td class="text_right">邮寄单号</td>
							    <td class="text_left">
							      <input type="text" name="yjdh" class="input-text" style="width:100px;height:24px;" value="${param.yjdh}" size="20">      
							    </td>
						
						  </c:if>
				  	    <td  align="text_right">
				  			<input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询"> 
					    </td>
				  </tr>
	              </table>
	              </form>
            </div>
          </div>
          <c:if test="${empty param.type or param.type eq '0'}">
          	<span id="show0"><input type="button" class="ext_btn ext_btn_success" value="批量打印" onclick="batchPrint(0)" style="margin-top:-5px;margin-left:10px;">
          	<input type="button" class="ext_btn ext_btn_success" value="合并打印" onclick="batchPrint(2)" style="margin-top:-5px;margin-left:10px;">
          	<input type="button" class="ext_btn ext_btn_success" value="客户取消邮寄" onclick="cancelYj(0)" style="margin-top:-5px;margin-left:10px;"></span>
          </c:if>
          <c:if test="${param.type eq '1'}">
          	<span id="show1"><input type="button" class="ext_btn ext_btn_success" value="批量打印" onclick="batchPrint(1)" style="margin-top:-5px;margin-left:10px;">
          	<input type="button" class="ext_btn ext_btn_success" value="客户取消邮寄" onclick="cancelYj(1)" style="margin-top:-5px;margin-left:10px;"></span>
          </c:if>
          <c:if test="${param.type eq '2'}">
          	<span id="show2"><input type="button" class="ext_btn ext_btn_success" value="批量短信" onclick="batchMessage(${param.type})" style="margin-top:-5px;margin-left:10px;"></span>
          </c:if>
          </div>
        </div>
      	<div  class="mt10" >
        	<div id="list_table_page1">
       		 <!-- 分页  ID不能修改-->
        </div>
        <div class="box span10 oh" id="list_table">
             <!-- 显示列表 ID不能修改 -->   
        </div>
        <div id="list_table_page">
        <!-- 分页  ID不能修改-->
     	</div>
  </div>
</body>
</html>