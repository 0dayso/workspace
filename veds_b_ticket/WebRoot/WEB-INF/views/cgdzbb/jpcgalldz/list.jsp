<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
  <title>采购对账结果查询</title>
  	<script type="text/javascript">
		$(function(){
			$("#searchFormButton").click();
		})
		function createCgdz(){
			if($("#dzrq_cj").val() == ""){
				$("#dzrq_cj").focus();
				layer.tips('请输入需要创建的对账日期！', $("#dzrq_cj"), {
				    time: 3,
				    closeBtn:[0, true]
				});
				return ;
			}
			$.ajax({
   	 			type:"post",
					url:"${ctx}/vedsb/cgdzbb/jpcgalldz/createCgdz?dzrq="+$("#dzrq_cj").val(),
					success:function(result){
						if(result == 0){
							layer.msg("创建失败："+$("#dzrq_cj").val()+"日期已经创建",2,1);
						}else{
							$("#searchFormButton").click();
						}
					}
   	 		});
		}
		function delDbresult(id){
			layer.confirm('你确定删除数据重新对账吗？',function(index){
					$.ajax({
		   	 			type:"post",
						url:"${ctx}/vedsb/cgdzbb/jpcgalldz/delDbresult?id="+id,
						success:function(result){
							if(result.result == 0){
								layer.msg("删除数据重新对账失败："+result.error,2,1);
							}else{
								layer.msg("删除数据重新对账成功！"+result.error,2,1);
								$("#searchFormButton").click();
							}
						}
			 		});
			});
		}
		function  modify(id){
			window.open("${ctx}/vedsb/cgdzbb/jpcgalldz/daycgdz?id="+id);
		}
  	</script>
  	<!-- 模板 -->
   <script id="tpl_list_table" type="text/html">
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	  <tr>
        <th width="20">序号</th>
        <th width="70">操作</th>
		<th width="30">对账日期</th>
		<th width="60">对账状态</th>
		<th width="35">审核状态</th>
		
		<th width="35">对账员</th>
		<th width="40">对账时间</th>
		<th width="40">审核员</th>
		<th width="60">审核时间</th>
	 

       </tr>
	   {{# for(var i = 0, len = d.list.length; i < len; i++){ }}
		<tr class="tr">
			<td class="td_center">{{ i+1 }}</td>
			<td class="td_center">
				<a onclick="delDbresult('{{d.list[i].id}}');" style="cursor: pointer;">清理数据</a>
				<a onclick="modify('{{d.list[i].id}}');" style="cursor: pointer;">查看</a>
			</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].dzrq)}}</td>
			<td class="td_center">
				{{# var zt;
					if(d.list[i].dzZt == "0"){
						zt = "<font>未对账</font>";
					}else if(d.list[i].dzZt == "1"){
						zt = "<font color='green'>核对无误已到账</font>";
					}else if(d.list[i].dzZt == "2"){
						zt = "<font color='red'>对账有异常</font>";
					}else if(d.list[i].dzZt == "3"){
						zt = "<font color='blue'>核对无误未到账</font>";
					}
				}}
				{{zt}}
            </td>
			<td class="td_center">
				{{# var zt;
					if(d.list[i].shZt == "0"){
						zt = "<font >未审核</font>";
					}else if(d.list[i].shZt == "1"){
						zt = "<font color='green'>审核通过</font>";
					}else if(d.list[i].shZt == "2"){
						zt = "<font color='red'>2审核未通过</font>";
					}
				}}
				{{zt}}
            </td>

			<td class="td_center">{{$.nullToEmpty(d.list[i].dzUserid)}}</td>
			<td class="td_center">{{$.dateF(d.list[i].dzDatetime,'yyyy-MM-dd HH:mm') }}</td>
			<td class="td_center">{{$.nullToEmpty(d.list[i].shUserid)}}</td>
			<td class="td_center">{{$.dateF(d.list[i].shDatetime,'yyyy-MM-dd HH:mm') }}</td>
			
			  
		</tr>
	   {{# } }}
	 </table>
   </script>
  </head>
  <body>
    <div class="container clear">
  	  <div id="search_bar">
       <div class="box">
          <div class="box_border">
            <div class="box_center pt10 pb10">
               <form action="${ctx}/vedsb/cgdzbb/jpcgalldz/getCgdzList" id="searchForm" name="searchForm" method="post">
	           	<input type="hidden"  name="VIEW" value="7dcf337d7210f2bc98b162a3c350e3d1" id="view"/>
	            <input type="hidden"  name="pageNum" value="${param.pageNum }" id="pageNum"/>
				<input type="hidden"  name="pageSize" value="10" id="pageSize"/>
				<input type="hidden"  name="orderBy" value="dzrq desc" id="orderBy"/>
	              <table class="table01" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="xsys" style="text-align: right;">对账日期始</td>
						<td>
						  <input type="text" name="search_GTE_dzrq" id="dzrqs" value="${vfn:dateShortPre(-7)}" size=10  onClick="WdatePicker()">
						</td>
						<td class="xsys" style="text-align: right;">对账日期止</td>
						<td>
						  <input type="text" name="search_LTE_dzrq" id="dzrqz" value="${vfn:dateShort()}"  size=10  onClick="WdatePicker()">
						</td>
		                <td>
		                  <input type="button" class="ext_btn ext_btn_success" value="查询" id="searchFormButton">
		                </td>
		                  <td>
		                  
		                  <input type="text"   id="dzrq_cj" value="${vfn:dateShort()}" size=10  onClick="WdatePicker()">
		                  <input type="button" class="ext_btn ext_btn_success" value="创建对账"  onclick="createCgdz();">
		                </td>
					  </tr>
					</table>
	              </form>
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
        <font color=""></font>
     </div>
   </div>
  </div>
  </body>
</html>
