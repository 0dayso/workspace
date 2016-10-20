<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(function(){
		$("#searchFormButton").click();
	});
	
	//不同日期业务查询
	function changeRq(obj){
		var $obj = $(obj);
		if($obj.val() == '1'){
			$("#begin").text("创建日始");
			$("#end").text("创建日止");
		}else if($obj.val() == '2'){
			$("#begin").text("审核日始");
			$("#end").text("审核日止");
		}
	}
	function flush(){
		$("#searchForm").pageSearch();
	}//刷新表单
	
	function insert(czlx){
		   var url ="${ctx}/vedsb/jpbcd/jpcyd/toedit";
		   $.layer({
				type: 2,
				title: ['新增差异单'],
				area: ['620px', '306px'],
				iframe: {src: url}
		   }); 
  		}
  	
  	//修改差异单
  	function updateCyd(ddbh,id,ddlx){
  		var url ="${ctx}/vedsb/jpbcd/jpcyd/toedit?ddbh="+ddbh+"&id="+id+"&ddlx="+ddlx;
		   $.layer({
				type: 2,
				title: ['修改差异单'],
				area: ['620px', '235px'],
				iframe: {src: url}
		   }); 
  	}
  	
  	//审核差异单
  	function shCyd(ddbh,id,ddlx){
  		var sh = '1';
  		var url ="${ctx}/vedsb/jpbcd/jpcyd/toedit?ddbh="+ddbh+"&id="+id+"&ddlx="+ddlx+"&sh="+sh;
		   $.layer({
				type: 2,
				title: ['审核差异单'],
				area: ['620px', '235px'],
				iframe: {src: url}
		   });
  	}
  	//取消差异单
  	function qxCyd(id){
  		var url = "${ctx}/vedsb/jpbcd/jpcyd/qxCyd?id="+id;
  		$.layer({
  			area: ['250px', '150px'],
  			dialog : {
  				 msg : "确定要取消 差异单吗？",
  				 btns: 2,                    
	        	 type: 4,
	        	 btn : ['确定','取消'],
	        	 yes : function(){
	        	 	$.ajax({
	        	 		type:"post",
	  					url:url,
	  					success:function(result){
	  						flush();
	  						if("1" == result){
	  							layer.msg("差异单取消成功！",2,1);
	  						}else{
	  							layer.msg("差异单取消失败！",2,1);
	  						}
	  					}
	        	 	});
	        	 },no: function(){
	             	layer.msg("取消差异单取消操作", 2, 3);
	          	 }
  			}
  		});
  	}
  	
  	//确认到账
  	function cydQrPay(id){
  		var url = "${ctx}/vedsb/jpbcd/jpcyd/cydQrPay?id="+id;
  		$.layer({
  			area: ['250px', '150px'],
  			dialog : {
  				 msg : "是否确认到账？",
  				 btns: 2,                    
	        	 type: 4,
	        	 btn : ['确定','取消'],
	        	 yes : function(){
	        	 	$.ajax({
	        	 		type:"post",
	  					url:url,
	  					success:function(result){
	  						flush();
	  						if("1" == result){
	  							layer.msg("确认到账成功！",2,1);
	  						}else{
	  							layer.msg("确认到账失败！",2,1);
	  						}
	  					}
	        	 	});
	        	 },no: function(){
	             	layer.msg("取消确认到账", 2, 3);
	          	 }
  			}
  		});
  	}
  	
  	function bcdDetail(bcdh){
  		var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?id="+bcdh+"&bcdDetail=1";
		   $.layer({
				type: 2,
				title: ['查看补差单'],
				area: ['370px', '190px'],
				iframe: {src: url}
		   });
  	}
</script>
<script id="tpl_list_table" type="text/html">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="10">序号</th>
			<th width="30">操作</th>
			<th width="30">状态</th>
			<th width="35">订单类型</th>
			<th width="60">外部单号</th>
			<th width="65">补差单号</th>
			<th width="30">PNR</th>
			<th width="35">差异类型</th>
			<th width="35">系统金额</th>
			<th width="35">网店金额</th>
			<th width="35">差异金额</th>
			<th width="55">差异说明</th>
			<th width="60">创建人</br>创建时间</th>
			<th width="60">审核人</br>审核时间</th>
			<th width="60">到账确认人</br>到账确认时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
					{{# if(d.list[i].zt !="3" && d.list[i].zt !="2"){ }}
					{{# if(d.list[i].zt !="1"){ }}
					<a onclick = "shCyd('{{d.list[i].ddbh}}','{{d.list[i].id}}','{{d.list[i].ddlx}}');" style="cursor: pointer;">审</a>&nbsp;
					{{# } }}
					{{# if(d.list[i].zt !="2" && d.list[i].zt != '0'){ }}
					<a style="cursor: pointer;" onclick = "cydQrPay('{{d.list[i].id}}')">确</a>&nbsp;
					{{# } }}
					<a onclick = "updateCyd('{{d.list[i].ddbh}}','{{d.list[i].id}}','{{d.list[i].ddlx}}');" style="cursor: pointer;">改</a>&nbsp;
					<a onclick = "qxCyd('{{d.list[i].id}}');" style="cursor: pointer;">消</a>&nbsp;
					{{# } }}
					{{# if(d.list[i].zt =="3" || d.list[i].zt =="2"){ }}
						<a style="color:#666">改</a>&nbsp;
						<a style="color:#666">消</a>&nbsp;
					{{# } }}
				</td>
       			<td class="td_center">
				{{#	var zt;
					if(d.list[i].zt =="0"){
						zt="<font>待审核</font>";
              		}else if(d.list[i].zt =="1"){
						zt="<font color='green'>已审核</font>";
					}else if(d.list[i].zt =="2"){
						zt="<font color='green'>已到账</font>";
					}else if(d.list[i].zt =="3"){
						zt="<font color='red'>已取消</font>";
					}
				}}
				{{zt}}
				</td>
				<td class="td_center">
				{{#  var ddlx;
					if(d.list[i].ddlx == "1"){
						ddlx = "<font>机票订单</font>";
					}else if(d.list[i].ddlx == "2"){
						ddlx = "<font>退票订单</font>";
					}else if(d.list[i].ddlx == "3"){
						ddlx = "<font>改签订单</font>";
					}
				}}
				{{ddlx}}
				</td>
				<td class="td_center" title="{{ $.nullToEmpty(d.list[i].wbdh)}}">{{  $.nullToEmpty(d.list[i].wbdhString) }}</td>
				<td class="td_center"><a onclick="bcdDetail('{{d.list[i].bcdh}}');" style="cursor: pointer;">{{  $.nullToEmpty(d.list[i].bcdh) }}</a></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].pnrNo) }}</td>
				<td class="td_center">
				{{# var cylx; 
					if(d.list[i].cylx == "1"){
						cylx = "<font>票价</font>";
					}else if(d.list[i].cylx == "2"){
						cylx = "<font>机建</font>";
					}else if(d.list[i].cylx == "3"){
						cylx = "<font>税费</font>";
					}else if(d.list[i].cylx == "4"){
						cylx = "<font>退票费</font>";
					}else if(d.list[i].cylx == "5"){
						cylx = "<font>改签费</font>";
					}
				}}
				{{cylx}}
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].xtTkje) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].wdTkje) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].cyje) }}</td>
				<td class="td_center" title="{{$.nullToEmpty(d.list[i].cysm)}}">{{  $.nullToEmpty(d.list[i].cysmString) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.CJYH.xm)}}</br>{{$.nullToEmpty(d.list[i].xgsjStr) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.SHYH.xm)}}</br>{{  $.dateF(d.list[i].shsj,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.QYYH.xm)}}</br>{{  $.dateF(d.list[i].qysj,'MM-dd HH:mm') }}</td>
    		</tr>
		{{# } }}
	</table>
</script>
</head>
<body>
 <%@ include file="/WEB-INF/views/jpbcd/jpbcd_label.jsp" %>
 <div class="container">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpbcd/jpcyd/list" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="5D7D06DAEDF4D00C12CE7DBF81AB7DF7" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                 <td class="xsys" style="text-align: right;" id="begin">创建日始</td>
	                  <td>
		                <input type="text" name="search_EQ_kssj" class="input-text Wdate" value="${vfn:dateShort()}" size="10" id="mindate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
	                  </td>
	                  <td class="xsys" style="text-align: right;" id="end">创建日止</td>
	                  <td>
	                  	<input type="text" name="search_EQ_jssj" size="10"  class="input-text Wdate" value="${vfn:dateShort()}" id="maxdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	                  </td>
	                  <td class="xsys" style="text-align: right;">订单类型</td>
	                  <td>
	                  	  <select name="search_EQ_ddlx" style="width:101px;height:24px;">
	                  	  	<option value="">==全部==</option>
	                  	  	<option value="1">正常单</option>
	                  	  	<option value="2">退票单</option>
	                  	  	<option value="3">改签单</option>
	                  	  </select>
	                  </td>
	                  <td class="xsys" style="text-align: right;">差异类型</td>
	                  <td >
	                  	  <select name="search_EQ_cylx" style="width:101px;height:24px;">
	                  	  	<option value="">==全部==</option>
	                  	  	<option value="1">票价</option>
	                  	  	<option value="2">机建</option>
	                  	  	<option value="3">税费</option>
	                  	  	<option value="4">退票费</option>
	                  	  	<option value="5">改签费用</option>
	                  	  </select>
	                  </td>
	                  <td class="xsys" style="text-align: right;">日期条件</td>
	                  <td>
	                	<select name="search_EQ_rqlx" style="width:101px;height:24px;" onchange="changeRq(this)">
	                  	  	<option value="1" ${param.rqlx eq '1' ? 'selected' : ''}>创建日期</option>
	                  	  	<option value="2" ${param.rqlx eq '2' ? 'selected' : '' }>审核日期</option>
	                  	 </select>
	                  </td>
	                </tr>
	                <tr>
	                  	 <td class="xsys" style="text-align: right;">外部单号</td>
	                  	 <td>
	                  	 	<input type="text" value="${param.search_EQ_wbdh}" name="search_EQ_wbdh" class="input-text lh30" size="10"/>
	                  	 </td>
	                  	 <td class="xsys" style="text-align: right;">P N R</td>
	                  	 <td>
	                  	 	<input type="text" value="${param.search_EQ_pnr_No}"  class="input-text lh30" size="10" name="search_EQ_pnr_No" onblur="value=$.trim(this.value).toUpperCase();"/>
	                  	 </td>
	                  	 <td class="xsys" style="text-align: right;">差异单状态</td>
	                  	  <td>
	                  	 	<select name="search_EQ_zt" style="width:101px;height:24px;">
		                  	  	<option value="">==全部==</option>
		                  	  	<option value="0">待审核</option>
		                  	  	<option value="1">已审核</option>
		                  	  	<option value="2">已确认</option>
		                  	  	<option value="3">已取消</option>
	                  	  	</select>
	                  	 </td>
	                  	  <td align="right">
	                      	<input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询"> 
	                  	  </td>
	                  	  <td colspan="9">
                      	  	<input type="button" class="ext_btn ext_btn_success" value="新增" onclick="insert()">
                      	  </td>
	                </tr>
	                <tr>
	                </tr>
	              </table>
	              </form>
            </div>
          </div>
        </div>
      <div  class="mt10">
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
   </div>
  </div>
</body>
</html>