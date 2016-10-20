<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<style>
	.input: {
		width: 50px;
	}
	
	.td_right {
		text-align: right;
	}
	
	.td_left {
		text-align: left;
	}
	a:link{
		text-decoration:none;
	}
</style>
<script type="text/javascript" src="${ctx}/static/core/data/gnhkgs.js?v=${VERSION}"></script>
<script type="text/javascript">
	$(function(){	
	   	//航司控件
	    $("#gn_hkgs_m").autocompleteGnHkgs("gn_hkgs");
	    $("#searchFormButton").click();
	})
 	//改变选择时间类型
	function setRqtj(){
		var rqtj = document.searchForm.rqtj.value;
  		if(rqtj == "2"){
  			document.getElementById("rq1").innerHTML="起飞日始";
  			document.getElementById("rq2").innerHTML="起飞日止";
  		}else{
  			document.getElementById("rq1").innerHTML="发布日始";
  			document.getElementById("rq2").innerHTML="发布日止";
  		}
	}
	
	
	function sendM(){
		var str="";
		$('input[type="checkbox"]:checkbox:checked').each(function() {
			if($(this).attr("nxsj") == "undefined" || $(this).attr("nxsj") == null || $(this).attr("nxsj") == "" ){
				str="";
			}else{
				str+=$(this).attr("nxsj")+" ";
			}
	    });
		var count = $("input[name='nxsj']:checked").length;
		if(count == "0"){
			alert("未选择需要发送短信的订单！");
		}else{
			$.layer({
				type: 2,
			   	title: ['航班异动短信发送'],
			   	area: ['600px', '330px'],
			   	iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/viewsendMessage?hbh=${param.hbh }&hc=${param.hc }&cfrq=${param.cfrq }&zt=${param.zt }&yjcfsj=${param.yjcfsj }&id=${param.id }&str="+str}
			});
		}
    }
   
	function HbydYcl(){
		var count = $("input[name='nxsj']:checked").length;
		if(count == "0"){
			alert("未选择需要处理的订单！");
		}else{
			$.ajax({
				type:"post",
				url:"${ctx}/vedsb/jphbyd/jphbyd/jphbydmxQxClSave?ydid=${param.id }",
				success:function(result){
					if("1" == result){
						layer.msg("航班异动取消处理成功！",2,1);
					}else{
						layer.msg("航班异动取消处理失败！",2,0);
					}
					location.reload();
				}
			})
		}
    }
	
	function HbydWcl(){
		var count = $("input[name='nxsj']:checked").length;
		if(count == "0"){
			alert("未选择需要处理的订单！");
		}else{
			$.layer({
				type: 2,
				title: ['航班异动处理'],
				area: ['400px', '160px'],
				iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/viewhbydmxedit?id=${param.id }"}
		    });
		}
    }
    
	function deleteById(id){
		//弹出删除框
		$.layer({
			area: ['400px', '150px'],
			dialog: {
				msg: '确定删除该选项?',
			    btns: 2,
			    type: 4,
			    btn: ['确定','取消'],
			    yes: function(){
			    	layer.msg('删除成功', 1, 2);
			    	parent.location.href="${ctx}/vedsb/jphbyd/jphbyd/delete_"+ id + "?turningUrl=/vedsb/jphbyd/jphbyd/viewlist";
			    }, no: function(){
			    	layer.msg('放弃删除', 1, 3);
			    }
			}
		});
	}
	
	function set(){
		$.layer({
			type: 2,
			title: ['航班异动规则设置'],
			area: ['800px', '440px'],
			iframe: {src: "${ctx}/vedsb/jphbyd/bqinfohbydgz/queryList"}
	    });
	}
	
	function hbydCl(hbh,hc,cfrq){
		$.layer({
			type: 2,
			title: ['航班异动处理'],
			area: ['800px', '440px'],
			iframe: {src: "${ctx}/vedsb/jphbyd/jphbyd/jphbydClAll?hbh="+hbh+"&hc="+hc+"&cfrq="+cfrq}
	    });
	}
	
	function selectall(obj){
		$("input[name=nxsj]").attr("checked", obj.checked);
	}
</script>
<script id="tpl_list_table" type="text/html">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		<tr>
			<th width="5%">序号</th>
			<th width="5%"><input type="checkbox" name="checkboxall" nxsj=" " onclick="selectall(this);"/></th>
			<th width="10%">PNR</th>
			<th width="10%">航程</th>
			<th width="10%">出发时间</th>
			<th width="10%">PNR状态</th>
			<th width="10%">订单状态</th>
            <th width="8%">乘机人</th>
            <th width="8%">联系人</th>
			<th width="15%">联系人电话</th>
			<th width="10%">短信</th>
			{{# if(${param.lx } != '3'){  }}
				<th width="10%">处理状态</th>
				<th width="20%">处理说明</th>
				<th width="15%">处理时间</th>
			{{# } }}
			<th width="8%">预订员</th>
			<th width="16%">预订时间</th>
		</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
				<td class="td_center">{{ i+1 }}</td>
				<td class="td_center"><input type="checkbox" name="nxsj" cjr="{{ d.list[i].CJR }}" nxsj="{{ d.list[i].NXSJ }}" disabled /></td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_PNR_NO) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].HC) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CFSJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].XS_PNR_ZT) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DDZT)== '3' ? '已出票' : '未出票' }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].CJR) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].NXR) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].NXSJ) }}</td>
				<td class="td_center">{{  $.nullToEmpty(d.list[i].SFYFDX)== '0' ? '未发' : '已发' }}</td>
				{{# if(${param.lx } != '3'){  }}
					<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_ZT)== '0' ? '未处理' : '已处理' }}</td>
					<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_BZ) }}</td>
					<td class="td_center">{{  $.nullToEmpty(d.list[i].CL_DATETIME) }}</td>
				{{# } }}			
				<td class="td_center">{{  $.nullToEmpty(d.list[i].DDYH) }}</td>
				<td class="td_center">{{  $.dateF(d.list[i].DDSJ,'MM-dd HH:mm') }}</td>
    		</tr>
		{{# } }}
	</table>
</script>
</head>
<body>
<div class="container_clear">
 	<div id="search_bar" class="mt10">
	&nbsp;航程:${param.hc }&nbsp;&nbsp;&nbsp;航班:${param.hbh }&nbsp;&nbsp;&nbsp;起飞日期:${param.cfrq }&nbsp;&nbsp;&nbsp;航变原因:${ydyy }
    <div class="box">
    <div class="box_border">
	<form action="${ctx}/vedsb/jphbyd/jphbyd/jphbydClAll" id="searchForm" name="searchForm" method="POST">
       	<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
		<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
		<input type="hidden" name="id" value="${param.id }"/>
		<input type="hidden" name="hbh" value="${param.hbh }"/>
		<input type="hidden" name="hc" value="${param.hc }"/>
		<input type="hidden" name="cfrq" value="${param.cfrq }"/>
		<input type="hidden" name="zt" value="${param.zt }"/>
		<input type="hidden" name="yjcfsj" value="${param.yjcfsj }"/>
		<input type="hidden" name="lx" value="${param.lx }">
        <table class="table01" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="td_right">订单状态:</td>
				<td class="td_left" colspan="2">
					<input type="radio" name="ddzt" value="" checked="checked" /> 全部
					<input type="radio" name="ddzt" value="3" />已出票
					<input type="radio" name="ddzt" value="1" />未出票
				</td>
				<td class="td_right">是否发送短信:</td>
				<td class="td_left">
					<input type="radio" name="sfyfdx" value="0" checked="checked"/>未发送
         			<input type="radio" name="sfyfdx" value="1" />已发送
				</td>
				<td style="text-align: center;" colspan="2">
					<input type="button" id="searchFormButton"  class="ext_btn ext_btn_submit" value="查询">
					<c:if test="${not empty param.lx && param.lx eq '1' }">
         				<input type="button" id="releaseButton" name="button" value="发送短信" onclick="sendM();" class="ext_btn ext_btn_success" />
					</c:if>
					<c:if test="${not empty param.lx && param.lx eq '2' }">
						<input type="button" id="setButton" name="button" value="取消处理" onclick="HbydYcl();" class="ext_btn ext_btn_success" />
						<input type="button" id="releaseButton" name="button" value="发送短信" onclick="sendM();" class="ext_btn ext_btn_success" />
					</c:if>
					<c:if test="${not empty param.lx && param.lx eq '3' }">
						<input type="button" id="setButton" name="button" value="处理" onclick="HbydWcl();" class="ext_btn ext_btn_success" />
						<input type="button" id="releaseButton" name="button" value="发送短信" onclick="sendM();" class="ext_btn ext_btn_success" />
					</c:if>
				</td>
			</tr>
        </table>
	</form>
    </div>
    </div>
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