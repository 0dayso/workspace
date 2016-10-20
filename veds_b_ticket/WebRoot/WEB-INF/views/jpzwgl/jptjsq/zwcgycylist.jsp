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
  		
  		//function export(){//导出
  			//if(confirm("你确定要导出追位已采用订单吗?")){
  				// var formVal=$("#searchForm").serialize();
  				 //window.location.href = "";
  			//}
  		//}
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
        <th width="5%">序号</th>
        <th width="13%">操作</th>
		<th width="13%">追位时间</th>
		<th width="13%">原PNR</th>
		<th width="13%">原PNR</br>状态</th>
		<th width="13%">原舱位</br>/票价</th>
		<th width="8%">现PNR</th>`		
		<th width="13%">现舱位</br>/票价</th>		
		<th width="6%">差价</th>
		<th width="13%">退票费率</th>
		<th width="13%">退票费</th>
		<th width="6%">利润</th>		
		<th width="12%">乘机人</th>
		<th width="10%">航程</th>
		<th width="13%">航班号</th>
		<th width="13%">起飞时间</th>
		<th width="13%">出票日期</th>
		<th width="12%">联系人</th>
		<th width="15%">联系电话</th>	
		<th width="13%">申请人</br>申请时间</th>
		<th width="12%">处理人</br>处理时间</th>
		<th width="6%">追位类型</th>
		<th width="12%">申请单号</th>
		<th width="12%">追位方式</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr>
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
		</td>
		<td class="td_center">{{ $.dateF(d.list[i].DP_DATETIME,'MM-dd HH:mm')}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_NO)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YPNR_ZT)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].YCW)}}/{{ $.nullToEmpty(d.list[i].YPRICE)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].XPNR_NO)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].XCW)}}/{{ $.nullToEmpty(d.list[i].PRICE)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CJ)}}</td>		
		<td class="td_center">{{ $.nullToEmpty(d.list[i].FL)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].TPF)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ccjr)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CFCITY)}}{{ $.nullToEmpty(d.list[i].DDCITY)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].HBH)}}</td>
		<td class="td_center">{{ $.dateF(d.list[i].QFTIME,'MM-dd HH:mm')}}</td>		
		<td class="td_center">{{ $.dateF(d.list[i].CP_DATETIME,'MM-dd HH:mm')}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXR)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].LXDH)}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.SQ_USERID.xm)}}</br>{{ $.dateF(d.list[i].SQ_DATETIME,'MM-dd HH:mm')}}</td>	
		<td class="td_center">{{ $.nullToEmpty(d.list[i].CL_USERID)}}/{{ $.dateF(d.list[i].CL_DATETIME,'MM-dd HH:mm')}}</td>
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
		<td class="td_center">{{ $.nullToEmpty(d.list[i].SQDH)}}</td>
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
	           	<input type="hidden"  name="VIEW" value="470DA2FC239828925B29122ECCE96522" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden" name="lb" value="zwcgycy"/>
				<input type="hidden" value="2" name="cjrclzt"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
		             <tr>
						<td class="xsys" style="text-align: right;">日期条件</td>
						<td class="">
							<select name="rqtj" style="width: 101px;height: 24px;" onclick="chengeSpan(this);">
								<option value="1" ${param.rqtj eq 'zw' ? 'selected' : '' }>追位日期</option>
								<option value="2" ${param.rqtj eq 'qf' ? 'selected' : '' }>起飞日期</option>
								<option value="3" ${param.rqtj eq 'cl' ? 'selected' : '' }>处理日期</option>
							</select>
						</td>
						<td class="xsys" style="text-align: right;">追位日始</td>
						<td>
							<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" style="width: 97px;height: 20px;" onClick="WdatePicker()" size ="10">
						</td>
						<td class="xsys" style="text-align: right;">追位日止</td>
						<td>
							<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" style="width: 97px;height: 20px;" onClick="WdatePicker()" size ="10">
						</td>
						<td class="xsys" style="text-align: right;">申请人</td>
						<td>
							<input type="text" id="usermc" name="usermc" value="${param.usermc}" style="width: 97px;height: 20px;" size ="10"/>
						</td>
						
						<td class="xsys" style="text-align: right;">原PNR</td>
						<td class="xsys">
							<input type="text" name="ypnrno" value="${param.ypnr_no }" onblur="value=$.trim(this.value).toUpperCase();" style="width: 97px;height: 20px;" size ="10">
						</td>
					</tr>
					<tr>
						<td class="xsys" style="text-align: right;">追位类型</td>
						<td>
							<select name="zwzt" style="width: 101px;height: 24px;">
								<option value="">==全部==</option>
								<option value="0" ${param.zw_zt eq '0' ? 'selected' : ''}>HL追位</option>
								<option value="1" ${param.zw_zt eq '1' ? 'selected' : ''}>降舱追位</option>
							</select>
						</td>	
						<td class="xsys" style="text-align: right;">联系人</td>
						<td>
							<input type="text" name="lxr" value="${param.lxr }" class="input1" onkeyup="this.value=this.value.replace('%','').toUpperCase()" style="width: 97px;height: 20px;" size ="10">
						</td>		
						<td class="xsys" style="text-align: right;">联系电话</td>
						<td>
							<input type="text" name="lxsj" value="${param.lxsj }" onkeyup="this.value=this.value.replace('%','').toUpperCase()" style="width: 97px;height: 20px;" size ="10" >
						</td>
						<td class="xsys" style="text-align: right;">出票时间</td>	
						<td>
					  	  	<select style="width: 101px;height: 24px;" name="cpdatetime">
					  	  		<option value="">==全部==</option>
					  	  		<option value="JR" ${param.cp_datetime eq 'JR' ? 'selected' : '' }>今日出票</option>
					  	  		<option value="LS" ${param.cp_datetime eq 'LS' ? 'selected' : '' }>历史出票</option>
					  	  	</select>
						</td>
						<td class="xsys" style="text-align: right;">国内国际</td>	
						<td>
							<select name="gngj" style="width: 101px;height: 24px;">
								<option value="">==全部==</option>
								<option value="0" ${param.gngj eq '0' ? 'selected' : ''}>国际</option>
								<option value="1" ${param.gngj eq '1' ? 'selected' : ''}>国内</option>
							</select>
						</td>			
					</tr>
					<tr>
						<td class="xsys" style="text-align: right;">航程</td>
						<td>
							<input type="text" size="9" value="${param.hc }" style="width: 97px;height: 20px;" name="hc" onkeyup="this.value=this.value.replace('%','').toUpperCase()" >
						</td>
				
						<td class="xsys" style="text-align: right;">航班号</td>
						<td>
							<input type="text" size="10" name="hbh" value="${param.hbh }" style="width: 97px;height: 20px;" onkeyup="this.value=this.value.replace('%','').toUpperCase()" >
						</td>
				
						<td class="xsys" style="text-align: right;">现舱位</td>
						<td>
							<input type="text" size="10" name="xcw" value="${param.xcw }" style="width: 97px;height: 20px;" onkeyup="this.value=this.value.replace('%','').toUpperCase()" >
						</td>
		
						<td class="xsys" style="text-align: right;">乘机人</td>
						<td>
							<input type="text" name="cjr" value="${param.cjr }" style="width: 97px;height: 20px;" onkeyup="this.value=this.value.replace('%','').toUpperCase()" size ="10">
						</td>
						
						<td class="xsys" style="text-align: right;">差价</td>
						<td>
							<input type="text" size="4" class="input1" name="cj" value="${param.cj}" style="width: 60px;height: 20px;" onkeyup="this.value=this.value.replace('%','').toUpperCase()" >及以上
						</td>
						<td>
							<input type="button"  id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
							<input type="button" class="ext_btn ext_btn_success" value="导出" onclick="export();">
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
