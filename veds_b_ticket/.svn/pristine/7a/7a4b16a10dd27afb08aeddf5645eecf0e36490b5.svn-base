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
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
        <th width="5%">序号</th>
        <th width="13%">操作</th>
		<th width="8%">航空</br>公司</th>
		<th width="13%">PNR</th>
		<th width="13%">航程</th>
		<th width="13%">航班号</th>
		<th width="15%">乘机日期</th>
		<th width="8%">航班时刻</th>
		<th width="13%">目标舱位</br>/票价上限</th>
		<th width="12%">预订有效期</th>
		<th width="12%">联系人</th>
		<th width="15%">联系电话</th>
		<th width="15%">未进入队列原因</th>
		<th width="15%">申请人</br>申请时间</th>
		<th width="15%">申请单号</th>
		<th width="12%">订单号</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr>
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center"></td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HKGS)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_NO)}}</td>
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
		<td class="td_center">{{ $.dateF(d.list[i].YXQ,'MM-dd HH:mm')}}</td>		
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXDH)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].by3s)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.SQ_USERID.xm)}}
			</br>{{ $.dateF(d.list[i].SQ_DATETIME,'MM-dd HH:mm')}}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQDH)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].DDBH)}}</td>
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
	           	<input type="hidden"  name="VIEW" value="1D27D9BF8109CE891C40BFEF2D27D1D4" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden"  name="rqlx" value="2"/>
				<input type="hidden" name="lb" value="wjzwdl"><!-- 未进追位队列 -->
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                <td class="xsys" style="text-align: right;" id="begin">申请日始</td>
		                <td>
			                <input type="text" name="ksrq" class="input-text Wdate" style="width: 99px;height: 22px;" size="10" id="mindate" onFocus="WdatePicker()" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}">
		                </td>
		                <td class="xsys" style="text-align: right;" id="end">申请日止</td>
		                <td>
		                   <input type="text" name="jsrq" size="10"  class="input-text Wdate" style="width: 99px;height: 22px;"  id="maxdate" onFocus="WdatePicker()" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}">
		                </td>
		                <td class="xsys" style="text-align: right;">国内国际</td>
		                <td>
		                  <select name="gngj" style="width: 101px;height: 24px;">
		                  		<option value="">==全部==</option>
		                  		<option value="1">国内</option>
		                  		<option value="0">国际</option>
		                  </select>
		                </td>
		                <td class="xsys" style="text-align: right;">联系人</td>
			            <td><input type="text" name="lxr" class="input-text lh30" size="10"/></td>
			            <td class="xsys" style="text-align: right;">联系电话</td>
			            <td><input type="text" name="lxdh" class="input-text lh30" size="10"/></td>
		              <tr>
			            <td class="xsys" style="text-align: right;">航程</td>
	                    <td>
	                  	    <input type="text" value="${param.hc}" style="width: 99px;height: 22px;"  class="input-text lh30" size="10" name="hc"/>
	           		    </td>   
			            <td class="xsys" style="text-align: right;">航班号</td>
	                    <td>
	                  	    <input type="text" value="${param.hbh}" style="width: 99px;height: 22px;"  class="input-text lh30" size="10" name="hbh"/>
	           		    </td>
	           		    <td class="xsys" style="text-align: right;">乘机人</td>
	                    <td>
	                  	    <input type="text" value="${param.cjr}" style="width: 99px;height: 22px;"  class="input-text lh30" size="10" name="cjr"/>
	           		    </td>
	           		    <td class="xsys" style="text-align: right;">PNR</td>
	                    <td>
	                  	    <input type="text" value="${param.ypnrno}" onblur="value=$.trim(this.value).toUpperCase();" class="input-text lh30" size="10" name="ypnrno"/>
	           		    </td>
	           		    <td align="right">
		                    <input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
		                </td>
		                <td align="right">
		                    <input type="button" class="ext_btn ext_btn_success" value="导出">
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
