<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<style>
	.ddgl{
		text-align:left; 
	}
	.ddglName{
		text-align:right; 
	}
	a{ 
		text-decoration: none;
	}
</style>
<script type="text/javascript" src="${ctx}/static/js/miniboard.js"></script>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/shareto/shareto_float.js"></script>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
<script type="text/javascript" src="${ctx}/static/js/copy.js"></script>
<script type="text/javascript">
	$(function(){
    	 $("#searchFormButton").click();
	});
	
	//订单详
	function detail(id){
		var url = "${ctx}/vedsb/jpddgl/jpkhdd/detail_"+id;
		window.open(url);
	}
	
	function xepnr(ddbh,pnrno,xelx){
	  var url="${ctx}/vedsb/xepnrgl/jpxepnr/xe" ;
	    $.layer({
		 area:['250px', '150px'],
		 title:["<b>"+xelx+"=>PNR:<font color='red'>"+pnrno+"</font></b>"],
		 dialog:{
		 msg:"您确定要XE[<font color='red'>"+pnrno+"</font>]吗?",
		 btns:2,
		 type:4,
		 yes: function(){
		   var i=layer.load('系统正在处理您的操作,请稍候');
			   $.ajax({
				   url:url,
				   type:"post",
				   data:{ddbh:ddbh,pnrno:pnrno,xelx:xelx},
				   success:function(result){
							 layer.close(i);
							 if(result.status=="1" || result.status=="2"){
								layer.msg(result.errmsg,1,1);
							 }else{
								layer.msg(result.errmsg,1,3); 
							 }
							 refresh();
						 } 
			          });
		      },
		   no: function(){
		    	layer.load('放弃XE',1,4)	  
		   }
		 }
	   });
	}
	
	//查看异动日志
	function enterLogPage(ddbh){
		var url="${ctx}/vedsb/jpddgl/jpkhddczrz/enterLogPage_"+ddbh+"?ywlx=01";
		$.layer({
			type: 2,
			title: ['<b>订单异动日志</b>'],
			area: ['650px', '360px'],
			iframe: {src: url}
	    });
	}
	
</script>

<!-- 1全部出票 2部分出票  0未出票 -1数据错误 -->
<script id="tpl_list_table" type="text/html">
<div>
	<table width="2000px" border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<th width="20">序号</th>
		<th width="50">PNR</th>
		<th width="80">订单编号</th>
		<th width="50">OFFICE</th>
		<th width="50">乘机人</th>
		<th width="50">航程</th>
		<th width="50">验证证件</th>
		<th width="50">验证航段</th>
		<th width="50">XE类型</th>
		<th width="50">XE状态</th>
		<th width="50">取消原因</th>
		<th width="120">取消失败原因</th>
		<th width="50">取消人</th>
		<th width="80">取消时间</th>
		<th width="50">操作来源</th>
		<th width="50">创建用户</th>
		<th width="80">创建时间</th>
		<th width="120">PNR内容</th>
	</tr>
		{{# for(var i = 0, len = d.list.length; i < len; i++){ }}
			<tr class="tr">
       			<td class="td_center">{{ i+1 }}</td>
				<td class="td_center">
                     <a href="###" class="copytext" copytext="{{ $.nullToEmpty(d.list[i].PNR_NO) }}" name="pnrno_{{i}}" onclick="enterLogPage('{{d.list[i].DDBH}}');">{{ $.nullToEmpty(d.list[i].PNR_NO) }}</a>
				</td>
				<td class="td_center"><a href="###" onclick="detail('{{d.list[i].DDBH}}');">{{ $.nullToEmpty(d.list[i].DDBH)   }}</a></td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].OFFICEID)}}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].CJR)    }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].HD)     }}</td>
				<td class="td_center">
					{{# 
                    	var sfyzzj= $.nullToEmpty(d.list[i].SFYZZJ); 
                        var ztmc=sfyzzj == "0" ? "否":"是";
					}}
                   	{{ztmc}}
				</td>
				<td class="td_center">
					{{# 
                    	var sfyzhd= $.nullToEmpty(d.list[i].SFYZHD); 
                        var ztmc=sfyzhd == "0" ? "否":"是";           
					}}
                   	{{ztmc}}
				</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].XELX)   }}</td>
				<td class="td_center">
					{{# 
                    	var ztmc; 
                    	var xezt=$.nullToEmpty(d.list[i].XEZT); 
						var ddbh=$.nullToEmpty(d.list[i].DDBH);
                        var pnrno=$.nullToEmpty(d.list[i].PNR_NO); 
						var xelx=$.nullToEmpty(d.list[i].XELX); 
						if(xezt == "0" ){ 
							ztmc="<a href='###' onclick=xepnr('"+ddbh+"','"+pnrno+"','"+xelx+"')><font color='blue'>待取消</font></a>";
						}else if(xezt == "1" ){ 
							ztmc="<font color='green'>已取消</font>";
						}else if(xezt == "2" ){ 
							ztmc="<a href='###' onclick=xepnr('"+ddbh+"','"+pnrno+"','"+xelx+"')><font color='red'>取消失败</font></a>";
						}
					}}
                   	{{ztmc}}
                </td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].XESY)   }}</td>
				<td class="td_center">{{ $.cut(d.list[i].XESBYY,20) }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].XEYH)   }}</td>
				<td class="td_center">{{ $.dateF(d.list[i].XESJ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].CZLY)   }}</td>
				<td class="td_center">{{ $.nullToEmpty(d.list[i].CJYH)   }}</td>
				<td class="td_center">{{ $.dateF(d.list[i].CJSJ,'MM-dd HH:mm') }}</td>
				<td class="td_center">{{ $.cut(d.list[i].PNR_LR,20)      }}</td>
    		</tr>
		{{# } }}
	</table>
</div>
</script>
</head>
<body>
<c:set var="gngj" value="${empty param.gngj ? '1' : param.gngj }"></c:set>
<!--网店页签 -->
<%@include file="/WEB-INF/views/cpkzt/cpkzt/list_title.jsp"%>
<div class="container">
  	  	<div id="search_bar">
       		<div class="box">
          		<div class="box_border">
            		<div class="box_center pt10 pb10">
            			<form action="${ctx}/vedsb/xepnrgl/jpxepnr/query" id="searchForm" name="searchForm" method="post" target="_blank">
            				<input type="hidden"  name="VIEW" value="692a3b3046e69162f490ff0c1e16bcf1" />
            				<input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
							<input type="hidden"  name="orderBy" value="cjsj desc" id="orderBy"/>
							<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
							<input type="hidden"  name="lx" value="${lx}" id="lx"/>
							<input type="hidden"  name="gngj" value="${empty param.gngj ? '1' : param.gngj}"/>
              				<table class="table01" border="0" cellpadding="0" cellspacing="0"> 
              					<tr>
              						<td id = "rs" class="ddglName">创建日始</td>
                  					<td><input type="text" name="ksrq" value="${empty param.ksrq ? vfn:dateShort() : param.ksrq}" class="input-text Wdate"style="width:100px;height:24px;" size="10" id="mindate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxdate\')}'})"></td> 
                  					<td id = "rz" class="ddglName">创建日止</td>
                 					<td><input type="text" name="jsrq" style="width:100px;height:24px;" value="${empty param.jsrq ? vfn:dateShort() : param.jsrq}" class="input-text Wdate"  id="maxdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'mindate\')}'})"></td>
              						<td class="ddglName">
              						P N R 
              						</td>
              						<td class="ddgl">
              							<input type="text" name="pnr_no" class="input-text lh25" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              						<td class="ddglName">
              							航    程
              						</td>
              						<td class="ddgl">
              							<input type="text" name="hc" class="input-text lh25" style="width:100px;height:24px;" onblur="value=$.trim(this.value).toUpperCase();" size="10" />
              						</td>
              					</tr>
              					<tr>
              						<td class="ddglName">
              							乘 机 人
              						</td>
              						<td class="ddgl">
              							<input type="text" name="cjr" class="input-text lh25" style="width:100px;height:24px;" size="10" />
              						</td>
              						<td class="ddglName">
              							XE类型
              						</td>
              						<td>
              							<select name="xelx" style="width:102px;height:26px;">
              								<option value="">全部</option>
              								<option value="XEPNR">XEPNR</option>
              								<option value="XECJR">XECJR</option>
              							</select>
              						</td>
              						<td class="ddglName">
              							XE状态
              						</td>
              						<td>
              							<select name="xezt" style="width:102px;height:26px;">
              								<option value="">全部</option>
              								<option value="0">待取消</option>
              								<option value="1">已取消</option>
              								<option value="2">取消失败</option>
              							</select>
              						</td>
              						<td class="ddglName">
              							操作来源
              						</td>
              						<td>
              							<select name="czly" style="width:102px;height:26px;">
              								<option value="">全部</option>
              								<option value="降舱出票">降舱出票</option>
              								<option value="换编码出票">换编码出票</option>
              								<option value="全自动退票">全自动退票</option>
              								<option value="退票">退票</option>
              							</select>
              						</td>
              						<td align="right" colspan="1">
										<input type="button" id="searchFormButton" name="button" value="查询"  class="ext_btn ext_btn_submit" />
									</td>
								</tr>
              				</table>
              			</form>
            		</div>
          		</div>
        	</div>
		</div>
      	<div  class="mt10" style="display:table;">
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