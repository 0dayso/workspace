<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<link href="${ctx}/static/core/validform-rjboy/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function(){
		$("#searchFormButton").click();
	});
	
	function updateBcd(id){
		var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?id="+id;
		   $.layer({
				type: 2,
				title: ['修改补差单'],
				area: ['370px', '190px'],
				iframe: {src: url}
		   });
	}
	
	function shBcd(id){
		var url ="${ctx}/vedsb/jpbcd/jpbcd/toedit?id="+id+"&sh=1";
		   $.layer({
				type: 2,
				title: ['审核补差单'],
				area: ['370px', '190px'],
				iframe: {src: url}
		   });
	}
	
	function flush(){
		$("#searchForm").pageSearch();
	}
	
	function qxBcd(id){
		var url = "${ctx}/vedsb/jpbcd/jpbcd/qxBcd?id="+id;
  		$.layer({
  			area: ['250px', '150px'],
  			dialog : {
  				 msg : "确定要取消 补差单吗？",
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
	  							layer.msg("补差单取消成功！",2,1);
	  						}else{
	  							layer.msg("补差单取消失败！",2,1);
	  						}
	  					}
	        	 	});
	        	 },no: function(){
	             	layer.msg("取消补差单取消操作", 2, 3);
	          	 }
  			}
  		});
	}
	//补差单支付
	function bcdPay(id){
		var url ="${ctx}/vedsb/jpbcd/jpbcd/toBcdPay?id="+id;
		   $.layer({
				type: 2,
				title: ['补差单支付'],
				area: ['550px', '250px'],
				iframe: {src: url}
		   });
	}
</script>
<script id="tpl_list_table" type="text/html">
	<table width="780px" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="10">序号</th>
			<th width="30">操作</th>
			<th width="20">补差单</br>状态</th>
			<th width="20">网店</th>
			<th width="20">PNR</th>
			<th width="45">外部单号</th>
			<th width="40">补差单类型</th>
			<th width="20">补差金额</th>
			<th width="55">补差说明</th>
			<th width="20">收款状态</th>
			<th width="50">申请人</th>
			<th width="50">申请时间</th>
			<th width="50">审核人</th>
			<th width="50">审核时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
					{{# if(d.list[i].bczt != '3' && d.list[i].bczt != '2'){ }}
					{{# if(d.list[i].bczt != '1'){ }}
					<a onclick = "shBcd('{{d.list[i].id}}');" style="cursor: pointer;">审</a>&nbsp;
					{{# } }}
					{{# if(d.list[i].bczt == '1'){ }}
					<a style="cursor: pointer;" onclick="bcdPay('{{d.list[i].id}}')">付</a>&nbsp;
					{{# } }}
					<a onclick = "updateBcd('{{d.list[i].id}}');" style="cursor: pointer;">改</a>&nbsp;
					<a onclick = "qxBcd('{{d.list[i].id}}');" style="cursor: pointer;">消</a>&nbsp;
					{{# } }}
					{{# if(d.list[i].bczt == '3' || d.list[i].bczt == '2'){ }}
					<a style="color:#666">改</a>&nbsp;
					<a style="color:#666">消</a>&nbsp;
					{{# } }}
				</td>
       			<td class="td_center">
				{{#	var zt;
					if(d.list[i].bczt =="0"){
						zt="<font>已申请</font>";
              		}else if(d.list[i].bczt =="1"){
						zt="<font color='green'>已审核</font>";
					}else if(d.list[i].bczt =="2"){
						zt="<font color='green'>已完成</font>";
					}else if(d.list[i].bczt =="3"){
						zt="<font color='red'>已取消</font>";
					}
				}}
				{{zt}}
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.WDPT.mc) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].pnrNo)}}</td>
				<td class="td_center" title="{{ $.nullToEmpty(d.list[i].wbdh)}}">{{  $.nullToEmpty(d.list[i].wbdhString) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.BCLX.mc) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].bcje) }}</td>
				<td class="td_center" title="{{$.nullToEmpty(d.list[i].bcsm)}}">{{  $.nullToEmpty(d.list[i].bcsmString) }}</td>
				<td class="td_center">
				{{# var skzt;
					  if(d.list[i].skzt == "0"){
						skzt = "<font>未收</font>";
					  }else{
						skzt = "<font>已收</font>";
					  }
				}}
				{{skzt}}
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.CJYH.xm) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].cjsj,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].ex.SHYH.xm) }}</td>
				<td class="td_center">{{  $.nullToEmpty($.dateF(d.list[i].shsj,'MM-dd HH:mm')) }}</td>
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
               <form action="${ctx}/vedsb/jpbcd/jpbcd/list" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="119F7119A2FD04465899AE83720236D4" />
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                   <td class="xsys" style="text-align: right;">申请日始</td>
	                   <td>
		                	<input type="text" name="search_GTEdate_cjsj" value="${vfn:dateShort()}" class="input-text Wdate" style="width:101px;height:24px;" size="10" id="mindate" onFocus="WdatePicker()">
	                   </td>
	                   <td class="xsys" style="text-align: right;">申请日止</td>
	                   <td>
	                  		<input type="text" name="search_LTEdate_cjsj"  value="${vfn:dateShort()}" size="10"  class="input-text Wdate" style="width:101px;height:24px;" id="maxdate" onFocus="WdatePicker()">
	                   </td>
	                   <td style="text-align: right;">P N R</td>
	                   <td>
	                  		<input type="text" value="${param.search_EQ_pnr_No}" class="input-text lh30" style="width:101px;height:24px;" size="10" name="search_EQ_pnr_No" onblur="value=$.trim(this.value).toUpperCase();"/>
	                   </td>
	                   <td class="xsys" style="text-align: right;">外部单号</td>
	                   <td>
	                  	 	<input type="text" value="${param.search_EQ_wbdh}" name="search_EQ_wbdh" class="input-text lh30" style="width:101px;height:24px;" size="10"/>
	                   </td>
	                   <!-- 
	                   <td class="xsys" style="text-align: right;">日期条件</td>
	                	<td>
		                	<select name="">
		                  	  	<option value="1">申请日期</option>
		                  	  	<option value="2">收款日期</option>
		                  	</select>
	                  	</td>
	                  	 -->
	                  	 <td class="xsys" style="text-align: right;">网店</td>
	                  	 <td>
	                  	 	<select name="search_EQ_wdpt" style="width:101px;height:24px;">
	                  	 		<option value="">==全部==</option> 
                  	 			<option value="10100010">去哪儿</option> 
                  	 			<option value="10100011">淘宝</option> 
                  	 			<option value="10100012">酷讯</option> 
                  	 			<option value="10100050">携程</option> 
                  	 			<option value="10100024">同程</option> 
	                  	 	</select>
	                  	 </td>
	                </tr>
	                <tr>
	                  	 <td class="xsys" style="text-align: right;">补差单状态</td>
	                  	 <td class="xsys">
	                  	 	<select name="search_EQ_bczt" style="width:101px;height:24px;">
		                  	  	<option value="">==全部==</option>
		                  	  	<option value="0">已申请</option>
		                  	  	<option value="1">已审核</option>
		                  	  	<option value="2">已完成</option>
		                  	  	<option value="3">已取消</option>
	                  	  	</select>
	                  	 </td>
	                  	 <td class="xsys" style="text-align: right;">补差单类型</td>
	                  	 <td>
	                  	 	<select name="search_EQ_bclx" style="width:101px;height:24px;">
	                  	 		<option value="">==全部==</option>
	                  	 		<c:forEach items="${list}" var="bcdlx">
	                  	 		<option value="${bcdlx.bh}">${bcdlx.mc}</option>
	                  	 		</c:forEach>
	                  	  	</select>
	                  	 </td>
	                  	 <td style="text-align: right;">业务类型</td>
	                	 <td>
	                	 	<select name="search_EQ_ywlx" style="width:101px;height:24px;">
		                  	  	<option value="">==全部==</option>
		                  	  	<option value="01">机票正常单</option>
		                  	  	<option value="02">机票退票单</option>
		                  	  	<option value="03">机票改签单</option>
		                  	  	<option value="04">差异单</option>
	                  	  	</select>
	                	 </td>
	                	 <td align="right">
	                      	<input type="button" id="searchFormButton" class="ext_btn ext_btn_submit" value="查询"/> 
	                  	 </td>
	                </tr>
	              </table>
	              </form>
            </div>
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
   </div>
  </div>
</body>
</html>