<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
 <title>票证入库查询</title>
	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
 <script type="text/javascript">
 	PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
 </script>
   
   <script id="tpl_list_table" type="text/html">	
	   <table  class="list_table" style="width:1024px;" border="0" cellpadding="0" cellspacing="0" >
     <tr>
		<th width="5">序号</th>
		<th width="20">操作</th>
		<th width="10">状态</th>
		<th width="15">票证类型</th>
        <th width="30">起始号码</th>
        <th width="30">终止号码</th>
		<th width="10">数量</th>
		<th width="30">OFFICE号</th>
		<th width="20">操作员</th>
		<th width="40">入库日期</th>
		<th width="60">备注</th>
    </tr>   
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			
			{{#
				var sfsh =d.list[i].sfsh;
				if(sfsh=='0') { 
			}}
				<a href="##" onclick="OptionIn('sh','{{d.list[i].inNo}}','{{d.list[i].startno}}','{{d.list[i].endno}}');" >审核</a>&nbsp;
				<a href="##" onclick="OptionIn('zf','{{d.list[i].inNo}}','{{d.list[i].startno}}','{{d.list[i].endno}}');">作废</a>
			{{#
				} else if (sfsh=='1') {
			}}
				<a href="##" onclick="OptionIn('fsh','{{d.list[i].inNo}}','{{d.list[i].startno}}','{{d.list[i].endno}}');">反审核</a>&nbsp;
			{{#
				} else {
			}}
				
			{{#
				}
			}}
		</td>
		<td class="td_center">{{$.findJson(PZZT,d.list[i].sfsh).mc}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.PZTYPE.mc) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].startno) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].endno) }}</td>
		<td class="td_right">{{ $.nullToEmpty(d.list[i].rksl) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].officeid) }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.YHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].inDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].bzbz) }}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
   
   <script type="text/javascript">
   
  		function OptionIn(my,inNo,startno,endno){
  			if(my=='sh'){
  				var boolean = window.confirm('请注意，该条记录审核通过后将正式生效，是否审核?');
				if(boolean){
					window.location.href='${ctx}/vedsb/pzzx/pzrk/changeSH?inNo='+inNo+'&sfsh2='+0;
				}	  			
  				
  			}else if(my=='fsh'){
  					var boolean = window.confirm('请注意，您将反审核该条记录，是否继续?');
					if(boolean){
						window.location.href='${ctx}/vedsb/pzzx/pzrk/changeFSH?inNo='+inNo+'&sfsh2='+1+'&startno='+startno+'&endno='+endno;
					}	
  				
  			}else{
  					var boolean = window.confirm('请注意，该条记录作废后将无效，是否继续?');
					if(boolean){
						window.location.href='${ctx}/vedsb/pzzx/pzrk/changeZF?inNo='+inNo+'&sfsh2='+0+'&startno='+startno+'&endno='+endno;
  				}
  			}
  		}
  		$(function() {
  			$("#searchFormButton").click();
  		});
   </script>
  </head>
  <body>
  <!--页签 -->
<%@include file="pzrk_title.jsp"%>
   <div class="container_clear">
  	  <div id="search_bar" class="mt10">
       	<div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
            <form action="${ctx}/vedsb/pzzx/pzrk/pzrkcx" id="searchForm" name="searchForm" method="post">
           	<input type="hidden"  name="VIEW" value="46faaed8d140d8486dd161b8b990df95" />
            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
            <input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
			<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
		    
		   <input type="hidden" name="method" value="${param.method }"/>
                <table class="table01" border="0" cellpadding="0" cellspacing="0">
                <tr>
                	<td>
            			票证类型:
            		</td>
            		<td>
		            	<select name="pztype"  style="width:100px" class="select">
			               				<c:forEach items="${vfc:getVeclassLb('7202')}" var="oneLx">
										    <c:if test="${oneLx.id ne '7202'}">
												<option value="${oneLx.id }">${oneLx.mc }</option>
											</c:if>
										</c:forEach>
			            </select>
		           		 &nbsp;<font color="red">*</font>
	           		</td>
                  <td class="tab_in_td_h">入库日始:</td>
                  <td><input type="text"  name="in_datetime"   class="input-text Wdate" value="${empty param.out_datetime ? vfn:dateShort() : param.out_datetime}"  size="12" onClick="WdatePicker()"/>
                  <td class="tab_in_td_h" >入库日止:</td>
                  <td><input type="text" name="rkrz"   class="input-text Wdate"  value="${empty param.out_datetime ? vfn:dateShort() : param.out_datetime}" size="12" onClick="WdatePicker()"/>
              
             <tr/>
             <tr>
             <td class="tab_in_td_h" >状  态:</td>
               <td>
                   <select id="sfsh" name="sfsh" class="select">         
                       <option value="" ${empty param.sfsh ? 'seletced' : '' }>==请选择==</option>
                       <option value="0" ${param.sfsh eq '0' ? 'selected' : ''  }>未审核</option>
                       <option value="1" ${param.sfsh eq '1' ? 'selected' : ''  }>已审核</option>
                       <option value="2" ${param.sfsh eq '2' ? 'selected' : ''  }>已作废</option>
                  </select>
              </td>
              <td class="tab_in_td_h" >起始号码:</td>
               <td class="tab_in_td_f" colspan="1" >
     	        <input type="text" id="startno" name="startno" size="12" value=""  class="input-text lh25" onkeyup="if(isNaN(value))execCommand('undo')">
               </td>
               <td class="tab_in_td_h" >终止号码:</td>
               <td class="tab_in_td_f" colspan="3" >
               <input type="text" id="endno" name="endno" value="" size="12"  class="input-text lh25" onkeyup="if(isNaN(value))execCommand('undo')">
               </td>
               
               <td>
               		<input type="button" id="searchFormButton" name="button" value="查询" class="ext_btn ext_btn_submit" />
              		<input type="hidden"  id="msg" name="msg" value="${param.msg }"/>
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



