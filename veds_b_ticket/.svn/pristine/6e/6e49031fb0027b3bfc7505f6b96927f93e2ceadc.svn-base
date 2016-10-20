<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  <script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
  	<script type="text/javascript">
  		$(function(){
  			$("#dr_hkgs").autocompleteGnHkgs("hkgss");
  			$("#searchFormButton").click();
  		});
  		
  		function changedate(obj){
  			var $obj = $(obj);
  			if($obj.val() == '1'){
  				$("#begin").text("导入日期始");
  				$("#end").text("导入日期止");
  			}else if($obj.val() == '2'){
  				$("#begin").text("申请日期始");
  				$("#end").text("申请日期止");
  			}else if($obj.val() == '3'){
  				$("#begin").text("起飞日期始");
  				$("#end").text("起飞日期止");
  			}
  		}
  		function tocancle(id){
  			var url = "${ctx}/vedsb/jpzwgl/jptjsqdr/qxZwdd?id="+id;
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
		           	 					layer.msg('取消失败',2,1);
		           	 				}else if("3" == data){
		           	 					layer.msg('通知追位系统取消追位单失败，请联系管理员',2,1);
		           	 				}
		           	 			}
		           	 		});
		             }, no: function(){
		                 layer.msg('放弃取消', 2, 3);
		             }
		         }
		     });
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
   		
   		function batchQx(){
   			var formVal=$("#searchForm").serialize();
	   		var str = getStr();
	   		var url = "${ctx}/vedsb/jpzwgl/jptjsqdr/batchQxZwdd?ids="+str;
   			if(str == ""){
   				$.layer({
		        area: ['265px', '150px'],
		        dialog: {
		        msg: '被取消的申请单将不再进行追位，确定取消所有追位单吗?',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	$.post("${ctx}/vedsb/jpzwgl/jptjsqdr/batchAllQxZwdd", 
		        		formVal,
		        		function(result) {
		        			if("1"==result){
		  						$("#searchForm").pageSearch();
		  						layer.msg('批量取消成功',2,1);
	  						}else{
	  							layer.msg('批量取消失败',2,1);
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
			           	 				}else if("2" == data){
			           	 					layer.msg('取消失败',2,1);
			           	 				}else if("3" == data){
			           	 					layer.msg('通知追位系统取消追位单失败，请联系管理员',2,1);
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
   
  		function toimport(){
  		   var url="${ctx}/vedsb/jpzwgl/jptjsqdr/toImport"
		   $.layer({
				type: 2,
				title: ['追位订单导入'],
				area: ['525px', '250px'],
				iframe: {src: url}
		   }); 
  		}
  		
  		function refresh(){
	   		$("#searchForm").pageSearch();
   		}
   		ZWDDDRZT = '${vfn:toJSON(vfn:dictList('ZWDDDRZT'))}';
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
		<th width="5%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
        <th width="5%">序号</th>
        <th width="13%">操作</th>
        <th width="4%">航空公司</th>
		<th width="8%">航程</th>
		<th width="13%">航班号</th>
		<th width="13%">乘机日期</th>
		<th width="13%">原PNR</th>
		<th width="13%">舱位/票价</th>
		<th width="13%">乘机人</th>
		<th width="13%">出票时间</th>
		<th width="13%">联系人</th>
		<th width="13%">联系电话</th>
		<th width="12%">提交状态</th>
		<th width="12%">追位状态</th>
		<th width="12%">申请人</br>申请时间</th>
		<th width="15%">申请单号</th>
		<th width="15%">失败原因</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center"><input type="checkbox" name="onechkx" onclick="ifCheckAll(this);" value="{{ $.nullToEmpty(d.list[i].sqdh)}}"></td>
        <td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			{{# if(d.list[i].sftjzw == '1' && d.list[i].sqZt == '1' ){ }}
				<a href="###" title="点击取消追位单" onclick="tocancle('{{d.list[i].sqdh}}');">消</a>
			{{# } }}
			{{# if(d.list[i].sftjzw == '1' && d.list[i].sqZt == '6' ){ }}
				<span title="追位单已取消">消</span>
			{{# } }}
			{{# if(d.list[i].sftjzw == '1' && d.list[i].sqZt == '3' ){ }}
				<span title="追位成功">消</span>
			{{# } }}
		</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].pnrHkgs)}}</div></td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].pnrHc) }}</div></td>		
		<td class="td_center">{{ $.nullToEmpty(d.list[i].pnrHb) }}</div></td>		
		<td class="td_center">{{ $.dateF(d.list[i].cjrqs,'MM-dd HH:mm') }}</td>
		<td class="td_center" style="color: blue;">{{ $.nullToEmpty(d.list[i].pnrNo) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].pnrCw) }}/{{ $.nullToEmpty(d.list[i].pjfw) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].pnrCjr) }}</td>
		<td class="td_center">{{ $.dateF(d.list[i].cpDatetime,'MM-dd HH:mm') }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].lxr) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].lxdh) }}</td>
		<td class="td_center">
			{{#	var zt;
				if(d.list[i].sftjzw == '0'){
					zt = "<font >提交中</font>"
				}else if(d.list[i].sftjzw == '1'){
					zt = "<font style='color: blue;'>提交成功</font>";
				}
			}}
			{{ zt }}
		</td>
		<td class="td_center">{{$.findJson(ZWDDDRZT,d.list[i].sqZt).mc}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.SQUSERID.xm)}}</br>{{ $.dateF(d.list[i].sqDatetime,'MM-dd HH:mm') }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].sqdh) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].by3) }}</td>	
       </tr>
	   {{# } }}
	 </table>
   </script>
    <title>追位订单导入</title>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/jpzwgl/zwlable.jsp" %>
  	<%@ include file="/WEB-INF/views/jpzwgl/jptjsqdr/drZw_label.jsp" %>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpzwgl/jptjsqdr/list" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="B6AAAE982285BC74148DF99EBE66BC64" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="drsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	              	<td class="xsys" style="text-align: right;">日期条件</td>
	                <td>
	                	<select name="search_EQ_rqlx" onchange="changedate(this)" style="width: 101px;height: 24px;">
	                  	  	<option value="1">导入日期</option>
	                  	  	<option value="2">申请日期</option>
	                  	  	<option value="3">起飞日期</option>
	                  	 </select>
	                </td>
	                <td class="xsys" style="text-align: right;" id="begin">导入日期始</td>
	                <td>
		                <input type="text" name="search_EQ_kssj" value="" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker()">
	                </td>
	                <td class="xsys" style="text-align: right;" id="end">导入日期止</td>
	                <td>
	                   <input type="text" name="search_EQ_jssj" value="" size="10"  class="input-text Wdate"  id="maxdate" onFocus="WdatePicker()">
	                </td>
	                <td class="xsys" style="text-align: right;">PNR</td>
	                <td>
	                   <input type="text" name="search_EQ_pnr_No" class="input-text lh30" size="10" value="${param.search_EQ_pnr_No}" onblur="value=$.trim(this.value).toUpperCase();">
	                </td>
	                <td class="xsys" style="text-align: right;">乘机人</td>
	                <td>
	                  	<input type="text" name="search_EQ_pnr_Cjr" class="input-text lh30" size="10" value="${param.search_EQ_pnr_Cjr}" onblur="value=$.trim(this.value);">
	                </td>
	               </tr>
	               <tr>
	               	  <td class="xsys" style="text-align: right;">追位状态</td>
		              <td>
		                 <select name="search_EQ_sq_Zt" style="width: 101px;height: 24px;">
		                  	<option value="">=全部=</option>
		                  	<option value="1">追位中</option>
		                  	<option value="2">追位成功</option>
		                  	<option value="3">追位失败</option>
		                  	<option value="3">管理员消</option>
		                  	</select>
		              </td>
	                  <td class="xsys" style="text-align: right;">航空公司</td>
                  	  <td>
                  	 	<input type="text" name="dr_hkgs" class="input-text lh30" size="10" value="${param.dr_hkgs}" id="dr_hkgs"/>
                  	 	<input type="hidden" name="search_EQ_pnr_Hkgs" id="hkgss" value="${param.search_EQ_pnr_Hkgs}"/>
                  	  </td>
	                  <td class="xsys" style="text-align: right;">航程</td>
	                  <td>
	                  	<input type="text" value="${param.search_EQ_pnr_Hc}"  class="input-text lh30" size="10" name="search_EQ_pnr_Hc" onblur="value=$.trim(this.value).toUpperCase();"/>
	                  </td>
	                  <td class="xsys" style="text-align: right;">航班号</td>
	                  <td>
	                  	<input type="text" value="${param.search_EQ_pnr_Hb}"  class="input-text lh30" size="10" name="search_EQ_pnr_Hb" onblur="value=$.trim(this.value).toUpperCase();"/>
	           		  </td>
	           		  <td class="xsys" style="text-align: right;">联系人</td>
	                  <td>
	                  	<input type="text" value="${param.search_EQ_lxr}"  class="input-text lh30" size="10" name="search_EQ_lxr"/>
	           		  </td>
	                </tr>
	                <tr>
	                  <td class="xsys" style="text-align: right;">联系电话</td>
	                  <td>
	                  	<input type="text" value="${param.search_EQ_lxdh}" style="width: 99px;height: 22px;"  class="input-text lh30" size="10" name="search_EQ_lxdh"/>
	           		  </td>
	                  <td align="right" colspan="2">
	                      	<input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
	                      	<input type="button" class="ext_btn ext_btn_success" value="导入" onclick="toimport()">
	                        <input type="button" class="ext_btn ext_btn_success" value="批量取消" onclick="batchQx()">
	                  </td>
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
