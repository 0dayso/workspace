<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  	<script type="text/javascript">
  		$(function(){
  			$("#searchFormButton").click();
  		});
  		
  		function flush(){
  			$("#searchForm").pageSearch();
  		}
  		function changedate(obj){
  			var $obj = $(obj);
  			if($obj.val() == '1'){
  				$("#begin").text("追位日始");
  				$("#end").text("追位日止");
  			}else if($obj.val() == '2'){
  				$("#begin").text("申请日始");
  				$("#end").text("申请日止");
  			}else if($obj.val() == '3'){
  				$("#begin").text("起飞日始");
  				$("#end").text("起飞日止");
  			}
  		}
  		
  		function toQrsy(id){
  			var url = "${ctx}/vedsb/jpzwgl/jptjsqdr/bjZw?id="+id;
	  		$.layer({
	  			area: ['250px', '150px'],
	  			dialog : {
	  				 msg : "确定要标记此追位单已使用吗？",
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
		  							layer.msg("标记成功！",2,1);
		  						}else{
		  							layer.msg("标记失败！",2,1);
		  						}
		  					}
		        	 	});
		        	 },no: function(){
		             	layer.msg("取消标记操作", 2, 3);
		          	 }
	  			}
	  		});
  		}
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <th width="5%">序号</th>
        <th width="13%">操作</th>
		<th width="8%">航程</th>
		<th width="13%">追位时间</th>
		<th width="13%">原PNR</th>
		<th width="13%">原PNR状态</th>
		<th width="15%">原舱位/票价</th>
		<th width="13%">现PNR</th>
		<th width="15%">现舱位/票价</th>
		<th width="13%">差价</th>
		<th width="12%">退票费率</th>
		<th width="12%">退票费</th>
		<th width="12%">利润</th>
		<th width="12%">乘机人</th>
		<th width="15%">航班号</th>
		<th width="15%">起飞时间</th>
		<th width="15%">出票日期</th>
		<th width="12%">联系人</th>
		<th width="15%">联系电话</th>
		<th width="15%">申请时间</th>
		<th width="15%">追位类型</th>
		<th width="15%">申请单号</th>
		<th width="15%">追位方式</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			{{# if(d.list[i].CL_ZT == '1'){ }}
				<span style="color: blue;">已采用</span>
			{{# } }}
			{{# if(d.list[i].CL_ZT == '0' && d.list[i].SQ_ZT == '3' && d.list[i].SFTJZW == '1'){ }}
				<input title="点击标记此单已使用" type="button" class="" onclick="toQrsy('{{d.list[i].sqdh}}')" value="标记"/>
			{{# } }}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CFCITY)}}{{ $.nullToEmpty(d.list[i].DDCITY)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].DP_DATETIME)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_NO)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_ZT)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YCWPJ)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ZWPNR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].XCWPJ)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CJ)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].FL)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].TPF)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CJR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HBH)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].QFTIME,'MM-dd HH:mm') }}</td>
		<td class="td_center">{{ $.dateF(d.list[i].CP_DATETIME,'MM-dd HH:mm') }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXDH)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].SQ_DATETIME,'MM-dd HH:mm') }}</td>
		<td class="td_center">
		{{# var zt;
			if(d.list[i].ZW_ZT == '0'){
				zt = "<font>HL追位</font>"
			}else if(d.list[i].ZW_ZT == '1'){
				zt = "<font>降舱追位</font>"
			}else{
				zt = "";
			}
		}}
		{{ zt }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQDH)}}</td>
		<td class="td_center">
		{{# var lx;
			if(d.list[i].ZWLX == '0'){
				lx = "<font>当天自动追位</font>"
			}else if(d.list[i].ZWLX == '3'){
				lx = "<font>隔天自动追位</font>"
			}else if(d.list[i].ZWLX == '1' || d.list[i].ZWLX == '2'){
				lx = "<font>手动追位</font>"
			}else{
				lx = "";
			}
		}}
		{{ lx }}
		</td>
	   {{# } }}
	 </table>
   </script>
    <title>追位成功管理</title>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/jpzwgl/zwlable.jsp" %>
  	<%@ include file="/WEB-INF/views/jpzwgl/jptjsqdr/drZw_label.jsp" %>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpzwgl/jptjsqdr/zwSuccPagelist" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="3F797EAF041025336C71F765439C800B" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="orderBy" value="drsj desc" id="orderBy"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	              	<td class="xsys" style="text-align: right;">日期条件</td>
	                <td>
	                	<select name="rqlx" onchange="changedate(this)" style="width: 101px;height: 24px;">
	                  	  	<option value="1">追位日期</option>
	                  	  	<option value="2">申请日期</option>
	                  	  	<option value="3">起飞日期</option>
	                  	 </select>
	                </td>
	                <td class="xsys" style="text-align: right;" id="begin">追位日始</td>
	                <td>
		                <input type="text" name="ksrq" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker()">
	                </td>
	                <td class="xsys" style="text-align: right;" id="end">追位日止</td>
	                <td>
	                   <input type="text" name="jsrq" size="10"  class="input-text Wdate"  id="maxdate" onFocus="WdatePicker()">
	                </td>
	                <td class="xsys" style="text-align: right;">申请人</td>
	                <td>
	                   <input type="text" name="squserid" class="input-text lh30" size="10" value="${param.squserid}">
	                </td>
	                <td class="xsys" style="text-align: right;">追位方式</td>
	                <td>
	                  	<select name="zwlx" style="width: 101px;height: 24px;">
	                  	  <option value="">=全部=</option>
	                  	  <option value="0">当天自动追位</option>
	                  	  <option value="3">隔天自动追位</option>
	                  	  <option value="1">手动追位</option>
	                  	</select>
	                </td>
	               </tr>
	               <tr>
	               	  <td class="xsys" style="text-align: right;">出票时间</td>
		              <td>
		                 <select name="cpdatetime" style="width: 101px;height: 24px;">
		                  	<option value="">=全部=</option>
		                  	<option value="JR">今日出票</option>
		                  	<option value="LS">历史出票</option>
		                  </select>
		              </td>
	                  <td class="xsys" style="text-align: right;">航班号</td>
	                  <td>
	                  	<input type="text" value="${param.hbh}"  class="input-text lh30" size="10" name="hbh" onkeyup="this.value=this.value.toUpperCase()"/>
	           		  </td>
	           		  <td class="xsys" style="text-align: right;">原PNR</td>
	                  <td>
	                  	<input type="text" value="${param.ypnrno}" onblur="value=$.trim(this.value).toUpperCase();" class="input-text lh30" size="10" name="ypnrno"/>
	           		  </td>
	           		  <td class="xsys" style="text-align: right;">乘机人</td>
	                  <td>
	                  	<input type="text" value="${param.cjr}"  class="input-text lh30" size="10" name="cjr"/>
	           		  </td>
	           		  <td class="xsys" style="text-align: right;">追位状态</td>
                  	  <td>
                  	 	<select name="sqzt" style="width: 101px;height: 24px;">
                  	 	  <option value="">=全部=</option>
	                  	  <option value="1">追位中</option>
	                  	  <option value="3">追位成功</option>
	                  	  <option value="4">追位失败</option>
	                  	  <option value="6">取消追位</option>
                  	 	</select>
                  	  </td>
	                </tr>
	                <tr>
	                  <td class="xsys" style="text-align: right;">是否采用</td>
                  	  <td>
                  	 	<select name="clzt" style="width: 101px;height: 24px;">
                  	 	  <option value="">=全部=</option>
	                  	  <option value="0">未采用</option>
	                  	  <option value="1">已采用</option>
                  	 	</select>
                  	  </td>
	                  <td align="right">
	                    <input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
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
