<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
 <html>
 <head>
   <!-- 日期选择控件  -->
   <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
   <script type="text/javascript">
   function deleteById(id){
	   if(confirm('确认删除吗?')){
		   document.location.href="/example/student/delete_"+id+"?turningUrl=/example/student/viewlist";
	   }
   }
   function insert(){
	   document.location.href="/example/student/viewedit";
   }
   </script>
 	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
       <tr>
		<th width="25">操作</th>
        <th width="10">序号</th>
        <th width="20">姓名</th>
        <th width="20">城市</th>
		<th width="10">年龄</th>
		<th width="20">出生日期</th>
		<th width="100">创建时间</th>
		<th width="100">总公司</th>
       </tr>
	   
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center"><a href="/example/student/edit_{{d.list[i].id}}">编辑</a>&nbsp;<a href="###" onclick="deleteById('{{d.list[i].id}}')">删除</a></td>
        <td class="td_center">{{ i+1 }}</td>
        <td class="td_center">{{ d.list[i].name }}</td>
        <td class="td_center">{{ d.list[i].city }}</td>
		<td class="td_center">{{ d.list[i].age }}</td>
		<td class="td_center">{{ d.list[i].csrqStr }}</td>
		<td class="td_center">{{ d.list[i].createtimeStr }}</td>
		<td class="td_center">{{ d.list[i].zgs }}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
   <script type="text/javascript">
   
   </script>
  </head>
  
  <body>
   <div class="container">
  	  <div id="button" class="mt10">
       <input type="button" name="button" class="ext_btn ext_btn_success" value="新增" onclick="insert()"> 
     </div>
  	  <div id="search_bar" class="mt10">
       <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">搜索</b></div>
            <div class="box_center pt10 pb10">
            <form action="/example/student/list" id="searchForm" name="searchForm" method="post">
           	<input type="hidden"  name="VIEW" value="D2C95EB52CD99905941BD2207D28971E" />
            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
			<input type="hidden"  name="orderBy" value="createtime desc" id="orderBy"/>
			<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>姓名</td>
                  <td><input type="text" name="search_LIKE_name" value="${param.search_LIKE_name }" class="input-text lh25" size="10"></td>
                  <td>性别</td>
                  <td>
                   <select name="search_EQ_age" class="select"> 
                        <option value="1">男</option> 
                        <option value="0">女</option> 
                        </select> 
                  </td>
                  <td>出生日期</td>
                  <td><input type="text" name="search_EQdate_csrq" value="${param.search_EQdate_csrq }"  class="input-text lh25" size="10" onClick="WdatePicker()"/></td>
                  <td>
                      <input type="button" name="button" id="searchFormButton"   class="ext_btn ext_btn_submit" value="查询">  
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
     </div>
  </body>
</html>
