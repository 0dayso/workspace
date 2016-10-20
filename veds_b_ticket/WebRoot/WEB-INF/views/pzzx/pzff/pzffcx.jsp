<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
 <title>票证发放查询</title>
<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
 <script type="text/javascript">
 	PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
 	$(function(){
 		//获取部门下拉选的值
			var url="${ctx}/vedsb/shbm/shbm/query";
			$.ajax({
				type:"post",
				dataType:"json",
				url:url,
				success:function(result){
					if(result.CODE == '0'){
						var list=result.DATA;
						for(var i=0;i<list.length;i++){
							var sel='${param.bmbh}'==list[i].id ? 'selected':''
							var $opt="<option value="+list[i].id+" "+sel+">"+list[i].mc+"</option>";
							$("#bmbh").append($opt);
						}
					}
				}
			});
 	});
 </script> 
   
   <script id="tpl_list_table" type="text/html">	
	 	  <table  class="list_table" style="width:1024px;" border="0" cellpadding="0" cellspacing="0" >  
       <tr>
		<th width="2">序号</th>
		<th width="5">票证类型</th>
        <th width="15">起始号码</th>
        <th width="15">终止号码</th>
		<th width="5">数量</th>
		<th width="10">OFFICE号</th>
		<th width="15">发放到部门</th>
		<th width="15">操作员</th>
		<th width="20">发放日期</th>
		<th width="10">备注</th>
       </tr>   
	   		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
        <td class="td_center">{{ i+1 }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.PZFL.mc) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].startno) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].endno) }}</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].ffsl) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].officeid) }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].bmbh)}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.YHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].outDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].bzbz) }}</td>
       </tr>
	   {{# } }}
	   
	 </table>

   </script>
   
   
   
   <script type="text/javascript">
   		
   		$(function(){
   			$("#searchFormButton").click();
   		});
  		
   </script>
  </head>
  
  <body>
     <!--页签 -->

<div class="box_top"><%@include file="pzff_title.jsp"%></div>
   <div class="container_clear">
  	  <div id="search_bar" class="mt10">
       	<div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
            <form action="${ctx}/vedsb/pzzx/pzff/pzffcx" id="searchForm" name="searchForm" method="post">
           	<input type="hidden"  name="VIEW" value="46faaed8d140d8486dd161b8b990df95" />
            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
            <input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
			<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
		    <input type="hidden" name="sfsh2" id="sfsh2" value=""/>
		    <input type="hidden" name="inNo2" id="inNo2" value=""/>
                <table class="table01" border="0" cellpadding="0" cellspacing="0">
                <tr>
                
                	 <td>
            		  票证类型:
            		</td>
            	<td>
	            	<select name="pzfl"  style="width:100px" class="select">
		               				<c:forEach items="${vfc:getVeclassLb('7202')}" var="oneLx">
									    <c:if test="${oneLx.id ne '7202'}">
											<option value="${oneLx.id }">${oneLx.mc }</option>
										</c:if>
									</c:forEach>
		            </select>
		            &nbsp;<font color="red">*</font>
	            </td>
                  <td class="tab_in_td_h" >发放日始:</td>
                  <td><input type="text" name="out_datetime"  class="input-text Wdate" value="${empty param.out_datetime ? vfn:dateShort() : param.out_datetime}"  size="12" onClick="WdatePicker()"/>
                  
                  <td class="tab_in_td_h" >发放日止:</td>
                  <td><input type="text" name="ffrz"   class="input-text Wdate" value="${empty param.out_datetime ? vfn:dateShort() : param.out_datetime}"  size="12" onClick="WdatePicker()"/>
                  </tr>
                  <tr>
                    <td class="tab_in_td_h" >发放到部门:</td>
                  <td>
                  	<select id="bmbh" name="bmbh" style="width: 100px;height: 24px;">
					 	<option value="">请选择</option>
					 </select>
                  </td>
               
               
              <td class="tab_in_td_h" >起始号码:</td>
               <td class="tab_in_td_f" colspan="1" >
     	        <input type="text" id="startno" name="startno" size="12" value=""   class="input-text lh25" onkeyup="if(isNaN(value))execCommand('undo')"> 
             </td>
              <td class="tab_in_td_h" >终止号码:</td>
               <td class="tab_in_td_f" colspan="1" >
     	        <input type="text" id="endno" name="endno" size="12" value=""   class="input-text lh25" onkeyup="if(isNaN(value))execCommand('undo')">
             </td>
               <td>
               		<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit"/>
               </td>
                </tr>
              </table> 
              </form>
          </div>
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
  </body>
</html>



