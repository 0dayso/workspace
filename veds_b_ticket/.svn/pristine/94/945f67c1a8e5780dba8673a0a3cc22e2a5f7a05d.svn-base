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
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	  <tr>
        <th width="5%">序号</th>
		<th width="8%">航空公司</th>
		<th width="13%">航程</th>
		<th width="13%">航班号</th>
		<th width="13%">乘机日期</th>
		<th width="13%">航班时刻</th>
		<th width="13%">目标舱位/票价上限</th>
		<th width="13%">退票费率</th>
		<th width="13%">退票费</th>
		<th width="12%">乘机人</th>
		<th width="12%">人数</th>
		<th width="12%">预订有效期</th>
		<th width="12%">联系人</th>
		<th width="15%">联系电话</th>
		<th width="15%">申请人</br>申请时间</th>
		<th width="12%">申请单号</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr>
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HKGS)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CFCITY)}}{{ $.nullToEmpty(d.list[i].DDCITY)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HBH)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].CJRQS,'MM-dd') }}/{{ $.dateF(d.list[i].CJRQZ,'MM-dd') }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HBSKS)}}-{{ $.nullToEmpty(d.list[i].HBSKZ)}}</td>
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
		<td class="td_center">{{ $.nullToEmpty(d.list[i].FL)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].TPF)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ZCJR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CJRS)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].YXQ,'MM-dd HH:mm')}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXDH)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQ_USERID)}}
			</br>{{ $.nullToEmpty(d.list[i].SQ_DATETIME)}}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQDH)}}</td>
		</tr>
	   {{# } }}
	 </table>
   </script>
    <title>订单追位管理</title>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/jpzwgl/jptjsq/jptjsq_label.jsp" %>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/jpzwgl/jptjsq/shZwPagelist" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="47FA151AD00393ED89DC6EC3530856A3" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden" value="0" name="sqzt" id="sqzt" />
				<input type="hidden"  name="rqlx" value="2"/>
				<input type="hidden" name="lb" value="shdl"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                <td class="xsys" style="text-align: right;" id="begin">申请日始</td>
		                <td>
			                <input type="text" name="ksrq" class="input-text Wdate" size="10" id="mindate" onFocus="WdatePicker()">
		                </td>
		                <td class="xsys" style="text-align: right;" id="end">申请日止</td>
		                <td>
		                   <input type="text" name="jsrq" size="10"  class="input-text Wdate"  id="maxdate" onFocus="WdatePicker()">
		                </td>
		                <td class="xsys" style="text-align: right;">国内国际</td>
		                <td>
		                  <select name="gngj">
		                  		<option value="">==所有==</option>
		                  		<option value="1">国内</option>
		                  		<option value="2">国际</option>
		                  </select>
		                </td>
		                <td class="xsys" style="text-align: right;">联系人</td>
		                <td><input type="text" name="lxr" class="input-text lh30" size="10"/></td>
		                <td class="xsys" style="text-align: right;">联系电话</td>
		                <td><input type="text" name="lxdh" class="input-text lh30" size="10"/></td>
		               </tr>
		               <tr>
		                  <td class="xsys" style="text-align: right;">航班号</td>
		                  <td>
		                  	<input type="text" value="${param.hbh}"  class="input-text lh30" size="10" name="hbh"/>
		           		  </td>
		           		  <td class="xsys" style="text-align: right;">乘机人</td>
		                  <td>
		                  	<input type="text" value="${param.cjr}"  class="input-text lh30" size="10" name="cjr"/>
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
