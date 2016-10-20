<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
	    $("#searchFormButton").click();
	})
	
	//跳转新增界面
	function addView(){
		$.layer({
			type: 2,
			title: ['新增账号设置'],
			area: ['500px', '400px'],
			iframe: {src: "${ctx}/vedsb/b2bsz/jpzdcpb2bzh/viewedit?by1=${param.by1 }"}
	    });
	}
	
	function update(id){
		$.layer({
			type: 2,
			title: ['修改账号设置'],
			area: ['500px', '400px'],
			iframe: {src: "${ctx}/vedsb/b2bsz/jpzdcpb2bzh/edit_"+id+"?by1=${param.by1}"}
	    });
	}
	
	//删除
	function deleteById(id){
		$.layer({
		   	 area: ['400px', '150px'],
		        dialog: {
		            msg: '确定删除该行？',
		            btns: 2,
		            type: 4,
		            btn: ['确定','取消'],
		            yes: function(){
		            	layer.msg('删除成功', 1, 1);
		            	setTimeout(function(){
							location.href="${ctx}/vedsb/b2bsz/jpzdcpb2bzh/delete_"+id+"?turningUrl=/vedsb/b2bsz/jpzdcpb2bzh/viewlist?by1=${param.by1 }";
						},800)
		            }, no: function(){
		                layer.msg('放弃删除', 1, 3);
		            }
		     }
	    });
	}
	
</script>
<script id="tpl_list_table" type="text/html">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="5%">序号</th>
			<th width="12%">操作</th>
			<th width="8%">航空<br>公司</th>
			<th width="15%">登录账号</th>
			{{#  
				var bh = ${param.by1 };
				if ( bh == '720102') {
			}}
				<th width="12%">OFFICE号</th>
			{{#
				} 
			}}
			<th width="15%">支付账号</th>
			<th width="25%">简要说明</th>
            <th width="15%">发布人</th>
            <th width="15%">发布时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
					<a href="###"  onclick="update('{{d.list[i].ID}}')">编辑</a>
					<a href="###"  onclick="deleteById('{{d.list[i].ID}}')">删除</a>
				</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HKGS) }}</td>
				<td class="td_left">{{  $.nullToEmpty(d.list[i].ZH) }}</td>
				{{#  
					var bh = ${param.by1 };
					if ( bh == '720102') {
				}}
					<td class="td_left">{{  $.nullToEmpty(d.list[i].OFFICE) }}</td>
				{{#
					} 
				}}
				<td class="td_left">{{  $.nullToEmpty(d.list[i].ZFZH1) }}<br>{{ $.nullToEmpty(d.list[i].ZFZH2) }}</td>
				<td class="td_left">{{  $.nullToEmpty(d.list[i].SM) }}</td>
				<td class="td_left">{{  $.nullToEmpty(d.list[i].YHBH ) }}<br>{{ $.nullToEmpty(d.list[i].XM ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CZDATETIME ) }}</td>
    		</tr>
		{{# } }}
	</table>
</script>
</head>
<body>
<div class="container_clear">
	<div class="box_border">
		<form action="${ctx}/vedsb/b2bsz/jpzdcpb2bzh/queryPage" id="searchForm" name="searchForm" method="POST">
		<input type="hidden"  name="VIEW" value="692A3B3046E69162F490FF0C1E16BCF1" />
		<input type="hidden"  name="by1" value="${param.by1 }"/>
		<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
		<input type="hidden"  name="sortType" value="czdatetime desc" id="sortType"/>
		<input type="hidden"  name="pageSize" value="20" id="pageSize"/>
			<table class="table01" border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td class="td_right" style="width: 15%;">航空公司</td>
		           	<td class="td_left" style="width: 50%;">
						<input type="text" id="gn_hkgs_m" value="${vfc:getHkgs(param.hkgs).shortname}" class="input-text lh25 srk" onchange="clearValue(this,'gn_hkgs');" style="width: 40%;"/>
						<input type="hidden" id="gn_hkgs" name="hkgs" value="${param.hkgs }">
		          	</td>
					<td colspan="6" align="center">
						<input type="button" onclick="addView();" class="ext_btn ext_btn_submit" value="新增">
						<input type="button" id="searchFormButton" class="ext_btn ext_btn_submit" value="查询">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div  class="mt10" style="display: table;">
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