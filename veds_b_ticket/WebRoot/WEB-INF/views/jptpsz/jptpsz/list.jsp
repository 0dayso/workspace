<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<script type="text/javascript"><!--
	   $(function(){
	   		refresh();
	   })
	   //通过ID删除
		function deleteById(id){
	   		var url = "${ctx}/vedsb/jptpsz/jptpsz/delete_"+id;	   			
			$.layer({
				area: ['250px', '150px'],
				dialog: {
					msg: '确定删除吗？',
					btns: 2,                    
					type: 4,
					btn: ['确定','取消'],
					yes: function(){
	             		var ii = layer.load('系统正在处理您的操作,请稍候!');
	            	 	$.ajax({url:url,
	           	 			success:function(data){
	             				layer.close(ii);	           	 			
	           	 				refresh();
	           	 				layer.msg('删除成功',2,1);     
           	 				}          	 				
	           	 		});
	            	}, no: function(){
						layer.msg('放弃删除', 2, 3);
					}
				}
			});
		}
		//批量删除
		function batchDel(){
			var id="";
			$(':checkbox:checked[name="selectRadio"]').each(function(){
				id+=$(this).val()+",";
			});
			if(!id){
				layer.msg("请选择您要删除的项");
				return;
			}
			var url = "${ctx}/vedsb/jptpsz/jptpsz/batchDel?id="+id+"&time="+new Date();	
			$.layer({
				area: ['250px', '150px'],
				title: ['批量删除'],
				dialog: {
					msg: '确定要批量删除吗？',
					btns: 2,                    
					type: 4,
					btn: ['确定','取消'],
					yes: function(){
	             		var ii = layer.load('系统正在处理您的操作,请稍候!');
	            	 	$.ajax({url:url,
	           	 			success:function(data){
		             			layer.close(ii);
	           	 				if(data=="0"){
		           	 				refresh();
		           	 				layer.msg('删除成功',2,1);
	           	 				}else{
		           	 				layer.msg('删除失败',2,3);
           	 					}
           	 				}
	           	 		});
	            	}, no: function(){
						layer.msg('放弃删除', 2, 3);
					}
				}
			});		
		}
		//刷新		
		function refresh(){
			$("#searchForm").attr("from","searchButton");
	   		$("#searchForm").pageSearch();
   		}
	  
		//复选框全选
		function selectAll(o) {
			$(':checkbox[name="selectRadio"]').each(function(){
				$(this).attr("checked",o.checked);
			});
		}
		//修改
  		function edit(id){
	  		var url ="${ctx}/vedsb/jptpsz/jptpsz/edit_"+id;
	   		$.layer({
				type: 2,
				title: ['修改'],
				area: ['500px', '250px'],
				iframe: {src: url}
	  	 	});
   		}
   		//添加
   		function add(){
	    	var url ="${ctx}/vedsb/jptpsz/jptpsz/viewedit";
	   		$.layer({
				type: 2,
				title: ['添加'],
				area: ['500px', '250px'],
				iframe: {src: url}
	  		 });
   		}
   --></script>
		<!-- 模板 -->
		<script id="tpl_list_table" type="text/html">
	 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
       <tr>

   		
		<th width="10"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="selectAll(this);"></th>
        <th width="15">操作</th>
		<th width="10">序号</th>
        <th width="20">航空公司</th>
        <th width="20">票证类型</th>
		<th width="20">是否自愿退票</th>
		<th width="50">预计回款时长/天</th>
		<th width="100">最后修改人/时间</th>
		
       </tr>

	  
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
      	 <tr class="tr">
		<td class="td_center"><input name="selectRadio" type="checkbox" value="{{d.list[i].id}}" />
		<td class="td_center"><a href="###" onclick="edit('{{d.list[i].id}}');">编辑</a>&nbsp;<a href="###" onclick="deleteById('{{d.list[i].id}}')">删除</a></td>
		<td class="td_center">{{ i+1 }}</td>        
        <td class="td_center">{{d.list[i].hkgs }}
		<td class="td_center">{{ d.list[i].pzlx }}</td>
		<td class="td_center">
			{{#	if($.nullToEmpty(d.list[i].sfzytp) == "1"){  }}
				是
			{{# }else{ }}
				否
			{{# } }}
		</td>
		<td class="td_center">{{ d.list[i].yjhksc}}天</td>
		<td class="td_center">{{ d.list[i].ex.XGYH.xm}}&nbsp;&nbsp;{{$.dateF(d.list[i].xgsj,'MM-dd HH:mm')}}</td>
		
       </tr>
	   {{# } }}
	 </table>
   </script>
		<script type="text/javascript">
   
   </script>
	</head>

	<body>
		<div class="box_center pt10 pb10">
			<form action="${ctx}/vedsb/jptpsz/jptpsz/list" id="searchForm" name="searchForm" method="post">
				<input type="hidden" name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
				<input type="hidden" name="pageNum" value="${param.pageNum }" id="pageNum" />
				<input type="hidden" name="orderBy" value="xgsj desc" id="orderBy" />
				<input type="hidden" name="pageSize" value="10" id="pageSize" />
			</form>
			&nbsp;
			<input type="button" class="ext_btn" onclick="batchDel()" value="批量删除">
			<input type="button" name="button" class="ext_btn ext_btn_success" value="添加" onclick="add()">
					
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
