<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<html>
<head>
<title>国际DOCA设置</title>
<script type="text/javascript">
	//关闭页面
	function closePage(){
		var index=parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
	
		//全选
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
		//取消全选
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
	
	//编辑/新增
	function toedit(id){
		var msg = "<b>修改</b>";
		if(id == ""){
			msg = "<b>新增</b>";
		}
		var url = "${ctx}/vedsb/jpzdcp/jpgjdocasz/toedit?id="+id;
		$.layer({
			type: 2,
			title: [msg],
			area: ['550px', '300px'],
			iframe: {src: url}
	    });
	}
	//单个删除
	function todel(id){
		if(id == ""){
			layer.msg('数据有误，请稍后重试!',2,3);
			return;
		}
		var url = "${ctx}/vedsb/jpzdcp/jpgjdocasz/todel";
		$.layer({
	        area: ['265px', '150px'],
	        title:['<b>删除</b>'],
		    dialog: {
		        msg: '你确定要删除吗？',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
			        $.ajax({
		  				type:"post",
		  				url:url,
		  				data:{id:id},
		  				success:function(result){
		        		    layer.close(ii);
			  				if(result == '0'){
								layer.msg('删除成功!',2,1);
								$("#searchForm").submit();
							}else{
	  							layer.msg('删除失败!',2,3);
	  						}
		  				}	
		  			});
		        }, 
		        no: function(){
		            layer.msg('放弃删除', 2, 3);
		        }
		      }
		 });
	}
	
	 //获取选中的记录的ID
   function getIdArr(){
   		//保存ID，已逗号分隔
   		var ids="";
   		$("input[name=onechkx]").each(function(){
   			if($(this).is(":checked")){
   				ids += $(this).attr("idVal") + ",";
   			}
   		});
   		if(ids){
   			ids = ids.substring(0,ids.length-1);
   		}
   		return ids;
   }
	//批量删除
	function batchDel(){
		var ids=getIdArr();
		if(ids == ""){
			layer.msg('请勾选要删除的记录!',2,3);
			return;
		}
		$.layer({
	        area: ['265px', '150px'],
	        title:['<b>批量删除</b>'],
		    dialog: {
		        msg: '你确定要批量删除吗？',
		        btns: 2,                   
		        type: 4,
		        btn: ['确定','取消'],
		        yes: function(){
		        	var ii = layer.load('系统正在处理您的操作,请稍候!');
			        $.ajax({
		  				type:"post",
		  				url:"${ctx}/vedsb/jpzdcp/jpgjdocasz/batchDel",
		  				data:{ids:ids},
		  				success:function(result){
		        		    layer.close(ii);
			  				if(result == "0"){
								layer.msg('批量删除成功!',2,1);
								$("#searchForm").submit();
							}else{
	  							layer.msg('批量删除失败!',2,3);
	  						}
		  				}	
		  			});
		        }, 
		        no: function(){
		            layer.msg('放弃批量删除', 2, 3);
		        }
		      }
		 });
	}
</script>
</head>
<body>
	<form action="${ctx}/vedsb/jpzdcp/jpgjdocasz/query" method="post" id="searchForm">
	</form>
	<table width="" cellpadding="1" style="margin-top: 3px;">
			<tr>
			    <td>
			       <input type="button"  value="新 增" name="New" class="ext_btn ext_btn_submit"  onClick="toedit('');">
			       <input type="button"  value="批量删除" name="Del" class="ext_btn ext_btn_submit" onClick="batchDel();">       
			  </td>
		  </tr>
		</table>
	<table class="list_table" border="0" cellpadding="0" cellspacing="0"  id="vc" align="center"  width="95%">
		<thead>
			<tr>
				<th rowspan="2" width="3%"><input type="checkbox" name="checkallbox" id="checkallbox" onclick="checkAll(this);"></th>
				<th rowspan="2" width="3%">序号</th>
				<th rowspan="2" width="7%">操作</th>
				<th rowspan="2" width="8%">城市匹配键<br><font color="gray">（城市三字码）</font></th>
				<th colspan="5">居住地（R）</th>
				<th colspan="5">目的地（D）</th>
				<th rowspan="2">修改人</th>
				<th rowspan="2" width="8%">修改时间</th>
			</tr>
			<tr>
				<th width="8%">国籍<br><font color="gray">（国家二字码）</font></th>
				<th width="7%">详细地址</th>
				<th width="7%">城市名称</th>
				<th width="7%">所在<br>省市州信息</th>
				<th width="6%">邮政编码</th>
				<th width="8%">国籍<br><font color="gray">（国家二字码）</font></th>
				<th width="7%">详细地址</th>
				<th width="7%">城市名称</th>
				<th width="7%">所在<br>省市州信息</th>
				<th width="6%">邮政编码</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="entity"  varStatus="status">
				<tr>
					<td align="center">
						<input type="checkbox" name="onechkx" id="checkallbox" onclick="ifCheckAll(this);" idVal="${entity.id }">
					</td>
					<td align="center">${ status.index + 1}</td>
					<td align="center">
						<a href="###" onclick="toedit('${entity.id }')">修改</a>
						<a href="###" onclick="todel('${entity.id }')">删除</a>
					</td>
					<td>${entity.csszm }</td>
					<td align="center">${entity.rGj }</td>
					<td>${entity.rXxdz }</td>
					<td>${entity.rCsmc }</td>
					<td>${entity.rCzxx }</td>
					<td align="center">${entity.rYzbm }</td>
					<td align="center">${entity.dGj }</td>
					<td>${entity.dXxdz }</td>
					<td>${entity.dCsmc }</td>
					<td>${entity.dCzxx }</td>
					<td align="center">${entity.dYzbm }</td>
					<td>${entity.xgyhbh }</td>
					<td align="center"><fmt:formatDate value="${entity.xgDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<div align="center">
		<input type="button" name="button" id="close_list" onclick="closePage()" class="ext_btn ext_btn_submit" value="关闭">
	</div>
</body>

</html>

