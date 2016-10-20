<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<html>
<head>
<title>报废查询</title>
<!-- 日期选择控件  -->
<script type="text/javascript"src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchFormButton").click();
		
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
<script type="text/javascript">
	PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
	//修改状态
	function xgSfsh(bfNo,sfsh,obj){
		//0未审核 1已审核 2已作废3已取消
		var msg="审核已成功！";
		var tsxx="你确认审核吗?"
		if(sfsh=='3'){
			msg="取消已成功！";
			tsxx="你确认取消作废吗?"
		}
		//提示对话框
		var flag=confirm(tsxx);
		if(!flag){
			return;
		}
		var url="${ctx}/vedsb/pzzx/pzbf/update_sfsh";
		$.ajax({
			url:url,
			type:"post",
			data:{"bfNo":bfNo,"sfsh":sfsh},
			dataType:"json",
			success:function(result){
				if(result.CODE=="0"){
					alert(msg);
					$("#searchFormButton").click();
				}else{
					alert(result.MSG);
				}
			},
			error:function(){
				alert("修改失败!");
			}
		});
	}
</script>
<!-- 模板 -->
<script id="tpl_list_table" type="text/html">
	 <table class="list_table" style="width:1024px;" border="0" cellpadding="0" cellspacing="0">
       <tr>
        <th width="10">序号</th>
		<th width="10">操作</th>
		<th width="16">票证类型</th>
		<th width="10">状态</th>
        <th width="30">起始号码</th>
        <th width="30">终止号码</th>
		<th width="10">数量</th>
		<th width="25">OFFICEID</th>
		<th width="30">报废部门</th>
		<th width="30">操作员</th>
		<th width="80">报废日期</th>
		<th width="60">备注</th>
       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
        <td class="td_center">{{ i+1 }}</td>
		<td class="td_center">
			{{#
				var sh=d.list[i].sfsh;
				if(sh=='0'){
			}}
				<a href="#" title="点击审核该操作" onclick="xgSfsh('{{d.list[i].bfNo}}','1',this);">审</a>
				<a href="#" title="点击取消该操作" onclick="xgSfsh('{{d.list[i].bfNo}}','3',this);">消</a>
			{{#
				}
			}}
		</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].ex.PZTYPE.mc) }}</td>
		<td class="td_center">{{$.findJson(PZZT,d.list[i].sfsh).mc}}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].startno) }}</td>
        <td class="td_center">{{ $.nullToEmpty(d.list[i].endno) }}</td>
		<td class="td_center">{{ d.list[i].endno-d.list[i].startno +1 }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].officeid )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.YHBH.bmmc )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.YHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].bfDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_center">{{$.nullToEmpty(d.list[i].bzbz)}}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
</head>
<body>
	<!-- title -->
	<div id="titlePage">
		<%@include file="list_title.jsp"%></div>
	<!-- 查询条件区 -->
	<div class="container_clear">
		<div id="search_bar" class="mt10">
			<div class="box">
				<div class="box_border">
					<div class="box_center pt10 pb10">
						<form id="searchForm" name="searchForm" action="${ctx}/vedsb/pzzx/pzbf/bf_list" method="post">
							<input type="hidden" name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" /> 
							<input type="hidden" name="pageNum" value="${param.pageNum }" id="pageNum" /> 
							<input type="hidden" name="pageSize" value="10" id="pageSize" />
							<table class="table01">
								<tr>
									<td>票证类型：</td>
									<td>
										 <select name="pztype" class="select srk" datatype="*" style="width: 110px;heigth:24px;"> 
										   <c:forEach items="${vfc:getVeclassLb('7202')}" var="onecgly">
										   		<c:if test="${onecgly.parid ne 'none'}">
							                       <option value="${onecgly.id}" ${param.pztype eq onecgly.mc ? 'selected':'' }>${onecgly.mc}</option>
							                    </c:if>
						                    </c:forEach>
					                     </select>
									</td>
									<td>退回日始：</td>
									<td>
										<input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}"
											class="input-text Wdate" size="11" onClick="WdatePicker()" />
									</td>
									<td>退回日止：</td>
									<td>
										<input type="text" name="jsrq" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}"
										 class="input-text Wdate" size="11" onClick="WdatePicker()" />
									</td>
								</tr>
								<tr>
									<td>起始号码：</td>
									<td>
									  	<input type="text" size="11" id="startno" class="input-text lh25" name="startno" value=""> 
									 </td>
									<td style="text-align: center;">终止号码：
									</td>
									<td>
										<input type="text" size="11" id="endno" class="input-text lh25" name="endno" value="">
									</td>
									<td>报废部门：</td>
									<td>
										<select id="bmbh" name="bmbh" style="width: 108px;height: 24px;">
					 						<option value="">请选择</option>
										 </select>
									</td>
									<td rowspan="2">
										<input type="button" value=" 查 询 "class="ext_btn ext_btn_submit" id="searchFormButton">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="mt10">
			<div id="list_table_page1">
				<!-- 分页  ID不能修改-->
			</div>
			<div class="box span10 oh" id="list_table">
				<!-- 显示列表 ID不能修改 -->
			</div>
			<br>
			<div id="list_table_page">
				<!-- 分页  ID不能修改-->
			</div>
		</div>
	</div>
</body>
</html>