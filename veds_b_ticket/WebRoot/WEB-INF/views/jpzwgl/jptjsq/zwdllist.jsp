<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  	<script type="text/javascript">
  		$(function(){
  			//获取账户余额
  			$.ajax({
  				url : "${ctx}/vedsb/jpzwgl/jptjsq/getzhye",
  				type: "POST",
  				dataType : "json",
  				success: function(result){
  					if(result.error != ''){
  						$("#zhye").append(result.error);
  					}else{
  						$("#zhye").append("您的追位账户总余额："+result.total+"元(追位账户"+result.kyye+"元;追位赠送账户"+result.zszhye+"元)"+"&nbsp;&nbsp;<font color='#EE11EE'>"+result.gg+"</font>");
  					}
   				}
  			});
  			$("#searchFormButton").click();
  		});
  		
  		function flush(){
  			$("#searchForm").pageSearch();
  		}
  		
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
  		
  		function changelx(obj){
  			var $obj = $(obj);
  			if($obj.val() == '1'){
  				$("#begin").text("审核日始");
  				$("#end").text("审核日止");
  			}else if($obj.val() == '2'){
  				$("#begin").text("申请日始");
  				$("#end").text("申请日止");
  			}else if($obj.val() == '3'){
  				$("#begin").text("起飞日始");
  				$("#end").text("起飞日止");
  			}
  		}
  		
  		function tocancle(id){//取消
  			var url = "${ctx}/vedsb/jpzwgl/jptjsq/qxzwd?id="+id;
  			$.layer({
		    	 area: ['250px', '150px'],
		         dialog: {
		             msg: '被取消的申请单将不再进行追位，确定取消吗?',
		             btns: 2,                    
		             type: 4,
		             btn: ['确定','取消'],
		             yes: function(){
		            	 	$.ajax({url:url,
		           	 			success:function(data){
		           	 				if("1"==data){
			           	 				$("#searchForm").pageSearch();
			           	 				layer.msg('取消成功',2,1);
		           	 				}else if("2" == data){
		           	 					layer.msg('该追位订单不能取消!',2,1);
		           	 				}else if("3" == data){
		           	 					layer.msg('通知追位系统取消追位单失败，请联系管理员!',2,1);
		           	 				}else{
		           	 					layer.msg('取消失败!',2,1);
		           	 				}
		           	 			}
		           	 		});
		             }, no: function(){
		                 layer.msg('放弃取消', 2, 3);
		             }
		         }
		     });
  		}
  		
  		function batchQx(){
  			var formVal=$("#searchForm").serialize();
	   		var str = getStr();
	   		var url = "${ctx}/vedsb/jpzwgl/jptjsq/batchQxzwds?ids="+str;
   			if(str == ""){
   				$.layer({
		        area: ['265px', '150px'],
		        dialog: {
		        msg: '被取消的申请单将不再进行追位，确定取消所有追位单吗?',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	$.post("${ctx}/vedsb/jpzwgl/jptjsq/batchAllQxzwd", 
		        		formVal,
		        		function(result) {
		        			if("1"==result){
		  						$("#searchForm").pageSearch();
		  						layer.msg('批量取消成功',2,1);
	  						}else if("2"==result){
	  							layer.msg('存在不能取消的追位单',2,1);
	  						}else if("3"==result){
	  							layer.msg('通知追位系统取消追位单失败，请联系管理员!',2,1);
	  						}else{
	  							layer.msg('取消失败',2,1);
	  						}
		        		}
		        	);
		        }, no: function(){
		        	 layer.msg('放弃取消', 2, 3);
		           }
		      }
		    });
   			}else{
				$.layer({
			    	 area: ['250px', '150px'],
			         dialog: {
			             msg: '被取消的申请单将不再进行追位，确定取消吗?',
			             btns: 2,                    
			             type: 4,
			             btn: ['确定','取消'],
			             yes: function(){
			            	 	$.ajax({url:url,
			           	 			success:function(data){
			           	 				if("1"==data){
				           	 				$("#searchForm").pageSearch();
				           	 				layer.msg('取消成功',2,1);
			           	 				}else if("2"==data){
	  										layer.msg('存在不能取消的追位单',2,1);
	  									}else if("3"==data){
	  										layer.msg('通知追位系统取消追位单失败，请联系管理员!',2,1);
	  									}else{
	  										layer.msg('取消失败',2,1);
	  									}
			           	 			}
			           	 		});
			             }, no: function(){
			                 layer.msg('放弃取消', 2, 3);
			             }
			         }
			     });
		   }
  		}
  		
  		function checkAll(checkallbox){
		   if(checkallbox.checked){
			   $('input[type="checkbox"][name="onechkx"]').each(function(){
				   $(this).attr("checked",true);
			   });
		   }else{
			   $('input[type="checkbox"][name="onechkx"]').each(function(){
				   $(this).attr("checked",false);
			   });
		   }
   		}
   		
   		function ifCheckAll(onechkx){
		   var chkxArr = $('input[type="checkbox"][name="onechkx"]');
		   var total = chkxArr.length;
		   var checkedLen = 0;
		   if(onechkx.checked){
			   $(chkxArr).each(function(){
				   if($(this).prop("checked")){
					   checkedLen++;
				   }
			   });
			   if(total==checkedLen){
				   $("#checkallbox").attr("checked",true);
			   }else{
				   $("#checkallbox").attr("checked",false);
			   }
		   }else{
			   $("#checkallbox").attr("checked",false);
		   }
   		}
   		ZWDLSQZT = '${vfn:toJSON(vfn:dictList('ZWDLSQZT'))}';
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
		<th width="5%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
        <th width="5%">序号</th>
        <th width="9%">操作</th>
		<th width="10%">航程</th>
		<th width="10%">航班号</th>
		<th width="13%">乘机日期</th>
		<th width="13%">出票日期</th>
		<th width="13%">目标舱位</br>/票价上限</th>
		<th width="9%">原编码</th>
		<th width="5%">原舱位</th>
		<th width="8%">退票费率</th>
		<th width="10%">退票费</th>
		<th width="12%">乘机人</th>
		<th width="8%">人数</th>
		<th width="12%">联系人</th>
		<th width="15%">联系电话</th>
		<th width="15%">申请人</br>申请时间</th>
		<th width="10%">申请单</br>状态</th>
		<th width="15%">申请单号</th>
		<th width="12%">追位方式</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr>
		<td class="td_center"><input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" value="{{ $.nullToEmpty(d.list[i].SQDH)}}"></td>
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			{{#if(d.list[i].SQ_ZT == '1'){ }}
				<a href="###" onclick="tocancle('{{d.list[i].SQDH}}');">消</a>
			{{# } }}
			{{#if(d.list[i].SQ_ZT != '1'){ }}
				<span>取消</span>
			{{# } }}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CFCITY)}}{{ $.nullToEmpty(d.list[i].DDCITY)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HBH)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].CJRQS,'MM-dd') }}/{{ $.dateF(d.list[i].CJRQZ,'MM-dd') }}</td>
		<td class="td_center">{{ $.dateF(d.list[i].CP_DATETIME,'MM-dd HH:mm') }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CW)}}
			{{# var fs;
				if(d.list[i].ZWFS == '0' && d.list[i].GNGJ == '1' && d.list[i].CW != ''){ 
					fs = "<font>及以下</font>"
				}else{
					fs = "";
				}
			}}
			{{fs}}/{{d.list[i].PJFW}}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_NO)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YCW)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].FL)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].TPF)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ccjr)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CJRS)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXDH)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].EX.SQ_USERID.xm)}}
			</br>{{ $.dateF(d.list[i].SQ_DATETIME,'MM-dd HH:mm')}}
		</td>
		<td class="td_center">{{$.findJson(ZWDLSQZT,d.list[i].SQ_ZT).mc}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQDH)}}</td>
		<td class="td_center">
			{{# var zts;
				if($.nullToEmpty(d.list[i].ZWLX) == '0'){
					zts = "当天自动追位";
				}else if($.nullToEmpty(d.list[i].ZWLX) == '3'){
					zts = "隔天自动追位";
				}else if($.nullToEmpty(d.list[i].ZWLX) == '1' || $.nullToEmpty(d.list[i].ZWLX) == '2'){
					zts = "手动追位";
				}else{
					zts = "";
				}
			}}
			{{zts}}
		</td>
		</tr>
	   {{# } }}
	 </table>
   </script>
    <title>订单追位管理</title>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/jpzwgl/zwlable.jsp" %>
  	<%@ include file="/WEB-INF/views/jpzwgl/jptjsq/jptjsq_label.jsp" %>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpzwgl/jptjsq/shZwPagelist" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="0930234A0E9416B4DCBF3C9B3EA000F8" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden" name="lb" value="shdl"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		              	<td class="xsys" style="text-align: right;">日期条件</td>
		              	<td>
		              		<select name="rqlx" onchange="changelx(this)" style="width: 101px;height: 24px;">
		              			<option value="2">申请日期</option>
		              			<option value="3">起飞日期</option>
		              		</select>
		              	</td>
		                <td class="xsys" style="text-align: right;" id="begin">申请日始</td>
		                <td>
			                <input type="text" name="ksrq" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker()" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}">
		                </td>
		                <td class="xsys" style="text-align: right;" id="end">申请日止</td>
		                <td>
		                   <input type="text" name="jsrq" size="10"  class="input-text Wdate"  id="maxdate" onFocus="WdatePicker()" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}">
		                </td>
	                    <td class="xsys" style="text-align: right;">申请人</td>
	                    <td>
	                  	  <input type="text" value=""  class="input-text lh30" size="10" name=""/>
	           		    </td>
	           		     <td class="xsys" style="text-align: right;">国内国际</td>
			             <td>
			                <select name="gngj" style="width: 101px;height: 24px;">
			                  <option value="">==全部==</option>
			                  <option value="1">国内</option>
			                  <option value="0">国际</option>
			                </select>
			             </td>
		               <tr>
			             <td class="xsys" style="text-align: right;">联系人</td>
			             <td><input type="text" name="lxr" class="input-text lh30" size="10"/></td>
			             <td class="xsys" style="text-align: right;">联系电话</td>
			             <td><input type="text" name="lxdh" class="input-text lh30" size="10"/></td>
			             <td class="xsys" style="text-align: right;">追位方式</td>
			             <td>
			                <select name="zwlx" style="width: 101px;height: 24px;">
			                  <option value="">==全部==</option>
			                  <option value="0">当天自动追位</option>
			                  <option value="3">隔天自动追位</option>
			                  <option value="1">手动追位</option>
			                </select>
			              </td>
			              <td class="xsys" style="text-align: right;">出票时间</td>
			              <td>
			                <select name="" style="width: 101px;height: 24px;">
			                  <option value="">==全部==</option>
			                  <option value="JR">今日出票</option>
			                  <option value="LS">历史出票</option>
			                </select>
			              </td>
			              <td class="xsys" style="text-align: right;">航班号</td>
		                  <td>
		                  	  <input type="text" value="${param.hbh}"  class="input-text lh30" size="10" name="hbh" onkeyup="this.value=this.value.replace('%','').toUpperCase()"/>
		           		  </td>
		           	 </tr>
		           	 <tr>
		           		  <td class="xsys" style="text-align: right;">航程</td>
		                  <td>
		                  	  <input type="text" value="${param.hc}"  class="input-text lh30" size="10" name="hc" onkeyup="this.value=this.value.replace('%','').toUpperCase()"/>
		           		  </td>
		           		  <td class="xsys" style="text-align: right;">原PNR</td>
		                  <td>
		                  	  <input type="text" value="${param.ypnrno}"  class="input-text lh30" size="10" name="ypnrno" onblur="value=$.trim(this.value).toUpperCase();"/>
		           		  </td>
		           		  <td class="xsys" style="text-align: right;">乘机人</td>
		                  <td>
		                  	  <input type="text" value="${param.cjr}"  class="input-text lh30" size="10" name="cjr"/>
		           		  </td>
		           		  <td class="xsys" style="text-align: right;">追位状态</td>
			              <td>
			                 <select name="sqzt" style="width: 101px;height: 24px;">
			                  <option value="">==全部==</option>
			                  <option value="1">追位中</option>
			                  <option value="2">部分追位</option>
			                  <option value="3">追位成功</option>
			                  <option value="4">客户消</option>
			                  <option value="4">管理员消</option>
			                  </select>
			                </td>
		                    <td align="right">
		                        <input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
		                    </td>
		                    <td align="right">
		                        <input type="button" class="ext_btn ext_btn_success" value="批量取消" onclick="batchQx()">
		                    </td>
		                 </tr>
	              </table>
	              </form>
	              <p style="background:#fef5e9; margin:5px 0; text-indent:10px; width:100%; height:30px; line-height:30px; border:1px solid #fee5c3; color:#cc3e00; font-size:14px; font-weight:bold;" id="zhye">
				  </p>
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
