<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function colorRemind(){
		var url="${ctx}/vedsb/colorremind/colorremind/viewlist";
  		$.layer({
				type: 2,
				title: ['提醒颜色修改'],
				area: ['600px', '400px'],
				iframe: {src: url}
		   });
	}
	
	//设置默认排序
	function szmrpx(){
		var url="${ctx}/vedsb/colorremind/colorremind/saveMrpx";
		var pxzd=$("#pxzd").val();
		var px=$("input[name='px']:checked").val();
		$.ajax({
			url:url,
			type:"post",
			data:{"bh":"2025","csz1":pxzd,"csz2":px},
			dataType:"json",
			success:function(result){
				if(result.CODE == '0'){
					$("#orderBy").val(pxzd+" "+px);
					layer.msg(result.MSG,2,1);
				}else{
					layer.msg(result.MSG,2,5);
				}
			},error:function(){
				layer.msg("保存失败!",2,5);
			}
		});
	}
</script>
<table>
    <tr>
		<td>
		    <a href="###" onclick="colorRemind();">颜色提醒设置</a>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;
		     <span style="color: red;">默认排序：</span>
		</td>
		<td>
   			<select name="pxzd" id="pxzd" class="input1-text" style="width:102px;height:26px;">
   				<option value="ddsj">预订时间 </option>
   				<option value="cfrq">起飞时间</option>
   				<option value="nosj">NO位时间</option>
   			</select>
   			&nbsp;&nbsp;
		 </td>
		<td>
	       <input type="radio" value="asc" name="px"/>升序
	       <input type="radio" value="desc" name="px"/>降序
	        &nbsp;&nbsp;
		</td>
		<td>
			<input type="button" value="应用" onclick="szmrpx()" class="ext_btn ext_btn_submit"/>
		</td>
	</tr>
</table>