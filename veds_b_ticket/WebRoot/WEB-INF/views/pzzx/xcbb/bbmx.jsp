<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
 <title>行程单详情</title>
	<script src="${ctx}/static/core/validform-rjboy/Validform_v5.3.2_min.js?v=${VERSION}" type="text/javascript"></script>
 <script type="text/javascript">
 	PZZT = '${vfn:toJSON(vfn:dictList('PZZT'))}';
 	XCDZT = '${vfn:toJSON(vfn:dictList('XCDZT'))}';
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
						var sel='${param.BMBH}'==list[i].mc ? 'selected':''
						var $opt="<option value="+list[i].id+" "+sel+">"+list[i].mc+"</option>";
						$("#bmbh").append($opt);
					}
				}
			}
		});
 	});
 	
 </script>
       
   <script id="tpl_list_table" type="text/html">	
	  <table  class="list_table"  border="0" cellpadding="0" cellspacing="0" >
     <tr>
		<th width="5">序号</th>
		<th width="20">行程单号</th>
		<th width="15">OFFICE号</th>
		<th width="20">发放到部门</th>
        <th width="30">发放日期</th>
        <th width="35">状态</th>
		<th width="28">票号</th>
		<th width="25">PNR</th>
		<th width="30">打印部门</th>
		<th width="25">打印人</th>
		<th width="40">打印时间</th>
		<th width="25">作废人</th>
		<th width="40">作废时间</th>
		<th width="25">收回人</th>
		<th width="40">收回时间</th>
     </tr>   
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
       <tr class="tr">
		<td class="td_center">{{ i+1 }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].xcdNo) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].officeid) }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.OUTYHBH.bmmc )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].outDatetime,'yyyy-MM-dd HH:mm')}}</td>
        <td class="td_center">{{$.findJson(XCDZT,d.list[i].pzzt).mc}}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].tkno) }}</td>
		<td class="td_center">{{ $.nullToEmpty(d.list[i].pnrno) }}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.JBYHBH.bmmc )}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.JBYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].jbDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.TFYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].tfDatetime,'yyyy-MM-dd HH:mm')}}</td>
		<td class="td_left">{{ $.nullToEmpty(d.list[i].ex.CKYHBH.xm )}}</td>
		<td class="td_center">{{$.dateF(d.list[i].ckDatetime,'yyyy-MM-dd HH:mm')}}</td>
       </tr>
	   {{# } }}
	 </table>
   </script>
   

  </head>
  <body>
  <!--页签 -->
   <div class="container_clear">
  	  <div id="search_bar" class="mt10">
       	<div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
            <form action="${ctx}/vedsb/pzzx/xcbb/getListByYhbh" id="searchForm" name="searchForm" method="post">
           	<input type="hidden"  name="VIEW" value="46faaed8d140d8486dd161b8b990df95" />
            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
			<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
                <table class="table01" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>起始单号:</td>
                  <td><input type="text" name="xcdNo_start" value="${param.startno}" size="12" class="input-text lh25"/></td>
                  <td>终止单号:</td>
                  <td><input type="text" name="xcdNo_end" size="12" value="${param.endno}" class="input-text lh25"/></td>
            	  <td>部&nbsp;&nbsp;门:</td>
             	  <td>
             	  		<!-- 
             	  		<input type="text" name="outBmbh" style="width:125px"  value="${param.BMBH}" class="input-text lh25"/>
             	  		 -->
             	  		<select id="bmbh" name="outBmbh" style="width: 125px;height: 24px;">
	 						<option value="">请选择</option>
						 </select>
             	  </td>
             	  <td></td>
             	  <td></td>
             	  <td></td>
             	</tr>
             	<tr>
              	  <td>开始日期:</td>
                  <td><input type="text" name="rkDatetime_start"   class="input-text Wdate" value="${param.ksrq}"  size="12" onClick="WdatePicker()"/></td>
                  <td>结束日止:</td>	
                  <td><input type="text" name="rkDatetime_end"   class="input-text Wdate"  value="${param.jsrq}" size="12" onClick="WdatePicker()"/></td>
              	  <td>状&nbsp;&nbsp;态:</td>
			    <td>
				  <select name="pzzt" style="width:130px;height: 24px" >
				    <option value=""  ${empty param.pzzt  ? 'selected' : '' }>==全部==</option>
				    <option value="0" ${param.pzzt eq '0' ? 'selected' : '' }>在库</option>
				    <option value="1" ${param.pzzt eq '1' ? 'selected' : '' }>未使用</option>
				    <option value="10" ${param.pzzt eq '10' ? 'selected' : ''}>已使用</option>
				    <option value="2" ${param.pzzt eq '2' ? 'selected' : '' }>出票</option>
				    <option value="3" ${param.pzzt eq '3' ? 'selected' : '' }>未创建已作废未回收</option>
				    <option value="4" ${param.pzzt eq '4' ? 'selected' : '' }>未创建已作废已回收</option>
				    <option value="5" ${param.pzzt eq '5' ? 'selected' : '' }>已创建已作废未回收</option>
				    <option value="6" ${param.pzzt eq '6' ? 'selected' : '' }>已创建已作废已回收</option>
				    <option value="7" ${param.pzzt eq '7' ? 'selected' : '' }>退废票已作废未回收</option>
				    <option value="8" ${param.pzzt eq '8' ? 'selected' : '' }>退废票已作废已回收</option>		
				    <option value="9" ${param.pzzt eq '9' ? 'selected' : '' }>报废</option>
				  </select>
			    </td>
               <td>
               		<input type="button" id="searchFormButton" name="button" value="查 询" class="ext_btn ext_btn_submit"/>
               </td>
               <td>
               		<input type="button" id="export" name="button" value="导 出" class="ext_btn ext_btn_submit" />
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



